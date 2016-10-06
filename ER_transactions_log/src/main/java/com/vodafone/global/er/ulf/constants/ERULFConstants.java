/**
 * 
 */
package com.vodafone.global.er.ulf.constants;

/**
 * 
 * Constants for ULF Logging
 * 
 * @author Ravi Aghera
 *
 */
public class ERULFConstants {
	
	//Log4j name of ULF Logger
	public final static String ER_ULF_LOGGER_NAME = "com.vodafone.global.er.UltimateLog"; 


	//enum of LogPoint is in ULFEntry in App common module
//	public enum LogPoint {
//	
//		REQUESTIN("RequestIn"),
//		REQUESTOUT("RequestOut"),
//		RESPONSEIN("ResponceIn"),
//		RESPONSEOUT("ResponceOut");
//		
//	    private final String key;
//
//	    private LogPoint(String key) {
//	        this.key = key;
//	    }
//
//	    @Override
//	    public String toString() {
//	        return this.key;
//	    }
//	}

	public enum ComponentName {
		
		ER_CORE("er-core"),
		ER_IF("er-if"),
		ER_PRICINGTOOL("er-pricingtool"),
		ER_CUSTOMERCARE("er-customercare"),
		ER_BPS("er-bps"),
		EPA("ecom-proxy-app");

	    private final String key;

	    private ComponentName(String key) {
	        this.key = key;
	    }

	    @Override
	    public String toString() {
	        return this.key;
	    }
	}
	

}
