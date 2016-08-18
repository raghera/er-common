package com.vizzavi.ecommerce.business.catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.internal.model.PricingModel;
import com.vizzavi.ecommerce.business.common.ChargingMethod;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.Constants;
import com.vizzavi.ecommerce.common.Utils;
import static org.apache.commons.lang.StringUtils.isNotBlank; 


/**
 * CatalogPackage aggregates a number of services.
 * There are two main types of package
 *     Subscription package
 *     Event package
 *
 * A subscription package is a package that has a start and expiry date. A subscription package
 * can be renewed once the subscription has expired.
 *
 * An event package would be used for java download. It would
 * A Catalog Package has a number of price points.
 *
 */
public class CatalogPackage implements java.io.Serializable
{
	private    static final long serialVersionUID = -1080643286921795316L;
	public static String DEFAULT_ID = "default";

	private static final Logger logger = LoggerFactory.getLogger(CatalogPackage.class);
	/**
        This is used to seperate the package and price point ids
	 */
	//@hud RFRFRF
	public final static String PRICEPOINT_ID_STARTER = ":";
	public final static String PACKAGE_DELIMITER = "__X__";
	public final static String TIER_DELIMITER = "__Y__";
	public final static String MODEL_DELIMITER = "__Z__";
	public final static String SUPERCREDIT_DELIMITER = "__SC__";

	/**
        The different types of package. The token package is not used in Vodafone Live
	 */
	public final static String CALENDAR_PACKAGE_TYPE = "Calendar";
	public final static String EVENT_PACKAGE_TYPE = "Event";
	//public final static String TOKEN_PACKAGE_TYPE = "Token";
	protected Long mKey;
	protected String mCreatedBy;
	protected String mModifiedBy;
	protected Date mModifiedDate;
	protected char mActiveStatus;
	protected String mPricingModelFk;

	/** ER 7 Compliant Custom fields for pricing text */
	protected String mPricingText1 = "";
	protected String mPricingText2 = "";

	/**
        A package can have two types of identifier.
            1. A package id defined in priceplan.  This type of package does
                not have a price as it depends on the purchase attributes selected.
            2. A combination of the package id defined in the priceplan and a rate plan name.
                This package does have a price as it defines the purchase attributes selected.
	 */
	protected String mId;

	/** The name of the package */
	protected String mName;

	/** The package description */
	protected String mDescription;

	// The following fields are stored in the catalog and are set by the pricing tool.
	// Client applications can use these fields.

	/** The list of names in multiple languages including the default language. 
	 * Key is the language code.  
	 * Since ER9 **/
	protected HashMap<String, String> names = null;

	/** The list of description in multiple languages including the default language.  
	 * Key is the language code. 
	 * Since ER 9**/    
	protected HashMap<String, String> descriptions = null;

	/** The package notification category */
	protected String mNotificationCategory;

	/** The package payment content information */
	protected PaymentContent mPaymentContent;

	/** This can be set in the pricing tool and can be retrieved by the ecom client applications*/
	protected String mUrl;

	/** custom fields defined in catalog using <custom_field> tag */
	protected Map<String, String> mCustomFields = new HashMap<String, String>();

	/** Custom fields for pricing text */
	/** The list of pricing text1 in multiple languages including the default language. 
	 * Key is the language code.  
	 * Since ER9 **/
	protected HashMap<String, String> pricingTextList1 = null;

	//ER 7 compliant
	protected String pricingText1 = null;

	/** The list of pricing text2 in multiple languages including the default language.  
	 * Key is the language code. 
	 * Since ER 9**/    
	protected HashMap<String, String> pricingTextList2 = null;    

	// [1] Mod Start
	protected String mProtectedType = "";
	protected String mDynamicProtectedValue = "";
	// [1] Mod End
	// end of catalog fields

	/** The list of services (CatalogService) in the package */
	protected Map<String, CatalogService> mServices = new HashMap<String, CatalogService>();

	/** The list of all available rate plan selectors */
	protected PricePoints mPricePoints;

	/** The selected price point */
	protected PricePoint mPricePoint;

	/** flag which if true indicates that the package should be bought in a two phase reserve-capture way */
	protected boolean mReserveOnly;

	/** This is used to store the type of package */
	protected String mPackageType;

	/* name of package deal */
	// This is moved from catalogpackageimpl for
	// Partner revenue share phase2

	protected String mPurchaseMethod = "";

	//CR0586 KPI Reporting Fields
	protected String kpiPackageProductCategory = "";
	protected String kpiPackageType = "";
	//CR0586 End

	protected String mTaxCode;

	/** Refund Disallowance attributes AV
	 * @since ER 5.1
	 * */
	protected boolean mRefundable = true;
	protected String mNonRefundableDescription = "";


	/**
	 * DRMType attribute
	 * @since ER 5.1
	 */
	protected DRMType m_DRMType;

	/** ADDED FOR EGYPT ER6 STUB **/
	protected boolean mExpressPurchase=false;

	//Added for ER6 requirement
	protected boolean mReceiptingFlag=false;

	/** ADDED FOR EGYPT ER7 STUB **/
	protected boolean mPricePointOrder=false;

	/** ADDED FOR ER7 STUB **/
	protected boolean mSuperPackage;

	//Added for ER7
	protected boolean mRevenueShareByUsage=false;

	//Added for ER7
	protected boolean mDynamicDefault=false;

	//Added for ER7
	protected int mPriority;

	//Added for ER 8
	protected boolean mACEPackage = false;

	//Added for ER 8 PH 2
	protected boolean mUpSell = false;

	// Added for R9 STKHREQ16
	protected String logoId = "";

	// Added for R9 STKHREQ67
	/**
	 * The multiple language support Partner Information Text
	 */
	protected HashMap<String, String> partnerInfoMap = null;


	///////////////////////////////////////////////////////////////
	//@hud STKHREQ36
	protected boolean mHasMicroService = false;

	/**
	 * Remedy 5538 
	 */
	protected HashMap<String, PricingModel> priceModels = new HashMap<String, PricingModel>();
	protected Boolean isPackageModel = Boolean.FALSE;

	//[2] Mod Start
	/**
	 * Added for ER9 for Ring Back Tones (RBT)
	 */
	protected boolean mParentPackage = false;
	protected String mParentPackageId = "";
	//[2] Mod End

	//RBT Enhancements Start
	protected ArrayList<String> childPackageIdList = null;
	protected boolean childServicesAreInParent = false;
	//RBT Enhancements End

	//ES FUP Enhancement CR Start - a list of comma sepearted service ids which are not in the package fair usage policy when configured
	protected String servicesNotInPackageFairUsagePolicy = null;
	//ES FUP Enhancement CR End
	/**
    MQC 4927 - This is used internally by the ER system
	 */
	protected boolean hasParentSub = false;
	protected String parentSubId;

	/**
    MQC 5485 - This is used internally by the ER system
	 */
	protected boolean hasParentSubSuspendedResProv = false;
	protected int parentSubStatus;

	//CR1113 - Prevent Subscription Cancellation
	protected boolean mDisallowCancellations = false;

	//CR 1212 - add sales model
	protected String mSalesModel = "";

	//CR1193 - Data Tariff Reporting Enhancement
	protected boolean mDataVoiceTariffInclusive = false;

	//CR1193 - Data Tariff Reporting Enhancement
	protected double mNominalValue = 0.0;

	//CR 1209 add package class
	protected String mPackageClass = null;

	//CR 1409 - Prevent Duplicate package purchase 
	protected String disallowDuplicateSubPurchase = "";

	//CR1209AR - VFES defrag
	protected boolean mUseBeingDeprovisionedStatus = false;

	//CR1564 -Utctimezone for diff region in country
	protected boolean hasPricePointsWithDate;
	protected boolean hasPromosWithDate;
	protected boolean hasBalanceImpactsWithDate;
	protected boolean hasTaxRateWithDate;

	//MQC 7733 - define package status's
	public final static String PACKAGE_STATUS_ACTIVE = "ACTIVE";
	public final static String PACKAGE_STATUS_INACTIVE = "INACTIVE";
	public final static String PACKAGE_STATUS_UNKNOWN = "UNKOWN";
	
	/**
	 * CR2006 - Reusable Promo Codes
	 * The promocodes assigned to this package
	 */
	protected HashMap<String, PromoCode> mPromoCodes = null;
	
	// MPAY replacement.  Goodwill credit.
	protected boolean isGoodwillCredit = false;

    // CR2210 - MPay Rate Card
	/**
	 * The 'mUseRateCardService' flag determines whether rate card service or standard service applies to revenue share.
	 */
	protected boolean mUseRateCardService	= false;

	/**
	 * The 'mRateCardServiceId' holds the service id for the rate card service,
	 * and this field is populated when the 'mUseRateCardService' flag is set.
	 */
	protected String mRateCardServiceId		= "";
    // CR2210 - Ends


	/**
	 * CR 2245 upsell discount prorate
	 * The 'mUpsellDiscountProrated' flag determines whether the package supports upsell discount prorated functionality.
	 */
	protected boolean mUpsellDiscountProrated = false;
	/**
	 * CR 2245 upsell discount prorate
	 * The 'mDisallowPrerate' flag determines whether the package supports prerate on purchase or renewals.
	 */
	protected boolean mDisallowPrerate = false;
	
	
    /**
	 * @return Returns the priceModels.
	 */
	public HashMap<String, PricingModel> getPriceModels() {
		return priceModels;
	}

	/**
	 * @param priceModels The priceModels to set.
	 */
	public void setPriceModels(HashMap<String, PricingModel> priceModels) {
		this.priceModels = priceModels;
	}



	/** Outputs to a string the data in the package
	 * WARNING this method is very slow
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer(mId);
		sb.append("\nmKey=").append( mKey).append("\n" );
//		sb.append("mCreatedBy=").append( mCreatedBy).append("\n" );
//		sb.append("mModifiedBy=").append( mModifiedBy).append("\n" );
//		sb.append("mModifiedDate=").append( mModifiedDate).append("\n" );
//		sb.append("mActiveStatus=").append( mActiveStatus).append("\n" );
		sb.append("mRefundable=").append( mRefundable).append("\n" );
		sb.append("mReceiptingFlag=").append( mReceiptingFlag).append("\n" );
		sb.append("mNonRefundableDescription=").append( mNonRefundableDescription).append("\n" );
		//sb.append( "mName=").append(mName ).append("\n" );
		//sb.append( "mDescription=").append(mDescription ).append("\n" );
		sb.append( "mName=").append(getName() ).append("\n" );
		sb.append( "mDescription=").append(getDescription() ).append("\n" );        
		sb.append("mNotificationCategory=").append( mNotificationCategory ).append("\n" );
		if( mPaymentContent != null ) {
			sb.append( "mPaymentContent=").append(mPaymentContent.toString()).append("\n" );
		}
		sb.append( "mServices=").append(Utils.StringifyList(mServices.keySet())).append("\n" );
		if( mPricePoints != null ) {
			sb.append( "mPricePoints=").append(mPricePoints.toString()  ).append("\n" );
		}else sb.append( "mPricePoints= null \n");
		if( mPricePoint != null ) {
			sb.append( "mPricePoint=").append(mPricePoint.toString() ).append("\n" );
		} else sb.append( "mPricePoint= null \n");
		sb.append( "mReserveOnly=").append(mReserveOnly).append("\n"  );
		if(m_DRMType != null)
		{
			sb.append("m_DRMType =").append(m_DRMType.toString()).append("\n");
		}
		sb.append("mExpressPurchase= ").append(mExpressPurchase).append("\n");
		sb.append("mPricePointOrder= ").append(mPricePointOrder).append("\n");
		sb.append("mSuperPackage= ").append(mSuperPackage).append("\n");
		sb.append("mRevenueShareByUsage= ").append(mRevenueShareByUsage).append("\n");
		//[1] Mod Start
		sb.append("mProtectedType= ").append(mProtectedType +"\n");
		sb.append("mDynamicProtectedValue= ").append(mDynamicProtectedValue +"\n");
		//[1] Mod End
		sb.append("mDynamicDefault= ").append(mDynamicDefault).append("\n");
		sb.append("mPriority= ").append(mPriority).append("\n");
		sb.append("mACEPackage= ").append(mACEPackage).append("\n");
		sb.append("mUpSell= ").append(mUpSell).append("\n");
		//CR0586 KPI Reporting Field
		sb.append("kpiPackageProductCategory= ").append(kpiPackageProductCategory).append("\n");
		sb.append("kpiPackageType= ").append(kpiPackageType).append("\n");
		//CR0586 End
		//[2] Mod Start
		sb.append("mParentPackage= ").append(mParentPackage).append("\n");
		sb.append("mParentPackageId= ").append(mParentPackageId).append("\n");
		//[2] Mod End
		//ES FUP Enhancement CR Start
		sb.append("servicesNotInPackageFairUsagePolicy= ").append(servicesNotInPackageFairUsagePolicy).append("\n");
		//ES FUP Enhancement CR End
		//CR1113 - Prevent Subscription Cancellation
		sb.append("mDisallowCancellations= ").append(mDisallowCancellations).append("\n");
		//CR 1212 - add sales model
		sb.append("mSalesModel= ").append(mSalesModel).append("\n");
		//CR1193 - Data Tariff Reporting Enhancement
		sb.append("mDataVoiceTariffInclusive= ").append(mDataVoiceTariffInclusive).append("\n");
		sb.append("mNominalValue= ").append(mNominalValue).append("\n");
		//CR 1209 add package class
		sb.append("mPackageClass= ").append(mPackageClass).append("\n");
		//CR1209AR - VFES defrag changes
		sb.append("mUseBeingDeprovisionedStatus=").append(mUseBeingDeprovisionedStatus).append("\n");
		//CR1409
		sb.append("disallowDuplicateSubPurchase=").append(disallowDuplicateSubPurchase).append("\n");
	    // CR2210 - MPay Rate Card
		sb.append("mUseRateCardService="	).append(mUseRateCardService	).append("\n");
		sb.append("mRateCardServiceId="		).append(mRateCardServiceId	).append("\n");
		//CR 2245 upsell discount prorate
		sb.append("mUpsellDiscountProrated=").append(mUpsellDiscountProrated).append("\n");
		sb.append("mDisallowPrerate=").append(mDisallowPrerate).append("\n");
		return sb.toString();
	}

	public CatalogPackage(String id, String name)
	{
		mId = id;
		setName(name);
		//ER7 Compliant
		mName = name;
		//ER7 Compliant
	}

	public CatalogPackage(Long key, String id, String name, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
	{
		mKey = key;
		mCreatedBy = createdBy;
		mModifiedBy = modifiedBy;
		mModifiedDate = modifiedDate;
		mActiveStatus = activeStatus;

		mId = id;
		setName(name);

		//ER7 Compliant
		mName = name;
		//ER7 Compliant
	}
	public CatalogPackage(String id, String name, List<CatalogService> services, String desc,
			PricePoints pts, String notificationCategory, PaymentContent paymentContent, boolean reserveOnly)
	{
		mId = id;

		setName(name);
		setDescription(desc);

		//ER7 Compliant
		mName = name;
		mDescription = desc;
		//ER7 Compliant

		mPricePoints = pts;
		mNotificationCategory = notificationCategory;
		mPaymentContent = paymentContent;
		mReserveOnly = reserveOnly;
		mServices = new HashMap<String, CatalogService>();

		for ( int i = 0; i<services.size(); i++ )  {
			CatalogService o = services.get(i);
			mServices.put(o.getId(), o);
		}
	}
	public CatalogPackage(Long key, String id, String name, List<CatalogService> services, String desc,
			PricePoints pts, String notificationCategory, PaymentContent paymentContent, boolean reserveOnly,
			String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
	{
		mKey = key;
		mCreatedBy = createdBy;
		mModifiedBy = modifiedBy;
		mModifiedDate = modifiedDate;
		mActiveStatus = activeStatus;

		mId = id;

		setName(name);
		setDescription(desc);

		//ER7 Compliant
		mName = name;
		mDescription = desc;
		//ER7 Compliant

		mPricePoints = pts;
		mNotificationCategory = notificationCategory;
		mPaymentContent = paymentContent;
		mReserveOnly = reserveOnly;
		mServices = new HashMap<String, CatalogService>();

		for ( int i = 0; i<services.size(); i++ )  {
			CatalogService o = services.get(i);
			mServices.put(o.getId(), o);
		}
	}

	/**
        Default constructor
	 */
	public CatalogPackage(String type)
	{
		this.mPackageType = type;
	}


	/**
        Default constructor
	 */
	public CatalogPackage()
	{
	}

	/**
        Allows copies of the package to be made
	 */
	public CatalogPackage(CatalogPackage pack)
	{
		this.mKey = pack.mKey;
		this.mCreatedBy = pack.mCreatedBy ;
		this.mModifiedBy = pack.mModifiedBy;
		this.mModifiedDate = pack.mModifiedDate;
		this.mActiveStatus = pack.mActiveStatus;
		this.mPricingModelFk = pack.mPricingModelFk;
		//		this.mProductFk = pack.mProductFk;

		this.mId = pack.mId;

		setName(pack.getName());
		setDescription(pack.getDescription());

		//ER7 Compliant
		mName = pack.mName;
		mDescription = pack.mDescription;
		//ER7 Compliant

		this.setNames(pack.getNames());
		this.setDescriptions(pack.getDescriptions());

		this.mUrl = pack.mUrl;
		this.mNotificationCategory = pack.mNotificationCategory;
		this.mReserveOnly = pack.mReserveOnly;
		this.mPackageType = pack.mPackageType;
		this.mServices = new HashMap<String, CatalogService>(pack.mServices);
		this.mCustomFields = new HashMap<String, String>(pack.mCustomFields);
		//ER7 Compliant
		this.mPricingText1 = pack.mPricingText1;
		this.mPricingText2 = pack.mPricingText2;
		//End ER7 Compliant
		this.setPricingText1(pack.getPricingText1());
		this.setPricingText2(pack.getPricingText2());
		this.setPricingTextList1(pack.getPricingTextList1());
		this.setPricingTextList2(pack.getPricingTextList2());
		// [1] Mod Start
		this.mProtectedType = pack.getProtectedType();
		this.mDynamicProtectedValue = pack.getDynamicProtectedValue();
		// [1] Mod End
		this.mTaxCode = pack.getTaxCode();
		this.mRefundable = pack.mRefundable;
		this.mNonRefundableDescription = pack.mNonRefundableDescription;
		this.m_DRMType =  pack.m_DRMType;
		this.mExpressPurchase = pack.mExpressPurchase;
		this.mReceiptingFlag = pack.mReceiptingFlag;
		this.mPricePointOrder = pack.mPricePointOrder;
		this.mSuperPackage = pack.mSuperPackage;
		this.mRevenueShareByUsage = pack.mRevenueShareByUsage;
		this.mDynamicDefault = pack.mDynamicDefault;
		this.mPriority = pack.mPriority;
		logger.debug("setting pricepoint to {}", pack.mPricePoint!=null?pack.mPricePoint.getId():pack.mPricePoint);
		this.mPricePoint = pack.mPricePoint;
		this.mPackageType = pack.mPackageType;
		this.mPricePoints = pack.mPricePoints;
		// VEF -  ER8 PII - ACE remaning credits [start]
		this.mACEPackage = pack.mACEPackage;
		this.mUpSell = pack.mUpSell;
		// VEF -  ER8 PII - ACE remaning credits [end]

		this.logoId = pack.logoId;
		this.partnerInfoMap = pack.getPartnerInfoMap();


		///////////////////////////////////////////////////////
		//@hud STKHREQ36
		mHasMicroService = pack.hasMicroService();

		/*
		 * Remedy 5538
		 */
		this.isPackageModel = pack.isPackageModel;
		this.priceModels = pack.priceModels;

		//[2] Mod Start
		//Added for Ring Back Tones (RBT)
		this.mParentPackage = pack.mParentPackage;
		this.mParentPackageId = pack.mParentPackageId;
		//[2] Mod End
		//ES FUP Enhancement CR Start
		this.servicesNotInPackageFairUsagePolicy = pack.servicesNotInPackageFairUsagePolicy;
		//ES FUP Enhancement CR Start

		//MQC 4326
		this.childPackageIdList = pack.childPackageIdList;
		//CR1113 - Prevent Subscription Cancellation
		this.mDisallowCancellations = pack.mDisallowCancellations;
		//CR 1212 - add sales model
		this.mSalesModel = pack.mSalesModel;
		//CR1193 - Data Tariff Reporting Enhancement
		this.mDataVoiceTariffInclusive = pack.mDataVoiceTariffInclusive;
		this.mNominalValue = pack.mNominalValue;
		//CR 1209 add package class
		this.mPackageClass = pack.mPackageClass;
		//CR1209AR - VFES defrag changes
	    this.mUseBeingDeprovisionedStatus = pack.mUseBeingDeprovisionedStatus;
	    //CR1409
	    this.disallowDuplicateSubPurchase = pack.disallowDuplicateSubPurchase;
	    
	    //CR2006 - add promo codes
	    if (pack.mPromoCodes != null && pack.mPromoCodes.size() > 0)
	    	this.mPromoCodes = new HashMap<String, PromoCode>(pack.mPromoCodes);
	    
	    // CR2040 MPAY replacement.  Goodwill Credit.
	    this.isGoodwillCredit = pack.isGoodwillCredit;

		// CR2210 - MPay Rate Card
		this.mUseRateCardService	= pack.mUseRateCardService;
		this.mRateCardServiceId		= pack.mRateCardServiceId;
		
		//CR 2245 upsell discount prorate
		this.mUpsellDiscountProrated = pack.mUpsellDiscountProrated;
		this.mDisallowPrerate = pack.mDisallowPrerate;
    }

	/**
	 * Returns either the simple package id (eg p003), or the rateIdentifier (eg p003__X__package:p003_blabla), depending what mood I'm in
	 * 
	 * After MQC 8385 this will never be called within ER Core or Batch
	 * Since this is a common module this could be called from other applications so 
	 * this is deprecated rather than removed completely.
	 * Instead use getFullPackagePricepointId() to get the fullId: p003__X__package:p003_blabla
	 * or getsimplePackageId() to get: p003
	 * 
	 * @see #getSimplePackageId() 
	 * @deprecated
	 */
	@Deprecated
	public String getId()
	{
		String id = mId; // also obtained by getSimplePackageId()
		//MD - what the hell is this?   the getId() method should be constant over time
		if (getPricePoint()!=null && getPricePoint().getId()!=null) {
			id = id + PACKAGE_DELIMITER + getPricePoint().getId();
		}

		return id;
	}
	
	//MQC 8385 - Get PackageId refactor

	/**
	 * Returns the full identifier for a package pricepoint: 
 (e.g. p003__X__package:p003_TAX_2_1_3_blabla) 
	 * @return
	 * @deprecated if you want the simple package Id, use getSimplePackageId() - if you want the pricepoint, do getPricepoint()
	 */
	@Deprecated
	public String getFullPackagePricepointId() {
	
		String fullId = null;
		
		if (mId != null && getPricePoint()!=null && getPricePoint().getId()!=null) {
			fullId = mId + PACKAGE_DELIMITER + getPricePoint().getId();
		}
		
		logger.debug("getFullPackagePricepointId returning: " + fullId);

		return fullId;
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

	/**
	 * this is probably not used any more
	 * @return
	 */
	public char getActiveStatus() {
		//MQC 7733 - package is active if at least one of its pricepoints is active
		if (mActiveStatus == '\u0000' && this.mPricePoints != null && this.mPricePoints.size() > 0) {
			mActiveStatus = 'I';
			for (PricePoint ppt : this.mPricePoints) {
				if (ppt.isActive(new Date())) {
					mActiveStatus = 'A';
					break;
				}
            }
		}
		
		return mActiveStatus;
	}

	
	/**
	 * Get the package id without the pricepoint id concatenated. eg p003
	 * @see #getId()
	 */
	public String getSimplePackageId()
	{
		return mId;
	}

	/**
	 * The name of the package for default language
	 */
	public String getName()
	{
		return getName(Constants.DEFAULT_LANGUAGE_CODE);
	}

	/**
	 * The description for default language
	 */
	public String getDescription()
	{
		return getDescription(Constants.DEFAULT_LANGUAGE_CODE);
	}

	/**
        Return the notification category for this package.
	 */
	public String getNotificationCategory()
	{
		return mNotificationCategory;
	}

	/**
	 * @return the payment content for this package.
	 */
	public PaymentContent getPaymentContent()
	{
		return mPaymentContent;
	}

	/**
	 * @return CatalogService[] an array of the services in the package
	 */
	public CatalogService[] getServices()
	{
		if (mServices!=null && mServices.values()!=null) {
			return mServices.values().toArray(new CatalogService[] {});
		} else {
			return null;
		}
	}

	//@hud
	public Map<String, CatalogService> getServiceMap() {
		return mServices;
	}

	/**
	 * Sets a new set of services
	 */
	// added to reset the services. Required as a product feature.
	// Bruno Meseguer,2005-DEC-07,LIT-PATCH-er_batch_jboss_7.0.1_rc002_LIT20050930_2005-12-07
	public void setServices(HashMap<String, CatalogService> newServices)
	{
		mServices = newServices;
	}

	/**
	 * @return true if the package has the service
	 */
	public boolean hasService(CatalogService service)
	{

		boolean rv = false;

		if (service!=null) {
			if (mServices.get(service.getId())!=null) {
				rv = true;
			}
		}


		return rv;
	}


	/**
	 * Returns the service specified. Returns null if the package does not have the service
	 * @return the catalog service
	 */
	public CatalogService getService(String id)
	{
		return mServices.get(id);
	}



	/**
        @return true if the package is an event package
	 */
	public boolean isEventPackage()
	{
		if (mPackageType!=null)
			return mPackageType.equals(EVENT_PACKAGE_TYPE);

		boolean rv = false;
		if (getPricePoints()!=null)	{
//			PricePoint[] pts = getPricePoints().getAll();
//			if (pts!=null && pts.length>0) {
//				PricePoint pt = pts[0];
//				//MQc8385 TODO - iterate through all ppts, don't just choose first one
//				if (pt != null) {
//					rv = ChargingMethod.isEvent(pt.getChargingMethod());
//				}
//			}
			if (!getPricePoints().isEmpty())
				rv = ChargingMethod.isEvent(getPricePoints().get(0).getChargingMethod());
		}
		return rv;
	}

	/**
        Check the current selected price point to see if the ChargingMethod is recurring
	 */
	public boolean isRecurringPackage()
	{
		boolean rv = false;
		PricePoint pt = getPricePoint();
		if (pt!=null) {
			rv = ChargingMethod.isRecurring(pt.getChargingMethod());
		}
		return rv;
		//MQC 8385 TODO: replace with following:
//		for (PricePoint pt: getPricePoints().getAll()){
//			if (pt.isRecurring())
//				return true;
//		}
//		return false;
	}

	/**
        The package type can be "Event" or "Calendar"
        @return the package type
	 */
	public String getPackageType() {
		return mPackageType;
	}

	/**
	 * Used to retrieve the description of why the package is non-refundable
	 * @return Non-Refundable-Description
	 * @since ER 5.1
	 */
	public String getNonRefundableDescription(){
		return this.mNonRefundableDescription;
	}


	/**
	 * Used to retrieve to assess if the package is refundable
	 * @return boolean
	 * @since ER 5.1
	 */
	public boolean isRefundable(){
		CatalogService[] catalogServices = getServices();
		if(catalogServices != null){
			int count = 0;
			for(int i=0; i<catalogServices.length; i++){
				if(!catalogServices[i].isRefundable()){
					count++;
				}
			}
			if(count > 0){
				this.mRefundable = false;
			}
		}
		return this.mRefundable;
	}

	/**
        @return true if the package is a calendar package (subscription package)
	 */
	public boolean isCalendarPackage()
	{
		boolean rv = false;

		if (mPackageType!=null)	{
			return mPackageType.equals(CALENDAR_PACKAGE_TYPE);
		} else {
			if (getPricePoints()!=null)	{
//				PricePoint[] pts = getPricePoints().getAll();
//	
//				if (pts!=null && pts.length>0) {
//					PricePoint pt = pts[0];
//					//MQc8385 TODO - iterate through all ppts, don't just choose first one
//					if (pt != null) {
//						rv = ChargingMethod.isCalendar(pt.getChargingMethod());
//					}
//				}
				if (!getPricePoints().isEmpty())
					rv = ChargingMethod.isCalendar(getPricePoints().get(0).getChargingMethod());
			}
		}
		if (rv==true && mPackageType==null) {
			mPackageType = CALENDAR_PACKAGE_TYPE;
		}
		return rv;
	}

	/**
        Return all of the price points in the package
	 */
	public PricePoints getPricePoints()
	{
		return mPricePoints;
	}

	/**
        Returns the current selected price point
        @deprecated as per MQC8385
	 */
	@Deprecated
	public PricePoint getPricePoint()
	{
		return mPricePoint;
	}

	/**
        Set the selected price point. This sets the price of the package
        This should only be used from a copied package.
        @see CatalogApi.getPackage()
        @deprecated as per MQC8385

	 */
	@Deprecated
	public void setPricePoint(PricePoint pt)
	{
		logger.debug("setting pricepoint to {}", pt!=null?pt.getId():null);
		mPricePoint = pt;
	}

	/**
        Set the selected price point. This sets the price of the package
        This should only be used from a copied package.
        @see CatalogApi.getPackage()
        @deprecated as per MQC8385
	 */
	@Deprecated
	public void setPricePoint(String pricePointId)
	{
		if (mPricePoints!=null) {
			logger.debug("setting pricepoint to {}", pricePointId);
			mPricePoint = mPricePoints.getPricePoint(pricePointId);
		}

	}

	/**
	 * MQC8385 TODO A package doesn't have a rate.  A pricepoint has a rate.
	 * @param date
	 * @return
	 */
	@Deprecated
	public float getRate() {
		return getRate(new Date());
	}

	/**
	 * A package doesn't have a rate.  A pricepoint has a rate.
	 * @param date
	 * @return
	 */
	@Deprecated
	public float getRate(Date date)
	{
		//MQC8385 TODO
		if (getPricePoint()!=null) {
			return (float)getPricePoint().getRate(date);
		}
		return -1;
	}

	//CR1564 -Utctimezone for diff region in country
	/** 
	 * @deprecated
	 */
	@Deprecated
	public String getRateAsString(Locale loc) {
		return getRateAsString(loc, new Date());
	}

	//CR1564 -Utctimezone for diff region in country
	/**
        This converts the rate in a price with 2 decimal places
        It uses the locale to work out whether to use
        a period (.) or comma (,) as the decimal point.

        0 is returned as 0.00 or 0,00
        1.5 is returned as 1.50 or 1,50
	 */
	public String getRateAsString(Locale loc, Date date)
	{
		//MQC8385 TODO
		String rv = null;
		if (getPricePoint()!=null) {
			rv = getPricePoint().getRateAsString(loc, date);
		}
		return rv;
	}


//	/** Modified for pre-Rate and date-Time in Purchase.
//	 * 
//	 */
//	@SuppressWarnings("deprecation")
//	public boolean isComplex()
//	{
//		//MQC8385 TODO
//		boolean rv = false;
//
//		if (getPricePoint()!=null) {
//			return getPricePoint().isComplex();
//		}
//
//		return rv;
//	}
	/**
	 * The type of currency
	 */
	public ChargingResource getResource()
	{
		//MQC8385 TODO
		if (getPricePoint()!=null) {
			return getPricePoint().getResource();
		}
		return null;
	}

	/**
	 * Tells whether this package should be bought with reserve-capture two phase semantics.
	 */
	public boolean isReserveOnly() {
		return this.mReserveOnly;
	}

	/** A catalog field */
	public String getUrl()
	{
		return mUrl;
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
	// [1] Mod Start
	public String getProtectedType() {
		return mProtectedType;
	}

	public void setProtectedType(String mProtectedType) {
		this.mProtectedType = mProtectedType;
	}

	public String getDynamicProtectedValue() {
		return mDynamicProtectedValue;
	}

	public void setDynamicProtectedValue(String mDynamicProtectedValue) {
		this.mDynamicProtectedValue = mDynamicProtectedValue;
	}
	// [1] Mod End
	/**
	 * Gets a custom field for the package.
	 * Custom fields appear in <custom_field> tags in the catalog XML.
	 */
	public String getCustomField(String name) {
		return mCustomFields.get(name);
	}

	/**
        Returns all of the custom fields
	 */
	public Map<String, String> getCustomFields() {
		return mCustomFields;
	}

	/**
	 *   Used to store the package under different types of deals
	 *   Not used by client applications
	 *   
	 */
	public String getDealName()
	{
		return "";
	}

	/**
	 * Used to return the purchase method
	 * @return purchase method
	 */
	// this has been moved from CatalogPackageImpl for
	// Partner revenue share phase 2
	public String getPurchaseMethod() { return mPurchaseMethod; }

	//CR0586 KPI Reporting Fields
	/**
	 * Used to return the package content category
	 * @return package content category
	 */
	public String getKpiPackageProductCategory() { return kpiPackageProductCategory; }

	/**
	 * Used to set the package content category
	 */
	public void setKpiPackageProductCategory(String packageProductCategory) 
	{ 
		this.kpiPackageProductCategory=packageProductCategory; 
	}

	/**
	 * Used to return the KPI package type
	 * @return KPI package type
	 */
	public String getKpiPackageType() { return kpiPackageType; }
	/**
	 * Used to return the KPI package type
	 * @return KPI package type
	 */
	public void setKpiPackageType(String packageType) 
	{ 
		this.kpiPackageType = packageType; 
	}

	//CR0586 End
	/**
	 * @deprecated
	 */
	@Deprecated
	public boolean isActive() {
		return isActive(new Date());
	}

	//CR1564 -Utctimezone for diff region in country
	//CR0586 End
	/**
        Returns active if the package has an active price point
	 */
	public boolean isActive(Date date)
	{
//		boolean rv = false;
//		PricePoints pts = getPricePoints();
//
//		if (pts!=null) {
//			PricePoint[] ptsArr = pts.getAll();
//
//			for (int index=0; ptsArr!=null && index<ptsArr.length; index++) {
//				if (ptsArr[index].isActive(date)) {
//					rv = true;
//					break;
//				}
//			}
//		}
//		return rv;
		for (PricePoint pt: getPricePoints())	{
			if (pt.isActive(date))
				return true;
		}
		return false;
	}

	public String getTaxCode()
	{
		return mTaxCode;
	}


	public String[] getPricingModels()
	{
		//REMEDY 7010
		//This method should not be used. The method to be used is in the CatalogPackageImpl
		//throw new RuntimeException("This is implemented in the implementation class");
		String[] tempString = {""};
		return tempString;
	}

	/**
	 * get the DRM flag associated with the package
	 * @param none
	 * @return DRMType the flag associated with the package
	 * @since ER 5.1
	 */
	public DRMType getDRMType()
	{
		return m_DRMType;
	}

	/**
	 * is this the default package?
	 * @return
	 */
	public boolean isDefault()
	{
		boolean rv = false;
		//MQC 7883 = the getId() method is not consistent over time.  If a user purchases the default package, it will return default__X__package:default_TAX_1_1_10010_999_999_*_*
		//if (getId().equals(DEFAULT_ID)) {
		if (getSimplePackageId().equals(DEFAULT_ID))	{
			rv = true;
		}

		return rv;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public boolean isExpressPurchase() {
		return mExpressPurchase;
	}

	/** ADDED FOR ER6 Requirement **/
	public boolean isRecieptingFlag() {
		return mReceiptingFlag;
	}
	public String getPricingModelFk()
	{
		return mPricingModelFk;
	}
	/** ADDED FOR ER7 Requirement **/
	public boolean isPricePointOrder() {
		return mPricePointOrder;
	}

	/** ADDED FOR ER7 Requirement **/
	public boolean isSuperPackage() {
		return mSuperPackage;
	}

	/** ADDED FOR ER7 Requirement **/
	public boolean isRevenueShareByUsage() {
		return mRevenueShareByUsage;
	}

	/** ADDED FOR ER7 Requirement **/
	public boolean isDynamicDefault() {
		return mDynamicDefault;
	}

	/** ADDED FOR ER7 Requirement **/
	public int getPriority()
	{
		return mPriority;
	}

	/** Added FOR ER 8 Requirement */
	public boolean getACEPackage()
	{
		return mACEPackage;
	}

	/** Added FOR ER 8 Ph 2 Requirement */
	public boolean isUpSell()
	{
		return mUpSell;
	}

	// Added since ER 9 - Start
	public HashMap<String, String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(HashMap<String, String> descriptions) {
		this.descriptions = descriptions;
	}

	public HashMap<String, String> getNames() {
		return names;
	}

	public void setNames(HashMap<String, String> names) {
		this.names = names;
	}

	public String getName(String languageCode){
		if (names == null || names.isEmpty()){
			return null;
		}

		if (languageCode == null || languageCode.length() <= 0){
			languageCode = Constants.DEFAULT_LANGUAGE_CODE;
		}

		if (names.get(languageCode) != null){
			return names.get(languageCode);
		}

		return names.get(Constants.DEFAULT_LANGUAGE_CODE);
	}

	public void setName(String language, String name){
		if (names == null){
			names = new HashMap<String, String>();
		}

		if (language == null || language.length() <= 0){
			language = Constants.DEFAULT_LANGUAGE_CODE;
		}

		if (name == null){
			name = new String();
		}

		names.put(language, name);
	}

	public void setName(String name) {
		setName(Constants.DEFAULT_LANGUAGE_CODE, name);	
	}


	public void setDescription(String language, String description) {    	
		if (descriptions == null){
			descriptions = new HashMap<String, String>();
		}  

		if (language == null || language.length() <= 0){
			language = Constants.DEFAULT_LANGUAGE_CODE;
		}

		if (description == null){
			description = new String();
		}

		descriptions.put(language, description);           
	}	

	public String getDescription(String languageCode){
		if (descriptions == null || descriptions.isEmpty()){
			return null;
		}

		if (languageCode == null || languageCode.length() <= 0){
			languageCode = Constants.DEFAULT_LANGUAGE_CODE;
		}

		if (descriptions.get(languageCode) != null){
			return descriptions.get(languageCode);
		}

		return descriptions.get(Constants.DEFAULT_LANGUAGE_CODE);
	}

	public void setDescription(String description) {
		setDescription(Constants.DEFAULT_LANGUAGE_CODE, description);
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

		if (pricingTextList1.get(languageCode) != null){		
			return this.pricingTextList1.get(languageCode);
		}

		return this.pricingTextList1.get(Constants.DEFAULT_LANGUAGE_CODE);
	}

	public String getPricingText2(String languageCode){
		if (this.pricingTextList2 == null || this.pricingTextList2.isEmpty()){
			return null;
		}

		if (languageCode == null || languageCode.length() <= 0){
			languageCode = Constants.DEFAULT_LANGUAGE_CODE;
		}

		if (pricingTextList2.get(languageCode) != null){		
			return this.pricingTextList2.get(languageCode);
		}

		return this.pricingTextList2.get(Constants.DEFAULT_LANGUAGE_CODE);
	}	

	public void setPricingText1(String text)
	{
		mPricingText1 = text;
		setPricingText1(Constants.DEFAULT_LANGUAGE_CODE, text);
	}

	public void setPricingText2(String text)
	{
		mPricingText2 = text;
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
	}    

	// Add since ER9 - END



	///////////////////////////////////////////////////////
	//@hud STKHREQ36 ER9
	public boolean hasMicroService() {
		return mHasMicroService;
	}
	public void setHasMicroService(boolean b) {
		mHasMicroService = b;
	}

	public String getLogoId() {
		return logoId;
	}

	public void setLogoId(String logoId) {
		this.logoId = logoId;
	}

	public HashMap<String, String> getPartnerInfoMap() {
		return partnerInfoMap;
	}

	public void setPartnerInfoMap(HashMap<String, String> partnerInfoMap) {
		this.partnerInfoMap = partnerInfoMap;
	}

	/**
	 * Get the Partner Infor for the default language
	 * @return
	 */
	public String getPartnerInfo(){
		return getPartnerInfo(Constants.DEFAULT_LANGUAGE_CODE);
	}

	public String getPartnerInfo(String languageCode){
		if (this.partnerInfoMap == null || this.partnerInfoMap.isEmpty()){
			return null;
		}

		if (languageCode == null || languageCode.length() <= 0){
			languageCode = Constants.DEFAULT_LANGUAGE_CODE;
		}

		if (partnerInfoMap.get(languageCode) != null){		
			return this.partnerInfoMap.get(languageCode);
		}

		return this.partnerInfoMap.get(Constants.DEFAULT_LANGUAGE_CODE);
	}

	public void setPartnerInfo(String partnerInfo){
		setPartnerInfo(Constants.DEFAULT_LANGUAGE_CODE, partnerInfo);
	}

	public void setPartnerInfo(String language, String partnerInfo){
		if (partnerInfo == null){
			partnerInfo = new String();    		
		}

		if (this.partnerInfoMap == null){
			this.partnerInfoMap = new HashMap<String, String>();
		}

		if (language == null || language.length() <= 0){
			language = Constants.DEFAULT_LANGUAGE_CODE;
		}		

		this.partnerInfoMap.put(language, partnerInfo);    			
	}

	/*
	 * Remedy 5538
	 */
	 /**
	  * @return Returns the isPackageModel.
	  */
	public Boolean getIsPackageModel() {
		return isPackageModel;
	}

	/**
	 * @param isPackageModel The isPackageModel to set.
	 */
	public void setIsPackageModel(Boolean isPackageModel) {
		this.isPackageModel = isPackageModel;
	}

	//[2] Mod Start
	/**
	 * @return Returns boolean of whether this is a parent package.
	 */
	public boolean isParentPackage()
	{
		return mParentPackage;
	}

	/**
	 * @param parent package flag
	 */
	public void setParentPackage(boolean isParentPackage)
	{
		this.mParentPackage = isParentPackage;
	}

	/**
	 * @return Returns String of the parent package ID.
	 */
	public String getParentPackageId()
	{
		return mParentPackageId;
	}

	/**
	 * @param parent package ID
	 */
	public void setParentPackageId(String parentPackageId)
	{
		this.mParentPackageId = parentPackageId;
	}
	//[2] Mod End

	//RBT Enhancements Start

	public void setChildPackages(ArrayList<String> childPackageIdList){
		this.childPackageIdList = childPackageIdList;
	}
	public ArrayList<String> getChildPackages(){
		return childPackageIdList;
	}

	/**
	 * True if all the services contained in an RBT Child Pack
	 * Are in it's Parent Package
	 * 
	 * @param inParent
	 */
	public void setchildServicesAreInParentFlag(boolean inParent){
		this.childServicesAreInParent= inParent;
	}
	public boolean hasServicesInParent(){
		return childServicesAreInParent;
	}	

	//RBT Enhancements End

	//ES FUP Enhancement CR Start
	public void setServicesNotInPackageFairUsagePolicy(String servicesNotInPackageFairUsagePolicyList){
		this.servicesNotInPackageFairUsagePolicy = servicesNotInPackageFairUsagePolicyList;
	}
	public String getServicesNotInPackageFairUsagePolicyList(){
		return servicesNotInPackageFairUsagePolicy;
	}
	//ES FUP Enhancement CR End

	/**
	 * MQC 4927 - This is used internally by the ER system
	 * True if any parent subscription exists for this package
	 */
	public boolean isHasParentSub() {
		return hasParentSub;
	}

	/**
    MQC 4927 - This is used internally by the ER system
	 */
	public void setHasParentSub (boolean hasSub) {
		this.hasParentSub = hasSub;
	}

	/**
	 * MQC 4927 - This is used internally by the ER system
	 * The sub id of the parent subscription if exists for this package
	 */
	public void setParentSubId(String parentSubcriptionId){
		this.parentSubId = parentSubcriptionId;
	}

	/**
    MQC 4927 - This is used internally by the ER system
	 */
	public String getParentSubId(){
		return this.parentSubId;
	}

	/**
	 * MQC 5485 - This is used internally by the ER system
	 * True if any parent subscription exists for this package and is in a reserved, suspended or being provisioned status
	 */
	public boolean isHasParentSubSuspendedResProv() {
		return hasParentSubSuspendedResProv;
	}

	/**
    MQC 5485 - This is used internally by the ER system
	 */
	public void setHasParentSubSuspendedResProv(boolean hasSubSuspendedResProv) {
		this.hasParentSubSuspendedResProv = hasSubSuspendedResProv;
	}

	/**
    MQC 5485 - This is used internally by the ER system
	 */
	public int getParentSubStatus() {
		return parentSubStatus;
	}

	/**
    MQC 5485 - This is used internally by the ER system
	 */
	public void setParentSubStatus(int status) {
		this.parentSubStatus = status;
	}


	/**
	 * CR1113 - Prevent Subscription Cancellation
	 * @return Returns boolean of whether to disallow cancellations for package.
	 */
	public boolean isDisallowCancellations()
	{
		return mDisallowCancellations;
	}

	/**
	 * @param disallow cancellation flag
	 */
	public void setDisallowCancellations(boolean disallowCancellations)
	{
		this.mDisallowCancellations = disallowCancellations;
	}

	/**
	 * CR1212 - add sales model
	 * @return Returns String of sales model.
	 */
	public String getSalesModel()
	{
		if (mSalesModel == null || mSalesModel.equals("")) {
			return Constants.RESELLER_SALES_MODEL;
		}
		else {
			return mSalesModel;
		}
	}

	/**
	 * CR1212 - add sales model
	 * @param sales model
	 */
	public void setSalesModel(String salesModel) {
		if (salesModel == null || salesModel.equals("")) {
			mSalesModel = Constants.RESELLER_SALES_MODEL;
		}
		else {
			mSalesModel = salesModel;
		}
	}

	/**
	 * CR1209 add package class
	 * @return Returns String of package class.
	 */
	public String getPackageClass()
	{
		return mPackageClass;
	}

	/**
	 * CR1209 add package class
	 * @param sales model
	 */
	public void setPackageClass(String packageClass) {

		mPackageClass = packageClass;
	}

	/**
	 * CR1193 - Data Tariff Reporting Enhancement
	 * @return Returns boolean of whether package is data / voice tariff inclusive.
	 */
	public boolean isDataVoiceTariffInclusive()
	{
		return mDataVoiceTariffInclusive;
	}

	/**
	 * CR1193 - Data Tariff Reporting Enhancement
	 * @param data / voice tariff inclusive flag
	 */
	public void setDataVoiceTariffInclusive(boolean dataVoiceTariffInclusive)
	{
		this.mDataVoiceTariffInclusive = dataVoiceTariffInclusive;
	}

	/**
	 * CR1193 - Data Tariff Reporting Enhancement
	 * @return Returns double representing the nominal value.
	 */
	public double getNominalValue()
	{
		return mNominalValue;
	}

	/**
	 * CR1193 - Data Tariff Reporting Enhancement
	 * @param nominal value
	 */
	public void setNominalValue(double nominalValue)
	{
		this.mNominalValue = nominalValue;
	}

	/**
	 * CR1209AR - VFES defrag
	 * @return the useBeingDeprovisionedStatus
	 */
	public boolean isUseBeingDeprovisionedStatus() {
		return mUseBeingDeprovisionedStatus;
	}

	/**
	 * CR1209AR - VFES defrag
	 * @param useBeingDeprovisionedStatus the useBeingDeprovisionedStatus to set
	 */
	public void setUseBeingDeprovisionedStatus(boolean useBeingDeprovisionedStatus) {
		this.mUseBeingDeprovisionedStatus = useBeingDeprovisionedStatus;
	}

	// CR 1409 Start
	public String getDisallowDuplicateSubPurchase() {
		return disallowDuplicateSubPurchase;
	}

	public void setDisallowDuplicateSubPurchase(String disallowDuplicateSubPurchase) {
		this.disallowDuplicateSubPurchase = disallowDuplicateSubPurchase;
	}
	// CR 1409 End

	//CR 1542 - return the number of active pricepoints
	/**
	 * @deprecated
	 */
	@Deprecated
	public int getNoActivePricepoints() {
		return getNoActivePricepoints(new Date());
	}

	//CR1564 -Utctimezone for diff region in country
	//CR 1542 - return the number of active pricepoints
	public int getNoActivePricepoints(Date date) {

		int rv = 0;

		if ( mPricePoints != null) {
			//PricePoint[] pts = mPricePoints.getAll();
			for (PricePoint pt : mPricePoints) {
				//MQC 6051 - also regard historic pricepoints as active as existing subscriptions may still have active subscriptions against these
				if (pt.isActive(date) || pt.isHistoric()) {
					rv++;
				}
			}
		}

		return rv;
	}

	//CR1564 -start
	public boolean isHasBalanceImpactsWithDate() {
		return hasBalanceImpactsWithDate;
	}

	public void setHasBalanceImpactsWithDate(boolean hasBalanceImpactsWithDate) {
		this.hasBalanceImpactsWithDate = hasBalanceImpactsWithDate;
	}

	public boolean isHasPricePointsWithDate() {
		return hasPricePointsWithDate;
	}

	public void setHasPricePointsWithDate(boolean hasPricePointsWithDate) {
		this.hasPricePointsWithDate = hasPricePointsWithDate;
	}

	public boolean isHasPromosWithDate() {
		return hasPromosWithDate;
	}

	public void setHasPromosWithDate(boolean hasPromosWithDate) {
		this.hasPromosWithDate = hasPromosWithDate;
	}

	public boolean isHasTaxRateWithDate() {
		return hasTaxRateWithDate;
	}

	public void setHasTaxRateWithDate(boolean hasTaxRateWithDate) {
		this.hasTaxRateWithDate = hasTaxRateWithDate;
	}
	//CR1564 -end

	//CR1564 -Utctimezone for diff region in country - set to true if you want to disable dyanamic calculation of values in mPricePoint and mPricePoints
	public void setUseStaticValues(boolean useStaticValues) {
		if (mPricePoint != null) {
			mPricePoint.setUseStaticValues(useStaticValues);
		}
		if (mPricePoints != null) {
			for (PricePoint pp : mPricePoints) {
				pp.setUseStaticValues(useStaticValues);
			}
		}
	}

	//MQC 6289 - return true if package contains active recurring pricepoint
	public boolean isRecurringPricePointPackage(Date date) {

		boolean rv = false;

		if (!this.isCalendarPackage()) {
			return false;
		}

//		if (mPricePoints == null || mPricePoints.getAll() != null 
//				|| mPricePoints.getAll().length == 0) {
//			PricePoint[] pts = mPricePoints.getAll();
		if (mPricePoints!=null && !mPricePoints.isEmpty())	{
			for (PricePoint pt : mPricePoints) {	
				if (pt.isActive(date) && pt.getChargingMethod() == ChargingMethod.RECURRING) {
					rv = true;
					break;
				}
			}
		}

		return rv;
	}

	//CR1759 - start
	public void setIsSuperPackage(boolean isSuperPackage)
	{
		this.mSuperPackage = isSuperPackage;
	}
	//CR1759 - end

	//CR1503aL. START required for merge versions functionality
	public void setPricePoints(PricePoints ps){
		mPricePoints = ps;
	}

	/**
	 * @param mRefundable the mRefundable to set
	 */
	public void setRefundable(boolean mRefundable) {
		this.mRefundable = mRefundable;
	}

	/**
	 * @param mNonRefundableDescription the mNonRefundableDescription to set
	 */
	public void setNonRefundableDescription(String mNonRefundableDescription) {
		this.mNonRefundableDescription = mNonRefundableDescription;
	}

	/**
	 * @param mPaymentContent the mPaymentContent to set
	 */
	public void setPaymentContent(PaymentContent mPaymentContent) {
		this.mPaymentContent = mPaymentContent;
	}
	//CR1503aL. END

	/** 
	 * CR2006 - Reusable Promo Codes 
	 * return the matching promo code 
	 * */
	public PromoCode getPromoCode(String id) {
		if (mPromoCodes == null || mPromoCodes.size() == 0) {
			return null;
		}

		return mPromoCodes.get(id);
	}

	/**
	 * CR2006 - Reusable Promo Codes
	 * return the promo code hashmap 
	 * */
	public HashMap<String, PromoCode> getPromoCodeMap() {
		return mPromoCodes;
	}

	/**
	 * CR2006 - Reusable Promo Codes
	 * return the size of the promo code hashmap 
	 * */
	public int getPromoCodeMapSize() {
		if (mPromoCodes == null) {
			return 0;
		} else {
			return mPromoCodes.size();
		}
	}


	/**
     * CR2006 - Reusable Promo Codes
     * @param promoCode - promocode to add
     * 
     */
    public void setPromoCode(PromoCode promoCode) {
    	if (promoCode != null) {
	    	if (mPromoCodes == null) {
	    		mPromoCodes = new HashMap<String, PromoCode>();
	    	}
	    	mPromoCodes.put(promoCode.getId(), promoCode);
    	}
    	
    }
    
    /**
     * CR2006 - Reusable Promo Codes
     * @param promoCodes - promocode map to add
     * 
     */
    public void setPromoCodeMap(HashMap<String, PromoCode> promoCodes) {
	    	mPromoCodes = promoCodes; 	
    }

    /**
     * MPAY replacement.  Is the package a goodwill credit package or not?
     * @return true (it is a goodwill credit package) or false (it isn't)
     */
	public boolean isGoodwillCredit() {
		return isGoodwillCredit;
	}
	
	public void setIsGoodwillCredit(boolean isGoodwillCredit) {
		this.isGoodwillCredit = isGoodwillCredit;
	}

	
	/**
	 * MQC 7733 - return the status of the package as a String
	 * @return String
	 */
	public String getActiveStatusAsString() {
		
		String rv = PACKAGE_STATUS_UNKNOWN;
		
		//MQC 7733 - package is active if at least one of its pricepoints is active
		if (mActiveStatus == '\u0000' && this.mPricePoints != null && this.mPricePoints.size() > 0) {
			mActiveStatus = 'I';
			for (PricePoint ppt : this.mPricePoints) {
				if (ppt.isActive(new Date())) {
					mActiveStatus = 'A';
					break;
				}
            }
		}
		
		if (mActiveStatus == 'A') {
			rv = PACKAGE_STATUS_ACTIVE;
		}
		else if (mActiveStatus == 'I') {
			rv = PACKAGE_STATUS_INACTIVE;
		}
		
		return rv;
	}

	// CR2210 - MPay Rate Card
	/**
	 * @return the mUseRateCardService
	 */
	public boolean isUseRateCardService() {
		return mUseRateCardService;
	}

	/**
	 * @return the mRateCardServiceId
	 */
	public String getRateCardServiceId() {
		return mRateCardServiceId;
	}
	// CR2210 - Ends

	/**
	 * CR2238 - return true if package contains active calendar user group pricepoint
	 * @return boolean
	 */
	public boolean isUserGroupCalendarPricePointPackage() {

		boolean rv = false;

		if (!this.isCalendarPackage()) {
			return false;
		}

//		if (mPricePoints == null || mPricePoints.getAll() != null 
//				|| mPricePoints.getAll().length == 0) {
//			PricePoint[] pts = mPricePoints.getAll();
		if (mPricePoints!=null && !mPricePoints.isEmpty())	{
			for (PricePoint pt : mPricePoints) {
				if (pt.isActive(new Date()) && (pt.getUserGroups().length> 0 && pt.getUserGroups()[0] != null 
						&& !pt.getUserGroups()[0].equals(Constants.STRING_NOT_SET)
						&& !pt.getUserGroups()[0].equals(Constants.STRING_MATCH_ALL)) ) {
					rv = true;
					break;
				}
			}
		}

		return rv;
	}
	
	/**
	 * MQC8284 - return true if package contains active user group pricepoint containing one of the input usergroups
	 * @return boolean
	 */
	public boolean containsUserGroupPricePoint(String[] usergroups) {

		boolean rv = false;

//		PricePoint[] pts = null;
				
		if (usergroups == null || usergroups.length == 0) {
			return rv;
		}

//		if (mPricePoints != null && mPricePoints.size() > 0) {
//			pts = mPricePoints.getAll();
//		}
		
		for (String ug : usergroups) {
			if ( !ug.equals(Constants.STRING_NOT_SET) && !ug.equals(Constants.STRING_MATCH_ALL) ) {
				for (PricePoint pt : mPricePoints) {
					if (pt.isActive(new Date()) && (pt.getUserGroups().length> 0 && pt.getUserGroups()[0] != null 
							&& !pt.getUserGroups()[0].equals(Constants.STRING_NOT_SET)
							&& !pt.getUserGroups()[0].equals(Constants.STRING_MATCH_ALL)
							&& ug.equalsIgnoreCase(pt.getUserGroups()[0])) ) {
						rv = true;
						break;
					}
				}
			}
			if (rv) {
				break;
			}
		}
		
		return rv;
	}
	
	/**
	 * CR 2245 upsell discount prorate
	 * @return the mUpsellDiscountProrated
	 */
	public boolean isUpsellDiscountProrated() {
		return mUpsellDiscountProrated;
	}

	/**
	 * CR 2245 upsell discount prorate
	 * @param upsellDiscountProrated the mUpsellDiscountProrated to set
	 */
	public void setUpsellDiscountProrated(boolean upsellDiscountProrated) {
		this.mUpsellDiscountProrated = upsellDiscountProrated;
	}

	/**
	 * CR 2245 upsell discount prorate
	 * @return the mDisallowPrerate
	 */
	public boolean isDisallowPrerate() {
		return mDisallowPrerate;
	}

	/**
	 * CR 2245 upsell discount prorate
	 * @param disallowPrerate the mDisallowPrerate to set
	 */
	public void setDisallowPrerate(boolean disallowPrerate) {
		this.mDisallowPrerate = disallowPrerate;
	}
	
	/**
	 * CR CTB-1 Advanced Linked Pricepoint - return true if package contains a pricepoint which has a link pricepoint
	 * @return boolean
	 */
	public boolean hasLinkPricePoint() {

		boolean rv = false;

		if (mPricePoints == null || mPricePoints.size() == 0) {
			return false;
		}
		
//		PricePoint[] pts = null;
				
//		pts = mPricePoints.getAll();
		
		for (PricePoint pt : mPricePoints) {
			if (pt.isActive(new Date()) && (isNotBlank(pt.getPricepointIdLink())) ) {
				rv = true;
				break;
			}
		}
		
		return rv;
	}
}



