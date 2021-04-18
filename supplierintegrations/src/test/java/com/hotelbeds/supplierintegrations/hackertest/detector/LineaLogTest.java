package com.hotelbeds.supplierintegrations.hackertest.detector;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hotelbeds.supplierintegrations.hackertest.detector.LineaLog.Action;

public class LineaLogTest {
	
	@Test
	@DisplayName("Login exitoso")
	public void logExitoso() {
		
		LineaLog lineaLog = new LineaLog(null, 0, Action.SIGNIN_SUCCESS, null);
		
		assertEquals(true, lineaLog.loginExitoso());
	}
	
	@Test
	@DisplayName("Login incorrecto")
	public void logNoExitoso() {
		
		LineaLog lineaLog = new LineaLog(null, 0, Action.SIGNIN_FAILURE, null);
		
		assertEquals(false, lineaLog.loginExitoso());
	}

}
