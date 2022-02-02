
package mx.com.aforecoppel.registromovilguardardatos.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Clase Java para notificarRegistroMovilContrato complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="notificarRegistroMovilContrato"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="cuerpo" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovil/}notificarRegistroMovilEntrada"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificarRegistroMovilContrato", propOrder = {
    "cuerpo"
})
public class NotificarRegistroMovilContrato {

    @XmlElement(required = true)
    protected NotificarRegistroMovilEntrada cuerpo;

    /**
     * Obtiene el valor de la propiedad cuerpo.
     * 
     * @return
     *     possible object is
     *     {@link NotificarRegistroMovilEntrada }
     *     
     */
    public NotificarRegistroMovilEntrada getCuerpo() {
        return cuerpo;
    }

    /**
     * Define el valor de la propiedad cuerpo.
     * 
     * @param value
     *     allowed object is
     *     {@link NotificarRegistroMovilEntrada }
     *     
     */
    public void setCuerpo(NotificarRegistroMovilEntrada value) {
        this.cuerpo = value;
    }

}
