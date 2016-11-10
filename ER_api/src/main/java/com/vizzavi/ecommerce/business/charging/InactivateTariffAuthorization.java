package com.vizzavi.ecommerce.business.charging;

import java.io.Serializable;

/**
 * CR-0978 Location Services
 * Inactivation Tariff Authorization returned when calling modify tariff API.
*/

public class InactivateTariffAuthorization implements Serializable {

	private static final long serialVersionUID = 4749234031982470013L;
	
	private boolean success;
	
	private String subscriptionId;
	
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getSubscriptionId() {
		return this.subscriptionId;
	}
	
	public void setSubscriptionId(String subId) {
		this.subscriptionId = subId;
	}
	
}
