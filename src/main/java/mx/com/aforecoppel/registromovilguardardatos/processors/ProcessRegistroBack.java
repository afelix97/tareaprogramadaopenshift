package mx.com.aforecoppel.registromovilguardardatos.processors;

import mx.com.aforecoppel.dao.ExcepcionesDAO;
import mx.com.aforecoppel.dto.SolicitudServicioDTO;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessRegistroBack implements Processor {

    final static Logger logger = LoggerFactory.getLogger(ProcessRegistroBack.class);

    private final ExcepcionesDAO excepcionesDAO;

    @Autowired
    public ProcessRegistroBack(ExcepcionesDAO excepcionesDAO) {
        this.excepcionesDAO = excepcionesDAO;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        java.lang.String cMessageLog = exchange.getIn().getHeader("messageLog") != null && !exchange.getIn().getHeader("messageLog").toString().equals("") ? exchange.getIn().getHeader("messageLog").toString() : "";

        try {

            logger.info(cMessageLog + "[ProcessRegistroBack.process]:: SOLICITUD ENCONTRADA CLAVE_SOLICITUD_SERVICIO__IN " + exchange.getIn().getHeader("CLAVE_SOLICITUD_SERVICIO"));
            logger.info(cMessageLog + "[ProcessRegistroBack.process]:: SOLICITUD ENCONTRADA KEYX_SOLICITUD_SERVICIO__IN " + exchange.getIn().getHeader("KEYX_SOLICITUD_SERVICIO"));


            if (exchange.getIn().getHeader("REINTENTAR") != null) {
                logger.info(cMessageLog + "[ProcessRegistroBack.process]:: EL SERVICIO ES UN REINTENTO(WSNotificarSolConstanciaImplica)::");
                exchange.setProperty("REINTENTAR", "SI");
                exchange.setProperty("NUMERO_INTENTO", exchange.getIn().getHeader("NUMERO_INTENTO"));
            } else {
                exchange.setProperty("REINTENTAR", "NO");
                exchange.setProperty("NUMERO_INTENTO", 0);
            }

            SolicitudServicioDTO solicitudServicioDto = excepcionesDAO.findByClaveSolicitudServicioAndKeyx(new Long(exchange.getIn().getHeader("CLAVE_SOLICITUD_SERVICIO").toString()), new Long(exchange.getIn().getHeader("KEYX_SOLICITUD_SERVICIO").toString()));

            exchange.setProperty("SOLICITUD_SERVICIO_DTO", solicitudServicioDto);
            exchange.getIn().setHeader("CLAVE_SOLICITUD_SERVICIO", solicitudServicioDto.getClavesolicitudservicio());
            exchange.getIn().setHeader("KEYX_SOLICITUD_SERVICIO", solicitudServicioDto.getKeyx());

            logger.debug(cMessageLog + "[ProcessRegistroBack.process]:: SOLICITUD OBTENIDA ::" + solicitudServicioDto.getClavesolicitudservicio());
            logger.debug(cMessageLog + "[ProcessRegistroBack.process]:: SOLICITUD KEYX ::" + solicitudServicioDto.getKeyx());
            logger.debug(cMessageLog + "[ProcessRegistroBack.process]:: SOLICITUD FECHA DE REGISTRO ::" + solicitudServicioDto.getFechainicial());

        } catch (Exception e) {
            logger.warn(cMessageLog + " [ProcessRegistroBack.process]:: ERROR EN CONSULTA :: " + ExceptionUtils.getStackTrace(e));
        }
    }

}
