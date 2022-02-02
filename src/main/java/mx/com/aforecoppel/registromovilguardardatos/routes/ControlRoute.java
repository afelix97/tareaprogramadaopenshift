package mx.com.aforecoppel.registromovilguardardatos.routes;

import mx.com.aforecoppel.dto.ConfiguracionAlertaDTO;
import mx.com.aforecoppel.registromovilguardardatos.config.PropertiesConfiguration;
import mx.com.aforecoppel.registromovilguardardatos.utils.Constantes;
import mx.com.aforecoppel.registromovilguardardatos.utils.MyUtils;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ControlRoute extends RouteBuilder {

    private static final String NOMBRE_SERVICIO = Constantes.getNombreServicioUpper();
    private static final String ACTION = "action";
    private static final String ACTION_ENDPOINT = "direct:action";
    private static final String VALIDATE_START_ENDPOINT = "direct:validateStart";

    private static final String LOG_VALIDATE_START = NOMBRE_SERVICIO + "::[camel-context.validateStart]:: ";

    @Autowired
    ConfiguracionAlertaDTO configuracionAlertaDTO;

    @Autowired
    PropertiesConfiguration properties;

    public ControlRoute() {
        super();
    }

    @Override
    public void configure() throws Exception {

        
        onException(Exception.class)
                .id("excepcion-general")
                .routeId("excepcion-general")
                .description("Procesa las excepciones, para cerrar los registros de trazabilidad y enviar alertas en su caso.")
                .setHeader(Constantes.ALERTA, constant(true))
                .setHeader(Constantes.ALERTA_ENCABEZADO, simple("Excepcion en Servicio " + Constantes.getNombreServicio()))
                .setHeader(Constantes.ALERTA_SERVICIO, simple(Constantes.getNombreServicioUpper()))
                .setHeader(Constantes.ALERTA_EXCEPCION, simple("${exception}"))
                .setHeader(Constantes.ALERTA_CORREOS, simple(configuracionAlertaDTO.getCorreos()))
                .setHeader(Constantes.MESSAGE_LOG, simple(""))
                .wireTap(Constantes.ENVIAR_ALERTA_ENDPOINT)
                .pattern(ExchangePattern.InOnly)
                .end()
                .log(LoggingLevel.ERROR, NOMBRE_SERVICIO + "[camel-context.excepcion-general]:: Termina proceso con error FATAL !!!");

        from("timer://routeStart?repeatCount=1&delay=" + properties.getStartDelay())
        .id("routeStart")
        .routeId("routeStart")
        .description("Verifica si esta dentro del horario de servicio, para iniciar la ruta cuando levanta el Microservicio")
        .log(LoggingLevel.INFO, NOMBRE_SERVICIO + "::[camel-context.routeStart]:: Verifica si esta dentro del horario de servicio, para iniciar la ruta cuando levanta el Microservicio")
        .onException(Exception.class)
        .to(Constantes.CONTROL_ERROR_ENDPOINT)
        .end()
        .setProperty(ACTION, simple("start"))
        .log(LoggingLevel.INFO, NOMBRE_SERVICIO + "::[camel-context.routeStart]:: Verficando si se puede iniciar el procesamiento")
        .process((Exchange exchange) -> {
        	
        	String horaInicioMantenimiento = MyUtils.getHoraCronString(NOMBRE_SERVICIO + "::", properties.getCronMaintenanceStart());
        	String horaFinMantenimiento = MyUtils.getHoraCronString(NOMBRE_SERVICIO + "::", properties.getCronMaintenanceEnd());
        	
            exchange.getIn().setBody(MyUtils.isAvailableDataBase(NOMBRE_SERVICIO + "::", horaInicioMantenimiento, horaFinMantenimiento));
        })
        .to(VALIDATE_START_ENDPOINT);
        
        from(Constantes.QUARTZ_ENDPOINT + "routeSuspend?cron=" + properties.getCronMaintenanceStart())
        .id("routeSuspend")
        .routeId("routeSuspend")
        .description("Detiene la ejecución de la ruta para no operar en horario de mantenimiento")
        .log(LoggingLevel.INFO, NOMBRE_SERVICIO + "::[camel-context.routeSuspend]:: Se detiene la ejecución de la ruta para no operar en horario de mantenimiento")
        .onException(Exception.class)
        	.to(Constantes.CONTROL_ERROR_ENDPOINT)
        .end()
        .setProperty(ACTION, simple("suspend"))
        .to(ACTION_ENDPOINT)
        .log(LoggingLevel.INFO, NOMBRE_SERVICIO + "::[camel-context.routeSuspend]:: Ruta suspendida, BASE DE DATOS EN MANTENIMIENTO");

        from(Constantes.QUARTZ_ENDPOINT + "routeResume?cron=" + properties.getCronMaintenanceEnd())
        .id("routeResume")
        .routeId("routeResume")
        .description("Reanuda el servicio de la ruta cuando la base de datos no este en mantenimiento")
        .log(LoggingLevel.INFO, NOMBRE_SERVICIO + "::[camel-context.routeResume]:: Reanudando el servicio de la ruta, base de datos disponible")
        .onException(Exception.class)
        .to(Constantes.CONTROL_ERROR_ENDPOINT)
        .end()
        .setProperty(ACTION, simple("resume"))
        .process((Exchange exchange) -> {
        	  exchange.getIn().setBody(true);//se envia true al estar fuera de mantenimiento
        })
        .to(VALIDATE_START_ENDPOINT)
        .log(LoggingLevel.INFO, NOMBRE_SERVICIO + "::[camel-context.routeSuspend]:: Rutas reanudada, BASE DE DATOS DISPONIBLE");

        
        from(VALIDATE_START_ENDPOINT)
        .errorHandler(noErrorHandler())
        .log(LoggingLevel.INFO, LOG_VALIDATE_START + "PROCESANDO VALIDACION DE DISPONIBILIDAD")
        .choice()
        .when(simple(Constantes.BODY))
        	.log(LoggingLevel.INFO, LOG_VALIDATE_START + "VALIDATION = " + Constantes.BODY)
    		.to(ACTION_ENDPOINT)
    		.log(LoggingLevel.INFO, LOG_VALIDATE_START + "Ruta Iniciada!")
        .otherwise()
        	.log(LoggingLevel.INFO, LOG_VALIDATE_START + "No se inicia ruta por mantenimiento de base de datos")
        .endChoice();

        from(ACTION_ENDPOINT)
        .toD("controlbus:route?routeId=wsNotificarRegistroMmovilFrontRoute&action=${property.action}")
        .removeProperty(ACTION);

        from(Constantes.CONTROL_ERROR_ENDPOINT)
                .log(LoggingLevel.ERROR, NOMBRE_SERVICIO + "::[camel-context.controlError]:: Error FATAL en las rutas de control. APAGANDO EL SISTEMA.\n ${exception.stacktrace}")
                .setHeader(Constantes.ALERTA, constant(true))
                .setHeader(Constantes.ALERTA_ENCABEZADO, simple("Excepcion en Servicio " + Constantes.getNombreServicio()))
                .setHeader(Constantes.ALERTA_SERVICIO, simple(Constantes.getNombreServicioUpper()))
                .setHeader(Constantes.ALERTA_EXCEPCION, simple("${exception}"))
                .setHeader(Constantes.ALERTA_CORREOS, simple(configuracionAlertaDTO.getCorreos()))
                .setHeader(Constantes.MESSAGE_LOG, simple(""))
                .wireTap(Constantes.ENVIAR_ALERTA_ENDPOINT)
                .pattern(ExchangePattern.InOnly)
                .end()
                .process((Exchange e) -> System.exit(-1));

    }
}
