/**
 * 
 */
package com.vizzavi.ecommerce.business.charging;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;

/**
 * @author appadm
 *
 */
public class PromoCodeValidation extends ValidatePromoStatus 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3393197397740669554L;
	
	
	//=======================================================
	// list of package price points if needed
	CatalogPackage[]	mPackagePricePoints;
	
	//========================================================
	// helpful constructors
	public PromoCodeValidation(int status)
	{
		super(status);
	}
	
	public PromoCodeValidation(int status, int type)
	{
		super(status, type);
	}
	
	public PromoCodeValidation(int status, int type, String precode, String uniqueCode)
	{
		super(status, type, precode, uniqueCode);
	}
	
	public PromoCodeValidation(ValidatePromoStatus vpStatus)
	{
		super(vpStatus);
	}
	
	
	public void setPackagePricePoints(CatalogPackage[] pps)
	{
		mPackagePricePoints = pps;
	}
	public CatalogPackage[] getPackagePricePoints()
	{
		return mPackagePricePoints;
	}
}
