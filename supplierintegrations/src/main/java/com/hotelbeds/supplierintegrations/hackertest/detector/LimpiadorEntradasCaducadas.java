package com.hotelbeds.supplierintegrations.hackertest.detector;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class LimpiadorEntradasCaducadas {

	public void limpiar(Map<String, IntentosFallidos> cache, long ultimoTimestampFallido, long umbralIntentosFallidos) {

		long timestampMinimoAnterior = ultimoTimestampFallido - umbralIntentosFallidos;

		for (Iterator<Entry<String, IntentosFallidos>> iterator = cache.entrySet().iterator(); iterator.hasNext();) {

			Entry<String, IntentosFallidos> entry = iterator.next();

			if(ultimoIntentoFallidoCaducado(entry, timestampMinimoAnterior)) {
				iterator.remove();
			}
		}
	}

	private boolean ultimoIntentoFallidoCaducado(Entry<String, IntentosFallidos> entry, long timestampMinimoAnterior) {

		long ultimoTimestamp = entry.getValue().getUltimoIntentoFallido();

		return ultimoTimestamp < timestampMinimoAnterior;
	}

}
