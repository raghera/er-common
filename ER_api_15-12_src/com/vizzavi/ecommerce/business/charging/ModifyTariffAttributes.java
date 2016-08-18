package com.vizzavi.ecommerce.business.charging;

import java.io.Serializable;

import com.vizzavi.ecommerce.business.common.Constants;

/**
 * CR-0978 Location Services
 * The attributes that the user can set when calling modify tariff API.
*/

public class ModifyTariffAttributes implements Serializable {

	private static final long serialVersionUID = -7775589519784427971L;
	
	public static final String NEW_TARIFF = "NEW";
    public static final String CHANGE_TARIFF = "CHANGE";
    public static final String TERMINATE_TARIFF = "TERMINATE";
    
    String action;
    
    String sourceTariff = Constants.STRING_MATCH_ALL;
    
    String destinationTariff = Constants.STRING_MATCH_ALL;
    
    String clientId;
    
    String reason;
    
    
    public String getAction() {
    	return this.action;
    }
    
    public void setAction(String action) {
    	this.action = action;
    }
    
    public String getSourceTariff() {
		return this.sourceTariff;
	}
	
	public void setSourceTariff(String srcTariff) {
		this.sourceTariff = srcTariff;
	}
	
	public String getDestinationTariff() {
		return this.destinationTariff;
	}
		
	public void setDestinationTariff(String destTariff) {
		this.destinationTariff = destTariff;
	}
	
	public String getClientId() {
		return this.clientId;
	}
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getReason() {
		return this.reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
}
