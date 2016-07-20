package com.vodafone.global.er.subscriptionmanagement;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.vizzavi.ecommerce.business.common.ChargingMethod;
import com.vizzavi.ecommerce.business.selfcare.SubscriptionFilter;

/**
    The attributes that the user can set when retrieving subscriptions

 */
public class SubscriptionFilterImpl extends FilterAttributesImpl implements SubscriptionFilter
{
	private static final long serialVersionUID = 8198190487017301045L;

	private static final Logger logger = Logger.getLogger(SubscriptionFilterImpl.class);
	
	//MQC 6056
	private String subscriptionId;

	protected int mMethod;
	protected int mSubscriptionStatus = -1;
	protected Date mEndDateStartValue;
	protected Date mEndDateEndValue;
	protected String mPackageId = null;
	protected String mServiceId = null;
	protected int mPreOrder = -1; // @ER7

	//RBT Start - Additional filter value
	protected int isParent;
	//RBT end 

	//RBT Enhancements Start - Additional filter value
	protected String mParentPackageId;
	//RBT Enhancements End

	//@hud STKHREQ36 ER9
	protected boolean mNeedMicroServiceStatus = true;

	// STKHREQ242 - Grace Period
	protected Date retryDateEndValue = null ;

	//REMEDY 6689
	protected int mDuration = -999;
	//REMEDY 6689

	protected String transactionsNotRequired = "no";

	//CR-0978 Location Services
	protected String mClientId;

	//CR-0978 Location Services
	protected String mTariff;

	//CR1209 - Filter subscriptions by Package Class
	protected String mPackageClass;

	//MQC 6519 - add flag to indicate whether to limit the number of transactions returned
	//for each subscription to max_events, the order of transactions returned will youngest first
	protected boolean mUseMaxEventsForTransactions = false;

	//@amin CR1564
	protected boolean usingLocalDate = false;

    //CR 1970 - Mobile Protect additions
    protected String mPartnerId;
    
    //CR 2245 upsell discount prorate, set to retrieve last payment transaction (purchase, renew or recurr) for the subscription
    protected boolean mRetrieveLastPaymentTransaction = false;
    
	public boolean isUsingLocalDate() {
		return usingLocalDate;
	}

	public void setUsingLocalDate(boolean usingLocalDate) {
		this.usingLocalDate = usingLocalDate;
	}
	// CR1564 ends

	//MQC 6056 Start
	@Override
	final public String getSubscriptionId(){
		return subscriptionId;
	}

	@Override
	final public void setSubscriptionId(final String subscriptionId){
		this.subscriptionId = subscriptionId;
	}
	//MQC 6056 End


	@Override
	public void setPreOrder(int PreOrder) // @ER7
	{
		mPreOrder = PreOrder;
	}

	@Override
	public int getPreOrder()// @ER7
	{
		return mPreOrder ;
	}
	@Override
	public void setPackageId(String PackageId)
	{
		//TODO refactor to use the list instead
		mPackageId = PackageId;
	}

	@Override
	public String getPackageId()
	{
		return mPackageId;
	}


	@Override
	public void setChargingMethod(int method)
	{
		mMethod = method;
	}

	@Override
	public int getChargingMethod()
	{
		return mMethod;
	}

	@Override
	public void setSubscriptionStatus(int subscriptionStatus)
	{
		mSubscriptionStatus = subscriptionStatus;
	}

	@Override
	public int getSubscriptionStatus()
	{
		return mSubscriptionStatus;
	}

	@Override
	public void setServiceId(String serviceId)
	{
		mServiceId = serviceId;
	}

	@Override
	public String getServiceId()
	{
		return mServiceId;
	}

	public boolean hasServiceId()
	{
		return mServiceId != null && mServiceId.trim().length() > 0;
	}

	@Override
	public void setRecurringEventsOnly(boolean flag)
	{
		setChargingMethod(ChargingMethod.RECURRING);

	}


	@Override
	public boolean isRecurringEventsOnly()
	{
		boolean rv = ChargingMethod.isRecurring(getChargingMethod());

		return rv;
	}


	@Override
	public void setNonRecurringEventsOnly(boolean flag)
	{
		setChargingMethod(ChargingMethod.NON_RECURRING);
	}


	@Override
	public boolean isNonRecurringEventsOnly()
	{
		boolean rv = ChargingMethod.isNonRecurring(getChargingMethod());

		return rv;
	}


	/**
        This is used to find subscriptions which expire between the expiry start and end dates.
	 */
	 @Override
	 public void setSubscriptionEndDateStartValue(Date val)
	{
		 mEndDateStartValue = val;
	}


	 /**
        This is used to find subscriptions which expire between the expiry start and end dates.
	  */
	 @Override
	 public void setSubscriptionEndDateEndValue(Date val)
	 {
		 mEndDateEndValue = val;
	 }

	 /**
        Used internally by ER
	  */
	 @Override
	 public Date getSubscriptionEndDateEndValue()
	 {
		 return mEndDateEndValue;
	 }


	 /**
        Used internally by ER
	  */
	 @Override
	 public Date getSubscriptionEndDateStartValue()
	 {
		 return mEndDateStartValue;
	 }



	 /**
	  * Returns the string representation.
	  * 
	  * @return		The String object containing string representation.
	  */
	 @Override
	 public String toString()
	 {

		 StringBuffer stringBuffer = new StringBuffer( "Charging Method=" ) ;
		 stringBuffer.append( mMethod ) ;
		 stringBuffer.append( ", SubscriptionStatus=" ) ;
		 stringBuffer.append( mSubscriptionStatus ) ;
		 logger.debug("calculating offset between "+mEndDateStartValue+" and today");
		 stringBuffer.append(", offset="+numDaysBetweenDates(mEndDateStartValue, new Date()));
		 if(mPackageId != null && !mPackageId.equals("") )	{
			 stringBuffer.append( ", packageId=" ) ;
			 stringBuffer.append( mPackageId ) ;
		 }
		 if(usingLocalDate)	{
			 stringBuffer.append( ", usingLocalDate=true" ) ;
		 stringBuffer.append( ", mRetrieveLastPaymentTransaction=" ) ;
		 stringBuffer.append( mRetrieveLastPaymentTransaction ) ;
		 }
		 stringBuffer.append(", maxEvents ").append(getMaxEvents());
		 
	    // MQC 9655 - add parent package id to subscription filter
		stringBuffer.append(", mParentPackageId=");
		stringBuffer.append(mParentPackageId);

		 return stringBuffer.toString() ;
	 }


	 //@hud STKHREQ36 ER9
	 @Override
	 public boolean needMicroServiceStatus()    {
		 return mNeedMicroServiceStatus;
	 }
	 @Override
	 public void setNeedMicroServiceStatus(boolean bNeed) {
		 mNeedMicroServiceStatus = bNeed;
	 }



	 /**
	  * STKHREQ242 - Grace Period
	  * Returns the retry date end value, i.e. all subscriptions with
	  * retry date in the past as compared to this date will be retried. 
	  * 
	  * @return				The retry date if set or null.
	  */
	 @Override
	 public Date getRetryDateEndValue()
	 {
		 return this.retryDateEndValue ;
	 }


	 /**
	  * STKHREQ242 - Grace Period
	  * Sets the retry date end value, i.e. all subscriptions with
	  * retry date in the past as compared to this date will be retried. 
	  * 
	  * @param	retryDateEndValue	The Date object for the retry end
	  * 								date, end value.
	  */
	 @Override
	 public void setRetryDateEndValue( Date retryDateEndValue )
	 {
		 this.retryDateEndValue = retryDateEndValue ;
	 }


	 //RBT Start - Additional filter value
	 @Override
	 public void setIsParent(int value){
		 isParent = value;
	 }

	 @Override
	 public int getIsParent(){
		 return isParent;
	 }
	 //RBT End - Additional filter value


	 //RBT Enhancements Start - Additional filter value
	 @Override
	 public void setParentPackageId(String value){
		 mParentPackageId = value;
	 }

	 @Override
	 public String getParentPackageId(){
		 return mParentPackageId;
	 }
	 //RBT Enhancements End - Additional filter value

	 //REMEDY 6689
	 @Override
	 public int getDuration()
	 {
		 return mDuration;
	 }

	 @Override
	 public void setDuration(int duration)
	 {
		 mDuration = duration;
	 }
	 //END REMEDY 6689

	 /* 'no' transactions are returned, 'yes' transactions are not returned */
	 @Override
	 public void setTransactionsNotRequired(String no)
	 {
		 transactionsNotRequired = no;
	 }
	 
	 //From the V2 Subs flow we shall use List<String> getTransacationTypes();
	 @Deprecated
	 @Override
	 public String getTransactionsNotRequired()
	 {
		 return transactionsNotRequired;
	 }

	 //CR-0978 Location Services
	 @Override
	 public void setClientId(String clientId) {
		 mClientId = clientId;
	 }

	 @Override
	 public String getClientId() {
		 return mClientId;
	 }

	 //CR-0978 Location Services
	 @Override
	 public void setTariff(String tariff) {
		 mTariff = tariff;
	 }

	 @Override
	 public String getTariff() {
		 return mTariff;
	 }

	 //CR1209 - Filter subscriptions by Package Class
	 @Override
	 public void setPackageClass(String packageClass) {
		 mPackageClass = packageClass;
	 }

	 @Override
	 public String getPackageClass() {
		 return mPackageClass;
	 }

	 //MQC 6519 - add flag to indicate whether to limit the number of transactions returned
	 //for each subscription to max_events, the order of transactions returned will youngest first
	 @Override
	 public void setUseMaxEventsForTransactions(boolean useMaxEventsForTransactions) {
		 mUseMaxEventsForTransactions = useMaxEventsForTransactions;
	 }

	 @Override
	 public boolean isUseMaxEventsForTransactions () {
		 return mUseMaxEventsForTransactions;
	 }

    //CR 1970 - Mobile Protect additions
    @Override
	public void setPartnerId(String partnerId) {
		mPartnerId = partnerId;
	}
    
    @Override
	public String getPartnerId() {
    	return mPartnerId;
    }
    
    //CR 2245 upsell discount prorate, set to retrieve last payment transaction (purchase, renew or recurr) for the subscription
    @Override
	public void setRetrieveLastPaymentTransaction(boolean flag) {
    	mRetrieveLastPaymentTransaction = flag;
    }

    //CR 2245 upsell discount prorate, retrieve last payment transaction (purchase, renew or recurr) for the subscription required
    @Override
	public boolean isRetrieveLastPaymentTransaction() {
		return mRetrieveLastPaymentTransaction;
	}
       

	/**
	 * Added a List of PackageIds
	 */
	
	public List<String> packageIds;

	public List<String> getPackageIds() {
		return packageIds;
	}

	public void setPackageIds(List<String> packageIds) {
		this.packageIds = packageIds;
	}	
	

	/**
	 *  Added following boolean for different transaction types
	 * 
	 * This is used specificially for the V2 subscription flow
	 *
	 */
	
	public boolean includeModifyTxns=false;
	
	public boolean includeRefundTxns=false;
	
	public boolean includePaymentTxns=false;

	public boolean includeModifyTxns() {
		return includeModifyTxns;
	}

	public void setIncludeModifyTxns(boolean includeModifyTxns) {
		this.includeModifyTxns = includeModifyTxns;
	}

	public boolean includeRefundTxns() {
		return includeRefundTxns;
	}

	public void setIncludeRefundTxns(boolean includeRefundTxns) {
		this.includeRefundTxns = includeRefundTxns;
	}

	public boolean includePaymentTxns() {
		return includePaymentTxns;
	}

	public void setIncludePaymentTxns(boolean includePaymentTxns) {
		this.includePaymentTxns = includePaymentTxns;
	}
	
}
