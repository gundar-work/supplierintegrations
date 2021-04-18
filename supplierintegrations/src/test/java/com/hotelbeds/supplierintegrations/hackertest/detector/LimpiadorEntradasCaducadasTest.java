package com.hotelbeds.supplierintegrations.hackertest.detector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LimpiadorEntradasCaducadasTest {
	
	private LimpiadorEntradasCaducadas limpiador;
	
	@BeforeEach                                         
    public void setUp() throws Exception {
        limpiador = new LimpiadorEntradasCaducadas(3,TimeUnit.SECONDS);
    }

	@Test
	@DisplayName("Debe limiar caché")
	public void testDebeLimpiar() throws InterruptedException {
		
		int espera = 4;

		TimeUnit.SECONDS.sleep(espera);

		boolean debeLimpiarCache = limpiador.debeLimpiarCache();
		assertTrue(debeLimpiarCache);
	}
	
	@Test
	@DisplayName("NO Debe limiar caché")
	public void testNoDebeLimpiar() throws InterruptedException {
		
		int espera = 2;

		TimeUnit.SECONDS.sleep(espera);

		boolean debeLimpiarCache = limpiador.debeLimpiarCache();
		assertFalse(debeLimpiarCache);
	}

	@Test
	@DisplayName("Limpiar entrada caducada")
	public void testEntradaCaducada() throws InterruptedException {
		
		Map<String, IntentosFallidos> cache = new HashMap<>();

		IntentosFallidos intentosFallidos = new IntentosFallidos(2, 10);
		intentosFallidos.maxIntentosFallidosSuperados(Instant.now().getEpochSecond());
		cache.put("ip", intentosFallidos);

		TimeUnit.SECONDS.sleep(10);

		boolean debeLimpiarCache = limpiador.debeLimpiarCache();
		assertEquals(true, debeLimpiarCache);

		limpiador.limpiar(cache, Instant.now().getEpochSecond(), 5);
		assertTrue(cache.isEmpty());
	}
	
	@Test
	@DisplayName("NO Limpiar entrada NO caducada")
	public void testEntradaNoCaducada() throws InterruptedException {
		
		TimeUnit.SECONDS.sleep(4);

		boolean debeLimpiarCache = limpiador.debeLimpiarCache();
		assertTrue(debeLimpiarCache);

		IntentosFallidos intentosFallidos = new IntentosFallidos(2, 10);
		intentosFallidos.maxIntentosFallidosSuperados(Instant.now().getEpochSecond());
		
		Map<String, IntentosFallidos> cache = new HashMap<>();
		cache.put("ip", intentosFallidos);

		limpiador.limpiar(cache, Instant.now().getEpochSecond(), 5);
		assertFalse(cache.isEmpty());
		
		debeLimpiarCache = limpiador.debeLimpiarCache();
		assertFalse(debeLimpiarCache);
	}

}
