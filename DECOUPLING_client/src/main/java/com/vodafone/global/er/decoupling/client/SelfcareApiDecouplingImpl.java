package com.vodafone.global.er.decoupling.client;

import com.vizzavi.ecommerce.business.charging.BaseAuthorization;
import com.vizzavi.ecommerce.business.common.*;
import com.vizzavi.ecommerce.business.selfcare.*;
import com.vizzavi.ecommerce.common.Utils;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.business.selfcare.BalanceFilter;
import com.vodafone.global.er.business.selfcare.MicroServiceStatus;
import com.vodafone.global.er.business.selfcare.ParentTransaction;
import com.vodafone.global.er.decoupling.PayloadConstants;
import com.vodafone.global.er.decoupling.binding.request.*;
import com.vodafone.global.er.decoupling.binding.request.SelfcareFullSubscriptions;
import com.vodafone.global.er.decoupling.binding.request.SelfcareFullTransactions;
import com.vodafone.global.er.decoupling.binding.request.SubscriptionFilterType;
import com.vodafone.global.er.decoupling.binding.response.ChargingResourceType;
import com.vodafone.global.er.decoupling.binding.response.*;
import com.vodafone.global.er.decoupling.binding.response.MicroServiceType;
import com.vodafone.global.er.decoupling.binding.response.ResourceBalanceType;
import com.vodafone.global.er.decoupling.binding.response.SubscriptionPromoCodeType;
import com.vodafone.global.er.decoupling.binding.response.v2.SelfcareFullSubscriptionsV2;
import com.vodafone.global.er.subsmngmnt.SubsManagementException;
import org.apache.log4j.Logger;

import java.util.*;

import com.vodafone.global.er.business.selfcare.BalanceFilter;


public class SelfcareApiDecouplingImpl extends BaseErApiDecouplingImpl implements SelfcareApi {


	private static final Logger log = Logger.getLogger(SelfcareApiDecouplingImpl.class);


	private static final String date= "$Date: 2015/12/02 15:01:05 $";
	private static final String version="$Revision: 1.13 $";

	public SelfcareApiDecouplingImpl(Locale locale, String clientId)	{
		super(locale, clientId);
		log.info("SelfcareApiDecouplingImpl "+version+" dated "+date);
	}


	@Override
	public Subscription[] getSubscriptions(String clientId, String msisdn, int device) throws EcommerceException
	{
		return this.getSubscriptions(clientId, msisdn, device, null);
	}

	private void updateTransactionAttrs(SubscriptionFilter filter, SubscriptionFilterType filterType) {
        final String delimiter = ",";
        StringBuffer sb = new StringBuffer();
	    if(filter.includePaymentTxns()) {
            sb.append("payment");
            sb.append(delimiter);
        }
        if (filter.includeRefundTxns()) {
            sb.append("refund");
            sb.append(delimiter);
        }
        if (filter.includeModifyTxns()) {
            sb.append("modify");
            sb.append(delimiter);
        }

        if(!sb.toString().isEmpty()) {
            sb.deleteCharAt(sb.lastIndexOf(delimiter));
            filterType.setIncludeTxns(sb.toString());
        }
    }

	@Override
	public Subscription[] getSubscriptions(String clientId, String msisdn, int device, SubscriptionFilter filter) throws EcommerceException {
		log.debug("Enter SelfcareApiDecouplingImpl.getSubscriptions");
		checkNullParams(msisdn);
		final SelfcareFullSubscriptions request_ = createRequest(PayloadConstants.SELFCARE_FULL_SUBSCRIPTIONS_REQUEST_PAYLOAD);

		final SubscriptionFilterType subFilType = new SubscriptionFilterType();
		request_.setDevice(device);
		request_.setMsisdn(msisdn);

		if(filter != null)
		{
		    updateTransactionAttrs(filter, subFilType);

			subFilType.setChargingMethod(filter.getChargingMethod());
			subFilType.setClientId(filter.getClientId());

			subFilType.setSubscriptionId(filter.getSubscriptionId());

			if(filter.getStartDate() != null && filter.getStartDate().getTime() > 0 && filter.getEndDate() != null)
			{
				final Calendar c_ = Calendar.getInstance();
				c_.setTime(filter.getStartDate());
				subFilType.setStartDate(c_);
				final Calendar c1_ = Calendar.getInstance();
				c1_.setTime(filter.getEndDate());
				subFilType.setEndDate(c1_);
			}

			if(filter.getMaxEvents() > 0)
				subFilType.setMaxEvents(filter.getMaxEvents());

			subFilType.setPackageClass((filter.getPackageClass() != null?filter.getPackageClass():null));

			// MQC 9655 
			if (filter.getParentPackageId() != null && !filter.getParentPackageId().isEmpty()) {
				subFilType.setParentPackageId(filter.getParentPackageId());
			}

			if(filter.getSubscriptionEndDateStartValue() != null && filter.getSubscriptionEndDateEndValue() != null)
			{
				final Calendar c1_ = Calendar.getInstance();
				c1_.setTime(filter.getSubscriptionEndDateStartValue());
				subFilType.setSubscriptionEndDateStartValue(c1_);
				final Calendar c_ = Calendar.getInstance();
				c_.setTime(filter.getSubscriptionEndDateEndValue());
				subFilType.setSubscriptionEndDateEndValue(c_);
			}
			subFilType.setIsUsingLocalDate(filter.isUsingLocalDates());
			subFilType.setSubscriptionStatus(filter.getSubscriptionStatus());
			subFilType.setPackageId(filter.getPackageId());

			subFilType.setTransactionsNotRequired(filter.getTransactionsNotRequired());

			subFilType.setUseMaxEventsForTrans(filter.isUseMaxEventsForTransactions()); // MQC9072

			subFilType.setTariff(filter.getTariff());

			subFilType.setPackageId(filter.getPackageId());

			request_.setSubscriptionFilter(subFilType);
		}

		//ET-280 // added v2 flow start
				//hidden parameter to switch back the v2 call by making it true
		if ( ConfigProvider.getPropertyAsBoolean("er.decoupling.use.v2", true))	{
			SelfcareFullSubscriptionsV2 subscriptionsType_;
			try {
                log.debug("Calling DecouplingClient V2 from  SelfcareApiDecouplingImpl.getSubscriptions");
				subscriptionsType_ = sendRequestAndGetResponse(PayloadConstants.SELFCARE_FULL_SUBSCRIPTIONS_REQUEST_PAYLOAD, request_, com.vodafone.global.er.decoupling.binding.response.v2.SelfcareFullSubscriptionsV2.class, 2);
                log.debug("Received response in SelfcareApiDecouplingImpl.getSubscriptions");
			} catch (EcommerceException e) {
				throw new SubsManagementException(e);
			}

            log.debug("Converting SelfcareApiDecouplingImpl response ");
			//List<Subscription> listsubs = converter.buildSubscriptions(subscriptionsType_.getSubscription());
			List<Subscription> listsubs = converter.buildPackageForSubscription(subscriptionsType_.getSubscription(),clientId);
            log.debug("Converted subs in SelfcareApiDecouplingImpl " + listsubs);
			//listsubs = converter.buildPackageForSubscription(listsubs);
			return listsubs.toArray(new Subscription[listsubs.size()]);
			
		
		//ET-280 // added v2 flow end
		}else{
            log.debug("Calling DecouplingClient from  SelfcareApiDecouplingImpl.getSubscriptions V1");
			final com.vodafone.global.er.decoupling.binding.response.SelfcareFullSubscriptions subscriptionsType_ =
					sendRequestAndGetResponse(PayloadConstants.SELFCARE_FULL_SUBSCRIPTIONS_REQUEST_PAYLOAD, request_, com.vodafone.global.er.decoupling.binding.response.SelfcareFullSubscriptions.class);
            log.debug("Response recieved from DecouplingClient in SelfcareApiDecouplingImpl.getSubscriptions V1");
			return converter.convertSubscriptionsArray(subscriptionsType_);
		}
	}

	@Override
	public boolean modifySubscriptionChargingMethod(String clientId, String msisdn , int deviceType, CustcareAttributes custcareAttributes) throws EcommerceException
	{

		checkNullParams(msisdn);
		final ModifySubscriptionChargingMethod request_ = createRequest(PayloadConstants.MODIFY_SUBSCRIPTION_CHARGING_METHOD_REQUEST_PAYLOAD);

		request_.setMsisdn(msisdn);
		request_.setChargingMethod(custcareAttributes.getNewChargingMethod());
		request_.setCsrId(custcareAttributes.getCsrId());
		request_.setDeviceType(deviceType);
		request_.setReason(custcareAttributes.getReason());
		request_.setSubscriptionId(custcareAttributes.getPackageSubId());
		//MQC 9420 flag to immediately inactive a subscription if it is in grace or suspended status
		request_.setInactivateGraceOrSuspendedSub(custcareAttributes.isInactivateGraceOrSuspendedSubscription());
		//JIRAET127 - set the penalty charge
		request_.setApplyPenaltyCharge(custcareAttributes.isApplyPenaltyCharge());

		final ModifySubscriptionChargingMethodResponse modifySubscriptionChargingMethod_ = 
				sendRequestAndGetResponse(PayloadConstants.MODIFY_SUBSCRIPTION_CHARGING_METHOD_REQUEST_PAYLOAD, request_, ModifySubscriptionChargingMethodResponse.class);
		return modifySubscriptionChargingMethod_.isSuccess();

	}

	@Override
	public boolean modifySubscriptionChargingMethod(String clientId, String msisdn, int deviceType, String packageSubId, int chargingMethod) throws EcommerceException
	{
		final CustcareAttributes custcareAttributes_ = new CustcareAttributes();
		custcareAttributes_.setPackageSubId(packageSubId);
		custcareAttributes_.setNewChargingMethod(chargingMethod);

		return this.modifySubscriptionChargingMethod(clientId, msisdn, deviceType, custcareAttributes_);
	}


	@Override
	public Transaction[] getTransactions(String clientId, String msisdn, int accessDevice, TransactionFilter transactionFilter) throws EcommerceException
	{
		checkNullParams(msisdn);

		final com.vodafone.global.er.decoupling.binding.request.SelfcareFullTransactions request_ = 
				createRequest(PayloadConstants.SELFCARE_FULL_TRANSACTIONS_REQUEST_PAYLOAD);
		request_.setMsisdn(msisdn);
		request_.setDevice(accessDevice);

		if(transactionFilter != null)
		{
			final SelfcareFullTransactions.TransactionsFilter transactionsFilterType_ = converter.buildTransactionFilter(transactionFilter);

			request_.setTransactionsFilter(transactionsFilterType_);
		}

		final com.vodafone.global.er.decoupling.binding.response.SelfcareFullTransactions selfcareTransactionsTypeImpl_ = 
				sendRequestAndGetResponse(PayloadConstants.SELFCARE_FULL_TRANSACTIONS_REQUEST_PAYLOAD, request_, com.vodafone.global.er.decoupling.binding.response.SelfcareFullTransactions.class);

		try	{
			return converter.convertTransactionArray(selfcareTransactionsTypeImpl_);
		}catch(Exception e){
			throw new EcommerceException(e);
		}

	}




	/**
	 * This implementation should fail since it passes an msisdn of 0.<br/>
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Transaction getTransaction(String clientId , String transactionId) throws EcommerceException, TransactionNotFoundException	{
		return getTransaction(clientId, "0", Long.valueOf(transactionId));
		//throw new EcommerceException("this method is not supported, for security reasons - use getTransaction(String, String, long) instead");
	}

	/**
	 * {@inheritDoc}
	 *  @throws TransactionNotFoundException if there is no such transaction
	 *  @throws EcommerceException in the event of any error talking to ER
	 *  
	 */
	@Override
	public Transaction getTransaction(String clientId, String msisdn, long transactionId) throws EcommerceException, TransactionNotFoundException
	{	
		checkNullParams(transactionId, msisdn);

		final com.vodafone.global.er.decoupling.binding.request.SelfcareFullTransactions request = 
				createRequest(PayloadConstants.SELFCARE_FULL_TRANSACTIONS_REQUEST_PAYLOAD);

		final SelfcareFullTransactions.TransactionsFilter transFilType = new SelfcareFullTransactions.TransactionsFilter();
		transFilType.setTransactionIdLong(transactionId);

		request.setMsisdn(msisdn);
		request.setDevice(DeviceType.CCA);
		request.setTransactionsFilter(transFilType);

		final com.vodafone.global.er.decoupling.binding.response.SelfcareFullTransactions selfcareFullTransactionsTypeImpl_ = 
				sendRequestAndGetResponse(PayloadConstants.SELFCARE_FULL_TRANSACTIONS_REQUEST_PAYLOAD, request, com.vodafone.global.er.decoupling.binding.response.SelfcareFullTransactions.class);


		if (selfcareFullTransactionsTypeImpl_.getTransaction().isEmpty())
			throw new TransactionNotFoundException("no transaction found in ER for msisdn ["+msisdn+"] and id "+transactionId);
		return converter.convertTransactionArray(selfcareFullTransactionsTypeImpl_)[0];

	}



	@Override
	public ResourceBalance[] getBalances(String clientId, String msisdn, int device)  throws EcommerceException
	{
		return getBalances(msisdn, clientId, device, null);
	}

	@Override
	public Subscription getSubscription(String clientId, String msisdn, int device, String packageSubId) throws EcommerceException
	{
		final SubscriptionFilter subscriptionFilter_ = EcomApiFactory.getSubscriptionFilter();
		subscriptionFilter_.setSubscriptionId(packageSubId);	

		Subscription[] subscriptionArr = this.getSubscriptions(clientId, msisdn, device, subscriptionFilter_);
		if(subscriptionArr==null||subscriptionArr.length==0){
			throw new SubscriptionNotFoundException("no subscription found in ER for msisdn "+msisdn+" with subscriptonId "+packageSubId);
		}

		return subscriptionArr[0];
	}

	@Override
	public BaseAuthorization getNextPaymentAmount(String clientId, String msisdn, String subscriptionId) throws EcommerceException
	{
		checkNullParams(msisdn, subscriptionId);
		final GetNextPaymentAmountRequest request_ = createRequest(PayloadConstants.GET_NEXT_PAYMENT_AMOUNT_REQUEST_PAYLOAD);

		request_.setMsisdn(msisdn);
		request_.setSubscriptionId(subscriptionId);

		final GetNextPaymentAmountResponse getNextPaymentAmountType_ = sendRequestAndGetResponse(PayloadConstants.GET_NEXT_PAYMENT_AMOUNT_REQUEST_PAYLOAD, request_, GetNextPaymentAmountResponse.class	, clientId);

		return converter.convertNextPaymentAmountType(getNextPaymentAmountType_.getNextPaymentAmount());

	}



	@Override
	public MicroServiceStatus[] getMicroServiceStatus(String clientId, String msisdn, MicroServiceFilter microServiceFilter) throws EcommerceException
	{

		checkNullParams(msisdn);
		final SelfcareMicroServicesRequest request_ = createRequest(PayloadConstants.SELFCARE_MICRO_SERVICE_REQUEST_PAYLOAD);
		request_.setMsisdn(msisdn);

		if(microServiceFilter != null)
		{
			final SelfcareMicroServicesRequest.MicroServiceFilter microServiceFilterType_ = new SelfcareMicroServicesRequest.MicroServiceFilter();
			microServiceFilterType_.setSubscriptionId(microServiceFilter.getSubscriptionId());
			request_.setMicroServiceFilter(microServiceFilterType_);
		}

		final SelfcareMicroServices selfcareMicroServicesType_ = //(SelfcareMicroServicesType)payload_;
				sendRequestAndGetResponse(PayloadConstants.SELFCARE_MICRO_SERVICE_REQUEST_PAYLOAD, request_, SelfcareMicroServices.class);


		final List<MicroServiceType> microServiceTypes_ =  selfcareMicroServicesType_.getMicroService();
		final List<MicroServiceStatus> microServiceStatuses_ = new ArrayList<>();
		for (final MicroServiceType microServiceType_ : microServiceTypes_)
		{
			final MicroServiceStatus microServiceStatus_ = new MicroServiceStatus();
			microServiceStatus_.setAccessDuration(microServiceType_.getAccessDuration());
			microServiceStatus_.setServiceName(microServiceType_.getName());
			microServiceStatuses_.add(microServiceStatus_);
		}

		return microServiceStatuses_.toArray(new MicroServiceStatus[microServiceStatuses_.size()]);

	}

	/**
	 * This decoupling implementation won't work since no msisdn is required.<br/>
	 * use {@link #getTransaction(String, String, long)} instead
	 */
	@Deprecated
	@Override
	public Transaction getTransaction(String clientId, TransactionFilter filter) throws EcommerceException {

		final Transaction[] transactions = this.getTransactions(clientId,"0",DeviceType.WEB,filter);

		for (final Transaction transaction : transactions) {
			if (transaction.getTransactionIdLong() == filter.getTransactionIdLong()){
				return transaction;
			}
		}

		return null;
	}

	@Override
	public boolean modifySubscriptionChargingMethod(String clientId, String msisdn, int deviceType, String packageSubId, int chargingMethod, String csrId, String reason) throws AccountNotFoundException, EcommerceException {

		if (msisdn==null ||packageSubId==null || chargingMethod <=0 )
			throw new EcommerceException("msisdn, subId and charging method must all be populated");

		final ModifySubscriptionChargingMethod request = new ModifySubscriptionChargingMethod();

		request.setMsisdn(msisdn);
		request.setDeviceType(deviceType);
		request.setCsrId(csrId);
		request.setChargingMethod(chargingMethod);
		request.setSubscriptionId(packageSubId);
		request.setReason(reason);

		final ModifySubscriptionChargingMethodResponse result = sendRequestAndGetResponse(PayloadConstants.MODIFY_SUBSCRIPTION_CHARGING_METHOD_REQUEST_PAYLOAD, request, ModifySubscriptionChargingMethodResponse.class);
		return result.isSuccess();

	}

	@Override
	public Transaction[] getTransactions(String clientId, String msisdn, int deviceType,
			Date startDate, Date endDate, int maxNum) throws EcommerceException {
		TransactionFilter filter = EcomApiFactory.getTransactionFilter();
		filter.setEndDate(endDate);
		filter.setStartDate(startDate);
		filter.setMaxEvents(maxNum);
		return getTransactions(clientId, msisdn, deviceType, filter);
	}


	@Override
	public Transaction[] getPaymentTransactions(String clientId, String msisdn, int deviceType,
			TransactionFilter filter) throws EcommerceException {
		filter.setMonetaryEventsOnly(true);
		return getTransactions(clientId, msisdn, deviceType, filter);
	}


	@Override
	public ResourceBalance[] getBalances(String msisdn, String clientId, int deviceId,
			BalanceFilter filter) throws AccountNotFoundException, EcommerceException {

		checkNullParams(msisdn, clientId);

		final GetBalancesRequest request = createRequest(PayloadConstants.GET_BALANCES_REQUEST_PAYLOAD);
		request.setMsisdn(msisdn);
		request.setClientId(clientId);
		request.setDeviceId(deviceId);

		if (filter!=null)	{
			request.setBalaceFilter(converter.buildBalanceFilter(filter));
		}

		final GetBalancesResponse getBalancesResponseType_ =sendRequestAndGetResponse(PayloadConstants.GET_BALANCES_REQUEST_PAYLOAD, request, GetBalancesResponse.class);

		final List<ResourceBalance> balanceResponse_ = new ArrayList<>();
		final List<ResourceBalanceType> balanceReponseTypes_ = getBalancesResponseType_.getBalance();
		for (final ResourceBalanceType getBalanceResponseType_ : balanceReponseTypes_)
		{
			final ChargingResourceType chargingresourceType_ = getBalanceResponseType_.getChargingResource(); 
			final ChargingResource chargingresource_ = new ChargingResource(new Integer(chargingresourceType_.getCode()), chargingresourceType_.getName());
			chargingresource_.setDescription(chargingresourceType_.getTranslatedResourceName());
			final ResourceBalance resourceBalance_ = new ResourceBalance(chargingresource_, getBalanceResponseType_.getBalance());
			balanceResponse_.add(resourceBalance_);
		}

		return balanceResponse_.toArray(new ResourceBalance[balanceResponse_.size()]);

	}

	/**
	 * this method is untested
	 */
	@Override
	public ParentTransaction getParentTransaction(String clientId, String msisdn,
			TransactionFilter filter) throws EcommerceException {
		checkNullParams(filter, filter.getParentTransactionId());
		Transaction[] array = getTransactions(clientId, msisdn, 0, filter);
		return new ParentTransaction(Arrays.asList(array));
	}


	/**
	 * JIRA ET196 Get account subscription promo-codes info
	 * @param msisdn
	 * @param clientId
	 * @return List<SubscriptionPromoCode>
	 * @throws EcommerceException
	 */
	@Override
	public List<SubscriptionPromoCode> getSubscriptionPromoCodes(String msisdn,
			String clientId) throws EcommerceException {

		List<SubscriptionPromoCode> subscriptionPromoCodes = null;

		checkNullParams(msisdn, clientId);

		final GetAccountSubscriptionPromoCodes request = createRequest(PayloadConstants.GET_ACCOUNT_SUBSCRIPTION_PROMO_CODES_REQUEST);
		request.setMsisdn(msisdn);
		request.setClientId(clientId);

		final GetAccountSubscriptionPromoCodesResponse getAccSubPromoCodesType_ =sendRequestAndGetResponse(PayloadConstants.GET_ACCOUNT_SUBSCRIPTION_PROMO_CODES_REQUEST, request, GetAccountSubscriptionPromoCodesResponse.class);

		if (getAccSubPromoCodesType_.getSubscriptionPromoCode() != null && getAccSubPromoCodesType_.getSubscriptionPromoCode().size() >0) {
			subscriptionPromoCodes = new ArrayList<>();
			for (SubscriptionPromoCodeType subPromoCodeType : getAccSubPromoCodesType_.getSubscriptionPromoCode() ) {
				SubscriptionPromoCode subPromoCode = new SubscriptionPromoCode(new Long(subPromoCodeType.getSubscriptionId()).longValue(), 
						subPromoCodeType.getPromoCode(), subPromoCodeType.getPackageId(), subPromoCodeType.getTimeStamp().getTime());
				subscriptionPromoCodes.add(subPromoCode);
			}
		}

		return subscriptionPromoCodes;
	}


	/**
	 * Jira ET245 implement get subscriptions in decoupling version 2
	 * 
	 * @param msisdn
	 * @param filter
	 * @param locale
	 * @return
	 * @throws SubsManagementException
	 */
	@Override
	public List<Subscription> getSubscriptions(String msisdn,
			SubscriptionFilter filter, Locale locale)
					throws SubsManagementException {

		checkNullParams(msisdn);
		final SelfcareFullSubscriptions request_ = createRequest(PayloadConstants.SELFCARE_FULL_SUBSCRIPTIONS_REQUEST_PAYLOAD);

		final SubscriptionFilterType subFilType = new SubscriptionFilterType();
		request_.setMsisdn(msisdn);

		if(filter != null)
		{
			subFilType.setChargingMethod(filter.getChargingMethod());
			subFilType.setClientId(filter.getClientId());
			subFilType.setSubscriptionId(filter.getSubscriptionId());

			if(filter.getStartDate() != null && filter.getStartDate().getTime() > 0 && filter.getEndDate() != null)
			{
				final Calendar c_ = Calendar.getInstance();
				c_.setTime(filter.getStartDate());
				subFilType.setStartDate(c_);
				final Calendar c1_ = Calendar.getInstance();
				c1_.setTime(filter.getEndDate());
				subFilType.setEndDate(c1_);
			}

			if(filter.getMaxEvents() > 0)
				subFilType.setMaxEvents(filter.getMaxEvents());

			subFilType.setPackageClass((filter.getPackageClass() != null?filter.getPackageClass():null));

			// MQC 9655 
			if (filter.getParentPackageId() != null && !filter.getParentPackageId().isEmpty()) {
				subFilType.setParentPackageId(filter.getParentPackageId());
			}

			if(filter.getSubscriptionEndDateStartValue() != null && filter.getSubscriptionEndDateEndValue() != null)
			{
				final Calendar c1_ = Calendar.getInstance();
				c1_.setTime(filter.getSubscriptionEndDateStartValue());
				subFilType.setSubscriptionEndDateStartValue(c1_);
				final Calendar c_ = Calendar.getInstance();
				c_.setTime(filter.getSubscriptionEndDateEndValue());
				subFilType.setSubscriptionEndDateEndValue(c_);
			}
			subFilType.setIsUsingLocalDate(filter.isUsingLocalDates());
			subFilType.setSubscriptionStatus(filter.getSubscriptionStatus());


			subFilType.setTransactionsNotRequired((filter.getTransactionsNotRequired() != null?filter.getTransactionsNotRequired():null));

			subFilType.setUseMaxEventsForTrans(filter.isUseMaxEventsForTransactions()); // MQC9072

			subFilType.setTariff(filter.getTariff());

			List<String> includeTxns = new ArrayList<>();

			if(filter.includePaymentTxns())
				includeTxns.add("payment");

			if(filter.includeModifyTxns())
				includeTxns.add("modify");

			if(filter.includeRefundTxns())
				includeTxns.add("refund");

			subFilType.setIncludeTxns(Utils.StringifyList(includeTxns, ","));

			request_.setSubscriptionFilter(subFilType);
		}

		SelfcareFullSubscriptionsV2 subscriptionsType_;
		try {
			subscriptionsType_ = sendRequestAndGetResponse(PayloadConstants.SELFCARE_FULL_SUBSCRIPTIONS_REQUEST_PAYLOAD, request_, com.vodafone.global.er.decoupling.binding.response.v2.SelfcareFullSubscriptionsV2.class, 2);
		} catch (EcommerceException e) {
			throw new SubsManagementException(e);
		}
		return converter.buildSubscriptions(subscriptionsType_.getSubscription());

	}

	@Override
	public List<ServiceOffer> getServiceOffers(String msisdn, String serviceIds)
			throws EcommerceException {
		checkNullParams(msisdn);
		checkNullParams(serviceIds);
		final GetServiceOffers request_ = createRequest(PayloadConstants.GET_SERVICE_OFFERS_REQUEST);
		request_.setMsisdn(msisdn);
		request_.setServiceIds(serviceIds);
		
		GetServiceOffersResponse getServiceOffersType_;
		getServiceOffersType_ = sendRequestAndGetResponse(PayloadConstants.GET_SERVICE_OFFERS_REQUEST, request_, com.vodafone.global.er.decoupling.binding.response.GetServiceOffersResponse.class);
		
		return converter.buildServiceOffers(getServiceOffersType_.getService());
		
	}




}
