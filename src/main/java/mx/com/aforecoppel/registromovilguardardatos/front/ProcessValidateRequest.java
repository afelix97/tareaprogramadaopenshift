
package mx.com.aforecoppel.registromovilguardardatos.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import mx.com.aforecoppel.registromovilguardardatos.jaxb.*;
import mx.com.aforecoppel.registromovilguardardatos.sql.PersistentService;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
/*
 * Created by Eduardo Cota
 * 9 April 2019
 */

@Component
public class ProcessValidateRequest implements Processor {

	final static Logger logger = LoggerFactory.getLogger(ProcessValidateRequest.class);

	private ArrayList<Exception> excepciones = new ArrayList<>();

	private final PersistentService persistentService;

	@Autowired
	public ProcessValidateRequest(PersistentService persistentService) {
		this.persistentService = persistentService;
	}

	/**
	 * @return
	 */
	public ArrayList<Exception> getExcepciones() {
		return excepciones;
	}

	/**
	 * @param excepciones
	 */
	public void setExcepciones(ArrayList<Exception> excepciones) {
		this.excepciones = excepciones;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		NotificarRegistroMovilContrato notificarRegistroMovilContrato = null;
		NotificarRegistroMovilEntrada notificarRegistroMovilEntrada = null;
		String messageLog = null;
		// Campos obligatorio del cuerpo de entrada
		String curpAhorrador = null;
		String nombre = null;
		String apellidoPaterno = null;
		Integer idProcesoExpediente = null;
		String fechaEnvio = null;
		String tipoAfiliacion = null;
		String folio = null;
		boolean validarRequest = true;
		ObjectFactory factory = new ObjectFactory();
		NotificarRegistroMovilRespuesta notificarRegistroMovilRespuesta = null;
		NotificarRegistroMovilSalida notificarRegistroMovilSalida = null;
		long folioSolicitud = 0L;
		HashMap<String, String> consultadireccionmenones = null;

		// CAMBIO DE LOGICA BEING
		String curpTutor = null;
		String nickname = null;
		String nss = null;
		String apellidoMaterno = null;
		String entidadNacimiento = null;
		String sexo = null;
		String fechaNacimiento = null;
		String nacionalidad = null;
		String telefonoFijo = null;
		String telefonoCelular = null;
		String correoElectronico = null;
		String calle = null;
		String numeroExterior = null;
		String numeroInterior = null;
		String colonia = null;
		String ciudadPoblacion = null;
		String pais = null;
		String entidadFederativa = null;
		String municipioDelegacion = null;
		String codigoPostal = null;
		String codigoPromocional = null;
		String actividadEconomicaAhorrador = null;
		String aplicacionOrigen = null;
		int calificacionImagen = 0;
		String ocupacion = null;
		String nivelEstudios = null;
		String fechaNotificacion = null;

		Integer resultSaveRegistro = 0;
		Integer resultSaveBeneficiarios = 0;
		Integer resultRollBack = 0;
		Integer resultValidFoliosRollBack = 0;
		// datos beneficiario

		String nombreBeneficiario = null;
		String apellidoPaternoBeneficiario = null;
		String apellidoMaternoBeneficiario = null;
		String curpBeneficiario = null;
		String porcentajeBeneficiario = null;
		// CAMBIO DE LOGICA END

		notificarRegistroMovilContrato = exchange.getIn().getBody(NotificarRegistroMovilContrato.class);
		messageLog = exchange.getIn().getHeader("messageLog") != null
				&& !exchange.getIn().getHeader("messageLog").toString().equals("")
						? exchange.getIn().getHeader("messageLog").toString()
						: "";
		fechaNotificacion = exchange.getIn().getHeader("FECHA_TRANSACCION") != null
				&& !exchange.getIn().getHeader("FECHA_TRANSACCION").toString().equals("")
						? exchange.getIn().getHeader("FECHA_TRANSACCION").toString()
						: "";

		if (notificarRegistroMovilContrato != null) {
			if (notificarRegistroMovilContrato.getCuerpo() != null) {
				try {

					notificarRegistroMovilEntrada = notificarRegistroMovilContrato.getCuerpo();

					/*** VALIDATE MANDATORY FIELDS CONTAINS SOME DATA ***/
					// curpAhorrador
					if (notificarRegistroMovilEntrada.getCurpAhorrador() != null
							&& !notificarRegistroMovilEntrada.getCurpAhorrador().trim().isEmpty())
						curpAhorrador = notificarRegistroMovilEntrada.getCurpAhorrador().toString().trim();
					else
						validarRequest = false;

					// nombre
					if (notificarRegistroMovilEntrada.getNombre() != null
							&& !notificarRegistroMovilEntrada.getNombre().trim().isEmpty())
						nombre = notificarRegistroMovilEntrada.getNombre().toString().trim();
					else
						validarRequest = false;

					// apellidoPaterno
					if (notificarRegistroMovilEntrada.getApellidoPaterno() != null
							&& !notificarRegistroMovilEntrada.getApellidoPaterno().trim().isEmpty())
						apellidoPaterno = notificarRegistroMovilEntrada.getApellidoPaterno().toString().trim();
					else
						validarRequest = false;

					// idProcesoExpediente
					if (notificarRegistroMovilEntrada.getIdProcesoExpediente() != null)
						idProcesoExpediente = notificarRegistroMovilEntrada.getIdProcesoExpediente().intValueExact();
					else
						validarRequest = false;

					// fechaEnvio
					if (notificarRegistroMovilEntrada.getFechaEnvio() != null
							&& !notificarRegistroMovilEntrada.getFechaEnvio().trim().isEmpty()) {
						fechaEnvio = notificarRegistroMovilEntrada.getFechaEnvio().toString().trim();
					} else
						validarRequest = false;

					// tipoAfiliacion
					if (notificarRegistroMovilEntrada.getTipoAfiliacion() == null
							|| notificarRegistroMovilEntrada.getTipoAfiliacion().trim().isEmpty())
						validarRequest = false;
					else
						tipoAfiliacion = notificarRegistroMovilEntrada.getTipoAfiliacion().toString().trim();
					;

					// folio
					if (notificarRegistroMovilEntrada.getFolio() == null
							|| notificarRegistroMovilEntrada.getFolio().trim().isEmpty())
						validarRequest = false;
					else
						folio = notificarRegistroMovilEntrada.getFolio().toString().trim();

						if (validarRequest) {

							// Guardar datos principales
							// CAMBIO DE LOGICA fnrmguardarregistroappmovil BEGIN
							folioSolicitud = persistentService.fun_regmovil_obtenerfoliosolicitud();

							curpTutor = notificarRegistroMovilEntrada.getCurpTutor() != null
									? notificarRegistroMovilEntrada.getCurpTutor().toString().trim()
									: "";

							nickname = notificarRegistroMovilEntrada.getNickname() != null
									? notificarRegistroMovilEntrada.getNickname().toString().trim()
									: "";
							nss = notificarRegistroMovilEntrada.getNss() != null
									? notificarRegistroMovilEntrada.getNss().toString().trim()
									: "";
							apellidoMaterno = notificarRegistroMovilEntrada.getApellidoMaterno() != null
									? notificarRegistroMovilEntrada.getApellidoMaterno().toString().trim()
									: "";
							entidadNacimiento = notificarRegistroMovilEntrada.getEntidadNacimiento() != null
									? notificarRegistroMovilEntrada.getEntidadNacimiento().toString().trim()
									: "";
							sexo = notificarRegistroMovilEntrada.getSexo() != null
									? notificarRegistroMovilEntrada.getSexo().value().toString()
									: "";
							fechaNacimiento = notificarRegistroMovilEntrada.getFechaNacimiento() != null
									? notificarRegistroMovilEntrada.getFechaNacimiento().toString().trim()
									: "";
							nacionalidad = notificarRegistroMovilEntrada.getNacionalidad() != null
									? notificarRegistroMovilEntrada.getNacionalidad().toString().trim()
									: "";
							telefonoFijo = notificarRegistroMovilEntrada.getTelefonoFijo() != null
									? notificarRegistroMovilEntrada.getTelefonoFijo().toString().toString()
									: "";
							telefonoCelular = notificarRegistroMovilEntrada.getTelefonoCelular() != null
									? notificarRegistroMovilEntrada.getTelefonoCelular().toString().trim()
									: "";
							correoElectronico = notificarRegistroMovilEntrada.getCorreoElectronico() != null
									? notificarRegistroMovilEntrada.getCorreoElectronico().toString().trim()
									: "";
							calle = notificarRegistroMovilEntrada.getCalle() != null
									? notificarRegistroMovilEntrada.getCalle().toString().trim()
									: "";
							numeroExterior = notificarRegistroMovilEntrada.getNumeroExterior() != null
									? notificarRegistroMovilEntrada.getNumeroExterior().toString().trim()
									: "";
							numeroInterior = notificarRegistroMovilEntrada.getNumeroInterior() != null
									? notificarRegistroMovilEntrada.getNumeroInterior().toString().trim()
									: "";
							colonia = notificarRegistroMovilEntrada.getColonia() != null
									? notificarRegistroMovilEntrada.getColonia().toString().trim()
									: "";
							ciudadPoblacion = notificarRegistroMovilEntrada.getCiudadPoblacion() != null
									? notificarRegistroMovilEntrada.getCiudadPoblacion().toString().trim()
									: "";
							pais = notificarRegistroMovilEntrada.getPais() != null
									? notificarRegistroMovilEntrada.getPais().toString().trim()
									: "";
							entidadFederativa = notificarRegistroMovilEntrada.getEntidadFederativa() != null
									? notificarRegistroMovilEntrada.getEntidadFederativa().toString().trim()
									: "";
							municipioDelegacion = notificarRegistroMovilEntrada.getMunicipioDelegacion() != null
									? notificarRegistroMovilEntrada.getMunicipioDelegacion().toString().trim()
									: "";
							codigoPostal = notificarRegistroMovilEntrada.getCodigoPostal() != null
									? notificarRegistroMovilEntrada.getCodigoPostal().toString().trim()
									: "";
							codigoPromocional = notificarRegistroMovilEntrada.getCodigoPromocional() != null
									? notificarRegistroMovilEntrada.getCodigoPromocional().toString().trim()
									: "";
							actividadEconomicaAhorrador = notificarRegistroMovilEntrada
									.getActividadEconomicaAhorrador() != null
											? notificarRegistroMovilEntrada.getActividadEconomicaAhorrador().toString()
													.trim()
											: "";
							aplicacionOrigen = notificarRegistroMovilEntrada.getAplicacionOrigen() != null
									? notificarRegistroMovilEntrada.getAplicacionOrigen().toString().trim()
									: "";
							calificacionImagen = notificarRegistroMovilEntrada.getCalificacionImagen() != null
									? notificarRegistroMovilEntrada.getCalificacionImagen().intValueExact()
									: 0;
							ocupacion = notificarRegistroMovilEntrada.getOcupacion() != null
									? notificarRegistroMovilEntrada.getOcupacion().toString().trim()
									: "";

							nivelEstudios = notificarRegistroMovilEntrada.getNivelEstudios() != null
									? notificarRegistroMovilEntrada.getNivelEstudios().toString().trim()
									: "";

							if (idProcesoExpediente == 80) {
								consultadireccionmenones = persistentService
										.fun_regmovil_consultadireccionmenones(curpTutor, messageLog);

								calle = consultadireccionmenones != null
										&& consultadireccionmenones.get("calle") != null
												? consultadireccionmenones.get("calle").toString().trim()
												: "";
								numeroExterior = consultadireccionmenones != null
										&& consultadireccionmenones.get("numero_exterior") != null
												? consultadireccionmenones.get("numero_exterior").toString().trim()
												: "";
								colonia = consultadireccionmenones != null
										&& consultadireccionmenones.get("colonia") != null
												? consultadireccionmenones.get("colonia").toString().trim()
												: "";
								ciudadPoblacion = consultadireccionmenones != null
										&& consultadireccionmenones.get("ciudad_poblacion") != null
												? consultadireccionmenones.get("ciudad_poblacion").toString().trim()
												: "";
								entidadFederativa = consultadireccionmenones != null
										&& consultadireccionmenones.get("entidad_federativa") != null
												? consultadireccionmenones.get("entidad_federativa").toString().trim()
												: "";
								pais = consultadireccionmenones != null && consultadireccionmenones.get("pais") != null
										? consultadireccionmenones.get("pais").toString().trim()
										: "";
							}

							resultSaveRegistro = persistentService.fun_regmovil_guardarregistroapp(curpAhorrador,
									curpTutor, nickname, nss, nombre, apellidoPaterno, apellidoMaterno,
									entidadNacimiento, sexo, fechaNacimiento, nacionalidad, telefonoFijo,
									telefonoCelular, correoElectronico, calle, numeroExterior, numeroInterior, colonia,
									ciudadPoblacion, pais, entidadFederativa, municipioDelegacion, codigoPostal,
									codigoPromocional, actividadEconomicaAhorrador, aplicacionOrigen,
									idProcesoExpediente, fechaEnvio, calificacionImagen, tipoAfiliacion, folio,
									Integer.parseInt(String.valueOf(folioSolicitud)), ocupacion, nivelEstudios, fechaNotificacion,
									messageLog);

							// CAMBIO DE LOGICA END

							// SI FOLIO SOLICITUD ES IGUAL A 0 ES POR QUE OCURRIO UNA EXCEPCION EN LA BD O
							// SE REGISTRO DE MANERA INCORRECTA
							if (folioSolicitud == 0 && resultSaveRegistro == 0) {
								logger.info("EL FOLIO DE SOLICITUD ES IGUAL A 0");
								validarRequest = false;
							}
							if (validarRequest) {
								// Registrar beneficiarios
								if (notificarRegistroMovilEntrada != null
										&& notificarRegistroMovilEntrada.getBeneficiarios() != null
										&& notificarRegistroMovilEntrada.getBeneficiarios().getBeneficiario() != null
										&& notificarRegistroMovilEntrada.getBeneficiarios().getBeneficiario().size() > 0
										&& validarRequest) {
									boolean validarGuardadoBeneficiarios = true;
									for (Beneficiario beneficiario : notificarRegistroMovilEntrada.getBeneficiarios()
											.getBeneficiario()) {

										// CAMBIO DE LOGICA fnrmguardarregistrobeneficiariosappmovil BEGIN

										nombreBeneficiario = beneficiario != null
												&& beneficiario.getNombreBeneficiario() != null
														? beneficiario.getNombreBeneficiario().trim()
														: "";
										apellidoPaternoBeneficiario = beneficiario != null
												&& beneficiario.getApellidoPaternoBeneficiario() != null
														? beneficiario.getApellidoPaternoBeneficiario().trim()
														: "";
										apellidoMaternoBeneficiario = beneficiario != null
												&& beneficiario.getApellidoMaternoBeneficiario() != null
														? beneficiario.getApellidoMaternoBeneficiario().trim()
														: "";
										curpBeneficiario = beneficiario != null
												&& beneficiario.getCurpBeneficiario() != null
														? beneficiario.getCurpBeneficiario().trim()
														: "";
										porcentajeBeneficiario = beneficiario != null
												&& beneficiario.getPorcentajeBeneficiario() != null
														? beneficiario.getPorcentajeBeneficiario().trim()
														: "";

										// OPCION 1 LOGICA FUNCION ORIGINAL fnrmguardarbeneficiarios
										String parentesco = "";// 1975.2 no se contemplara mas el parentesco

										resultSaveBeneficiarios = persistentService.fun_regmovil_guardarbeneficiarios(
												folioSolicitud, nombreBeneficiario, apellidoPaternoBeneficiario,
												apellidoMaternoBeneficiario, parentesco, curpBeneficiario,
												porcentajeBeneficiario, curpAhorrador, nss, idProcesoExpediente);

										// CAMBIO DE LOGICA fnrmguardarregistrobeneficiariosappmovil END

										if (resultSaveBeneficiarios > 0) {
											logger.info(messageLog + "[ProcessValidateRequest.process]::"
													+ " Datos CURP Beneficiario["
													+ Optional.ofNullable(beneficiario.getCurpBeneficiario()).orElse("")
													+ "] REGISTRADOS ::");
										} else {
											validarGuardadoBeneficiarios = false;
											logger.warn(messageLog + "[ProcessValidateRequest.process]::"
													+ " Datos CURP Beneficiario["
													+ Optional.ofNullable(beneficiario.getCurpBeneficiario()).orElse("")
													+ "] NO SE  REGISTRARON ::");
										}

										nombreBeneficiario = null;
										apellidoPaternoBeneficiario = null;
										apellidoMaternoBeneficiario = null;
										curpBeneficiario = null;
										porcentajeBeneficiario = null;

									}
									if (!validarGuardadoBeneficiarios) {
										validarRequest = false;
										logger.warn(messageLog + "[ProcessValidateRequest.process]::"
												+ " ALGO FALLO AL REGISTRAR BENEFICIARIOS HACIENDO ROLLBACK ::");
										// CAMBIO DE LOGICA fnrmguardarregistrobeneficiariosappmovil BEGIN
										resultRollBack = persistentService
												.fun_regmovil_eliminarregistro(folioSolicitud);

										if (resultRollBack > 0) {
											resultValidFoliosRollBack = persistentService
													.fun_regmovil_validarfoliosolicitud(folioSolicitud);
										}

										// CAMBIO DE LOGICA fnrmguardarregistrobeneficiariosappmovil END

										if (resultValidFoliosRollBack == 1) {
											logger.info(messageLog + "[ProcessValidateRequest.process]::"
													+ "ROLLBACK EJECUTADO CORRECTAMENTE::");
										} else {
											logger.warn(messageLog + "[ProcessValidateRequest.process]::"
													+ "ROLLBACK EJECUTADO INCORRECTAMENTE::::");
										}
									}

								} else {
									// para el caso cuando no manden la etiqueta <beneficiarios> en request
									boolean validarGuardadoBeneficiarios = true;

									// CAMBIO DE LOGICA fnrmguardarregistrobeneficiariosappmovil BEGIN
									resultSaveBeneficiarios = persistentService.fun_regmovil_guardarbeneficiarios(
											folioSolicitud, "", "", "", "", "", "", curpAhorrador, nss,
											idProcesoExpediente);
									// CAMBIO DE LOGICA fnrmguardarregistrobeneficiariosappmovil END

									if (resultSaveBeneficiarios > 0) {
										logger.info(messageLog + "[ProcessValidateRequest.process]::"
												+ " Datos Registrados sin beneficiarios");
									} else {
										validarGuardadoBeneficiarios = false;
										logger.warn(messageLog + "[ProcessValidateRequest.process]::"
												+ " Datos No Registrados");
									}

									if (!validarGuardadoBeneficiarios) {
										validarRequest = false;
										logger.warn(messageLog + "[ProcessValidateRequest.process]::"
												+ " ALGO FALLO AL REGISTRAR DATOS SIN BENEFICIARIOS HACIENDO ROLLBACK ::");

										// CAMBIO DE LOGICA fnrmguardarregistrobeneficiariosappmovil BEGIN
										resultRollBack = persistentService
												.fun_regmovil_eliminarregistro(folioSolicitud);

										if (resultRollBack > 0) {
											resultValidFoliosRollBack = persistentService
													.fun_regmovil_validarfoliosolicitud(folioSolicitud);
										}
										// CAMBIO DE LOGICA fnrmguardarregistrobeneficiariosappmovil END

										if (resultValidFoliosRollBack == 1) {
											logger.info(messageLog + "[ProcessValidateRequest.process]::"
													+ "ROLLBACK EJECUTADO CORRECTAMENTE::");
										} else {
											logger.warn(messageLog + "[ProcessValidateRequest.process]::"
													+ "ROLLBACK EJECUTADO INCORRECTAMENTE::::");
										}
									}

									logger.info(messageLog + "[ProcessValidateRequest.process]"
											+ " !!! NO CONTIENE BENEFICIARIOS ¡¡¡ ");
								}
							} else {
								logger.info(messageLog + "[ProcessValidateRequest.process]"
										+ " !!! FUNCION fnrmguardarregistroappmovil REGRESO = 0  ¡¡¡ ");
							}

						} else {
							logger.warn(messageLog + "[ProcessValidateRequest.process]"
									+ " !!! NO SE RECIBIO UNO O MAS CAMPOS OBLIGATORIOS ¡¡¡ ");
						}

					notificarRegistroMovilRespuesta = new NotificarRegistroMovilRespuesta();
					notificarRegistroMovilSalida = new NotificarRegistroMovilSalida();

					if (validarRequest) {
						notificarRegistroMovilSalida.setConfirmacionTransaccion("01");
					} else {
						notificarRegistroMovilSalida.setConfirmacionTransaccion("02");
					}

					/*
					 * Date date = new Date(); SimpleDateFormat formatter = new
					 * SimpleDateFormat("yyyyMMdd hhmmss"); String strDate = formatter.format(date);
					 * strDate = formatter.format(date);
					 */
					
					notificarRegistroMovilSalida.setFechaTransaccion(fechaNotificacion.replaceAll(":", "").replaceAll("-", "").toString().trim());//se manda la fecha de la notificacion que estubo en destiempo

					if (validarRequest) {
						notificarRegistroMovilSalida.setMotivoRechazo("000");
					} else {
						notificarRegistroMovilSalida.setMotivoRechazo("999");
					}

					notificarRegistroMovilRespuesta.setObjetoRespuesta(notificarRegistroMovilSalida);

					exchange.getIn()
							.setBody(factory.createNotificarRegistroMovilResponse(notificarRegistroMovilRespuesta));

				} catch (Exception e) {
					exchange.setException(e);
					logger.warn(messageLog + "[ProcessValidateRequest.process]::"
							+ "Excepcion encontrada en ProcessValidateRequest ::" + ExceptionUtils.getStackTrace(e));
				}

			} else {
				logger.warn(messageLog + "[ProcessValidateRequest.process]::"
						+ " !!! objeto NotificarRegistroMovilContrato.getCuerpo() = null ¡¡¡");
			}
		} else {
			logger.warn(messageLog + "[ProcessValidateRequest.process]::"
					+ " !!! objeto NotificarRegistroMovilContrato = null ¡¡¡");
		}

		messageLog = null;
		notificarRegistroMovilContrato = null;
		notificarRegistroMovilEntrada = null;
		curpAhorrador = null;
		nombre = null;
		fechaNotificacion = null;
		apellidoPaterno = null;
		idProcesoExpediente = null;
		fechaEnvio = null;
		tipoAfiliacion = null;
		folio = null;
		notificarRegistroMovilRespuesta = null;
		notificarRegistroMovilSalida = null;

	}

	// retorna true si la hora actual esta entre la hora inicio y hora fin
	public boolean isAvailableDataBase(String messageLog, String horaInicio, String horaFin) {
		boolean isAvailable = false;
		String horaActual;
		Date parseHoraActual;
		Date inicia;
		Date finaliza;
		try {
			if ((horaInicio != null && !horaInicio.isEmpty()) && (horaFin != null && !horaFin.isEmpty())) {

				horaActual = new SimpleDateFormat("HH:mm").format(new Date());// obtiene la hora actual en string con
				parseHoraActual = new SimpleDateFormat("HH:mm").parse(horaActual);// obtiene la hora actual en date

				inicia = new SimpleDateFormat("HH:mm").parse(horaInicio);
				finaliza = new SimpleDateFormat("HH:mm").parse(horaFin);
				if (!(parseHoraActual.after(inicia) && parseHoraActual.before(finaliza))) {
					isAvailable = true;
				}
			} else {
				logger.warn(messageLog + "[ProcessValidateRequest.isAvailableDataBase]:: BAD DATA :: "
						+ " !!! (horaInicio | horaFin) = (" + horaInicio + " | " + horaFin + ") ¡¡¡");
			}
		} catch (ParseException e) {
			logger.error(messageLog + "[ProcessValidateRequest.isAvailableDataBase]::EXCEPCION:: "
					+ ExceptionUtils.getStackTrace(e));
		}
		return isAvailable;
	}
}
