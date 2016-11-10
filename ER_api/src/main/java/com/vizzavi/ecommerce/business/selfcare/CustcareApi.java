package com.vizzavi.ecommerce.business.selfcare;

import java.util.List;
import java.util.Locale;

import com.vizzavi.ecommerce.business.charging.AccountValidationAuthorization;
import com.vizzavi.ecommerce.business.charging.ModifyAuthorisation;
import com.vizzavi.ecommerce.business.charging.RefundAttributes;
import com.vizzavi.ecommerce.business.charging.RefundAuthorization;
import com.vizzavi.ecommerce.business.charging.SubscriptionAttributes;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.EcommerceException;

/**
    Customer care api allows the customer care application to set a reason and the id of the cust care user.
    It extends the SelfcareApi and allows the customer care user to set a reason and their user id before
    calling each call.

    To use the API, before each call set the reason code and the customer service id

    The Customer Care API is NOT thread safe so each client should construct their own instance.
 */
public interface CustcareApi extends SelfcareApi	{



	/**
        Sets the customer care id and reason, which are then used in any subsequent requests where csrId or reason are null or not supplied.

        @param reason a String describing why the customer care agent is making the change
        @param csrId the account id of the customer service representative
        @deprecated pass the csrId and reason on each call you make
	 */
	@Deprecated
	public void setCustCareDetails(String csrId, String reason);

	/**
        This causes PECS to send a validation message to the soap server. If the validation fails, the account is
        set to be inactive in PECS. If the validation succeeds, there is no effect on the account (i.e. the account is
        not inactivated). To force inactivation of an account without calling the soap handler at all, use the
        <code>inactivateAccount(String clientId, String msisdn, boolean validateAccount)</code> method.

        The mandatory attributes are msisdn.
        @param clientId an identifier for the client to allow identification of request in the ER2 log
        @param msisdn the id of the account to invalidate
        @return boolean true if the account was set to inactive
	 */
	public boolean inactivateAccount(String clientId, String msisdn,String csrId,String reason) throws EcommerceException;


	/**
	 * This checks the flag to see if a validation message is sent to the soap server. If the validateAccount
	 * flag is true, the behaviour is identical to <code>inactivateAccount(String clientId, String msisdn)</code>.
	 * If the validateAccount flag is false, the account is inactivated immediately (PECS does not call the validate soap handler at all).
	 * The mandatory attributes are msisdn and validateAccount.
	 * @param clientId an identifier for the client to allow identification of request in the ER2 log
	 * @param msisdn the id of the account to invalidate
	 * @param validateAccount send a validate message to soap server if set to true otherwise inactivate the account without checking
	 * @param csrId the customer service representative id (can be null)
	 * @param reason (can be null)
	 * @return boolean true if the account was set to inactive
	 * @throws EcommerceException
	 */
	public boolean inactivateAccount(String clientId, String msisdn, boolean validateAccount,String csrId,String reason) throws EcommerceException;



	/**
	 * This makes a subscription inactive.  The mandatory attributes are subscriptionId, clientId and msisdn
	 * @param clientId an identifier for the client to eg 'ercc'
	 * @param msisdn the id of the account
	 * @param subscriptionId the id of the package/subscription to make inactive.
	 * @param csrId the customer service Rep id
	 * @param reason reason for inactivation
	 * @return
	 * @throws EcommerceException
	 */
	public boolean inactivateSubscription(String clientId, String msisdn, String subscriptionId,String csrId,String reason) throws EcommerceException;

	/**
        Performs a monetary refund against a payment transaction.
        The refund amount must be less than the cost of the payment.

        call RefundAuthorization.isAuthorized() to check if the refund worked.
        The ReasonCode can be checked if an error.
        Note :
            The subscription id does not have to be set in the RefundAttributes in this version
        @return RefundAuthorization which tells
        @param clientId an identifier for the client to allow identification of request in the ER2 log
        @param msisdn the id of the account
        @param transactionId the payment transaction id
        @param the amount to refund - if not included, the original 
        @param the resource
        @param the attributes
	 */
	public RefundAuthorization refundTransactionMonetary(String clientId, String msisdn, String transactionId, double amount, ChargingResource res, RefundAttributes attributes) throws EcommerceException;



	/**
        Extends the expiry date of the subscription object associated with a payment transaction by the number of days specified.
        The subscription must be a calendar subscription package.

        call RefundAuthorization.isAuthorized() to check if the refund worked.
        The ReasonCode can be checked if an error.
        Note :
            The subscription id does not have to be set in the RefundAttributes in this version
        @param clientId an identifier for the client to allow identification of request in the ER2 log
        @param msisdn the id of the account
        @param transactionId the payment transaction id
        @param the numberDaysToExtend
        @param the attributes
	 */
	public RefundAuthorization refundTransactionEnlargement(String clientId, String msisdn, String transactionId, long numberDaysToExtend, RefundAttributes attributes) throws EcommerceException;


	/**
        Gives a discount on the cost of the next payment of the recurring subscription.
        The subscription must be a recurring subscription.

        The discount can either be from
            1 to 99% of the next payment amount
          or 0 to 1 times the next payment amount

        The preferred format is the % discount of the next payment amount.
        If you set 0.1, this is equivalent to a 90% discount.

        call RefundAuthorization.isAuthorized() to check if the refund worked.
        The ReasonCode can be checked if an error.
        Note :
            The subscription id does not have to be set in the RefundAttributes in this version
        @param clientId an identifier for the client to allow identification of request in the ER2 log
        @param msisdn the id of the account
        @param transactionId the payment transaction id
        @param the discount the % discount to give
        @param the attributes
	 */
	public RefundAuthorization refundTransactionDiscount(String clientId, String msisdn, String transactionId, double discount, RefundAttributes attributes) throws EcommerceException;


	/**
	 * Modifies the msisdn of the user.
	 * This is used so that a user can change their msisdn and keep their transaction history
	 * @param clientId an identifier for the client to allow identification of request in the logs
	 * @param msisdn the id of the account
	 * @param newMsisdn the new msisdn
	 * @param csrId customer service rep (can be null)
	 * @param reason can be null
	 * @return true if the modification was successful
	 * @throws EcommerceException
	 */
	public boolean modifyMsisdn(String clientId, String msisdn, String newMsisdn,String csrId,String reason) throws EcommerceException;



	/**
        Modifies the ban of the user directly without using validate handler
        @param clientId an identifier for the client to allow identification of request in the ER2 log
        @param msisdn the id of the account
        @param newBan the value to set the ban to
        @return true if the modification was successful
	 */

	public boolean modifyBan(String clientId, String msisdn, String newBan,String csrId,String reason) throws EcommerceException;




	/**
        Returns the basic account for the msisdn

        @param clientId used to identify the client making the request
        @param msisdn the account to search for
        @param deviceType logs the type of device used to get the transaction list

        @return Basic Account fot the msisdn
	 */
	public BasicAccount getBasicAccount(String clientId, String msisdn, int accessDevice) throws EcommerceException;

	/**
	 * ET-340 Modify the get-full-accounts api call to have a force refresh functionality
	 * @param clientId used to identify the client making the request
	 * @param msisdn msisdn the account to search for
	 * @param forceRefresh if true, a force refresh of the customer data is required by triggering a validate request to ERIF
	 * @return
	 * @throws EcommerceException
	 */
	public BasicAccount getBasicAccount(String clientId, String msisdn, boolean forceRefresh) throws EcommerceException;

	/**
    This makes a subscription inactive. Using Apply Penalty Charge boolean (ER7)
    The mandatory attributes are subscriptionId
    @param clientId an identifier for the client to allow identification of request in the ER2 log
    @param msisdn the id of the account
    @param subscriptionId the id of the package/subscription to make inactive.
    @return true if the subscription was cancelled
	 */   
	public boolean inactivateSubscription (String clientId, String msisdn,int device, CustcareAttributes custcareAttributes)throws EcommerceException;

//	/**
//	 * @deprecated 
//	 * @param locale1
//	 * @param url
//	 * @param name
//	 * @return
//	 * @throws EcommerceException
//	 */
//	@Deprecated
//	public boolean notificationSubscribe(Locale locale1,String url,String name) throws EcommerceException;



	/**
	 * Performs credit refund against a payment transaction or 
	 * parent transaction
	 * 
	 * @param clientId
	 * @param msisdn
	 * @param paymentTransId			Could be a parent transaction Id
	 * @param creditAmount
	 * @param res						Must not be a currency
	 * @param refundAttr
	 * @return							authorization object
	 * @throws EcommerceException
	 * 
	 * @author hud
	 * 
	 */
	public RefundAuthorization refundTransactionCredit(
			RefundAttributes 	refundAttr
			) throws EcommerceException;


	/**
	 *   CR1564-utc timezone for diff region in country for DI changes
	 * 
	 * @param clientId
	 * @param msisdn
	 * @param timezone
	 * @param csrId
	 * @param reason
	 * @return
	 * @throws EcommerceException
	 */

	//public ModifyAuthorisation modifyTimezone(String clientId, String msisdn, String timezone, String csrId, String reason) throws EcommerceException;//ET-153


	/**
	 * 
	 * @param clientId
	 * @param msisdn
	 * @param billingCycle
	 * @param csrId
	 * @param reason
	 * @return
	 * @throws EcommerceException
	 */
	public ModifyAuthorisation modifyBillingCycle(String clientId, String msisdn, int billingCycle, String csrId, String reason) throws EcommerceException;


	/**
	 * 
	 * @param clientId
	 * @param msisdn
	 * @param usergroup
	 * @param csrId - optional
	 * @param reason - optional
	 * @param action (add, remove, overwrite) - if null defaults to over-write
	 * @return
	 * @throws EcommerceException
	 * CR2229 - add action
	 */
	public ModifyAuthorisation modifyUserGroup(String clientId, String msisdn, List<String> usergroup, String csrId, String reason, String action) throws EcommerceException;


	// CR 1643 - Pre-Pay Post-Pay Split
	/**
	 * Updates the Service Provider ID in the Accounts table
	 * @param clientId
	 * @param msisdn
	 * @param spId
	 * @return ModifyAuthorisation
	 * @throws EcommerceException
	 */
	public ModifyAuthorisation modifyAccountSpId(String clientId, String msisdn, String spId) throws EcommerceException;

	/**
	 * Updates the Is Prepay flag in the Accounts table
	 * @param clientId
	 * @param msisdn
	 * @param isPrepay
	 * @return ModifyAuthorisation
	 * @throws EcommerceException
	 */
	public ModifyAuthorisation modifyAccountIsPrepay(String clientId, String msisdn, String isPrepay) throws EcommerceException;
	// CR 1643 - Ends

	/** CR 1791 
	 * 
	 * @param clientApplicationId
	 * @param msisdn
	 * @param subscriptionId
	 * @param subscriptionAttributes
	 * @throws  EcommerceException

	 */
	public ModifySubscriptionAuthorization modifySubscription(
			String clientApplicationId,
			String msisdn,
			String subscriptionId,
			SubscriptionAttributes subscriptionAttributes
			) throws EcommerceException;

//	/**
//	 * MQC 6669
//	Performs a monetary refund against the last rolled back transaction for the subscription id.
//	The refund amount equals the cost of this rolled backed payment. As part of the refund if the subscription
//	package has provisionable services, then deprovisioning call is made.
//	
//	This call should only be used internally and is only called in the
//	PurchaseApiDelegateImpl.renewPurchasePackageMsisdn method when a Rollback is detected,
//	this is usally the case when there has been a lockid mismatch when trying to commit the renewal  
//	
//	
//	@return RefundAuthorization which tells
//	@param clientId an identifier for the client to allow identification of request in the ER2 log
//	@param msisdn the id of the account
//	@param transactionId the payment transaction id
//	@param the amount to refund
//	@param the resource
//	@param the attributes
//	 */
//	public RefundAuthorization refundRollbackTransactionMonetary(String clientId, String msisdn, String subscriptionId, RefundAttributes attributes) throws EcommerceException;


	/**
	 *   CR2082 - validate msisdn call, used to make a direct validation msisdn call to the opco ERIF
	 * 
	 * @param msisdn mandatory
	 * @param validateAttributes optional - can specify partner, serviceId, etc
	 * @return {@link AccountValidationAuthorization}
	 * @throws EcommerceException
	 * 
	 */
	public AccountValidationAuthorization validateMsisdnAccount(String msisdn, ValidateMsisdnAttributes validateAttributes) throws EcommerceException;

	/**
	 * CR2040 MPAY replacement.  Modifies MSISDN spend limits.
	 * @param clientId
	 * @param msisdn
	 * @param spendLimits
	 * @return object containing result details - isSuccess true or false
	 */
	public ModifyAuthorisation modifySpendLimits(String clientId, String msisdn, SpendLimits spendLimits, String csrId) throws EcommerceException ;


	/**
	 * CR2198 - Updates the Child Service Provider ID in the Accounts table
	 * @param clientId
	 * @param msisdn
	 * @param childSpId
	 * @return ModifyAuthorisation
	 * @throws EcommerceException
	 */
	public ModifyAuthorisation modifyAccountChildSpId(String clientId, String msisdn, String childSpId) throws EcommerceException;

	/**
	 * CR2198 - Updates the Is Service Provider Type in the Accounts table
	 * @param clientId
	 * @param msisdn
	 * @param spType
	 * @return ModifyAuthorisation
	 * @throws EcommerceException
	 */
	public ModifyAuthorisation modifyAccountSpType(String clientId, String msisdn, String spType) throws EcommerceException;

	 /**
     * JIRA ET148 Add SMS blacklist flag to opt out of courtesy SMS notifications
	 * @param clientId
     * @param custcareAttrs
     * @throws EcommerceException
     */
	public ModifyAuthorisation modifyAccount(String clientId, CustcareAttributes custcareAttrs) throws EcommerceException;
	
	
	/**
	 * Enable or disable a bar for a particular user.
	 * <br/>PPMID113010 ET197 DE CTB Migration.  
	 * @param barName the name of the bar we are going to modify
	 * @param msisdn 
	 * @param clientId
	 * @param newValue the new value of the bar.  True = enable bar, false = disable bar
	 * @return the updated Bar entry for the msisdn
	 * @throws EcommerceException
	 */
	public Bar modifyBar(String barName, String msisdn, String clientId, boolean newValue) throws EcommerceException;
	
	/**
	 * Retrieve a list of the bars for a particular user.
	 * <br/>PPMID113010 ET197 DE CTB Migration.  
	 * @param msisdn
	 * @param clientId
	 * @return a List of {@link Bar}s for that user
	 * @throws EcommerceException
	 */
	public List<Bar> getBars(String msisdn, String clientId) throws EcommerceException;
	
	/**
	 * JIRA ET196 Inactivate subscription promo-code
	 * @param msisdn
	 * @param clientId
	 * @param subscriptionId
	 * @param packageId
	 * @return List<InactivateSubscriptionPromoCodeAuthorization>
	 * @throws EcommerceException
	 */
	public List<InactivateSubscriptionPromoCodeAuthorization> inactivateSubscriptionPromoCode(String msisdn, String clientId, InactivateSubscriptionPromoCodeAttributes inactivateSubPromoAttrs) throws EcommerceException;
	
}
