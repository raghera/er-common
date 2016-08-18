package com.vodafone.global.er.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.catalog.Tax;
import com.vizzavi.ecommerce.business.selfcare.UserGroup;

/**
 * some useful methods for handling pricepoints, packages, etc
 * @author matt
 *
 */
public class CatalogUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogUtil.class);

	private CatalogUtil(){}
	
	/**
	 * assembles a 'full package id' from a pricepoint (starting with 'package:')
	 * @param pricePointId
	 * @return
	 */
	public static String getLongPackageIdFromPricePointId(String pricePointId) {
		String packageId = getPackageIdFromPricepoint(pricePointId);
		return packageId+CatalogPackage.PACKAGE_DELIMITER+pricePointId;
	}



	/**
	 * <p>returns the short packageId from either a "full" packageId or a pricepoint</p>
	 * @param pricePoint
	 * @return short packageId
	 */
	public static String getPackageIdFromPricepoint(String pricePoint){
		if (pricePoint.indexOf(CatalogPackage.PACKAGE_DELIMITER) >1)
			return pricePoint.substring(0, pricePoint.indexOf(CatalogPackage.PACKAGE_DELIMITER));

		if(!pricePoint.startsWith(PricePoint.PACKAGE_PRICEPOINT_ID_PREFIX))	{
			logger.warn("can't work out package id from pricepoint {}", pricePoint);
			return pricePoint;
		}
		
		//TODO test for PricePoint.CONTENT_PRICEPOINT_ID_PREFIX ('content:')
		
		//now attempt to work out what the tax code is for this ppt
		for (String code: Tax.taxCodes)	{
			if (pricePoint.indexOf("_"+code+"_")>9)	{	//why 9? well the pricepoint string starts with 'package:' and the minimum length of a packageId is 1
				String guess = pricePoint.substring(8, pricePoint.indexOf("_"+code+"_"));
				logger.info("guessing at packageId {} from pricepoint {}", guess, pricePoint);
				return guess;
			}
		}
		
		if (pricePoint.indexOf("_TAX_")==-1)	{
			logger.warn("can't work out package id from pricepoint {}: can't find any tax code in pricepoint", pricePoint);
			return pricePoint.substring(8, pricePoint.indexOf("_"));	//a guess - will fail if packId has a _ character in it
		}	else
			return pricePoint.substring(8, pricePoint.indexOf("_TAX_"));
			 
		
	
	}


	/**
	 * <p>pass in a "full" packageId and it will get the pricepoint string (required eg for catalogApi.getPricepoint)</p>
	 * eg pass in: <br/>"Credit Against Volume Package__X__package:Credit Against Volume Package_TAX_2_5_999_999_999_*_ _*_false_false"<br/>
	 * and it will return:<br/>
	 * "package:Credit Against Volume Package_TAX_2_5_999_999_999_*_ _*_false_false"
	 */
	public static String getShortPricePointFromLongPackageId(String longPackageId)	{
		if (longPackageId.indexOf(CatalogPackage.PACKAGE_DELIMITER) >1)	{
			return longPackageId.substring(longPackageId.indexOf(CatalogPackage.PACKAGE_DELIMITER)+CatalogPackage.PACKAGE_DELIMITER.length());
		}	else
			return longPackageId;	
	}
	
	
	public static String stringifyUserGroups(UserGroup[] userGroups)	{
		if (userGroups == null || userGroups.length==0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (UserGroup ug: userGroups)	{
			sb.append(ug.getName()).append(", ");
		}
		return sb.substring(0, sb.length()-2);
	}
	
	/**
	 * removes inactive pricepoints... not historic ones
	 * @param pricepoints
	 */
	public static void removeInactivePricepoints(List<PricePoint> pricepoints) {
		List<PricePoint> rv = new ArrayList<PricePoint>(pricepoints);
		synchronized(rv){
			for( Iterator< PricePoint > it = rv.iterator(); it.hasNext() ; )	{
				PricePoint pp = it.next();
				if (! pp.isActive() )
					it.remove();
			}
		}
	}
	
	/**
	 * CR CTB-1 Advanced Linked Pricepoint - compare the linked pricepoint count to the subscription renewal to linked pricepoint counter, return true if renewal required to linked pricepoint 
	 * @param pricepointRenewalCount
	 * @param subRenewalCounter
	 * @return boolean
	 */
	public static boolean checkRenewalCounterToLinkedPricepoint(int pricepointRenewalCount, int subRenewalCounter) {
		
		logger.debug( "Enter in method checkRenewalCounterToLinkedPricepoint" );
		
		boolean rv = true;
		
		if (pricepointRenewalCount <= 1) {
			return rv;
		}
		
		if (subRenewalCounter +1 >= pricepointRenewalCount) {
			return rv;
		}
		else {
			rv = false;
		}
		
		logger.debug( "Exit in method checkRenewalCounterToLinkedPricepoint" );
		
		return rv;
	}
}
