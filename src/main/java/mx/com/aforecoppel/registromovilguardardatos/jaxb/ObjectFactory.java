
package mx.com.aforecoppel.registromovilguardardatos.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.com.aforecoppel.registromovilguardardatos.jaxb package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NotificarRegistroMovilRequest_QNAME = new QName("https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovil/", "notificarRegistroMovilRequest");
    private final static QName _NotificarRegistroMovilResponse_QNAME = new QName("https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovil/", "notificarRegistroMovilResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.aforecoppel.registromovilguardardatos.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotificarRegistroMovilContrato }
     * 
     */
    public NotificarRegistroMovilContrato createNotificarRegistroMovilContrato() {
        return new NotificarRegistroMovilContrato();
    }

    /**
     * Create an instance of {@link NotificarRegistroMovilRespuesta }
     * 
     */
    public NotificarRegistroMovilRespuesta createNotificarRegistroMovilRespuesta() {
        return new NotificarRegistroMovilRespuesta();
    }

    /**
     * Create an instance of {@link NotificarRegistroMovilEntrada }
     * 
     */
    public NotificarRegistroMovilEntrada createNotificarRegistroMovilEntrada() {
        return new NotificarRegistroMovilEntrada();
    }

    /**
     * Create an instance of {@link NotificarRegistroMovilSalida }
     * 
     */
    public NotificarRegistroMovilSalida createNotificarRegistroMovilSalida() {
        return new NotificarRegistroMovilSalida();
    }

    /**
     * Create an instance of {@link Beneficiarios }
     * 
     */
    public Beneficiarios createBeneficiarios() {
        return new Beneficiarios();
    }

    /**
     * Create an instance of {@link Beneficiario }
     * 
     */
    public Beneficiario createBeneficiario() {
        return new Beneficiario();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificarRegistroMovilContrato }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NotificarRegistroMovilContrato }{@code >}
     */
    @XmlElementDecl(namespace = "https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovil/", name = "notificarRegistroMovilRequest")
    public JAXBElement<NotificarRegistroMovilContrato> createNotificarRegistroMovilRequest(NotificarRegistroMovilContrato value) {
        return new JAXBElement<NotificarRegistroMovilContrato>(_NotificarRegistroMovilRequest_QNAME, NotificarRegistroMovilContrato.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificarRegistroMovilRespuesta }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NotificarRegistroMovilRespuesta }{@code >}
     */
    @XmlElementDecl(namespace = "https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovil/", name = "notificarRegistroMovilResponse")
    public JAXBElement<NotificarRegistroMovilRespuesta> createNotificarRegistroMovilResponse(NotificarRegistroMovilRespuesta value) {
        return new JAXBElement<NotificarRegistroMovilRespuesta>(_NotificarRegistroMovilResponse_QNAME, NotificarRegistroMovilRespuesta.class, null, value);
    }

}
