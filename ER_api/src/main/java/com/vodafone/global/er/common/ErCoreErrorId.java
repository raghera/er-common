package com.vodafone.global.er.common;
import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import com.vizzavi.ecommerce.business.charging.PurchaseAuthorizationException;
import com.vizzavi.ecommerce.business.charging.UsageAuthorizationException;
import com.vizzavi.ecommerce.business.common.AccountNotFoundException;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.EcommerceSystemException;
import com.vodafone.global.er.batch.BatchException;
import com.vodafone.global.er.rating.RatingException;
import com.vodafone.global.er.rating.TaxException;
import com.vodafone.global.er.subsmngmnt.SubsManagementException;

/**
 * This is the class that defines all the logging error definitions.
 * Every error thrown for logging purposes will be instances of this
 * class.
 * All the error codes and logging definitions are defined in this class
 * as static values.
 */
public class ErCoreErrorId
implements java.io.Serializable
{
    /**********************************************************************/

    /*
     * Error code integers.
     * Er_transactions : [216400, 216599]
     */
    public final static int C_SUCCESS = 216400;
    public final static int C_INVALID_BAN = 216401;
    public final static int C_PAYMENT_HANDLER_DOWN = 216402;
    public final static int C_INACTIVATION_FAILED = 216410;
    public final static int C_DUPLICATE_SUBSCRIPTION = 216420;
    public final static int C_UNAUTHROZIED_RENEWAL = 216421;
    public final static int C_INVALID_INPUT = 216422;
    public final static int C_NON_VALID_SUBSCRIPTION = 216423;
    public final static int C_NON_VALID_PURCHASE_AUTHORIZATION = 216424;
    public final static int C_PROVISIONING_HANDLER_DOWN = 216425;
    public final static int C_NOTIFICATION_HANDLER_DOWN = 216426;
    public final static int C_PAYMENT_DENIED = 216427;
    public final static int C_PAYMENT_REJECTED = 216428;
    //MQC 6676 - add payment auth status of ERROR
    public final static int C_PAYMENT_ERROR = 216471;
    public final static int C_UNEXPECTED_PAYMENT_STATUS = 216429;  //used only to check Payment Authorization Code
    public final static int C_DUPLICATE_PROMO_SUBSCRIPTION = 216430;
    public final static int C_UNKNOWN_ERROR_RETRIEVING_ACT_DETAILS = 216431;
    public final static int C_RMI_CONNECTION = 216432;
    public final static int C_ACCOUNT_NOT_VALIDATED = 216433;
    public final static int C_INTEGRATION_FRAMEWORK_DOWN = 216434;
    public final static int C_INTEGRATION_FRAMEWORK_UP = 216435;
    public final static int C_WRONG_DISCOUNT_VALUE = 216436;
    public final static int C_AMOUNT_LESS_THAN_ZERO = 216437;
    public final static int C_NON_MONETARY_TRANSACTION = 216438;
    public final static int C_REFUND_EXCEEDS_MAX_AMOUNT = 216439;
    public final static int C_AUTHORIZATION_DENIED = 216440;
    public final static int C_AUTHORIZATION_REJECTED = 216441;
    public final static int C_AUTHORIZATION_FAILED = 216442;
    public final static int C_ENFORCE_PURCHASE_FOUND_MORE_THAN_ONE_PURCHASE = 216443;
    //nayera Min Subscription Period - German Migration
    public final static int C_PACKAGE_HAS_NO_CANCELLATION_SERVICE = 216444;
    public final static int C_SUBSCRIBED_SYSTEM_DOWN = 216445;
    
    //Addded ER8 NFR
    public final static int C_TRANSACTION_NULL = 216446;
    public final static int C_DYNAMIC_PURCHASE_FAILED = 216447;
    public final static int C_PURCHASE_FAILED = 216448;
    public final static int C_PURCHASE_FAILED_NO_ACTIVE_EXPRESS_PRICEPOINT = 216449;
    public final static int C_PURCHASE_FAILED_NO_SERVICE_EXPRESS_PRICEPOINT = 216450;
    public final static int C_PURCHASE_FAILED_MULTIPLE_EXPRESS_PRICEPOINT_SERVICE = 216451;
    public final static int C_NO_DEFAULT_ACCOUNT_PACKAGE = 216452;

    //@hud Credit Refund Added for ER9
    public final static int C_INVALID_CHARGING_RESOURCE = 216453;
    //@hud STKHREQ13107
    
    //CR1988 - added for failed renewal
    public final static int C_RENEWAL_FAILED = 216454;
    
    public final static int C_UNIQUE_PROMOCODE_NOT_FOUND = 216460;
    public final static int C_UNIQUE_PROMOCODE_REMOVED     = 216461;
    public final static int C_UNIQUE_PROMOCODE_USED         = 216462;

    //MQC 6008
    public final static int C_ENFORCE_PURCHASE_NO_VALID_PACKAGES_FOUND = 216454;
    /**********************************************************************/

    //CR-1791
    public final static int C_ENHANCE_PURCHASE_FAILED_INVALID_STATUS = 216455;
    public final static int C_PURCHASE_FAILED_INVALID_START_AND_END_DATES = 216456;
    public final static int C_PURCHASE_FAILED_INVALID_END_DATE = 216457;
    public final static int C_MODIFY_SUBSCRPITION_FAILED_INVALID_START_AND_END_DATES = 216458;
    public final static int C_PURCHASE_FAILED_MISSING_PACKAGE_ID_INPUT_PARAM = 216459;
    
    public final static int C_PURCHASE_FAILED_MORE_THAN_ONE_MATCHING_PRICEPOINT_EXISTS = 216470;
    
    public final static int C_GOODWILL_CREDIT_PARTNER_NOT_RECOGNISED = 216480; // CR2040 MPAY replacement.  Goodwill credit.
    public final static int C_GOODWILL_CREDIT_LIMITS_NOT_SET = 216481; // CR2040 MPAY replacement.  Goodwill credit.
    public final static int C_GOODWILL_CREDIT_NOT_A_GOODWILL_CREDIT_PACKAGE = 216482; // CR2040 MPAY replacement.  Goodwill credit.
    
    /** can't modify msisdn because the destination msisdn already exists.  MQC 8456 */
    public final static int C_DUPLICATE_MSISDN = 258456; 
    //MQC10256 - add errors for validation failure for the destination msisdn for modifyMsidn
    public final static int C_DESTINATION_MSISDN_VALIDATE_REJECTED = 258457; 
    public final static int C_DESTINATION_MSISDN_VALIDATE_DENIED = 258458; 
    public final static int C_DESTINATION_MSISDN_VALIDATE_ERROR = 258459; 

    /*
     * Error code integers.
     * Er_subscriptions : [217400,217599]
     */
    public final static int C_INVALID_MSISDN = 218437;
    public final static int C_MSISDN_NOT_FOUND = 218438;
//    public final static int C_SUBSCRIPTION_EXPIRED = 21000;

    //ET83: changes starts here
    public final static int C_MSISDN_NULL = 218439;
    //ET83: changes ends here
    
    //added these codes, based on ER6 t3 spec.
    public final static int C_TXID_NOT_FOUND = 218440;
    public final static int C_REFUND_TXID_NOT_FOUND = 218441;
    public final static int C_ACCOUNT_NOT_CREATED = 218442;
    public final static int C_JNDI_LOOKUP_ERR = 218443;
    public final static int C_DB_CONNECTION_ERR = 218444;
    public final static int C_SUBSMAN_SQLERR = 218448;

    //MQC6025
    public final static int C_SUBS_ID_NOT_FOUND = 218449;
 
    //MQC 7091 - When a http connection cannot be obtained 
    public final static int C_HTTP_CONNECTION_POOL_TIMEOUT_ERROR = 218500; 
    
    
    //@hud STKHREQ13076 SP ROAMING
    public final static int C_SERVICE_NOT_AVAILABLE_FOR_ROAMING = 219410;
    public final static int C_NO_ROAMING_ENABLED = 219412;
    public final static int C_INVALID_ROAMING_REFUND = 219413;
    /**********************************************************************/

    
    
    /*
     * Error code integers.
     * Er_rating : [30001,40000]
     */

    //added these codes, based on ER6 t3 spec.
    public final static int C_PACKAGE_NOT_FOUND = 217402;
    public final static int C_SERVICE_NOT_FOUND = 217403;
    public final static int C_INVALID_PACKAGE_ATTRIBS = 217404;
    public final static int C_INVALID_SERVICE_ATTRIBS = 217405;
    public final static int C_PACKAGE_DATE_NOT_VALID = 217420;
    public final static int C_PACKAGE_WITH_SERVICE_EXPIRED = 217421;
    public final static int C_PACKAGE_WITH_INVALID_PRICEPOINT = 217430;
    public final static int C_SERVICE_WITH_INVALID_PRICEPOINT = 217431;
    public final static int C_PRICEPOINT_WITH_NO_RESOURCE = 217432;
    public final static int C_PRICEPOINT_WITH_MORE_THAN_ONE_RESOURCE = 217433;
    public final static int C_TAX_CALCULATION_FAILED = 217434;
    public final static int C_PRICEMODEL_WITH_INVALID_TIER = 217435;
    public final static int C_TAX_RATING_FAILED = 217436;

    /**********************************************************************/

    /*
     * Error code integers.
     * Er_catalog : [215400,215599]
     */

    //added these codes, based on ER6 t3 spec.
    public final static int C_CATALOGUE_OR_PRICEPLAN_NOT_FOUND =215401;
    public final static int C_INVALID_PRICEPLAN = 215403;
    public final static int C_INVALID_CATALOGUE = 215404;
    public final static int C_CATALOG_OBJECT_NOT_FOUND =215405;
    public final static int C_CATALOG_OBJECT_NOT_SET = 215406;
    public final static int C_PRICEPLAN_OBJECT_NOT_FOUND = 215407;
    public final static int C_PRICEPLAN_OBJECT_NOT_SET = 215408;

    /** @since ER9 Promocodes **/
    public final static int C_PROMOCODE_ZIPFILE_NOT_FOUND = 215409;
    public final static int C_PROMOCODE_ZIPFILE_NOT_LOADED = 215410;
    /**********************************************************************/
    /*
     * Error code integers.
     * Er_Core : [240400, 240599]
     */

    public final static int C_MALFORMED_URL = 240401;



    /**********************************************************************/

    /*
     * Clear code integers.
     * Er_clear : [240000, 240199]
     */
    public final static int C_PAYMENT_HANDLER_UP = 240001;
    public final static int C_PROVISIONING_HANDLER_UP = 240002;
    public final static int C_NOTIFICATION_HANDLER_UP = 240026;
    public final static int C_JNDI_LOOKUP_SUCCESS = 240101;
    public final static int C_DB_CONNECTION_SUCCESS = 240102;
    public final static int C_TAX_CALCULATION_SUCCESS = 240104;
    public final static int C_VALID_PRICEPLAN_AND_CATALOGUE = 240103;

    /**********************************************************************/
    /**********************************************************************/


    /*************************   Decoupling  ******************************/
    /*
     * Fatal code integers.
     * Decoupling Application : [255600,255699]
     */
    public final static int C_DC_TRANSPORT_FAILURE = 255601; /*  */


    /*
     * Error code integers.
     * Decoupling Application : [255400,255599]
     */
    public final static int C_DC_PROCESS_ERROR = 255401; /*  */
    public final static int C_DC_VALIDATION_ERROR = 255402; /*  */

    /*
     * Info code integers.
     * Decoupling Application : [255000,255199]
     */
    public final static int C_DC_PROCESS_ST = 255001; /*  */
    public final static int C_DC_PROCESS_ED = 255002; /*  */

    /*
     * Clear code integers.
     * Decoupling Application : [255000,255199]
     */
    public final static int C_DC_TRANSPORT_SUCCESS = 255003; /*  */



    /**********************************************************************/

    /*
     * Info code integers.
     * Er_clear : [216000,216199]
     * com.vodafone.global.er.transctrl.charging.ejb
     */
    public final static int C_CB_USAGE_AUTH_RATE_CHARGE_ST = 216001; /* Usage Transaction starts  */
    public final static int C_CB_USAGE_AUTH_RATE_CHARGE_ED = 216002; /* End Of Usage Transactions */
    public final static int C_PB_VALIDATE_ACCOUNT_ST = 216003; /* API call to validate account */
    public final static int C_PB_VALIDATE_ACCOUNT_ED = 216008; /* End of API call to validate account */
    public final static int C_PB_PURCHASE_PKG_ST = 216004; /* Purchase Session starting */
    public final static int C_PB_PURCHASE_PKG_ED = 216005; /* End of purchase session */
    public final static int C_PB_FIND_PKG_W_SERV_ST = 216006; /* Validates user, retrieve user groups and retrieve valid options*/
    public final static int C_PB_FIND_PKG_W_SERV_ED = 216007; /* End of API call findPackagesWithService */



    /*
     * Info code integers.
     * Er_clear : [216000,216199]
     * com.vodafone.global.er.transctrl.bl
     */
    public final static int C_BL_USAGE_TR_AUTHORIZE_CHARGE_ST = 216021; /* Business logic arround authorizing, creating, charging a usage transaction */
    public final static int C_BL_USAGE_TR_AUTHORIZE_CHARGE_ED = 216022; /* End of business logic for usage transaction*/
    public final static int C_BL_AC_VALIDATE_USER_ST = 216023; /* Call to Payment handler to validate a msisdn */
    public final static int C_BL_AC_VALIDATE_USER_ED = 216024; /* End of call to Payment handler to validate msisdn */
    public final static int C_BL_PY_AUTHORIZATION_ST = 216025; /* Call to Payment handler to Authorize payment */
    public final static int C_BL_PY_AUTHORIZATION_ED = 216026; /* End of call to Payment handler to Authorize payment */
    public final static int C_BL_PY_CAPTURE_ST = 216027; /* Call to Payment handler to Authorize paymaent */
    public final static int C_BL_PY_CAPTURE_ED = 216028; /* End of call to Payment handler to Authorize payment */
    public final static int C_BL_PURCH_TR_PACKAGE_ST = 216029; /* Rating Engine authorised the purchase; Business logic to create a payment transaction, authorize, capture, provision, notify */
    public final static int C_BL_PURCH_TR_PACKAGE_ED = 216030; /* Purchase Transaction is completed */
    public final static int C_BL_PURCH_TR_CREATE_SUB_ST =216031; /* Creation of a subscription */
    public final static int C_BL_PURCH_TR_CREATE_SUB_ED = 216032; /* End of subscription creation */
    public final static int C_BL_PURCH_TR_CHK_DUPLICATE_SUB_ST = 216033; /* Browse through existing subscriptions to find duplicates */
    public final static int C_BL_PURCH_TR_CHK_DUPLICATE_SUB_ED = 216034; /* End of check for duplictae*/
    public final static int C_BL_PURCH_TR_CALL_PROVISION_SUB_ST = 216035; /* Calling provisionHandler to send provisioning requerst */
    public final static int C_BL_PURCH_TR_CALL_PROVISION_SUB_ED = 216036; /* End of call to provisionHandler*/



    /*
     * Info code integers.
     * Er_clear : [217000,217199]
     * com.vodafone.global.er.rating
     */
    public final static int C_RT_USAGE_AUTH_RATE_ST = 217001;   /* This method will initiate Rating to authorize the use of a specific service ID */
    public final static int C_RT_USAGE_AUTH_RATE_ED = 217002;   /* End of business logic for usage transaction*/
    public final static int C_RT_EXT_RATE_USAGE_ST = 217003;    /* Rating Engine to calculate the rate without tax of a usage */
    public final static int C_RT_EXT_RATE_USAGE_ED = 217004;    /* End of Rate Calculation */
    public final static int C_RT_EXT_CALC_RATE_ST = 217005;     /* Tax Engine to calculate the tax of a transaction rate */
    public final static int C_RT_EXT_CALC_RATE_ED = 217006;     /* End of Tax calculation */
    public final static int C_RT_RATE_PURCH_ID_ST = 217007;     /* Rerated a prerated event using the rating identifier. Use of UsageRatingApi, PurchaseRatingApi */
    public final static int C_RT_RATE_PURCH_ID_ED = 217008;     /* End of Rating activities for a specific package ID */
    public final static int C_RT_EXT_RATE_PURCH_ID_ST = 217009; /* Rates specific package ID given rating attributes (as part of PurchaseRatingApi) */
    public final static int C_RT_EXT_RATE_PURCH_ID_ED = 217010; /* End of Rate calculation */
    public final static int C_RT_DISC_SRV_PURCH_OPT_ST = 217011; /* Return valid purchase options for a specific service ID */
    public final static int C_RT_DISC_SRV_PURCH_OPT_ED = 217012; /* End of discover  purchase Options */
    public final static int C_RT_GET_PKG_PURCH_OPT_ST = 217013; /* It calculates valid package options and sends each package option to the external rating engine */
    public final static int C_RT_GET_PKG_PURCH_OPT_ED = 217014; /* End of Get Packge Valid options */
    public final static int C_RT_RATE_PACKAGE_ST = 217015;         /*It returns the package rated for each price point in the package. If the price point is not active it is not returned. */
    public final static int C_RT_RATE_PACKAGE_ED = 217016;         /*End of rate and return CatalogPackage */
    public final static int C_RT_EXT_RATE_PACKAGE_ST = 217019;     /*Ext Rating Engine rates all Package options for a specic ID*/
    public final static int C_RT_EXT_RATE_PACKAGE_ED = 217020;     /* End of rating on packages options*/

    /*
     * Volume Model Rating Error codes
     *
     */
    public final static int C_RT_VR_GET_EVENT_MODEL = 217400; /*Failed to get the event model*/
    public final static int C_RT_VR_GET_VOLUME_RATING_CHARGE = 217401; /*Failed to get the volume rating charge*/
    /*
     * Info code integers.
     * Er_clear : [218000,218199]
     * com.vodafone.global.er.subsmngmnt.ejb
     */
    public final static int C_SB_GET_ACTIVE_SUBS_ST = 218001; /* Call to DB to retrieve all active subscription for a specific account*/
    public final static int C_SB_GET_ACTIVE_SUBS_ED = 218002; /* End of Call to DB to retrieve all active subscriptions  */
    public final static int C_SB_GET_ACCOUNT_DETAILS_ST = 218003; /* Call to DB to retrieve account level information*/
    public final static int C_SB_GET_ACCOUNT_DETAILS_ED = 218004; /* End of Call to DB to retrieve account level information  */
    public final static int C_SB_GET_SUB_ST = 218005; /* Call to DB to retrieve a specific subscription details*/
    public final static int C_SB_GET_SUB_ED = 218006; /* End of Call to DB to retrieve a specific subscription details */
    public final static int C_SB_GET_SUBS_ST = 218007; /* Call to DB to get all subscriptions given a set of attributes */
    public final static int C_SB_GET_SUBS_ED = 218008; /* End of Call to DB to get all subscriptions */
    public final static int C_SB_GET_TRAN_ST = 218009; /* Call to DB to get a specific transaction */
    public final static int C_SB_GET_TRAN_ED = 218010; /* End of Call to DB to get transaction */
    public final static int C_SB_FIND_ACC_BY_PK_ST = 218011; /* Call to DB to retrieve Account details*/
    public final static int C_SB_FIND_ACC_BY_PK_ED = 218012; /* End of call to DB to retrieve Account Deatils */
    public final static int C_SB_GET_PROMOCODE_USED_ST = 218013; /* Call to DB to retrieve All promocodes used*/
    public final static int C_SB_GET_PROMOCODE_USED_ED = 218014; /* End of call to DB to retrieve all promocodes used */
    //CR 1542
    public final static int C_GET_ACTIVE_SUBS_USAGE_ST = 218015; /* Call to DB to retrieve all active subscription for a specific account for the usage flow*/
    public final static int C_GET_ACTIVE_SUBS_USAGE_ED = 218016; /* End of call to DB to retrieve all active subscription for a specific account for the usage flow*/
    
    /*
     * Info code integers.
     * Er_clear : [219000,219199]
     * com.vodafone.global.er.delegate.charging
     */
    public final static int C_DEL_SESSION_END = 219001; /* End of Session */
    public final static int C_DEL_USAGE_AUTH_RATE_CHARGE_ST = 219002; /* Initiate UsageAuthRateCharge Session */
    public final static int C_DEL_VALIDATE_ACCOUNT_ST = 219003; /* Initiate ValidateAccount Session */
    public final static int C_DEL_PURCHASE_PKG_ST = 219004; /* Initiate PurchasePackageMSIDN Session */
    public final static int C_DEL_FIND_PKG_W_SERV_ST = 219005; /* Initiate FindPackagesWithServiceId Session*/
    public final static int C_DEL_USAGE_COMPLETE_ST = 219006; /* Initiate UsageComplete Session */
    public final static int C_DEL_UPDATE_SERVICE_STATUS_ST = 219007; /* Initiate UpdateServiceStatus Session */

    /*
     * Info code integers.
     * Pricing Tool : [251001,251199]
     *
     */

    public final static int C_PT_SAVE_PRICEPLAN_DB = 251001; /* Save price plan in DB */
    public final static int C_PT_SAVE_PRICEPLAN_XML = 251002; /* Save price plan has XML */
    public final static int C_PT_ADD_USER = 251003; /* Added Partner */
    public final static int C_PT_EDIT_USER = 251004; /* Amended Partner */
    public final static int C_PT_REMOVE_USER = 251005; /* Removed Partner */
    public final static int C_PT_ADD_USER_GROUP = 251006; /* Added User Group */
    public final static int C_PT_EDIT_USER_GROUP = 251007; /* Amended User Group */
    public final static int C_PT_REMOVE_USER_GROUP = 251008; /* Removed User Group */
    public final static int C_PT_ADD_PARTNER = 251009; /* Added Partner */
    public final static int C_PT_EDIT_PARTNER = 251010; /* Amended Partner */
    public final static int C_PT_REMOVE_PARTNER = 251011; /* Removed Partner */
    public final static int C_PT_ADD_PACKAGE = 251012; /* Added Package */
    public final static int C_PT_EDIT_PACKAGE = 251013; /* Edited Package */
    public final static int C_PT_REMOVE_PACKAGE = 251014; /* Removed Package */
    public final static int C_PT_ADD_SERVICE = 251015; /* Add Service */
    public final static int C_PT_EDIT_SERVICE = 251016; /* Edit Service */
    public final static int C_PT_REMOVE_SERVICE = 251017; /* Removed Service */

    //public final static int C_PT_USER_SAVE = 61018; /* Removed Service */

    public final static int C_PT_ADD_PACKAGE_PRICE_POINT = 251018; /* Add Package Price Point */
    public final static int C_PT_EDIT_PACKAGE_PRICE_POINT = 251019; /* Edit Package Price Point */
    public final static int C_PT_REMOVE_PACKAGE_PRICE_POINT = 251020; /* Delete Package Price Point */
    public final static int C_PT_ADD_SERVICE_PRICE_POINT = 251021; /* Add Service Price Point */
    public final static int C_PT_EDIT_SERVICE_PRICE_POINT = 251022; /* Edit Service Price Point */
    public final static int C_PT_REMOVE_SERVICE_PRICE_POINT = 251023; /* Delete Service Price Point */

    public final static int C_PT_ADD_BALANCE_IMPACT = 251024; /* Add Service Price Point */
    public final static int C_PT_EDIT_BALANCE_IMPACT = 251025; /* Edit Service Price Point */
    public final static int C_PT_REMOVE_BALANCE_IMPACT = 251026; /* Delete Service Price Point */

    public final static int C_PT_ADD_REVENUE_SHARE = 251030; /* Add Revenue Share */
    public final static int C_PT_EDIT_REVENUE_SHARE = 251031; /* Edit Revenue Share */
    public final static int C_PT_REMOVE_REVENUE_SHARE = 251032; /* Delete Revenue Share */

    public final static int C_PT_ADD_REFERENCE_DATA = 251040; /* Add Reference Data */
    public final static int C_PT_EDIT_REFERENCE_DATA = 251041; /* Edit Reference Data */
    public final static int C_PT_REMOVE_REFERENCE_DATA = 251042; /* Delete Reference Data */

    public final static int C_PT_ADD_CUSTOM_DATA = 251050; /* Add Custom Data */
    public final static int C_PT_EDIT_CUSTOM_DATA = 251051; /* Edit Custom Data */
    public final static int C_PT_REMOVE_CUSTOM_DATA = 251052; /* Delete Custom Data */

    public final static int C_PT_ADD_TAX_CODE = 251060; /* Add Tax Code */
    public final static int C_PT_EDIT_TAX_CODE = 251061; /* Edit Tax Code */
    public final static int C_PT_REMOVE_TAX_CODE = 251062; /* Delete Tax Code */
    public final static int C_PT_ADD_TAX_RATE = 251063; /* Add Tax Rate */
    public final static int C_PT_EDIT_TAX_RATE = 251064; /* Edit Tax Rate */
    public final static int C_PT_REMOVE_TAX_RATE = 251065; /* Delete Tax Rate */

    public final static int C_PT_ADD_DATE_TIME_MODEL = 251070; /* Add Date Time Model */
    public final static int C_PT_EDIT_DATE_TIME_MODEL = 251071; /* Edit Date Time Model */
    public final static int C_PT_REMOVE_DATE_TIME_MODEL = 251072; /* Delete Date Time Model */

    public final static int C_PT_REMOVE_DATE_TIME_TIER = 251073; /* Delete Date Time Tier */

    
//    public final static int C_WPI_TRANSPORT_FAILURE = 254601; /*  */
//    public final static int C_WPI_PROCESS_ERROR = 254401; /*  */
//    public final static int C_WPI_VALIDATION_ERROR = 254402; /*  */
//    public final static int C_WPI_PROCESS_ST = 254000; /*  */
//    public final static int C_WPI_PROCESS_ED = 254001; /*  */
//    public final static int C_WPI_TRANSPORT_SUCCESS = 254199; /*  */



    /*************************     ER IF     ******************************/

    /*
     * Error code integers.
     * IF Application : [256200,256399]
     */
    public final static int C_IF_PROVISIONING_SERVICE_HANDLER_PROCESS_ERROR = 256200;
    public final static int C_IF_SYSTEM_HANDLER_PROCESS_ERROR = 256201;
    public final static int C_IF_PROPERTIES_FILE_ERROR = 256202; /* Error of problems with the er2.properties file */
    public final static int C_IF_MAIL_SEND_ERROR = 256203; /* Error of problems with the mail */
    public final static int C_IF_JMS_PAYLOAD_ERROR = 256204; /* Error of JMS payload */
    public final static int C_IF_JMS_HANDLER_ERROR = 256205; /* Error of JMS Handler */
    public final static int C_IF_JMS_PROCESSING_ERROR = 256206; /* Error in processing JMS message */
    public final static int C_IF_NOTIFICATION_BEAN_ERROR = 256207; /* Error of Notification Bean with missing data*/
    public final static int C_IF_NOTIFICATION_PPUPDATE_ERROR = 256208; /* Error of Notification Bean with missing data*/
    public final static int C_IF_NOTIFICATION_PPUPDATE_SUBSCRIBE_HANDLER_ERROR = 256209; /* Error of Notification PPU Subscribe Handler*/
    public final static int C_IF_NOTIFICATION_VELOCITY_ERROR = 256210; /* Error with org.apache.velocity */
    public final static int C_IF_DATABASE_ERROR = 256211; /* Error with the database */
    public final static int C_IF_PAYMENT_AUTH_HANDLER_ERROR = 256212; /* Error with the payment auth handler */
    public final static int C_IF_PROVISION_SCANNER_MESSAGE_RESEND_ERROR = 256213; /* Error with resending provisioning scanner */
    public final static int C_IF_PROVISION_SCANNER_SCAN_ERROR = 256214; /* Error with provisioning scanner scan method */
    public final static int C_IF_PROVISION_PACKAGE_HANDLER_ERROR = 256215; /* Error with provision package handler process */
    public final static int C_IF_ROUTING_HTTP_INVOKER_HANDLER_ERROR = 256216; /* Error with Http invoker handler */
    public final static int C_IF_ROUTING_MESSAGE_SERVICE_CONFIG_ERROR = 256217; /* Error with MessageServiceConfig startup */
    public final static int C_IF_ROUTING_MESSAGE_SERVICE_BUILDER_ERROR = 256218; /* Error with message attributes */
    public final static int C_IF_ROUTING_FIND_MESSAGE_SERVICES_ERROR = 256219; /* Error with find Message Services */

    /*
     * Info code integers.
     * IF Application : [256400,256599]
     */
    public final static int C_IF_JMS_ONMESSAGE_ST = 256400; /* Start of calling onMessage */
    public final static int C_IF_JMS_ONMESSAGE_ED = 256401; /* End of call to process message */
    public final static int C_IF_JMS_MESSAGE = 256402; /* Info level log from message wrapper */
    public final static int C_IF_NOTIFICATION_HANDLER_PROCESS_ST = 256403; /* Start of calling Notification Handler's process */
    public final static int C_IF_NOTIFICATION_HANDLER_PROCESS_ED = 256404; /* End of calling Notification Handler's process */
    public final static int C_IF_NOTIFICATION_PPUPDATE_HANDLER_PROCESS_ST = 256405; /* Start of calling Notification PPUPdate Handler's process */
    public final static int C_IF_NOTIFICATION_PPUPDATE_HANDLER_PROCESS_ED = 256406; /* End of calling Notification PPUpdate Handler's process */
    public final static int C_IF_NOTIFICATION_PPU_SUBSCRIBER_HANDLER_PROCESS_ST = 256407; /* Start of calling Notification price plan update subscribe Handler's process */
    public final static int C_IF_NOTIFICATION_PPU_SUBSCRIBER_HANDLER_PROCESS_ED = 256408; /* End of calling Notification price plan update subscribe Handler's process */
    public final static int C_IF_NOTIFICATION_DB_CACHE = 256409; /* Notification DB Cache details */
    public final static int C_IF_PAYMENT_HANDLER_INFO = 256410; /* Payment handler info */
    public final static int C_IF_PROVISION_DATA_STORE_INFO = 256411; /* Provisioning data store info */
    public final static int C_IF_PROVISION_SCANNER_INFO = 256412; /* Provisioning data store info */
    public final static int C_IF_PROVISION_PACKAGE_HANDLER_PROCESS_ST = 256413; /* Start of calling provisioning Handler's process */
    public final static int C_IF_PROVISION_PACKAGE_HANDLER_PROCESS_ED = 256414; /* End of calling provisioning Handler's process */
    public final static int C_IF_PROVISION_SERVICE_HANDLER_PROCESS_ST = 256415; /* Start of calling provisioning Handler's process */
    public final static int C_IF_PROVISION_SERVICE_HANDLER_PROCESS_ED = 256416; /* End of calling provisioning Handler's process */
    public final static int C_IF_ROUTING_MESSAGE_INFO = 256417; /* Info of message service config */
    public final static int C_IF_SYSTEM_HANDLER_INFO = 256418; /* Info of system handler */
    public final static int C_IF_SYSTEM_HANDLER_PROCESS_ST = 256419; /* Start of calling System Handler's process */
    public final static int C_IF_SYSTEM_HANDLER_PROCESS_ED = 256420; /* End of calling System Handler's process */
    public final static int C_IF_VALIDATE_MSISDN_HANDLER_PROCESS_ST = 256421; /* Start of calling Validate Msisdn Handler's process */
    public final static int C_IF_VALIDATE_MSISDN_HANDLER_PROCESS_ED = 256422; /* End of calling Validate Msisdn Handler's process */
    
    /*
     * MQC 5190
     */
    public static final int C_IF_CONN_TIMEOUT_ERROR = 256423;

    /*
     * MQC 6948
     * These error strings define which point in the business logic
     * an IF_CONN_TIMEOUT_ERROR occurred
     */
    public static final String PAYMENT_AUTH_IF_CONN_TIMEOUT_ERROR_STR = "PaymentAuth Error";
    public static final String PAYMENT_CAPTURE_IF_CONN_TIMEOUT_ERROR_STR = "PaymentCapture Error";
    public static final String PROVISION_IF_CONN_TIMEOUT_ERROR_STR = "Provision Error";
    public static final String PAYMENT_CANCEL_IF_CONN_TIMEOUT_ERROR_STR = "PaymentCancel Error";
    public static final String PAYMENT_REFUND_MONETARY_IF_CONN_TIMEOUT_ERROR_STR = "PaymentRefund Monetary Error";
     
    /*
     * MQC 7332 - add string for payment capture response rejection, denied or error
     */
    public static final String PAYMENT_CAPTURE_IF_RESPONSE_REJECTED_DENIED_ERROR_STR = "PaymentCapture Response Rejected, Denied or Error";
    
    
    
    //CR1425
    public static final int C_IF_CONN_CERT_INVALID_ERROR = 256424;
    public static final int C_IF_CONN_CERT_NOT_TRUSTED_ERROR = 256425;
    public static final int C_IF_MESSAGE_XML_PARSE_ERROR = 256426;
    //CR1425

    /*
     * MQC 7332 - add string for payment capture response rejection, denied or error
     */
    public static final int C_PAYMENT_CAPTURE_IF_RESPONSE_REJECTED_DENIED_ERROR = 256427;
    
    /*
     * Clear code integers.
     * IF Application : [256600,256799]
     */

    /*
     * Warn code integers.
     * IF Application : [256800,256999]
     */
    public final static int C_IF_PROPERTIES_FILE_WARNING = 256800; /* Warning of problems with the er2.properties file */
    public final static int C_IF_PROVISION_DATA_STORE_WARNING = 256801; /* Warning of problems with the provisioning data store */
    public final static int C_IF_PROVISION_SCANNER_WARNING = 256802; /* Warning of problems with the provisioning scanner */


    /*
     * Dexterra provisioning handler [259000, 259999]
     */
    public final static int C_DX_PSH_ACTIVATE_ST = 259000; /* Start of Dexterra provision service handler activate() */
    public final static int C_DX_PSH_ACTIVATE_ED = 259001; /* End of Dexterra provision service handler activate() */
    public final static int C_DX_PSH_DEACTIVATE_ST = 259002; /* Start of Dexterra provision service handler deactivate() */
    public final static int C_DX_PSH_DEACTIVATE_ED = 259003; /* End of Dexterra provision service handler deactivate() */
    public final static int C_DX_PSH_REACTIVATE_ST = 259005; /* Start of Dexterra provision service handler reactivate() */
    public final static int C_DX_PSH_REACTIVATE_ED = 259006; /* End of Dexterra provision service handler reactivate() */
    public final static int C_DX_PSH_SUSPEND_ST = 259007; /* Start of Dexterra provision service handler suspend() */
    public final static int C_DX_PSH_SUSPEND_ED = 259008; /* End of Dexterra provision service handler suspend() */
    public final static int C_DX_PSH_UPDATE_ST = 259009; /* Start of Dexterra provision service handler update() */
    public final static int C_DX_PSH_UPDATE_ED = 259010; /* End of Dexterra provision service handler update() */

    public final static int C_DX_PSH_DX_ERR = 259050; /*  Dexterra error */

    /*
     * FSecure provisioning handler [260000, 260999]
     */
    public final static int C_FS_PSH_ACTIVATE_ST = 260000; /* Start of FSecure provision service handler activate() */
    public final static int C_FS_PSH_ACTIVATE_ED = 260001; /* End of FSecure provision service handler activate() */
    public final static int C_FS_PSH_DEACTIVATE_ST = 260002; /* Start of FSecure provision service handler deactivate() */
    public final static int C_FS_PSH_DEACTIVATE_ED = 260003; /* End of FSecure provision service handler deactivate() */
    public final static int C_FS_PSH_REACTIVATE_ST = 260005; /* Start of FSecure provision service handler reactivate() */
    public final static int C_FS_PSH_REACTIVATE_ED = 260006; /* End of FSecure provision service handler reactivate() */
    public final static int C_FS_PSH_SUSPEND_ST = 260007; /* Start of FSecure provision service handler suspend() */
    public final static int C_FS_PSH_SUSPEND_ED = 260008; /* End of FSecure provision service handler suspend() */
    public final static int C_FS_PSH_UPDATE_ST = 260009; /* Start of FSecure provision service handler update() */
    public final static int C_FS_PSH_UPDATE_ED = 260010; /* End of FSecure provision service handler update() */

    public final static int C_FS_PSH_FS_ERR = 260050; /* FSecure error */


    /*
     * Front End Proxy [261000, 261999]
     */
    public final static int C_FEP_DOPOST_ST = 261000; /* Start of FEP doPost() */
    public final static int C_FEP_DOPOST_ED = 261001; /* End of FEP doPost() */
    public final static int C_FEP_HANDLE_ST = 261002; /* Start of FEP handle() */
    public final static int C_FEP_HANDLE_ED = 261003; /* End of FEP handle() */
    public final static int C_FEP_ROUTE_ST = 261004; /* Start of FEP route() */
    public final static int C_FEP_ROUTE_ED = 261005; /* End of FEP route() */
        public final static int C_FEP_ERR = 261006; /* FEP error */
        public final static int C_FEP_IP_LOG = 261007; /* FEP IP address logging */


    /**********************************************************************/
    /*
     * Generic Error code
     * ERROR : [2XX400 - 2XX599]
     */
    /**
     * Error code for Logging Error
     *
     */
    public static final int C_LOGGING = 200401;

    /**
     * Error code for object null check
     */
    public static final int C_GENERIC_NULL_OBJECT = 200402;

    /**
     * Error code for Null Input Parameters
     */
    public static final int C_NULL_INPUT_PARAMETER = 200403;

    /**********************************************************************/
    /*
     * Generic Error code
     * FATAL : [2XX600 - 2XX699]  @mawn R9 RF
     */
    /**
     * JNDI lookup failed for Account Bean.
     */
    public static final int C_JNDI_LOOKUP_FAILURE_FOR_ACCOUNT_BEAN = 200600;

    /**
     * JNDI lookup failed for Charging Bean.
     */
    public static final int C_JNDI_LOOKUP_FAILURE_FOR_CHARGING_BEAN = 200601;

    /**
     * JNDI lookup failed for Notification Bean.
     */
    public static final int C_JNDI_LOOKUP_FAILURE_FOR_NOTIFICATION_BEAN = 200602;

    /**
     * JNDI lookup failed for Payment Bean.
     */
    public static final int C_JNDI_LOOKUP_FAILURE_FOR_PAYMENT_BEAN = 200603;

    /**
     * JNDI lookup failed for Provision Bean.
     */
    public static final int C_JNDI_LOOKUP_FAILURE_FOR_PROVISION_BEAN = 200604;

    /**
     * JNDI lookup failed for Purchase Bean.
     */
    public static final int C_JNDI_LOOKUP_FAILURE_FOR_PURCHASE_BEAN = 200605;

    /**
     * JNDI lookup failed for Routable Bean.
     */
    public static final int C_JNDI_LOOKUP_FAILURE_FOR_ROUTABLE_BEAN = 200606;

    /**
     * JNDI lookup failed for Selfcare Bean.
     */
    public static final int C_JNDI_LOOKUP_FAILURE_FOR_SELFCARE_BEAN = 200607;


    /**
     * UNKNOWN EXCEPTION
     */
    public static final int C_UNKNOWN_ERROR = 200599;

    //MQC 7625
    public static final int C_UNEXPECTED_DB_ERROR = 200450;
    
    /**
     * Properties file missing data or incorrect format
     */
    public static final int C_PROPERTIES_FILE_ERROR = 200100;

    /**********************************************************************/
    /*
     * Error code for DEBUG
     * Er-debug
     */
    public static final int C_DEBUG = 999999;

    /**********************************************************************/

    /*
     * Logging Severity
     */
    public static final String FATAL = "F";
    public static final String ERROR = "E";
    public static final String WARN = "W";
    public static final String CLEAR = "C";
    public static final String INFO = "I";
    public static final String DEBUG = "D";

    /**********************************************************************/


    /**********************************************************************/
    /*
     * Error code objects for ER_Transactions.
     */
    public final static ErCoreErrorId SUCCESS = new ErCoreErrorId(C_SUCCESS,
            "Routine succeeded", INFO);
    /**216401*/
    public final static ErCoreErrorId INVALID_BAN = new ErCoreErrorId(C_INVALID_BAN,
            "Billing Account Number not validated by Payment handler", ERROR);
    public final static ErCoreErrorId PAYMENT_HANDLER_DOWN = new ErCoreErrorId(C_PAYMENT_HANDLER_DOWN,
            "Payment handler returned null as PurchaseAuthorization", FATAL, true);
    public final static ErCoreErrorId INACTIVATION_FAILED = new ErCoreErrorId(C_INACTIVATION_FAILED,
            "Subscription Management Failed to inactivate Account", ERROR);
    //MQC7230 - start
    public final static ErCoreErrorId DUPLICATE_SUBSCRIPTION = new ErCoreErrorId(C_DUPLICATE_SUBSCRIPTION,
            //was
    		//"Routine succeeded", INFO
    		"Duplicate subscriptions", WARN);
    //MQC7230 - end
    
    public final static ErCoreErrorId UNAUTHROZIED_RENEWAL = new ErCoreErrorId(C_UNAUTHROZIED_RENEWAL,
            "Renewal has not been authorized; probably because services are still being provisioned or it failed to be provisioned",
            ERROR);
    public final static ErCoreErrorId INVALID_INPUT = new ErCoreErrorId(C_INVALID_INPUT,
            "Invalid Input - Could not process transaction", ERROR);

    public final static ErCoreErrorId SUBSCRIBED_SYSTEM_DOWN = new ErCoreErrorId(C_SUBSCRIBED_SYSTEM_DOWN,
            "couldn't connect to external URL", ERROR);


    /**Transaction not found - mqc8324*/
    public final static ErCoreErrorId TRANSACTION_NULL = new ErCoreErrorId(C_TRANSACTION_NULL,
            "Transaction not found", ERROR);

    public final static ErCoreErrorId DYNAMIC_PURCHASE_FAILED = new ErCoreErrorId(C_DYNAMIC_PURCHASE_FAILED,
            "Failed to purchase dynamic default package ", ERROR);

    public final static ErCoreErrorId PURCHASE_FAILED = new ErCoreErrorId(C_PURCHASE_FAILED,
            "Failed to purchase package ", ERROR);

    //CR1988 - added for failed renewal
    public final static ErCoreErrorId RENEWAL_FAILED = new ErCoreErrorId(C_RENEWAL_FAILED,
            "Failed to renew subscription ", ERROR);
    
    public final static ErCoreErrorId PURCHASE_FAILED_NO_ACTIVE_EXPRESS_PRICEPOINT = new ErCoreErrorId(C_PURCHASE_FAILED_NO_ACTIVE_EXPRESS_PRICEPOINT,
            "No Active Express PricePoint associated with express package ", ERROR);

    public final static ErCoreErrorId PURCHASE_FAILED_NO_SERVICE_EXPRESS_PRICEPOINT = new ErCoreErrorId(C_PURCHASE_FAILED_NO_SERVICE_EXPRESS_PRICEPOINT,
            "No Express Package associated with service ", ERROR);

    public final static ErCoreErrorId PURCHASE_FAILED_MULTIPLE_EXPRESS_PRICEPOINT_SERVICE = new ErCoreErrorId(C_PURCHASE_FAILED_MULTIPLE_EXPRESS_PRICEPOINT_SERVICE,
            "More than one Express Package associated with service ", ERROR);

    public final static ErCoreErrorId NO_DEFAULT_ACCOUNT_PACKAGE = new ErCoreErrorId(C_NO_DEFAULT_ACCOUNT_PACKAGE,
            "No Default Package for that account ", ERROR);
    public final static ErCoreErrorId INVALID_CHARGING_RESOURCE = new ErCoreErrorId(C_INVALID_CHARGING_RESOURCE,
            "Invalid charging resource ", ERROR);

    public final static ErCoreErrorId UNIQUE_PROMOCODE_NOT_FOUND = new ErCoreErrorId(C_UNIQUE_PROMOCODE_NOT_FOUND,
            "The unique promo code is not found ", ERROR);
    public final static ErCoreErrorId UNIQUE_PROMOCODE_REMOVED = new ErCoreErrorId(C_UNIQUE_PROMOCODE_REMOVED,
            "The unique promo code has been removed ", ERROR);
    public final static ErCoreErrorId UNIQUE_PROMOCODE_USED = new ErCoreErrorId(C_UNIQUE_PROMOCODE_USED,
            "The unique promo code has been used ", ERROR);



    public final static ErCoreErrorId NON_VALID_SUBSCRIPTION = new ErCoreErrorId(C_NON_VALID_SUBSCRIPTION,
            "Subscription or one of it attributes is null or corrupted", ERROR);
    public final static ErCoreErrorId NON_VALID_PURCHASE_AUTHORIZATION = new ErCoreErrorId(C_NON_VALID_PURCHASE_AUTHORIZATION,
            "PurchaseAuthorization or one of its attributes is null or corrupted",
            ERROR);
    public final static ErCoreErrorId PROVISIONING_HANDLER_DOWN = new ErCoreErrorId(C_PROVISIONING_HANDLER_DOWN,
            "Provisioning Handler is down", FATAL, true);
    public final static ErCoreErrorId NOTIFICATION_HANDLER_DOWN = new ErCoreErrorId(C_NOTIFICATION_HANDLER_DOWN,
            "Notification Handler is down", FATAL, true);
    public final static ErCoreErrorId PAYMENT_DENIED = new ErCoreErrorId(C_PAYMENT_DENIED,
            "Payment Denied by External billing System", INFO);
    public final static ErCoreErrorId PAYMENT_REJECTED = new ErCoreErrorId(C_PAYMENT_REJECTED,
            "Payment Rejected by External System", INFO);
    //MQC 6676 - add payment auth status of ERROR
    public final static ErCoreErrorId PAYMENT_ERROR = new ErCoreErrorId(C_PAYMENT_ERROR,
            "Payment Error by External System", INFO);
    public final static ErCoreErrorId UNEXPECTED_PAYMENT_STATUS = new ErCoreErrorId(C_UNEXPECTED_PAYMENT_STATUS,
            "Payment handler returned un-expected payment status code", ERROR);
    public final static ErCoreErrorId DUPLICATE_PROMO_SUBSCRIPTION = new ErCoreErrorId(C_DUPLICATE_PROMO_SUBSCRIPTION,
            "Purchase Request should failed as user has bought the subscription with identic promocode",
            ERROR);
    public final static ErCoreErrorId UNKNOWN_ERROR_RETRIEVING_ACT_DETAILS = new ErCoreErrorId(C_UNKNOWN_ERROR_RETRIEVING_ACT_DETAILS,
            "Subscription Managment returning unknown error related to findBy methods",
            ERROR);
    public final static ErCoreErrorId RMI_CONNECTION = new ErCoreErrorId(C_RMI_CONNECTION,
            "RMI Connection failed.", INFO);
    public final static ErCoreErrorId ACCOUNT_NOT_VALIDATED = new ErCoreErrorId(C_ACCOUNT_NOT_VALIDATED,
            "Payment handler has not returned any BAN which means it has not validated the given Msisdn",
            ERROR);
    public final static ErCoreErrorId INTEGRATION_FRAMEWORK_DOWN = new ErCoreErrorId(C_INTEGRATION_FRAMEWORK_DOWN,
            "Integration Framework is not available.",
            FATAL, true);
    public final static ErCoreErrorId WRONG_DISCOUNT_VALUE = new ErCoreErrorId(C_WRONG_DISCOUNT_VALUE,
            "Discount value is either less than zero or more than 100 ",
            ERROR);
    public final static ErCoreErrorId AMOUNT_LESS_THAN_ZERO = new ErCoreErrorId(C_AMOUNT_LESS_THAN_ZERO,
            "Cash Amount value is less than zero. ",
            ERROR);
    public final static ErCoreErrorId NON_MONETARY_TRANSACTION = new ErCoreErrorId(C_NON_MONETARY_TRANSACTION,
            "Cash Amount value is less than zero. ",
            ERROR);
    public final static ErCoreErrorId REFUND_EXCEEDS_MAX_AMOUNT = new ErCoreErrorId(C_REFUND_EXCEEDS_MAX_AMOUNT,
            "RefundTransaction.createRefundTransaction : Refund transaction exceeds max cash.",
            ERROR);
    public final static ErCoreErrorId AUTHORIZATION_DENIED = new ErCoreErrorId(C_AUTHORIZATION_DENIED,
            "Authorization DENIED ",
            ERROR);
    public final static ErCoreErrorId AUTHORIZATION_REJECTED = new ErCoreErrorId(C_AUTHORIZATION_REJECTED,
            "Authorization REJECTED ",
            ERROR);
    public final static ErCoreErrorId AUTHORIZATION_FAILED = new ErCoreErrorId(C_AUTHORIZATION_FAILED,
            "Authorization FAILED ",
            ERROR);
    public final static ErCoreErrorId ENFORCE_PURCHASE_FOUND_MORE_THAN_ONE_PURCHASE = new ErCoreErrorId(C_ENFORCE_PURCHASE_FOUND_MORE_THAN_ONE_PURCHASE,
            "enforce Purchase: There should only be one purchase option for enforce purchase on usage ",
            ERROR);
    //nayera Min Subscription Period - German Migration
    public final static ErCoreErrorId PACKAGE_HAS_NO_CANCELLATION_SERVICE = new ErCoreErrorId(C_PACKAGE_HAS_NO_CANCELLATION_SERVICE,
            "min subscription period: a package having a penalty charge must have a cancellation service ",
            ERROR);

    //MQC 6008
    public final static ErCoreErrorId ENFORCE_PURCHASE_NO_VALID_PACKAGES_FOUND = new ErCoreErrorId(C_ENFORCE_PURCHASE_FOUND_MORE_THAN_ONE_PURCHASE,
            "Force Purchase: No valid packages were found.  There should be exactly one package",
            ERROR);
    /** Modify msisdn: The modify request failed because the destination msisdn exists*/
    public final static ErCoreErrorId MODIFY_FAILED_DUPLICATE_MSISDN = new ErCoreErrorId(C_DUPLICATE_MSISDN,
            "Modify msisdn: The modify request failed because the destination msisdn exists",
            ERROR);
    
    //MQC10256 - add errors for validation failure for the destination msisdn for modifyMsidn
    public final static ErCoreErrorId MODIFY_FAILED_DESTINATION_MSISDN_VALIDATE_REJECTED = new ErCoreErrorId(C_DESTINATION_MSISDN_VALIDATE_REJECTED,
            "Modify msisdn: The modify request failed because the validation handler returned rejected for the destination msisdn",
            ERROR);
    public final static ErCoreErrorId MODIFY_FAILED_DESTINATION_MSISDN_VALIDATE_DENIED = new ErCoreErrorId(C_DESTINATION_MSISDN_VALIDATE_DENIED,
            "Modify msisdn: The modify request failed because the validation handler returned denied for the destination msisdn",
            ERROR);
    public final static ErCoreErrorId MODIFY_FAILED_DESTINATION_MSISDN_VALIDATE_ERROR = new ErCoreErrorId(C_DESTINATION_MSISDN_VALIDATE_ERROR,
            "Modify msisdn: The modify request failed because the validation handler returned error for the destination msisdn",
            ERROR);
    
    /**218437*/
    public final static ErCoreErrorId INVALID_MSISDN = new ErCoreErrorId(C_INVALID_MSISDN,
            "Invalid MSISDN", INFO);
    /**218438*/
    public final static ErCoreErrorId MSISDN_NOT_FOUND = new ErCoreErrorId(C_MSISDN_NOT_FOUND,
            "Account not found for given MSISDN.", ERROR);
    //ET83: changes starts here
    /**218439*/
    public final static ErCoreErrorId INPUT_MSISDN_NULL = new ErCoreErrorId(C_MSISDN_NULL,
            "Input MSISN is null.", ERROR);
    //ET83: changes ends here    
    /**218440*/
    public final static ErCoreErrorId TXID_NOT_FOUND = new ErCoreErrorId(C_TXID_NOT_FOUND,
            "Account not found for given ER transaction ID.", ERROR);
    /**218441*/
    public final static ErCoreErrorId REFUND_TXID_NOT_FOUND = new ErCoreErrorId(C_REFUND_TXID_NOT_FOUND,
            "Account not found for given ER refund transaction ID.", ERROR);
    /**218442*/
    public final static ErCoreErrorId ACCOUNT_NOT_CREATED = new ErCoreErrorId(C_ACCOUNT_NOT_CREATED,
            "Unable to create a new account.", ERROR);
    public final static ErCoreErrorId JNDI_LOOKUP_ERR = new ErCoreErrorId(C_JNDI_LOOKUP_ERR,
            "Unable to complete JNDI lookup for data source.", FATAL);
    public final static ErCoreErrorId DB_CONNECTION_ERR = new ErCoreErrorId(C_DB_CONNECTION_ERR,
            "Unable to open a database connection", FATAL);
    public final static ErCoreErrorId SUBSMAN_SQLERR = new ErCoreErrorId(C_SUBSMAN_SQLERR,
            "A generic SQL error occured in the code.", ERROR);


    //MQC6025
    public final static ErCoreErrorId SUBSID_NOT_FOUND = new ErCoreErrorId(C_SUBS_ID_NOT_FOUND,
            "Subscription ID not found.", ERROR);


    //@hud STKHREQ13076
    public final static ErCoreErrorId SERVICE_NOT_AVAILABLE_FOR_ROAMING = new ErCoreErrorId(C_SERVICE_NOT_AVAILABLE_FOR_ROAMING,
            "The service is not available for roaming.", ERROR);
    public final static ErCoreErrorId NO_ROAMING_ENABLED = new ErCoreErrorId(C_NO_ROAMING_ENABLED,
            "Roaming is not enabled.", ERROR);
    public final static ErCoreErrorId INVALID_ROAMING_REFUND = new ErCoreErrorId(C_INVALID_ROAMING_REFUND,
            "Invalid roaming refund.", ERROR);


    /**
     * ER rating
     */
    public final static ErCoreErrorId PACKAGE_NOT_FOUND = new ErCoreErrorId(C_PACKAGE_NOT_FOUND,
            "Error retrieving package, Package ID supplied is invalid or null.", ERROR);
    public final static ErCoreErrorId SERVICE_NOT_FOUND = new ErCoreErrorId(C_SERVICE_NOT_FOUND,
            "Error retrieving service, Service ID supplied is invalid or null.", ERROR);
    public final static ErCoreErrorId INVALID_PACKAGE_ATTRIBS = new ErCoreErrorId(C_INVALID_PACKAGE_ATTRIBS,
            "Invalid or null package rating attributes supplied.", ERROR);
    public final static ErCoreErrorId INVALID_SERVICE_ATTRIBS = new ErCoreErrorId(C_INVALID_SERVICE_ATTRIBS,
            "Invalid or null usage rating attributes supplied.", ERROR);
    public final static ErCoreErrorId PACKAGE_DATE_NOT_VALID = new ErCoreErrorId(C_PACKAGE_DATE_NOT_VALID,
            "Package no longer available to purchase, package end date surpassed. ",
            ERROR);
    public final static ErCoreErrorId PACKAGE_WITH_SERVICE_EXPIRED = new ErCoreErrorId(C_PACKAGE_WITH_SERVICE_EXPIRED,
            "Usage of service prohibited, purchase package, which contains the service, has expired.",
            ERROR);
    public final static ErCoreErrorId PACKAGE_WITH_INVALID_PRICEPOINT = new ErCoreErrorId(C_PACKAGE_WITH_INVALID_PRICEPOINT,
            "Package attempted to be purchased has an invalid or null price point.",
            ERROR);
    public final static ErCoreErrorId SERVICE_WITH_INVALID_PRICEPOINT = new ErCoreErrorId(C_SERVICE_WITH_INVALID_PRICEPOINT,
            "Service attenoted to be used has an invalid or null price plan.", ERROR);
    public final static ErCoreErrorId PRICEPOINT_WITH_NO_RESOURCE = new ErCoreErrorId(C_PRICEPOINT_WITH_NO_RESOURCE,
            "Price point contains no or null charging resource.", ERROR);
    public final static ErCoreErrorId PRICEPOINT_WITH_MORE_THAN_ONE_RESOURCE = new ErCoreErrorId(C_PRICEPOINT_WITH_MORE_THAN_ONE_RESOURCE,
            "Price point contains more than one custom resource.", ERROR);
    public final static ErCoreErrorId TAX_CALCULATION_FAILED = new ErCoreErrorId(C_TAX_CALCULATION_FAILED,
            "Tax rating calculation failed due to no TAX code defined in priceplan_XX.xml file.",
            ERROR);
    public final static ErCoreErrorId PRICEMODEL_WITH_INVALID_TIER = new ErCoreErrorId(C_PRICEMODEL_WITH_INVALID_TIER,
            "Pricing model has an invalid tier.", ERROR);
    public final static ErCoreErrorId TAX_RATING_FAILED = new ErCoreErrorId(C_TAX_RATING_FAILED,
            "Tax Rating Failed. ", ERROR);



    /**
     * ER Catalog
     */
//    public final static ErCoreErrorId CATALOG_PRICEPLAN_NOT_FOUND = new ErCoreErrorId(C_CATALOG_PRICEPLAN_NOT_FOUND,
//    "Priceplan_XX.xml file does not exist.", FATAL
//    );
//    public final static ErCoreErrorId CATALOG_CATALOGUE_NOT_FOUND = new ErCoreErrorId(C_CATALOG_CATALOGUE_NOT_FOUND,
//    "Catalogue_XX.xml file does not exist.", FATAL
//    );
    public final static ErCoreErrorId CATALOGUE_OR_PRICEPLAN_NOT_FOUND = new ErCoreErrorId(C_CATALOGUE_OR_PRICEPLAN_NOT_FOUND,
            "Following file does not exist. ", ERROR);
//    public final static ErCoreErrorId CATALOGUE_NOT_FOUND = new ErCoreErrorId(C_CATALOGUE_NOT_FOUND,
//    "Catalogue_XX.xml file does not exist.", ERROR
//    );
    public final static ErCoreErrorId INVALID_PRICEPLAN = new ErCoreErrorId(C_INVALID_PRICEPLAN,
            "Priceplan_XX.xml parsing error, file may be corrupted or in the wrong format.",
            FATAL);
    public final static ErCoreErrorId INVALID_CATALOGUE = new ErCoreErrorId(C_INVALID_CATALOGUE,
            "Catalogue_XX.xml parsing error, file may be corrupted or in the wrong format.",
            FATAL);
    public final static ErCoreErrorId CATALOG_OBJECT_NOT_FOUND = new ErCoreErrorId(C_CATALOG_OBJECT_NOT_FOUND,
            "Error retrieving catalog object item, value supplied is invalid or null.",
            ERROR);
    public final static ErCoreErrorId CATALOG_OBJECT_NOT_SET = new ErCoreErrorId(C_CATALOG_OBJECT_NOT_SET,
            "Error setting catalog object item, object to set is invalid or null.",
            ERROR);
    public final static ErCoreErrorId PRICEPLAN_OBJECT_NOT_FOUND = new ErCoreErrorId(C_PRICEPLAN_OBJECT_NOT_FOUND,
            "Error retrieving priceplan object item, value supplied is invalid or null.",
            ERROR);
    public final static ErCoreErrorId PRICEPLAN_OBJECT_NOT_SET = new ErCoreErrorId(C_PRICEPLAN_OBJECT_NOT_SET,
            "Error setting priceplan object item, object to set is invalid or null.",
            ERROR);

    public final static ErCoreErrorId PROMOCODE_FILE_NOT_FOUND = new ErCoreErrorId(C_PROMOCODE_ZIPFILE_NOT_FOUND,
            "Error retrieving promocode zipfile object item, value supplied is invalid or null.",
            ERROR);

    public final static ErCoreErrorId PROMOCODE_FILE_NOT_LOADED = new ErCoreErrorId(C_PROMOCODE_ZIPFILE_NOT_LOADED,
            "Promocode zip file not found and may not be needed for this priceplan, skip loading.",
            ERROR);
    /**
     * Clear Codes
     */
    public final static ErCoreErrorId PAYMENT_HANDLER_UP = new ErCoreErrorId(C_PAYMENT_HANDLER_UP,
            "Payment Handler is up.", CLEAR);
    public final static ErCoreErrorId PROVISIONING_HANDLER_UP = new ErCoreErrorId(C_PROVISIONING_HANDLER_UP,
            "Provisioning Handler is up.", CLEAR);
    public final static ErCoreErrorId NOTIFICATION_HANDLER_UP = new ErCoreErrorId(C_NOTIFICATION_HANDLER_UP,
            "Notification Handler is up.", CLEAR);
    public final static ErCoreErrorId JNDI_LOOKUP_SUCCESS = new ErCoreErrorId(C_JNDI_LOOKUP_SUCCESS,
            "Database JNDI lookup was successful: ", CLEAR);
    public final static ErCoreErrorId DB_CONNECTION_SUCCESS = new ErCoreErrorId(C_DB_CONNECTION_SUCCESS,
            "Successfully connected to database: ", CLEAR);

    public final static ErCoreErrorId VALID_PRICEPLAN_AND_CATALOGUE = new ErCoreErrorId(C_VALID_PRICEPLAN_AND_CATALOGUE,
            "Valid priceplan and catalogue found.", CLEAR);
    public final static ErCoreErrorId INTEGRATION_FRAMEWORK_UP = new ErCoreErrorId(C_INTEGRATION_FRAMEWORK_UP,
            "Integration Framework is now available.",
            CLEAR);

    /************************************************************************/
    /************************************************************************/
    public final static ErCoreErrorId BL_USAGE_TR_AUTHORIZE_CHARGE_ST = new ErCoreErrorId(C_BL_USAGE_TR_AUTHORIZE_CHARGE_ST,
            "End of API call to validate account", INFO);
    public final static ErCoreErrorId BL_USAGE_TR_AUTHORIZE_CHARGE_ED = new ErCoreErrorId(C_BL_USAGE_TR_AUTHORIZE_CHARGE_ED,
            "End of business logic for usage transaction", INFO);
    public final static ErCoreErrorId BL_AC_VALIDATE_USER_ST = new ErCoreErrorId(C_BL_AC_VALIDATE_USER_ST,
            "Call to Payment handler to validate a msisdn", INFO);
    public final static ErCoreErrorId BL_AC_VALIDATE_USER_ED = new ErCoreErrorId(C_BL_AC_VALIDATE_USER_ED,
            "End of call to Payment handler to validate msisdn", INFO);
    public final static ErCoreErrorId BL_PY_AUTHORIZATION_ST = new ErCoreErrorId(C_BL_PY_AUTHORIZATION_ST,
            "Call to Payment handler to Authorize paymaent", INFO);
    public final static ErCoreErrorId BL_PY_AUTHORIZATION_ED = new ErCoreErrorId(C_BL_PY_AUTHORIZATION_ED,
            "End of call to Payment handler to Authorize payment", INFO);
    public final static ErCoreErrorId BL_PY_CAPTURE_ST = new ErCoreErrorId(C_BL_PY_CAPTURE_ST,
            "Call to Payment handler to Capture payment", INFO);
    public final static ErCoreErrorId BL_PY_CAPTURE_ED = new ErCoreErrorId(C_BL_PY_CAPTURE_ED,
            "Call to Payment handler to Capture payment", INFO);
    public final static ErCoreErrorId CB_USAGE_AUTH_RATE_CHARGE_ST = new ErCoreErrorId(C_CB_USAGE_AUTH_RATE_CHARGE_ST,
            "Usage Transaction starts", INFO);
    public final static ErCoreErrorId CB_USAGE_AUTH_RATE_CHARGE_ED = new ErCoreErrorId(C_CB_USAGE_AUTH_RATE_CHARGE_ED,
            "End Of Usage Transactions", INFO);
    public final static ErCoreErrorId PB_VALIDATE_ACCOUNT_ST = new ErCoreErrorId(C_PB_VALIDATE_ACCOUNT_ST,
            "API call to validate account", INFO);
    public final static ErCoreErrorId PB_VALIDATE_ACCOUNT_ED = new ErCoreErrorId(C_PB_VALIDATE_ACCOUNT_ED,
            "End of API call to validate account", INFO);
    public final static ErCoreErrorId RT_USAGE_AUTH_RATE_ST = new ErCoreErrorId(C_RT_USAGE_AUTH_RATE_ST,
            "This will initiate Rating to authorize the use of a specific service ID ", INFO);
    public final static ErCoreErrorId RT_USAGE_AUTH_RATE_ED = new ErCoreErrorId(C_RT_USAGE_AUTH_RATE_ED,
            "End of business logic for usage transaction ", INFO);
    public final static ErCoreErrorId RT_EXT_RATE_USAGE_ST = new ErCoreErrorId(C_RT_EXT_RATE_USAGE_ST,
            "Rating Engine to calculate the rate without tax of a usage ", INFO);
    public final static ErCoreErrorId RT_EXT_RATE_USAGE_ED = new ErCoreErrorId(C_RT_EXT_RATE_USAGE_ED,
            "End of Rate Calculation ", INFO);
    public final static ErCoreErrorId RT_EXT_CALC_RATE_ST = new ErCoreErrorId(C_RT_EXT_CALC_RATE_ST,
            "Tax Engine to calculate the tax of a transaction rate ", INFO);
    public final static ErCoreErrorId RT_EXT_CALC_RATE_ED = new ErCoreErrorId(C_RT_EXT_CALC_RATE_ED,
            "End of Tax calculation", INFO);
    public final static ErCoreErrorId SB_GET_ACTIVE_SUBS_ST = new ErCoreErrorId(C_SB_GET_ACTIVE_SUBS_ST,
            "Call to DB to retrieve all active subscription for a specific account", INFO);
    public final static ErCoreErrorId SB_GET_ACTIVE_SUBS_ED = new ErCoreErrorId(C_SB_GET_ACTIVE_SUBS_ED,
            "End of Call to DB to retrieve all active subscriptions ", INFO);
    //CR 1542
    public final static ErCoreErrorId SB_GET_ACTIVE_SUBS_USAGE_ST = new ErCoreErrorId(C_GET_ACTIVE_SUBS_USAGE_ST,
            "Call to DB to retrieve all active subscription for a specific account for the usage flow", INFO);
    public final static ErCoreErrorId SB_GET_ACTIVE_SUBS_USAGE_ED = new ErCoreErrorId(C_GET_ACTIVE_SUBS_USAGE_ED,
            "End of Call to DB to retrieve all active subscriptions for the usage flow", INFO);
    public final static ErCoreErrorId SB_GET_ACCOUNT_DETAILS_ST = new ErCoreErrorId(C_SB_GET_ACCOUNT_DETAILS_ST,
            "Call to DB to retrieve account level information", INFO);
    public final static ErCoreErrorId SB_GET_ACCOUNT_DETAILS_ED = new ErCoreErrorId(C_SB_GET_ACCOUNT_DETAILS_ED,
            "End of Call to DB to retrieve account level information", INFO);
    public final static ErCoreErrorId SB_GET_SUB_ST = new ErCoreErrorId(C_SB_GET_SUB_ST,
            "Call to DB to retrieve a specific subscription details", INFO);
    public final static ErCoreErrorId SB_GET_SUB_ED = new ErCoreErrorId(C_SB_GET_SUB_ED,
            "End of Call to DB to retrieve a specific subscription details", INFO);
    public final static ErCoreErrorId RT_RATE_PURCH_ID_ST = new ErCoreErrorId(C_RT_RATE_PURCH_ID_ST,
            "Rerated a prerated event using the rating identifier. Use of UsageRatingApi, PurchaseRatingApi", INFO);
    public final static ErCoreErrorId RT_RATE_PURCH_ID_ED = new ErCoreErrorId(C_RT_RATE_PURCH_ID_ED,
            "End of Rating activities for a specific package ID", INFO);
    public final static ErCoreErrorId RT_EXT_RATE_PURCH_ID_ST = new ErCoreErrorId(C_RT_EXT_RATE_PURCH_ID_ST,
            "Rates specific package ID given rating attributes (as part of PurchaseRatingApi)", INFO);
    public final static ErCoreErrorId RT_EXT_RATE_PURCH_ID_ED = new ErCoreErrorId(C_RT_EXT_RATE_PURCH_ID_ED,
            "End of Rate calculation", INFO);
    public final static ErCoreErrorId SB_GET_SUBS_ST = new ErCoreErrorId(C_SB_GET_SUBS_ST,
            "Call to DB to get all subscriptions given a set of attributes", INFO);
    public final static ErCoreErrorId SB_GET_SUBS_ED = new ErCoreErrorId(C_SB_GET_SUBS_ED,
            "End of Call to DB to get all subscriptions", INFO);
    public final static ErCoreErrorId SB_GET_TRAN_ST = new ErCoreErrorId(C_SB_GET_TRAN_ST,
            "Call to DB to get a specific transaction given a set of attributes", INFO);
    public final static ErCoreErrorId SB_GET_TRAN_ED = new ErCoreErrorId(C_SB_GET_TRAN_ED,
            "End of Call to DB to get a specific transaction", INFO);
    public final static ErCoreErrorId BL_PURCH_TR_CHK_DUPLICATE_SUB_ST = new ErCoreErrorId(C_BL_PURCH_TR_CHK_DUPLICATE_SUB_ST,
            "Check for duplicate subscription that can be re-used", INFO);
    public final static ErCoreErrorId BL_PURCH_TR_CHK_DUPLICATE_SUB_ED = new ErCoreErrorId(C_BL_PURCH_TR_CHK_DUPLICATE_SUB_ED,
            "End of check duplicate subscription creation", INFO);
    public final static ErCoreErrorId BL_PURCH_TR_PACKAGE_ED = new ErCoreErrorId(C_BL_PURCH_TR_PACKAGE_ST,
            "Rating Engine authorised the purchase; Business logic to create a payment transaction, authorize, capture, provision, notify", INFO);
    public final static ErCoreErrorId BL_PURCH_TR_PACKAGE_ST = new ErCoreErrorId(C_BL_PURCH_TR_PACKAGE_ED,
            "Purchase Transaction is completed", INFO);
    public final static ErCoreErrorId PB_PURCHASE_PKG_ST = new ErCoreErrorId(C_PB_PURCHASE_PKG_ST,
            "Purchase Session starting", INFO);
    public final static ErCoreErrorId PB_PURCHASE_PKG_ED = new ErCoreErrorId(C_PB_PURCHASE_PKG_ED,
            "End of purchase session", INFO);
    public final static ErCoreErrorId SB_FIND_ACC_BY_PK_ST = new ErCoreErrorId(C_SB_FIND_ACC_BY_PK_ST,
            "Call to DB to retrieve Account details", INFO);
    public final static ErCoreErrorId SB_FIND_ACC_BY_PK_ED = new ErCoreErrorId(C_SB_FIND_ACC_BY_PK_ED,
            "End of call to DB to retrieve Account Deatils ", INFO);
    public final static ErCoreErrorId SB_GET_PROMOCODE_USED_ST = new ErCoreErrorId(C_SB_GET_PROMOCODE_USED_ST,
            "Call to DB to retrieve All promocodes used", INFO);
    public final static ErCoreErrorId SB_GET_PROMOCODE_USED_ED = new ErCoreErrorId(C_SB_GET_PROMOCODE_USED_ED,
            "End of call to DB to retrieve all promocodes used", INFO);
    public final static ErCoreErrorId RT_EXT_RATE_PACKAGE_ST = new ErCoreErrorId(C_RT_EXT_RATE_PACKAGE_ST,
            "Ext Rating Engine rates all Package options for a specic ID", INFO);
    public final static ErCoreErrorId RT_EXT_RATE_PACKAGE_ED = new ErCoreErrorId(C_RT_EXT_RATE_PACKAGE_ED,
            "End of rating on packages options ", INFO);


    public final static ErCoreErrorId RT_RATE_PACKAGE_ST = new ErCoreErrorId(C_RT_RATE_PACKAGE_ST,
            "It calculates valid package options and sends each package option to the external rating engine", INFO);
    public final static ErCoreErrorId RT_RATE_PACKAGE_ED = new ErCoreErrorId(C_RT_RATE_PACKAGE_ED,
            "End of Get Package Valid options ", INFO);

    public final static ErCoreErrorId RT_GET_PKG_PURCH_OPT_ST = new ErCoreErrorId(C_RT_GET_PKG_PURCH_OPT_ST,
            "It calculates valid package options and sends each package option to the external rating engine", INFO);
    public final static ErCoreErrorId RT_GET_PKG_PURCH_OPT_ED = new ErCoreErrorId(C_RT_GET_PKG_PURCH_OPT_ED,
            "End of Get Packge Valid options ", INFO);

    public final static ErCoreErrorId RT_DISC_SRV_PURCH_OPT_ST = new ErCoreErrorId(C_RT_DISC_SRV_PURCH_OPT_ST,
            "Return valid purchase options for a specific service ID", INFO);
    public final static ErCoreErrorId RT_DISC_SRV_PURCH_OPT_ED = new ErCoreErrorId(C_RT_DISC_SRV_PURCH_OPT_ED,
            "End of discover  purchase Options ", INFO);
    public final static ErCoreErrorId PB_FIND_PKG_W_SERV_ST = new ErCoreErrorId(C_PB_FIND_PKG_W_SERV_ST,
            "Returns all of the packages in the catalog matching the input CatalogService", INFO);
    public final static ErCoreErrorId PB_FIND_PKG_W_SERV_ED = new ErCoreErrorId(C_PB_FIND_PKG_W_SERV_ED,
            "End of API call findPackagesWithService", INFO);
    public final static ErCoreErrorId BL_PURCH_TR_CALL_PROVISION_SUB_ST = new ErCoreErrorId(C_BL_PURCH_TR_CALL_PROVISION_SUB_ST,
            "Calling provisionHandler to send provisioning request", INFO);
    public final static ErCoreErrorId BL_PURCH_TR_CALL_PROVISION_SUB_ED = new ErCoreErrorId(C_BL_PURCH_TR_CALL_PROVISION_SUB_ED,
            "End of call to provisionHandler", INFO);
    public final static ErCoreErrorId BL_PURCH_TR_SEND_NOTIF_SUB_ST = new ErCoreErrorId(C_BL_PURCH_TR_CALL_PROVISION_SUB_ST,
            "Calling notificationHandler to send notification request", INFO);
    public final static ErCoreErrorId BL_PURCH_TR_SEND_NOTIF_SUB_ED = new ErCoreErrorId(C_BL_PURCH_TR_CALL_PROVISION_SUB_ED,
            "End of call to notificationHandler", INFO);
    public final static ErCoreErrorId DEL_UPDATE_SERVICE_STATUS_ST = new ErCoreErrorId(C_DEL_UPDATE_SERVICE_STATUS_ST,
            "Initiate FindPackagesWithServiceId Session", INFO);
    public final static ErCoreErrorId DEL_USAGE_COMPLETE_ST = new ErCoreErrorId(C_DEL_USAGE_COMPLETE_ST,
            "Initiate UsageComplete Session", INFO);
    public final static ErCoreErrorId DEL_FIND_PKG_W_SERV_ST = new ErCoreErrorId(C_DEL_FIND_PKG_W_SERV_ST,
            "Initiate UsageComplete Session", INFO);

    public final static ErCoreErrorId BL_PURCH_TR_CREATE_SUB_ST = new ErCoreErrorId(C_BL_PURCH_TR_CREATE_SUB_ST,"Create subscription",INFO);

    public final static ErCoreErrorId BL_PURCH_TR_CREATE_SUB_ED = new ErCoreErrorId(C_BL_PURCH_TR_CREATE_SUB_ED,"End of Subscription creation", INFO );


    /************************************************************************/
    // ER CORE
    /************************************************************************/

    public final static ErCoreErrorId MALFORMED_URL = new ErCoreErrorId(C_MALFORMED_URL,"Malformed URL: ", ERROR );

    /************************************************************************/
    // Audit Logging for Pricing Tool
    /************************************************************************/

    public final static ErCoreErrorId PT_SAVE_PRICEPLAN_DB = new ErCoreErrorId(C_PT_SAVE_PRICEPLAN_DB, "PT: Saving PricePlan in Database", INFO);
    public final static ErCoreErrorId PT_SAVE_PRICEPLAN_XML = new ErCoreErrorId(C_PT_SAVE_PRICEPLAN_XML, "PT: Saving PricePlan as an XML", INFO);

    public final static ErCoreErrorId PT_ADD_USER = new ErCoreErrorId(C_PT_ADD_USER, "PT: Added Administrative User ", INFO);
    public final static ErCoreErrorId PT_EDIT_USER = new ErCoreErrorId(C_PT_EDIT_USER, "PT: Updated Administrative User ", INFO);
    public final static ErCoreErrorId PT_REMOVE_USER = new ErCoreErrorId(C_PT_REMOVE_USER, "PT: Deleted Administrative User ", INFO);

    public final static ErCoreErrorId PT_ADD_USER_GROUP = new ErCoreErrorId(C_PT_ADD_USER_GROUP, "PT: Added Administrative User Group ", INFO);
    public final static ErCoreErrorId PT_EDIT_USER_GROUP = new ErCoreErrorId(C_PT_EDIT_USER_GROUP, "PT: Updated Administrative User Group ", INFO);
    public final static ErCoreErrorId PT_REMOVE_USER_GROUP = new ErCoreErrorId(C_PT_REMOVE_USER_GROUP, "PT: Deleted Administrative User Group ", INFO);

    public final static ErCoreErrorId PT_ADD_PARTNER = new ErCoreErrorId(C_PT_ADD_PARTNER, "PT: Added Partner ", INFO);
    public final static ErCoreErrorId PT_EDIT_PARTNER = new ErCoreErrorId(C_PT_EDIT_PARTNER, "PT: Updated Partner ", INFO);
    public final static ErCoreErrorId PT_REMOVE_PARTNER = new ErCoreErrorId(C_PT_REMOVE_PARTNER, "PT: Deleted Partner ", INFO);
    public final static ErCoreErrorId PT_ADD_PACKAGE = new ErCoreErrorId(C_PT_ADD_PACKAGE, "PT: Added Package ", INFO);
    public final static ErCoreErrorId PT_EDIT_PACKAGE = new ErCoreErrorId(C_PT_EDIT_PACKAGE, "PT: Updated Package ", INFO);
    public final static ErCoreErrorId PT_REMOVE_PACKAGE = new ErCoreErrorId(C_PT_REMOVE_PACKAGE, "PT: Deleted Package ", INFO);
    public final static ErCoreErrorId PT_ADD_SERVICE = new ErCoreErrorId(C_PT_ADD_SERVICE, "PT: Added Service ", INFO);
    public final static ErCoreErrorId PT_EDIT_SERVICE = new ErCoreErrorId(C_PT_EDIT_SERVICE, "PT: Updated Service ", INFO);
    public final static ErCoreErrorId PT_REMOVE_SERVICE = new ErCoreErrorId(C_PT_REMOVE_SERVICE, "PT: Deleted Service ", INFO);

    public final static ErCoreErrorId PT_ADD_REFERENCE_DATA = new ErCoreErrorId(C_PT_ADD_REFERENCE_DATA, "PT: Adding Reference Data ", INFO);
    public final static ErCoreErrorId PT_EDIT_REFERENCE_DATA = new ErCoreErrorId(C_PT_EDIT_REFERENCE_DATA, "PT: Editing Reference Data ", INFO);
    public final static ErCoreErrorId PT_REMOVE_REFERENCE_DATA = new ErCoreErrorId(C_PT_REMOVE_REFERENCE_DATA, "PT: Removing Reference Data ", INFO);

    public final static ErCoreErrorId PT_ADD_CUSTOM_DATA = new ErCoreErrorId(C_PT_ADD_CUSTOM_DATA, "PT: Adding Custom Data ", INFO);
    public final static ErCoreErrorId PT_EDIT_CUSTOM_DATA = new ErCoreErrorId(C_PT_EDIT_CUSTOM_DATA, "PT: Editing Custom Data ", INFO);
    public final static ErCoreErrorId PT_REMOVE_CUSTOM_DATA = new ErCoreErrorId(C_PT_REMOVE_CUSTOM_DATA, "PT: Removing Custom Data ", INFO);

    public final static ErCoreErrorId PT_ADD_TAX_CODE = new ErCoreErrorId(C_PT_ADD_TAX_CODE, "PT: Adding Tax Code ", INFO);
    public final static ErCoreErrorId PT_EDIT_TAX_CODE = new ErCoreErrorId(C_PT_EDIT_TAX_CODE, "PT: Editing Tax Code ", INFO);
    public final static ErCoreErrorId PT_REMOVE_TAX_CODE = new ErCoreErrorId(C_PT_REMOVE_TAX_CODE, "PT: Removing Tax Code ", INFO);
    public final static ErCoreErrorId PT_ADD_TAX_RATE = new ErCoreErrorId(C_PT_ADD_TAX_RATE, "PT: Adding Tax Rate ", INFO);
    public final static ErCoreErrorId PT_EDIT_TAX_RATE = new ErCoreErrorId(C_PT_EDIT_TAX_RATE, "PT: Editing Tax Rate ", INFO);
    public final static ErCoreErrorId PT_REMOVE_TAX_RATE = new ErCoreErrorId(C_PT_REMOVE_TAX_RATE, "PT: Removing Tax Rate ", INFO);

    public final static ErCoreErrorId PT_ADD_DATE_TIME_MODEL = new ErCoreErrorId(C_PT_ADD_DATE_TIME_MODEL, "PT: Adding Date Time Model ", INFO);
    public final static ErCoreErrorId PT_EDIT_DATE_TIME_MODEL = new ErCoreErrorId(C_PT_EDIT_DATE_TIME_MODEL, "PT: Editing Date Time Model ", INFO);
    public final static ErCoreErrorId PT_REMOVE_DATE_TIME_MODEL = new ErCoreErrorId(C_PT_REMOVE_DATE_TIME_MODEL, "PT: Removing Date Time Model ", INFO);

    public final static ErCoreErrorId PT_REMOVE_DATE_TIME_TIER = new ErCoreErrorId(C_PT_REMOVE_DATE_TIME_TIER, "PT: Removing Date Time Tier ", INFO);

    /**********************************************************************/

    /**********************************************************************/
    /**            Decoupling Application                                **/
    /**********************************************************************/

    public final static ErCoreErrorId DC_TRANSPORT_FAILURE = new ErCoreErrorId(C_DC_TRANSPORT_FAILURE, "DC: Error in sending response. ", FATAL);
    public final static ErCoreErrorId DC_TRANSPORT_SUCCESS = new ErCoreErrorId(C_DC_TRANSPORT_SUCCESS, "DC: Error in sending response. ", CLEAR);

    public final static ErCoreErrorId DC_PROCESS_ERROR = new ErCoreErrorId(C_DC_PROCESS_ERROR, "DC: Error in processing request. ", ERROR);
    public final static ErCoreErrorId DC_VALIDATION_ERROR = new ErCoreErrorId(C_DC_VALIDATION_ERROR, "DC: Error in validating response. ", ERROR);

    public final static ErCoreErrorId DC_PROCESS_ST = new ErCoreErrorId(C_DC_PROCESS_ST, "DC: Start of process method.", INFO);
    public final static ErCoreErrorId DC_PROCESS_ED = new ErCoreErrorId(C_DC_PROCESS_ED, "DC: End of process method. ", INFO);

    /**********************************************************************/
    /**                               WPI                                **/
    /**********************************************************************/

//    public final static ErCoreErrorId WPI_TRANSPORT_FAILURE = new ErCoreErrorId(C_WPI_TRANSPORT_FAILURE, "WPI: Error in sending response. ", FATAL);
//    public final static ErCoreErrorId WPI_TRANSPORT_SUCCESS = new ErCoreErrorId(C_WPI_TRANSPORT_SUCCESS, "WPI: Error in sending response. ", CLEAR);
//
//    public final static ErCoreErrorId WPI_PROCESS_ERROR = new ErCoreErrorId(C_WPI_PROCESS_ERROR, "WPI: Error in processing request. ", ERROR);
//    public final static ErCoreErrorId WPI_VALIDATION_ERROR = new ErCoreErrorId(C_WPI_VALIDATION_ERROR, "WPI: Error in validating response. ", ERROR);
//
//    public final static ErCoreErrorId WPI_PROCESS_ST = new ErCoreErrorId(C_WPI_PROCESS_ST, "WPI: Start of process method.", INFO);
//    public final static ErCoreErrorId WPI_PROCESS_ED = new ErCoreErrorId(C_WPI_PROCESS_ED, "WPI: End of process method. ", INFO);

    /**********************************************************************/
    /**                               IF                                 **/
    /**********************************************************************/

    // ERROR
    public final static ErCoreErrorId IF_PROVISIONING_SERVICE_HANDLER_PROCESS_ERROR = new ErCoreErrorId(C_IF_PROVISIONING_SERVICE_HANDLER_PROCESS_ERROR,
            "IF: Error in Provisioning service handler's process method.", ERROR);
    public final static ErCoreErrorId IF_SYSTEM_HANDLER_PROCESS_ERROR = new ErCoreErrorId(C_IF_SYSTEM_HANDLER_PROCESS_ERROR,
            "IF: Error in System handler's process method.", ERROR);
    public final static ErCoreErrorId IF_PROPERTIES_FILE_ERROR = new ErCoreErrorId(C_IF_PROPERTIES_FILE_ERROR, "IF: er2.properties file.", ERROR);
    public final static ErCoreErrorId IF_MAIL_SEND_ERROR = new ErCoreErrorId(C_IF_MAIL_SEND_ERROR, "IF: Error sending email.", ERROR);
    public final static ErCoreErrorId IF_JMS_PAYLOAD_ERROR = new ErCoreErrorId(C_IF_JMS_PAYLOAD_ERROR, "IF: Error in message payload.", ERROR);
    public final static ErCoreErrorId IF_JMS_HANDLER_ERROR = new ErCoreErrorId(C_IF_JMS_HANDLER_ERROR, "IF: Handler returned null response object, or Message of unknown type.", ERROR);
    public final static ErCoreErrorId IF_JMS_PROCESSING_ERROR = new ErCoreErrorId(C_IF_JMS_PROCESSING_ERROR, "IF: Exception thrown while processing message.", ERROR);
    public final static ErCoreErrorId IF_NOTIFICATION_BEAN_ERROR = new ErCoreErrorId(C_IF_NOTIFICATION_BEAN_ERROR, "IF: Error with Notification Bean. ", ERROR);
    public final static ErCoreErrorId IF_NOTIFICATION_PPUPDATE_ERROR = new ErCoreErrorId(C_IF_NOTIFICATION_PPUPDATE_ERROR, "IF: Error with Notification PPUpdate. ", ERROR);
    public final static ErCoreErrorId IF_NOTIFICATION_PPUPDATE_SUBSCRIBE_HANDLER_ERROR = new ErCoreErrorId(C_IF_NOTIFICATION_PPUPDATE_SUBSCRIBE_HANDLER_ERROR, "IF: Error with Notification PPUpdate Subscribe. ", ERROR);
    public final static ErCoreErrorId IF_NOTIFICATION_VELOCITY_ERROR = new ErCoreErrorId(C_IF_NOTIFICATION_VELOCITY_ERROR, "IF: org.apach.velocity ERROR. ", ERROR);
    public final static ErCoreErrorId IF_DATABASE_ERROR = new ErCoreErrorId(C_IF_DATABASE_ERROR, "IF: Database ERROR. ", ERROR);
    public final static ErCoreErrorId IF_PAYMENT_AUTH_HANDLER_ERROR = new ErCoreErrorId(C_IF_PAYMENT_AUTH_HANDLER_ERROR, "IF: Payment Auth Handler ERROR. ", ERROR);
    public final static ErCoreErrorId IF_PROVISION_SCANNER_MESSAGE_RESEND_ERROR = new ErCoreErrorId(C_IF_PROVISION_SCANNER_MESSAGE_RESEND_ERROR, "IF: Provisioning Scanner ", ERROR);
    public final static ErCoreErrorId IF_PROVISION_SCANNER_SCAN_ERROR = new ErCoreErrorId(C_IF_PROVISION_SCANNER_SCAN_ERROR, "IF: Provisioning Scanner ", ERROR);
    public final static ErCoreErrorId IF_PROVISION_PACKAGE_HANDLER_ERROR = new ErCoreErrorId(C_IF_PROVISION_PACKAGE_HANDLER_ERROR, "IF: Provision Package Handler: ", ERROR);
    public final static ErCoreErrorId IF_ROUTING_HTTP_INVOKER_HANDLER_ERROR = new ErCoreErrorId(C_IF_ROUTING_HTTP_INVOKER_HANDLER_ERROR, "IF: Http Invoker Handler: ", ERROR);
    public final static ErCoreErrorId IF_ROUTING_MESSAGE_SERVICE_CONFIG_ERROR = new ErCoreErrorId(C_IF_ROUTING_MESSAGE_SERVICE_CONFIG_ERROR, "IF: MessageServiceConfig: ", ERROR);
    public final static ErCoreErrorId IF_ROUTING_MESSAGE_SERVICE_BUILDER_ERROR = new ErCoreErrorId(C_IF_ROUTING_MESSAGE_SERVICE_BUILDER_ERROR, "IF: Message Service Builder: ", ERROR);
    public final static ErCoreErrorId IF_ROUTING_FIND_MESSAGE_SERVICES_ERROR = new ErCoreErrorId(C_IF_ROUTING_FIND_MESSAGE_SERVICES_ERROR, "IF: No Message services found: ", ERROR);

    // INFO
    public final static ErCoreErrorId IF_JMS_ONMESSAGE_ST = new ErCoreErrorId(C_IF_JMS_ONMESSAGE_ST, "IF: Start of onMessage method.", INFO);
    public final static ErCoreErrorId IF_JMS_ONMESSAGE_ED = new ErCoreErrorId(C_IF_JMS_ONMESSAGE_ED, "IF: End of onMessage method.", INFO);
    public final static ErCoreErrorId IF_JMS_MESSAGE = new ErCoreErrorId(C_IF_JMS_MESSAGE, "IF: Processing Message.", INFO);
    public final static ErCoreErrorId IF_NOTIFICATION_HANDLER_PROCESS_ST = new ErCoreErrorId(C_IF_NOTIFICATION_HANDLER_PROCESS_ST, "IF: Start of process method.", INFO);
    public final static ErCoreErrorId IF_NOTIFICATION_HANDLER_PROCESS_ED = new ErCoreErrorId(C_IF_NOTIFICATION_HANDLER_PROCESS_ED, "IF: End of process method.", INFO);
    public final static ErCoreErrorId IF_NOTIFICATION_PPUPDATE_HANDLER_PROCESS_ST = new ErCoreErrorId(C_IF_NOTIFICATION_PPUPDATE_HANDLER_PROCESS_ST, "IF: Start of process method.", INFO);
    public final static ErCoreErrorId IF_NOTIFICATION_PPUPDATE_HANDLER_PROCESS_ED = new ErCoreErrorId(C_IF_NOTIFICATION_PPUPDATE_HANDLER_PROCESS_ED, "IF: End of process method.", INFO);
    public final static ErCoreErrorId IF_NOTIFICATION_PPU_SUBSCRIBER_HANDLER_PROCESS_ST = new ErCoreErrorId(C_IF_NOTIFICATION_PPU_SUBSCRIBER_HANDLER_PROCESS_ST, "IF: Start of process method.", INFO);
    public final static ErCoreErrorId IF_NOTIFICATION_PPU_SUBSCRIBER_HANDLER_PROCESS_ED = new ErCoreErrorId(C_IF_NOTIFICATION_PPU_SUBSCRIBER_HANDLER_PROCESS_ED, "IF: Start of process method.", INFO);
    public final static ErCoreErrorId IF_NOTIFICATION_DB_CACHE = new ErCoreErrorId(C_IF_NOTIFICATION_DB_CACHE, "IF: DB Cache.", INFO);
    public final static ErCoreErrorId IF_PAYMENT_HANDLER_INFO = new ErCoreErrorId(C_IF_PAYMENT_HANDLER_INFO, "IF: Payment Handler.", INFO);
    public final static ErCoreErrorId IF_PROVISION_DATA_STORE_INFO = new ErCoreErrorId(C_IF_PROVISION_DATA_STORE_INFO, "IF: Provision Data Store.", INFO);
    public final static ErCoreErrorId IF_PROVISION_SCANNER_INFO = new ErCoreErrorId(C_IF_PROVISION_SCANNER_INFO, "IF: Provision Scanner.", INFO);
    public final static ErCoreErrorId IF_PROVISION_PACKAGE_HANDLER_PROCESS_ST = new ErCoreErrorId(C_IF_PROVISION_PACKAGE_HANDLER_PROCESS_ST, "IF: Start of process method.", INFO);
    public final static ErCoreErrorId IF_PROVISION_PACKAGE_HANDLER_PROCESS_ED = new ErCoreErrorId(C_IF_PROVISION_PACKAGE_HANDLER_PROCESS_ED, "IF: End of process method.", INFO);
    public final static ErCoreErrorId IF_PROVISION_SERVICE_HANDLER_PROCESS_ST = new ErCoreErrorId(C_IF_PROVISION_SERVICE_HANDLER_PROCESS_ST, "IF: Start of process method.", INFO);
    public final static ErCoreErrorId IF_PROVISION_SERVICE_HANDLER_PROCESS_ED = new ErCoreErrorId(C_IF_PROVISION_SERVICE_HANDLER_PROCESS_ED, "IF: End of process method.", INFO);
    public final static ErCoreErrorId IF_ROUTING_MESSAGE_INFO = new ErCoreErrorId(C_IF_ROUTING_MESSAGE_INFO, "IF: Message Service Config.", INFO);
    public final static ErCoreErrorId IF_SYSTEM_HANDLER_INFO = new ErCoreErrorId(C_IF_SYSTEM_HANDLER_INFO, "IF: Message Service Config.", INFO);
    public final static ErCoreErrorId IF_SYSTEM_HANDLER_PROCESS_ST = new ErCoreErrorId(C_IF_SYSTEM_HANDLER_PROCESS_ST, "IF: Start of process method.", INFO);
    public final static ErCoreErrorId IF_SYSTEM_HANDLER_PROCESS_ED = new ErCoreErrorId(C_IF_SYSTEM_HANDLER_PROCESS_ED, "IF: End of process method.", INFO);
    public final static ErCoreErrorId IF_VALIDATE_MSISDN_HANDLER_PROCESS_ST = new ErCoreErrorId(C_IF_VALIDATE_MSISDN_HANDLER_PROCESS_ST, "IF: Start of process method.", INFO);
    public final static ErCoreErrorId IF_VALIDATE_MSISDN_HANDLER_PROCESS_ED = new ErCoreErrorId(C_IF_VALIDATE_MSISDN_HANDLER_PROCESS_ED, "IF: End of process method.", INFO);

    // WARN
    public final static ErCoreErrorId IF_PROPERTIES_FILE_WARNING = new ErCoreErrorId(C_IF_PROPERTIES_FILE_WARNING, "IF: er2.properties file.", WARN);
    public final static ErCoreErrorId IF_PROVISION_DATA_STORE_WARNING = new ErCoreErrorId(C_IF_PROVISION_DATA_STORE_WARNING, "IF: Provision DataStore.", WARN);
    public final static ErCoreErrorId IF_PROVISION_SCANNER_WARNING = new ErCoreErrorId(C_IF_PROVISION_SCANNER_WARNING, "IF: Provision Scanner.", WARN);

    /**********************************************************************/


    /*
     *  FSecure Provision Service Handler
     */
    public final static ErCoreErrorId FS_PSH_ACTIVATE_ST = new ErCoreErrorId(C_FS_PSH_ACTIVATE_ST, "FSecureProvisionServiceHandler: Start of activate().", INFO);
    public final static ErCoreErrorId FS_PSH_ACTIVATE_ED = new ErCoreErrorId(C_FS_PSH_ACTIVATE_ED, "FSecureProvisionServiceHandler: End of activate().", INFO);
    public final static ErCoreErrorId FS_PSH_DEACTIVATE_ST = new ErCoreErrorId(C_FS_PSH_DEACTIVATE_ST, "FSecureProvisionServiceHandler: Start of deactivate().", INFO);
    public final static ErCoreErrorId FS_PSH_DEACTIVATE_ED = new ErCoreErrorId(C_FS_PSH_DEACTIVATE_ED, "FSecureProvisionServiceHandler: End of deactivate().", INFO);
    public final static ErCoreErrorId FS_PSH_REACTIVATE_ST = new ErCoreErrorId(C_FS_PSH_REACTIVATE_ST, "FSecureProvisionServiceHandler: Start of reactivate().", INFO);
    public final static ErCoreErrorId FS_PSH_REACTIVATE_ED = new ErCoreErrorId(C_FS_PSH_REACTIVATE_ED, "FSecureProvisionServiceHandler: End of reactivate().", INFO);
    public final static ErCoreErrorId FS_PSH_SUSPEND_ST = new ErCoreErrorId(C_FS_PSH_SUSPEND_ST, "FSecureProvisionServiceHandler: Start of suspend().", INFO);
    public final static ErCoreErrorId FS_PSH_SUSPEND_ED = new ErCoreErrorId(C_FS_PSH_SUSPEND_ED, "FSecureProvisionServiceHandler: End of suspend().", INFO);
    public final static ErCoreErrorId FS_PSH_UPDATE_ST = new ErCoreErrorId(C_FS_PSH_UPDATE_ST, "FSecureProvisionServiceHandler: Start of update().", INFO);
    public final static ErCoreErrorId FS_PSH_UPDATE_ED = new ErCoreErrorId(C_FS_PSH_UPDATE_ED, "FSecureProvisionServiceHandler: End of update().", INFO);

    public final static ErCoreErrorId FS_PSH_FS_ERR = new ErCoreErrorId(C_FS_PSH_FS_ERR, "FSecureProvisionServiceHandler: Error in FSecure system", ERROR);


    /*
     *  Dexterra Provision Service Handler
     */
    public final static ErCoreErrorId DX_PSH_ACTIVATE_ST = new ErCoreErrorId(C_DX_PSH_ACTIVATE_ST, "DexterraProvisionServiceHandler: Start of activate().", INFO);
    public final static ErCoreErrorId DX_PSH_ACTIVATE_ED = new ErCoreErrorId(C_DX_PSH_ACTIVATE_ED, "DexterraProvisionServiceHandler: End of activate().", INFO);
    public final static ErCoreErrorId DX_PSH_DEACTIVATE_ST = new ErCoreErrorId(C_DX_PSH_DEACTIVATE_ST, "DexterraProvisionServiceHandler: Start of deactivate().", INFO);
    public final static ErCoreErrorId DX_PSH_DEACTIVATE_ED = new ErCoreErrorId(C_DX_PSH_DEACTIVATE_ED, "DexterraProvisionServiceHandler: End of deactivate().", INFO);
    public final static ErCoreErrorId DX_PSH_REACTIVATE_ST = new ErCoreErrorId(C_DX_PSH_REACTIVATE_ST, "DexterraProvisionServiceHandler: Start of reactivate().", INFO);
    public final static ErCoreErrorId DX_PSH_REACTIVATE_ED = new ErCoreErrorId(C_DX_PSH_REACTIVATE_ED, "DexterraProvisionServiceHandler: End of reactivate().", INFO);
    public final static ErCoreErrorId DX_PSH_SUSPEND_ST = new ErCoreErrorId(C_DX_PSH_SUSPEND_ST, "DexterraProvisionServiceHandler: Start of suspend().", INFO);
    public final static ErCoreErrorId DX_PSH_SUSPEND_ED = new ErCoreErrorId(C_DX_PSH_SUSPEND_ED, "DexterraProvisionServiceHandler: End of suspend().", INFO);
    public final static ErCoreErrorId DX_PSH_UPDATE_ST = new ErCoreErrorId(C_DX_PSH_UPDATE_ST, "DexterraProvisionServiceHandler: Start of update().", INFO);
    public final static ErCoreErrorId DX_PSH_UPDATE_ED = new ErCoreErrorId(C_DX_PSH_UPDATE_ED, "DexterraProvisionServiceHandler: End of update().", INFO);

    public final static ErCoreErrorId DX_PSH_DX_ERR = new ErCoreErrorId(C_DX_PSH_DX_ERR, "DexterraProvisionServiceHandler: Error in Dexterra system", ERROR);


    /*
     *  Front End Proxy
     */
    public final static ErCoreErrorId FEP_DOPOST_ST = new ErCoreErrorId(C_FEP_DOPOST_ST, "FrontEndProxy: Start of doPost().", INFO);
    public final static ErCoreErrorId FEP_DOPOST_ED = new ErCoreErrorId(C_FEP_DOPOST_ED, "FrontEndProxy:: End of doPost().", INFO);
    public final static ErCoreErrorId FEP_HANDLE_ST = new ErCoreErrorId(C_FEP_HANDLE_ST, "FrontEndProxy: Start of handle().", INFO);
    public final static ErCoreErrorId FEP_HANDLE_ED = new ErCoreErrorId(C_FEP_HANDLE_ED, "FrontEndProxy:: End of handle().", INFO);
    public final static ErCoreErrorId FEP_ROUTE_ST = new ErCoreErrorId(C_FEP_ROUTE_ST, "FrontEndProxy: Start of route().", INFO);
    public final static ErCoreErrorId FEP_ROUTE_ED = new ErCoreErrorId(C_FEP_ROUTE_ED, "FrontEndProxy:: End of route().", INFO);
    public final static ErCoreErrorId FEP_ERR = new ErCoreErrorId(C_FEP_ERR, "FrontEndProxy: Error", ERROR);
    public final static ErCoreErrorId FEP_IP_LOG = new ErCoreErrorId(C_FEP_IP_LOG, "FrontEndProxy: IP address log", ERROR);



    public final static ErCoreErrorId GENERIC_LOGGING_ERROR = new ErCoreErrorId(C_LOGGING,
            "Generic Logging Error.", ERROR);

    public final static ErCoreErrorId GENERIC_NULL_OBJECT = new ErCoreErrorId(C_GENERIC_NULL_OBJECT,
            "Null Object found.", ERROR);

    public final static ErCoreErrorId NULL_INPUT_PARAMETER = new ErCoreErrorId(C_NULL_INPUT_PARAMETER,
            "Input Parameter is null. ", ERROR);

    public final static ErCoreErrorId UNKNOWN_ERROR = new ErCoreErrorId(C_UNKNOWN_ERROR,
            "Unexpected Exception thrown. ", ERROR);
    
    //MQC 7625 Start
    public final static ErCoreErrorId UNEXPECTED_DB_ROLLBACK_ERROR = new ErCoreErrorId(C_UNEXPECTED_DB_ERROR,
            "Unexpected Exception thrown. Internal Database Error caused a database rollback ", ERROR);
    //MQC 7625 End

    public final static ErCoreErrorId PROPERTIES_FILE_ERROR = new ErCoreErrorId(C_PROPERTIES_FILE_ERROR,
            "There is an error with the properties file.  ", ERROR);

    /**********************************************************************/
    public final static ErCoreErrorId JNDI_LOOKUP_FAILURE_FOR_ACCOUNT_BEAN = new ErCoreErrorId(C_JNDI_LOOKUP_FAILURE_FOR_ACCOUNT_BEAN,
            "JNDI lookup failure for account bean. ", FATAL, true);

    public final static ErCoreErrorId JNDI_LOOKUP_FAILURE_FOR_CHARGING_BEAN = new ErCoreErrorId(C_JNDI_LOOKUP_FAILURE_FOR_CHARGING_BEAN,
            "JNDI lookup failure for charging bean. ", FATAL, true);

    public final static ErCoreErrorId JNDI_LOOKUP_FAILURE_FOR_NOTIFICATION_BEAN = new ErCoreErrorId(C_JNDI_LOOKUP_FAILURE_FOR_NOTIFICATION_BEAN,
            "JNDI lookup failure for notification bean. ", FATAL, true);

    public final static ErCoreErrorId JNDI_LOOKUP_FAILURE_FOR_PAYMENT_BEAN = new ErCoreErrorId(C_JNDI_LOOKUP_FAILURE_FOR_PAYMENT_BEAN,
            "JNDI lookup failure for payment bean. ", FATAL, true);

    public final static ErCoreErrorId JNDI_LOOKUP_FAILURE_FOR_PROVISION_BEAN = new ErCoreErrorId(C_JNDI_LOOKUP_FAILURE_FOR_PROVISION_BEAN,
            "JNDI lookup failure for provision bean. ", FATAL, true);

    public final static ErCoreErrorId JNDI_LOOKUP_FAILURE_FOR_PURCHASE_BEAN = new ErCoreErrorId(C_JNDI_LOOKUP_FAILURE_FOR_PURCHASE_BEAN,
            "JNDI lookup failure for purchase bean. ", FATAL, true);

    public final static ErCoreErrorId JNDI_LOOKUP_FAILURE_FOR_ROUTABLE_BEAN = new ErCoreErrorId(C_JNDI_LOOKUP_FAILURE_FOR_ROUTABLE_BEAN,
            "JNDI lookup failure for routable bean. ", FATAL, true);

    public final static ErCoreErrorId JNDI_LOOKUP_FAILURE_FOR_SELFCARE_BEAN = new ErCoreErrorId(C_JNDI_LOOKUP_FAILURE_FOR_SELFCARE_BEAN,
            "JNDI lookup failure for selfcare bean. ", FATAL, true);

    
    /*
     * MQC 5190
     */
    public final static ErCoreErrorId IF_CONN_TIMEOUT_ERROR = new ErCoreErrorId(C_IF_CONN_TIMEOUT_ERROR,
            "IF_CONN_TIMEOUT", ERROR);

    //CR1425
    public final static ErCoreErrorId IF_CONN_CERT_INVALID_ERROR = new ErCoreErrorId(C_IF_CONN_CERT_INVALID_ERROR,
                    "Errors authenticating server certificate (might be expired)", ERROR);

    public final static ErCoreErrorId IF_CONN_CERT_NOT_TRUSTED_ERROR = new ErCoreErrorId(C_IF_CONN_CERT_NOT_TRUSTED_ERROR,
            "Server certificate is not trusted", ERROR);

    public final static ErCoreErrorId IF_MESSAGE_XML_PARSE_ERROR = new ErCoreErrorId(C_IF_MESSAGE_XML_PARSE_ERROR,
            "IF XML message couldn't be parsed", ERROR);

    //CR1791
    public final static ErCoreErrorId ENHANCE_PURCHASE_FAILED_INVALID_STATUS = new ErCoreErrorId(C_ENHANCE_PURCHASE_FAILED_INVALID_STATUS,
    		"Invalid status in enhanced purchase ", ERROR);

    public final static ErCoreErrorId PURCHASE_FAILED_INVALID_START_AND_END_DATES = new ErCoreErrorId(C_PURCHASE_FAILED_INVALID_START_AND_END_DATES,
            "Start date greater than End date in purchase request", ERROR);

    public final static ErCoreErrorId PURCHASE_FAILED_INVALID_END_DATE = new ErCoreErrorId(C_PURCHASE_FAILED_INVALID_END_DATE,
            "End date is in the past in purchase request", ERROR);

    public final static ErCoreErrorId PURCHASE_FAILED_MISSING_PACKAGE_ID_INPUT_PARAM = new ErCoreErrorId(C_PURCHASE_FAILED_MISSING_PACKAGE_ID_INPUT_PARAM,
            "Missing package-id or rating attributes short-package-id input parameter", ERROR);
    
    public final static ErCoreErrorId MODIFY_SUBSCRPITION_FAILED_INVALID_START_AND_END_DATES = new ErCoreErrorId(C_MODIFY_SUBSCRPITION_FAILED_INVALID_START_AND_END_DATES,
            "Start date greater than End date in modify subscription request", ERROR);
    
    public final static ErCoreErrorId PURCHASE_FAILED_MORE_THAN_ONE_MATCHING_PRICEPOINT_EXISTS = new ErCoreErrorId(C_PURCHASE_FAILED_MORE_THAN_ONE_MATCHING_PRICEPOINT_EXISTS,
            "Rating attributes in purchase request match more than one pricepoint.", ERROR);

    //CR1425
    
    //MQC 7091
    public final static ErCoreErrorId HTTP_CONNECTION_POOL_TIMEOUT_ERROR = new ErCoreErrorId(C_HTTP_CONNECTION_POOL_TIMEOUT_ERROR,
            "Timeout error waiting for a http connection from the http connection pool manager", ERROR);
    
    //MQC 7332
    public final static ErCoreErrorId PAYMENT_CAPTURE_IF_RESPONSE_REJECTED_DENIED_ERROR = new ErCoreErrorId(C_PAYMENT_CAPTURE_IF_RESPONSE_REJECTED_DENIED_ERROR,
    		PAYMENT_CAPTURE_IF_RESPONSE_REJECTED_DENIED_ERROR_STR, ERROR);
    
    public final static ErCoreErrorId GOODWILL_CREDIT_PARTNER_NOT_RECOGNISED = new ErCoreErrorId(C_GOODWILL_CREDIT_PARTNER_NOT_RECOGNISED, "goodwill credit partner not recognised", ERROR);
    public final static ErCoreErrorId GOODWILL_CREDIT_LIMITS_NOT_SET = new ErCoreErrorId(C_GOODWILL_CREDIT_LIMITS_NOT_SET, "goodwill credit limits not set", ERROR);
    public final static ErCoreErrorId GOODWILL_CREDIT_NOT_A_GOODWILL_CREDIT_PACKAGE = new ErCoreErrorId(C_GOODWILL_CREDIT_NOT_A_GOODWILL_CREDIT_PACKAGE, "goodwill credit not a valid goodwill credit package", ERROR);
    
    
    
    
    // ER8 NFR
    public final static int ACCOUNT_NOT_FOUND_EXCEPTION = 0;
    public final static int ACCOUNT_CREATE_EXCEPTION = 1;
    public final static int BATCH_EXCEPTION = 2;
    public final static int ECOMMERCE_SYSTEM_EXCEPTION = 3;
    public final static int PURCHASE_AUTHORIZATION_EXCEPTION = 4;
    public final static int RATING_EXCEPTION = 5;
    public final static int SCALABILITY_LOG_EXCEPTION = 6;
    public final static int SUBS_MANAGEMENT_EXCEPTION = 7;
    public final static int TAX_EXCEPTION = 8;
    public final static int USAGE_AUTHORIZATION_EXCEPTION = 9;

    public final static int ECOMMERCE_EXCEPTION = 99;

    /**********************************************************************/

    /*
     * INSTANCE VARIABLES.
     */
    int code;
    String name;
    Object object;
    Exception mException;
    private String severity;
    private boolean manualClear;


    /**
     * For internal use only.
     */
    private ErCoreErrorId(int code, String name, String severity)    {
        this(code, name, severity, false);
    }

    /**
     * For internal use only.
     */
    private ErCoreErrorId(int code, String name, String severity,
            boolean manualClear)	{
        this.code = code;
        this.name = name;
        this.severity = severity;
        this.manualClear = manualClear;
    }

    public boolean isInvalidInput()    {
        return getCode() == C_INVALID_INPUT;
    }


    public boolean isAccountValidated()    {
        return getCode() == C_ACCOUNT_NOT_VALIDATED;
    }

    public boolean isSuccess()    {
        return getCode() == C_SUCCESS;
    }

    public boolean isUnauthorizedRenewal()    {
        return getCode() == C_UNAUTHROZIED_RENEWAL;
    }

    public boolean isAccountNotFound()    {
        return getCode() == C_MSISDN_NOT_FOUND;    
    }

    public boolean isDuplicateSubscription()    {
        return getCode() == C_DUPLICATE_SUBSCRIPTION;
    }


    public boolean isDuplicatePromoSubscription()	{
        return getCode() == C_DUPLICATE_PROMO_SUBSCRIPTION;
    }

    /**
     */
    public boolean isInvalidBan()   {
        return getCode() == C_INVALID_BAN;
    }

    /**
     */
    public String getName()    {
        return this.name;
    }

    /**
     */
    public void setObject(Object obj)    {
        this.object = obj;
    }

    /**
     */
    public Object getObject()    {
        return object;
    }

    /**
     */
    protected void setName(String s)    {
        this.name = s;
    }

    /**
     */
    public int getCode()    {
        return this.code;
    }

    /**
     */
    protected void setCode(int c)    {
        this.code = c;
    }

    public String getResourceName()    {
        return "ErCoreErrorId_" + getCode();
    }

    public Exception getException()    {
        return this.mException;
    }

    public void setException(Exception ex)    {
        this.mException = ex;
    }

    /**
     * Gets the severity level.  This can be one of several values defined
     * has constants in this class.
     * @returns severity - the String of the severity level.
     */
    public String getSeverity()    {
        return severity;
    }

    /**
     * Sets the severity level.  This can be one of several values defined
     * has constants in this class.
     * @params severity - the String of the severity level to set.
     */
    public void setSeverity(String severity)    {
        this.severity = severity;
    }

    /**
     * Gets the manualClear for this.
     * @returns manualClear - the boolean value.
     */
    public boolean isManualClear()    {
        return manualClear;
    }

    /**
     * Sets the manualClear for this.
     * @params manualClear - the boolean value to set.
     */
    public void setManualClear(boolean manualClear)    {
        this.manualClear = manualClear;
    }

    /**
     * This returns a String with attributes of this class.
     * @returns String of concatenated information including the name and code.
     */
    @Override
	public String toString()    {
        StringBuffer sb = new StringBuffer("{code=");
        sb.append(code);
        sb.append(" name=");
        sb.append(name);
        sb.append("}");
        return sb.toString();
    }

    /**
     * This method checks if an ErCoreErrorId object is the same as the current
     * object.
     * @params code - the object being tested with current object.
     * @returns boolean value of true if code is equal, and false if not equal.
     */
    public boolean equals(ErCoreErrorId code)    {
       return getCode() == code.getCode();
    }

    /**
     * This method checks if an object is the same as the current object.
     * @params obj - the object being tested with current object.
     * @returns boolean value of true if obj is equal, and false if not equal.
     */
    @Override
	public boolean equals(Object obj)
    {
        if (obj == null)  // caution
        {
            return false;
        }

        if (obj == this)  // optimization
        {
            return true;
        }

        // Castable to this class?
        if (!(obj instanceof ErCoreErrorId))
        {
           return false;
        }

        ErCoreErrorId code = (ErCoreErrorId) obj;

        boolean rv = false;

        if (getCode() == code.getCode())
        {
            rv = true;
        }

        return rv;
    }

    /**
     * Returns the hashCode value of this object.
     * @returns an int of the hashCode.
     */
    @Override
	public int hashCode()
    {
        int val = 0;
        val += code;
        val += name.hashCode();
        val += severity.hashCode();

        if (manualClear)
        {
            val += 11;
        }
        else
        {
            val += 19;
        }

        return val;
    }

    /**
     * This method creates an EcommerceException with the error code and
     * description from the ErCoreErrorId object passed in.
     * @params erCoreErrorId - used for getting the code and description.
     * @returns EcommerceException with the code and description of the
     * ErCoreErrorId.
     */
    public static EcommerceException createException(ErCoreErrorId erCoreErrorId, int type)
    {
        return createException(erCoreErrorId, "", null, type);
    }

    //@hud RFRFRF
    // A new method to incorprate the error logging
    public static EcommerceException createException(
            ErCoreErrorId         erCoreErrorId
        ,     String                 extraDescription
        ,     Throwable             thr
        ,     int                 type
        ,    Logger            logger    // log the error
    )
    {
        if (logger != null) {
            logger.error(extraDescription, thr);
        }

        return createException(erCoreErrorId, extraDescription, thr, type);
    }
    
    public static EcommerceException createException(ErCoreErrorId erCoreErrorId, String extraDescription, Throwable thr, int type)
    {
        EcommerceException ecom = createExceptionType(type, thr);
        ecom.setErrorId(erCoreErrorId.getCode());
        if (isNotBlank(extraDescription) && ! extraDescription.equals(erCoreErrorId.getName())) {
        	ecom.setErrorDescription(erCoreErrorId.getName() + ": " + extraDescription);
        } else {
        	ecom.setErrorDescription(erCoreErrorId.getName() + " ");
        }
        return ecom;
    }

    private static EcommerceException createExceptionType (int type, Throwable thr)
    {
        String msg = "";
        if (thr!=null)
        {
            msg = thr.getMessage();
        }
        switch (type)
        {
//        case ACCOUNT_CREATE_EXCEPTION:
//        return new AccountCreationException(msg, thr);
        case ACCOUNT_NOT_FOUND_EXCEPTION:
            return new AccountNotFoundException(msg, thr);
        case BATCH_EXCEPTION:
            return new BatchException(msg, thr);
        case ECOMMERCE_SYSTEM_EXCEPTION:
            return new EcommerceSystemException(msg, thr);
        case PURCHASE_AUTHORIZATION_EXCEPTION:
            return new PurchaseAuthorizationException(msg, thr);
        case RATING_EXCEPTION:
            return new RatingException(msg, thr);
        case SUBS_MANAGEMENT_EXCEPTION:
            return new SubsManagementException(msg, thr);
        case TAX_EXCEPTION:
            return new TaxException(msg, thr);
        case USAGE_AUTHORIZATION_EXCEPTION:
            return new UsageAuthorizationException(msg, thr);
        default:
            // otherwise return EcommerceException
        	 return new EcommerceException(msg, thr);
        }
    }
}


