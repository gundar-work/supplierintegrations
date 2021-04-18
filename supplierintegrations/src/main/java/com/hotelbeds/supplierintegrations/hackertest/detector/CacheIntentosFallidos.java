package com.hotelbeds.supplierintegrations.hackertest.detector;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class CacheIntentosFallidos {

	private static final int MAXIMO_INTENTOS_FALLIDOS = 5;

	private final Map<String, IntentosFallidos> intentosFallidosPorIp = new HashMap<>();
	private final LimpiadorEntradasCaducadas limpiadorCache = new LimpiadorEntradasCaducadas(10, TimeUnit.MINUTES);
	
	private final long umbralIntentosFallidosSeg;
	private final int maximosIntentosFallidos;
	
	public CacheIntentosFallidos() {
		this(5, TimeUnit.MINUTES, MAXIMO_INTENTOS_FALLIDOS);
	}
	
	public CacheIntentosFallidos(long periodoIntentosFallidos, TimeUnit unidadUmbral, int maximosIntentosFallidos) {
		this.umbralIntentosFallidosSeg = unidadUmbral.toSeconds(periodoIntentosFallidos);
		this.maximosIntentosFallidos = maximosIntentosFallidos;
	}

	public boolean esIpSospechosa(LineaLog linea) {
		
		if(limpiadorCache.debeLimpiarCache()) {
			
			limpiadorCache.limpiar(intentosFallidosPorIp, linea.getDate(), maximosIntentosFallidos);
		}

		IntentosFallidos intentosFallidos = intentosFallidosPorIp.computeIfAbsent(
				linea.getIp(), 
				(s)-> new  IntentosFallidos(maximosIntentosFallidos, umbralIntentosFallidosSeg));

		return intentosFallidos.maxIntentosFallidosSuperados(linea.getDate());
	}
	
}
