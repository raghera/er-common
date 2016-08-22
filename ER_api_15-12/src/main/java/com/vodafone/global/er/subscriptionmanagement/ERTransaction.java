package com.vodafone.global.er.subscriptionmanagement;

import java.util.Date;
import java.util.List;

import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.RatingAttributes;
import com.vizzavi.ecommerce.business.selfcare.ModificationInfo;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;
import com.vizzavi.ecommerce.business.selfcare.Transaction;
import com.vizzavi.ecommerce.business.selfcare.TransactionSubRecord;
import com.vizzavi.ecommerce.business.selfcare.TransactionType;
import com.vodafone.global.er.rating.TaxRatedEvent;

/**
 * 
 * @author hud
 *
 * This class is badly coded and MUST BE re-factored!!!! -- hud
 * Lots of method MUST BE moved to Transaction class!!
 */
public class ERTransaction extends Transaction implements DirtyMarker {
	
	private static final long serialVersionUID = -22293929424491462L;
	
	
	private boolean mDirty = false;
	private boolean mNewInstance = true;
	
	public ERTransaction()
	{
	}
	
	/**
	 * @author hud
	 * Refactored
	 * 
	 * @param type
	 * @param subscriptionId
	 * @param status
	 * @param paymentTransId
	 */
	public ERTransaction (
			TransactionType 	type
		, 	String 				subscriptionId
		, 	int 				status
		, 	String 				paymentTransId
	)
	{
		super(type, subscriptionId, status, paymentTransId);
	}
	
	public ERTransaction (
			TransactionType 	type
		, 	long 				subscriptionIdLong
		, 	int 				status
		, 	long 				paymentTransIdLong
	)
	{
		super(type, subscriptionIdLong, status, paymentTransIdLong);
	}	
	
	public ERTransaction(TaxRatedEvent event)
	{
		super(event);
	}
	
	public ERTransaction(ERTransaction trans)
	{
		super(trans);
		
		//MQC 7204 - There are some attributes not set in the super constructor
		
		if (trans != null && trans.getMatchingAttributes() != null 
				&& trans.getMatchingAttributes() instanceof ERRatingAttributes) {
			
			final ERRatingAttributes origAttr = (ERRatingAttributes) trans.getMatchingAttributes();
			final ERRatingAttributes copyAttr = new ERRatingAttributes(trans.getMatchingAttributes());
			
			if (origAttr.isDirty()) {
				copyAttr.setDirty();
			}
			if( origAttr.isNew() ) {
				copyAttr.setNew();
			}
			//Set directly as dirty flag is set above
			this.mMatchingAttributes = copyAttr;
		}
		//MQC 7204 - End
	}
	
	//MQC 6669 - send refund message for a rolledback transaction
	public ERTransaction(Transaction trans)
	{
		super(trans);
	}
	
	//Getter Setter methods for TransactionType
	@Override
	public void setHostId(String hostId)
	{
		super.setHostId(hostId);
		mDirty = true;
	}
	
	@Override
	public void setClientId(String clientId)
	{
		super.setClientId(clientId);
		mDirty = true;
	}
	
	@Override
	public void setRateIdentifier(String rateIdentifier)
	{
		super.setRateIdentifier(rateIdentifier);
		mDirty = true;
	}
	
	
	
	//Getter Setter methods for TransactionType
	@Override
	public void setType(TransactionType type)
	{
		super.setType(type);
		mDirty = true;
	}
	
	
	
	//Getter Setter methods for Purchase Date
	@Override
	public void setPurchaseDate(Date purchaseDate)
	{
		super.setPurchaseDate(purchaseDate);
		mDirty = true;
	}
	
	
	//Getter Setter methods for Purchase Rate
	@Override
	public void setPurchaseRate(double purchaseRate)
	{			
		super.setPurchaseRate(purchaseRate);
		mDirty = true;
	}
	
	//Getter Setter methods for Tax Rate
//ET-330	
//	@Override
//	public void setTaxRate(double taxRate)
//	{
//		super.setTaxRate(taxRate);
//		mDirty = true;
//	}
	@Override
	public void setTaxRate(Double taxRate)
	{
		if(taxRate !=null){
			super.setTaxRate(taxRate);
			mDirty = true;
		}
	}
	//Getter Setter methods for Purchase Currency
	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void setPurchaseCurrency(ChargingResource resource)
	{
		super.setPurchaseCurrency(resource);
		mDirty = true;
	}
	
	@Override
	public void setChargingResource(ChargingResource resource)
	{
		super.setChargingResource(resource);
		mDirty = true;
	}
	
	
	//Getter Setter methods for TransactionId
	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void setTransactionId(String transactionId)
	{
		super.setTransactionId(transactionId);
		mDirty = true;
	}
	
	public void setTransactionIdLong(long transactionId)	{
		if(transactionId>0)
			super.setTransactionIdLong(transactionId);
		mDirty=true;
	}
	
	//Getter Setter methods for Next Cycle Discount
	@Override
	public void setNextCycleDiscount(Double nextCycleDiscount)
	{
		super.setNextCycleDiscount(nextCycleDiscount);
		mDirty = true;
	}
	
	
	//Getter Setter methods for Content Name
	@Override
	public void setServiceId(String ServiceId)
	{
		super.setServiceId(ServiceId);
		mDirty = true;
	}
	
	
	
	/****************Angie Phase II *******************/ 
	// Setter methos for the Asset ID
	@Override
	public void setAssetId(String assetId)  
	{    
		super.setAssetId(assetId);
		mDirty = true;
	}
	
	
	// Getter setter methods to set the CSR id
	@Override
	public void setCsrId(String csrId)
	{
		super.setCsrId(csrId);
		mDirty = true;
	}
	
	// Getter setter methods for Description
	@Override
	public void setDescription(String description)
	{
		super.setDescription(description);
		mDirty = true;
	}
	
	// Getter setter methods for Reason
	@Override
	public void setReason(String reason)
	{
		super.setReason(reason);
		mDirty = true;
	}
	
	@Override
	public void setResourceBalances(ResourceBalance[] balance)
	{
		super.setResourceBalances(balance);
		//@hud ??? no dirty set? just added it here
		mDirty = true;
	}
	
	// Getter setter methods for AuthCode
	@Override
	public void setAuthCode(String authCode)
	{
		super.setAuthCode(authCode);
		mDirty = true;
	}
	
	// Getter setter methods for PaymentStatus
	@Override
	public void setPaymentStatus(int paymentStatus)
	{
		super.setPaymentStatus(paymentStatus);
		mDirty = true;
	}
	
	// Getter setter methods for PaymentError Id
	@Override
	public void setPaymentErrorId(String errorId)
	{
		super.setPaymentErrorId(errorId);
		mDirty = true;
	}
	
	// Getter setter methods for PaymentError Description
	@Override
	public void setPaymentErrorDescription(String errorDescription)
	{
		super.setPaymentErrorDescription(errorDescription);
		mDirty = true;
	}
	
	
	//	Getter setter methods for Refund Enlargement Date
	@Override
	public void setRefundEnlargementDate(Date refundEnlargementDate)
	{
		super.setRefundEnlargementDate(refundEnlargementDate);
		mDirty = true;
	}
	
	/**
        Returns extra information if a modification transaction
	 */
	
	// Getter setter methods for Modifications Info
	@Override
	public void setModificationInfo(ModificationInfo modInfo)
	{
		super.setModificationInfo(modInfo);
		mDirty = true;
	}
	
	// Getter setter methods for Payment Type
	@Override
	public void setPaymentType(int paymentType)
	{
		super.setPaymentType(paymentType);
		mDirty = true;
	}
	
	// Getter setter methods for Subscription Id
	@Override
	@Deprecated
	public void setSubscriptionId(String subscriptionId)
	{
		super.setSubscriptionId(subscriptionId);
		mDirty = true;
	}
	
	// Getter setter methods for Transaction Status
	@Override
	public void setStatus(int status)
	{
		super.setStatus(status);
		mDirty = true;
	}
	

	@Override
	public void setBearer(String bearer)
	{
		super.setBearer(bearer);
		mDirty = true;
	}
	//END ADDED BY VFE
	
	// Getter setter methods for Access Device
	@Override
	public void setAccessDevice(int accessDevice)
	{
		super.setAccessDevice(accessDevice);
		mDirty = true;
	}
	

	@Override
	public void setMatchingAttributes(RatingAttributes matchingAttrs)
	{
		super.setMatchingAttributes(matchingAttrs);
		mDirty = true;
	}
	
	@Override
	public void setPaymentInfo(String paymentInfo)
	{
		super.setPaymentInfo(paymentInfo);
		mDirty = true;
	}    
	
	@Override
	public void setContentDescription(String contentDescription)
	{
		super.setContentDescription(contentDescription);
		mDirty = true;
	}
	
	@Override
	public void setRefundPaymentTransactionId(String refundPaymentTransactionId )
	{
		super.setRefundPaymentTransactionId(refundPaymentTransactionId);
		mDirty = true;
	}

	
	@Override
	public void setBalanceImpact(int balanceImpact)
	{
		super.setBalanceImpact(balanceImpact);
		mDirty = true;
	}
	//	end VFE - PS team code
	
	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setDirty()
	 */
	
	@Override
	public void setDirty() {
		this.mDirty = true;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isDirty()
	 */

	@Override
	public boolean isDirty() {
		return mDirty;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetDirty()
	 */
	 
	@Override
	public void resetDirty() {
		mDirty = false;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setNew()
	 */
	
	@Override
	public void setNew() {
		mNewInstance = true;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isNew()
	 */
	@Override
	public boolean isNew() {
		return mNewInstance;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetNew()
	 */
	@Override
	public void resetNew() {
		mNewInstance = false;
	}
	
	//@hud moved getComplexTransactionId to Transaction
	/**
	 * To generate complex Id for Payment Handlers
	 * @param locale
	 * @return
	 * @throws EcommerceException
	 */
	
	
	@Override
	public void setSubRecords(List<TransactionSubRecord> subRecords)
	{
		super.setSubRecords(subRecords);
		mDirty = true;
	}
//	Amd001 [end] 
	
	
	/**
	 * @version      ER 8.0 - P2
	 * @author       VFE  PS Team
	 * @date         18-Sept-2005
	 * @description  (Access Channel Reporting)   The purpose of this method is to set the Access channel in  the ERtransaction object. **/
	
	@Override
	public void setAccessChannel(String accessChannel)  
	{    
		super.setAccessChannel(accessChannel);
		mDirty = true;
	}
	
	/**
	 * @version      ER 8.0 - P2
	 * @author       VFE  PS Team
	 * @date         18-Sept-2005
	 * @description  (Purchase Channel Reporting)   The purpose of this method is to set the Purchase channel in  the ERtransaction object. **/
	
	@Override
	public void setPurchaseChannel(String purchaseChannel)  
	{    
		super.setPurchaseChannel(purchaseChannel);
		mDirty = true;
	}
	
	/**
	 * @version      ER 8.0 - P2
	 * @author       VFE  PS Team
	 * @date         18-Sept-2005
	 * @description  (Distribution Channel Reporting)   The purpose of this method is to set the Device ID in  the ERtransaction object. **/
	
	@Override
	public void setDeviceId(String deviceId)  
	{    
		super.setDeviceId(deviceId);
		mDirty = true;
	}
	
	
	//CR AffiliateID
	@Override
	public void setAffiliateID(String affiliateID)  
	{    
		super.setAffiliateID(affiliateID);
		mDirty = true;
	}
	
	//CR-0869 start
	@Override
	public void setPartnerId(String partnerId)  
	{    
		super.setPartnerId(partnerId);
		mDirty = true;
	}
	
	@Override
	public void setAggregatorId(String aggregatorId)  
	{    
		super.setAggregatorId(aggregatorId);
		mDirty = true;
	}
	//CR-0869 start

	//CR1423: Duplicate Charging
    @Override
	public void setReIssue(int reIssueNumber)  
    {    
        super.setReIssue(reIssueNumber);
        mDirty = true;
    }
    
    /**
     * CR 2198
	 * @param childSpId the childSpId to set
	 */
    public void setChildSpId(String childSpId) {
		super.setChildSpId(childSpId);
	}
    
    /**
     * CR 2198
   	 * @param spType the spType to set
   	 */
    public void setSpType(String spType) {
  		super.setSpType(spType);
  	}
    
    /**
     * CR 2255
   	 * @param contentCategory the mContentCategory to set
   	 */
    public void setContentCategory(String contentCategory) {
  		super.setContentCategory(contentCategory);
  	}
}
