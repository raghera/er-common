package com.vizzavi.ecommerce.business.charging;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.common.EcommerceException;

public interface PurchaseApi extends java.io.Serializable
{
     /**
     * Send a purchase request to the charging system.
     *
     * If the user already has the package, the ReasonCode object returns the code ReasonCode
     * The package can be purchased anyway by calling setting the setIsRepurchase flag to true
     * in the purchase attributes.
     * The purchase attributes are NOT used for rating.
     * To check for success, call PurchaseAuthorization.isAuthorized().
     * If not authorised, check the ReasonCode.
     *
     * The mandatory attributes are msisdn and packageId
     * @param clientApplicationId the id of the application calling this method.
     * @param msisdn the msisdn of the customer device (including iso-countrycode).
     * @param packageId the unique identifier for this service.
     * @param purchaseAttributes user attributes for this request.
     *
     * @return PurchaseAuthorization object for this request.
     * @exception PurchaseAuthorizationException
     */
     public PurchaseAuthorization purchasePackageMsisdn(
         String clientApplicationId,
         String msisdn,
         String packageId,
         PurchaseAttributes purchaseAttributes
         ) throws PurchaseAuthorizationException;


    /**
    * This rates the package using the supplied attributes.
    * This has similar functionality to the findPackagesWithService.
    * It returns the package rated for each price point in the package. If the price point is not active
    * it is not returned.
    * Promocode : if the * is supplied in the list all promo codes are returned unless the pricepoint is inactive or the customer has already used it.
    * UserGroup : if the * is supplied in the list all user group price points are returned
    * If an msisdn is supplied all promo code matches are checked to see if the user has already purchased.
    * If an event date is supplied, each price point is rated for date and time.
    * If no event date time is supplied, the current system time is used.
    * If the price point is not active for the supplied date and time, it is not returned.
    * To retrieve the matching price points call getPricePoints()
    * @param clientApplicationId the id of the application calling this method.
    * @param msisdn the msisdn of the customer device (including iso-countrycode).
    * @param packageId the unique identifier for this package.
    * @param purchaseAttributes user attributes for this request.
    *
    * @return CatalogPackage object for this request.
    * @exception EcommerceException
    */
    public CatalogPackage ratePackage(
        String clientApplicationId,
        String msisdn,
        String packageId,
        PurchaseAttributes purchaseAttributes
        ) throws EcommerceException;

    /**
        This is the replacement method for ratePackage
    */
    /*public PurchaseAuthorization[] discoverPackagePurchaseOptions(
        String clientId,
        String msisdn,
        String packageId,
        RatingAttributes attributes,
        Comparator comp) throws EcommerceException;*/

    /**
        This is the replacement method for findPackagesWithService
    */
/*
    public PurchaseAuthorization[] discoverServicePurchaseOptions(
        String clientId,
        String msisdn,
        String serviceId,
        RatingAttributes attributes,
        Comparator comp) throws EcommerceException; */


/*
        This rates the purchase based on the input package id and rate identifier
        The use of packageId__X__pricepointid is now not supported.
    public PurchaseAuthorization ratePurchaseIdentifier(String clientId, String msisdn, String packageId, String rateIdentifier) throws EcommerceException;
*/
    /**
    * Send a renewal purchase request to the charging system.
    * If you set the payment type in the purchase attributes, this type is sent to the external payment system.
    * The purchase attributes are NOT used for rating.
    * To check for success, call PurchaseAuthorization.isAuthorized().
    * The price of the renewal can be determined from the Subscription class by calling the getNextPaymentAmount()
    * If not authorised, check the ReasonCode.
    *
    * The mandatory attributes are msisdn and packageSubscriptionId
    * @param clientApplicationId the id of the application calling this method.
    * @param msisdn the msisdn of the customer.
    * @param packageSubscriptionId the unique identifier for this package subscription.
    * @param purchaseAttributes user attributes for this request.
    *
    * @return PurchaseAuthorization object for this request.
    * @see com.vizzavi.ecommerce.business.selfcare.Subscription
    * @exception PurchaseAuthorizationException
    */
    public PurchaseAuthorization renewPurchasePackageMsisdn(
        String clientApplicationId,
        String msisdn,
        String packageSubscriptionId,
        PurchaseAttributes purchaseAttributes
        ) throws PurchaseAuthorizationException;


    /**
    *
    * Update a pending purchase with the result of the authentication of the user
    * for asynchronous paymentmethods
    *
    * The payment id comes from the previous purchase and is contained in the
    * PurchaseAuthorization class.
    *
    * The authentication result can either be
    *    0  DENIED
    *    1  ACCEPTED
    *    2  REJECTED
    *
    * The authentication code is a String and can be any helpful String returned by the payment
    * system
    *
    * The mandatory attributes are msisdn, paymentId, authResult and authCode
    * @param clientApplicationId the id of the application calling this method.
    * @param msisdn the msisdn of the customer device (including iso-countrycode).
    * @param paymentId the unique identifier for the pending purchase.
    * @param authResult the status of the purchase.
    * @param authCode the String returned by the authentication system
    *
    * @return PurchaseAuthorization object for this request.
    * @exception PurchaseAuthorizationException
    */
    public PurchaseAuthorization confirmPurchasePackagePending(
        String clientApplicationId,
        String msisdn,
        String paymentId,
        int authResult,
        String authCode
        ) throws PurchaseAuthorizationException;


    /**
     * This is used to check if a user has already purchased a package/promo code combination.
     * A package can only be purchased once per promo code. This call would be used if the purchase
     * application did not want to display a price point if the price point has a promo code and has
     * already been purchased for this package. This method is used by findPackagesWithService.
     * The mandatory attributes are msisdn, paymentId, authResult and authCode
     * 
     * @hud STKHRQ13107 This method is deprecated as it doesn't return enough information
     * A new method with same method name but different signature is created below.
     * For backwards compatibility, this method is still available for a period.
     * 
     * @deprecated
     * 
     * @param clientApplicationId the id of the application calling this method.
     * @param msisdn the msisdn of the customer device (including iso-countrycode).
     * @param packageId Check for this package.
     * @param promoCode  Check for this promo code.
     * @see CatalogApi.findPackagesWithService()
     * @return true if the user has already purchased the package with the supplied promo code
     * @exception EcommerceException
     */
    @Deprecated
	public boolean checkUserHasPackageWithPromoCode(
    		String 			clientApplicationId
    	, 	String 			msisdn
    	,	String 			packageId
    	, 	String 			promoCode
    ) throws EcommerceException;
    
    /**
     * @author hud STKHREQ13107 Unique Promo Code
     * This method extends the existing deprecated method checkUserHasPackageWithPromoCode
     * by provide more generic return information for both regular and unique promo codes
     */
    public ValidatePromoStatus validatePromoCode(
    		ValidatePromoParam		vpParam
    ) throws EcommerceException; 
 	
	/**
     * Send a purchase request to the charging system.
     *
     * If the user already has the package, the ReasonCode object returns the code ReasonCode
     * The package can be purchased anyway by calling setting the setIsRepurchase flag to true
     * in the purchase attributes.
     * The purchase attributes are NOT used for rating.
     * To check for success, call PurchaseAuthorization.isAuthorized().
     * If not authorised, check the ReasonCode.
     *
     * The mandatory attributes are msisdn and packageId
     * @param clientApplicationId the id of the application calling this method.
     * @param msisdn the msisdn of the customer device (including iso-countrycode).
     * @param packageId the unique identifier for this service.
     * @param purchaseAttributes user attributes for this request.
     *
     * @return PurchaseAuthorization object for this request.
     * @exception PurchaseAuthorizationException
     */
     public PurchaseAuthorization purchasePackage(
         String clientApplicationId,
         String msisdn,
         String packageId,
         PurchaseAttributes purchaseAttributes
         ) throws PurchaseAuthorizationException;
     
     
     /**
      * CR-0978 Location Services
      * Send a modify tariff request to the charging system.
      *
      * modifytariffattributes.action can only have the value NEW or CHANGE or TERMINATE
	  * e.g. use cases
	  *	For NEW - purchase all pricepoints with tariff A
	  *
	  *	action = NEW
	  *	sourceTariff = *
	  *	destinationTariff = A
	  *
	  *	For CHANGE - inactivate all subscriptins to tariff A & purchase all pricepoints with tariff B
	  *
	  *	action = CHANGE
	  *	sourceTariff = A
	  *	destinationTariff = B
	  *
	  *	For TERMINATE inactivate all subscriptins to tariff B
	  *
	  *	action = TERMINATE
	  *	sourceTariff = B
	  *	destinationTariff = *
      *
      * The mandatory attributes are msisdn and modifytariffattributes.action
      * 
      * @param msisdn the msisdn of the customer device (including iso-countrycode).
      * @param modifyTariffAttributes modify tariff attributes for this request.
      *
      * @return ModifyTariffAuthorization object for this request.
      * @exception EcommerceException
      */
     public ModifyTariffAuthorization modifyTariff(
             String msisdn,
             ModifyTariffAttributes modifyTariffAttributes
             ) throws EcommerceException;


     /**
      * CR2040 MPAY replacement.
      * CR - Add Invoice Text to goodwill credit request - consolidated attributes into GoodwillCreditAttributes class
      * Credit a customer's account with specified "goodwill" amount.
      * @param msisdn - customer's MSISDN
      * @param goodwillCreditAttributes - following goodwill credit attributes
      * 	clientId - id of calling application
      * 	partnerId - the Approved Payment Intermediary
      * 	merchantId - the merchant associated with the partner
      * 	packageId - the package id in the price plan for revenue share/reporting
      * 	preRate - the pre-rated amount to give to customer
      * 	invoiceText - the text to be presented on the customer bill for this transaction
      * @return GoodwillCreditAuthorization indicating success or failure to credit account.
      * @throws GoodwillCreditAuthorizationException
      */
	public GoodwillCreditAuthorization goodwillCredit(String msisdn, GoodwillCreditAttributes goodwillCreditAttributes) throws GoodwillCreditAuthorizationException ;



	/**
	 * get some information about the ER core instance.    This call should return info like manifest name, timestamp, version number etc.
	 * The keys are the names of the properties, and the values are the values, eg
	 * "Manifest name" : "13.8"
	 * "Timestamp"     : "20130903 1534"
	 * etc
	 * added for MQC9301 but then removed since it's not required.
	 * @return
	 */
	//public Map<String, String> getVersionInfo();
}
