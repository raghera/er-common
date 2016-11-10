/**
 * 
 */
package com.vizzavi.ecommerce.util;

/**
 * @author prashantsrivastava
 *
 */
public class TaxUtil {

	/**
	 * 
	 */
	public TaxUtil() {
		
	}
	
	/**
	 * This method computes the Tax Rate if Net Rate and Gross Rate differ
	 * Here's the formula for the tax rate:
	 * 
	 * TaxRate = (GrossRate - NetRate) / NetRate
	 * 
	 * @param taxRate
	 * @param grossRate
	 * @param netRate
	 * @return
	 */
	public static double computeTaxRateIfRequired(double taxRate, double grossRate, double netRate) {
		if (taxRate == 0.0 && grossRate != netRate) 
			taxRate = (grossRate - netRate) / netRate;
		return taxRate;
	}

}
