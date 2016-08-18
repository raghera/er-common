package com.vizzavi.ecommerce.business.charging;

import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.selfcare.Subscription;

public class GoodwillCreditAuthorization extends BaseAuthorization {

	private static final long serialVersionUID = 2199257978573123598L;
	private String msisdn;
	
	public GoodwillCreditAuthorization() {
		super();
	}
	
	public GoodwillCreditAuthorization(boolean isSuccess, String transactionId, String msisdn,
			String subscriptionId, ReasonCode reasonCode, ReasonCode subReasonCode, String errorId, String errorDescription, String rate, int currency) {
		super.setIsSuccess(isSuccess);
		super.setTransactionId(transactionId);
		this.msisdn = msisdn;
		super.setPackageSubscriptionId(subscriptionId);
		super.setReasonCode(reasonCode);
		super.setSubReasonCode(subReasonCode);
		super.setErrorId(errorId);
		super.setErrorDescription(errorDescription);
	}

}
