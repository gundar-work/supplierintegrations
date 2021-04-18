package com.hotelbeds.supplierintegrations.timecalculation;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.function.ToLongBiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeCalculation implements ToLongBiFunction<String, String>{

	private static final Logger log = LoggerFactory.getLogger(TimeCalculation.class);
	private static final String FORMATO_RFC_2822 = "EEE, dd MMM yyyy HH:mm:ss Z";

	private final DateTimeFormatter parserFecha;

	public TimeCalculation() {
		this(FORMATO_RFC_2822);
	}

	public TimeCalculation(String formatoFecha) {
		this.parserFecha = DateTimeFormatter.ofPattern(formatoFecha, Locale.US);
	}

	@Override
	public long applyAsLong(String fecha1, String fecha2) {

		log.info("Calculando diferencia en minutos entre [{}] y [{}]", fecha1, fecha2);

		OffsetDateTime fecha1Parseada = parse(fecha1);
		OffsetDateTime fecha2Parseada = parse(fecha2);

		long minutosDiferencia = ChronoUnit.MINUTES.between(fecha1Parseada, fecha2Parseada);
		minutosDiferencia = Math.abs(minutosDiferencia);

		log.debug("[{}] minutos de diferencia entre la fecha [{}] y la fecha [{}]", minutosDiferencia, fecha1Parseada, fecha2Parseada);

		return minutosDiferencia;
	}

	private OffsetDateTime parse(String fecha) {

		OffsetDateTime fechaParseada = OffsetDateTime.parse(fecha, parserFecha);

		log.debug("Fecha [{}] parseada como [{}]", fecha, fechaParseada);

		return fechaParseada;
	}

	@Override
	public String toString() {
		return "TimeCalculation [parserFecha=" + parserFecha + "]";
	}

}
