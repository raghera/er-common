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
	

	public String getMsisdn();
	
	public void setMsisdn(String msisdn);
	
	public String getClientId();
	
	public void setClientId(String clientId);
	
	public String getRequestName();
	
	public void setRequestName(String requestName);
	
	public String getCountryCode();
	
	public void setCountryCode(String countryCode);
	
	
}
