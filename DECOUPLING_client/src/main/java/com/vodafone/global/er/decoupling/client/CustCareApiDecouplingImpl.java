package com.vodafone.global.er.decoupling.client;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.Element;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.charging.AccountValidationAuthorization;
import com.vizzavi.ecommerce.business.charging.ModifyAuthorisation;
import com.vizzavi.ecommerce.business.charging.RefundAttributes;
import com.vizzavi.ecommerce.business.charging.RefundAuthorization;
import com.vizzavi.ecommerce.business.charging.SubscriptionAttributes;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.selfcare.BasicAccount;
import com.vizzavi.ecommerce.business.selfcare.CustcareApi;
import com.vizzavi.ecommerce.business.selfcare.CustcareAttributes;
import com.vizzavi.ecommerce.business.selfcare.ModifySubscriptionAuthorization;
import com.vizzavi.ecommerce.business.selfcare.SpendLimits;
import com.vizzavi.ecommerce.business.selfcare.Transaction;
import com.vizzavi.ecommerce.business.selfcare.ValidateMsisdnAttributes;
import com.vodafone.global.er.decoupling.PayloadConstants;
import com.vodafone.global.er.decoupling.binding.request.*;
import com.vodafone.global.er.decoupling.binding.request.RefundAttributesType;
import com.vodafone.global.er.decoupling.binding.request.SimpleChargingResourceType;
import com.vodafone.global.er.decoupling.binding.request.SubscriptionAttributesType;
import com.vodafone.global.er.decoupling.binding.request.impl.InactivateSubscriptionImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.ModifyBanImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.ModifyMsisdnImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.ModifySpendLimitsRequestImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.ModifyUsergroupImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.SimpleChargingResourceTypeImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.UsergroupsImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.ValidateMsisdnRequestImpl;
import com.vodafone.global.er.decoupling.binding.response.*;
import com.vodafone.global.er.decoupling.binding.response.ReasonCodeType;
import com.vodafone.global.er.decoupling.binding.response.RefundAuthorisationFullType;


/**
 * 
 * @author matt
 *
 */
class CustCareApiDecouplingImpl extends SelfcareApiDecouplingImpl implements CustcareApi {


	private static final Logger log = LoggerFactory.getLogger(CustCareApiDecouplingImpl.class);


	private static final String date= "$Date: 2013/10/31 12:41:46 $";
	private static final String version="$Revision: 1.23 $";

	public CustCareApiDecouplingImpl(Locale locale, String clientId)	{
		super(locale, clientId);
	}


	@Override
	public BasicAccount getBasicAccount(String clientId, String msisdn, int accessDevice) throws EcommerceException
	{
		checkNullParams(msisdn);
		
		final GetFullAccount request = createRequest(PayloadConstants.GET_FULL_ACCOUNT_PAYLOAD);
		request.setMsisdn(msisdn);
		request.setAccessDevice(accessDevice);

		final FullAccountType jaxbAccount = sendRequestAndGetResponse(PayloadConstants.GET_FULL_ACCOUNT_PAYLOAD, request, FullAccountType.class, clientId);
		return converter.buildAccount(jaxbAccount);
		
	}

	@Override
	public boolean inactivateSubscription(String clientId, String msisdn, int device, CustcareAttributes custcareAttributes) throws EcommerceException
	{
		checkNullParams(msisdn, custcareAttributes, custcareAttributes.getPackageSubId());

		final InactivateSubscriptionImpl request_ = createRequest(PayloadConstants.INACTIVATE_SUBSCRIPTION_REQUEST_PAYLOAD);

		request_.setMsisdn(msisdn);
		request_.setSubscriptionId(custcareAttributes.getPackageSubId());
		request_.setReason(custcareAttributes.getReason());
		request_.setCsrId(custcareAttributes.getCsrId());
		final InactivateSubscriptionResponse inactivateSubscription_ = //getResultFromPayload(payload_, InactivateSubscriptionResponse.class);
				sendRequestAndGetResponse(PayloadConstants.INACTIVATE_SUBSCRIPTION_REQUEST_PAYLOAD, request_, InactivateSubscriptionResponse.class);

		return inactivateSubscription_.isSuccess();

	}


	@Override
	public boolean inactivateSubscription(String clientId, String msisdn, String subscriptionId, String csrId, String reason) throws EcommerceException
	{

		final CustcareAttributes custcareAttributes_ = new CustcareAttributes();
		custcareAttributes_.setPackageSubId(subscriptionId);
		custcareAttributes_.setCsrId(csrId);
		custcareAttributes_.setReason(reason);
		return this.inactivateSubscription("", msisdn, 0, custcareAttributes_);
	
	}

	@Override
	public RefundAuthorization refundTransactionCredit(RefundAttributes refundAttributes) throws EcommerceException
	{
		checkNullParams(refundAttributes, refundAttributes.getMsisdn(), refundAttributes.getPaymentTransactionId()/*, refundAttributes.getChargingResource()*/);
		if(StringUtils.isBlank(refundAttributes.getCsrId()))
			throw new EcommerceException("CSR-id is Null or empty");
		if(StringUtils.isBlank(refundAttributes.getReason() ))
			throw new EcommerceException("Reason is Null  or empty");
		
		final CustcareFullRefundCreditRequest request_ = createRequest(PayloadConstants.CUSTCARE_FULL_REFUND_CREDIT_REQUEST_PAYLOAD);

		request_.setMsisdn(refundAttributes.getMsisdn());

		final RefundAttributesType refundAttributesType_ = new com.vodafone.global.er.decoupling.binding.request.impl.RefundAttributesTypeImpl();
		ChargingResource chargingResource_ = refundAttributes.getChargingResource();

		final SimpleChargingResourceType chargingResourceType_ = new SimpleChargingResourceTypeImpl();
		
		if (chargingResource_ != null)		{
			chargingResourceType_.setCode(chargingResource_.getCode());
			chargingResourceType_.setName(chargingResource_.getResourceName());
			refundAttributesType_.setChargingResource(chargingResourceType_);
		}
		refundAttributesType_.setCsrId(refundAttributes.getCsrId());
		refundAttributesType_.setDeprovFlag(refundAttributes.getDeprovisionFlag());
		refundAttributesType_.setDescription(refundAttributes.getDescription());
		refundAttributesType_.setInvoiceText(refundAttributes.getInvoiceText());
		refundAttributesType_.setMerchantName(refundAttributes.getMerchantName());
		refundAttributesType_.setParentTransactionId(refundAttributes.getParentTransactionId());
		//Missing in RefundAttributes
		//refundAttributesType_.setPartnerId("");
		if(refundAttributes.getPaymentTransactionId() != null && refundAttributes.getPaymentTransactionId().length()>0)
			refundAttributesType_.setPaymentTransactionId(refundAttributes.getPaymentTransactionId());
		refundAttributesType_.setReason(refundAttributes.getReason());
		refundAttributesType_.setRefundCreditAmount(refundAttributes.getRefundCreditAmount());
		refundAttributesType_.setRefundRoamingCharge(refundAttributes.needRefundRoamingCharge());
		refundAttributesType_.setSubscriptionId(refundAttributes.getSubscriptionId());
		request_.setRefundAttributes(refundAttributesType_);


		//MQC 6626 - credit refunds not working with decoupling
		final CustcareFullRefundCredit cfrc_ =// (CustcareFullRefundCredit)payload_;
				sendRequestAndGetResponse(PayloadConstants.CUSTCARE_FULL_REFUND_CREDIT_REQUEST_PAYLOAD, request_, CustcareFullRefundCredit.class);

		final RefundAuthorisationFullType refundAuthorizationType_ = cfrc_.getRefundAuthorisation();

		if (refundAuthorizationType_  == null)
		{
			log.error("Payload is Null");
			throw new EcommerceException("Payload is Null");
		}
		final ReasonCodeType reasonCodeType_ = refundAuthorizationType_.getReasonCode();
		final ReasonCode reasonCode_ = converter.convertReasonCode(reasonCodeType_);
		RefundAuthorization refundAuthorization_ = null;

		//MQC 6626 - enlargement date not relevant for credit refunds
		//if(refundAuthorizationType_.getRefundTransactionId() != null || refundAuthorizationType_.getRefundEnlargementDate() != null)
		if(refundAuthorizationType_.getRefundTransactionId() != null )
		{
			try
			{
				final Transaction transaction_ = new Transaction();
				if(refundAuthorizationType_.getRefundTransactionId() != null)
				{
					transaction_.setTransactionId(refundAuthorizationType_.getRefundTransactionId());
				}
				//MQC 6626 - enlargement date not relevant for credit refunds
				//MQC 6626 - set the transaction purchase currency
				if (chargingResource_ != null) {
					transaction_.setPurchaseCurrency(chargingResource_);
				}

				final long transactionIdAsLong_ = new Long(refundAuthorizationType_.getTransactionId());
				refundAuthorization_ = new RefundAuthorization(transactionIdAsLong_,
						transaction_,
						null,
						reasonCode_,
						refundAuthorizationType_.getErrorId(),
						refundAuthorizationType_.getErrorDescription());
				refundAuthorization_.setIsSuccess(refundAuthorizationType_.isIsSuccess());
				//MQC 6626 - set the net rate in the authorization, to the amount being refunded
				refundAuthorization_.setNetRate(refundAttributes.getRefundCreditAmount());
			}
			catch(final Exception e)
			{
				log.error("Problem setting RefundAuthorization:" + e);
			}
		}		else		{
			refundAuthorization_ = new RefundAuthorization(reasonCode_);
			refundAuthorization_.setTransactionId(refundAuthorizationType_.getTransactionId());
			refundAuthorization_.setErrorId(refundAuthorizationType_.getErrorId());
			refundAuthorization_.setErrorDescription(refundAuthorizationType_.getErrorDescription());
			refundAuthorization_.setIsSuccess(refundAuthorizationType_.isIsSuccess());
		}

		return refundAuthorization_;
	}

	@Override
	public RefundAuthorization refundTransactionDiscount(String clientId, String msisdn, String transactionId, double discount, RefundAttributes refundAttributes) throws EcommerceException
	{
		checkNullParams(msisdn, transactionId, refundAttributes, discount);

		final CustcareFullRefundDiscountRequest request_ = createRequest(PayloadConstants.CUSTCARE_FULL_REFUND_DISCOUNT_REQUEST_PAYLOAD);

		request_.setMsisdn(msisdn);
		request_.setTransactionId(transactionId);
		request_.setDiscount(discount);

		final RefundAttributesType refundAttributesType_ = new com.vodafone.global.er.decoupling.binding.request.impl.RefundAttributesTypeImpl();

		final SimpleChargingResourceType chargingResourceType_ = new SimpleChargingResourceTypeImpl();
		final ChargingResource chargingResource_ = refundAttributes.getChargingResource();
		if(chargingResource_ != null)
		{
			chargingResourceType_.setCode(chargingResource_.getCode());
			chargingResourceType_.setName(chargingResource_.getName());
			refundAttributesType_.setChargingResource(chargingResourceType_);
		}
		refundAttributesType_.setCsrId(refundAttributes.getCsrId());
		refundAttributesType_.setDeprovFlag(refundAttributes.getDeprovisionFlag());
		refundAttributesType_.setDescription(refundAttributes.getDescription());
		refundAttributesType_.setInvoiceText(refundAttributes.getInvoiceText());
		refundAttributesType_.setMerchantName(refundAttributes.getMerchantName());
		refundAttributesType_.setParentTransactionId(refundAttributes.getParentTransactionId());
		//Missing in RefundAttributes
		//refundAttributesType_.setPartnerId("");
		if(refundAttributes.getPaymentTransactionId() != null && refundAttributes.getPaymentTransactionId().length()>0)
			refundAttributesType_.setPaymentTransactionId(refundAttributes.getPaymentTransactionId());
		refundAttributesType_.setReason(refundAttributes.getReason());
		refundAttributesType_.setRefundCreditAmount(refundAttributes.getRefundCreditAmount());
		refundAttributesType_.setRefundRoamingCharge(refundAttributes.needRefundRoamingCharge());
		refundAttributesType_.setSubscriptionId(refundAttributes.getSubscriptionId());
		request_.setRefundAttributes(refundAttributesType_);
	
		final CustcareFullRefundDiscountType cfrd_=	sendRequestAndGetResponse(PayloadConstants.CUSTCARE_FULL_REFUND_DISCOUNT_REQUEST_PAYLOAD, request_, CustcareFullRefundDiscountType.class);
		final RefundAuthorisationFullType refundAuthorizationType_ = cfrd_.getRefundAuthorisation();
		final ReasonCodeType reasonCodeType_ = refundAuthorizationType_.getReasonCode();
		final ReasonCode reasonCode_ = converter.convertReasonCode(reasonCodeType_);
		RefundAuthorization refundAuthorization_ = null;

		if(refundAuthorizationType_.getRefundTransactionId() != null || refundAuthorizationType_.getRefundEnlargementDate() != null)
		{
			log.debug("refund successful: {}", refundAuthorizationType_.getRefundTransactionId());
			final Transaction transaction_ = new Transaction();
			if(refundAuthorizationType_.getRefundTransactionId() != null)
			{
				transaction_.setTransactionId(refundAuthorizationType_.getRefundTransactionId());
			}
			if(refundAuthorizationType_.getRefundEnlargementDate() != null)
			{
				transaction_.setRefundEnlargementDate(new Date(refundAuthorizationType_.getRefundEnlargementDate().getTimeInMillis()));
			}
			final long transactionIdAsLong_ = new Long(refundAuthorizationType_.getTransactionId());
			refundAuthorization_ = new RefundAuthorization(transactionIdAsLong_,
					transaction_,
					null,
					reasonCode_,
					refundAuthorizationType_.getErrorId(),
					refundAuthorizationType_.getErrorDescription());
			refundAuthorization_.setIsSuccess(refundAuthorizationType_.isIsSuccess());
		}
		else
		{
			log.debug("refund failed: {}", reasonCode_);
			refundAuthorization_ = new RefundAuthorization(reasonCode_);
			refundAuthorization_.setTransactionId(refundAuthorizationType_.getTransactionId());
			refundAuthorization_.setErrorId(refundAuthorizationType_.getErrorId());
			refundAuthorization_.setErrorDescription(refundAuthorizationType_.getErrorDescription());
			refundAuthorization_.setIsSuccess(refundAuthorizationType_.isIsSuccess());
		}
		return refundAuthorization_;

	}


	@Override
	public RefundAuthorization refundTransactionEnlargement(String clientId, String msisdn, String transactionId , long numberOfDaysToExtend , RefundAttributes refundAttributes) throws EcommerceException
	{
		checkNullParams(msisdn, transactionId, refundAttributes);
		if(StringUtils.isBlank(refundAttributes.getCsrId()))
			throw new EcommerceException("CSR-id is Null or empty");
		if(StringUtils.isBlank(refundAttributes.getReason() ))
			throw new EcommerceException("Reason is Null  or empty");
		
	//	Object payload_ = null;

		final CustcareFullRefundEnlargementRequest request = createRequest(PayloadConstants.CUSTCARE_FULL_REFUND_ENLARGEMENT_REQUEST_PAYLOAD);

		request.setMsisdn(msisdn);
		request.setTransactionId(transactionId);

		//NOTE the data type in the type is int and in this method it's a long
//		try
//		{
			request.setExtensiondays((int)numberOfDaysToExtend);
//		}
//		catch(final ClassCastException cce)
//		{
//			throw new EcommerceException("number of days to extend "+numberOfDaysToExtend+" too big"); 
//		}

		final RefundAttributesType refundAttributesType_ = new com.vodafone.global.er.decoupling.binding.request.impl.RefundAttributesTypeImpl();

		final SimpleChargingResourceType chargingResourceType_ = new SimpleChargingResourceTypeImpl();
		final ChargingResource chargingResource_ = refundAttributes.getChargingResource();
		if(chargingResource_ != null)
		{
			chargingResourceType_.setCode(chargingResource_.getCode());
			chargingResourceType_.setName(chargingResource_.getName());
			refundAttributesType_.setChargingResource(chargingResourceType_);
		}
		refundAttributesType_.setCsrId(refundAttributes.getCsrId());
		refundAttributesType_.setDeprovFlag(refundAttributes.getDeprovisionFlag());
		refundAttributesType_.setDescription(refundAttributes.getDescription());
		refundAttributesType_.setInvoiceText(refundAttributes.getInvoiceText());
		refundAttributesType_.setMerchantName(refundAttributes.getMerchantName());
		refundAttributesType_.setParentTransactionId(refundAttributes.getParentTransactionId());
		//Missing in RefundAttributes
		//refundAttributesType_.setPartnerId("");
		if(refundAttributes.getPaymentTransactionId() != null && refundAttributes.getPaymentTransactionId().length()>0)
			refundAttributesType_.setPaymentTransactionId(refundAttributes.getPaymentTransactionId());
		refundAttributesType_.setReason(refundAttributes.getReason());
		refundAttributesType_.setRefundCreditAmount(refundAttributes.getRefundCreditAmount());
		refundAttributesType_.setRefundRoamingCharge(refundAttributes.needRefundRoamingCharge());
		refundAttributesType_.setSubscriptionId(refundAttributes.getSubscriptionId());
		request.setRefundAttributes(refundAttributesType_);


//		final Element element_ = _factory_.buildEnvelope(PayloadConstants.CUSTCARE_FULL_REFUND_ENLARGEMENT_REQUEST_PAYLOAD, request_, null);
//		payload_ = _client.getPayload(element_);
//		final CustcareFullRefundEnlargement cfre_ = //(CustcareFullRefundEnlargement)payload_;
//				getResultFromPayload(payload_, CustcareFullRefundEnlargement.class);
		final CustcareFullRefundEnlargement cfre_ = sendRequestAndGetResponse(PayloadConstants.CUSTCARE_FULL_REFUND_ENLARGEMENT_REQUEST_PAYLOAD, request, CustcareFullRefundEnlargement.class);

		final RefundAuthorisationFullType refundAuthorizationType_ = cfre_.getRefundAuthorisation();

		final ReasonCodeType reasonCodeType_ = refundAuthorizationType_.getReasonCode();
		final ReasonCode reasonCode_ = converter.convertReasonCode(reasonCodeType_);
		RefundAuthorization refundAuthorization_ = null;

		if(refundAuthorizationType_.getRefundTransactionId() != null || refundAuthorizationType_.getRefundEnlargementDate() != null)
		{
//			try
//			{
			final Transaction transaction_ = new Transaction();
			if(refundAuthorizationType_.getRefundTransactionId() != null)
			{
				transaction_.setTransactionId(refundAuthorizationType_.getRefundTransactionId());
			}
			if(refundAuthorizationType_.getRefundEnlargementDate() != null)
			{
				transaction_.setRefundEnlargementDate(new Date(refundAuthorizationType_.getRefundEnlargementDate().getTimeInMillis()));
			}
			final long transactionIdAsLong_ = new Long(refundAuthorizationType_.getTransactionId());
			refundAuthorization_ = new RefundAuthorization(transactionIdAsLong_,
					transaction_,
					null,
					reasonCode_,
					refundAuthorizationType_.getErrorId(),
					refundAuthorizationType_.getErrorDescription());
			refundAuthorization_.setIsSuccess(refundAuthorizationType_.isIsSuccess());
//			}
//			catch(final Exception e)
//			{
//				log.error("Problem setting RefundAuthorization:" + e);
//			}
		}	else  {
			refundAuthorization_ = new RefundAuthorization(reasonCode_);
			refundAuthorization_.setTransactionId(refundAuthorizationType_.getTransactionId());
			refundAuthorization_.setErrorId(refundAuthorizationType_.getErrorId());
			refundAuthorization_.setErrorDescription(refundAuthorizationType_.getErrorDescription());
			refundAuthorization_.setIsSuccess(refundAuthorizationType_.isIsSuccess());
		}

		return refundAuthorization_;

	}

	@Override
	public RefundAuthorization refundTransactionMonetary(String clientId, String msisdn, String transactionId, double amount, ChargingResource chargingResource, RefundAttributes refundAttributes) throws EcommerceException
	{
		checkNullParams(msisdn, transactionId);

		final CustcareFullRefundMonetaryRequest request_ = createRequest(PayloadConstants.CUSTCARE_FULL_REFUND_MONETARY_REQUEST_PAYLOAD);

		request_.setMsisdn(msisdn);
		request_.setTransactionId(transactionId);
		//business logic in decoupling api layer - not great but can't see any other way of doing it
		if (amount == 0.0)	{
			final RefundAuthorization refundAuth =  new RefundAuthorization(ReasonCode.MONETARY_REFUND_INVALID_AMOUNT);
			refundAuth.setIsSuccess(false);
			return refundAuth;
			//throw new EcommerceException("invalid refund amount 0.0");
		} else if (amount >0)
			request_.setAmount(amount);
		//else we don't set the amount in the xml, to simulate this valid use case where user wants full refund without specifying amount

		if(chargingResource != null)
		{
			final SimpleChargingResourceType mainChargingResourceType_ = new SimpleChargingResourceTypeImpl();

			if(chargingResource.getName() != null)
				mainChargingResourceType_.setName(chargingResource.getName());
			else
				mainChargingResourceType_.setName("");

			mainChargingResourceType_.setCode(chargingResource.getCode());
			request_.setChargingResource(mainChargingResourceType_);

		}	//else
		//throw new EcommerceException("charging resource is null");

		if(refundAttributes != null)
		{

			final RefundAttributesType refundAttributesType_ = new com.vodafone.global.er.decoupling.binding.request.impl.RefundAttributesTypeImpl();

			final SimpleChargingResourceType chargingResourceType_ = new SimpleChargingResourceTypeImpl();
			final ChargingResource chargingResource_ = refundAttributes.getChargingResource();
			if(chargingResource_ != null)
			{
				chargingResourceType_.setCode(chargingResource_.getCode());
				chargingResourceType_.setName((chargingResource_.getName() != null?chargingResource_.getName():""));
				refundAttributesType_.setChargingResource((chargingResourceType_ != null?chargingResourceType_:null));
			}
			refundAttributesType_.setCsrId(refundAttributes.getCsrId());
			refundAttributesType_.setDeprovFlag(refundAttributes.getDeprovisionFlag());
			refundAttributesType_.setDescription((refundAttributes.getDescription() != null?refundAttributes.getDescription():""));
			refundAttributesType_.setInvoiceText((refundAttributes.getInvoiceText() != null?refundAttributes.getInvoiceText():""));
			refundAttributesType_.setMerchantName((refundAttributes.getMerchantName() != null?refundAttributes.getMerchantName():""));
			refundAttributesType_.setParentTransactionId((refundAttributes.getParentTransactionId() != null?refundAttributes.getParentTransactionId():""));
			//CR2273 - also set partner id
			refundAttributesType_.setPartnerId((refundAttributes.getPartnerId() != null?refundAttributes.getPartnerId():""));
			refundAttributesType_.setPaymentTransactionId((refundAttributes.getPaymentTransactionId() != null?refundAttributes.getPaymentTransactionId():""));
			refundAttributesType_.setReason((refundAttributes.getReason() != null?refundAttributes.getReason():""));
			refundAttributesType_.setRefundCreditAmount(refundAttributes.getRefundCreditAmount());
			refundAttributesType_.setRefundRoamingCharge(refundAttributes.needRefundRoamingCharge());
			refundAttributesType_.setSubscriptionId((refundAttributes.getSubscriptionId() != null?refundAttributes.getSubscriptionId():""));
			request_.setRefundAttributes(refundAttributesType_);
		}
		final CustcareFullRefundMonetary response
		= super.sendRequestAndGetResponse(PayloadConstants.CUSTCARE_FULL_REFUND_MONETARY_REQUEST_PAYLOAD, request_, CustcareFullRefundMonetary.class, clientId);//CR2241 added clientId (may be null)
		final RefundAuthorisationFullType refundAuthorizationType_ = response.getRefundAuthorisation();

		final ReasonCodeType reasonCodeType_ = refundAuthorizationType_.getReasonCode();
		final ReasonCode reasonCode_ = converter.convertReasonCode(reasonCodeType_);
		RefundAuthorization refundAuthorization_ = null;

		if(refundAuthorizationType_.getRefundTransactionId() != null || refundAuthorizationType_.getRefundEnlargementDate() != null)
		{
			try
			{
				final Transaction transaction_ = new Transaction();
				if(refundAuthorizationType_.getRefundTransactionId() != null)
				{
					transaction_.setTransactionId(refundAuthorizationType_.getRefundTransactionId());
				}
				if(refundAuthorizationType_.getRefundEnlargementDate() != null)
				{
					transaction_.setRefundEnlargementDate(new Date(refundAuthorizationType_.getRefundEnlargementDate().getTimeInMillis()));
				}
				final long transactionIdAsLong_ = new Long(refundAuthorizationType_.getTransactionId());
				refundAuthorization_ = new RefundAuthorization(transactionIdAsLong_,
						transaction_,
						null,
						reasonCode_,
						refundAuthorizationType_.getErrorId(),
						refundAuthorizationType_.getErrorDescription());
				refundAuthorization_.setIsSuccess(refundAuthorizationType_.isIsSuccess());
			}
			catch(final Exception e)
			{
				log.error("Problem setting RefundAuthorization:" + e);
			}
		}
		else
		{
			refundAuthorization_ = new RefundAuthorization(reasonCode_);
			refundAuthorization_.setTransactionId(refundAuthorizationType_.getTransactionId());
			refundAuthorization_.setErrorId(refundAuthorizationType_.getErrorId());
			refundAuthorization_.setErrorDescription(refundAuthorizationType_.getErrorDescription());
			refundAuthorization_.setIsSuccess(refundAuthorizationType_.isIsSuccess());
		}
		return refundAuthorization_;
	
	}



	/**
	 * CR2040 MPAY replacement.
	 * @param clientId
	 * @param msisdn
	 * @param limits
	 * @return
	 * @throws EcommerceException
	 */
	@Override
	public ModifyAuthorisation modifySpendLimits(String clientId, String msisdn,
			SpendLimits limits, String csrId) throws EcommerceException {
		checkNullParams(msisdn);
		final ModifyAuthorisation rv = new ModifyAuthorisation();

		final ModifySpendLimitsRequest request = createRequest(PayloadConstants.MODIFY_SPEND_LIMITS_REQUEST_ID);
		rv.setSuccess(false);
		request.setMsisdn(msisdn);

		final ModifySpendLimitsRequest.SpendLimitsType spendLimitsType = new ModifySpendLimitsRequestImpl.SpendLimitsTypeImpl();
		spendLimitsType.setPerDayLimit(limits.getPerDayLimit());
		spendLimitsType.setPerMonthLimit(limits.getPerMonthLimit());
		spendLimitsType.setPerTxLimit(limits.getPerTxLimit());

		request.setSpendLimits(spendLimitsType);

		request.setCsrId(csrId);

		final ModifySpendLimitsResponseType modifySpendLimitsResponse = sendRequestAndGetResponse(PayloadConstants.MODIFY_SPEND_LIMITS_REQUEST_ID, request, ModifySpendLimitsResponseType.class, clientId);//CR2241 added clientId (may be null)

		rv.setAccountNotfound(!modifySpendLimitsResponse.isIsSuccess());
		rv.setSuccess(modifySpendLimitsResponse.isIsSuccess());
		rv.setReasonCode(converter.convertReasonCode(modifySpendLimitsResponse.getReasonCode()));
		return rv;
	}



	//begin methods copied from custcareapidecouplingjunitstub


	/**
	 * @see com.vizzavi.ecommerce.business.selfcare.CustcareApi#inactivateAccount(String, String, String, String)
	 */
	@Override
	public boolean inactivateAccount(String clientId, String msisdn, String csrId, String reason) throws EcommerceException  {
		return inactivateAccount(clientId, msisdn, false, csrId, reason);
	}





	//Remedy 6780 and 6778, Bruno Meseguer, missing decoupling API
	@Override
	public boolean inactivateAccount(	String clientId,
			String msisdn,
			boolean validateAccount,
			String csrId,
			String reason) throws EcommerceException
			{
		InactivateAccountType method = createRequest(PayloadConstants.INACTIVATE_ACCOUNT_REQUEST_PAYLOAD);
		method.setMsisdn(msisdn);
		method.setValidateAccount(Boolean.toString(validateAccount));


		if(csrId == null)
			method.setCsrId(clientId);
		else
			method.setCsrId(csrId);

		if(reason == null)
			method.setReason(clientId);
		else
			method.setReason(reason);
		InactivateAccountResponseType result = sendRequestAndGetResponse(PayloadConstants.INACTIVATE_ACCOUNT_REQUEST_PAYLOAD, method, InactivateAccountResponseType.class);
		return result.isSuccess();
			}



	@Override
	public ModifyAuthorisation modifyUserGroup(String clientId,
			String msisdn, List<String> usergroup, String csrId, String reason, String action)
					throws EcommerceException {

		final ModifyUsergroupType method = new ModifyUsergroupImpl();
		method.setMsisdn(msisdn);
		method.setCsrId(csrId);
		method.setReason(reason);
		method.setAction(action);

		final UsergroupsImpl userGroups = new UsergroupsImpl();
		for(final String group: usergroup){
			userGroups.getUsergroup().add(group);
		}

		method.setUsergroups(userGroups);
	
		ModifyUsergroupReponseType response = sendRequestAndGetResponse(PayloadConstants.MODIFY_USERGROUP_PAYLOAD, method, ModifyUsergroupReponseType.class);
		final ModifyAuthorisation modAuth = new ModifyAuthorisation();
		modAuth.setSuccess(response.isSuccess());

		modAuth.setReasonCode(converter.convertReasonCode(response.getReasonCode()));

		return modAuth;
	}



	@Override
	public boolean modifyBan(	String clientId,
			String msisdn,
			String newBan,
			String csrId,
			String reason) throws EcommerceException
			{
		final ModifyBanType method = new ModifyBanImpl();

		checkNullParams(msisdn, newBan);

		method.setMsisdn(msisdn);
		method.setNewBan(newBan);

		if(csrId != null || reason != null)
		{
			if(csrId == null)
				method.setCsrId("");
			else
				method.setCsrId(csrId);

			if(reason == null)
				method.setReason("");
			else
				method.setReason(reason);
		}

		//Object payload = rHelper.invokeApi(method,getHeaders());

		final Element element_ = _factory_.buildEnvelope(PayloadConstants.MODIFY_BAN_REQUEST, method, null);
		Object payload = null;
		try {
			payload = _client.getPayload(element_, getHeaders(element_)); // MQC 9487
		} catch (final IOException e) {
			throw new EcommerceException(e);
		}

		ModifyBanResponseType jaxbObject = null;

		//we check the type of response is the expected one
		if(payload instanceof ModifyBanResponseType)
			jaxbObject = (ModifyBanResponseType)payload;
		else
			throw new RuntimeException("Bad response object, expecting: ModifyBanResponseTypeImpl, recieved: " + payload.getClass().getName());

		return jaxbObject.isSuccess();
			}

	@Override
	public boolean modifyMsisdn(String clientId,
			String msisdn,
			String newMsisdn,
			String csrId,
			String reason) throws EcommerceException	{
		
		final ModifyMsisdnType request = new ModifyMsisdnImpl();
		if (msisdn ==null ||newMsisdn ==null)
			throw new EcommerceException("both old and new msisdn must be non-null");
		request.setMsisdn(msisdn);
		request.setNewMsisdn(newMsisdn);

		if(csrId == null)
			request.setCsrId("");
		else
			request.setCsrId(csrId);

		if(reason == null)
			request.setReason("");
		else
			request.setReason(reason);

		final ModifyMsisdnResponseType jaxbObject = sendRequestAndGetResponse(PayloadConstants.MODIFY_MSISDN_REQUEST, request, ModifyMsisdnResponseType.class);
		return jaxbObject.isSuccess();		

	}

	@Override
	public ModifySubscriptionAuthorization modifySubscription(String clientApplicationId,
			String msisdn, String subscriptionId, SubscriptionAttributes subscriptionAttributes)
					throws EcommerceException {
		ModifySubscriptionRequestType request = createRequest(PayloadConstants.MODIFY_SUBSCRIPTION_REQUEST_PAYLOAD);
		
		SubscriptionAttributesType jaxbAttrs = converter.buildSubscriptionAttributes(subscriptionAttributes);
		
		request.setSubscriptionAttributes(jaxbAttrs);
		request.setMsisdn(msisdn);
		request.setSubscriptionId(subscriptionId);
		com.vodafone.global.er.decoupling.binding.response.ModifySubscriptionAuthorization response = 
				sendRequestAndGetResponse(PayloadConstants.MODIFY_SUBSCRIPTION_REQUEST_PAYLOAD, request, com.vodafone.global.er.decoupling.binding.response.ModifySubscriptionAuthorization.class, clientApplicationId);
		ModifySubscriptionAuthorization result = new ModifySubscriptionAuthorization();
		result.setReasonCode(converter.convertReasonCode(response.getReasonCode()));
		result.setSuccess(response.isIsSuccess());
		return result;
	}
	
	//end methods copied from custcareapidecouplingjunitstub
	//begin CustCareApi unsupported methods

	
	/**
	 * TODO make this work - need to change the xsds to add a bunch of fields to the response.<br/>
	 */
	@Override
	public AccountValidationAuthorization validateMsisdnAccount(String msisdn,
			ValidateMsisdnAttributes attrs) throws EcommerceException {
		checkNullParams(msisdn, attrs);
		ValidateMsisdnRequest request = new ValidateMsisdnRequestImpl();
		request.setMsisdn(msisdn);
		if (StringUtils.isNotBlank(attrs.getPartnerId()))
			request.setPartnerId(attrs.getPartnerId());
		if (StringUtils.isNotBlank(attrs.getServiceId()))
			request.setServiceId(attrs.getServiceId());
		if (StringUtils.isNotBlank(attrs.getVendorId()))
			request.setVendorId(attrs.getVendorId());
		ValidateMsisdnResponse response = sendRequestAndGetResponse(PayloadConstants.VALIDATE_MSISDN_REQUEST_PAYLOAD, request, ValidateMsisdnResponse.class);
		
		AccountValidationAuthorization rv = converter.buildAccountValidation(response);
		
		return rv;
	}
	
	
	
	@Override
	public void setCustCareDetails(String csrId, String reason) {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public boolean notificationSubscribe(Locale locale1, String url, String name)
			throws EcommerceException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}

	@Override
	public ModifyAuthorisation modifyTimezone(String clientId, String msisdn, String timezone,
			String csrId, String reason) throws EcommerceException, ClassNotFoundException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public ModifyAuthorisation modifyBillingCycle(String clientId, String msisdn, int billingCycle,
			String csrId, String reason) throws EcommerceException, ClassNotFoundException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public ModifyAuthorisation modifyAccountSpId(String clientId, String msisdn, String spId)
			throws EcommerceException, ClassNotFoundException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}

	@Override
	public ModifyAuthorisation modifyAccountIsPrepay(String clientId, String msisdn, String isPrepay)
			throws EcommerceException, ClassNotFoundException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public RefundAuthorization refundRollbackTransactionMonetary(String clientId, String msisdn,
			String subscriptionId, RefundAttributes attributes) throws EcommerceException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}



	@Override
	public ModifyAuthorisation modifyAccountChildSpId(String clientId, String msisdn,
			String childSpId) throws EcommerceException, ClassNotFoundException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}

	@Override
	public ModifyAuthorisation modifyAccountSpType(String clientId, String msisdn, String spType)
			throws EcommerceException, ClassNotFoundException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}
	//end CustCareApi unsupported methods



}
