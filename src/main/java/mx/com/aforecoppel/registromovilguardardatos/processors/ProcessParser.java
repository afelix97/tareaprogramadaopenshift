package mx.com.aforecoppel.registromovilguardardatos.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProcessParser implements Processor {

	final static Logger logger = LoggerFactory
			.getLogger(ProcessParser.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		String payload = xmlReplaceEscapeText(exchange.getIn().getBody(
				String.class));
		exchange.getOut().setBody(payload);
	}

	String xmlReplaceEscapeText(String xml) {
		return xml.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
				.replaceAll("&quot;", "\"").replaceAll("&amp;", "&")
				.replaceAll("&apos;", "\'");
	}

}
