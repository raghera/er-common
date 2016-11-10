package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.vizzavi.ecommerce.business.charging.BaseAuthorization;
import com.vizzavi.ecommerce.business.common.AccountNotFoundException;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vodafone.global.er.business.selfcare.BalanceFilter;
import com.vodafone.global.er.business.selfcare.MicroServiceStatus;
import com.vodafone.global.er.business.selfcare.ParentTransaction;
import com.vodafone.global.er.subsmngmnt.SubsManagementException;

/**
    The API used to get the transactional and subscription list for a user.
*/
public interface SelfcareApi extends Serializable	{

    /**
        This retrieves all of the packages for a customer. This retrieves event packages, failed packages and
        packages that have failed.
        The method with the SubscriptionFilter should be used as it allows filtering and increases performance.

        The mandatory attributes are msisdn.
        @param clientId used to identify the client making the request
        @param msisdn the account to search for
        @param deviceType logs the type of device used to get the transaction list
        
    */
    public Subscription[] getSubscriptions(String clientId, String msisdn, int device) throws AccountNotFoundException, EcommerceException;

    /**
        The mandatory attribute is msisdn, with the filter being supplied by calling the getSubscriptionFilter method
        on the EcomApiFactory.
        @param clientId used to identify the client making the request
        @param msisdn the account to search for
        @param deviceType logs the type of device used to get the transaction list
        @param filter the attributes to filter the list with
    */
    public Subscription[] getSubscriptions(String clientId, String msisdn, int device, SubscriptionFilter filter) throws AccountNotFoundException, EcommerceException;

    /**
        This is used to cancel a recurring subscription so that it expires at the next payment date. This changes the charging method from recurring to non-recurring.

        <li>boolean rv = selfCareApi.modifySubscriptionChargingMethod("This is a test program", "44789765432", DeviceType.WAP, ChargingMethod.NON_RECURRING);
        <li>if (rv) {</li>
        <li>  .... check the result ... </li>
        The mandatory attributes are msisdn, packageSubId and chargingMethod.
        @param clientId used to identify the client making the request
        @param msisdn the account to search for
        @param deviceType logs the type of device used to get the transaction list
        @return boolean true for success change, false for failure
    */
    public boolean modifySubscriptionChargingMethod(String clientId, String msisdn, int deviceType, String packageSubId, int chargingMethod ) throws AccountNotFoundException, EcommerceException;
    public boolean modifySubscriptionChargingMethod(String clientId, String msisdn, int deviceType, String packageSubId, int chargingMethod,String csrId,String reason) throws AccountNotFoundException, EcommerceException;
     

    /**
        Returns the list of transactions that are between the two dates specified.
        The deviceType is NOT used for filtering.
        No more than 100 transactions are returned.
       The mandatory attributes are msisdn, start date, end date and maxNum.

        @param clientId used to identify the client making the request
        @param msisdn the account to search for
        @param deviceType logs the type of device used to get the transaction list

    */
    public Transaction[] getTransactions(String clientId, String msisdn, int deviceType, Date startDate, Date endDate, int maxNum) throws EcommerceException;



    /**
        Returns the list of transactions according to the filter attributes
        The deviceType is NOT used for filtering.
        No more than 100 transactions are returned.
        The filter should set start date, end date and maxNum.

        @param clientId used to identify the client making the request
        @param msisdn the account to search for
        @param deviceType logs the type of device used to get the transaction list

    */
    public Transaction[] getTransactions(String clientId, String msisdn, int accessDevice, TransactionFilter filter) throws EcommerceException;



    /**
     * This API represents a security hole since there is no check that the msisdn corresponds to the transactionId<br/>
     * use {@link #getTransaction(String , String , long )} instead
	*/
    @Deprecated
    public Transaction getTransaction(String clientId, String transId) throws EcommerceException;

    /**
     * a more secure get transaction method which requires an msisdn as well as transactionId.  The msisdn should correspond to the transaction, else we throw an exception
     * @param clientId
     * @param msisdn
     * @param transactionId
     * @return
     * @throws EcommerceException
     */
    public Transaction getTransaction(String clientId, String msisdn, long transactionId)	throws EcommerceException;
 
    /**
        Returns the list of transactions matching the filter.
        The deviceType is NOT used for filtering.
        No more than 100 transactions is returned.
        The mandatory attributes are msisdn and the filter
        Only retrieve payment and refund transactions

        @see TransactionFilter
        @param clientId used to identify the client making the request
        @param msisdn the account to search for
        @param deviceType logs the type of device used to get the transaction list
        @param filter the attributes to filter the list with
        @return Transaction [] the list of transactions found for the user

    */
    public Transaction[] getPaymentTransactions(String clientId, String msisdn, int deviceType, TransactionFilter filter) throws EcommerceException;


    /**
        Returns the balances against an account
        The mandatory attributes are msisdn
    */
    public ResourceBalance[] getBalances(String clientId, String msisdn, int device) throws AccountNotFoundException, EcommerceException;
  
    /**
        Retrives a particular subscription for a given msisdn.
        If the no subscription is found which matches both of these attributes, a SubscriptionNotFoundException is thrown
        @return the Subscription requested
        @throws SubscriptionNotFoundException if no subscription is found
    */
    public Subscription getSubscription(String clientId, String msisdn, int deviceType, String packageSubId) throws EcommerceException;

    /**
        Retrieves the next payment amount of a recurring subscription
        @param clientId String
        @param device int
        @param msisdn String
        @param subscriptionId String
        @param locale Locale
        @return PurchaseAuthorization
    */
    public BaseAuthorization getNextPaymentAmount(String clientId,String msisdn,String subscriptionId) throws EcommerceException;//, RemoteException;

    /**
     * To include the Apply Penalty charge. ER7 functionality
     *  
     * @param clientId
     * @param msisdn
     * @param custcareAttributes
     * @return
     * @throws EcommerceException
     */
    public boolean modifySubscriptionChargingMethod(String clientId, String msisdn, int deviceType, CustcareAttributes attr ) throws AccountNotFoundException, EcommerceException;



    public ResourceBalance[] getBalances(String msisdn, String clientId, int deviceId, BalanceFilter filter)
    throws AccountNotFoundException, EcommerceException;

    /** Added: 15-09-05 
     * Added for ER8 phase2 
     *Added by : VFE - PS team
     * Purpose: Usage of credits from multiple packages
     * SelfCare Module: Parent transaction used to group together transactions for multiple packages
     */
    public ParentTransaction getParentTransaction(String clientId, String msisdn, TransactionFilter transactionfilter) throws EcommerceException;
    
 
    /**
     *  This is new API to get the detailed information of 
     *  micro-service usage for a particular subscription
     *  
     *  It is possible that there may be more than one micro services upon
     *  a subscription. The returned array contains iniformation:
     *  
     *  1. Service Id
     *  2. Status (used | valid)
     *  3. Triggering usage time
     */
    public MicroServiceStatus[] getMicroServiceStatus(
    		String				clientId
    	,	String				msisdn
    	,	MicroServiceFilter	msfilter			//@hud STARTING NOW, SUBSCRIPTION ID SHOULD BE LONG
    ) throws EcommerceException;

    /**
     * this is insecure since the msisdn is not required
     * @param clientId
     * @param filter
     * @return
     * @throws EcommerceException
     */
    @Deprecated
    public Transaction getTransaction(String clientId, TransactionFilter filter) throws EcommerceException;
    
    
    /**
     * JIRA ET196 Get account subscription promo-codes info
     * @param msisdn
     * @param clientId
     * @return List<SubscriptionPromoCode>
     * @throws EcommerceException
     */
    public List<SubscriptionPromoCode> getSubscriptionPromoCodes(String msisdn, String clientId) throws EcommerceException;
    
    /**
     * Jira ET245 implement get subscriptions in decoupling version 2
     * 
     * @param msisdn
     * @param filter
     * @param locale
     * @return
     * @throws SubsManagementException
     */
    public List<Subscription> getSubscriptions(String msisdn, SubscriptionFilter filter, Locale locale) throws SubsManagementException;
    
    /**
     * JIRA ET-238 - new get service offers call
     * @param msisdn
     * @param serviceIds
     * @return
     * @throws SubsManagementException
     */
    public List<ServiceOffer> getServiceOffers(String msisdn, String serviceIds) throws EcommerceException;
}
