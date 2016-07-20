/**
 * 
 */
package com.vodafone.global.er.data;

/**
 * A Java Bean to hold logging related data to pass between
 * methods
 * 
 * @author Ravi Aghera
 *
 */
public class ERLogDataImpl implements ERLogData {

	private String msisdn;
	private String clientId;
	private String requestName;
	private String countryCode;
	
	public ERLogDataImpl(String msisdn, String clientId, String requestName,
			String countryCode) {
		super();
		this.msisdn = msisdn;
		this.clientId = clientId;
		this.requestName = requestName;
		this.countryCode = countryCode;
	}
	
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	@Override
	public String toString() {
		return "ERLogDataImpl [msisdn=" + msisdn + ", clientId=" + clientId
				+ ", requestName=" + requestName
				+ ", countryCode=" + countryCode + "]";
	}
	

}
