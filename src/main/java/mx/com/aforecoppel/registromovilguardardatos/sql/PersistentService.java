package mx.com.aforecoppel.registromovilguardardatos.sql;

import java.sql.SQLException;
import java.util.HashMap;

public interface PersistentService {

	/**
	 * @param sNss                //p_nss
	 * @param curpAhorrador       //p_curp
	 * @param idProcesoExpediente //p_id
	 * @return
	 * @throws SQLException
	 */

	// se agregan metodos para la separacion de logica fnrmguardarregistroappmovil
	public Integer fun_regmovil_eliminarregistro(long folioSolicitud) throws SQLException;

	public Integer fun_regmovil_guardarbeneficiarios(long folioSolicitud, String nombreBeneficiario,
			String apellidoPaternoBeneficiario, String apellidoMaternoBeneficiario, String parentescoBeneficiario,
			String curpBeneficiario, String porcentajeBeneficiario, String curpAhorrador, String nss,Integer idProcesoExpediente)
			throws SQLException;

	public Integer fun_regmovil_validarfoliosolicitud(long folioSolicitud) throws SQLException;

	// se agregan metodos para la separacion de logica
	// fnrmguardarregistrobeneficiariosappmovil
	public HashMap<String, String> fun_regmovil_consultadireccionmenones(String curpTutor, String messageLog)
			throws SQLException;

	public Integer fun_regmovil_guardarregistroapp(String curpAhorrador, String curpTutor, String nickName, String sNss,
			String sNombre, String apellidoPaterno, String apellidoMaterno, String entidadNacimiento, String sSexo,
			String fechaNacimiento, String sNacionalidad, String telefonoFijo, String telefonoCelular,
			String correoElectronico, String sCalle, String numeroExterior, String numeroInterior, String sColonia,
			String ciudadPoblacion, String sPais, String entidadFederativa, String municipioDelegacion,
			String codigoPostal, String codigoPromocional, String actividadEconomicaAhorrador, String aplicacionOrigen,
			Integer idProcesoExpediente, String dFechaEnvio, Integer calificacionImagen, String tipoAfiliacion,
			String sFolio, int folioSolicitud,String ocupacion, String nivelEstudios, String fechaTransaccion, String messageLog) throws SQLException;

	public long fun_regmovil_obtenerfoliosolicitud() throws SQLException;

}
