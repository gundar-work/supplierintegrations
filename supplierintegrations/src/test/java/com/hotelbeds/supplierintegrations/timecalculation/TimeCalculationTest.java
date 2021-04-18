package com.hotelbeds.supplierintegrations.timecalculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TimeCalculationTest {
	
	private static TimeCalculation timeCalculation;
	
	@BeforeAll
    public static void setUp() throws Exception {
        timeCalculation = new TimeCalculation();
    }
	
	@Test
	@DisplayName("Misma fecha")
	public void mismaFecha() {
		
		String fecha = "Thu, 21 Dec 2000 16:01:07 +0200";
		
		long minutosDiferencia = timeCalculation.applyAsLong(fecha, fecha);
		
		assertEquals(0, minutosDiferencia);
	}
	
	@Test
	@DisplayName("Misma fecha distinto timezone")
	public void mismaFechaDistintoTimeZone() {
		
		String fecha1 = "Thu, 21 Dec 2000 16:01:07 +0200";
		String fecha2 = "Thu, 21 Dec 2000 15:01:07 +0100";
		
		long minutosDiferencia = timeCalculation.applyAsLong(fecha1, fecha2);
		
		assertEquals(0, minutosDiferencia);
	}
	
	@Test
	@DisplayName("Un día diferencia")
	public void unDiaDiferencia() {
		
		String fecha1 = "Thu, 21 Dec 2000 16:01:07 +0200";
		String fecha2 = "Fri, 22 Dec 2000 16:01:07 +0200";
		
		long minutosDiferencia = timeCalculation.applyAsLong(fecha1, fecha2);
		
		assertEquals(24 * 60, minutosDiferencia);
	}
	
	@Test
	@DisplayName("Menos de un minuto")
	public void menosDeUnMinuto() {
		
		String fecha1 = "Thu, 21 Dec 2000 16:01:07 +0200";
		String fecha2 = "Thu, 21 Dec 2000 16:00:30 +0200";
		
		long minutosDiferencia = timeCalculation.applyAsLong(fecha1, fecha2);
		
		assertEquals(0, minutosDiferencia);
	}

}
