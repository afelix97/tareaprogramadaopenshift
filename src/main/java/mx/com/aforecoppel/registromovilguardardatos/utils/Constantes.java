package mx.com.aforecoppel.registromovilguardardatos.utils;

public class Constantes {

    /**
     * Consulta de Servicio.
     */
    private int claveServicio;
    public static final String NOMBRE_SERVICIO = "wsregistromovilguardardatos";

    public static final String ALERTA = "ALERTA";
    public static final String ALERTA_ENCABEZADO = "ALERTA_ENCABEZADO";
    public static final String ALERTA_SERVICIO = "ALERTA_SERVICIO";
    public static final String ALERTA_EXCEPCION = "ALERTA_EXCEPCION";
    public static final String ALERTA_CORREOS = "ALERTA_CORREOS";
    public static final String FATAL_EXCEPTION = "FATAL_EXCEPTION";
    public static final String ALERTA_LIMITE_OCURRENCIA = "ALERTA_LIMITE_OCURRENCIA";
    
    public static final String QUARTZ_ENDPOINT = "quartz2://";
    public static final String ENVIAR_ALERTA_ENDPOINT = "direct:enviarAlerta";
    public static final String CONTROL_ERROR_ENDPOINT = "direct:controlError";
    
    public static final String MESSAGE_LOG = "messageLog";
    public static final String REINTENTAR = "retry";
    public static final String ES_REINTENTO = "isRetry";
    public static final String NUMERO_INTENTO = "retryNumber";
    public static final String REQUEST = "request";
    public static final String RESPONSE = "response";
    public static final String BODY = "${body}";
    public static final String CURP_FOLIOACK = "curp_folio_ack";
    public static final String APP_ORIGEN = "aplicacion_origen";
    public static final String ID_MESSAGE = "uuid";
    public static final String CURP = "curp";
    public static final String TITLE = "title";
    public static final String CLAVE_SOLICITUD_SERVICIO = "claveService";
    public static final String KEYX_SOLICITUD_SERVICIO = "keyxService";
    public static final String SOAP_MESSAGE = "soapMessage";

    public int getClaveServicio() {
        return claveServicio;
    }

    public void setClaveServicio(int claveServicio) {
        this.claveServicio = claveServicio;
    }

    public static String getNombreServicio() {
        return NOMBRE_SERVICIO;
    }
    
    public static String getNombreServicioUpper() {
        return NOMBRE_SERVICIO.toUpperCase();
    }
}
