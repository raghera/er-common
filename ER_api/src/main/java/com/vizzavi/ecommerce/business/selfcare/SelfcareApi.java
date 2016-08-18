package com.vizzavi.ecommerce.business.selfcare;

import java.util.Date;

import com.vizzavi.ecommerce.business.common.AccountNotFoundException;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vodafone.global.er.business.selfcare.BalanceFilter;
import com.vodafone.global.er.business.selfcare.MicroServiceStatus;
import com.vodafone.global.er.business.selfcare.ParentTransaction;

/**
    The API used to get the transactional and subscription list for a user.
*/
public interface SelfcareApi extends java.io.Serializable
{

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
        Returns the single transaction according to the transId specified

        @return null if the transaction doesn't exist
        @param clientId used to identify the client making the request

        @param transId the transaction Id to search for
        
        @hud  we must use filter instead of transId
        

    */
    public Transaction getTransaction(String clientId, String transId) throws EcommerceException;



    /**
        Returns the list of transactions matching the filter.
        The deviceType is NOT used for filtering.
        No more than 100 transactions is returned.
        The mandatory attributes are msisdn and the filter
        @see TransactionFilter
        @param clientId used to identify the client making the request
        @param msisdn the account to search for
        @param deviceType logs the type of device used to get the transaction list
        @param filter the attributes to filter the list with
        @return Transaction [] the list of transactions found for the user
    */

    /**
        Only retrieve payment and refund transactions
    */
    public Transaction[] getPaymentTransactions(String clientId, String msisdn, int deviceType, TransactionFilter filter) throws EcommerceException;


    /**
        Returns the balances against an account
        The mandatory attributes are msisdn
    */
    public ResourceBalance[] getBalances(String clientId, String msisdn, int device) throws AccountNotFoundException, EcommerceException;
   /**
        Retrives a particular subscription.
        The mandatory attributes are packageSubId
        The msisdn is not used to find the subscription
        @return null if the subscription doesn't exist
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
    public com.vizzavi.ecommerce.business.charging.BaseAuthorization getNextPaymentAmount(String clientId,String msisdn,String subscriptionId) throws com.vizzavi.ecommerce.business.common.EcommerceException, java.rmi.RemoteException;

 /**
     * To include the Apply Penalty charge. ER7 functionality
     *  
     * @param clientId
     * @param msisdn
     * @param custcareAttributes
     * @return
     * @throws EcommerceException
     */
    //nayera Min Subscription Period - German Migration - new method with CustcareAttributes object to hold the extra attributes including boolean for applyPenalty
    public boolean modifySubscriptionChargingMethod(String clientId, String msisdn, int deviceType, CustcareAttributes attr ) throws AccountNotFoundException, EcommerceException;

    public ResourceBalance[] getSuperCreditBalances(String clientId, String msisdn, int device) throws AccountNotFoundException, EcommerceException;


    public ResourceBalance[] getBalances(String msisdn, String clientId, int deviceId, BalanceFilter filter)
    throws com.vizzavi.ecommerce.business.common.AccountNotFoundException, EcommerceException;

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

    public Transaction getTransaction(String clientId, TransactionFilter filter) throws EcommerceException;
}
