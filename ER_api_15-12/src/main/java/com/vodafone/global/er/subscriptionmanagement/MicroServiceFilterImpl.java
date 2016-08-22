/**
 * 
 */
package com.vodafone.global.er.subscriptionmanagement;

import com.vizzavi.ecommerce.business.selfcare.MicroServiceFilter;


/**
 * @author hud
 * STKHREQ36
 *
 */
public class MicroServiceFilterImpl 
	extends FilterAttributesImpl
	implements MicroServiceFilter
{
	long mSubscriptionId = -1;
	String mServicePricePointId = null;
	
	
	
	// get/set subscription id
	public long getSubscriptionId() {
		return mSubscriptionId;
	}
	
	public void setSubscriptionId(long subId) {
		mSubscriptionId = subId;
	}
	
	
	// get/set service price point
	public String getServicePricePointId()	{
		return mServicePricePointId;
	}
	public void setServicePricePointId(String ppId) {
		mServicePricePointId = ppId;
	}
	
}
