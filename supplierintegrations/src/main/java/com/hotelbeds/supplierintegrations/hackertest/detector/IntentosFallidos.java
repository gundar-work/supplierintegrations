package com.hotelbeds.supplierintegrations.hackertest.detector;

import java.util.Iterator;
import java.util.LinkedList;

class IntentosFallidos {
	
	private final LinkedList<Long> timestampIntentosFallidos = new LinkedList<>();
	
	public int getIntentosFallidosEnUmbral(long timestampIntentoFallido, long umbralIntentosFallidos) {
		
		long timestampMinimoAnterior = timestampIntentoFallido - umbralIntentosFallidos;
		
		boolean tiempoMaximoSuperado = false;
		
		for (Iterator<Long> iterator = timestampIntentosFallidos.iterator(); iterator.hasNext();) {
			
			Long intentoFallidoAnterior = iterator.next();
			
			if(!tiempoMaximoSuperado) {
				
				tiempoMaximoSuperado = tiempoMaximoSuperado(intentoFallidoAnterior, timestampMinimoAnterior);
			}
			
			if(tiempoMaximoSuperado) {
				iterator.remove();
			}
		}
		
		timestampIntentosFallidos.addFirst(timestampIntentoFallido);
		
		return timestampIntentosFallidos.size();
	}
	
	public long getUltimoIntentoFallido() {
		
		return timestampIntentosFallidos.getFirst();
	}
	
	private boolean tiempoMaximoSuperado(long timestampAnterior, long timestampMinimoAnterior) {
		
		return timestampAnterior < timestampMinimoAnterior;
	}

	@Override
	public String toString() {
		return "IntentosFallidos [timestampIntentosFallidos=" + timestampIntentosFallidos + "]";
	}

}
