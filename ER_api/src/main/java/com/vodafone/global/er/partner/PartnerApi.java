package com.vodafone.global.er.partner;

import java.rmi.RemoteException;

import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.SubscriptionNotFoundException;
import com.vizzavi.ecommerce.business.common.TransactionNotFoundException;
import com.vizzavi.ecommerce.business.selfcare.ExternalSubscriptionTransactionStatus;
import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vizzavi.ecommerce.business.selfcare.Transaction;

/**
 * created for CR 2082 (google dcb3) this interface contains methods to allow a partner to check the 
 * status of a transaction or subscription based on the external subscription or transaction id passed in at the time of purchase
 * @author matt
 *
 */
public interface PartnerApi extends java.io.Serializable {

	/**
	 * returns a Transaction object based on the external TransactionId and partner ID passed in
	 * @param externalTransactionId
	 * @param partnerId
	 * @return
	 * @throws TransactionNotFoundException if no transaction in the DB matches the criteria
	 */
	public Transaction getDetailsForExternalTransaction(String externalTransactionId, String partnerId) throws TransactionNotFoundException;

	/**
	 * returns a Subscription object based on the external TransactionId and partner ID passed in.
	 * @param externalSubscriptionId
	 * @param partnerId
	 * @return
	 * @throws SubscriptionNotFoundException if no subscription in the DB matches the criteria
	 * @see #getDetailsForExternalSubscription(String msisdn, String externalSubscriptionId, String partnerId)
	 */
	public Subscription getDetailsForExternalSubscription(String externalSubscriptionId, String partnerId) throws SubscriptionNotFoundException;

	/**
	 * returns a Subscription object based on the external TransactionId, msisdn and partner ID passed in.
	 * @param msisdn the msisdn of the user
	 * @param externalSubscriptionId the external subscription ID
	 * @param partnerId (PARTNER_ID from ER_SUBSCRIPTIONS)
	 * @return
	 * @throws SubscriptionNotFoundException if no subscription in the DB matches the criteria
	 * @see #getDetailsForExternalSubscription(String externalSubscriptionId, String partnerId)
	 */
	public Subscription getDetailsForExternalSubscription(String msisdn, String externalSubscriptionId, String partnerId) throws SubscriptionNotFoundException;

	/**
	 * check if there is already a record (transaction or subscription) in the database with the specified external sub or trans id.   NB if there 
	 * is both an external sub and external trans, it finds the sub first and doesn't check for the transaction (since the presence of either will block a purchase)
	 * @param partnerId - mandatory
	 * @param externalSubId - optional
	 * @param externalTransId - optional
	 * @return <code>ExternalSubscriptionTransactionStatus.EXT_SUB_ID_SUB_EXISTS</code> if an external subId was found (there could also be an external transactionId<br/>
	 * <code>ExternalSubscriptionTransactionStatus.EXT_SUB_ID_TRAN_EXISTS</code> if an external transId was found<br/>
	 * <code>ExternalSubscriptionTransactionStatus.EXT_SUB_TRAN_ID_NOT_EXISTS</code> if neither was found 
	 */
	public ExternalSubscriptionTransactionStatus recordExists(String partnerId, String externalSubId,	String externalTransId);
	
	/**
	 * CR 2245 Phase 2 - get the subscription partner information for a country specific partner
	 * @param partnerId
	 * @param countryObjId
	 * @return B2BPartner
	 */
	public B2BPartner getB2BPartner(String partnerId, int countryObjId);

}
