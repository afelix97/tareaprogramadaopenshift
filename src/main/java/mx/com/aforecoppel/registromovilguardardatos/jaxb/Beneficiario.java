
package mx.com.aforecoppel.registromovilguardardatos.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Clase Java para beneficiario complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="beneficiario"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="nombreBeneficiario" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_40" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="apellidoPaternoBeneficiario" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_40" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="apellidoMaternoBeneficiario" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_40" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="parentescoBeneficiario" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_2" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="curpBeneficiario" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}curp" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="porcentajeBeneficiario" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_3" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "beneficiario", propOrder = {
    "nombreBeneficiario",
    "apellidoPaternoBeneficiario",
    "apellidoMaternoBeneficiario",
    "parentescoBeneficiario",
    "curpBeneficiario",
    "porcentajeBeneficiario"
})
public class Beneficiario {

    protected String nombreBeneficiario;
    protected String apellidoPaternoBeneficiario;
    protected String apellidoMaternoBeneficiario;
    protected String parentescoBeneficiario;
    protected String curpBeneficiario;
    protected String porcentajeBeneficiario;

    /**
     * Obtiene el valor de la propiedad nombreBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    /**
     * Define el valor de la propiedad nombreBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreBeneficiario(String value) {
        this.nombreBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad apellidoPaternoBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoPaternoBeneficiario() {
        return apellidoPaternoBeneficiario;
    }

    /**
     * Define el valor de la propiedad apellidoPaternoBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoPaternoBeneficiario(String value) {
        this.apellidoPaternoBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad apellidoMaternoBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoMaternoBeneficiario() {
        return apellidoMaternoBeneficiario;
    }

    /**
     * Define el valor de la propiedad apellidoMaternoBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoMaternoBeneficiario(String value) {
        this.apellidoMaternoBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad parentescoBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentescoBeneficiario() {
        return parentescoBeneficiario;
    }

    /**
     * Define el valor de la propiedad parentescoBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentescoBeneficiario(String value) {
        this.parentescoBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad curpBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurpBeneficiario() {
        return curpBeneficiario;
    }

    /**
     * Define el valor de la propiedad curpBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurpBeneficiario(String value) {
        this.curpBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentajeBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPorcentajeBeneficiario() {
        return porcentajeBeneficiario;
    }

    /**
     * Define el valor de la propiedad porcentajeBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPorcentajeBeneficiario(String value) {
        this.porcentajeBeneficiario = value;
    }

}
