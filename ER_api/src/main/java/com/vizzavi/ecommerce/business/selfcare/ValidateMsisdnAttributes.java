package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;


/**
 * CR2082 Created for Validate Msisdn Request, this can be extended in the future to add any 
 * additional fields to the validate request call to ERIF 
 * 
*/

public class ValidateMsisdnAttributes implements Serializable {
	
	private static final long serialVersionUID = -4190920387581569805L;
	

	public ValidateMsisdnAttributes() {
		//default
	}
	
	//MQC 7434
	public ValidateMsisdnAttributes(final String serviceId, final String partnerId, final String vendorId) {
		
		this.serviceId = serviceId;
		this.partnerId = partnerId;
		this.vendorId = vendorId;
		
	}
	
	private String serviceId;
	
	private String partnerId;
	
	 //CR 2134 - add vendor id to validate if message
	private String vendorId;	
	
	public String getServiceId() {
		return this.serviceId;
	}
	
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getPartnerId() {
		return this.partnerId;
	}
	
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	
	 //CR 2134 - add vendor id to validate if message
	public String getVendorId() {
		return this.vendorId;
	}
	
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
}

