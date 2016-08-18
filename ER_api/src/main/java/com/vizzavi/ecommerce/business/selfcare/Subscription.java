package com.vizzavi.ecommerce.business.selfcare;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.DRMObject;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.common.ChargingMethod;
import com.vizzavi.ecommerce.business.common.RatingAttributes;
import com.vodafone.global.er.business.selfcare.MicroServiceStatus;
import com.vodafone.global.er.partner.B2BPartner;

/**
 * @author trushantpatel
 *
 */
public class Subscription implements java.io.Serializable
{
	
	protected Map<String, PurchasedService> purchasedServices = new HashMap<String, PurchasedService>();

	
	
	
	protected static final Logger logger = LoggerFactory.getLogger(Subscription.class);
	private    static final long serialVersionUID = 589407364300363869L;
	protected CatalogPackage mPack = null;
	//MQC8385 - we should store the pricepoint at sub level so we don't have to 
	//store it in the package
	protected PricePoint pricePoint=null;
	protected Date mPurchaseDate = null;
	protected Date mExpiryDate = null;
	protected Date mStartDate = null;
	protected Date mRenewalDate = null;
	protected boolean mPromotional = false; 
	protected boolean mBatchProcessor = false; 
	protected Date mPromotionalExpiryDate = null; 
	/**
        The status values can be
	 */
	protected int mStatus;
	
	protected boolean mHasValidMicroService = false;
	protected List<MicroServiceStatus> mMicroServiceSubList = null;
	protected Date mOverdueExpiryDate	= null;	// may not be used
	protected Date mBatchRetryDate = null;
	protected Date mLastExpiryDate = null;
	
	protected String mSubscriptionId = null;
	protected long mSubscriptionIdLong = -1;

	protected int mPaymentType;
	protected int mContentPaymentType;
	protected boolean  mIsDefault;
	protected boolean mPreOrdered =  false; //  @ER7
	protected String  mLinkedPtID = null; // @ER7

	protected double mNextCycleDiscount = 0;
	protected int mDeviceType = -1;
	// customer care data
	protected String mCsrId = null;
	protected boolean mIsArchived = false;

	protected Map<String, String> mServiceProvisioningTags = new HashMap<String, String>();
	protected Map<String, Integer> mServiceStatuses = new HashMap<String, Integer>();

	protected ResourceBalance[] mBalances = null;
	protected RatingAttributes mMatchingAttributes = null;

	/**
	 * DRM Object associated with the subscription
	 * @since ER 5.1
	 */
	protected DRMObject m_DRMObject = null;

	//an associated payment transaction that has paid for the subscription
	protected Long paymentTransactionId;
	/** new method to check if this subscription has expired
	 * @since ER 5.1
	 * */
	protected boolean mHasExpired = false;

	/** new attributes to support the non-refundable subscriptions
	 * @since ER 5.1
	 * */
	protected String nonRefundDescription = null;
	protected boolean isRefundable = true;
	protected Map<String, String> mServiceNonRefundDescription = new HashMap<String, String>();

	/**
	 * @since ER 6
	 */
	public static final int INTERACTIVE_NONE= 0;
	public static final int INTERACTIVE_FIRST= 1;
	public static final int INTERACTIVE_ALL= 2;
	protected int mInteractiveUsageFlage=INTERACTIVE_NONE;

	// [Amd001] - 6/4/2005 [start]  
	protected long mCurrentNoOfOccurences = -1; // the default  recurring subscription.
	// [Amd001] - 6/4/2005 [end]  

	/**** German Migration -sally  Use Case: Minimum Subscription Period ****/
	protected Date mFinalMinSubscriptionEndDate=new Date();
	protected double mPenaltyCharge=0; 
	/**** End German Migration -sally  Use Case: Minimum Subscription Period ****/
	protected boolean mSuperPackage;  // will hold 1 if the package is super package

	/**
	 * Creation balance,set only in case creation is done using super credit.
	 */
	protected int superCreditBalance;
	/**
	 * Creation Id,,set only in case creation is done using super credit.
	 */
	protected String superCreditID;
	/**
	 * A collection of'SuperCreditOption' objects for this subscription with keys'superCreditIds'
	 */
	//protected HashMap superCreditBalances;
	protected ResourceBalance[] superCreditBalances = null;

	//REMEDY 5685 - add flag to indicate that on time of initial purchase a duplicate historic pricepoint exists
    protected boolean mHasHistoricPricepoint = false;
    
    //REMEDY 5871 - add flag to indicate whether this is / was a recurring TRIAL subscription
    protected boolean mIsWasRecurringTrial = false;
    
    //RBT Enhancement CR - Start
    protected String mParentPackageId = null;
    //RBT Enhancement CR - End
    
	// CR-0095 RBT START 
    protected int extensionPeriod;
	// CR-0095 RBT END 

    //MQC 5126
    protected int mPreviousStatus;
    
    //CR-1209_1 - flag to indicate for parent / child scenarios that the parent was purchased together with the child
    protected boolean mParentAlsoPurchased = false;
    
    //MQC6014 - flag to indicate if subscription has had a successful provision on usage
    protected boolean mProvisionOnUsageSuccess = false;
    
    //CR 1209 add package class
	protected String mPackageClass = null;
	
    protected List<? extends Transaction> transactions = new ArrayList<Transaction>();

	// CR - Enhanced Bundle Reporting (Feb 2010)
    protected Date lastPeriodStartDate = null;
    //CR1564-Utctimezone for diff region in country
    protected Date mLocalStartDate = null;
    protected Date mLocalEndDate = null;
   	//CR1564-Utctimezone for diff region in country
 	protected Calendar mLocalStartDateCal = null;
    protected Calendar mLocalEndDateCal = null;

    //MQC 6085: Duplicate charging using inactive / closed subscriptions
    protected long lastUsageTransactionIdForSameContent = -1;
    

    //CR 1970 - Mobile Protect additions
    protected String mPartnerId = null; 	//Partner ID of the subscription
    
    //CR1981 Misano changes, account SPID when the subscription was bought
    protected String spId = null;
    
    protected String msisdn;
    /** introduced for CR 2082 - Google */
    protected String externalSubId;

    //CR 2080 - SMS Notification additions
    protected String mMerchantName = null; 	//Merchant Name of the subscription
    
    //CR 2108
    protected double renewalPreRate = -1;
    //If provided then this will be the tax rate applied and NOT what is in the priceplan
    protected double partnerTaxRate = -1;
 
    //MQC 8393 - add flag to indicate whether this is a recurring Promo Code subscription, this can be a TRIAL or any other promo code
    protected boolean mIsWasRecurringPromoCode = false;
    

    //MQC 8385 - PackageId refactor
    //protected PricePoint pricepoint;
    
    public final static String PACKAGE_DELIMITER = "__X__";
    
    //CR 2245 upsell discount prorate, the last payment transaction (purchase, renew or recurr) for the subscription, this is only set if specified on the subscription filter
    protected Transaction lastPaymentTransaction = null;
    
    //CR2255 Phase2 - add partner information
    protected B2BPartner b2bPartner = null;
    
    /**
  	 * CR CTB-1 Advanced Linked Pricepoint - a counter of the number of renewals of a pricepoint which has a linked pricepoint
  	 */
  	protected int renewalCounterToLinkedPricepoint = -1;
  	
  	/**
  	 * MQC9013 - pre rate price as gross to also be applied when renewal pre rate is set
  	 */
  	protected boolean renewalPreRateGross = false;
  	
	/**end ER7 av*/
	//static Category logger= Category.getInstance(Subscription.class);
	public Subscription()
	{
	}

	public Subscription(CatalogPackage pack, RatingAttributes attrs)
	{
		setPackage(pack);
		mMatchingAttributes = attrs;
	}
	
	public Subscription(CatalogPackage pack, RatingAttributes attrs, Date purchaseDate, Date expiryDate, int status)
	{
		this.mPurchaseDate = purchaseDate;
		this.mExpiryDate = expiryDate;
		this.mStatus = status;
		setPackage(pack);
		mMatchingAttributes = attrs;
	}

	public Subscription(Subscription sub)
	{
		if (sub.mPack!=null) 		
			setPackage(sub.mPack);
;
		this.mPurchaseDate = sub.mPurchaseDate;
		this.mExpiryDate = sub.mExpiryDate;
		this.mStartDate = sub.mStartDate;
		this.mRenewalDate = sub.mRenewalDate;
		this.mStatus = sub.mStatus;

		this.mSubscriptionId = sub.mSubscriptionId;
		mSubscriptionIdLong = sub.getSubscriptionIdLong();

		this.mPaymentType = sub.mPaymentType;
		this.mContentPaymentType = sub.mContentPaymentType;
		this.mIsDefault = sub.mIsDefault;
		this.mNextCycleDiscount = sub.mNextCycleDiscount;
		this.mDeviceType = sub.mDeviceType;
		this.mCsrId = sub.mCsrId;
		this.mIsArchived = sub.mIsArchived;
		this.mServiceProvisioningTags = new HashMap<String, String>(sub.mServiceProvisioningTags);
		this.mServiceStatuses = new HashMap<String, Integer>(sub.mServiceStatuses);
		if (sub.mMatchingAttributes!=null) this.mMatchingAttributes = new RatingAttributes (sub.mMatchingAttributes);
		if (sub.mBalances!=null) {
			mBalances = new ResourceBalance [sub.mBalances.length];
			for (int i = 0; i<sub.mBalances.length;i++)
				this.mBalances[i] = new ResourceBalance (sub.mBalances[i]);
		}
		this.mHasExpired = sub.mHasExpired;
		this.mServiceNonRefundDescription = new HashMap<String, String>(sub.mServiceNonRefundDescription);
		this.isRefundable = sub.isRefundable;
		this.mPreOrdered = sub.isPreOrdered(); //  @ER7 
		this.mLinkedPtID = sub.getLinkedPtID(); // @ER7
		this.nonRefundDescription = sub.nonRefundDescription;
		this.m_DRMObject = sub.m_DRMObject;
		this.mInteractiveUsageFlage = sub.mInteractiveUsageFlage;
		this.mPromotional = sub.mPromotional; 
		this.mPromotionalExpiryDate = sub.mPromotionalExpiryDate; 
		this.mBatchProcessor = sub.mBatchProcessor; 
		// [Amd001] - 31/3/2005 [start]  
		                         //	this.currentNoOfOccurances = sub.currentNoOfOccurances; 
		this.mCurrentNoOfOccurences = sub.mCurrentNoOfOccurences; 
		// [Amd001] - 31/3/2005 [end]  
		/**** German Migration -sally  Use Case: Minimum Subscription Period ****/
		this.mFinalMinSubscriptionEndDate = sub.mFinalMinSubscriptionEndDate;
		this. mPenaltyCharge = sub.mPenaltyCharge ; 
		/**** End German Migration -sally  Use Case: Minimum Subscription Period ****/    
		/*********************Angie Phase II ************************/
		this.mSuperPackage   = sub.isSuperPackage();
		/************************************************************/
		this.superCreditBalances = sub.getSuperCreditBalances();

		//@hud STKHREQ36
		mHasValidMicroService = sub.hasValidMicroService();
		
		//REMEDY 5685
        this.mHasHistoricPricepoint = sub.hasHasHistoricPricepoint();
        
        //RBT Enhancement CR - Start
        this.mParentPackageId = sub.getParentPackageID();
        //RBT Enhancement CR - End
        
		// CR-0095 RBT START 
        this.extensionPeriod = sub.getExtensionPeriod();
		// CR-0095 RBT END 
        
        //MQC 5126
        this.mPreviousStatus = sub.getPreviousStatus();
        
        //CR-1209_1 - flag to indicate for parent / child scenarios that the parent was purchased together with the child
        this.mParentAlsoPurchased = sub.isParentAlsoPurchased();

        //MQC6014 - flag to indicate if subscription has had a successful provision on usage
        this.mProvisionOnUsageSuccess = sub.isProvisionOnUsageSuccess();
        
        //CR 1209 add package class
        this.mPackageClass = sub.getPackageClass();

    	// CR - Enhanced Bundle Reporting (Feb 2010)
        this.lastPeriodStartDate = sub.getLastPeriodStartDate();
        
        //MQC 6085: Duplicate charging using inactive / closed subscriptions
        this.lastUsageTransactionIdForSameContent = sub.getLastUsageTransactionIdForSameContent();

        //MQC 6368 set paymentTransactionId
        this.paymentTransactionId = sub.getPaymentTransactionId();
        
        //CR 1970 - Mobile Protect additions
        this.mPartnerId = sub.getPartnerId();
        
        //CR1981 Misano changes, account SPID when the subscription was bought
        this.spId = sub.getSpId();
        
        //CR 2080 - SMS Notification additions
        this.mMerchantName = sub.getMerchantName();
        
        //CR 2108 - Flexible Subs
        this.renewalPreRate = sub.getRenewalPreRate();
        
        this.msisdn = sub.getMsisdn();
        
        //CR 2245 upsell discount prorate, last payment transaction (purchase, renew or recurr) for the subscription
        this.lastPaymentTransaction = sub.getLastPaymentTransaction();
        
        //CR2255 Phase2 - add partner information
        this.b2bPartner = sub.getB2BPartner();
        
        //CR CTB-1 Advanced Linked Pricepoint
        this.renewalCounterToLinkedPricepoint = sub.getRenewalCounterToLinkedPricepoint();
        
        //MQC 9031 - pre rate price as gross to also be applied when renewal pre rate is set
        this.renewalPreRateGross = sub.isRenewalPreRateGross();
	}


	/**
	 * Check to see if package has expired
	 * @return a boolean; true if package has expired
	 * @since ER 5.1
	 */
	public boolean hasExpired()
	{
		return mHasExpired;
	}

	public boolean isArchived()
	{
		return mIsArchived;
	}

	public Date getPurchaseDate()
	{
		return mPurchaseDate;
	}

	public Date getStartDate()
	{
		return mStartDate;
	}

	public Date getExpiryDate()
	{
		return mExpiryDate;
	}

	public boolean isPromotional() 
	{ 
		return mPromotional; 
	} 

	public boolean isBatchProcessor() 
	{ 
		return mBatchProcessor; 
	} 

	public Date getPromotionalExpiryDate() 
	{ 
		return mPromotionalExpiryDate; 
	} 

	public Date getRenewalDate()
	{
		return mRenewalDate;
	}

	public CatalogPackage getPackage()
	{
		return mPack;
	}

	public int getPaymentType()
	{
		return mPaymentType;
	}

	/**
	 * @return
	 */
	 public String getSubscriptionId()
	{
		if (mSubscriptionId == null) {
			if (mSubscriptionIdLong > 0) {
				mSubscriptionId = Long.toString(mSubscriptionIdLong);
			}
		}
		return mSubscriptionId;
	}

	 //@hud RFRFRF
	 public long getSubscriptionIdLong() {
		 if (mSubscriptionIdLong == -1) {
			 if (mSubscriptionId != null && !mSubscriptionId.equals("")) {
				 try {
					 mSubscriptionIdLong = Long.parseLong(mSubscriptionId);
				 }
				 catch (NumberFormatException ne) {
					 // nothing
					 mSubscriptionIdLong = -2;
				 }
			 }
		 }
		 return mSubscriptionIdLong;
	 }

	 public void setSubscriptionIdLong(long id) {
		 mSubscriptionIdLong = id;
		 mSubscriptionId = Long.toString(mSubscriptionIdLong);
	 }


	 public int getStatus()
	 {
		 return (mStatus == SubscriptionStatus.ACTIVE || mHasValidMicroService) ?
				 SubscriptionStatus.ACTIVE : mStatus;
	 }

	 public boolean isInactiveOrClosed()	{
		 return (mStatus==SubscriptionStatus.INACTIVE || mStatus==SubscriptionStatus.CLOSE);
	 }
	 
	 //MQC 9590 - return if subscription in failed status
	 public boolean isFailed()	{
		 return (mStatus>=SubscriptionStatus.FAILED_START_RANGE)&&(mStatus<SubscriptionStatus.FAILED_END_RANGE);
	 }
	 
	 public boolean isActive()
	 {
		 //return SubscriptionStatus.isActive(mStatus);
		 if (mStatus==SubscriptionStatus.ACTIVE) {
			 return true;
		 }
		 else return false;
	 }

	 public boolean isSuspended()
	 {
		 //return SubscriptionStatus.isActive(mStatus);
		 if (mStatus==SubscriptionStatus.SUSPENDED) {
			 return true;
		 }
		 else return false;
	 }
	 
	 //MQC6080
	 public boolean isBeingProvisioned()	{
		 if (mStatus==SubscriptionStatus.BEING_PROVISIONED)
			 return true;
		 return false;
	 }
	 
	 //MQC6080
	 public boolean isReserved()	{
		 if (mStatus==SubscriptionStatus.RESERVED) 
			 return true;
		 return false;
	 }
	 

	 public String getLinkedPtID()//  @ER7
	 {
		 return mLinkedPtID;
	 }

	 public boolean isPreOrdered()//  @ER7
	 {
		 return mPreOrdered;
	 }

	 public boolean isRecurring()
	 {
		 boolean rv = false;
		 if (mPack!=null && mPack.getPricePoint()!=null ) {
			 rv = ChargingMethod.isRecurring(mPack.getPricePoint().getChargingMethod());
		 }	else	{
			 logger.warn("can't determine whether package is recurring");
		 }
		 return rv;
	 }

	 public String getCsrId()
	 {
		 return mCsrId;
	 }

	 public int getContentPaymentType()
	 {
		 return mContentPaymentType;
	 }

	 public int getPurchaseDeviceType()
	 {
		 int rv = 0;
		 if (mDeviceType==-1) {
			 if (this.getRatingAttributes()!=null) rv = this.getRatingAttributes().getDeviceType();
		 } else rv = mDeviceType;
		 return rv;
	 }

	 // return the % discount
	 public double getNextCyclePercentDiscount()
	 {
		 double discount = 1 - (1-mNextCycleDiscount);
		 return discount * 100;
	 }

	 /**
	  * @return
	  */
	 public double getNextPaymentAmount()
	 {
		 double rv = -1;
		 //double rv = 0.0;
		 double rate;

		 //CR1430 - changed to call getFutureRate()
		 if (getPricePoint()!=null) 
			 rate = getPricePoint().getFutureRate(mExpiryDate);
		 else 
			 return rv;

		 double discount = mNextCycleDiscount;

		 if (discount==0.0) {
			 return rate;
		 }
		 else {
			 //rv = rate * discount;
			 //PVCS 3580 - calculate the get payment amount
			 rv = rate - (discount/100 * rate);
		 }
		 return rv;
	 }

	 @Override
	public String toString()
	 {
		 StringBuffer buf = new StringBuffer("{");
		 buf.append("SubscriptionId=").append(mSubscriptionId);
		 
		 buf.append("\npurchasedate=").append(mPurchaseDate);

		 buf.append("\nstartdate=").append(mStartDate);

		 buf.append("\nexpirydate=").append(mExpiryDate);

		 buf.append("\nmRenewalDate=").append(mRenewalDate);

		 buf.append("\nmStatus=").append(mStatus);

		 buf.append("type=" + mPaymentType + "\n");
		 //MQC 8385 - PackageId refactor
		 if ((mPack!=null) && (mPack.getFullPackagePricepointId()!=null)) { 
			 buf.append("pack=" + mPack.getFullPackagePricepointId() + "\n");
		 }
		 else {
			 buf.append("pack= null");
		 }
			 
		 buf.append("\nisRecurring=").append(isRecurring());

		 buf.append("\nnextCycleDiscount=").append(getNextCyclePercentDiscount());

		 buf.append("\nmHasExpired=").append(mHasExpired);

		 buf.append("\nnextPaymentAmount=").append(getNextPaymentAmount());
		 buf.append("\ndeviceType=").append(getPurchaseDeviceType());

		 buf.append("\ncsrId=").append(getCsrId());

		 buf.append("\ncontentPaymentType=").append(getContentPaymentType());
		 buf.append("\nisArchived=").append(isArchived());
		 buf.append("\nmServiceProvisioningTags.size=").append(mServiceProvisioningTags!=null?mServiceProvisioningTags.size():null);
			 
		 buf.append("\nmServiceStatuses.size=").append(mServiceStatuses!=null?mServiceStatuses.size():null);

		 if ( (getServiceIds()!=null) && (getServiceIds().length>0) ) {
			 for (String serviceId : getServiceIds()) {
				 buf.append("\nserviceId= ").append(serviceId);
				 buf.append(", service Prov Tag = " ).append( getServiceProvTag(serviceId));
			 }
		 }
		 else 
			 buf.append("\nserviceIds= null\n");
		buf.append("\nmMatchingAttributes =").append( mMatchingAttributes);

		 if (mBalances!=null) {
			 for (ResourceBalance mBalance : mBalances)
				buf.append("\nmBalance[").append(mBalance.getResource()).append("]=").append(mBalance.getBalance());
		 } else 
			 buf.append("\nmBalances = null\n");

		 if(m_DRMObject != null)
		 {
			 buf.append("\nDRM Id = " +m_DRMObject.getDRMId());
		 }
		 buf.append("\nmInteractiveUsageFlage=" ).append( mInteractiveUsageFlage );
		 buf.append("\nmMinimuSubscriptionDate" ).append( mFinalMinSubscriptionEndDate );
		 //REMEDY 5685
		 buf.append("\nmHasHistoricPricepoint" ).append( mHasHistoricPricepoint );
		 //RBT Enhancement CR - Start
		 buf.append("\nmParentPackageId" ).append( mParentPackageId );
		 //RBT Enhancement CR - End
		//CR-0095 RBT START
		 buf.append("\nextensionPeriod").append(extensionPeriod);
		//CR-0095 RBT END
		 //MQC 5126
		 buf.append("\nmPreviousStatus").append(mPreviousStatus);
		//CR-1209_1 - flag to indicate for parent / child scenarios that the parent was purchased together with the child
		 buf.append("\nmParentAlsoPurchased").append(mParentAlsoPurchased);
		//MQC6014 - flag to indicate if subscription has had a successful provision on usage
		 buf.append("\nmProvisionOnUsageSuccess").append(mProvisionOnUsageSuccess);
		 //CR 1209 add package class
		 buf.append("\nmPackageClass").append(mPackageClass);
		 buf.append("\nlastPeriodStartDate").append(lastPeriodStartDate);
		 buf.append("\nlastUsageTransactionIdForSameContent").append(lastUsageTransactionIdForSameContent);
		 //MQC 6368
		 buf.append("\npaymentTransactionId").append(paymentTransactionId);
		 //CR 1970 - Mobile Protect additions
		 buf.append("\npartnerId").append(mPartnerId);
		 //CR1981 Misano changes, account SPID when the subscription was bought
		 buf.append("\nspId").append(spId);
		 //CR 2080 - SMS Notification additions
		 buf.append("\nmerchantName").append(mMerchantName);
		 //CR 2108 - Flexible Subs
		 buf.append("\nrenewalPreRate").append(renewalPreRate);
		 buf.append("\npurchasedServices: ").append(purchasedServices);
		//CR CTB-1 Advanced Linked Pricepoint
		 buf.append("\nrenewalCounterToLinkedPricepoint: ").append(renewalCounterToLinkedPricepoint);
		 //MQC 9031
		 buf.append("\nrenewalPreRateGross").append(renewalPreRateGross);
		 buf.append("\n}");
		 return buf.toString();
	 }

	 public boolean isDefault()
	 {
		 return mIsDefault;
	 }


	 /**
        Returns an array of Strings. The array contains the service ids that the provisioning tag is set for.
	  */
	 public String[] getServiceIds()
	 {
		 return mServiceProvisioningTags.keySet().toArray(new String[mServiceProvisioningTags.size()]);
	 }

	 /**
        Gets the provisioning tag for a service.
        @param serviceId  you want to check the provisioning tag of
        @return  provisioning tag
	  */
	 public String getServiceProvTag(String serviceId)
	 {
		 if (mServiceProvisioningTags != null && serviceId != null)
			 return mServiceProvisioningTags.get(serviceId);
		 else return null;
	 }

	 /**
        Gets the status for a service.  These are of the kind which go in the database - e.g. 
        BEING_PROVISIONED (200), ACTIVE (201), INACTIVE (211), DISABLED (212) etc
         If the service can't be found, returns -1.
        @param  serviceId you want to check the status of
        @return Service Status
	  */

	 public int getServiceStatus(String serviceId) {
		 int rv = -1;
		 if (mServiceStatuses != null && serviceId != null) {
			 Integer stat = mServiceStatuses.get(serviceId);
			 if (stat != null) {
				 rv = stat.intValue();
			 }
		 }

		 return rv;
	 }

	 /**
	  * @return
	  */
	 public ResourceBalance[] getResourceBalances() {
		 return mBalances;
	 }

	 public void setResourceBalances(ResourceBalance[] val)
	 {
		 mBalances = val;
	 }

	 /**
	  * @return
	  */
	 public RatingAttributes getRatingAttributes() {
		 return mMatchingAttributes;
	 }

	 /**
	  * @return
	  */
	 public Long getPaymentTransactionId() {
		 return paymentTransactionId;
	 }

	 /**
	  * @return
	  */
	 public boolean isRefundable() {
		 return isRefundable;
	 }

	 /**
	  * @return
	  */
	 public String getNonRefundDescription() {
		 return nonRefundDescription;
	 }

	 /**
		Gets the provisioning tag for a service.
	  */
	 public String getServiceNonRefundDescription(String serviceId)
	 {
		 if (mServiceNonRefundDescription != null && serviceId != null)
			 return mServiceNonRefundDescription.get(serviceId);
		 else return null;
	 }


	 /**
	  * get the DRM object associated with the Subscription
	  * @return DRMObject
	  * @since ER 5.1
	  */
	 public DRMObject getDRMObject()
	 {
		 return m_DRMObject;
	 }


	 /**
	  *  Checks usage history with this subscription
	  *
	  *@return    mInteractiveUsageFlage
	  *@since ER 6
	  */
	 public int getInteractiveUsageFlag()
	 {
		 return mInteractiveUsageFlage;
	 }

	 /**
	  *  Checks usage history with this subscription
	  *
	  *@return    true if no service was used using this subscription
	  *@since ER 6
	  */
	 public boolean isFirstUsage()
	 {
		 return mInteractiveUsageFlage==INTERACTIVE_FIRST;
	 }

	 /**
	  *  Checks usage history with this subscription
	  *
	  *@return    true if a service was used using this subscription
	  *@since ER 6
	  */
	 public boolean isSubscriptionUsed()
	 {
		 return mInteractiveUsageFlage==INTERACTIVE_ALL;
	 }

	 public long getCurrentNoOfOccurences(){
		 return mCurrentNoOfOccurences;
	 }

	 /**** German Migration -sally  Use Case: Minimum Subscription Period ****/
	 public Date getFinalMinSubscriptionEndDate()      
	 {     
		 return mFinalMinSubscriptionEndDate ; 
	 }
	 public double getPenaltyCharge()      
	 {     
		 return mPenaltyCharge ;    
	 }   
	 public boolean isMinSubscriptionPeriodElapsed()      
	 {     
		 return mFinalMinSubscriptionEndDate.before(new Date()) ; 
	 }
	 //@hud RFRF faster implementation if needed
	 public boolean isMinSubscriptionPeriodElapsed(Date nowDate)      
	 {     
		 return mFinalMinSubscriptionEndDate.before(nowDate) ; 
	 }     
	 
	 /**** End German Migration -sally  Use Case: Minimum Subscription Period ****/

	 public  boolean isSuperPackage(){
		 return mSuperPackage;
	 }


	 public int getSuperCreditBalance()
	 {
		 return superCreditBalance;
	 }

	 public String getSuperCreditID()
	 {
		 return superCreditID;
	 }

	 //public HashMap getSuperCreditBalances()
	 public ResourceBalance[] getSuperCreditBalances()
	 {
		 return superCreditBalances;
	 }

	 public boolean isCreatedBySuperCredit()
	 {
		 boolean bSuperCredit = true;
		 if(superCreditBalance <=0 || superCreditID == null || superCreditID.trim().length() == 0 )
			 bSuperCredit = false;

		 return bSuperCredit;
	 }

	 public boolean isUnderGracePeriod() {
		 return mStatus == SubscriptionStatus.UNDER_GRACE_PERIOD;
	 }
	 public Date getBatchRetryDate() {
		 return mBatchRetryDate;
	 }
	 public void setBatchRetryDate(Date dt) {
		 mBatchRetryDate = dt;
	 }
	 
	 // not used
	 public Date getOverdueExpiryDate() {
		 return mOverdueExpiryDate;
	 }
	 public void setOverdueExpiryDate(Date dt) {
		 mOverdueExpiryDate = dt;
	 }
	 
	 
	//@hud STKHREQ36
	 public boolean hasValidMicroService() {
		 return mHasValidMicroService;
	 }
	 public void setHasValidMicroService(boolean bHas) {
		 mHasValidMicroService = bHas;
	 }
	 
	 //@hud STKHREQ242 36
	 public Date getLastExpiryDate() {
		 return mLastExpiryDate;
	 }
	 public void setLastExpiryDate(Date dt) {
		 mLastExpiryDate = dt;
	 }
	 
	 //@hud For internal use only
	 public List<MicroServiceStatus> getMicroServiceSubList() {
		 return mMicroServiceSubList;
	 }
	 public void setMicroServiceSubList(List<MicroServiceStatus> al) {
		 mMicroServiceSubList = al;
	 }

	 //REMEDY 5685
	  public boolean hasHasHistoricPricepoint()
	  {
		  return mHasHistoricPricepoint;
	  }
	  
	  //REMEDY 5871
	  public boolean isWasRecurringTrial() {
		  return mIsWasRecurringTrial;
	  }
	  
	  //RBT Enhancement CR - Start
	  public String getParentPackageID()
	  {
		  return this.mParentPackageId;
	  }
	  //RBT Enhancement CR - End

	  // CR-0095 RBT START 
	  public void setExtensionPeriod(int ExtensionPeriod){
		  extensionPeriod = ExtensionPeriod;
	  }
		
	  public int getExtensionPeriod(){
		  return extensionPeriod;
	  }
	  // CR-0095 RBT END 

	  /**
	   * This returns a list of Transactions. The transaction objects returned are not always fully populated.
	   */
	  public List<? extends Transaction> getTransactions() {
		  return transactions;
	  }

	  /**
	   * This passes an array of Transactions.
	   */
	  public void setTransactions(List<? extends Transaction> transactions) {
		  this.transactions = transactions;
	  }
	  
	  //MQC 5126
	  public int getPreviousStatus()
	  {
		  return this.mPreviousStatus;
	  }
	  
	  //CR-1209_1 - flag to indicate for parent / child scenarios that the parent was purchased together with the child
	  public boolean isParentAlsoPurchased()
	  {
		  return mParentAlsoPurchased;
	  }
	
	  public void setParentAlsoPurchased(boolean parentPurchased)
	  {
		  this.mParentAlsoPurchased = parentPurchased;
	  }
	  
	  //MQC6014 - flag to indicate if subscription has had a successful provision on usage
	  public void setIsProvisionOnUsageSuccess(boolean isProvisionOnUsageSuccess) {
			this.mProvisionOnUsageSuccess = isProvisionOnUsageSuccess;
		}
		
	  //MQC6014 - flag to indicate if subscription has had a successful provision on usage
	  public boolean isProvisionOnUsageSuccess() {
		  return mProvisionOnUsageSuccess;
	  }
	  
	  //CR 1209 add package class
	  public String getPackageClass()
	  {
		  return mPackageClass;
	  }
	
	
	  public void setPackageClass(String packageClass) 
	  {
    	mPackageClass = packageClass;
	  }
	 
	  // CR - Enhanced Bundle Reporting (Feb 2010)
	  public Date getLastPeriodStartDate()
	  {
		  return this.lastPeriodStartDate;
	  }

	  public void setLastPeriodStartDate(Date lastPeriodStartDate)
	  {
		  this.lastPeriodStartDate = lastPeriodStartDate;
	  }
	  // Enhanced Bundle Reporting - ends
	  
	//CR1564-start-Utctimezone for diff region in country
	public Date getMLocalEndDate() {
		return mLocalEndDate;
	}

	//public void setMLocalEndDate(Date localEndDate) {
	//	mLocalEndDate = localEndDate;
	//}

	public Date getMLocalStartDate() {
		return mLocalStartDate;
	}

	//public void setMLocalStartDate(Date localStartDate) {
	//	mLocalStartDate = localStartDate;
	//}
	public Calendar getMLocalEndDateCal() {
		return mLocalEndDateCal;
	}

	public void setMLocalEndDate(Calendar localEndDate) {
		mLocalEndDateCal = localEndDate;
		mLocalEndDate = localEndDate.getTime();
	}

	public Calendar getMLocalStartDateCal() {
		return mLocalStartDateCal;
	}

	public void setMLocalStartDate(Calendar localStartDate) {
		mLocalStartDateCal = localStartDate;
		mLocalStartDate = localStartDate.getTime();
	}
   //CR1564-end
	
	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	public long getLastUsageTransactionIdForSameContent() {
		return lastUsageTransactionIdForSameContent;
	}

	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	public void setLastUsageTransactionIdForSameContent(long lastTransId) {
		lastUsageTransactionIdForSameContent = lastTransId;
	}
	
	//CR 1970 - Mobile Protect additions
	public String getPartnerId()
	{
		return mPartnerId;
	}
	
	public void setPartnerId(String partnerId) 
	{
		mPartnerId = partnerId;
	}	

	//CR1981 Misano changes, account SPID when the subscription was bought
	public String getSpId()  
    {  
    	return spId;  
    }
  
    public void setSpId(String spId)  
    {    
    	this.spId = spId;	
    }
    
    //moved here for Google thingy
    public String getMsisdn() {
  	  return this.msisdn;
    }
    
    public void setMsisdn(String newMsisdn)	{
    	this.msisdn=newMsisdn;
    }

	public String getExternalSubId() {
		return externalSubId;
	}

	public void setExternalSubId(String externalSubId) {
		this.externalSubId = externalSubId;
	}
	
	//CR 2080 - SMS Notification additions
	public String getMerchantName()
	{
		return mMerchantName;
	}
	
	public void setMerchantName(String merchantName) 
	{
		mMerchantName = merchantName;
	}
	
	//CR 2108 Start - Only getter here.
	//Setter is in ERSubscription
	
	public double getRenewalPreRate() {
		return renewalPreRate;
	}

	public double getPartnerTaxRate() {
		return partnerTaxRate;
	}
	//CR 2108 End	
	
    @Override
	public boolean equals(Object obj)	{
    	if (obj instanceof Subscription){
    		Subscription sub = (Subscription)obj;
    		return sub.getSubscriptionIdLong() == getSubscriptionIdLong();
    	}
    	return false;
    }	
    
    @Override
	public int hashCode()	{
    	return new Long(getSubscriptionIdLong()).hashCode();
    }
    
    
    

	/**
	 * @return
	 */
	public PurchasedService getPurchasedService(String serviceId) {
		PurchasedService serv = null;
		if (serviceId != null && purchasedServices.size()>0)
		  serv = purchasedServices.get(serviceId);
		return serv;
	}
    
    //MQC8202
	public void setIsRefundable(boolean refundableFlag) {
		isRefundable = refundableFlag ;
	}
	
	public void setPackage(CatalogPackage val)
    {
        mPack = val;
        if (val.getPricePoint()!=null)
        	pricePoint=val.getPricePoint();	//TODO MQC8385 remove this line, eventually
    }
	
	public void setRatingAttributes(RatingAttributes val)
	{
		this.mMatchingAttributes = val;
	}
	
    public void setStatus(int val)
    {
        mStatus = val;
    }
    
    public void setSuperPackage(boolean flag)
    {
          this.mSuperPackage = flag;
    }
    
	public void setHasExpired(boolean val) {
		mHasExpired = val;
	}
	
	/**
	 * 
	 * @deprecated use {@link #setSubscriptionIdLong} instead
	 */
	@Deprecated
	public void setSubscriptionId(String val) {
	    mSubscriptionId = val;
		if (mSubscriptionIdLong<0 && StringUtils.isNotBlank(val))
	    mSubscriptionIdLong=Long.parseLong(val);
	}
	
	public void setCurrentNoOfOccurences(long mCurrentNoOfOccurences) {
		  this.mCurrentNoOfOccurences = mCurrentNoOfOccurences ;
	}

	public void setExpiryDate(Date val) {
	    mExpiryDate = val;
	}

	public void setPreOrdered(boolean flag) {
	 	this.mPreOrdered = flag;
	 }

	public void setFinalMinSubscriptionEndDate(Date date) {
		this.mFinalMinSubscriptionEndDate = date;
	}
	
	public void setPurchaseDate(Date val) {
	    mPurchaseDate = val;
	}

	public void setStartDate(Date val) {
	    mStartDate = val;
	}
	
	public void setSuperCreditID(String superCreditID) {
	    this.superCreditID = superCreditID;
	}
	
	public void setSuperCreditBalances(ResourceBalance[] superCreditBalances) {
	    this.superCreditBalances = superCreditBalances;
	}
	
	public void setDRMObject(DRMObject drmobject) {
	    m_DRMObject = drmobject;
	}
	
	public void setPenaltyCharge(double penaltyCharge) {
		this.mPenaltyCharge = penaltyCharge;
	}
	
	public void setNextCyclePercentValue(double val) {
	    mNextCycleDiscount = val;
	}
	
    //MQC8202 - start
    public void setServiceProvTag(String serviceId, String provTag)
    {
		PurchasedService ps = null;
        if (serviceId!=null) {
        	if (purchasedServices.containsKey(serviceId)) {
        		ps = purchasedServices.get(serviceId);
        		ps.setProvisioningTag(provTag);
        	} else {
        		//create the object
        		ps = new PurchasedService();
        		ps.setProvisioningTag(provTag);
        		ps.setServiceId(serviceId);
				purchasedServices.put(serviceId,ps);
        	};
        	//for legacy support ...
            mServiceProvisioningTags.put(serviceId, provTag);
        }
    }
    
    /**
     *MQC 8393
     * @return boolean
     */
    public boolean isWasRecurringPromoCode() {
    	return mIsWasRecurringPromoCode;
    }
  
    /**
     * MQC 8393
     * @param isWasRecurringPromoCode
     */
    public void setIsWasRecurringPromoCode(boolean isWasRecurringPromoCode) {
    	this.mIsWasRecurringPromoCode = isWasRecurringPromoCode;
    }
    
    //MQC 8385 Start - Refactor PricepointId
    
//    /**
//     * This method was originally in CatalogPackage object and moved.
//     * 
//     * Concatenates the fullPackagePricepointId from simplePackageId and the pricepointId
//     * The result will look like this BP001__X__package:BP001_TAX_3_4_10010_999_999_*_*_*_false_false
//     * 
//     * Returns a null if there is no simplePackageId or Pricepoint associated with the Subscription
//     * 
//     * @return
//     */
//    public String getFullPackagePricepointId() {
//		
//    	String fullId = null;
//		
//		if (mPack != null && mPack.getSimplePackageId() != null 
//				&& getPricePoint() !=null && getPricePoint().getId()!=null) {
//			
//			fullId = mPack.getSimplePackageId() + PACKAGE_DELIMITER + getPricePoint().getId();
//		}
//		
//		logger.debug("getFullPackagePricepointId returning: " + fullId);
//
//		return fullId;
//    	
//	}
//    
//    /**
//     * Represents the currently selected pricepoint for the subscription i.e. the one 
//     * used for the purchase / renewal
//     * 
//     * This is worked out when through the rating attributes from the ER_Options table
//     * 
//     * @param pricepoint
//     */
//    public void setPricePoint(PricePoint pricepoint) {
//		this.pricepoint = pricepoint;
//	}
//    
//    /**
//     * Returns the currently selected pricepoint for the subscription
//     * @return
//     */
//    public PricePoint getPricePoint() {
//		return pricepoint;
//	}
    //MQC 8385 End - Refactor PricepointId

    
    /**
     * CR 2245 upsell discount prorate, the last payment transaction (purchase, renew or recurr) for the subscription
     * @return last payment transaction
     */
	public Transaction getLastPaymentTransaction() {
		return lastPaymentTransaction;
	}
	
	/**
	 * CR2255 Phase2 - add partner information
	 * @return b2bPartner
	 */
	public B2BPartner getB2BPartner() {
		return b2bPartner;
	}
	
	/**
	 * CR2255 Phase2 - add partner information
	 * @param partner, set the B2BPartner
	 */
	public void setB2BPartner(B2BPartner partner) {
		this.b2bPartner = partner;
	}

	/**
	 * should be equivalent to getPackage().getPricePoint()<br/>
	 * added for MQC8385 since the pricepoint should be associated with the sub, not the package
	 * @return
	 */
	public PricePoint getPricePoint() {
		if (pricePoint==null && getPackage()!=null)
			pricePoint=getPackage().getPricePoint();	//TODO MQC8385 remove this line, eventually
		return pricePoint;

	}

	/**
	 * use this in preference to getPackage().setPricePoint()<br/>
	 * added for MQC8385 since the pricepoint should be associated with the sub, not the package
	 * @param pricePoint
	 */
	public void setPricePoint(PricePoint pricePoint) {
		this.pricePoint = pricePoint;
		if (getPackage()!=null)
			getPackage().setPricePoint(pricePoint);	//TODO MQC8385 remove this line, eventually
	}

	/**
	 * CR CTB-1 Advanced Linked Pricepoint
	 * @return the renewalCounterToLinkedPricepoint
	 */
	public int getRenewalCounterToLinkedPricepoint() {
		return this.renewalCounterToLinkedPricepoint;
	}
	
	/**
	 * CR CTB-1 Advanced Linked Pricepoint
	 * @param noOfRenewals the renewalCounterToLinkedPricepoint to set
	 */
	public void setRenewalCounterToLinkedPricepoint(int noOfRenewals) {
		this.renewalCounterToLinkedPricepoint = noOfRenewals;
	}
	
	/**
	 * MQC 9031
	 * @return the renewalPreRateGross
	 */
	public boolean isRenewalPreRateGross() {
		return this.renewalPreRateGross;
	}
	
	/**
	 * @param renPreRateGross the renewalPreRateGross to set
	 */
	public void setRenewalPreRateGross(boolean renPreRateGross) {
		this.renewalPreRateGross = renPreRateGross;
	}
	
}
