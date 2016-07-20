 package com.vodafone.global.er.decoupling.client;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.charging.*;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.common.EcommerceRuntimeException;
import com.vodafone.global.er.decoupling.PayloadConstants;
import com.vodafone.global.er.decoupling.binding.request.GoodwillCreditRequest;
import com.vodafone.global.er.decoupling.binding.request.ModifyTariffRequest;
import com.vodafone.global.er.decoupling.binding.request.Purchase;
import com.vodafone.global.er.decoupling.binding.request.PurchaseAttributesType;
import com.vodafone.global.er.decoupling.binding.request.RatingAttributesType;
import com.vodafone.global.er.decoupling.binding.request.RenewPurchasePackageRequest;
import com.vodafone.global.er.decoupling.binding.response.ErVersionInfoResponse;
import com.vodafone.global.er.decoupling.binding.response.GoodwillCreditResponse;
import com.vodafone.global.er.decoupling.binding.response.ModifyTariffAuthorisation;
import com.vodafone.global.er.decoupling.binding.response.RenewPurchaseAuthorisation;
import com.vodafone.global.er.decoupling.binding.response.UsageAuthorisation;


/**
 * 
 * @author matt
 *
 */
public class PurchaseApiDecouplingImpl extends BaseErApiDecouplingImpl implements PurchaseApi {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseApiDecouplingImpl.class);

	public PurchaseApiDecouplingImpl(Locale locale, String clientId) {
		super(locale, clientId);
	}


	@Override
	public PurchaseAuthorization purchasePackageMsisdn(String clientApplicationId, String msisdn, String packageId, PurchaseAttributes purchaseAttributes) throws PurchaseAuthorizationException
	{
			checkNullParams(clientApplicationId, msisdn);
			final Purchase request = super.createRequest(PayloadConstants.PURCHASE_REQUEST_PAYLOAD);

			request.setMsisdn(msisdn);


			if (packageId == null && (purchaseAttributes==null || isBlank(purchaseAttributes.getShortPackageId()))) {
				throw new PurchaseAuthorizationException("packageId and short packageId both null");
			}
			
			if(packageId != null)
				request.setPackageId(packageId);

			PurchaseAttributesType purAttrType = converter.buildPurchAttrs(purchaseAttributes);

			request.setPurchaseAttributes(purAttrType);

			//Rating Attributes
			RatingAttributesType ratingAtt = converter.buildRatingAttributes(purchaseAttributes);
				
			request.setRatingAttributes(ratingAtt);

			UsageAuthorisation usageAuthorizationType_;
			try {
//				final ErRequest element = _factory_.buildEnvelope(PayloadConstants.PURCHASE_REQUEST_PAYLOAD, request, null);
//				element.setClientApplicationId(clientApplicationId);
//				Object payload = _client.getPayload(element, getHeaders(request));
//				usageAuthorizationType_ = getResultFromPayload(payload, UsageAuthorisation.class);
				usageAuthorizationType_ = sendRequestAndGetResponse(PayloadConstants.PURCHASE_REQUEST_PAYLOAD, request, UsageAuthorisation.class, clientApplicationId);
			} catch (EcommerceException e) {
				logger.warn(e.getMessage());
				throw new PurchaseAuthorizationException(e);

			}

			return converter.convertPurchaseAuthObj(usageAuthorizationType_);

	}

	@Override
	public PurchaseAuthorization renewPurchasePackageMsisdn(String clientApplicationId, String msisdn, String packageSubscriptionId, PurchaseAttributes purchaseAttributes) throws PurchaseAuthorizationException
	{
			final RenewPurchasePackageRequest request = createRequest(PayloadConstants.RENEW_PURCHASE_PACKAGE_REQUEST_PAYLOAD);

			checkNullParams(packageSubscriptionId, msisdn, clientApplicationId);
			//TODO use converter.buildRatingAttributes(purchaseAttributes);
			//Rating Attributes
			com.vodafone.global.er.decoupling.binding.request.RatingAttributesType ratingAtt = null;
			if (purchaseAttributes != null) {
				ratingAtt = new RatingAttributesType();
				ratingAtt.setPaymentInformation(purchaseAttributes.getPaymentInformation());
				if(purchaseAttributes.getShortPackageId() != null) {
					ratingAtt.setShortPackageId(purchaseAttributes.getShortPackageId());
				}
				ratingAtt.setExternalTransId(purchaseAttributes.getExternalTransId());
				ratingAtt.setPartnerId(purchaseAttributes.getPartnerId());
				//CR 2145
				if(purchaseAttributes.getPreRate()>=0)
					ratingAtt.setPreRate(purchaseAttributes.getPreRate());
				ratingAtt.setPreRatePriceIsGross(purchaseAttributes.isPreRatePriceGross());

				//CR - Add Invoice Text to goodwill credit request
				ratingAtt.setInvoiceText(purchaseAttributes.getInvoiceText());
				
				//JIRAET81 - Add Asset Id
				ratingAtt.setAssetId(purchaseAttributes.getAssetID());
			}
			request.setPurchaseAttributes(ratingAtt);
			request.setClientId(clientApplicationId);
			request.setMsisdn(msisdn);
			request.setSubscriptionId(packageSubscriptionId);
			//CR2237 - always get error id
			request.setErrorIdRequired(true);
			
			RenewPurchaseAuthorisation renewAuthResponse;
			try {
				renewAuthResponse = sendRequestAndGetResponse(PayloadConstants.RENEW_PURCHASE_PACKAGE_RESPONSE_PAYLOAD, request, RenewPurchaseAuthorisation.class);
			} catch (EcommerceException e) {
				logger.warn(e.getMessage());
				throw new PurchaseAuthorizationException(e);
			}

			return converter.buildRenewPurchaseAuthObj(renewAuthResponse);

	}
	
	

	@Override
	public PurchaseAuthorization purchasePackage(
			String clientApplicationId,
			String msisdn,
			String packageId,
			PurchaseAttributes purchaseAttributes
			) throws PurchaseAuthorizationException{

		return purchasePackageMsisdn(clientApplicationId, msisdn, packageId, purchaseAttributes);
	}



	//CR - Add Invoice Text to goodwill credit request - consolidated attributes into GoodwillCreditAttributes class
	public GoodwillCreditAuthorization goodwillCredit( String msisdn, GoodwillCreditAttributes goodwillCreditAttributes) throws GoodwillCreditAuthorizationException {
		
		
		final GoodwillCreditRequest goodwillCreditRequest = new GoodwillCreditRequest();

		goodwillCreditRequest.setMsisdn(msisdn);
		goodwillCreditRequest.setPackageId(goodwillCreditAttributes.getPackageId());
		goodwillCreditRequest.setPreRate(goodwillCreditAttributes.getPreRate());
		goodwillCreditRequest.setPartnerId(goodwillCreditAttributes.getPartnerId());
		goodwillCreditRequest.setMerchantId(goodwillCreditAttributes.getMerchantId());
		goodwillCreditRequest.setInvoiceText(goodwillCreditAttributes.getInvoiceText());

    	GoodwillCreditResponse response;
		try {
			response = sendRequestAndGetResponse(PayloadConstants.GOODWILL_CREDIT_REQUEST_ID, goodwillCreditRequest, GoodwillCreditResponse.class);
		} catch (EcommerceException e) {
			throw new GoodwillCreditAuthorizationException(e);
		}

    	return converter.buildGoodwillCreditAuthorization(response);
	}

	@Override
	public CatalogPackage ratePackage(String clientApplicationId, String msisdn, String packageId,
			PurchaseAttributes purchaseAttributes) throws EcommerceException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}



	@Override
	public PurchaseAuthorization confirmPurchasePackagePending(String clientApplicationId,
			String msisdn, String paymentId, int authResult, String authCode)
			throws PurchaseAuthorizationException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}

	@Override
	public boolean checkUserHasPackageWithPromoCode(String clientApplicationId, String msisdn,
			String packageId, String promoCode) throws EcommerceException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}

	@Override
	public ValidatePromoStatus validatePromoCode(ValidatePromoParam vpParam)
			throws EcommerceException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}
	

	@Override
	public ModifyTariffAuthorization modifyTariff(String msisdn,
			ModifyTariffAttributes modifyTariffAttributes) throws EcommerceException {
		final ModifyTariffRequest request = createRequest(PayloadConstants.GET_MODIFY_TARIFF_REQUEST_PAYLOAD);
		request.setMsisdn(msisdn);
		request.setAction(modifyTariffAttributes.getAction());
		request.setClientId(modifyTariffAttributes.getClientId());
		request.setDestinationTariff(modifyTariffAttributes.getDestinationTariff());
		request.setReason(modifyTariffAttributes.getReason());
		request.setSourceTariff(modifyTariffAttributes.getSourceTariff());
		
		ModifyTariffAuthorisation modifyTariffAuthorisation=null;
		try {
			modifyTariffAuthorisation = sendRequestAndGetResponse(PayloadConstants.GET_MODIFY_TARIFF_REQUEST_PAYLOAD, request, ModifyTariffAuthorisation.class);
		} catch (EcommerceException e) {
			logger.warn(e.getMessage());
			throw new EcommerceException(e);
		}
		
		return converter.buildModifyTariffAuthrization(modifyTariffAuthorisation);
	}

	/**
	 * added for MQC9301 but then removed since it's not required.  This call should return info like manifest name, timestamp, version number etc.
	 * The keys are the names of the properties, and the values are the values, eg
	 * "Manifest name" : "13.8"
	 * etc
	 * @return
	 */
	public Map<String, String> getVersionInfo() {
		final JAXBElement<Object> request = createRequest(PayloadConstants.ER_VERSION_INFO_REQUEST_PAYLOAD);

		
		Map<String, String> result = new HashMap<String, String>();
		ErVersionInfoResponse response;
		try {
			response = sendRequestAndGetResponse(PayloadConstants.ER_VERSION_INFO_REQUEST_PAYLOAD, request, ErVersionInfoResponse.class);
		} catch (EcommerceException e) {
			throw new EcommerceRuntimeException(e);
		}
		//Manifest name : er_core_batch_matt        
		//Manifest type : dev        
		//Timestamp     : 20130903 1534
		for (String line: response.getErVersionInfo().split("\n"))	{
			String[] vals = line.split(":", 2);
			if (StringUtils.isNotBlank(vals[0]))
				result.put(vals[0].trim(), vals[1]);
		}
		
		return result;
	}

}
