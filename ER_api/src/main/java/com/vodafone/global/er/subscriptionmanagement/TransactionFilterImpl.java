package com.vodafone.global.er.subscriptionmanagement;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import com.vizzavi.ecommerce.business.selfcare.TransactionFilter;


/**
    The attributes that the user can set when getting back a transaction history

    They can set the
        Start date
        Successful transactions
        Non zero transactions
*/
public class TransactionFilterImpl extends FilterAttributesImpl implements TransactionFilter
{
   private    static final long serialVersionUID = -6186493255536404632L;
    // to check if amount is < 1000 { checks the boolean monetary field in er_resources, if true will return all transactions for which amount < 1000}
    protected boolean mOnlyMonetaryEventsFlag;

	// get all transactions for which the amount is not zero
    protected boolean mNoZeroCostEventsFlag;

	// check if the set method was ever called.
	protected boolean mNoZeroCostEventsSetFlag=false;

    // stores the current page value.
    private int _currentPage = -1;

	// get all transacions for this Subscription ID

    protected long mSubscriptionIdLong = -1;


    // if true get all transactions for which status is set to complete
    protected boolean mSuccessfulEventsFlag = false;

	protected String mTransactionType = ALL_TRANSACTIONS;

    // the returned collection object should have only one transaction, whose transaction id is set

	protected long mTransactionIdLong = -1;

	// this variable needs to be set in TransactionDao
	protected String msisdn = null;

	// is set to true get RatingAttributes, ResourceBalances, ERSubscriptions along with ERTransaction else only ERTransaction
	protected boolean mRelatedObjcts = false;

    // if true get all transactions for which status is set to reserved
	protected boolean mReserverOnlyFlag = false;

    // this variable needs to be set in TransactionDao
	protected String mTransactionName = null ;

	// get this refund transaction only
	protected Long mRefundId = null ;

	//Remedy 3418 get this modify transaction only
	protected Long mModifyId = null ;


     // SelfCare Module: Parent transaction used to group together transactions for multiple packages
 
	protected long mParentTransactionIdLong = -1;

	//	@mawn R8.0 STKHREQ10301 missing implementation.  Added in R9.0 CQPRD00015358
	protected String mExternalTransId;

	protected String mExternalField1 ;

	protected String mExternalField2 ;
	
	//CR 1029 ACR - add partner id to transaction filter
	protected String mPartnerId = null;
	
	//MQC 6137 - add flag to retrieve only lite transactions
	protected boolean mLiteTransactionsOnly = false;
	
	// @mawn R9.0

    
	protected boolean zeroCostIgnore = false ;

	/* MQC 6128 - Sometimes it is not necessary to retrieve
	 * the Subscription for every transaction returned
	 * for performance reasons.
	 */ 
	private boolean retrieveSubscriptionForTrans = true;
	
	
	
	/* Status = TransactionStatus.ACTIVE
	*/
	@Override
	public boolean isActiveEventsOnly()
    {
        return mSuccessfulEventsFlag;
    }


	/* Status = TransactionStatus.ACTIVE

	*/
    @Override
	public void setActiveEventsOnly(boolean flag)
    {
        mSuccessfulEventsFlag = flag;
    }

	/* Amount != 0
	*/
    @Override
	public boolean isNoZeroCostEvents()
    {
        return mNoZeroCostEventsFlag;
    }


	/* resourceID <1000
	*/
    @Override
	public boolean isMonetaryEventsOnly()
    {
        return isTransactionTypeSet(PAYMENT_TRANSACTIONS_ONLY);
    }


	/* Transaction.Type ==
	*/
   @Override
    public boolean isModifyEventsOnly()
    {
        return isTransactionTypeSet(MODIFY_TRANSACTIONS_ONLY);
    }

   @Override
    public boolean isRefundEventsOnly()
    {
        return isTransactionTypeSet(REFUND_TRANSACTIONS_ONLY);
    }

    /**@deprecated - use {@link #isRefundEventsOnly} instead*/
    @Override
	public boolean isRefundPaymentsEventsOnly()
    {
        return isTransactionTypeSet(REFUND_PAYMENT_TRANSACTIONS_ONLY);
    }

    @Override
	public boolean isAllEvents()
    {
        return isTransactionTypeSet(ALL_TRANSACTIONS);
    }

    private boolean isTransactionTypeSet(String val)
    {
        boolean rv = false;
        if (mTransactionType!=null && val!=null) {
            rv = mTransactionType.equals(val);
        }
        return rv;
    }

    @Override
	public void setMonetaryEventsOnly(boolean flag)
    {
        if (flag==true) {
            setTransactionType(PAYMENT_TRANSACTIONS_ONLY);
        } else if (isMonetaryEventsOnly()) {
            setTransactionType(ALL_TRANSACTIONS);
        }

        mOnlyMonetaryEventsFlag = flag;
    }


    @Override
	public void setNoZeroCostEvents(boolean flag)
    {
    	mNoZeroCostEventsSetFlag=true;
    	mNoZeroCostEventsFlag = flag;
    }

    public boolean isNoZeroCostEventsSet()
    {
    	return mNoZeroCostEventsSetFlag;
    }

    /**
     * pass in null to reset the filter
     * @deprecated use setSubscriptionIdLong instead
     */
    @Deprecated
	@Override
	public void setSubscriptionId(String subscriptionId)
    {
        if (StringUtils.isNotBlank(subscriptionId))
        	mSubscriptionIdLong=Long.parseLong(subscriptionId);
        else 
        	mSubscriptionIdLong=-1;
    }

    /**
     * @deprecated use getSubscriptionIdLong instead
     */
    @Deprecated
	public String getSubscriptionId()
    {
    	if (mSubscriptionIdLong > 0)
    		return String.valueOf(mSubscriptionIdLong);
    	else
    		return null;
    }

    //@hud RFRFRF
    @Override
    public void setSubscriptionIdLong(long id) {
    	mSubscriptionIdLong = id;
    }
    
    @Override
    public long getSubscriptionIdLong() {

    	return mSubscriptionIdLong;
    }
    
    /**
     * This method is used to setup paging within the filter.
     * @param page The requested page of results.
     * @param pageSize The size of the page to be returned.  For example, 100 records.
     */
    @Override
	public void setPaging(int page, int pageSize)
    {
    	pageSize = pageSize<0 ? 100 : pageSize; //use a positive value.
    	
    	_currentPage = page;
    	this.setMaxEvents( pageSize );
    }
    
    public boolean isPagingSet()
    {
    	return _currentPage != -1;
    }
    
    /**
     * Returns the current page value.
     * @return The current page value.
     */
    public int getCurrentPage()
    {
    	return _currentPage;
    }

    /**
        This chooses which type of transaction to return.
        The choice is<ul>
            <li>ALL_TRANSACTIONS (default)</li>
            <li>REFUND_TRANSACTIONS_ONLY</li>
            <li>REFUND_PAYMENT_TRANSACTIONS_ONLY</li>
            <li>PAYMENT_TRANSACTIONS_ONLY</li>
            <li>MODIFY_TRANSACTIONS_ONLY</li>
		</ul>
    */
    @Override
	public void setTransactionType(String val)
    {
        mTransactionType = val;
    }

    /**
     * Added: 19/03/07
     * This method can be used to retrieve a transaction type.
     */
    @Override
    public String getTransactionType()
    {
        return mTransactionType;
    }
    
    

	// Setter getter methods for Transaction Id
    /**
     * @deprecated use {@link #getTransactionIdLong} instead
     */
	@Deprecated
	public String getTransactionId()   {

		if (mTransactionIdLong > 0) 
			return String.valueOf(mTransactionIdLong);
		else
			return null;
    }

    /**
     * pass in null or "" to reset the filter
     * @deprecated use {@link #setTransactionIdLong} instead
     */
    @Deprecated
	public void setTransactionId(String trId)
    {
    	if (StringUtils.isNotBlank(trId))
    		mTransactionIdLong = Long.parseLong(trId);
    	else
    		mTransactionIdLong=-1;
    }

    //@hud RFRFRF
    @Override
	public long getTransactionIdLong() {

    	return mTransactionIdLong;
    }
    @Override
	public void setTransactionIdLong(long id) {
    	mTransactionIdLong = id;
    }


    // Setter getter methods for Related Objects
	public boolean getRelatedObjects(){
        return mRelatedObjcts;
    }

    public void setRelatedObjects(boolean rObjects){
        mRelatedObjcts = rObjects;
    }

    // Setter getter methods for Reserved Only flag
	public boolean getReservedOnlyFlag(){
        return mReserverOnlyFlag;
    }

    public void setReservedOnlyFlag(boolean mReserverOnlyFlag){
        this.mReserverOnlyFlag = mReserverOnlyFlag;
    }

	// Setter getter methods for Msisdn
	public String getMsisdn(){
        return msisdn;
    }

    public void setMsisdn(String  msisdn){
        this.msisdn = msisdn;
    }

	// Setter getter methods for mTransactionName
	public String getTransactionName(){
		return this.mTransactionName;
	}

	public void setTransactionName(String  newTransactionName){
		this.mTransactionName = newTransactionName;
	}


	/**
	 * @return
	 */
	public Long getRefundId() {
		return mRefundId;
	}

	/**
	 * @param long1
	 */
	public void setRefundId(Long refundId) {
		mRefundId = refundId;
	}

	//Remedy 3418
	/**
	 * @return
	 */
	public Long getModifyId() {
		return mModifyId;
	}

	/**
	 * @param long1
	 */
	public void setModifyId(Long modifyId) {
		mModifyId = modifyId;
	}

   /** Added: 13-09-05
     * Added for ER8 phase2
     * Added by : VFE - PS team
     * Purpose: Usage of credits from multiple packages
     * SelfCare Module: Parent transaction used to group together transactions for multiple packages
     * Description: SetParentTransactionId to a String value
     * @deprecated
     */
	@Deprecated
	@Override
	public void setParentTransactionId(String  parentTransactionId)
	{
//		mParentTransactionId = parentTransactionId;
		mParentTransactionIdLong = Long.parseLong(parentTransactionId);
	}
	public void setParentTransactionIdLong(long id) {
		mParentTransactionIdLong = id;
	}

   /** Added: 13-09-05
     * Added for ER8 phase2
     * Added by : VFE - PS team
     * Purpose: Usage of credits from multiple packages
     * SelfCare Module: Parent transaction used to group together transactions for multiple packages
     * Description: Returns the ParentTransactionId
     * @deprecated
     */
   @Deprecated
	@Override
public String getParentTransactionId()
   {
	   return String.valueOf(mParentTransactionIdLong);
   }

   public long getParentTransactionIdLong() {
//	   if (mParentTransactionIdLong == -1) {
//		   if (mParentTransactionId != null) {
//			   try {
//				   mParentTransactionIdLong = Long.parseLong(mParentTransactionId);
//			   }
//			   catch (NumberFormatException ne) {
//				   // nothing
//			   }
//		   }
//	   }

	   return mParentTransactionIdLong;
   }


	public String getExternalField1() {
		return mExternalField1;
	}


	@Override
	public void setExternalField1(String externalField1) {
		mExternalField1 = externalField1;
	}


	public String getExternalField2() {
		return mExternalField2;
	}


	@Override
	public void setExternalField2(String externalField2) {
		mExternalField2 = externalField2;
	}


	public String getExternalTransId() {
		return mExternalTransId;
	}


	@Override
	public void setExternalTransId(String externalTransId) {
		mExternalTransId = externalTransId;
	}
	
	//CR 1029 ACR - add partner id to transaction filter
	@Override
	public String getPartnerId() {
		return mPartnerId;
	}

	//CR 1029 ACR - add partner id to transaction filter
	@Override
	public void setPartnerId(String partnerId) {
		mPartnerId = partnerId;
	}
	
   


	@Override
	public boolean isZeroCostIgnore() {
		return zeroCostIgnore;
	}


	@Override
	public void setZeroCostIgnore(boolean zeroCostIgnore) {
		this.zeroCostIgnore = zeroCostIgnore;
	}
	
	//MQC 6128 Start
	
	public void setRetrieveSubscriptionForTrans(boolean retrieveSubscriptionForTrans) { 
		this.retrieveSubscriptionForTrans = retrieveSubscriptionForTrans;
	}
	
	public boolean getRetrieveSubscriptionForTrans() { 
		return this.retrieveSubscriptionForTrans;
	}
	
	//MQC 6128 Start
	
	
	
	//MQC 6137 - add flag to retrieve only lite transactions
	public boolean isLiteTransactionsOnly()
    {
        return mLiteTransactionsOnly;
    }

    @Override
	public void setLiteTransactionsOnly(boolean flag)
    {
    	mLiteTransactionsOnly = flag;
    }
    
    @Override
	public String toString()	{
    	//not great since we're creating a new object every time we call this method...
    	int offset = super.numDaysBetweenDates(getEndDate(), Calendar.getInstance().getTime());
    	StringBuffer sbuf = new StringBuffer( "offset=" ).append(offset) ;
		sbuf.append(", isActiveEventsOnly=").append( isActiveEventsOnly() ) ;
		sbuf.append(", mReserveOnlyFlag=");
		sbuf.append(mReserverOnlyFlag);
		sbuf.append(", mTransactionType=").append(mTransactionType);
		return sbuf.toString() ;
    }
    
    /** 
     * JIRAET183 - is partnerid set to ALL_PARTNER_TRANSACTIONS
     */
    @Override
    public boolean isAllPartnerTransactionsPartnerId() {
    	boolean rv = false;
    	
    	if (StringUtils.isNotBlank(mPartnerId) && mPartnerId.equals(ALL_PARTNER_TRANSACTIONS)) {
    		rv = true;
    	}
    	
    	return rv;
    }
    
}
