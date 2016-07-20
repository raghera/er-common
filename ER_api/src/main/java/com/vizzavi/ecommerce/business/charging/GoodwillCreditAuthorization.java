package com.vizzavi.ecommerce.business.charging;

import com.vizzavi.ecommerce.business.common.ReasonCode;

public class GoodwillCreditAuthorization extends BaseAuthorization {

	private static final long serialVersionUID = 2199257978573123598L;
//	private String msisdn;
	
	public GoodwillCreditAuthorization() {
		super();
	}
	
	/**
	 * 
	 * @param isSuccess
	 * @param transactionId
	 * @param msisdn not used
	 * @param subscriptionId
	 * @param reasonCode
	 * @param subReasonCode
	 * @param errorId
	 * @param errorDescription
	 * @param rate not used
	 * @param currency not used
	 */
	public GoodwillCreditAuthorization(boolean isSuccess, String transactionId, String msisdn,
			String subscriptionId, ReasonCode reasonCode, ReasonCode subReasonCode, String errorId, String errorDescription, String rate, int currency) {
		super.setIsSuccess(isSuccess);
		super.setTransactionId(transactionId);
//		this.msisdn = msisdn;
		super.setPackageSubscriptionId(subscriptionId);
		super.setReasonCode(reasonCode);
		super.setSubReasonCode(subReasonCode);
		super.setErrorId(errorId);
		super.setErrorDescription(errorDescription);
	}

}
