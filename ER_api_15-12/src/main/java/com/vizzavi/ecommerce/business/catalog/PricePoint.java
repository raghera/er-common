package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.internal.BalanceImpact;
import com.vizzavi.ecommerce.business.catalog.internal.BalanceImpacts;
import com.vizzavi.ecommerce.business.catalog.internal.PricePointTier;
import com.vizzavi.ecommerce.business.charging.BaseAuthorization;
import com.vizzavi.ecommerce.business.charging.PurchaseAuthorization;
import com.vizzavi.ecommerce.business.charging.UsageAuthorization;
import com.vizzavi.ecommerce.business.common.ChargingMethod;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.Constants;
import com.vizzavi.ecommerce.business.common.Duration;
import com.vizzavi.ecommerce.business.common.ErCoreConst;
import com.vizzavi.ecommerce.business.common.PeriodValue;
import com.vizzavi.ecommerce.business.common.RatingAttributes;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;
import com.vodafone.config.ConfigProvider;

/**
 * A PricePoint represents a RatePlan in the Infranet price plan.<br/>
 *
 * Each price point has a number of pricing attributes. <br/>
 *
 * The following are usage price attributes:
 *  <ul><li>Channel</li>
 *   <li>  SupplierId</li></ul>
 *
 * The following are purchase pricing attributes:
 *    <ul><li>DurationCode</li>
 *    <li> ChargingMethod</li>
 *    <li> PromoCode</li>
 *    <li> UserGroup</li></ul>
 *
 * The following are both usage and purchase pricing attributes
 *    <ul><li> AccessDevice</li>
 *    <li> PaymentType</li></ul>
 *
 */
@Entity
@Access(AccessType.FIELD)
public class PricePoint extends RatingAttributes implements Serializable, CatalogBean	{

	protected static final Logger log = LoggerFactory.getLogger(PricePoint.class);

	private static final long serialVersionUID = 7649541222744453297L;

	static final String ROUND_NTH_DECIMAL = "ROUND_NTH_DECIMAL";
	public static final String PACKAGE_PRICEPOINT_ID_PREFIX = "package:"; //[1] MOD
	public static final String CONTENT_PRICEPOINT_ID_PREFIX = "content:"; //[2] MOD


	//@lle STKHREQ242 Grace, suspension period, retry frequency
	@Transient
	private PeriodValue gracePeriod = null;
	@Transient
	private PeriodValue suspensionPeriod = null;
	@Transient
	private PeriodValue retryFrequency = null;


	//@hud STKHREQ36 Micro Service
	private double mAccessDuration		= -1;

	/**
        The identifier of the price point
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="`KEY`")
	protected Long mKey;

	protected String mId;

	protected String mContentId = Constants.STRING_MATCH_ALL;
	protected String mPackageId = Constants.STRING_MATCH_ALL;

	/** The price/token price of the package */
	protected double mRateWithoutTax = -9999;


	//TODO remove this ResourceBalance array!!  

	/**
	 * each ResourceBalance contains a ChargingResource, a balance, and a few other things
	 */
	@Transient
	protected ResourceBalance[] mBalances;
	/**
	 * a list of BalanceImpact objects, each of which contains a ChargingResource and a few other things
	 */
	protected BalanceImpacts mImpacts = new BalanceImpacts();

	//TODO remove this field
	//PPM136861 refactoring aL. START
	/**
	 * replaces map of tiers.  contains a list of BalanceImpact objects, each of which contains a ChargingResource and a few other things
	 */
	@Transient private PricePointTier mPricePointTier = new PricePointTier();


	//extra fields for hibernate
	@ManyToOne(optional=false, targetEntity=CatalogPackage.class, fetch=FetchType.LAZY)	
	private CatalogPackage pack;
	
	/**
        Set to true if the price point is not valid anymore and is not in the priceplan
	 */
	protected boolean mIsArchived = false;


	protected String mGlid = "";


	protected Date mPurchaseExpiryDate;

	protected Date mPurchaseStartDate;


	/** Custom fields for pricing text */
	private String mPricingText1 = "";
	private String mPricingText2 = "";

	/** The list of pricing text1 in multiple languages including the default language.
	 * Key is the language code.
	 * Since ER9 **/
	@Transient
	protected Map<String, String> pricingTextList1 = null;

	/** The list of pricing text2 in multiple languages including the default language.
	 * Key is the language code.
	 * Since ER 9**/
	@Transient
	protected Map<String, String> pricingTextList2 = null;

	/** custom fields defined in catalog using <custom_field> tag */
	@Transient
	protected Map<String, String> mCustomFields = new HashMap<String, String>();

	protected double mStandardRate = -1;

	/**
	 * The linked price point id associated with the price point, used for selection of renewal package for trial packages
	 * @since ER 5.1
	 */
	protected String mPricepointIdLink;

	/** ADDED FOR EGYPT ER6 STUB **/
	protected boolean mForcedPurchase=false;

	/** ADDED FOR EGYPT ER6 STUB **/
	protected boolean mDuplicateSubscription=true;

	protected Date mPurchaseFixedExpiryDate;


	/** ADDED FOR EGYPT ER6 STUB **/
	public static final String INTERACTIVE_NONE= "NONE";

	/** ADDED FOR EGYPT ER6 STUB **/
	public static final String INTERACTIVE_ALL= "ALL";

	/** ADDED FOR EGYPT ER6 STUB **/
	public static final String INTERACTIVE_USAGE= "USAGE";

	/** ADDED FOR EGYPT ER6 STUB **/
	protected String mInteractiveFlag = INTERACTIVE_NONE;

	/** ADDED FOR EGYPT ER7 STUB **/
	protected boolean mReserveOnly=false;

	/** ADDED FOR ER6.5 STUB **/
	protected int mMinSubPeriod = -1;

	/** ADDED FOR ER6.5 STUB **/
	protected double mPenaltyCharges=0.0;

	/** ADDED FOR ER6.5 STUB **/
	protected boolean mCancellation=false;

	/** ADDED FOR ER6.5 STUB **/
	public static final String MONTHEND_SUBSCRIPTION_NULL = "NULL";

	/** ADDED FOR ER6.5 STUB **/
	public static final String MONTHEND_SUBSCRIPTION_REDUCED = "REDUCED";

	/** ADDED FOR ER6.5 STUB **/
	public static final String MONTHEND_SUBSCRIPTION_ENLARGED = "ENLARGED";

	/** ADDED FOR ER6.5 STUB **/
	protected String mMonthEndSubscription = PricePoint.MONTHEND_SUBSCRIPTION_NULL;

	/** ADDED FOR EGYPT ER6.5 STUB **/
	protected boolean mHistoric = false;

	/** ADDED FOR ER6.5 STUB **/
	protected long mFixedRecurrence;

	/** ADDED FOR ER6.5 STUB **/
	protected boolean mReceipting=false;


	public static final String RECEIPTING_ATTRIBUTE_NONE= "NULL";
	public static final String RECEIPTING_ATTRIBUTE_EXPRESS= "Express";
	public static final String RECEIPTING_ATTRIBUTE_NON_EXPRESS= "Non-Express";
	public static final String RECEIPTING_ATTRIBUTE_ALL= "All";


	/** ADDED FOR ER6.5 STUB **/
	protected String mReceiptingAttribute=PricePoint.RECEIPTING_ATTRIBUTE_NONE;

	//[13] Mod Start
	/** ADDED FOR ER8 STUB **/
	protected String mPaymentHandler=PricePoint.PAYMENT_HANDLER_NONE;
	public static final String PAYMENT_HANDLER_NONE= "NULL";
	public static final String PAYMENT_HANDLER_SUBMIT= "Submit";
	
	/**  ADDED FOR ET233: Add Zero Price Payment Handler Suppression to Pricepoint */
	public static final String PAYMENT_HANDLER_SUPPRESS= "Suppress";
	//[13] Mod End

	/** ADDED FOR EGYPT ER7 STUB **/
	protected int mOrder;

	/** Remedy 4945 **/
	protected boolean mFixedRecurringPricePoint=false;

	/** ADDED FOR ER8 PH2 STUB **/
	protected boolean mBasePricePoint = false;

	//[4] STKHREQ13113 Start
	/** The first pricing text template name. **/
	protected String pricingTextTemplateName1 = null;

	/** The second pricing text template name. **/
	protected String pricingTextTemplateName2 = null;

	/** The first translated pricing text from the first pricing text template text. **/
	protected String translatedPricingText1 = null;

	/** The second translated pricing text from the second pricing text template text. **/
	protected String translatedPricingText2 = null;

	protected String translatedPricingText =null ;
	//[4] STKHREQ13113 End

	/** ER9.0 STKHREQ 13022 @ansarit **/
	protected long usageTime = 0;

	/** ER9.0 STKREQ 66**/
	protected int recurrenceDay = -1;
	protected boolean alignWithExternal = false;

	//Roaming START
	protected String pricingTextTemplateNameRoaming=null;
	protected double onFootPrintPrice = 0;
	protected double offFootPrintPrice = 0;

	protected boolean isZeroCostIgnore = false;

	//CR-0978 Location Services
	protected boolean hideForPurchaseOptions = false;

	//REMEDY 6968
	static final String ROUND_NTH_EXPRESS_PATTERN_DEFAULT = "0.00";
	static final String ROUND_NTH_EXPRESS_PATTERN_INTEGER = "0.##";
	private String ROUND_NTH_EXPRESS_PATTERN;

	//Fair Usage Start
	private int fairUsageLimit = -1;
	private int fairUsagePeriod = -1;
	private String fairUsagePeriodUnit = "Day(s)";
	// Fair Usage Ends

	// CR-0095 RBT START 
	protected int mExtensionPeriod;
	// CR-0095 RBT END

	//ES FUP Enhancement CR Start
	//This flag is only applicable to service pricepoint and is only set to true when
	//Fair Usage Policy is defined at package pricepoint level and you want to include this
	//Service pricepoint usage to count towards the pcakage fair usage policy.
	protected boolean includeServiceForPackageFUP = false;
	//ES FUP Enhancement CR End

	//MQC 5654 - add Tax object for real time tax rate calculation
	protected Tax mTax = null;

	//CR1564 -Utctimezone for diff region in country - set to true if you want to disable dyanamic calculation of values
	protected boolean useStaticValues = false;

	//CR1429 Always validate MSISDN puring purchase
	protected boolean alwaysValidateMsisdn = false;

	//CR CTB-1 Advanced Linked Pricepoint - the number of renewals of a pricepoint until the subscription is moved to the linked pricepoint
	//i.e. if this value is 5, then on the fifth renewal of the pricepoint, the subscripiton is moved to the linked pricepoint
	protected int renewalsUntilLinkedPricepoint = -1;
	//CR Advanced Linked Pricepoint - true if pricepoint is a link pricepoint from a TRIAL pricepoint
	protected boolean linkedByTrialPricepoint = false;
	

	void init(PricePoint pt) {
		init(pt, new Date());
	}

	//CR1564 -Utctimezone for diff region in country
	void init(PricePoint pt, Date date)	{
		mId = pt.mId;
		mContentId = pt.mContentId;
		mPackageId = pt.mPackageId;
		mTaxRate = pt.getTaxRate(date);
		mTaxCode = pt.getTaxCode();
		mPurchaseStartDate = pt.getStartDate();
		mPurchaseExpiryDate = pt.getExpiryDate();
		//[1] Mod start
		mPurchaseFixedExpiryDate = pt.getFixedExpiryDate();
		mInteractiveFlag = pt.getInteractiveFlag();
		//[1] Mod end
//		m_DRMObject = pt.getDRMObject();
		mDuplicateSubscription = pt.isSubscriptionDuplicate();
		mForcedPurchase = pt.isForcedPurchase();
		mReserveOnly = pt.isReserveOnly();
		mPreOrder = pt.isPreOrder();
		mOrder = pt.getOrder();
		mMinSubPeriod = pt.getMinSubPeriod();
		mPenaltyCharges = pt.getPenaltyCharges();
		mCancellation = pt.getCancellation();
		mMonthEndSubscription = pt.getMonthEndSubscription();
		recurrenceDay =pt.getRecurrenceDay();
		alignWithExternal = pt.isAlignWithExternal();
		mHistoric = pt.isHistoric();
		mFixedRecurrence = pt.getFixedRecurrence();
		mReceipting = pt.isReceipting();
		mReceiptingAttribute = pt.getReceiptingAttribute();
//		mPurchaseableBySuperCredit = pt.isPurchaseableBySuperCredit();
//		mCreditPurchasePricePoints = pt.getCreditPurchasePricePoints();
//		mbSuperCreditDonor = pt.isSuperCreditDonor();

		mRateWithoutTax = pt.getNetRate();

		mIsArchived = pt.isArchived();

		//ER 7 Compliant
		//FIX
		if(pt.getPricingText1() != null && pt.getPricingText1().length() > 0)
			setmPricingText1(pt.getPricingText1());

		if(pt.getPricingText2() != null && pt.getPricingText2().length() > 0)
			setmPricingText2(pt.getPricingText2());
		//END FIX
		//ER 7 Compliant


		setPricingTextList1(pt.getPricingTextList1());
		setPricingTextList2(pt.getPricingTextList2());

		//PPM136861 refactoring aL. removed
//		mResource = pt.getResource();



//		mActiveStatus = pt.getActiveStatus();
//		mAlternativeCurrency = pt.getAlternativeCurrency();

		mBalances = pt.mBalances;
		
		//PPM136861 refactoring aL. START
		//copy impacts... why not!? :-)
		mImpacts = pt.mImpacts;
		
//		mCreatedBy = pt.getCreatedBy();
		mIsDiscount = pt.isDiscount();
		mKey = pt.getKey();
//		mModifiedBy = pt.getModifiedBy();
		mPricepointIdLink = pt.getPricepointIdLink();
		mPromoText = pt.mPromoText;

		mStandardRate = pt.getStandardRate(date);

//		mValidSuperCredit = pt.isValidSuperCreditOption();
		mCustomFields = new HashMap<String, String>(pt.getCustomFields());
		//Remedy 4945
		mFixedRecurringPricePoint = pt.isFixedRecurringPricePoint();
		//[13] Mod Start
		mPaymentHandler = pt.getPaymentHandler();
		//[13] Mod Start
		//ER 8 Stubb
		mBasePricePoint = pt.isBasePricePoint();
		//ER 8 Stubb



		//@hud STKHREQ36
		mAccessDuration = pt.getAccessDuration();

		//@lle STKHREQ242
		gracePeriod = pt.getGracePeriod();
		suspensionPeriod = pt.getSuspensionPeriod();
		retryFrequency = pt.getRetryFrequency();

		this.pricingTextTemplateName1 = pt.pricingTextTemplateName1;
		this.pricingTextTemplateName2 = pt.pricingTextTemplateName2;
//		this.pricingTextTemplateNameRoaming = pt.getPricingTextTemplateNameRoaming();

		//REMEDY 5553
		if(pt.mUserGroups != null && pt.mUserGroups.length > 0)
		{
			System.arraycopy(pt.mUserGroups, 0, mUserGroups, 0, pt.mUserGroups.length);
		}
		else
		{
			mUserGroups = new String[] {Constants.STRING_MATCH_ALL};
		}

		if(pt.mUserGroup!= null)
		{
			mUserGroup = pt.mUserGroup;
		}
		else
		{
			mUserGroup = Constants.STRING_MATCH_ALL;
		}

		if(pt.mSupplierId != null)
		{
			mSupplierId = pt.mSupplierId;
		}
		else
		{
			mSupplierId = Constants.STRING_MATCH_ALL;

		}

		mChannel = pt.mChannel;

		if(pt.mPromoCodes != null && pt.mPromoCodes.length > 0)
		{
			System.arraycopy(pt.mPromoCodes, 0, mPromoCodes, 0, pt.mPromoCodes.length);
		}
		else
		{
			mPromoCodes = new String[] {Constants.STRING_MATCH_ALL};
		}

		mInteractiveFlag = pt.mInteractiveFlag;

		mMinSubPeriod = pt.mMinSubPeriod;

		if(mMonthEndSubscription!= null)
			mMonthEndSubscription = pt.mMonthEndSubscription;

		usageTime = pt.usageTime;

		mEventUnits = pt.mEventUnits;

		//REMEDY 5653
//		mVolumeModel = pt.mVolumeModel;

		mTierName = pt.mTierName;

		mEventDateTime = pt.mEventDateTime;
		//END 5653
		//END REMEDY 5553

		//REMEDY 6493 - set all the rating attributes
		mChannel = pt.mChannel;
		mPaymentType = pt.mPaymentType;
		mDeviceType = pt.mDeviceType;
		mPremiumLevel = pt.mPremiumLevel;
		mSupplierId = pt.mSupplierId;
		mUserGroups = pt.mUserGroups;
		mPromoCodes = pt.mPromoCodes;
		mEventDateTime = pt.mEventDateTime;
		mEventUnits = pt.mEventUnits;
		mDuration = pt.mDuration;
		mChargingMethod = pt.mChargingMethod;
		mIsMatchTrialPromoCode = pt.mIsMatchTrialPromoCode;
		mPaymentInformation = pt.mPaymentInformation;
		mPreOrder = pt.mPreOrder;
		mContentName = pt.mContentName;
		mAssetID     = pt.mAssetID;
		expressPurchaseFlag = pt.expressPurchaseFlag;
		mBearer = pt.mBearer;
		mBearerIds = pt.mBearerIds;
		cancellationUsage = pt.cancellationUsage;
		mPromoPrecode = pt.mPromoPrecode;
		mPromoUniqueCode = pt.mPromoUniqueCode;
//		mNetworkCode = pt.getNetworkCode();
//		mNetworkCodeStr = pt.getNetworkCodeStr();
		mNextCycleDisount = pt.mNextCycleDisount;

		translatedPricingText = pt.translatedPricingText;

		//END REMEDY 6493

		//REMEDY 6559
		isZeroCostIgnore = pt.isZeroCostIgnore;
		//REMEDY 6559

		// Fair Usage start
		fairUsageLimit = pt.getFairUsageLimit();
		fairUsagePeriod = pt.getFairUsagePeriod();
		// Fair Usage end

		// CR-0095 RBT START 
		mExtensionPeriod = pt.getExtensionPeriod();
		// CR-0095 RBT START 

		//ES FUP Enhancement CR Start
		includeServiceForPackageFUP = pt.isIncludeServiceForPackageFUP();
		//ES FUP Enhancement CR End

		//CR-0978 Location Services
		if (pt.getTariff() == null || pt.getTariff().equals("")) {
			mTariff = Constants.STRING_MATCH_ALL;
		}
		else {
			mTariff = pt.getTariff();
		}

		//CR-0978 Location Services
		hideForPurchaseOptions = pt.isHideForPurchaseOptions();

		//MQC 5654 - add Tax object for real time tax rate calculation
		mTax = pt.getTax();
		
		//CR CTB-1 Advanced Linked Pricepoint
		renewalsUntilLinkedPricepoint = pt.getRenewalsUntilLinkedPricepoint();
		linkedByTrialPricepoint = pt.getLinkedByTrialPricepoint();
	}
	
	public PricePoint()	{
		// price point is always initialised to match all
		mChannel = Constants.INT_MATCH_ALL;
		mPaymentType = Constants.INT_MATCH_ALL;
		mDeviceType = Constants.INT_MATCH_ALL;
		mPremiumLevel = Constants.INT_MATCH_ALL;
		mSupplierId = Constants.STRING_MATCH_ALL;
		mUserGroups = new String[] {Constants.STRING_MATCH_ALL};
		mPromoCodes = new String[] {Constants.STRING_MATCH_ALL};
		//[10] Mod Starts
		mBearerIds = new String[] {Constants.STRING_MATCH_ALL};
		//[10] ModEnds
		//CR-0978 Location Services
		mTariff = Constants.STRING_MATCH_ALL;
	}

	//CR1564 -Utctimezone for diff region in country
	/**
	 *
	 */
	public PricePoint(RatingAttributes attr) {
		/*	this(attr, new Date());
	}//ET-153

	//CR1564 -Utctimezone for diff region in country
	public PricePoint(RatingAttributes attr, Date date)
	{*/
		super(attr);
		if (attr instanceof PricePoint) {
			init((PricePoint)attr/*, date*/);

		}
	}

	public PricePoint(PricePoint pt)
	{
		init(pt);
	}

	/*
	 * Remedy 5538
	 */
	public PricePoint(PricePoint pt, double newRate)
	{
		init(pt);
		this.mRateWithoutTax = newRate;
	}

	//CR1564 -Utctimezone for diff region in country
	/**
	 * @deprecated 
	 */
	@Deprecated
	public PricePoint(BaseAuthorization auth) {
		/*this(auth, new Date()); //ET-153
	}

	public PricePoint(BaseAuthorization auth, Date date) {
		this(auth.getMatchingAttributes(), date);*/
		this(auth.getMatchingAttributes());
		// balance impacts
		mId = auth.getRateIdentifier();
		mPackageId = auth.getPackageId();
		mContentId = auth.getContentId();
		mTaxRate = auth.getTaxRate();
		mTaxCode = auth.getTaxCode();
		mStandardRate = auth.getStandardRate();
		mRateWithoutTax = auth.getNetRate();
		//PPM136861 refactoring removed
//		mResource = auth.getResource();

		mIsDiscount = auth.isDiscount();
		mPromoText = auth.getDiscountPromoText();
		setPricingText1(auth.getPricingText1());
		setPricingText2(auth.getPricingText2());

		//ER 7 Compliant
		setmPricingText1(auth.getPricingText1());
		setmPricingText2(auth.getPricingText2());
		//ER 7 Compliant

		if (auth instanceof PurchaseAuthorization) {
			PurchaseAuthorization pAuth = (PurchaseAuthorization) auth;
			mBalances = pAuth.getResourceBalances();
		}
		if (auth instanceof UsageAuthorization) {
			UsageAuthorization uAuth = (UsageAuthorization) auth;
			mPreOrder = uAuth.getIsPreordered() == 1?true:false;
		}


		//CR1429
		alwaysValidateMsisdn = auth.isAlwaysValidateMsisdn();
	}

	public PricePoint(String id, int durationCode, int chargingMethod, String promoCode, double rate, ChargingResource res, Map<?, ?> impacts, boolean archiveFlag)
	{
		mId = id;
		setDuration(durationCode);
		setChargingMethod(chargingMethod);
		String[] codes = new String[] { promoCode};
		setPromoCodes(codes);
		mRateWithoutTax = rate;
//PPM136861 refactoring removed
//		mResource = res;
		mIsArchived = archiveFlag;
	}

	public PricePoint(String id, int durationCode, int chargingMethod, String promoCode,String bearerId, double rate, ChargingResource res, Map<?, ?> impacts, boolean archiveFlag)
	{
		mId = id;
		setDuration(durationCode);
		setChargingMethod(chargingMethod);
		String[] codes = new String[] { promoCode};
		setPromoCodes(codes);
		//[10] Mod Starts
		String[] bearerIds = new String[] { bearerId};
		setBearerIds(bearerIds);
		setBearer(bearerId);
		//[10] Mod Ends
		mRateWithoutTax = rate;
//PPM136861 refactoring removed
//		mResource = res;
		mIsArchived = archiveFlag;
	}

	public PricePoint(Long key,String id, int durationCode, int chargingMethod, String promoCode, double rate, ChargingResource res, Map<?, ?> impacts, boolean archiveFlag,
			String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
	{
		mKey = key;

		mId = id;
		setDuration(durationCode);
		setChargingMethod(chargingMethod);
		String[] codes = new String[] { promoCode};
		setPromoCodes(codes);
		mRateWithoutTax = rate;
//PPM136861 refactoring removed
//		mResource = res;
		mIsArchived = archiveFlag;
	}

	public PricePoint(Long key,String id, int durationCode, int chargingMethod, String promoCode,String bearerId, double rate, ChargingResource res, Map<?, ?> impacts, boolean archiveFlag,
			String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
	{
		mKey = key;

		mId = id;
		setDuration(durationCode);
		setChargingMethod(chargingMethod);
		String[] codes = new String[] { promoCode};
		setPromoCodes(codes);
		//[10] Mod Starts
		String[] bearerIds = new String[] { bearerId};
		setBearerIds(bearerIds);
		setBearer(bearerId);
		//[10] Mod Ends
		mRateWithoutTax = rate;
//PPM136861 refactoring removed
//		mResource = res;
		mIsArchived = archiveFlag;
	}
	
	public Long getKey() {
		return mKey;
	}
	
	void setKey(Long key)	{
		this.mKey = key;
	}



	public String getId()
	{
		return mId;
	}
	/**
	 *@deprecated
	 */
	@Deprecated
	public String getPricingModelTier()
	{
		return null;
	}
	
	@Transient
	public boolean isArchived()
	{
		return mIsArchived;
	}



	public boolean isBasePricePoint() {
		return mBasePricePoint;
	}


	//CR1564 -Utctimezone for diff region in country
	/**
        Retrieves the price of the price point.
        This will be the price the customer is charged. If the price point is rated
        at a discount, this rate will be the discount rate. getStandardRate() will retrieve the
        normal price when the discount does not apply.
        @return double the price
	 */
	public double getRate(Date date)	{
		double rv = -1;
		int round_nth_decimal =  ConfigProvider.getPropertyAsInteger("ROUND_NTH_DECIMAL", 4);
		rv = (1 + getTaxRate(/*date ET-153*/)) * getNetRate();
		return roundDouble(rv,round_nth_decimal);
	}

	
//	//CR1564 -Utctimezone for diff region in country
//	/**
//	 * @deprecated 
//	 */
//	@Deprecated
	public double getNetRate(double volumeAmount) {
//		return getNetRate(volumeAmount, new Date());
//	}
//
//	//CR1564 -Utctimezone for diff region in country
//	@Deprecated
//	public double getNetRate(double volumeAmount, Date date)
//	{
		return getNetRate();
	}

	/**
        This is the same as getDrviceType()
	 */
	@Transient
	public int getAccessDevice()
	{
		return getDeviceType();
	}

	/**
    Calculates amount based on volume amount
    overridden by the implementation
	 */
	public double getRate(double volumeAmount)
	{
		return getRate();
	}

	@Transient
	public double getAlternativeRate()
	{
		return getRate();
	}


	public String getRateAsString(Locale loc) {
		return getRateAsString(loc, new Date()); 
	}

	public String getRateAsString(Locale loc, Date date)
	{
		String rv = null;
		try {
			NumberFormat form = NumberFormat.getNumberInstance(loc);
			//form.setMinimumFractionDigits(2);
			//form.setMaximumFractionDigits(2);
			//rv = form.format(getRate());

			//REMEDY 6408
			// int round_nth_decimal =  4;
			int round_nth_decimal =  ConfigProvider.getPropertyAsInteger("ROUND_NTH_DECIMAL", 4);

			//             if(ConfigProvider.getProperty(ROUND_NTH_DECIMAL)!=null)
			//             {
			//                 round_nth_decimal = Integer.parseInt(ConfigProvider.getProperty(ROUND_NTH_DECIMAL));

			form.setMinimumFractionDigits(round_nth_decimal);
			form.setMaximumFractionDigits(round_nth_decimal);
			//           }

			//REMEDY 5023 - return the rate returned as a String with rounding as this
			// is already done by the getRate call
			//rv = Double.toString(this.getRate());

			//REMEDY 6968
			//rv = form.format(this.getRate());
			double rate = this.getRate(date);

			DecimalFormat df = (DecimalFormat)form;

			//MQC 6016 - move retrieval of property to local method of where it is used
			String round_nth_express_pattern = ConfigProvider.getProperty("ROUND_NTH_EXPRESS_PATTERN");
			if (round_nth_express_pattern == null) {
				ROUND_NTH_EXPRESS_PATTERN = ROUND_NTH_EXPRESS_PATTERN_DEFAULT;
			}
			else {
				ROUND_NTH_EXPRESS_PATTERN = round_nth_express_pattern;
			}

			df.applyPattern(ROUND_NTH_EXPRESS_PATTERN);

			rv = df.format(rate);
			//END REMEDY 6968

			//REMEDY 6408


			/*
			 * MODIFICATION MADE FOR VODAFONE ROMANIA
			 * IF PROPERTY RETURN_RATE_NET IS SET IN CLIENT'S er2.properties
			 * THEN NET AMOUNT IS RETURNED
			 */
			if(ConfigProvider.getProperty("API_PRICEPOINT_RETURN_RATE_AS_STRING_NET")!=null){

				//Added an extra condition to check if net/gross needs to be returned
				if(ConfigProvider.getPropertyAsBoolean("API_PRICEPOINT_RETURN_RATE_AS_STRING_NET", false))
				{

					if(ConfigProvider.getProperty(ROUND_NTH_DECIMAL)!=null){
						round_nth_decimal = Integer.parseInt(ConfigProvider.getProperty(ROUND_NTH_DECIMAL));
					}
					rv = form.format(roundDouble(getNetRate(),round_nth_decimal));
				}
				//End Added extra condition
			}
			// END VF-RO MODIFICATIONS
		} catch (Exception e) {
			// ignore this error
		}
		return rv;
	}

	/**
	 * if there is a balance impact which is a currency, return that. 
	 *  If not, return the LAST BalanceImpact which is not a currency but which has +ve fixed or scaled amount.   
	 *  If the balance impacts are null, return the charging resource of the first ResourceBalance which is a currency
	 * @return
	 */
	@Transient
  	public ChargingResource getResource()	{	
		if (mImpacts !=null && mImpacts.size() > 0)	{
			//this logic taken from the set..... method
			if (getAllBalanceImpacts().getCurrency() != null) {
	 			return getAllBalanceImpacts().getCurrency();
	 		} else // if (getAllBalanceImpacts().getNonCurrencyResource() != null) 	
	 			return getAllBalanceImpacts().getNonCurrencyResource();
		}
		//if the balance impacts null it could be that this pricepoint object was created
 		// during e.g. the rating flow
 		//in this case we can try the resource balance object instead
		if (getResourceBalances()!=null)	{
			for (ResourceBalance bal: getResourceBalances())	{
				if (bal.getResource()!=null && bal.getResource().isCurrency())
					return bal.getResource();
			}
		}
		
		return null;
  	}

	//MQC 6610 - setter method for charging resource
	/**
	 * does nothing
	 * @param res
	 */
	public void setResource(ChargingResource res)
	{
		log.debug("PPM136861 refactoring: setResource(int mResource) does nothing now");
	}


	/**
        The array of resources that the user will get if the price point is purchased - including the currency 
	 */
	@Transient
	public ChargingResource[] getBalanceImpacts()	{
		
		List<ChargingResource> rv = new ArrayList<ChargingResource>();
		for (int index=0; mBalances!=null && index<mBalances.length; index++) {
			 if (mBalances[index].getBalance() != 0.0) {
				 // PPM136861 refactoring aL. 
				ChargingResource cr = mBalances[index].getResource();
				if (cr != null ){	//ET138 ppt balance impacts missing from decoupling response
					rv.add(cr);
				}
			}
			//REMEDY 6493 END
		}
		return rv.toArray(new ChargingResource[rv.size()]);
	}
	
	@OneToMany(mappedBy="pricePoint",targetEntity=BalanceImpact.class, fetch=FetchType.LAZY)
//	@ForeignKey(name = "one_ppt_many_bal_impacts")	//only for the ddl generation to give the constraint a readable name
	@Access(AccessType.PROPERTY)
	public List<BalanceImpact> getBalanceImpactList() {
		return mImpacts;
	}
	
	public void setBalanceImpactList(List<BalanceImpact> impacts){
		mImpacts = new BalanceImpacts(impacts);
	}

	/**
        The amount of each resource purchased
	 */
	public double getBalanceImpactRate(ChargingResource res)
	{
		double rv = 0;
		for (int index=0; mBalances!=null && index<mBalances.length; index++) {
			if (mBalances[index].getResource().getCode() == res.getCode()) {
				rv = mBalances[index].getBalance();
				break;
			}
		}
		return rv;
	}


	@Override
	public String toString() {
		return "PricePoint [gracePeriod=" + gracePeriod + ", suspensionPeriod="
				+ suspensionPeriod + ", retryFrequency=" + retryFrequency
				+ ", mAccessDuration=" + mAccessDuration + ", mKey=" + mKey
				+ ", mId=" + mId + ", mContentId=" + mContentId
				+ ", mPackageId=" + mPackageId + ", mRateWithoutTax="
				+ mRateWithoutTax + ", mResource=REMOVED, mBalances="
				+ Arrays.toString(mBalances) + ", mImpacts=" + mImpacts
				+ ", mPricePointTier=" + mPricePointTier + ", pack=" + pack
				+ ", mIsArchived=" + mIsArchived + ", mGlid=" + mGlid
				+ ", mPurchaseExpiryDate=" + mPurchaseExpiryDate
				+ ", mPurchaseStartDate=" + mPurchaseStartDate
				+ ", mPricingText1=" + mPricingText1 + ", mPricingText2="
				+ mPricingText2 + ", pricingTextList1=" + pricingTextList1
				+ ", pricingTextList2=" + pricingTextList2 + ", mCustomFields="
				+ mCustomFields + ", mStandardRate=" + mStandardRate
				+ ", mPricepointIdLink=" + mPricepointIdLink
				+ ", mForcedPurchase=" + mForcedPurchase
				+ ", mDuplicateSubscription=" + mDuplicateSubscription
				+ ", mPurchaseFixedExpiryDate=" + mPurchaseFixedExpiryDate
				+ ", mInteractiveFlag=" + mInteractiveFlag + ", mReserveOnly="
				+ mReserveOnly + ", mMinSubPeriod=" + mMinSubPeriod
				+ ", mPenaltyCharges=" + mPenaltyCharges + ", mCancellation="
				+ mCancellation + ", mMonthEndSubscription="
				+ mMonthEndSubscription + ", mHistoric=" + mHistoric
				+ ", mFixedRecurrence=" + mFixedRecurrence + ", mReceipting="
				+ mReceipting + ", mReceiptingAttribute="
				+ mReceiptingAttribute + ", mPaymentHandler=" + mPaymentHandler
				+ ", mOrder=" + mOrder + ", mFixedRecurringPricePoint="
				+ mFixedRecurringPricePoint + ", mBasePricePoint="
				+ mBasePricePoint + ", pricingTextTemplateName1="
				+ pricingTextTemplateName1 + ", pricingTextTemplateName2="
				+ pricingTextTemplateName2 + ", translatedPricingText1="
				+ translatedPricingText1 + ", translatedPricingText2="
				+ translatedPricingText2 + ", translatedPricingText="
				+ translatedPricingText + ", usageTime=" + usageTime
				+ ", recurrenceDay=" + recurrenceDay + ", alignWithExternal="
				+ alignWithExternal + ", pricingTextTemplateNameRoaming="
				+ pricingTextTemplateNameRoaming + ", onFootPrintPrice="
				+ onFootPrintPrice + ", offFootPrintPrice=" + offFootPrintPrice
				+ ", isZeroCostIgnore=" + isZeroCostIgnore
				+ ", hideForPurchaseOptions=" + hideForPurchaseOptions
				+ ", ROUND_NTH_EXPRESS_PATTERN=" + ROUND_NTH_EXPRESS_PATTERN
				+ ", fairUsageLimit=" + fairUsageLimit + ", fairUsagePeriod="
				+ fairUsagePeriod + ", fairUsagePeriodUnit="
				+ fairUsagePeriodUnit + ", mExtensionPeriod="
				+ mExtensionPeriod + ", includeServiceForPackageFUP="
				+ includeServiceForPackageFUP + ", mTax=" + mTax
				+ ", useStaticValues=" + useStaticValues
				+ ", alwaysValidateMsisdn=" + alwaysValidateMsisdn
				+ ", renewalsUntilLinkedPricepoint="
				+ renewalsUntilLinkedPricepoint + ", linkedByTrialPricepoint="
				+ linkedByTrialPricepoint + ", mIsDiscount=" + mIsDiscount
				+ ", mPromoText=" + mPromoText + ", mIsOriginal=" + mIsOriginal
				+ ", mIsVolumeType=" + mIsVolumeType + ", mProtectedFk="
				+ mProtectedFk + "]";
	}
	
	/**
	 * a user-readable (for dropdowns or logging...) interpretation of the pricepoint<br/>
	 * e.g. "Non-recurring 1 day"
	 * @param ppt
	 * @return
	 */
	public String getDescription()	{
		StringBuilder sb = new StringBuilder();
		Duration dur = new Duration( this.getDuration());
		int method = this.getChargingMethod();
		sb.append(ChargingMethod.getName(method)).append(" ");	//eg "Recurring "
		if(ChargingMethod.isCalendar(method))	{
			sb.append(dur.getValue()).append(" ");
			sb.append(dur.getUnit());
		}
		if (this.isTrial())
			sb.append(" TRIAL ");
		else if (this.getPromoCode()!=null && !this.getPromoCode().equals("*"))
			sb.append(", PromoCode ").append(this.getPromoCode());
		if (StringUtils.isNotBlank(this.getUserGroup()) && !this.getUserGroup().equals("*"))
			sb.append(", User Group ").append(this.getUserGroup());
		
		if (StringUtils.isNotBlank(this.getBearer()) && !this.getBearer().equals("*"))
			sb.append(", Bearer ").append(this.getBearer());
		
		if (this.getTariff()!=null && !this.getTariff().equals("*"))
			sb.append (", Tariff ").append(this.getTariff());
		
		if (this.isHistoric())
			sb.append(", HISTORIC");
		
		if (this.getChannel()!=998 && this.getChannel()!=999)
			sb.append(", Channel ").append(this.getChannel());
		
		if (this.isExpressFlag())
			sb.append(", Express");
		
		return sb.toString();
	}

	/**
        The price point is a trial price point.
        @return boolean true if the price point has the promo code set to trial/TRIAL (any case)
	 */
	public boolean isTrial()	{
		boolean rv = false;
		String code = getPromoCode();
		if (code!=null && code.toLowerCase().equals("trial")) {
			rv = true;
		}
		return rv;
	}


	/**
        This is used to determine if a price point is active ie can be used to buy a package.
        @return true if the pricepoint can be bought
	 */
	@Transient
	public boolean isActive()	{
		return isActive(new Date());
	}
	
	/**
        This is used to determine if a price point is active ie can be used to buy a package, for the specified date.
        @return true if the pricepoint can be bought
	 */
	public boolean isActive(Date date)	{
		boolean rv = true;
		Date currentDate = (date != null) ? date : new Date();

		if (mPurchaseStartDate!=null && mPurchaseStartDate.after(currentDate)) {
			rv = false;
		} else if (mPurchaseExpiryDate!=null && mPurchaseExpiryDate.before(currentDate)) {
			rv = false;
		}

		return rv;
	}


	public String getGlid() {
		return mGlid;
	}

	@Override
	public String getTaxCode()	{
		return mTaxCode;
	}

	@Override
	public double getTaxRate() {
		return getTaxRate(new Date());
	}

	//CR1564 -Utctimezone for diff region in country
//ET-153 | commented this one,below is the modified method | Start	
//	public double getTaxRate(Date date)
//	{
//		//MQC 5654 - add Tax object for real time tax rate calculation
//		if (mTax != null) {
//
//			// Need to put some extra logic here to get the proper tax rate
//			//boolean isTimeZoneAdjustedDate = isTimeZoneAdjustedDate(date);
//
//			if (isTimeZoneAdjustedDate) {
//				return mTax.getTaxRate(date);
//			} else if (useStaticValues) {
//				return mTaxRate;
//			} else {
//				return mTax.getTaxRate(date);
//			}
//		}
//		else {
//			return mTaxRate;
//		}
//	}
//ET-153 |  commented this one,below is the modified method | End	
	public double getTaxRate(Date date)
	{	
		if (mTax != null) {
			return mTax.getTaxRate(date); 
			} else {
			return mTaxRate;
			}
	}
	
	/**
        The expiry purchase date defining the end of the period the price point can be bought
	 */
	public Date getExpiryDate()	{
		return mPurchaseExpiryDate;
	}

	/**
        The start purchase date defining the start of the period the price point can be bought
	 */
	public Date getStartDate()	{
		return mPurchaseStartDate;
	}

	/**
        The id of the service. This is the same as the service id. This is not set for package
        price points.
	 */
	public String getContentId()	{
		return mContentId;
	}

	/**
        The id of the package the price point belongs to.
	 */
	public String getPackageId()	{
		return mPackageId;
	}
	

	/**
        Used to store special pricing information.
        A catalog field
	 */
	public String getPricingText1()
	{
		return getPricingText1(Constants.DEFAULT_LANGUAGE_CODE);
	}

	/**
        Used to store special pricing information.
        A catalog field
	 */
	public String getPricingText2()
	{
		return getPricingText2(Constants.DEFAULT_LANGUAGE_CODE);
	}

	/**
	 * Sets a custom field for the service.
	 * Custom fields appear in <custom_field> tags in the catalog XML.
	 */
	public String getCustomField(String name) {
		return mCustomFields.get(name);
	}


	/**
        Returns all custom fields
	 */
	@Transient
  	public Map<String, String> getCustomFields() {
		return mCustomFields;
	}

	// new methods to support promotional activity
	protected boolean mIsDiscount=false;
	protected String mPromoText="";

	private boolean mIsOriginal = false;

	protected boolean mIsVolumeType = false;

	protected String mProtectedFk;


	/**
        This is a promotional price point.  This will have discount text associated with it.
	 */
	public boolean isDiscount()
	{
		return mIsDiscount;
	}


	//CR1564 -Utctimezone for diff region in country
	/**
	 * @deprecated 
	 */
	@Deprecated
	public double getStandardRate() {
		return getStandardRate(new Date());
	}


	//CR1564 -Utctimezone for diff region in country
	/**
        This is the normal price of the price point if there was no discount
	 */
	public double getStandardRate(Date date)
	{
		double rv = mStandardRate;

		// Need to put some extra logic here to get the proper standard rate
		//boolean isTimeZoneAdjustedDate = isTimeZoneAdjustedDate(date);//ET-153

		if (rv<0 /*|| isTimeZoneAdjustedDate ET-153*/) {
			rv = getRate(date);
		}

		return rv;
	}

	//CR1564 -Utctimezone for diff region in country
	/**
	 * @deprecated 
	 */
	@Deprecated
	public String getStandardRateAsString(Locale locale) {
		return getStandardRateAsString(locale, new Date());
	}

	/**
        This is the normal price of the price point formated as x.xx or x,xx.
        For example
        1.23 for Locale.GB or 1,23 for Locale.GERMANY
	 */
	public String getStandardRateAsString(Locale locale, Date date)
	{
		String rv = null;

		try {
			NumberFormat form = NumberFormat.getNumberInstance(locale);
			form.setMinimumFractionDigits(2);
			form.setMaximumFractionDigits(2);
			rv = form.format(getStandardRate(date));

			/*
			 * MODIFICATION MADE FOR VODAFONE ROMANIA
			 * IF PROPERTY RETURN_RATE_NET IS SET IN CLIENT'S er2.properties
			 * THEN NET AMOUNT IS RETURNED
			 */


			if(ConfigProvider.getProperty("API_PRICEPOINT_RETURN_RATE_AS_STRING_NET")!=null){


				//Added an extra condition to check if net/gross needs to be returned
				if(ConfigProvider.getPropertyAsBoolean("API_PRICEPOINT_RETURN_RATE_AS_STRING_NET", false))
				{
					double net = getStandardRate(date) / ( 1 + getTaxRate(/*date ET-153*/) );
					rv = form.format(net);
				}
				//End add extra condition
			}
			// END VF-RO MODIFICATIONS
		} catch (Exception e) {
			// ignore this error
		}
		return rv;
	}

	/**
        If this is a discount price point, the discount text is returned.
	 */
	public String getDiscountPromoText()
	{
		return mPromoText;
	}


	/**
	 * return the pricepoint id link associated with the price point.
	 * @return String
	 * @since 5.1
	 */
	public String getPricepointIdLink()
	{
		return mPricepointIdLink;
	}



	/** ADDED FOR EGYPT ER6 STUB **/
	public boolean isPreview() {
		boolean rv = false;
		String code = getPromoCode();
		if (code!=null && code.toLowerCase().equals("preview")) {
			rv = true;
		}
		return rv;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public String getInteractiveFlag() {
		return mInteractiveFlag;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public boolean isForcedPurchase() {
		return mForcedPurchase;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public boolean isSubscriptionDuplicate() {
		return mDuplicateSubscription;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public Date getFixedExpiryDate()
	{
		return mPurchaseFixedExpiryDate;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	public boolean isReserveOnly() {
		return mReserveOnly;
	}

	/** ADDED FOR ER6.5 STUB **/
	public int getMinSubPeriod() {
		return mMinSubPeriod;
	}

	/** ADDED FOR ER6.5 STUB **/
	public double getPenaltyCharges() {
		return mPenaltyCharges;
	}

	/** ADDED FOR ER6.5 STUB  - should be isCancellation**/
	public boolean getCancellation() {
		return mCancellation;
	}
	//[11] Mod Start
//	public VolumeModel getVolumeModel() {
//		return mVolumeModel;
//	}
	//[11] Mod End

	/** ADDED FOR ER6.5 STUB **/
	public String getMonthEndSubscription() {
		return mMonthEndSubscription;
	}

	/** ADDED FOR EGYPT ER6.5 STUB **/
	public boolean isHistoric() {
		return mHistoric;
	}

	/** the fixed number of renewals for this pricepoint **/
	public long getFixedRecurrence() {
		return mFixedRecurrence;
	}

	/** ADDED FOR ER6.5 STUB **/
	public boolean isFixedRecurringPricePoint() {
		//return false;
		//Remedy 4945
		return mFixedRecurringPricePoint;
	}

	/** ADDED FOR ER6.5 STUB **/
	public boolean isReceipting() {
		return mReceipting;
	}

	/** ADDED FOR ER6.5 STUB **/
	public String getReceiptingAttribute() {
		return mReceiptingAttribute;
	}


	/** ADDED FOR EGYPT ER7 STUB **/
	@Override
	public boolean isPreOrder() {
		return mPreOrder;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	@Column(name="pp_order")
	public int getOrder() {
		return mOrder;
	}

	public static final double roundDouble(double d, int nthDecimal)
	{
		return Math.round(d * Math.pow(10, nthDecimal)) / Math.pow(10,nthDecimal);
	}


	//[13] Mod Start
	/** ADDED FOR ER8 STUB **/
	public String getPaymentHandler() {
		if(mPaymentHandler == null || mPaymentHandler.equals("")){
			mPaymentHandler = PAYMENT_HANDLER_NONE;
		}
		return mPaymentHandler;
	}
	//[13] Mod Start


	/**
	 * @version   ER 8.0
	 * @author    ER-WC - Remedy 3645
	 * @date      9-Dec-2005
	 * @description  The returns an array of String Objects containing the non "*" & "" UserGroups associated
	 * 				with this pricepoint
	 * @return      Array of String Objects containing the non "*" & "" UserGroups associated with this pricepoint
	 **/
	@Transient
    public String[] getNonMatchAllUserGroups(){
		List<String> list = new ArrayList<String>();

		if (mUserGroups != null && mUserGroups.length>0) {
			for (int i=0; i<mUserGroups.length; i++) {
				if ( (mUserGroups[i]!= null &&
						!mUserGroups[i].equals(Constants.STRING_MATCH_ALL)) &&
						(!mUserGroups[i].equals(Constants.STRING_NOT_SET)) ) {
					//There must be a custom user group here
					list.add(mUserGroups[i]);
				}
			}
			//No custom user groups
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * @version   ER 8.0
	 * @author    VFE  PS Team
	 * @date      15-Aug-2005
	 * @description  The purpose of this logic is to return TRUE if the pricepoint has a User-Group which
	 *               is set to a value other than "*" otherwise return FALSE.
	 * @return       boolean true if there is a user group set to a string value other than "*" (Match all).
	 **/
	public boolean IsUserGroups()
	{
		boolean rv = false;
		if (mUserGroups != null && mUserGroups.length>0) {
			for (int i=0; i<mUserGroups.length; i++) {
				if ( (mUserGroups[i]!= null &&
						!mUserGroups[i].equals(Constants.STRING_MATCH_ALL)) &&
						(!mUserGroups[i].equals(Constants.STRING_NOT_SET)) ) {
					//There must be a custom user group here
					rv = true;
					break;
				}
			}
			//No custom user groups
		}
		return rv;
	}
	/**
	 *@ VFE  PS Code Ends
	 **/

	/**
	 * @version   ER 8.0
	 * @author    VFE  PS Team
	 * @date      15-Aug-2005
	 * @description  The purpose of this logic is to check if the pricepoint has a Promo-Code which
	 *               is set to a value other than "*". If Promo-Code found return TRUE otherwise return FALSE.
	 *               The function get the first element in the "mPromoCodes" attribute and check it.
	 * @return       boolean true if a promo code is set to a string value other than "*" (Match all).
	 **/
	public boolean isPromo()
	{
		boolean rv = false;
		String promoCode = this.getPromoCode();
		if (Constants.isNotSet(promoCode)==false && Constants.isMatchAll(promoCode)==false)  {
			rv = true;
		}
		return rv;
	}
	/**
	 *@ VFE  PS Code Ends
	 **/
	/**
	 * This method returns a boolean to allow a message to be sent to the payment handler
	 * @version   ER 8.0
	 * @author    VFE  PS Team
	 * @date      15-Aug-2005
	 *
	 */
	public boolean isSubmitToPaymentHandler(){
		boolean rv = false;
		if(mPaymentHandler == null || mPaymentHandler.equals("")){
			mPaymentHandler = PAYMENT_HANDLER_NONE;
		}

		if (mPaymentHandler.equals(PAYMENT_HANDLER_SUBMIT)){
			rv = true;
		}
		return rv;
	}

	/**  ADDED FOR ET233: Add Zero Price Payment Handler Suppression to Pricepoint */
	public boolean isSuppressToPaymentHandler(){
		boolean rv = false;
		if(mPaymentHandler == null || mPaymentHandler.equals("")){
			mPaymentHandler = PAYMENT_HANDLER_NONE;
		}

		if (mPaymentHandler.equals(PAYMENT_HANDLER_SUPPRESS)){
			rv = true;
		}
		return rv;
	}

	//[4] Start
	public String getPricingTextTemplateName1() {
		return pricingTextTemplateName1;
	}

	public void setPricingTextTemplateName1(String pricingTextTemplateName1) {
		this.pricingTextTemplateName1 = pricingTextTemplateName1;
	}

	public String getPricingTextTemplateName2() {
		return pricingTextTemplateName2;
	}

	public void setPricingTextTemplateName2(String pricingTextTemplateName2) {
		this.pricingTextTemplateName2 = pricingTextTemplateName2;
	}

	public String getTranslatedPricingText1() {
		return translatedPricingText1;
	}

	public void setTranslatedPricingText1(String translatedPricingText1) {
		this.translatedPricingText1 = translatedPricingText1;
	}

	public String getTranslatedPricingText2() {
		return translatedPricingText2;
	}

	public void setTranslatedPricingText2(String translatedPricingText2) {
		this.translatedPricingText2 = translatedPricingText2;
	}

	//	[4] End

	public long getUsageTime() {
		return usageTime;
	}
	public void setUsageTime(long usageTime) {
		this.usageTime = usageTime;
	}

	public int getRecurrenceDay() {
		return recurrenceDay;
	}

	public void setRecurrenceDay(int recurrenceDay) {
		this.recurrenceDay = recurrenceDay;
		if (recurrenceDay != -1)
			alignWithExternal = false;
	}

	@Transient
	public Map<String, String> getPricingTextList1() {
		return pricingTextList1;
	}

	public void setPricingTextList1(Map<String, String> pricingTextList1) {
		this.pricingTextList1 = pricingTextList1;
	}

	@Transient
	public Map<String, String> getPricingTextList2() {
		return pricingTextList2;
	}

	public void setPricingTextList2(Map<String, String> pricingTextList2) {
		this.pricingTextList2 = pricingTextList2;
	}

	public String getPricingText1(String languageCode){
		if (this.pricingTextList1 == null || this.pricingTextList1.isEmpty()){
			return null;
		}

		if (languageCode == null || languageCode.length() <= 0){
			languageCode = Constants.DEFAULT_LANGUAGE_CODE;
		}

		//FIX
		if (pricingTextList1.get(languageCode) != null){

			String pText = pricingTextList1.get(languageCode);

			if(pText.length() > 0)
				return pText;

		}
		//REMEDY 7249 START
		else if ( (!Constants.DEFAULT_LANGUAGE_CODE.equals(languageCode))
				&& (pricingTextList1.get(Constants.DEFAULT_LANGUAGE_CODE) != null) ) {

			String pText = pricingTextList1.get(Constants.DEFAULT_LANGUAGE_CODE);

			if(pText.length() > 0)
				return pText;
		}
		//REMEDY 7249 END

		if(getmPricingText1()!= null && getmPricingText1().length() > 0)
			return getmPricingText1();

		//END FIX

		return this.pricingTextList1.get(Constants.DEFAULT_LANGUAGE_CODE);
	}

	public String getPricingText2(String languageCode){
		if (this.pricingTextList2 == null || this.pricingTextList2.isEmpty()){
			return null;
		}

		if (languageCode == null || languageCode.length() <= 0){
			languageCode = Constants.DEFAULT_LANGUAGE_CODE;
		}

		//FIX
		if (pricingTextList2.get(languageCode) != null){
			String pText = pricingTextList2.get(languageCode);

			if(pText.length() > 0)
				return pText;
		}
		//REMEDY 7249 START
		else if ( (!Constants.DEFAULT_LANGUAGE_CODE.equals(languageCode))
				&& (pricingTextList2.get(Constants.DEFAULT_LANGUAGE_CODE) != null) ) {

			String pText = pricingTextList2.get(Constants.DEFAULT_LANGUAGE_CODE);

			if(pText.length() > 0)
				return pText;
		}
		//REMEDY 7249 END

		if(getmPricingText2()!= null && getmPricingText2().length() > 0)
			return getmPricingText2();

		//END FIX

		return this.pricingTextList2.get(Constants.DEFAULT_LANGUAGE_CODE);
	}

	public void setPricingText1(String text)
	{
		//ER 7 Compliant
		setmPricingText1(text);
		//ER 7 Compliant

		setPricingText1(Constants.DEFAULT_LANGUAGE_CODE, text);
	}

	public void setPricingText2(String text)
	{
		//ER 7 Compliant
		setmPricingText2(text);
		//ER 7 Compliant

		setPricingText2(Constants.DEFAULT_LANGUAGE_CODE, text);
	}

	public void setPricingText1(String language, String text){
		if (text == null){
			text = new String();
		}

		if (this.pricingTextList1 == null){
			this.pricingTextList1 = new HashMap<String, String>();
		}

		if (language == null || language.length() <= 0){
			language = Constants.DEFAULT_LANGUAGE_CODE;
		}

		this.pricingTextList1.put(language, text);

		//REMEDY 7249 START
		if (Constants.DEFAULT_LANGUAGE_CODE.equals(language)) {
			setmPricingText1(text);
		}
		//REMEDY 7249 END
	}

	public void setPricingText2(String language, String text){
		if (text == null){
			text = new String();
		}

		if (this.pricingTextList2 == null){
			this.pricingTextList2 = new HashMap<String, String>();
		}

		if (language == null || language.length() <= 0){
			language = Constants.DEFAULT_LANGUAGE_CODE;
		}

		this.pricingTextList2.put(language, text);

		//REMEDY 7249 START
		if (Constants.DEFAULT_LANGUAGE_CODE.equals(language)) {
			setmPricingText2(text);
		}
		//REMEDY 7249 END
	}

	public boolean isAlignWithExternal() {
		return alignWithExternal;
	}

	public void setAlignWithExternal(boolean alignWithExternal) {
		this.alignWithExternal = alignWithExternal;
		if (alignWithExternal)
			this.recurrenceDay = -1;
	}

	//Roaming START
//	public void setPricingTextTemplateNameRoaming(String PricingTextTemplateNameRoaming){
//		this.pricingTextTemplateNameRoaming=PricingTextTemplateNameRoaming;
//	}
//	public void setOnFootPrintPrice(double OnFootPrintPrice){
//		this.onFootPrintPrice = OnFootPrintPrice;
//	}
//	public void setOffFootPrintPrice(double OffFootPrintPrice){
//		this.offFootPrintPrice = OffFootPrintPrice;
//	}
//	public void setIsOnRoamingPresent(boolean IsOnRoamingPresent){
//		this.isOnRoamingPresent = IsOnRoamingPresent;
//	}
//	public void setIsOffRoamingPresent(boolean IsOffRoamingPresent){
//		this.isOffRoamingPresent = IsOffRoamingPresent;
//	}
//	public String getPricingTextTemplateNameRoaming(){
//		return this.pricingTextTemplateNameRoaming ;
//	}
//	public double getOnFootPrintPrice(){
//		return this.onFootPrintPrice ;
//	}
//	public double getOffFootPrintPrice(){
//		return this.offFootPrintPrice ;
//	}
//	public boolean getIsOnRoamingPresent(){
//		return this.isOnRoamingPresent ;
//	}
//	public boolean getIsOffRoamingPresent(){
//		return this.isOffRoamingPresent ;
//	}
//	//@hud
//	public boolean isRoamingEnabled() {
//		return isOnRoamingPresent || isOffRoamingPresent;
//	}
	//Roaming END



	///////////////////////////////////////////////////////
	//@hud STKHREQ13076 SP ROAMING
//	public double getRoamingNetAmount() {
//		return mRoamingNetAmount;
//	}
//	public void setRoamingNetAmount(double roamingNetAmount) {
//		mRoamingNetAmount = roamingNetAmount;
//	}
//	public double getRoamingGrossAmount() {
//		return mRoamingGrossAmount;
//	}
//	public void setRoamingGrossAmount(double roamingGrossAmount) {
//		mRoamingGrossAmount = roamingGrossAmount;
//	}
//	public int getRoamingType() {
//		if (getNetworkCode() == null) {
//			return ErCoreConst.ROAMING_DOMESTIC;
//		}
//		else {
//			return getNetworkCode().getRoamingType();
//		}
//	}


	//////////////////////////////////////////////////////
	//@hud STKHREQ36 Micro Service
	public double getAccessDuration() {
		return mAccessDuration;
	}
	public void setAccessDuration(double accessDuration) {
		mAccessDuration = accessDuration;
	}
	//////////////////////////////////////////////////////

	///////////////////////////////////////////////////
	//@lle STKHREQ242
	@Transient
  	public PeriodValue getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(PeriodValue gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	@Transient
    public PeriodValue getRetryFrequency() {
		return retryFrequency;
	}

	public void setRetryFrequency(PeriodValue retryFrequency) {
		this.retryFrequency = retryFrequency;
	}

	@Transient
  	public PeriodValue getSuspensionPeriod() {
		return suspensionPeriod;
	}

	public void setSuspensionPeriod(PeriodValue suspensionPeriod) {
		this.suspensionPeriod = suspensionPeriod;
	}

	/**
	 * Specifies whether the grace, suspension, retry frequency combo is undefined
	 * @return true if any of the grace, suspension, or retry frequency is null or undefined
	 */
	@Transient
  	public boolean isGraceSuspensionRetryFrequencyUndefined() {
		if ((gracePeriod == null) || (suspensionPeriod == null) || (retryFrequency == null)
				|| gracePeriod.isUndefined() || suspensionPeriod.isUndefined() || retryFrequency.isUndefined()) {
			return true;
		}
		return false;
	}
	public String getTranslatedPricingText() {
		return translatedPricingText;
	}
	public void setTranslatedPricingText(String translatedPricingText) {
		this.translatedPricingText = translatedPricingText;
	}
	public boolean isZeroCostIgnore() {
		return isZeroCostIgnore;
	}
	public void setZeroCostIgnore(boolean isZeroCostIgnore) {
		this.isZeroCostIgnore = isZeroCostIgnore;
	}

	// Fair Usage start
	public void setFairUsageLimit(int fairUsageLimit){
		this.fairUsageLimit = fairUsageLimit;
	}

	public int getFairUsageLimit() {
		return fairUsageLimit;
	}

	public void setFairUsagePeriod(int fairUsagePeriod){
		this.fairUsagePeriod = fairUsagePeriod;
	}

	public int getFairUsagePeriod() {
		return fairUsagePeriod;
	}

	public void setFairUsagePeriodUnit(String fairUsagePeriodUnit){
		this.fairUsagePeriodUnit = fairUsagePeriodUnit;
	}

	public String getFairUsagePeriodUnit() {
		return fairUsagePeriodUnit;
	}
	// Fair Usage end

	// CR-0095 RBT START 
	public void setExtensionPeriod(int ExtensionPeriod){
		mExtensionPeriod = ExtensionPeriod;
	}

	public int getExtensionPeriod(){
		return mExtensionPeriod;
	}
	// CR-0095 RBT END

	//ES FUP Enhancement CR Start
	public void setIncludeServiceForPackageFUP(boolean includeServiceForPackageFUP){
		this.includeServiceForPackageFUP = includeServiceForPackageFUP;
	}

	public boolean isIncludeServiceForPackageFUP() {
		return this.includeServiceForPackageFUP;
	}

	public boolean isFairUsagePolicyEnabled() {
		if ( (this.fairUsageLimit) > 0 && (this.fairUsagePeriod > 0 && this.fairUsagePeriod < 32)) {
			return true;
		}
		else {
			return false;
		}
	}
	//ES FUP Enhancement CR End

	//CR-0978 Location Services
	@Transient
	public boolean isTariff() {
		if (this.mTariff != null && !mTariff.equals("") && !mTariff.equals(Constants.STRING_MATCH_ALL)) {
			return true;
		}
		else {
			return false;
		}
	}

	//CR-0978 Location Services
	public boolean isHideForPurchaseOptions() {
		return this.hideForPurchaseOptions;
	}

	//CR-0978 Location Services
	public void setHideForPurchaseOptions(boolean hide) {
		this.hideForPurchaseOptions = hide;
	}

	//MQC 5067 - return the resource balances
	@Transient
  	public ResourceBalance[] getResourceBalances () {
		return this.mBalances;
	}

	//MQC 5067 - return the custom resource balances
	@Transient
  	public ResourceBalance[] getCustomResourceBalances () {
		List<ResourceBalance> rv = new ArrayList<ResourceBalance>();
		for (int index=0; mBalances!=null && index<mBalances.length; index++) {
			if (mBalances[index].getResource().isResource()) {
				rv.add(mBalances[index]);
			}
		}
		if (rv.size() > 0) {
			return rv.toArray(new ResourceBalance[rv.size()]);
		}
		else {
			return null;
		}
	}

	//MQC 5654 - add Tax object for real time tax rate calculation
	@ManyToOne(optional=true, cascade=CascadeType.PERSIST)
	public Tax getTax() {
		return mTax;
	}

	//CR1564 -Utctimezone for diff region in country - moved from PricePointImpl
	//MQC 5654 - add Tax object for real time tax rate calculation
	public void setTax(Tax taxObject) {
		mTax = taxObject;
	}

	/**
	 * @deprecated use {@link #getNetRate} instead
	 * @param futureDate
	 * @return
	 */
	@Deprecated
	public double getFutureRate(Date futureDate) {
		return getNetRate();
	}


	//CR1429 - Begin - Always validate MSISDN during purchase
	public boolean isAlwaysValidateMsisdn() {
		return alwaysValidateMsisdn;
	}

	public void setAlwaysValidateMsisdn(boolean alwaysValidateMsisdn) {
		this.alwaysValidateMsisdn = alwaysValidateMsisdn;
	}
	//CR1429 - End


	public void setId(String id)
	{
		this.mId = id;
	}

	public void setNetRate(double netRate)
	{
		this.mRateWithoutTax = netRate;
	}

	@Override
	public void setTaxRate(double taxRate)
	{
		this.mTaxRate = taxRate;
	}

	public void setIsHistoric(boolean isHistoric)
	{
		this.mHistoric = isHistoric;
	}
	//CR1759 - end

	//CR1503aL. START
	/**
	 * @return the mBalances
	 */
	@Transient
	public ResourceBalance[] getBalances() {
		return mBalances;
	}
	/**
	 * @param mBalances the mBalances to set
	 */
	public void setBalances(ResourceBalance[] mBalances) {
		this.mBalances = mBalances;
	}
	public void setPaymentHandler(String paymentHandler) {
		mPaymentHandler = paymentHandler;
	}
	public void setIsReserveOnly(boolean b){
		mReserveOnly = b;
	}

	/**
	 * @param mPurchaseExpiryDate the mPurchaseExpiryDate to set
	 */
	public void setExpiryDate(Date mPurchaseExpiryDate) {
		this.mPurchaseExpiryDate = mPurchaseExpiryDate;
	}

	/**
	 * @param mPurchaseStartDate the mPurchaseStartDate to set
	 */
	public void setStartDate(Date mPurchaseStartDate) {
		this.mPurchaseStartDate = mPurchaseStartDate;
	}

	/**
	 * returns true only for recurring calendar pricepoints, and false for event OR non-recurring calendar ppts.
	 * @return
	 */
	public boolean isRecurring()	{
		return ChargingMethod.isRecurring(getChargingMethod());
	}
	//CR1503aL. END
	
	/**
	 * CR CTB-1 Advanced Linked Pricepoint
	 * @return the renewalsUntilLinkedPricepoint
	 */
	public int getRenewalsUntilLinkedPricepoint() {
		return this.renewalsUntilLinkedPricepoint;
	}
	
	/**
	 * CR CTB-1 Advanced Linked Pricepoint
	 * @param noOfRenewals the renewalsUntilLinkedPricepoint to set
	 */
	public void setRenewalsUntilLinkedPricepoint(int noOfRenewals) {
		this.renewalsUntilLinkedPricepoint = noOfRenewals;
	}
	
	/**
	 * CR CTB-1 Advanced Linked Pricepoint
	 * @return the linkedByTrialPricepoint
	 */
	@Override
	public boolean getLinkedByTrialPricepoint() {
		return this.linkedByTrialPricepoint;
	}
	
	/**
	 * CR CTB-1 Advanced Linked Pricepoint
	 * @param linkedByTrial the linkedByTrialPricepoint to set
	 */
	@Override
	public void setLinkedByTrialPricepoint(boolean linkedByTrial) {
		this.linkedByTrialPricepoint = linkedByTrial;
	}
	
	public PricePointTier getPricePointTier(String tierId, String modelId) {
		PricePointTier rv = new PricePointTier();
		rv.setBalanceImpacts(mImpacts);
				//mPricePointTiers.get(getPricePointTierId(tierId,modelId));
		return rv;
	}
	
	protected String getPricePointTierId(String tier, String pricingModel) {
		String ret = tier;
		if (ret == null) ret="";
		if (pricingModel != null ) ret = ret + pricingModel ;
		return ret;
	}

	
	
	public boolean hasUserGroup() {
		return StringUtils.isNotBlank(getUserGroup());
	}
	


	
	public BalanceImpacts getAllBalanceImpacts() {
 		return mImpacts;
	}

	public void setAttributes(int durationCode, int chargingMethod, String promoCode,
			int paymentTypeCode, int customResource) {
				setDuration(durationCode);
				setPromoCodes(new String[] {promoCode});
				setChargingMethod(chargingMethod);
				setPaymentType(paymentTypeCode);
				setResource(customResource);
			}

	public void setAttributes(int durationCode, int chargingMethod, String promoCode,
			String bearerId, int paymentTypeCode, int customResource) {
		setDuration(durationCode);
		setPromoCodes(new String[] {promoCode});
		//[5] Mod Starts
		setBearerIds(new String[] {bearerId});
		setBearer(bearerId);
		//[5] Mod Ends
		setChargingMethod(chargingMethod);
		setPaymentType(paymentTypeCode);
		setResource(customResource);
	}




	public void setArchived(boolean mIsArchived) {
		this.mIsArchived = mIsArchived;
	}

	public void setRateWithoutTax(double val) {
		this.mRateWithoutTax = val;
	}

	/**
	    The type of currency or tokens.
	
	 */
	public void setResource(int mResource) {
		//PPM136861 refactoring removed
//		this.mResource = ChargingResource.getResource(mResource);
		log.debug("PPM136861 refactoring: setResource(int mResource) does nothing now");
	}

	public void setContentId(String val) {
		mContentId = val;
	}

	public void setPackageId(String val) {
		mPackageId = val;
	}

	public String getPackageIdentifier() {
		StringBuffer buf = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		buf.append(PACKAGE_PRICEPOINT_ID_PREFIX).append(getPackageId()).append('_');
		buf.append(getTaxCode()).append('_');
		buf.append(getChargingMethod()).append('_');
		buf.append(getDuration()).append('_');
		buf.append(getPaymentType()).append('_');
		buf.append(getAccessDevice()).append('_');
		buf.append(getChannel()).append('_');
		buf.append(getPromoCode()).append('_');
		//[5] Mod Starts
		buf.append(getBearer()).append('_');
		//[5] Mod Ends
		buf.append(getUserGroup()).append('_');
		buf.append(isExpressFlag()).append('_');
		//Modified by marcell 200405
		buf.append(isHistoric());

		//CR MPP - start			
		if(this.isHistoric()){		
			if(this.getStartDate()!=null){
				buf.append(sdf.format(this.getStartDate()));
			} else {
				buf.append(sdf.format(new Date()));
			}			
		}
		//CR MPP - end

		//CR-0978 Location Services
		buf.append('_').append(getTariff());

		return buf.toString();
	}

	public String getServiceIdentifier() {
		StringBuffer buf = new StringBuffer();
		buf.append(CONTENT_PRICEPOINT_ID_PREFIX).append(getPackageId()).append('_');
		buf.append(getTaxCode()).append('_');
		buf.append(getContentId()).append('_');
		buf.append(getChannel()).append('_');
		buf.append(getPaymentType()).append('_');
		//[5] Mod Starts
		buf.append(getBearer()).append('_');
		//[5] Mod Ends
		buf.append(getAccessDevice()).append('_');
		buf.append(getAccessDevice());
		return buf.toString();
	}

	public void addBalanceImpact(BalanceImpact impact) {
	
		if (mImpacts == null) {
			mImpacts = new BalanceImpacts();
		}
	
		mImpacts.addBalanceImpact(impact);
	
		/* the following code is to ensure that whenever a link is created
		 * between a pricepoint and a balance impact, a corresponding default
		 * tier is added.  this does cause redundant links between objects,
		 * but is currently perceived to be easier than refactoring the entire
		 * priceplan code!
		 *///rbw20040406
		PricePointTier pptier = new PricePointTier();
		pptier.setBalanceImpacts(mImpacts);
		//pptier.setDefaultPPT(true);
		pptier.setTier(PricePointTier.PPT_DEFAULT_ID);
		addPricePointTier(pptier);
	
	}

	public void deleteAllBalanceImpacts() {
		mImpacts = new BalanceImpacts();
		log.debug("[Deleted all balance impacts.]");
	}

	public void setGlid(String val) {
		mGlid = val;
	}

	@Override
	public void setTaxCode(String val) {
		mTaxCode = val;
	}


	/**
	 * @deprecated 
	 */
	@Deprecated
	public void setBalanceImpacts(BalanceImpacts impacts) {
	    setBalanceImpacts(impacts, new Date());
	}

	public void setBalanceImpacts(BalanceImpacts impacts, Date date) {
		mImpacts = impacts;
	
	
		if (impacts != null) {
	
			if (impacts.getCurrency() != null) {
//PPM136861 refactoring removed
//				setResource(impacts.getCurrency().getCode());
				double rate = impacts.getRate(date);
				setRateWithoutTax(rate);
			} else if (impacts.getNonCurrencyResource() != null) {
//PPM136861 refactoring removed
//				setResource(impacts.getNonCurrencyResource().getCode());
				setRateWithoutTax(impacts.getRate(impacts.getNonCurrencyResource(), date));
			}
	
			/* see addBalanceImpact() for a dissertation on this.
			 *///rbw20030406
			PricePointTier pptier = new PricePointTier();
			pptier.setBalanceImpacts(mImpacts);
			//pptier.setDefaultPPT(true);
			pptier.setTier(PricePointTier.PPT_DEFAULT_ID);
			addPricePointTier(pptier);
		}
	}



	// PPM136861 TODO: can remove now if pricing tool doesn't use
	public ChargingResource getResourceField() {
		// PPM136861 refactoring aL. removed
//		return mResource;
		return getResource();
	}

	/**
	 * the rate ie amount
	 */
	public double getRate() {
	    return getRate(new Date());
	}

	public double getRate(double volumeAmount, Date date) {
	
	
		double rv = getNetRate(volumeAmount);
	
		if (this.getResource() != null && this.getResource().isCurrency()) {
	
			double taxRate = getTaxRate();
			rv = rv + rv * taxRate;
			rv = roundDouble(rv, ErCoreConst.getRoundNthDecimal());
	
		}
		// PPM136861 refactoring aL. added to handle cases where legacy code depends on presence of a resource :-\
		return rv;
	}

	/**
	 * CR1430 changed to get the rate from the Balance Impacts as there
	 * can now be >1
	 */
	public double getNetRate() {
	    return mRateWithoutTax;
	}

	

	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof PricePoint)) {	//instanceof will return false if obj is null
			return false;
		}
		
		PricePoint impl = (PricePoint) obj;
		
		if (!getContentId().equals(impl.getContentId())) {
			return false;
		} else if (!getPackageId().equals(impl.getPackageId())) {
			return false;
		} else if (!getSupplierId().equals(impl.getSupplierId())) {
			return false;
		} else if (isHistoric() != impl.isHistoric()) {
			return false;
		} else if (getPremiumLevel() != impl.getPremiumLevel()) {
			return false;
		} else if (!getPromoCode().equals(impl.getPromoCode())) {
			return false;
			//[5] Mod Starts
		} else if (!getBearer().equals(impl.getBearer())) {
			return false;
			//[5] Mod Ends
		} else if (!getUserGroup().equals(impl.getUserGroup())) {
			return false;
		} else if (getAccessDevice() != impl.getAccessDevice()) {
			return false;
		} else if (getPaymentType() != impl.getPaymentType()) {
			return false;
		} else if (getChannel() != impl.getChannel()) {
			return false;
		} else if (getChargingMethod() != impl.getChargingMethod()) {
			return false;
		} else if (getDuration() != impl.getDuration()) {
			return false;
		}
	
		return true;
	}

	/**
	 * Sets a custom field for the service.
	 * Custom fields appear in <custom_field> tags in the catalog XML.
	 */
	public void setCustomField(String name, String value) {
		mCustomFields.put(name, value);
	}

	/**
	         Retrieves the price for this tier. -1 means that this tier is not valid.
	         This method will change in ER5.
	 */
	public double getPricingModelTierPriceWithTax(String tier, String pricingModel) {
		double priceWithoutTax = getPricingModelTierPrice(tier, pricingModel);
		double rv = -1;
		if (priceWithoutTax>=0) {
			double taxRate = getTaxRate();
	
			priceWithoutTax = 100 * priceWithoutTax + priceWithoutTax * taxRate;
			long val = Math.round(priceWithoutTax);
			rv = val*0.01;
		}
	
		return rv;
	}

	public double getPricingModelTierPrice(String tier, String pricingModel) {
		
		PricePointTier ppt = getPricePointTier();////PPM136861 refactoring aL. START s.get( getPricePointTierId(tier, pricingModel) );
		Double val = null;
		if (ppt != null)
			val = ppt.getPromotionalPrice();
		double rv = -1;
		if (val != null) {
			rv = val.doubleValue();
		}
	
		return rv;
	}

	public String getPricingModelTierPricingText(String tier, String pricingModel) {
		String ret = null;
		PricePointTier ppt = getPricePointTier();////PPM136861 refactoring aL. STARTs.get( getPricePointTierId(tier, pricingModel) );
		if (ppt!=null) ret = ppt.getPromotionalPricingText();
		return ret;
	}

	public void setIsDiscount(boolean val) {
		mIsDiscount = val;
	}

	public void setDiscountPromoText(String text) {
		mPromoText = text;
	}

	public double getStandardRateWithoutTax() {
		//Remedy 5037, Bruno Meseguer, Calculations were wrong. It is as simple as getting the Net Rate.
		return getNetRate();
	
	}

	public void setPricingModelTier(String tier, String pricingModel, boolean discount) {
		if (isOriginal()) {
			throw new RuntimeException("This object cannot be altered. A copy of the price point can be rereived using CatalogApi.getPricePoint().");
		}
	
		setIsDiscount(discount);
		if (tier != null && tier.equals("") == false) {
			double val = getPricingModelTierPrice(tier,pricingModel);
			if (val < 0) {
				// @mawn RF MDC.put(LoggingConstant.METHOD, "setPricingModelTier");
				log.error( "The price for the tier in price point " + getId()
						+ " has a negative price associated with it. Please assign a price to this tier");
				// @mawn RFLoggingHelper.clearMDC();
	
				/**
				 *  @mawn RF
				 logger.error(
						"The price for the tier in  price point "
						+ getId()
						+ " has a negative price associated with it. Please assign a price to this tier");
						**/
			} else {
				setRateWithoutTax(getPricingModelTierPrice(tier,pricingModel));
			}
			if (discount) {
				setDiscountPromoText(getPricingModelTierPricingText(tier,pricingModel));
			}
		}
	}

	/**
	 * does nothing
	 * @deprecated
	 */
	public void deletePricingModelData() {	}

	public void deletePricingModel(String id) {
		PricePointTier[] tiers = getPricePointTiers();
	
		for (int index=0; tiers!=null && index<tiers.length; index++) {
			if (tiers[index].getPricingModel().equals(id)) {
				deletePricingModelTier(tiers[index].getTier(), id);
			}
		}
	}

	/**
	 * does nothing
	 * @param tier
	 * @param pricingModel
	 * @deprecated
	 */
	public void deletePricingModelTier(String tier, String pricingModel) {		}

	public boolean isVolumeType() {
		return mIsVolumeType;
	}

	public void setVolumeType(boolean val) {
		mIsVolumeType = val;
	}

	public boolean isOriginal() {
		return mIsOriginal;
	}

	public void setIsOriginal() {
		mIsOriginal = true;
	}

	/**
	 * @return an array of one PricePointTier
	 */
	public PricePointTier[] getPricePointTiers() {
			//PPM136861 refactoring aL. START
			PricePointTier[] ret = new PricePointTier[1];
			ret[0] = getPricePointTier();
			return ret;
		}

	public void addPricePointTier(PricePointTier pptier) {
		if (pptier != null){
			//PPM136861 refactoring aL. START
			if (pptier.isDefaultPPT() || "default".equals(pptier.getTier())){
				//				add tier
				setPricePointTier(pptier);
			}

		}
	}

	/**
	 * does nothing
	 * @param tier
	 * @param pricingModel
	 */
	public void removePricePointTier(String tier, String pricingModel) {}

	public void removeAllPricePointTiers() {
			//PPM136861 refactoring aL. START
	//		mPricePointTiers = new HashMap<String, PricePointTier>();
			setPricePointTier(new PricePointTier());
		}

	/**
	 * @param map
	 */
	public void setPricePointTiers(Map<String, PricePointTier> map) {
			//PPM136861 refactoring aL. START
			//changed to use individual tier, no more map
			Set<Entry<String,PricePointTier>> m = map.entrySet();
			for (Entry<String, PricePointTier> tier : m) {
				if (tier.getValue().isDefaultPPT() || "default".equals(tier.getValue().getTier())){
					//logger.debug("alexxxx got default tier");
	//				map.remove(tier.getKey());
					setPricePointTier(tier.getValue());
					break;
				}
			}
	//		mPricePointTiers = map;
		}

	/**
	 * Setter method for the Pricepoint ID Link field
	 * @param String
	 * @return void
	 * @since ER 5.1
	 */
	public void setPricepointIdLink(String pricepointid) {
		mPricepointIdLink = pricepointid;
	}

	/**
	 * return null
	 * @param pricingModel
	 * @return
	 * @deprecated
	 */
	public String[] getPricingModelTiers(String pricingModel) {
		return null;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public void setForcedPurchase(boolean forcedPurchase) {
		mForcedPurchase = forcedPurchase;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public void setSubscriptionDuplicate(boolean subscriptionDuplicate) {
		mDuplicateSubscription = subscriptionDuplicate;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public void setFixedExpiryDate(Date val) {
		mPurchaseFixedExpiryDate = val;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public void setInteractiveFlag(String interactiveFlag) {
		mInteractiveFlag = interactiveFlag;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	public void setReserveOnly(boolean reserveOnly) {
		mReserveOnly = reserveOnly;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setMinSubPeriod(int MinSubPeriod) {
		mMinSubPeriod = MinSubPeriod;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setPenaltyCharges(double penaltyCharges) {
		mPenaltyCharges = penaltyCharges;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setCancellation(boolean cancellation) {
		mCancellation = cancellation;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setMonthEndSubscription(String MonthEndSubscription) {
		mMonthEndSubscription = MonthEndSubscription;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setHistoric(boolean historic) {
		mHistoric = historic;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setFixedRecurrence(long fixedRecurrence) {
		mFixedRecurrence = fixedRecurrence;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setReceipting(boolean receipting) {
		mReceipting = receipting;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setReceiptingAttribute(String receiptingAttribute) {
		mReceiptingAttribute = receiptingAttribute;
	}

	public void setFixedRecurringPricePoint(boolean isFixedRecurringPricePoint) {
		mFixedRecurringPricePoint = isFixedRecurringPricePoint;
	}

	/** ADDED FOR EGYPT ER8 STUB **/
	public void setBasePricePoint(boolean BasePricePoint) {
		mBasePricePoint = BasePricePoint;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	@Override
	public void setPreOrder(boolean preOrder) {
		mPreOrder = preOrder;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	public void setOrder(int order) {
		mOrder = order;
	}

	public void setProtectedFk(String protectedFk) {
		mProtectedFk = protectedFk;
	}

	public String getProtectedFk() {
		return mProtectedFk;
	}


	// CR-0095 RBT END

	/**
	 * @deprecated 
	 */
	@Deprecated
	public void recalculateRateWithoutTax() {
	    recalculateRateWithoutTax(new Date());
	}

	public void recalculateRateWithoutTax(Date date) {
		double rv = this.getNetRate();
	    if (mImpacts != null && mImpacts.size() != 0 ) {
	        rv = mImpacts.getRate(0.0, date);
	    }
	    
	    mRateWithoutTax = rv;
	}

	public String getmPricingText1() {
		return mPricingText1;
	}

	public void setmPricingText1(String mPricingText1) {
		this.mPricingText1 = mPricingText1;
	}

	public String getmPricingText2() {
		return mPricingText2;
	}

	public void setmPricingText2(String mPricingText2) {
		this.mPricingText2 = mPricingText2;
	}

	public PricePointTier getPricePointTier() {
		return mPricePointTier;
	}

	public void setPricePointTier(PricePointTier mPricePointTier) {
		this.mPricePointTier = mPricePointTier;
	}

	//CR1430 end
	
	/**
	 * returns true only for non-recurring calendar pricepoints, and false for event OR recurring calendar ppts.
	 * @return
	 */
	public boolean isNonRecurring()	{
		return ChargingMethod.isNonRecurring(getChargingMethod());
	}
	
	/**
	 * returns true only for event pricepoints, and false for non-recurring OR recurring calendar ppts.
	 * @return
	 */
	public boolean isEvent()	{
		return ChargingMethod.isEvent(getChargingMethod());
	}
}
