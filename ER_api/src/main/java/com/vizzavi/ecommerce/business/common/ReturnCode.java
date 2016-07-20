package com.vizzavi.ecommerce.business.common;

import java.util.HashMap;


/**
 * Wraps a {@link ReasonCode} with the database ID of the return code (for foreign key mappings).
 * <br/>
 * For example, ReasonCode.REFUND_EXCEED_MAX_CASH has a code of 51, but in the DB the primary key value is 11.<br/>
 * When creating a new ReturnCode, please add it to the the ER_RETURN_CODES table and notify the GIG team so they can handle it<br/>
 * @author matt
 * @see ReasonCode
 *
 */
public enum ReturnCode {
	/** objId 1, ReasonCode 0 */
	OK(1,ReasonCode.OK),
	/** objId 2, ReasonCode 1 */
	SYSTEM_ERROR(2,ReasonCode.SYSTEM_ERROR),
	/** objId 3 Reason Code 11 PAYMENT_PENDING*/
	PAYMENT_PENDING (3, ReasonCode.PAYMENT_PENDING ),
	/** objId 4 Reason Code 12 RENEWAL NEEDED*/
	PACKAGE_RENEWAL (4, ReasonCode.PACKAGE_RENEWAL ),
	/** objId 5 Reason Code 21 PAYMENT FAILED*/
	PAYMENT_FAILED (5, ReasonCode.PAYMENT_FAILED ),
	/** objId 6 Reason Code 22 DUPLICATE PROMO CODE*/
	DUPLICATE_PROMO_CODE (6, ReasonCode.DUPLICATE_PROMO_CODE ),
	/** objId 7 Reason Code 23 ACCOUNT NOT FOUND*/
	ACCOUNT_NOT_FOUND (7, ReasonCode.ACCOUNT_NOT_FOUND ),
	/** objId 8 Reason Code 24 INVALID RENEWAL*/
	INVALID_RENEWAL (8, ReasonCode.INVALID_RENEWAL ),
	/** objId 9 Reason Code 25 (null)*/
	NO_TOKENS (9, ReasonCode.NO_TOKENS ),
	/** objId 10 Reason Code 26 (null)*/
	EXCEED_TOKEN_MAXIMUM (10, ReasonCode.EXCEED_TOKEN_MAXIMUM ),
	/** objId 11 Reason Code 51 The refund price is greater than the original payment*/
	REFUND_EXCEED_MAX_CASH (11, ReasonCode.REFUND_EXCEED_MAX_CASH ),
	/** objId 12 Reason Code 31 PACKAGE ID NOT FOUND*/
	PACKAGE_ID_NOT_FOUND (12, ReasonCode.PACKAGE_ID_NOT_FOUND ),
	/** objId 13 Reason Code 32 SERVICE ID NOT FOUND*/
	SERVICE_ID_NOT_FOUND (13, ReasonCode.SERVICE_ID_NOT_FOUND ),
	/** objId 14 Reason Code 34 PRICEPOINT ID NOT SET IN PACKAGE*/
	PRICEPOINT_ID_NOT_SET (14, ReasonCode.PRICEPOINT_ID_NOT_SET ),
	/** objId 15 Reason Code 35 PRICEPOINT IS NOT ACTIVE*/
	PRICEPOINT_NOT_ACTIVE (15, ReasonCode.PRICEPOINT_NOT_ACTIVE ),
	/** objId 16 Reason Code 27 RENEWAL UNAUTHORISED ON UNPROVISIONED PACKAGE*/
	RENEWAL_UNAUTHORISED_ON_UNPROVISIONED_PACKAGE (16, ReasonCode.RENEWAL_UNAUTHORISED_ON_UNPROVISIONED_PACKAGE ),
	/** objId 17 Reason Code 33 INVALID PAYMENT TYPE*/
	INVALID_PAYMENT_TYPE (17, ReasonCode.INVALID_PAYMENT_TYPE ),
	/** objId 18 Reason Code 41 NO VALID PACKAGE*/
	NO_VALID_PACKAGE (18, ReasonCode.NO_VALID_PACKAGE),
	/** objId 19 Reason Code 42 BAD ASYNC RECORD ID*/
	BAD_ASYNC_RECORD_ID (19, ReasonCode.BAD_ASYNC_RECORD_ID ),
	/** objId 20 Reason Code 102 SUBSCRIPTION NOT FOUND*/
	SUBSCRIPTION_NOT_FOUND (20, ReasonCode.SUBSCRIPTION_NOT_FOUND ),
	/** objId 21 Reason Code 61 CUSTOMER DOES NOT HAVE SUBSCRIPTION*/
	CUSTOMER_DOES_NOT_HAVE_SUBSCRIPTION (21, ReasonCode.CUSTOMER_DOES_NOT_HAVE_SUBSCRIPTION ),
	/** objId 22 Reason Code 62 SUBSCRIPTION DOES NOT HAVE SERVICE*/
	SUBSCRIPTION_SERVICE_NOT_FOUND (22, ReasonCode.SUBSCRIPTION_SERVICE_NOT_FOUND ),
	/** objId 23 Reason Code 63 NEW PROVISION TAG NOT SET*/
	ORIGINAL_PROVISION_TAG_NOT_SET (23, ReasonCode.ORIGINAL_PROVISION_TAG_NOT_SET),
	/** objId 24 Reason Code 63 ORIGINAL PROVISION TAG NOT SET*/
	NEW_PROVISION_TAG_NOT_SET (24, ReasonCode.NEW_PROVISION_TAG_NOT_SET ),

	/**	25      1001    SUBSCRIPTION API RETURNED FALSE FOR INACTIVATE SUBSCRIPTION*/
	SUBSCRIPTION_API_FAILED_INACTIVATE(25,ReasonCode.SUBSCRIPTION_API_FAILED_INACTIVATE),
	/**26      1002    PURCHASE API RETURNED AN EXCEPTION*/
	PURCHASE_API_EXCEPTION(26,ReasonCode.PURCHASE_API_EXCEPTION),
	/**		27      1003    SUBSCRIPTION API RETURNED AN EXCEPTION*/
	SUBSCRIPTION_API_EXCEPTION(27,ReasonCode.SUBSCRIPTION_API_EXCEPTION),
	/**		28      1004    PROVISIONING API RETURNED AN EXCEPTION*/
	PROVISIONING_API_EXCEPTION (28,ReasonCode.PROVISIONING_API_EXCEPTION),

	/** objId 29 Reason Code 3000 Account was not validated by the external system*/
	ACCOUNT_VALIDATION_FAILED (29, ReasonCode.ACCOUNT_VALIDATION_FAILED ),
	/** objId 30 Reason Code 28 RENEWAL UNAUTHORISED ON EVENT PACKAGE*/
	RENEWAL_UNAUTHORISED_ON_EVENT_PACKAGE (30, ReasonCode.RENEWAL_UNAUTHORISED_ON_EVENT_PACKAGE ),
	/** objId 31 Reason Code 29 RENEWAL_UNAUTHORISED_ON_INACTIVE_CLOSED_SUBSCRIPTION*/
	RENEWAL_UNAUTHORISED_ON_INACTIVE_CLOSED_SUBSCRIPTION (31, ReasonCode.RENEWAL_UNAUTHORISED_ON_INACTIVE_CLOSED_SUBSCRIPTION ),
	/** objId 32 Reason Code 36 PRE-ORDER SUBSCRIPTION*/
	PRE_ORDER_SUBSCRIPTION (32, ReasonCode.PRE_ORDER_SUBSCRIPTION ),
	/** objId 33 Reason Code 37 NO MATCHING PRICEPOINT FOUND*/
	NO_MATCHING_NON_RECURRING_PRICEPOINT (33, ReasonCode.NO_MATCHING_NON_RECURRING_PRICEPOINT ),
	/** objId 34 Reason Code 38 NUMBER OF RECURRENCES EXPIRED*/
	EXPIRED_NUM_RECURRENCE (34, ReasonCode.EXPIRED_NUM_RECURRENCE ),
	/** objId 35 Reason Code 39 AMOUNT TO CAPTURE TOO HIGH*/
	AMOUNT_TO_CAPTURE_TOO_HIGH (35, ReasonCode.AMOUNT_TO_CAPTURE_TOO_HIGH ),
	/** objId 36 Reason Code 39 Super Credit balance used up or not enough*/
	SUPERCREDIT_BALANCE_USEDUP (36, ReasonCode.SUPERCREDIT_BALANCE_USEDUP),
	/** objId 37 Reason Code 52 Credit refund attempted against an inactive or closed subscription*/
	CREDIT_REFUND_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION (37, ReasonCode.CREDIT_REFUND_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION ),
	/** objId 38 Reason Code 53 Refund attempted against a payment transaction older than the authorised period*/
	REFUND_PAYMENT_TRANSACTION_EXCEEDS_AUTH_MONTHS_PERIOD (38, ReasonCode.REFUND_PAYMENT_TRANSACTION_EXCEEDS_AUTH_MONTHS_PERIOD ),
	/** objId 39 Reason Code 64 RESERVED_TRANSACTION_NOT_FOUND*/
	RESERVED_TRANSACTION_NOT_FOUND (39, ReasonCode.RESERVED_TRANSACTION_NOT_FOUND ),
	/** objId 40 Reason Code 71 C_ACCESS_CONTROL_DENIED*/
	ACCESS_CONTROL_DENIED (40, ReasonCode.ACCESS_CONTROL_DENIED ),
	/** objId 41 Reason Code 72 C_ACCESS_CONTROL_ERROR*/
	ACCESS_CONTROL_ERROR (41, ReasonCode.ACCESS_CONTROL_ERROR ),
	/** objId 42 Reason Code 80 C_NO_ROAMING_ENABLED*/
	NO_ROAMING_ENABLED (42, ReasonCode.NO_ROAMING_ENABLED ),
	/** objId 43 Reason Code 101 SUBSCRIPTION EXPIRED*/
	SUBSCRIPTION_EXPIRED (43, ReasonCode.SUBSCRIPTION_EXPIRED ),
	/** objId 44 Reason Code 103 USAGE LIMIT EXCEEDED*/
	USAGE_LIMIT_EXCEEDED (44, ReasonCode.USAGE_LIMIT_EXCEEDED),
	/** objId 45 Reason Code 1000 */
	UNIT_TEST_ERROR(45, ReasonCode.UNIT_TEST_ERROR),
	/** objId 46 ReasonCode 41 sub reason code -1 INSUFFICIENT RESOURCE BALANCE*/
	INSUFFICIENT_RESOURCE_BALANCE(46  ,   ReasonCode.INSUFFICIENT_RESOURCE_BALANCE),
	/** objId 47 ReasonCode 41 sub reason code -2 ACTIVE SUBSCRIPTION NOT FOUND*/
	ACTIVE_SUBSCRIPTION_NOT_FOUND(47 ,    ReasonCode.ACTIVE_SUBSCRIPTION_NOT_FOUND),
	/** objId 48 ReasonCode -3 sub reason code -3 SUCCESSFUL EXPRESS PURCHASE*/
	SUCCESSFUL_EXPRESS_PURCHASE (48, ReasonCode.SUCCESSFUL_EXPRESS_PURCHASE),

	FAILED_SUSPENDED (49, ReasonCode.FAILED_SUSPENDED ),
	/** objId 50 ReasonCode 41 sub reason code -4 SERVICE DOES NOT BELONG TO AN EXPRESS PACKAGE*/
	SERVICE_DOES_NOT_BELONG_TO_AN_EXPRESS_PACKAGE(50,  ReasonCode.SERVICE_DOES_NOT_BELONG_TO_AN_EXPRESS_PACKAGE),
	/** objId 51 ReasonCode 41 sub reason code -5 SERVICE BELONGS TO MORE THAN ONE EXPRESS PACKAGE*/
	SERVICE_BELONGS_TO_MORE_THAN_ONE_EXPRESS_PACKAGE(51,  ReasonCode.SERVICE_BELONGS_TO_MORE_THAN_ONE_EXPRESS_PACKAGE),
	/** objId 52 ReasonCode 41 sub reason code -6  NO ACTIVE PRICEPOINT FOR EXPRESS PACKAGE*/
	NO_ACTIVE_PRICEPOINT_FOR_EXPRESS_PACKAGE(52,  ReasonCode.NO_ACTIVE_PRICEPOINT_FOR_EXPRESS_PACKAGE),
	/** objId 53 Reason Code -7 -7 PARENT_IS_SUSPENDED*/
	PARENT_IS_SUSPENDED (53, ReasonCode.PARENT_IS_SUSPENDED ),
	/** objId 54 Reason Code 300 INVALID UNIQUE PROMO CODE*/
	INVALID_UNIQUE_PROMOCODE (54, ReasonCode.INVALID_UNIQUE_PROMOCODE ),
	/** objId 55 Reason Code 301 Only sufficient funds to purchase the parent package but not the child package*/
	RBT_PURCHASED_PARENT_PACKAGE_ONLY (55, ReasonCode.RBT_PURCHASED_PARENT_PACKAGE_ONLY ),
	/** objId 56 Reason Code 302 PURCHASE_TARIFF_FAILURE*/
	PURCHASE_TARIFF_FAILURE (56, ReasonCode.PURCHASE_TARIFF_FAILURE ),
	/** objId 57 Reason Code 303 INACTIVATE_TARIFF_FAILURE*/
	INACTIVATE_TARIFF_FAILURE (57, ReasonCode.INACTIVATE_TARIFF_FAILURE ),
	/** objId 58 Reason Code 304 PURCHASE_AND_INACTIVATE_TARIFF_FAILURE*/
	PURCHASE_AND_INACTIVATE_TARIFF_FAILURE (58, ReasonCode.PURCHASE_AND_INACTIVATE_TARIFF_FAILURE ),
	/** objId 59 Reason Code 305 INVALID_TARIFF_ACTION*/
	INVALID_TARIFF_ACTION (59, ReasonCode.INVALID_TARIFF_ACTION ),
	/** objId 60 Reason Code 306 INVALID_TARIFF*/
	INVALID_TARIFF (60, ReasonCode.INVALID_TARIFF ),
	/** objId 61 Reason Code 311 INVALID_ACCOUNT_SP_ID_ACTION*/
	INVALID_ACCOUNT_SP_ID_ACTION (61, ReasonCode.INVALID_ACCOUNT_SP_ID_ACTION ),
	/** objId 62 Reason Code 312 MODIFY_ACCOUNT_SP_ID_ERIF*/
	MODIFY_ACCOUNT_SP_ID_ERIF (62, ReasonCode.MODIFY_ACCOUNT_SP_ID_ERIF ),
	/** objId 63 Reason Code 313 INVALID_ACCOUNT_IS_PREPAY_ACTION*/
	INVALID_ACCOUNT_IS_PREPAY_ACTION (63, ReasonCode.INVALID_ACCOUNT_IS_PREPAY_ACTION ),
	/** objId 64 Reason Code 314 MODIFY_ACCOUNT_IS_PREPAY_ERIF*/
	MODIFY_ACCOUNT_IS_PREPAY_ERIF (64, ReasonCode.MODIFY_ACCOUNT_IS_PREPAY_ERIF ),

	//as part of CR 2082, I've identified all ReasonCodes not present in this class, and added them here and to ER_RETURN_CODES
	/** objId 65 Reason Code -9 PARENT_IS_PAYMENT_FAILED*/
	PARENT_IS_PROVISIONING_FAILED (65, ReasonCode.PARENT_IS_PROVISIONING_FAILED ),
	/** objId 66 Reason Code 503 purchase failed because external subscriptionId and partnerId supplied already exist in ER*/
	PURCHASE_FAILED_DUPLICATE_EXT_SUB_ID (66, ReasonCode.PURCHASE_FAILED_DUPLICATE_EXT_SUB_ID ),
	/** objId 67 Reason Code 320 PURCHASE_FAILED_MORE_THAN_ONE_MATCHING_PRICEPOINT_EXISTS*/
	PURCHASE_FAILED_MORE_THAN_ONE_MATCHING_PRICEPOINT_EXISTS (67, ReasonCode.PURCHASE_FAILED_MORE_THAN_ONE_MATCHING_PRICEPOINT_EXISTS ),
	/** objId 68 Reason Code 460 ACCOUNT LOCKED - PROBABLY FOR INACTIVATION*/
	ACCOUNT_LOCKED (68, ReasonCode.ACCOUNT_LOCKED ),
	/** objId 69 Reason Code 323 Missing package-id or rating attributes short-package-id input parameter*/
	PURCHASE_FAILED_MISSING_PACKAGE_ID_INPUT_PARAM (69, ReasonCode.PURCHASE_FAILED_MISSING_PACKAGE_ID_INPUT_PARAM ),
	/** objId 70 Reason Code 41 NO VALID PACKAGE*/
	NO_VALID_PACKAGE_EX (70, ReasonCode.NO_VALID_PACKAGE_EX ),
	/** objId 71 Reason Code 330 Partner Trading Limit for Individual Transaction Value Limit Exceeded*/
	PARTNER_TRANSACTION_VALUE_LIMIT_EXCEEDED (71, ReasonCode.PARTNER_TRANSACTION_VALUE_LIMIT_EXCEEDED ),
	/** objId 72 Reason Code 57 refund discount attempted against an event or non-recurring package*/
	REFUND_DISCOUNT_AGAINST_EVENT_NON_RECURRING_SUBSCRIPTION (72, ReasonCode.REFUND_DISCOUNT_AGAINST_EVENT_NON_RECURRING_SUBSCRIPTION ),
	/** objId 73 Reason Code 454 Modify not available through client request*/
	MODIFY_BILLINGCYCLE_ERIF (73, ReasonCode.MODIFY_BILLINGCYCLE_ERIF ),
	/** objId 74 Reason Code 320 Invalid status in enhanced purchase*/
	ENHANCE_PURCHASE_INVALID_STATUS (74, ReasonCode.ENHANCE_PURCHASE_INVALID_STATUS ),
	/** objId 75 Reason Code 59 Monetary refund charging resource mismatch*/
	MONETARY_REFUND_MONETARY_CHARGING_RESOURCE_MISMATCH (75, ReasonCode.MONETARY_REFUND_MONETARY_CHARGING_RESOURCE_MISMATCH ),
	/** objId 76 Reason Code 322 End date is in the past in purchase request*/
	PURCHASE_FAILED_INVALID_END_DATE (76, ReasonCode.PURCHASE_FAILED_INVALID_END_DATE ),
	/** objId 77 Reason Code 0 SERVICE_BEING_PROVISIONED*/
	SERVICE_BEING_PROVISIONED (77, ReasonCode.SERVICE_BEING_PROVISIONED ),
	/** objId 78 Reason Code 452 Modify not available through client request*/
	MODIFY_TIMEZONE_ERIF (78, ReasonCode.MODIFY_TIMEZONE_ERIF ),
	/** objId 79 Reason Code 501 subscription not found for external subscriptionId and partnerId*/
	SUB_NOT_FOUND_FOR_EXT_SUB_ID (79, ReasonCode.SUB_NOT_FOUND_FOR_EXT_SUB_ID ),
	/** objId 80 Reason Code 54 Refund enlargement attempted against an event subscription*/
	REFUND_ENLARGEMENT_AGAINST_EVENT_SUBSCRIPTION (80, ReasonCode.REFUND_ENLARGEMENT_AGAINST_EVENT_SUBSCRIPTION ),
	/** objId 81 Reason Code -9 PARENT_IS_PAYMENT_FAILED*/
	PARENT_IS_PAYMENT_FAILED (81, ReasonCode.PARENT_IS_PAYMENT_FAILED ),
	/** objId 82 Reason Code 460 Start date greater than End date in modify subscription request*/
	MODIFY_SUBSCRPITION_FAILED_INVALID_START_AND_END_DATES (82, ReasonCode.MODIFY_SUBSCRPITION_FAILED_INVALID_START_AND_END_DATES ),
	/** objId 83 Reason Code 307 child is attempted to be purchased where the parent package is in being provisioned state*/
	RBT_PARENT_PACKAGE_BEING_PROVISIONED (83, ReasonCode.RBT_PARENT_PACKAGE_BEING_PROVISIONED ),
	/** objId 84 Reason Code 331 Partner Trading Limit for Cumulative Transaction Value Limit Exceeded*/
	PARTNER_CUMULATIVE_TRANSACTION_VALUE_LIMIT_EXCEEDED (84, ReasonCode.PARTNER_CUMULATIVE_TRANSACTION_VALUE_LIMIT_EXCEEDED ),
	/** objId 85 Reason Code 500 payment transaction not found for external transactionID and partnerId*/
	TXN_NOT_FOUND_FOR_EXT_TXN_ID (85, ReasonCode.TXN_NOT_FOUND_FOR_EXT_TXN_ID ),
	/** objId 86 Reason Code -4  FAILED INACTIVE*/
	FAILED_INACTIVE (86, ReasonCode.FAILED_INACTIVE ),
	/** objId 87 Reason Code 453 Billingcycle should be 1 to 31*/
	MODIFY_BILLINGCYCLE (87, ReasonCode.MODIFY_BILLINGCYCLE ),
	/** objId 88 Reason Code 65 UPDATE SERVICE STATUS FAILED*/
	UPDATE_SERVICE_STATUS_FAILED (88, ReasonCode.UPDATE_SERVICE_STATUS_FAILED ),
	/** objId 89 Reason Code 55 Refund enlargement attempted against an inactive or closed subscription*/
	REFUND_ENLARGEMENT_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION (89, ReasonCode.REFUND_ENLARGEMENT_AGAINST_INACTIVE_CLOSED_SUBSCRIPTION ),
	/** objId 90 Reason Code 502 purchase failed because external transactionID and partnerId supplied already exist in ER*/
	PURCHASE_FAILED_DUPLICATE_EXT_TXN_ID (90, ReasonCode.PURCHASE_FAILED_DUPLICATE_EXT_TXN_ID ),
	/** objId 91 Reason Code 58 Monetary refund attempted with a non monetary charging resource*/
	MONETARY_REFUND_WITH_NON_MONETARY_CHARGING_RESOURCE (91, ReasonCode.MONETARY_REFUND_WITH_NON_MONETARY_CHARGING_RESOURCE ),
	/** objId 92 Reason Code 308 child is attempted to be purchased where the parent package is in reserved state*/
	RBT_PARENT_PACKAGE_RESERVED (92, ReasonCode.RBT_PARENT_PACKAGE_RESERVED ),
	/** objId 93 Reason Code 56 Refund discount attempted with an invalid amount, should be between 0 and 1*/
	REFUND_DISCOUNT_INVALID_AMOUNT (93, ReasonCode.REFUND_DISCOUNT_INVALID_AMOUNT ),
	/** objId 94 Reason Code -6 PROVISIONING FAILED*/
	FAILED_PROVISIONING_FAILED (94, ReasonCode.FAILED_PROVISIONING_FAILED ),
	/** objId 95 Reason Code -8 PARENT_IS_INACTIVE*/
	PARENT_IS_INACTIVE (95, ReasonCode.PARENT_IS_INACTIVE ),
	/** objId 96 Reason Code 321 Start date greater than End date in purchase request*/
	PURCHASE_FAILED_INVALID_START_AND_END_DATES (96, ReasonCode.PURCHASE_FAILED_INVALID_START_AND_END_DATES ),
	/** objId 97 Reason Code 450 Timezone provided is invalid for specified country*/
	MODIFY_TIMEZONE (97, ReasonCode.MODIFY_TIMEZONE ),
	/** objId 98 Reason Code 103 THIS TRANSACTION IS NOT REFUNDABLE - REASON SHOULD BE STATED HERE*/
	NON_REFUNDABLE_TRANSACTION (98, ReasonCode.NON_REFUNDABLE_TRANSACTION ),
	/** objId 99 Reason Code 455 Modify not available through client request*/
	MODIFY_USERGROUPS_ERIF (99, ReasonCode.MODIFY_USERGROUPS_ERIF ),
	/** objId 100 Reason Code -5 PAYMENT FAILED*/
	FAILED_PAYMENT_FAILED (100, ReasonCode.FAILED_PAYMENT_FAILED ),
	/** objId 101, Reason Code 60 MODIFY_ACCOUNT_SP_TYPE_ERIF */
	MONETARY_REFUND_INVALID_AMOUNT (101, ReasonCode.MONETARY_REFUND_INVALID_AMOUNT),
	/** refund only possible against a completed transaction*/
	REFUND_INVALID_TXN_STATUS(104, ReasonCode.REFUND_INVALID_TXN_STATUS)
	;

	//CR1836
	//TRANSACTION_NOT_FOUND(65, ReasonCode.)

	/**
	 * a map of ReturnCodes indexed by db return_code_obj_id (eg payment failed = 5), so you can find a ReturnCode given just the objectId
	 */
	public static final HashMap<Integer, ReturnCode> _codesMapbyObjId = new HashMap<Integer,ReturnCode>();
	/**
	 * a map of ReturnCodes indexed by the ReasonCode id (eg payment failed = 21), so you can find a ReturnCode given just the ReasonCode id. <b>NB this can return incorrect results since some ReasonCodes share the same id (e.g. SERVICE_BEING_PROVISIONED and OK)</b>
	 */
	public static final HashMap<Integer, ReturnCode> _codesMapbyReasonCodeId = new HashMap<Integer,ReturnCode>();
	/**
	 * a map of ReturnCodes indexed by the ReasonCode object , so you can find a ReturnCode given just the ReasonCode
	 */
	public static final HashMap<ReasonCode, ReturnCode> _codesMapbyReasonCode = new HashMap<ReasonCode,ReturnCode>();


	static	{	//populate hashmap using id as an index so we can find things by id
		for(ReturnCode code: ReturnCode.values())	{
			_codesMapbyObjId.put(code.getReturnCodeObjId(), code);
			_codesMapbyReasonCodeId.put(code.getReasonCode().getCode(), code);
			_codesMapbyReasonCode.put(code.getReasonCode(), code);

		}		
	}

	private final int returnCodeObjId;
	private final ReasonCode reasonCode;

	private ReturnCode(int ReturnCodeObjId, ReasonCode reasonCode)	{
		this.returnCodeObjId = ReturnCodeObjId;
		this.reasonCode = reasonCode;
	}

	/**
	 * the db primary key for this return code ie ER_RETURN_CODES.RETURN_CODE_OBJ_ID
	 * @return
	 */
	public int getReturnCodeObjId(){
		return returnCodeObjId;
	}

	/**
	 * the ReasonCode object (nb contains an int value for Reason Code different to returnCodeObjId eg payment failed has Reason Code object with code 21, but returnCodeObjId = 5) 
	 * @return
	 */
	public ReasonCode getReasonCode()	{
		return reasonCode;
	}


}
