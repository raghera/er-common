package com.vizzavi.ecommerce.business.catalog.internal;


import java.util.Date;
import java.util.HashMap;

import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.business.catalog.PaymentContent;
import com.vizzavi.ecommerce.business.catalog.PricePoints;
import com.vizzavi.ecommerce.business.catalog.ServiceRevenueSharePartner;

public class CatalogServiceImpl extends CatalogService{

   private    static final long serialVersionUID = -8203088561335532278L;

    public CatalogServiceImpl(CatalogService serv)
    {
        super(serv);
        mPricePoints = new PricePointsImpl(serv.getPricePoints());
        if (serv instanceof CatalogServiceImpl) {
            CatalogServiceImpl impl = (CatalogServiceImpl)serv;
            
            //PPM136861 refactoring aL. START
			//not used
            //this.mRevSharePartnerMap = new HashMap<String, ServiceRevenueSharePartner>(impl.mRevSharePartnerMap);
            this.mRevSharePartnerPurchaseChMap = new HashMap<String, ServiceRevenueSharePartner>(impl.mRevSharePartnerPurchaseChMap);
            this.mRevSharePartnerForPackageMap = impl.mRevSharePartnerForPackageMap;
            //PPM136861 refactoring aL. END

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
            //PPM136861 refactoring aL. START removed
//            this.mPricingModels = new HashMap<String, String>(mPricingModels);
            // CR0586 KPI Reporting Fields
            this.mProductWholesalePrice = impl.mProductWholesalePrice;
            this.mProductSelfRegulation = impl.mProductSelfRegulation;
            // CR0586 end
            this.mServiceShareOverride = impl.mServiceShareOverride;
			this.mExpiredPackageService = impl.mExpiredPackageService;
			this.mFixedUsageAmount = impl.mFixedUsageAmount;
            
//            this.mOnRoamingDisabled = impl.mOnRoamingDisabled;
//            this.mOffRoamingDisabled = impl.mOffRoamingDisabled;
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
    		this.mGlobalHandlerProvision	= impl.mGlobalHandlerProvision;
    		
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
    
    
    //END REMEDY 6689

	
}




