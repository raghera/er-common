/*****************************Modification History*********************************
 * 
 *    Rev. No.		Date		Author					Description
 **********************************************************************************
 *		[1]		Apr-28-2006		Ly Le					CQ14112:
 *														Fix to tell if this is the first usage transaction
 *														for receipting data at package pricepoint 
 *	
 */
package com.vizzavi.ecommerce.business.charging;


import java.util.Date;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;
import com.vodafone.global.er.rating.RatedEvent;
/**
 * Encapsulates the result of a usage authorization call to the charging subsystem.
 * This is a simple bean.
 *
 */
public class UsageAuthorization extends BaseAuthorization {

	private    static final long serialVersionUID = -5123460399280837293L;

//	protected ServiceUsageInstance mServiceUsageInstance = null; 

	/**
	 * Maximum largest date object possible.
	 * For event based packages, set the end date to this maximum date.
	 * This is equivalent to Aug 17, 292278994.
	 */
	public static final Date DATE_MAX = new Date (Long.MAX_VALUE);
	private int mPreOrdered = 0; // @ER7 Hazem

	/**
	 * This stores the end date for the Usage from the Subscriptions object.
	 * It has been added here to enable the correct sorting of UsageAuthorization
	 * objects in ErRatingEngineImpl.usageAuthRate().
	 *
	 * Use for fix 1324. Given 2 calendar packages, a purchase of a monthly package,
	 * followed by the purchase of a weekly package, both with the same service.
	 * Performing a usage on the service should use the weekly package first, as
	 * this as an earlier expiration/end date.
	 *
	 * NOTE.  Event based packages have a start and end date on the same day!!!
	 * However, the heuristics will use the date check last in class
	 * UsageAuthorizationComparator.
	 */
	private Date mEndDate;
	private Date mStartDate;
	
	
	//@HUD RFRFRF
	//private boolean mAccountHasCustomResource = false;


	// ER7 Change

	private int mSubscriptionStatus;
	
	//[1] CQ14112 - add new member for first usage transaction
	 private boolean firstUsage = false;

	/**
	 * store the subscription resource balances.
	 * @todo Has to be reviewed
	 * @version ER8-2
	 */
	private ResourceBalance[] mSubResourceBalances;
	/**
	 * the value is true in case of usage from multiple subscriptions.
	 * @todo Has to be reviewed
	 * @version ER8-2
	 */
	private boolean mBasedOnMultiplePackages  ;

	//Er8 Change
	//TODO: ? why another EventUnits attribute , should remove it
	//@mawn
	//private double mEventUnits = 1 ;

	//(VFE-PS, ER8 P2: Access Control)
	//protected AccessControlDecision mAccessControlDecision;

	/**
	 * 
	 * holds the parent transaction id in case of usage from multiple subscriptions 
	 * @todo Has to be reviewed
	 * @version ER8-2
	 */
	private String mParentTransactionId;
	private long mParentTransactionIdLong = -1;

	//private long mReceiptingCreditBalanceImpact = 0;
	//private int mReceiptingUsageTypeAttribute = -1;
	//public final static int  PURCHASE_RECEIPT = 0;
	//public final static int  USAGE_COST = 1;
	//public final static int  NILE_COST = 2;
	//public final static int  CREDIT_COST = 3;
	
	//CR1423: Duplicate Charging
	private int reIssue = 0;
	
	//MQC 9436 - Denotes whether a validation request was sent as part of this auth
	private Boolean isValidatedAccount = null;
	/**
	 * Public constructor.
	 *
	 * @param amount - double of the amount ,
	 * @param currency - int of the currency code,
	 * @param taxAmount - double of the taxable amount,
	 * @param taxRate - double of the tax rate,
	 * @param errorId - String of the error code,
	 * @param errorDescription - String of the error description,
	 * @param pack - the CatalogPackage object,
	 * @param subscriptionId - String of the subscription id,
	 * @param servicePricePoint - the PricePoint object.
	 */
	public UsageAuthorization(double amount, int currency, double taxAmount, double taxRate,
			String errorId, String errorDescription, CatalogPackage pack,
			String subscriptionId, PricePoint servicePricePoint)
	{
		this(amount, currency, taxAmount, taxRate, errorId, errorDescription, pack,
				subscriptionId, servicePricePoint, DATE_MAX);
	}

	/**
	 * Public constructor.
	 *
	 * @param amount - double of the amount ,
	 * @param currency - int of the currency code,
	 * @param taxAmount - double of the taxable amount,
	 * @param taxRate - double of the tax rate,
	 * @param errorId - String of the error code,
	 * @param errorDescription - String of the error description,
	 * @param pack - the CatalogPackage object,
	 * @param subscriptionId - String of the subscription id,
	 * @param servicePricePoint - the PricePoint object.
	 * @param endDate - the endDate of the UsageAuthorization.
	 */
	public UsageAuthorization(double amount, int currency, double taxAmount, double taxRate,
			String errorId, String errorDescription, CatalogPackage pack,
			String subscriptionId, PricePoint servicePricePoint, Date endDate)
	{
		//this.taxRate = taxRate;
		mTaxAmount = taxAmount;
		//setRate(amount);
		setResource(ChargingResource.getResource(currency));
		this.errorId = errorId;
		this.errorDescription = errorDescription;
		this.mPackage =pack;
		super.packageSubscriptionId = subscriptionId;
		this.mPricePoint = servicePricePoint;
		this.mEndDate = endDate;
	}

	/**
	 * Public constructor, setting the date to DATE_MAX.
	 */
	public UsageAuthorization()
	{
		this.mEndDate = DATE_MAX;
	}

	/**
	 * Public constructor, setting the date to DATE_MAX.
	 * @param event - the RatedEvent object.
	 */
	@SuppressWarnings("deprecation")
	public UsageAuthorization(RatedEvent event)
	{
		super(event);
		this.setEventUnits(event.getEventUnits());
		this.mEndDate = DATE_MAX;
	}
	/**
        This is the matching price point found for the authorisation
	 */
	public PricePoint getServicePricePoint()
	{
		return getPricePoint();
	}

	/**
	 * Get the end date for the Subscription and PricePoint.
	 * @return endDate - Date object of the end date.
	 */
	public Date getEndDate()
	{
		return this.mEndDate;
	}

	/**
	 * get the status of the Subscription.
	 * @return status - int value of the status.
	 */
	public int getSubscriptionStatus()
	{
		return this.mSubscriptionStatus;
	}

	//ER 8 

	@Override
	public double getEventUnits()
	{
		return super.getEventUnits();
	}

	//ER 8 

	@Override
	public void setEventUnits(double EventUnits)
	{
		super.setEventUnits(EventUnits);
	}


//	/**
//	 * @version      ER 8.0 P2
//	 * @author       VFE PS Team
//	 * @date         12-Sep-2005
//	 * @description  (Access Control use-case)      return AccessControlDecision. 
//	 **/
//	public AccessControlDecision getAccessControlDecision()
//	{
//		return this.mAccessControlDecision; 
//	}
//
//	/**
//	 * @version      ER 8.0 P2
//	 * @author       VFE PS Team
//	 * @date         12-Sep-2005
//	 * @description  (Access Control use-case)      Set mAccessControlDecision. 
//	 **/
//	public void setAccessControlDecision (AccessControlDecision accCtrlDecision) 
//	{
//		this.mAccessControlDecision = accCtrlDecision;
//	}

	/**
	 * Set the end date for the Subscription and PricePoint.
	 * @param endDate - Date object of the end date.
	 */
	public void setEndDate(Date endDate)
	{
		this.mEndDate = endDate;
	}

	public void setIsPreordered(int PreOrdered)
	{
		mPreOrdered = PreOrdered;
	}
	public int getIsPreordered()
	{
		return mPreOrdered;
	}
	/**
	 * Set the status of the Subscription.
	 * @param status - int value of the status.
	 */
	public void setSubscriptionStatus(int status)
	{
		this.mSubscriptionStatus = status;
	}
	/**
	 * ER7 This check if this is a usage or an express purchase
	 * 
	 */
	public boolean isSuccessfulExpressPurchase()
	{
		boolean val = false;
		if (this.subReasonCode.getCode()==ReasonCode.C_SUCCESSFUL_EXPRESS_PURCHASE) val = true;
		return val;
	}

	/** ER7 **/
	//public int getReceiptingUsageTypeAttribute(){
	//	return mReceiptingUsageTypeAttribute;
	//}
	/** ER7 **/
	//public long getReceiptingCreditBalanceImpact(){
	//	return mReceiptingCreditBalanceImpact;
	//}

	/**
	 * Returns a String representation of the object.
	 * @return - String of the parameters of the current object.
	 */
	@Override
	public String toString()
	{
        StringBuffer sb = new StringBuffer(super.toString());

        sb.append( " endDate=" );
        sb.append(  mEndDate );

        sb.append(  " SubscriptionStatus=" );
        sb.append(  mSubscriptionStatus );
        
        sb.append(  " reIssue=" );
        sb.append(  reIssue );
	        
	    return sb.toString();
	}

	public void setSubResourceBalances(ResourceBalance[] mSubResourceBalances)
	{
		this.mSubResourceBalances = mSubResourceBalances;
	}


	public ResourceBalance[] getSubResourceBalances()
	{
		return mSubResourceBalances;
	}

	public boolean isBasedOnMultiplePackages(){
		return mBasedOnMultiplePackages;
	}

	public void setBasedOnMultiplePackages(boolean basedOnMultiplePackages){
		this.mBasedOnMultiplePackages = basedOnMultiplePackages;

	}

	public void setSubscriptionIds(String[] subscriptionIds){
		this.mSubscriptionIds = subscriptionIds;
	}


	/**
	 * 
	 * @param mParentTransactionId
	 */
	public void setParentTransactionId(String mParentTransactionId)
	{
		this.mParentTransactionId = mParentTransactionId;
	}
	public void setParentTransactionIdLong(long id) {
		mParentTransactionIdLong = id;
	}


	/**
	 * 
	 * @return
	 */
	public String getParentTransactionId()
	{
		if (mParentTransactionId == null) {
			if (mParentTransactionIdLong > 0) {
				mParentTransactionId = Long.toString(mParentTransactionIdLong);
			}
		}
		return mParentTransactionId;
	}
	public long getParentTransactionIdLong() {
		if (mParentTransactionIdLong == -1) {
			if (mParentTransactionId != null) {
				try {
					mParentTransactionIdLong = Long.parseLong(mParentTransactionId);
				}
				catch (NumberFormatException ne) {
					// nothing
					mParentTransactionIdLong = -2;
				}
			}
		}
		return mParentTransactionIdLong;
	}


	public void setServiceSubmit(boolean isServiceSubmit)
	{
		this.mServiceSubmit = isServiceSubmit;
	}


	public void setStartDate(Date mStartDate)
	{
		this.mStartDate = mStartDate;
	}


	public Date getStartDate()
	{
		return mStartDate;
	}


	/**
     * [1] CQ14112
     * Returns true if this is the authorisation of the first usage transaction
     * @return true|false
     */
	public boolean isFirstUsage() {
		return firstUsage;
	}

	/**
	 * [1] CQ14112
	 * Set to true if this is the result of the first usage transaction
	 * @param firstUsage
	 */
	public void setFirstUsage(boolean firstUsage) {
		this.firstUsage = firstUsage;
	}
	
	
	
	// Fair Usage implementation - equals method required for method getUsageAuthorizationForFairUsage
	// in ErRatingEngineImpl class.  This method may remove UsageAuthorizations from the heuristic options.
	// For a pre-ordered subscription, the UsageAuthorization returned is not the same object as that in  
	// the options list.  To remove the object it is necessary to override the equals method here.
	
	
	//CR1423: Duplicate Charging
	/**
     * Gets the re-issue Number.
     * Each following duplicate transaction will have its re-issue number 
     * incremented by one. 
     * (i.e: the first duplicate transaction will have a 
     * duplicate number set to 1. The second duplicate 
     * transaction will have a duplicate number set to 2)
	 *
	 * @return int
	 */
	public int getReIssue() {
        return reIssue;
    }

    /**
     * Sets re-issue Number.
     * Each following duplicate transaction will have its re-issue number 
     * incremented by one. 
     * (i.e: the first duplicate transaction will have a 
     * duplicate number set to 1. The second duplicate 
     * transaction will have a duplicate number set to 2)
     * @param reIssue
     */
    public void setReIssue(int reIssue) {
        this.reIssue = reIssue;
    }
    //CR1423: Duplicate Charging
    
    /**
     * Set to denote whether or not validation has been done as
     * part of this Usage Transaction
     * @param isValidated
     */
    public void setIsValidatedAccount(final Boolean isValidated) {
    	this.isValidatedAccount = isValidated;
    }
    
    //MQC 9436 Start
	/**
	 * Tells you if a validation was done for the related account for this 
	 * Authorisation
	 * true means it has already been done - no
	 * @return
	 */
    public Boolean getIsValidatedAccount() {
		return isValidatedAccount;
	}

    //MQC 9436 End
    
    /** 
	 * Override the equals method to return true if this UsageAutorization equals the object parameter
	 * @param object
	 * @return true | false
	 */
	@Override
	public boolean equals(Object o) {
		
		if((o !=null) && (o.getClass().equals(this.getClass()))) {
		   return ((UsageAuthorization)o).getPackageSubscriptionId().equals(this.getPackageSubscriptionId()); 
		}
		
		return false;
	}

	//
	// Fair Usage implementation with override of equals method.
	/**
	 * Override the hashCode method 
	 * @return int
	 */
	@Override
	public int hashCode() {
		
		return this.getPackageSubscriptionId().hashCode();
	}
	

	
}
