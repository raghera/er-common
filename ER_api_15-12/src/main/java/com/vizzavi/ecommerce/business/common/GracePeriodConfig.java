package com.vizzavi.ecommerce.business.common;

import com.vodafone.config.ConfigProvider;

/**
 * encapsulates the 4 possible values of com.vodafone.global.er.transctrl.bl.PurchaseTransaction.USE_SUSPENDED_STATUS
 * @author matt
 *
 */
public enum GracePeriodConfig {
	/**ER 7 functionality - no grace period, only suspended.  Subscriptions are not automatically moved to INACTIVE when a recur fails but stay in SUSPENDED.*/
	ON, 
	/** all grace period and suspended configuration, in config and priceplan,		
		is ignored, and subscription will remain in an active status on payment failure on renewal		 */
	ALWAYSACTIVE, 
	/**The default - grace and suspension periods configured.  If grace or suspension periods are configured in priceplan, these values will over-ride those in config		*/
	OFF, 
	/**only grace period and suspended configuration in the priceplan are taken into account.		
		Any configuration from config is ignored.<br/> If no configuration is defined in the priceplan 		
		the subscription will remain in an active status on payment failure on renewal		*/
	PRICEPLANDECIDE;

	/**
	 * what is the current value in config for this parameter?
	 * @return
	 */
	public static GracePeriodConfig getFromConfig()	{
		return GracePeriodConfig.valueOf(ConfigProvider.getProperty("com.vodafone.global.er.transctrl.bl.PurchaseTransaction.USE_SUSPENDED_STATUS","OFF").trim());
	}
	
}
