package com.vizzavi.ecommerce.business.selfcare;

/**
    The attributes that the user can set when getting back a transaction history

    They can set the
        Start date
        Successful transactions
        Non zero transactions
*/
public interface TransactionFilter extends FilterAttributes
{
    public static String ALL_TRANSACTIONS = "all";
    public static String REFUND_TRANSACTIONS_ONLY = "refund";
    public static String REFUND_PAYMENT_TRANSACTIONS_ONLY = "refund_payment";
    public static String PAYMENT_TRANSACTIONS_ONLY = "payment";
    public static String MODIFY_TRANSACTIONS_ONLY = "modify";
    
    //CR1886 - retrieve all transactions for any partner
    public static String ALL_PARTNER_TRANSACTIONS = "ALL";
    
    //@hud RFRFRF
    public void setTransactionIdLong(long tranId);
    public long getTransactionIdLong();
    
    /**
        used internally by ER2
    */
    public boolean isNoZeroCostEvents();

    /**
        used internally by ER2
    */
    public boolean isMonetaryEventsOnly();

    /**
        This only returns payment transactions
        setting setTransactionType(TransactionFilter.PAYMENT_TRANSACTIONS_ONLY);
        default value is false.
    */
    public void setMonetaryEventsOnly(boolean flag);

    /**
        This ignores zero cost events. The default is true.
        This does not be used if the client is enabled to not create
        a payment record for zero cost events.
    */
    public void setNoZeroCostEvents(boolean flag);

    /**
     * @deprecated use setSubscriptionIdLong instead
     * @param subscriptionId
     */
    @Deprecated
    public void setSubscriptionId(String subscriptionId);

    /**
        This only returns transactions that were successful.
        Failed transactions would be ytransactions that failed due to system
        errors or if they failed in the payment handler.
    */
    public void setActiveEventsOnly(boolean flag);

    /**
        This chooses which type of transaction to return.
        The choice is
            ALL_TRANSACTIONS (default)
            REFUND_TRANSACTIONS_ONLY
            REFUND_PAYMENT_TRANSACTIONS_ONLY
            PAYMENT_TRANSACTIONS_ONLY
            MODIFY_TRANSACTIONS_ONLY

    */
    public void setTransactionType(String val);
    

	public void setOrderByObject(String orderByObject);
	
	/**
     * This method is used to setup paging within the filter.
     * @param page The requested page of results.
     * @param pageSize The size of the page to be returned.  For example, 100 records.
     */
    public void setPaging(int page, int pageSize);
    
    
    /**
     * Added: 13-09-05 
     * Added for ER8 phase2 
     * Added by : VFE - PS team
     * Purpose: Usage of credits from multiple packages
     * SelfCare Module: Parent transaction
     * Description: This Sets the value of the parentTransactionId, 
     *              which is the id of a group Transactions from multiple packages
    */
   public void setParentTransactionId(String  mParentTransactionId);
   /**
     * Added: 13-09-05 
     * Added for ER8 phase2 
     * Added by : VFE - PS team
     * Purpose: Usage of credits from multiple packages
     * SelfCare Module: Parent transaction
     * Description: This Gets the value of the parentTransactionId, 
     *              which is the id of a group Transactions from multiple packages
    */
   public String getParentTransactionId();
   
   
   //	@mawn R8.0 STKHREQ10301 missing implementation.  Added in R9.0 CQPRD00015358
   public void setExternalField1(String externalField1) ;
	
   public void setExternalField2(String externalField2) ;
	
   public void setExternalTransId(String externalTransId);
   
   //CR 1029 ACR - add partner id to transaction filter
   public void setPartnerId(String partnerId);
   
   public String getPartnerId();
   
   //MQC 6137 - add flag to retrieve only lite transactions
   public void setLiteTransactionsOnly(boolean flag);
   
	// @mawn R9.0 zero cost ignore flag
   public void setZeroCostIgnore(boolean zeroCostIgnore) ;
   public boolean isZeroCostIgnore();
   
   public void setSubscriptionIdLong(long id);
	boolean isActiveEventsOnly();
	boolean isModifyEventsOnly();
	boolean isRefundEventsOnly();
	long getSubscriptionIdLong();
	String getTransactionType();
	public  boolean isAllEvents();
	public  boolean isRefundPaymentsEventsOnly();
   
}
