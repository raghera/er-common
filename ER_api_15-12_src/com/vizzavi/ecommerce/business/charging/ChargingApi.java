package com.vizzavi.ecommerce.business.charging;

import java.rmi.RemoteException;

import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vodafone.global.er.business.charging.UsageCompleteAttributes;

/**
 * Provides access to the charging subsystem for usage authorization requests.
 * The authorize methods return a UsageAuthorization object which must be queried
 * to see if authorization was successful. An exception is thrown
 * if the client passes in an unrecognised service identifier.
 * Service identifiers are well known names which uniquely identify a service for
 * a given locale.
 * <p>
 * In the case that the user is authorized for a service, the return object from the
 * authorize method can be queried for additional usage and charging information.
 * <p>
 * Example usage: <p>
<code>
<pre>

    ChargingApi chargingApi = EcomApiFactory.getChargingApi( locale );
    UsageAuthorization authResult =
        chargingApi.usageAuthRateCharge( "myApp", "447930666555", "vx_sports_news" );

    if( authResult.isAuthorized() ) {
        // deliver service
        ...
    }

</pre>
</code>

Handling errors can be done in a variety of ways depending on the application environment.

<code>
<pre>

    if( !authResult.isAuthorized() ) {

        switch( authResult.getReasonCode() ) {
             case ReasonCode.SUBSCRIPTION_EXPIRED:  ...

    }

</pre>
</code>


 * For platforms using two stage reserve/capture, the usageAuthRateCharge method can be called as normal
 * first. There <em>must</em> subsequently be a call to usageComplete.
 *

<code>
<pre>

    String eventReservationId = null;

    UsageAuthorization authResult =
        chargingApi.usageAuthRateCharge( "deliveryApp", "447930666555", "quake_III_J2ME" );

    if( authResult.isAuthorized() ) {

        // the id from usage auth result must be stored for later use in the complete call
        eventReservationId = authResult.getEventReservationId();

        // deliver game
        ...

    }


    // later on we have a delivery confirmation if the authorization result is reserved

    if (authResult.isReservedOnly()) {

        UsageAuthorization completionResult = chargingApi.usageComplete(
            "receiptApp",
            eventReservationId,
            ChargingApi.USAGE_COMPLETION_SUCCESS );

        if( completionResult.isAuthorized() == false ) {
            // some technical problem occurred!
        }

    }

</pre>
</code>

 *
 *
 *
 */
public interface ChargingApi extends java.io.Serializable {

	/**
	 * Send a usage authorize request to the charging system.
	 * If successful, the user has been authenticated that he can use this service.
	 *
	 * The mandatory attributes are msisdn
	 * @param clientApplicationId the id of the application calling this method.
	 * @param msisdn the msisdn of the customer device (including iso-countrycode).
	 * @param serviceId the unique identifier for this service.
	 * @return usageAuthorization object for this request.
	 * @exception UsageAuthorizationException
	 *                   if the serviceId is not recognised or has been disabled
	 *                   if the charging subsystem is not operational
	 */
	public UsageAuthorization usageAuth(
			String clientApplicationId,
			String msisdn,
			String serviceId,
			UsageAttributes usageAttributes
	) throws UsageAuthorizationException;


	/**
	 * Send a usage authorize and rating request to the charging system.
	 * If successful, the user will be authenicated to use this service
	 * and rating information returned
	 *
	 * The mandatory attributes are msisdn
	 * @param clientApplicationId the id of the application calling this method.
	 * @param msisdn the msisdn of the customer device (including iso-countrycode).
	 * @param serviceId the unique identifier for this service.
	 * @param usageAttributes additional attributes for this request.
	 *
	 * @return usageAuthorization object for this request.
	 * @exception UsageAuthorizationException
	 *                   if the serviceId is not recognised or has been disabled
	 *                   if the charging subsystem is not operational
	 */

	public UsageAuthorization usageAuthRate(
			String clientApplicationId,
			String msisdn,
			String serviceId,
			UsageAttributes usageAttributes
	) throws EcommerceException;


	/**
	 * Send a usage charge request to the charging system.
	 * If successful, the user will be charged for this usage event
	 *
	 * The mandatory attributes are msisdn
	 * @param clientApplicationId the id of the application calling this method.
	 * @param msisdn the msisdn of the customer device (including iso-countrycode).
	 * @param serviceId the unique identifier for this service.
	 * @param usageAttributes additional attributes for this request.
	 *
	 * @return usageAuthorization object for this request.
	 * @exception UsageAuthorizationException
	 *                   if the serviceId is not recognised or has been disabled
	 *                   if the charging subsystem is not operational
	 */

	public UsageAuthorization usageAuthRateCharge(
			String clientApplicationId,
			String msisdn,
			String serviceId,
			UsageAttributes usageAttributes
	) throws UsageAuthorizationException;


	/**
	 * Status code used for deliveryStatus to be passed to the usageComplete method.
	 */
	public final static int USAGE_COMPLETION_SUCCESS   = 1;

	/**
	 * Status code used for deliveryStatus to be passed to the usageComplete method.
	 */
	public final static int USAGE_COMPLETION_FAILIURE  = 0;

	/**
	 * Send a usage completion acknowledgement to the charging system for a previously reserved authorization call.
	 * This is used primarily to inform the charging system of a successful or failed delivery.
	 * It must only be called once for a given eventReservationId (any attempt to complete a usage event twice
	 * will return ReasonCode BAD_ASYNC_RECORD_ID).
	 * <b>This should only be called by specialist clients such as download servers</b>.
	 * This is for use in the capture stage of reserve/capture scenarios. (Please see the relevant system
	 * documentation for full details of this process).
	 *
	 * Briefly, if the deliveryStatus flag is set to a success code, and there is a payment event outstanding
	 * it will be captured. If the flag is set to a fail code, the payment event may be rolled back. This could involve
	 * either cancelling a prior reservation (e.g. for payment systems that support true reservation functionality)
	 * or issuing a technical refund (e.g. for payment systems that do not support true reservation functionality).
	 *
	 * @param clientApplicationId the id of the application calling this method.
	 * @param eventReservationId the unique identifier for the usage event to be completed.
	 * @param deliveryStatus the status of the event. This is one of:
      <ul>
        <li>USAGE_COMPLETION_SUCCESS
        <li>USAGE_COMPLETION_FAILIURE
      </ul>
	 *
	 * @return UsageCompletionResult object for this request. Call isAuthorized() to see if complete was successful.
	 * @exception UsageAuthorizationException if an unknown system error occurs
	 * (NB If the eventReservationId is not recognised or if it has already been completed already
	 * then ReasonCode BAD_ASYNC_RECORD_ID will be returned)
	 */

	public UsageAuthorization usageComplete(
			String clientApplicationId,
			String eventReservationId,
			int deliveryStatus
	) throws UsageAuthorizationException;



	/**
	 * Rates the service using the priceplan without sending a request to Infranet
	 * This should not be used. The functionlity is now is usageAuthRate/
	 * @deprecated yeah, for about 10 years - time to remove it
	 * @param clientApplicationId the id of the application calling this method.
	 * @param serviceId the unique identifier for this service.
	 * @param usageAttributes additional attributes for this request.
	 *
	 * @return CatalogService object for this request.
	 * @exception UsageAuthorizationException
	 *                   if the serviceId is not recognised or has been disabled
	 *                   if the charging subsystem is not operational
	 */
	@Deprecated
	public CatalogService rateService(String clientApplicationId,
			String serviceId,
			UsageAttributes usageAttributes
	) throws UsageAuthorizationException;

//	/** Waleed PII
//	 * Same as above with addition of the service usage instance keeping track of the events, child events, used rating model 
//	 * 
//	 */
//	public UsageAuthorization usageAuthRateCharge(
//			String clientApplicationId,
//			String msisdn,
//			String serviceId,
//			UsageAttributes usageAttributes,
//			ServiceUsageInstance serviceUsageInstance
//	) throws UsageAuthorizationException;
//
//
//
//	/** Waleed PII
//	 * Same as above with addition of the service usage instance keeping track of the events, child events, used rating model 
//	 * 
//	 */
//
//	public UsageAuthorization usageAuthRate(
//			String clientApplicationId,
//			String msisdn,
//			String serviceId,
//			UsageAttributes usageAttributes,
//			ServiceUsageInstance serviceUsageInstance
//	) throws EcommerceException;

	//Added for ER8 PH2
	public UsageAuthorization usageComplete(String clientId, String reservationId, UsageCompleteAttributes  att)
	throws UsageAuthorizationException;

	
	/**
	 * @author hud
	 * STKHREQ13107 Unique Promo Code
	 * This method checks the unique promo code and returns
	 * discounted purchase options if the promo code is valid
	 * Otherwise, it returns error code: INVALID
	 * 
	 * Here is the workflow for the promo code processing:
	 * + User enters promo code and submits to purchase app.
	 * + Purchase app examines the promo code by compare it with promo codes in the package price points received from the last usage auth call.
	 * + If there is a match, set a match flag to true and submit to ER CORE to get discounted package price points with the promo code.
	 * + If there is no match, set a match flag to false and submit to ER CORE, which will find out if this is a 
	 *   unique promo code and return status INVALID, VALID. On INVALID, ask user to enter promo code again, otherwise, 
	 *   the discounted price points are returned to purchase app for display.
	 * + Decoupling must be modified to reflect this change.
	 */
	
	public PromoCodeValidation validatePromoCode(
			PromoCodeAttributes	promoCodeAttrs
	) throws EcommerceException;
	
	

}


