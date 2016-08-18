package com.vodafone.global.er.subscriptionmanagement;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.vizzavi.ecommerce.business.charging.PurchaseAuthorizationException;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.selfcare.BasicAccount;

public interface SubscriptionManagement extends java.io.Serializable
{
   public Account findAccountByPrimaryKey(String msisdn) throws EcommerceException;

   public Account findAccountByTransactionId(Long transactionId) throws EcommerceException;

   public Account findAccountByParentTransactionId(Long parentTransactionId) throws EcommerceException;

   public Account findAccountByRefundId(Long transactionId) throws EcommerceException;

   public Account findAccountBySubscriptionId(Long subscriptionId) throws EcommerceException;

   public Account createAccount(String msisdn, String ban, Locale locale, String packageId) throws EcommerceException;
   //@since 5.1
   public Account createAccount(String msisdn, String ban, Locale locale, String packageId, ERUserGroup[] userGroups) throws EcommerceException;

   public Account createAccount(String msisdn, String ban, Locale locale, String packageId, ERUserGroup[] userGroups, int billingCycleDate) throws EcommerceException;
   //CR1564-utcTimezoneOffset for diff region in country
   public Account createAccount(String msisdn, String ban, Locale locale, String packageId, ERUserGroup[] userGroups, int billingCycleDate, String utcTimezoneOffset) throws EcommerceException;

   // CR 1643 - Pre-Pay Post-Pay Split
   public Account createAccount(String msisdn, String ban, Locale locale, String packageId, ERUserGroup[] userGroups, 
		   int billingCycleDate, String utcTimezoneOffset, String spId, String isPrepay) throws EcommerceException;

   public void removeSubscription(String subId);

    //Remedy 7128, Bruno Meseguer, correction on Bean handling
    public void removeAccountBeanFromContainer(String primaryKey);

    public void consumeSuperCreditImpact(String msisdn, String superCreditID, int balanceImpact) throws PurchaseAuthorizationException;
    public int getOldestSuperCreditSubscription(String msisdn, String superCreditID, int balanceImpact);
    public int getOldestSuperCreditSubscription(String msisdn, String superCreditID);
    public void updateSuperCreditBalance(String msisdn, int addedBalance, int subscriptionId, String superCreditID);
    public Map<String, Long> getSuperCreditAggregateBalances(String msisdn, int balanceImpact) throws EcommerceException;
    public void deleteSuperCreditBalances(int subscriptionID)  throws EcommerceException;
    public void insertSuperCreditBalance (int subscriptionID, String superCreditID, int superCreditBalance)  throws EcommerceException;

    //Remedy 3418
    public Account findAccountByModifyId(Long transactionId) throws EcommerceException;

    //CR1564 -Utctimezone for diff region in country
    public Date convertDateToLocal(String msisdn, Date cdDate) throws EcommerceException;

	boolean checkPromotionExistsinPromotionHistory(String msisdn,
			List<String> promoCodesToCheck, List<String> packageIds) throws EcommerceException;

    /**MQC 6903 - get a count of active child packages after the parent package has been purchased,
    this will determine whether an active child has ever existed for the parent i.e for scenarios
    where the parent was successfully purchased but the child failed due to a payment or system error*/
    public int getActiveChildSubCount(String msisdn, long parentSubId, String childPackageId) throws EcommerceException; 
    
    /**
     * CR2198 - create account with a Basic Account, User Groups and packageids
     * @param basicAccount
     * @return Account
     * @throws EcommerceException
     */
    public Account createAccount(BasicAccount basicAccount, ERUserGroup[] userGroups, String packageId) throws EcommerceException;

    /**
	 * CR2165 Partner Notification Handler
	 * Get a count of the active subscriptions for the MSISDN passed.
     * @param msisdn
     * @param countryObjId
     * @param parnerId
     * @param serviceId
     * @return integer
     * @throws EcommerceException
     */
	public int getActiveSubCount(String msisdn, long countryObjId, String parnerId, String serviceId) throws EcommerceException;

}


