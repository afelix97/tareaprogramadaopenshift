package mx.com.aforecoppel.registromovilguardardatos.processors;

import java.util.Date;

import mx.com.aforecoppel.dao.ExcepcionesDAO;
import mx.com.aforecoppel.dto.SolicitudServicioDTO;
import mx.com.aforecoppel.registromovilguardardatos.utils.ConstantesExcepciones;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessActualizarSolicitudServicio implements Processor {

    final static Logger logger = LoggerFactory.getLogger(ProcessActualizarSolicitudServicio.class);

    private final ConstantesExcepciones constantesExcepciones;

    private final ExcepcionesDAO excepcionesDAO;

    @Autowired
    public ProcessActualizarSolicitudServicio(ConstantesExcepciones constantesExcepciones, ExcepcionesDAO excepcionesDAO) {
        this.constantesExcepciones = constantesExcepciones;
        this.excepcionesDAO = excepcionesDAO;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String folio = "";

        String cMessageLog = exchange.getIn().getHeader("messageLog") != null && !exchange.getIn().getHeader("messageLog").toString().equals("") ? exchange.getIn().getHeader("messageLog").toString() : "";

        logger.info(cMessageLog + ":: OBTENIENDO SOLICITUD SERVICIO PARA ACTUALIZAR::");

        SolicitudServicioDTO solicitudServicioDto = (SolicitudServicioDTO) exchange.getProperty("SOLICITUD_SERVICIO_DTO");

        if (exchange.getIn().getHeader("curp") != null && !exchange.getIn().getHeader("curp").toString().equals("")) {
            folio = exchange.getIn().getHeader("curp").toString().trim();
        } else {
            folio = "";
        }

        logger.debug(cMessageLog + ":: SOLICITUD OBTENIDA:: " + solicitudServicioDto.getKeyx());

        logger.debug(cMessageLog + ":: ACTUALIZANDO SOLICITUD CON FOLIO " + "" + " ::");

        solicitudServicioDto.setFechafinal(new Date());
        solicitudServicioDto.setFolioack(folio);
        solicitudServicioDto.setClaveestatus(constantesExcepciones.EN_COLA_DE_MENSAJERIA);
        excepcionesDAO.updateSolicitudServicio(solicitudServicioDto);
        logger.debug(cMessageLog + "SOLICITUD ACTUALIZADA ::");
    }

}
