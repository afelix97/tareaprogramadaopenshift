package mx.com.aforecoppel.registromovilguardardatos.processors;

import mx.com.aforecoppel.dto.ConfiguracionAlertaDTO;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessExceptionWsHandler implements Processor {

	final static Logger logger = LoggerFactory.getLogger(ProcessExceptionWsHandler.class);

	private final ConfiguracionAlertaDTO configuracionAlertaDefaultDto;

	@Autowired
	public ProcessExceptionWsHandler(ConfiguracionAlertaDTO configuracionAlertaDefaultDto) {
		this.configuracionAlertaDefaultDto = configuracionAlertaDefaultDto;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		Exception contextoCamelException = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		logger.warn("::[WSREGISTROMOVILGUARDARDATOS]:: [AvisoNoRecep-Front]:: EXCEPCION FATAL - EXCEPTION WS HANDLER - ::::::");
		exchange.getIn().setHeader("ALERTA","SI");
		exchange.getIn().setHeader("ALERTA_ENCABEZADO","Excepcion en Servicio wsregistromovilguardardatos");
		exchange.getIn().setHeader("ALERTA_SERVICIO","wsregistromovilguardardatos");
		String exception = ExceptionUtils.getStackTrace(contextoCamelException);
		exchange.getIn().setHeader("ALERTA_EXCEPCION",exception);
		exchange.getIn().setHeader("ALERTA_CORREOS",configuracionAlertaDefaultDto.getCorreos());
	}

}
