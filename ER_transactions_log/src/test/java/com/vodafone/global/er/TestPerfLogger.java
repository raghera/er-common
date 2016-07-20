package com.vodafone.global.er;

import org.junit.Test;

import com.vodafone.global.er.PerfLogger.Type;

public class TestPerfLogger {

	
	
	@Test
	public void testPerfLogger()	{
		PerfLogger.start(Type.INT, "hello", null);
		
		PerfLogger.start( Type.INT, "hello");

		PerfLogger.start(null, Type.INT, "hello", null);

		PerfLogger.start(null, Type.INT, "hello");
		
		PerfLogger.lap("");
		
		PerfLogger.lap(this, "message");
		
		PerfLogger.stop();
		
		PerfLogger.stop(this);
		
		PerfLogger.stop("");
		
		PerfLogger.stop(this,  "message");

	}
}
