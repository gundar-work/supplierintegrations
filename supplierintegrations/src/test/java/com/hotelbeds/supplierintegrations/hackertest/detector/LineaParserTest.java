package com.hotelbeds.supplierintegrations.hackertest.detector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hotelbeds.supplierintegrations.hackertest.detector.LineaLog.Action;

public class LineaParserTest {
	
	private static final String SEPARADOR_CAMPOS = ",";
	
	private LineaParser lineaParser;
	
	@BeforeEach                                         
    public void setUp() throws Exception {
        lineaParser = new LineaParser(SEPARADOR_CAMPOS);
    }
	
	@Test
	@DisplayName("Parseo linea login exitoso")
	public void parseoLineaLoginExitoso() {
		
		String ip = "80.238.9.179";
		long date = 1336129471;
		Action action = Action.SIGNIN_SUCCESS;
		String userName = "Will.Smith";
		
		String linea = ip + SEPARADOR_CAMPOS + date + SEPARADOR_CAMPOS + action + SEPARADOR_CAMPOS + userName;
		
		LineaLog lineaLog = lineaParser.parse(linea);
		
		assertEquals(ip, lineaLog.getIp());
		assertEquals(date, lineaLog.getDate());
		assertEquals(action, lineaLog.getAction());
		assertEquals(userName, lineaLog.getUsername());
	}
	
	@Test
	@DisplayName("Parseo linea login NO exitoso")
	public void parseoLineaLoginNoExitoso() {
		
		String ip = "80.238.9.179";
		long date = 1336129471;
		Action action = Action.SIGNIN_FAILURE;
		String userName = "Will.Smith";
		
		String linea = ip + SEPARADOR_CAMPOS + date + SEPARADOR_CAMPOS + action + SEPARADOR_CAMPOS + userName;
		
		LineaLog lineaLog = lineaParser.parse(linea);
		
		assertEquals(ip, lineaLog.getIp());
		assertEquals(date, lineaLog.getDate());
		assertEquals(action, lineaLog.getAction());
		assertEquals(userName, lineaLog.getUsername());
	}
	
	@Test
	@DisplayName("Parseo linea comas en nombre")
	public void parseoLineaComasEnNombre() {
		
		String ip = "80.238.9.179";
		long date = 1336129471;
		Action action = Action.SIGNIN_FAILURE;
		String userName = "Will,Smith";
		
		String linea = ip + SEPARADOR_CAMPOS + date + SEPARADOR_CAMPOS + action + SEPARADOR_CAMPOS + userName;
		
		LineaLog lineaLog = lineaParser.parse(linea);
		
		assertEquals(ip, lineaLog.getIp());
		assertEquals(date, lineaLog.getDate());
		assertEquals(action, lineaLog.getAction());
		assertEquals(userName, lineaLog.getUsername());
	}
	
	@Test
	@DisplayName("Parseo linea menos campos")
	public void parseoLineaMenosCampos() {
		
		String linea = "80.238.9.179,133612947,SIGNIN_SUCCESS";
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> lineaParser.parse(linea));
	}

}
