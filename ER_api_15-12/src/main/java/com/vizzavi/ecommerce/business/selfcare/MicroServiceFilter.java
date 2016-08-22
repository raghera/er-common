/**
 * 
 */
package com.vizzavi.ecommerce.business.selfcare;

/**
 * @author hud
 * 
 *
 */
public interface MicroServiceFilter extends FilterAttributes 
{

	// get/set subscription id
	public long getSubscriptionId();
	public void setSubscriptionId(long subId);
	
	// get/set service price point
	public String getServicePricePointId();
	public void setServicePricePointId(String ppId);
	
}
