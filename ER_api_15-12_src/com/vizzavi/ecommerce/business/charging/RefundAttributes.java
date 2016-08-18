package com.vizzavi.ecommerce.business.charging;

import com.vizzavi.ecommerce.business.common.BaseAttributes;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.resources.PropertyDataBool;

/**
    The attributes that the user can set when making a refund
*/
public class RefundAttributes extends BaseAttributes
{
   private    static final long serialVersionUID = -9145052915920229685L;
   
   
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	//@hud Flags that were/are set in er2.properties
	// The reason we set it here is to make it fully configurable and programmable
	// Testing codes will only use parameter while real product will use the values
	// defined in er2.properties
	// This implementationn makes dollars
	
	// In order to implement this, we need 2 sets of variables
	// 1. static CONSTANT from er2.properties
	// 2. instance field that will be initialized with CONSTANT and can be set later
	//public static final int CFG_CREDIT_REFUND_REQUEST_PAYMENT_HANDLER = 
	//			PropertyServer.getPropertyBool("CREDIT_REFUND_REQUEST_PAYMENT_HANDLER");
	//protected int mCreditRefundRequestPaymentHandler = CFG_CREDIT_REFUND_REQUEST_PAYMENT_HANDLER;
	private final PropertyDataBool mCreditRefundRequestPaymentHandler = new PropertyDataBool();
	public boolean needNotifyCreditRefundRequestPaymentHandler()  {
		return mCreditRefundRequestPaymentHandler.isTrue();
	}
	public void setNotifyCreditRefundRequestPaymentHandler(boolean bOn) {
		mCreditRefundRequestPaymentHandler.setVal(bOn);
	}
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////
	// roaming flag
	//private boolean mRefundRoamingCharge		= false;
	
	
	
	//////////////////////////////////////////////////////
	// normalize the parameters
	@Override
	public void normalize()
	{
		super.normalize();
		//mCreditRefundRequestPaymentHandler.normalize(ErCoreConst.CFG_CREDIT_REFUND_REQUEST_PAYMENT_HANDLER());
	}
   
   
	///////////////////////////////////////////////////////
	//change this field but test using TestNextPaymentAmount or TestRealTimeRefund
	private String mCsrId = "";
    private String mReason = "";
    private String mDescription = "";
    private String mSubscriptionId = "";
    protected int mDeprovisionFlag = 0;
    
    
    //@hud CREDIT REFUND
    // This is kind of refactoring, 
    // it'll be much easier and extensible if we pass everything in 
    // this class
    // Currently will only be applied to refundTransactionCredit
    // which will only take ONE parameter, RefundAttributes
    /**
     * 
     */
    protected String		mPaymentTransactionId = null;
    /** 
     * 
     */
    protected String		mParentTransactionId = null;
    protected long 			mPaymentTransactionIdLong = -1;
    protected long			mParentTransactionIdLong = -1;
    
    
    protected double 			mRefundCreditAmount = 0;
    protected ChargingResource 	mChargingResource = null;
    
    //CR-1448 - add merchant name and invoice text
	private String mMerchantName = null;
	private String mInvoiceText = null;

	//MQC6137 - add partner id to monetary refund
	protected String mPartnerId = null;
	
	//CR2082 Google DCB3 - add flag to determine whether the full transaction amount should be the refund amount, 
	//only applicable for monetary refunds
	protected boolean mRefundFullAmount = false;
	
	
    public void setReason(String s) {
        mReason = s;
    }

    /**
        This is used internally by the ER2 system
    */
    public String getReason() {
        return mReason;
    }

	@Override
	public void setCsrId(String val){
		mCsrId = val;
	}

	@Override
	public String getCsrId(){
		return mCsrId;
	}



    /**
        The description to associate with this refund for customer care purposes.
        This data can be viewed in the refund transaction.
    */
    public void setDescription(String s) {
        mDescription = s;
    }

    /**
        This is used internally by the ER2 system
    */
    public String getDescription() {
        return mDescription;
    }

    /**
        This should not be used anymore as refund works it out from the transaction.
        
    */
    public void setSubscriptionId(String s) {
        mSubscriptionId = s;
    }

    /**
        This should not be used anymore as refund works it out from the transaction.
        
    */
    public String getSubscriptionId() {
        return mSubscriptionId;
    }

    public void setDeprovisionFlag(int i) {
        mDeprovisionFlag = i;
    }

    /**
        This is used internally by the ER2 system
    */
    public int getDeprovisionFlag() {
        return mDeprovisionFlag;
    }

    /**
        This is used internally by the ER2 system
    */
    @Override
	public String toString()
    {
        StringBuffer buf = new StringBuffer(super.toString());
        buf.append("mReason = " + mReason).append('\n');
        buf.append("mDescription = " + mDescription).append('\n');
		buf.append("mDeprovision = " + mDeprovisionFlag).append('\n');
		buf.append("mPaymentTransactionId = " + mPaymentTransactionId).append('\n');
		buf.append("mParentTransactionId = " + mParentTransactionId).append('\n');
		buf.append("mRefundCreditAmount = " + mRefundCreditAmount).append('\n');
		buf.append("mChargingResource = " + mChargingResource).append('\n');
		buf.append("mPartnerId = " + mPartnerId).append('\n');
        return buf.toString();
    }

	/**
	 * @return Returns the mChargingResource.
	 */
	public ChargingResource getChargingResource() {
		return mChargingResource;
	}

	/**
	 * @param chargingResource The mChargingResource to set.
	 */
	public void setChargingResource(ChargingResource chargingResource) {
		mChargingResource = chargingResource;
	}

	/**
	 * 
	 * @return Returns the mPaymentTransactionId.
	 */
	public String getPaymentTransactionId() {
		if (mPaymentTransactionId == null) {
			if (mPaymentTransactionIdLong > 0) {
				mPaymentTransactionId = Long.toString(mPaymentTransactionIdLong);
			}
		}
		return mPaymentTransactionId;
	}

	/**
	 * 
	 * @param paymentTransactionId The mPaymentTransactionId to set.
	 */
	public void setPaymentTransactionId(String paymentTransactionId) {
		mPaymentTransactionId = paymentTransactionId;
	}
	public void setPaymentTransactionIdLong(long id) {
		mPaymentTransactionIdLong = id;
	}
	public long getPaymentTransactionIdLong() {
		if (mPaymentTransactionIdLong == -1) {
			if (mPaymentTransactionId != null) {
				try {
					mPaymentTransactionIdLong = Long.parseLong(mPaymentTransactionId);
				}
				catch (NumberFormatException ne) {
					// nothing
					mPaymentTransactionIdLong = -2;
				}
			}
		}
		return mPaymentTransactionIdLong;
	}
	

	/**
	 * @return Returns the mRefundCreditAmount.
	 */
	public double getRefundCreditAmount() {
		return mRefundCreditAmount;
	}

	/**
	 * @param refundCreditAmount The mRefundCreditAmount to set.
	 */
	public void setRefundCreditAmount(double refundCreditAmount) {
		mRefundCreditAmount = refundCreditAmount;
	}

	/**
	 * 
	 * @return Returns the mParentTransactionId.
	 */
	public String getParentTransactionId() {
		if (mParentTransactionId == null) {
			if (mParentTransactionIdLong > 0) {
				mParentTransactionId = Long.toString(mParentTransactionIdLong);
			}
		}
		return mParentTransactionId;
	}

	/**
	 * 
	 * @param parentTransactionId The mParentTransactionId to set.
	 */
	public void setParentTransactionId(String parentTransactionId) {
		mParentTransactionId = parentTransactionId;
	}
	
	public void setParentTransactionIdLong(long id) {
		mParentTransactionIdLong = id;
	}
	public long getParentTransactionIdLong() {
		
		if (mParentTransactionIdLong == -1) {
			if (mParentTransactionId != null) {
				try {
					mParentTransactionIdLong = Long.parseLong(mParentTransactionId);
				}
				catch (NumberFormatException ne) {
					// nothing
					mParentTransactionIdLong = -2;
				}
			}
		}
		return mParentTransactionIdLong;
	}

	
	//@hud STKHREQ13076
//	public void setRefundRoamnigCharge(boolean refundRoamingCharge) {
//		mRefundRoamingCharge = refundRoamingCharge;
//	}
//	public boolean needRefundRoamingCharge() {
//		return mRefundRoamingCharge;
//	}
	
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
	
	//MQC6137 - add partner id to monetary refund
	public String getPartnerId() {
        return mPartnerId;
    }      
	
	public void setPartnerId(String partnerId) {
        mPartnerId = partnerId;
    }
	
	//CR2082 Google DCB3 - add flag to determine whether the full transaction amount should be the refund amount, 
	//only applicable for monetary refunds
	public boolean isRefundFullAmount() {
		return mRefundFullAmount;
	}      
		
	public void setRefundFullAmount(boolean refundFullAmount) {
		mRefundFullAmount = refundFullAmount;
	}
	
}
