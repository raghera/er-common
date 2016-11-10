package com.vizzavi.ecommerce.business.charging;

import java.io.Serializable;

import com.vizzavi.ecommerce.business.common.ReasonCode;

public class ModifyAuthorisation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private boolean success;
	
	private ReasonCode reasonCode;
	private boolean accountNotfound;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ReasonCode getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(ReasonCode reasonCode) {
		this.reasonCode = reasonCode;
	}

	public void setAccountNotfound(boolean accountNotfound) {
		this.accountNotfound = accountNotfound;
	}

	public boolean isAccountNotfound() {
		return accountNotfound;
	}

	@Override
	public String toString() {
		return "ModifyAuthorisation [success=" + success + ", reasonCode="
				+ reasonCode + ", accountNotfound=" + accountNotfound + "]";
	}


}
