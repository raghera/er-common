package com.vizzavi.ecommerce.business.selfcare;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.vizzavi.ecommerce.business.catalog.DRMObject;
import com.vizzavi.ecommerce.business.charging.BaseAuthorization;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.CountryCode;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.ErCoreConst;
import com.vizzavi.ecommerce.business.common.RatingAttributes;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.rating.TaxRatedEvent;


//MQC8202 - start - was
//public class Transaction implements  java.io.Serializable
public class Transaction implements  java.io.Serializable
//M!C8202 - end
{
	private    static final long serialVersionUID = 9215527645977333771L;
	private static Logger sLog = Logger.getLogger(Transaction.class);
	protected String mSuperCreditId;
	protected int balanceImpact;
	protected int mBalanceImpact;
	protected String mBearer; // Bearer Type
	protected Date mPurchaseDate;    // Creation date
	protected Date mLocalPurchaseDate;    // //CR1564-Utctimezone for diff region in country
	protected Calendar mLocalPurchaseDateCal;    // //CR1564-Utctimezone for diff region in country
	protected double mPurchaseRate;   // The rate purchasd
	protected double mTaxRate;        // tax rate
	protected ChargingResource mResource; // currency
	protected String mContentName;
	protected String mServiceId;        // RPS selectors
	protected String mTransactionId = null;      // transaction poid
	protected long	mTransactionIdLong = -1;
	protected double mNextCycleDiscount;  // next cycle discount
	protected Date mRefundEnlargementDate;    // refund enlargement date
	protected String mRefundPaymentTransactionId = null;    // associated payment transaction with refund
	protected long mRefundPaymentTransactionIdLong = -1;
	protected TransactionType mType;      // name of transaction
	protected Subscription mSubscription;
	protected ModificationInfo mModInfo;
	protected String mUsageClassName, mUsageSubClassName;
	protected int mEventVolume;
	protected int eventVolume;
	// VFE-ER8.0 (Record Undiscounted price on discounted purchases).  This field is added to Store the undiscounted price in the transaction.
	protected double mStandardRate;
	// VFE-ER8.0 (Record Undiscounted price on discounted purchases).  This field is added to Store the Tier Name in the transaction
	protected  String mTierName;
	protected  String mAccessChannel;		// VFE-ER8.0 P2 (Access Channel Reporting).  This field is added to Store the Access Channel in the transaction
	protected  String mPurchaseChannel;		// VFE-ER8.0 P2 (Purchase Channel Reporting).  This field is added to Store the Purchase Channel in the transaction	
	protected  String mDeviceId;	// VFE-ER8.0 P2 (Distribution Channel Reporting).  This field is added to Store the Device ID in the transaction
	protected int mSuitabilityDecision; //(VFE-PS, ER8 P2: Access Control). This field is added to Store the "Suitability Decision" in the transaction.
	protected String mParentTransactionId = null;
	protected long mParentTransactionIdLong = -1;
	private boolean mExpressflag = false;
	private String mPromoPrecode = null;	// this is the full promo code
	private String mPromoUniqueCode = null;
	private double 	mRoamingNetAmount = 0;
	private double 	mRoamingGrossAmount = 0;
	private int 	mRoamingTypeId = ErCoreConst.ROAMING_DOMESTIC;
	private int		mNetworkCodeId = 0;
	private String	mClientNetworkCode = null;
	private boolean	mRefundRoamingCharge = false;
	//private static final boolean ER4STATUSCONFIG_ON;
	protected String mExternalTransId;
	protected String mExternalField1 ;
	protected String mExternalField2 ;
	protected boolean zeroCostIgnore = false ;
	private double usageTime = 0 ;	//usagetime of a volume service
	protected DRMObject mDRMObject = null;
	protected String mPartnerId = null; 	//Partner ID of the transaction
	protected String mAggregatorId = null;	//Aggregator ID of the transaction
	protected String mRecipientMsisdn = null;	//CR-1209_1 - add receipient msisdn and product code
	protected String mProductCode = null;
	protected int mReIssue = 0;		//CR1423: Duplicate Charging
	// CR - ENHANCED BUNDLE REPORTING (Feb 2010)
	protected Date mSubPeriodStart;
	protected Date mSubPeriodEnd;
	// CR - ENHANCED BUNDLE REPORTING - ends
	//CR-1448 - add merchant name and invoice text
	protected String mMerchantName = null;
	protected String mInvoiceText = null;

	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	protected boolean mContainsReIssueService = false;

	protected String spId;		// CR 1643 - Pre-Pay Post-Pay Split
	protected String isPrepay;	// CR 1643 - Pre-Pay Post-Pay Split
	// transaction status
	// 0 successful
	// >=1 failed
	protected int mStatus;

	// Customer care data
	protected String mCsrId;
	protected String mReason;
	protected String mDescription;

	// payment information
	protected String mAuthCode;
	protected int mPaymentStatus;
	protected int mAccessDevice=-1;
	protected String mErrorId;
	protected String mErrorDescription;
	protected int mPaymentType;
	protected String mSubscriptionId;
	protected long mSubscriptionIdLong = -1;
	protected ResourceBalance[] mBalances;

	/**
        This can be used to identify the price. It allows quick rating to be performed
        It doesn't indentify the tax or discount percentage
	 */
	protected String mRateIdentifier;

	/**
        This will contain the attributes that matched the input parameters
	 */
	protected RatingAttributes mMatchingAttributes;
	protected String mHostId;
	protected String mClientId;
	protected String mPaymentInfo;
	protected String mContentDescription;  // This is contentName in all other classes

	/** requirement for Refund Diallowance
	 *  by default it is always refundable
	 */
	protected String mNonRefundableDescription = "";
	protected boolean mRefundable = true;
	protected String mSessionId = "";

	static final String ROUND_NTH_DECIMAL = "ROUND_NTH_DECIMAL";
	protected  String mAssetId;
	protected  String mAssetID;
	//protected String mBearer; // Bearer Type
	protected String mDonnerSubscriptionId = null;
	protected List<TransactionSubRecord> mSubRecords;
	//CR AffiliateID
	protected String mAffiliateID;

	protected String mMsisdn;

	//CR2082 - Google DCB3, add the packageid of the subscription for this transaction
	protected String mPackageId;

	//CR2080 - add associated modify transaction id, to send in modify CM and inactivate sub notification ERIF message
	protected String mModifyTransactionId = null;

	protected String childSpId;	// CR 2198 - add child spid and service provider type
	protected String spType;	// CR 2198 - add child spid and service provider type

	protected String mContentCategory;	// CR 2255 - add content_category
	
	public Transaction()
	{
		mStatus = 1;
	}

	public Transaction(Transaction tran)
	{
		this.mSessionId = tran.getSessionId();
		this.mPurchaseDate = tran.getPurchaseDate();
		this.mLocalPurchaseDate = tran.getLocalPurchaseDate();
		this.mLocalPurchaseDateCal = tran.getLocalPurchaseDateCal();
		this.mPurchaseRate = tran.getPurchaseRate();
		this.mTaxRate = tran.getTaxRate();
		this.mResource = tran.getPurchaseCurrency();

		//Remedy 6837, Bruno Meseguer, API interface was broken, contentName restored
		setContentName(tran.getContentName());

		//@hud this.mContentName = tran.getContentName();
		//@hud this.mContentID = tran.getContentID();
		this.mServiceId = tran.getServiceId();
		this.mAssetId     = tran.getAssetId();

		/*ER 7 Compliant */
		this.mAssetID     = tran.mAssetID;

		this.mUsageClassName=tran.getUsageClassName();
		this.mUsageSubClassName=tran.getUsageSubClassName();

		this.mEventVolume=tran.getEventVolume();

		//Remedy 6838, Bruno Meseguer, API interface was broken, eventVolume restored
		//needed to maintain ER7 client compatibility
		this.eventVolume=tran.getEventVolume();

		this.setTransactionId(tran.getTransactionId());
		this.mNextCycleDiscount = tran.mNextCycleDiscount;
		this.mRefundEnlargementDate = tran.getRefundEnlargementDate();
		this.mType = tran.getType();
		this.mSubscription = tran.getSubscription();
		this.mStatus = tran.getStatus();
		this.mCsrId = tran.getCsrId();
		this.mReason = tran.getReason();
		this.mDescription = tran.getDescription();
		this.mAuthCode = tran.getAuthCode();
		this.mPaymentStatus = tran.getPaymentStatus();
		this.mErrorId = tran.getPaymentErrorId();
		this.mErrorDescription = tran.getPaymentErrorDescription();
		this.mModInfo = tran.getModificationInfo();
		this.mPaymentType = tran.getPaymentType();
		this.mSubscriptionId = tran.getSubscriptionId();

		// VFE-ER8.0 (Record Undiscounted price on discounted purchases).  This logic copies the standard rate and the tier name to the new Transaction Object.
		this.mStandardRate = tran.getStandardRate();
		this.mTierName     = tran.getTierName();

		//START ADDED BY EGYPT TEAM -- ER7 PIII SPAIN MIGRATION
		//	Added on:	21-04-2005
		//	Added by:	VFE - Amr Mowafi - PS team
		//	Purpose :	ER7 PIII Spain Migration
		this.mBearer = tran.getBearer();
		//END ADDED BY VFE

		this.mRefundPaymentTransactionId = tran.mRefundPaymentTransactionId;
		mRefundPaymentTransactionIdLong = tran.getRefundPaymentTransactionIdLong();


		if (tran.mMatchingAttributes!=null) this.mMatchingAttributes = new RatingAttributes (tran.mMatchingAttributes);
		if (tran.mBalances!=null) {
			mBalances = new ResourceBalance [tran.mBalances.length];
			for (int i = 0; i<tran.mBalances.length;i++)
				this.mBalances[i] = new ResourceBalance (tran.mBalances[i]);
		}
		this.mPaymentInfo = tran.getPaymentInfo();

		//Remedy 6837, Bruno Meseguer, API interface was broken, contentName restored
		setContentDescription(tran.getContentDescription());

		//was
		//this.mContentDescription = tran.getContentDescription();

		this.mClientId = tran.getClientId();
		this.mHostId = tran.getHostId();

		this.mBearer = tran.getBearer();
		this.mDonnerSubscriptionId = tran.getDonnerSubscriptionId();
		this.mSuperCreditId = tran.getSuperCreditId();

		this.mBalanceImpact = tran.getBalanceImpact();

		//Remedy 6842, Bruno Meseguer, API interface was broken, balanceImpact restored
		this.balanceImpact = tran.getBalanceImpact();

		this.mSubRecords = tran.getSubRecords();

		// VFE-ER8.0 - P2 (Access Channel Reporting).  This logic copies the Access Channel to the new Transaction Object.
		this.mAccessChannel = tran.getAccessChannel();

		// VFE-ER8.0 - P2 (Purchase Channel Reporting).  This logic copies the Purchase Channel to the new Transaction Object.
		this.mPurchaseChannel = tran.getPurchaseChannel();

		// VFE-ER8.0 - P2 (Distribution Channel Reporting).  This logic copies the Device ID to the new Transaction Object.
		this.mDeviceId = tran.getDeviceId();
		this.mParentTransactionId = tran.getParentTransactionId();

		//@kamatha stk13076 roaming
		this.mRoamingNetAmount = tran.getRoamingNetAmount();
		this.mRoamingGrossAmount = tran.getRoamingGrossAmount();
		this.mRoamingTypeId = tran.getRoamingTypeId();
		this.mNetworkCodeId = tran.getNetworkCodeId();
		this.mRefundRoamingCharge = tran.needRefundRoamingCharge();

		//REMEDY 6065 - add drm object
		this.mDRMObject = tran.getDRMObject();

		//CR AffiliateID
		this.mAffiliateID = tran.getAffiliateID();

		//CR-0869 Start
		this.mPartnerId = tran.getPartnerId();
		this.mAggregatorId = tran.getAggregatorId();
		//CR-0869 End
		//MQC 5356 - set usage time
		this.usageTime = tran.getUsageTime();


		//CR-1209_1 - add receipient msisdn and product code
		this.mRecipientMsisdn = tran.getReceipientMsisdn();
		this.mProductCode = tran.getProductCode();

		//CR1423: Duplicate Charging
		this.mReIssue = tran.getReIssue();

		// CR - ENHANCED BUNDLE REPORTING (Feb 2010)
		this.mSubPeriodStart = tran.getSubPeriodStart();
		this.mSubPeriodEnd	 = tran.getSubPeriodEnd();
		// CR - ENHANCED BUNDLE REPORTING - ends

		//CR-1448 - add merchant name and invoice text
		this.mMerchantName = tran.getMerchantName();
		this.mInvoiceText = tran.getInvoiceText();

		//MQC 6085: Duplicate charging using inactive / closed subscriptions
		this.mContainsReIssueService = tran.isContainsReIssueService();

		this.spId		= tran.spId;		// CR 1643 - Pre-Pay Post-Pay Split
		this.isPrepay	= tran.isPrepay;	// CR 1643 - Pre-Pay Post-Pay Split

		this.mPackageId	= tran.getPackageId(); //CR2082 - add package id

		this.mModifyTransactionId = tran.getModifyTransactionId(); // CR 2080 associated modify transaction id
		
		this.childSpId	= tran.childSpId;	// CR 2198 - add child spid and service provider type
		this.spType		= tran.spType;		// CR 2198 - add child spid and service provider type
		
		this.mContentCategory = tran.mContentCategory; 	// CR 2255 - add content_category
	}

	public Transaction(TaxRatedEvent event)
	{
		mPurchaseRate = event.getRate();
		mTaxRate = event.getTaxRate();
		mResource = ChargingResource.getResource(event.getCurrencyId());
		mNextCycleDiscount = event.getDiscountPercentage();
		mMatchingAttributes = event.getMatchingAttributes();
		if (event instanceof BaseAuthorization) {
			BaseAuthorization auth = (BaseAuthorization)event;
			mErrorId = auth.getErrorId();
			mErrorDescription = auth.getErrorDescription();
			mSubscriptionId = auth.getPackageSubscriptionId();
		}
	}


	/**
	 * @author hud
	 * Added based on ERTransaction's requirement
	 *
	 * @param type
	 * @param subscriptionId
	 * @param status
	 * @param paymentTransId
	 */
	public Transaction (
			TransactionType 	type
			,	String 				subscriptionId
			, 	int 				status
			, 	String 				paymentTransId
			)
	{
		mType = type;
		mSubscriptionId = subscriptionId;
		mStatus = status;
		mTransactionId = paymentTransId;
		//	mStatus = 1;

		// parsing here, throw as necessary
		if (mSubscriptionId != null && !mSubscriptionId.equals("")) {
			mSubscriptionIdLong = Long.parseLong(mSubscriptionId);
		}
//
		if (mTransactionId != null && !mTransactionId.equals("")) {
			mTransactionIdLong = Long.parseLong(mTransactionId);
		}

	}

	//@hud RFRFRF
	public Transaction (
			TransactionType 	type
			,	long 				subscriptionIdLong
			, 	int 				status
			, 	long 				paymentTransIdLong
			)
	{
		mType = type;
		mSubscriptionIdLong = subscriptionIdLong;
		mStatus = status;
		mTransactionIdLong = paymentTransIdLong;

		// for backwards compatibility
		mSubscriptionId = Long.toString(mSubscriptionIdLong);
		mTransactionId = Long.toString(mTransactionIdLong);
	}



	public void setUsageClassName (String usageClassName)
	{
		mUsageClassName = usageClassName;
	}

	public void setEventVolume (int eventvolume)
	{
		mEventVolume = eventvolume;
		//Remedy 6838, Bruno Meseguer, API interface was broken, eventVolume restored
		//needed to maintain ER7 client compatibility
		eventVolume = eventvolume;
	}

	public void setUsageSubClassName (String usageSubClassName)
	{
		mUsageSubClassName = usageSubClassName;
	}

	public String getUsageClassName()
	{
		return mUsageClassName;
	}


	public String getUsageSubClassName()
	{
		return mUsageSubClassName;
	}


	public int getEventVolume()
	{
		return mEventVolume;
	}

	public void setPaymentType(int paymentType)
	{
		mPaymentType = paymentType;
	}
	public int getPaymentType()
	{
		return mPaymentType;
	}

	/**
        This returns the associated subscription id
	 */
	public void setSubscriptionId(String subscriptionId)
	{
		mSubscriptionId = subscriptionId;
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

	public void setAccessDevice(int accessDevice)
	{
		mAccessDevice = accessDevice;
	}
	public int getAccessDevice()
	{
		int ret = 0;
		if (mAccessDevice == -1) {
			if (getMatchingAttributes() != null) {
				ret = getMatchingAttributes().getDeviceType();
			}
		}
		else {
			ret = mAccessDevice;
		}
		return ret;
	}

	// payment id

	// subscription info
	/*
    protected String mPackageSubId;
    protected CatalogPackage mPackage;
    protected Date mExpiryDate;
    protected Date mStartDate;
    private String mErrorId;
    private String mErrorDescription;

	 */

	public void setType (TransactionType type)
	{
		mType = type;
	}
	public TransactionType getType()
	{
		return mType;
	}

	public void setPurchaseDate (Date purchaseDate)
	{
		mPurchaseDate = purchaseDate;
	}
	public Date getPurchaseDate()
	{
		return mPurchaseDate;
	}

	//CR1564-start-Utctimezone for diff region in country
	public void setLocalPurchaseDate (Date date)
	{
		mLocalPurchaseDate = date;
	}
	public Date getLocalPurchaseDate()
	{
		return mLocalPurchaseDate;
	}

	public void setLocalPurchaseDate (Calendar date)
	{
		mLocalPurchaseDateCal = date;
		mLocalPurchaseDate = date.getTime();
	}
	public Calendar getLocalPurchaseDateCal()
	{
		return mLocalPurchaseDateCal;
	}
	//CR1564-end
	/**
	 * sets the Purchase rate - which should include tax
	 * @param purchaseRate
	 */
	public void setPurchaseRate (double purchaseRate)
	{
		//REMEDY 5501
		int round_nth_decimal =  4;

		double rv = -1;

		if(ConfigProvider.getProperty(ROUND_NTH_DECIMAL)!=null){
			round_nth_decimal = Integer.parseInt(ConfigProvider.getProperty(ROUND_NTH_DECIMAL));
		}

		rv = roundDouble(purchaseRate, round_nth_decimal);

		mPurchaseRate = rv;
		//END REMEDY 5501
	}

	public double getPurchaseRate()
	{
		return mPurchaseRate;
	}


	public double getPurchaseNetRate()
	{
		return mPurchaseRate/(1+this.getTaxRate());
	}

	public void setTaxRate(double taxRate)
	{
		mTaxRate = taxRate;
	}
	public double getTaxRate()
	{
		return mTaxRate;
	}


	//===========================================
	//@hud this MUST BE DEPRECATED
	/**
	 * @author hud
	 * @see setChargingResource
	 */
	public void setPurchaseCurrency(ChargingResource resource)
	{
		mResource = resource;
	}

	/**
	 * @author hud
	 *
	 */
	public void setChargingResource(ChargingResource resource)
	{
		mResource = resource;
	}

	/**
	 * @author hud
	 * @return
	 * @see getChargingResource()
	 */
	public ChargingResource getPurchaseCurrency()
	{
		return mResource;
	}

	/**
	 *
	 * @return
	 */
	public ChargingResource getChargingResource()
	{
		return mResource;
	}
	//====================================================

	/**
	 * @deprecated use {@link #setTransactionIdLong} instead
	 */
	@Deprecated
	public void setTransactionId(String transactionId)
	{
		mTransactionId = transactionId;
		if (mTransactionIdLong<0 && StringUtils.isNotBlank(transactionId))
			mTransactionIdLong=Long.parseLong(transactionId);
		//@hud RFRFRF
	}
	/**
	 * Returns the transactionId as a string.  As if that's any use to anyone...
	 * use {@link #getTransactionIdLong()} instead
	 * @see {@link #getTransactionIdLong()}
	 */
	public String getTransactionId()
	{
		if (mTransactionId == null) {
			if (mTransactionIdLong > 0) {
				mTransactionId = Long.toString(mTransactionIdLong);
			}
		}
		return mTransactionId;
	}

	/**
        Return the percentage discount off the real price
	 */
	public void setNextCycleDiscount(double nextCycleDiscount)
	{
		mNextCycleDiscount = nextCycleDiscount;
	}
	public double getNextCycleDiscount()
	{
		return mNextCycleDiscount;
	}

	/**
        Return the percentage discount off the real price
	 */
	public double getNextCycleDiscountPercent()
	{
		return mNextCycleDiscount * 100;
	}


	//Remedy 6837, Bruno Meseguer, API interface was broken, contentName restored
	//Note: below we can see ContentName commented out, that is wrong, it brakes the API
	public String getContentName()
	{
		return getContentDescription();
	}

	public void setContentName(String ContentName)
	{
		setContentDescription(ContentName);
	}

	/*
        public String getContentName()
        {
                //if (mContentName != null)
                return mContentName;
        //else return "N/A";
        }

        public String getContentID() {
            return mContentID;
        }
	 */

	public void setServiceId(String ServiceId)
	{
		mServiceId = ServiceId;
	}
	public String getServiceId()
	{
		//if (mContentName != null)
		return mServiceId;
		//else return "N/A";
	}


	public Subscription getSubscription()
	{
		return mSubscription;
	}

	public Calendar getEventDateTime() {
		if (mMatchingAttributes==null) return null;
		return mMatchingAttributes.getEventDateTime();
	}

	public double getEventUnits() {
		double rv =0.0;
		if (mMatchingAttributes!=null) rv = mMatchingAttributes.getEventUnits();
		return rv;
	}

	public void setEventUnits(double eventUnits) {

		if (mMatchingAttributes!=null)
			mMatchingAttributes.setEventUnits(eventUnits);

	}


	// Customer care data
	public void setCsrId(String csrId)
	{
		mCsrId = csrId;
	}
	public String getCsrId()
	{
		return mCsrId;
	}

	public void setDescription(String description)
	{
		mDescription = description;
	}
	public String getDescription()
	{
		return mDescription;
	}

	public void setReason(String reason)
	{
		mReason = reason;
	}
	public String getReason()
	{
		return mReason;
	}

	public void setStatus(int status)
	{
		mStatus = status;
	}
	
	/**
	 * eg 101 for COMPLETED, etc
	 * @return
	 */
	public int getStatus()
	{
		return mStatus;
	}

	public boolean isSuccess()
	{
		//if ( (getStatus()==TransactionStatus.COMPLETED) || (getStatus()==TransactionStatus.PECS_PAY_STATUS_AUTH_ACCEPTED) ) {
		if(mStatus==TransactionStatus.COMPLETED) {
			return true;
		}
		else return false;
	}

	public void setAuthCode(String authCode)
	{
		mAuthCode = authCode;
	}
	public String getAuthCode()
	{
		return mAuthCode;
	}

	public void setPaymentStatus(int paymentStatus)
	{
		mPaymentStatus = paymentStatus;
	}
	public int getPaymentStatus()
	{
		return mPaymentStatus;
	}

	public void setPaymentErrorId(String errorId)
	{
		mErrorId = errorId;
	}
	public String getPaymentErrorId()
	{
		return mErrorId;
	}

	public void setPaymentErrorDescription(String errorDescription)
	{
		mErrorDescription = errorDescription;
	}
	public String getPaymentErrorDescription()
	{
		return mErrorDescription;
	}


	/* Subscription info
    public String getPackageSubId()
    {
        return mPackageSubId;
    }

    public CatalogPackage getPackage()
    {
        return mPackage;
    }

    public Date getStartDate()
    {
        return mStartDate;
    }
	 */

	public void setRefundEnlargementDate(Date refundEnlargementDate)
	{
		mRefundEnlargementDate = refundEnlargementDate;
	}
	public Date getRefundEnlargementDate()
	{
		return mRefundEnlargementDate;
	}

	/**
        This is the associated payment transaction if that this refund is associated with
	 */
	public void setRefundPaymentTransactionId(String refundPaymentTransactionId )
	{
		mRefundPaymentTransactionId = refundPaymentTransactionId;
	}
	/**
	 */
	public String getRefundPaymentTransactionId()
	{
		if (mRefundPaymentTransactionId == null) {
			if (mRefundPaymentTransactionIdLong > 0) {
				mRefundPaymentTransactionId = Long.toString(mRefundPaymentTransactionIdLong);
			}
		}
		return mRefundPaymentTransactionId;
	}

	//@hud RFRFRF
	public void setRefundPaymentTransactionIdLong(long id)
	{
		mRefundPaymentTransactionIdLong = id;

		//Remedy 7004, Bruno Meseguer, compatibility with ER7 was broken
		mRefundPaymentTransactionId = Long.toString(id);
	}

	public long getRefundPaymentTransactionIdLong() {
		if (mRefundPaymentTransactionIdLong == -1) {
			if (mRefundPaymentTransactionId != null) {
				try {
					mRefundPaymentTransactionIdLong = Long.parseLong(mRefundPaymentTransactionId);
				}
				catch (NumberFormatException ne) {
					// nothing
					mRefundPaymentTransactionIdLong = -2;
				}
			}
		}
		return mRefundPaymentTransactionIdLong;
	}




	@Override
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		buf.append("tranid=" + mTransactionId + ", ");
		if (mPurchaseDate!=null) buf.append("purchasedate=" + mPurchaseDate + ", ");
		else buf.append("purchasedate= null, ");
		buf.append("purchaserate=" + mPurchaseRate + ", ");
		if (mResource!=null) buf.append("resource=" + mResource + ", ");
		else buf.append("resource= null");
		buf.append("service id=" + mServiceId + ", ");
		buf.append("asset="+mAssetId);
		buf.append("type=" + mType + ", ");
		buf.append("discountPercentage=" + getNextCycleDiscountPercent() + ", ");
		if (mSubscription!=null) buf.append("subscription=" + mSubscription + ", ");
		else buf.append("mSubscription= null, ");
		buf.append("status=" + getStatus() + ", ");
		buf.append("mCsrId=" + mCsrId + ", ");
		buf.append("mClientId=" + mClientId + ", ");
		buf.append("mReason=" + mReason + ", ");
		buf.append("mDescription=" + mDescription + ", ");
		buf.append("mAuthCode=" + mAuthCode + ", ");
		buf.append("mPaymentStatus=" + getPaymentStatus()+ ", ");
		buf.append("mErrorId=" + mErrorId+ ", ");
		buf.append("mErrorDescription=" + mErrorDescription+ ", ");
		buf.append("taxRate=" + mTaxRate + ", ");
		if (mRefundEnlargementDate!=null) buf.append("mRefundEnlargementDate=" + mRefundEnlargementDate + ", ");
		else buf.append("mRefundEnlargementDate= null");
		buf.append("mRefundPaymentTransactionId=" + mRefundPaymentTransactionId + ", ");
		buf.append("mPaymentInformation=" + mPaymentInfo + ", ");
		buf.append("mContentDescription=" + mContentDescription + ", ");
		buf.append("mRefundable=" + mRefundable + ", ");
		buf.append("mNonRefundableDescription=" + mNonRefundableDescription + ", ");
		if (mModInfo!=null) buf.append("mModInfo=" + mModInfo + ", ");
		else buf.append("mModInfo= null, ");
		if (mMatchingAttributes!=null) buf.append("mMatchingAttributes ="+ mMatchingAttributes+", ");
		else buf.append("mMatchingAttributes = null, ");
		buf.append("mRateIdentifier=" + mRateIdentifier + " ");
		if (mSuperCreditId!=null) buf.append("\nsuperCreditID = "+ mSuperCreditId);
		else buf.append("\nsuperCreditID = null ");
		buf.append("isSuperCredit = " + this.isCreatedBySuperCredit());
		buf.append("usageClass = " + this.mUsageClassName);
		buf.append("usageSubClass = " + this.mUsageSubClassName);
		buf.append("eventVolume = " + this.mEventVolume);

		// VFE-ER8.0 - P2 (Access Channel Reporting).
		buf.append("AccessChannel = " +this.mAccessChannel);

		// VFE-ER8.0 - P2(Purchase Channel Reporting).
		buf.append("PurchaseChannel = " +this.mPurchaseChannel);

		// VFE-ER8.0 - P2 (Distribution Channel Reporting).
		buf.append("DeviceID = " +this.mDeviceId);

		//REMEDY 6065 - drm object
		buf.append("DRM Object = " +this.mDRMObject);

		//CR AffiliateID
		buf.append("AffiliateID = " +this.mAffiliateID);

		//CR-0869 Start
		buf.append(" PartnerId = " + this.mPartnerId);
		buf.append(" AggregatorId = " + this.mAggregatorId);
		//CR-0869 End

		//CR-1209_1 - add receipient msisdn and product code
		buf.append(" RecipientMsisdn = " + this.mRecipientMsisdn);
		buf.append(" ProductCode = " + this.mProductCode);

		//CR1423: Duplicate Charging
		buf.append(" mReIssue = " + this.mReIssue);

		// CR - ENHANCED BUNDLE REPORTING (Feb 2010)
		buf.append(" SubPeriodStart = " + this.mSubPeriodStart);
		buf.append(" SubPeriodEnd = " + this.mSubPeriodEnd);
		// CR - ENHANCED BUNDLE REPORTING - ends

		//CR-1448 - add merchant name and invoice text
		buf.append(" MerchantName = " + this.mMerchantName);
		buf.append(" InvoiceText = " + this.mInvoiceText);

		buf.append(" spId = " + this.spId);			// CR 1643 - Pre-Pay Post-Pay Split
		buf.append(" isPrepay = " + this.isPrepay);	// CR 1643 - Pre-Pay Post-Pay Split

		buf.append(" PackageId = " + this.mPackageId);	// CR 2082 - Package Id

		buf.append(" ModifyTransactionId = " + this.mModifyTransactionId);	// CR 2080 - modify Transaction Id
		
		buf.append(" childSpId = " + this.childSpId);	// CR 2198 - add child spid and service provider type
		buf.append(" isPrepay = " + this.spType);		// CR 2198 - add child spid and service provider type
		
		buf.append(" contentCategory = " + this.mContentCategory);		// CR 2255 - add content_category
		/*
        buf.append("startdate=" + mStartDate + "\n");
        buf.append("expirydate=" + mExpiryDate + "\n");
        buf.append("packagesubid=" + mPackageSubId + "\n");
        buf.append("pack=" + mPackage + "\n");
		 */
		buf.append("}");
		return buf.toString();
	}

	public void setSubscription(Subscription sub)
	{
		mSubscription = sub;
		setSubscriptionIdLong(sub.getSubscriptionIdLong());
	}

	/**
	 * Used to retrieve the description of why the transaction is non-refundable
	 * @return Non-Refundable-Description
	 * @since ER 51
	 */
	public String getNonRefundableDescription(){
		return this.mNonRefundableDescription;
	}

	/**
	 * Used to assess if the transaction is refundable
	 * @return false if transaction not refundable
	 * @since ER 5.1
	 */
	public boolean getRefundable(){
		return this.mRefundable;
	}

	/**
	 * Used to assess if the transaction is refundable
	 * @return false if transaction not refundable
	 * @since ER 5.1
	 */
	public boolean isRefundable(){
		return this.mRefundable;
	}


	/**
        Returns extra information if a modification transaction
	 */
	public void setModificationInfo(ModificationInfo modInfo)
	{
		mModInfo = modInfo;
	}
	public ModificationInfo getModificationInfo()
	{
		return mModInfo;
	}

	public double getTaxAmount()
	{
		int round_nth_decimal =  ConfigProvider.getPropertyAsInteger(ROUND_NTH_DECIMAL, 4);
		double rv = -1;
		//		if(ConfigProvider.getProperty(ROUND_NTH_DECIMAL)!=null){
		//			round_nth_decimal = Integer.parseInt(ConfigProvider.getProperty(ROUND_NTH_DECIMAL));
		//		}
		if (getPurchaseCurrency().isCurrency()) {
			double netAmount = getPurchaseRate()/(1 + getTaxRate());
			rv = netAmount * getTaxRate();
		}
		return roundDouble(rv, round_nth_decimal);
	}


	public void setResourceBalances(ResourceBalance[] balance)
	{
		mBalances = balance;
	}
	public ResourceBalance[] getResourceBalances()
	{
		return mBalances;
	}
	/**
	 * @return
	 */
	public void setMatchingAttributes(RatingAttributes matchingAttrs)
	{
		mMatchingAttributes = matchingAttrs;
	}
	public RatingAttributes getMatchingAttributes() {
		return mMatchingAttributes;
	}

	/**
	 * @return
	 */
	public void setClientId (String clientId)
	{
		mClientId = clientId;
	}

	public String getClientId() {
		return mClientId;
	}

	/**
	 * @return
	 */
	//@hud
	//Getter Setter methods for TransactionType
	public void setHostId (String hostId)
	{
		mHostId = hostId;
	}

	public String getHostId() {
		return mHostId;
	}

	/**
	 * @return
	 */
	public double getNextCycleDiscountValue() {
		return mNextCycleDiscount;
	}

	/**
	 * @return
	 */
	public void setPaymentInfo(String paymentInfo)
	{
		mPaymentInfo = paymentInfo;
	}
	public String getPaymentInfo()
	{
		return mPaymentInfo;
	}

	//START ADDED BY EGYPT TEAM -- ER7 PIII SPAIN MIGRATION
	//	Added on:	21-04-2005
	//	Added by:	VFE - Amr Mowafi - PS team
	//	Purpose :	ER7 PIII Spain Migration

	//returns the Bearer type value
	public void setBearer(String bearer)
	{
		mBearer = bearer;
	}
	public String getBearer()
	{
		return mBearer;
	}
	//END ADDED BY VFE

	/**
	 * @return
	 */
	public void setContentDescription(String contentDescription)
	{
		mContentDescription = contentDescription;

		//Remedy 6837, Bruno Meseguer, API interface was broken, contentName restored
		mContentName 		= contentDescription;
	}
	public String getContentDescription()
	{
		return mContentDescription;
	}
	public static final double roundDouble(double d, int nthDecimal)
	{
		return Math.round(d * Math.pow(10, nthDecimal)) / Math.pow(10,nthDecimal);
	}

	public void setSessionId(String sessionId)
	{
		mSessionId = sessionId;
	}

	public String getSessionId()
	{
		return mSessionId;
	}
	/**
	 * @return
	 */
	public boolean isSuperCredit (){
		if (mDonnerSubscriptionId == null)return false;
		else return true;
	}
	/**
	 * @return
	 */
	public String getDonnerSubscriptionId(){
		return mDonnerSubscriptionId;
	}


	public void setAssetId (String assetId)
	{
		mAssetId = assetId ;
		mAssetID = assetId ;
	}
	public String getAssetId(){
		return mAssetId;
	}

	/**
	 * @param assetId
	 */
	public void setAssetID (String assetId)
	{
		mAssetId = assetId ;
	}
	/**
	 */
	public String getAssetID(){
		return mAssetId;
	}


	/**
	 * Added :     21-04-2005
	 * Added by :  VFE - PS team
	 * Purpose :   ER7 PIII Spain Migration
	 */
	public void setSuperCreditId(String superCreditId)
	{
		mSuperCreditId = superCreditId;
	}
	public String getSuperCreditId()
	{
		return mSuperCreditId;
	}

	/**
	 * @param balanceImpact
	 */
	public void setSuperCreditID(String superCreditId)
	{
		setSuperCreditId(superCreditId);
	}
	/**
	 * @param balanceImpact
	 */
	public String getSuperCreditID()
	{
		return getSuperCreditId();
	}



	public void setBalanceImpact(int balanceImpact)
	{
		mBalanceImpact = balanceImpact;

		//Remedy 6842, Bruno Meseguer, API interface was broken, balanceImpact restored
		this.balanceImpact = balanceImpact;
	}
	public int getBalanceImpact()
	{
		return mBalanceImpact;
	}

	/**
	 * This will check if a superCredit Id has been populated and assume this transactions
	 * is a Super Credit Consumer if true.
	 *
	 */
	public boolean isCreatedBySuperCredit()
	{
		boolean bSuperCredit = true;
		if (mBalanceImpact<=0
				|| mSuperCreditId==null
				|| mSuperCreditId.equals(""))
			bSuperCredit = false;
		return bSuperCredit;
	}
	//	end VFE - PS team code

	//	Amd001 [start]
	public void setSubRecords(List<TransactionSubRecord> subRecords) {

		mSubRecords = subRecords;
	}
	public List<TransactionSubRecord> getSubRecords()
	{
		return mSubRecords;
	}
	//	Amd001 [end]
	/**
	 * @version      ER 8.0
	 * @author       VFE ? PS Team
	 * @date         15-Aug-2005
	 * @description  (Record Undiscounted price on discounted purchases)   The purpose of this method is to get the undiscounted price from the transaction object. **/
	public void setStandardRate (double standardRate)
	{
		this. mStandardRate = standardRate;
	}
	public double getStandardRate()
	{
		return mStandardRate;
	}
	/**
	 * @version       ER 8.0
	 * @author        VFE ? PS Team
	 * @date          15-Aug-2005
	 * @description  (Record Undiscounted price on discounted purchases)   The purpose of this method is to get the Date Time Model from the transaction object. **/

	public void setTierName(String tierName)
	{
		mTierName = tierName;
	}
	public String getTierName()
	{
		return mTierName;
	}

	/**
	 * @version       ER 8.0 - P2
	 * @author        VFE ? PS Team
	 * @date          18-Sept-2005
	 * @description  (Access Channel Reporting)   The purpose of this method is to get the Access Channel from the transaction object. **/

	public void setAccessChannel(String accessChannel)
	{
		mAccessChannel = accessChannel;
	}
	public String getAccessChannel()
	{
		return mAccessChannel;
	}

	/**
	 * @version       ER 8.0 - P2
	 * @author        VFE ? PS Team
	 * @date          18-Sept-2005
	 * @description  (Purchase Channel Reporting)   The purpose of this method is to get the Purchase Channel from the transaction object. **/

	public void setPurchaseChannel(String purchaseChannel)
	{
		mPurchaseChannel = purchaseChannel ;
	}
	public String getPurchaseChannel()
	{
		return mPurchaseChannel;
	}

	/**
	 * @version       ER 8.0 - P2
	 * @author        VFE ? PS Team
	 * @date          18-Sept-2005
	 * @description  (Distribution Channel Reporting)   The purpose of this method is to get the Device ID from the transaction object. **/

	public void setDeviceId(String deviceId)
	{
		mDeviceId = deviceId ;
	}
	public String getDeviceId()
	{
		return mDeviceId;
	}

	/**
	 * @param deviceId
	 */
	public void setDeviceID(String deviceId)
	{
		mDeviceId = deviceId ;
	}
	/**
	 * @param deviceId
	 */
	public String getDeviceID()
	{
		return mDeviceId;
	}


	/**
	 * @version       ER 8.0 P2
	 * @author        VFE ? PS Team
	 * @date          13-Sep-2005
	 * @description  (Access Control)   getter for the "SuitabilityDecision" from the transaction object. **/
	public void setSuitabilityDecision(int suitabilityDecision)
	{
		mSuitabilityDecision = suitabilityDecision;
	}
	public int getSuitabilityDecision()
	{
		return this.mSuitabilityDecision;
	}

	/**
	 * @param parentTransactionId
	 */
	public void setParentTransactionId(String parentTransactionId)
	{
		mParentTransactionId = parentTransactionId;
	}
	/**
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

	public boolean hasParent() {
		if (mParentTransactionId != null)
			return true;
		return false;
	}



	// Getter and setter methods for express flag
	public boolean getExpressFlag()
	{
		return mExpressflag;
	}

	public void setExpressFlag(boolean expressflag)
	{
		mExpressflag = expressflag;
	}


	public void setRateIdentifier (String rateIdentifier)
	{
		mRateIdentifier = rateIdentifier;
	}
	public String getRateIdentifier()
	{
		return mRateIdentifier;
	}


	/**
	 * To generate complex Id for Payment Handlers
	 * @param locale
	 * @return
	 * @throws EcommerceException
	 */
	public String getComplexTransactionId(Locale locale) throws EcommerceException
	{
		// Format : C001/123456Z
		String rv = null;
		CountryCode countryCode = null;
		try {countryCode = CountryCode.getCountryCode(locale);}
		catch (Exception e) {sLog.error("Transaction:getComplexTransactionId failed: "+e);}

		if (locale !=null && this.mTransactionId!=null && countryCode!=null && this.mType!=null){
			sLog.debug ("Type of transaction : "+this.mType.toString()+"\nCountry Code : "+countryCode.getCode());
			if (this.mType.isPaymentContent() )
				rv="C";
			else
				rv="P";
			if (countryCode.getCode()<9) rv=rv+"00";
			else if (countryCode.getCode()<99) rv=rv+"0";
			//else; //nothing

			//MQC 6016 - move retrieval of property to local method of where it is used
			String transactionIdPrefix = "007"; // default value

			if(this.getType()!= null && this.getType().isRefundCash() || this.getType().isRefundDiscount() || this.getType().isRefundEnlargement() || this.getType().isRefundNonCash()){

				//UK Customization
				if(countryCode.getCode() == 5)
				{
					//MQC 6016 - move retrieval of property to local method of where it is used            	
					transactionIdPrefix = ConfigProvider.getProperty("TRANSACTION_ID_PREFIX", "007");
					rv = rv + transactionIdPrefix +"/"+this.mRefundPaymentTransactionId+"Z";
				}
				else
				{
					rv = rv + (countryCode.getCode()+1)+"/"+this.mRefundPaymentTransactionId+"Z";
				}
			}
			else
			{
				//UK Customization
				if(countryCode.getCode() == 5)
				{
					//MQC 6016 - move retrieval of property to local method of where it is used            	
					transactionIdPrefix = ConfigProvider.getProperty("TRANSACTION_ID_PREFIX", "007");
					rv = rv + transactionIdPrefix +"/"+this.mTransactionId+"Z";
				}
				else
				{
					rv = rv + (countryCode.getCode()+1)+"/"+this.mTransactionId+"Z";
				}
			}
		}
		else {
			throw new EcommerceException ("ERTransaction:getComplexTransactionId: failed to create complex Transaction Id");
		}

		return rv;

	}


	//===============================================================
	//@hud STKHREQ13107
	public String getPromoPrecode() {
		return mPromoPrecode;
	}
	public void setPromoPrecode(String precode) {
		mPromoPrecode = precode;
	}
	public String getPromoUniqueCode() {
		return mPromoUniqueCode;
	}
	public void setPromoUniqueCode(String uniqueCode) {
		mPromoUniqueCode = uniqueCode;
	}


	public double getUsageTime() {

		return usageTime;
	}


	public void setUsageTime(double usageTime) {

		this.usageTime = usageTime;
	}

	//================================================================
	//@hud STKHREQ13076 sp roaming
	public double getRoamingNetAmount() {
		return mRoamingNetAmount;
	}
	public void setRoamingNetAmount(double roamingNetAmount) {
		mRoamingNetAmount = roamingNetAmount;
	}
	public double getRoamingGrossAmount() {
		return mRoamingGrossAmount;
	}
	public void setRoamingGrossAmount(double roamingGrossAmount) {
		mRoamingGrossAmount = roamingGrossAmount;
	}
	public int getRoamingTypeId() {
		return mRoamingTypeId;
	}
	public void setRoamingTypeId(int roamingTypeId) {
		mRoamingTypeId = roamingTypeId;
	}
	public int getNetworkCodeId() {
		return mNetworkCodeId;
	}
	public void setNetworkCodeId(int networkCodeId) {
		mNetworkCodeId = networkCodeId;
	}
	public boolean needRefundRoamingCharge() {
		return mRefundRoamingCharge;
	}
	public void setRefundRoamingCharge(boolean refundRoamingCharge) {
		mRefundRoamingCharge = refundRoamingCharge;
	}

	public void setTransactionIdLong(long lTranId) {
		mTransactionIdLong = lTranId;
		mTransactionId=Long.toString(lTranId);
	}
	
	/**
	 * Returns the transactionID as a long.  If there is a problem it will be negative.
	 * @return
	 */
	public long getTransactionIdLong() {
		if (mTransactionIdLong == -1 ) {
			if (mTransactionId != null && !mTransactionId.equals("")) {
				try {
					mTransactionIdLong = Long.parseLong(mTransactionId);
				}
				catch (NumberFormatException ne) {
					mTransactionIdLong = -2;
				}
			}
		}
		return mTransactionIdLong;
	}
	public void setSubscriptionIdLong(long lSubId) {
		mSubscriptionIdLong = lSubId;
	}
	
	public long getSubscriptionIdLong() {
		if (mSubscriptionIdLong == -1) {
			if (mSubscriptionId != null) {
				try {
					mSubscriptionIdLong = Long.parseLong(mSubscriptionId);
				}
				catch (NumberFormatException ne) {
					mSubscriptionIdLong = -2;
				}
			}
		}

		return mSubscriptionIdLong;
	}
	public void setParentTransactionIdLong(long lParentTransactionId) {
		mParentTransactionIdLong = lParentTransactionId;
	}
	public long getParentTransactionIdLong() {
		if (mParentTransactionIdLong == -1) {
			if (mParentTransactionId != null) {
				try {
					mParentTransactionIdLong = Long.parseLong(mParentTransactionId);
				}
				catch (NumberFormatException ne) {
					mParentTransactionIdLong = -2;
				}
			}
		}
		return mParentTransactionIdLong;
	}

	//@hud STKHREQ13076
	public void setClientNetworkCode(String networkCodeStr) {
		mClientNetworkCode = networkCodeStr;
	}
	public String getClientNetworkCode() {
		return mClientNetworkCode;
	}

	public String getExternalField1() {
		return mExternalField1;
	}

	public void setExternalField1(String externalField1) {
		mExternalField1 = externalField1;
	}

	public String getExternalField2() {
		return mExternalField2;
	}

	public void setExternalField2(String externalField2) {
		mExternalField2 = externalField2;
	}

	public String getExternalTransId() {
		return mExternalTransId;
	}

	public void setExternalTransId(String externalTransId) {
		mExternalTransId = externalTransId;
	}

	public boolean isZeroCostIgnore() {
		return zeroCostIgnore;
	}

	public void setZeroCostIgnore(boolean zeroCostIgnore) {
		this.zeroCostIgnore = zeroCostIgnore;
	}

	//REMEDY 6065
	public DRMObject getDRMObject()
	{
		return mDRMObject;
	}

	//CR AffiliateID
	public void setAffiliateID (String affiliateID)
	{
		mAffiliateID = affiliateID;
	}

	public String getAffiliateID(){
		return mAffiliateID;
	}

	//CR-0869 Start
	/**
	 * Gets transaction partner ID
	 * @return String Partner ID
	 */
	public String getPartnerId() {
		return mPartnerId;

	}      

	/**
	 * Sets partner ID of the transaction
	 * @param partnerId The partner ID
	 */
	public void setPartnerId(String partnerId) {
		mPartnerId = partnerId;
	}

	/**
	 * Gets the aggregator of the transaction
	 * @return String Aggregator
	 */
	public String getAggregatorId() {
		return mAggregatorId;
	}

	/**
	 * Sets aggregator ID of the transaction
	 * @param aggregatorId The aggregator ID of the transaction
	 */
	public void setAggregatorId(String aggregatorId) {
		mAggregatorId = aggregatorId;
	}
	//CR-0869 End

	//CR-1209_1 - add receipient msisdn and product code
	public String getReceipientMsisdn()  
	{  
		return mRecipientMsisdn;  
	}

	public void setReceipientMsisdn(String receipientMsisdn)  
	{    
		mRecipientMsisdn = receipientMsisdn;	
	}

	public String getProductCode()  
	{  
		return mProductCode;  
	}

	public void setProductCode(String productCode)  
	{    
		mProductCode = productCode;	
	}

	// CR - ENHANCED BUNDLE REPORTING (Feb 2010)
	public void setSubPeriodStart (Date subPeriodStart)
	{
		mSubPeriodStart = subPeriodStart;
	}

	public Date getSubPeriodStart()
	{
		return mSubPeriodStart;
	}

	public void setSubPeriodEnd(Date subPeriodEnd)
	{
		mSubPeriodEnd = subPeriodEnd;
	}

	public Date getSubPeriodEnd()
	{
		return mSubPeriodEnd;
	}
	// CR - ENHANCED BUNDLE REPORTING - ends

	//CR1423
	/**
	 * @return the reIssue
	 */
	public int getReIssue() {
		return mReIssue;
	}

	/**
	 * @param reIssueNumber the reIssue to set
	 */
	public void setReIssue(int reIssue) {
		this.mReIssue = reIssue;
	}  
	//CR1423

	//CR-1448 - add merchant name and invoice text
	public String getMerchantName()  
	{  
		return mMerchantName;  
	}

	public void setMerchantName(String merchantName)  
	{    
		mMerchantName = merchantName;	
	}

	public String getInvoiceText()  
	{  
		return mInvoiceText;  
	}

	public void setInvoiceText(String invoiceText)  
	{    
		mInvoiceText = invoiceText;	
	}

	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	/**
	 * @return the mContainsReIssueService
	 */
	public boolean isContainsReIssueService() {
		return mContainsReIssueService;
	}

	/**
	 * @param reIssueService the mContainsReIssueService to set
	 */
	public void setContainsReIssueService(boolean reIssueService) {
		this.mContainsReIssueService = reIssueService;
	}  

	// CR 1643 - Pre-Pay Post-Pay Split
	public String getSpId()  
	{  
		return spId;  
	}

	public void setSpId(String spId)  
	{    
		this.spId = spId;	
	}

	public String getIsPrepay()  
	{  
		return isPrepay;  
	}

	public void setIsPrepay(String isPrepay)  
	{    
		this.isPrepay = isPrepay;	
	}
	// CR 1643 - Ends

	/**if this Transaction object is not a ERTransaction under the skin, this will probably be null	*/
	public String getMsisdn() {
		return mMsisdn;
	}

	/**CR 1887	*/
	public void setMsisdn(String Msisdn) {
		this.mMsisdn = Msisdn;
	}

	/**CR 2082	*/
	public String getPackageId() {
		return mPackageId;
	}

	/**CR 2082	*/
	public void setPackageId(String packageId) {
		this.mPackageId = packageId;
	}

	/**CR 2080	*/
	public String getModifyTransactionId() {
		return mModifyTransactionId;
	}

	/**CR 2080	*/
	public void setModifyTransactionId(String modifyTransactionId) {
		this.mModifyTransactionId = modifyTransactionId;
	}
	
	/**
	 * CR 2198
	 * @return the childSpId
	 */
    public String getChildSpId() {
		return childSpId;
	}
    
    /**
	 * CR 2198
	 * @return the spType
	 */
    public String getSpType() {
		return spType;
	}
    
    @Override
	public boolean equals(Object obj)	{
    	if (obj instanceof Transaction){
    		Transaction txn = (Transaction)obj;
    		return txn.getTransactionIdLong() == getTransactionIdLong();
    	}
    	return false;
    }

	@Override
	public int hashCode() {
		return new Long(getTransactionIdLong()).hashCode();
	}
	
	public boolean isRefundTransaction()	{
		return getType().isRefund();
	}

	/**
	 * @version       ER 8.0 P2
	 * @author        VFE  PS Team
	 * @date          13-Sep-2005
	 * @description  (Access Control)   setter for the "SuitabilityDecision" for the transaction object. **/
	public void setDRMObject(DRMObject drmobject) {
	    mDRMObject = drmobject;
	}
	
    /**
	 * CR 2255
	 * @return the mContentCategory
	 */
    public String getContentCategory() {
		return mContentCategory;
	}

}
