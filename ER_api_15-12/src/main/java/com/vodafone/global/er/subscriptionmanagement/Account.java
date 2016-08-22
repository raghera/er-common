package com.vodafone.global.er.subscriptionmanagement;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.FinderException;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.GoodwillCreditLimits;
import com.vizzavi.ecommerce.business.catalog.PartnerTradingLimit;
import com.vizzavi.ecommerce.business.catalog.PartnerTradingLimitStatus;
import com.vizzavi.ecommerce.business.charging.UsageSubscriptionAttributes;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.selfcare.*;
import com.vodafone.global.er.subsmngmnt.SubsManagementException;

/**
 * the account bean ejb interface spec
 *
 */
public interface Account  {

	public void setAccount(BasicAccount account);
	
	public List<Subscription> getActiveSubscriptionsWithService(String serviceId) throws RemoteException;

	public List<ResourceBalance> getResourceBalances() throws RemoteException, SubsManagementException;

	public List<ERPromoCode> getPromoCodesUsedWithPackage(String packageId, /*REMEDY 6488*/Locale loc) throws RemoteException;

	public List<ERPromoCode> getPromoCodesUsedWithPackage(String packageId, boolean returnTrialPromo, /*REMEDY 6488*/Locale loc) throws EcommerceException;
	
	public List<ERPromoCode> getPromoCodesUsedWithSubscriptionId(String subscriptionId, Locale loc) throws EcommerceException;
    
	public Long createSubscriptionPK() throws EcommerceException;

	public Long createTransactionPK() throws EcommerceException;

	public ERAccount getAccountDetails() throws EcommerceException;

//	public ERSubscription getSubscription() throws RemoteException;

	public ERSubscription getSubscription(long subscriptionId, boolean getResourceBalance) throws EcommerceException;

	public List<Subscription> getSubscriptions(SubscriptionFilter subFilter) throws EcommerceException;

	public List<Transaction> getTransactions(TransactionFilter transFilter) throws EcommerceException;

	public boolean inactivateAccount() throws RemoteException;

	public void setAccount(ERAccount erAccount, boolean bSetDirty) throws RemoteException;

	public void setTransaction(ERTransaction erTransaction, boolean bSetDirty) throws RemoteException;
	
	public void setTransactions(ERTransaction[] erTransactions, boolean bSetDirty) throws EcommerceException,RemoteException;

	public void setRefund(ERTransaction erTransaction) throws RemoteException;

	public void setCreditRefundTransactions(ERTransaction[] aRefundTrans, boolean isDirty) throws RemoteException;

	public void setModification(ERTransaction erTransaction, boolean bSetDirty) throws EcommerceException;

	public List<Transaction> getRefundTransactions(TransactionFilter transFilter, boolean getSubsDetails) throws EcommerceException;

	public List<Transaction> getModifyTransactions(TransactionFilter transFilter, boolean getAccountTransactions) throws EcommerceException;

	public void setSubscription(ERSubscription erSubscription, boolean bSetDirty) throws EcommerceException;

	public void setSubscriptions(ERSubscription[] erSubscriptions, boolean bSetDirty) throws EcommerceException,RemoteException;

	public void doUpdUserGroupAccount(String msisdn, String ban, Locale locale, String packageId, ERUserGroup[] userGroups) throws RemoteException;

	public void doUpdLastValidateAccount(String msisdn, String ban, Locale locale, Date validateCallTime) throws RemoteException;

	public void doUpdBillingCycleDate(String msisdn, String ban, Locale locale, int billingDate) throws RemoteException , SubsManagementException ;

    //CR1564 -Utctimezone for diff region in country
    public void doUpdUtcTzOffset(String msisdn, String ban, Locale locale, String utcOffSet) throws RemoteException, SubsManagementException;

	public boolean isContentPreviewed(String packageId, String contentName) throws RemoteException;

	public List<Subscription> getActiveSubscriptionsWithSuperPackage (CatalogPackage[] packageIds)throws RemoteException;

	public List<ResourceBalance> getResourceBalances(boolean factorizeResources) throws EcommerceException, SubsManagementException; 
	
	//JIRA ET-49 - add country obj id
	public List<ResourceBalance> adjustResourcesNames(List<ResourceBalance> resourceBalances, int countryObjId) throws EcommerceException , SubsManagementException; // pol 

	public  ChargingResource updateChargingResource(int id, double amount) throws RemoteException , SubsManagementException;
	 
	public ERPromoCode findUniquePromoCode(String precode, String uniqueCode) throws RemoteException, SubsManagementException;

	public void setTranAndSub(ERTransaction tran, ERSubscription sub, boolean bSetDirty) throws EcommerceException,RemoteException;
	
	public MicroServiceStatusInternal getMicroServiceStatus(MicroServiceFilter	msFilter) throws SubsManagementException, RemoteException;

	public int getUsageTransactionCount(String msisdn, long subId, String serviceId, int FairUsagePeriod) throws SubsManagementException, RemoteException;
	
	public int getUsageTransactionCountForSubscription(String msisdn, long subId, int FairUsagePeriod) throws SubsManagementException, RemoteException;
	
	public int getUsageTransactionCountForSubscriptionServiceIdList(String msisdn, long subId, int FairUsagePeriod, String servicesNotInPackageFairUsagePolicyList) throws SubsManagementException, RemoteException;
	
    public void setERAccountToMigrateTo(ERAccount erAccount, boolean bSetDirty) throws RemoteException;
    
    public int getUsageTransactionCountForSubscription(String msisdn, long subId, Date startDate) throws SubsManagementException, RemoteException;
    
    // CR1209 - get the number of usages for a receipient msisdn
    public int getUsageTransactionCountForRecMsisdn(String msisdn, String recMsisdn) throws SubsManagementException, RemoteException;
    
	//CR 1542 - return active subscriptions for usage flow
    public List<Subscription> getActiveSubscriptionsForUsage(UsageSubscriptionAttributes usageSubscriptionAttributes) throws RemoteException;
    
    //CR1564-find utc time value for single country from DB.
	//public Integer getCountryUtcValue(String timezone, String countryCode)throws SubsManagementException, RemoteException;

	//MQC 6128 - Provisioning Performance Upgrade
	public List<Transaction> getProvisioningTransactions(long subscriptionId, String serviceId, boolean getSubscriptionsForEachTransaction)throws SubsManagementException, RemoteException;
	
	//MQC 6137 - get lite payment and refund transactions for msisdn and partner
	public List<Transaction> getLitePartnerTransactions(TransactionFilter transFilter) throws SubsManagementException, EcommerceException;
	
	// CR 1643 - Pre-Pay Post-Pay Split
	public void doUpdateAccountSpId(String msisdn, String spId, Locale locale) throws EcommerceException, SubsManagementException;

	public void doUpdateAccountIsPrepay(String msisdn, String isPrepay, Locale locale) throws EcommerceException, SubsManagementException;
	// CR 1643 - Ends
	
	//MQC 6388 - get active subscriptions for find express api
    public List<Subscription> getActiveSubscriptionsForExpress(String msisdn, String packageIds) throws RemoteException;

    
    //CR1923 Partner Trading Limit
    //CR1923 Extension, add country specific column
    public PartnerTradingLimitStatus getPartnerTransactionSpend(String msisdn, PartnerTradingLimit partnerTradingLimit, double transactionAmount, String packageId, String serviceId, boolean insertRecordOnly, Locale locale)throws RemoteException, SubsManagementException;

    /**MQC6903 - reopened, get the number of usage transactions which have been carried out against child
  	packages for a particular parent*/
  	public int getUsageCountChildSubscriptions (String msisdn, String parentPackageId) throws SubsManagementException, RemoteException;
  	
  	/**
	 * MQC 7354 - get a count of all msisdn successful usage transactions for a parent package 
	 * plus all successful usage transactions where msisdn is the recipient msisdn
	 * @param msisdn
	 * @param parentPackageId
	 * @return int
	 */
	public int getAllUsageCountChildSubscriptions (String msisdn, String parentPackageId) throws SubsManagementException, RemoteException;
	
 	/**
	 * CR2040 MPAY replacement.
	 * Gets cumulative goodwill credited for given partner.
	 */
  	public CumulativeGoodwillCredited 
  			getCumulativeGoodwillCredited(String partnerId) 
  					throws SubsManagementException,EcommerceException ;
  	/**
  	 * CR2040 MPAY replacement.
  	 * Updates cumulative goodwill credited for given partner.
  	 * @param partnerId
  	 * @param cumulativeGoodwillCredited
  	 */
	public void updateCumulativeGoodwillCredited(String partnerId,
			CumulativeGoodwillCredited cumulativeGoodwillCredited) 
					throws SubsManagementException,EcommerceException/*, RemoteException*/;
	
	/**
	 * CR2040 MPAY replacement.
	 * Gets spend limits and cumulative spend for given account.
	 * @return spend limits including cumulative spend
	 * @throws SubsManagementException
	 * @throws RemoteException
	 */
	public SpendLimits getSpendLimits() 
			throws SubsManagementException, EcommerceException;
	
	/**
	 * CR2040 MPAY replacement.
	 * Updates cumulative spend for account.
	 * @param msisdn
	 * @param spendLimits (cumulative spend)
	 */
	public void updateCumulativeSpend(SpendLimits spendLimits) 
			throws SubsManagementException, EcommerceException;
	
	/**
	 * CR2040 MPAY replacement.
	 * Updates spend limits for account.
	 * @param spendLimits
	 */
	public void updateSpendLimits(SpendLimits spendLimits) 
			throws SubsManagementException, EcommerceException;
	
	/**
	 * CR2040 MPAY replacement.
	 * Inserts goodwill credit limits exceeded record for reporting.
	 * @param amount
	 * @param partnerId
	 * @param limits
	 * @param credited
	 * @param packageId
	 * @param serviceId
	 */
	public void insertGoodwillCreditLimitsExceeded(double amount,
			String partnerId, GoodwillCreditLimits limits, 
			CumulativeGoodwillCredited credited,
			String packageId, String serviceId)
					throws SubsManagementException, EcommerceException;
	
	/**
	 * CR2040 MPAY replacement.
	 * Inserts spend limits exceeded record for reporting.
	 * @param amount
	 * @param partnerId
	 * @param limits
	 * @param packageId
	 * @param serviceId
	 * @param reasonCode reason that limits exceeded
	 */
	public void insertSpendLimitsExceeded(double amount,
			String partnerId, SpendLimits limits,
			String packageId, String serviceId, ReasonCode reasonCode)
					throws SubsManagementException, EcommerceException;

	 /**
     * CR2198 - update account
     * @param account
     * @throws SubsManagementException
	 * @throws RemoteException
     */
	public void doUpdateAccount(ERAccount account) throws SubsManagementException, RemoteException;
	
//	/**
//	 * CR2198 - update account Child Service Provider Id
//	 * @param msisdn
//	 * @param childSpId
//	 * @throws RemoteException
//	 * @throws SubsManagementException
//	 */
//	public void doUpdateAccountChildSpId(String msisdn, String childSpId) throws EcommerceException, SubsManagementException;

//	/**
//	 * CR2198 - update account Service Provider Type
//	 * @param msisdn
//	 * @param spType
//	 * @throws RemoteException
//	 * @throws SubsManagementException
//	 */
//	public void doUpdateAccountSpType(String msisdn, String spType) throws EcommerceException, SubsManagementException;

	/**
	 * CR2040 MPAY replacement.
	 * Gets cumulative spend for given partner and service provider.
	 * @param partnerId partner id
	 * @param serviceProviderId service provider id
	 */
  	public Map<String, CumulativeSpend>
  			getPartnersCumulativeSpends(String partnerId, String serviceProviderId) 
  					throws SubsManagementException, EcommerceException;
  	
  
  	/**
  	 * CR2203 MPAY replacment.  Updates cumulative spends for given partners.
  	 * @param partnerCumulativeSpend
  	 * @param serviceProviderCumulativeSpend
  	 * @throws SubsManagementException
  	 * @throws RemoteException
  	 */
  	public void updatePartnersCumulativeSpends(CumulativeSpend partnerCumulativeSpend,
  			CumulativeSpend serviceProviderCumulativeSpend) 
  					throws SubsManagementException, EcommerceException;
  	
  	/**
	 * MQC 9593 - get a count of all modify msisdn transactions greater than a particular transaction id
	 * @param account_obj_id
	 * @param account_obj_id
	 * @return int
	 */
	public int getModifyMsisdnTransactionCount (Long account_obj_id, String transactionId) throws SubsManagementException, RemoteException;
	
	
	/**
	 * JIRAET65 - return the last completed renewal transaction rate tag
	 * @param subscriptionIdLong
	 * @param events
	 * @return String
	 * @throws SubsManagementException
	 */
	public List<String> getLatestCompletedTransactionRateTag(Long subscriptionIdLong, int events) throws SubsManagementException, RemoteException;

	/**
	 * JIRA ET148 Add SMS blacklist flag to opt out of courtesy SMS notifications
	 * @param custcareAttrs
	 * @throws SubsManagementException
	 */
	public void doUpdateAccountWithSendCourtesyFlag(CustcareAttributes custcareAttrs) throws SubsManagementException;
	
	/**
     * JIRA ET196 Get account subscription promo-codes info
     * @param msisdn
     * @return List<SubscriptionPromoCode>
     * @throws SubsManagementException
     * @throws RemoteException
     */
	public List<SubscriptionPromoCode> getSubscriptionPromoCodes(String msisdn, int countryObjId) throws SubsManagementException/*, RemoteException*/;
	
	/**
	 * JIRA ET196 Inactivate subscription promo-code
	 * @param accountId
	 * @param clientId
	 * @param subscriptionId
	 * @param packageId
	 * @param countryObjId
	 * @return List<InactivateSubscriptionPromoCodeAuthorization>
	 * @throws SubsManagementException
	 * @throws RemoteException
	 */
	public List<InactivateSubscriptionPromoCodeAuthorization> inactivateSubscriptionPromoCode(long accountId, int countryObjId, InactivateSubscriptionPromoCodeAttributes inactivateSubPromoAttrs) 
			throws SubsManagementException/*, RemoteException*/;
	
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

  //CR1564 -Utctimezone for diff region in country
  	//ET-153 remove convertDateToLocal timezone
      //public Date convertDateToLocal(String msisdn, String homeTimezone, Date cdDate) throws SubsManagementException, java.rmi.RemoteException;

  	public boolean checkPromotionExistsinPromotionHistory(String msisdn, List<String> promoCodesToCheck, List<String> packageIds) throws  EcommerceException;

      //MQC 6903 - get a count of active child packages after the parent package has been purchased,
      //this will determine whether an active child has ever existed for the parent i.e for scenarios
      //where the parent was succesfully purchased but the child failed due to a payment or system error
      public int getActiveChildSubCount(String msisdn, long parentSubId, String childPackageId) throws SubsManagementException, EcommerceException;

      /**
  	 * CR2165 Partner Notification Handler
  	 * Get a count of the active subscriptions for the MSISDN passed.
       * @param msisdn
       * @param countryObjId
       * @param parnerId
       * @param serviceId
       * @return integer
       * @throws SubsManagementException, java.rmi.RemoteException
       */
  	public int getActiveSubCount(String msisdn, long countryObjId, String parnerId, String serviceId) throws SubsManagementException, EcommerceException;
  	
  	 
    public void deleteSubscription(String subId) throws FinderException, EcommerceException;

}
