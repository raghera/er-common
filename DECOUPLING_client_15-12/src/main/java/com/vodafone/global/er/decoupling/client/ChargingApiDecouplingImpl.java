package com.vodafone.global.er.decoupling.client;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.business.charging.ChargingApi;
import com.vizzavi.ecommerce.business.charging.PromoCodeAttributes;
import com.vizzavi.ecommerce.business.charging.PromoCodeValidation;
import com.vizzavi.ecommerce.business.charging.UsageAttributes;
import com.vizzavi.ecommerce.business.charging.UsageAuthorization;
import com.vizzavi.ecommerce.business.charging.UsageAuthorizationException;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vodafone.global.er.business.charging.UsageCompleteAttributes;
import com.vodafone.global.er.decoupling.PayloadConstants;
import com.vodafone.global.er.decoupling.binding.request.RatingAttributesType;
import com.vodafone.global.er.decoupling.binding.request.UsageAttributesType;
import com.vodafone.global.er.decoupling.binding.request.UsageAuthRateChargeRequestType;
import com.vodafone.global.er.decoupling.binding.request.UsageAuthRateRequestType;
import com.vodafone.global.er.decoupling.binding.request.UsageComplete;
import com.vodafone.global.er.decoupling.binding.response.PurchaseOptions;
import com.vodafone.global.er.decoupling.binding.response.UsageAuthorisation;


/**
 * a <b>partial</b> implementation of the ChargingApi via decoupling
 * @author matt
 *
 */
public class ChargingApiDecouplingImpl  extends BaseErApiDecouplingImpl implements ChargingApi {

	private static final Logger logger = LoggerFactory.getLogger(ChargingApiDecouplingImpl.class);

	/**
	 * create a new charging api impl
	 * @param locale
	 * @param clientId
	 */
	public ChargingApiDecouplingImpl(Locale locale, String clientId) {
		super(locale, clientId);
	}

	
	/**
	 * {@inheritDoc}<br/>
	 * <b>NB there is no usage Auth in decoupling!</b>
	 */
	@Override
	public UsageAuthorization usageAuth(String clientApplicationId, String msisdn,
			String serviceId, UsageAttributes usageAttributes) throws UsageAuthorizationException {
		//TODO there is no usage Auth in decoupling!
		try {
			return usageAuthRate(clientApplicationId, msisdn, serviceId, usageAttributes);
		} catch (EcommerceException e) {
			throw new UsageAuthorizationException(e);
		}
	}

	@Override
	public UsageAuthorization usageAuthRate(String clientApplicationId, String msisdn,
			String serviceId, UsageAttributes usageAttributes) throws EcommerceException {

		checkNullParams(msisdn, serviceId);
		UsageAuthRateRequestType uar = createRequest(PayloadConstants.USAGE_AUTH_RATE_REQUEST_PAYLOAD);
		uar.setMsisdn(msisdn);
		uar.setServiceId(serviceId);
		//Usage Attributes
		final com.vodafone.global.er.decoupling.binding.request.UsageAttributesType usAttrType = new UsageAttributesType(); 
		usAttrType.setForcePurchase(usageAttributes.getForcePurchaseFlag());
		uar.setUsageAttributes(usAttrType);

		//Rating Attributes
		final RatingAttributesType ratingAtt = converter.buildRatingAttributes(usageAttributes);
		
		uar.setRatingAttributes(ratingAtt);
		
		Object responsePayload = null;
		try {
//			ErRequest erRequest = new DecouplingMessageFactory().buildEnvelope(PayloadConstants.USAGE_AUTH_RATE_REQUEST_PAYLOAD, uar, clientApplicationId);
//			responsePayload = new DecouplingClient(locale).getPayload(erRequest, getHeaders(erRequest)); // MQC 9487 - set headers
			responsePayload = sendRequestAndGetResponse(PayloadConstants.USAGE_AUTH_RATE_REQUEST_PAYLOAD, uar, Object.class, clientApplicationId);
		} catch (final EcommerceException e) {
			logger.info("problem talking to er? {}", e.getMessage());
			throw new UsageAuthorizationException(e);
		}
		
		if (responsePayload instanceof UsageAuthorisation)	{
			UsageAuthorisation jaxbAuth = getResultFromPayload(responsePayload, UsageAuthorisation.class);
			return converter.buildUsageAuthObj(jaxbAuth);
		}	else {	// if (responsePayload instanceof PurchaseOptions)	{
			PurchaseOptions jaxbOptions = getResultFromPayload(responsePayload, PurchaseOptions.class);
			return converter.buildUsageAuthObj(jaxbOptions, false);
		}
	}

	/**
	 * {@inheritDoc}
	 * @exception UsageAuthorizationException
	 *                   if the serviceId is not recognised or has been disabled
	 *                   if the charging subsystem is not operational
	 */
	@Override
	public UsageAuthorization usageAuthRateCharge(String clientApplicationId, String msisdn,
			String serviceId, UsageAttributes usageAttributes) throws UsageAuthorizationException {

		if (msisdn == null || serviceId == null)
			throw new RuntimeException("msisdn and serviceId can't be null");


		final UsageAuthRateChargeRequestType request = createRequest(PayloadConstants.USAGE_AUTH_RATE_CHARGE_REQUEST_PAYLOAD);

		request.setMsisdn(msisdn);
		request.setServiceId(serviceId);

		//Usage Attributes
		final UsageAttributesType usAttrType = new UsageAttributesType();
		if (usageAttributes.getForcePurchaseFlag())
			usAttrType.setForcePurchase(true);
		if(usageAttributes.isReIssueFlagPresent()) 
			usAttrType.setEnableReIssue(usageAttributes.isReIssueEnabled());
		if (usageAttributes.getReserveOnlyFlag()==1)
			usAttrType.setForceReservation(true);
		//Rating Attributes
		final RatingAttributesType ratingAtt = converter.buildRatingAttributes(usageAttributes);
		
		request.setUsageAttributes(usAttrType);
		request.setRatingAttributes(ratingAtt);

		Object responsePayload = null;
		try {
//			ErRequest erRequest = new DecouplingMessageFactory().buildEnvelope(PayloadConstants.USAGE_AUTH_RATE_CHARGE_REQUEST_PAYLOAD, request, clientApplicationId);
//			responsePayload = new DecouplingClient(locale).getPayload(erRequest);
			responsePayload = sendRequestAndGetResponse(PayloadConstants.USAGE_AUTH_RATE_CHARGE_REQUEST_PAYLOAD, request, Object.class, clientApplicationId);
			if (responsePayload instanceof UsageAuthorisation)	{
				UsageAuthorisation jaxbAuth = getResultFromPayload(responsePayload, UsageAuthorisation.class);
				return converter.buildUsageAuthObj(jaxbAuth);
			}	else {	// if (responsePayload instanceof PurchaseOptions)	{
				PurchaseOptions jaxbOptions = getResultFromPayload(responsePayload, PurchaseOptions.class);
				return converter.buildUsageAuthObj(jaxbOptions, false);
			}
		} catch (final EcommerceException e) {
			logger.info("problem talking to er? {}", e.getMessage());
			throw new UsageAuthorizationException(e);
		}
	}


	@Override
	public UsageAuthorization usageComplete(String clientApplicationId, String eventReservationId,
			int deliveryStatus) throws UsageAuthorizationException {
		checkNullParams(eventReservationId);
		UsageComplete request = createRequest(PayloadConstants.USAGE_COMPLETE_REQUEST_PAYLOAD);
		request.setReservationId(eventReservationId);
		UsageComplete.UsageCompleteAttributes attrs = new UsageComplete.UsageCompleteAttributes();
		attrs.setDeliveryStatus(deliveryStatus);
		request.setUsageCompleteAttributes(attrs);
		try	{
			UsageAuthorisation result = sendRequestAndGetResponse(PayloadConstants.USAGE_COMPLETE_REQUEST_PAYLOAD, request, UsageAuthorisation.class, clientApplicationId);
			UsageAuthorization businessObject = converter.buildUsageAuthObj(result);
			return businessObject;
		}	catch(Exception e)	{
			throw new UsageAuthorizationException(e);
		}
	}

	@Override
	public UsageAuthorization usageComplete(String clientId, String reservationId,
			UsageCompleteAttributes att) throws UsageAuthorizationException {
		return usageComplete(clientId, reservationId, att.getDeliveryStatus());
	}


	@Override
	public CatalogService rateService(String clientApplicationId, String serviceId,
			UsageAttributes usageAttributes) throws UsageAuthorizationException {
		//TODO write this method
		throw new RuntimeException("rateService not supported in this version: "+version+" dated "+date);
	}


	@Override
	public PromoCodeValidation validatePromoCode(PromoCodeAttributes promoCodeAttrs)
			throws EcommerceException {
		//TODO write this method
		throw new RuntimeException("validatePromoCode not supported in this version: "+version+" dated "+date);
	}

}
