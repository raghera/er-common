package com.vizzavi.ecommerce.business.selfcare;

import java.util.Date;



/**

    @see FilterAttributes
*/
public interface SubscriptionFilter extends FilterAttributes
{
	
	//MQC 6056 Start
	
	public String getSubscriptionId();
	public void setSubscriptionId(String subscriptionId);

	//MQC 6056 End
	
    public int getChargingMethod();

    public void setPreOrder(int PreOrder); // @ER7
    
    public int getPreOrder();// @ER7

    
    public void setChargingMethod(int method);

    /**
    * @see SubscriptionStatus
    */
    public void setSubscriptionStatus(int status);

    /**
        Find subscriptions which end after this date
    */
    public void setSubscriptionEndDateStartValue(Date val);

    /**
     * Used to filter subscriptions by package ServiceId
     */
    public void setServiceId(String serviceId);

    /**
     * Used to filter subscriptions by package ServiceId
     */
    public String getServiceId();

    /**
        This is used to find subscriptions which expire between the start and end values.
        Find subscriptions which expire before this date
    */
    public void setSubscriptionEndDateEndValue(Date val);

    /**
        Used internally by ER
    */
    public Date getSubscriptionEndDateStartValue();

    /**
        Used internally by ER
    */
    public Date getSubscriptionEndDateEndValue();
    
    
    //@hud STKHREQ36
    public boolean needMicroServiceStatus();
    public void setNeedMicroServiceStatus(boolean bNeed);
    

    
    /**
     * STKHREQ242 - Grace Period
     * Returns the retry date end value, i.e. all subscriptions with
     * retry date in the past as compared to this date will be retried. 
     * 
     * @return				The retry date if set or null.
     */
    public Date getRetryDateEndValue() ;

    /**
     * STKHREQ242 - Grace Period
     * Sets the retry date end value, i.e. all subscriptions with
     * retry date in the past as compared to this date will be retried. 
     * 
     * @param	retryDateEndValue	The Date object for the retry end
     * 								date, end value.
     */
    public void setRetryDateEndValue( Date retryDateEndValue ) ;
    
	//RBT Start - Additional filter value
    public void setIsParent(int value);
    public int getIsParent();
    //RBT End

    //REMEDY 6689
    public int getDuration();
    
    public void setDuration(int duration);
    //END REMEDY 6689
    
  //RBT Enhancements Start - Additional filter value
    public void setParentPackageId(String value);
    
    public String getParentPackageId();
    //RBT Enhancements End - Additional filter value
    
    /* no - transactions are send (default is 'no'), yes - transactions are not send */
    public void setTransactionsNotRequired(String no);
    
    public String getTransactionsNotRequired();
    
    //CR-0978 Location Services
	public void setClientId(String clientId);
    
    public String getClientId();
    
    //CR-0978 Location Services
	public void setTariff(String tariff);
    
    public String getTariff();
    
    //CR1209 - Filter subscriptions by Package Class
    public void setPackageClass(String packageClass);
    
    public String getPackageClass();
    
    //CR-1791
    public void setPackageId(String packageId);
    
    public String getPackageId();
    
    //MQC 6519 - add flag to indicate whether to limit the number of transactions returned
    //for each subscription to max_events, the order of transactions returned will youngest first
    public void setUseMaxEventsForTransactions(boolean useMaxEventsForTransactions);
    
    public boolean isUseMaxEventsForTransactions ();
    
    //CR 1970 - Mobile Protect additions
    public void setPartnerId(String partnerId);
    
    public String getPartnerId();
	public abstract int getSubscriptionStatus();
	
	/**
	 * CR 2245 upsell discount prorate, set to retrieve last payment transaction (purchase, renew or recurr) for the subscription
	 * @param flag
	 */
	public void setRetrieveLastPaymentTransaction(boolean flag);
	
	/**
	 * CR 2245 upsell discount prorate, retrieve last payment transaction (purchase, renew or recurr) for the subscription required
	 * @return last payment transaction required
	 */
	public boolean isRetrieveLastPaymentTransaction();
	
}
