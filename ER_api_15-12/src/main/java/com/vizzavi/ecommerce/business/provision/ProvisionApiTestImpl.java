/**
 * 
 */
package com.vizzavi.ecommerce.business.provision;

/**
 * A stub test class implementation of the ProvisionApi
 * 
 * Concentrates mainly in the error scenarios so Exceptions and 
 * error codes are returned.
 * 
 * 
 * @author Ravi Aghera
 *
 */
public class ProvisionApiTestImpl implements ProvisionApi {

	/* (non-Javadoc)
	 * @see com.vizzavi.ecommerce.business.provision.ProvisionApi#updateServiceStatus(java.lang.String, int, int)
	 */
	@Override
	public boolean updateServiceStatus(String provisioningId,
			int serviceStatus, int provisioningStatus)
			throws ProvisionException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vizzavi.ecommerce.business.provision.ProvisionApi#updateServiceStatus(java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public boolean updateServiceStatus(String provisioningId,
			int serviceStatus, int provisioningStatus, String provisioningTag)
			throws ProvisionException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vizzavi.ecommerce.business.provision.ProvisionApi#updateProvisioningTag(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ProvisionAuthorization updateProvisioningTag(String clientId,
			String msisdn, String subscriptionId, String serviceId,
			String newProvisioningTag) throws ProvisionException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vizzavi.ecommerce.business.provision.ProvisionApi#updateServiceStatusAuth(java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public UpdateServiceStatusAuthorization updateServiceStatusAuth(
			String provisioningId, int serviceStatus, int provisioningStatus,
			String provisioningTag) throws ProvisionException {
		// TODO Auto-generated method stub
		return null;
	}

}
