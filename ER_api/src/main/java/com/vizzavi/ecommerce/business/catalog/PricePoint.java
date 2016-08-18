package com.vizzavi.ecommerce.business.catalog;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

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
public class PricePoint extends RatingAttributes implements java.io.Serializable
{

	private static final long serialVersionUID = 7649541222744453297L;

	static final String ROUND_NTH_DECIMAL = "ROUND_NTH_DECIMAL";
	public static final String PACKAGE_PRICEPOINT_ID_PREFIX = "package:"; //[1] MOD
	public static final String CONTENT_PRICEPOINT_ID_PREFIX = "content:"; //[2] MOD


	////////////////////////////////////////////////////////////
	//@lle STKHREQ242 Grace, suspension period, retry frequency
	///////////////////////////////////////////////////////////
	private PeriodValue gracePeriod = null;
	private PeriodValue suspensionPeriod = null;
	private PeriodValue retryFrequency = null;

	///////////////////////////////////////////////////////
	//@hud STKHREQ13076 SP ROAMING
	// Added rated roaming amount for findPackagesWithService
	private double mRoamingNetAmount	= 0;
	private double mRoamingGrossAmount 	= 0;
	//////////////////////////////////////////////////////


	//////////////////////////////////////////////////////
	//@hud STKHREQ36 Micro Service
	private double mAccessDuration		= -1;
	//////////////////////////////////////////////////////

	/**
        The identifier of the price point
	 */
	protected Long mKey;
	protected String mCreatedBy;
	protected String mModifiedBy;
	protected Date mModifiedDate;
	protected char mActiveStatus;
	protected String mId;

	protected String mContentId = Constants.STRING_MATCH_ALL;
	protected String mPackageId = Constants.STRING_MATCH_ALL;

	/** The price/token price of the package */
	protected double mRateWithoutTax = -9999;

	/** The price/token price of the package */
	protected double mAlternativeRate = -9999;
	protected ChargingResource mAlternativeCurrency = null;


	/** The type of resource whether token or currency */
	protected ChargingResource mResource = null;


	/**
        Set to true if the price point is not valid anymore and is not in the priceplan
	 */
	protected boolean mIsArchived = false;


	protected String mGlid = "";

	protected String mTaxCode = "";

	protected double mTaxRate;

	protected Date mPurchaseExpiryDate;

	protected Date mPurchaseStartDate;


	/** Custom fields for pricing text */
	protected String mPricingText1 = "";
	protected String mPricingText2 = "";

	/** The list of pricing text1 in multiple languages including the default language.
	 * Key is the language code.
	 * Since ER9 **/
	protected HashMap<String, String> pricingTextList1 = null;

	/** The list of pricing text2 in multiple languages including the default language.
	 * Key is the language code.
	 * Since ER 9**/
	protected HashMap<String, String> pricingTextList2 = null;

	/** custom fields defined in catalog using <custom_field> tag */
	protected Map<String, String> mCustomFields = new HashMap<String, String>();

	protected double mStandardRate = -1;

	protected ResourceBalance[] mBalances;

	/**
	 * The DRM Object associated with the price point
	 * @since ER 5.1
	 */
	protected DRMObject m_DRMObject=null;

	/**
	 * The linked price point id associated with the price point, used for selection of renewal package for trial packages
	 * @since ER 5.1
	 */
	protected String mPricepointIdLink;



	/** ADDED FOR EGYPT ER6 STUB **/
	protected boolean mForcedPurchase=false;

	/** ADDED FOR EGYPT ER6 STUB **/
	protected boolean mDuplicateSubscription=true;
	//protected Date mPurchaseFixedExpiryDate = new Date();
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

	//[11] Mod Start
//	protected VolumeModel mVolumeModel;
	//[11] Mod Start

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

	/** ADDED FOR ER6.5 STUB **/
	//public static final String RECEIPTING_ATTRIBUTE_NONE= "NONE";
	//public static final String RECEIPTING_ATTRIBUTE_EXPRESS= "";
	//public static final String RECEIPTING_ATTRIBUTE_NON_= "NON-";
	//public static final String RECEIPTING_ATTRIBUTE_ALL= "ALL";

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
	//[13] Mod End

	/** ADDED FOR EGYPT ER7 STUB **/
//	protected boolean mPreOrder=false;

	/** ADDED FOR EGYPT ER7 STUB **/
	protected int mOrder;

	/** ADDED FOR ER7 STUB **/
	protected SuperCreditPricePoint mCreditPurchasePricePoints [];

	/** ADDED FOR ER7 STUB **/
	protected boolean mPurchaseableBySuperCredit=false;

	/** ADDED FOR ER7 STUB **/
	protected boolean mbSuperCreditDonor=false;

	/** Remedy 4945 **/
	protected boolean mFixedRecurringPricePoint=false;

	protected boolean mValidSuperCredit = false;

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
	//REMEDY 7306
	//protected boolean isOnRoamingPresent = true;
	//protected boolean isOffRoamingPresent = true;
	protected boolean isOnRoamingPresent = false;
	protected boolean isOffRoamingPresent = false;

	//END REMEDY 7306	
	//Roaming END

	protected boolean isZeroCostIgnore = false;

	//CR-0978 Location Services
	protected boolean hideForPurchaseOptions = false;

	//REMEDY 6968
	static final String ROUND_NTH_EXPRESS_PATTERN_DEFAULT = "0.00";
	static final String ROUND_NTH_EXPRESS_PATTERN_INTEGER = "0.##";
	//MQC 6016 - change to private variable from static
	//WAS
	//static final String ROUND_NTH_EXPRESS_PATTERN; 
	//NOW
	private String ROUND_NTH_EXPRESS_PATTERN;

	//MQC 6016 - move retrieval of property to local method of where it is used
	// static {

	//     String round_nth_express_pattern = ConfigProvider.getProperty("ROUND_NTH_EXPRESS_PATTERN");
	//     if (round_nth_express_pattern == null) {
	//         ROUND_NTH_EXPRESS_PATTERN = ROUND_NTH_EXPRESS_PATTERN_DEFAULT;
	//     }
	//     else {
	//         ROUND_NTH_EXPRESS_PATTERN = round_nth_express_pattern;
	//     }

	//}
	//END REMEDY 6968

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
	
	//CR1564 -Utctimezone for diff region in country
	/**
	 * @deprecated 
	 */
	@Deprecated
	void init(PricePoint pt) {
		init(pt, new Date());
	}

	//CR1564 -Utctimezone for diff region in country
	void init(PricePoint pt, Date date)
	{
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
		m_DRMObject = pt.getDRMObject();
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
		mPurchaseableBySuperCredit = pt.isPurchaseableBySuperCredit();
		mCreditPurchasePricePoints = pt.getCreditPurchasePricePoints();
		mbSuperCreditDonor = pt.isSuperCreditDonor();

		mRateWithoutTax = pt.getNetRate();

		mIsArchived = pt.isArchived();

		//ER 7 Compliant
		//FIX
		if(pt.getPricingText1() != null && pt.getPricingText1().length() > 0)
			mPricingText1 = pt.getPricingText1();

		if(pt.getPricingText2() != null && pt.getPricingText2().length() > 0)
			mPricingText2 = pt.getPricingText2();
		//END FIX
		//ER 7 Compliant


		setPricingTextList1(pt.getPricingTextList1());
		setPricingTextList2(pt.getPricingTextList2());
		mResource = pt.getResource();
		mActiveStatus = pt.getActiveStatus();
		mAlternativeCurrency = pt.getAlternativeCurrency();
		mBalances = pt.mBalances;
		mCreatedBy = pt.getCreatedBy();
		mIsDiscount = pt.isDiscount();
		mKey = pt.getKey();
		mModifiedBy = pt.getModifiedBy();
		mPricepointIdLink = pt.getPricepointIdLink();
		mPromoText = pt.mPromoText;

		mStandardRate = pt.getStandardRate(date);

		mValidSuperCredit = pt.isValidSuperCreditOption();
		mCustomFields = new HashMap<String, String>(pt.getCustomFields());
		//Remedy 4945
		mFixedRecurringPricePoint = pt.isFixedRecurringPricePoint();
		//[13] Mod Start
		mPaymentHandler = pt.getPaymentHandler();
		//[13] Mod Start
		//ER 8 Stubb
		mBasePricePoint = pt.isBasePricePoint();
		//ER 8 Stubb


		//@hud STKHREQ13076
		mRoamingGrossAmount = pt.getRoamingGrossAmount();
		mRoamingNetAmount = pt.getRoamingNetAmount();
		onFootPrintPrice = pt.getOnFootPrintPrice();
		offFootPrintPrice = pt.getOffFootPrintPrice();

		//@hud STKHREQ36
		mAccessDuration = pt.getAccessDuration();

		//@lle STKHREQ242
		gracePeriod = pt.getGracePeriod();
		suspensionPeriod = pt.getSuspensionPeriod();
		retryFrequency = pt.getRetryFrequency();

		this.pricingTextTemplateName1 = pt.pricingTextTemplateName1;
		this.pricingTextTemplateName2 = pt.pricingTextTemplateName2;
		this.pricingTextTemplateNameRoaming = pt.getPricingTextTemplateNameRoaming();

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
		mNetworkCode = pt.getNetworkCode();
		mNetworkCodeStr = pt.getNetworkCodeStr();
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
	public PricePoint()
	{
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
	 * @deprecated 
	 */
	@Deprecated
	public PricePoint(RatingAttributes attr) {
		this(attr, new Date());
	}

	//CR1564 -Utctimezone for diff region in country
	public PricePoint(RatingAttributes attr, Date date)
	{
		super(attr);
		if (attr instanceof PricePoint) {
			init((PricePoint)attr, date);
			/*
			PricePoint pt = (PricePoint)attr;
			mId = pt.mId;
			mContentId = pt.mContentId;
			mPackageId = pt.mPackageId;
			mTaxRate = pt.getTaxRate();
			mTaxCode = pt.getTaxCode();
			mPurchaseStartDate = pt.getStartDate();
			mPurchaseExpiryDate = pt.getExpiryDate();
			//[1] Mod start
			mPurchaseFixedExpiryDate = pt.getFixedExpiryDate();
			mInteractiveFlag = pt.getInteractiveFlag();
			//[1] Mod end
			m_DRMObject = pt.getDRMObject();
			mDuplicateSubscription = pt.isSubscriptionDuplicate();
			mForcedPurchase = pt.isForcedPurchase();
			mReserveOnly = pt.isReserveOnly();
			//[11] Mod Start
			mVolumeModel = pt.getVolumeModel();
			//[11] Mod End
			mPreOrder = pt.isPreOrder();
			mOrder = pt.getOrder();
			mMinSubPeriod = pt.getMinSubPeriod();
			mPenaltyCharges = pt.getPenaltyCharges();
			mCancellation = pt.getCancellation();
			mMonthEndSubscription = pt.getMonthEndSubscription();
			recurrenceDay = pt.getRecurrenceDay();
			alignWithExternal = pt.isAlignWithExternal();
			mHistoric = pt.isHistoric();
			mFixedRecurrence = pt.getFixedRecurrence();
			mReceipting = pt.isReceipting();
			mReceiptingAttribute = pt.getReceiptingAttribute();
			mPurchaseableBySuperCredit = pt.isPurchaseableBySuperCredit();
			mCreditPurchasePricePoints = pt.getCreditPurchasePricePoints();
			mbSuperCreditDonor = pt.isSuperCreditDonor();

			mRateWithoutTax = pt.getNetRate();
			mIsArchived = pt.isArchived();

                        //ER 7 Compliant
			mPricingText1 = pt.getPricingText1();
			mPricingText2 = pt.getPricingText2();
                        //ER 7 Compliant


			this.setPricingTextList1(pt.getPricingTextList1());
			this.setPricingTextList2(pt.getPricingTextList2());
			mResource = pt.getResource();
			mActiveStatus = pt.getActiveStatus();
			mAlternativeCurrency = pt.getAlternativeCurrency();
			mBalances = pt.mBalances;
			mCreatedBy = pt.getCreatedBy();
			mIsDiscount = pt.isDiscount();
			mKey = pt.getKey();
			mModifiedBy = pt.getModifiedBy();
			mPricepointIdLink = pt.getPricepointIdLink();
			mPromoText = pt.mPromoText;
			mStandardRate = pt.getStandardRate();
			mValidSuperCredit = pt.isValidSuperCreditOption();
			mCustomFields = new HashMap(pt.getCustomFields());
			//[13] Mod Start
			mPaymentHandler = pt.getPaymentHandler();
			//[13] Mod Start
			//ER 8 Stubb
			mBasePricePoint = pt.isBasePricePoint();
			//ER 8 Stubb

			//@hud STKHREQ13076
			mRoamingGrossAmount = pt.getRoamingGrossAmount();
			mRoamingNetAmount = pt.getRoamingNetAmount();
			onFootPrintPrice = pt.getOnFootPrintPrice();
			offFootPrintPrice = pt.getOffFootPrintPrice();

			//@hud STKHREQ36
			mAccessDuration = pt.getAccessDuration();

			this.pricingTextTemplateName1 = pt.pricingTextTemplateName1;
			this.pricingTextTemplateName2 = pt.pricingTextTemplateName2;

			//@lle STKHREQ242
			gracePeriod = pt.getGracePeriod();
			suspensionPeriod = pt.getSuspensionPeriod();
			retryFrequency = pt.getRetryFrequency();
			 */

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
		this(auth, new Date());
	}

	public PricePoint(BaseAuthorization auth, Date date) {
		this(auth.getMatchingAttributes(), date);
		// balance impacts
		mId = auth.getRateIdentifier();
		mPackageId = auth.getPackageId();
		mContentId = auth.getContentId();
		mTaxRate = auth.getTaxRate();
		mTaxCode = auth.getTaxCode();
		mStandardRate = auth.getStandardRate();
		mRateWithoutTax = auth.getNetRate();
		mResource = auth.getResource();
		mIsDiscount = auth.isDiscount();
		mPromoText = auth.getDiscountPromoText();
		setPricingText1(auth.getPricingText1());
		setPricingText2(auth.getPricingText2());

		//ER 7 Compliant
		mPricingText1 = auth.getPricingText1();
		mPricingText2 = auth.getPricingText2();
		//ER 7 Compliant

		if (auth instanceof PurchaseAuthorization) {
			PurchaseAuthorization pAuth = (PurchaseAuthorization) auth;
			mBalances = pAuth.getResourceBalances();
		}
		if (auth instanceof UsageAuthorization) {
			UsageAuthorization uAuth = (UsageAuthorization) auth;
			mPreOrder = uAuth.getIsPreordered() == 1?true:false;
		}

		//@hud STKHREQ13076
		mRoamingGrossAmount = auth.getRoamingGrossAmount();
		mRoamingNetAmount = auth.getRoamingNetAmount();
		mNetworkCode = auth.getNetworkCode();

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
		mResource = res;
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
		mResource = res;
		mIsArchived = archiveFlag;
	}

	public PricePoint(Long key,String id, int durationCode, int chargingMethod, String promoCode, double rate, ChargingResource res, Map<?, ?> impacts, boolean archiveFlag,
			String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
	{
		mKey = key;
		mCreatedBy = createdBy;
		mModifiedBy = modifiedBy;
		mModifiedDate = modifiedDate;
		mActiveStatus = activeStatus;

		mId = id;
		setDuration(durationCode);
		setChargingMethod(chargingMethod);
		String[] codes = new String[] { promoCode};
		setPromoCodes(codes);
		mRateWithoutTax = rate;
		mResource = res;
		mIsArchived = archiveFlag;
	}

	public PricePoint(Long key,String id, int durationCode, int chargingMethod, String promoCode,String bearerId, double rate, ChargingResource res, Map<?, ?> impacts, boolean archiveFlag,
			String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
	{
		mKey = key;
		mCreatedBy = createdBy;
		mModifiedBy = modifiedBy;
		mModifiedDate = modifiedDate;
		mActiveStatus = activeStatus;

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
		mResource = res;
		mIsArchived = archiveFlag;
	}

	public Long getKey() {
		return mKey;
	}


	public String getCreatedBy() {
		return mCreatedBy;
	}

	public String getModifiedBy() {
		return mModifiedBy;
	}

	public Date getModifiedDate() {
		return mModifiedDate;
	}

	public char getActiveStatus() {
		return mActiveStatus;
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
	public boolean isArchived()
	{
		return mIsArchived;
	}

	public boolean isValidSuperCreditOption() {
		return mValidSuperCredit;
	}

	public boolean isBasePricePoint() {
		return mBasePricePoint;
	}


	public double getRate() {
		return getRate(new Date());
	}

	//CR1564 -Utctimezone for diff region in country
	/**
        Retrieves the price of the price point.
        This will be the price the customer is charged. If the price point is rated
        at a discount, this rate will be the discount rate. getStandardRate() will retrieve the
        normal price when the discount does not apply.
        @return double the price
	 */
	public double getRate(Date date)
	{
		//int round_nth_decimal =  4;
		double rv = -1;

		int round_nth_decimal =  ConfigProvider.getPropertyAsInteger("ROUND_NTH_DECIMAL", 4);


		rv = (1 + getTaxRate(date)) * getNetRate();

		return roundDouble(rv,round_nth_decimal);
	}

	public double getNetRate()
	{
		return mRateWithoutTax;
	}

	//CR1564 -Utctimezone for diff region in country
	/**
	 * @deprecated 
	 */
	@Deprecated
	public double getNetRate(double volumeAmount) {
		return getNetRate(volumeAmount, new Date());
	}

	//CR1564 -Utctimezone for diff region in country
	@Deprecated
	public double getNetRate(double volumeAmount, Date date)
	{
		return getNetRate();
	}

	/**
        This is the same as getDrviceType()
	 */
	public int getAccessDevice()
	{
		return getDeviceType();
	}

	/**
    Calculates amount based on volume amount
    overridden by the implementation
	 */
	@Deprecated
	public double getRate(double volumeAmount)
	{
		return getRate();
	}

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
        The type of currency or tokens.
        @return the currency used to purchase/use price point
	 */
	public ChargingResource getResource()
	{
		return mResource;
	}

	//MQC 6610 - setter method for charging resource
	public void setResource(ChargingResource res)
	{
		mResource = res;
	}

	/**
        The type of currency or tokens.
        @return the currency used to purchase/use price point
	 */
	public ChargingResource getAlternativeCurrency()
	{
		return mAlternativeCurrency;
	}

	/**
        The array of resources that the user will get if the price point is purchased
	 */
	public ChargingResource[] getBalanceImpacts()
	{
		List<ChargingResource> rv = new ArrayList<ChargingResource>();
		for (int index=0; mBalances!=null && index<mBalances.length; index++) {
			//ER7 - changed for super credits requirement, only return the negative balances
			//REMEDY 6493
			if (mBalances[index].getResource().isSuperCredit()) {
				if (mBalances[index].getBalance() < 0.0) {
					rv.add(mBalances[index].getResource());
				}
			}
			else if (mBalances[index].getBalance() != 0.0) {
				rv.add(mBalances[index].getResource());
			}
			//REMEDY 6493 END
		}
		return rv.toArray(new ChargingResource[rv.size()]);
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

	/**
	 * e.g. "package:p001_3_4_999_999_*_*_true duration code 4, charging method 3, rate 2.35"
	 * @return
	 * @see getDescription() for something more user-friendly
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder(32);
		sb.append(" : duration code ").append(getDuration());
		sb.append(", charging method ").append(getChargingMethod());
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
		sb.append(", rate=").append(getRate());
		return sb.toString();
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
	public double getTaxRate(Date date)
	{
		//MQC 5654 - add Tax object for real time tax rate calculation
		if (mTax != null) {

			// Need to put some extra logic here to get the proper tax rate
			boolean isTimeZoneAdjustedDate = isTimeZoneAdjustedDate(date);

			if (isTimeZoneAdjustedDate) {
				return mTax.getTaxRate(date);
			} else if (useStaticValues) {
				return mTaxRate;
			} else {
				return mTax.getTaxRate(date);
			}
		}
		else {
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
	
//	/**
//        This should not be used
//        @deprecated
//	 */
//	@Deprecated
//	public boolean isComplex() {
//		return false;
//	}
//
//	/**
//        This should not be used
//
//	 */
//	public void enableComplex() {
//		//complex=true;
//	}
//
//	/**
//        This should not be used
//
//	 */
//	public void disableComplex() {
//		//complex=false;
//	}
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
	public Map<String, String> getCustomFields() {
		return mCustomFields;
	}

	// new methods to support promotional activity
	protected boolean mIsDiscount=false;
	protected String mPromoText="";

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
		boolean isTimeZoneAdjustedDate = isTimeZoneAdjustedDate(date);

		if (rv<0 || isTimeZoneAdjustedDate) {
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
					double net = getStandardRate(date) / ( 1 + getTaxRate(date) );
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
        Retrieves all of the tiers valid for this price point.
        This method will change in ER5.
	 */
	public String[] getPricingModelTiers(String pricingModel)
	{
		throw new RuntimeException("This is implemented in the implementation class");
	}

	/**
        Retrieves the price for this tier. -1 means that this tier is not valid.
        This method will change in ER5.
	 */
	public double getPricingModelTierPriceWithTax(String tier, String pricingModel)
	{
		//REMEDY 7010
		//This method should not be used. The method to be used is in the PricePointImpl
		//throw new RuntimeException("This is implemented in the implementation class");
		return 0;
	}

	/**
        The pricing text for this tier.
        This method will change in ER5.
	 */
	public String getPricingModelTierPricingText(String tier, String pricingModel)
	{
		//REMEDY 7010
		//This method should not be used. The method to be used is in the PricePointImpl
		//throw new RuntimeException("This is implemented in the implementation class");
		return "";
	}

	/**
	 * return the DRM Object associated with the price point.
	 * @return String
	 * @since 5.1
	 */
	public DRMObject getDRMObject()
	{
		return m_DRMObject;
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

	/** ADDED FOR ER6.5 STUB **/
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
	public int getOrder() {
		return mOrder;
	}

	public static final double roundDouble(double d, int nthDecimal)
	{
		return Math.round(d * Math.pow(10, nthDecimal)) / Math.pow(10,nthDecimal);
	}

	/** ADDED FOR EGYPT ER7 STUB superCredit **/
	public SuperCreditPricePoint[] getCreditPurchasePricePoints() {
		return mCreditPurchasePricePoints;
	}

	/** ADDED FOR ER7 STUB **/
	public boolean isPurchaseableBySuperCredit() {
		return mPurchaseableBySuperCredit;
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

	/** ADDED FOR ER7 STUB **/
	public boolean isSuperCreditDonor() {
		return mbSuperCreditDonor;
	}

	//[12] Mod Start
	/**
        Take the input of usageClassName, UsageSubClassName and VolumeModelRating and entry point.
        From the volume model rating get the id of the volume model, i.e
        volumeModelRating.getId(),

        based on the volume rating model id, usage class and usage subclass and
        entrypoint, look in the volume model tree for the matching volume model
        rating object
        once you get the rating
        from the volumemodelrating object get the tEventTiers and populate them with the rating information.
        Once you have done this return the same object in the method.
        I believe this may not be very clear, we can have a call tomorrow if you need to understand further.
	 */
//	public VolumeModelRating getVolumeModelRating(String usageClassName, String usageSubClassName, String eventId, String childEventId) {
//		VolumeModelRating volumeModelRating = null;
//
//		if(mVolumeModel != null && usageClassName != null && usageSubClassName != null) {
//			UsageClass [] uClass = mVolumeModel.getUsageClass();
//			if(uClass != null) {
//				for (UsageClass uClas : uClass) {
//					if(uClas.getId() != null && uClas.getId().equals(usageClassName)) {
//						UsageSubClass [] uSub = uClas.getUsageSubClass();
//						if(uSub != null) {
//							for (UsageSubClass element : uSub) {
//								if(element.getId() != null && element.getId().equals(usageSubClassName)) {
//									EventGroup eGroup = element.getEventGroup();
//									if(eGroup!=null) {
//										EventModel [] eModel = eGroup.getEventModel();
//										if(eModel != null) {
//											for (EventModel element2 : eModel) {
//												if(childEventId != null && !childEventId.equals("")) { //this is a call for child event model
//													if(element2.getId() != null && element2.getId().equals(eventId)) {
//														ChildEventGroup cEGroup = element2.getChildEventGroup();
//														if(cEGroup!=null) {
//															ChildEventModel [] cEModel = cEGroup.getChildEventModel();
//															if(cEModel != null) {
//																for (ChildEventModel element3 : cEModel) {
//																	if(element3.getId() != null && element3.getId().equals(childEventId)) {
//																		volumeModelRating = getNewVolumeModelRating(
//																				childEventId,
//																				null,//eventgroup,
//																				element3.getStepTier(),
//																				element3.getAllEach(),
//																				element3.getVolumeType(),
//																				setRatingAttributes(element3.getRange()));
//																		volumeModelRating.setName(element3.getName());
//																		return volumeModelRating;
//																	}
//																}
//															}
//														}
//													}
//												} else { // this is a call for event model
//													if(element2.getId() != null && element2.getId().equals(eventId)) {
//														volumeModelRating = getNewVolumeModelRating(
//																eventId,
//																null,//eventgroup,
//																element2.getStepTier(),
//																element2.getAllEach(),
//																element2.getVolumeType(),
//																setRatingAttributes(element2.getRange()));
//														volumeModelRating.setName(element2.getName());
//														return volumeModelRating;
//													}
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		return null;
//	}

//	private VolumeModelRating getNewVolumeModelRating(String childEventId, com.vizzavi.ecommerce.business.charging.EventGroup eventGroup, String stepTier, String allEach, String volumeType, ArrayList<EventTier> eventTiers) {
//		int iStepTier = 0;
//		if(stepTier != null && stepTier.equals("Step")) {
//			iStepTier = VolumeModelRating.STEP;
//		} else if(stepTier != null && stepTier.equals("Tier")) {
//			iStepTier = VolumeModelRating.TIER;
//		}
//		int iAllEach = 0;
//		if(allEach != null && allEach.equals("All")) {
//			iAllEach = VolumeModelRating.ALL;
//		} else if(allEach != null && allEach.equals("Each")) {
//			iAllEach = VolumeModelRating.EACH;
//		}
//
//		int iVolumeType = 0;
//		if(volumeType != null && volumeType.equals("NULL")) {
//			iVolumeType = VolumeModelRating.VOLUME_NON;
//		} else if(volumeType != null && volumeType.equals("True")) {
//			iVolumeType = VolumeModelRating.VOLUME_TRUE;
//		} else if(volumeType != null && volumeType.equals("Banded")) {
//			iVolumeType = VolumeModelRating.VOLUME_BANDED;
//		}
//		return new VolumeModelRating(
//				childEventId,
//				//eventGroup,
//				iStepTier,
//				iAllEach,
//				iVolumeType,
//				eventTiers);
//	}
//
//	private ArrayList<EventTier> setRatingAttributes(Range[] range) {
//		ArrayList<EventTier> arrEveTiers = new ArrayList<EventTier>();
//		if(range == null) return null;
//		for (Range element : range) {
//			//@hud STKHREQ13076
//			EventTier eveTier = new EventTier(
//					(int)element.getFrom(),
//					(int)element.getTo(),
//					(float)element.getRate(),
//					element.getOnFootPrintPrice(),
//					element.getOffFootPrintPrice()
//					);
//			arrEveTiers.add(eveTier);
//		}
//		return arrEveTiers;
//	}
//	//[12] Mod End
//
//	//[13] Mod Start
//	public com.vizzavi.ecommerce.business.charging.EventGroup getEventGroup(String usageSubClassName) {
//		if(mVolumeModel != null && usageSubClassName != null) {
//			UsageClass [] uClass = mVolumeModel.getUsageClass();
//			if(uClass != null) {
//				for (UsageClass uClas : uClass) {
//					UsageSubClass [] uSub = uClas.getUsageSubClass();
//					if(uSub != null) {
//						for (UsageSubClass element : uSub) {
//							if(element.getId() != null && element.getId().equals(usageSubClassName)) {
//								return getChargingEventGroup(element.getEventGroup());
//							}
//						}
//					}
//				}
//			}
//		}
//		return null;
//	}
//
//	private com.vizzavi.ecommerce.business.charging.EventGroup getChargingEventGroup(EventGroup eventGroup) {
//		int eventCap=0,childEventCap=0;
//		String childEventMultiplier = eventGroup.getChildEventMultiplier();
//		if(eventGroup.getEventCap() != null){
//			try {
//				//REMEDY 4862/4863 - Added the check for length = 0. Was throwing exception previously
//				if (eventGroup.getEventCap() == null || eventGroup.getEventCap().equalsIgnoreCase("NULL") || (eventGroup.getEventCap().length() ==0)) {
//					eventCap = 0;
//				}
//				else {
//					eventCap = Integer.parseInt(eventGroup.getEventCap());
//				}
//			} catch (NumberFormatException e) {
//				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//			}
//		}
//		if(eventGroup.getChildEventCap() != null) {
//			try {
//				//REMEDY 4862/4863 - Added the check for length = 0. Was throwing exception previously
//				if (eventGroup.getChildEventCap() == null || eventGroup.getChildEventCap().equalsIgnoreCase("NULL") || (eventGroup.getChildEventCap().length()==0)) {
//					childEventCap = 0;
//				}
//				else {
//					childEventCap = Integer.parseInt(eventGroup.getChildEventCap());
//				}
//			} catch (NumberFormatException e) {
//				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//			}
//		}
//		ArrayList<Priority> arrPriority = new ArrayList<Priority>();
//		if(eventGroup.getEventModel()!=null) {
//			EventModel [] eveModel = eventGroup.getEventModel();
//			for (EventModel element : eveModel) {
//				Priority pr = new Priority();
//				pr.setPriority(element.getPriority());
//				pr.setEventModelName(element.getName());
//				arrPriority.add(pr);
//			}
//		}
//		com.vizzavi.ecommerce.business.charging.EventGroup chrginEGrp = new com.vizzavi.ecommerce.business.charging.EventGroup(eventCap,childEventCap,childEventMultiplier,arrPriority);
//		return chrginEGrp;
//	}

	/**
	 * @version   ER 8.0
	 * @author    ER-WC - Remedy 3645
	 * @date      9-Dec-2005
	 * @description  The returns an array of String Objects containing the non "*" & "" UserGroups associated
	 * 				with this pricepoint
	 * @return      Array of String Objects containing the non "*" & "" UserGroups associated with this pricepoint
	 **/
	public Object[] getNonMatchAllUserGroups(){
		ArrayList<String> list = new ArrayList<String>();

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
		return list.toArray();
	}

	/**
	 * @version   ER 8.0
	 * @author    VFE � PS Team
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
	 *@ VFE � PS Code Ends
	 **/

	/**
	 * @version   ER 8.0
	 * @author    VFE � PS Team
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
	 *@ VFE � PS Code Ends
	 **/
	/**
	 * This method returns a boolean to allow a message to be sent to the payment handler
	 * @version   ER 8.0
	 * @author    VFE � PS Team
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

	public HashMap<String, String> getPricingTextList1() {
		return pricingTextList1;
	}

	public void setPricingTextList1(HashMap<String, String> pricingTextList1) {
		this.pricingTextList1 = pricingTextList1;
	}

	public HashMap<String, String> getPricingTextList2() {
		return pricingTextList2;
	}

	public void setPricingTextList2(HashMap<String, String> pricingTextList2) {
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

		if(mPricingText1!= null && mPricingText1.length() > 0)
			return mPricingText1;

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

		if(mPricingText2!= null && mPricingText2.length() > 0)
			return mPricingText2;

		//END FIX

		return this.pricingTextList2.get(Constants.DEFAULT_LANGUAGE_CODE);
	}

	public void setPricingText1(String text)
	{
		//ER 7 Compliant
		mPricingText1 = text;
		//ER 7 Compliant

		setPricingText1(Constants.DEFAULT_LANGUAGE_CODE, text);
	}

	public void setPricingText2(String text)
	{
		//ER 7 Compliant
		mPricingText2 = text;
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
			mPricingText1 = text;
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
			mPricingText2 = text;
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
	public void setPricingTextTemplateNameRoaming(String PricingTextTemplateNameRoaming){
		this.pricingTextTemplateNameRoaming=PricingTextTemplateNameRoaming;
	}
	public void setOnFootPrintPrice(double OnFootPrintPrice){
		this.onFootPrintPrice = OnFootPrintPrice;
	}
	public void setOffFootPrintPrice(double OffFootPrintPrice){
		this.offFootPrintPrice = OffFootPrintPrice;
	}
	public void setIsOnRoamingPresent(boolean IsOnRoamingPresent){
		this.isOnRoamingPresent = IsOnRoamingPresent;
	}
	public void setIsOffRoamingPresent(boolean IsOffRoamingPresent){
		this.isOffRoamingPresent = IsOffRoamingPresent;
	}
	public String getPricingTextTemplateNameRoaming(){
		return this.pricingTextTemplateNameRoaming ;
	}
	public double getOnFootPrintPrice(){
		return this.onFootPrintPrice ;
	}
	public double getOffFootPrintPrice(){
		return this.offFootPrintPrice ;
	}
	public boolean getIsOnRoamingPresent(){
		return this.isOnRoamingPresent ;
	}
	public boolean getIsOffRoamingPresent(){
		return this.isOffRoamingPresent ;
	}
	//@hud
	public boolean isRoamingEnabled() {
		return isOnRoamingPresent || isOffRoamingPresent;
	}
	//Roaming END



	///////////////////////////////////////////////////////
	//@hud STKHREQ13076 SP ROAMING
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
	public int getRoamingType() {
		if (getNetworkCode() == null) {
			return ErCoreConst.ROAMING_DOMESTIC;
		}
		else {
			return getNetworkCode().getRoamingType();
		}
	}


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
	public PeriodValue getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(PeriodValue gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public PeriodValue getRetryFrequency() {
		return retryFrequency;
	}

	public void setRetryFrequency(PeriodValue retryFrequency) {
		this.retryFrequency = retryFrequency;
	}

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
	public ResourceBalance[] getResourceBalances () {
		return this.mBalances;
	}

	//MQC 5067 - return the custom resource balances
	public ResourceBalance[] getCustomResourceBalances () {
		List<ResourceBalance> rv = new ArrayList<ResourceBalance>();
		for (int index=0; mBalances!=null && index<mBalances.length; index++) {
			if (mBalances[index].getResource().isResource()) {
				rv.add(mBalances[index]);
			}
		}
		if (rv != null && rv.size() > 0) {
			return rv.toArray(new ResourceBalance[rv.size()]);
		}
		else {
			return null;
		}
	}

	//MQC 5654 - add Tax object for real time tax rate calculation
	public Tax getTax() {
		return mTax;
	}

	//CR1564 -Utctimezone for diff region in country - moved from PricePointImpl
	//MQC 5654 - add Tax object for real time tax rate calculation
	public void setTax(Tax taxObject) {
		mTax = taxObject;
	}

	//CR1430
	public double getFutureRate(Date futureDate) {
		return getNetRate();
	}

	//CR1564 -Utctimezone for diff region in country
	public boolean isUseStaticValues() {return useStaticValues;}
	public void setUseStaticValues(boolean useStaticValues) {this.useStaticValues = useStaticValues;}

	//CR1564 -Utctimezone for diff region in country
	public boolean isTimeZoneAdjustedDate(Date date) {
		boolean rv = false;
		try {
			if (date != null) {
				Date nowDate = new Date();
				Long dateDiff = date.getTime() - nowDate.getTime();
				// If date difference between the input date and nowDate is greater than 10 minutes we are dealing with a time zone adjusted date
				if (dateDiff > 600000 || dateDiff < -600000) {
					rv = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rv;
	}

	//CR1429 - Begin - Always validate MSISDN during purchase
	public boolean isAlwaysValidateMsisdn() {
		return alwaysValidateMsisdn;
	}

	public void setAlwaysValidateMsisdn(boolean alwaysValidateMsisdn) {
		this.alwaysValidateMsisdn = alwaysValidateMsisdn;
	}
	//CR1429 - End

	//CR1759 - start
	public void setIsPurchaseableBySuperCredit(boolean isPurchaseableBySuperCredit)
	{
		this.mPurchaseableBySuperCredit = isPurchaseableBySuperCredit;
	}

	public void setDRMObject(DRMObject drmObject)
	{
		this.m_DRMObject = drmObject;
	}

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
}
