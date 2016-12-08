package com.vodafone.global.er.decoupling;

public class ErrorConstants {
	
	/* Error Id's */
	public static final int ERROR_ID_UNEXPECTED 			= 100001;
	public static final int ERROR_ID_UNDEFINED_PAYLOAD_ID 	= 100002;
	/**aka validation*/
	public static final int ERROR_ID_UNMARSHALL_EXCEPTION 	= 100003;
	public static final int ERROR_ID_INVALID_HTTP_REQUEST	= 100004;
	public static final int ERROR_ID_INVALID_COUNTRY		= 100005;
	public static final int ERROR_ID_ILLEGAL_ACCESS		= 100006;
    
	/* Error Types */
	public static final String ERROR_TYPE_VALIDATION 	= "VALIDATION";
	public static final String ERROR_TYPE_TRANSPORT 	= "TRANSPORT";
	public static final String ERROR_TYPE_ECOMMERCE 	= "ECOMMERCE";
	public static final String ERROR_TYPE_UNEXPECTED 	= "UNEXPECTED";
	public static final String ERROR_TYPE_INVALID_COUNTRY 	= "INVALID_COUNTRY";
	public static final String ERROR_TYPE_ILLEGAL_ACCESS 	= "ILLEGAL_ACCESS";

}
