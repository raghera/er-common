package com.vizzavi.ecommerce.util;

import java.util.*;


public class MiscUtils
{
//	/**
//	 * @deprecated should use CountryCode.getLocale(String countryCode)
//	 * @param isoCode
//	 * @return
//	 */
//    @Deprecated
//	public static Locale getLocaleFromIsoCode(String isoCode)
//    {
//    	if("GB".equals(isoCode)) return Locale.UK;
//        if("DE".equals(isoCode)) return Locale.GERMANY;
//        if("FR".equals(isoCode)) return Locale.FRANCE;
//        if("IT".equals(isoCode)) return Locale.ITALY;
//        return null;
//    }

    public static Vector splitToVector( String s, String sep )
    {
        StringTokenizer st = new StringTokenizer( s, sep );
        Vector ret = new Vector( st.countTokens() );
        while (st.hasMoreTokens()) {
            ret.add( st.nextToken() );
        }
        return ret;    
    }

    public static String[] splitToArray( String s, String sep )
    {
        String[] ret = null;
        if( s != null ) {
            StringTokenizer st = new StringTokenizer( s, sep );
            ret = new String[ st.countTokens() ];
            int i = 0;
            while (st.hasMoreTokens()) {
                ret[i++] = st.nextToken();
            }
        }
        return ret;    
    }
     

    
    /**
     * [1] CQ14118
     * This will round a double to the given number of decimal places. 
     * 
     * @param d - the double to round.
     * @param nthDecimal - the number of decimal places to use for rounding.
     * @return - the rounded double value.
     */
    public static double roundDouble(double d, int nthDecimal)
    {
      return Math.round(d * Math.pow(10, nthDecimal)) 
      		/ Math.pow(10,nthDecimal);
    }    

}