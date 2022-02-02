package mx.com.aforecoppel.registromovilguardardatos.processors;

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
public class ProcessRegistro implements Processor {

    final static Logger logger = LoggerFactory.getLogger(ProcessRegistro.class);

    private final ConstantesExcepciones constantesExcepciones;

    private final ExcepcionesDAO excepcionesDAO;

    @Autowired
    public ProcessRegistro(ConstantesExcepciones constantesExcepciones, ExcepcionesDAO excepcionesDAO) {
        this.constantesExcepciones = constantesExcepciones;
        this.excepcionesDAO = excepcionesDAO;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        java.lang.String messageLog = exchange.getIn().getHeader("messageLog") != null ? exchange.getIn().getHeader("messageLog").toString() : "";
        java.lang.String parametros = exchange.getProperty("REQUEST") != null ? exchange.getProperty("REQUEST").toString() : "";
        exchange.setProperty("REINTENTAR", "NO");
        exchange.setProperty("NUMERO_INTENTO", 0);
        SolicitudServicioDTO solicitudServicioDto = new SolicitudServicioDTO(constantesExcepciones.getClaveServicio(), parametros, constantesExcepciones.RECIBIDA);
        solicitudServicioDto = excepcionesDAO.insertSolicitudServicio(solicitudServicioDto);
        exchange.setProperty("SOLICITUD_SERVICIO_DTO", solicitudServicioDto);
        exchange.getIn().setHeader("CLAVE_SOLICITUD_SERVICIO", solicitudServicioDto.getClavesolicitudservicio());
        exchange.getIn().setHeader("KEYX_SOLICITUD_SERVICIO", solicitudServicioDto.getKeyx());
        messageLog = null;
        parametros = null;
        solicitudServicioDto = null;
    }
}
