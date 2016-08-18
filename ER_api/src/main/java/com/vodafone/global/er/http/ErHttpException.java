package com.vodafone.global.er.http;

public class ErHttpException extends Exception {

    private static final long serialVersionUID = 1L;

	
	public static final String E0001 = "An unrecoverable exception has occured";
	
	public static final String E0002 = "An unrecoverable HTTP protocol transport exception has occured";
	
	public static final String E0003 = "HTTP ERROR received";
	
	public static final String E0004 = "HTTP Layer initialization error";
	
	public static final String E0005 = "HTTP Call initialization error";
	
	public static final String E0006 = "Error creating global SSL context";
	
	public static final String E0007 = "Error deserializing object to send";

	public static final String E0008 = "Error serializing object received";
	
	public static final String E0009 = "Error verifying host name. See HTTP.SECURITY.HOSTNAME.VERIFICATION.ENABLED in configuration";
	
	public static final String E0010 = "Connection to host refused. Connectivity must be checked";

	public static final String E0011 = "Errors loading truststore";
	public static final String E0012 = "SocketException: Connection reset";
	
    public static final String E1000 = "Timeout error while connecting to  HTTP server or waiting for an available connection" + 
                                           "from a HttpConnectionManager";

    public static final String E1001 = "Timeout error has occurred on socket read";
    
    //MQC 7091
    public static final String E1002 = "Timeout error waiting for a http connection from the http connection pool manager. " + 
            "Check http connection pool size property.";

    public static final String E2000 = "Unable to find valid trusted certificate to requested target";
    public static final String E2001 = "Counter party certificate expired";
    public static final String E2002 = "Counter party certificate authentication has failed. " + E2001 + "?";
    



    public ErHttpException() {
        // TODO Auto-generated constructor stub
    }

    public ErHttpException(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    public ErHttpException(Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    public ErHttpException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

}

