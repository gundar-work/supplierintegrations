package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotelbeds.supplierintegrations.hackertest.detector.LineaLog.Action;

class LineaParser {
	
	private static final Logger log = LoggerFactory.getLogger(LineaParser.class);
	
	private static final int NUMERO_CAMPOS = 4;
	private final String separadorCampos;
	
	public LineaParser(String separadorCampos) {
		this.separadorCampos = separadorCampos;
	}
	
	public LineaLog parse(String lineaLog) {
		
		log.debug("Parseando la linea [{}]", lineaLog);
		
		String[] camposLinea = lineaLog.split(separadorCampos, NUMERO_CAMPOS);
		
		if(camposLinea.length != NUMERO_CAMPOS) {
			throw new IllegalArgumentException("La línea no contiene los [" + NUMERO_CAMPOS + "] campos esperados. Linea = [" + lineaLog + "]");
		}
		
		String ip = camposLinea[0];
		long date = Long.parseLong(camposLinea[1]);
		Action action = Action.valueOf(camposLinea[2]);
		String username = camposLinea[3];
		
		return new LineaLog(ip, date, action, username);
	}

	@Override
	public String toString() {
		return "LineaParser [separadorCampos=" + separadorCampos + "]";
	}

}
