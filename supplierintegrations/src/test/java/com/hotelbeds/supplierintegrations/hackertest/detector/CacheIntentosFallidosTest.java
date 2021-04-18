package com.hotelbeds.supplierintegrations.hackertest.detector;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hotelbeds.supplierintegrations.hackertest.detector.LineaLog.Action;

public class CacheIntentosFallidosTest {

	public static void main(String[] args) {

		CacheIntentosFallidos cacheIntentosFallidos = new CacheIntentosFallidos(5, TimeUnit.MINUTES, 2);

		LineaLog lineaLog = new LineaLog(
				"80.238.9.179",
				1336129471,
				Action.SIGNIN_FAILURE, 
				"Will.Smith");

		boolean esIpSospechosa = cacheIntentosFallidos.esIpSospechosa(lineaLog);
		System.out.println("Ip sospechosa = " + esIpSospechosa);

		esIpSospechosa = cacheIntentosFallidos.esIpSospechosa(lineaLog);
		System.out.println("Ip sospechosa = " + esIpSospechosa);

		esIpSospechosa = cacheIntentosFallidos.esIpSospechosa(lineaLog);
		System.out.println("Ip sospechosa = " + esIpSospechosa);
	}

	@Test
	@DisplayName("No superado máximo intentos fallidos")
	public void maxIntentoFallidosNoSuperado() {

		CacheIntentosFallidos cacheIntentosFallidos = new CacheIntentosFallidos(5, TimeUnit.MINUTES, 2);

		LineaLog lineaLog = new LineaLog(
				"80.238.9.179",
				1336129471,
				Action.SIGNIN_FAILURE, 
				"Will.Smith");

		boolean esIpSospechosa = cacheIntentosFallidos.esIpSospechosa(lineaLog);
		assertEquals(false, esIpSospechosa);
	}

	@Test
	@DisplayName("Si superado máximo intentos fallidos")
	public void maxIntentoFallidosSuperado() {

		CacheIntentosFallidos cacheIntentosFallidos = new CacheIntentosFallidos(5, TimeUnit.MINUTES, 2);

		LineaLog lineaLog = new LineaLog(
				"80.238.9.179",
				1336129471,
				Action.SIGNIN_FAILURE, 
				"Will.Smith");

		boolean esIpSospechosa = cacheIntentosFallidos.esIpSospechosa(lineaLog);
		assertEquals(false, esIpSospechosa);

		esIpSospechosa = cacheIntentosFallidos.esIpSospechosa(lineaLog);
		assertEquals(true, esIpSospechosa);
	}

	@Test
	@DisplayName("Si superado máximo intentos fallidos pero no en rango")
	public void maxIntentoFallidosSuperadoPeroNoEnRango() {
		
		int umbralFallosSeg = 4;

		CacheIntentosFallidos cacheIntentosFallidos = new CacheIntentosFallidos(umbralFallosSeg, TimeUnit.SECONDS, 2);

		LineaLog lineaLog = new LineaLog(
				"80.238.9.179",
				1336129471,
				Action.SIGNIN_FAILURE, 
				"Will.Smith");
		
		boolean esIpSospechosa = cacheIntentosFallidos.esIpSospechosa(lineaLog);
		assertEquals(false, esIpSospechosa);
		
		LineaLog lineaLog2 = new LineaLog(
				"80.238.9.179",
				lineaLog.getDate() + umbralFallosSeg + 1,
				Action.SIGNIN_FAILURE, 
				"Will.Smith");
		
		esIpSospechosa = cacheIntentosFallidos.esIpSospechosa(lineaLog2);
		assertEquals(false, esIpSospechosa);
	}

}
