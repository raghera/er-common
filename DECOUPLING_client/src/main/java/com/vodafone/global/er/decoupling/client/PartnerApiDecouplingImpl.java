package com.vodafone.global.er.decoupling.client;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.common.SubscriptionNotFoundException;
import com.vizzavi.ecommerce.business.common.TransactionNotFoundException;
import com.vizzavi.ecommerce.business.selfcare.ExternalSubscriptionTransactionStatus;
import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vizzavi.ecommerce.business.selfcare.Transaction;
import com.vodafone.global.er.decoupling.PayloadConstants;
import com.vodafone.global.er.decoupling.binding.request.GetDetailsForExternalSubscriptionRequest;
import com.vodafone.global.er.decoupling.binding.request.GetDetailsForExternalTransactionRequest;
import com.vodafone.global.er.decoupling.binding.response.GetDetailsForExternalSubscriptionResponse;
import com.vodafone.global.er.decoupling.binding.response.GetDetailsForExternalTransactionResponse;
import com.vodafone.global.er.partner.B2BPartner;
import com.vodafone.global.er.partner.PartnerApi;


//this class needs to be public for the ecomapifactory.getPartnerApi call to work

public class PartnerApiDecouplingImpl extends BaseErApiDecouplingImpl implements PartnerApi {
	
	private static final long serialVersionUID = 2899720574004858945L;
	private static final Logger logger = LoggerFactory.getLogger(PartnerApiDecouplingImpl.class);
	
	public PartnerApiDecouplingImpl(Locale locale, String clientId) {
		super(locale, clientId);
	}

	//only called by ecomapifactory
	public PartnerApiDecouplingImpl(Locale locale) {
		this(locale, "ecom wrapper");
	}
	
	@Override
	public Transaction getDetailsForExternalTransaction(String externalTransactionId,
			String partnerId) throws TransactionNotFoundException {
		//checkNullParams(externalTransactionId, partnerId);
		final GetDetailsForExternalTransactionRequest request = new GetDetailsForExternalTransactionRequest();
		request.setExternalTransId(externalTransactionId);
		request.setPartnerId(partnerId);
		GetDetailsForExternalTransactionResponse response;
		try {
			response = sendRequestAndGetResponse(PayloadConstants.GET_DETAILS_FOR_EXTERNAL_TXN_REQUEST, request, GetDetailsForExternalTransactionResponse.class);
		} catch (EcommerceException e) {
			throw new TransactionNotFoundException(e);
		}
	    
	    if (response.getReasonCode() !=null && !response.getReasonCode().equals(ReasonCode.OK))	{
	    	throw new TransactionNotFoundException(response.getReasonCode().getName());
	    }	else	{
		    final long transID = response.getTransactionId();
		    Transaction rv = new Transaction();
		    rv.setTransactionIdLong(transID);
		    if (response.getChargingResource() !=null)	{
			    final ChargingResource cr = new ChargingResource(response.getChargingResource().getCode(), response.getChargingResource().getName());
			    rv.setChargingResource(cr);
		    }
		    rv.setPurchaseRate(response.getAmount());
		    rv.setEventUnits( response.getAmount());
		    rv.setMsisdn( response.getMsisdn());
		    rv.setReason(response.getReasonCode()==null? ReasonCode.OK.getName(): response.getReasonCode().getName());
		    rv.setStatus( response.getStatus());
		    rv.setSubscriptionIdLong(response.getSubscriptionId());
		    if (response.getTimestamp() != null)
		    	rv.setPurchaseDate(response.getTimestamp().getTime());
		    return rv;
	    }

	}

	@Override
	public Subscription getDetailsForExternalSubscription(String externalSubscriptionId,
			String partnerId) throws SubscriptionNotFoundException {
		//checkNullParams(externalSubscriptionId, partnerId);
		final GetDetailsForExternalSubscriptionRequest request = new GetDetailsForExternalSubscriptionRequest();
		request.setExternalSubId(externalSubscriptionId);
		request.setPartnerId(partnerId);
		try {
			final GetDetailsForExternalSubscriptionResponse response =sendRequestAndGetResponse(PayloadConstants.GET_DETAILS_FOR_EXTERNAL_SUB_REQUEST, request, GetDetailsForExternalSubscriptionResponse.class);
			return buildSubFromResponse(response);
		} catch (EcommerceException e) {
			throw new SubscriptionNotFoundException(e);
		}
	}

	@Override
	public Subscription getDetailsForExternalSubscription(String msisdn,
			String externalSubscriptionId, String partnerId) throws SubscriptionNotFoundException {
		//checkNullParams(externalSubscriptionId, partnerId, msisdn);
		final GetDetailsForExternalSubscriptionRequest request = new GetDetailsForExternalSubscriptionRequest();
		request.setExternalSubId(externalSubscriptionId);
		request.setPartnerId(partnerId);
		request.setMsisdn(msisdn);
		try {
			final GetDetailsForExternalSubscriptionResponse response =sendRequestAndGetResponse(PayloadConstants.GET_DETAILS_FOR_EXTERNAL_SUB_REQUEST, request, GetDetailsForExternalSubscriptionResponse.class);
			return buildSubFromResponse(response);
		} catch (EcommerceException e) {
			throw new SubscriptionNotFoundException(e);
		}
	}

	@Override
	public ExternalSubscriptionTransactionStatus recordExists(String partnerId,
			String externalSubId, String externalTransId) {
		// TODO Write this method!
		return null;
	}

	
	private Subscription buildSubFromResponse(GetDetailsForExternalSubscriptionResponse response) throws SubscriptionNotFoundException	{	

		if (response.getReasonCode() !=null && !response.getReasonCode().equals(ReasonCode.OK))	{
			throw new SubscriptionNotFoundException(response.getReasonCode().getName());
		}	else	{
			//MQC8202
			final Subscription  rv = new Subscription();
			rv.setMsisdn( response.getMsisdn());
			if (response.getEndDate() != null)	{
				rv.setMLocalEndDate(response.getEndDate());
				rv.setExpiryDate(response.getEndDate().getTime());
			}
			rv.setSubscriptionIdLong(response.getErSubscriptionId());

			rv.setStatus(response.getStatus());
			rv.setPackage(new CatalogPackage(response.getPackageId(), null));
			return rv;

		}
	}
	
	@Override
	public B2BPartner getB2BPartner(String partnerId, int countryObjId) {
		// TODO Write this method if required
		return null;
	}
}
