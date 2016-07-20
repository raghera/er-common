package com.vodafone.global.er.util;

import com.vizzavi.ecommerce.business.catalog.CatalogApi;
import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.catalog.Tax;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.selfcare.UserGroup;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	 *  Pass in a pricepoint id for a package or service
	 *  content:pAlt_[TAX_sAlt]_1_999_*_999_999 or package:pAlt_[TAX_999_999_999_999_1_*_*_*_false_false_*].
	 *  This method will extract the tax code from the string and return it
	 *  PricePoint instance.
	 */
	public static final String getTaxCodeFromPricePointId(final String pricePointIdString) {
        if(pricePointIdString == null ) {
            return null;
        }
		int startIdx = pricePointIdString.indexOf("_") + 1;
		int endIdx = pricePointIdString.indexOf("_", startIdx );
		final String substring = pricePointIdString.substring(startIdx,endIdx);
        return substring;
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
		List<PricePoint> rv = new ArrayList<>(pricepoints);
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

	
    //ET-172 - Added to find the translated pricing text at startup
	public static final String getTranslatedPricingText(final CatalogApi catalogApi, final PricePoint pricepoint) {
	    
	    String translatedPricingText = "";
	    PricePoint linkedPricePoint = null;
	    
	    if(pricepoint == null || catalogApi == null) {
	        logger.error("Unable to translate pricing text due to invalid arguments. Template name: {}.  Returning an empty String. ", pricepoint!=null?pricepoint.getPricingTextTemplateName1():null );
            return translatedPricingText;
        }
	    
	    if(StringUtils.isNotBlank( pricepoint.getLinkPricepointId() )) {
	        linkedPricePoint = catalogApi.getPricePoint( pricepoint.getLinkPricepointId() );
	    }
	    
	    try {
            translatedPricingText  = 
                    catalogApi.translatePricingText( new PricePoint[] {pricepoint, linkedPricePoint }, pricepoint.getPricingTextTemplateName1(), catalogApi.getLocale().getLanguage(), 0);
        } catch (EcommerceException e) {
            logger.error("Unable to translate pricing text for name: {}.  Returning an empty String. ", pricepoint.getPricingTextTemplateName1(), e );
        }
	    
	    return translatedPricingText;
	    
	}
	
	
//	@Test
//	public void testUtil()	{
//		assertTrue(matchesPackageId("p001", "p001"));
//		assertFalse(matchesPackageId("!(p001)", "p001"));
//		assertTrue(matchesPackageId("!(p002)", "p001"));
//		assertFalse(matchesPackageId("p002", "p001"));
//		assertTrue(matchesPackageId("PK_Spotify.*", "PK_Spotify_SA"));
//		assertFalse(matchesPackageId("!PK_Spotify", "PK_Spotify"));
//		assertTrue(matchesPackageId("!PK_Spotify", "PK_netflix"));
//
//	}
	
	
	   /**
     * Checks whether the supplied packageId string matches the regex - incorporating custom negation (!) syntax<br/>
     * <br/>
     * regex, packageId<br/>
     * p001,      p001 -->    true<br/>
     * !(p001),		p001 --> false<br/>
     * !(p002),		p001 --> true<br/>
     * p001,      2pp_001 --> false<br/>
     * !PK_Spotify,	PK_Spotify -->false<br/>
     * !PK_Spotify,	PK_netflix --> true
     */
    public static boolean matchesPackageId(String regex, String packageId){
    	
       	boolean invertResult= false;
    	if(regex.startsWith("!"))	{	//negative logic
    		invertResult=true;
    		regex = regex.substring(1);	//remove the '!'
    		regex = regex.replaceAll("[()]", "");	//remove any brackets
    	}
		boolean result = packageId.matches(regex) ;
		return invertResult ? !result : result;
    }
	
}
