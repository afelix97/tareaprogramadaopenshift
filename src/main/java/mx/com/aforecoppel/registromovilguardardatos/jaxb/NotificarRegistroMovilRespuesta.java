
package mx.com.aforecoppel.registromovilguardardatos.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Clase Java para notificarRegistroMovilRespuesta complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="notificarRegistroMovilRespuesta"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="objetoRespuesta" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovil/}notificarRegistroMovilSalida"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificarRegistroMovilRespuesta", propOrder = {
    "objetoRespuesta"
})
public class NotificarRegistroMovilRespuesta {

    @XmlElement(required = true)
    protected NotificarRegistroMovilSalida objetoRespuesta;

    /**
     * Obtiene el valor de la propiedad objetoRespuesta.
     * 
     * @return
     *     possible object is
     *     {@link NotificarRegistroMovilSalida }
     *     
     */
    public NotificarRegistroMovilSalida getObjetoRespuesta() {
        return objetoRespuesta;
    }

    /**
     * Define el valor de la propiedad objetoRespuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link NotificarRegistroMovilSalida }
     *     
     */
    public void setObjetoRespuesta(NotificarRegistroMovilSalida value) {
        this.objetoRespuesta = value;
    }

}
