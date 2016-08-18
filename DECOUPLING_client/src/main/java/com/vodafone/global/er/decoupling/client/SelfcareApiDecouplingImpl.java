package com.vodafone.global.er.decoupling.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.vizzavi.ecommerce.business.charging.BaseAuthorization;
import com.vizzavi.ecommerce.business.common.AccountNotFoundException;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.DeviceType;
import com.vizzavi.ecommerce.business.common.EcomApiFactory;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.selfcare.CustcareAttributes;
import com.vizzavi.ecommerce.business.selfcare.MicroServiceFilter;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;
import com.vizzavi.ecommerce.business.selfcare.SelfcareApi;
import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vizzavi.ecommerce.business.selfcare.SubscriptionFilter;
import com.vizzavi.ecommerce.business.selfcare.Transaction;
import com.vizzavi.ecommerce.business.selfcare.TransactionFilter;
import com.vodafone.global.er.business.selfcare.BalanceFilter;
import com.vodafone.global.er.business.selfcare.MicroServiceStatus;
import com.vodafone.global.er.business.selfcare.ParentTransaction;
import com.vodafone.global.er.decoupling.PayloadConstants;
import com.vodafone.global.er.decoupling.binding.request.GetBalancesRequest;
import com.vodafone.global.er.decoupling.binding.request.GetNextPaymentAmountRequest;
import com.vodafone.global.er.decoupling.binding.request.GetSupercreditBalancesRequest;
import com.vodafone.global.er.decoupling.binding.request.ModifySubscriptionChargingMethod;
import com.vodafone.global.er.decoupling.binding.request.SelfcareFullSubscriptions;
import com.vodafone.global.er.decoupling.binding.request.SelfcareFullSubscriptionsType;
import com.vodafone.global.er.decoupling.binding.request.SelfcareFullTransactionsType;
import com.vodafone.global.er.decoupling.binding.request.SelfcareMicroServicesRequest;
import com.vodafone.global.er.decoupling.binding.request.SelfcareMicroServicesRequestType.MicroServiceFilterType;
import com.vodafone.global.er.decoupling.binding.request.impl.BalanceFilterImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.ModifySubscriptionChargingMethodImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.SelfcareFullTransactionsTypeImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.SelfcareMicroServicesRequestTypeImpl.MicroServiceFilterTypeImpl;
import com.vodafone.global.er.decoupling.binding.response.*;
import com.vodafone.global.er.decoupling.binding.response.impl.GetBalancesResponseImpl;

class SelfcareApiDecouplingImpl extends BaseErApiDecouplingImpl implements SelfcareApi {


	private static final Logger _log = Logger.getLogger(SelfcareApiDecouplingImpl.class);


	private static final String date= "$Date: 2013/11/12 11:57:15 $";
	private static final String version="$Revision: 1.15 $";

	public SelfcareApiDecouplingImpl(Locale locale, String clientId)	{
		super(locale, clientId);
	}


	@Override
	public Subscription[] getSubscriptions(String clientId, String msisdn, int device) throws EcommerceException
	{
		return this.getSubscriptions(clientId, msisdn, device, null);
	}

	@Override
	public Subscription[] getSubscriptions(String clientId, String msisdn, int device, SubscriptionFilter filter) throws EcommerceException
	{

		checkNullParams(msisdn);
		final SelfcareFullSubscriptions request_ = createRequest(PayloadConstants.SELFCARE_FULL_SUBSCRIPTIONS_REQUEST_PAYLOAD);

		final SelfcareFullSubscriptionsType.SubscriptionFilterType subFilType = 
				new com.vodafone.global.er.decoupling.binding.request.impl.SelfcareFullSubscriptionsTypeImpl.SubscriptionFilterTypeImpl();
		request_.setDevice(device);
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

			if(filter.getPreOrder() > -1)
				subFilType.setPreOrder(filter.getPreOrder());

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
			
			request_.setSubscriptionFilter(subFilType);
		}

		final com.vodafone.global.er.decoupling.binding.response.SelfcareFullSubscriptions subscriptionsType_ = 
				sendRequestAndGetResponse(PayloadConstants.SELFCARE_FULL_SUBSCRIPTIONS_REQUEST_PAYLOAD, request_, com.vodafone.global.er.decoupling.binding.response.SelfcareFullSubscriptions.class);
		return converter.convertSubscriptionsArray(subscriptionsType_);

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
		
		final ModifySubscriptionChargingMethodResponseType modifySubscriptionChargingMethod_ = 
				sendRequestAndGetResponse(PayloadConstants.MODIFY_SUBSCRIPTION_CHARGING_METHOD_REQUEST_PAYLOAD, request_, ModifySubscriptionChargingMethodResponseType.class);
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
			final SelfcareFullTransactionsType.TransactionsFilterType transactionsFilterType_ = new SelfcareFullTransactionsTypeImpl.TransactionsFilterTypeImpl();

			final TransactionFilter transactionFilterImpl_ = transactionFilter;


			if(transactionFilterImpl_.getTransactionType() == TransactionFilter.MODIFY_TRANSACTIONS_ONLY)
				transactionsFilterType_.setIsModifyEventsOnly(true);
			else if(transactionFilterImpl_.getTransactionType() == TransactionFilter.REFUND_TRANSACTIONS_ONLY)
				transactionsFilterType_.setIsRefundEventsOnly(true);
			else if(transactionFilterImpl_.getTransactionType() == TransactionFilter.REFUND_PAYMENT_TRANSACTIONS_ONLY)
				transactionsFilterType_.setIsRefundPaymentsEventsOnly(true);
			else if(transactionFilterImpl_.getTransactionType() == TransactionFilter.ALL_TRANSACTIONS)
				transactionsFilterType_.setIsAllEvents(true);



//			if(transactionFilterImpl_.isUsingLocalDates())
//				transactionsFilterType_.setIsUsingLocalDate(true);

			if(transactionFilter.isMonetaryEventsOnly())
				transactionsFilterType_.setIsMonetryEventsOnly(true);

			if(transactionFilter.isNoZeroCostEvents())
				transactionsFilterType_.setIsNoZeroCostEventsOnly(true);

			if(transactionFilterImpl_.isActiveEventsOnly())
				transactionsFilterType_.setActiveEventsOnly(true);


			transactionsFilterType_.setPartnerId((transactionFilterImpl_.getPartnerId() != null?transactionFilterImpl_.getPartnerId():null));

			if(transactionFilterImpl_.getSubscriptionIdLong() > -1)
				transactionsFilterType_.setSubscriptionId(String.valueOf(transactionFilterImpl_.getSubscriptionIdLong()));

			//MQC 6628 - set transaction type and descending order
			transactionsFilterType_.setTransactionType((transactionFilterImpl_.getTransactionType() != null?transactionFilterImpl_.getTransactionType():null));
			transactionsFilterType_.setDescendingOrder(transactionFilterImpl_.isDescendingOrder());


			if (transactionFilter.getTransactionIdLong() > -1)
			{
				transactionsFilterType_.setTransactionId(Long.toString(transactionFilter.getTransactionIdLong()));
				transactionsFilterType_.setTransactionIdLong(transactionFilter.getTransactionIdLong());
			}        

			transactionsFilterType_.setMaxEvents(transactionFilter.getMaxEvents());

			if(transactionFilter.getEndDate() != null)
			{
				final Calendar calendar_ = Calendar.getInstance();
				calendar_.setTime(transactionFilter.getEndDate());
				if(_log.isDebugEnabled())
					_log.debug("CR1759 end date:" + calendar_.getTime());
				transactionsFilterType_.setEndDate(calendar_);
			}

			if(transactionFilter.getStartDate() != null)
			{
				final Calendar calendar_ = Calendar.getInstance();
				calendar_.setTime(transactionFilter.getStartDate());
				if(_log.isDebugEnabled())
					_log.debug("CR1759 start date:" + calendar_.getTime());
				transactionsFilterType_.setStartDate(calendar_);
			}

			request_.setTransactionsFilter(transactionsFilterType_);
		}

		final SelfcareFullTransactions selfcareTransactionsTypeImpl_ = 
				sendRequestAndGetResponse(PayloadConstants.SELFCARE_FULL_TRANSACTIONS_REQUEST_PAYLOAD, request_, SelfcareFullTransactions.class);

		try	{
			return converter.convertTransactionArray(selfcareTransactionsTypeImpl_);
		}catch(Exception e){
			throw new EcommerceException(e);
		}

	}

	@Override
	public Transaction getTransaction(String clientId , String transactionId) throws EcommerceException
	{
		checkNullParams(transactionId);

		final com.vodafone.global.er.decoupling.binding.request.SelfcareFullTransactions request = 
				createRequest(PayloadConstants.SELFCARE_FULL_TRANSACTIONS_REQUEST_PAYLOAD);

		final SelfcareFullTransactionsType.TransactionsFilterType transFilType = new SelfcareFullTransactionsTypeImpl.TransactionsFilterTypeImpl();
		transFilType.setTransactionId(transactionId);

		request.setMsisdn("0");
		request.setDevice(DeviceType.CCA);
		request.setTransactionsFilter(transFilType);

		final com.vodafone.global.er.decoupling.binding.response.SelfcareFullTransactions selfcareFullTransactionsTypeImpl_ = 
				sendRequestAndGetResponse(PayloadConstants.SELFCARE_FULL_TRANSACTIONS_REQUEST_PAYLOAD, request, com.vodafone.global.er.decoupling.binding.response.SelfcareFullTransactions.class);
	
		return converter.convertTransactionArray(selfcareFullTransactionsTypeImpl_)[0];

	}




	@Override
	public ResourceBalance[] getBalances(String clientId, String msisdn, int device)  throws EcommerceException
	{
		checkNullParams(msisdn, clientId);

		final GetBalancesRequest request = createRequest(PayloadConstants.GET_BALANCES_REQUEST_PAYLOAD);
		request.setMsisdn(msisdn);
		request.setClientId(clientId);
		request.setDeviceId(device);

		final com.vodafone.global.er.decoupling.binding.request.BalanceFilter b_ = new BalanceFilterImpl();
		b_.setOnlyAceAttribute(false);
		request.setBalaceFilter(b_);

		final GetBalancesResponseType getBalancesResponseType_ =sendRequestAndGetResponse(PayloadConstants.GET_BALANCES_REQUEST_PAYLOAD, request, GetBalancesResponseType.class);

		final GetBalancesResponse getBalancesResponse_ = new GetBalancesResponseImpl();

		final List<ResourceBalance> balanceResponse_ = getBalancesResponse_.getBalance();
		final List<ResourceBalanceType> balanceReponseTypes_ = getBalancesResponseType_.getBalance();
		for (final ResourceBalanceType getBalanceResponseType_ : balanceReponseTypes_)
		{
			final ChargingResourceType chargingresourceType_ = getBalanceResponseType_.getChargingResource(); 
			final ChargingResource chargingresource_ = new ChargingResource(new Integer(chargingresourceType_.getCode()), chargingresourceType_.getName());
			chargingresource_.setSuperCredit(chargingresourceType_.isIsSuperCredit());
			chargingresource_.setDescription(chargingresourceType_.getTranslatedResourceName());
			final ResourceBalance resourceBalance_ = new ResourceBalance(chargingresource_, getBalanceResponseType_.getBalance());
			balanceResponse_.add(resourceBalance_);
		}

		//if it's empty, don't return null, return an empty array
	//	if(!getBalancesResponse_.getBalance().isEmpty())
			return balanceResponse_.toArray(new ResourceBalance[balanceResponse_.size()]);
//		else
//			return null;
	}

	@Override
	public Subscription getSubscription(String clientId, String msisdn, int device, String packageSubId) throws EcommerceException
	{
		final SubscriptionFilter subscriptionFilter_ = EcomApiFactory.getSubscriptionFilter();
		subscriptionFilter_.setSubscriptionId(packageSubId);
		return this.getSubscriptions(clientId, msisdn, device, subscriptionFilter_)[0];
	}

	@Override
	public BaseAuthorization getNextPaymentAmount(String clientId, String msisdn, String subscriptionId) throws EcommerceException
	{
		checkNullParams(msisdn, subscriptionId);
		final GetNextPaymentAmountRequest request_ = createRequest(PayloadConstants.GET_NEXT_PAYMENT_AMOUNT_REQUEST_PAYLOAD);

		request_.setMsisdn(msisdn);
		request_.setSubscriptionId(subscriptionId);

		final GetNextPaymentAmountResponseType getNextPaymentAmountType_ = sendRequestAndGetResponse(PayloadConstants.GET_NEXT_PAYMENT_AMOUNT_REQUEST_PAYLOAD, request_, GetNextPaymentAmountResponseType.class	, clientId);

		return converter.convertNextPaymentAmountType(getNextPaymentAmountType_.getNextPaymentAmount());

	}



	@Override
	public ResourceBalance[] getSuperCreditBalances(String clientId, String msisdn, int device) throws EcommerceException
	{
		checkNullParams(msisdn, clientId);
		final GetSupercreditBalancesRequest request_ = createRequest(PayloadConstants.GET_SUPERCREDIT_BALANCES_REQUEST_PAYLOAD);

		request_.setMsisdn(msisdn);
		request_.setDevice(device);
		request_.setClientId(clientId);

		final GetSupercreditBalancesResponse getSupercreditBalancesResponseImpl_ = super.sendRequestAndGetResponse(PayloadConstants.GET_SUPERCREDIT_BALANCES_REQUEST_PAYLOAD, request_, GetSupercreditBalancesResponse.class);
		final List<ResourceBalanceType> resourceBalanceTypes_ = getSupercreditBalancesResponseImpl_.getBalance();
		final ResourceBalance[] resourceBalances_ = new ResourceBalance[resourceBalanceTypes_.size()];
		int i = 0;
		for (final ResourceBalanceType resourceBalanceType : resourceBalanceTypes_)
		{
			final ResourceBalance resourceBalance_ = new ResourceBalance();
			resourceBalance_.setSubscriptionIdLong(new Long(resourceBalanceType.getSubscriptionId()));
			resourceBalance_.setBalance(resourceBalanceType.getBalance());
			resourceBalances_[i++] = resourceBalance_; 
		}

		return resourceBalances_;

	}



	@Override
	public MicroServiceStatus[] getMicroServiceStatus(String clientId, String msisdn, MicroServiceFilter microServiceFilter) throws EcommerceException
	{
	//	Object payload_ = null;
//		try
//		{
		checkNullParams(msisdn);
			final SelfcareMicroServicesRequest request_ = createRequest(PayloadConstants.SELFCARE_MICRO_SERVICE_REQUEST_PAYLOAD);
				//	(SelfcareMicroServicesRequestImpl)object_;
			request_.setMsisdn(msisdn);
//			if(msisdn != null)
//				request_.setMsisdn(msisdn);
//			else
//				throw new EcommerceException("MSISDN is Null");

			if(microServiceFilter != null)
			{
				final MicroServiceFilterType microServiceFilterType_ = new MicroServiceFilterTypeImpl();
				microServiceFilterType_.setSubscriptionId(microServiceFilter.getSubscriptionId());
				request_.setMicroServiceFilter(microServiceFilterType_);
			}

//			final Element element_ = _factory_.buildEnvelope(PayloadConstants.SELFCARE_MICRO_SERVICE_REQUEST_PAYLOAD, request_, null);
//			payload_ = _client.getPayload(element_);

			final SelfcareMicroServicesType selfcareMicroServicesType_ = //(SelfcareMicroServicesType)payload_;
					sendRequestAndGetResponse(PayloadConstants.SELFCARE_MICRO_SERVICE_REQUEST_PAYLOAD, request_, SelfcareMicroServicesType.class);
//			if (selfcareMicroServicesType_  == null)
//			{
//				_log.error("Payload is Null");
//				throw new EcommerceException("Payload is Null");
//			}

			final List<MicroServiceType> microServiceTypes_ =  selfcareMicroServicesType_.getMicroService();
			final List<MicroServiceStatus> microServiceStatuses_ = new ArrayList<MicroServiceStatus>();
			for (final MicroServiceType microServiceType_ : microServiceTypes_)
			{
				final MicroServiceStatus microServiceStatus_ = new MicroServiceStatus();
				microServiceStatus_.setAccessDuration(microServiceType_.getAccessDuration());
				//microServiceType_.getFirstAccessTime();
				microServiceStatus_.setServiceName(microServiceType_.getName());
				microServiceStatuses_.add(microServiceStatus_);
			}

		//	if(!microServiceStatuses_.isEmpty())
				return microServiceStatuses_.toArray(new MicroServiceStatus[microServiceStatuses_.size()]);
//			else
//				return null;

//		}
//		catch(final ClassCastException ce)
//		{
//			_log.error(ce);
//			final Error errorResponse_ = (Error)payload_;
//			throw buildExceptionFromError(errorResponse_);
//		}
//		catch(final Exception e)
//		{
//			_log.error("Problem in getMicroServiceStatus:" + e);
//			throw new EcommerceException(e);
//		}
	}

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

		final ModifySubscriptionChargingMethod request = new ModifySubscriptionChargingMethodImpl();

		request.setMsisdn(msisdn);
		request.setDeviceType(deviceType);
		request.setCsrId(csrId);
		request.setChargingMethod(chargingMethod);
		request.setSubscriptionId(packageSubId);
		request.setReason(reason);

		final ModifySubscriptionChargingMethodResponseType result = sendRequestAndGetResponse(PayloadConstants.MODIFY_SUBSCRIPTION_CHARGING_METHOD_REQUEST_PAYLOAD, request, ModifySubscriptionChargingMethodResponseType.class);
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
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}

	@Override
	public ParentTransaction getParentTransaction(String clientId, String msisdn,
			TransactionFilter transactionfilter) throws EcommerceException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}




}
