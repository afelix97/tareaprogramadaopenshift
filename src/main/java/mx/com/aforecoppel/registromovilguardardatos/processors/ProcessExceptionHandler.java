package mx.com.aforecoppel.registromovilguardardatos.processors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;

import mx.com.aforecoppel.dao.ExcepcionesDAO;
import mx.com.aforecoppel.dto.ConfiguracionAlertaDTO;
import mx.com.aforecoppel.dto.ConstantesExcepciones;
import mx.com.aforecoppel.dto.ExcepcionesDTO;
import mx.com.aforecoppel.dto.PeticionServicioDTO;
import mx.com.aforecoppel.dto.SolicitudServicioDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessExceptionHandler implements Processor {

    final static Logger logger = LoggerFactory.getLogger(ProcessExceptionHandler.class);

    private final ConfiguracionAlertaDTO configuracionAlertaDefaultDto;

    private final ExcepcionesDAO excepcionesDAO;

    @Autowired
    public ProcessExceptionHandler(ConfiguracionAlertaDTO configuracionAlertaDefaultDto, ExcepcionesDAO excepcionesDAO) {
        this.configuracionAlertaDefaultDto = configuracionAlertaDefaultDto;
        this.excepcionesDAO = excepcionesDAO;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        java.lang.String cMessageLog =
                exchange.getIn().getHeader("messageLog") != null && !exchange.getIn().getHeader("messageLog").toString().equals("") ? exchange.getIn().getHeader("messageLog").toString() : "";

        try {
            @SuppressWarnings("unchecked")
            ArrayList<Exception> aExcepciones = (ArrayList<Exception>) exchange.getIn().getHeader("AEXCEPCIONES");
            Exception contextoCamelException = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

            SolicitudServicioDTO solicitudServicioDto = (SolicitudServicioDTO) exchange.getProperty("SOLICITUD_SERVICIO_DTO");
            PeticionServicioDTO peticionServicioDto = (PeticionServicioDTO) exchange.getProperty("PETICION_SERVICIO_DTO");
            int iNumeroIntento = (int) exchange.getProperty("NUMERO_INTENTO");

            ConfiguracionAlertaDTO configuracion = null;

            //REGISTRAMOS EXCEPCION EN EXCHANGE.
            exchange.setProperty("RUTA_ERROR", "SI");

            if (contextoCamelException != null) {
                // 1.-DETERMINAR EXCEPCIONES
                if (aExcepciones != null) {
                    aExcepciones = eliminarDuplicados(aExcepciones);
                } else {
                    aExcepciones = new ArrayList<Exception>();
                    aExcepciones.add(contextoCamelException);
                }

                // 2.- ACTUALIZAR SOLICITUD DE SERVICIO FUSE
                solicitudServicioDto.setFechafinal(new Date());
                solicitudServicioDto.setClaveestatus(ConstantesExcepciones.ERROR);
                solicitudServicioDto.setFolioack("curp");
                excepcionesDAO.updateSolicitudServicio(solicitudServicioDto);

                Collections.sort(aExcepciones, new Comparator<Exception>() {
                    @Override
                    public int compare(Exception o1, Exception o2) {
                        return getCanonicalName(o2).compareTo(getCanonicalName(o1));
                    }
                });

                Exception exception = aExcepciones.get(0);
                //for (Exception exception : aExcepciones) {
                logger.warn(cMessageLog + " EXCEPCION -->" + getCanonicalName(exception));
                logger.warn(cMessageLog + " EXCEPCION_STACK_TRACE -->" + ExceptionUtils.getStackTrace(exception));
                ExcepcionesDTO excepcionDto = excepcionesDAO.findByExcepcionAndDescripcion(getCanonicalName(exception), ExceptionUtils.getMessage(exception));
                if (excepcionDto != null) {
                    //3.-CONSULTAR LA ACCION DEL TIPO DE EXCEPCION
                    configuracion = excepcionesDAO.findByClaveConfiguracion(excepcionDto.getClaveconfiguracion());
                    logger.warn(cMessageLog + " LA EXCEPCION REQUIERE REINTENTO -->" + configuracion.getRequierereintento());
                    logger.warn(cMessageLog + " NUMERO DE REINTENTOS -->" + configuracion.getNumeroreintentos());
                    logger.warn(cMessageLog + " LIMITE OCURRENCIA EXCEPCION -->" + configuracion.getLimiteocurrencia());
                } else {
                    logger.warn(cMessageLog + " NO SE ENCONTRO CONFIGURACION PARA LA EXCEPCION, AGREGANDO EXCEPCION DETECTADA");
                    //4.-REGISTRAR EXCEPCION EN BASE DE DATOS - NO ENCONTRADA.
                    configuracion = excepcionesDAO.insertConfiguracionAlerta(configuracionAlertaDefaultDto);
                    excepcionDto = new ExcepcionesDTO();
                    excepcionDto.setExcepcion(getCanonicalName(exception));
                    excepcionDto.setDescripcion(ExceptionUtils.getMessage(exception));
                    excepcionDto.setClaveconfiguracion(configuracionAlertaDefaultDto.getClaveconfiguracion());
                    excepcionDto = excepcionesDAO.insertExcepcion(excepcionDto);

                }
                //5.-ACTUALIZAR PETICION GENERADA.
                peticionServicioDto.setFechafinal(new Date());
                peticionServicioDto.setClaveexcepcion(excepcionDto.getClaveexcepcion());
                StringBuilder sb = new StringBuilder("");
                for (Exception excepcionDetectada : aExcepciones) {
                    sb.append(ExceptionUtils.getStackTrace(excepcionDetectada)).append("|");
                }
                peticionServicioDto.setExcepcionStackTrace(sb.toString());
                peticionServicioDto.setRequest(exchange.getProperty("REQUEST") != null ? (String) exchange.getProperty("REQUEST") : "");
                peticionServicioDto.setResponse(exchange.getProperty("RESPONSE") != null ? (String) exchange.getProperty("RESPONSE") : "");
                excepcionesDAO.updatePeticionServicio(peticionServicioDto);

                //6.-VERIFICAR REINTENTO DE PETICION.
                if (configuracion.getRequierereintento() == ConstantesExcepciones.REQUIERE_REINTENTO) {
                    // 6.1.-ACTUALIZAR FOLIOSERVICIO AFORE A FALLIDO.
                    if (iNumeroIntento < configuracion.getNumeroreintentos() && iNumeroIntento < ConstantesExcepciones.LIMITE_REINTENTOS) {
                        exchange.setProperty("REINTENTAR", "SI");
                        logger.warn(cMessageLog + " !!!!! REINTENTAR ? -->" + exchange.getProperty("REINTENTAR"));
                    } else {
                        exchange.setProperty("REINTENTAR", "NO");
                        logger.warn(cMessageLog + " !!!!!! REINTENTAR ? -->" + exchange.getProperty("REINTENTAR"));
                    }
                    // 6.2.-ACTUALIZAR FOLIOSERVICIO AFORE A FALLIDO.
                }

                //7.- ENVIAR ALERTA DE EXCEPCION
                if (configuracion.getClaveconfiguracion() != ConstantesExcepciones.NO_ENVIAR_ALERTA) {
                    // 7.1 AGREGAR MENSAJE DE ALERTA.
                    if (esMultiplo((excepcionDto.getNumeroincidencias() + 1), configuracion.getLimiteocurrencia(), cMessageLog)) {
                        configuracion.getCorreos();
                        exchange.getIn().setHeader("ALERTA", "SI");
                        exchange.getIn().setHeader("ALERTA_ENCABEZADO", "Excepcion en Servicio wsregistromovilguardardatos");
                        exchange.getIn().setHeader("ALERTA_SERVICIO", "CONSULTA wsregistromovilguardardatos");
                        exchange.getIn().setHeader("ALERTA_EXCEPCION", peticionServicioDto.getExcepcionStackTrace());
                        exchange.getIn().setHeader("ALERTA_LIMITE_OCURRENCIA", configuracion.getLimiteocurrencia());
                        exchange.getIn().setHeader("ALERTA_CORREOS", configuracion.getCorreos());
                    }
                }
                //8.- ACTUALIZAR ESTADISTICA DE EXCEPCION.
                excepcionDto.setNumeroincidencias(excepcionDto.getNumeroincidencias() + 1);
                excepcionesDAO.updateException(excepcionDto);
                exchange.getIn().setHeader("CLAVE_SOLICITUD_SERVICIO", solicitudServicioDto.getClavesolicitudservicio());
                exchange.getIn().setHeader("KEYX_SOLICITUD_SERVICIO", solicitudServicioDto.getKeyx());

                logger.warn(cMessageLog + " TERMINA EXCEPTION HANDLER ::::::");
            }
        } catch (Exception e) {
            logger.warn(cMessageLog + " EXCEPCION FATAL - EXCEPTION CONSULTA wsavisonorecepcion HANDLER - ::::::" + ExceptionUtils.getStackTrace(e));
            exchange.getIn().setHeader("ALERTA", "SI");
            exchange.getIn().setHeader("ALERTA_ENCABEZADO", "Excepcion en Servicio Consulta wsregistromovilguardardatos");
            exchange.getIn().setHeader("ALERTA_SERVICIO", "Consulta wsregistromovilguardardatos");
            exchange.getIn().setHeader("ALERTA_EXCEPCION", ExceptionUtils.getStackTrace(e));
            exchange.getIn().setHeader("ALERTA_CORREOS", configuracionAlertaDefaultDto.getCorreos());
        }

    }

    public String getCanonicalName(Exception exception) {
        String name = null;
        try {
            name = exception.getClass().getCanonicalName();
        } catch (Exception e) {
            logger.warn("::[WSREGISTROMOVILGUARDARDATOS]:: !!! No se pudo obtener la clase de excepcion. !!!");
        }

        return name;
    }

    public static boolean esMultiplo(int n1, int n2, String cMessageLog) {
        logger.warn(cMessageLog + " ES MULTIPLO ---> " + n1 + " % " + n2);
        if (n1 % n2 == 0) {
            logger.warn(cMessageLog + " SI ES MULTIPLO ::");
            return true;
        } else {
            logger.warn(cMessageLog + " NO ES MULTIPLO ::");
            return false;
        }
    }

    public ArrayList<Exception> eliminarDuplicados(ArrayList<Exception> excepciones) {
        HashSet<Exception> hs = new HashSet<Exception>();
        hs.addAll(excepciones);
        excepciones.clear();
        excepciones.addAll(hs);
        return excepciones;
    }

}
