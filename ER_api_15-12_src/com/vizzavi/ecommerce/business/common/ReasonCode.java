package com.vizzavi.ecommerce.business.common;

import org.apache.commons.lang.StringUtils;



/**
 * A ReasonCode consists of an id (the code) and a name (the description).<br/>
 * When creating a new ReasonCode, please add it in ReturnCode.java and the ER_RETURN_CODES table.<br/>
 * Also notify the GIG team so they can handle it<br/>
 * NB the ReasonCode id (accessed via {@link #getCode()}) does NOT return the primary key RETURN_CODE_OBJ_ID used by the ER_RETURN_CODES table -
 * for that, use {@link ReturnCode}	<br/>
 * <br/>
 * 
 */
public class ReasonCode implements java.io.Serializable
{
	private    static final long serialVersionUID = -363537846850350069L;



	/**********************************************************************/
	/*
	 * Error code integers.
	 */
	public final static int C_OK = 0;
	public final static int C_SYSTEM_ERROR = 1;

	/**
        Payment pending means that the front end has to confirm authorisation
	 */
	public final static int C_PAYMENT_PENDING = 11;
	/**
        Customer already has the package.
	 */
	public final static int C_PACKAGE_RENEWAL = 12;

	/**
        Payment errors from PECS
	 */
	public final static int C_PAYMENT_FAILED = 21;
	public final static int C_DUPLICATE_PROMO_CODE = 22;
	public final static int C_ACCOUNT_NOT_FOUND = 23;
	public final static int C_INVALID_RENEWAL = 24;
	public final static int C_NO_TOKENS = 25;
	public final static int C_EXCEED_TOKEN_MAXIMUM = 26;
	public final static int C_RENEWAL_UNAUTHORISED_ON_UNPROVISIONED_PACKAGE = 27;
	//REMEDY 6401 - added for attempted renewals on non recurring / event or inactive subscriptions
	public final static int C_RENEWAL_UNAUTHORISED_ON_EVENT_PACKAGE = 28;
	public final static int C_RENEWAL_UNAUTHORISED_ON_INACTIVE_CLOSED_SUBSCRIPTION = 29;
	/**
        Front end errors
	 */
	public final static int C_PACKAGE_ID_NOT_FOUND = 31;
	public final static int C_SERVICE_ID_NOT_FOUND  = 32;
	public final static int C_INVALID_PAYMENT_TYPE = 33;
	public final static int C_PRICEPOINT_ID_NOT_SET = 34;
	public final static int C_PRICEPOINT_NOT_ACTIVE = 35;
	public final static int C_PRE_ORDER_SUBSCRIPTION = 36; // @ER7 
	// VFEG [start]
	// 22/6/2005 - Fixed number of recurrences 
	public final static int C_NO_MATCHING_NON_RECURRING_PRICEPOINT = 37; // @ER7 
	public final static int C_EXPIRED_NUM_RECURRENCE = 38; // @ER7 
	// Partial session charge 
	public final static int C_AMOUNT_TO_CAPTURE_TOO_HIGH = 39; // @ER8 PII

	//VFEG [end]
	public final static int C_SUPERCREDIT_BALANCE_USEDUP = 39; // @ER7


	/**
        Usage errors
	 */
	public final static int C_NO_VALID_PACKAGE = 41;

	public final static int C_BAD_ASYNC_RECORD_ID = 42;   //WRAPPER_EXIT_BAD_ASYNC_RECORD_ID

	/**
    Refund reason codes
	 */
	public final static int C_REFUND_EXCEED_MAX_CASH = 51;
	//REMEDY 6401 - add reason code where credit refund against inactive or closed subscription
	public final static int C_CREDIT_REFUND_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION = 52;
	//MQC 4441
	public final static int C_REFUND_PAYMENT_TRANSACTION_EXCEEDS_AUTH_MONTHS_PERIOD = 53;

	//MQC 5955 - add reason code for refund enlargement against an event package
	public final static int C_REFUND_ENLARGEMENT_AGAINST_EVENT_SUBSCRIPTION = 54;

	//MQC 5955 - add reason code for refund enlargement against against inactive or closed subscription
	public final static int C_REFUND_ENLARGEMENT_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION = 55;

	//MQC 6173 - add reason code for refund discount with invalid discount amount
	public final static int C_REFUND_DISCOUNT_INVALID_AMOUNT = 56;

	//MQC 6173 - add reason code for refund discount against an event or non-recurring package
	public final static int C_REFUND_DISCOUNT_AGAINST_EVENT_NON_RECURRING_SUBSCRIPTION = 57;

	//MQC 6922 - add reason code where a monetary refund is being carried out with a non monetary charging resource
	public final static int C_MONETARY_REFUND_WITH_NON_MONETARY_CHARGING_RESOURCE = 58;

	//MQC 6922 - add reason code where a monetary refund is being carried out where the 
	//monetary charging resource in the request does not match the monetary charging resource of the transaction
	//being refunded against
	public final static int C_MONETARY_REFUND_MONETARY_CHARGING_RESOURCE_MISMATCH = 59;

		
	public final static int C_MONETARY_REFUND_INVALID_AMOUNT = 60;
	
	public final static int C_REFUND_INVALID_TXN_STATUS=104;
	
	//CR XXXX - add reason code where a monetary refund is being carried out where the 
	//partner id in the request does not match the partner id of the transaction
	//being refunded against
	public final static int C_MONETARY_REFUND_MONETARY_PARTNER_MISMATCH = 90;
		
    // MQC 9138 Refund Payment Handler failure
    public final static int C_REFUND_PAYMENT_FAILED = 91;
    
    // MQC 9138 Refund Payment Handler failure, sub codes
    public final static int C_REFUND_PAYMENT_REJECTED = 92;
    public final static int C_REFUND_PAYMENT_DENIED = 93;
    public final static int C_REFUND_PAYMENT_ERROR = 94;
     
    // MQC 9138 Refund Payment Handler failure, rejected response from ER-IF
    public final static ReasonCode REFUND_PAYMENT_REJECTED = 
    		new ReasonCode(C_REFUND_PAYMENT_FAILED, 
    				C_REFUND_PAYMENT_REJECTED,
    				"REFUND_PAYMENT_REJECTED");
    
    // MQC 9138 Refund Payment Handler failure, rejected response from ER-IF
    public final static ReasonCode REFUND_PAYMENT_DENIED = 
    		new ReasonCode(C_REFUND_PAYMENT_FAILED, 
    				C_REFUND_PAYMENT_DENIED,
    				"REFUND_PAYMENT_DENIED");
    
    // MQC 9138 Refund Payment Handler failure, rejected response from ER-IF
    public final static ReasonCode REFUND_PAYMENT_ERROR = 
    		new ReasonCode(C_REFUND_PAYMENT_FAILED, 
    				C_REFUND_PAYMENT_ERROR,
    				"REFUND_PAYMENT_ERROR");
	/** refund only possible against a completed transaction*/
    public final static ReasonCode REFUND_INVALID_TXN_STATUS = 
    		new ReasonCode(C_REFUND_INVALID_TXN_STATUS,"refund only possible against a completed transaction");
    
	/**
    Provision Reason Codes
	 */
	public final static int C_CUSTOMER_DOES_NOT_HAVE_SUBSCRIPTION = 61;
	public final static int C_SUBSCRIPTION_SERVICE_NOT_FOUND = 62;
	public final static int C_NEW_PROVISION_TAG_NOT_SET = 63;
	public final static int C_ORIGINAL_PROVISION_TAG_NOT_SET = 63;

	/* CR-1759 - start*/
	public final static int C_UPDATE_SERVICE_STATUS_FAILED = 65;
	/* CR-1759 - end*/

	/**
	Transaction Reason Codes
	 */
	public final static int C_RESERVED_TRANSACTION_NOT_FOUND = 64;


	/**
	 * @version     ER8 P2
	 * @author      VFE PS Team
	 * Access Control Codes
	 */
	public final static int C_ACCESS_CONTROL_DENIED = 71;
	public final static int C_ACCESS_CONTROL_ERROR = 72;



	/**
	 * @hud STKHREQ13076 ROAMING
	 */
	public final static int C_NO_ROAMING_ENABLED = 80;

	/**
        These are  not implemented
	 */
	public final static int C_SUBSCRIPTION_EXPIRED = 101;
	public final static int C_SUBSCRIPTION_NOT_FOUND = 102;
	//MQC 8135 - set to 40 due to legacy errorneous value
	//public final static int C_USAGE_LIMIT_EXCEEDED = 103;
	public final static int C_USAGE_LIMIT_EXCEEDED = 40;

	/**
	 * @since ER 5.1
	 */
	//MQC 8135 - set to 103 due to legacy errorneous values
	//public final static int C_NON_REFUNDABLE_TRANSACTION = 200;
	public final static int C_NON_REFUNDABLE_TRANSACTION = 103;

	public final static int C_UNIT_TEST_ERROR = 1000;

	//REMEDY 6934
	public final static int C_ACCOUNT_VALIDATION_FAILED = 3000;

	/**
	 * @since ER 6
	 * No sub-reason is defined
	 */
	public final static int C_SUBREASON_UNKNOWN = 0;


	/**
	 * @since ER 6
	 * C_NO_VALID_PACKAGE sub-reasons
	 */
	public final static int C_SUBREASON_INSUFFICIENT_RESOURCE_BALANCE = -1;
	public final static int C_SUBREASON_ACTIVE_SUBSCRIPTION_NOT_FOUND = -2;
	public final static int C_SUCCESSFUL_EXPRESS_PURCHASE = -3;

	//REMEDY 6856 - add sub reason codes for express purchase failure
	public final static int C_SUBREASON_SERVICE_DOES_NOT_BELONG_TO_AN_EXPRESS_PACKAGE = -4;
	public final static int C_SUBREASON_SERVICE_BELONGS_TO_MORE_THAN_ONE_EXPRESS_PACKAGE = -5;
	public final static int C_SUBREASON_NO_ACTIVE_PRICEPOINT_FOR_EXPRESS_PACKAGE = -6;

	/**
	 * @since ER 6
	 * C_NO_VALID_PACKAGE sub-reasons
	 */
	public final static int C_FAILED_SUSPENDED = -3;

	//RBT Enhancements Start
	//Sub-Reason Code for FAILED_SUSPENDED when RBT parent is suspended
	public final static int C_PARENT_IS_SUSPENDED = -7;
	//RBT Enhancements End


	//MQC7083
	public final static int C_FAILED_INACTIVE = -4;       
	public final static int C_PARENT_IS_INACTIVE = -8;

	public final static int C_FAILED_PAYMENT_FAILED = -5;       
	public final static int C_PARENT_IS_PAYMENT_FAILED = -9;

	public final static int C_FAILED_PROVISIONING_FAILED = -6;       
	public final static int C_PARENT_IS_PROVISIONING_FAILED = -10;
	//MQC7083

	//CR1564-utc timezone 

	public final static int  C_MODIFY_TIMEZONE=450;
	public final static ReasonCode MODIFY_TIMEZONE = new ReasonCode(C_MODIFY_TIMEZONE, "Timezone provided is invalid for specified country");

	// CR1564 - Remove old status code which is no longer required.
	//	public final static int  C_MODIFY_TIMEZONE_UTC=451;
	//    public final static ReasonCode MODIFY_TIMEZONE_UTC = new ReasonCode(C_MODIFY_TIMEZONE_UTC, "Timezone utc format is not valid,should be [ex:+05:00UTC or -05:00UTC]");

	public final static int  C_MODIFY_TIMEZONE_ERIF=452;
	public final static ReasonCode MODIFY_TIMEZONE_ERIF = new ReasonCode(C_MODIFY_TIMEZONE_ERIF, "Modify not available through client request");


	public final static int C_MODIFY_BILLINGCYCLE=453;
	public final static ReasonCode MODIFY_BILLINGCYCLE = new ReasonCode(C_MODIFY_BILLINGCYCLE, "Billingcycle should be 1 to 31");

	public final static int C_MODIFY_BILLINGCYCLE_ERIF = 454;
	public final static ReasonCode MODIFY_BILLINGCYCLE_ERIF = new ReasonCode(C_MODIFY_BILLINGCYCLE_ERIF, "Modify not available through client request");

	public final static int C_MODIFY_USERGROUPS_ERIF = 455;
	public final static ReasonCode MODIFY_USERGROUPS_ERIF = new ReasonCode(C_MODIFY_USERGROUPS_ERIF, "Modify not available through client request");

	//end CR1564

	//CR 2229 - add action to modify usergroups
	public static final int C_MODIFY_USERGROUPS_INVALID_ACTION = 456;
	public final static ReasonCode MODIFY_USERGROUPS_INVALID_ACTION = new ReasonCode(C_MODIFY_USERGROUPS_INVALID_ACTION, "Invalid action for Modify usergroups");
	
	//@hud STKHREQ13107
	public final static int C_INVALID_UNIQUE_PROMOCODE = 300; // @ER8 PII
	public final static ReasonCode INVALID_UNIQUE_PROMOCODE = new ReasonCode(C_INVALID_UNIQUE_PROMOCODE, "INVALID UNIQUE PROMO CODE");


	/**
	 * Added for Ring Back Tone functionality in ER9.
	 * This is used when there are only sufficient funds to purchase
	 * the parent package but not the child package.
	 */
	public static final int C_RBT_PURCHASED_PARENT_PACKAGE_ONLY = 301;

	//MQC 5485 - new reason codes for when a child is attempted to be purchased where the
	//parent package is reserved, being provisioned or suspended
	public static final int C_RBT_PARENT_PACKAGE_BEING_PROVISIONED = 307;
	public static final int C_RBT_PARENT_PACKAGE_RESERVED = 308;


	//CR-0978 Location Services
	public static final int C_PURCHASE_TARIFF_FAILURE = 302;
	public static final int C_INACTIVATE_TARIFF_FAILURE = 303;
	public static final int C_PURCHASE_AND_INACTIVATE_TARIFF_FAILURE = 304;
	public static final int C_INVALID_TARIFF_ACTION = 305;
	public static final int C_INVALID_TARIFF = 306;

	public static final int C_SUBREASON_SERVICE_BEING_PROVISIONED = 310;  // CR1209

	// CR 1643 - Pre-Pay Post-Pay Split
	public final static int C_INVALID_ACCOUNT_SP_ID_ACTION = 311;
	public final static ReasonCode INVALID_ACCOUNT_SP_ID_ACTION = new ReasonCode(C_INVALID_ACCOUNT_SP_ID_ACTION, "Invalid action for SP_ID (Service Provider ID)");

	public final static int C_MODIFY_ACCOUNT_SP_ID_ERIF = 312;
	public final static ReasonCode MODIFY_ACCOUNT_SP_ID_ERIF = new ReasonCode(C_MODIFY_ACCOUNT_SP_ID_ERIF, "Modify not available through client request");

	public final static int C_INVALID_ACCOUNT_IS_PREPAY_ACTION = 313;
	public final static ReasonCode INVALID_ACCOUNT_IS_PREPAY_ACTION = new ReasonCode(C_INVALID_ACCOUNT_IS_PREPAY_ACTION, "Invalid value for IS_PREPAY. Only 'PRE', 'POST' or NULL values are accepted");

	public final static int C_MODIFY_ACCOUNT_IS_PREPAY_ERIF = 314;
	public final static ReasonCode MODIFY_ACCOUNT_IS_PREPAY_ERIF = new ReasonCode(C_MODIFY_ACCOUNT_IS_PREPAY_ERIF, "Modify not available through client request");
	// CR 1643 - Ends

	//CR2198 - Add Child Service Provider ID and Service Provider Type
	public final static int C_MODIFY_ACCOUNT_CHILD_SP_ID_ERIF = 315;
	public final static ReasonCode MODIFY_ACCOUNT_CHILD_SP_ID_ERIF = new ReasonCode(C_MODIFY_ACCOUNT_CHILD_SP_ID_ERIF, "Modify not available through client request");
	
	public final static int C_MODIFY_ACCOUNT_SP_TYPE_ERIF = 316;
	public final static ReasonCode MODIFY_ACCOUNT_SP_TYPE_ERIF = new ReasonCode(C_MODIFY_ACCOUNT_SP_TYPE_ERIF, "Modify not available through client request");
	
	//CR-1791
	public static final int C_ENHANCE_PURCHASE_FAILED_INVALID_STATUS = 320;
	public static final int C_PURCHASE_FAILED_INVALID_START_AND_END_DATES = 321;
	public static final int C_PURCHASE_FAILED_INVALID_END_DATE = 322;
	public static final int C_PURCHASE_FAILED_MISSING_PACKAGE_ID_INPUT_PARAM = 323;
	public static final int C_MODIFY_SUBSCRPITION_FAILED_INVALID_START_AND_END_DATES = 460;

	public static final int C_PURCHASE_FAILED_MORE_THAN_ONE_MATCHING_PRICEPOINT_EXISTS = 320;

	//CR2245 - Disallow prerate flag on catalog package
	public static final int C_PURCHASE_FAILED_PRERATE_DISALLOWED_ON_PACKAGE = 324;
	public final static ReasonCode PURCHASE_FAILED_PRERATE_DISALLOWED_ON_PACKAGE = new ReasonCode(C_PURCHASE_FAILED_PRERATE_DISALLOWED_ON_PACKAGE, "Prerate not allowed on package purchase or renewal");
	
	//CR1923
	public static final int C_PARTNER_TRANSACTION_VALUE_LIMIT_EXCEEDED = 330;
	public static final int C_PARTNER_CUMULATIVE_TRANSACTION_VALUE_LIMIT_EXCEEDED = 331;

	//MQC 8284
	public static final int C_RENEWAL_FAILED_NEW_USERGROUP_PRICEPOINT_RATE_GREATER = 340;
	public static final int C_RENEWAL_FAILED_SUBSCRIPTION_DEFAULT_USERGROUP_PRICEPOINT_MISMATCH = 341;
	
	
	//MD google CR 2082
	public static final int C_TXN_NOT_FOUND_FOR_EXT_TXN_ID=500;
	public static final int C_SUB_NOT_FOUND_FOR_EXT_SUB_ID=501;
	public static final int C_PURCHASE_FAILED_DUPLICATE_EXT_TXN_ID=502;
	public static final int C_PURCHASE_FAILED_DUPLICATE_EXT_SUB_ID=503;

	//Changes for ET-131 starts here
	public static final int C_PURCHASE_FAILED_PRICEPOINT_USERGROUP_NOT_ASSIGNED_TO_USER=325;
	public final static ReasonCode PURCHASE_FAILED_PRICEPOINT_USERGROUP_NOT_ASSIGNED_TO_USER = new ReasonCode(C_PURCHASE_FAILED_PRICEPOINT_USERGROUP_NOT_ASSIGNED_TO_USER, "Purchase against a usergroup pricepoint where the user does not belong to the usergroup");
	//Changes for ET-131 ends here
	/**********************************************************************/

	//JIRA ET196 Inactivate subscription promo-code
	public static final int C_INACTIVATE_SUB_PROMO_FAILED_MISSING_PARAM=510;
	public static final int C_INACTIVATE_SUB_PROMO_FAILED_MSISDN_SUBID_PACKAGEID_MISMATCH=511;
	public static final int C_INACTIVATE_SUB_PROMO_FAILED_ACTIVE_SUBSCRIPTION=512;
	public static final int C_INACTIVATE_SUB_PROMO_FAILED_NON_EXISTENT_SUBSCRIPTION_PROMO=513;
	public static final int C_INACTIVATE_SUB_PROMO_FAILED_SUBSCRIPTION_PROMO_ALREADY_INACTIVATED=514;
	/*
	 * Error code objects.
	 */
	public final static ReasonCode OK = new ReasonCode( C_OK, "OK" );
	public final static ReasonCode SYSTEM_ERROR = new ReasonCode( C_SYSTEM_ERROR, "SYSTEM_ERROR" );
	//MD 21/12/11 - additional ReasonCodes required here, because the following have entries in ER_RETURN_CODE but don't appear in this class:
	/*	25      1001    SUBSCRIPTION API RETURNED FALSE FOR INACTIVATE SUBSCRIPTION
		26      1002    PURCHASE API RETURNED AN EXCEPTION
		27      1003    SUBSCRIPTION API RETURNED AN EXCEPTION
		28      1004    PROVISIONING API RETURNED AN EXCEPTION
	 */
	public final static ReasonCode SUBSCRIPTION_API_FAILED_INACTIVATE = new ReasonCode(C_SYSTEM_ERROR, 1001, "SUBSCRIPTION API RETURNED FALSE FOR INACTIVATE SUBSCRIPTION");
	public final static ReasonCode PURCHASE_API_EXCEPTION = new ReasonCode(C_SYSTEM_ERROR, 1002, "PURCHASE API RETURNED FALSE FOR INACTIVATE SUBSCRIPTION");
	public final static ReasonCode SUBSCRIPTION_API_EXCEPTION = new ReasonCode(C_SYSTEM_ERROR, 1003, "SUBSCRIPTION API RETURNED FALSE FOR INACTIVATE SUBSCRIPTION");
	public final static ReasonCode PROVISIONING_API_EXCEPTION = new ReasonCode(C_SYSTEM_ERROR, 1004, "PROVISIONING API RETURNED FALSE FOR INACTIVATE SUBSCRIPTION");
	//end MD 21/12/11

	public final static ReasonCode PAYMENT_PENDING = new ReasonCode( C_PAYMENT_PENDING, "PAYMENT_PENDING" );
	public final static ReasonCode PACKAGE_RENEWAL = new ReasonCode( C_PACKAGE_RENEWAL, "RENEWAL NEEDED" );

	/* PECS ERROR CODES */
	public final static ReasonCode PAYMENT_FAILED = new ReasonCode( C_PAYMENT_FAILED, "PAYMENT FAILED" );
	public final static ReasonCode DUPLICATE_PROMO_CODE = new ReasonCode( C_DUPLICATE_PROMO_CODE, "DUPLICATE PROMO CODE" );
	public final static ReasonCode ACCOUNT_NOT_FOUND = new ReasonCode( C_ACCOUNT_NOT_FOUND, "ACCOUNT NOT FOUND" );
	public final static ReasonCode INVALID_RENEWAL = new ReasonCode( C_INVALID_RENEWAL, "INVALID RENEWAL" );
	public final static ReasonCode NO_TOKENS = new ReasonCode( C_NO_TOKENS, "" );
	public final static ReasonCode EXCEED_TOKEN_MAXIMUM = new ReasonCode( C_EXCEED_TOKEN_MAXIMUM, "" );

	public final static ReasonCode REFUND_EXCEED_MAX_CASH =
			new ReasonCode( C_REFUND_EXCEED_MAX_CASH, "The refund price is greater than the original payment" );
	//REMEDY 6401 - add reason code where credit refund against inactive or closed subscription
	public final static ReasonCode CREDIT_REFUND_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION =
			new ReasonCode( C_CREDIT_REFUND_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION, "Credit refund attempted against an inactive or closed subscription" );
	public final static ReasonCode REFUND_PAYMENT_TRANSACTION_EXCEEDS_AUTH_MONTHS_PERIOD =
			new ReasonCode( C_REFUND_PAYMENT_TRANSACTION_EXCEEDS_AUTH_MONTHS_PERIOD, "Refund attempted against a payment transaction older than the authorised period" );
	//MQC 5955 - add reason code for refund enlargement against an event package
	public final static ReasonCode REFUND_ENLARGEMENT_AGAINST_EVENT_SUBSCRIPTION =
			new ReasonCode( C_REFUND_ENLARGEMENT_AGAINST_EVENT_SUBSCRIPTION, "Refund enlargement attempted against an event subscription" );
	//MQC 5955 - add reason code for refund enlargement against against inactive or closed subscription
	public final static ReasonCode REFUND_ENLARGEMENT_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION =
			new ReasonCode( C_REFUND_ENLARGEMENT_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION, "Refund enlargement attempted against an inactive or closed subscription" );
	//MQC 6173 - add reason code for refund discount with invalid discount amount
	public final static ReasonCode REFUND_DISCOUNT_INVALID_AMOUNT =
			new ReasonCode( C_REFUND_DISCOUNT_INVALID_AMOUNT, "Refund discount attempted with an invalid amount, should be between 0 and 1" );
	//MQC 6173 - add reason code for refund discount against an event or non-recurring package
	public final static ReasonCode REFUND_DISCOUNT_AGAINST_EVENT_NON_RECURRING_SUBSCRIPTION =
			new ReasonCode( C_REFUND_DISCOUNT_AGAINST_EVENT_NON_RECURRING_SUBSCRIPTION, "refund discount attempted against an event or non-recurring package" );
	//MQC 6922 - add reason code where a monetary refund is being carried out with a non monetary charging resource
	public final static ReasonCode MONETARY_REFUND_WITH_NON_MONETARY_CHARGING_RESOURCE =
			new ReasonCode( C_MONETARY_REFUND_WITH_NON_MONETARY_CHARGING_RESOURCE, "Monetary refund attempted with a non monetary charging resource" );
	//MQC 6922 - add reason code where a monetary refund is being carried out where the 
	//monetary charging resource in the request does not match the monetary charging resource of the transaction
	//being refunded against
	public final static ReasonCode MONETARY_REFUND_MONETARY_CHARGING_RESOURCE_MISMATCH =
			new ReasonCode( C_MONETARY_REFUND_MONETARY_CHARGING_RESOURCE_MISMATCH, "Monetary refund charging resource mismatch" );

	public final static ReasonCode MONETARY_REFUND_INVALID_AMOUNT =
			new ReasonCode(C_MONETARY_REFUND_INVALID_AMOUNT, "Invalid cash refund amount");
	
	//CR 2273 - add reason code where a monetary refund is being carried out where the 
	//partner id in the request does not match the partner id of the transaction
	//being refunded against
	public final static ReasonCode MONETARY_REFUND_MONETARY_PARTNER_MISMATCH =
			new ReasonCode( C_MONETARY_REFUND_MONETARY_PARTNER_MISMATCH, "Monetary refund partner mismatch" );
	
	/**
        Front end errors
	 */
	public final static ReasonCode PACKAGE_ID_NOT_FOUND =
			new ReasonCode( C_PACKAGE_ID_NOT_FOUND, "PACKAGE ID NOT FOUND" );
	public final static ReasonCode SERVICE_ID_NOT_FOUND =
			new ReasonCode( C_SERVICE_ID_NOT_FOUND, "SERVICE ID NOT FOUND" );
	public final static ReasonCode PRICEPOINT_ID_NOT_SET =
			new ReasonCode( C_PRICEPOINT_ID_NOT_SET, "PRICEPOINT ID NOT SET IN PACKAGE" );
	public final static ReasonCode PRICEPOINT_NOT_ACTIVE =
			new ReasonCode( C_PRICEPOINT_NOT_ACTIVE, "PRICEPOINT IS NOT ACTIVE" );
	public final static ReasonCode RENEWAL_UNAUTHORISED_ON_UNPROVISIONED_PACKAGE =
			new ReasonCode( C_RENEWAL_UNAUTHORISED_ON_UNPROVISIONED_PACKAGE, "RENEWAL UNAUTHORISED ON UNPROVISIONED PACKAGE" );
	public final static ReasonCode RESERVED_TRANSACTION_NOT_FOUND =
			new ReasonCode( C_RESERVED_TRANSACTION_NOT_FOUND, "RESERVED_TRANSACTION_NOT_FOUND" );
	//REMEDY 6401 - added reason codes for attempted renewals on non recurring / event or inactive subscriptions
	public final static ReasonCode RENEWAL_UNAUTHORISED_ON_EVENT_PACKAGE =
			new ReasonCode( C_RENEWAL_UNAUTHORISED_ON_EVENT_PACKAGE, "RENEWAL UNAUTHORISED ON EVENT PACKAGE" );
	public final static ReasonCode RENEWAL_UNAUTHORISED_ON_INACTIVE_CLOSED_SUBSCRIPTION =
			new ReasonCode( C_RENEWAL_UNAUTHORISED_ON_INACTIVE_CLOSED_SUBSCRIPTION, "RENEWAL_UNAUTHORISED_ON_INACTIVE_CLOSED_SUBSCRIPTION" );	

	public final static ReasonCode PRE_ORDER_SUBSCRIPTION =
			new ReasonCode( C_PRE_ORDER_SUBSCRIPTION, "PRE-ORDER SUBSCRIPTION" );

	public final static ReasonCode INVALID_PAYMENT_TYPE =
			new ReasonCode( C_INVALID_PAYMENT_TYPE, "INVALID PAYMENT TYPE" );
	public final static ReasonCode NO_VALID_PACKAGE =
			new ReasonCode( C_NO_VALID_PACKAGE, "NO VALID PACKAGE" );
	public final static ReasonCode NO_VALID_PACKAGE_EX =
			new ReasonCode( C_NO_VALID_PACKAGE, C_NO_VALID_PACKAGE, "NO VALID PACKAGE" );
	public final static ReasonCode INSUFFICIENT_RESOURCE_BALANCE =
			new ReasonCode( C_NO_VALID_PACKAGE, C_SUBREASON_INSUFFICIENT_RESOURCE_BALANCE, "INSUFFICIENT RESOURCE BALANCE" );
	public final static ReasonCode ACTIVE_SUBSCRIPTION_NOT_FOUND =
			new ReasonCode( C_NO_VALID_PACKAGE, C_SUBREASON_ACTIVE_SUBSCRIPTION_NOT_FOUND, "ACTIVE SUBSCRIPTION NOT FOUND" );
	public final static ReasonCode BAD_ASYNC_RECORD_ID =
			new ReasonCode( C_BAD_ASYNC_RECORD_ID, "BAD ASYNC RECORD ID" );
	public final static ReasonCode SUCCESSFUL_EXPRESS_PURCHASE =
			new ReasonCode( C_SUCCESSFUL_EXPRESS_PURCHASE, C_SUCCESSFUL_EXPRESS_PURCHASE, "SUCCESSFUL EXPRESS PURCHASE" );

	//REMEDY 6856 - add sub reason codes for express purchase failure
	public final static ReasonCode SERVICE_DOES_NOT_BELONG_TO_AN_EXPRESS_PACKAGE =
			new ReasonCode( C_NO_VALID_PACKAGE, C_SUBREASON_SERVICE_DOES_NOT_BELONG_TO_AN_EXPRESS_PACKAGE, "SERVICE DOES NOT BELONG TO AN EXPRESS PACKAGE" );
	public final static ReasonCode SERVICE_BELONGS_TO_MORE_THAN_ONE_EXPRESS_PACKAGE =
			new ReasonCode( C_NO_VALID_PACKAGE, C_SUBREASON_SERVICE_BELONGS_TO_MORE_THAN_ONE_EXPRESS_PACKAGE, "SERVICE BELONGS TO MORE THAN ONE EXPRESS PACKAGE" ); 
	public final static ReasonCode NO_ACTIVE_PRICEPOINT_FOR_EXPRESS_PACKAGE =
			new ReasonCode( C_NO_VALID_PACKAGE, C_SUBREASON_NO_ACTIVE_PRICEPOINT_FOR_EXPRESS_PACKAGE, "NO ACTIVE PRICEPOINT FOR EXPRESS PACKAGE" ); 

	// since ER7
	public final static ReasonCode FAILED_SUSPENDED = 
			new ReasonCode(C_FAILED_SUSPENDED , " FAILED SUSPENDED");

	//RBT Enhancements Start
	public final static ReasonCode PARENT_IS_SUSPENDED =
			new ReasonCode( C_PARENT_IS_SUSPENDED , "PARENT_IS_SUSPENDED");
	//RBT Enhancements End


	//MQC7083
	public final static ReasonCode FAILED_INACTIVE = 
			new ReasonCode(C_FAILED_INACTIVE , " FAILED INACTIVE");

	public final static ReasonCode PARENT_IS_INACTIVE =
			new ReasonCode( C_PARENT_IS_INACTIVE , "PARENT_IS_INACTIVE");


	public final static ReasonCode FAILED_PAYMENT_FAILED = 
			new ReasonCode(C_FAILED_PAYMENT_FAILED , "PAYMENT FAILED");

	public final static ReasonCode PARENT_IS_PAYMENT_FAILED =
			new ReasonCode( C_PARENT_IS_PAYMENT_FAILED , "PARENT_IS_PAYMENT_FAILED");


	public final static ReasonCode FAILED_PROVISIONING_FAILED = 
			new ReasonCode(C_FAILED_PROVISIONING_FAILED , "PROVISIONING FAILED");

	public final static ReasonCode PARENT_IS_PROVISIONING_FAILED =
			new ReasonCode( C_PARENT_IS_PAYMENT_FAILED , "PARENT_IS_PAYMENT_FAILED");

	//MQC7083

	// Provison exception
	public final static ReasonCode SUBSCRIPTION_NOT_FOUND =
			new ReasonCode( C_SUBSCRIPTION_NOT_FOUND, "SUBSCRIPTION NOT FOUND" );
	public final static ReasonCode CUSTOMER_DOES_NOT_HAVE_SUBSCRIPTION =
			new ReasonCode( C_CUSTOMER_DOES_NOT_HAVE_SUBSCRIPTION, "CUSTOMER DOES NOT HAVE SUBSCRIPTION" );
	public final static ReasonCode SUBSCRIPTION_SERVICE_NOT_FOUND =
			new ReasonCode( C_SUBSCRIPTION_SERVICE_NOT_FOUND, "SUBSCRIPTION DOES NOT HAVE SERVICE" );
	public final static ReasonCode NEW_PROVISION_TAG_NOT_SET =
			new ReasonCode( C_NEW_PROVISION_TAG_NOT_SET, "NEW PROVISION TAG NOT SET" );
	public final static ReasonCode ORIGINAL_PROVISION_TAG_NOT_SET =
			new ReasonCode( C_ORIGINAL_PROVISION_TAG_NOT_SET, "ORIGINAL PROVISION TAG NOT SET" );

	/* CR-1759 - start*/
	public final static ReasonCode UPDATE_SERVICE_STATUS_FAILED =
			new ReasonCode( C_UPDATE_SERVICE_STATUS_FAILED, "UPDATE SERVICE STATUS FAILED");
	/* CR-1759 - end*/

	// VFEG [start]
	// 22/6/2005 - Fixed number of recurrences 
	public final static ReasonCode NO_MATCHING_NON_RECURRING_PRICEPOINT =
			new ReasonCode( C_NO_MATCHING_NON_RECURRING_PRICEPOINT, "NO MATCHING PRICEPOINT FOUND" );

	public final static ReasonCode EXPIRED_NUM_RECURRENCE =
			new ReasonCode( C_EXPIRED_NUM_RECURRENCE, "NUMBER OF RECURRENCES EXPIRED" );
	// VFEG [end]

	//REMEDY 4360
	public final static ReasonCode SUPERCREDIT_BALANCE_USEDUP =
			new ReasonCode( C_SUPERCREDIT_BALANCE_USEDUP, "Super Credit balance used up or not enough" );

	// 4/10/2005 - partial session charge
	public final static ReasonCode AMOUNT_TO_CAPTURE_TOO_HIGH =
			new ReasonCode( C_AMOUNT_TO_CAPTURE_TOO_HIGH, "AMOUNT TO CAPTURE TOO HIGH" );

	/**
        These are  not implemented
	 */
	public final static ReasonCode SUBSCRIPTION_EXPIRED =
			new ReasonCode( C_SUBSCRIPTION_EXPIRED, "SUBSCRIPTION EXPIRED" );
	public final static ReasonCode USAGE_LIMIT_EXCEEDED =
			new ReasonCode( C_USAGE_LIMIT_EXCEEDED , "USAGE LIMIT EXCEEDED");


	/**
	 * @since ER 5.1
	 */
	//MQC 8135 - updated to correct value from errorneous legacy value
	//public final static ReasonCode NON_REFUNDABLE_TRANSACTION =
	//		new ReasonCode( C_USAGE_LIMIT_EXCEEDED , "THIS TRANSACTION IS NOT REFUNDABLE - REASON SHOULD BE STATED HERE");

	public final static ReasonCode NON_REFUNDABLE_TRANSACTION =
			new ReasonCode( C_NON_REFUNDABLE_TRANSACTION , "THIS TRANSACTION IS NOT REFUNDABLE");


	public final static ReasonCode UNIT_TEST_ERROR = new ReasonCode( C_UNIT_TEST_ERROR );

	/**
	 * @version     ER8 P2
	 * @author      VFE PS Team
	 * Access Control Codes
	 */
	public final static ReasonCode ACCESS_CONTROL_DENIED = new ReasonCode(C_ACCESS_CONTROL_DENIED, "C_ACCESS_CONTROL_DENIED");
	public final static ReasonCode ACCESS_CONTROL_ERROR = new ReasonCode(C_ACCESS_CONTROL_ERROR, "C_ACCESS_CONTROL_ERROR");


	/**
	 * @hud STKHREQ13076
	 */
	public final static ReasonCode NO_ROAMING_ENABLED = new ReasonCode(C_NO_ROAMING_ENABLED, "C_NO_ROAMING_ENABLED");

	/**
	 * Added for Ring Back Tone functionality in ER9.
	 * This is used when there are only sufficient funds to purchase
	 * the parent package but not the child package.
	 */
	public static final ReasonCode RBT_PURCHASED_PARENT_PACKAGE_ONLY = new ReasonCode(C_RBT_PURCHASED_PARENT_PACKAGE_ONLY, 
			"Only sufficient funds to purchase the parent package but not the child package");

	//MQC 5485 - new reason codes for when a child is attempted to be purchased where the
	//parent package is reserved or being provisioned
	public static final ReasonCode RBT_PARENT_PACKAGE_BEING_PROVISIONED = new ReasonCode(C_RBT_PARENT_PACKAGE_BEING_PROVISIONED, 
			"child is attempted to be purchased where the parent package is in being provisioned state");
	public static final ReasonCode RBT_PARENT_PACKAGE_RESERVED = new ReasonCode(C_RBT_PARENT_PACKAGE_RESERVED, 
			"child is attempted to be purchased where the parent package is in reserved state");



	//REMEDY 6934
	public final static ReasonCode ACCOUNT_VALIDATION_FAILED = new ReasonCode(C_ACCOUNT_VALIDATION_FAILED, "Account was not validated by the external system");
	//END REMEDY 6934

	//CR-0978 Location Services
	public final static ReasonCode PURCHASE_TARIFF_FAILURE = new ReasonCode(C_PURCHASE_TARIFF_FAILURE, "PURCHASE_TARIFF_FAILURE");
	public final static ReasonCode INACTIVATE_TARIFF_FAILURE = new ReasonCode(C_INACTIVATE_TARIFF_FAILURE, "INACTIVATE_TARIFF_FAILURE");
	public final static ReasonCode PURCHASE_AND_INACTIVATE_TARIFF_FAILURE = new ReasonCode(C_PURCHASE_AND_INACTIVATE_TARIFF_FAILURE, "PURCHASE_AND_INACTIVATE_TARIFF_FAILURE");
	public final static ReasonCode INVALID_TARIFF_ACTION = new ReasonCode(C_INVALID_TARIFF_ACTION, "INVALID_TARIFF_ACTION");
	public final static ReasonCode INVALID_TARIFF = new ReasonCode(C_INVALID_TARIFF, "INVALID_TARIFF");

	// CR1209 VF-ES
	public final static ReasonCode SERVICE_BEING_PROVISIONED = new ReasonCode(C_OK, C_SUBREASON_SERVICE_BEING_PROVISIONED, "SERVICE_BEING_PROVISIONED");

	//CR-1791
	public final static ReasonCode ENHANCE_PURCHASE_INVALID_STATUS = new ReasonCode(C_ENHANCE_PURCHASE_FAILED_INVALID_STATUS, "Invalid status in enhanced purchase");
	public final static ReasonCode PURCHASE_FAILED_INVALID_START_AND_END_DATES = new ReasonCode(C_PURCHASE_FAILED_INVALID_START_AND_END_DATES, "Start date greater than End date in purchase request");
	public final static ReasonCode PURCHASE_FAILED_INVALID_END_DATE = new ReasonCode(C_PURCHASE_FAILED_INVALID_END_DATE, "End date is in the past in purchase request");
	public final static ReasonCode PURCHASE_FAILED_MISSING_PACKAGE_ID_INPUT_PARAM = new ReasonCode(C_PURCHASE_FAILED_MISSING_PACKAGE_ID_INPUT_PARAM, "Missing package-id or rating attributes short-package-id input parameter");
	public final static ReasonCode MODIFY_SUBSCRPITION_FAILED_INVALID_START_AND_END_DATES = new ReasonCode(C_MODIFY_SUBSCRPITION_FAILED_INVALID_START_AND_END_DATES, "Start date greater than End date in modify subscription request");


	public final static ReasonCode PURCHASE_FAILED_MORE_THAN_ONE_MATCHING_PRICEPOINT_EXISTS = new ReasonCode(C_PURCHASE_FAILED_MORE_THAN_ONE_MATCHING_PRICEPOINT_EXISTS, "PURCHASE_FAILED_MORE_THAN_ONE_MATCHING_PRICEPOINT_EXISTS");

	//MQC 6354
	public final static int C_ACCOUNT_LOCKED=460;
	public final static ReasonCode ACCOUNT_LOCKED = new ReasonCode(C_ACCOUNT_LOCKED, "ACCOUNT LOCKED - PROBABLY FOR INACTIVATION");

	//CR1923
	public final static ReasonCode PARTNER_TRANSACTION_VALUE_LIMIT_EXCEEDED = new ReasonCode(C_PARTNER_TRANSACTION_VALUE_LIMIT_EXCEEDED, "Partner Trading Limit for Individual Transaction Value Limit Exceeded");
	public final static ReasonCode PARTNER_CUMULATIVE_TRANSACTION_VALUE_LIMIT_EXCEEDED = new ReasonCode(C_PARTNER_CUMULATIVE_TRANSACTION_VALUE_LIMIT_EXCEEDED, "Partner Trading Limit for Cumulative Transaction Value Limit Exceeded");

	//MD google
	/**payment transaction not found for external transactionID and partnerId (500)*/
	public static final ReasonCode TXN_NOT_FOUND_FOR_EXT_TXN_ID=new ReasonCode(C_TXN_NOT_FOUND_FOR_EXT_TXN_ID, "payment transaction not found for external transactionID and partnerId");
	/** subscription not found for external subscriptionId and partnerId (501)*/
	public static final ReasonCode SUB_NOT_FOUND_FOR_EXT_SUB_ID=new ReasonCode(C_SUB_NOT_FOUND_FOR_EXT_SUB_ID, "subscription not found for external subscriptionId and partnerId");
	/**purchase failed because external transactionID and partnerId supplied already exist in ER */
	public static final ReasonCode PURCHASE_FAILED_DUPLICATE_EXT_TXN_ID=new ReasonCode(C_PURCHASE_FAILED_DUPLICATE_EXT_TXN_ID, "purchase failed because external transactionID and partnerId supplied already exist in ER");
	/**purchase failed because external subscriptionId and partnerId supplied already exist in ER*/
	public static final ReasonCode PURCHASE_FAILED_DUPLICATE_EXT_SUB_ID=new ReasonCode(C_PURCHASE_FAILED_DUPLICATE_EXT_SUB_ID, "purchase failed because external subscriptionId and partnerId supplied already exist in ER");

	 // CR2040 MPAY replacement.  Credit limit error codes.
    public final static int C_GOODWILL_CREDIT_LIMIT_EXCEEDED = 600;  // top level error code for credit limits exceeded errors
    
    public final static int C_AGGREGATOR_TRANSACTION_CREDIT_LIMIT_EXCEEDED = 601;  // aggregator per transaction credit limit exceeded
    public final static int C_SERVICEPROVIDER_TRANSACTION_CREDIT_LIMIT_EXCEEDED = 602; // service provider per transaction credit limit exceeded
    public final static int C_OVERALL_TRANSACTION_CREDIT_LIMIT_EXCEEDED = 603; // overall per transaction limit exceeded
    public final static int C_AGGREGATOR_DAILY_CREDIT_LIMIT_EXCEEDED = 604; // aggregator daily credit limit exceeded
    public final static int C_SERVICEPROVIDER_DAILY_CREDIT_LIMIT_EXCEEDED = 605; // service provider daily credit limit exceeded
    public final static int C_OVERALL_DAILY_CREDIT_LIMIT_EXCEEDED = 606; // overall daily credit limit exceeded
    public final static int C_AGGREGATOR_MONTHLY_CREDIT_LIMIT_EXCEEDED = 607; // aggregator monthly credit limit exceeded
    public final static int C_SERVICEPROVIDER_MONTHLY_CREDIT_LIMIT_EXCEEDED = 608; // service provider monthly credit limit exceeded
    public final static int C_OVERALL_MONTHLY_CREDIT_LIMIT_EXCEEDED = 609; // overall monthly credit limit limit exceeded
    
    public final static ReasonCode AGGREGATOR_TRANSACTION_CREDIT_LIMIT_EXCEEDED = 
    		new ReasonCode(C_GOODWILL_CREDIT_LIMIT_EXCEEDED, 
    				C_AGGREGATOR_TRANSACTION_CREDIT_LIMIT_EXCEEDED,
    				"AGGREGATOR_TRANSACTION_CREDIT_LIMIT_EXCEEDED");
    
    public final static ReasonCode SERVICEPROVIDER_TRANSACTION_CREDIT_LIMIT_EXCEEDED = 
    		new ReasonCode(C_GOODWILL_CREDIT_LIMIT_EXCEEDED, 
    				C_SERVICEPROVIDER_TRANSACTION_CREDIT_LIMIT_EXCEEDED,
    				"SERVICEPROVIDER_TRANSACTION_CREDIT_LIMIT_EXCEEDED");
    
    public final static ReasonCode OVERALL_TRANSACTION_CREDIT_LIMIT_EXCEEDED = 
    		new ReasonCode(C_GOODWILL_CREDIT_LIMIT_EXCEEDED, 
    				C_OVERALL_TRANSACTION_CREDIT_LIMIT_EXCEEDED,
    				"OVERALL_TRANSACTION_CREDIT_LIMIT_EXCEEDED");
    
    public final static ReasonCode AGGREGATOR_DAILY_CREDIT_LIMIT_EXCEEDED = 
    		new ReasonCode(C_GOODWILL_CREDIT_LIMIT_EXCEEDED, 
    				C_AGGREGATOR_DAILY_CREDIT_LIMIT_EXCEEDED,
    				"AGGREGATOR_DAILY_CREDIT_LIMIT_EXCEEDED");
    
    public final static ReasonCode SERVICEPROVIDER_DAILY_CREDIT_LIMIT_EXCEEDED = 
    		new ReasonCode(C_GOODWILL_CREDIT_LIMIT_EXCEEDED, 
    				C_SERVICEPROVIDER_DAILY_CREDIT_LIMIT_EXCEEDED,
    				"SERVICEPROVIDER_DAILY_CREDIT_LIMIT_EXCEEDED");
    
    public final static ReasonCode OVERALL_DAILY_CREDIT_LIMIT_EXCEEDED = 
    		new ReasonCode(C_GOODWILL_CREDIT_LIMIT_EXCEEDED, 
    				C_OVERALL_DAILY_CREDIT_LIMIT_EXCEEDED,
    				"OVERALL_DAILY_CREDIT_LIMIT_EXCEEDED");
    
    public final static ReasonCode AGGREGATOR_MONTHLY_CREDIT_LIMIT_EXCEEDED = 
    		new ReasonCode(C_GOODWILL_CREDIT_LIMIT_EXCEEDED, 
    				C_AGGREGATOR_MONTHLY_CREDIT_LIMIT_EXCEEDED,
    				"AGGREGATOR_MONTHLY_CREDIT_LIMIT_EXCEEDED");
    
    public final static ReasonCode SERVICEPROVIDER_MONTHLY_CREDIT_LIMIT_EXCEEDED = 
    		new ReasonCode(C_GOODWILL_CREDIT_LIMIT_EXCEEDED, 
    				C_SERVICEPROVIDER_MONTHLY_CREDIT_LIMIT_EXCEEDED,
    				"SERVICEPROVIDER_MONTHLY_CREDIT_LIMIT_EXCEEDED");
    
    public final static ReasonCode OVERALL_MONTHLY_CREDIT_LIMIT_EXCEEDED = 
    		new ReasonCode(C_GOODWILL_CREDIT_LIMIT_EXCEEDED, 
    				C_OVERALL_MONTHLY_CREDIT_LIMIT_EXCEEDED,
    				"OVERALL_MONTHLY_CREDIT_LIMIT_EXCEEDED");
    
    // CR2040 MPAY replacement. Spend limit error codes.
    public final static int C_SPEND_LIMIT_EXCEEDED = 610;  // top level error code for spend limits exceeded errors
    
    public final static int C_AGGREGATOR_TRANSACTION_SPEND_LIMIT_EXCEEDED = 611;
    public final static int C_SERVICEPROVIDER_TRANSACTION_SPEND_LIMIT_EXCEEDED = 612;
    public final static int C_MSISDN_TRANSACTION_SPEND_LIMIT_EXCEEDED = 613;
    public final static int C_AGGREGATOR_DAILY_SPEND_LIMIT_EXCEEDED = 614;
    public final static int C_SERVICEPROVIDER_DAILY_SPEND_LIMIT_EXCEEDED = 615;
    public final static int C_MSISDN_DAILY_SPEND_LIMIT_EXCEEDED = 616;
    public final static int C_AGGREGATOR_MONTHLY_SPEND_LIMIT_EXCEEDED = 617;
    public final static int C_SERVICEPROVIDER_MONTHLY_SPEND_LIMIT_EXCEEDED = 618;
    public final static int C_MSISDN_MONTHLY_SPEND_LIMIT_EXCEEDED = 619;
    
    public final static ReasonCode AGGREGATOR_TRANSACTION_SPEND_LIMIT_EXCEEDED = 
    		new ReasonCode(C_SPEND_LIMIT_EXCEEDED, 
    				C_AGGREGATOR_TRANSACTION_SPEND_LIMIT_EXCEEDED,
    				"AGGREGATOR_TRANSACTION_SPEND_LIMIT_EXCEEDED");
    
    public final static ReasonCode SERVICEPROVIDER_TRANSACTION_SPEND_LIMIT_EXCEEDED = 
    		new ReasonCode(C_SPEND_LIMIT_EXCEEDED, 
    				C_SERVICEPROVIDER_TRANSACTION_SPEND_LIMIT_EXCEEDED,
    				"SERVICEPROVIDER_TRANSACTION_SPEND_LIMIT_EXCEEDED");
    
    public final static ReasonCode MSISDN_TRANSACTION_SPEND_LIMIT_EXCEEDED = 
    		new ReasonCode(C_SPEND_LIMIT_EXCEEDED, 
    				C_MSISDN_TRANSACTION_SPEND_LIMIT_EXCEEDED,
    				"MSISDN_TRANSACTION_SPEND_LIMIT_EXCEEDED");
    
    public final static ReasonCode AGGREGATOR_DAILY_SPEND_LIMIT_EXCEEDED = 
    		new ReasonCode(C_SPEND_LIMIT_EXCEEDED, 
    				C_AGGREGATOR_DAILY_SPEND_LIMIT_EXCEEDED,
    				"AGGREGATOR_DAILY_SPEND_LIMIT_EXCEEDED");
    
    public final static ReasonCode SERVICEPROVIDER_DAILY_SPEND_LIMIT_EXCEEDED = 
    		new ReasonCode(C_SPEND_LIMIT_EXCEEDED, 
    				C_SERVICEPROVIDER_DAILY_SPEND_LIMIT_EXCEEDED,
    				"SERVICEPROVIDER_DAILY_SPEND_LIMIT_EXCEEDED");
    
    public final static ReasonCode MSISDN_DAILY_SPEND_LIMIT_EXCEEDED = 
    		new ReasonCode(C_SPEND_LIMIT_EXCEEDED, 
    				C_MSISDN_DAILY_SPEND_LIMIT_EXCEEDED,
    				"MSISDN_DAILY_SPEND_LIMIT_EXCEEDED");
    
    public final static ReasonCode AGGREGATOR_MONTHLY_SPEND_LIMIT_EXCEEDED = 
    		new ReasonCode(C_SPEND_LIMIT_EXCEEDED, 
    				C_AGGREGATOR_MONTHLY_SPEND_LIMIT_EXCEEDED,
    				"AGGREGATOR_MONTHLY_SPEND_LIMIT_EXCEEDED");
    
    public final static ReasonCode SERVICEPROVIDER_MONTHLY_SPEND_LIMIT_EXCEEDED = 
    		new ReasonCode(C_SPEND_LIMIT_EXCEEDED, 
    				C_SERVICEPROVIDER_MONTHLY_SPEND_LIMIT_EXCEEDED,
    				"SERVICEPROVIDER_MONTHLY_SPEND_LIMIT_EXCEEDED");
    
    public final static ReasonCode MSISDN_MONTHLY_SPEND_LIMIT_EXCEEDED = 
    		new ReasonCode(C_SPEND_LIMIT_EXCEEDED, 
    				C_MSISDN_MONTHLY_SPEND_LIMIT_EXCEEDED,
    				"MSISDN_MONTHLY_SPEND_LIMIT_EXCEEDED");
    
    // CR2040 MPAY replacement.  Additional non credit limits exceeded top level goodwill credit error code.
    public final static int C_GOODWILL_CREDITING_FAILED = 620;
    
    // CR2040 MPAY replacement.  Goodwill credit "payment" failures in ER-IF
    public final static int C_GOODWILL_CREDIT_PAYMENT_REJECTED = 621;
    public final static int C_GOODWILL_CREDIT_PAYMENT_DENIED = 622;
    public final static int C_GOODWILL_CREDIT_PAYMENT_ERROR = 623;
     
    // CR2040 MPAY replacement.  Goodwill credit payment rejected response from ER-IF
    public final static ReasonCode GOODWILL_CREDIT_PAYMENT_REJECTED = 
    		new ReasonCode(C_GOODWILL_CREDITING_FAILED, 
    				C_GOODWILL_CREDIT_PAYMENT_REJECTED,
    				"GOODWILL_CREDIT_PAYMENT_REJECTED");
    
    // CR2040 MPAY replacement.  Goodwill credit payment denied response from ER-IF
    public final static ReasonCode GOODWILL_CREDIT_PAYMENT_DENIED = 
    		new ReasonCode(C_GOODWILL_CREDITING_FAILED, 
    				C_GOODWILL_CREDIT_PAYMENT_DENIED,
    				"GOODWILL_CREDIT_PAYMENT_DENIED");
    
    // CR2040 MPAY replacement.  Goodwill credit payment error response from ER-IF
    public final static ReasonCode GOODWILL_CREDIT_PAYMENT_ERROR = 
    		new ReasonCode(C_GOODWILL_CREDITING_FAILED, 
    				C_GOODWILL_CREDIT_PAYMENT_ERROR,
    				"GOODWILL_CREDIT_PAYMENT_ERROR");
    
    // CR2040 MPAY replacement.  Miscellaneous goodwill credit error codes.
    public final static int C_GOODWILL_CREDIT_NOT_A_GOODWILL_CREDIT_PACKAGE = 625;  // package isn't a goodwill credit package
    public final static int C_GOODWILL_CREDIT_PARTNER_ID_NOT_RECOGNISED = 626; // partner id was not recognised (not in the catalog)
    public final static int C_GOODWILL_CREDIT_LIMITS_NOT_SET = 627; // credit limits not set for a given partner (this is not allowed)
    
    public final static ReasonCode GOODWILL_CREDIT_NOT_A_GOODWILL_CREDIT_PACKAGE = 
    		new ReasonCode(C_GOODWILL_CREDITING_FAILED, 
    				C_GOODWILL_CREDIT_NOT_A_GOODWILL_CREDIT_PACKAGE,
    				"NOT_A_GOODWILL_CREDIT_PACKAGE");
    
    public final static ReasonCode GOODWILL_CREDIT_PARTNER_ID_NOT_RECOGNISED = 
    		new ReasonCode(C_GOODWILL_CREDITING_FAILED, 
    				C_GOODWILL_CREDIT_PARTNER_ID_NOT_RECOGNISED,
    				"PARTNER_ID_NOT_RECOGNISED");
    
    public final static ReasonCode GOODWILL_CREDIT_LIMITS_NOT_SET = 
    		new ReasonCode(C_GOODWILL_CREDITING_FAILED, 
    				C_GOODWILL_CREDIT_LIMITS_NOT_SET,
    				"GOODWILL_CREDIT_LIMITS_NOT_SET");
    // // CR2040 MPAY replacement. End.
    
    // CR2203 MPAY replacement.  Goodwill credit enable/disable.
    public final static int C_GOODWILL_CREDIT_NOT_ENABLED = 628;
	public static final ReasonCode GOODWILL_CREDIT_NOT_ENABLED = 
			new ReasonCode(C_GOODWILL_CREDIT_NOT_ENABLED, "GOODWILL_CREDIT_NOT_ENABLED");
    
	//CR - Add Invoice Text to goodwill credit request, general error when goodwill credit attributes not set in request
	public final static int C_GOODWILL_CREDIT_ATTRIBUTES_NOT_SET = 629;
	public static final ReasonCode GOODWILL_CREDIT_ATTRIBUTES_NOT_SET = 
			new ReasonCode(C_GOODWILL_CREDIT_ATTRIBUTES_NOT_SET, "GOODWILL_CREDIT_ATTRIBUTES_NOT_SET");
	
    // CR2203 MPAY replacement.  Partner spend limits.
    public final static int C_PARTNER_SPEND_LIMITS_NOT_SET = 630;
    public final static ReasonCode PARTNER_SPEND_LIMITS_NOT_SET = 
    		new ReasonCode(C_PARTNER_SPEND_LIMITS_NOT_SET, "PARTNER_SPEND_LIMITS_NOT_SET");
    
    // CR2203 MPAY replacement.  MSISDN spend limits.
    public final static int C_MSISDN_SPEND_LIMITS_NOT_SET = 631;
    public final static ReasonCode MSISDN_SPEND_LIMITS_NOT_SET = 
    		new ReasonCode(C_MSISDN_SPEND_LIMITS_NOT_SET, "MSISDN_SPEND_LIMITS_NOT_SET");

    //MQC 8032 - MSISDN spend limits is not enabled
    public final static int C_MSISDN_SPEND_LIMITS_NOT_ENABLED = 632;
    public final static ReasonCode MSISDN_SPEND_LIMITS_NOT_ENABLED = 
    		new ReasonCode(C_MSISDN_SPEND_LIMITS_NOT_ENABLED, "MSISDN_SPEND_LIMITS_NOT_ENABLED");
    
    //MQC 8284
    public final static ReasonCode RENEWAL_FAILED_NEW_USERGROUP_PRICEPOINT_RATE_GREATER = new ReasonCode(C_RENEWAL_FAILED_NEW_USERGROUP_PRICEPOINT_RATE_GREATER, "Renewal usergroup pricepoint rate greater than current subscription rate, subscription inactivated");
    public final static ReasonCode RENEWAL_FAILED_SUBSCRIPTION_DEFAULT_USERGROUP_PRICEPOINT_MISMATCH = new ReasonCode(C_RENEWAL_FAILED_SUBSCRIPTION_DEFAULT_USERGROUP_PRICEPOINT_MISMATCH, "Package contains active usergroup pricepoint which the user belongs but has no renewal pricepoint, subscription inactivated");
	
    //JIRA ET196 Inactivate subscription promo-code
	public final static ReasonCode INACTIVATE_SUB_PROMO_FAILED_MISSING_PARAM = new ReasonCode(C_INACTIVATE_SUB_PROMO_FAILED_MISSING_PARAM, "Inactivate Subscription Promo Code request missing subscriptionId or packageId");
	public final static ReasonCode INACTIVATE_SUB_PROMO_FAILED_MSISDN_SUBID_PACKAGEID_MISMATCH = new ReasonCode(C_INACTIVATE_SUB_PROMO_FAILED_MSISDN_SUBID_PACKAGEID_MISMATCH, "Inactivate Subscription Promo Code request msisdn, subscriptionId or packageId mismatch");
	public final static ReasonCode INACTIVATE_SUB_PROMO_FAILED_ACTIVE_SUBSCRIPTION = new ReasonCode(C_INACTIVATE_SUB_PROMO_FAILED_ACTIVE_SUBSCRIPTION, "Inactivate Subscription Promo Code request on an active subscription");
	public final static ReasonCode INACTIVATE_SUB_PROMO_FAILED_NON_EXISTENT_SUBSCRIPTION_PROMO = new ReasonCode(C_INACTIVATE_SUB_PROMO_FAILED_NON_EXISTENT_SUBSCRIPTION_PROMO, "Inactivate Subscription Promo Code request on an non-existent subscription promo code");
	public final static ReasonCode INACTIVATE_SUB_PROMO_FAILED_SUBSCRIPTION_PROMO_ALREADY_INACTIVATED = new ReasonCode(C_INACTIVATE_SUB_PROMO_FAILED_SUBSCRIPTION_PROMO_ALREADY_INACTIVATED, "Inactivate Subscription Promo Code request on an already inactivated subscription promo code");
	
    /*
	 * INSTANCE VARIABLES.
	 */

	int code;
	int subCode = C_SUBREASON_UNKNOWN;
	String name;

	/*
	 * INSTANCE METHODS.
	 */

	/**
	 * For internal use only.
	 */
	private ReasonCode( int code ) {
		this( code, null );

	}

	/**
	 * For internal use only.
	 */
	private ReasonCode(int code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * For internal use only.
	 */
	private ReasonCode(int code, int subCode) {
		this.code = code;
		this.subCode = subCode;
	}

	/**
	 * For internal use only.
	 */
	private ReasonCode(int code, int subCode, String name) {
		this.code = code;
		this.subCode = subCode;
		this.name = name;
	}

	/**this field is not really the name, it's the description - ie can contain spaces and lower case characters.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 */
	public void setName(String s) {
		this.name = s;
	}

	/**
	 * The id of this ReasonCode
	 * @return an integer
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * The id of this ReasonCode
	 */
	private void setCode(int c) {
		this.code = c;
	}

	/**
	 * @since ER 6
	 */
	public int getSubCode() {
		return this.subCode;
	}

	/**
	 * @since ER 6
	 */
	private void setSubCode(int c) {
		this.subCode = c;
	}

	public String getResourceName()
	{
		return "ReasonCode_" + getCode();
	}


	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer("{");
		sb.append(  "code=" ).append(  code );
		sb.append(  " subCode=" ).append(  subCode );
		sb.append(  " name=" ).append(  name );
		sb.append(  "}" );
		return sb.toString();
	}

	/**
	 * Two codes will be equal if:<br/>
	 *		- The code and subCode of both matches<br/>
	 *		- The code of both matches and subCode of any is C_SUBREASON_UNKNOWN
	 * @param code
	 * @return
	 */
	public boolean equals(ReasonCode code)
	{
		boolean rv = false;
		/*
			Add check for sub-reason.
			Two codes will be equal if:
			- The code and subCode of both matches
			- The code of both matches and subCode of any is C_SUBREASON_UNKNOWN
		 */
		if (getCode() == code.getCode() && getSubCode() == code.getSubCode()) {
			rv = true;
		}
		else if (getCode() == code.getCode() && (getSubCode()==C_SUBREASON_UNKNOWN || code.getSubCode()==C_SUBREASON_UNKNOWN) ) {
			rv = true;
		}

		return rv;
	}
	/**
	 * returns the ReasonCode object for the int code supplied.<br/>  <b>NB can give the wrong result sometimes, since there are more than one ReasonCode with the same code</b> - e.g. OK (0) and SERVICE_BEING_PROVISIONED (0)<br/>
	 * @param code eg  52
	 * @return the ReasonCode object for that code eg ReasonCode.CREDIT_REFUND_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION
	 * @throws NullPointerException if the int supplied doesn't match anything in {@link ReturnCode#_codesMapbyReasonCodeId()} or ReasonCodes.reasonCodes
	 * @see getReasonCode(int, int) for a more reliable method of getting reason code
	 * @deprecated use getReasonCode(int, int) instead
	 */
	@Deprecated
	public static ReasonCode getReasonCode(int code)	{
		ReturnCode tmp= ReturnCode._codesMapbyReasonCodeId.get(code);
		if (tmp != null)
			return tmp.getReasonCode();

		for (ReasonCode reaCode: ReasonCodes.reasonCodes)	{
			if (reaCode.getCode() == code)
				return reaCode;
		}
		//if we reach this point, the reason code doesn't exist

		throw new NullPointerException("no ReasonCode found for code Id "+code);
	}

	/**
	 * MQC8068 - returns the ReasonCode object for the int code and name supplied.
	 * @param code eg  52
	 * @param name eg  Credit refund attempted against an inactive or closed subscription
	 * @return the ReasonCode object for that code and name eg ReasonCode.CREDIT_REFUND_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION
	 * @throws NullPointerException if there is no ReasonCode which matches the combination of code and name supplied
	 */
	public static ReasonCode getReasonCode(int code, String name)	{
		
		ReasonCode tmp= getReasonCode(code);
		if (StringUtils.containsIgnoreCase(name, tmp.getName()))
			return tmp;
		//MQC9403 - if getCode() matches but not name, loop through all in reasonCodes to find the one which matches code and name
		for (ReasonCode reaCode: ReasonCodes.reasonCodes)	{
			if (reaCode.getCode() == code && name != null && reaCode.getName() != null & reaCode.getName().equals(name))
				return reaCode;
		}
		//if we reach this point, the reason code doesn't exist

		throw new NullPointerException("no ReasonCode found for code Id "+code + " and name " +name);
	}
	
	/**
	 * get a ReasonCode based on the reason code, name and sub code supplied.
	 * @param code
	 * @param subcode
	 * @param name the exact string of the reason code
	 * @return
	 * @throws NullPointerException if there is no ReasonCode which matches the combination of code, name and subcode supplied
	 */
	public static ReasonCode getReasonCode(int code, int subcode, String name) {
		ReasonCode tmp= getReasonCode(code);
		if (StringUtils.containsIgnoreCase(name, tmp.getName()) && subcode== tmp.getSubCode())
			return tmp;	//we've matched on code, name and subcode from one stored in the ReturnCode._codesMapbyReasonCodeId Map
		
		//next we should try matching on code, subcode and name from local copies
		for (ReasonCode reaCode: ReasonCodes.reasonCodes)	{
			if (reaCode.getCode() == code && name != null && reaCode.getName() != null & reaCode.getName().equals(name) && reaCode.getSubCode()==subcode)
				return reaCode;
		}
		//if we reach this point, the reason code doesn't exist

		throw new NullPointerException("no ReasonCode found for code Id "+code + ", name [" +name+"], and subCode "+subcode);
	}
	
	/**
	 * get a ReasonCode based on the reason code and sub code supplied.
	 * @param code
	 * @param subcode
	 * @return
	 * @throws NullPointerException if there is no ReasonCode which matches the combination of code and subcode supplied
	 */
	public static ReasonCode getReasonCode(int code, int subcode) {
		ReasonCode tmp= getReasonCode(code);
		if (subcode== tmp.getSubCode())
			return tmp;	//we've matched on code and subcode from one stored in the ReturnCode._codesMapbyReasonCodeId Map
		
		//next we should try matching on code and subcode from local copies
		for (ReasonCode reaCode: ReasonCodes.reasonCodes)	{
			if (reaCode.getCode() == code && reaCode.getSubCode()==subcode)
				return reaCode;
		}
		//if we reach this point, the reason code doesn't exist

		throw new NullPointerException("no ReasonCode found for code Id "+code + " and subCode "+subcode);
	}
	
	
//	public static ReasonCode getReasonCode(int code, int subcode, String name) {
//		//this method works, but it makes it possible for a client to create any arbitrary ReasonCode and thereby removes all encapsulation of this class
//		ReasonCode reasonCode = new ReasonCode(code, subcode, name);
//		return reasonCode;
//	}
	

	
	@Override
	public boolean equals(Object obj)	{
		if (obj instanceof ReasonCode)	{
			ReasonCode other = (ReasonCode) obj;
			return (other.getCode()==getCode()	&& other.getSubCode() == getSubCode());
		}
		return false;
	}
	
	@Override
	public int hashCode()	{	
		//for completeness, since we've over-ridden equals
		return new Integer(getCode()).hashCode() * new Integer(getSubCode()).hashCode();
	}
}
