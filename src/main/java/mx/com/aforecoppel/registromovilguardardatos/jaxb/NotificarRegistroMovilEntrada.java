
package mx.com.aforecoppel.registromovilguardardatos.jaxb;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import mx.com.aforecoppel.registromovilguardardatos.notificarregistromoviltypes.Sexo;


/**
 * &lt;p&gt;Clase Java para notificarRegistroMovilEntrada complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="notificarRegistroMovilEntrada"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="curpAhorrador" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}curp"/&amp;gt;
 *         &amp;lt;element name="curpTutor" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}curp" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="nickname" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_15" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="nss" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}nss" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="nombre" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_40"/&amp;gt;
 *         &amp;lt;element name="apellidoPaterno" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_40"/&amp;gt;
 *         &amp;lt;element name="apellidoMaterno" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_40" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="entidadNacimiento" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfabetico_2" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="sexo" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}sexo" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="fechaNacimiento" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}fecha_DDMMAAAA" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="nacionalidad" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfabetico_3" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="telefonoFijo" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}numerico_10" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="telefonoCelular" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}numerico_10" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="correoElectronico" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_50" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="calle" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_65" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroExterior" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_15" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroInterior" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_15" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="colonia" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_65" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ciudadPoblacion" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_65" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="pais" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_3" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="entidadFederativa" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_65" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="municipioDelegacion" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_65" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codigoPostal" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_5" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="beneficiarios" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovil/}beneficiarios" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codigoPromocional" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_10" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="actividadEconomicaAhorrador" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_2" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="aplicacionOrigen" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_1" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="idProcesoExpediente" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}numerico_2"/&amp;gt;
 *         &amp;lt;element name="fechaEnvio" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}fecha_hora"/&amp;gt;
 *         &amp;lt;element name="calificacionImagen" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}numerico_3" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipoAfiliacion" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_2"/&amp;gt;
 *         &amp;lt;element name="folio" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_3"/&amp;gt;
 *         &amp;lt;element name="ocupacion" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_2" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="nivelEstudios" type="{https://www.aforecoppel.com.mx/NotificarRegistroMovil/notificarRegistroMovilTypes/}alfaNumerico_2" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificarRegistroMovilEntrada", propOrder = {
    "curpAhorrador",
    "curpTutor",
    "nickname",
    "nss",
    "nombre",
    "apellidoPaterno",
    "apellidoMaterno",
    "entidadNacimiento",
    "sexo",
    "fechaNacimiento",
    "nacionalidad",
    "telefonoFijo",
    "telefonoCelular",
    "correoElectronico",
    "calle",
    "numeroExterior",
    "numeroInterior",
    "colonia",
    "ciudadPoblacion",
    "pais",
    "entidadFederativa",
    "municipioDelegacion",
    "codigoPostal",
    "beneficiarios",
    "codigoPromocional",
    "actividadEconomicaAhorrador",
    "aplicacionOrigen",
    "idProcesoExpediente",
    "fechaEnvio",
    "calificacionImagen",
    "tipoAfiliacion",
    "folio",
    "ocupacion",
    "nivelEstudios"
})
public class NotificarRegistroMovilEntrada {

    @XmlElement(required = true)
    protected String curpAhorrador;
    protected String curpTutor;
    protected String nickname;
    protected String nss;
    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String entidadNacimiento;
    @XmlSchemaType(name = "string")
    protected Sexo sexo;
    protected String fechaNacimiento;
    protected String nacionalidad;
    protected BigInteger telefonoFijo;
    protected BigInteger telefonoCelular;
    protected String correoElectronico;
    protected String calle;
    protected String numeroExterior;
    protected String numeroInterior;
    protected String colonia;
    protected String ciudadPoblacion;
    protected String pais;
    protected String entidadFederativa;
    protected String municipioDelegacion;
    protected String codigoPostal;
    protected Beneficiarios beneficiarios;
    protected String codigoPromocional;
    protected String actividadEconomicaAhorrador;
    protected String aplicacionOrigen;
    @XmlElement(required = true)
    protected BigInteger idProcesoExpediente;
    @XmlElement(required = true)
    protected String fechaEnvio;
    protected BigInteger calificacionImagen;
    @XmlElement(required = true)
    protected String tipoAfiliacion;
    @XmlElement(required = true)
    protected String folio;
    protected String ocupacion;
    protected String nivelEstudios;

    /**
     * Obtiene el valor de la propiedad curpAhorrador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurpAhorrador() {
        return curpAhorrador;
    }

    /**
     * Define el valor de la propiedad curpAhorrador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurpAhorrador(String value) {
        this.curpAhorrador = value;
    }

    /**
     * Obtiene el valor de la propiedad curpTutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurpTutor() {
        return curpTutor;
    }

    /**
     * Define el valor de la propiedad curpTutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurpTutor(String value) {
        this.curpTutor = value;
    }

    /**
     * Obtiene el valor de la propiedad nickname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Define el valor de la propiedad nickname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
    }

    /**
     * Obtiene el valor de la propiedad nss.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNss() {
        return nss;
    }

    /**
     * Define el valor de la propiedad nss.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNss(String value) {
        this.nss = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad apellidoPaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Define el valor de la propiedad apellidoPaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoPaterno(String value) {
        this.apellidoPaterno = value;
    }

    /**
     * Obtiene el valor de la propiedad apellidoMaterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Define el valor de la propiedad apellidoMaterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoMaterno(String value) {
        this.apellidoMaterno = value;
    }

    /**
     * Obtiene el valor de la propiedad entidadNacimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntidadNacimiento() {
        return entidadNacimiento;
    }

    /**
     * Define el valor de la propiedad entidadNacimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntidadNacimiento(String value) {
        this.entidadNacimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad sexo.
     * 
     * @return
     *     possible object is
     *     {@link Sexo }
     *     
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     * Define el valor de la propiedad sexo.
     * 
     * @param value
     *     allowed object is
     *     {@link Sexo }
     *     
     */
    public void setSexo(Sexo value) {
        this.sexo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaNacimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Define el valor de la propiedad fechaNacimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNacimiento(String value) {
        this.fechaNacimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad nacionalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Define el valor de la propiedad nacionalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad telefonoFijo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * Define el valor de la propiedad telefonoFijo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTelefonoFijo(BigInteger value) {
        this.telefonoFijo = value;
    }

    /**
     * Obtiene el valor de la propiedad telefonoCelular.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTelefonoCelular() {
        return telefonoCelular;
    }

    /**
     * Define el valor de la propiedad telefonoCelular.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTelefonoCelular(BigInteger value) {
        this.telefonoCelular = value;
    }

    /**
     * Obtiene el valor de la propiedad correoElectronico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Define el valor de la propiedad correoElectronico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreoElectronico(String value) {
        this.correoElectronico = value;
    }

    /**
     * Obtiene el valor de la propiedad calle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Define el valor de la propiedad calle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroExterior.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroExterior() {
        return numeroExterior;
    }

    /**
     * Define el valor de la propiedad numeroExterior.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroExterior(String value) {
        this.numeroExterior = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroInterior.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroInterior() {
        return numeroInterior;
    }

    /**
     * Define el valor de la propiedad numeroInterior.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroInterior(String value) {
        this.numeroInterior = value;
    }

    /**
     * Obtiene el valor de la propiedad colonia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Define el valor de la propiedad colonia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColonia(String value) {
        this.colonia = value;
    }

    /**
     * Obtiene el valor de la propiedad ciudadPoblacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudadPoblacion() {
        return ciudadPoblacion;
    }

    /**
     * Define el valor de la propiedad ciudadPoblacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudadPoblacion(String value) {
        this.ciudadPoblacion = value;
    }

    /**
     * Obtiene el valor de la propiedad pais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Define el valor de la propiedad pais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Obtiene el valor de la propiedad entidadFederativa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    /**
     * Define el valor de la propiedad entidadFederativa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntidadFederativa(String value) {
        this.entidadFederativa = value;
    }

    /**
     * Obtiene el valor de la propiedad municipioDelegacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipioDelegacion() {
        return municipioDelegacion;
    }

    /**
     * Define el valor de la propiedad municipioDelegacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipioDelegacion(String value) {
        this.municipioDelegacion = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoPostal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Define el valor de la propiedad codigoPostal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPostal(String value) {
        this.codigoPostal = value;
    }

    /**
     * Obtiene el valor de la propiedad beneficiarios.
     * 
     * @return
     *     possible object is
     *     {@link Beneficiarios }
     *     
     */
    public Beneficiarios getBeneficiarios() {
        return beneficiarios;
    }

    /**
     * Define el valor de la propiedad beneficiarios.
     * 
     * @param value
     *     allowed object is
     *     {@link Beneficiarios }
     *     
     */
    public void setBeneficiarios(Beneficiarios value) {
        this.beneficiarios = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoPromocional.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPromocional() {
        return codigoPromocional;
    }

    /**
     * Define el valor de la propiedad codigoPromocional.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPromocional(String value) {
        this.codigoPromocional = value;
    }

    /**
     * Obtiene el valor de la propiedad actividadEconomicaAhorrador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividadEconomicaAhorrador() {
        return actividadEconomicaAhorrador;
    }

    /**
     * Define el valor de la propiedad actividadEconomicaAhorrador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividadEconomicaAhorrador(String value) {
        this.actividadEconomicaAhorrador = value;
    }

    /**
     * Obtiene el valor de la propiedad aplicacionOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAplicacionOrigen() {
        return aplicacionOrigen;
    }

    /**
     * Define el valor de la propiedad aplicacionOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAplicacionOrigen(String value) {
        this.aplicacionOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad idProcesoExpediente.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdProcesoExpediente() {
        return idProcesoExpediente;
    }

    /**
     * Define el valor de la propiedad idProcesoExpediente.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdProcesoExpediente(BigInteger value) {
        this.idProcesoExpediente = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEnvio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * Define el valor de la propiedad fechaEnvio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaEnvio(String value) {
        this.fechaEnvio = value;
    }

    /**
     * Obtiene el valor de la propiedad calificacionImagen.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCalificacionImagen() {
        return calificacionImagen;
    }

    /**
     * Define el valor de la propiedad calificacionImagen.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCalificacionImagen(BigInteger value) {
        this.calificacionImagen = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoAfiliacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAfiliacion() {
        return tipoAfiliacion;
    }

    /**
     * Define el valor de la propiedad tipoAfiliacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAfiliacion(String value) {
        this.tipoAfiliacion = value;
    }

    /**
     * Obtiene el valor de la propiedad folio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Define el valor de la propiedad folio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolio(String value) {
        this.folio = value;
    }

    /**
     * Obtiene el valor de la propiedad ocupacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOcupacion() {
        return ocupacion;
    }

    /**
     * Define el valor de la propiedad ocupacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOcupacion(String value) {
        this.ocupacion = value;
    }

    /**
     * Obtiene el valor de la propiedad nivelEstudios.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivelEstudios() {
        return nivelEstudios;
    }

    /**
     * Define el valor de la propiedad nivelEstudios.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivelEstudios(String value) {
        this.nivelEstudios = value;
    }

}
