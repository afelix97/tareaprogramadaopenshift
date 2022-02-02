package mx.com.aforecoppel.registromovilguardardatos.utils;

public class ConstantesExcepciones extends mx.com.aforecoppel.dto.ConstantesExcepciones {

    /**
     * Consulta de Servicio.
     */
    private int CLAVE_SERVICIO;
    public static final String NOMBRE_SERVICIO = "wsregistromovilguardardatos";


    public int getClaveServicio() {
        return CLAVE_SERVICIO;
    }

    public void setClaveServicio(int claveServicio) {
        CLAVE_SERVICIO = claveServicio;
    }
}
