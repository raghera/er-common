package com.vodafone.global.er.decoupling;

public class PayloadConstants {
	/**
	 * Request payload identifiers.
	 */
	public static final int EXPRESS_PACKAGE_REQUEST_PAYLOAD					= 100001;
	public static final int INACTIVATE_SUBSCRIPTION_REQUEST_PAYLOAD			= 100002;
	public static final int MODIFY_SUBSCRIPTION_CHARGING_METHOD_REQUEST_PAYLOAD
																			= 100003;
	public static final int PURCHASE_REQUEST_PAYLOAD						= 100004;
	public static final int SELFCARE_SUBSCRIPTIONS_REQUEST_PAYLOAD			= 100005;
	public static final int SELFCARE_TRANSACTIONS_REQUEST_PAYLOAD			= 100006;
	public static final int USAGE_AUTH_RATE_CHARGE_REQUEST_PAYLOAD			= 100007;
	public static final int USAGE_COMPLETE_REQUEST_PAYLOAD					= 100008;
	public static final int ER_CONSTANTS_REQUEST_PAYLOAD					= 100009;
	public static final int GET_PACKAGE_REQUEST_PAYLOAD						= 100010;
	public static final int VALIDATE_SERVICE_REQUEST_PAYLOAD			    = 100011;
    public static final int SELFCARE_MICRO_SERVICE_REQUEST_PAYLOAD          = 100012;
    public static final int VALIDATE_PROMO_CODE_REQUEST_PAYLOAD             = 100013;
    public static final int GET_SERVICES_REQUEST_PAYLOAD                    = 100014;
	public static final int USAGE_AUTH_RATE_CHARGE_EX_REQUEST_PAYLOAD		= 100015;

	//REMEDY 6332
	public static final int GET_BASE_PRICES_REQUEST_PAYLOAD                 = 100016;
	public static final int USAGE_AUTH_RATE_REQUEST_PAYLOAD                 = 100017;
	public static final int GET_BALANCES_REQUEST_PAYLOAD                    = 100018;
	public static final int GET_PRICEPOINT_REQUEST_PAYLOAD                  = 100019;
    //END 6332
	//REMEDY 6333
	public static final int GET_PACKAGES_REQUEST_PAYLOAD                 	= 100020;
	public static final int GET_ALL_SERVICES_REQUEST_PAYLOAD                = 100021;
	public static final int GET_SERVICE_REQUEST_PAYLOAD               		= 100022;
	public static final int GET_NEXT_PAYMENT_AMOUNT_REQUEST_PAYLOAD         = 100023;
	public static final int IS_UNIQUE_PROMO_PRECODE_REQUEST_PAYLOAD         = 100024;
    //END 6333

	//REMEDY 6334
	public static final int CHECK_PROMOTIONS_REQUEST_PAYLOAD                = 100025;
	public static final int GET_SUPERCREDIT_BALANCES_REQUEST_PAYLOAD        = 100026;
	public static final int RENEW_PURCHASE_PACKAGE_REQUEST_PAYLOAD          = 100027;
	public static final int RATE_PACKAGE_REQUEST_PAYLOAD                    = 100028;
	//END REMEDY 6334

	//REMEDY 6335
	public static final int GET_VERSION_REQUEST_PAYLOAD         			= 100029;
	public static final int GET_TAX_REQUEST_PAYLOAD         				= 100030;
	public static final int GET_PARENT_TRANSACTION_REQUEST_PAYLOAD          = 100031;
	//END 6335
	//REMEDY 6345
	public static final int GET_REASON_CODES_REQUEST_PAYLOAD                = 100032;
	//END 6345
	//REMEDY 6978
	public static final int GET_FIND_PACKAGES_WITH_SERVICE_REQUEST_PAYLOAD  = 100033;
	//END 6978

	//REMEDY 6779
	public static final int GET_REFUND_MONETARY_REQUEST_PAYLOAD 			= 100034;
	public static final int GET_REFUND_ENLARGEMENT_REQUEST_PAYLOAD 			= 100035;
	public static final int GET_REFUND_DISCOUNT_REQUEST_PAYLOAD 			= 100036;
	public static final int GET_REFUND_CREDIT_REQUEST_PAYLOAD 				= 100037;
	//REMEDY 6779
	public static final int GET_BASIC_ACCOUNT_REQUEST_PAYLOAD				= 100038;
	//MQC 8355 - add request id for Notification subscribe
	public static final int NOTIFICATION_SUBSCRIBE_REQUEST_PAYLOAD			= 100039;
	public static final int INACTIVATE_ACCOUNT_REQUEST_PAYLOAD				= 100040;
	public static final int MODIFY_BAN_REQUEST								= 100041;
	public static final int MODIFY_MSISDN_REQUEST							= 100042;

	//CR-0978 Location Services
	public static final int GET_MODIFY_TARIFF_REQUEST_PAYLOAD 				= 100043;
	public static final int GET_TARIFF_REQUEST_PAYLOAD 						= 100044;

	public static final int ER_VERSION_INFO_REQUEST_PAYLOAD            		= 100045;

	//CR-1528 - Selfcare Lite Transactions api
	public static final int SELFCARE_LITE_TRANSACTIONS_REQUEST_PAYLOAD      = 100046;

	//CR-1619 - Get application configuration
	public static final int GET_APPLICATION_CONFIG_REQUEST_PAYLOAD          = 100047;

	//CR-1759 - Start
    public static final int PROVISION_FULL_UPDATE_SERVICE_STATUS_REQUEST_PAYLOAD      	= 100048;
    public static final int PROVISION_SIMPLE_UPDATE_SERVICE_STATUS_REQUEST_PAYLOAD      = 100049;

	//CR-1791  Decoupling Interface 
	public static final int MODIFY_SUBSCRIPTION_REQUEST_PAYLOAD     		 = 100051;

    public static final int CUSTCARE_FULL_REFUND_CREDIT_REQUEST_PAYLOAD     = 100063;
    public static final int CUSTCARE_FULL_REFUND_MONETARY_REQUEST_PAYLOAD   = 100064;
    public static final int CUSTCARE_FULL_REFUND_DISCOUNT_REQUEST_PAYLOAD   = 100065;
    public static final int CUSTCARE_FULL_REFUND_ENLARGEMENT_REQUEST_PAYLOAD	= 100066;

    public static final int CATALOG_FULL_PACKAGE_REQUEST_PAYLOAD      		= 100067;
    public static final int CATALOG_FULL_PACKAGES_REQUEST_PAYLOAD      		= 100068;
    public static final int CATALOG_FULL_PRICEPOINT_REQUEST_PAYLOAD      	= 100069;
    public static final int CATALOG_FULL_SERVICE_REQUEST_PAYLOAD      		= 100070;

    public static final int SELFCARE_FULL_BALANCES_REQUEST_PAYLOAD      	= 100071;
    //CR-1759 - End

    //CR1923 Partner Trading Limit
    public static final int GET_PARTNERS_WITH_TRADING_LIMIT_REQUEST_PAYLOAD = 100072;
    public static final int GET_PARTNER_TRADING_LIMIT_REQUEST_PAYLOAD = 100073;
    
    //CR1938 Usage Auth Rate Plus
    public static final int USAGE_AUTH_RATE_PLUS_REQUEST_PAYLOAD			= 100074;
    public static final int USAGE_AUTH_RATE_CHARGE_PLUS_REQUEST_PAYLOAD      = 100075;
    
    //CR2068 Services with Partner information
    public static final int GET_ALL_SERVICES_PARTNERS_REQUEST_PAYLOAD       = 100076;
    
    //CR2082 validation call to the opco ERIF
    public static final int VALIDATE_MSISDN_REQUEST_PAYLOAD       			= 100077;
    
    //CR2198 - account Child Service Provider Id and Service Provider Type
    public static final int MODIFY_ACCOUNT_CHILD_SP_ID_REQUEST_PAYLOAD		= 100078;
    public static final int MODIFY_ACCOUNT_SP_TYPE_REQUEST_PAYLOAD			= 100079;
    
    public static final int GET_DETAILS_FOR_EXTERNAL_TXN_REQUEST   			= 110001;
    public static final int GET_DETAILS_FOR_EXTERNAL_SUB_REQUEST   			= 110002;
    
    //MQC7353
    public static final int CATALOG_LITTLE_PACKAGES_REQUEST_PAYLOAD      		= 110003;
    // CR2040 MPAY replacement.
    //MQC8355 - only use the unique id, node name should no longer be used
    //public static final String GOODWILL_CREDIT_REQUEST							= "goodwill-credit-request";
    //public static final String MODIFY_SPEND_LIMITS_REQUEST						= "modify-spend-limits-request";
    public static final int GOODWILL_CREDIT_REQUEST_ID							= 120000;
    public static final int MODIFY_SPEND_LIMITS_REQUEST_ID						= 120002;
    

    
	/**
	 * Response payload identifiers.
	 */
	public static final int ERROR_RESPONSE_PAYLOAD							= 100001;
	public static final int EXPRESS_PACKAGE_RESPONSE_PAYLOAD				= 100002;
	public static final int INACTIVATE_SUBSCRIPTION_RESPONSE_PAYLOAD		= 100003;
	public static final int MODIFY_SUBSCRIPTION_CHARGING_METHOD_RESPONSE_PAYLOAD
																			= 100004;
	public static final int PURCHASE_OPTIONS_RESPONSE_PAYLOAD				= 100005;
	public static final int SELFCARE_SUBSCRIPTIONS_RESPONSE_PAYLOAD			= 100006;
	public static final int SELFCARE_TRANSACTIONS_RESPONSE_PAYLOAD			= 100007;
	public static final int USAGE_AUTHORISATION_RESPONSE_PAYLOAD			= 100008;
	public static final int ER_CONSTANTS_RESPONSE_PAYLOAD					= 100009;
	public static final int GET_PACKAGE_RESPONSE_PAYLOAD					= 100010;
	public static final int VALIDATE_SERVICE_RESPONSE_PAYLOAD				= 100011;
    public static final int SELFCARE_MICRO_SERVICE_RESPONSE_PAYLOAD         = 100012;
    public static final int VALIDATE_PROMO_CODE_RESPONSE_PAYLOAD            = 100013;
    public static final int GET_SERVICES_RESPONSE_PAYLOAD                    = 100014;
	//REMEDY 6332
	public static final int GET_BASE_PRICES_RESPONSE_PAYLOAD                 = 100016;
	public static final int GET_BALANCES_RESPONSE_PAYLOAD                    = 100018;
	public static final int GET_PRICEPOINT_RESPONSE_PAYLOAD                  = 100019;
    //END 6332
	//REMEDY 6333
	public static final int GET_PACKAGES_RESPONSE_PAYLOAD                 	= 100020;
	public static final int GET_ALL_SERVICES_RESPONSE_PAYLOAD               = 100021;
	public static final int GET_SERVICE_RESPONSE_PAYLOAD               		= 100022;
	public static final int GET_NEXT_PAYMENT_AMOUNT_RESPONSE_PAYLOAD        = 100023;
	public static final int IS_UNIQUE_PROMO_PRECODE_RESPONSE_PAYLOAD        = 100024;
    //END 6333

	//REMEDY 6334
	public static final int CHECK_PROMOTIONS_RESPONSE_PAYLOAD               = 100025;
	public static final int GET_SUPERCREDIT_BALANCES_RESPONSE_PAYLOAD       = 100026;
	public static final int RENEW_PURCHASE_PACKAGE_RESPONSE_PAYLOAD         = 100027;
	public static final int RATE_PACKAGE_RESPONSE_PAYLOAD                   = 100028;
	//END REMEDY 6334

    //REMEDY 6335
	public static final int GET_VERSION_RESPONSE_PAYLOAD         			= 100029;
	public static final int GET_TAX_RESPONSE_PAYLOAD         				= 100030;
	public static final int GET_PARENT_TRANSACTION_RESPONSE_PAYLOAD         = 100031;
	//END 6335
	//REMEDY 6345
	public static final int GET_REASON_CODES_RESPONSE_PAYLOAD               = 100032;
	//END 6345
	//REMEDY 6978
	public static final int GET_FIND_PACKAGES_WITH_SERVICE_RESPONSE_PAYLOAD = 100033;
	//END 6978

	//REMEDY 6779
	public static final int REFUND_AUTHORIZATION_PAYLOAD 					= 100034;
	//REMEDY 6779

	//MQC 8355 - add request id 
	public static final int GET_BASIC_ACCOUNT_RESPONSE_PAYLOAD				= 100035;
	public static final int NOTIFICATION_SUBSCRIBE_RESPONSE_PAYLOAD			= 100036;
	public static final int INACTIVATE_ACCOUNT_RESPONSE_PAYLOAD				= 100037;
	public static final int MODIFY_BAN_RESPONSE								= 100038;
	public static final int MODIFY_MSISDN_RESPONSE							= 100039;
	
	
	//CR-0978 Location Services
	public static final int MODIFY_TARIFF_AUTHORIZATION_PAYLOAD 			= 100040;
	public static final int GET_TARIFF_RESPONSE_PAYLOAD               		= 100041;


	public static final int ER_VERSION_INFO_PAYLOAD_RESPONSE				= 100046;

	//CR-1528 - Selfcare Lite Transactions api
	public static final int SELFCARE_LITE_TRANSACTIONS_RESPONSE_PAYLOAD     = 100047;

        //CR-1619 - get Application Configuration
	public static final int GET_APPLICATION_CONFIG_RESPONSE_PAYLOAD         = 100048;

	//CR-1759 - Start
    public static final int PROVISION_FULL_UPDATE_SERVICE_STATUS_RESPONSE_PAYLOAD      = 100049;
    public static final int PROVISION_SIMPLE_UPDATE_SERVICE_STATUS_RESPONSE_PAYLOAD    = 100050;

//CR-1791 - Modify Subscription - Decoupling 
	public static final int MODIFY_SUBSCRIPTION_RESPONSE_PAYLOAD    		 = 100051;

    public static final int SELFCARE_FULL_SUBSCRIPTIONS_RESPONSE_PAYLOAD 	= 100056;
    public static final int SELFCARE_FULL_TRANSACTIONS_RESPONSE_PAYLOAD  	= 100057;
    
    public static final int CUSTCARE_FULL_REFUND_CREDIT_RESPONSE_PAYLOAD    = 100063;
    public static final int CUSTCARE_FULL_REFUND_MONETARY_RESPONSE_PAYLOAD  = 100064;
    public static final int CUSTCARE_FULL_REFUND_DISCOUNT_RESPONSE_PAYLOAD  = 100065;
    public static final int CUSTCARE_FULL_REFUND_ENLARGEMENT_RESPONSE_PAYLOAD   = 100066;
        
    public static final int CATALOG_FULL_PACKAGE_RESPONSE_PAYLOAD      		= 100067;
    public static final int CATALOG_FULL_PACKAGES_RESPONSE_PAYLOAD      	= 100068;
    public static final int CATALOG_FULL_PRICEPOINT_RESPONSE_PAYLOAD      	= 100069;
    public static final int CATALOG_FULL_SERVICE_RESPONSE_PAYLOAD      		= 100070;

    public static final int SELFCARE_FULL_BALANCES_RESPONSE_PAYLOAD      	= 100071;
    //CR-1759 - End

	//Remedy 6780 and 6778, Bruno Meseguer, missing decoupling API
	//new operations are identified by node name instead of id attribute
	//TP 10112010 - new operations MUST only be identified by the id attribute, node name
	//must no longer be used.
    //MQC8355 - only use the unique id, node name should no longer be used
	//public static final String GET_BASIC_ACCOUNT_PAYLOAD					= "get-basic-account";
	//public static final String NOTIFICATION_SUBSCRIBE_PAYLOAD				= "notification-subscribe";
	//public static final String INACTIVATE_ACCOUNT_PAYLOAD					= "inactivate-account";
	//public static final String MODIFY_BAN_PAYLOAD							= "modify-ban";
	//public static final String MODIFY_MSISDN_PAYLOAD						= "modify-msisdn";
    
    //CR1564 -Utctimezone for diff region in country
	public static final int MODIFY_USERGROUP_PAYLOAD 					= 100053;
	public static final int MODIFY_TIMEZONE_PAYLOAD 				    = 100054;
	public static final int MODIFY_BILLING_CYCLE_PAYLOAD 				= 100055;
	public static final int SELFCARE_FULL_SUBSCRIPTIONS_REQUEST_PAYLOAD	= 100056;
	public static final int SELFCARE_FULL_TRANSACTIONS_REQUEST_PAYLOAD	= 100057;
	public static final int GET_FULL_ACCOUNT_PAYLOAD				    = 100058;
	//CR1789 onestep - this id used for both request and response
	public static final int FIND_PACKAGES_BY_SERVICE_ID_ONE_STEP_PAYLOAD  = 100060;
	public static final int MODIFY_ACCOUNT_SP_ID					    = 100061;	// CR 1643 - Pre-Pay Post-Pay Split
	public static final int MODIFY_ACCOUNT_IS_PREPAY				    = 100062;	// CR 1643 - Pre-Pay Post-Pay Split

	//CR1923 Partner Trading Limit
    public static final int GET_PARTNERS_WITH_TRADING_LIMIT_RESPONSE_PAYLOAD = 100072;
    public static final int GET_PARTNER_TRADING_LIMIT_RESPONSE_PAYLOAD = 100073;
    
    //CR1938 Usage Auth Rate Plus
    public static final int USAGE_AUTHORISATION_PLUS_RESPONSE_PAYLOAD			= 100074;
    public static final int PURCHASE_OPTIONS_PLUS_RESPONSE_PAYLOAD				= 100075;

    //CR2068 Services with Partner information
    public static final int GET_ALL_SERVICES_PARTNERS_RESPONSE_PAYLOAD       	= 100076;
    
    //CR2082 validation call to the opco ERIF
    public static final int VALIDATE_MSISDN_RESPONSE_PAYLOAD       				= 100077;
    
    //CR2198 - account Child Service Provider Id and Service Provider Type
    public static final int MODIFY_ACCOUNT_CHILD_SP_ID_RESPONSE_PAYLOAD			= 100078;
    public static final int MODIFY_ACCOUNT_SP_TYPE_RESPONSE_PAYLOAD				= 100079;
    
    public static final int GET_DETAILS_FOR_EXTERNAL_TXN_RESPONSE   			= 110001;
    public static final int GET_DETAILS_FOR_EXTERNAL_SUB_RESPONSE   			= 110002;
    
    //MQC7353
    public static final int CATALOG_LITTLE_PACKAGES_RESPONSE_PAYLOAD      	= 100080;
    // CR2040 MPAY replacement.
    //MQC8355 - only use the unique id, node name should no longer be used
    //public static final String GOODWILL_CREDIT_RESPONSE							= "goodwill-credit-response";
    //public static final String MODIFY_SPEND_LIMITS_RESPONSE						= "modify-spend-limits-response";
    public static final int GOODWILL_CREDIT_RESPONSE_ID							= 120001;
    public static final int MODIFY_SPEND_LIMITS_RESPONSE_ID						= 120003;
    
}
