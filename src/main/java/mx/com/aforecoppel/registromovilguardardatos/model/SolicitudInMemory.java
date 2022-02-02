/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.aforecoppel.registromovilguardardatos.model;

/**
 *
 * @author usuario
 */
public class SolicitudInMemory {

    private String idMessage;
    private String curp;
    private int intento;
    private Long claveSolicitud;
    private Long keyxSolicitud;

    public SolicitudInMemory() {
    }

    public SolicitudInMemory(String idMessage, String curp, int intento, Long claveSolicitud, Long keyxSolicitud) {
        this.idMessage = idMessage;
        this.curp = curp;
        this.intento = intento;
        this.claveSolicitud = claveSolicitud;
        this.keyxSolicitud = keyxSolicitud;
    }

    /**
     * @return the idMessage
     */
    public String getIdMessage() {
        return idMessage;
    }

    /**
     * @param idMessage the idMessage to set
     */
    public void setIdMessage(String idMessage) {
        this.idMessage = idMessage;
    }

    /**
     * @return the curp
     */
    public String getCurp() {
        return curp;
    }

    /**
     * @param curp the curp to set
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     * @return the intento
     */
    public int getIntento() {
        return intento;
    }

    /**
     * @param intento the intento to set
     */
    public void setIntento(int intento) {
        this.intento = intento;
    }

    /**
     * @return the claveSolicitud
     */
    public Long getClaveSolicitud() {
        return claveSolicitud;
    }

    /**
     * @param claveSolicitud the claveSolicitud to set
     */
    public void setClaveSolicitud(Long claveSolicitud) {
        this.claveSolicitud = claveSolicitud;
    }

    /**
     * @return the keyxSolicitud
     */
    public Long getKeyxSolicitud() {
        return keyxSolicitud;
    }

    /**
     * @param keyxSolicitud the keyxSolicitud to set
     */
    public void setKeyxSolicitud(Long keyxSolicitud) {
        this.keyxSolicitud = keyxSolicitud;
    }
}
