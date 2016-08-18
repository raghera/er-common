package com.vizzavi.ecommerce.business.charging;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;
import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vizzavi.ecommerce.business.selfcare.ValidateMsisdnAttributes;
import com.vizzavi.ecommerce.util.MiscUtils;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.payment.PaymentAuthStatus;
import com.vodafone.global.er.rating.RatedEvent;



/**
* Encapsulates the result of a purchase authorization call to the charging subsystem.
* i<br/><pre>$Id: PurchaseAuthorization.java,v 1.25 2013/09/26 14:23:36 matt.darwin Exp $</pre>
*/
public class PurchaseAuthorization extends BaseAuthorization implements java.io.Serializable{
   // private final Hashtable flistInfo = null;
   private    static final long serialVersionUID = 2704297195295293234L;

   //MQC 7434 - Add validation attributes
   ValidateMsisdnAttributes validateAttributes = null;
   
   
   //@hud RFRFRF this only called in test codes
    public PurchaseAuthorization(String subscriptionId, String code, 
    		PaymentAuthStatus status,	//@hud int status, 
    		double rate, double taxRate, int currency, String errorId, String errorDescription, List<Subscription> subscriptions)
    {
        super.packageSubscriptionId = subscriptionId;
        mAuthCode = code;
       setPaymentStatus(status);
        //setRate(rate);
        //setResource(ChargingResource.getResource(currency));
        //this.taxRate = taxRate;
        this.errorId = errorId;
        this.errorDescription = errorDescription;
        this.mSubscriptions = subscriptions;
    }

    /**
        This represents the balances purchased by the package
    */
    protected ResourceBalance[] mBalances;


    /**
        The list of subscriptions, if there was a renwal match
    */
    protected List<Subscription> mSubscriptions;

    public PurchaseAuthorization() {}

    public PurchaseAuthorization(CatalogPackage pack) {
        super.mPackage = pack;
    }
//    /**
//        Used for complex rating of a purchase.
//        This date can be placed in the PurchaseAttributes class when doing a purchase to speed up the call.
//        Sets the
//        @deprecated
//    */
//    @Deprecated
//	public Hashtable getFlistInfo() {
//        return null;
//    }

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public PurchaseAuthorization(RatedEvent event) {
        this(event, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
    public PurchaseAuthorization(RatedEvent event, Date date)
    {
        super(event, date);
        if (event instanceof PurchaseAuthorization) {
            PurchaseAuthorization opt = (PurchaseAuthorization)event;
            mBalances = opt.mBalances;
            if (opt.mSubscriptions!=null) {
                mSubscriptions = new ArrayList<Subscription>(opt.mSubscriptions);
            }
        }
        // VFE-ER8.0 (Record Undiscounted price on discounted purchases).  This purpose of this logic is to Store the value of the Standard rate from the rated event to the purchase authorization
        this.setUndiscountedStandardRate(event.getUndiscountedStandardRate());
    }



    /**
        This is used for asynchronous payments and allows the purchase app to redirect the
        user to the appropriate payment system.
        @return the URL to transfer the user to.
    */
    public String getTransferUrl()
    {
        return getAuthCode();
    }

    /**
        This is used internally by the ER2 system
    */
    @Override
	public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append(  " subscriptions=" );
        sb.append( mSubscriptions == null ? "null" : mSubscriptions.toString() );

        return sb.toString();
    }

    /**
        If the package has already been purchased (ie duplicate promo code or an active subscripton),
        the matching subscriptions are returned.
        @return the array of subscriptions that the customer has.
    */
    public Subscription[] getSubscriptions()
    {
        Subscription [] rv = null;
        if (mSubscriptions!=null && mSubscriptions.size()>0) {
            rv = mSubscriptions.toArray(new Subscription[mSubscriptions.size()]);
        }

        return rv;
    }

	//Remedy 1548 - added setter method
	public void setSubscriptions(Subscription[] subscriptions)
	{
		mSubscriptions = new ArrayList<Subscription>();
		if (subscriptions != null && subscriptions.length>0) {
			for (Subscription subscription : subscriptions) {
				mSubscriptions.add(subscription);
			}
		}
    }

    /**
        The custom resource balances that the customer gets
    */
    public ResourceBalance[] getResourceBalances()
    {
        return mBalances;
    }

    public void setResourceBalances(ResourceBalance[] balances)
    {
        mBalances = balances;
    }
    
    /**
     * MQC 4027. Return the standard gross rate
     * @return
     */
	public double getUndiscountedStandardGrossRate ()
	{
		double result = 0.0;
		
		if(mStandardRate ==0)
		{
			int round_nth_decimal = ConfigProvider.getPropertyAsInteger("ROUND_NTH_DECIMAL",0);
			
	    	if (round_nth_decimal == -1) {
	    		return (mNetStandardRate + (mNetStandardRate * getTaxRate()));
	    	}
	    	
	        // performing rounding before returning.
	        result = MiscUtils.roundDouble(mNetStandardRate + (mNetStandardRate * getTaxRate()),round_nth_decimal);
		}
		else
		{
			result = mStandardRate;
		}
        return result;
	}    
	
	// MQC 7424 Start
	public ValidateMsisdnAttributes getValidateMsisdnAttributes() {
		return validateAttributes;
	}
	public void setValidateMsisdnAttributes(ValidateMsisdnAttributes validateMsisdnAttributes) {
		
		validateAttributes = validateMsisdnAttributes;
		
	}
	
	// MQC 7424 End
}
