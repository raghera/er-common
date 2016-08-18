package com.vizzavi.ecommerce.business.common;

import java.util.Calendar;

import com.vizzavi.ecommerce.business.catalog.NetworkCode;
import com.vizzavi.ecommerce.common.RatingUtils;
import com.vizzavi.ecommerce.resources.PropertyDataBool;
import com.vizzavi.ecommerce.resources.PropertyDataInt;


/**
 * 
 * If the user doesn't supply a user group, the user group is set to not set
 *
 */
public class RatingAttributes extends BaseAttributes
{
	private    static final long serialVersionUID = -4970057450995570398L;
	
	
	// Each property requires
	// 1. pre-loaded from er2.properties (static)
	// 2. Attribute to be actually used (instance)
	// 3. Pre-defined constants if any
	// 4. getter and setter
	// 5. normalizer
	//--------------------------------------------------------------------------
	//@hud constants for multiple usage
	/**Don't use!  The value of com.vizzavi.ecommerce.business.charging.PATIAL_WHOLE should be retrieved directly from ConfigProvider*/
	protected PropertyDataInt mMultiUsageMode = new PropertyDataInt();		// meaning use the default value on server side, i.e., CFG_MULTIUSAGE_MODE
	/**
	 * Don't use!  The value of com.vizzavi.ecommerce.business.charging.PATIAL_WHOLE should be retrieved directly from ConfigProvider
	 * @deprecated this won't work with multi-tenancy.  
	 * @return
	 */
	@Deprecated
	public int getMultiUsageMode()	{
		//REMEDY 6363
           if(mMultiUsageMode != null && (mMultiUsageMode.getVal() > -1))
           { 
		return mMultiUsageMode.getVal();
           }
           else
           {
               return ErCoreConst.MUM_ONLY_WHOLE;
           }
	}
	/**
	 * Don't use!  The value of com.vizzavi.ecommerce.business.charging.PATIAL_WHOLE should be retrieved directly from ConfigProvider
	 * @deprecated this won't work with multi-tenancy.  
	 * @return
	 */
	@Deprecated
	public void setMultiUsageMode(int mum)	{
                if(mMultiUsageMode != null)
		mMultiUsageMode.setVal(mum);
	}


	private PropertyDataInt mNetworkCodeMatchMethod = new PropertyDataInt();
	/**
	 * Don't use!  The value of NETWORK_CODE_MATCH_METHOD should be retrieved directly from ConfigProvider
	 * @deprecated this won't work with multi-tenancy.  
	 * @return
	 */
	@Deprecated
	public int getNetworkCodeMatchMethod()	{
            if(mNetworkCodeMatchMethod != null)
            {
		return mNetworkCodeMatchMethod.getVal();
            }
            else
            {
                return ErCoreConst.NCMATCH_WILDCARD;
            }
	}
	/**
	 * Don't use!  The value of NETWORK_CODE_MATCH_METHOD should be retrieved directly from ConfigProvider
	 * @deprecated this won't work with multi-tenancy.  
	 * @return
	 */
	@Deprecated
	public void setNetworkCodeMatchMethod(int matchMethod)	{
                if(mNetworkCodeMatchMethod != null)
		    mNetworkCodeMatchMethod.setVal(matchMethod);
	}

	

	private PropertyDataBool mRefuseUsageWhenProvisioning = new PropertyDataBool();
	
	/**
	 * Don't use!  The value of REFUSE_USAGE_WHEN_PROVISIONING should be retrieved directly from ConfigProvider
	 * @deprecated this won't work with multi-tenancy.  
	 * @return
	 */
	@Deprecated
	public boolean needRefuseUsageWhenProvisioning() {
                boolean bRefuseUsageWhenProvisioning = false;

                if(mRefuseUsageWhenProvisioning != null)
                {
		    bRefuseUsageWhenProvisioning = mRefuseUsageWhenProvisioning.isTrue();
                }

                return bRefuseUsageWhenProvisioning;
	}
	
	/**
	 * Don't use!  The value of REFUSE_USAGE_WHEN_PROVISIONING should be retrieved directly from ConfigProvider
	 * @deprecated this won't work with multi-tenancy.  
	 */
	@Deprecated
	public void setRefuseUsageWhenProvisioning(boolean bOn) {

                if(mRefuseUsageWhenProvisioning != null)
		    mRefuseUsageWhenProvisioning.setVal(bOn);
	}
	
	
	
	// general normalization method
	@Override
	public void normalize()
	{
		super.normalize();
		
		if(mMultiUsageMode != null)mMultiUsageMode.normalize(ErCoreConst.CFG_MULTIUSAGE_MODE());
		//if(mAccessControlSwitch != null)mAccessControlSwitch.normalize(ErCoreConst.CFG_ACCESS_CONTROL_SWITCH());
		if(mNetworkCodeMatchMethod !=null)mNetworkCodeMatchMethod.normalize(ErCoreConst.CFG_NETWORK_CODE_MATCH_METHOD());
		if(mRefuseUsageWhenProvisioning!= null)mRefuseUsageWhenProvisioning.normalize(ErCoreConst.CFG_REFUSE_USAGE_WHEN_PROVISIONING());
		
	}

    protected double mNextCycleDisount = 0;

	
	boolean interactiveFlag = true;
	
	protected int    mChannel = Constants.INT_NOT_SET;
	protected int    mPaymentType = Constants.INT_MATCH_ALL;
	protected int    mDeviceType = Constants.INT_MATCH_ALL;
	protected int    mPremiumLevel = Constants.INT_NOT_SET;
	protected String    mSupplierId = Constants.STRING_NOT_SET;
	protected String[]  mUserGroups = new String[] {Constants.STRING_NOT_SET};
	protected String[]  mPromoCodes = new String[] {Constants.STRING_NOT_SET};
	
	
	protected String	mPromoPrecode = null;
	protected String	mPromoUniqueCode = null;	// this is the full code
	
	
	//Remedy 1702 - re-introduced set user group
	protected String mUserGroup = Constants.STRING_NOT_SET;
	protected Calendar mEventDateTime = null;
	protected double mEventUnits = 0;
	protected boolean mIsMatchTrialPromoCode = true;
	//nayera Min Subscription Period - German Migration - boolean to indicate if this is usage of cancellation service
	protected boolean cancellationUsage=false;
	/**
        The duration of the package.
	 */
	protected int mDuration = Constants.INT_MATCH_ALL;
	
	/**
        The type of package
	 */
	protected int mChargingMethod = Constants.INT_MATCH_ALL;
	//--
	
	protected String mPaymentInformation = null;
	
	protected double mPreRate = -1;
	
	//R10 TSM
	protected double mTaxRate = -1;
	
	//MQC 4902 - add Double object to hold tax rate, do determine whether java client
	//has sent the tax rate
	Double mTaxRateAsDouble = null;
	
	protected boolean mPreOrder = false; // @ER7
	protected boolean includeUnavailable = false;
	
	protected boolean expressPurchaseFlag = false;
	protected String mContentName = null;
	
	protected String mBearer = Constants.STRING_MATCH_ALL;

	protected String mAssetID;
	
	/** ADDED By India **/
	protected String[]  mBearerIds = new String[] {Constants.STRING_NOT_SET};
	
	/**  VFE-ER8.0 (Record Undiscounted price on discounted purchases).  This field is added to Store the date time model in the rating attributes **/
	protected  String mTierName;
	
	
	
	//roaming - TODO remove these
	protected String mNetworkCodeStr;		// for external clients
	protected NetworkCode mNetworkCode;		// for internal use
	
	//REMEDY 5685 - subscription has historic pricepoint flag
	protected boolean mHasHistoricPricePointFlag = false;
	
	//REMEDY 5685 - are rating attributes for a renewal
	protected boolean mIsForRenewal = false;
	
	//CR AffiliateID
	protected String mAffiliateID;
	
	//CR-0978 Location Services TODO remove
	protected String mTariff = Constants.STRING_MATCH_ALL;
	//CR-0869 start
	/**
	 * Partner ID of the transaction
	 */
	protected String mPartnerId = null;
	
	/**
	 * Aggregator ID of the transaction
	 */
	protected String mAggregatorId = null;
	//CR-0869 end
	
	//CR-1209_1 - add receipient msisdn and product code
	protected String mReceipientMsisdn = null;
	protected String mProductCode = null;
	
	//MQC 5337
	protected   boolean mInForcePurchaseFlow = false;
	
	//CR-1448 - add merchant name and invoice text
	protected String mMerchantName = null;
	protected String mInvoiceText = null;
	
	//MQC 6085 - moved from UsageAttributes class
	protected boolean enableReIssue = false;
	//CR1935
	protected boolean reIssueFlagPresent = false;
	
	//CR-1791
	protected String mShortPackageId = null;
	protected String mTaxCode = Constants.STRING_MATCH_ALL;
	protected Calendar mHistoryStartDate = null;
		
	/**
	 * CR 2134 - add vendor id to ERIF validate request
	 *
	 */
	protected String mVendorId = null;
		
	/**
	 * MQC 7449 - are rating attributes for a next payment amount request
	 *
	 */
	protected boolean mIsForNextPaymentAmount = false;
	
	
	protected boolean preRatePriceIsGross=false;
	
	//CR 2108
	double renewalPreRate = -1;
	
	//MQC 8034 - flag to check whether renewal pre rate has been set by client. This is required since when calls
	//made by old ecom client where the renewalPreRate field does not exist, upon serialization the renewalPreRate
	//value is defaulted to the type default i.e. 0.0, instead of -1 as specified above
	protected boolean hasRenewalPreRate=false;
	
	//CR2245 - internal flag to determine whether to override the catalog package disallow prerate flag, set to true only internally
	// in the Discount Prorated Upsell flow
	protected boolean overrideDisallowPreRateFlag = false;
	
	/**
	 * CR 2255 - add content_category
	 *
	 */
	protected String mContentCategory;
	
	/**
	 * CR 2255 Phase 2 - add the Partner URL
	 */
	protected String  mPartnerUrl;
	
	/**
	 * CR 2255 Phase 2 - add the Partner Contact Information
	 */
	protected String mPartnerContactInfo;
	
	/**
	 * CR 2255 Phase 2 - add the partner email Address
	 */
	protected String mPartnerEmail;
	
	
	/**
	 * for CR 2145.  Returns false by default unless the client sets it to true
	 * @return
	 */
	public boolean isPreRatePriceGross() {
		return preRatePriceIsGross;
	}

	public void setPreRatePriceIsGross(boolean preRatePriceIsGross) {
		this.preRatePriceIsGross = preRatePriceIsGross;
	}

	/**
  	 * CR CTB-1 Advanced Linked Pricepoint - a counter of the number of subscription renewals of a pricepoint which has a linked pricepoint
  	 */
  	protected int mSubRenewalCounterToLinkedPricepoint = -1;
  	
  	/**
  	 * CR CTB-1 Advanced Linked Pricepoint - a counter of the number of renewals of a pricepoint which has a linked pricepoint defined in the priceplan
  	 */
  	protected int mPPtRenewalCounterToLinkedPricepoint = -1;
  	
  	/**
  	 * CR CTB-1 Advanced Linked Pricepoint - is pricepoint linked by a trial pricepoint
  	 */
  	
  	protected boolean mLinkedByTrialPricepoint = false;
  	
  	/**
  	 * CR CTB-1 Advanced The current pricepoint id of the subscription that is being renewed
  	 */
  	
  	protected String mSubRenewalPricepointId;
  	
  	/**
  	 * CR Advanced Linked Pricepoint - the link pricepoint id
  	 */
  	
  	protected String mLinkPricepointId;
  	
  	/**
  	 * CR Advanced Linked Pricepoint - the first purchase of the subscription was a TRIAL 
  	 */
  	
  	protected boolean mSubPurchaseTransactionTrial;
  	
  	/**
  	 * MQC 9383 - discard hidden pricepoints from pricepoint rating 
  	 */
  	
  	protected boolean mDiscardHiddenInactivePricepoints;
  	
	/**
	 *   Default constructor.
	 */
	public RatingAttributes()
	{
	}
	

	
	public RatingAttributes(RatingAttributes attrs)
	{
		//@hud we should set supper
		super(attrs);
		
		if (attrs!=null) {
			mChannel = attrs.mChannel;
			mPaymentType = attrs.mPaymentType;
			mDeviceType = attrs.mDeviceType;
			mPremiumLevel = attrs.mPremiumLevel;
			mSupplierId = attrs.mSupplierId;
			mUserGroups = attrs.mUserGroups;
			mPromoCodes = attrs.mPromoCodes;
			mEventDateTime = attrs.mEventDateTime;
			mEventUnits = attrs.mEventUnits;
			mDuration = attrs.mDuration;
			mChargingMethod = attrs.mChargingMethod;
			mIsMatchTrialPromoCode = attrs.mIsMatchTrialPromoCode;
			mPaymentInformation = attrs.mPaymentInformation;
			interactiveFlag = attrs.interactiveFlag;
			mPreOrder = attrs.mPreOrder; // @ER7
			mContentName = attrs.mContentName;
			mAssetID     = attrs.mAssetID;
			expressPurchaseFlag = attrs.expressPurchaseFlag;
			mBearer = attrs.mBearer;
			mBearerIds = attrs.mBearerIds;
			//n
			cancellationUsage = attrs.cancellationUsage;
			
			//@hud STKHREQ13107
			mPromoPrecode = attrs.mPromoPrecode;
			mPromoUniqueCode = attrs.mPromoUniqueCode;
			
			
			//@hud STKHREQ13076 SP ROAMING
			mNetworkCode = attrs.getNetworkCode();
			mNetworkCodeStr = attrs.getNetworkCodeStr();

		   mMultiUsageMode =  new PropertyDataInt();
		   //mAccessControlSwitch = new PropertyDataBool();
		   mNetworkCodeMatchMethod = new PropertyDataInt(); 
           mRefuseUsageWhenProvisioning = new PropertyDataBool();
                        
           //REMEDY 5532
           
           mNextCycleDisount = attrs.mNextCycleDisount;
           
           //END REMEDY 5532
           
           //REMEDY 5685
           mHasHistoricPricePointFlag = attrs.getHasHistoricPricePointFlag();
           
           //REMEDY 5685
           mIsForRenewal = attrs.isIsForRenewal();
           
           //CR AffiliateID
       	   mAffiliateID = attrs.mAffiliateID;
       	   
       	   //CR-0978 Location Services
       	   if (attrs.mTariff == null || attrs.mTariff.equals("")) {
       		   mTariff = Constants.STRING_MATCH_ALL;
       	   }
       	   else {
       		   mTariff = attrs.mTariff;
       	   }

			//CR-0869 Start
           this.mPartnerId = attrs.getPartnerId();
		   this.mAggregatorId = attrs.getAggregatorId();
		   //CR-0869 End
		   
		   //MQC 5337
		   this.mInForcePurchaseFlow = attrs.isForcePurchaseFlow();
		   
		   //CR-1209_1 - add receipient msisdn and product code
		   this.mReceipientMsisdn = attrs.getReceipientMsisdn();
		   this.mProductCode = attrs.getProductCode();
		   
		   //CR-1448 - add merchant name and invoice text
		   this.mMerchantName = attrs.getMerchantName();
		   this.mInvoiceText = attrs.getInvoiceText();
		   
		   //MQC 6085 - moved from UsageAttributes class
		   this.enableReIssue = attrs.isReIssueEnabled();
		   
		   //CR 2134 - add vendor id
		   this.mVendorId = attrs.getVendorId();
		   
		   //MQC7449 - are rating attributes for a next payment amount request
		   this.mIsForNextPaymentAmount = attrs.isIsForNextPaymentAmount();
		   
		   this.overrideDisallowPreRateFlag = attrs.isOverrideDisallowPreRateFlag();
		   
		   // CR 2255 - add content_category
		   this.mContentCategory = attrs.getContentCategory();
		   
		   //CR2255 Phase2 - add partner information
		   this.mPartnerUrl = attrs.getPartnerUrl();
		   this.mPartnerContactInfo = attrs.getPartnerContactInfo();
		   this.mPartnerEmail = attrs.getPartnerEmail();
		   //CR CTB-1 Advanced Linked Pricepoint
		   this.mSubRenewalCounterToLinkedPricepoint = attrs.getSubRenewalCounterToLinkedPricepoint();
		   this.mPPtRenewalCounterToLinkedPricepoint = attrs.getPPtRenewalCounterToLinkedPricepoint();
		   this.mLinkedByTrialPricepoint = attrs.getLinkedByTrialPricepoint();
		   this.mLinkPricepointId = attrs.getLinkPricepointId();
		   this.mSubRenewalPricepointId = attrs.getSubRenewalPricepointId();
		   this.mSubPurchaseTransactionTrial = attrs.getSubPurchaseTransactionTrial();
		   //MQC 9383
		   this.mDiscardHiddenInactivePricepoints = attrs.getDiscardHiddenInactivePricepoints();
		}
	}
	
	/**
	 * this is the pre-tax amount to be charged
	 */
	public void setPreRate(double preRate){
		this.mPreRate = preRate;
	}
	
	/**
	 * this is the pre-tax amount to be charged
	 * @return
	 */
	public double getPreRate()	{
			return mPreRate ;
	}
	
	public String getPaymentInformation()
	{
		return mPaymentInformation;
	}
	
	public void setPaymentInformation(String newPaymentInformation)
	{
		mPaymentInformation = newPaymentInformation;
	}
	
	public String getContentName()
	{
		return mContentName;
	}
	
	public void setContentName(String newContentName)
	{
		mContentName = newContentName;
	}
	/****************Angie Phase II *******************/
	public String getAssetID()  
	{  
		return mAssetID;  
	}  
	public void setAssetID(String newAssetID)  
	{    
		mAssetID = newAssetID;
		
	}  
	/***********************************************/
	public int getPremiumLevel()
	{
		return mPremiumLevel;
	}
	
	public void setPremiumLevel(int level)
	{
		mPremiumLevel = level;
	}
	
	/**
        This is used internally by the ER2 system
        @deprecated
	 */
	@Deprecated
	public int getReserveOnlyFlag() {
		return 0;
	}
	

	/**
        This is used internally by the ER2 system
	 */
	public String getSupplierId() {
		return this.mSupplierId;
	}
	
	/**
	 * Set the supplied id. This is used to rate the service.
	 * Different rates can exist for different suppliers.
	 */
	public void setSupplierId( String id) {
		this.mSupplierId = id;
	}
	
	
	
	/**
	 *   Retrieve the access device type.
	 * @see DeviceType
	 */
	public int getDeviceType()
	{
		return this.mDeviceType;
	}
	
	/**
	 *   Set the access device type. Must be defined in DeviceType class.
	 * @see DeviceType
	 */
	public void setDeviceType( int code )
	{
		this.mDeviceType = code;
	}
	
	
	/**
	 *  Retrieve the user groups.
	 * This allows a customer to belong to a number of user groups.
	 */
	public String[] getUserGroups()
	{
		return this.mUserGroups;
	}
	
	/**
	 *  Get the user group.
	 */
	public String getUserGroup()
	{
		String rv = null;
		
		if (this.mUserGroup != null && !this.mUserGroup.equals(Constants.STRING_NOT_SET)){
			return this.mUserGroup;
		}
		else if (mUserGroups!=null && mUserGroups.length>0) {
			rv = mUserGroups[0];
		}
		return rv;
	}
	
	/**
	 *  Set the user group.
	 */
	public void setUserGroup( String userGroup )
	{
		this.mUserGroup = userGroup;
	}
	
	/**
	 *  Set the user group.
	 */
	@Deprecated
	public void setUserGroups( String[] userGroups )
	{
		this.mUserGroups = userGroups;
	}
	
	/**
	 *   Retrieve the access device type.
	 */
	public int getPaymentType()
	{
		return this.mPaymentType;
	}
	
	/**
	 *   Retrieve the access device type.
	 */
	public int getChannel()
	{
		return this.mChannel;
	}
	
	/**
	 *   Set the payment type.
	 */
	public void setPaymentType( int code )
	{
		this.mPaymentType = code;
	}
	
	
	/**
	 */
	public void setChannel( int code )
	{
		this.mChannel = code;
	}
	
	/**
	 * This is used internally by the ER system
	 */
	public Calendar getEventDateTime() {
		return mEventDateTime;
	}
	
	/**
	 *   This is used to set the time that the package was purchased/ the service was used.
	 */
	public void setEventDateTime(Calendar eventDateTime) {
		mEventDateTime = eventDateTime;
	}
	
	/**
	 * This is used internally by the ER system
	 */
	public double getEventUnits() {
		return mEventUnits;
	}
	
	/**
	 * Sets the amount the event has been used. This is should in volume service
    to set the usage amount.
	 */
	public void setEventUnits(double eventUnits) {
		mEventUnits = eventUnits;
	}
	
	/**
        This is used by the CatalogApi findPackagesWithService() method. It is used
        to find matching packagess in the catalog/priceplan based on the promo codes entered by the user.,
        @return String[] The array of promo codes that the user has entered
	 */
	public String[] getPromoCodes()
	{
		return this.mPromoCodes;
	}
	
	public String[] getBearerIds()
	{
		return this.mBearerIds;
	}
	public void setBearerIds(String[] val)
	{
		this.mBearerIds = val;
		this.mBearer = val[0];
	}
	public String getPromoCode()
	{
		String rv = null;
		
		if (mPromoCodes!=null && mPromoCodes.length>0) {
			rv = mPromoCodes[0];
		}
		return rv;
	}
	
	/**
        This allows a customer to set a number of promo codes although in practise only one is usually set.
        @param val String[] The array of promo codes that the user has entered
	 */
	public void setPromoCodes(String[] val)
	{
		this.mPromoCodes = val;
	}
	
	/**
        The duration of the package. The duration is the length of the package.
        This is normally used when rating a package rating option but it is also can be used
        to rate a service dependent on the package it was purchased with.
        The ER rating engine sets this for usage to the duration
        of the subscription that the service is a part of.
	 */
	public int getDuration()
	{
		return mDuration;
	}
	
	/**
        Charging method - ie event (1), non-recurring (2), or recurring (3)
        @see {@link ChargingMethod}
	 */
	public int getChargingMethod()
	{
		return mChargingMethod;
	}
	
	/** ADDED FOR EGYPT ER7 STUB **/
	public String getBearer()
	{
		//return mBearer;
		String rv = null;
		if(mBearer!=null && !(mBearer.equals(""))){
			rv =mBearer;
		} else {
			if (mBearerIds!=null && mBearerIds.length>0) {
				rv = mBearerIds[0];
			}
		}
		return rv;
	}
	
	public void setDuration(int val)
	{
		mDuration = val;
	}
	
	public void setChargingMethod(int val)
	{
		mChargingMethod = val;
	}
	
	/**
	 *   Returns information about service for debug purposes.
            mUserGroups = attrs.mUserGroups;
            mPromoCodes = attrs.mPromoCodes;
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("supplierId=" + this.mSupplierId).append(",");
		sb.append("channel=" + this.mChannel).append(",");
		sb.append("deviceType=" + this.mDeviceType).append(",");
		sb.append("paymentType=" + this.mPaymentType).append(",");
		sb.append("mPremiumLevel=" + this.mPremiumLevel).append(",");
		sb.append("interactiveFlag=" + this.interactiveFlag).append(','); //interactiveFlag
		sb.append("matchTrialPromoCode=" + this.mIsMatchTrialPromoCode).append(',');
		sb.append("duration=").append(mDuration).append(',');
		sb.append("method=").append(mChargingMethod).append(',');
		
		if (mUserGroups!=null && mUserGroups.length>0) {
			for (String mUserGroup2 : mUserGroups) {
				sb.append("userGroup=").append(mUserGroup2).append(',');
			}
		}
		else sb.append("userGroups=null,");
		
		/*if (mUserGroups!=null && mUserGroups.length>0) {
            sb.append("userGroup=").append(mUserGroups[0]).append(',');
            if (mUserGroups.length>1) {
                sb.append("userGroup=").append(mUserGroups[1]).append(',');
            }
        }*/
		if (mPromoCodes!=null && mPromoCodes.length>0) {
			/*sb.append("promoCodes=").append(mPromoCodes[0]).append(',');
            if (mPromoCodes.length>1) {
                sb.append("promoCodes=").append(mPromoCodes[1]).append(',');
            }*/
			for (String mPromoCode : mPromoCodes) {
				sb.append("promoCode=").append(mPromoCode).append(',');
			}
		}
		else sb.append("promoCodes=null,");
		//[2] Mod Starts
		if (mBearerIds!=null && mBearerIds.length>0) {
			sb.append("BearerId=").append(mBearerIds[0]).append(',');
			if (mBearerIds.length>1) {
				sb.append("BearerId=").append(mBearerIds[1]).append(',');
			}
		}
		//[2] Mod Ends
		if (mEventDateTime != null) {
			sb.append("eventDateTime=" + mEventDateTime.toString()).append(',');
		}
		sb.append("eventUnits=" + mEventUnits).append(',');
		sb.append("contentName=" + mContentName).append(',');
		sb.append("expressFlag=" + expressPurchaseFlag).append(',');
		sb.append("bearer=" + mBearer).append(',');
		//n
		sb.append("cancellation=" +cancellationUsage).append(',');
		//nn
		//REMEDY 5685
		sb.append("hashistoricpricepointflag=" + mHasHistoricPricePointFlag).append(',');
		sb.append("isforrenewal=" + mIsForRenewal).append(',');
		//CR AffiliateID
		sb.append("affiliateID=" + mAffiliateID).append(',');
		//CR-0978 Location Services
		sb.append("tariff=" + mTariff).append(',');
		//CR-0869 Start
		sb.append("partnerid=" + mPartnerId).append(',');
		sb.append("aggregatorid=" + mAggregatorId).append(',');
		//MQC 5337
		sb.append("inforcepurchaseflow=" + mInForcePurchaseFlow).append(',');
		//CR-1209_1 - add receipient msisdn and product code
		sb.append("receipientmsisdn=" + mReceipientMsisdn).append(',');
		sb.append("productcode=" + mProductCode).append(',');
		//CR-1448 - add merchant name and invoice text
		sb.append("merchantname=" + mMerchantName).append(',');
		sb.append("invoicetext=" + mInvoiceText).append(',');
		sb.append("mIsForNextPaymentAmount=" + mIsForNextPaymentAmount).append(',');
		// CR 2255 - add content_category
		sb.append("mContentCategory=" + mContentCategory).append(',');
		//CR2255 Phase2 - add partner information
		sb.append("mPartnerUrl=" + mPartnerUrl).append(',');
		sb.append("mPartnerContactInfo=" + mPartnerContactInfo).append(',');
		sb.append("mPartnerEmail=" + mPartnerEmail).append(',');
		//CR CTB-1 Advanced Linked Pricepoint
		sb.append("mSubRenewalCounterToLinkedPricepoint=" + mSubRenewalCounterToLinkedPricepoint).append(',');
		sb.append("mPPtRenewalCounterToLinkedPricepoint=" + mPPtRenewalCounterToLinkedPricepoint).append(',');
		sb.append("mLinkedByTrialPricepoint=" + mLinkedByTrialPricepoint).append(',');
		sb.append("mLinkPricepointId=" + mLinkPricepointId).append(',');
		sb.append("mSubPurchaseTransactionTrial=" + mSubPurchaseTransactionTrial).append(',');
		//MQC9383
		sb.append("mDiscardHiddenInactivePricepoints=" + mDiscardHiddenInactivePricepoints).append(',');
		return sb.toString();
	}
	
	/**
	 * Disables interactivity
	 * By default, the client will be in interactive mode which means that it can request user
	 * interaction. Fpr example by redirecting to a purchase application.
	 * For most clients the default behaviour is interactive
	 * For example, VAMP would call this method
	 */
	public void disableInteractivity() {
		this.interactiveFlag = false;
	}
	
	/**
        This is used internally by the ER2 system
	 */
	public boolean isInteractive()
	{
		return this.interactiveFlag;
	}
	
	public void setMatchTrialPromoCode(boolean val)
	{
		mIsMatchTrialPromoCode = val;
	}
	
	public boolean matchTrialPromoCode()
	{
		return mIsMatchTrialPromoCode;
	}
	
	public void setIncludeUnavailable(boolean flag)
	{
		includeUnavailable = flag;
	}
	
	public boolean isIncludeUnavailable()
	{
		return includeUnavailable;
	}
	
	public void setExpressFlag(boolean expFlag)
	{
		expressPurchaseFlag = expFlag;
	}
	
	public boolean getExpressFlag()
	{
		return expressPurchaseFlag;
	}
	
	public boolean isExpressFlag()
	{
		return expressPurchaseFlag;
	}
	
	public boolean	isPreOrder() // @ER7
	{
		return mPreOrder;
	}
	
	public void	setPreOrder(boolean preOrder) // @ER7
	{
		mPreOrder = preOrder;
	}
	
	/** ADDED FOR EGYPT ER7 STUB **/
	public void setBearer(String bearer)
	{
		mBearer = bearer;
	}
	
//	nayera
	public void setCancellationUsage(boolean cancellationUsage)
	{
		this.cancellationUsage = cancellationUsage;
	}
	
	
	public boolean isCancellationUsage()
	{
		return cancellationUsage;
	}
	/**
	 *  @version      ER 8.0
	 *  @author       VFE � PS Team
	 *  @date         15-Aug-2005
	 *  @description  (Record Undiscounted price on discounted purchases)   The purpose of this method is to get the Tier name from the rating attributes . 
	 **/
	public String getTierName ()
	{
		return mTierName;
	}
	
	/**
	 *  @version      ER 8.0
	 *  @author       VFE � PS Team
	 *  @date         15-Aug-2005
	 *  @description  (Record Undiscounted price on discounted purchases)   The purpose of this method is to set the Tier name in the rating attributes. 
	 **/
	public void  setTierName (String val)
	{
		mTierName = val;
	}
	
	/**
	 * @author hud
	 * STKHREQ13107
	 * UNique Promo Code
	 * Naming Convention:
	 * PromoPrecode: The precode
	 * PromoUniqueCode: The 5-char unique code within precode set
	 * UniquePromoCode: PromoPrecode + PromoUniqueCode 
	 */
	public String getPromoPrecode()
	{
		return mPromoPrecode;
	}
	
	protected void _setPromoPrecode(String precode)
	{
		mPromoPrecode = precode;
		mPromoCodes = new String[1];
		mPromoCodes[0] = precode;
	}
	public void setPromoPrecode(String precode)
	{
		mPromoPrecode = precode;
	}
	
	public void setUniquePromoCode(String precode, String uniqueCode)
	{
		_setPromoPrecode(precode);
		mPromoUniqueCode = uniqueCode;
	}
	public String getUniquePromoCode() 
	{
		if (mPromoPrecode != null && mPromoUniqueCode != null) {
			return mPromoPrecode + mPromoUniqueCode;
		}
		else {
			return null;
		}
	}
	public void setPromoUniqueCode(String uniqueCode)
	{
		mPromoUniqueCode = uniqueCode;
	}
	public String getPromoUniqueCode()
	{
		return mPromoUniqueCode;
	}
	
	
	//////////////////////////////////////////////////////////
	/**
	 * @author hud
	 * STKHREQ13076 SP ROAMING
	 * Network code access
	 */
	public void setNetworkCodeStr(String networkCodeStr)
	{
		mNetworkCodeStr = networkCodeStr;
	}
	public String getNetworkCodeStr() 
	{
		return mNetworkCodeStr;
	}
	
	public void setNetworkCode(NetworkCode networkCode)
	{
		mNetworkCode = networkCode;
	}
	public NetworkCode getNetworkCode() 
	{
		return mNetworkCode;
	}
	
	
	/**
	 * @hud moved from PurchaseAttributes
	 * This is used internally by the ER2 system
	*/
	public double getNextCycleDiscount() {
		return mNextCycleDisount;
	}
	public void setNextCycleDiscount(double val) {
		mNextCycleDisount = val;
	}	
	
	/** 
	   * [1] CQ14138 - lle
	   * Return true if this rating attribute is a matched non-recurring to the recurring rating attributes
	   * @param recurring The recurring rating attributes to be compared
	   * @return true if this rating attribute is a matched non-recurring to the recurring rating attributes
	   * 		 false otherwise
	   */
	  public boolean isMatchedNonRecurring(RatingAttributes recurring)
	  {
		  if ((mChargingMethod == ChargingMethod.NON_RECURRING) 
				  && (recurring.getChargingMethod() == ChargingMethod.RECURRING)
				  && (RatingUtils.isAttributeEqual(recurring.getDuration(),mDuration))
				  && (RatingUtils.isAttributeEqual(recurring.getPromoCode(),getPromoCode()))
				  && (RatingUtils.isAttributeEqual(recurring.getUserGroup(),getUserGroup()))				 
				  && (RatingUtils.isAttributeEqual(recurring.getSupplierId(),mSupplierId))
				  && (RatingUtils.isAttributeEqual(recurring.getPremiumLevel(),mPremiumLevel))
				  && (RatingUtils.isAttributeEqual(recurring.getChannel(),mChannel))
				  && (RatingUtils.isAttributeEqual(recurring.getDeviceType(), mDeviceType)))
		  {
			  return true;
		  }
		  return false;
		  
	  }

	  //REMEDY 5685
	  public boolean getHasHistoricPricePointFlag() {
		  return mHasHistoricPricePointFlag;
	  }
	  
	  public void setHasHistoricPricePointFlag(boolean hasHistoricPricePointFlag) {
		  mHasHistoricPricePointFlag = hasHistoricPricePointFlag;
	  }

	  //	REMEDY 5685
	  public boolean isIsForRenewal() {
		  return mIsForRenewal;
	  }
	  
	  public void setIsForRenewal(boolean isForRenewal) {
		  mIsForRenewal = isForRenewal;
	  }
	  	
	  public double getTaxRate() {
			return mTaxRate;
	  }
	
	  //MQC 5006 - also set the tax Rate Double object
	  public void setTaxRate(double taxRate) {
			mTaxRate = taxRate;
			mTaxRateAsDouble = new Double(taxRate);
	  }
	  //R10 TSM end
	
	  //MQC 5006
	  public Double getTaxRateAsDouble () {
		  return mTaxRateAsDouble;
	  }
	  
	  //CR AffiliateID
	  public String getAffiliateID()  
	  {  
		return mAffiliateID;  
	  }  
	  public void setAffiliateID(String affiliateID)  
	  {    
		  mAffiliateID = affiliateID;	
	  }  
	  
	  //CR-0869 Start
      /**
       * Gets transaction partner ID
       * @return String Partner ID
       */
	  public String getPartnerId() {
        return mPartnerId;

      }      
	  
	  //CR Location
	  public String getTariff()  
	  {  
		//MQC 4965 - return match all if null or empty string, this will be the case of older clients
		if (mTariff == null || mTariff.equals("")) {
			return Constants.STRING_MATCH_ALL;
		}
		else {
			return mTariff;
		}
	  }
	  
	  public void setTariff(String tariff)  
	  {   
		  //MQC 4965 - set match all if null or empty string, this will be the case of older clients
		  if (tariff == null || tariff.equals("")) {
			  mTariff = Constants.STRING_MATCH_ALL;
		  }
		  else {
			  mTariff = tariff;
		  }
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
      
  	//MQC 5337
  	public boolean isForcePurchaseFlow(){
  		return this.mInForcePurchaseFlow;
  	}

  	public void setForcePurchaseFlow (boolean isForcePurchaseFlow){
  		this.mInForcePurchaseFlow = isForcePurchaseFlow;
  	}
	//CR-1209_1 - add receipient msisdn and product code
	  public String getReceipientMsisdn()  
	  {  
		return mReceipientMsisdn;  
	  }
	  
	  public void setReceipientMsisdn(String receipientMsisdn)  
	  {    
		  mReceipientMsisdn = receipientMsisdn;	
	  }
	  
	  public String getProductCode()  
	  {  
		return mProductCode;  
	  }
	  
	  public void setProductCode(String productCode)  
	  {    
		  mProductCode = productCode;	
	  }
	  
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
	  
	  //MQC 6085 - moved from UsageAttributes class
	    /**
	     * Enables charging functionality in ER.
	     * ER returns the re-issue element in the 
	     * UsageAuthorisation response
	     */
	    public void enableReIssue() {
	        this.enableReIssue = true;
   	        //CR1935
	        this.reIssueFlagPresent = true;
	    }

	    /**
	     * Disables charging functionality in ER.
	     * ER does not return the re-issue element in the
	     * UsageAuthorisation response
	     */
	    public void disableReIssue() {
	        this.enableReIssue = false;
	        //CR1935
	        this.reIssueFlagPresent = true;
	    }

	    /**
	     * Gets value of enableReIssue flag.
	     */
	    public boolean isReIssueEnabled() {
	        return this.enableReIssue;
	    }
	    //CR1935 start
	    public boolean isReIssueFlagPresent() {
	        return this.reIssueFlagPresent;
	    }

	    public void setReIssueFlagPresent(boolean value) {
	        this.reIssueFlagPresent = value;
	    }
	    //CR1935 end

	    //CR-1791 Start
		public String getShortPackageId() {
			return mShortPackageId;
		}

		public void setShortPackageId(String mShortPackageId) {
			this.mShortPackageId = mShortPackageId;
		}

		public String getTaxCode() {
			return mTaxCode;
		}

		public void setTaxCode(String mTaxCode) {
			this.mTaxCode = mTaxCode;
		}

		public Calendar getHistoryStartDate() {
			return mHistoryStartDate;
		}

		public void setHistoryStartDate(Calendar mHistoryStartDate) {
			this.mHistoryStartDate = mHistoryStartDate;
		}
		//CR-1791 End
		
		/**
		 * CR 2134 - add vendor id
		 * @return String Vendor Id
		 */
		public String getVendorId() {
			return mVendorId;
		}
		
		/**
		 * CR 2134 - add vendor id
		 * @param vendorId The vendor Id
		 */
	    public void setVendorId(String vendorId) {
	    	mVendorId = vendorId;
	    }

	    /**
		 * MQC 7449 - add is for next payment amount flow
		 * @return boolean
		 */
	    public boolean isIsForNextPaymentAmount() {
	    	return mIsForNextPaymentAmount;
	    }
  
	    /**
		 * MQC 7449 - add is for next payment amount flow
		 * @param boolean
		 */
	    public void setIsForNextPaymentAmount(boolean isForNextPaymentAmount) {
	    	mIsForNextPaymentAmount = isForNextPaymentAmount;
	    }
	    
	    //CR2108 - Start
		/**
		 * This is the amount charged for a renewal if specified in a request
		 * This is pre tax unless the setPreRatePriceIsGross is set to true
		 * in which case this will be a gross price
		 */
		public void setRenewalPreRate(double renewalPreRate){
			this.renewalPreRate = renewalPreRate;
			//MQC 8034 - also set the hasRenewalPreRate flag
			this.hasRenewalPreRate = true;
		}
		
		/**
		 * this is the pre-tax amount to be charged
		 * @return
		 */
		public double getRenewalPreRate()	{
				return renewalPreRate ;
		}
	    //CR2108 - End
		
		/**
		 * MQC 8034
		 * has renewal pre rate been set
		 * @return
		 */
		public boolean hasRenewalPreRate () {
			return this.hasRenewalPreRate;
		}
		
		/**
		 * CR 2245
		 * has override disallow pre rate flag been set
		 * @return boolean
		 */
		public boolean isOverrideDisallowPreRateFlag(){

			return overrideDisallowPreRateFlag;
		}
		
		/**
		 * CR 2245
		 * set override disallow pre rate flag
		 * @param overrideFlag
		 */
		public void setOverrideDisallowPreRateFlag ( boolean overrideFlag ){
		
			overrideDisallowPreRateFlag = overrideFlag;
		}
		
		/**
		 * CR 2255
		 * @return the mContentCategory
		 */
	    public String getContentCategory() {
			return mContentCategory;
		}
	    
	    /**
	     * CR 2255
	   	 * @param contentCategory the mContentCategory to set
	   	 */
	  	public void setContentCategory(String contentCategory) {
	  		this.mContentCategory = contentCategory;
	  	}
	  	
		/**
		 * CR 2255 Phase2 - add partner information
		 * @return the mPartnerUrl
		 */
		public String getPartnerUrl() {
			return this.mPartnerUrl;
		}
		
		
		/**
		 * CR 2255 Phase2 - add partner information
		 * @param partnerUrl, the mPartnerUrl is set
		 */
		public void setPartnerUrl(String partnerUrl) {
			this.mPartnerUrl = partnerUrl;
		}
		
		/**
		 * CR 2255 Phase2 - add partner information
		 * @return the mPartnerContactInfo
		 */
		public String getPartnerContactInfo() {
			return this.mPartnerContactInfo;
		}
		
		/**
		 * CR 2255 Phase2 - add partner information
		 * @param partnerContactInfo, the mPartnerContactInfo is set
		 */
		public void setPartnerContactInfo(String partnerContactInfo) {
			this.mPartnerContactInfo = partnerContactInfo;
		}
		
		/**
		 * CR 2255 Phase2 - add partner information
		 * @return the mPartnerEmail
		 */
		public String getPartnerEmail() {
			return this.mPartnerEmail;
		}
		
		/**
		 * CR 2255 Phase2 - add partner information
		 * @param partnerEmail, the mPartnerEmail is set
		 */
		public void setPartnerEmail(String partnerEmail) {
			this.mPartnerEmail = partnerEmail;
		}
		
		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @return the mRenewalCounterToLinkedPricepoint
		 */
		public int getSubRenewalCounterToLinkedPricepoint() {
			return this.mSubRenewalCounterToLinkedPricepoint;
		}
		
		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @param noOfSubRenewals the mRenewalCounterToLinkedPricepoint to set
		 */
		public void setSubRenewalCounterToLinkedPricepoint(int noOfSubRenewals) {
			this.mSubRenewalCounterToLinkedPricepoint = noOfSubRenewals;
		}
		
		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @return the mSubRenewalCounterToLinkedPricepoint
		 */
		public int getPPtRenewalCounterToLinkedPricepoint() {
			return this.mPPtRenewalCounterToLinkedPricepoint;
		}
		
		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @param noOfPPtRenewals the mSubRenewalCounterToLinkedPricepoint to set
		 */
		public void setPPtRenewalCounterToLinkedPricepoint(int noOfPPtRenewals) {
			this.mPPtRenewalCounterToLinkedPricepoint = noOfPPtRenewals;
		}
		
		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @return the mLinkedByTrialPricepoint
		 */
		public boolean getLinkedByTrialPricepoint() {
			return this.mLinkedByTrialPricepoint;
		}
		
		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @param linkedByTrial the mLinkedByTrialPricepoint to set
		 */
		public void setLinkedByTrialPricepoint(boolean linkedByTrial) {
			this.mLinkedByTrialPricepoint = linkedByTrial;
		}

		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @return the mLinkPricepointId
		 */
		public String getSubRenewalPricepointId() {
			return this.mSubRenewalPricepointId;
		}
		
		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @param linkedByTrial the mLinkedByTrialPricepoint to set
		 */
		public void setSubRenewalPricepointId(String pPtId) {
			this.mSubRenewalPricepointId = pPtId;
		}
		
		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @return the mLinkPricepointId
		 */
		public String getLinkPricepointId() {
			return this.mLinkPricepointId;
		}
		
		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @param linkedByTrial the mLinkedByTrialPricepoint to set
		 */
		public void setLinkPricepointId(String linkedPPtId) {
			this.mLinkPricepointId = linkedPPtId;
		}
		
		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @return the mSubPurchaseTransactionTrial
		 */
		public boolean getSubPurchaseTransactionTrial() {
			return this.mSubPurchaseTransactionTrial;
		}
		
		/**
		 * CR CTB-1 Advanced Linked Pricepoint
		 * @param subPurchTranTrial the mSubPurchaseTransactionTrial to set
		 */
		public void setSubPurchaseTransactionTrial(boolean subPurchTranTrial) {
			this.mSubPurchaseTransactionTrial = subPurchTranTrial;
		}
		
		/**
		 * MQC 9383 - discard hidden pricepoints from pricepoint rating
		 * @return the mDiscardHiddenPricepoints
		 */
		public boolean getDiscardHiddenInactivePricepoints() {
			return this.mDiscardHiddenInactivePricepoints;
		}
		
		/**
		 * MQC 9383 - discard hidden pricepoints from pricepoint rating
		 * @param discardHiddenPPTs the mDiscardHiddenPricepoints to set
		 */
		public void setDiscardHiddenInactivePricepoints(boolean discardHiddenInactivePPTs) {
			this.mDiscardHiddenInactivePricepoints = discardHiddenInactivePPTs;
		}
		
}
