package com.vodafone.global.er.subscriptionmanagement;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.GoodwillCreditLimits;
import com.vizzavi.ecommerce.business.catalog.PartnerTradingLimit;
import com.vizzavi.ecommerce.business.catalog.PartnerTradingLimitStatus;
import com.vizzavi.ecommerce.business.charging.PurchaseAuthorizationException;
import com.vizzavi.ecommerce.business.charging.UsageSubscriptionAttributes;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.selfcare.MicroServiceFilter;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;
import com.vizzavi.ecommerce.business.selfcare.SpendLimits;
import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vizzavi.ecommerce.business.selfcare.SubscriptionFilter;
import com.vizzavi.ecommerce.business.selfcare.Transaction;
import com.vizzavi.ecommerce.business.selfcare.TransactionFilter;
import com.vodafone.global.er.subsmngmnt.SubsManagementException;

public interface Account {

	public List<Subscription> getActiveSubscriptionsWithService(String serviceId) throws java.rmi.RemoteException;

	public List<ResourceBalance> getResourceBalances() throws java.rmi.RemoteException, SubsManagementException;

	//  Added :     30-04-2005
	//  Added by :  VFE - PS team
	//  Purpose :   ER7 PIII Spain Migration
	public List<ResourceBalance> getResourceSuperCreditBalances() throws java.rmi.RemoteException, SubsManagementException;
	//	end VFE - PS team code

	public List<ERPromoCode> getPromoCodesUsedWithPackage(String packageId, /*REMEDY 6488*/Locale loc) throws java.rmi.RemoteException;

//Remedy 5392 TR 1/1 start
        public List<ERPromoCode> getPromoCodesUsedWithPackage(String packageId, boolean returnTrialPromo, /*REMEDY 6488*/Locale loc) throws java.rmi.RemoteException;
//Remedy 5392 TR 1/1 end
    
	public Long createSubscriptionPK() throws java.rmi.RemoteException;


	public Long createTransactionPK() throws java.rmi.RemoteException;

	public ERAccount getAccountDetails() throws java.rmi.RemoteException;

	public ERSubscription getSubscription() throws java.rmi.RemoteException;

	public ERSubscription getSubscription(long subscriptionId, boolean getResourceBalance) throws java.rmi.RemoteException;

	public List<Subscription> getSubscriptions(SubscriptionFilter subFilter) throws java.rmi.RemoteException;

	public List<Transaction> getTransactions(TransactionFilter transFilter) throws java.rmi.RemoteException;

	public boolean inactivateAccount() throws java.rmi.RemoteException;

	public void setAccount(ERAccount erAccount, boolean bSetDirty) throws java.rmi.RemoteException;

	public void setTransaction(ERTransaction erTransaction, boolean bSetDirty) throws java.rmi.RemoteException;
	
	public void setTransactions(ERTransaction[] erTransactions, boolean bSetDirty) throws java.rmi.RemoteException;

	public void setRefund(ERTransaction erTransaction) throws java.rmi.RemoteException;

	public void setCreditRefundTransactions(ERTransaction[] aRefundTrans, boolean isDirty) throws java.rmi.RemoteException;

	public void setModification(ERTransaction erTransaction, boolean bSetDirty) throws java.rmi.RemoteException;

	public List<Transaction> getRefundTransactions(TransactionFilter transFilter, boolean getSubsDetails) throws java.rmi.RemoteException;

	public List<Transaction> getModifyTransactions(TransactionFilter transFilter, boolean getAccountTransactions) throws java.rmi.RemoteException;

	public void setSubscription(ERSubscription erSubscription, boolean bSetDirty) throws java.rmi.RemoteException;

	public void setSubscriptions(ERSubscription[] erSubscriptions, boolean bSetDirty) throws java.rmi.RemoteException;

	public void doUpdUserGroupAccount(String msisdn, String ban, Locale locale, String packageId, ERUserGroup[] userGroups) throws java.rmi.RemoteException;

	public void doUpdLastValidateAccount(String msisdn, String ban, Locale locale, Date validateCallTime) throws java.rmi.RemoteException;

	public void doUpdBillingCycleDate(String msisdn, String ban, Locale locale, int billingDate) throws java.rmi.RemoteException , SubsManagementException ;

    //CR1564 -Utctimezone for diff region in country
    public void doUpdUtcTzOffset(String msisdn, String ban, Locale locale, String utcOffSet) throws java.rmi.RemoteException, SubsManagementException;
    
    public String findSuitableTimeZoneByCountry(String countryCode, String utcOffSet) throws java.rmi.RemoteException;
    
    public TimeZone findMatchingTimeZoneByCountryOffset(String utcTimezoneOffset, String country)  throws java.rmi.RemoteException;
    //CR1564 - Ends

	public boolean isContentPreviewed(String packageId,String contentName) throws java.rmi.RemoteException;

	public List<Subscription> getActiveSubscriptionsWithSuperPackage (CatalogPackage[] packageIds)throws java.rmi.RemoteException;

	public Map<String, Long> getSuperCreditAggregateBalances(int balanceImpact) throws java.rmi.RemoteException, SubsManagementException;
	
	public void consumeSuperCreditImpact(String superCreditID, int rate) throws PurchaseAuthorizationException, java.rmi.RemoteException ;

	public List<ResourceBalance> getResourceBalances(boolean factorizeResources) throws java.rmi.RemoteException, SubsManagementException; 
	
	public List<ResourceBalance> adjustResourcesNames(List<ResourceBalance> resourceBalances) throws java.rmi.RemoteException , SubsManagementException; // pol 

	public  ChargingResource updateChargingResource(int id, double amount) throws java.rmi.RemoteException , SubsManagementException;
	 
	public ERPromoCode findUniquePromoCode(String precode, String uniqueCode) throws java.rmi.RemoteException, SubsManagementException;

	public void setTranAndSub(ERTransaction tran, ERSubscription sub, boolean bSetDirty) throws java.rmi.RemoteException;
	
	public MicroServiceStatusInternal getMicroServiceStatus(MicroServiceFilter	msFilter) throws SubsManagementException, RemoteException;

	public int getUsageTransactionCount(String msisdn, long subId, String serviceId, int FairUsagePeriod) throws SubsManagementException, java.rmi.RemoteException;
	
	public int getUsageTransactionCountForSubscription(String msisdn, long subId, int FairUsagePeriod) throws SubsManagementException, java.rmi.RemoteException;
	
	public int getUsageTransactionCountForSubscriptionServiceIdList(String msisdn, long subId, int FairUsagePeriod, String servicesNotInPackageFairUsagePolicyList) throws SubsManagementException, java.rmi.RemoteException;
	
    public void setERAccountToMigrateTo(ERAccount erAccount, boolean bSetDirty) throws java.rmi.RemoteException;
    
    public int getUsageTransactionCountForSubscription(String msisdn, long subId, Date startDate) throws SubsManagementException, java.rmi.RemoteException;
    
    // CR1209 - get the number of usages for a receipient msisdn
    public int getUsageTransactionCountForRecMsisdn(String msisdn, String recMsisdn) throws SubsManagementException, java.rmi.RemoteException;
    
	//CR 1542 - return active subscriptions for usage flow
    public List<Subscription> getActiveSubscriptionsForUsage(UsageSubscriptionAttributes usageSubscriptionAttributes) throws java.rmi.RemoteException;
    
    //CR1564-find utc time value for single country from DB.
	//public Integer getCountryUtcValue(String timezone, String countryCode)throws SubsManagementException, java.rmi.RemoteException;

	//MQC 6128 - Provisioning Performance Upgrade
	public List<Transaction> getProvisioningTransactions(long subscriptionId, String serviceId, boolean getSubscriptionsForEachTransaction)throws SubsManagementException, java.rmi.RemoteException;
	
	//MQC 6137 - get lite payment and refund transactions for msisdn and partner
	public List<Transaction> getLitePartnerTransactions(TransactionFilter transFilter) throws SubsManagementException, java.rmi.RemoteException;
	
	// CR 1643 - Pre-Pay Post-Pay Split
	public void doUpdateAccountSpId(String msisdn, String spId, Locale locale) throws java.rmi.RemoteException, SubsManagementException;

	public void doUpdateAccountIsPrepay(String msisdn, String isPrepay, Locale locale) throws java.rmi.RemoteException, SubsManagementException;
	// CR 1643 - Ends
	
	//MQC 6388 - get active subscriptions for find express api
    public List<Subscription> getActiveSubscriptionsForExpress(String msisdn, String packageIds) throws java.rmi.RemoteException;

    
    //CR1923 Partner Trading Limit
    //CR1923 Extension, add country specific column
    public PartnerTradingLimitStatus getPartnerTransactionSpend(String msisdn, PartnerTradingLimit partnerTradingLimit, double transactionAmount, String packageId, String serviceId, boolean insertRecordOnly, Locale locale)throws java.rmi.RemoteException, SubsManagementException;

    //MQC6903 - reopened, get the number of usage transactions which have been carried out against child
  	//packages for a particular parent
  	public int getUsageCountChildSubscriptions (String msisdn, String parentPackageId) throws SubsManagementException, java.rmi.RemoteException;
  	
  	/**
	 * MQC 7354 - get a count of all msisdn successful usage transactions for a parent package 
	 * plus all successful usage transactions where msisdn is the recipient msisdn
	 * @param msisdn
	 * @param parentPackageId
	 * @return int
	 */
	public int getAllUsageCountChildSubscriptions (String msisdn, String parentPackageId) throws SubsManagementException, java.rmi.RemoteException;
	
 	/**
	 * CR2040 MPAY replacement.
	 * Gets cumulative goodwill credited for given partner.
	 */
  	public CumulativeGoodwillCredited 
  			getCumulativeGoodwillCredited(String partnerId) 
  					throws SubsManagementException, java.rmi.RemoteException;
  	/**
  	 * CR2040 MPAY replacement.
  	 * Updates cumulative goodwill credited for given partner.
  	 * @param partnerId
  	 * @param cumulativeGoodwillCredited
  	 */
	public void updateCumulativeGoodwillCredited(String partnerId,
			CumulativeGoodwillCredited cumulativeGoodwillCredited) 
					throws SubsManagementException, java.rmi.RemoteException;
	
	/**
	 * CR2040 MPAY replacement.
	 * Gets spend limits and cumulative spend for given account.
	 * @return spend limits including cumulative spend
	 * @throws SubsManagementException
	 * @throws java.rmi.RemoteException
	 */
	public SpendLimits getSpendLimits() 
			throws SubsManagementException, java.rmi.RemoteException;
	
	/**
	 * CR2040 MPAY replacement.
	 * Updates cumulative spend for account.
	 * @param msisdn
	 * @param spendLimits (cumulative spend)
	 */
	public void updateCumulativeSpend(SpendLimits spendLimits) 
			throws SubsManagementException, java.rmi.RemoteException;
	
	/**
	 * CR2040 MPAY replacement.
	 * Updates spend limits for account.
	 * @param spendLimits
	 */
	public void updateSpendLimits(SpendLimits spendLimits) 
			throws SubsManagementException, java.rmi.RemoteException;
	
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
					throws SubsManagementException, java.rmi.RemoteException;
	
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
					throws SubsManagementException, java.rmi.RemoteException;

	 /**
     * CR2198 - update account
     * @param account
     * @throws SubsManagementException
	 * @throws java.rmi.RemoteException
     */
	public void doUpdateAccount(ERAccount account) throws SubsManagementException, java.rmi.RemoteException;
	
	/**
	 * CR2198 - update account Child Service Provider Id
	 * @param msisdn
	 * @param childSpId
	 * @throws java.rmi.RemoteException
	 * @throws SubsManagementException
	 */
	public void doUpdateAccountChildSpId(String msisdn, String childSpId) throws java.rmi.RemoteException, SubsManagementException;

	/**
	 * CR2198 - update account Service Provider Type
	 * @param msisdn
	 * @param spType
	 * @throws java.rmi.RemoteException
	 * @throws SubsManagementException
	 */
	public void doUpdateAccountSpType(String msisdn, String spType) throws java.rmi.RemoteException, SubsManagementException;

	/**
	 * CR2040 MPAY replacement.
	 * Gets cumulative spend for given partner and service provider.
	 * @param partnerId partner id
	 * @param serviceProviderId service provider id
	 */
  	public Map<String, CumulativeSpend>
  			getPartnersCumulativeSpends(String partnerId, String serviceProviderId) 
  					throws SubsManagementException, java.rmi.RemoteException;
  	
  
  	/**
  	 * CR2203 MPAY replacment.  Updates cumulative spends for given partners.
  	 * @param partnerCumulativeSpend
  	 * @param serviceProviderCumulativeSpend
  	 * @throws SubsManagementException
  	 * @throws java.rmi.RemoteException
  	 */
  	public void updatePartnersCumulativeSpends(CumulativeSpend partnerCumulativeSpend,
  			CumulativeSpend serviceProviderCumulativeSpend) 
  					throws SubsManagementException, java.rmi.RemoteException;
  	
  	/**
	 * MQC 9593 - get a count of all modify msisdn transactions greater than a particular transaction id
	 * @param account_obj_id
	 * @param account_obj_id
	 * @return int
	 */
	public int getModifyMsisdnTransactionCount (Long account_obj_id, String transactionId) throws SubsManagementException, java.rmi.RemoteException;
}
