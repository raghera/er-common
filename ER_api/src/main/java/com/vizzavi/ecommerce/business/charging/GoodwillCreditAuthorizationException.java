package com.vizzavi.ecommerce.business.charging;

import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.ReasonCode;

public class GoodwillCreditAuthorizationException extends EcommerceException {

	private static final long serialVersionUID = 8692658859979725739L;
	private ReasonCode reasonCode;

	public GoodwillCreditAuthorizationException(Throwable t) {
		super(t);
	}
	
	public GoodwillCreditAuthorizationException(String msg) {
		super(msg);
	}
	
	public GoodwillCreditAuthorizationException(ReasonCode reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	public GoodwillCreditAuthorizationException(String msg, Exception e) {
		super(msg, e);
	}

	public ReasonCode getReasonCode() {
		return reasonCode;
	}
	
	public void setReasonCode(ReasonCode reasonCode) {
		this.reasonCode = reasonCode;
	}
}
