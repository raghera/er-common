package com.vizzavi.ecommerce.business.charging;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.common.Constants;
import com.vizzavi.ecommerce.business.common.RatingAttributes;

/**
    The attributes that the user can set when purchasing a package.

    For a normal purchase of a package the following attributes should be called.
        PaymentType - The default for a country can be trieved from

<code>
<pre>

    Locale locale = Locale.GERMANY;
    int paymentType = paymentSystemType.getSyncPaymentType( locale );


</pre>
</code>

*/
public class PurchaseAttributes extends RatingAttributes
{
   private    static final long serialVersionUID = -2988451635805630852L;
   
   private static final Logger logger = LoggerFactory.getLogger(PurchaseAttributes.class);
   
    protected boolean mIsRepurchase = false;
    protected Map<String, String> mProvisioningTags = new HashMap<String, String>();
    protected PaymentCardDetails mPaymentCardDetails;
    protected String mPurchasedBy = "";
    
    
    int         reserveOnlyFlag = 0;
    protected boolean mPromotional = false;
    protected boolean mBatchProcessor = false;
    protected Date mPromotionalExpiryDate;
    
    protected boolean mReturnExpressPricePoint = false;

    //MQC 4927
    protected String mParentPackageId = "";
    
    //CR-1209_1 - flag to indicate for parent / child scenarios that the parent was purchased together with the child
    protected boolean mParentAlsoPurchased = false;
    
    //CR-1791
    private int mStatus = Constants.INT_NOT_SET;
    private Date mStartDate = null;
    private Date mEndDate = null;
    private ResourceBalanceOnly[] mResourceBalancesOnly = null;
    
    //CR1923 Partner Trading Limit
    protected String mForcePurchaseServiceId = "";
    
    private String externalSubId="";
    
    //CR 2134 - pass in service id to the purchase in the express purchase flow
    protected String mExpressPurchaseServiceId = "";
    
	//MQC 7424
    private String serviceId = "";
    
    //MQC 9436 - If the account is already validated and does not need to be
    //validated again
    private Boolean isValidatedAccount = null;

    public PurchaseAttributes()    {
    }

	public PurchaseAttributes(RatingAttributes attrs)
	{
		super(attrs);
	}

    /**
    * Forces this usage event to only reserve funds instead of reserving then capturing.
    * Calling this overrides any setting in the Catalog for this service.
    * For most clients the default behaviour specified in the Catalog should be sufficient and they
    * should not need to call this method.
    * <b>You must be familiar with 2-phase payment scenarios before calling this method</b>.
    */
    public void forceReservation() {
        this.reserveOnlyFlag = 1;
    }

    /**
        This is used internally by the ER2 system
    */
    @Override
	public int getReserveOnlyFlag() {
        return this.reserveOnlyFlag;
    }


    /**
    * Forces this usage event to reserve and capture funds instead of possibly only reserving.
    * Calling this overrides any setting in the Catalog for this package.
    * For most clients the default behaviour specified in the Catalog should be sufficient and they
    * should not need to call this method.
    * <b>You must be familiar with 2-phase payment scenarios before calling this method</b>.
    */
    public void disableReservation() {
        this.reserveOnlyFlag = 0;
    }



    /**
        If the customer already has the package, the ReasonCode.PACKAGE_RENEWAL will be returned if
        purchased.  If this attribute is set to true, this check is not done and the package will be
        purchased.
        @param flag set to true if the package should be repurchased
    */
    public void setIsRepurchase(boolean flag)
    {
        mIsRepurchase = flag;
    }

    /**
        Check if the package should be purchased even if the customer alreday has it.
        This is used internally by the ER2 system
        @return boolean true if the package will be purchased even if the user already has the package.
    */
    public boolean isRepurchase()
    {
        return mIsRepurchase;
    }


    /**
        Allows the provisioning tag (in the catalog) to be overwritten by a new provisioning id
        @param serviceId the id of the service to be provisioned
        @param provTag the new provisioning tag to be passed to the provisioning system
    */
    public void setServiceProvTag(String serviceId, String provTag)
    {
        mProvisioningTags.put(serviceId, provTag);
    }

    /**
        Gets the provisioning tag for a service.  If set to null, the default value in the catalog will
        be used.
        This is used internally by the ER2 system
    */
    public String getServiceProvTag(String serviceId)
    {
        return mProvisioningTags.get(serviceId);
    }

    /**
        Set the payment card details.
        This can be used for a payment type of less than a 1000, to send payment details to the
        payment system.
    */
    public void setPaymentCardDetails( PaymentCardDetails pcd )
    {
        mPaymentCardDetails = pcd;
    }

    /**
        Get payment card details.
        This is used internally by the ER2 system
    */
    public PaymentCardDetails getPaymentCardDetails()
    {
        return mPaymentCardDetails;
    }
    public boolean isPromotional()
  {
    return mPromotional;
  }

  public void setPromotional(boolean newMPromotional)
  {
    mPromotional = newMPromotional;
  }

  public Date getPromotionalExpiryDate()
  {
    return mPromotionalExpiryDate;
  }

  public void setPromotionalExpiryDate(Date newMPromotionalExpiryDate)
  {
    mPromotionalExpiryDate = newMPromotionalExpiryDate;
  }

//  public boolean isBatchProcessor()
//  {
//    return mBatchProcessor;
//  }
//
//  public void setBatchProcessor(boolean newMBatchProcessor)
//  {
//    mBatchProcessor = newMBatchProcessor;
//  }

 
   /**
        This is used internally by the ER2 system
        @deprecated
    */
    @Deprecated
	public boolean getRateOnly() {
        return false;
    }

    /**
        This is used to rate the purchase rather than do the purchase itself.
        When the rating call returns it returns an ER hashtable that can be set using setFlistInfo when
        the purchase has to be done. This would speed up the purchase.
        It is not recommended to use this functionality.
        @deprecated
    */
    @Deprecated
	public void setRateOnly(boolean mRateOnly) {
        //rateOnly = mRateOnly;
    }
    /**
        Set this if the purchase was purchased by another system such as cca
    */
    public void setPurchasedBy(String accId)
    {
        mPurchasedBy = accId;
    }

    /**
        This is used internally by the ER2 system
    */
    public String getPurchasedBy()
    {
        return mPurchasedBy;
    }
    
    //<< @hud JDP 2006-02-09
    public boolean getReturnExpressPricePoint()
    {
    	return mReturnExpressPricePoint;
    }
    public void setReturnExpressPricePoint(boolean bReturnExpressPricePoint)
    {
    	mReturnExpressPricePoint = bReturnExpressPricePoint;
    }
    
    //>> @hud JDP 2006-02-09

	/**
     MQC 4927 - This is used internally by the ER system
    */
    public void setParentPackageId(String parentPackageId)
    {
        mParentPackageId = parentPackageId;
    }

    /**
    MQC 4927 - This is used internally by the ER system
    */
    public String getParentPackageId()
    {
        return mParentPackageId;
    }
    
    /**
    CR-1209_1 - flag to indicate for parent / child scenarios that the parent was purchased together with the child
   */
   public void setParentAlsoPurchased(boolean parentAlsoPurchased)
   {
	   mParentAlsoPurchased = parentAlsoPurchased;
   }

   /**
   CR-1209_1 - flag to indicate for parent / child scenarios that the parent was purchased together with the child
   */
   public boolean getParentAlsoPurchased()
   {
       return mParentAlsoPurchased;
   }

   /**
   CR-1791 - status going to set at end of purchase transaction 
   */
	public int getStatus() {
		return mStatus;
	}
	
	/**
	CR-1791 - status going to set at end of purchase transaction 
    */
	public void setStatus(int status) {
		this.mStatus = status;
	}
	
	/**
	CR-1791 - start date going to set at end of purchase transaction 
    */
	public Date getStartDate() {
		return mStartDate;
	}
	
	/**
	CR-1791 - start date going to set at end of purchase transaction 
    */
	public void setStartDate(Date startDate) {
		this.mStartDate = startDate;
	}
	
	/**
	CR-1791 - end date going to set at end of purchase transaction 
    */
	public Date getEndDate() {
		return mEndDate;
	}
	
	/**
	CR-1791 - end date going to set at end of purchase transaction 
    */
	public void setEndDate(Date endDate) {
		this.mEndDate = endDate;
	}
	
	/**
	CR-1791 - resource balance going to set at end of purchase transaction 
    */
	public ResourceBalanceOnly[] getResourceBalancesOnly() {
		return mResourceBalancesOnly;
	}
	
	/**
	CR-1791 - resource balance going to set at end of purchase transaction 
    */
	public void setResourceBalancesOnly(ResourceBalanceOnly[] resourceBalancesOnly) {
		this.mResourceBalancesOnly = resourceBalancesOnly;
	}

	/**
    * Output data in the bean for logging purposes
    */
    @Override
	public String toString()
    {
        StringBuffer buf = new StringBuffer(super.toString());
        buf.append("purchasedBy=" + this.mPurchasedBy).append(',');
        buf.append("reserveOnlyFlag=" + this.reserveOnlyFlag).append(',');
        buf.append("isRepurchase=" + this.mIsRepurchase).append(',');
        buf.append("provisioningTags=" + this.mProvisioningTags).append(',');
        buf.append("AccessChannel="+this.getAccessChannel()).append(",");
        buf.append("PurchaseChannel="+this.getPurchaseChannel()).append(",");
        buf.append("PurchaseChannel="+this.getDeviceID()).append(",");
        buf.append("Msisdn="+this.getMsisdn()).append(",");
        buf.append("PaymentInformation="+this.getPaymentInformation()).append(",");
        buf.append("ContentName="+this.getContentName()).append(",");
        buf.append("AssetID="+this.getAssetID()).append(",");
        buf.append("PremiumLevel="+this.getPremiumLevel()).append(",");
        buf.append("DeviceType="+this.getDeviceType()).append(",");
        buf.append("PromoCode="+this.getPromoCode()).append(",");
        buf.append("Bearer="+this.getBearer()).append(",");
        buf.append("UniquePromoCode="+this.getUniquePromoCode()).append(",");
        buf.append("ContentName="+this.getContentName()).append(",");
        buf.append("mChannel="+this.mChannel).append(",");
        buf.append("PaymentType="+this.getPaymentType()).append(",");
        buf.append("Channel="+this.getChannel()).append(",");
        buf.append("mPromoCodes="+this.mPromoCodes).append(",");
        buf.append("mBearerIds="+this.mBearerIds).append(",");
        buf.append("mUserGroups="+this.mUserGroups).append(",");
        buf.append("LanguageLocale="+this.getLanguageLocale()).append(",");
        buf.append("PreRate="+this.getPreRate()).append(",");
        buf.append("mTariff="+this.mTariff).append(",");
        buf.append("mInForcePurchaseFlow="+this.mInForcePurchaseFlow).append(",");
        buf.append("EventUnits="+this.getEventUnits()).append(",");
        buf.append("Duration="+this.getDuration()).append(",");
        buf.append("ChargingMethod="+this.getChargingMethod()).append(",");
        buf.append("expressPurchaseFlag="+this.expressPurchaseFlag).append(",");
        return buf.toString();
    }
    
    /**
     * compare EVERY attribute in the class and its superclasses...
     * Maybe each superclass should have its own equals method comparing its own parameters.
     * MQC 6086 performance
     * 
     * @param obj the PurchaseAttributes object to be compared
     * @return true if they are the same, false if they're not (well duh)
     */
    @Override
	public boolean equals(Object obj){
    	
    	if (obj == null || obj.getClass() != PurchaseAttributes.class)   
    	     return false;  
    	
    	PurchaseAttributes otherAttrs = (PurchaseAttributes) obj;
    	    	
    	if(areDifferent(this.getAccessChannel(), otherAttrs.getAccessChannel()))	
    		return false;
    	
    	if(areDifferent(this.getPurchaseChannel(), otherAttrs.getPurchaseChannel()))	
    		return false;
    	
    	if(areDifferent(this.getDeviceID(), otherAttrs.getDeviceID()))	
    		return false;
    	
    	if(areDifferent(this.getMsisdn(), otherAttrs.getMsisdn()))	
    		return false;
    	
    	if(areDifferent(this.getPaymentInformation(), otherAttrs.getPaymentInformation()))	
    		return false;
    	
    	if(areDifferent(this.getContentName(), otherAttrs.getContentName()))	
    		return false;
    	
    	if(areDifferent(this.getAssetID(), otherAttrs.getAssetID()))	
    		return false;
    	
    	if(areDifferent(this.getPremiumLevel(), otherAttrs.getPremiumLevel()))	
    		return false;
    	
    	if(areDifferent(this.getDeviceType(), otherAttrs.getDeviceType()))	
    		return false;
    	
    	if(areDifferent(this.getPromoCode(), otherAttrs.getPromoCode()))	
    		return false;
    	
    	if(areDifferent(this.getBearer(), otherAttrs.getBearer()))	
    		return false;
    	
    	if(areDifferent(this.getUniquePromoCode(), otherAttrs.getUniquePromoCode()))	
    		return false;
    	
    	if(areDifferent(this.getContentName(), otherAttrs.getContentName()))	
    		return false;

    	if(areDifferent(this.mChannel, otherAttrs.mChannel))	
    		return false;

    	if(areDifferent(this.getPaymentType(), otherAttrs.getPaymentType()))		
    		return false;

    	if(areDifferent(this.getChannel(), otherAttrs.getChannel()))	
    		return false;
    	
    	if(areDifferent(this.mPromoCodes, otherAttrs.mPromoCodes)) {	
    		return false;
    	}
    	if(areDifferent(this.mBearerIds, otherAttrs.mBearerIds))	
    		return false;
    	
    	if(areDifferent(this.mUserGroups, otherAttrs.mUserGroups)) {
    		return false;
    	}
    	if((this.getLanguageLocale() != null) &&!(this.getLanguageLocale().equals(otherAttrs.getLanguageLocale())))
       		return false;
       	
    	if(this.getPreRate() != otherAttrs.getPreRate())
    		return false;

    	if(areDifferent(this.mTariff, otherAttrs.mTariff))	
       		return false;
       	
       	if(this.mInForcePurchaseFlow != otherAttrs.mInForcePurchaseFlow)	
    		return false;
    	
       	if(this.getEventUnits() != otherAttrs.getEventUnits())
    		return false;
       	
       	if(this.getDuration() != otherAttrs.getDuration())
       		return false;
       	
       	if(this.getChargingMethod() != otherAttrs.getChargingMethod())
       		return false;
       	
       	if(this.expressPurchaseFlag != otherAttrs.expressPurchaseFlag)	
       		return false;
    		
    	return true;
    }
    
    private boolean areDifferent(int first, int second){  	 	
    	if (first == second)
    		return false;
    	return true;
    }
    
    private boolean areDifferent(String first, String second){  	
    	if (first == null && second == null)
    		return false;
    	if((first == null && second != null) || (first !=null && second == null))
    		return true;  	
    	if (first.equals(second))
    		return false;
    	return true;
    }
    
    private boolean areDifferent(String[] first, String[] second)	{
    	if (first == null && second == null)
    		return false;
    	//MQC 9380 Null and Empty list are considered the same for this check
    	if ( (first == null && second != null && second.length ==0) || (first != null && first.length == 0 && second == null) )  {
    		return false;
    	}
    	if((first == null && second != null) || (first !=null && second == null))
    		return true;  	
    	if( Arrays.equals(first, second))
    		return false;
    	return true;
    }
    
    //CR1923 Partner Trading Limit
    public String getForcePurchaseServiceId() {
    	return this.mForcePurchaseServiceId;
    }
    
    public void setForcePurchaseServiceId(String forcePurchaseService) {
    	this.mForcePurchaseServiceId = forcePurchaseService;
    }

	public String getExternalSubId() {
		return externalSubId;
	}

	public void setExternalSubId(String externalSubId) {
		this.externalSubId = externalSubId;
	}
	
	//CR 2134 - pass in service id to the purchase in the express purchase flow
    public String getExpressPurchaseServiceId() {
    	return this.mExpressPurchaseServiceId;
    }
    
    public void setExpressPurchaseServiceId(String expressPurchaseService) {
    	this.mExpressPurchaseServiceId = expressPurchaseService;
    }

    //MQC 7424 Start
    public String getServiceId() {
    	return serviceId;
    }
    public void setServiceId(final String serviceId){
    	this.serviceId = serviceId;
    }
    //MQC 7424 End
    
    //MQC 9436 start
    public Boolean getIsValidatedAccount() {
		return isValidatedAccount;
	}
    
    public void setIsValidatedAccount(final Boolean isValidatedAccount) {
		this.isValidatedAccount = isValidatedAccount;
	}
    //MQC 9436 en
}
