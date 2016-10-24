/**
 * 
 */
package com.vodafone.global.er.data;

/**
 * 
 * A data transfer object for ULF data
 * from Application manager class to the Service
 * 
 * @author Ravi Aghera
 *
 */
public interface ERLogData {
	

	String getMsisdn();
	
	void setMsisdn(String msisdn);
	
	String getClientId();
	
	void setClientId(String clientId);
	
	String getRequestName();
	
	void setRequestName(String requestName);
	
	String getCountryCode();
	
	void setCountryCode(String countryCode);

	void setApiName(String apiName);

    String getApiName();
	
}
