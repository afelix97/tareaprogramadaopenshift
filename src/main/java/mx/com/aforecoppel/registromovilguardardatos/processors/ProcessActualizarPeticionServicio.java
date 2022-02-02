package mx.com.aforecoppel.registromovilguardardatos.processors;

import java.util.Date;

import mx.com.aforecoppel.dao.ExcepcionesDAO;
import mx.com.aforecoppel.dto.PeticionServicioDTO;
import mx.com.aforecoppel.registromovilguardardatos.utils.Metricas;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessActualizarPeticionServicio implements Processor {

    final static Logger logger = LoggerFactory.getLogger(ProcessActualizarPeticionServicio.class);

    private final ExcepcionesDAO excepcionesDAO;

    private final Metricas metrica;

    @Autowired
    public ProcessActualizarPeticionServicio(ExcepcionesDAO excepcionesDAO, Metricas metrica) {
        this.excepcionesDAO = excepcionesDAO;
        this.metrica = metrica;
    }


    @Override
    public void process(Exchange exchange) throws Exception {
        java.lang.String cMessageLog =
                exchange.getIn().getHeader("messageLog") != null && !exchange.getIn().getHeader("messageLog").toString().equals("") ?
                        exchange.getIn().getHeader("messageLog").toString() : "";
        PeticionServicioDTO peticionServicioDto = (PeticionServicioDTO) exchange.getProperty("PETICION_SERVICIO_DTO");
        peticionServicioDto.setFechafinal(new Date());
        peticionServicioDto.setRequest(exchange.getProperty("REQUEST") != null ? (String) exchange.getProperty("REQUEST") : "");
        peticionServicioDto.setResponse(exchange.getProperty("RESPONSE") != null ? (String) exchange.getProperty("RESPONSE") : "");
        logger.debug(cMessageLog + "[AvisoNoRecep-Front]:: REQUEST A GUARDAR---" + peticionServicioDto.getRequest());
        logger.debug(cMessageLog + "[AvisoNoRecep-Front]:: RESPONSE A GUARDAR---" + peticionServicioDto.getResponse());
        excepcionesDAO.updatePeticionServicio(peticionServicioDto);
    }
}
