package mx.com.aforecoppel.registromovilguardardatos.processors;


import mx.com.aforecoppel.registromovilguardardatos.jaxb.NotificarRegistroMovilRespuesta;
import mx.com.aforecoppel.registromovilguardardatos.jaxb.NotificarRegistroMovilSalida;
import mx.com.aforecoppel.registromovilguardardatos.jaxb.ObjectFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class ProcessBuildResponse implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(ProcessBuildResponse.class);


    @Override
    public void process(Exchange exchange) throws Exception {

    }
    public void respuestaErrorInterno(Exchange exchange) {
        System.out.println("CONSTRUYENDO ERROR INTERNO");
        String messageLog = Optional.ofNullable(exchange.getIn().getHeader("messageLog")).orElse("").toString();

        ObjectFactory of = new ObjectFactory();
        NotificarRegistroMovilRespuesta response = new NotificarRegistroMovilRespuesta();
        NotificarRegistroMovilSalida salida = new NotificarRegistroMovilSalida();

        salida.setConfirmacionTransaccion("02");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hhmmss");
        String strDate = formatter.format(date);
        strDate = formatter.format(date);
        salida.setFechaTransaccion(strDate.toString().trim());
        salida.setMotivoRechazo("999");

        response.setObjetoRespuesta(salida);
        exchange.getIn().setBody(of.createNotificarRegistroMovilResponse(response));

        logger.info("{} [ProcessBuildResponse.respuestaErrorInterno]:: SE CONTRUYO RESPUESTA DE ERROR GENERAL::", messageLog);
    }



}
