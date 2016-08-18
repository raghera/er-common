package com.vodafone.global.er.decoupling.util.xml;

import javax.xml.namespace.QName;

import com.vodafone.global.er.decoupling.PayloadConstants;

/**
 * a utility class to map between er request id (eg 100007 for usage-auth-rate-charge) and qname - eg "usage-auth-rate-charge"
 * @author matt
 *
 */
public class QnameMapper {

	//these lot were copied from the generated ObjectFactory
	   private final static QName _GetAllServicesPartnersRequest_QNAME = new QName("", "get-all-services-partners-request");
	    private final static QName _CatalogLittlePackagesRequest_QNAME = new QName("", "catalog-little-packages-request");
	    private final static QName _UsageAuthRateChargePlus_QNAME = new QName("", "usage-auth-rate-charge-plus");
	    private final static QName _GetApplicationConfig_QNAME = new QName("", "get-application-config");
	    private final static QName _ErVersionInfoRequest_QNAME = new QName("", "er-version-info-request");
	    private final static QName _UsageAuthRate_QNAME = new QName("", "usage-auth-rate");
	    private final static QName _ProvisionFullUpdateServiceStatusRequest_QNAME = new QName("", "provision-full-update-service-status-request");
	    private final static QName _GetReasonCodesRequest_QNAME = new QName("", "get-reason-codes-request");
	    private final static QName _UsageAuthRateCharge_QNAME = new QName("", "usage-auth-rate-charge");
	    private final static QName _ProvisionSimpleUpdateServiceStatusRequest_QNAME = new QName("", "provision-simple-update-service-status-request");
	    private final static QName _CatalogFullPackagesRequest_QNAME = new QName("", "catalog-full-packages-request");
	    private final static QName _GetVersionRequest_QNAME = new QName("", "get-version-request");
	    private final static QName _GetPartnersWithTradingLimit_QNAME = new QName("", "get-partners-with-trading-limit");
	    private final static QName _GetAllServicesRequest_QNAME = new QName("", "get-all-services-request");
	    private final static QName _UsageAuthRatePlus_QNAME = new QName("", "usage-auth-rate-plus");


	/**
	 * returns the {@link javax.xml.namespace.QName} (qualified xml name) for the payload type based on the er request ID
	 * @param erRequestId
	 * @return
	 */
	public static QName getQname(int erRequestId)	{
		//info taken from decoupling guide
		switch(erRequestId)	{
			case PayloadConstants.USAGE_AUTH_RATE_CHARGE_REQUEST_PAYLOAD: 	return _UsageAuthRateCharge_QNAME;
			case PayloadConstants.USAGE_AUTH_RATE_CHARGE_PLUS_REQUEST_PAYLOAD: return _UsageAuthRateChargePlus_QNAME;
			case PayloadConstants.USAGE_AUTH_RATE_PLUS_REQUEST_PAYLOAD: 	return _UsageAuthRatePlus_QNAME;
			case PayloadConstants.USAGE_AUTH_RATE_REQUEST_PAYLOAD: 			return _UsageAuthRate_QNAME;
			case PayloadConstants.PROVISION_FULL_UPDATE_SERVICE_STATUS_REQUEST_PAYLOAD: return _ProvisionFullUpdateServiceStatusRequest_QNAME;
			case PayloadConstants.PROVISION_SIMPLE_UPDATE_SERVICE_STATUS_REQUEST_PAYLOAD: return _ProvisionSimpleUpdateServiceStatusRequest_QNAME;
			
			//these next are empty calls
			case PayloadConstants.ER_CONSTANTS_REQUEST_PAYLOAD: return new QName("", "constants");
			case 100076: return _GetAllServicesPartnersRequest_QNAME;
			case 100047: return _GetApplicationConfig_QNAME;
			case 100045: return _ErVersionInfoRequest_QNAME;
			case 100068: return _CatalogFullPackagesRequest_QNAME;
			case 100032: return _GetReasonCodesRequest_QNAME;
			case 100029: return _GetVersionRequest_QNAME;
			case 100072: return _GetPartnersWithTradingLimit_QNAME;
			case PayloadConstants.GET_ALL_SERVICES_REQUEST_PAYLOAD:			return _GetAllServicesRequest_QNAME;
			case PayloadConstants.CATALOG_LITTLE_PACKAGES_REQUEST_PAYLOAD: 	return _CatalogLittlePackagesRequest_QNAME;
		}
		
		throw new RuntimeException("can't work out what kind of er request payload to create based on er request Id " +erRequestId);
	}
	
}
