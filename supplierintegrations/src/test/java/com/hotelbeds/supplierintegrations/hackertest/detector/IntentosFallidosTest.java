package com.hotelbeds.supplierintegrations.hackertest.detector;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntentosFallidosTest {

	private static final long UMBRAL_INTENTO_FALLIDOS_SEG = TimeUnit.MINUTES.toSeconds(5);

	@Test
	@DisplayName("No superado máximo intentos fallidos")
	public void maxIntentoFallidosNoSuperado() {

		long timestamp = 1336129471;

		IntentosFallidos intentosFallidos = new IntentosFallidos(2, UMBRAL_INTENTO_FALLIDOS_SEG);
		
		boolean intentosFallidosSuperados = intentosFallidos.maxIntentosFallidosSuperados(timestamp);
		
		assertEquals(false, intentosFallidosSuperados);
	}
	
	@Test
	@DisplayName("Si superado máximo intentos fallidos")
	public void maxIntentoFallidosSuperado() {

		long timestamp = 1336129471;

		IntentosFallidos intentosFallidos = new IntentosFallidos(2, UMBRAL_INTENTO_FALLIDOS_SEG);
		
		boolean intentosFallidosSuperados = intentosFallidos.maxIntentosFallidosSuperados(timestamp);
		assertEquals(false, intentosFallidosSuperados);
		
		intentosFallidosSuperados = intentosFallidos.maxIntentosFallidosSuperados(timestamp);
		assertEquals(true, intentosFallidosSuperados);
	}
	
	@Test
	@DisplayName("Si superado máximo intentos fallidos pero no en rango")
	public void maxIntentoFallidosSuperadoPeroNoEnRango() {

		long timestamp = 1336129471;
		int umbralFallosSeg = 4;

		IntentosFallidos intentosFallidos = new IntentosFallidos(2, umbralFallosSeg);
		
		boolean intentosFallidosSuperados = intentosFallidos.maxIntentosFallidosSuperados(timestamp);
		assertEquals(false, intentosFallidosSuperados);
		
		long timestamp2 = timestamp + umbralFallosSeg + 1; 
		intentosFallidosSuperados = intentosFallidos.maxIntentosFallidosSuperados(timestamp2);
		assertEquals(false, intentosFallidosSuperados);
	}

}
