package com.vizzavi.ecommerce.business.selfcare;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.common.ChargingMethod;
import com.vizzavi.ecommerce.business.common.RatingAttributes;
import com.vizzavi.ecommerce.business.selfcare.BasicAccount;
import com.vodafone.global.er.business.selfcare.MicroServiceStatus;
import com.vizzavi.ecommerce.business.selfcare.PurchasedService;
import com.vizzavi.ecommerce.business.selfcare.ModifyTxn;
import com.vizzavi.ecommerce.business.selfcare.PaymentTxn;
import com.vizzavi.ecommerce.business.selfcare.RefundTxn;
import com.vizzavi.ecommerce.common.ErCountry;
//import com.vodafone.global.er.common.BizLogger;
import com.vodafone.global.er.partner.B2BPartner;

/**
 * @author trushantpatel
 *
 */
@Entity
@EntityListeners(SubscriptionEntityListener.class)
@Table(name="ER_SUBSCRIPTIONS")
public class Subscription implements Serializable	{

	@Transient
	protected Map<String, PurchasedService> purchasedServices = new HashMap<>();

	protected static final Logger logger = LoggerFactory.getLogger(Subscription.class);
	private static final long serialVersionUID = 589407364300363869L;
	
	
	@Transient
	protected CatalogPackage mPack = null;
	//MQC8385 - we should store the pricepoint at sub level so we don't have to 
	//store it in the package
	@Transient
	protected PricePoint pricePoint=null;
	protected Date mPurchaseDate = null;
	@Transient
	protected Date mExpiryDate = null;
	protected Date mStartDate = null;
	protected Date mRenewalDate = null;

	protected boolean mPromotional = false; 
	
	protected Date mPromotionalExpiryDate = null; 
	protected int mStatus;

	protected boolean mHasValidMicroService = false;
	@Transient
	protected List<MicroServiceStatus> mMicroServiceSubList = null;
	@Transient
	protected Date mOverdueExpiryDate	= null;	// may not be used
	
	protected Date mBatchRetryDate = null;
	protected Date mLastExpiryDate = null;

	protected long mSubscriptionIdLong = -1;

	@Transient
	public int mPaymentType;
	@Transient
	public int mContentPaymentType;
	@Transient
	protected boolean  mIsDefault;
	protected boolean mPreOrdered =  false; //  @ER7
	protected String  mLinkedPtID = null; // @ER7

	protected double mNextCycleDiscount = 0d;
	@Transient
	public int mDeviceType = -1;
	// customer care data
	@Transient
	protected String mCsrId = null;
	@Transient
	protected boolean mIsArchived = false;

	@Transient
	protected Map<String, String> mServiceProvisioningTags = new HashMap<>();
	@Transient
	protected Map<String, Integer> mServiceStatuses = new HashMap<>();

	@Transient
	protected ResourceBalance[] mBalances = null;
	@Transient
	protected RatingAttributes mMatchingAttributes = null;


	//an associated payment transaction that has paid for the subscription
	@Transient
	protected Long paymentTransactionId;

	@Transient
	protected boolean mHasExpired = false;


	protected String nonRefundDescription = null;
	protected boolean isRefundable = true;
	@Transient
	protected Map<String, String> mServiceNonRefundDescription = new HashMap<>();


	public static final int INTERACTIVE_NONE= 0;
	public static final int INTERACTIVE_FIRST= 1;
	public static final int INTERACTIVE_ALL= 2;
	
	@Transient
	protected int mInteractiveUsageFlage=INTERACTIVE_NONE;

	protected long mCurrentNoOfOccurences = -1; // the default  recurring subscription.

	/**** German Migration -sally  Use Case: Minimum Subscription Period ****/
	@Transient
	protected Date mFinalMinSubscriptionEndDate=new Date();
	protected double mPenaltyCharge=0; 
	/**** End German Migration -sally  Use Case: Minimum Subscription Period ****/
	protected boolean mSuperPackage;  // will hold 1 if the package is super package


	//REMEDY 5685 - add flag to indicate that on time of initial purchase a duplicate historic pricepoint exists
	protected boolean mHasHistoricPricepoint = false;

	//REMEDY 5871 - add flag to indicate whether this is / was a recurring TRIAL subscription
	@Transient
	protected boolean mIsWasRecurringTrial = false;

	//RBT Enhancement CR - Start
	//@Transient
	protected String mParentPackageId = null;
	//RBT Enhancement CR - End

	// CR-0095 RBT START 
	@Transient
	protected int extensionPeriod;
	// CR-0095 RBT END 

	//MQC 5126
	public int mPreviousStatus = 0;

	//CR-1209_1 - flag to indicate for parent / child scenarios that the parent was purchased together with the child
	@Transient
	protected boolean mParentAlsoPurchased = false;

	//MQC6014 - flag to indicate if subscription has had a successful provision on usage
	@Transient
	protected boolean mProvisionOnUsageSuccess = false;

	//CR 1209 add package class
	protected String mPackageClass = null;
	
	 //    @OneToMany( mappedBy="subscription",targetEntity=Transaction.class,     fetch=FetchType.LAZY)
	protected List<? extends Transaction> transactions = new ArrayList<>();

	protected List<PaymentTxn> paymentTxns = new ArrayList<>();

	protected List<ModifyTxn> modifyTxns = new ArrayList<>();

	// CR - Enhanced Bundle Reporting (Feb 2010)
	@Transient
	protected Date lastPeriodStartDate = null;
	//CR1564-Utctimezone for diff region in country
	@Transient
	protected Date mLocalStartDate = null;
	@Transient
	protected Date mLocalEndDate = null;
	//CR1564-Utctimezone for diff region in country
	@Transient
	protected Calendar mLocalStartDateCal = null;
	@Transient
	protected Calendar mLocalEndDateCal = null;

	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	@Transient
	protected long lastUsageTransactionIdForSameContent = -1;


	//CR 1970 - Mobile Protect additions
	protected String mPartnerId = null; 	//Partner ID of the subscription

	//CR1981 Misano changes, account SPID when the subscription was bought
	@Transient
	protected String spId = null;

	@Transient
	protected String msisdn;
	/** introduced for CR 2082 - Google */
	protected String externalSubId;

	//CR 2080 - SMS Notification additions
	protected String mMerchantName = null; 	//Merchant Name of the subscription

	//CR 2108
	protected double renewalPreRate = -1d;
	//If provided then this will be the tax rate applied and NOT what is in the priceplan
	protected double partnerTaxRate = -1d;

	//MQC 8393 - add flag to indicate whether this is a recurring Promo Code subscription, this can be a TRIAL or any other promo code
	@Transient
	protected boolean mIsWasRecurringPromoCode = false;


	//MQC 8385 - PackageId refactor
	//protected PricePoint pricepoint;

	public final static String PACKAGE_DELIMITER = "__X__";

	//CR 2245 upsell discount prorate, the last payment transaction (purchase, renew or recurr) for the subscription, this is only set if specified on the subscription filter
	@Transient
	protected Transaction lastPaymentTransaction = null;

	//CR2255 Phase2 - add partner information
	//@Transient
	protected B2BPartner b2bPartner = null;

	/**
	 * CR CTB-1 Advanced Linked Pricepoint - a counter of the number of renewals of a pricepoint which has a linked pricepoint
	 */
	protected int renewalCounterToLinkedPricepoint = -1;

	/**
	 * MQC9013 - pre rate price as gross to also be applied when renewal pre rate is set
	 */
	protected boolean renewalPreRateGross = false;

	/**
	 * JIRAET65 - the last successful monetary payment amount for this subscription
	 */
	@Transient
	protected double lastPaymentTransactionAmount = -1;

	/**
	 * JIRAET65 - the last to last successful monetary payment amount for this subscription
	 */
	@Transient
	protected double lastToLastPaymentTransactionAmount = -1;

	/**
	 * JIRAET65 -  whether this subscription is being renewed to a different pricepoint i.e. for TRIAL, Advanced Link Pricepoint renewals
	 */
	@Transient
	protected boolean pricePointChangeOnRenewal = false;

	/**
	 * JIRAET96 - Add the old inactivated subscription id, if this subscription is created as part of the upsell flow
	 */
	@Transient
	protected long oldSubscriptionId = -1;

	/**
	 * JIRAET96 - Add the old msisdn, if this subscription is part of the change msisdn flow 
	 */
	@Transient
	protected String oldMsisdn;
	
	protected ErCountry country;
	
	//Jira ET391 starts here
	
	protected String extIdentifier1;
	protected String extIdentifier2;
	protected String extIdentifier3;
	
	protected PaymentTxn lastPackagePaymentTransaction;
	
  	@Column(name="EXT_IDENTIFIER_1")
 	public String getExtIdentifier1() {
		return extIdentifier1;
	}

	public void setExtIdentifier1(String extIdentifier1) {
		this.extIdentifier1 = extIdentifier1;
	}
	
	@Column(name="EXT_IDENTIFIER_2")
 	public String getExtIdentifier2() {
		return extIdentifier2;
	}

	public void setExtIdentifier2(String extIdentifier2) {
		this.extIdentifier2 = extIdentifier2;
	}
	
	@Column(name="EXT_IDENTIFIER_3")
 	public String getExtIdentifier3() {
		return extIdentifier3;
	}

	public void setExtIdentifier3(String extIdentifier3) {
		this.extIdentifier3 = extIdentifier3;
	}
	
	//Jira ET391 ends here


	 
 	//JIra ET245 starts here
	  	
 	protected String packageId;
 	
 	@Column(name="package_id")
 	public String getPackageId() {
 		return packageId;
 	}
 
 	public void setPackageId(String packageId) {
 		this.packageId = packageId;
 	}
 	
 	protected Options options;
 	
 	@OneToOne(fetch = FetchType.LAZY, mappedBy = "subscription", cascade = CascadeType.ALL)
 	public Options getOptions() {
 		return options;
 	}
 
 	public void setOptions(Options opts) {
 		this.options = opts; 		
 	}
 
 	//JIra ET245 ends here
	
	/**
	 * JIRAET124 - the number of future renewals for the subscription pricepoint, this is relevant for the Advanced Link TRIAL pricepoints use case where the number of renewals
	 *	can be specified before renewing to the next pricepoint and for the fixed number of occurrences uses case.
	 *	default value is -1 and a value of -2 represents infinite number of future potential renewals, such as for a normal recurring pricepoint or the final pricepoint in the normal TRIAL
	 *	or Advanced Link TRIAL pricepoints use cases
	 */
	public static final int NO_FUTURE_RENEWALS_DEFAULT = -1;
	public static final int NO_FUTURE_RENEWALS_INFINITE = -2;
	
	@Transient
	protected int noFuturePricePointRenewals = NO_FUTURE_RENEWALS_DEFAULT;

	
	protected BasicAccount account;
	
	
	/**
	 * the account associated with the subscription.  <b>Only works in a JPA context</b>
	 * @return
	 */
	@ManyToOne(optional=false,	targetEntity=BasicAccount.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)	
	@JoinColumn(name="account_obj_id")
	public BasicAccount getAccount() {
		return account;
	}

	protected void setAccount(BasicAccount account) {
		this.account = account;
	}
	
	/**end ER7 av*/
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

		this.mPurchaseDate = sub.mPurchaseDate;
		this.mExpiryDate = sub.mExpiryDate;
		this.mStartDate = sub.mStartDate;
		this.mRenewalDate = sub.mRenewalDate;
		this.mStatus = sub.mStatus;

		//this.mSubscriptionId = sub.mSubscriptionId;
		mSubscriptionIdLong = sub.getSubscriptionIdLong();

		this.mPaymentType = sub.mPaymentType;
		this.mContentPaymentType = sub.mContentPaymentType;
		this.mIsDefault = sub.mIsDefault;
		this.mNextCycleDiscount = sub.mNextCycleDiscount;
		this.mDeviceType = sub.mDeviceType;
		this.mCsrId = sub.mCsrId;
		this.mIsArchived = sub.mIsArchived;
		this.mServiceProvisioningTags = new HashMap<>(sub.mServiceProvisioningTags);
		this.mServiceStatuses = new HashMap<>(sub.mServiceStatuses);
		if (sub.mMatchingAttributes!=null) this.mMatchingAttributes = new RatingAttributes (sub.mMatchingAttributes);
		if (sub.mBalances!=null) {
			mBalances = new ResourceBalance [sub.mBalances.length];
			for (int i = 0; i<sub.mBalances.length;i++)
				this.mBalances[i] = new ResourceBalance (sub.mBalances[i]);
		}
		this.mHasExpired = sub.mHasExpired;
		this.mServiceNonRefundDescription = new HashMap<>(sub.mServiceNonRefundDescription);
		this.isRefundable = sub.isRefundable;
		this.mPreOrdered = sub.isPreOrdered(); //  @ER7 
		this.mLinkedPtID = sub.getLinkedPtID(); // @ER7
		this.nonRefundDescription = sub.nonRefundDescription;
		//		this.m_DRMObject = sub.m_DRMObject;
		this.mInteractiveUsageFlage = sub.mInteractiveUsageFlage;
		this.mPromotional = sub.mPromotional; 
		this.mPromotionalExpiryDate = sub.mPromotionalExpiryDate; 
		//		this.mBatchProcessor = sub.mBatchProcessor; 
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
		//		this.superCreditBalances = sub.getSuperCreditBalances();

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

		//JIRAET65 - the last successful monetary payment amount for this subscription
		this.lastPaymentTransactionAmount = sub.getLastPaymentTransactionAmount();
		this.lastToLastPaymentTransactionAmount = sub.getLastToLastPaymentTransactionAmount();

		//JIRAET65 -  whether this subscription is being renewed to a different pricepoint i.e. for TRIAL, Advanced Link Pricepoint renewals
		this.pricePointChangeOnRenewal = sub.isPricePointChangeOnRenewal();

		//JIRAET96 - Add the old msisdn, if this subscription is part of the change msisdn flow 
		this.oldMsisdn = sub.getOldMsisdn();
		//JIRAET96 - Add the old inactivated subscription id, if this subscription is created as part of the upsell flow
		this.oldSubscriptionId = sub.getOldSubscriptionId();

		//JIRAET124 - the number of future renewals for the pricepoint
		this.noFuturePricePointRenewals = sub.getFuturePricePointRenewals();
		//ET245
		this.packageId = sub.getPackageId();
		//Jira ET391 starts here
		this.extIdentifier1 = sub.getExtIdentifier1();
		this.extIdentifier2 = sub.getExtIdentifier2();
		this.extIdentifier3 = sub.getExtIdentifier3();
		//Jira ET391 ends here
	}


	/**
	 * Check to see if package has expired
	 * @return a boolean; true if package has expired
	 * @since ER 5.1
	 */
	@Transient
	public boolean hasExpired()
	{
		return mHasExpired;
	}

	@Transient
	public boolean isArchived()
	{
		return mIsArchived;
	}

	@Column(name="time_stamp")
	public Date getPurchaseDate()	{
		return mPurchaseDate;
	}

	@Column(name="start_date")
	public Date getStartDate()	{
		return mStartDate;
	}

	@Column(name="end_date")
	public Date getExpiryDate()	{
		return mExpiryDate;
	}

	
	@Column(name="promotional", columnDefinition="NUMBER")
	public boolean isPromotional()	{ 
		return mPromotional; 
	} 

	protected void setPromotional(Boolean isPromotional){
		if(isPromotional!=null)
			this.mPromotional= isPromotional.booleanValue();
	}	


	@Column(name="PROMOTIONAL_EXPIRY_DATE")
	public Date getPromotionalExpiryDate() 	{ 
		return mPromotionalExpiryDate; 
	} 

	protected void setPromotionalExpiryDate(Date promotionalExpiryDate){
		this.mPromotionalExpiryDate = promotionalExpiryDate;
	}
	
	@Transient
	//@Column(name="end_date")
    public Date getRenewalDate()	{
		return mRenewalDate;
	}

	public void setRenewalDate(Date renewalDate){
		this.mRenewalDate = renewalDate;
	}
	
	@Transient
	public CatalogPackage getPackage()	{
		return mPack;
	}

	@Transient
	public int getPaymentType()	{
		return mPaymentType;
	}

	/**
	 *  @deprecated use {@link #getSubscriptionIdLong} instead
	 * @return
	 */
	@Deprecated
	@Transient
	public String getSubscriptionId()	{
		//Commented out this logic since in JPA NewAccountBean flow because of this conditional check we were getting NPE.
		if (mSubscriptionIdLong > 0)
			return String.valueOf(mSubscriptionIdLong);
		else 
			return null;
	}

	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="subseq")
	@SequenceGenerator(name="subseq", sequenceName="ER_SUBSCRIPTIONS_SEQ", allocationSize=1/*00, initialValue = 10*/)
	@Id
	@Column(name="SUBSCRIPTION_OBJ_ID")
	public long getSubscriptionIdLong() {
		return mSubscriptionIdLong;
	}

	public void setSubscriptionIdLong(long id) {
		if (id>0)
			mSubscriptionIdLong = id;
	}

	@Column(name="status_obj_id")
	public int getStatus()	 {
		return (mStatus == SubscriptionStatus.ACTIVE || mHasValidMicroService) ?
				SubscriptionStatus.ACTIVE : mStatus;
	}

	@Transient
	public boolean isInactiveOrClosed()	{
		return (mStatus==SubscriptionStatus.INACTIVE || mStatus==SubscriptionStatus.CLOSE);
	}

	//MQC 9590 - return if subscription in failed status
	
	@Transient
	public boolean isFailed()	{
		return (mStatus>=SubscriptionStatus.FAILED_START_RANGE)&&(mStatus<SubscriptionStatus.FAILED_END_RANGE);
	}

	@Transient
	public boolean isActive()	 {
		if (mStatus==SubscriptionStatus.ACTIVE) {
			return true;
		}
		else return false;
	}

	@Transient
	public boolean isSuspended()	 {
		if (mStatus==SubscriptionStatus.SUSPENDED) {
			return true;
		}
		else return false;
	}

	//MQC6080
	@Transient
	public boolean isBeingProvisioned()	{
		return (mStatus==SubscriptionStatus.BEING_PROVISIONED);
	}

	//MQC6080
	@Transient
	public boolean isReserved()	{
		if (mStatus==SubscriptionStatus.RESERVED) 
			return true;
		return false;
	}


	
	@Column(name="LINKED_PRICE_POINT_ID")
    public String getLinkedPtID()	 {
		return mLinkedPtID;
	}

	protected void setLinkedPtID(String linkedPtId)	 {
		this.mLinkedPtID = linkedPtId;
	}
	
	@Column(name="PREORDER", columnDefinition="NUMBER")
	public boolean isPreOrdered()	 {
		return mPreOrdered;
	}

	@Transient
	public boolean isRecurring()	 {
		boolean rv = false;
		if (getPricePoint()!=null)
			return getPricePoint().isRecurring();
		logger.warn("pricepoint is null for subscription {}", mSubscriptionIdLong);

		if (mPack!=null && mPack.getPricePoint()!=null ) {
			rv = ChargingMethod.isRecurring(mPack.getPricePoint().getChargingMethod());
		}	else	{
			logger.warn("can't determine whether package is recurring");
		}
		return rv;
	}

	@Transient
	public String getCsrId()	 {
		return mCsrId;
	}

	@Transient
	public int getContentPaymentType()	 {
		return mContentPaymentType;
	}

	@Transient
	public int getPurchaseDeviceType()	 {
		int rv = 0;
		if (mDeviceType==-1) {
			if (this.getRatingAttributes()!=null) rv = this.getRatingAttributes().getDeviceType();
		} else rv = mDeviceType;
		return rv;
	}

	// return the % discount
	//
	@Transient
    public double getNextCyclePercentDiscount()	 {
		double discount = 1 - (1-mNextCycleDiscount);
		return discount * 100;
	}

	/**
	 * @deprecated use getNextPaymentAmount in {@link #com.vizzavi.ecommerce.business.selfcare.SelfcareApi} instead
	 * @return
	 */
	@Deprecated
	@Transient
	public double getNextPaymentAmount()	 {
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
	public String toString()	 {
		StringBuffer buf = new StringBuffer("{");
		buf.append("SubscriptionId=").append(mSubscriptionIdLong);

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

		//		 if(m_DRMObject != null)
		//		 {
		//			 buf.append("\nDRM Id = " +m_DRMObject.getDRMId());
		//		 }
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
		//ET245
		buf.append("\npackageId").append(packageId);
		buf.append("\nnonRefundDescription").append(nonRefundDescription);
		buf.append("\n}");
		
		
		return buf.toString();
	}

	@Transient
	public boolean isDefault()	 {
		return mIsDefault;
	}


	/**
        Returns an array of Strings. The array contains the service ids that the provisioning tag is set for.
	 */
	@Transient
	public String[] getServiceIds()	 {
		return mServiceProvisioningTags.keySet().toArray(new String[mServiceProvisioningTags.size()]);
	}

	/**
        Gets the provisioning tag for a service.
        @param serviceId  you want to check the provisioning tag of
        @return  provisioning tag
	 */
	@Transient
	public String getServiceProvTag(String serviceId)	 {
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
	@Transient
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
	@Transient
	public ResourceBalance[] getResourceBalances() {
		return mBalances;
	}

	public void setResourceBalances(ResourceBalance[] val)	 {
		mBalances = val;
	}

	
	@OneToMany( mappedBy="subscription",targetEntity=ResourceBalance.class,	fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public List<ResourceBalance> getResourceBalancesList() {
		if(mBalances != null )
			return Arrays.asList(mBalances);
		return null;
	}

	public void setResourceBalancesList(List<ResourceBalance> resourceBalancesList) {
		if(resourceBalancesList != null && !resourceBalancesList.isEmpty()){
			setResourceBalances(resourceBalancesList.toArray(new ResourceBalance[resourceBalancesList.size()]));
			this.mBalances = resourceBalancesList.toArray(new ResourceBalance[resourceBalancesList.size()]);
		}
	}


	/**
	 * @return
	 */
	@Transient
	public RatingAttributes getRatingAttributes() {
		return mMatchingAttributes;
	}

	/**
	 * @return
	 */
	@Transient
	public Long getPaymentTransactionId() {
		//TODO should use the list of PaymentTxns and find the first one by date
		return paymentTransactionId;
	}

	/**
	 * @return
	 */
	@Column(name="IS_REFUNDABLE", columnDefinition="NUMBER")
	public boolean isRefundable() {
		return isRefundable;
	}

	protected void setRefundable(Boolean refundableFlag) {
		if(refundableFlag!=null)
			isRefundable = refundableFlag.booleanValue() ;
	}
	/**
	 * @return
	 */
	@Column(name = "NON_REFUND_DESCRIPTION")
	public String getNonRefundDescription() {
		return nonRefundDescription;
	}
	
	protected void setNonRefundDescription(String nonRefundDescription) {
		this.nonRefundDescription = nonRefundDescription;
	}

	/**
		Gets the provisioning tag for a service.
	 */
	@Transient
	public String getServiceNonRefundDescription(String serviceId)	 {
		if (mServiceNonRefundDescription != null && serviceId != null)
			return mServiceNonRefundDescription.get(serviceId);
		else return null;
	}




	/**
	 *  Checks usage history with this subscription
	 *
	 *@return    mInteractiveUsageFlage
	 *@since ER 6
	 */
	@Transient
	public int getInteractiveUsageFlag()	 {
		return mInteractiveUsageFlage;
	}

	/**
	 *  Checks usage history with this subscription
	 *
	 *@return    true if no service was used using this subscription
	 *@since ER 6
	 */
	@Transient
	public boolean isFirstUsage()	 {
		return mInteractiveUsageFlage==INTERACTIVE_FIRST;
	}

	/**
	 *  Checks usage history with this subscription
	 *
	 *@return    true if a service was used using this subscription
	 *@since ER 6
	 */
	@Transient
	public boolean isSubscriptionUsed()	 {
		return mInteractiveUsageFlage==INTERACTIVE_ALL;
	}

	@Column(name="FIXED_NO_OF_OCCURENCES")
	public long getCurrentNoOfOccurences(){
		return mCurrentNoOfOccurences;
	}

	/**The date before which the customer will be liable to a penalty charge if they cancel*/
	@Column(name="FINAL_MIN_SUB_PAYMENT_DATE")
	public Date getFinalMinSubscriptionEndDate()      	 {     
		return mFinalMinSubscriptionEndDate ; 
	}
	
	@Column(name="PENALTY_CHARGE")
	public double getPenaltyCharge()    {     
		return mPenaltyCharge ;    
	}   
	
	@Transient
	public boolean isMinSubscriptionPeriodElapsed()    	 {     
		return mFinalMinSubscriptionEndDate.before(new Date()) ; 
	}

	@Transient
	public boolean isMinSubscriptionPeriodElapsed(Date nowDate)      
	{     
		return mFinalMinSubscriptionEndDate.before(nowDate) ; 
	}     

	/**** End German Migration -sally  Use Case: Minimum Subscription Period ****/

	@Column(name="SUPER_PACKAGE", columnDefinition="NUMBER")
	public  boolean isSuperPackage(){
		return mSuperPackage;
	}

	@Transient
	public boolean isUnderGracePeriod() {
		return mStatus == SubscriptionStatus.UNDER_GRACE_PERIOD;
	}
	
	@Column(name="BATCH_RETRY_DATE")
	public Date getBatchRetryDate() {
		return mBatchRetryDate;
	}
	public void setBatchRetryDate(Date dt) {
		mBatchRetryDate = dt;
	}

	@Transient
	public Date getOverdueExpiryDate() {
		return mOverdueExpiryDate;
	}
	public void setOverdueExpiryDate(Date dt) {
		mOverdueExpiryDate = dt;
	}


	public boolean hasValidMicroService() {
		return mHasValidMicroService;
	}
	
	public void setHasValidMicroService(boolean bHas) {
		mHasValidMicroService = bHas;
	}
	
	//these two methods just for hibernate
	
	@Column(name="HAS_VALID_MICROSERVICE")
	private String getHasValidMicroServiceAsString()	{
		return mHasValidMicroService? "Y": "N";
	}
	
	private void setHasValidMicroServiceAsString(String s)	{
		mHasValidMicroService = "Y".equalsIgnoreCase(s);
	}
	
	
	@Column(name="last_expiry_date")
	public Date getLastExpiryDate() {
		return mLastExpiryDate;
	}
	public void setLastExpiryDate(Date dt) {
		mLastExpiryDate = dt;
	}

	@Transient
	public List<MicroServiceStatus> getMicroServiceSubList() {
		return mMicroServiceSubList;
	}
	public void setMicroServiceSubList(List<MicroServiceStatus> al) {
		mMicroServiceSubList = al;
	}

	/**
	 * did the package have a historic pricepoint, at the time this subscription was purchased?  
	 * @return
	 */
	public boolean hasHasHistoricPricepoint()	  {
		return getHasHistoricPricepoint();
	}

	@Column(name="HAS_HISTORIC_PRICEPOINT")
	private boolean getHasHistoricPricepoint()	{
		return mHasHistoricPricepoint;
	}
	
	public void setHasHistoricPricepoint(Boolean mHasHistoricPricepoint){
		if(mHasHistoricPricepoint!=null){
			
			this.mHasHistoricPricepoint = mHasHistoricPricepoint;
		}
	}
	
	@Transient
	public boolean isWasRecurringTrial() {
		return mIsWasRecurringTrial;
	}


	@Column(name="PARENT_PACKAGE_ID")
	public String getParentPackageID()	  {
		return this.mParentPackageId;
	}
	//RBT Enhancement CR - End

	// MQC 9655
	public void setParentPackageID(String parentPackageId) {
		mParentPackageId = parentPackageId;
	}

	// CR-0095 RBT START 
	public void setExtensionPeriod(int ExtensionPeriod){
		extensionPeriod = ExtensionPeriod;
	}

	@Transient
	public int getExtensionPeriod(){
		return extensionPeriod;
	}
	// CR-0095 RBT END 

	/**
	 * This returns a list of Transactions. The transaction objects returned are not always fully populated.
	 */
	@Transient
	public List<? extends Transaction> getTransactions() {
		return transactions;
	}

	/** new, only for use with JPA DAO
	 * @since May 2015
	 * @return
	 */
	@Transient
	public List<RefundTxn> getRefundTransactions() {
	        List<RefundTxn> txns = new ArrayList<>();
	        if (paymentTxns==null || paymentTxns.isEmpty())
	                return txns;
	        for (PaymentTxn txn: paymentTxns)       {
	                txns.addAll(txn.getRefundTransactions());
	        }
	        return txns;
	}
	/**
	 * This passes an array of Transactions.
	 */
	public void setTransactions(List<Transaction> txns) {
		this.transactions = txns;
//		for (Transaction t: txns)       {
//			addTransaction(t);
//		}
	}

	//MQC 5126
	
	@Column(name="PREVIOUS_STATUS_OBJ_ID", nullable=true)
	public int getPreviousStatus()	{
		return this.mPreviousStatus;
	}

	protected void setPreviousStatus(Integer mPreviousStatus)	{
		if(mPreviousStatus != null)
			this.mPreviousStatus = mPreviousStatus;
	}
	
	//CR-1209_1 - flag to indicate for parent / child scenarios that the parent was purchased together with the child
	@Column(name="PARENT_ALSO_PURCHASED")
	public boolean isParentAlsoPurchased()	  {
		return mParentAlsoPurchased;
	}

	public void setParentAlsoPurchased(Boolean parentPurchased)	  {
		if (parentPurchased!=null)
			this.mParentAlsoPurchased = parentPurchased;
	}

	//MQC6014 - flag to indicate if subscription has had a successful provision on usage
	public void setIsProvisionOnUsageSuccess(boolean isProvisionOnUsageSuccess) {
		this.mProvisionOnUsageSuccess = isProvisionOnUsageSuccess;
	}

	//MQC6014 - flag to indicate if subscription has had a successful provision on usage
	@Column(name="PROVISION_ON_USAGE_SUCCESS")
	public boolean isProvisionOnUsageSuccess() {
		return mProvisionOnUsageSuccess;
	}

	//for hibernate only
	void setProvisionOnUsageSuccess(Boolean isProvisionOnUsageSuccess) {
		if (isProvisionOnUsageSuccess!=null)
			this.mProvisionOnUsageSuccess = isProvisionOnUsageSuccess;
	}
	
	//CR 1209 add package class
	@Column(name="package_class")
	public String getPackageClass()	  {
		return mPackageClass;
	}


	public void setPackageClass(String packageClass) 
	{
		mPackageClass = packageClass;
	}

	// CR - Enhanced Bundle Reporting (Feb 2010)
	@Column(name="LAST_PERIOD_START_DATE")
	public Date getLastPeriodStartDate()	  {
		return this.lastPeriodStartDate;
	}

	public void setLastPeriodStartDate(Date lastPeriodStartDate)
	{
		this.lastPeriodStartDate = lastPeriodStartDate;
	}
	// Enhanced Bundle Reporting - ends

	//CR1564-start-Utctimezone for diff region in country
	@Transient
	public Date getMLocalEndDate() {
		return mLocalEndDate;
	}


	protected void setMLocalEndDate(Date mLocalEndDate) {
		this.mLocalEndDate = mLocalEndDate;
	}

	
	@Transient
	public Date getMLocalStartDate() {
		return mLocalStartDate;
	}


	@Transient
	public Calendar getMLocalEndDateCal() {
		return mLocalEndDateCal;
	}

	protected void setMLocalEndDateCal(Calendar mLocalEndDateCal) {
		this.mLocalEndDateCal = mLocalEndDateCal;
	}

	public void setMLocalEndDate(Calendar localEndDate) {
		mLocalEndDateCal = localEndDate;
		mLocalEndDate = localEndDate.getTime();
	}

	@Transient
	public Calendar getMLocalStartDateCal() {
		return mLocalStartDateCal;
	}

	protected void setMLocalStartDateCal(Calendar mLocalStartDateCal) {
		this.mLocalStartDateCal = mLocalStartDateCal;
	}

	public void setMLocalStartDate(Calendar localStartDate) {
		mLocalStartDateCal = localStartDate;
		mLocalStartDate = localStartDate.getTime();
	}
	//CR1564-end

	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	@Transient
	public long getLastUsageTransactionIdForSameContent() {
		return lastUsageTransactionIdForSameContent;
	}

	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	public void setLastUsageTransactionIdForSameContent(long lastTransId) {
		lastUsageTransactionIdForSameContent = lastTransId;
	}

	/**CR 1970 - Mobile Protect additions<br/>
	 * returns partnerId, or b2b partnerId, depending
	 * @return
	 */
	@Transient
	public String getPartnerId()	  {
		if (b2bPartner!=null)
			return this.b2bPartner.getPartnerId();
		else
			return mPartnerId;
	}

	public void setPartnerId(String partnerId) 
	{
		mPartnerId = partnerId;
	}	

	//CR1981 Misano changes, account SPID when the subscription was bought
	@Transient
	public String getSpId()  
	{  
		return spId;  
	}

	public void setSpId(String spId)  	{    
		this.spId = spId;	
	}

	//moved here for Google thingy
	@Transient
	public String getMsisdn() {
		//if (StringUtils.isBlank(this.msisdn))
		//	this.msisdn = getAccount()!=null?getAccount().getMsisdn():null;
		return this.msisdn;
 
			
	}

	public void setMsisdn(String newMsisdn)	{
		this.msisdn=newMsisdn;
	}

	@Column(name="EXTERNAL_SUB_ID")
	public String getExternalSubId() {
		return externalSubId;
	}

	public void setExternalSubId(String externalSubId) {
		this.externalSubId = externalSubId;
	}

	//CR 2080 - SMS Notification additions
	@Column(name="merchant_name")
	public String getMerchantName()	  {
		return mMerchantName;
	}

	public void setMerchantName(String merchantName) 
	{
		mMerchantName = merchantName;
	}

	//CR 2108 Start - Only getter here.
	//Setter is in ERSubscription

	//
	
	@Column(name="RENEWAL_PRE_RATE", nullable=true)
	public double getRenewalPreRate() {
		return renewalPreRate;
	}


	protected void setRenewalPreRate(Double renewalPreRate) {
		if(renewalPreRate != null)
			this.renewalPreRate = renewalPreRate.doubleValue();
	}
	
	@Column(name="PARTNER_TAX_RATE", nullable=true)
	public double getPartnerTaxRate() {
		return partnerTaxRate;
	}
	
	protected void setPartnerTaxRate(Double partnerTaxRate) {
		if(partnerTaxRate != null)
			this.partnerTaxRate = partnerTaxRate.doubleValue();
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


	void setCountry(ErCountry opco) {
		this.country = opco;
	}
	
	@Transient
	public ErCountry getCountry()	{
		return country;
	}
	
	//private Integer countryId;
	
	@SuppressWarnings("unused")
	private void setCountryId(Integer id)	{
		if(id!=null)
			country = ErCountry.getCountryById(id);
	}
	
	@Column(name="country_obj_id")
	public Integer getCountryId()	{
		return country.getErId();
	}

	/**
	 * @return
	 */
	@Transient
	public PurchasedService getPurchasedService(String serviceId) {
		PurchasedService serv = null;
		if (serviceId != null && purchasedServices.size()>0)
			serv = purchasedServices.get(serviceId);
		return serv;
	}

	//MQC8202
	public void setIsRefundable(Boolean refundableFlag) {
		if(refundableFlag!=null)
			isRefundable = refundableFlag.booleanValue() ;
	}

	public void setPackage(CatalogPackage val)
	{
		mPack = val;
		if (val !=null && val.getPricePoint()!=null)	{
			pricePoint=val.getPricePoint();	//TODO MQC8385 remove this line, eventually
		}
	}

	public void setRatingAttributes(RatingAttributes val)
	{
		this.mMatchingAttributes = val;
	}

	public void setStatus(Integer val)
	{
		if(val !=null)
		mStatus = val;
	}

	public void setSuperPackage(Boolean flag)
	{
		if(flag != null)
			this.mSuperPackage = flag.booleanValue();
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
		if(StringUtils.isNotBlank(val))
			mSubscriptionIdLong=Long.parseLong(val);
	}

	public void setCurrentNoOfOccurences(Long mCurrentNoOfOccurences) {
		if(mCurrentNoOfOccurences != null)
		this.mCurrentNoOfOccurences = mCurrentNoOfOccurences ;
	}

	public void setExpiryDate(Date val) {
		mExpiryDate = val;
	}

	public void setPreOrdered(Boolean flag) {
		if(flag!=null)
			this.mPreOrdered = flag.booleanValue();
	}

	/**The date before which the customer will be liable to a penalty charge if they cancel*/
	public void setFinalMinSubscriptionEndDate(Date date) {
		this.mFinalMinSubscriptionEndDate = date;
	}

	public void setPurchaseDate(Date val) {
		mPurchaseDate = val;
	}

	public void setStartDate(Date val) {
		mStartDate = val;
	}



	public void setPenaltyCharge(Double penaltyCharge) {
		if(penaltyCharge!=null)
		this.mPenaltyCharge = penaltyCharge;
	}

	public void setNextCyclePercentValue(Double val) {
		
		if(val !=null)
			mNextCycleDiscount = val.doubleValue();
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
			}
			//for legacy support ...
			mServiceProvisioningTags.put(serviceId, provTag);
		}
	}

	/**
	 *MQC 8393
	 * @return boolean
	 */
	@Transient
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
	@Transient
	public Transaction getLastPaymentTransaction() {
		return lastPaymentTransaction;
	}

	/**
	 * CR2255 Phase2 - add partner information
	 * @return b2bPartner
	 */
	@ManyToOne(optional=true,     targetEntity=B2BPartner.class, fetch=FetchType.LAZY)
    @JoinColumns({
          @JoinColumn(name="PARTNER_ID", referencedColumnName = "PARTNER_ID", insertable = false, updatable = false),
          @JoinColumn(name="country_obj_id", referencedColumnName = "country_obj_id",  insertable = false, updatable = false)
        })
	public B2BPartner getB2BPartner() {
		return b2bPartner;
	}

	/**
	 * CR2255 Phase2 - add partner information
	 * @param partner, set the B2BPartner
	 */
	public void setB2BPartner(B2BPartner partner) {
		this.b2bPartner = partner;
		if (partner!=null)
			setPartnerId(partner.getPartnerId());
	}

	/**
	 * should be equivalent to getPackage().getPricePoint()<br/>
	 * added for MQC8385 since the pricepoint should be associated with the sub, not the package
	 * @return
	 */
	@Transient
	public PricePoint getPricePoint() {
		if (pricePoint==null && getPackage()!=null)	{
			logger.warn("sub {} should be able to get pricepoint from subscription, not package", getSubscriptionIdLong());
			pricePoint=getPackage().getPricePoint();	//TODO MQC8385 remove this line, eventually
		}
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
	
	@Column(name="RENEWAL_COUNTER_TO_LINKED_PPT")
	public int getRenewalCounterToLinkedPricepoint() {
		return this.renewalCounterToLinkedPricepoint;
	}

	/**
	 * CR CTB-1 Advanced Linked Pricepoint
	 * @param noOfRenewals the renewalCounterToLinkedPricepoint to set
	 */
	public void setRenewalCounterToLinkedPricepoint(Integer noOfRenewals) {
		this.renewalCounterToLinkedPricepoint = (noOfRenewals !=null)?noOfRenewals.intValue():0;
	}

	/**
	 * MQC 9031
	 * @return the renewalPreRateGross
	 */
	
	@Column(name="RENEWAL_PRE_RATE_GROSS", columnDefinition="NUMBER")
	public boolean isRenewalPreRateGross() {
		return this.renewalPreRateGross;
	}

	/**
	 * @param renPreRateGross the renewalPreRateGross to set
	 */
	public void setRenewalPreRateGross(Boolean renPreRateGross) {
		if(renPreRateGross != null)
			this.renewalPreRateGross = renPreRateGross.booleanValue();
	}

	/**
	 * Adds the txn to the list associated with this subscription 
	 * @param txn
	 */
	public void addTransaction(Transaction txn){
		if (!transactions.contains(txn))	{
            ((List<Transaction>)transactions).add(txn);
			txn.setSubscription(this);
			if (StringUtils.isBlank(txn.getMsisdn()))
				txn.setMsisdn(getMsisdn());
		}
//		switch (txn.getMetaType())      {
//		case Modification:modifyTxns.add((ModifyTxn)txn);
//		case Payment:paymentTxns.add((PaymentTxn)txn);
//		case Refund: //find the associated PaymentTxn and add it as a child object?			
//
//		}
	}

	/**
	 * MQC10448 - return the next pricepoint for the Advanced TRIAL Pricepoint Link flow
	 * @return String
	 */
	@Transient
	public String getNextPricePointId() {

		String rv = "";

		if (this.getPricePoint() != null) {
			if (this.renewalCounterToLinkedPricepoint < this.getPricePoint().getRenewalsUntilLinkedPricepoint() - 1 ) {
				rv = this.getPricePoint().getId();
			}
			else {
				rv = this.getPricePoint().getPricepointIdLink();
			}
		}

		return rv;
	}

	/**
	 * @return the lastPaymentTransactionAmount
	 */
	@Transient
	public double getLastPaymentTransactionAmount() {
		return this.lastPaymentTransactionAmount;
	}

	/**
	 * @param lastAmount, the lastPaymentTransactionAmount to set
	 */
	public void setLastPaymentTransactionAmount (double lastAmount) {
		this.lastPaymentTransactionAmount = lastAmount;
	}

	/**
	 * @return the lastToLastPaymentTransactionAmount
	 */
	@Transient
	public double getLastToLastPaymentTransactionAmount() {
		return this.lastToLastPaymentTransactionAmount;
	}

	/**
	 * @param lastAmount, the lastToLastPaymentTransactionAmount to set
	 */
	public void setLastToLastPaymentTransactionAmount (double lastToLastAmount) {
		this.lastToLastPaymentTransactionAmount = lastToLastAmount;
	}

	/**
	 * JIRAET65
	 * @return the pricepointChangeOnRenewal
	 */
	@Transient
	public boolean isPricePointChangeOnRenewal() {
		return this.pricePointChangeOnRenewal;
	}

	/**
	 * JIRAET65
	 * @param pricepointChange, the pricepointChangeOnRenewal to set
	 */
	public void setPricePointChangeOnRenewal(boolean pricePointChange) {
		this.pricePointChangeOnRenewal = pricePointChange;
	}

	/**
	 * JIRAET96 - Add the old inactivated subscription id, if this subscription is created as part of the upsell flow
	 * @return the oldSubcriptionId
	 */
	@Transient
	public long getOldSubscriptionId() {
		return oldSubscriptionId;
	}

	/**
	 * JIRAET96 - Add the old inactivated subscription id, if this subscription is created as part of the upsell flow
	 * @param s, the oldSubcriptionId to set
	 */
	public void setOldSubscriptionId( long oldSubId ) {
		oldSubscriptionId = oldSubId;
	}
	/**
	 * JIRAET96 - Add the old msisdn, if this subscription is part of the change msisdn flow
	 * @return the oldAccountId
	 */
	@Transient
	public String getOldMsisdn() {
		return oldMsisdn;
	}

	/**
	 * JIRAET96 - Add the old msisdn, if this subscription is part of the change msisdn flow
	 * @param s, the oldAccountId to set
	 */
	public void setOldMsisdn( String oldAccMsisdn ) {
		oldMsisdn = oldAccMsisdn;
	}

	/**
	 * JIRAET119
	 * Checks whether subscription has provisionable services
	 * @return boolean
	 */
	@Transient
	public boolean isProvisionable()	{

		boolean rv = false;

		if (purchasedServices != null && purchasedServices.size() > 0)
		{

			List<PurchasedService> purchServices = new ArrayList<>(purchasedServices.values());

			for (PurchasedService purchServ : purchServices) {
				if (isNotBlank(purchServ.getProvisioningTag()) && !purchServ.getProvisioningTag().equals("N/A") && !purchServ.isProvisionOnUsage()) {
					rv = true;
					break;
				}
			}
		}

		return rv;
	}

	/**
	 * JIRAET124
	 * @return the noFuturePricePointRenewals
	 */
	@Transient
	public int getFuturePricePointRenewals() {
		return noFuturePricePointRenewals;
	}

	/**
	 * JIRAET124
	 * @param futurePricePointRenewals, the noFuturePricePointRenewals to set
	 */
	public void setFuturePricePointRenewals( int futurePricePointRenewals ) {
		noFuturePricePointRenewals = futurePricePointRenewals;
	}

	@Transient
	public PurchasedService[] getPurchasedServices() {
		PurchasedService[] serv = null;
		if (purchasedServices.size()>0)
			serv = purchasedServices.values().toArray(new PurchasedService[purchasedServices.size()]);

		return serv;
	}


	protected List<PurchasedService> purcServiceList;
	
	@OneToMany( mappedBy="subscription",targetEntity=PurchasedService.class,	fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OrderColumn(name="SUBSCRIPTION_OBJ_ID")
	public List<PurchasedService> getPurcServiceList() {
		return purcServiceList;
	}

	public void setPurcServiceList(List<PurchasedService> purcServiceList) {
		this.purcServiceList = purcServiceList;
		if(purcServiceList!=null)
			for(PurchasedService purchasedService: purcServiceList) {
	
				if (purchasedService != null && purchasedService.getServiceId() != null) {
				  purchasedServices.put(purchasedService.getServiceId(), purchasedService);
				  mServiceStatuses.put(purchasedService.getServiceId(), new Integer(purchasedService.getStatus()));
				  mServiceProvisioningTags.put(purchasedService.getServiceId(),purchasedService.getProvisioningTag());
				  if (purchasedService.getNonRefundDescription() != null) mServiceNonRefundDescription.put(purchasedService.getServiceId(),purchasedService.getNonRefundDescription());
				}
			
			}
		
	}

	
	
	/**
	 * new, only for use with JPA DAO
	 * 
	 * @since May 2015
	 * @return
	 */
	@OneToMany(mappedBy = "subscription", targetEntity = PaymentTxn.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	public List<PaymentTxn> getPaymentTransactions() {
		return paymentTxns;
	}

	public void setPaymentTransactions(List<PaymentTxn> txns) {
		paymentTxns = txns;
		//QC-13161: to set the last price values starts here
		if(txns!=null && !txns.isEmpty()){
			if(txns.get(0)!=null && txns.get(0).getAmount() != null)
			lastPaymentTransactionAmount = txns.get(0).getAmount().doubleValue();
		}
		//QC-13161: to set the last price values ends here
	}

	/**
	 * new, only for use with JPA DAO
	 * 
	 * @since May 2015
	 * @return
	 */
	@OneToMany(mappedBy = "subscription", targetEntity = ModifyTxn.class, fetch = FetchType.LAZY)
	public List<ModifyTxn> getModifyTransactions() {
		return modifyTxns;
	}

	public void setModifyTransactions(List<ModifyTxn> txns) {
		modifyTxns = txns;
	}

	@Column(name="NEXT_CYCLE_DISCOUNT", nullable=true)
	public double getNextCyclePercentValue()	{
		return mNextCycleDiscount;
	}
	
	/**
     * Used to store the locking id related to the subscription row in the database.
     * This is used to handle concurrent updates to the ER_SUBSCRIPTIONS table from
     * multiple core instances.
     * @since er 5.1
     */
	protected Long lockId = -1l;
	
	/**
	 * used for concurrency and transaction locking.  
	 * @return
	 * @since ER 5.1
	 */
	@Column(name="subs_lock_id")
	//@Version
	public Long getLockId() {
		return lockId;
	}

	/**
	 * used for concurrency and transaction locking.
	 * @param lockId
	 */
	public void setLockId(Long lockId) {
		this.lockId = lockId;
	}

	/**
	 * Used to check if a package requires provisioning
	 * Put in as part of REMEDY 1830
	 * @return
	 */
	public boolean provisionRequired() {
		//START REMEDY 4919 - for a super package there will be no purchased services so return false
		if (this.getPackage() == null || this.getPackage().isSuperPackage()) {
			return false;
		}
		//END REMEDY 4919
		boolean provisioningFlag = false;
		if (getPurchasedServices() != null) {
			for (PurchasedService service: this.getPurchasedServices()) {
				if (!"N/A".equals(service.getProvisioningTag())) {
					provisioningFlag = true;
					break;
				}
			}
		}
		return provisioningFlag;
	}

	public void setServiceStatus(CatalogService catService, int status) {
		PurchasedService ps = null;
		if (catService!=null)		{			
			String serviceId = catService.getId();
	
			if (serviceId!=null)	{
				if (purchasedServices.containsKey(serviceId))		{
					ps = purchasedServices.get(serviceId);
					ps.setStatus(status);
				}	else	{
					//create the object
					ps = new PurchasedService();
					ps.setStatus(status);
					ps.setServiceId(serviceId);
	
					//Remedy 4100, Bruno Meseguer, Secures the ProvTag is set when creating a new ERPurchasedService
					ps.setProvisioningTag(catService.getProvisioningTag());
	
					ps.setIsProvisionOnUsage(catService.isProvisionOnUsage()); // CR1209
	
					purchasedServices.put(serviceId,ps);
				}
	
				// for legacy support
				mServiceStatuses.put(serviceId, new Integer(status));
			}
		}
	}

	public void setServiceStatus(String serviceId, int status) {
		PurchasedService ps = null;
		if (serviceId!=null) {
			if (purchasedServices.containsKey(serviceId)) {
				ps = purchasedServices.get(serviceId);
				ps.setStatus(status);
			} else {
				//create the object
				ps = new PurchasedService();
				ps.setStatus(status);
				ps.setServiceId(serviceId);
				purchasedServices.put(serviceId,ps);
			}
			// for legacy support
			mServiceStatuses.put(serviceId, new Integer(status));
		}
	}
	
	/**
	 * whether Subscription contains the service
	 * added for JIRA ET-238 - new get service offers call
	 * @param serviceId
	 * @return boolean
	 */
	public boolean containsPurchasedService(String serviceId) {
		boolean rv = false;
		
		if (isNotBlank(serviceId) && purchasedServices.containsKey(serviceId)) {
			rv = true;
		}
			
		return rv;
	}
}
