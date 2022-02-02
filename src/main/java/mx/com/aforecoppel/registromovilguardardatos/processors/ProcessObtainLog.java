package mx.com.aforecoppel.registromovilguardardatos.processors;

import mx.com.aforecoppel.registromovilguardardatos.jaxb.NotificarRegistroMovilContrato;
import mx.com.aforecoppel.registromovilguardardatos.jaxb.NotificarRegistroMovilEntrada;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProcessObtainLog implements Processor {
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 * 
	 * class create to build MessageLog to be used along application 
	 * 
	 * Eduardo Cota
	 * 8 Abril 2018
	 */
    final static Logger logger = LoggerFactory.getLogger(ProcessRegistro.class);
    
	@Override
    public void process(Exchange exchange) throws Exception {
		java.lang.String messageLog 									= null;
		java.lang.String cCurp 											= null;
		NotificarRegistroMovilContrato notificarRegistroMovilContrato 	= null;

		notificarRegistroMovilContrato = exchange.getIn().getBody(NotificarRegistroMovilContrato.class);
		
		if (notificarRegistroMovilContrato != null && notificarRegistroMovilContrato.getCuerpo() != null &&
			notificarRegistroMovilContrato.getCuerpo().getCurpAhorrador() != null)
		{
			cCurp = notificarRegistroMovilContrato.getCuerpo().getCurpAhorrador().toString().trim();
			
			messageLog = "::WSREGISTROMOVILGUARDARDATOS::[" + cCurp + "]";
	     	
			exchange.getIn().setHeader("messageLog",   messageLog);
			exchange.getIn().setHeader("curp",   cCurp);
	     	
			logger.info(messageLog + "[ProcessObtainLog.process]:: ¡¡¡ COMIENZA PROCESO WEBSERVICE REGISTRO MOVIL GUARDAR DATOS !!!");			
			
		} 
		else 
		{
			messageLog = "::WSREGISTROMOVILGUARDARDATOS:: ";
			logger.info(messageLog + "[ProcessObtainLog.process]:: ¡¡¡ COMIENZA PROCESO WEBSERVICE REGISTRO MOVIL GUARDAR DATOS !!!");
			logger.info(messageLog + "[ProcessObtainLog.process]:: WSREGISTROMOVILGUARDARDATOS:: Request vacio");
			exchange.getIn().setHeader("messageLog",   messageLog);
		}
		
		messageLog 									= null;
		cCurp 										= null;
		notificarRegistroMovilContrato 				= null;
	}
}
