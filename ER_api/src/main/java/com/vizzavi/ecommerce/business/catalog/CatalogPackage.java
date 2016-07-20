package com.vizzavi.ecommerce.business.catalog;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.internal.PricePointsImpl;
import com.vizzavi.ecommerce.business.catalog.internal.model.PricingModel;
import com.vizzavi.ecommerce.business.common.ChargingMethod;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.Constants;
import com.vizzavi.ecommerce.common.EnvironmentException;

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
@Entity
@Table(name="package")
@Access(AccessType.FIELD)
public class CatalogPackage implements Serializable, CatalogBean	{
	
	private static final long serialVersionUID = -1080643286921795316L;
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
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="`KEY`")
	protected Long mKey;

	@Transient
	protected char mActiveStatus;
	
//	PPM136861 refactoring aL. START
//	@Transient
//	protected String mPricingModelFk;

	/** ER 7 Compliant Custom fields for pricing text */
	//@Column(name="pricingText1")
	@Transient
	protected String mPricingText1 = "";
	//@Column(name="pricingText2")
	@Transient
	protected String mPricingText2 = "";

	/**
        A package can have two types of identifier.
            1. A package id defined in priceplan.  This type of package does
                not have a price as it depends on the purchase attributes selected.
            2. A combination of the package id defined in the priceplan and a rate plan name.
                This package does have a price as it defines the purchase attributes selected.
	 */
//	@Id
	@Column(name="Id")
	protected String mId;

	/** The name of the package */
	@Transient
	protected String mName;

	/** The package description */
	@Column(name="description")
	protected String mDescription;

	// The following fields are stored in the catalog and are set by the pricing tool.
	// Client applications can use these fields.

	/** The list of names in multiple languages including the default language. 
	 * Key is the language code.  
	 * Since ER9 **/
	@Transient
	protected Map<String, String> names = null;

	/** The list of description in multiple languages including the default language.  
	 * Key is the language code. 
	 * Since ER 9**/    
	@Transient
	protected Map<String, String> descriptions = null;

	/** The package notification category */
	@Column(name="notificationCategory")
	protected String mNotificationCategory;

	/** The package payment content information */
	@Transient
	protected PaymentContent mPaymentContent;

	/** This can be set in the pricing tool and can be retrieved by the ecom client applications*/
	@Column(name="url")
	protected String mUrl;

	/** custom fields defined in catalog using <custom_field> tag */
	@Transient
	protected Map<String, String> mCustomFields = new HashMap<>();

	/** Custom fields for pricing text */
	/** The list of pricing text1 in multiple languages including the default language. 
	 * Key is the language code.  
	 * Since ER9 **/
	@Transient
	protected Map<String, String> pricingTextList1 = null;

	//ER 7 compliant
	protected String pricingText1 = null;

	/** The list of pricing text2 in multiple languages including the default language.  
	 * Key is the language code. 
	 * Since ER 9**/    
	@Transient
	protected Map<String, String> pricingTextList2 = null;    

	// [1] Mod Start
	@Column(name="protectedType")
	protected String mProtectedType = "";
	@Column(name="DynamicProtectedValue")
	protected String mDynamicProtectedValue = "";
	// [1] Mod End
	// end of catalog fields

	/** The map of services (CatalogService) in the package, indexed by serviceId */
	@Transient
	protected Map<String, CatalogService> mServices = new HashMap<>();

	/** The list of all available rate plan selectors */
	@Transient
	protected PricePoints mPricePoints = new PricePoints();

	/** The selected price point */
	@Transient
	protected PricePoint mPricePoint;

	/** flag which if true indicates that the package should be bought in a two phase reserve-capture way */
	@Column(name="reserveOnly")
	protected boolean mReserveOnly;

	/** This is used to store the type of package */
	@Column(name="PackageType")
	protected String mPackageType;

	/* name of package deal */
	// This is moved from catalogpackageimpl for
	// Partner revenue share phase2

	@Column(name="PurchaseMethod")
	protected String mPurchaseMethod = "";

	//CR0586 KPI Reporting Fields
	protected String kpiPackageProductCategory = "";
	protected String kpiPackageType = "";
	//CR0586 End

	@Column(name="taxcode")
	protected String mTaxCode;

	/** Refund Disallowance attributes AV
	 * @since ER 5.1
	 * */
	@Column(name="Refundable")
	protected boolean mRefundable = true;
	@Column(name="NonRefundableDescription")
	protected String mNonRefundableDescription = "";


	/**
	 * DRMType attribute
	 * @since ER 5.1
	 */
//	protected DRMType m_DRMType;

	/** ADDED FOR EGYPT ER6 STUB **/
	@Column(name="ExpressPurchase")
	protected boolean mExpressPurchase=false;

	//Added for ER6 requirement
	@Column(name="RecieptingFlag")
	protected boolean mReceiptingFlag=false;

	/** ADDED FOR EGYPT ER7 STUB **/
	@Column(name="PricePointOrder")
	protected boolean mPricePointOrder=false;

	/** ADDED FOR ER7 STUB **/
	@Column(name="SuperPackage")
	protected boolean mSuperPackage;

	//Added for ER7
	@Column(name="RevenueShareByUsage")
	protected boolean mRevenueShareByUsage=false;

	//Added for ER7
	@Column(name="DynamicDefault")
	protected boolean mDynamicDefault=false;

	//Added for ER7
	@Column(name="Priority")
	protected int mPriority;

	//Added for ER 8
	@Transient
	protected boolean mACEPackage = false;

	//Added for ER 8 PH 2
	@Column(name="UpSell")
	protected boolean mUpSell = false;

	// Added for R9 STKHREQ16
	protected String logoId = "";

	// Added for R9 STKHREQ67
	/**
	 * The multiple language support Partner Information Text
	 */
	@Transient
	protected Map<String, String> partnerInfoMap = null;


	///////////////////////////////////////////////////////////////
	//@hud STKHREQ36
//	@Column(name="HasMicroService")
	@Transient
	protected boolean mHasMicroService = false;

	//PPM136861 refactoring aL. START
//	/**
//	 * Remedy 5538 
//	 */
//	@Transient
//	protected Map<String, PricingModel> priceModels = new HashMap<String, PricingModel>();
	protected Boolean isPackageModel = Boolean.FALSE;

	//[2] Mod Start
	/**
	 * Added for ER9 for Ring Back Tones (RBT)
	 */
	@Column(name="parentPackage")
	protected boolean mParentPackage = false;
	@Column(name="parentPackageId")
	protected String mParentPackageId = "";
	//[2] Mod End

	//RBT Enhancements Start
	@Transient
	protected List<String> childPackageIdList = null;
	@Transient
	protected boolean childServicesAreInParent = false;
	//RBT Enhancements End

	//ES FUP Enhancement CR Start - a list of comma sepearted service ids which are not in the package fair usage policy when configured
	@Column(name="svcsNotInPackFUPolicyList")
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
	@Column(name="hasParentSubSusResProv")
//	@Transient
	protected boolean hasParentSubSuspendedResProv = false;
	protected int parentSubStatus;

	//CR1113 - Prevent Subscription Cancellation
	@Column(name="DisallowCancellations")
	protected boolean mDisallowCancellations = false;

	//CR 1212 - add sales model
	@Column(name="SalesModel")
	protected String mSalesModel = "";

	//CR1193 - Data Tariff Reporting Enhancement
	@Column(name="DataVoiceTariffInclusive")
	protected boolean mDataVoiceTariffInclusive = false;

	//CR1193 - Data Tariff Reporting Enhancement
	@Column(name="NominalValue")
	protected double mNominalValue = 0.0;

	//CR 1209 add package class
	@Column(name="PackageClass")
	protected String mPackageClass = null;

	//CR 1409 - Prevent Duplicate package purchase 
	protected String disallowDuplicateSubPurchase = "";

	//CR1209AR - VFES defrag
	@Column(name="UseBeingDeprovisionedStatus")
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
	@Transient
	protected HashMap<String, PromoCode> mPromoCodes = null;
	
	// MPAY replacement.  Goodwill credit.
	@Column(name="goodwillCredit")
	protected boolean isGoodwillCredit = false;

    // CR2210 - MPay Rate Card
	/**
	 * The 'mUseRateCardService' flag determines whether rate card service or standard service applies to revenue share.
	 */
	@Column(name="useRateCardService")
	protected boolean mUseRateCardService	= false;

	/**
	 * The 'mRateCardServiceId' holds the service id for the rate card service,
	 * and this field is populated when the 'mUseRateCardService' flag is set.
	 */
	@Column(name="RateCardServiceId")
	protected String mRateCardServiceId		= "";
    // CR2210 - Ends


	/**
	 * CR 2245 upsell discount prorate
	 * The 'mUpsellDiscountProrated' flag determines whether the package supports upsell discount prorated functionality.
	 */
	@Column(name="UpsellDiscountProrated")
	protected boolean mUpsellDiscountProrated = false;
	/**
	 * CR 2245 upsell discount prorate
	 * The 'mDisallowPrerate' flag determines whether the package supports prerate on purchase or renewals.
	 */
	@Column(name="DisallowPrerate")
	protected boolean mDisallowPrerate = false;
	
	@ManyToOne(optional=true, targetEntity=Priceplan.class, fetch=FetchType.LAZY)	
	@Access(AccessType.FIELD)
//	@ManyToOne(optional=false, targetEntity=Priceplan.class, fetch=FetchType.LAZY)	
//	@Access(AccessType.FIELD)
	private Priceplan priceplan;
	
	//PPM136861 refactoring aL. moved here from impl
	@Transient
	protected boolean mIsOriginal = false;
	
	//PPM136861 refactoring aL. START
//	@Transient
//	protected Map<String, String> mPricingModels = new HashMap<String, String>();
	
    /**
     * //CR2303p5 defaultPartnerProvisioningId for Global Handler Notificaiton Service
     * This string is used by ER Core for the provisioning message to ERIF only if 
     * the partner id is not provided in the request (e.g. from customer care app)
     */
    @Column(name="DefaultPartnerProvisioningId")
    protected String mDefaultPartnerProvisioningId;
  	
    /**
     * JIRA-ET271 - Enable User group comparison at renewal config at Catalog Package level
     * This string is used by ER Core to determine what action to take on renewal of a 
     * usergroup pricepoint where the msidn no longer belongs to that usergroup.
     * SYSTEM - (default) carry on the renewal based on the system config of compare_account_usergroups_on_renewal (true / false)
	 * and compare_usergroup_pricepoints_on_renewal (true / false)
     * CONTINUE_SUB - carry on the renewal of the current pricepoint, even if the msisdn does not belong to the usergroup anymore
     * INACTIVATE_SUB - inactivate the subscription. Same as 'compare_account_usergroups_on_renewal' in system config.
     * ALTERNATIVE_PRICEPOINT - renew to a cheaper / same cost usergroup pricepoint (same chargingmethod & duration) 
     * which the msisdn now belongs. Same as 'compare_usergroup_pricepoints_on_renewal' in system config.
     */
    @Column(name="UserGroupComparisonAtRenewal")
    protected String mUserGroupComparisonAtRenewal;
    
    public final static String USERGROUP_COMPARISON_ON_RENEWAL_SYSTEM = "SYSTEM";
    public final static String USERGROUP_COMPARISON_ON_RENEWAL_CONTINUE = "CONTINUE_SUB";
    public final static String USERGROUP_COMPARISON_ON_RENEWAL_INACTIVATE_SUB = "INACTIVATE_SUB";
    public final static String USERGROUP_COMPARISON_ON_RENEWAL_ALTERNATIVE_PRICEPOINT = "ALTERNATIVE_PRICEPOINT";
    
    /**
	 * @return Returns the priceModels.
	 */
	@Transient
	@Deprecated
	public Map<String, PricingModel> getPriceModels() {
		//PPM136861 refactoring aL. START
		return null;
//		return priceModels;
	}

	/**
	 * @param priceModels The priceModels to set.		super();
		setId(id);
		//REMEDY 6149 - removed comments
		mName = name;
		mDescription = desc;
		setName(name);
		setDescription(name);
		mPricePoints = new PricePointsImpl();
	 */
	@Deprecated
	public void setPriceModels(HashMap<String, PricingModel> priceModels) {
		//PPM136861 refactoring aL. START
		//do nothing
//		this.priceModels = priceModels;
	}



	/** Outputs to a string the data in the package
	 * WARNING this method is very slow
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer(getSimplePackageId()).append(" (" );
		sb.append(getName() ).append(")" );
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

	//PPM136861 refactoring aL. START
	public CatalogPackage(String id, HashMap<String, String> names, HashMap<String, String> desc){
		mId = id;
		setNames(names);
		setDescriptions(desc);
		//REMEDY 6149 - set name and description
		mName = getName(null);
		mDescription = getDescription(null);
		mPricePoints = new PricePointsImpl();
	}
	
	public CatalogPackage(Long key, String id, String name, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
	{
		mKey = key;
		mActiveStatus = activeStatus;
		mId = id;
		setName(name);
		mName = name;
	}
	public CatalogPackage(String id, String name, List<CatalogService> services, String desc,
			PricePoints pts, String notificationCategory, PaymentContent paymentContent, boolean reserveOnly)
	{
		mId = id;
		setName(name);
		setDescription(desc);
		mName = name;
		mDescription = desc;
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
		mActiveStatus = activeStatus;
		mId = id;
		setName(name);
		setDescription(desc);
		mName = name;
		mDescription = desc;
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
		this.mActiveStatus = pack.mActiveStatus;
		
//		PPM136861 refactoring aL. START
//		this.mPricingModelFk = pack.mPricingModelFk;
		//		this.mProductFk = pack.mProductFk;

		this.mId = pack.mId;

		setName(pack.getName());
		setDescription(pack.getDescription());
		mName = pack.mName;
		mDescription = pack.mDescription;
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
//		this.m_DRMType =  pack.m_DRMType;
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
		//PPM136861 refactoring aL. START
//		this.priceModels = pack.priceModels;

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

		//QC 10630 missing payment content from payment Auth call
        this.mPaymentContent = pack.mPaymentContent;
        
        // CR2303p5 DefaultPartnerProvisioningId for Global Handler Notification
		// service
        this.mDefaultPartnerProvisioningId = pack.mDefaultPartnerProvisioningId;
		//ET126 missing fields in response for ES
        this.kpiPackageProductCategory = pack.kpiPackageProductCategory;
        this.kpiPackageType = pack.kpiPackageType;
        //JIRA-ET271 - Enable User group comparison at renewal config at Catalog Package level
        this.mUserGroupComparisonAtRenewal = pack.mUserGroupComparisonAtRenewal;
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
	 * (e.g. p003__X__package:p003_TAX_2_1_3_blabla) 
	 * @return
	 * @deprecated if you want the simple package Id, use getSimplePackageId() - if you want the pricepoint, do getPricepoint()
	 */
	@Deprecated
	@Transient
	public String getFullPackagePricepointId() {
	
		String fullId = null;
		
		if (mId != null && getPricePoint()!=null && getPricePoint().getId()!=null) {
			fullId = mId + PACKAGE_DELIMITER + getPricePoint().getId();
		}
		
		logger.debug("getFullPackagePricepointId returning: " + fullId);

		return fullId;
	}
	
	/**
	 * this will be used as the database primary key in the priceplan refactor
	 * @return
	 */
	public Long getKey() {
		return mKey;
	}

	private void setKey(Long key){
		mKey = key;
	}


	/**
	 * this is probably not used any more
	 * @return
	 */
	@Transient
	public char getActiveStatus() {
		return isActive()?'A':'I';
	}

	
	/**
	 * Get the package id without the pricepoint id concatenated. eg p003
	 * @see #getId()
	 */
	@Transient
	public String getSimplePackageId()	{
		return mId;
	}

	/**
	 * The name of the package for default language
	 */
	@Access(AccessType.PROPERTY)
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
//	@AttributeOverrides({
//		@AttributeOverride(name="description",column=@Column(name="pay_content_desc")),
//	})
//	@Embedded
	@Transient
	public PaymentContent getPaymentContent()
	{
		return mPaymentContent;
	}

	/**
	 * @return List<CatalogService> a list of the services in the package, or null sometimes
	 */	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="PACKAGE_SERVICE",
	           joinColumns={@JoinColumn(name="PACKAGE_KEY")},
	            inverseJoinColumns={@JoinColumn(name="SERVICE_KEY")})
	public List<CatalogService> getServices()	{
		if (mServices!=null && mServices.values()!=null) {
			return new ArrayList<CatalogService>(mServices.values());
		} else {
			return null;
		}
	}
	
	/**
	 * use {@link #getServices} instead.
	 * @return CatalogService[] an array view of the services in the package, (never null)
	 */
	@Deprecated
	@Transient
	public CatalogService[] getServiceArray()	{
		//added to keep pricing tool happy
		if (mServices!=null)
				return mServices.values().toArray(new CatalogService[mServices.size()]);
		else
			return new CatalogService[0];
	}

	/**
	 * JIRA-ET231 - Separation of Local & Global notification messages, return true if this package contains both local and global notification services
	 * @return boolean
	 */
	public boolean containsGlobalAndLocalNotificationServices () {
		boolean rv = false;
		
		int noGlobalNotificationServices = 0;
		int noLocalNotificationServices = 0;
		
		if (mServices!=null) {
			for(CatalogService serv: getServices())	{
				if (serv.mGlobalHandlerNotification) {
					noGlobalNotificationServices++;
				} else {
					noLocalNotificationServices++;
				}
				if (noGlobalNotificationServices > 0 && noLocalNotificationServices > 0) {
					rv = true;
					break;
				}
			}
		}
		
		return rv;
	}
	
	//@hud
	@Transient
	public Map<String, CatalogService> getServiceMap() {
		return mServices;
	}

	/**
	 * Sets a new set of services
	 */
	// added to reset the services. Required as a product feature.
	public void setServices(Map<String, CatalogService> newServices)
	{
		mServices = newServices;
	}
	
	/**
	 * Add Charging Services
	 * @param services
	 */
	public void setServices(List<CatalogService> services){

		mServices = new HashMap<String, CatalogService>();

		for ( CatalogService o: services)  {
			addService(o);
		}
	}
	
	/**
	 * Add Charging Service
	 * @param serv
	 */
	public void addService(CatalogService servImpl)	{
		mServices.put(servImpl.getId(), servImpl);
	}

	/**
	 * @return true if the package has the service
	 */
	public boolean hasService(CatalogService service)	{
		return service!=null && mServices.containsKey(service.getId());
	}


	/**
	 * Returns the service specified. Returns null if the package does not have the service
	 * @return the catalog service
	 */
	public CatalogService getService(String id)	{
		return mServices.get(id);
	}



	/**
        @return true if the package is an event package
	 */
	@Transient
	public boolean isEventPackage()
	{
		if (isNotBlank(mPackageType))
			return mPackageType.equals(EVENT_PACKAGE_TYPE);

		boolean rv = false;
		if (getPricePoints()!=null &&!getPricePoints().isEmpty())	{
			rv = ChargingMethod.isEvent(getPricePoints().get(0).getChargingMethod());
		}
		if (rv==true && isBlank(mPackageType)) {
			logger.warn("package type for {} was [{}] but should have been Event", mId, mPackageType );
			mPackageType = EVENT_PACKAGE_TYPE;
		}
		return rv;
	}

	/**
        Check the current selected price point to see if the ChargingMethod is recurring
	 */
	@Transient
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
		if (isNotBlank(mPackageType))
			return mPackageType;
		//ET205 - work around problem in priceplan where packageType is empty
		if (isCalendarPackage())
			return CALENDAR_PACKAGE_TYPE;
		else if (isEventPackage())
			return EVENT_PACKAGE_TYPE;
		logger.warn("can't work out package type for {}", getId());
		return null;
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
		for(CatalogService s: getServices())	{
			if(!s.isRefundable()){
				this.mRefundable = false;
				break;
			}
		}
		return this.mRefundable;
	}

	/**
        @return true if the package is a calendar package (subscription package)
	 */
	@Transient
	public boolean isCalendarPackage()	{
		boolean rv = false;

		if (isNotBlank(mPackageType))	{
			return mPackageType.equals(CALENDAR_PACKAGE_TYPE);
		} else if (getPricePoints()!=null && !getPricePoints().isEmpty())	{
			// all ppts in the package have the same charging method so just choose the first one
			rv = ChargingMethod.isCalendar(getPricePoints().get(0).getChargingMethod());
		}
		if (rv==true && isBlank(mPackageType)) {
			logger.warn("package type for {} was [{}] but should have been Calendar", mId, mPackageType );
			mPackageType = CALENDAR_PACKAGE_TYPE;
		}
		return rv;
	}

	/**
        Return all of the price points in the package
	 */
	@Transient
	public PricePoints getPricePoints()	{
		return mPricePoints;
	}

	@OneToMany(mappedBy="pack", targetEntity=PricePoint.class, fetch=FetchType.LAZY)
	//@ForeignKey(name = "onepackage_manyppts")	//only for the ddl generation to give the constraint a readable name
	@Access(AccessType.PROPERTY)
	List<PricePoint> getPricePointList()	{
		return getPricePoints();
	}
	
	void setPricePointList(List<PricePoint> ps){
		mPricePoints = new PricePoints(ps);
	}
	
	/**
        Returns the current selected price point
        @deprecated as per MQC8385
	 */
	@Deprecated
	@Transient
	public PricePoint getPricePoint()	{
		return mPricePoint;
	}

	/**
        Set the selected price point. This sets the price of the package
        This should only be used from a copied package.
        @see CatalogApi.getPackage()
        @deprecated as per MQC8385

	 */
	@Deprecated
	public void setPricePoint(PricePoint pt)	{
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
	public void setPricePoint(String pricePointId)	{
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
	public double getRate() {
		return getRate(new Date());
	}

	/**
	 * A package doesn't have a rate.  A pricepoint has a rate.
	 * @param date
	 * @return
	 */
	@Deprecated
	public double getRate(Date date)	{
		//MQC8385 TODO
		if (getPricePoint()!=null) {
			return getPricePoint().getRate(date);
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
	public String getRateAsString(Locale loc, Date date)	{
		//MQC8385 TODO
		String rv = null;
		if (getPricePoint()!=null) {
			rv = getPricePoint().getRateAsString(loc, date);
		}
		return rv;
	}


	/**
	 * The type of currency
	 */
	@Transient
	public ChargingResource getResource()	{
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
	public String getUrl()	{
		return mUrl;
	}

	/**
        Used to store special pricing information.
        A catalog field
	 */
	@Access(AccessType.PROPERTY)
	public String getPricingText1()	{
		return getPricingText1(Constants.DEFAULT_LANGUAGE_CODE);
	}

	/**
        Used to store special pricing information.
        A catalog field
	 */
	@Access(AccessType.PROPERTY)
	public String getPricingText2()	{
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
	@Transient
	public Map<String, String> getCustomFields() {
		return mCustomFields;
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


	@Transient
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
		for (PricePoint pt: getPricePoints())	{
			if (pt.isActive(date))
				return true;
		}
		return false;
	}

	public String getTaxCode()	{
		return mTaxCode;
	}


	@Transient
	public String[] getPricingModels()	{
//		String[] tempString = {""};
//		return tempString;
		return new String[0];
	}



	/**
	 * is this the default package?
	 * @return
	 */
	@Transient
	public boolean isDefault()	{
		return getSimplePackageId().equals(DEFAULT_ID);
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public boolean isExpressPurchase() {
		return mExpressPurchase;
	}

	/** ADDED FOR ER6 Requirement **/
	public boolean isRecieptingFlag() {
		return mReceiptingFlag;
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
	public int getPriority()	{
		return mPriority;
	}

	@Transient
	public boolean getACEPackage()	{
		return mACEPackage;
	}

	/** Added FOR ER 8 Ph 2 Requirement */
	public boolean isUpSell()	{
		return mUpSell;
	}

	// Added since ER 9 - Start
	@Transient
	public Map<String, String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Map<String, String> descriptions) {
		this.descriptions = descriptions;
	}

	@Transient
	public Map<String, String> getNames() {
		return names;
	}

	public void setNames(Map<String, String> names) {
		this.names = names;
	}

	public String getName(String languageCode)	{
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

	@Transient
	public Map<String, String> getPartnerInfoMap() {
		return partnerInfoMap;
	}

	public void setPartnerInfoMap(Map<String, String> partnerInfoMap) {
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

	public void setChildPackages(List<String> childPackageIdList){
		this.childPackageIdList = childPackageIdList;
	}
	
	@Transient
	public List<String> getChildPackages(){
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
	
	//@Column(name="svcsNotInPackFUPolicyList")
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
//	@Column(name="hasParentSubSusResProv")
//	@Access(AccessType.PROPERTY)
	public boolean isHasParentSubSuspendedResProv() {
		return hasParentSubSuspendedResProv;
	}

	/**
    MQC 5485 - This is used internally by the ER system
	 */
//	@Column(name="hasParentSubSusResProv")
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
	@Column(name="disallowsubpch")
	@Access(AccessType.PROPERTY)
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
		/*return getNoActivePricepoints(new Date());//ET-153
	}

	//CR1564 -Utctimezone for diff region in country
	//CR 1542 - return the number of active pricepoints
	public int getNoActivePricepoints(Date date) {
*/
		int rv = 0;

		if ( mPricePoints != null) {
			//PricePoint[] pts = mPricePoints.getAll();
			for (PricePoint pt : mPricePoints) {
				//MQC 6051 - also regard historic pricepoints as active as existing subscriptions may still have active subscriptions against these
				if (pt.isActive(/*date*/) || pt.isHistoric()) {
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
	
//ET-153 | commented the time zone code | start	
//	public void setUseStaticValues(boolean useStaticValues) {
//		if (mPricePoint != null) {
//			mPricePoint.setUseStaticValues(useStaticValues);
//		}
//		if (mPricePoints != null) {
//			for (PricePoint pp : mPricePoints) {
//				pp.setUseStaticValues(useStaticValues);
//			}
//		}
//	}
//	ET-153 | commented the time zone code | end	

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
	@Transient
	public Map<String, PromoCode> getPromoCodeMap() {
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
	@Column(name="goodwillCredit")
    @Transient
	public boolean isGoodwillCredit() {
		return isGoodwillCredit;
	}
	
	@Column(name="goodwillCredit")
	public void setIsGoodwillCredit(boolean isGoodwillCredit) {
		this.isGoodwillCredit = isGoodwillCredit;
	}

	
	/**
	 * MQC 7733 - return the status of the package as a String
	 * @return "ACTIVE" or "INACTIVE"
	 */
	@Transient
	public String getActiveStatusAsString() {
		return isActive()?PACKAGE_STATUS_ACTIVE:PACKAGE_STATUS_INACTIVE;
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
	@Transient
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
		
		for (PricePoint pt : mPricePoints) {
			if (pt.isActive() && (isNotBlank(pt.getPricepointIdLink())) ) {
				rv = true;
				break;
			}
		}
		
		return rv;
	}

	
	/**
	 * JIRA ET237 - Linking of Non-TRIAL pricepoints - return the link pricepoint order if the pricepoint belongs to a linked price model
	 * @param pricePoint
	 * @return int
	 */
	public int getLinkedPricepointOrder (PricePoint pricePoint) {
		
		int rv = -1; //default value, not a pricepoint in a link model
		
		//Event pricepoints cannot be linked
		if (pricePoint == null || pricePoint.isEvent()) {
			return rv;
		}
		
		//a non-recurring TRIAL pricepoint cannot be linked
		if (pricePoint.isTrial() && pricePoint.isNonRecurring()) {
			return rv;
		}
				
		//a recurring TRIAL pricepoint must be linked and will always be the first pricepoint in the link model
		if (pricePoint.isTrial() && pricePoint.isRecurring()) {
			rv = 1;
			return rv;
		}
		
		//a recurring non-TRIAL with a link pricepoint id, if the pricpoint itself is not a 
		//linked pricepoint then this must be first pricepoint in the link model, if it is a linked pricepoint then it must
		//be the second pricepoint in the link model
		if (pricePoint.isRecurring() && isNotBlank(pricePoint.getPricepointIdLink())) {
			rv = 1;
			if (this.mPricePoints != null) {
				for (PricePoint pt : mPricePoints) {
					if ( (pt.isActive() || pt.isHistoric()) && (isNotBlank(pt.getPricepointIdLink()) && pt.getPricepointIdLink().equals(pricePoint.getId()))) {
						rv = 2;
						break;
					}
				}
			}
			return rv;
		}
		
		//for the non-recurring or recurring calendar without a link pricepoint id, if the pricepoint itself is a linked pricepoint, if the master pricepoint is a TRIAL then
		//this pricepoint must be the second pricepoint in the link model. If the master pricepoint is not TRIAL then check this
		//pricepoint is itself a linked one, then this will be the third pricepoint in the link model
		else {
			PricePoint masterPricePoint = null;
			if (this.mPricePoints != null) {
				for (PricePoint pt : mPricePoints) {
					if ((pt.isActive() || pt.isHistoric()) && (isNotBlank(pt.getPricepointIdLink()) && pt.getPricepointIdLink().equals(pricePoint.getId()))) {
						masterPricePoint = pt;
						break;
					}
				}
				if (masterPricePoint != null) {
					if (masterPricePoint.isTrial()) {
						rv = 2;
						return rv;
					} else {
						for (PricePoint pt : mPricePoints) {
							if ((pt.isActive() || pt.isHistoric()) && (isNotBlank(pt.getPricepointIdLink()) && pt.getPricepointIdLink().equals(masterPricePoint.getId()))) {
								rv = 3;
								return rv;
							}
						}
					}
					//if rv is still not set and this is a non-recurring pricepoint which is a link or this is a recurring pricepoint 
					//with a blank link, then it can only be the second pricepoint in the link model
					if (rv == -1 && ( (pricePoint.isNonRecurring()) || (pricePoint.isRecurring() && isBlank(pricePoint.getPricepointIdLink())) ) ) {
						rv = 2;
					} 
				}
			}
		}
				
		return rv;
	}
	
	/**
	 * JIRA ET237 - Linking of Non-TRIAL pricepoints - return true if pricepoint belongs to a link model
	 * @param pricePoint
	 * @return
	 */
	public boolean isPricepointInLinkModel (PricePoint pricePoint) {
		boolean rv = false;
		
		if (pricePoint != null && getLinkedPricepointOrder(pricePoint)>0) {
			rv = true;
		}
		
		return rv;
	}
	
//PPM136861 refactoring aL. START
//	@ManyToOne(optional=false, targetEntity=Priceplan.class, fetch=FetchType.LAZY)	
//	@Access(AccessType.PROPERTY)
	Priceplan getPriceplan() {
		return priceplan;
	}

//PPM136861 refactoring aL. START changed default to public
	public void setPriceplan(Priceplan priceplan) {
		this.priceplan = priceplan;
	}

//PPM136861 refactoring aL. START moved here from impl
	@Deprecated
	public void addPricingModel(String val) {
		//PPM136861 refactoring aL. START do nothing
//		if (! mPricingModels.containsKey(val))
//			mPricingModels.put(val,null);
	}

	/**
	 * Remove Charging Service
	 * @param serv
	 */
	public void removeService(CatalogService servImpl) {
		mServices.remove(servImpl.getId());
	}

	/**
	    Returns true if package has a purchase range
	 */
	public boolean hasStartEndDate() {
		return true;
		// this should cycle through the price points and work this out
		// we should have a setStartDate and setExpiryDate as well
		// which sets all price points
	
		/*        if (getStartDate()==null || getExpiryDate()==null) {
	        return false;
	    } else {
	        return true;
	    }
		 */
	}

	public Date getStartDate() {
		//TODO wtf?  
		return new Date();
	}

	public Date getExpiryDate() {
		return new Date();
	}

	public void setPackageId(String Id) {
		mId = Id;
	}

	public void setActiveStatus(char activeStatus) {
		mActiveStatus = activeStatus;
	}

	public void setUrl(String url) {
		mUrl = url;
	}

	public String[] getServiceNames() {
			List<String> rv = new ArrayList<String>();
	//		CatalogService[] packs = getServices();
			for (CatalogService pack : getServices()) {
				rv.add(pack.getName());
			}
			return rv.toArray(new String[] {});
		}

	public void setId(String val) {
		try {
			Long.parseLong(val);
			throw new EnvironmentException("The package id cannot be a number " + val);
		} catch (Exception e) {
			// this is okay
		}
		mId = val;
	}

	public void setPackageType(String type) {
		mPackageType = type;
	}

	public void setPurchaseMethod(String purchaseMethod) { mPurchaseMethod = purchaseMethod; }

	public void setReserveOnly(boolean isReserveOnly) { mReserveOnly = isReserveOnly; }


	public void setNotificationCategory(String val) {
		mNotificationCategory = val;
	}

	/**
	 * Sets a custom field for the package.
	 * Custom fields appear in <custom_field> tags in the catalog XML.
	 */
	public void setCustomField(String name, String value) {
		mCustomFields.put(name, value);
	}

	public boolean isOriginal() {
		return mIsOriginal;
	}

	public void setIsOriginal() {
		mIsOriginal = true;
	}

	public String getPricingModel() {
		String rv = null;
		String [] val = getPricingModels();
		if (val.length>0) {
			rv = val[0];
		}
	
		return rv;
	}

//	PPM136861 refactoring aL. START
	public void setPricingModelFk(String pricingModelFk) {
//		mPricingModelFk = pricingModelFk;
	}

	public void setPricingModel(String val) {
//		mPricingModels.put(val,val);
	}

	public void deletePricingModelData() {
//			if (getPricePoints()!=null) {
//	//			PricePoint[] pts = getPricePoints().getAll();
//	//			for (int index=0; pts!=null && index<pts.length; index++) {
//				for (PricePoint p: getPricePoints())	{
//					PricePointImpl impl = (PricePointImpl)p;
//					impl.deletePricingModelData();
//				}
//			}
		}

	public void deletePricingModel(String pricingModel) {
//			mPricingModels.remove(pricingModel);
//			if (getPricePoints()!=null) {
//	//			PricePoint[] pts = getPricePoints().getAll();
//	//			for (int index=0; pts!=null && index<pts.length; index++) {
//				for (PricePoint p: getPricePoints())	{
//					PricePointImpl impl = (PricePointImpl)p;
//					impl.deletePricingModel(pricingModel);
//				}
//			}
		}

	public void deletePricingModelTier(String pricingModel, String tierId) {
//			if (getPricePoints()!=null) {
//	//			PricePoint[] pts = getPricePoints().getAll();
//	//			for (int index=0; pts!=null && index<pts.length; index++) {
//				for (PricePoint p: getPricePoints())	{
//					PricePointImpl impl = (PricePointImpl)p;
//					impl.deletePricingModelTier(pricingModel, tierId);
//				}
//			}
		}
//	PPM136861 refactoring aL. END
	
	/**
	 * @param string
	 */
	public void setTaxCode(String string) {
		mTaxCode = string;
	}

//	PPM136861 refactoring aL. START
//	/**
//	 * @param id
//	 * @return
//	 */
//	public boolean containsPricingModel(String id) {
//		if (mPricingModels != null && id != null) return mPricingModels.containsKey(id);
//		return false;
//	}

	/**
	 * @param refundable
	 */
	public void setNonRefundable(boolean bool) {
		mRefundable = !bool;
	}


	/** ADDED FOR EGYPT ER6 STUB **/
	public void setExpressPurchase(boolean expressPurchase) {
		mExpressPurchase = expressPurchase;
	}

	/** ADDED FOR ER6 Requirement **/
	public void setRecieptingFlag(boolean receiptingFlag) {
		mReceiptingFlag = receiptingFlag;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	public void setPricePointOrder(boolean pricePointOrder) {
		mPricePointOrder = pricePointOrder;
	}

	/** ADDED FOR ER7 STUB **/
	public void setRevenueShareByUsage(boolean revenueShareByUsage) {
		mRevenueShareByUsage = revenueShareByUsage;
	}

	/** ADDED FOR ER7 STUB **/
	public void setDynamicDefault(boolean dynamicDefault) {
		mDynamicDefault = dynamicDefault;
	}

	/** ADDED FOR ER7 STUB **/
	public void setPriority(int priority) {
		mPriority = priority;
	}

	/** ADDED FOR ER7 STUB **/
	public void setSuperPackage(boolean superPackage) {
		mSuperPackage = superPackage;
	}

	/** ADDED FOR ER8  **/
	public void setACEPackage(boolean ACEPackage) {
		mACEPackage = ACEPackage;
	}

	/** ADDED FOR ER8 Ph 2  **/
	public void setUpSell(boolean UpSell) {
		mUpSell = UpSell;
	}

	/**
	 * @param mUseRateCardService the mUseRateCardService to set
	 */
	public void setmUseRateCardService(boolean useRateCardService) {
		this.mUseRateCardService = useRateCardService;
	}

	/**
	 * @param mRateCardServiceId the mRateCardServiceId to set
	 */
	public void setmRateCardServiceId(String rateCardServiceId) {
		this.mRateCardServiceId = rateCardServiceId;
	}
	// CR2210 - Ends
	//PPM136861 refactoring aL. END
	// CR2303p5
	public String getDefaultPartnerProvisioningId() {
		return mDefaultPartnerProvisioningId;
	}
	// CR2303p5
	public void setDefaultPartnerProvisioningId(String defaultPartnerProvisioningId) {
		this.mDefaultPartnerProvisioningId = defaultPartnerProvisioningId;
	}
	
	/**
     * JIRA-ET271 - Enable User group comparison at renewal config at Catalog Package level
     * @return the user group comparison at renewal value
     */
    public String getUserGroupComparisonAtRenewal() {
    	return this.mUserGroupComparisonAtRenewal;
    }
    
    /**
     * JIRA-ET271 - Enable User group comparison at renewal config at Catalog Package level
     * @return set the user group comparison at renewal value
     */
    public void setUserGroupComparisonAtRenewal(String userGroupComparisonAtRenewal) {
    	this.mUserGroupComparisonAtRenewal = userGroupComparisonAtRenewal;
    }
}



