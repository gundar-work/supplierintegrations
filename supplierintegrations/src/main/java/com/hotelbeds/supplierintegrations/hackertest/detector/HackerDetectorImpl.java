package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HackerDetectorImpl implements HackerDetector{

	private static final Logger log = LoggerFactory.getLogger(HackerDetectorImpl.class);
	private static final String SEPARADOR_CAMPOS = ",";

	private final LineaParser lineaParser;
	private final CacheIntentosFallidos cacheIntentosFallidos;

	public HackerDetectorImpl() {
		this(SEPARADOR_CAMPOS);
	}

	public HackerDetectorImpl(String separadorCamposLinea) {
		this.lineaParser = new LineaParser(separadorCamposLinea);
		this.cacheIntentosFallidos = new CacheIntentosFallidos();
	}

	@Override
	public String parseLine(String line) {

		log.info("Comprobando ataques en la linea de log [{}]", line);

		LineaLog lineaLog = lineaParser.parse(line);

		boolean esIpSospechosa = cacheIntentosFallidos.esIpSospechosa(lineaLog);

		log.debug("Es ip sospechosa [{}] la linea = {}", esIpSospechosa, lineaLog);

		final String ipSospechosa;
		if(esIpSospechosa) {
			ipSospechosa = lineaLog.getIp();
		}else {
			ipSospechosa = null;
		}

		return ipSospechosa;
	}

}
