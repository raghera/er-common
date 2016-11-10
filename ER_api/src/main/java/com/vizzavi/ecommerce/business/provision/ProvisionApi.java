package com.vizzavi.ecommerce.business.provision;


/**
* This is used to update the provisioning status of a service and also contains constant definitions for the
* values of fields contained in some ER2 XML messages.

* Example code for updating a provision tag
<br/>
// This retrieves the provisioning tags from the existing subscription
<pre>

Subscription sub = EcomApiFactory.getSelfcareApi(locale).getSubscription(subId);

String[] serviceIds = sub.getServiceIds();

for (int index=0; index&lt;serviceIds.length; index++) {
    String provTag = sub.getServiceProvTag(serviceIds[index]);

}

// this updates one of the provisioning tags
ProvisionApi api = EcomApiFactory.getProvisionApi(locale);

ProvisionAuthorization auth = api.updateProvisioningTag("id", msisdn, subscriptionId, serviceId, newProvTag);

if (auth.isAuthorized()) {
    // successful
} else {
    // check reason code and log error
    ReasonCode code = auth.getReasonCode();

    // the following reason codes are returned
        //SYSTEM_ERROR
        //SUBSCRIPTION_NOT_FOUND
        //CUSTOMER_DOES_NOT_HAVE_SUBSCRIPTION
        //SUBSCRIPTION_SERVICE_NOT_FOUND
        //NEW_PROVISION_TAG_NOT_SET
        //ORIGINAL_PROVISION_TAG_NOT_SET
        //ACCOUNT_NOT_FOUND
}

</pre>

*/
public interface ProvisionApi extends java.io.Serializable
{

    /** Int code for the status of the service for passing in to the updateServiceStatus method. */
    final public int SERVICE_STATUS_FAILED = 0;

    /** Int code for the status of the service for passing in to the updateServiceStatus method. */
    final public int SERVICE_STATUS_DELAYED = 1;

    /** Int code for the status of the service for passing in to the updateServiceStatus method. */
    final public int SERVICE_STATUS_ACTIVE = 2;



    /** Int code for the provisioning status of the service for passing in to the updateServiceStatus method. */
    final public int PROVISION_FAILED_ACTIVATION          =4;

    /** Int code for the provisioning status of the service for passing in to the updateServiceStatus method. */
    final public int PROVISION_FAILED_DEACTIVATION        =5;

    /** Int code for the provisioning status of the service for passing in to the updateServiceStatus method. */
    final public int PROVISION_DELAYED_ACTIVATION         =6;

    /** Int code for the provisioning status of the service for passing in to the updateServiceStatus method. */
    final public int PROVISION_DELAYED_DEACTIVATION       =7;

    /** Int code for the provisioning status of the service for passing in to the updateServiceStatus method. */
    final public int PROVISION_SUCCESSFUL_ACTIVATION     =8;

    /** Int code for the provisioning status of the service for passing in to the updateServiceStatus method. */
    final public int PROVISION_SUCCESSFUL_DEACTIVATION   =9;




    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_NOT_REQUIRED              = "NOT_REQUIRED";

    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_PENDING_REQUEST           = "PENDING_REQUEST";

    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_PENDING_ACTIVATION        = "PENDING_ACTIVATION";

    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_PENDING_DEACTIVATION      = "PENDING_DEACTIVATION";

    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_PENDING_REACTIVATION      = "PENDING_REACTIVATION";

    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_UNKNOWN                   = "UNKNOWN";

    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_FAILED_ACTIVATION         = "FAILED_ACTIVATION";

    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_FAILED_DEACTIVATION       = "FAILED_DEACTIVATION";

    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_DELAYED_ACTIVATION        = "DELAYED_ACTIVATION";

    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_DELAYED_DEACTIVATION      = "DELAYED_DEACTIVATION";

    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_SUCCESSFUL_ACTIVATION     = "SUCCESSFULL_ACTIVATION";

    /** String value for the provisioning status of the service as appears in ER2 XML messages */
    final public String PROVISION_STATUS_SUCCESSFUL_DEACTIVATION   = "SUCCESSFULL_DEACTIVATION";



	/**
	 *   Updates the status of the service defined by the provisioning id
	 * @param provisioningId (combines the serviceid with subscriptionId)
	 * @param serviceStatus the status to set the the service to- values around 2, eg {@link ProvisionScanner.ACTIVE}   
	 * @param provisioningStatus the result of the provisioning- values around 220, eg {@link ProvisionScanner.PECS_PROVISION_SUCCESSFULL_ACTIVATION}
	 * @return true if successful
	 */
    public boolean updateServiceStatus(
        String provisioningId,
        int serviceStatus,
        int provisioningStatus) throws ProvisionException;


	/**
	 *   Updates the status of the service defined by the provisioning id
	 * @param provisioningId (combines the serviceid with subscriptionId)
	 * @param serviceStatus the status to set the the service to- values around 2, eg {@link ProvisionScanner.ACTIVE}   
	 * @param provisioningStatus the result of the provisioning- values around 220, eg {@link ProvisionScanner.PECS_PROVISION_SUCCESSFULL_ACTIVATION}
	 * @param provisioningTag - the new provisioningTag. Important: leave this null if you don't want the provTag in the request (usually the case)
	 * @return true if successful
	 */
    public boolean updateServiceStatus(String provisioningId,
                                       int serviceStatus,
                                       int provisioningStatus,
                                       String provisioningTag) throws ProvisionException;


    /**
        Updates the provisioning tag for the subscription and service
        If the sendReactivationMessage is set to true, a provision message is sent to the
        provision handler.
        @param subscriptionId the subscription id
        @param serviceId the service identifier
        @param newProvisioningTag the value to update to
        @param sendReactivationMessage true sends a provision message to the provision system
        @return true if successful
    */
    public ProvisionAuthorization updateProvisioningTag(
        String clientId,
        String msisdn,
        String subscriptionId,
        String serviceId,
        String newProvisioningTag) throws ProvisionException;
    
    
    /* CR-1759 - start */
    /**
    Updates the status of the service defined by the provisioning id
    @param provisioningId the identifier for the service
    @param serviceStatus the status to set the the service to
    @param provisioningStatus the result of the provisioning
    @param provisioningTag the new provisioningTag
    @return an UpdateServiceStatusAuthorization object with return status
     */
    public UpdateServiceStatusAuthorization updateServiceStatusAuth(String provisioningId,
        int serviceStatus,
        int provisioningStatus,
        String provisioningTag) throws ProvisionException;
    /* CR-1759 - end */

}
