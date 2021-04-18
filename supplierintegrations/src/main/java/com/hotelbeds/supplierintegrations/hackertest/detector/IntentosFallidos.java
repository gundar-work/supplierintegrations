package com.hotelbeds.supplierintegrations.hackertest.detector;

import java.util.Iterator;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class IntentosFallidos {
	
	private static final Logger log = LoggerFactory.getLogger(IntentosFallidos.class);
	
	private final LinkedList<Long> timestampIntentosFallidos = new LinkedList<>();
	private final int maximoIntentosFallidos;
	private final long umbralIntentosFallidos;
	
	public IntentosFallidos(int maximoIntentosFallidos, long umbralIntentosFallidos) {
		this.maximoIntentosFallidos = maximoIntentosFallidos;
		this.umbralIntentosFallidos = umbralIntentosFallidos;
	}
	
	public boolean maxIntentosFallidosSuperados(long timestampIntentoFallido) {
		
		long timestampMinimoAnterior = timestampIntentoFallido - umbralIntentosFallidos;
		
		int iteracion = 1;
		boolean borrarTimestampRestantes = false;
		
		for (Iterator<Long> iterator = timestampIntentosFallidos.iterator(); iterator.hasNext();) {
			
			Long intentoFallidoAnterior = iterator.next();
			
			if(!borrarTimestampRestantes) {
				borrarTimestampRestantes = superadoMaximoIntentosFallidos(iteracion);
			}
			
			if(!borrarTimestampRestantes) {
				borrarTimestampRestantes = intentoFallidoCaducado(intentoFallidoAnterior, timestampMinimoAnterior);
			}
			
			if(borrarTimestampRestantes) {
				log.debug("Borrado el timestamp {}", intentoFallidoAnterior);
				iterator.remove();
			}
			
			iteracion++;
		}
		
		timestampIntentosFallidos.addFirst(timestampIntentoFallido);
		
		log.debug("Total intentos fallidos [{}]. Timestamps = {}", timestampIntentosFallidos.size(), timestampIntentosFallidos );
		
		return timestampIntentosFallidos.size() >= maximoIntentosFallidos;
	}
	
	public long getUltimoIntentoFallido() {
		
		return timestampIntentosFallidos.getFirst();
	}
	
	private boolean intentoFallidoCaducado(long timestampAnterior, long timestampMinimoAnterior) {
		
		return timestampAnterior < timestampMinimoAnterior;
	}
	
	private boolean superadoMaximoIntentosFallidos(int intentoActual) {
		
		return intentoActual > maximoIntentosFallidos;
	}

	@Override
	public String toString() {
		return "IntentosFallidos [timestampIntentosFallidos=" + timestampIntentosFallidos + "]";
	}

}
