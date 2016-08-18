package com.vizzavi.ecommerce.business.common;

import com.vodafone.config.ConfigProvider;

/**
 * encapsulates the 4 possible values of com.vodafone.global.er.transctrl.bl.PurchaseTransaction.USE_SUSPENDED_STATUS
 * @author matt
 *
 */
public enum GracePeriodConfig {
	ON, ALWAYSACTIVE, OFF, PRICEPLANDECIDE;

	/**
	 * what is the current value in config for this parameter?
	 * @return
	 */
	public static GracePeriodConfig getFromConfig()	{
		return GracePeriodConfig.valueOf(ConfigProvider.getProperty("com.vodafone.global.er.transctrl.bl.PurchaseTransaction.USE_SUSPENDED_STATUS","OFF"));
	}
	
}
