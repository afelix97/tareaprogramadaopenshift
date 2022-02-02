package mx.com.aforecoppel.registromovilguardardatos.processors;

import java.util.Date;

import mx.com.aforecoppel.dao.ExcepcionesDAO;
import mx.com.aforecoppel.dto.ConstantesExcepciones;
import mx.com.aforecoppel.dto.PeticionServicioDTO;
import mx.com.aforecoppel.dto.SolicitudServicioDTO;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessActualizarSolicitudServicioBusTramites implements Processor {

    final static Logger logger = LoggerFactory.getLogger(ProcessActualizarSolicitudServicioBusTramites.class);

    private final ExcepcionesDAO excepcionesDAO;

    @Autowired
    public ProcessActualizarSolicitudServicioBusTramites(ExcepcionesDAO excepcionesDAO) {
        this.excepcionesDAO = excepcionesDAO;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        SolicitudServicioDTO solicitudServicioDto = (SolicitudServicioDTO) exchange.getProperty("SOLICITUD_SERVICIO_DTO");
        PeticionServicioDTO peticionServicioDto = (PeticionServicioDTO) exchange.getProperty("PETICION_SERVICIO_DTO");

        peticionServicioDto.setFechafinal(new Date());
        peticionServicioDto.setRequest(exchange.getProperty("REQUEST") != null ? (String) exchange.getProperty("REQUEST") : "");
        peticionServicioDto.setResponse(exchange.getProperty("RESPONSE") != null ? (String) exchange.getProperty("RESPONSE") : "");

        excepcionesDAO.updatePeticionServicio(peticionServicioDto);

        solicitudServicioDto.setFechafinal(new Date());
        solicitudServicioDto.setClaveestatus(ConstantesExcepciones.PROCESADA);
        excepcionesDAO.updateSolicitudServicio(solicitudServicioDto);
        logger.info("::[WSREGISTROMOVILGUARDARDATOS]:: SOLICITUD ACTUALIZADA ::");

    }
}
