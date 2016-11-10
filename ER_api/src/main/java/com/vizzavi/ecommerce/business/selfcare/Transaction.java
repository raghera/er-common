package com.vizzavi.ecommerce.business.selfcare;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.charging.BaseAuthorization;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.CountryCode;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.RatingAttributes;
import com.vizzavi.ecommerce.common.ErCountry;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.rating.TaxRatedEvent;



@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Transaction implements Serializable	{
	
	/**
	 * Corresponds to one of 5 broad categories:Payment, Goodwill Credit, Refund, Modification, and Usage.<br/>
	 * TransactionType is more fine-grained - e.g. there are about 18 kinds of Modify transaction<br/>
	 * N.B. Payment and Usage both correspond to the same DB table.<br/>
	 */
	public static enum MetaType{Payment, Goodwill_Credit, Refund, Modification, Usage}
	
	private static final long serialVersionUID = 9215527645977333771L;
	private static Logger sLog = LoggerFactory.getLogger(Transaction.class);

	protected int balanceImpact;
	protected int mBalanceImpact;	//TODO fix this duplication...
	protected String mBearer; // Bearer Type
	protected Date mPurchaseDate;    // Creation date
	protected Date mLocalPurchaseDate;    // //CR1564-Utctimezone for diff region in country
	protected Calendar mLocalPurchaseDateCal;    // //CR1564-Utctimezone for diff region in country
	protected double mPurchaseRate;   // The rate purchasd
	protected double mTaxRate;        // tax rate
	protected ChargingResource mResource; // currency
	protected String mContentName;
	protected String mServiceId;        // RPS selectors
	protected long	mTransactionIdLong = -1;
	protected double mNextCycleDiscount;  // next cycle discount
	protected Date mRefundEnlargementDate;    // refund enlargement date
	protected String mRefundPaymentTransactionId = null;    // associated payment transaction with refund
	protected long mRefundPaymentTransactionIdLong = -1;
	protected TransactionType mType;      // name of transaction
	protected Subscription mSubscription;
	protected ModificationInfo mModInfo;

	protected int eventVolume;
	// VFE-ER8.0 (Record Undiscounted price on discounted purchases).  This field is added to Store the undiscounted price in the transaction.
	protected double mStandardRate;
	// VFE-ER8.0 (Record Undiscounted price on discounted purchases).  This field is added to Store the Tier Name in the transaction
	protected  String mTierName;
	protected  String mAccessChannel;		// VFE-ER8.0 P2 (Access Channel Reporting).  This field is added to Store the Access Channel in the transaction
	protected  String mPurchaseChannel;		// VFE-ER8.0 P2 (Purchase Channel Reporting).  This field is added to Store the Purchase Channel in the transaction	
	protected  String mDeviceId;	// VFE-ER8.0 P2 (Distribution Channel Reporting).  This field is added to Store the Device ID in the transaction
	protected int mSuitabilityDecision; //(VFE-PS, ER8 P2: Access Control). This field is added to Store the "Suitability Decision" in the transaction.
	protected long mParentTransactionIdLong = -1;
	private boolean mExpressflag = false;
	private String mPromoPrecode = null;	// this is the full promo code
	private String mPromoUniqueCode = null;

	protected String mExternalTransId;
	protected String mExternalField1 ;
	protected String mExternalField2 ;
	protected boolean zeroCostIgnore = false ;
	protected double usageTime = 0 ;	//usagetime of a volume service
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
//	protected  String mAssetID;
	//protected String mBearer; // Bearer Type
//	protected String mDonnerSubscriptionId = null;
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
	
	protected ErCountry country;
	
	public Transaction()	{
		mStatus = 1;
		sLog.warn("creating a txn without a type");
	}

	public Transaction(Transaction tran)	{
		this.mSessionId = tran.getSessionId();
		this.mPurchaseDate = tran.getPurchaseDate();
		this.mLocalPurchaseDate = tran.getLocalPurchaseDate();
		this.mLocalPurchaseDateCal = tran.getLocalPurchaseDateCal();
		this.mPurchaseRate = tran.getPurchaseRate();
		this.mTaxRate = tran.getTaxRate();
		this.mResource = tran.getPurchaseCurrency();

		//Remedy 6837, Bruno Meseguer, API interface was broken, contentName restored
		setContentName(tran.getContentName());

		this.mServiceId = tran.getServiceId();
		this.mAssetId     = tran.getAssetId();

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
		this.mSubscriptionIdLong = tran.getSubscriptionIdLong();
		// VFE-ER8.0 (Record Undiscounted price on discounted purchases).  This logic copies the standard rate and the tier name to the new Transaction Object.
		this.mStandardRate = tran.getStandardRate();
		this.mTierName     = tran.getTierName();

		this.mBearer = tran.getBearer();

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

		this.mClientId = tran.getClientId();
		this.mHostId = tran.getHostId();

		this.mBearer = tran.getBearer();
//		this.mDonnerSubscriptionId = tran.getDonnerSubscriptionId();

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
		mParentTransactionIdLong = tran.getParentTransactionIdLong();

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
		//Added for Ticket ET51
		this.mExternalField1 = tran.mExternalField1;
		this.mExternalField2 = tran.mExternalField2;

		this.mExternalTransId=tran.mExternalTransId;
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
			mSubscriptionIdLong = auth.getPackageSubscriptionIdLong();
		}
		sLog.warn("creating a transaction without a type");
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
		mStatus = status;

		// parsing here, throw as necessary
//		if (isNotBlank(subscriptionId)) {
//			mSubscriptionIdLong = Long.parseLong(subscriptionId);
//		}
		setSubscriptionId(subscriptionId);
//		if (isNotBlank(paymentTransId)) {
//			mTransactionIdLong = Long.parseLong(paymentTransId);
//		}
		setTransactionId(paymentTransId);
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
	}




	public void setPaymentType(int paymentType)	{
		mPaymentType = paymentType;
	}
	@Transient
	public int getPaymentType()	{
		return mPaymentType;
	}

	/**
        @deprecated use {@link #setSubscriptionIdLong} instead
	 */
	@Deprecated
	public void setSubscriptionId(String subscriptionId)	{
		if (isNotBlank(subscriptionId))
			mSubscriptionIdLong = Long.parseLong(subscriptionId);
	}
	/**
	 * returns null if the mSubscriptionId has not been set yet
	 * @return
	 */
	@Transient
	public String getSubscriptionId()	{

		if (mSubscriptionIdLong > 0)
			return Long.toString(mSubscriptionIdLong);
		else
			return null;
	}

	public void setAccessDevice(int accessDevice)	{
		mAccessDevice = accessDevice;
	}
	@Transient
	public int getAccessDevice()	{
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



	public void setType (TransactionType type)	{
		mType = type;
	}
	
	/**
	 * the {@link TransactionType} of this transaction 
	 * @return
	 */
	@Transient
	public TransactionType getType()	{
		return mType;
	}

	//2 methods for jpa only
	void setTxnTypeId(int id)	{
		mType = TransactionTypeEnum.getTransactionType(id);
	}
	
	@Transient
	int getTxnTypeId()	{
		return TransactionTypeEnum.getTransactionType(mType.getType()).getId();
	}
	
	/**
	 * mapped to time_stamp column
	 * @param purchaseDate
	 */
	public void setPurchaseDate (Date purchaseDate)	{
		mPurchaseDate = purchaseDate;
	}
	
	/**
	 * mapped to timestamp column.  
	 * @return
	 */
	@Transient
	public Date getPurchaseDate()	{
		return mPurchaseDate;
	}
	/**
	 * mapped to timestamp column. 
	 * @param date
	 */
	protected void setTimeStamp(Date date) {
		setPurchaseDate(date);
	}
	
	@Column(name="TIME_STAMP")
	public Date getTimeStamp()	{
		return getPurchaseDate();
	}
	
	
	//CR1564-start-Utctimezone for diff region in country
	public void setLocalPurchaseDate (Date date)	{
		mLocalPurchaseDate = date;
	}
	@Transient
	public Date getLocalPurchaseDate()	{
		return mLocalPurchaseDate;
	}

	public void setLocalPurchaseDate (Calendar date)	{
		mLocalPurchaseDateCal = date;
		mLocalPurchaseDate = date.getTime();
	}
	
	@Transient
	public Calendar getLocalPurchaseDateCal()	{
		return mLocalPurchaseDateCal;
	}
	//CR1564-end
	/**
	 * sets the Purchase rate - which should include tax
	 * @param purchaseRate
	 */
	public void setPurchaseRate (double purchaseRate)	{
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

	/**
	 * for a refund, this will be the refund amount 
	 * @return
	 */
	@Transient
	public double getPurchaseRate()	{
		return mPurchaseRate;
	}


	@Transient
	public double getPurchaseNetRate()	{
		return mPurchaseRate/(1+this.getTaxRate());
	}
//ET-330
//	public void setTaxRate(double taxRate)	{
//		mTaxRate = taxRate;
//	}
	
	public void setTaxRate(Double taxRate)	{
		if(taxRate != null)
			mTaxRate = taxRate;
	}
	
	@Transient
	public double getTaxRate()	{
		return mTaxRate;
	}


	/**
	 * @author hud
	 * @see setChargingResource
	 */
	public void setPurchaseCurrency(ChargingResource resource)	{
		mResource = resource;
	}

	/**
	 * @author hud
	 *
	 */
	public void setChargingResource(ChargingResource resource)	{
		mResource = resource;
	}

	/**
	 * same as {@link #getChargingResource}
	 * @return
	 * @see getChargingResource()
	 */
	@Transient
	public ChargingResource getPurchaseCurrency()	{
		return mResource;
	}

	/**
	 * same as {@link #getPurchaseCurrency}
	 * @return
	 */
	@Transient
	public ChargingResource getChargingResource()	{
		return mResource;
	}

	/**
	 * @deprecated use {@link #setTransactionIdLong} instead
	 */
	@Deprecated
	public void setTransactionId(String transactionId)	{
		if (isNotBlank(transactionId))
			mTransactionIdLong=Long.parseLong(transactionId);
	}
	/**
	 * Returns the transactionId as a string.  returns null if the ID has not been set (ie is -1)<br/>
	 * use {@link #getTransactionIdLong()} instead
	 * @see {@link #getTransactionIdLong()}
	 */
	@Transient
	public String getTransactionId()	{
		if (mTransactionIdLong > 0)
			return String.valueOf(mTransactionIdLong);
		else
			return null;
	}

	/**
        Return the percentage discount off the real price
	 */
	public void setNextCycleDiscount(Double nextCycleDiscount)	{
		if(nextCycleDiscount !=null)
		   mNextCycleDiscount = nextCycleDiscount;
	}
	
	@Transient
	public double getNextCycleDiscount()	{
		return mNextCycleDiscount;
	}

	/**
        Return the percentage discount off the real price
	 */
	@Transient
	public double getNextCycleDiscountPercent()	{
		return mNextCycleDiscount * 100;
	}


	//Remedy 6837, Bruno Meseguer, API interface was broken, contentName restored
	//Note: below we can see ContentName commented out, that is wrong, it brakes the API
	@Transient
	public String getContentName()	{
		return getContentDescription();
	}

	public void setContentName(String ContentName)	{
		setContentDescription(ContentName);
	}


	public void setServiceId(String ServiceId)	{
		mServiceId = ServiceId;
	}
	@Transient
	public String getServiceId()	{
		//if (mContentName != null)
		return mServiceId;
		//else return "N/A";
	}


	@Transient
	public Subscription getSubscription()	{
		return mSubscription;
	}

	@Transient
	public Calendar getEventDateTime() {
		if (mMatchingAttributes==null) return null;
		return mMatchingAttributes.getEventDateTime();
	}

	@Transient
	public double getEventUnits() {
		double rv =0.0;
		if (mMatchingAttributes!=null) rv = mMatchingAttributes.getEventUnits();
		return rv;
	}
//ET-330
//	public void setEventUnits(double eventUnits) {
//		if (mMatchingAttributes!=null)
//			mMatchingAttributes.setEventUnits(eventUnits);
//	}
	
	public void setEventUnits(Double eventUnits) {
		if (mMatchingAttributes!=null)
			mMatchingAttributes.setEventUnits(eventUnits);
	}


	// Customer care data
	public void setCsrId(String csrId)	{
		mCsrId = csrId;
	}
	@Column(name="csr_id")
	public String getCsrId()	{
		return mCsrId;
	}

	public void setDescription(String description)	{
		mDescription = description;
	}
	
	@Transient
	public String getDescription()	{
		return mDescription;
	}

	public void setReason(String reason)	{
		mReason = reason;
	}
	
	@Transient
	public String getReason()	{
		return mReason;
	}
	public void setStatus(int status)	{
		mStatus = status;
	}
	
	/**
	 * eg 101 for COMPLETED, etc
	 * @return
	 */
	@Transient
	public int getStatus()	{
		return mStatus;
	}

	@Transient
	public boolean isSuccess()	{
		//if ( (getStatus()==TransactionStatus.COMPLETED) || (getStatus()==TransactionStatus.PECS_PAY_STATUS_AUTH_ACCEPTED) ) {
		if(mStatus==TransactionStatus.COMPLETED) {
			return true;
		}
		else return false;
	}

	public void setAuthCode(String authCode)	{
		mAuthCode = authCode;
	}
	
	@Transient
	public String getAuthCode()	{
		return mAuthCode;
	}

	public void setPaymentStatus(int paymentStatus)	{
		mPaymentStatus = paymentStatus;
	}
	
	@Transient
	public int getPaymentStatus()	{
		return mPaymentStatus;
	}

	public void setPaymentErrorId(String errorId)	{
		mErrorId = errorId;
	}
	
	/**
	 * If the OpCo has implemented the ERIf developer guidelines of March 2013, this value will be one of the following:
        <ul>
        <li>OK</li>
        <li>CONTENT_BLOCKED</li>
        <li>USER_SPEND_LIMIT</li>
        <li>SPEND_LIMIT</li>
        <li>INSUFFICIENT_FUNDS</li>
        <li>USER_SUSPENDED</li>
        <li>AMOUNT_INVALID</li>
        <li>REJECTED_OTHER</li>
        <li>USER_NOT_FOUND</li>
        <li>USER_INVALID</li>
        <li>USER_BARRED</li>
        <li>DENIED_OTHER</li>
        <li>VALIDATION_ERROR</li>
        <li>SYSTEM_ERROR</li>
        </ul>
        @see ResponseType 
	 * @return
	 */
	@Transient
	public String getPaymentErrorId()	{
		return mErrorId;
	}

	public void setPaymentErrorDescription(String errorDescription)	{
		mErrorDescription = errorDescription;
	}
	
	@Transient
	public String getPaymentErrorDescription()	{
		return mErrorDescription;
	}


	public void setRefundEnlargementDate(Date refundEnlargementDate)	{
		mRefundEnlargementDate = refundEnlargementDate;
	}
	@Transient
	public Date getRefundEnlargementDate()	{
		return mRefundEnlargementDate;
	}

	/**
        This is the associated payment transaction if that this refund is associated with
	 */
	public void setRefundPaymentTransactionId(String refundPaymentTransactionId )	{
		mRefundPaymentTransactionId = refundPaymentTransactionId;
	}
	/**the ID of the  payment transaction associated with refund
	 */
	@Transient
	public String getRefundPaymentTransactionId()	{
		if (mRefundPaymentTransactionId == null) {
			if (mRefundPaymentTransactionIdLong > 0) {
				mRefundPaymentTransactionId = Long.toString(mRefundPaymentTransactionIdLong);
			}
		}
		return mRefundPaymentTransactionId;
	}

	//@hud RFRFRF
	public void setRefundPaymentTransactionIdLong(long id)	{
		mRefundPaymentTransactionIdLong = id;

		//Remedy 7004, Bruno Meseguer, compatibility with ER7 was broken
		mRefundPaymentTransactionId = Long.toString(id);
	}

	/**
	 * the id of the associated payment txn
	 * @return
	 */
	@Transient
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
	public String toString()	{
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		buf.append("tranid=" + mTransactionIdLong + ", ");
		buf.append("type=" + mType + ", ");
		if (mPurchaseDate!=null) buf.append("purchasedate=" + mPurchaseDate + ", ");
		else buf.append("purchasedate= null, ");
		buf.append("purchaserate=" + mPurchaseRate + ", ");
		if (mResource!=null) buf.append("resource=" + mResource + ", ");
		else buf.append("resource= null");
		buf.append("service id=" + mServiceId + ", ");
		buf.append("asset="+mAssetId);
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
		// VFE-ER8.0 - P2 (Access Channel Reporting).
		buf.append("AccessChannel = " +this.mAccessChannel);

		// VFE-ER8.0 - P2(Purchase Channel Reporting).
		buf.append("PurchaseChannel = " +this.mPurchaseChannel);

		// VFE-ER8.0 - P2 (Distribution Channel Reporting).
		buf.append("DeviceID = " +this.mDeviceId);

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
	@Transient
	public String getNonRefundableDescription(){
		return this.mNonRefundableDescription;
	}

	/**
	 * Used to assess if the transaction is refundable
	 * @return false if transaction not refundable
	 * @since ER 5.1
	 */
	@Transient
	public boolean getRefundable(){
		return this.mRefundable;
	}

	/**
	 * Used to assess if the transaction is refundable
	 * @return false if transaction not refundable
	 * @since ER 5.1
	 */
	@Transient
	public boolean isRefundable(){
		return this.mRefundable;
	}


	/**
        Returns extra information if a modification transaction
	 */
	public void setModificationInfo(ModificationInfo modInfo)	{
		mModInfo = modInfo;
	}
	
	@Transient
	public ModificationInfo getModificationInfo()	{
		return mModInfo;
	}

	@Transient
	public double getTaxAmount()	{
		int round_nth_decimal =  ConfigProvider.getPropertyAsInteger(ROUND_NTH_DECIMAL, 4);
		double rv = -1;
		//		if(ConfigProvider.getProperty(ROUND_NTH_DECIMAL)!=null){
		//			round_nth_decimal = Integer.parseInt(ConfigProvider.getProperty(ROUND_NTH_DECIMAL));
		//		}
		if (getPurchaseCurrency() != null && getPurchaseCurrency().isCurrency()) {
			double netAmount = getPurchaseRate()/(1 + getTaxRate());
			rv = netAmount * getTaxRate();
		}
		return roundDouble(rv, round_nth_decimal);
	}


	public void setResourceBalances(ResourceBalance[] balance)	{
		mBalances = balance;
	}
	
	@Transient
	public ResourceBalance[] getResourceBalances()	{
		return mBalances;
	}
	/**
	 * @return
	 */
	public void setMatchingAttributes(RatingAttributes matchingAttrs)	{
		mMatchingAttributes = matchingAttrs;
	}
	@Transient
	public RatingAttributes getMatchingAttributes() {
		return mMatchingAttributes;
	}

	/**
	 * @return
	 */
	public void setClientId (String clientId)	{
		mClientId = clientId;
	}

	@Transient
	public String getClientId() {
		return mClientId;
	}

	/**
	 * @return
	 */
	//@hud
	//Getter Setter methods for TransactionType
	public void setHostId (String hostId)	{
		mHostId = hostId;
	}

	@Transient
	public String getHostId() {
		return mHostId;
	}

	/**
	 * @return
	 */
	@Transient
	public double getNextCycleDiscountValue() {
		return mNextCycleDiscount;
	}

	/**
	 * @return
	 */
	public void setPaymentInfo(String paymentInfo)	{
		mPaymentInfo = paymentInfo;
	}
	
	@Transient
	public String getPaymentInfo()	{
		return mPaymentInfo;
	}

	//returns the Bearer type value
	public void setBearer(String bearer)	{
		mBearer = bearer;
	}
	
	@Transient
	public String getBearer()	{
		return mBearer;
	}

	/**
	 * @return
	 */
	public void setContentDescription(String contentDescription)	{
		mContentDescription = contentDescription;

		//Remedy 6837, Bruno Meseguer, API interface was broken, contentName restored
		mContentName 		= contentDescription;
	}
	
	@Transient
	public String getContentDescription()	{
		return mContentDescription;
	}
	
	@Transient
	public static final double roundDouble(double d, int nthDecimal)	{
		return Math.round(d * Math.pow(10, nthDecimal)) / Math.pow(10,nthDecimal);
	}

	public void setSessionId(String sessionId)	{
		mSessionId = sessionId;
	}

	@Transient
	public String getSessionId()	{
		return mSessionId;
	}


	public void setAssetId (String assetId)	{
		mAssetId = assetId ;
	}
	
	@Transient
	public String getAssetId(){
		return mAssetId;
	}


	
	/**
	 */
	@Transient
	public String getAssetID(){
		return mAssetId;
	}


	public void setBalanceImpact(int balanceImpact)	{
		mBalanceImpact = balanceImpact;

		//Remedy 6842, Bruno Meseguer, API interface was broken, balanceImpact restored
		this.balanceImpact = balanceImpact;
	}
	
	@Transient
	public int getBalanceImpact()	{
		return mBalanceImpact;
	}


	public void setSubRecords(List<TransactionSubRecord> subRecords) {
		mSubRecords = subRecords;
	}
	
	@Transient
	public List<TransactionSubRecord> getSubRecords()	{
		return mSubRecords;
	}
	
	/**
	 * @version      ER 8.0
	 * @author       VFE ? PS Team
	 * @date         15-Aug-2005
	 * @description  (Record Undiscounted price on discounted purchases)   The purpose of this method is to get the undiscounted price from the transaction object. **/
	public void setStandardRate (Double standardRate)	{
		if (standardRate!=null) this.mStandardRate = standardRate;
		//this. mStandardRate = standardRate;
	}
	
	@Transient
	public double getStandardRate()	{
		return mStandardRate;
	}
	/**
	 * @version       ER 8.0
	 * @author        VFE ? PS Team
	 * @date          15-Aug-2005
	 * @description  (Record Undiscounted price on discounted purchases)   The purpose of this method is to get the Date Time Model from the transaction object. **/

	public void setTierName(String tierName)	{
		mTierName = tierName;
	}
	
	@Transient
	public String getTierName()	{
		return mTierName;
	}

	/**
	 * @version       ER 8.0 - P2
	 * @author        VFE ? PS Team
	 * @date          18-Sept-2005
	 * @description  (Access Channel Reporting)   The purpose of this method is to get the Access Channel from the transaction object. **/

	public void setAccessChannel(String accessChannel)	{
		mAccessChannel = accessChannel;
	}
	
	@Transient
	public String getAccessChannel()	{
		return mAccessChannel;
	}

	/**
	 * @version       ER 8.0 - P2
	 * @author        VFE ? PS Team
	 * @date          18-Sept-2005
	 * @description  (Purchase Channel Reporting)   The purpose of this method is to get the Purchase Channel from the transaction object. **/

	public void setPurchaseChannel(String purchaseChannel)	{
		mPurchaseChannel = purchaseChannel ;
	}
	
	@Transient
	public String getPurchaseChannel()	{
		return mPurchaseChannel;
	}

	/**
	 * @version       ER 8.0 - P2
	 * @author        VFE ? PS Team
	 * @date          18-Sept-2005
	 * @description  (Distribution Channel Reporting)   The purpose of this method is to get the Device ID from the transaction object. **/

	public void setDeviceId(String deviceId)	{
		mDeviceId = deviceId ;
	}
	
	@Transient
	public String getDeviceId()	{
		return mDeviceId;
	}


	
	/**
	 * @param deviceId
	 */
	@Transient
	public String getDeviceID()	{
		return mDeviceId;
	}


	/**
	 * @version       ER 8.0 P2
	 * @author        VFE ? PS Team
	 * @date          13-Sep-2005
	 * @description  (Access Control)   getter for the "SuitabilityDecision" from the transaction object. **/
	public void setSuitabilityDecision(int suitabilityDecision)	{
		mSuitabilityDecision = suitabilityDecision;
	}
	
	@Transient
	public int getSuitabilityDecision()	{
		return this.mSuitabilityDecision;
	}

	/**
	 * @param parentTransactionId
	 * @deprecated
	 */
	@Deprecated
	public void setParentTransactionId(String parentTransactionId)	{
		if (isNotBlank(parentTransactionId))
			mParentTransactionIdLong = Long.parseLong(parentTransactionId);
	}
	/**
	 * returns null if the transactionId has not been set yet
	 * @return
	 */
	@Transient
	public String getParentTransactionId()	{
		if (mParentTransactionIdLong > 0) 
			return  Long.toString(mParentTransactionIdLong);
		else
			return null;
	}

	@Transient
	public boolean hasParent() {
		return mParentTransactionIdLong>0;
	}



	// Getter and setter methods for express flag
	@Transient	//TODO
	public boolean getExpressFlag()	{
		return mExpressflag;
	}

	public void setExpressFlag(Boolean expressflag)	{
		if(expressflag != null)
			mExpressflag = expressflag;
	}


	public void setRateIdentifier (String rateIdentifier)	{
		mRateIdentifier = rateIdentifier;
	}
	
	@Transient
	public String getRateIdentifier()	{
		return mRateIdentifier;
	}


	/**
	 * To generate complex Id for Payment Handlers
	 * @param locale
	 * @return
	 * @throws EcommerceException
	 */
	@Transient
	public String getComplexTransactionId(Locale locale) throws EcommerceException	{
		// Format : C001/123456Z
		String rv = null;
		CountryCode countryCode = null;
		try {countryCode = CountryCode.getCountryCode(locale);}
		catch (Exception e) {sLog.error("Transaction:getComplexTransactionId failed: "+e);}

		if (locale !=null  && countryCode!=null && this.mType!=null){
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
					rv = rv + transactionIdPrefix +"/"+this.mTransactionIdLong+"Z";
				}
				else
				{
					rv = rv + (countryCode.getCode()+1)+"/"+this.mTransactionIdLong+"Z";
				}
			}
		}
		else {
			throw new EcommerceException ("ERTransaction:getComplexTransactionId: failed to create complex Transaction Id");
		}

		return rv;

	}


	@Transient
	public String getPromoPrecode() {
		return mPromoPrecode;
	}
	
	public void setPromoPrecode(String precode) {
		mPromoPrecode = precode;
	}
	
	@Transient
	public String getPromoUniqueCode() {
		return mPromoUniqueCode;
	}
	
	public void setPromoUniqueCode(String uniqueCode) {
		mPromoUniqueCode = uniqueCode;
	}


	@Transient
	public double getUsageTime() {
		return usageTime;
	}


	public void setUsageTime(double usageTime) {
		this.usageTime = usageTime;
	}



	public void setTransactionIdLong(long lTranId) {
		mTransactionIdLong = lTranId;
	}
	
	/**
	 * Returns the transactionID as a long.  If there is a problem it will be negative.
	 * @return
	 */
	@Transient
	public long getTransactionIdLong() {
		return mTransactionIdLong;
	}
	
	public void setSubscriptionIdLong(long lSubId) {
		mSubscriptionIdLong = lSubId;
	}
	
	@Transient
	//@Column(name="subscription_obj_id")
	public long getSubscriptionIdLong() {
		return mSubscriptionIdLong;
	}
	
	public void setParentTransactionIdLong(Long lParentTransactionId) {
		if (lParentTransactionId!=null)
			mParentTransactionIdLong = lParentTransactionId;
	}
	
	@Transient
	public long getParentTransactionIdLong() {
		return mParentTransactionIdLong;
	}


	@Transient
	public String getExternalField1() {
		return mExternalField1;
	}

	/**
	 * truncated to 512 characters as part of MQC 11980 - change field length from 255 to 512
	 * @param externalField1
	 */
	public void setExternalField1(String externalField1) {
		mExternalField1 = StringUtils.left(externalField1, 512);
	}
	
	@Transient
	public String getExternalField2() {
		return mExternalField2;
	}

	/**
	 * truncated to 512 characters as part of MQC 11980 - change field length from 255 to 512
	 * @param externalField2
	 */
	public void setExternalField2(String externalField2) {
		mExternalField2 = StringUtils.left(externalField2, 512);
	}

	@Transient
	public String getExternalTransId() {
		return mExternalTransId;
	}

	/**
	 * truncated to 255 characters
	 * @param externalTransId
	 */
	public void setExternalTransId(String externalTransId) {
		mExternalTransId = StringUtils.left(externalTransId, 255);
	}

	@Transient
	public boolean isZeroCostIgnore() {
		return zeroCostIgnore;
	}

//ET-330
//	public void setZeroCostIgnore(boolean zeroCostIgnore) {
//		this.zeroCostIgnore = zeroCostIgnore;
//	}
	
	public void setZeroCostIgnore(Boolean zeroCostIgnore) {
		if(zeroCostIgnore !=null)
		   this.zeroCostIgnore = zeroCostIgnore;
	}


	//CR AffiliateID
	public void setAffiliateID (String affiliateID)	{
		mAffiliateID = affiliateID;
	}

	@Transient
	public String getAffiliateID(){
		return mAffiliateID;
	}

	//CR-0869 Start
	/**
	 * Gets transaction partner ID
	 * @return String Partner ID
	 */
	@Transient
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
	@Transient
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
	@Transient
	public String getReceipientMsisdn()  {  
		return mRecipientMsisdn;  
	}

	public void setReceipientMsisdn(String receipientMsisdn)  	{    
		mRecipientMsisdn = receipientMsisdn;	
	}

	@Transient
	public String getProductCode()	{  
		return mProductCode;  
	}

	public void setProductCode(String productCode)  	{    
		mProductCode = productCode;	
	}

	// CR - ENHANCED BUNDLE REPORTING (Feb 2010)
	public void setSubPeriodStart (Date subPeriodStart)	{
		mSubPeriodStart = subPeriodStart;
	}

	@Transient
	public Date getSubPeriodStart()	{
		return mSubPeriodStart;
	}

	public void setSubPeriodEnd(Date subPeriodEnd)	{
		mSubPeriodEnd = subPeriodEnd;
	}

	@Transient
	public Date getSubPeriodEnd()	{
		return mSubPeriodEnd;
	}
	// CR - ENHANCED BUNDLE REPORTING - ends

	//CR1423
	/**
	 * @return the reIssue
	 */
	@Transient
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
	@Transient
	public String getMerchantName()  	{  
		return mMerchantName;  
	}

	public void setMerchantName(String merchantName)  	{    
		mMerchantName = merchantName;	
	}

	@Transient
	public String getInvoiceText()  	{  
		return mInvoiceText;  
	}

	public void setInvoiceText(String invoiceText)  	{    
		mInvoiceText = invoiceText;	
	}

	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	/**
	 * @return the mContainsReIssueService
	 */
	@Transient
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
	@Transient
	public String getSpId()  	{  
		return spId;  
	}

	public void setSpId(String spId)  	{    
		this.spId = spId;	
	}

	@Transient
	public String getIsPrepay()  	{  
		return isPrepay;  
	}

	public void setIsPrepay(String isPrepay) 	{    
		this.isPrepay = isPrepay;	
	}
	// CR 1643 - Ends

	/**if this Transaction object is not a ERTransaction under the skin, this will probably be null	*/
	@Transient
	public String getMsisdn() {
		return mMsisdn;
	}

	/**CR 1887	*/
	public void setMsisdn(String Msisdn) {
		this.mMsisdn = Msisdn;
	}

	/**CR 2082	*/
	@Transient
	public String getPackageId() {
		return mPackageId;
	}

	/**CR 2082	*/
	public void setPackageId(String packageId) {
		this.mPackageId = packageId;
	}

	/**CR 2080	*/
	@Transient
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
	@Transient
	public String getChildSpId() {
		return childSpId;
	}
    
    /**
	 * CR 2198
	 * @return the spType
	 */
	@Transient
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
	
	@Transient
	public boolean isRefundTransaction()	{
		return getType().isRefund();
	}

	
    /**
	 * CR 2255
	 * @return the mContentCategory
	 */
	@Transient
	public String getContentCategory() {
		return mContentCategory;
	}
    /**
     * CR 2198
	 * @param childSpId the childSpId to set
	 */
    protected void setChildSpId(String childSpId) {
		this.childSpId = childSpId;
	}
    
    /**
     * CR 2198
   	 * @param spType the spType to set
   	 */
    protected void setSpType(String spType) {
  		this.spType = spType;
  	}
    
    /**
     * CR 2255
   	 * @param contentCategory the mContentCategory to set
   	 */
    protected void setContentCategory(String contentCategory) {
  		this.mContentCategory = contentCategory;
  	}
	
    /**
	 *  one of Payment, Refund, Modification, Goodwill_Credit.
	 * @author matt
	 * @return {@link MetaType}
	 */
    @Transient
    public MetaType getMetaType()	{
    	return getType().getMetaType();
    }

 
}
