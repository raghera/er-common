package com.vizzavi.ecommerce.common;

import com.vizzavi.ecommerce.business.common.Constants;

/**
 * Consists of utility methods for rating purposes (moved from PurchaseBean class)
 * @author lel
 * @version
 *
 */
public final class RatingUtils {

	/**
	 * default constructor
	 */
	public RatingUtils() {
		super();		
	}
	
	/**
	 * Returns true following these rules:
	 * 	- if the rating attribute is set to match all, all matches will be returned
	 * 	- if the pricing attribute is set to match all, all matches wil be returned
	 * 	- if the rating attribute is not set, it will only match *
	 * 	- if the rating attribute is set to particular value, it will only match value
	 * 
	 * @param pricingAttribute 	the value retrieved from priceplan
	 * @param ratingAttribute	the value retrieved from rating attributes
	 * @return true|false as described above
	 */
	 public static boolean isAttributeEqual(String pricingAttribute, String ratingAttribute)
	 {
	   boolean rv = false;
	
	   if ((ratingAttribute != null) && Constants.isMatchAll(ratingAttribute))
	   {
	     rv = true;
	   }
	   else if ((pricingAttribute != null) && Constants.isMatchAll(pricingAttribute))
	   {
	     rv = true;
	   }
	   else if (Constants.isNotSet(ratingAttribute) && Constants.isMatchAll(pricingAttribute))
	   {
	     rv = true;
	   }
	   else if (isMatch(pricingAttribute, ratingAttribute))
	   {
	     rv = true;
	   }
	
	   return rv;
	 }
	
	 /**
	  * This checks that two attributes are equal if and only if they are not null, are set to a particular value,
	  * and have exactly the same value.
	  * 	    
	  * @param pricingAttribute	The value retrieved from the priceplan
	  * @param ratingAttribute	The value retrieved from the rating attributes
	  * @return true|false as above.
	  */
	 public static boolean isMatch(String pricingAttribute, String ratingAttribute)
	 {
	   boolean rv = false;
	
	   if (Constants.isNotSet(pricingAttribute)
	       || Constants.isNotSet(ratingAttribute)
	   )
	   {
	     // false
	   }
	   else if ((pricingAttribute != null) && (ratingAttribute != null)
	       && pricingAttribute.equals(ratingAttribute)
	   )
	   {
	     rv = true;
	   }
	
	   return rv;
	 }
	
	/**
	 * This checks that two attributes are equal if and only if they are not null, are set to some value,
	 * and have exactly the same value
	 * 
	 * @param pricingAttribute	the value retrieved from the priceplan
	 * @param ratingAttribute	the value retrieved from the rating attribute
	 * @return 	true as described above
	 * 			false otherwise
	 */
	 public static boolean isMatch(int pricingAttribute, int ratingAttribute)
	 {
	   boolean rv = false;
	
	   if (Constants.isNotSet(pricingAttribute)
	       || Constants.isNotSet(ratingAttribute)
	   )
	   {
	     // false
	   }
	   else if (pricingAttribute == ratingAttribute)
	   {
	     rv = true;
	   }
	
	   return rv;
	 }
	
	/**
	 * Returns true following these rules:
	 * 	- if the rating attribute is set to match all, all matches will be returned
	 *  - if the rating attribute is not set, it will only match *
	 *  - if the rating attribute is set to particular value, it will only match value and *
	 *  
	 * @param pricingAttribute	the value retrieved from the priceplan
	 * @param ratingAttribute	the value retrieved from the rating attributes
	 * @return	true as described above
	 * 			false otherwise
	 */
	 public static boolean isAttributeEqual(int pricingAttribute, int ratingAttribute)
	 {
	   boolean rv = false;
	
	   if (Constants.isMatchAll(ratingAttribute))
	   {
	     rv = true;
	   }
	   else if (Constants.isMatchAll(pricingAttribute))
	   {
	     rv = true;
	   }
	   else if (Constants.isNotSet(ratingAttribute)
	       && Constants.isMatchAll(pricingAttribute)
	   )
	   {
	     rv = true;
	   }
	   else if (isMatch(pricingAttribute, ratingAttribute))
	   {
	     rv = true;
	   }
	
	   return rv;
	 }
	
	 /**
	  * If val2 contains match all, always return true.
	  * If val2 contains only particular values, it will only match particular values and *
	  * @param val1	the value to compare
	  * @param val2	the values to compare against
	  * @return
	  */
	 public static boolean isAttributeEqual(String val1, String[] val2)
	 {
	   boolean rv = false;
	   	
	   if ((val2 == null) || (val2.length == 0))
	   {
	     rv = isAttributeEqual(val1, "");
	   }
	   else
	   {
	     for (String element : val2) {
	       if ((val1 != null) && val1.equals(element))
	       {
	         rv = true;
	         break;
	       }
	     }
	   }
	
	   return rv;
	 }

}
