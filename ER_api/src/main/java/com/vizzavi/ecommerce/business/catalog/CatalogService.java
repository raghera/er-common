package com.vizzavi.ecommerce.business.catalog;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vizzavi.ecommerce.business.catalog.Partner;
import com.vizzavi.ecommerce.business.catalog.RateCardPartners;
import com.vizzavi.ecommerce.business.common.Constants;

/**
    A service represents what a user an use.

    A service might be a ringtone, an alert, a mp3 content
*/
public class CatalogService implements java.io.Serializable
{
   private    static final long serialVersionUID = 297274952574297422L;

    final public static String VOLUME_TYPE = "volume";
    protected Long mKey;
    protected String mCreatedBy;
    protected String mModifiedBy;
    protected Date mModifiedDate;
    protected char mActiveStatus;
    protected Long mProductFk;

    //@hud STKHREQ13076
    protected boolean mOnRoamingDisabled = false;
    protected boolean mOffRoamingDisabled = false;
    //protected boolean mFake;// sometime client just new CatalogService("", "", "") and pass in, this is very bad!it's fake


    /** The id of the service  */
    protected String mId;

    /** The list of names in multiple languages including the default language.
     * Key is the language code.
     * Since ER9 **/
    protected HashMap<String, String> names = null;

    /** The list of description in multiple languages including the default language.
     * Key is the language code.
     * Since ER 9**/
    protected HashMap<String, String> descriptions = null;

    /**  The name of the service    */
    protected String mName;

    protected String mUsageId = "";
    protected String mProvTag = "";
    protected String mProvSystem = "";
    protected String mServiceCategory = "";
    protected String mNotificationCategory = "";

    protected PaymentContent mPaymentContent;

    /**  The description to display to the user    */
    protected String mDescription = "";

    protected boolean mIsDefaultService;
    protected String mUrl = "";
    protected String mTaxCode;

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

    /** The list of all available rate plan selectors */
    protected PricePoints mPricePoints = null;

    protected PricePoint mPricePoint;

    /** flag which if true indicates that the service should be used in a two phase reserve-capture way */
    protected boolean mReserveOnly;

    /* custom fields defined in catalog using <custom_field> tag */
    protected Map<String, String> mCustomFields = new HashMap<String, String>();

    /* ServiceRevenueSharePartners */
    protected Map<String,ServiceRevenueSharePartner> mRevSharePartnerMap = new HashMap<String, ServiceRevenueSharePartner>();
    //[1] Mod Start
    /* ServiceRevenueSharePartners Added For ER8 Stubs*/
    protected Map<String,ServiceRevenueSharePartner> mRevSharePartnerPurchaseChMap = new HashMap<String, ServiceRevenueSharePartner>();
    //[1] Mod End

    //Added ER7.0 - Added Vivek
    protected Map<String, HashMap<String, ServiceRevenueSharePartner>> mRevSharePartnerForPackageMap = new HashMap<String, HashMap<String, ServiceRevenueSharePartner>>();
    //Added ER7.0 - Added Vivek

    //Added ER8 stubb
    protected String mDistributionChannel;

   /*The deal package is in*/
   protected String mDealName;

    //++ Scalability Option
    /** Level which will tell the flow to follow by an usage in usageAuthRateCharge */
    protected int mHighVolumeInterfaceLevel = Constants.INT_NOT_SET;
    //-- Scalability Option

/* Added by Periasamy on 01/07/2003 */

    protected String mIndirectValue = "";
    protected String mIndirectValueFormat = "";
    protected String mPromoValue = "";
    protected String mPromoValueFormat = "";
    protected String mContentCategory = "";
    protected String mContentSubCategory = "";
    // Default to empty
    protected String mContentItem = "";
    protected String mDeliveryMechanism = "";
    protected String mProductCategory = "";
    protected String mProductSubCategory1 = "";
    protected String mProductSubCategory2 = "";
    protected String mServiceType = "";

	/** requirement for Refund Diallowance
	 *  by default it is always refundable
	 * @since ER 5.1
	 */
	 protected String mNonRefundableDescription = "";
     protected boolean mRefundable = true;

     // CR0586 KPI Reporting Fields
     protected String mProductWholesalePrice = "";
     protected String mProductSelfRegulation= "";
     // CR0586 end

     /* Added for ER7 stubs */
     protected boolean mServiceShareOverride = false;
 	/* Added for ER7 stubs */
     protected boolean mExpiredPackageService = false;
 	/* Added for ER7 stubs */
     protected double mFixedUsageAmount;
     /* Added to set if the call to findPackagesWithService must return all the CatalogServices or just the one passed as the parameter - Italy performance improvement when a Catalogue has hundreds of Services*/
     protected boolean mReturnAllCatalogueServicesInfo = true;

     //REMEDT 6689
     protected boolean mHasExpress = false;
     protected boolean mHasDynamic = false;
     protected boolean mHasSuperPackage = false;
     //END REMEDY 6689
     
     //CR 1174 return trial options only
     protected boolean mReturnTrialOptionsOnly = false;
     
     //CR 1212 - add sales model
     protected String mSalesModel = "";
     
     //CR 1209 add service class
 	 protected String mServiceClass = null;
 	
	 //CR1209AR VFES defrag
     protected boolean mProvisionOnUsage = false;

	 //CR Off portal
     protected Map<String, BandRevenueShare> mBandRevenueShares;

     //CR Double Charging
     protected boolean mReIssuePermittedFlag = false;

     //CR 1542 - Service can be charged by the following
     public static String CURRENCY_ONLY = "Currency Only";
     public static String CUSTOM_CREDITS_ONLY = "Custom Credits Ony";
     public static String CURRENCY_AND_CUSTOM_CREDITS = "Currency and Custom Credits";
     public static String NOT_DEFINED = "Not Defined";
     
     protected String mChargeableBy = NOT_DEFINED;
     
     //CR 1542 - The packageids the service belongs to
     protected ArrayList<String> mPackageIds = null;
     
     //CR 1542 - Service is a micro service
     protected boolean mMicroService = false;

	 // CR1503 - Multi Price plans - phase 2
     /** This attribute holds the external price plan's name (price plan used to import the service). */
     protected String mExternalServPricePlan;

     //MQC 6067 - The super packageids the service belongs to
     protected ArrayList<String> mSuperPackageIds = null;

     // CR2210 - MPay Rate Card
     /**
      * The 'mUseRateCard' flag determines whether this is a rate card service or a standard service.
      * When the flag is set the service is not exposed to the packages, and it is not used for rating.
      */
     protected boolean mUseRateCard					= false;

     /**
      * This is the internal partner (Vodafone partner).
      */
     protected Partner mInternalPartner				= null;

     /**
      * This container holds the details of all the rate card partners associated to the service.
      */
     protected RateCardPartners mRateCardPartners	= null;
     // CR2210 - Ends

     /**
      * CR2256
      * This flag determines whether a successful usage can be carried out against the service if the subscription is in being provisioned status.
      * If the flag is false (default), then the usage will not succeed since the subscription is being provisioned status
      * If the flag is true, the usage will succeed. This will also override the system config setting of REFUSE_USAGE_WHEN_PROVISIONING=ON
      */
     protected boolean mUsageAllowedBeingProvisionedSub	= false;

     /**
      * CR2165 Partner Notification Handler
      * This flag determines whether a provisioning message is going to be forwarded to the Global Integration Framework (GIF) or to the Local Integration Framework.
      * If the flag is false (default), then the message is going to be forwarded to the Local IF.
      * This flag is also intended to override the system config setting of 'er.routing.provisioning.global.services'.
      */
     protected boolean mGlobalHandler = false;

     /**
      * MQC8858 Global Handler Notificaiton Service
      * This flag determines whether a notification message is going to be forwarded to the Global Integration Framework (GIF) or to the Local Integration Framework.
      * If the flag is false (default), then the message is going to be forwarded to the Local IF.
      * This flag is also intended to override the system config setting of 'er.routing.notification.global.services'.
      */
     protected boolean mGlobalHandlerNotification = false;
     
     //Remedy 5819, Bruno Meseguer, ticket reworked as old client was always getting partial service information
     private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
     {
    	 //This is the default value that needs to be set
    	 //otherwise when deserializing from an old client, java will set it to false
    	 //for more information visit: http://java.sun.com/j2se/1.4/pdf/serial-spec.pdf
    	 //section 5.6.1 Incompatible changes.
    	 mReturnAllCatalogueServicesInfo = true;

    	 //concerning the variable 'mReturnAllCatalogueServicesInfo':
    	 //if a different value comes from the client, the default value will then be updated with the new one
    	 //if no value comes from the client the default one will remain
    	 in.defaultReadObject();
     }
     /**
      * creates a copy of the service passed in
      * @param copy
      */
    public CatalogService(CatalogService copy)
    {
		mKey = copy.mKey;
        mCreatedBy = copy.mCreatedBy ;
		mModifiedBy = copy.mModifiedBy;
        mModifiedDate = copy.mModifiedDate;
    	mActiveStatus = copy.mActiveStatus;
		mProductFk = copy.mProductFk;
        mId = copy.mId;
        setName(copy.getName());

        /* ER 7 Compliant */
        mName = copy.mName;
        mDescription = copy.mDescription;
        mPricingText1 = copy.mPricingText1;
        mPricingText2 = copy.mPricingText2;
        /* ER 7 Compliant */

        this.setNames(copy.getNames());

        mUsageId = copy.mUsageId;
        mProvTag = copy.mProvTag;
        mProvSystem = copy.mProvSystem;
        mServiceCategory = copy.mServiceCategory;
        mNotificationCategory = copy.mNotificationCategory;

        //mPaymentContent = new PaymentContent(copy.mPaymentContent);
        setDescription(copy.getDescription());
        this.setDescriptions(copy.getDescriptions());

        mIsDefaultService = copy.mIsDefaultService;
        mUrl = copy.mUrl;
        mPricePoint = copy.mPricePoint;
        mReserveOnly = copy.mReserveOnly;
        mDealName = copy.getDealName();

        setPricingText1(copy.getPricingText1());
        setPricingText2(copy.getPricingText2());
        this.setPricingTextList1(copy.getPricingTextList1());
        this.setPricingTextList2(copy.getPricingTextList2());

        mCustomFields = new HashMap<String, String>(copy.mCustomFields);
        mServiceType = copy.mServiceType;
		mNonRefundableDescription = copy.mNonRefundableDescription;
		mRefundable = copy.isRefundable();

		//++ Scalability Option
		mHighVolumeInterfaceLevel = copy.mHighVolumeInterfaceLevel;
		//-- Scalability Option
		mServiceShareOverride = copy.mServiceShareOverride;
		mExpiredPackageService = copy.mExpiredPackageService;
		mFixedUsageAmount = copy.mFixedUsageAmount;

		mOnRoamingDisabled = copy.mOnRoamingDisabled;
		mOffRoamingDisabled = copy.mOffRoamingDisabled;

		/* Added for ER8 stubs */
		mDistributionChannel = copy.getDistributionChannel();
		
			//CR 1174 return trial options only
		mReturnTrialOptionsOnly = copy.isReturnTrialOptionsOnly();
	
		//CR 1212 - add sales model
		mSalesModel = copy.getSalesModel();
		
		//CR 1209 add service class
		mServiceClass = copy.getServiceClass();
		
		//CR Double charging
		mReIssuePermittedFlag = copy.isReIssuePermittedFlag();
		
		//CR1209AR VFES defrag
		mProvisionOnUsage = copy.isProvisionOnUsage();
		
		// CR1503 - Multi Price plans - phase 2
		mExternalServPricePlan = copy.getmExternalServPricePlan();

		// CR2210 - MPay Rate Card
		mUseRateCard		= copy.isUseRateCard();
		mInternalPartner	= copy.getInternalPartner();
		mRateCardPartners	= copy.getRateCardPartners();
		// CR2210 - Ends
		
		//CR2256
		mUsageAllowedBeingProvisionedSub = copy.isUsageAllowedBeingProvisionedSub();

		// CR2165 Partner Notification Handler
		mGlobalHandler		= copy.isGlobalHandler();
		
		//MQC8858 Global Handler Notificaiton Service
		mGlobalHandlerNotification		= copy.isGlobalHandlerNotification();
		
		if (copy.mPackageIds!=null)
			mPackageIds = new ArrayList<String>(copy.mPackageIds);	//required for fast rating
    }

    @Override
	public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(getId()).append(" ");
        sb.append( getName() );
        sb.append( " " );

        sb.append( mUsageId );
        sb.append( " " );

        sb.append( mProvTag );
        sb.append( " " );

        sb.append( mProvSystem );
        sb.append( " " );

        sb.append( mNotificationCategory );
        sb.append( " " );

		sb.append( "isRefundable() = "+ mRefundable );
		sb.append( " " );

		sb.append( "mNonRefundableDescription() = "+ mNonRefundableDescription );
		sb.append( " " );

        if( mPaymentContent != null ) {
            sb.append( mPaymentContent.toString());
            sb.append( " " );
        }

        sb.append( getDescription() );
        sb.append( " " );

        sb.append( mIsDefaultService );
        sb.append( " " );

        if( mPricePoints != null ) {
            sb.append( mPricePoints.toString() );
            sb.append( " " );
        }

        if( mPricePoint != null ) {
            sb.append( mPricePoint.toString() );
            sb.append( " " );
        }
        sb.append( mReserveOnly );


    sb.append( "mServiceShareOverride = " + mServiceShareOverride );
    sb.append( "mExpiredPackageService = " + mExpiredPackageService );
    sb.append( "mFixedUsageAmount = " + mFixedUsageAmount );
    //CR 1209 add service class
    sb.append( "mServiceClass = " + mServiceClass );
    
    //CR Double charging
    sb.append( " " );
    sb.append( "mReIssuePermittedFlag = " + mReIssuePermittedFlag );
    sb.append("mProvisionOnUsage = " + mProvisionOnUsage);

	// CR1503 - Multi Price plans - phase 2
	sb.append(" mExternalServPricePlan = ").append(mExternalServPricePlan);

	// CR2210 - MPay Rate Card
	sb.append(" mUseRateCard = ").append(mUseRateCard);

	if (null != mInternalPartner)
		sb.append(" mInternalPartner = ").append(mInternalPartner.toString());

	// CR2210 - Ends

	//CR2256
	sb.append(" mUsageAllowedBeingProvisionedSub = ").append(mUsageAllowedBeingProvisionedSub);

	// CR2165 Partner Notification Handler
	sb.append(" mGlobalHandler = ").append(mGlobalHandler);

	//MQC8858 Global Handler Notificaiton Service
	sb.append(" mGlobalHandlerNotification = ").append(mGlobalHandlerNotification);
	
	return sb.toString();
    }

    /**
     * 
     * @param id
     * @param name
     * @param desc
     * @param pts pricepoints
     * @param defaultServiceFlag
     * @param usageId
     * @param provTag
     * @param provSystem
     * @param notcat
     * @param paymentContent
     * @param reserveOnly
     * @param highVolumeInterfaceLevel
     */
    public CatalogService(String id, String name, String desc, PricePoints pts, boolean defaultServiceFlag, String usageId, String provTag, String provSystem, String notcat, PaymentContent paymentContent, boolean reserveOnly, int highVolumeInterfaceLevel)
    {
        this(id, name, desc, pts, defaultServiceFlag);
        mUsageId = usageId;
        mProvTag = provTag;
        mProvSystem = provSystem;
        mNotificationCategory = notcat;
        mReserveOnly = reserveOnly;
        mHighVolumeInterfaceLevel = highVolumeInterfaceLevel;

        /* ER 7 Compliant */
        mName = name;

        mDescription = desc;
        /* ER 7 Compliant */

    }
    /**
     * 
     * @param key
     * @param id
     * @param name
     * @param desc
     * @param pts
     * @param defaultServiceFlag
     * @param usageId
     * @param provTag
     * @param provSystem
     * @param notcat
     * @param paymentContent
     * @param reserveOnly
     * @param highVolumeInterfaceLevel
     * @param createdBy
     * @param modifiedBy
     * @param modifiedDate
     * @param activeStatus
     */
    public CatalogService(Long key, String id, String name, String desc, PricePoints pts, boolean defaultServiceFlag, String usageId, String provTag, String provSystem, String notcat, PaymentContent paymentContent, boolean reserveOnly, int highVolumeInterfaceLevel,
                          String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
    {
        this(key, id, name, desc, pts, defaultServiceFlag, createdBy, modifiedBy, modifiedDate, activeStatus);

        mUsageId = usageId;
        mProvTag = provTag;
        mProvSystem = provSystem;
        mNotificationCategory = notcat;
        mReserveOnly = reserveOnly;
        mHighVolumeInterfaceLevel = highVolumeInterfaceLevel;
        /* ER 7 Compliant */
        mName = name;

        mDescription = desc;
        /* ER 7 Compliant */

    }

    /**
     * 
     * @param id
     * @param name
     * @param desc
     * @param pts
     * @param defaultServiceFlag
     * @param usageId
     * @param provTag
     * @param provSystem
     * @param notcat
     * @param paymentContent
     * @param reserveOnly
     */
    public CatalogService(String id, String name, String desc, PricePoints pts, boolean defaultServiceFlag, String usageId, String provTag, String provSystem, String notcat, PaymentContent paymentContent, boolean reserveOnly)
    {
        this(id, name, desc, pts, defaultServiceFlag);
        mUsageId = usageId;
        mProvTag = provTag;
        mProvSystem = provSystem;
        mNotificationCategory = notcat;
        mReserveOnly = reserveOnly;
        /* ER 7 Compliant */
        mName = name;

        mDescription = desc;
        /* ER 7 Compliant */

    }
    
    /**
     * 
     * @param key
     * @param id
     * @param name
     * @param desc
     * @param pts
     * @param defaultServiceFlag
     * @param usageId
     * @param provTag
     * @param provSystem
     * @param notcat
     * @param paymentContent
     * @param reserveOnly
     * @param createdBy
     * @param modifiedBy
     * @param modifiedDate
     * @param activeStatus
     */
    public CatalogService(Long key, String id, String name, String desc, PricePoints pts, boolean defaultServiceFlag, String usageId, String provTag, String provSystem, String notcat, PaymentContent paymentContent, boolean reserveOnly,
                          String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
    {
        this(key, id, name, desc, pts, defaultServiceFlag, createdBy, modifiedBy, modifiedDate, activeStatus);
        mUsageId = usageId;
        mProvTag = provTag;
        mProvSystem = provSystem;
        mNotificationCategory = notcat;
        mReserveOnly = reserveOnly;
        /* ER 7 Compliant */
        mName = name;

        mDescription = desc;
        /* ER 7 Compliant */

    }
    /**
     * 
     * @param id
     * @param name
     * @param desc
     * @param pts
     * @param defaultServiceFlag
     */
    public CatalogService(String id, String name, String desc, PricePoints pts, boolean defaultServiceFlag)
    {
        mId = id;
        setName(name);
        setDescription(desc);
        mIsDefaultService = defaultServiceFlag;
        mPricePoints = pts;
        /* ER 7 Compliant */
        mName = name;

        mDescription = desc;
        /* ER 7 Compliant */

    }
    /**
     * 
     * @param id
     * @param names
     * @param descs
     * @param pts
     * @param defaultServiceFlag
     */
    public CatalogService(String id, HashMap<String, String> names, HashMap<String, String> descs, PricePoints pts, boolean defaultServiceFlag)
    {
        mId = id;
        setNames(names);
        setDescriptions(descs);
        mIsDefaultService = defaultServiceFlag;
        mPricePoints = pts;
    }

    /**
     * 
     * 
     * @param key
     * @param id
     * @param name
     * @param desc
     * @param pts
     * @param defaultServiceFlag
     * @param createdBy
     * @param modifiedBy
     * @param modifiedDate
     * @param activeStatus
     */
    public CatalogService(Long key, String id, String name, String desc, PricePoints pts, boolean defaultServiceFlag,
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
        mIsDefaultService = defaultServiceFlag;
        mPricePoints = pts;
        /* ER 7 Compliant */
        mName = name;

        mDescription = desc;
        /* ER 7 Compliant */

    }
    /**
     * 
     * 
     * @param id
     * @param name
     * @param desc
     * @param pts
     */
     public CatalogService(String id, String name, String desc, PricePoints pts)
    {
        this(id, name, desc, pts, false);
        /* ER 7 Compliant */
        mName = name;

        mDescription = desc;
        /* ER 7 Compliant */

    }
     /**
      * 
      * 
      * @param id
      * @param names
      * @param descs
      * @param pts
      */
     public CatalogService(String id, HashMap<String, String> names, HashMap<String, String> descs, PricePoints pts)
     {
         this(id, names, descs, pts, false);
     }
     /**
      * 
      * 
      * @param key
      * @param id
      * @param name
      * @param desc
      * @param pts
      * @param createdBy
      * @param modifiedBy
      * @param modifiedDate
      * @param activeStatus
      */
	public CatalogService(Long key, String id, String name, String desc, PricePoints pts, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
    {
        this(key, id, name, desc, pts, false, createdBy, modifiedBy, modifiedDate, activeStatus);
        /* ER 7 Compliant */
        mName = name;

        mDescription = desc;
        /* ER 7 Compliant */

    }
	/**
	 * 
	 * 
	 * @param id - the service id
	 * @param name - the name of the service
	 * @param desc - a description
	 */
    public CatalogService(String id, String name, String desc)
    {
        this(id, name, desc, (PricePoints)null);
        /* ER 7 Compliant */
        mName = name;

        mDescription = desc;
        /* ER 7 Compliant */

    }
    /**
     * 
     * 
     * @param id
     * @param names
     * @param descs
     */
    public CatalogService(String id, HashMap<String, String> names, HashMap<String, String> descs)
    {
        this(id, names, descs, (PricePoints)null);
    }
    /**
     * 
     * 
     * @param key
     * @param id
     * @param name
     * @param desc
     * @param createdBy
     * @param modifiedBy
     * @param modifiedDate
     * @param activeStatus
     */
    public CatalogService(Long key, String id, String name, String desc, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
    {
        this(key, id, name, desc, (PricePoints)null, createdBy, modifiedBy, modifiedDate, activeStatus);
        /* ER 7 Compliant */
        mName = name;

        mDescription = desc;
        /* ER 7 Compliant */

    }
    /**
        Return the usage id of the service
    */
    public String getId()
    {
        return mId;
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

	/**
	 * Used to retrieve the description of why the service is non-refundable
	 * @return Non-Refundable-Description
	 * @since ER 5.1
	 */
	public String getNonRefundableDescription(){
		return this.mNonRefundableDescription;
	}


    /**
	 * Used to retrieve to assess if the service is refundable
	 * @return boolean
	 * @since ER 5.1
	 */
	public boolean isRefundable(){
		return this.mRefundable;
	}

	/**
	 * Used to assess if the call to findPackagesWithService(CatalogueService serv)should return or not all the CatalogueServices included into the found CataloguePackges
	 *
	 * @return boolean
	 *
	 */
	public boolean isReturnAllCatalogueServicesInfo(){
		return this.mReturnAllCatalogueServicesInfo;
	}

	public void returnAllCatalogueServicesInfo(){
		this.mReturnAllCatalogueServicesInfo = true;
	}

	public void doNotReturnAllCatalogueServicesInfo(){
		this.mReturnAllCatalogueServicesInfo = false;
	}

    /**
        Return the name of the service
    */
    public String getName()
    {
        return getName(Constants.DEFAULT_LANGUAGE_CODE);
    }

    /**
        Return the description of the service
    */
    public String getDescription()
    {
        return getDescription(Constants.DEFAULT_LANGUAGE_CODE);
    }

    public boolean isDefaultService()
    {
      //MQC7327 - was
      //return mIsDefaultService;
      return this.getName(Constants.DEFAULT_LANGUAGE_CODE).startsWith("default_");
    }

    public PricePoints getPricePoints()
    {
        return mPricePoints;
    }

/*
    public PricePoints getPricePoints(CatalogPackage pack)
    {
        return mPricePoints;
    }
*/
    public PricePoint getPricePoint()
    {
        return mPricePoint;
    }

    public void setPricePoint(PricePoint pt)
    {
        mPricePoint = pt;
    }

    public String getProvisioningTag()
    {
        return mProvTag;
    }

    public String getProvisioningSystem()
    {
        return mProvSystem;
    }

    public String getUsageId()
    {
        return mUsageId;
    }

    public String getUrl()
    {
        return mUrl;
    }

    /**
        Return the notification category for this service.
    */
    public String getNotificationCategory()
    {
        return mNotificationCategory;
    }

    /**
    * Return the payment content for this service.
    */
    public PaymentContent getPaymentContent()
    {
        return mPaymentContent;
    }

    public String getServiceCategory()
    {
        return mServiceCategory;
    }

    /**
    * Tells whether this service should be used with reserve-capture two phase semantics.
    */
    public boolean isReserveOnly() {
        return this.mReserveOnly;
    }


    /**
     * Sets a custom field for the service.
     * Custom fields appear in <custom_field> tags in the catalog XML.
     */
    public String getCustomField(String name) {
        return mCustomFields.get(name);
    }


    public Map<String, String> getCustomFields() {
        return mCustomFields;
    }

    /**
    * @depreccated
    */
    public String getDealName()
    {
        return mDealName;
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
    //ER 8 stubb
    /**
        Used to store special pricing information.
        A catalog field
    */
    public String getDistributionChannel()
    {
        return mDistributionChannel;
    }

    // ER 8 stubb ends


    /***
     *
     */
     public int getHighVolumeInterfaceLevel() {
        return this.mHighVolumeInterfaceLevel;
    }

    /***
     *
     */
    public void setHighVolumeInterfaceLevel(int highVolumeInterfaceLevelIn) {
        this.mHighVolumeInterfaceLevel= highVolumeInterfaceLevelIn;
    }

    /***
     *
     */
    public boolean isHighVolumeInterface() {
    return false;
    }
    public ServiceRevenueSharePartner[] getServiceRevenueSharePartners() { return (getServiceRevenueSharePartnersPurchaseCh()); }//[1] Mod Implementation

    /**
     * @author hud
     * Get number of revenue share partners
     * usually used if we want to know whether the service has PRS or how many
     * Using getServiceRevenueSharePartners().lemgth is too expensive and unnecessary
     */
    public int getServiceRevenueSharePartnerNum()
    {
    	int num = 0;

    	if (mRevSharePartnerPurchaseChMap != null) {
    		num = mRevSharePartnerPurchaseChMap.size();
    	}

    	return num;
    }

    //[1] Mod Start
    /** Added for ER8 Stubs */
    public ServiceRevenueSharePartner[] getServiceRevenueSharePartnersPurchaseCh() {
        return mRevSharePartnerPurchaseChMap.values().toArray(new ServiceRevenueSharePartner[0]);
    }

    /** Added for ER8 Stubs */
    public ServiceRevenueSharePartner getServiceRevenueSharePartnerPurchaseCh(String partnerId,String purchaseChannel) {
        if(partnerId==null) {
            return null;
        } else if(purchaseChannel==null) {
            purchaseChannel="*";
        }
        return mRevSharePartnerPurchaseChMap.get(partnerId+"|"+purchaseChannel);
    }
    public ServiceRevenueSharePartner [] getServiceRevenueSharePartners(String partnerId) {
        List<ServiceRevenueSharePartner> serviceRevSharePartners = new ArrayList<ServiceRevenueSharePartner>();
//        Iterator keys = mRevSharePartnerMap.keySet().iterator();
//        while (keys.hasNext()) {
//            String id = (String)keys.next();
        for(String id: mRevSharePartnerMap.keySet())	{
            if(id.indexOf(partnerId)>=0 && id.indexOf(partnerId)<id.indexOf("|")) { //If partner id exists
                serviceRevSharePartners.add(mRevSharePartnerMap.get(id));
            }
        }
		return serviceRevSharePartners.toArray(new ServiceRevenueSharePartner[0]); // Mod 11-11-05
    }

    //[1] Mod End

    public ServiceRevenueSharePartner getServiceRevenueSharePartner(String partnerChannelId) {
        //[1] Mod Start
        //return (ServiceRevenueSharePartner)mRevSharePartnerMap.get(partnerId);
        String partner;
        String channel;
        int sepPos = partnerChannelId.indexOf("|");
        if (sepPos == -1) {
            partner = partnerChannelId;
            channel = "*";
        } else {
            partner = partnerChannelId.substring(0, sepPos);
            channel = partnerChannelId.substring(sepPos + 1);
        }
        return getServiceRevenueSharePartnerPurchaseCh(partner, channel);
        //[1] Mod End
    }

	// Added for ER7.0 stubs - Added Vivek

    public ServiceRevenueSharePartner[] getServiceRevenueSharePartnersForPackage(String packageId) {
		HashMap<String, ServiceRevenueSharePartner> mServiceRevenueSharePartner = new HashMap<String, ServiceRevenueSharePartner>();
		if (mRevSharePartnerForPackageMap.get(packageId)!=null) {
			mServiceRevenueSharePartner = mRevSharePartnerForPackageMap.get(packageId);
		}
		return mServiceRevenueSharePartner.values().toArray(new ServiceRevenueSharePartner[mServiceRevenueSharePartner.size()]);
	}

	public ServiceRevenueSharePartner getServiceRevenueSharePartnerForPackage(String packageId, String partnerid) {
		//REMEDY 6293 - START
		HashMap<String, ServiceRevenueSharePartner> mServiceRevenueSharePartner = new HashMap<String, ServiceRevenueSharePartner>();
		
		if (mRevSharePartnerForPackageMap.get(packageId)!=null) { 
			mServiceRevenueSharePartner = mRevSharePartnerForPackageMap.get(packageId);
		}
		return mServiceRevenueSharePartner.get(partnerid);
		//REMEDY 6293 - END
	}
	// Added for ER7.0 stubs - Added Vivek

    /* Added by Periasamy on 01/07/2003  for Partner Revenue Share Phase 2  */


    /**
     * getter method for indirect value
     * @return indirect value
     */
    public String getIndirectValue() { return mIndirectValue; }

    /**
     * Getter method for indirect value format
     * @return indirect value format
     */
    public String getIndirectValueFormat() { return mIndirectValueFormat; }

    /**
     * getter method for promo value
     * @return promo value
     */
    public String getPromoValue() { return mPromoValue; }

    /**
     * getter method for promo value format
     * @return promo value format
     */
    public String getPromoValueFormat() { return mPromoValueFormat; }

    /**
     * Getter method for content category
     * @return content category
     */
    public String getContentCategory() { return mContentCategory; }

    /**
     * Getter method for content sub category
     * @return content sub category
     */
    public String getContentSubCategory() { return mContentSubCategory; }

    /**
     * getter method for content item
     * @return content item
     */
    public String getContentItem() { return mContentItem; }

    /**
     * getter method for delivey mechanism
     * @return delivey mechanism
     */
    public String getDeliveryMechanism() { return mDeliveryMechanism; }

    /**
     * Getter method for product category
     * @return product category
     */
    public String getProductCategory() { return mProductCategory; }

    /**
     * Setter method for product category
     */
    public void setProductCategory(String productCategory) 
    { 
    	this.mProductCategory=productCategory; 
    }

    /**
     * Getter method for product subcatgory 1
     * @return product subcatgory 1
     */
    public String getProductSubCategory1() { return mProductSubCategory1; }
    
    /**
     * Setter method for product sub category
     */
    public void setProductSubCategory1(String productSubCategory1) 
    { 
    	this.mProductSubCategory1=productSubCategory1; 
    }
    
    /**
     * Getter method for product subcatgory 2
     * @return product subcatgory 2
     */
    public String getProductSubCategory2() { return mProductSubCategory2; }
    
    // CR0586 KPI Reporting Fields
    /**
     * Getter method for product Wholesale Price
     * @return product Wholesale Price
     */
    // CR0586 KPI Reporting Fields
    public String getProductWholesalePrice() { return mProductWholesalePrice; }

    /**
     * Getter method for product Self Regulation
     * @return product Self Regulation
     */
    public String getProductSelfRegulation() { return mProductSelfRegulation; }
    // CR0586 KPI end
    /**
        Returns the type of service.  A normal ER service has a blank service type.
        @return the type of service as a string identifier.
    */
    public String getServiceType()
    {
        return mServiceType;
    }

    /**
        Checks whether the service is a volume service. A volume service has a type of
        VOLUME_TYPE. A volume service has a scaled quantity amount with the fixed amount and when
        it is used, a usage quantity is passed.
        @return true if volume service
    */
    public boolean isVolumeService()
    {
        boolean rv = false;
        if (mServiceType!=null && mServiceType.toLowerCase().equals(VOLUME_TYPE)) {
            rv = true;
        }
        return rv;
    }

    public String getTaxCode()
    {
        return mTaxCode;
    }
    public Long getProductFk()
    {
        return mProductFk;
    }

    /* Added for ER7 stubs */
	public boolean isServiceShareOverride()
	{
		return mServiceShareOverride;
	}

	/* Added for ER7 stubs */
	public boolean isExpiredPackageService()
	{
		return mExpiredPackageService;
	}

	/* Added for ER7 stubs */
	public double getFixedUsageAmount()
	{
		return mFixedUsageAmount;
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

                /* ER 7 Compliant */
                mName = getName();
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
            //ER 7 compliant
            mName = name;

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
        //ER 7 compliant
        mDescription = description;

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
        //ER 7 compliant
        mDescription = description;

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
        //ER7 Compliant
        mPricingText1 = text;

    	setPricingText1(Constants.DEFAULT_LANGUAGE_CODE, text);
    }

    public void setPricingText2(String text)
    {
        //ER7 Compliant
        mPricingText2 = text;

    	setPricingText2(Constants.DEFAULT_LANGUAGE_CODE, text);
    }

    public void setPricingText1(String language, String text){
        //ER7 Compliant
        mPricingText1 = text;

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
        //ER7 Compliant
        mPricingText2 = text;

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


    //@kamatha STKHREQ13076
    public void setOnRoamingDisabled(boolean b) {
    	mOnRoamingDisabled = b;
    }
    public boolean getOnRoamingDisabled() {
    	return mOnRoamingDisabled;
    }

    public void setOffRoamingDisabled(boolean b) {
    	mOffRoamingDisabled = b;
    }
    public boolean getOffRoamingDisabled() {
    	return mOffRoamingDisabled;
    }
    // Add since ER9 - END

    //REMEDY 6689
    public boolean getHasExpress()
    {
    	return mHasExpress;
    }

    public boolean getHasDynamicDefault()
    {
    	return mHasDynamic;
    }

    public boolean getHasSuperPackage()
    {
    	return mHasSuperPackage;
    }
    
    //CR 1174 return trial options only
    public boolean isReturnTrialOptionsOnly()
    {
    	return mReturnTrialOptionsOnly;
    }
    
    //CR 1212 - add sales model
    public String getSalesModel()
    {
    	if (mSalesModel == null || mSalesModel.equals("")) {
    		return Constants.RESELLER_SALES_MODEL;
    	}
    	else {
    		return mSalesModel;
    	}
    }

    //CR 1209 - add service class
    public String getServiceClass()
    {	
    	return mServiceClass;
    }
    
	// CR Off portal
	public Map<String, BandRevenueShare> getBandRevenueShares() {
		return mBandRevenueShares;
	}

    /**
     * @return the mReIssuePermittedFlag
     */
    public boolean isReIssuePermittedFlag() {
        return mReIssuePermittedFlag;
    }

    /**
     * @param reIssuePermittedFlag the mReIssuePermittedFlag to set
     */
    public void setReIssuePermittedFlag(boolean reIssuePermittedFlag) {
        mReIssuePermittedFlag = reIssuePermittedFlag;
    }

	//CR1209AR VFES defrag
    public boolean isProvisionOnUsage()
    {
    	return mProvisionOnUsage;
    }
    
   //CR1209AR VFES defrag
    public void setProvisionOnUsage(boolean provisionOnUsage){
    	mProvisionOnUsage = provisionOnUsage;
    }
    
    //CR 1542 - add service chargeable by
    public String getChargeableBy()
    {	
    	return mChargeableBy;
    }
    
    //CR 1542 - The packageids the service belongs to
    public String[] getPackageIds() {
    	if (mPackageIds != null && mPackageIds.size() > 0) {
    		return mPackageIds.toArray((new String[mPackageIds.size()]));
    	} else {
    		return null;
    	}
    }
    
    //CR 1542 - Service is a micro service
    public boolean isMicroService() {
    	return mMicroService;
    }
    
    //MQC 6067 - The super packageids the service belongs to
    public String[] getSuperPackageIds() {
    	if (mSuperPackageIds != null && mSuperPackageIds.size() > 0) {
    		return mSuperPackageIds.toArray((new String[]{}));
    	} else {
    		return null;
    	}
    }

	// CR1503 - Multi Price plans - phase 2
    /**
     * This method resets the package id list.
     */
    public void resetPackageIds() {
		if (mPackageIds!=null)
			mPackageIds.clear();
		else
			mPackageIds = new ArrayList<String>();
    }

    /**
	 * @return the mExternalServPricePlan
	 */
	public String getmExternalServPricePlan() {
		return mExternalServPricePlan;
	}

	/**
	 * @param mExternalServPricePlan the mExternalServPricePlan to set
	 */
	public void setmExternalServPricePlan(String mExternalServPricePlan) {
		this.mExternalServPricePlan = mExternalServPricePlan;
	}
	// CR1503 - phase 2 ends

	//CR1503aL. START required for merge versions functionality
	public void setPricePoints(PricePoints ps){
		mPricePoints = ps;
	}
	/**
	 * @param mDistributionChannel the mDistributionChannel to set
	 */
	public void setDistributionChannel(String mDistributionChannel) {
		this.mDistributionChannel = mDistributionChannel;
	}
	/**
	 * @param mNonRefundableDescription the mNonRefundableDescription to set
	 */
	public void setNonRefundableDescription(String mNonRefundableDescription) {
		this.mNonRefundableDescription = mNonRefundableDescription;
	}
	/**
	 * @return the mRefundable
	 */
	public boolean ismRefundable() {
		return mRefundable;
	}
	/**
	 * @param mRefundable the mRefundable to set
	 */
	public void setRefundable(boolean mRefundable) {
		this.mRefundable = mRefundable;
	}
	/**
	 * @return the mReturnTrialOptionsOnly
	 */
	public boolean ismReturnTrialOptionsOnly() {
		return mReturnTrialOptionsOnly;
	}
	/**
	 * @param mReturnTrialOptionsOnly the mReturnTrialOptionsOnly to set
	 */
	public void setReturnTrialOptionsOnly(boolean mReturnTrialOptionsOnly) {
		this.mReturnTrialOptionsOnly = mReturnTrialOptionsOnly;
	}
	/**
	 * @param mSalesModel the mSalesModel to set
	 */
	public void setSalesModel(String mSalesModel) {
		this.mSalesModel = mSalesModel;
	}
	//CR1503aL. END

	// CR2210 - MPay Rate Card
	/**
	 * @return the mUseRateCard
	 */
	public boolean isUseRateCard() {
		return mUseRateCard;
	}
	/**
	 * @param mUseRateCard the mUseRateCard to set
	 */
	public void setUseRateCard(boolean useRateCard) {
		this.mUseRateCard = useRateCard;
	}
	/**
	 * @return the mInternalPartner
	 */
	public Partner getInternalPartner() {
		return mInternalPartner;
	}
	/**
	 * @param mInternalPartner the mInternalPartner to set
	 */
	public void setInternalPartner(Partner internalPartner) {
		this.mInternalPartner = internalPartner;
	}
	/**
	 * @return the mRateCardPartners
	 */
	public RateCardPartners getRateCardPartners() {
		return mRateCardPartners;
	}
	/**
	 * @param mRateCardPartners the mRateCardPartners to set
	 */
	public void setRateCardPartners(RateCardPartners rateCardPartners) {
		this.mRateCardPartners = rateCardPartners;
	}
	// CR2210 - Ends

	/**
	 * CR2256
	 * @return the mUsageAllowedBeingProvisionedSub
	 */
	public boolean isUsageAllowedBeingProvisionedSub() {
		return mUsageAllowedBeingProvisionedSub;
	}

	/**
	 * CR2165 Partner Notification Handler
	 * @return the mGlobalHandler
	 */
	public boolean isGlobalHandler() {
		return mGlobalHandler;
	}

	/**
	 * a friendly string for customer care<br/>
	 * eg "s001 (Test Service)"
	 * @return
	 */
	public String getDisplayName()	{
		StringBuffer sb = new StringBuffer(getId());
		sb.append(" (");
		sb.append(getName());
		sb.append(")");
		return sb.toString();
	}
	
	   /**
     * add a packageId which represents a package containing this service
     * @since CR 1542, moved up from CatalogServiceImpl for MQC8867
     * 
     * @param packageId
     */
    public void setPackageId(String packageId) {
    	if (mPackageIds == null) {
    		mPackageIds = new ArrayList<String>();
    	}
    	if (!mPackageIds.contains(packageId)) {
    		mPackageIds.add(packageId);
    	}
    }
    
	/**
	 * MQC8858 Global Handler Notificaiton Service
	 * @return the mGlobalHandlerNotification
	 */
	public boolean isGlobalHandlerNotification() {
		return mGlobalHandlerNotification;
	}
    
}




