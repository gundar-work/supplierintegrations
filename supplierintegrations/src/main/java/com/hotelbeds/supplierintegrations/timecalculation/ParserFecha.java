//package com.hotelbeds.supplierintegrations.timecalculation;
//
//import java.time.OffsetDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Locale;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//class ParserFecha {
//	
//	private static final Logger log = LoggerFactory.getLogger(ParserFecha.class);
//	
//	private final DateTimeFormatter parserFecha;
//	
//	public ParserFecha(String formatoFecha) {
//		this.parserFecha = DateTimeFormatter.ofPattern(formatoFecha, Locale.US);
//	}
//	
//	public OffsetDateTime parse(String fecha) {
//		
//		OffsetDateTime fechaParseada = OffsetDateTime.parse(fecha, parserFecha);
//		
//		log.debug("Fecha [{}] parseada como [{}]", fecha, fechaParseada);
//		
//		return fechaParseada;
//	}
//	
//	@Override
//	public String toString() {
//		return "ParserRfc2822 [PARSER_RFC_2822=" + parserFecha + "]";
//	}
//
//}
