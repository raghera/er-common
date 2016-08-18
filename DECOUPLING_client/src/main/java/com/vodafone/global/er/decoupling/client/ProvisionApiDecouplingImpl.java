package com.vodafone.global.er.decoupling.client;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.provision.ProvisionApi;
import com.vizzavi.ecommerce.business.provision.ProvisionAuthorization;
import com.vizzavi.ecommerce.business.provision.ProvisionException;
import com.vizzavi.ecommerce.business.provision.UpdateServiceStatusAuthorization;
import com.vodafone.global.er.decoupling.PayloadConstants;
import com.vodafone.global.er.decoupling.binding.request.ProvisionFullUpdateServiceStatusRequest;
import com.vodafone.global.er.decoupling.binding.response.ProvisionFullUpdateServiceStatus;
import com.vodafone.global.er.decoupling.binding.response.UpdateServiceStatusAuthorisationFullType;

class ProvisionApiDecouplingImpl extends BaseErApiDecouplingImpl implements ProvisionApi {

	private static final Logger logger = LoggerFactory.getLogger(ProvisionApiDecouplingImpl.class);
	
	public ProvisionApiDecouplingImpl(Locale locale, String clientId) {
		super(locale, clientId);
	}

	/**
	 * 
	 * @param provisioningId provisioningId (combines the serviceid with subscriptionId)
	 * @param serviceStatus  values around 2, eg {@link ProvisionScanner.ACTIVE}   
	 * @param provisionStatus  values around 220, eg {@link ProvisionScanner.PECS_PROVISION_SUCCESSFULL_ACTIVATION}
	 * @return
	 * @throws ProvisionException
	 */
	@Override
	public boolean updateServiceStatus(String provisioningId, int serviceStatus,
			int provisionStatus) throws ProvisionException {
		return this.updateServiceStatus(provisioningId, serviceStatus, provisionStatus, null);
	}

	/**
	 * 
	 * @param provisioningId provisioningId (combines the serviceid with subscriptionId)
	 * @param serviceStatus  values around 2, eg {@link ProvisionScanner.ACTIVE}   
	 * @param provisionStatus  values around 220, eg {@link ProvisionScanner.PECS_PROVISION_SUCCESSFULL_ACTIVATION}
	 * @param provisioningTag - important - leave this null if you don't want the provTag in the request (usually the case)
	 * @return
	 * @throws ProvisionException
	 */
	@Override
	public boolean updateServiceStatus(String provisioningId, int serviceStatus,
			int provisionStatus, String provisioningTag) throws ProvisionException {
		//TODO - what's the difference between this method and updateServiceStatusAuth ?
//		try
//		{
//			final ProvisionFullUpdateServiceStatusRequest request = createRequest(PayloadConstants.PROVISION_FULL_UPDATE_SERVICE_STATUS_REQUEST_PAYLOAD);
//
//			request.setProvisioningId(provisioningId);
//			request.setServiceStatus(serviceStatus);
//			request.setProvisioningStatus(provisionStatus);
//
//			if(StringUtils.isNotBlank(provisioningTag))
//				request.setProvisioningTag(provisioningTag);
//
//			final ProvisionFullUpdateServiceStatus result = sendRequestAndGetResponse(PayloadConstants.PROVISION_FULL_UPDATE_SERVICE_STATUS_REQUEST_PAYLOAD, request, ProvisionFullUpdateServiceStatus.class);
//			return result.getUpdateServiceStatusAuthorisation().isIsSuccess();		
//		
//		}	catch(Exception e)	{
//			throw new ProvisionException(e);
//		}
		return updateServiceStatusAuth(provisioningId, serviceStatus, provisionStatus, provisioningTag).isSuccess();
	}

	@Override
	public ProvisionAuthorization updateProvisioningTag(String clientId, String msisdn,
			String subscriptionId, String serviceId, String newProvisioningTag)
			throws ProvisionException {
		throw new RuntimeException("updateProvisioningTag not supported in this version: "+version+" dated "+date);

	}

	@Override
	public UpdateServiceStatusAuthorization updateServiceStatusAuth(String provisioningId,
			int serviceStatus, int provisioningStatus, String provisioningTag)
			throws ProvisionException {

		try
		{
			final ProvisionFullUpdateServiceStatusRequest request = createRequest(PayloadConstants.PROVISION_FULL_UPDATE_SERVICE_STATUS_REQUEST_PAYLOAD);

			request.setProvisioningId(provisioningId);
			request.setServiceStatus(serviceStatus);
			request.setProvisioningStatus(provisioningStatus);

			if(StringUtils.isNotBlank(provisioningTag))
				request.setProvisioningTag(provisioningTag);

			final ProvisionFullUpdateServiceStatus result = sendRequestAndGetResponse(PayloadConstants.PROVISION_FULL_UPDATE_SERVICE_STATUS_REQUEST_PAYLOAD, request, ProvisionFullUpdateServiceStatus.class);
			UpdateServiceStatusAuthorisationFullType authResponse = result.getUpdateServiceStatusAuthorisation(); 
			logger.debug("IsSuccess()? {}, reasonCode {}" , authResponse.isIsSuccess(), authResponse.getReasonCode());
			
			return converter.convertUpdateServiceStatusAuthorisationFullType(authResponse);
		}	catch(Exception e)	{
			throw new ProvisionException(e);
		}
	}

}
