/**
 * 
 */
package com.vizzavi.ecommerce.business.common;

import java.util.Locale;


/**
 * @author Ravi Aghera
 * 
 * Object for passing required data between layers of the application
 *
 */
public interface PromotionData {

	/**
	 * Setter method.
	 * @param clientApplicationId - The calling application identifier
	 * 
	 */
	public void setClientApplicationId(String ClientApplicationId);

	/**
	 * Getter method
	 * @return - The calling application identifier
	 */
	public String getClientApplicationId();
	
	/**
	 * Setter method
	 * @param catalogServiceId - The name of the service from which to checkPromotions
	 */
	public void setCatalogServiceId(String catalogServiceId);
	
	/**
	 * Getter method
	 * @return - the id if the service from which to checkPromotions
	 */
	public String getCatalogServiceId();


	/**
	 * Setter method
	 * @param msisdn - The name of the service from which to checkPromotions
	 */
	public void setMsisdn(String msisdn);

	/**
	 * Getter method
	 * @return - the msisdn for account
	 */
	public String getMsisdn();

	/**
	 * Setter method
	 * @param msisdn - The current locale
	 */	
	public void setPromotionLocale(Locale promotionLocale);

	/**
	 * Getter method
	 * @return - the locale
	 */
	public Locale getPromotionLocale();

	String[] getPackIds();

	void setPackIds(String[] packIds);

}



