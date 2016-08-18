package com.vizzavi.ecommerce.business.charging;

import java.util.Date;

import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.selfcare.Transaction;

/**
* Encapsulates the result of a refund authorization call to the charging subsystem.
*
* The user should check that the refund was successful. In unsuccessful, the ReasonCode object
* should be queried.
* <p>
* The following Refund ReasonCodes are applicable
* </p>
        OK<br>
        SYSTEM_ERROR<br>
        PAYMENT_FAILED<br>
        REFUND_EXCEED_MAX_CASH<br>
<br>
Example usage: <p>
<code>
<pre>

    try {
        String csrId = <the id of the customer service representative>;
        String reason = <the reason supplied by the CSR>;
        String transactionId = paymentTransaction.getId();
        double amount = <this could be the transaction amount if giving a full refund>;
        CustcareApi custcareApi = EcomApiFactory.getChargingApi( locale );
        custcareApi.setCustCareDetails(csrId, reason);
        RefundAuthorization authResult =
            custcareApi.refundTransactionMonetary( "myApp", msisdn,  transactionId, amount, paymentTransaction.getPurchaseCurrency(), new RefundAttributes());
        if( authResult.isSuccess() ) {
            // refund success
            ...
        } else {
            ReasonCode code = custcareApi.getReasonCode();
            if (code.equals(ReasonCode.PAYMENT_FAILED)) {
                // payment failed, the error id and description can be retrieved
                String errorId = authResult.getPaymentErrorId();
                String errorDescription = authResult.getPaymentErrorDescription();
            } else if (code.equals(ReasonCode.REFUND_EXCEED_MAX_CASH)) {
                // the refund amount is greater than the refudn amount allowed
            } else if (code.equals(ReasonCode.SYSTEM_ERROR)) {
                // payment failed, the error id and description can be retrieved.
                // these are set from Infranet
                String errorId = authResult.getPaymentErrorId();
                String errorDescription = authResult.getPaymentErrorDescription();

            } else {
                // another reason code
            }

        }


    } catch (EcommerceException e) {
        // system error in ecommerce system
        log error
    } catch (Exception e) {
        // system error in application
        log error
    }
</pre>
</code>

*
*/
public class RefundAuthorization extends BaseAuthorization {

   private    static final long serialVersionUID = 2800786827088308687L;
    private Transaction mRefundTransaction;
    private Transaction mPaymentTransaction;

//    /**
//     * @deprecated
//     * @param transactionId
//     * @param tran
//     * @param paymentTransaction
//     * @param code
//     * @param errorId
//     * @param errorDescription
//     */
//    @Deprecated
//	public RefundAuthorization(String transactionId, Transaction tran, Transaction paymentTransaction,
//        ReasonCode code, String errorId, String errorDescription)
//    {
//        super.transactionId = transactionId;
//        super.errorId = errorId;
//        super.errorDescription = errorDescription;
//        mRefundTransaction = tran;
//        mPaymentTransaction = paymentTransaction;
//        setReasonCode(code);
//        if (tran!=null) {
//            /***** We should subtract the tax from this ***/
//            /***** Or use the net rate *****/
//            mNetRate = tran.getPurchaseRate();
//            if( tran.getChargingResource() != null)
//            mCurrencyId = tran.getChargingResource().getCode();
//            mTaxRate=tran.getTaxRate();
//            if (errorId==null) {
//                super.errorId = tran.getPaymentErrorId();
//                super.errorDescription = tran.getPaymentErrorDescription();
//            }
//            this.packageSubscriptionId = tran.getSubscriptionId();
//            //paymentId = tran.getPaymentId();
//        }
//    }
    /**
     * construct a FAILED refund transaction - use this when the refund was rejected, so you don't have a refund txn to pass into the xtr
     * @param paymentTransaction
     * @param code
     * @param errorId
     * @param errorDescription
     */
    public RefundAuthorization(Transaction paymentTransaction, ReasonCode code, String errorId, String errorDescription)	{
    	super.errorId = errorId;
        super.errorDescription = errorDescription;
        mPaymentTransaction = paymentTransaction;
        setReasonCode(code);
        if (code.getCode() != ReasonCode.C_OK) 
			this.mIsSuccess = false;
		else 
			this.mIsSuccess = true;
    }

    public RefundAuthorization(long transactionId, Transaction tran, Transaction paymentTransaction,
            ReasonCode code, String errorId, String errorDescription)
        {
            super.mTransactionIdLong = transactionId;
            super.transactionId = String.valueOf(transactionId);
            
            super.errorId = errorId;
            super.errorDescription = errorDescription;
            mRefundTransaction = tran;
            mPaymentTransaction = paymentTransaction;
            setReasonCode(code);
            if (tran!=null) {
                /***** We should subtract the tax from this ***/
                /***** Or use the net rate *****/
                mNetRate = tran.getPurchaseRate();
                if( tran.getChargingResource() != null)
                mCurrencyId = tran.getChargingResource().getCode();
                mTaxRate=tran.getTaxRate();
                if (errorId==null) {
                    super.errorId = tran.getPaymentErrorId();
                    super.errorDescription = tran.getPaymentErrorDescription();
                }
                this.packageSubscriptionId = tran.getSubscriptionId();
                //paymentId = tran.getPaymentId();
            }
        }
    
    
    
	public RefundAuthorization()
	{
	}

	public RefundAuthorization(ReasonCode code)
	{
		this.reasonCode = code;
		if (code.getCode() != ReasonCode.C_OK) 
			this.mIsSuccess = false;
		else 
			this.mIsSuccess = true;
	}
    /**
        If a enlargement refund, this gives the new refund date
        @return the new package subscription expiry date
    */
    public Date getNewExpiryDate() {
        Date rv = null;
        if (mRefundTransaction!= null) {
			rv = mRefundTransaction.getRefundEnlargementDate();
		}
        return rv;
    }

    /**
        If a discount refund, this gives the next payment amount for a recurring package subscription
        @return the next payment amount when the recurring payment is made
    */
    public double getNextPaymentAmount()
    {
        double rv = -1;

        if (mPaymentTransaction!=null && mPaymentTransaction.getSubscription()!=null) {
            rv = mPaymentTransaction.getSubscription().getNextPaymentAmount();
        }
        return rv;
    }

    /**
        @return true if the refund was successful
    */
    @Override
	public boolean isSuccess()
    {
        return super.isSuccess();
    }

    /**
        Displays all of the data in the refund
    */
    @Override
	public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append(super.toString());
        sb.append("{");
        sb.append("transactionId=" + getTransactionId());
        sb.append(" isSuccess=" + isSuccess());
        sb.append(" transaction=" + getTransaction());
        sb.append(" paymentTransaction=" + getPaymentTransaction());
        sb.append(" authCode=" + getAuthCode());
        sb.append(" nextPaymentAmount=" + getNextPaymentAmount());
        sb.append(" expiryDate=" + getNewExpiryDate());
        sb.append("}");
        return sb.toString();
    }

    /**
        @return The authorization code returned from the payment system
    */
    @Override
	public String getAuthCode()
    {
        String rv = "";

        if (mRefundTransaction!=null) {
            rv = mRefundTransaction.getAuthCode();
        }
        return rv;
    }

    /**
        @return the associated payment transaction that the refund was made against
    */
    public Transaction getPaymentTransaction()
    {
        return mPaymentTransaction;
    }

    /**
        @return the transaction that was created as a result of this refund
    */
    public Transaction getTransaction()
    {
        return mRefundTransaction;
    }
}
