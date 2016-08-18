package com.vizzavi.ecommerce.business.catalog.internal;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.vizzavi.ecommerce.business.catalog.BandRevenueShare;
import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.business.catalog.PaymentContent;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.catalog.PricePoints;
import com.vizzavi.ecommerce.business.catalog.ServiceRevenueSharePartner;
import com.vizzavi.ecommerce.business.common.Constants;

public class CatalogServiceImpl extends CatalogService{

   private    static final long serialVersionUID = -8203088561335532278L;

    protected Map<String, String> mPricingModels = new HashMap<String, String>();

    public CatalogServiceImpl(CatalogService serv)
    {
        super(serv);
        mPricePoints = new PricePointsImpl(serv.getPricePoints());
        if (serv instanceof CatalogServiceImpl) {
            CatalogServiceImpl impl = (CatalogServiceImpl)serv;
            this.mRevSharePartnerMap = new HashMap<String, ServiceRevenueSharePartner>(impl.mRevSharePartnerMap);
            //[1] Mod Start
            this.mRevSharePartnerPurchaseChMap = new HashMap<String, ServiceRevenueSharePartner>(impl.mRevSharePartnerPurchaseChMap);
            //[1] Mod End
            this.mIndirectValue = impl.mIndirectValue;
            this.mIndirectValueFormat = impl.mIndirectValueFormat;
            this.mPromoValue = impl.mPromoValue;
            this.mPromoValueFormat = impl.mPromoValueFormat;
            this.mContentCategory = impl.mContentCategory;
            this.mContentSubCategory = impl.mContentSubCategory;
            this.mContentItem = impl.mContentItem;
            this.mDeliveryMechanism = impl.mDeliveryMechanism;
            this.mProductCategory = impl.mProductCategory;
            this.mProductSubCategory1 = impl.mProductSubCategory1;
            this.mProductSubCategory2 = impl.mProductSubCategory2;
            this.mPricingModels = new HashMap<String, String>(mPricingModels);
            // CR0586 KPI Reporting Fields
            this.mProductWholesalePrice = impl.mProductWholesalePrice;
            this.mProductSelfRegulation = impl.mProductSelfRegulation;
            // CR0586 end
            this.mServiceShareOverride = impl.mServiceShareOverride;
			this.mExpiredPackageService = impl.mExpiredPackageService;
			this.mFixedUsageAmount = impl.mFixedUsageAmount;
            this.mRevSharePartnerForPackageMap = impl.mRevSharePartnerForPackageMap;
            
            this.mOnRoamingDisabled = impl.mOnRoamingDisabled;
            this.mOffRoamingDisabled = impl.mOffRoamingDisabled;
            //added vivek for ER7.0
            if (serv.getPaymentContent()!=null) {
                this.mPaymentContent = new PaymentContent(serv.getPaymentContent());
            }

            /* ER 7 Compliant */
            mName = impl.mName;
            mDescription = impl.mDescription;
            mPricingText1 = impl.mPricingText1;
            mPricingText2 = impl.mPricingText2;
            /* ER 7 Compliant */

            // CR Off portal
            this.mBandRevenueShares = impl.mBandRevenueShares;

            // CR1503 - Multi Price plans - phase 2
            mExternalServPricePlan = impl.mExternalServPricePlan;

    		// CR2210 - MPay Rate Card
    		mUseRateCard		= impl.mUseRateCard;
    		mInternalPartner	= impl.mInternalPartner;
    		mRateCardPartners	= impl.mRateCardPartners;
    		// CR2210 - Ends
    		
    		// CR2256
    		mUsageAllowedBeingProvisionedSub = impl.mUsageAllowedBeingProvisionedSub;

    		// CR2165 Partner Notification Handler
    		this.mGlobalHandler	= impl.mGlobalHandler;
    		
    		//MQC8858 Global Handler Notification Service
    		this.mGlobalHandlerNotification	= impl.mGlobalHandlerNotification;
        }
    }

    public CatalogServiceImpl(String id, String name, String desc, PricePoints pts,
    boolean defaultServiceFlag, String usageId, String provTag, String provSystem, String
    notcat, PaymentContent paymentContent, boolean res)
    {
        super(id, name, desc, pts, defaultServiceFlag, usageId, provTag, provSystem,
        notcat, paymentContent, res);
        if (pts == null) {
            setPricePoints(new PricePointsImpl());
        }
    }

    public CatalogServiceImpl(Long key, String id, String name, String desc, PricePoints pts,
    boolean defaultServiceFlag, String usageId, String provTag, String provSystem, String notcat,
    PaymentContent paymentContent, boolean res, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
    {
        super(key, id, name, desc, pts, defaultServiceFlag, usageId, provTag, provSystem,
        notcat, paymentContent, res, createdBy, modifiedBy, modifiedDate, activeStatus);
        if (pts == null) {
            setPricePoints(new PricePointsImpl());
        }
    }
     public CatalogServiceImpl(String id, String name, String desc){
      super(id,name,desc);
     }
     
     public CatalogServiceImpl(String id, HashMap<String, String> names, HashMap<String, String> descs){
         super(id,names,descs);
     }     

     public CatalogServiceImpl(Long key, String id, String name, String desc, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus){
      super(key,id,name,desc,createdBy, modifiedBy, modifiedDate, activeStatus);
     }

    public void setId(String id){
       mId = id;
    }

    public void setKey(Long key){
       mKey = key;
    }

    public void setCreatedBy(String createdBy) {
        mCreatedBy = createdBy;
    }

    public void setModifiedBy(String modifiedBy) {
        mModifiedBy = modifiedBy;
    }

    public void setModifiedDate(Date modifiedDate) {
        mModifiedDate = modifiedDate;
    }

    public void setActiveStatus(char activeStatus) {
    	mActiveStatus = activeStatus;
    }

    public void setProductFk(Long productFk)
    {
        mProductFk = productFk;
    }

    //MQC7327 - removed
    /*
    public void setDefaultService(boolean isDefaultService)
    {
        mIsDefaultService = isDefaultService;
    }
    */

    @Override
	public PricePoints getPricePoints()
    {
        if (mPricePoints==null) {
            mPricePoints = new PricePointsImpl();
        }
        return mPricePoints;
    }

    @Override
	public PricePoint getPricePoint()
    {
        return mPricePoint;
    }

    @Override
	public void setPricePoint(PricePoint pt)
    {
        mPricePoint = pt;
    }

    public void setProvisioningTag(String provTag)
    {
        mProvTag = provTag;
    }

    public void setProvisioningSystem(String provSystem)
    {
        mProvSystem = provSystem;
    }

    public void setUsageId(String usageId){
        mUsageId = usageId;
    }


    public void setNotificationCategory(String notificationCategory)
    {
        mNotificationCategory = notificationCategory;
    }

    /**
    * set the payment content for this service.
    */
    public void setPaymentContent(PaymentContent paymentContent)
    {
        mPaymentContent = paymentContent;
    }

    public void setUrl(String val)
    {
        mUrl = val;
    }

    public void setServiceCategory(String val)
    {
        mServiceCategory = val;
    }



    @Override
	public void setPricePoints(PricePoints pricePoints) {

        mPricePoints = pricePoints;
    }

    public void setDealName(String dealName) {
        mDealName = dealName; 
    }

    public void addPricePoint(PricePoint pt) { 
    
        ((PricePointsImpl)mPricePoints).addPricePoint(pt);
    }


    /**
     * @deprecated
     * REMEDY 7257
     * This is an old method and is being deprecated.
     * Please use addServiceRevenueSharePartnerPurchaseCh instead
     */
    @Deprecated
	public void addServiceRevenueSharePartner(ServiceRevenueSharePartner
    revenueSharePartner) {

        mRevSharePartnerMap.put(revenueSharePartner.getId(), revenueSharePartner);
    }

    //[1] Mod Start
    /** Added for ER8 Stubs */
    public void addServiceRevenueSharePartnerPurchaseCh(ServiceRevenueSharePartner revenueSharePartner) {
        String purchaseChannel = revenueSharePartner.getPurchaseChannel();
        if(revenueSharePartner.getId()==null){
            return;
        } else if(purchaseChannel==null) {
            purchaseChannel = "*";
        }
        mRevSharePartnerPurchaseChMap.put(revenueSharePartner.getId()+"|"+purchaseChannel, revenueSharePartner);
    }

    /** Added for ER8 Stubs */
    public void removeServiceRevenueSharePartnerPurchaseCh(String id, String purchaseChannel) {
        if(id==null){
            return;
        } else if(purchaseChannel==null) {
            purchaseChannel = "*";
        }
        mRevSharePartnerPurchaseChMap.remove(id+"|"+purchaseChannel);
    }
    //[1] Mod End

    /**
     * @deprecated
     * REMEDY 7257
     * This is an old method and is being deprecated
     * Please use removeServiceRevenueSharePartnerPurchaseCh instead
     */
    @Deprecated
	public void removeServiceRevenueSharePartner(String id) {

        mRevSharePartnerMap.remove(id);
    }

    // Added for ER7.0 stubs -- Added vivek
    public void addServiceRevenueSharePartnerForPackage(String packageId, ServiceRevenueSharePartner
	revenueSharePartner) {
		HashMap<String, ServiceRevenueSharePartner> mRevenueSharePartner = null;
		if (mRevSharePartnerForPackageMap.get(packageId) != null) {
			mRevenueSharePartner = mRevSharePartnerForPackageMap.get(packageId);
			mRevenueSharePartner.put(revenueSharePartner.getId(), revenueSharePartner);
		} else {
			mRevenueSharePartner = new HashMap<String, ServiceRevenueSharePartner>();
			mRevenueSharePartner.put(revenueSharePartner.getId(), revenueSharePartner);
			mRevSharePartnerForPackageMap.put(packageId, mRevenueSharePartner);
		}
	}

	public void removeServiceRevenueSharePartnerForPackage(String packageId, String partnerid) {
		if (mRevSharePartnerForPackageMap.get(packageId) != null) {
			HashMap<String, ServiceRevenueSharePartner> mRevenueSharePartner = mRevSharePartnerForPackageMap.get(packageId);
			mRevenueSharePartner.remove(partnerid);
		}
	}
    // Added for ER7.0 stubs -- Added vivek



    public ServiceRevenueSharePartner getPriorityServiceRevenueSharePartner()
    {
        ServiceRevenueSharePartner rv = null;
        if (mRevSharePartnerMap!=null && mRevSharePartnerMap.size() >0) {
            Collection<ServiceRevenueSharePartner> values = mRevSharePartnerMap.values();

            Iterator<ServiceRevenueSharePartner> iter = values.iterator();

            while (iter.hasNext()) {
                ServiceRevenueSharePartner pnr = iter.next();

                if (pnr.isPriorityPartner()) {
                    rv = pnr;
                    break;
                }
            }
        }

        return rv;
    }

    public void setIndirectValue(String indirectValue) { mIndirectValue = indirectValue; }


    public void setIndirectValueFormat(String indirectValueFormat) { mIndirectValueFormat
    = indirectValueFormat; }


    public void setPromoValue(String promoValue) { mPromoValue = promoValue; }


    public void setPromoValueFormat(String promoValueFormat) { mPromoValueFormat =
    promoValueFormat; }


    public void setContentCategory(String contentCategory) { mContentCategory =
    contentCategory; }


    public void setContentSubCategory(String contentSubCategory) { mContentSubCategory =
    contentSubCategory; }


    public void setContentItem(String contentItem) { mContentItem = contentItem; }


    public void setDeliveryMechanism(String deliveryMechanism) { mDeliveryMechanism =
    deliveryMechanism; }


    @Override
	public void setProductCategory(String productCategory) { mProductCategory =
    productCategory; }


    @Override
	public void setProductSubCategory1(String productSubCategory1) { mProductSubCategory1
    = productSubCategory1; }


    public void setProductSubCategory2(String productSubCategory2) { mProductSubCategory2
    = productSubCategory2; }

    // CR0586 KPI Reporting Fields
    public void setProductWholesalePrice(String productWholesalePrice){ mProductWholesalePrice
    = productWholesalePrice;
    }
    public void setProductSelfRegulation(String productSelfRegulation){ mProductSelfRegulation
    = productSelfRegulation;
    }
    // CR0586 end
    public void setReserveOnly(boolean reserveOnly) { mReserveOnly = reserveOnly; }

    /**
     * Sets a custom field for the service.
     * Custom fields appear in <custom_field> tags in the catalog XML.
     */
    public void setCustomField(String name, String value) {
        mCustomFields.put(name, value);
    }


    @Override
	public void setHighVolumeInterfaceLevel(int highVolumeInterfaceLevelIn) {
        this.mHighVolumeInterfaceLevel= highVolumeInterfaceLevelIn;
    }

    public void setServiceType(String val)
    {
        mServiceType = val;
    }

    public String[] getPricingModels()
    {
        return mPricingModels.keySet().toArray(new String [0]);
    };

    public boolean containsPricingModel(String id) {
        if (mPricingModels != null && id != null) return mPricingModels.containsKey(id);
        return false;
    };

    public void addPricingModel(String val)
    {
        if (! mPricingModels.containsKey(val))
        mPricingModels.put(val,null);
    }

    public void removePricingModel(String val)
    {
       mPricingModels.remove(val);
    };


    public void setPricingModel(String val)
    {
        mPricingModels.put(val,val);
    }

    /**
     * @param string
     */
    public void setTaxCode(String string) {
        mTaxCode = string;
    }

    /**
    * @param refundable
    */
    public void setNonRefundable(boolean bool){
        mRefundable = !bool;
    }

    /**
    * @param nonRefundableDescription
    */
    @Override
	public void setNonRefundableDescription(String nonRefundableDescription){
        mNonRefundableDescription = nonRefundableDescription;
    }

    /* Added for ER7 stubs */
	public void setServiceShareOverride(boolean serviceShareOverride) 
	{ 
		mServiceShareOverride = serviceShareOverride; 
	}

	/* Added for ER7 stubs */
	public void setExpiredPackageService(boolean expiredPackageService) 
	{ 
		mExpiredPackageService = expiredPackageService; 
	}

	/* Added for ER7 stubs */
	public void setFixedUsageAmount(double fixedUsageAmount) 
	{ 
		mFixedUsageAmount = fixedUsageAmount; 
	}
	

    /* Added for ER 8. 08092005 DiasC */
    @Override
	public void setDistributionChannel(String distChannel)
    {
        mDistributionChannel = distChannel;
    }

    //REMEDY 6689
    public void setHasExpress(boolean hasExpress)
    {
    	mHasExpress = hasExpress;
    }

    public void setHasDynamic(boolean hasDynamic)
    {
    	mHasDynamic = hasDynamic;
    }

    public void setHasSuperPackage(boolean hasSuperPackage)
    {
    	mHasSuperPackage = hasSuperPackage;
    }
    
    //END REMEDY 6689
    
    //CR 1174 return trial options only
    @Override
	public void setReturnTrialOptionsOnly(boolean returnTrialOptionsOnly)
    {
    	mReturnTrialOptionsOnly = returnTrialOptionsOnly;
    }
    
    //CR 1212 - add sales model
    @Override
	public void setSalesModel(String salesModel) {
    	if (salesModel == null || salesModel.equals("")) {
    		mSalesModel = Constants.RESELLER_SALES_MODEL;
    	}
    	else {
    		mSalesModel = salesModel;
    	}
    }

    //CR 1209 add service class
    public void setServiceClass(String serviceClass) {
    	mServiceClass = serviceClass;
    }

    //CR Double charging
    @Override
	public void setReIssuePermittedFlag(boolean flag) {
        mReIssuePermittedFlag = flag;
    }

    
	//CR Off portal
    public void setBandRevenueShare(Map<String, BandRevenueShare> pBandRevenueShares)
    {
    	mBandRevenueShares = pBandRevenueShares;
    }
    
    //CR 1542 - add service chargeable by
    public void setChargeableBy(String chargeableBy) {
    	mChargeableBy = chargeableBy;
    }
    
 
    
    //CR 1542 - Service is a micro service
    public void setMicroService(boolean microService) {
    	mMicroService = microService;
    }
    
    //MQC 6067 - The super packageids the service belongs to
    public void setSuperPackageId(String packageId) {
    	if (mSuperPackageIds == null) {
    		mSuperPackageIds = new ArrayList<String>();
    	}
    	if (!mSuperPackageIds.contains(packageId)) {
    		mSuperPackageIds.add(packageId);
    	}
    }
    
   /**
    * CR2256
    * @param flag
    */
    public void setUsageAllowedBeingProvisionedSub(boolean flag) {
    	mUsageAllowedBeingProvisionedSub = flag;
	}

    /**
     * CR2165 Partner Notification Handler
     * @param flag
     */
	public void setGlobalHandler(boolean flag) {
		this.mGlobalHandler	= flag;
	}
    
    /**
     * MQC8858 Global Handler Notification Service
     * @param flag
     */
	public void setGlobalHandlerNotification(boolean flag) {
		this.mGlobalHandlerNotification	= flag;
	}
	
}




