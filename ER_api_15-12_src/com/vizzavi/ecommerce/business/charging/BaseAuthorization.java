package com.vizzavi.ecommerce.business.charging;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.EcomApiFactory;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.common.ResponseStatus;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;
import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.business.selfcare.MicroServiceStatus;
import com.vodafone.global.er.payment.PaymentAuthStatus;
import com.vodafone.global.er.rating.RatedEvent;
import com.vodafone.global.er.rating.TaxRatedEvent;
import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Encapsulates the result of a authorization call to the charging subsystem.
 * For an authorization call, the isAuthorized() method should be checked to see if successful.
 * For a charging call, isCharged() should be checked to see if successful
 * If the call failed, the ReasonCode should be retrieved. The ReasonCode object gives more
 * information why a particular call failed.
 */
public class BaseAuthorization extends TaxRatedEvent implements java.io.Serializable
{
	private    static final long serialVersionUID = -4939204237621506879L;

	private static final transient Logger logger = LoggerFactory.getLogger(BaseAuthorization.class);
	
	/****************Angie German Migration **********/
	public static final int NO_RECEIPT       = -1;
	public static final int PURCHASE_RECEIPT = 0; 
	public static final int USAGE_COST       = 1;
	public static final int NILE_COST        = 2;
	public static final int CREDIT_COST      = 3;
	/*************************************************/   

	protected CatalogPackage mPackage;

	//@hud STKHREQ36 & RF
	private Subscription mSub = null;	// sub attached to this auth, we actually doesn't need to use subsIds[]

	
	/**
        This is the values denoting an error
        This is either populated by the payment system or by ER
	 */
	protected String errorId;
	protected String errorDescription;

	/**
        This is the reason code returned to the user
	 */
	protected ReasonCode reasonCode = ReasonCode.OK;


	/**
    	ER6: UC001:
		This is the sub reason code returned to the user,
		gives additional info given the main reason code.
	 */
	protected ReasonCode subReasonCode = ReasonCode.OK;

	/**
        This means that the payment was reserved
	 */
	protected boolean isReservedOnly = false;

	/**
	 * 
	 *   The associated subscription
	 */
	protected String packageSubscriptionId;
	protected long mPackageSubscriptionIdLong = -1;

	/**
		ER6: UC004. The user resource balance usage.
	 */
	protected ResourceBalance mUserResourceBalance = null;

	/**

	 */
	//@hud RFRFRF
	/**
	 * 
	 */
	protected String transactionId;
	protected long mTransactionIdLong = -1;
	
	
	//============================================================
	//@hud STKHREQ36
	protected List<MicroServiceStatus> mMicroServiceSubList = null;
	protected boolean	mHasValidMicroService = false;
	protected MicroServiceStatus mValidMicroServiceSub = null;	// this sounds redundant, but easier to handle
	//===========================================================
	
	
	
	protected String mAuthCode;
	
    //Change this and it will break old ecom clients
	protected int mPaymentStatus;
	
	//ResponseStatus is a new improved version of PaymentAuthStatus
	//private transient ResponseStatus	statusEnum;
	
	//set to transient so it's not serialized for ecom clients
	protected PaymentAuthStatus mPaymentStatusEnum;
	//TODO in the future (once we aren't worried about old ecom clients) 
	//delete PaymentAuthStatus class completely, since it duplicates ResponseStatus

	protected String mPaymentInfo;

	//PVCS 3414
	protected String paymentId;
	protected String mContentName;
	/**
        This is the same as the matching rating attributes
	 */
	protected PricePoint mPricePoint;

//	protected DRMObject m_DRMObject;

	protected double receiptingCreditBalanceImpact;
	protected int  receiptingUsageTypeAttribute = NO_RECEIPT; 


	protected boolean mInteractive=false;

	//Remedy 2314 
	protected ChargingResource chargingResource=null;


	protected String[] mSubscriptionIds;
	protected long[]	mSubscriptionIdsLong = null;

	/**
	 * Takes the value true if all pricepoints' submit flag (that the user can use)
	 * have the same value true. false otherwise.
	 * @todo Has to be reviewed
	 * @version ER8-2
	 */
	protected boolean mServiceSubmit = false;
	
	//CR1542 - add list of Active Subscriptions which can be stored and then passed onto
	// calls within multiple call flows, such as force purchase, thus avoiding additional
	// database calls
	
	protected List<Subscription> mActiveSubscriptions = null;
	
	protected boolean mHasActiveSubscriptions = false;

	//CR 2198 - add partner id
	protected String mPartnerId;

	
	public BaseAuthorization()
	{
	}

    //CR1564 -Utctimezone for diff region in country

	public BaseAuthorization(RatedEvent event) {
        this(event, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
	public BaseAuthorization(RatedEvent event, Date date)
	{
		super(event, date);
		if (event instanceof BaseAuthorization) {
			BaseAuthorization auth = (BaseAuthorization)event;
			errorId = auth.errorId;
			errorDescription = auth.errorDescription;
			transactionId = auth.transactionId;
			mAuthCode = auth.mAuthCode;
			mPaymentStatusEnum = auth.mPaymentStatusEnum;
			mPaymentStatus = auth.mPaymentStatus;
			isReservedOnly = auth.isReservedOnly;
			mPackage = auth.mPackage;
			mPricePoint = auth.mPricePoint;
			packageSubscriptionId = auth.getPackageSubscriptionId();
			mPackageSubscriptionIdLong = auth.getPackageSubscriptionIdLong();
			mPaymentInfo = auth.mPaymentInfo;
			mContentName = auth.mContentName;
			mNetRate = event.getNetRate();
			if (auth.getPaymentId() != null) {
				paymentId = auth.getPaymentId();
			}
			reasonCode = auth.getReasonCode();
			subReasonCode = auth.getSubReasonCode();
			paymentId = auth.paymentId;
			mInteractive = auth.mInteractive;
			mSubscriptionIds = auth.mSubscriptionIds;
			mServiceSubmit = auth.mServiceSubmit;
			
			//@hud STKHREQ36
			mMicroServiceSubList = auth.getMicroServiceSubList();
			mHasValidMicroService = auth.hasValidMicroService();
			mValidMicroServiceSub = auth.getValidMicroServiceSub();
			mSub = auth.getSubscription();
			//CR 2198 - add partner id
			mPartnerId = auth.getPartnerId();
			
		}
		
		//CR1429
		alwaysValidateMsisdn = event.isAlwaysValidateMsisdn();
	}

	/**
	 * Outputs the data in this bean
	 */
	 @Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer(super.toString());

		sb.append(  " isAuthorized=" );
		sb.append(  isAuthorized() );

		sb.append(  " isCharged=" );
		sb.append(  isCharged() );

		sb.append(  " reasonCode=" );
		sb.append(  reasonCode );

		sb.append(  " subReasonCode=" );
		sb.append(  subReasonCode );

		sb.append(  " isReservedOnly=" );
		sb.append(  isReservedOnly );


		sb.append(  " subscriptionId=" );
		sb.append(  packageSubscriptionId );

		sb.append(  " transactionId=" );
		sb.append(  getTransactionId() );

		sb.append(  " authCode=" );
		sb.append(  getAuthCode() );

		sb.append(  " errorId=" );
		sb.append(  getPaymentErrorId() );

		sb.append(  " errorDesc=" );
		sb.append(  getPaymentErrorDescription() );

		sb.append(  " paymentId=" );
		sb.append(  getPaymentId() );

		sb.append(  " eventReservationId=" );
		sb.append(  getEventReservationId() );

		sb.append(" mContentName=");
		sb.append(getContentName());

		sb.append(  " mPaymentInfo=" );
		sb.append(  getPaymentInfo() );

		sb.append(  " mInteractive=" );
		sb.append(  mInteractive );
		sb.append(  " mPricePoint=" );
//		if (mPricePoint!=null){
//			sb.append( mPricePoint.toString()  );        	
//		} else {
//			sb.append( "PRICEPOINT IS NULL"  ); 
//		}
		//CR 2198 - add partner id
		sb.append(  " mPartnerId=" );
		sb.append(  mPartnerId );
		
		sb.append(  "}" );

		return sb.toString();
	}

	 /**
	  * Query whether this customer was successfully authorized.
	  * 
	  * @return true for authorized, otherwise false
	  */
	 public boolean isAuthorized()
	 {
		 return isSuccess();
	 }

	 /**
	  * Query whether this customer was actually charged for this usage event.
	  * 
	  * @return true for charged, otherwise false
	  */
	 public boolean isCharged()
	 {
		 return isSuccess();
	 }


//	 /**
//	  * Does nothing!!  No seriously, it does literally nothing! <br/>
//	  * use {@link com.vodafone.global.er.rating.RatedEvent#setNetRate(double) RatedEvent.setNetRate} instead if you want to actually set the rate...
//	  * TODO comment out this method and update / remove all calls
//	  */
//	 public void setRate(double rate)
//	 {
//		 //this.rate = rate;
//	 }
	 /**
	  * Obtain the resource used to pay for this event (custom resource or currency).
	  * @return the resource which was used to pay for this event.
	  */
	 public ChargingResource getResource()
	 {
		 //Remedy 2314 - return this.chargingResource if set
		 if (this.chargingResource != null) {
			 return this.chargingResource;
		 }
		 else {
			 return ChargingResource.getResource(getCurrencyId());
		 }
	 }

	 /**
	  * Sets the resource used to pay for this event
	  * This is used internally by the ER2 system
	  * 
	  */
	 public void setResource(ChargingResource cr)
	 {
		 //Remedy 2314 - uncomment this line
		 this.chargingResource = cr;
	 }

	 /**
	  * Sets the user resource balance before usage authorization
	  */
	 public void setUserResourceBalance(ResourceBalance rb)
	 {
		 this.mUserResourceBalance = rb;
	 }

	 /**
	  * Gets the user resource balance before usage authorization
	  */
	 public ResourceBalance getUserResourceBalance()
	 {
		 return mUserResourceBalance;
	 }

	 /**
	  * Obtain the reasonCode code for an authorization result.
	  * @return the reasonCode for success/failiure.
	  */
	 public ReasonCode getReasonCode()
	 {
		 return this.reasonCode;
	 }

	 /**
	  * ER6: UC001:
	  * Obtain the subReasonCode code for an authorization result.
	  * @return the subReasonCode for success/failiure.
	  */
	 public ReasonCode getSubReasonCode()
	 {
		 return this.subReasonCode;
	 }

	 /**
        This value can be used to determinate why a payment has failed.
        If the ReasonCode object has a payment error, the error id is the error id returned by
        the payment system.
        If the ReasonCode object is a system error, the error id is set to id returned by the ER billing system
        If the OpCo has implemented the ERIf developer guidelines of March 2013, this value will be one of the following:
        <ul>
        <li>CONTENT_BLOCKED</li>
        <li>USER_SPEND_LIMIT</li>
        <li>SPEND_LIMIT</li>
        <li>INSUFFICIENT_FUNDS</li>
        <li>USER_SUSPENDED</li>
        <li>AMOUNT_INVALID</li>
        <li>REJECTED_OTHER</li>
        <li>USER_NOT_FOUND</li>
        <li>USER_INVALID</li>
        <li>USER_BARRED</li>
        <li>DENIED_OTHER</li>
        <li>VALIDATION_ERROR</li>
        <li>SYSTEM_ERROR</li>
        </ul>
        @see ResponseType 
        @return the error id from the payment system or the error id from the ER billing system
	  */
	 public String getPaymentErrorId()
	 {
		 return getErrorId();
	 }

	 /**
        This is set to either the error description returned from the payment system or to the name of the
        associated error id from the ER Billing system if a system error.
        
        @return the error description associated with the error id
	  */
	 public String getPaymentErrorDescription()
	 {
		 return getErrorDescription();
	 }

	 /**
        This value can be used to determinate why a payment has failed.
        If the ReasonCode object has a payment error, the error id is the error id returned by
        the payment system.
        This is the same as getPaymentErrorId()
        If the ReasonCode object is a system error, the error id is set to id returned by the ER billing system.
        If the OpCo has implemented the ERIf developer guidelines of March 2013, this value will be one of the following:
        <ul>
        <li>OK</li>
        <li>CONTENT_BLOCKED</li>
        <li>USER_SPEND_LIMIT</li>
        <li>SPEND_LIMIT</li>
        <li>INSUFFICIENT_FUNDS</li>
        <li>USER_SUSPENDED</li>
        <li>AMOUNT_INVALID</li>
        <li>REJECTED_OTHER</li>
        <li>USER_NOT_FOUND</li>
        <li>USER_INVALID</li>
        <li>USER_BARRED</li>
        <li>DENIED_OTHER</li>
        <li>VALIDATION_ERROR</li>
        <li>SYSTEM_ERROR</li>
        </ul>
        @see ResponseType 
        @return the error id from the payment system or the error id from the ER billing system
	  */
	 public String getErrorId()  {
		 return this.errorId;
	 }

	 /**
        This is set to either the error description returned from the payment system or to the name of the
        associated error id from the ER Billing system if a system error.
        This is the same as getPaymentErrorDescription()
        @return the error description associated with the error id
	  */
	 public String getErrorDescription()  {
		 return this.errorDescription;
	 }


	 /**
	  * Check if payment for the transaction has been reserved only.
	  * The client, can then call getTransactionId() to retrieve the
	  * a unique id to capture payment using usageComplete().
	  * @see com.vizzavi.ecommerce.business.charging.ChargingApi
	  * @return true if reserved; otherwise false
	  */
	 public boolean isReservedOnly()
	 {
		 return this.isReservedOnly;
	 }

	 /**
	  * This is the same as the transaction id
	  * 
	  * @return the ER2 payment id used to identify payment in the ER2 system
	  */
	 //PVCS 3414 reintroduced payment id to hold complex format of transaction id
	 public String getPaymentId()
	 {
		 if (this.paymentId == null) {
			 return getTransactionId();
		 }
		 else return this.paymentId;
	 }

	 /**
	  * Get the event reservation id. If this is a reserved event, this id can be used to complete payment.
	  * This is the same as the transaction id
	  * 
	  * @return the reservation id for this payment
	  */
	 public String getEventReservationId()
	 {
		 //return getTransactionId();
		 //PVCS 3795 return in complex format
		 return getPaymentId();
	 }

	 /**
	  * Set paymentid
	  * This is used internally by the ER2 system
	  *
	  */
	 public void setPaymentId( String paymentId )
	 {
		 //PVCS 3414 reintroduced payment id to hold complex format of transaction id
		 this.paymentId  =  paymentId;
	 }

	 /**
	  * 
        This id can be used to retrieve the associated subscription details for this event
        @return the last subscription id of the package associated with this event
	  */
	 public String getPackageSubscriptionId()
	 {
		 if (mSubscriptionIds != null && mSubscriptionIds.length > 0 ) {
			 return mSubscriptionIds[mSubscriptionIds.length-1];
		 } else {
			 return null;
		 }
	 }
	 public long getPackageSubscriptionIdLong() {
		 if (mPackageSubscriptionIdLong < 0) {
			 if (getSubscriptionIdsLong() != null && mSubscriptionIdsLong.length > 0) {
				 mPackageSubscriptionIdLong = mSubscriptionIdsLong[mSubscriptionIdsLong.length - 1];
			 }
		 }
		 return mPackageSubscriptionIdLong;
	 }

//	 /**
//	  * WARNING!  This method does nothing!  
//	  * 
//	  */
//	 public void setEventReservationId(String eventReservationId) {
//
//		 //this.eventReservationId = eventReservationId;
//	 }

	 /**
        The transaction id can be used in calls to getTransaction() and
        refund apis.
        
        @return the transaction id associated with this authorization
	  */
	 public String getTransactionId()
	 {
		 if (transactionId == null) {
			 if (mTransactionIdLong > 0) {
				 transactionId = Long.toString(mTransactionIdLong);
			 }
		 }
		 return transactionId;
	 }
	 //@hud RFRFRF
	 public long getTransactionIdLong() {
		 if (mTransactionIdLong == -1) {
			 
			 try {
				 mTransactionIdLong = Long.parseLong(transactionId);
			 }
			 catch (NumberFormatException ne) {
				 // nothing
				 mTransactionIdLong = -2;
			 }
			 
		 }
		 
		 return mTransactionIdLong;
	 }
	 public void setTransactionIdLong(long transactionIdLong) {
		 mTransactionIdLong = transactionIdLong;

                 /* ER 7 Compliant */
                 transactionId = Long.toString(mTransactionIdLong);
	 }

	 /**
        @return the authorization code from the payment system
	  */
	 public String getAuthCode()
	 {
		 return mAuthCode;
	 }


     /**
      * Supporting old clients
      */
	 public int getPaymentStatus()
	 {
		 return mPaymentStatus;
	 }

	 /**
	  * @deprecated use {@link #getStatusEnum} instead
	  * @return
	  * @see #getStatusEnum
	  */
	 @Deprecated
	public  PaymentAuthStatus getPaymentStatusEnum()
	 {
		 return mPaymentStatusEnum;
	 }

	 /**
	  *  use {@link #setPaymentStatus(ResponseStatus status)} instead
	  * @param status
	  */
	public void setPaymentStatus(PaymentAuthStatus status)
	 {
		 mPaymentStatusEnum = status;
		// statusEnum = status!=null?status.getResponseStatus():null;
                 //Set the int status
		 mPaymentStatus = status.getId();
	 }

	 /**
	  * 
	  * @return a {@link ResponseStatus}
	  * @see #getPaymentStatusEnum
	  */
	 public  ResponseStatus getStatusEnum()	{
		 return mPaymentStatusEnum.getResponseStatus();
	 }

	 public void setPaymentStatus(ResponseStatus status)	{
		 switch(status)	{
			 case ACCEPTED:	setPaymentStatus(PaymentAuthStatus.ACCEPTED);	break;
			 case DENIED:	setPaymentStatus(PaymentAuthStatus.DENIED);		break;
			 case REJECTED:	setPaymentStatus(PaymentAuthStatus.REJECTED);	break;
			 case ERROR:	setPaymentStatus(PaymentAuthStatus.ERROR);		break;
			 case INVALID_BAN:	setPaymentStatus(PaymentAuthStatus.INVALID_BAN);	break;
		 }
		 //statusEnum = status;
		 //mPaymentStatus = status.getId();
	 }
	 
	 /**
        This returns the information on what package the service was found in,
        if rating was successful
	  */
	 public CatalogPackage getPackage()
	 {
		 return mPackage;
	 }

	 /**
	  * this is probably a service-level pricepoint (e.g. for a usage), not the package-level pricepoint
	  * @return
	  */
	 public PricePoint getPricePoint()	 {
		 if (mPricePoint == null) {
			 try {
				 logger.warn("pricepoint is null - retrieving via ecom / delegate layer");
				 mPricePoint= EcomApiFactory.getPricePoint(mMatchingAttributes);
			 }	 catch (EcommerceException e)	 {
				 e.printStackTrace();
			 }
		 }
		 return mPricePoint;
	 }


	 /**
	  * Set the reason code for an authorization result
	  * This is used internally by the ER2 system
	  * @param c the reason code for success/failure
	  */
	 public void setReasonCode(ReasonCode c)
	 {
		 this.reasonCode = c;
	 }

	 /**
	  * ER6: UC001:
	  * Set the sub reason code for an authorization result
	  * This is used internally by the ER2 system
	  * @param c the reason code for success/failure
	  */
	 public void setSubReasonCode(ReasonCode c)
	 {
		 this.subReasonCode = c;
	 }

	 /**
	  * Set the transaction id
	  * This is used internally by the ER2 system
	  */
	 public void setTransactionId(String transactionId)
	 {
		 this.transactionId = transactionId;
	 }

	 /**
	  * Set the reserved flag.
	  * This is used internally by the ER2 system
	  */
	 public void setReserved( boolean res )
	 {
		 this.isReservedOnly = res;
	 }

	 public void setPackage(CatalogPackage pack)
	 {
		 mPackage = pack;
	 }


	 public void setPricePoint(PricePoint pt)
	 {
		 mPricePoint = pt;
	 }

	 public void setCharged(boolean val)
	 {
		 mIsSuccess = val;
	 }

	 public void setAuthCode(String authCode)
	 {
		 mAuthCode = authCode;
	 }

	 @Override
	public void setTaxAmount(double taxAmount)
	 {
		 mTaxAmount = taxAmount;
	 }

	 @Override
	public double getTaxAmount()
	 {
		 return mTaxAmount;
	 }

	 public void setErrorDescription(String s)
	 {
		 errorDescription = s;
	 }


	 public void setErrorId(String s)
	 {
		 errorId = s;
	 }


	 public void setAuthorized(boolean a)
	 {
		 setIsSuccess(a);
	 }

	 public boolean getAuthorized()
	 {
		 return isSuccess();
	 }

	  
	  
	  
	  
	  

//	 /**
//	  * @since ER 5.1
//	  * @return DRMObject
//	  */
//	 public DRMObject getDRMObject()
//	 {
//		  return m_DRMObject;
//	 }
//	  
//	  /**
//	   * @since ER 5.1
//	   * @param drmobject
//	   */
//	  public void setDRMObject(DRMObject drmobject)
//	  {
//		  m_DRMObject = drmobject;
//	  }

	  public void setPackageSubscriptionId(String newPackageSubscriptionId )
	  {
                  /* ER 7 Compliant */
                  packageSubscriptionId = newPackageSubscriptionId;


		  mSubscriptionIds = new String[1];
		  mSubscriptionIds[0] = newPackageSubscriptionId;
	  }


	  public String getPaymentInfo()
	  {
		  return mPaymentInfo;
	  }

	  public void setPaymentInfo(String newPaymentInfo)
	  {
		  mPaymentInfo = newPaymentInfo;
	  }

	  public String getContentName()
	  {
		  return mContentName;
	  }

	  public void setContentName(String newContentName)
	  {
		  mContentName = newContentName;
	  }
	  /**
	   * @return true if the usage is interactive.
	   * @since ER 6
	   */
	  public boolean isInteractive()
	  {
		  return mInteractive;
	  }

	  /**
	   * @since ER 6
	   */
	  public void setInteractive(boolean interactive)
	  {
		  mInteractive = interactive;
	  }
	  /****************Angie German Migration **********/
	  public void setReceiptingCreditBalanceImpact(double pReceiptingCreditBalanceImpact)
	  {    
		  this.receiptingCreditBalanceImpact  = pReceiptingCreditBalanceImpact;
	  }
	  public double getReceiptingCreditBalanceImpact()
	  {    
		  return this.receiptingCreditBalanceImpact  ;
	  }
	  public void setReceiptingUsageTypeAttribute(int pReceiptingUsageTypeAttribute)
	  {    
		  this.receiptingUsageTypeAttribute  = pReceiptingUsageTypeAttribute;
	  }
	  public int getReceiptingUsageTypeAttribute()
	  {    
		  return this.receiptingUsageTypeAttribute  ;
	  }  

	  public String[] getSubscriptionIds(){
		  return mSubscriptionIds;
	  }

	  public boolean isServiceSubmit(){
		  return mServiceSubmit;
	  }

	  //@hud RFRFRF
	  public long[] getSubscriptionIdsLong() {
		  if (mSubscriptionIdsLong == null) {
			  // do some parsing
			  if (mSubscriptionIds != null) {
				  mSubscriptionIdsLong = new long[mSubscriptionIds.length];
				  for (int i = 0; i < mSubscriptionIds.length; ++ i) {
					  try {
						  mSubscriptionIdsLong[i] = Long.parseLong(mSubscriptionIds[i]);
					  }
					  catch (NumberFormatException ne) {
						  mSubscriptionIdsLong[i] = -1;
					  }
				  }
			  }
		  }
		  
		  return mSubscriptionIdsLong;
	  }
	  
	  
	  
	  //@hud STKHREQ36
	  public List<MicroServiceStatus> getMicroServiceSubList() {
		  return mMicroServiceSubList;
	  }
	  public void setMicroServiceSubList(List<MicroServiceStatus> al) {
		  mMicroServiceSubList = al;
	  }
	  public boolean hasValidMicroService() {
		  return mHasValidMicroService;
	  }
	  public void setHasValidMicroService(boolean b) {
		  mHasValidMicroService = b;
	  }
	  public MicroServiceStatus getValidMicroServiceSub() {
		  return mValidMicroServiceSub;
	  }
	  public void setValidMicroServiceSub(MicroServiceStatus mss) {
		  mValidMicroServiceSub = mss;
	  }
  
	//@hud STKRHEQ36 RF
	public void setSubscription(Subscription sub) {
		mSub = sub;
	}
	public Subscription getSubscription() {
		return mSub;
	}
	
	//CR1542
	public List<Subscription> getActiveSubscriptions()
	{
        return mActiveSubscriptions;
    }

	//CR1542
	public void setActiveSubscriptions(List<Subscription> subs)
	{
		if (subs != null) {
			mActiveSubscriptions = new ArrayList<Subscription>();
			mActiveSubscriptions = subs;
		}	
    }
	
	//CR1542
	public boolean hasActiveSubscriptions() {
		return mHasActiveSubscriptions;
	}

	//CR1542
	public void setHasActiveSubscriptions(boolean hasActiveSubs) {
		mHasActiveSubscriptions = hasActiveSubs;
	}

	/**
	 * CR 2198
	 * @return the mPartnerId
	 */
    public String getPartnerId() {
		return mPartnerId;
	}
    
    /**
     * CR 2198
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(String partnerId) {
		this.mPartnerId = partnerId;
	}

	/**
	 * MQC-11978 - purchase failure when ZERORATE_PURCHASE_PAYMENT_CALL parameter is set to OFF
	 * If an ACCEPTED payment auth has been received from the opco, then the auth code must be mandatory,
	 * unless zero cost payment has been configured to not to send to the opco, then the auth code is ok to be blank.
	 * If REJECTED, DENIED or ERROR returned by the opco then return true
	 * 
	 * @return boolean
	 */
	public boolean isValid() {
		boolean rv = true;
		
		if (getStatusEnum().isAccepted()) {
			boolean ZERO_PURCHASE_PAYMENT_HANDLER_CALL = ConfigProvider.getPropertyAsBoolean("transctrl.bl.PurchaseTransaction.ZERORATE_PURCHASE_PAYMENT_CALL", true);
			if (isBlank(getAuthCode()) && ZERO_PURCHASE_PAYMENT_HANDLER_CALL) {
				rv = false;
			}
		} 
		
		return rv;
	}
}
