/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.aforecoppel.registromovilguardardatos.utils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author adrian
 */
public class MyUtils {

	final static Logger logger = LoggerFactory.getLogger(MyUtils.class);

	public static void objectToString(Object obj, Logger logger)
			throws IllegalArgumentException, IllegalAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("---------- {} -----------", obj.getClass().getName());
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field f : fields) {
				f.setAccessible(true);
				logger.debug("{} : {}", f.getName(), f.get(obj) != null ? f.get(obj).toString() : "null");
				f.setAccessible(false);
			}
			logger.debug("---------- {} -----------", obj.getClass().getName());
		}
	}

	public static ArrayList<Exception> eliminarDuplicados(ArrayList<Exception> excepciones) {
		HashSet<Exception> hs = new HashSet<>();
		hs.addAll(excepciones);
		excepciones.clear();
		excepciones.addAll(hs);
		return excepciones;
	}

	public static boolean isValidTime(Calendar today) {
		int dayOfW = today.get(Calendar.DAY_OF_WEEK);

		Calendar calendar1 = (Calendar) today.clone();
		calendar1.set(Calendar.HOUR_OF_DAY, 8);
		calendar1.set(Calendar.MINUTE, 0);

		Calendar calendar2 = (Calendar) today.clone();
		calendar2.set(Calendar.HOUR_OF_DAY, 21);
		calendar2.set(Calendar.MINUTE, 45);

		return today.after(calendar1) && today.before(calendar2) && 1 < dayOfW && dayOfW < 7;

	}

	// retorna true si la hora actual esta entre la hora inicio y hora fin
	public static boolean isAvailableDataBase(String messageLog, String horaInicio, String horaFin) {
		boolean isAvailable = false;
		String horaActual;
		Date parseHoraActual;
		Date inicia;
		Date finaliza;

		try {
			horaActual = new SimpleDateFormat("HH:mm").format(new Date());// obtiene la fecha actual

			logger.info(messageLog + "[MyUtils.isAvailableDataBase]:: (horaActual | horaInicio | horaFin) = ("
					+ horaActual + " | " + horaInicio + " | " + horaFin + ")");

			if ((horaInicio != null && !horaInicio.isEmpty()) && (horaFin != null && !horaFin.isEmpty())) {

				parseHoraActual = new SimpleDateFormat("HH:mm").parse(horaActual);// obtiene la hora actual

				inicia = new SimpleDateFormat("HH:mm").parse(horaInicio);
				finaliza = new SimpleDateFormat("HH:mm").parse(horaFin);

				if (inicia.after(finaliza)) {// si la fecha de mantenimiento inicia un dia y termina al siguiente
					if ((inicia.after(parseHoraActual) && finaliza.before(parseHoraActual))) {
						isAvailable = true;
					}
				} else { // si la fecha de mantenieminto inicia y termina el mismo dia
					if (!(parseHoraActual.after(inicia) && parseHoraActual.before(finaliza))) {
						isAvailable = true;
					}
				}
			} else {
				logger.warn(messageLog + "[MyUtils.isAvailableDataBase]:: BAD DATA :: "
						+ " !!! (horaInicio | horaFin) = (" + horaInicio + " | " + horaFin + ") ¡¡¡");
			}
		} catch (ParseException e) {
			logger.error(messageLog + "[MyUtils.isAvailableDataBase]::EXCEPCION:: " + ExceptionUtils.getStackTrace(e));
		}
		return isAvailable;
	}

	//retorna la hora y minutos de la expresion cron con formato HH:mm
	public static String getHoraCronString(String messageLog,String CronExpression) {
		String horaFormat = null;
		try {
			String[] parts = CronExpression.split(" ");
			//ejemplo expresion cron "0 26 10 * * ?"
			//parts[2] = hora en expresion 	  = 10
			//parts[1] = minutos en expresion = 26
			horaFormat = parts[2] + ":" + parts[1];
		} catch (Exception e) {
			logger.error(messageLog + "[MyUtils.getHoraCronString]::EXCEPCION:: " + ExceptionUtils.getStackTrace(e));
		}
		return horaFormat;
	}
}
