package mx.com.aforecoppel.registromovilguardardatos.processors;

import java.util.Date;

import mx.com.aforecoppel.dao.ExcepcionesDAO;
import mx.com.aforecoppel.dto.PeticionServicioDTO;
import mx.com.aforecoppel.dto.SolicitudServicioDTO;
import mx.com.aforecoppel.registromovilguardardatos.utils.ConstantesExcepciones;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessActualizarSolicitudServicioBack implements Processor {

    final static Logger logger = LoggerFactory.getLogger(ProcessActualizarSolicitudServicioBack.class);

    private final ConstantesExcepciones constantesExcepciones;

    private final ExcepcionesDAO excepcionesDAO;

    @Autowired
    public ProcessActualizarSolicitudServicioBack(ConstantesExcepciones constantesExcepciones, ExcepcionesDAO excepcionesDAO) {
        this.constantesExcepciones = constantesExcepciones;
        this.excepcionesDAO = excepcionesDAO;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String resultadoOperacionAfore = (String) exchange.getIn().getHeader("resultadoOperacionAfore");
        String diagnosticoAfore = (String) exchange.getIn().getHeader("diagnosticoAfore");
        String response = (String) exchange.getIn().getHeader("RESPONSE");

        java.lang.String cMessageLog = exchange.getIn().getHeader("messageLog") != null && !exchange.getIn().getHeader("messageLog").toString().equals("") ? exchange.getIn().getHeader("messageLog").toString() : "";
        String folio = "";


        SolicitudServicioDTO solicitudServicioDto = (SolicitudServicioDTO) exchange.getProperty("SOLICITUD_SERVICIO_DTO");
        PeticionServicioDTO peticionServicioDto = (PeticionServicioDTO) exchange.getProperty("PETICION_SERVICIO_DTO");
        peticionServicioDto.setFechafinal(new Date());
        peticionServicioDto.setResponse(response);

        exchange.setProperty("RESPONSE", response);
        excepcionesDAO.updatePeticionServicio(peticionServicioDto);
        if (exchange.getIn().getHeader("curp") != null && !exchange.getIn().getHeader("curp").toString().equals("")) {
            folio = exchange.getIn().getHeader("curp").toString().trim();
        } else {
            folio = "";
        }
        solicitudServicioDto.setFolioack(folio);
        solicitudServicioDto.setFechafinal(new Date());
        solicitudServicioDto.setClaveestatus(ConstantesExcepciones.PROCESADA);
        excepcionesDAO.updateSolicitudServicio(solicitudServicioDto);
        logger.info(cMessageLog + "[ProcessActualizarSolicitudServicioBack.process]:: SOLICITUD ACTUALIZADA ::");

    }

}
