package mx.com.aforecoppel.registromovilguardardatos.sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("persistentService")
public class PersistentServiceImpl implements PersistentService {

	final static Logger logger = LoggerFactory.getLogger(PersistentServiceImpl.class);

	@Autowired
	@Qualifier("bitacoraAforedigitalDataSource")
	private DataSource datasourceBitacoraAforedigital;

	@Autowired
	@Qualifier("aforeGlobalDataSource")
	private DataSource datasourceAforeGlobal;

	@Autowired
	@Qualifier("aforeDigitalDataSource")
	private DataSource datasourceaAforeDigital;

	@Override
	public Integer fun_regmovil_eliminarregistro(long folioSolicitud) throws SQLException {
		Integer respuesta = 0;
		CallableStatement clbStmtAforeDigital = null;
		String sqlQuery = null;
		Connection conexionAforeDigital = null;
		ResultSet rsEliminarregistro = null;
		String cMessageLog = "::WSREGISTROMOVILGUARDARDATOS::[" + folioSolicitud
				+ "][PersistentServiceImpl.fun_regmovil_eliminarregistro]";
		try {
			conexionAforeDigital = datasourceaAforeDigital.getConnection();

			sqlQuery = "{call fun_regmovil_eliminarregistro(?)}";
			clbStmtAforeDigital = conexionAforeDigital.prepareCall(sqlQuery);
			clbStmtAforeDigital.setLong(1, folioSolicitud);

			rsEliminarregistro = clbStmtAforeDigital.executeQuery();

			if (rsEliminarregistro != null) {
				if (rsEliminarregistro.next()) {
					if (rsEliminarregistro.getInt(1) > 0) {
						respuesta = rsEliminarregistro.getInt(1);
						logger.info(cMessageLog + ":: FUNCION fun_regmovil_eliminarregistro EJECUTADA CON EXITO {"
								+ respuesta + "}");
					} else {
						logger.warn(cMessageLog
								+ ":: !!! FUNCION fun_regmovil_eliminarregistro NO ACTUALIZO DATOS EN BD ¡¡¡");
					}
				}
			} else {
				logger.warn(cMessageLog + " !!! RESULTSET NULL rsGuardaRegistroBeneficiariosAppMovil ¡¡¡");
			}

		} catch (Exception e) {
			logger.error(cMessageLog + "[PersistentServiceImpl.fun_regmovil_eliminarregistro]::EXCEPCION:: "
					+ ExceptionUtils.getStackTrace(e));
		} finally {
			if (rsEliminarregistro != null && rsEliminarregistro.isClosed() == false) {
				rsEliminarregistro.close();
			}

			if (clbStmtAforeDigital != null && clbStmtAforeDigital.isClosed() == false) {
				clbStmtAforeDigital.close();
			}

			if (conexionAforeDigital != null && conexionAforeDigital.isClosed() == false) {
				conexionAforeDigital.close();
			}

			clbStmtAforeDigital = null;
			sqlQuery = null;
			conexionAforeDigital = null;
			rsEliminarregistro = null;
		}
		return respuesta;
	}

	@Override
	public Integer fun_regmovil_guardarbeneficiarios(long folioSolicitud, String nombreBeneficiario,
			String apellidoPaternoBeneficiario, String apellidoMaternoBeneficiario, String parentescoBeneficiario,
			String curpBeneficiario, String porcentajeBeneficiario, String curpAhorrador, String nss,
			Integer idProcesoExpediente) throws SQLException {
		Integer respuesta = 0;
		CallableStatement clbStmtAforeDigital = null;
		String sqlQuery = null;
		Connection conexionAforeDigital = null;
		ResultSet rsGuardarbeneficiarios = null;

		String cMessageLog = "::WSREGISTROMOVILGUARDARDATOS::[" + folioSolicitud
				+ "][PersistentServiceImpl.fun_regmovil_guardarbeneficiarios]";
		try {
			conexionAforeDigital = datasourceaAforeDigital.getConnection();

			sqlQuery = "{call fun_regmovil_guardarbeneficiarios(?,?,?,?,?,?,?,?,?,?)}";
			clbStmtAforeDigital = conexionAforeDigital.prepareCall(sqlQuery);
			clbStmtAforeDigital.setLong(1, folioSolicitud);// iFolioSolicitud ALIAS FOR $1;
			clbStmtAforeDigital.setString(2, nombreBeneficiario);// cNombrebeneficiario ALIAS FOR $2;
			clbStmtAforeDigital.setString(3, apellidoPaternoBeneficiario);// cAapellidopaternobeneficiario ALIAS FOR $3;
			clbStmtAforeDigital.setString(4, apellidoMaternoBeneficiario);// cApellidomaternobeneficiario ALIAS FOR $4;
			clbStmtAforeDigital.setString(5, parentescoBeneficiario);// cparentescobeneficiario ALIAS FOR $5;
			clbStmtAforeDigital.setString(6, curpBeneficiario);// cCurpbeneficiario ALIAS FOR $6;
			clbStmtAforeDigital.setString(7, porcentajeBeneficiario);// cPorcentajebeneficiario ALIAS FOR $7;
			clbStmtAforeDigital.setString(8, curpAhorrador);// cCurpahorrador ALIAS FOR $8;
			clbStmtAforeDigital.setString(9, nss);// cNssahorrador ALIAS FOR $9;
			clbStmtAforeDigital.setInt(10, idProcesoExpediente);// iTiposolicitud ALIAS FOR $10;

			rsGuardarbeneficiarios = clbStmtAforeDigital.executeQuery();

			if (rsGuardarbeneficiarios != null) {
				if (rsGuardarbeneficiarios.next()) {
					respuesta = rsGuardarbeneficiarios.getInt(1);
					logger.info(cMessageLog + ":: FUNCION fun_regmovil_guardarbeneficiarios EJECUTADA CON EXITO {"
							+ respuesta + "}");
				}
			} else {
				logger.warn(cMessageLog + " !!! RESULTSET NULL rsGuardaRegistroBeneficiariosAppMovil ¡¡¡");
			}
		} catch (Exception e) {
			logger.error(cMessageLog + "[PersistentServiceImpl.fun_regmovil_guardarbeneficiarios]::EXCEPCION:: "
					+ ExceptionUtils.getStackTrace(e));
		} finally {
			if (rsGuardarbeneficiarios != null && rsGuardarbeneficiarios.isClosed() == false) {
				rsGuardarbeneficiarios.close();
			}

			if (clbStmtAforeDigital != null && clbStmtAforeDigital.isClosed() == false) {
				clbStmtAforeDigital.close();
			}

			if (conexionAforeDigital != null && conexionAforeDigital.isClosed() == false) {
				conexionAforeDigital.close();
			}

			clbStmtAforeDigital = null;
			sqlQuery = null;
			conexionAforeDigital = null;
			rsGuardarbeneficiarios = null;
		}
		return respuesta;
	}

	@Override
	public Integer fun_regmovil_validarfoliosolicitud(long folioSolicitud) throws SQLException {
		Integer respuesta = 0;
		CallableStatement clbStmtAforeDigital = null;
		String sqlQuery = null;
		Connection conexionAforeDigital = null;
		ResultSet rsValidarfoliosolicitud = null;

		String cMessageLog = "::WSREGISTROMOVILGUARDARDATOS::[" + folioSolicitud
				+ "][PersistentServiceImpl.fun_regmovil_validarfoliosolicitud]";

		try {
			conexionAforeDigital = datasourceaAforeDigital.getConnection();

			sqlQuery = "{call fun_regmovil_validarfoliosolicitud(?)}";
			clbStmtAforeDigital = conexionAforeDigital.prepareCall(sqlQuery);
			clbStmtAforeDigital.setLong(1, folioSolicitud);

			rsValidarfoliosolicitud = clbStmtAforeDigital.executeQuery();

			if (rsValidarfoliosolicitud != null) {
				if (rsValidarfoliosolicitud.next()) {
					respuesta = rsValidarfoliosolicitud.getInt(1);
					logger.info(cMessageLog + ":: FUNCION fun_regmovil_validarfoliosolicitud EJECUTADA CON EXITO {"
							+ respuesta + "}");
				}
			} else {
				logger.warn(cMessageLog + " !!! RESULTSET NULL fun_regmovil_validarfoliosolicitud ¡¡¡");
			}
		} catch (Exception e) {
			logger.error(cMessageLog + "[PersistentServiceImpl.fun_regmovil_validarfoliosolicitud]::EXCEPCION:: "
					+ ExceptionUtils.getStackTrace(e));
		} finally {
			if (rsValidarfoliosolicitud != null && rsValidarfoliosolicitud.isClosed() == false) {
				rsValidarfoliosolicitud.close();
			}

			if (clbStmtAforeDigital != null && clbStmtAforeDigital.isClosed() == false) {
				clbStmtAforeDigital.close();
			}

			if (conexionAforeDigital != null && conexionAforeDigital.isClosed() == false) {
				conexionAforeDigital.close();
			}

			clbStmtAforeDigital = null;
			sqlQuery = null;
			conexionAforeDigital = null;
			rsValidarfoliosolicitud = null;
		}
		return respuesta;
	}

	@Override
	public HashMap<String, String> fun_regmovil_consultadireccionmenones(String curpTutor, String messageLog)
			throws SQLException {
		HashMap<String, String> hRespuesta = null;
		CallableStatement clbStmtAforeGlobal = null;
		String sqlQuery = null;
		Connection conexionAforeGlobal = null;
		ResultSet rsConsultadireccionmenones = null;

		String cMessageLog = "::WSREGISTROMOVILGUARDARDATOS::[" + curpTutor
				+ "][PersistentServiceImpl.fun_regmovil_consultadireccionmenones]";

		try {
			hRespuesta = new HashMap<String, String>();
			conexionAforeGlobal = datasourceAforeGlobal.getConnection();

			sqlQuery = "{call fun_regmovil_consultadireccionmenones(?)}";
			clbStmtAforeGlobal = conexionAforeGlobal.prepareCall(sqlQuery);
			clbStmtAforeGlobal.setString(1, curpTutor);

			rsConsultadireccionmenones = clbStmtAforeGlobal.executeQuery();

			if (rsConsultadireccionmenones != null) {
				if (rsConsultadireccionmenones.next()) {
					hRespuesta.put("calle", rsConsultadireccionmenones.getString("calle"));
					hRespuesta.put("numero_exterior", rsConsultadireccionmenones.getString("numero_exterior"));
					hRespuesta.put("colonia", rsConsultadireccionmenones.getString("colonia"));
					hRespuesta.put("ciudad_poblacion", rsConsultadireccionmenones.getString("ciudad_poblacion"));
					hRespuesta.put("entidad_federativa", rsConsultadireccionmenones.getString("entidad_federativa"));
					hRespuesta.put("pais", rsConsultadireccionmenones.getString("pais"));
					logger.info(cMessageLog + ":: FUNCION fun_regmovil_consultadireccionmenones EJECUTADA CON EXITO '"
							+ hRespuesta.toString() + "'");
				}
			} else {
				logger.warn(cMessageLog + " !!! RESULTSET NULL fun_regmovil_consultadireccionmenones ¡¡¡");
			}
		} catch (Exception e) {
			logger.error(cMessageLog + "[PersistentServiceImpl.fun_regmovil_consultadireccionmenones]::EXCEPCION:: "
					+ ExceptionUtils.getStackTrace(e));
		} finally {
			if (rsConsultadireccionmenones != null && rsConsultadireccionmenones.isClosed() == false) {
				rsConsultadireccionmenones.close();
			}

			if (clbStmtAforeGlobal != null && clbStmtAforeGlobal.isClosed() == false) {
				clbStmtAforeGlobal.close();
			}

			if (conexionAforeGlobal != null && conexionAforeGlobal.isClosed() == false) {
				conexionAforeGlobal.close();
			}

			clbStmtAforeGlobal = null;
			sqlQuery = null;
			conexionAforeGlobal = null;
			rsConsultadireccionmenones = null;
		}
		return hRespuesta;
	}

	@Override
	public Integer fun_regmovil_guardarregistroapp(String curpAhorrador, String curpTutor, String nickName, String sNss,
			String sNombre, String apellidoPaterno, String apellidoMaterno, String entidadNacimiento, String sSexo,
			String fechaNacimiento, String sNacionalidad, String telefonoFijo, String telefonoCelular,
			String correoElectronico, String sCalle, String numeroExterior, String numeroInterior, String sColonia,
			String ciudadPoblacion, String sPais, String entidadFederativa, String municipioDelegacion,
			String codigoPostal, String codigoPromocional, String actividadEconomicaAhorrador, String aplicacionOrigen,
			Integer idProcesoExpediente, String dFechaEnvio, Integer calificacionImagen, String tipoAfiliacion,
			String sFolio, int folioSolicitud, String ocupacion, String nivelEstudios, String fechaTransaccion, String messageLog)
			throws SQLException {
		Integer respuesta = 0;
		CallableStatement clbStmtAforeDigital = null;
		String sqlQuery = null;
		Connection conexionAforeDigital = null;
		ResultSet rsGuardarregistroapp = null;

		String cMessageLog = "::WSREGISTROMOVILGUARDARDATOS::[" + folioSolicitud
				+ "][PersistentServiceImpl.fun_regmovil_guardarregistroapp]";

		try {
			conexionAforeDigital = datasourceaAforeDigital.getConnection();

			sqlQuery = "{call fun_regmovil_guardarnotificacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			clbStmtAforeDigital = conexionAforeDigital.prepareCall(sqlQuery);
			clbStmtAforeDigital.setString(1, curpAhorrador); // cCurpAhorrador ALIAS FOR $1;
			clbStmtAforeDigital.setString(2, curpTutor); // cCurpTutor ALIAS FOR $2;
			clbStmtAforeDigital.setString(3, nickName); // cNickname ALIAS FOR $3;
			clbStmtAforeDigital.setString(4, sNss); // cNss ALIAS FOR $4;
			clbStmtAforeDigital.setString(5, sNombre); // cNombre ALIAS FOR $5;
			clbStmtAforeDigital.setString(6, apellidoPaterno); // cApellidoPaterno ALIAS FOR $6;
			clbStmtAforeDigital.setString(7, apellidoMaterno); // cApellidoMaterno ALIAS FOR $7;
			clbStmtAforeDigital.setString(8, entidadNacimiento); // cEntidadNacimiento ALIAS FOR $8;
			clbStmtAforeDigital.setString(9, sSexo); // cSexo ALIAS FOR $9;
			clbStmtAforeDigital.setString(10, fechaNacimiento); // cFechaNacimiento ALIAS FOR $10;
			clbStmtAforeDigital.setString(11, sNacionalidad); // cNacionalidad ALIAS FOR $11;
			clbStmtAforeDigital.setString(12, telefonoFijo); // cTelefonoFijo ALIAS FOR $12;
			clbStmtAforeDigital.setString(13, telefonoCelular); // cTelefonoCelular ALIAS FOR $13;
			clbStmtAforeDigital.setString(14, correoElectronico); // cCorreoElectronico ALIAS FOR $14;
			clbStmtAforeDigital.setString(15, sCalle); // cCalle ALIAS FOR $15;
			clbStmtAforeDigital.setString(16, numeroExterior); // cNumeroExterior ALIAS FOR $16;
			clbStmtAforeDigital.setString(17, numeroInterior); // cNumeroInterior ALIAS FOR $17;
			clbStmtAforeDigital.setString(18, sColonia); // cColonia ALIAS FOR $18;
			clbStmtAforeDigital.setString(19, ciudadPoblacion); // cCiudadPoblacion ALIAS FOR $19;
			clbStmtAforeDigital.setString(20, sPais); // cPais ALIAS FOR $20;
			clbStmtAforeDigital.setString(21, entidadFederativa); // cEntidadFederativa ALIAS FOR $21;
			clbStmtAforeDigital.setString(22, municipioDelegacion); // cMunicipioDelegacion ALIAS FOR $22;
			clbStmtAforeDigital.setString(23, codigoPostal); // cCodigoPostal ALIAS FOR $23;
			clbStmtAforeDigital.setString(24, codigoPromocional); // cCodigoPromocional ALIAS FOR $24;
			clbStmtAforeDigital.setString(25, actividadEconomicaAhorrador); // ActividadEconomicaAhorrador ALIAS FOR
			clbStmtAforeDigital.setString(26, aplicacionOrigen); // cAplicacionOrigen ALIAS FOR $26;
			clbStmtAforeDigital.setInt(27, idProcesoExpediente); // iIdProcesoExpediente ALIAS FOR $27;
			clbStmtAforeDigital.setString(28, dFechaEnvio); // dFechaEnvio ALIAS FOR $28;
			clbStmtAforeDigital.setInt(29, calificacionImagen); // iCalificacionImagen ALIAS FOR $29;
			clbStmtAforeDigital.setString(30, tipoAfiliacion); // cTipoAfiliacion ALIAS FOR $30;
			clbStmtAforeDigital.setString(31, sFolio); // cFolio ALIAS FOR $31;
			clbStmtAforeDigital.setInt(32, folioSolicitud); // iFolioSolicitud ALIAS FOR $32;
			clbStmtAforeDigital.setString(33, ocupacion); // cOcupacion ALIAS FOR $33;
			clbStmtAforeDigital.setString(34, nivelEstudios); // cNivelEstudios ALIAS FOR $34;
			clbStmtAforeDigital.setInt(35, 1); // iGuardaJms ALIAS FOR $35;
			clbStmtAforeDigital.setString(36, fechaTransaccion); // fechaTransaccion ALIAS FOR $36;

			rsGuardarregistroapp = clbStmtAforeDigital.executeQuery();

			if (rsGuardarregistroapp != null) {
				if (rsGuardarregistroapp.next()) {
					respuesta = rsGuardarregistroapp.getInt(1);
					logger.info(cMessageLog + ":: FUNCION fun_regmovil_guardarregistroapp EJECUTADA CON EXITO {"
							+ respuesta + "}");
				}
			} else {
				logger.warn(cMessageLog + " !!! RESULTSET NULL fun_regmovil_guardarregistroapp ¡¡¡");
			}
		} catch (Exception e) {
			logger.error(cMessageLog + "[PersistentServiceImpl.fun_regmovil_guardarregistroapp]::EXCEPCION:: "
					+ ExceptionUtils.getStackTrace(e));
		} finally {
			if (rsGuardarregistroapp != null && rsGuardarregistroapp.isClosed() == false) {
				rsGuardarregistroapp.close();
			}

			if (clbStmtAforeDigital != null && clbStmtAforeDigital.isClosed() == false) {
				clbStmtAforeDigital.close();
			}

			if (conexionAforeDigital != null && conexionAforeDigital.isClosed() == false) {
				conexionAforeDigital.close();
			}

			clbStmtAforeDigital = null;
			sqlQuery = null;
			conexionAforeDigital = null;
			rsGuardarregistroapp = null;
		}
		return respuesta;
	}

	@Override
	public long fun_regmovil_obtenerfoliosolicitud() throws SQLException {
		Integer respuesta = 0;
		CallableStatement clbStmtAforeGlobal = null;
		String sqlQuery = null;
		Connection conexionAforeGlobal = null;
		ResultSet rsObtenerfoliosolicitud = null;

		String cMessageLog = "::WSREGISTROMOVILGUARDARDATOS::[PersistentServiceImpl.fun_regmovil_obtenerfoliosolicitud]";

		try {
			conexionAforeGlobal = datasourceAforeGlobal.getConnection();

			sqlQuery = "{call fun_regmovil_obtenerfoliosolicitud()}";
			clbStmtAforeGlobal = conexionAforeGlobal.prepareCall(sqlQuery);

			rsObtenerfoliosolicitud = clbStmtAforeGlobal.executeQuery();

			if (rsObtenerfoliosolicitud != null) {
				if (rsObtenerfoliosolicitud.next()) {
					if (rsObtenerfoliosolicitud.getInt(1) > 0) {
						respuesta = rsObtenerfoliosolicitud.getInt(1);
						logger.info(cMessageLog + ":: FUNCION fun_regmovil_obtenerfoliosolicitud EJECUTADA CON EXITO {"
								+ respuesta + "}");
					} else {
						logger.warn(cMessageLog
								+ ":: !!! FUNCION fun_regmovil_obtenerfoliosolicitud NO ACTUALIZO DATOS EN BD ¡¡¡");
					}
				}
			} else {
				logger.warn(cMessageLog + " !!! RESULTSET NULL fun_regmovil_obtenerfoliosolicitud ¡¡¡");
			}
		} catch (Exception e) {
			logger.error(cMessageLog + "[PersistentServiceImpl.fun_regmovil_obtenerfoliosolicitud]::EXCEPCION:: "
					+ ExceptionUtils.getStackTrace(e));
		} finally {
			if (rsObtenerfoliosolicitud != null && rsObtenerfoliosolicitud.isClosed() == false) {
				rsObtenerfoliosolicitud.close();
			}

			if (clbStmtAforeGlobal != null && clbStmtAforeGlobal.isClosed() == false) {
				clbStmtAforeGlobal.close();
			}

			if (conexionAforeGlobal != null && conexionAforeGlobal.isClosed() == false) {
				conexionAforeGlobal.close();
			}

			clbStmtAforeGlobal = null;
			sqlQuery = null;
			conexionAforeGlobal = null;
			rsObtenerfoliosolicitud = null;
		}
		return respuesta;
	}

}
