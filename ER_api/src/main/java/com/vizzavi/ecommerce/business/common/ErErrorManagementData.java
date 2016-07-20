package com.vizzavi.ecommerce.business.common;

import java.util.Locale;

import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vizzavi.ecommerce.business.selfcare.Transaction;


/**
 * Ravi Aghera
 * 
 * An data object to hold relevant data
 * to manage an error response during a 
 * ER api call.  
 * Originally added for PurchaseApi or refundApi
 * IF Timeout errors
 * 
 */
public interface ErErrorManagementData {
	
	/**
	 * The subscription required related to api response returned
	 * @param subscription
	 */
	public void setSubscription( final Subscription subscription );
	public Subscription getSubscription( );
	
	/**
	 * The Transaction associated to the original api call
	 * @param transaction
	 */
	public void setTransaction( final Transaction transaction );
	public Transaction getTransaction( );
	
	/**
	 * The msisdn to identify the account associated to the
	 * original api call
	 * @param msisdn
	 */
	public void setMsisdn( final String msisdn );
	public String getMsisdn( );
	
	/**
	 * The errorId associated to the original api call
	 * @param errorId
	 */
	public void setErrorId( final String errorId );
	public String getErrorId( );
	
	/**
	 * The error description associated to the original api call
	 * @param errorDescription
	 */
	public void setErrorDescription( final String errorDescription );
	public String getErrorDescription( );

	/**
	 * Locale associated to the original api call
	 * @param locale
	 */
	public void setLocale(final Locale locale);
	public Locale getLocale();
	
	
}
