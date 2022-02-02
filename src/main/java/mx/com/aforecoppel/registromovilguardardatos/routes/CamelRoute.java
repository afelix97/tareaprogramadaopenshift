package mx.com.aforecoppel.registromovilguardardatos.routes;

import mx.com.aforecoppel.dto.ConfiguracionAlertaDTO;
import mx.com.aforecoppel.registromovilguardardatos.config.PropertiesConfiguration;
import mx.com.aforecoppel.registromovilguardardatos.front.ProcessValidateRequest;
import mx.com.aforecoppel.registromovilguardardatos.processors.ProcessActualizarSolicitudServicio;
import mx.com.aforecoppel.registromovilguardardatos.processors.ProcessActualizarSolicitudServicioBack;
import mx.com.aforecoppel.registromovilguardardatos.processors.ProcessExceptionHandler;
import mx.com.aforecoppel.registromovilguardardatos.processors.ProcessObtainLog;
import mx.com.aforecoppel.registromovilguardardatos.processors.ProcessRegistro;
import mx.com.aforecoppel.registromovilguardardatos.processors.ProcessRegistroBack;
import mx.com.aforecoppel.registromovilguardardatos.processors.ProcessRegistroPeticionServicio;
import mx.com.aforecoppel.registromovilguardardatos.processors.ProcessRegistroPeticionServicioBack;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.stereotype.Component;
import org.apache.camel.component.kubernetes.cluster.KubernetesClusterService;
import org.apache.camel.model.HystrixConfigurationDefinition;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CamelRoute extends RouteBuilder {

	

	@Autowired
	ProcessActualizarSolicitudServicio processActualizarSolicitudServicio;
	
	@Autowired
	ProcessActualizarSolicitudServicioBack processActualizarSolicitudServicioBack;
	  
	@Autowired
	ProcessRegistro processRegistroSolicitudServicio;
	
	@Autowired
	ProcessRegistroBack processRegistroSolicitudServicioBack;
	 
	@Autowired
	ProcessRegistroPeticionServicio processRegistroPeticionServicio;
	
	@Autowired
	ProcessRegistroPeticionServicioBack processRegistroPeticionServicioBack;
	
	@Autowired
	ProcessObtainLog processObtainLog;
	 
	@Autowired
	ProcessExceptionHandler processExceptionHandler;

	@Autowired
	ConfiguracionAlertaDTO configuracionAlertaDTO;

	@Autowired
	PropertiesConfiguration properties;

	@Autowired
	HystrixConfigurationDefinition hcd;
    
    public CamelRoute() {
        super();
    }
    
    private static final String BUILD_RESPONSE = "processBuildResponse";
    private static final String BODY = "${body}";
    private static final String RESPONSE = "RESPONSE";

    @Override
    public void configure() throws Exception {

		KubernetesClusterService kubernetes = new KubernetesClusterService();
        this.getContext().addService(kubernetes);
        
        JaxbDataFormat soap = new JaxbDataFormat("mx.com.aforecoppel.registromovilguardardatos.jaxb");
       
        onException(Exception.class)
        .handled(true)
        .log(LoggingLevel.WARN, "${in.header.messageLog}[camel-context.onException] EXCEPCION GENERAL EN EL PROCESO -")
        .log(LoggingLevel.WARN, "${in.header.messageLog}[camel-context.onException] Mensaje de Error ${exception.stacktrace}")
        .bean(BUILD_RESPONSE, "respuestaErrorInterno")
        .marshal(soap)
        .convertBodyTo(String.class)
        .process(processExceptionHandler)
        .wireTap("direct:enviarAlerta")
        .choice()
        .when(simple("${exchangeProperty.REINTENTAR} == 'SI'"))
        .setHeader("REINTENTAR", constant("SI"))
        .setHeader("NUMERO_INTENTO", simple("${exchangeProperty.NUMERO_INTENTO}"))
        .setBody(simple("${in.header.parametros}"))
        .setHeader("CamelJmsDestinationName", simple("${exchangeProperty.COLA_ORIGEN}"))
        .log(LoggingLevel.WARN, "${in.header.messageLog}[camel-context.onException] REINTENTANDO SERVICIO - ENVIANDO A COLA DE MENSAJERIA  > ${in.header.CamelJmsDestinationName}")
        .recipientList(simple("activemq:queue:${exchangeProperty.COLA_ORIGEN}?exchangePattern=InOnly"))
        .endChoice();
        
        from("activemq:queue:{{registromovilguardardatos.jms-uri}}")
		.autoStartup(false)
		.id("wsNotificarRegistroMmovilFrontRoute")
		.routeId("wsNotificarRegistroMmovilFrontRoute")
		.convertBodyTo(String.class)
		.process(processObtainLog)
		.setHeader("parametros", simple("${body}", String.class))
		.setProperty("REQUEST", simple("${body}", String.class))
		.setHeader("curp", xpath("//*[local-name()=\"cuerpo\"]/curpAhorrador/text()").stringResult())
		.process(processRegistroSolicitudServicio)
        .process(processRegistroPeticionServicio)
		.log("${in.header.messageLog}[camel-context.wsNotificarRegistroMmovilFrontRoute]:: FECHA NOTIFICACION ..${in.header.FECHA_TRANSACCION}")
		.unmarshal(soap)
		.bean(ProcessValidateRequest.class)
		.marshal(soap).convertBodyTo(String.class)
		.setProperty("RESPONSE", simple("${body}", String.class))
		.setHeader("RESPONSE", simple("${body}", String.class))
		.setHeader("COLA_DESTINO", simple(properties.getJmsUriContinuacion()))
        .log("${in.header.messageLog}[camel-context.wsNotificarRegistroMmovilFrontRoute]:: RESPONSE ..${body}")
        .log("${in.header.messageLog}[camel-context.wsNotificarRegistroMmovilFrontRoute]:: QUEUE_DESTINO :: ${in.header.COLA_DESTINO}")
        .recipientList(simple("activemq:queue:${in.header.COLA_DESTINO}?exchangePattern=InOnly"))
        .end();

        from("activemq:queue:{{registromovilguardardatos.jms-uri-continuacion}}")
        .id("wsAvisoNoRecepcionBackRoute")
        .autoStartup(true)
        .removeHeader("AEXCEPCIONES")
        .setProperty("COLA_ORIGEN", simple("${in.header.COLA_DESTINO}", String.class))
        .setBody(simple("${in.header.parametros}", String.class))
        .log("${in.header.messageLog}[camel-context.wsAvisoNoRecepcionBackRoute]:: CLAVE_SOLICITUD_SERVICIO > ${in.header.CLAVE_SOLICITUD_SERVICIO}")
        .log("${in.header.messageLog}[camel-context.wsAvisoNoRecepcionBackRoute]:: KEYX_SOLICITUD_SERVICIO > ${in.header.KEYX_SOLICITUD_SERVICIO}")
        .setProperty("REQUEST", simple("${body}", String.class))
        .process(processRegistroSolicitudServicioBack)
        .process(processRegistroPeticionServicioBack)
        .process(processActualizarSolicitudServicioBack)
        .doTry()
        .unmarshal(soap)
        .doCatch(Exception.class)
        .log(LoggingLevel.WARN, "${in.header.messageLog}[camel-context.wsAvisoNoRecepcionBackRoute] !!! EXCEPCION JAXB EN EL PROCESO BACKEND ¡¡¡ ${body}")
        .doFinally()
        .log(LoggingLevel.INFO, "${in.header.messageLog}[camel-context.wsAvisoNoRecepcionBackRoute] !!! TERMINA PROCESO -GUARDAR DATOS DE ADMINCISTRACION DE CITAS ¡¡¡")
        .endDoTry();
        
        from("direct:enviarAlerta")
        .id("enviarAlerta")
        .choice()
        .when(simple("${in.header.ALERTA} == 'SI'"))
        .doTry()
        .setHeader("DATOS_SERVER_IP", simple("${bean:serverInfo.getServerip}"))
        .setHeader("DATOS_SERVER_NAME", simple("${bean:serverInfo.getServerhostname}"))
        .setHeader("DATOS_SERVER_CONTAINE", simple("${bean:serverInfo.getServercontainer}"))
        .inOnly("activemq:queue:Excepciones")
        .endDoTry()
        .doCatch(Exception.class)
        .log("${in.header.messageLog}[camel-context.enviarAlerta] !!! NO SE PUEDE ENVIAR MENSAJE DE ALERTA AL BROKER ¡¡¡ ${headers}")
        .end()
        .endChoice();
    }
}
