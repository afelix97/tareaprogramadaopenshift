
package mx.com.aforecoppel.registromovilguardardatos.notificarregistromoviltypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Clase Java para sexo.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="sexo"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="H"/&amp;gt;
 *     &amp;lt;enumeration value="M"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "sexo", namespace = "https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/")
@XmlEnum
public enum Sexo {

    H,
    M;

    public String value() {
        return name();
    }

    public static Sexo fromValue(String v) {
        return valueOf(v);
    }

}
