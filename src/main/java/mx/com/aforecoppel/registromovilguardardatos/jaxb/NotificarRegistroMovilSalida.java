
package mx.com.aforecoppel.registromovilguardardatos.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Clase Java para notificarRegistroMovilSalida complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="notificarRegistroMovilSalida"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="confirmacionTransaccion" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_2"/&amp;gt;
 *         &amp;lt;element name="fechaTransaccion" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}fecha_hora"/&amp;gt;
 *         &amp;lt;element name="motivoRechazo" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_3"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificarRegistroMovilSalida", propOrder = {
    "confirmacionTransaccion",
    "fechaTransaccion",
    "motivoRechazo"
})
public class NotificarRegistroMovilSalida {

    @XmlElement(required = true)
    protected String confirmacionTransaccion;
    @XmlElement(required = true)
    protected String fechaTransaccion;
    @XmlElement(required = true)
    protected String motivoRechazo;

    /**
     * Obtiene el valor de la propiedad confirmacionTransaccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmacionTransaccion() {
        return confirmacionTransaccion;
    }

    /**
     * Define el valor de la propiedad confirmacionTransaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmacionTransaccion(String value) {
        this.confirmacionTransaccion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaTransaccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    /**
     * Define el valor de la propiedad fechaTransaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaTransaccion(String value) {
        this.fechaTransaccion = value;
    }

    /**
     * Obtiene el valor de la propiedad motivoRechazo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    /**
     * Define el valor de la propiedad motivoRechazo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoRechazo(String value) {
        this.motivoRechazo = value;
    }

}
