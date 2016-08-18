package com.vodafone.global.er.decoupling.client;




import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vodafone.global.er.decoupling.PayloadConstants;
import com.vodafone.global.er.decoupling.binding.request.ErRequest;
import com.vodafone.global.er.decoupling.binding.request.ObjectFactory;
import com.vodafone.global.er.decoupling.binding.request.PayloadType;
import com.vodafone.global.er.decoupling.binding.request.impl.ErRequestImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.PayloadTypeImpl;

/**
 * Copyright VODAFONE - 2010
 * File created 21 okt 2010
 * Author: Magnus Goransson
 * info@okatima.se
 * 
 */
class DecouplingMessageFactory
{

	private static final Logger _log = LoggerFactory.getLogger(DecouplingMessageFactory.class);
	private ObjectFactory _objFactory = null;



	public DecouplingMessageFactory()
	{
		_objFactory = new ObjectFactory();
	}

	/**
	 * pass in a payload constant and this method gives you the right jaxb request object.  But it's a bit redundant, since you can just do:<br/> <code>ModifyMsisdnType request = new ModifyMsisdnImpl();</code>
	 * @param payloadConstant
	 * @return
	 */
	public javax.xml.bind.Element createRequest(int payloadConstant) 
	{
		try
		{
//			_log.debug("Request for creating payload message of type: {}" , payloadConstant);

			switch(payloadConstant)	{
			case  PayloadConstants.GET_VERSION_REQUEST_PAYLOAD : return _objFactory.createGetVersionRequest();
			case  PayloadConstants.USAGE_AUTH_RATE_CHARGE_REQUEST_PAYLOAD : return _objFactory.createUsageAuthRateCharge();
			case  PayloadConstants.INACTIVATE_SUBSCRIPTION_REQUEST_PAYLOAD : return _objFactory.createInactivateSubscription();
			case  PayloadConstants.RENEW_PURCHASE_PACKAGE_REQUEST_PAYLOAD : return _objFactory.createRenewPurchasePackageRequest();
			case  PayloadConstants.GET_SUPERCREDIT_BALANCES_REQUEST_PAYLOAD : return _objFactory.createGetSupercreditBalancesRequest();
			case  PayloadConstants.SELFCARE_MICRO_SERVICE_REQUEST_PAYLOAD : return _objFactory.createSelfcareMicroServicesRequest();
			case  PayloadConstants.GET_NEXT_PAYMENT_AMOUNT_REQUEST_PAYLOAD : return _objFactory.createGetNextPaymentAmountRequest();
			case  PayloadConstants.PURCHASE_REQUEST_PAYLOAD : return _objFactory.createPurchase();
			case  PayloadConstants.GET_FULL_ACCOUNT_PAYLOAD : return _objFactory.createGetFullAccount();
			case  PayloadConstants.GET_BALANCES_REQUEST_PAYLOAD : return _objFactory.createGetBalancesRequest();
			case  PayloadConstants.GET_PACKAGES_REQUEST_PAYLOAD : return _objFactory.createGetPackagesRequest();
			case  PayloadConstants.PROVISION_FULL_UPDATE_SERVICE_STATUS_REQUEST_PAYLOAD : return _objFactory.createProvisionFullUpdateServiceStatusRequest();
			case  PayloadConstants.PROVISION_SIMPLE_UPDATE_SERVICE_STATUS_REQUEST_PAYLOAD : return _objFactory.createProvisionSimpleUpdateServiceStatusRequest();
			case  PayloadConstants.CATALOG_FULL_PACKAGE_REQUEST_PAYLOAD : return _objFactory.createCatalogFullPackageRequest();
			case  PayloadConstants.CATALOG_FULL_PACKAGES_REQUEST_PAYLOAD : return _objFactory.createCatalogFullPackagesRequest();
			case  PayloadConstants.CATALOG_LITTLE_PACKAGES_REQUEST_PAYLOAD : return _objFactory.createCatalogLittlePackagesRequest();
			case  PayloadConstants.CATALOG_FULL_SERVICE_REQUEST_PAYLOAD : return _objFactory.createCatalogFullServiceRequest();
			case  PayloadConstants.CATALOG_FULL_PRICEPOINT_REQUEST_PAYLOAD : return _objFactory.createCatalogFullPricepointRequest();
			case  PayloadConstants.SELFCARE_TRANSACTIONS_REQUEST_PAYLOAD : return _objFactory.createSelfcareTransactionsRequest();
			case  PayloadConstants.SELFCARE_FULL_SUBSCRIPTIONS_REQUEST_PAYLOAD : return _objFactory.createSelfcareFullSubscriptions();
			case  PayloadConstants.SELFCARE_FULL_TRANSACTIONS_REQUEST_PAYLOAD : return _objFactory.createSelfcareFullTransactions();
			case  PayloadConstants.SELFCARE_FULL_BALANCES_REQUEST_PAYLOAD : return _objFactory.createSelfcareFullBalancesRequest();
			case  PayloadConstants.CUSTCARE_FULL_REFUND_CREDIT_REQUEST_PAYLOAD : return _objFactory.createCustcareFullRefundCreditRequest();
			case  PayloadConstants.CUSTCARE_FULL_REFUND_DISCOUNT_REQUEST_PAYLOAD : return _objFactory.createCustcareFullRefundDiscountRequest();
			case  PayloadConstants.CUSTCARE_FULL_REFUND_ENLARGEMENT_REQUEST_PAYLOAD : return _objFactory.createCustcareFullRefundEnlargementRequest();
			case  PayloadConstants.CUSTCARE_FULL_REFUND_MONETARY_REQUEST_PAYLOAD : return _objFactory.createCustcareFullRefundMonetaryRequest();
			case  PayloadConstants.MODIFY_SUBSCRIPTION_CHARGING_METHOD_REQUEST_PAYLOAD : return _objFactory.createModifySubscriptionChargingMethod();
			case  PayloadConstants.MODIFY_SPEND_LIMITS_REQUEST_ID : return _objFactory.createModifySpendLimitsRequest();
			case  PayloadConstants.INACTIVATE_ACCOUNT_REQUEST_PAYLOAD : return _objFactory.createInactivateAccount();
			case  PayloadConstants.GET_BASIC_ACCOUNT_REQUEST_PAYLOAD : return _objFactory.createGetBasicAccount();
			case  PayloadConstants.MODIFY_USERGROUP_PAYLOAD : return _objFactory.createModifyUsergroup();
			case  PayloadConstants.GET_PRICEPOINT_REQUEST_PAYLOAD : return _objFactory.createGetPricepointRequest();
			case  PayloadConstants.CHECK_PROMOTIONS_REQUEST_PAYLOAD : return _objFactory.createCheckPromotionsRequest();
			case  PayloadConstants.USAGE_COMPLETE_REQUEST_PAYLOAD : return _objFactory.createUsageComplete();
			case  PayloadConstants.USAGE_AUTH_RATE_REQUEST_PAYLOAD : return _objFactory.createUsageAuthRate();
			case  PayloadConstants.GET_DETAILS_FOR_EXTERNAL_SUB_REQUEST	:return _objFactory.createGetDetailsForExternalSubscriptionRequest();
			case  PayloadConstants.GET_DETAILS_FOR_EXTERNAL_TXN_REQUEST :return _objFactory.createGetDetailsForExternalTransactionRequest();
			case  PayloadConstants.MODIFY_SUBSCRIPTION_REQUEST_PAYLOAD : return _objFactory.createModifySubscriptionRequest();
			//case  PayloadConstants.ER_CONSTANTS_REQUEST_PAYLOAD : return _objFactory.create  ;
			case  PayloadConstants.ER_VERSION_INFO_REQUEST_PAYLOAD : return _objFactory.createErVersionInfoRequest()  ;
			//case  PayloadConstants.EXPRESS_PACKAGE_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.FIND_PACKAGES_BY_SERVICE_ID_ONE_STEP_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_ALL_SERVICES_PARTNERS_REQUEST_PAYLOAD : return _objFactory.create  ;
			case  PayloadConstants.GET_ALL_SERVICES_REQUEST_PAYLOAD : return _objFactory.createGetAllServicesRequest()  ;
			//case  PayloadConstants.GET_APPLICATION_CONFIG_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_BASE_PRICES_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_FIND_PACKAGES_WITH_SERVICE_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_MODIFY_TARIFF_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_PACKAGE_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_PARENT_TRANSACTION_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_PARTNERS_WITH_TRADING_LIMIT_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_PARTNER_TRADING_LIMIT_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_REASON_CODES_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_REFUND_CREDIT_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_REFUND_DISCOUNT_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_REFUND_ENLARGEMENT_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_REFUND_MONETARY_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_SERVICES_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_SERVICE_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_TARIFF_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GET_TAX_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.GOODWILL_CREDIT_REQUEST_ID : return _objFactory.create  ;
			//case  PayloadConstants.IS_UNIQUE_PROMO_PRECODE_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.MODIFY_ACCOUNT_CHILD_SP_ID_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.MODIFY_ACCOUNT_IS_PREPAY : return _objFactory.create  ;
			//case  PayloadConstants.MODIFY_ACCOUNT_SP_ID : return _objFactory.create  ;
			//case  PayloadConstants.MODIFY_ACCOUNT_SP_TYPE_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.MODIFY_BAN_REQUEST : return _objFactory.create  ;
			//case  PayloadConstants.MODIFY_BILLING_CYCLE_PAYLOAD : return _objFactory.create  ;
			case  PayloadConstants.MODIFY_MSISDN_REQUEST : return _objFactory.createModifyMsisdn()  ;
			//case  PayloadConstants.MODIFY_TARIFF_AUTHORIZATION_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.MODIFY_TIMEZONE_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.NOTIFICATION_SUBSCRIBE_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.RATE_PACKAGE_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.REFUND_AUTHORIZATION_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.SELFCARE_LITE_TRANSACTIONS_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.SELFCARE_SUBSCRIPTIONS_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.USAGE_AUTH_RATE_CHARGE_EX_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.USAGE_AUTH_RATE_CHARGE_PLUS_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.USAGE_AUTH_RATE_PLUS_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.VALIDATE_MSISDN_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.VALIDATE_PROMO_CODE_REQUEST_PAYLOAD : return _objFactory.create  ;
			//case  PayloadConstants.VALIDATE_SERVICE_REQUEST_PAYLOAD : return _objFactory.create  ;
			
			default : 	_log.info("UNKNOWN PAYLOAD CONSTANT " + payloadConstant + "- will return null");
						throw new RuntimeException("DecouplingMessageFactory: unknown payload constant "+payloadConstant);
			}

		}   catch(final JAXBException jaxbe)   {
			_log.error("problem creating payload message:" , jaxbe);
			throw new RuntimeException(jaxbe);
		}

	}


	/**
	 * Builds an ErRequest from the object passed in, using the specified id
	 * @param id
	 * @param obj
	 * @return
	 */
	public ErRequest buildEnvelope(int id, Object obj) {
		return buildEnvelope(id, obj, null);
	}

	/**
	 * Builds an ErRequest from the object passed in, using the specified id
	 * @param id
	 * @param obj
	 * @param clientId for the  client-application-id field in the request - can be null
	 * @return
	 */
	public ErRequest buildEnvelope(int id, Object obj, String clientId) {

		_log.debug("build Envelope with id - {} - {}", id , obj.getClass().getSimpleName());
		final ErRequest er = new ErRequestImpl();
		er.setId(id);
		final PayloadType pt = new PayloadTypeImpl();
		pt.setAny(obj);
		er.setPayload(pt);
		if (clientId!=null)
			er.setClientApplicationId(clientId);
		return er;
	}

}
