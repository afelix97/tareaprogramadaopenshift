package mx.com.aforecoppel.registromovilguardardatos.processors;

import mx.com.aforecoppel.dao.ExcepcionesDAO;
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
public class ProcessRegistroPeticionServicioBack implements Processor {

	final static Logger logger = LoggerFactory.getLogger(ProcessRegistroPeticionServicioBack.class);

	private final ConstantesExcepciones constantesExcepciones;

    private final ExcepcionesDAO excepcionesDAO;

	private final ServerInfo serverInfo;

    @Autowired
	public ProcessRegistroPeticionServicioBack(ConstantesExcepciones constantesExcepciones, ExcepcionesDAO excepcionesDAO, ServerInfo serverInfo) {
	    this.constantesExcepciones = constantesExcepciones;
	    this.excepcionesDAO = excepcionesDAO;
	    this.serverInfo = serverInfo;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        String cMessageLog = exchange.getIn().getHeader("messageLog") != null && !exchange.getIn().getHeader("messageLog").toString().equals("") ? exchange.getIn().getHeader("messageLog").toString() : "";

        SolicitudServicioDTO solicitudServicioDto = (SolicitudServicioDTO) exchange.getProperty("SOLICITUD_SERVICIO_DTO");
        Integer KEYX_PETICION_SERVICIO = (Integer) exchange.getIn().getHeader("KEYX_PETICION_SERVICIO");
        PeticionServicioDTO peticionServicioDto = null;

        if (exchange.getProperty("REINTENTAR").equals("NO")) {
            peticionServicioDto = excepcionesDAO.findByClaveSolicitudAndKeyx(solicitudServicioDto.getClavesolicitudservicio(), new Long(KEYX_PETICION_SERVICIO));
            logger.info(cMessageLog + "[ProcessRegistroPeticionServicioBack.process]:: NO SE INCREMENTA EL REINTENTO " + exchange.getProperty("NUMERO_INTENTO"));
        } else {
            peticionServicioDto = new PeticionServicioDTO(solicitudServicioDto.getClavesolicitudservicio(),
                    ConstantesExcepciones.REINTENTO,
                    ConstantesExcepciones.DEFAULT,
                    exchange.getIn().getBody(String.class),
                    serverInfo.getServerip(),
                    serverInfo.getServerhostname(),
                    serverInfo.getServercontainer());
            int iNumeroIntento = (int) exchange.getProperty("NUMERO_INTENTO");
            iNumeroIntento = iNumeroIntento + 1;
            logger.info(cMessageLog + "[ProcessRegistroPeticionServicioBack.process]:: SE INCREMENTA EL REINTENTO " + exchange.getProperty("NUMERO_INTENTO"));
            exchange.setProperty("NUMERO_INTENTO", iNumeroIntento++);
            peticionServicioDto = excepcionesDAO.insertPeticionServicio(peticionServicioDto);
        }

        logger.debug(cMessageLog + "[ProcessRegistroPeticionServicioBack.process] !!!!!! PROPIEDADES DEL EXCHANGE INICIAL  -->" + exchange.getProperties());


        exchange.setProperty("PETICION_SERVICIO_DTO", peticionServicioDto);
        exchange.setProperty("REINTENTAR", "NO");

        logger.debug(cMessageLog + "[ProcessRegistroPeticionServicioBack.process]:: PETICION GENERADA ::" + peticionServicioDto.getClavepeticionservicio());
        logger.debug(cMessageLog + "[ProcessRegistroPeticionServicioBack.process]:: PETICION KEYX ::" + peticionServicioDto.getKeyx());
        logger.debug(cMessageLog + "[ProcessRegistroPeticionServicioBack.process]:: PETICION FECHA DE REGISTRO ::" + peticionServicioDto.getFechainicial());
        logger.debug(cMessageLog + "[ProcessRegistroPeticionServicioBack.process]:: PETICION NUMERO DE INTENTO ::" + exchange.getProperty("NUMERO_INTENTO"));

    }

}
