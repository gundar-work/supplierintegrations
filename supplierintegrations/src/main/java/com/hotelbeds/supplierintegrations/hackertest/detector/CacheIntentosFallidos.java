package com.hotelbeds.supplierintegrations.hackertest.detector;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class CacheIntentosFallidos {

	private static final long UMBRAL_INTENTO_FALLIDOS_MS = TimeUnit.MINUTES.toMillis(5);
	private static final int MAXIMO_INTENTOS_FALLIDOS = 5;

	private final Map<String, IntentosFallidos> intentosFallidosPorIp = new HashMap<>();
	private final LimpiadorEntradasCaducadas limpiadorCache = new LimpiadorEntradasCaducadas(10, TimeUnit.MINUTES);

	public boolean esIpSospechosa(LineaLog linea) {
		
		if(limpiadorCache.debeLimpiarCache()) {
			
			limpiadorCache.limpiar(intentosFallidosPorIp, linea.getDate(), MAXIMO_INTENTOS_FALLIDOS);
		}

		IntentosFallidos intentosFallidos = intentosFallidosPorIp.computeIfAbsent(linea.getIp(), (s)-> new  IntentosFallidos());

		int intentosFallidosEnUmbral = intentosFallidos.getIntentosFallidosEnUmbral(linea.getDate(), UMBRAL_INTENTO_FALLIDOS_MS);
		
		return intentosFallidosEnUmbral >= MAXIMO_INTENTOS_FALLIDOS;
	}
	
}
