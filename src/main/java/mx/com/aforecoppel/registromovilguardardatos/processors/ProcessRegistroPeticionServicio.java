package mx.com.aforecoppel.registromovilguardardatos.processors;

import mx.com.aforecoppel.dao.ExcepcionesDAO;
import mx.com.aforecoppel.dao.ExcepcionesImpl;
import mx.com.aforecoppel.dto.PeticionServicioDTO;
import mx.com.aforecoppel.dto.SolicitudServicioDTO;
import mx.com.aforecoppel.exceptionsmodel.utils.ServerInfo;
import mx.com.aforecoppel.registromovilguardardatos.utils.ConstantesExcepciones;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProcessRegistroPeticionServicio implements Processor {

    final static Logger logger = LoggerFactory.getLogger(ProcessRegistroPeticionServicio.class);

    private final ConstantesExcepciones constantesExcepciones;

    private final ExcepcionesDAO excepcionesDAO;

    private final ServerInfo serverInfo;

    @Autowired
    public ProcessRegistroPeticionServicio(ConstantesExcepciones constantesExcepciones, ExcepcionesDAO excepcionesDAO, ServerInfo serverInfo) {
        this.constantesExcepciones = constantesExcepciones;
        this.excepcionesDAO = excepcionesDAO;
        this.serverInfo = serverInfo;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
    	
    	java.lang.String  messageLog				= null;
    	SolicitudServicioDTO solicitudServicioDto 	= null;
    	PeticionServicioDTO peticionServicioDto 	= null;

        solicitudServicioDto = (SolicitudServicioDTO) exchange.getProperty("SOLICITUD_SERVICIO_DTO");
        messageLog = exchange.getIn().getHeader("messageLog") != null && !exchange.getIn().getHeader("messageLog").toString().equals("") ? exchange.getIn().getHeader("messageLog").toString() : "";
        
        if (exchange.getProperty("REINTENTAR").equals("NO")) {
            peticionServicioDto = new PeticionServicioDTO(solicitudServicioDto.getClavesolicitudservicio(),
                    constantesExcepciones.NORMAL,
                    constantesExcepciones.DEFAULT,
                    exchange.getIn().getBody(String.class),
                    serverInfo.getServerip(),
                    serverInfo.getServerhostname(),
                    serverInfo.getServercontainer());
            logger.info(messageLog + "[ProcessRegistroPeticionServicio.process]:: " + "NO SE INCREMENTA EL REINTENTO " + exchange.getProperty("NUMERO_INTENTO"));
        } else {
            peticionServicioDto = new PeticionServicioDTO(solicitudServicioDto.getClavesolicitudservicio(),
                    constantesExcepciones.REINTENTO,
                    constantesExcepciones.DEFAULT,
                    exchange.getIn().getBody(String.class),
                    serverInfo.getServerip(),
                    serverInfo.getServerhostname(),
                    serverInfo.getServercontainer());
            int iNumeroIntento = (int) exchange.getProperty("NUMERO_INTENTO");
            iNumeroIntento = iNumeroIntento + 1;
            logger.info(messageLog + "[ProcessRegistroPeticionServicio.process]:: " + "SE INCREMENTA EL REINTENTO(WSREGISTROMOVILGUARDARDATOS) ::" + exchange.getProperty("NUMERO_INTENTO"));
            exchange.setProperty("NUMERO_INTENTO", iNumeroIntento++);
        }
        logger.debug(messageLog + "[ProcessRegistroPeticionServicio.process]:: " + "!!!!!! PROPIEDADES DEL EXCHANGE INICIAL  -->" + exchange.getProperties());
        peticionServicioDto = excepcionesDAO.insertPeticionServicio(peticionServicioDto);
        exchange.setProperty("PETICION_SERVICIO_DTO", peticionServicioDto);
        exchange.setProperty("REINTENTAR", "NO");
        exchange.getIn().setHeader("CLAVE_PETICION_SERVICIO", peticionServicioDto.getClavepeticionservicio());
        exchange.getIn().setHeader("KEYX_PETICION_SERVICIO", peticionServicioDto.getKeyx());

        logger.debug(messageLog + "[ProcessRegistroPeticionServicio.process]:: " + "PETICION GENERADA:: " + peticionServicioDto.getClavepeticionservicio());
        logger.debug(messageLog + "[ProcessRegistroPeticionServicio.process]:: " + "PETICION KEYX:: " + peticionServicioDto.getKeyx());
        logger.debug(messageLog + "[ProcessRegistroPeticionServicio.process]:: " + "PETICION FECHA DE REGISTRO:: " + peticionServicioDto.getFechainicial());
        logger.debug(messageLog + "[ProcessRegistroPeticionServicio.process]:: " + "PETICION NUMERO DE INTENTO:: " + exchange.getProperty("NUMERO_INTENTO"));
        
        messageLog				= null;
    	solicitudServicioDto 	= null;
    	peticionServicioDto 	= null;
        
    }

}
