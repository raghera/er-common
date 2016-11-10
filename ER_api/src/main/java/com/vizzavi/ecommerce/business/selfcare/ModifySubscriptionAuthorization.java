package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;

import com.vizzavi.ecommerce.business.common.ReasonCode;

/**
 * CR 1691 Created for Modifysubscription Response
 * 
*/

public class ModifySubscriptionAuthorization implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean success;
	
	private ReasonCode reason;
	
	
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public ReasonCode getReasonCode() {
		return this.reason;
	}
	
	public void setReasonCode(ReasonCode reason) {
		this.reason = reason;
	}
	
	
}

