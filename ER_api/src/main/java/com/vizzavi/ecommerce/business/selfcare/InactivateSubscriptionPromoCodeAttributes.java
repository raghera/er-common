package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;



/**
 * JIRA ET196 Inactivate subscription promo-code api request attributes
 * @author trushantpatel
 *
 */

public class InactivateSubscriptionPromoCodeAttributes implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String subscriptionId; 
	
	private String packageId;
	
	private String reason;

	private String	promoCode;
	
	public InactivateSubscriptionPromoCodeAttributes() {
		
	}
	
	public InactivateSubscriptionPromoCodeAttributes(final String subId, final String pkgId, final String reason) {
		this.subscriptionId = subId;
		this.packageId = pkgId;
		this.reason = reason;
	}
	
	public InactivateSubscriptionPromoCodeAttributes(final SubscriptionPromoCode promoCode, final String reason)	{
		this.subscriptionId =  promoCode.getSubscriptionIdString();
		this.packageId = promoCode.getPackageId();
		this.promoCode = promoCode.getPromoCode();
		this.reason = reason;

	}
	
	
	/**
	 * @return the subscriptionId
	 */
	public String getSubscriptionId() {
		return this.subscriptionId;
	}
	
	/**
	 * @return the packageId
	 */
	public String getPackageId() {
		return this.packageId;
	}
	
	/**
	 * @return the reason
	 */
	public String getReason() {
		return this.reason;
	}

	public String getPromoCode() {
		return promoCode;
	}

	
}

