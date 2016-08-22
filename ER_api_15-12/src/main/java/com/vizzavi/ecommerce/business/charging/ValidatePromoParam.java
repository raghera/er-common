package com.vizzavi.ecommerce.business.charging;

import java.io.Serializable;

/**
 * 
 * @author hud
 * STKHRQ13107 Unique promo code
 * Only used in PurchaseApi.validatePromoCode
 * if mPackageId is null, ER CORE will treat it as a unique promo code
 */
public class ValidatePromoParam implements Serializable
{
	private static final long serialVersionUID = -5743478498676245015L;
	
	// Mandatory fields
	private String mMsisdn				= null;
	public String 	getMsisdn	() 				{	return mMsisdn;			}
	public void 	setMsisdn	(String msisdn) {	mMsisdn = msisdn;		}
	
	private String mPromoCode			= null;
	public String	getPromoCode	()						{ 	return mPromoCode;		}
	public void		setPromoCode	(String promoCode)		{	mPromoCode = promoCode;	}
	
	
	// optional fields
	private String mPackageId			= null;
	public String	getPackageId	()						{ 	return mPackageId;		}
	public void		setPackageId	(String packageId)		{	mPackageId = packageId;	}
	
	private String mServiceId			= null;
	public String	getServiceId	()						{ 	return mServiceId;		}
	public void		setServiceId	(String serviceId)		{	mServiceId = serviceId;	}
	
	private String mClientId			= null;
	public String	getClientId		()						{ 	return mClientId;		}
	public void		setClientId		(String clientId)		{	mClientId = clientId;	}
	
	
	//////////////////////////////////////////////////////////////
	// helpers
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("msisdn = ").append(mMsisdn)
		  .append(", promo code = ").append(mPromoCode)
		  .append(", package id = ").append(mPackageId)
		  .append(", service id = ").append(mServiceId)
		  .append(", client id = ").append(mClientId);
		
		return sb.toString();
	}
	
	// avlidate the parameters
	public boolean validate()
	{
		boolean ret = false;

		if (mMsisdn != null && mPromoCode != null) {
			ret = true;
		}
		
		return ret;
	}
}
