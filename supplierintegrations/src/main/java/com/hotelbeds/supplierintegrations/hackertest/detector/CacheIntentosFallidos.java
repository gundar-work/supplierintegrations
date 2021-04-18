package com.hotelbeds.supplierintegrations.hackertest.detector;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class CacheIntentosFallidos {

	private static final long UMBRAL_INTENTO_FALLIDOS = TimeUnit.MINUTES.toMillis(5);
	private static final int MAXIMO_INTENTOS_FALLIDOS = 5;

	private final Map<String, IntentosFallidos> intentosFallidosPorIp = new HashMap<>();
	private final LimpiadorEntradasCaducadas limpiadorCache = new LimpiadorEntradasCaducadas();

	public boolean esIpSospechosa(LineaLog linea) {

		IntentosFallidos intentosFallidos = intentosFallidosPorIp.computeIfAbsent(linea.getIp(), (s)-> new  IntentosFallidos());

		int intentosFallidosEnUmbral = intentosFallidos.getIntentosFallidosEnUmbral(linea.getDate(), UMBRAL_INTENTO_FALLIDOS);
		
		return intentosFallidosEnUmbral >= MAXIMO_INTENTOS_FALLIDOS;
	}
	
//	private void limpiarIntentosFallidosCaducados(long timestampIntentoFallido) {
//		
//		long timestampMinimoAnterior = timestampIntentoFallido - UMBRAL_INTENTO_FALLIDOS;
//		
//		for (Iterator<Entry<String, IntentosFallidos>> iterator = intentosFallidosPorIp.entrySet().iterator(); iterator.hasNext();) {
//			
//			Entry<String, IntentosFallidos> entry = iterator.next();
//			
//			if(ultimoIntentoFallidoCaducado(entry, timestampMinimoAnterior)) {
//				iterator.remove();
//			}
//		}
//	}
//	
//	private boolean ultimoIntentoFallidoCaducado(Entry<String, IntentosFallidos> entry, long timestampMinimoAnterior) {
//		
//		long ultimoTimestamp = entry.getValue().getUltimoIntentoFallido();
//		
//		return ultimoTimestamp < timestampMinimoAnterior;
//	}

}
