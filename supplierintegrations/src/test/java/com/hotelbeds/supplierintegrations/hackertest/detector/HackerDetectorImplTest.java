package com.hotelbeds.supplierintegrations.hackertest.detector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HackerDetectorImplTest {
	
	private HackerDetector detector;
	
	@BeforeEach                                         
    public void setUp() throws Exception {
		detector = new HackerDetectorImpl(",");
    }
	
	@Test
	@DisplayName("Login correcto")
	public void loginCorrecto() {
		
		String linea1 = "80.238.9.179,1336129471,SIGNIN_SUCCESS,Will.Smith";
		
		String ip = detector.parseLine(linea1);
		assertNull(ip);
	}
	
	@Test
	@DisplayName("Un Login incorrecto")
	public void unLoginInCorrecto() {
		
		String linea1 = "80.238.9.179,1336129471,SIGNIN_FAILURE,Will.Smith";
		
		String ip = detector.parseLine(linea1);
		assertNull(ip);
	}
	
	@Test
	@DisplayName("Superado Umbral Incorrectos")
	public void superadoUmbralIncorrectos() {
		
		String linea1 = "80.238.9.179,1336129471,SIGNIN_FAILURE,Will.Smith";
		String ip = detector.parseLine(linea1);
		assertNull(ip);
		
		String linea2 = "80.238.9.179,1336129472,SIGNIN_FAILURE,Will.Smith";
		ip = detector.parseLine(linea2);
		assertNull(ip);
		
		String linea3 = "80.238.9.179,1336129473,SIGNIN_FAILURE,Will.Smith";
		ip = detector.parseLine(linea3);
		assertNull(ip);
		
		String linea4 = "80.238.9.179,1336129474,SIGNIN_FAILURE,Will.Smith";
		ip = detector.parseLine(linea4);
		assertNull(ip);
		
		String linea5 = "80.238.9.179,1336129475,SIGNIN_FAILURE,Will.Smith";
		ip = detector.parseLine(linea5);
		assertEquals("80.238.9.179", ip);
		
		String linea6 = "80.238.9.179,1336129476,SIGNIN_FAILURE,Will.Smith";
		ip = detector.parseLine(linea6);
		assertEquals("80.238.9.179", ip);
	}
	
	@Test
	@DisplayName("Superado Umbral Incorrectos con un caducado")
	public void superadoUmbralIncorrectosUnCaducado() {
		
		String linea1 = "80.238.9.179,1336129471,SIGNIN_FAILURE,Will.Smith";
		String ip = detector.parseLine(linea1);
		assertNull(ip);
		
		String linea3 = "80.238.9.179,1336129673,SIGNIN_FAILURE,Will.Smith";
		ip = detector.parseLine(linea3);
		assertNull(ip);
		
		String linea4 = "80.238.9.179,1336129674,SIGNIN_FAILURE,Will.Smith";
		ip = detector.parseLine(linea4);
		assertNull(ip);
		
		String linea5 = "80.238.9.179,1336129675,SIGNIN_FAILURE,Will.Smith";
		ip = detector.parseLine(linea5);
		assertNull(ip);
		
		String linea6 = "80.238.9.179,1336129876,SIGNIN_FAILURE,Will.Smith";
		ip = detector.parseLine(linea6);
		assertEquals(null, ip);
		
		String linea7 = "80.238.9.179,1336129876,SIGNIN_FAILURE,Will.Smith";
		ip = detector.parseLine(linea7);
		assertEquals("80.238.9.179", ip);
	}

}
