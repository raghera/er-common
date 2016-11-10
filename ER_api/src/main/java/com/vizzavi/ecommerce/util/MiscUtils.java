package com.vizzavi.ecommerce.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import com.vizzavi.ecommerce.common.ErCountry;
import com.vizzavi.ecommerce.common.Utils;


public class MiscUtils	{


    public static List<String> splitToVector( String s, String sep )
    {
        StringTokenizer st = new StringTokenizer( s, sep );
        List<String> ret = new ArrayList<String>( st.countTokens() );
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
    
    /**
     * This method provides the set of ErCountry objects representing the countries batch was configured for at startup(using eg -o or -O params when running jbctl) 
     * 
     * Going forward we shall use this method from this class and get rid of all the duplicate code in the Application.
     * 
     * @return - Set of {@link ErCountry} objects - may be empty but never null
     */
    public static Set<ErCountry> getCountriesFromStartupParams() {

		Set<ErCountry> countriesCoreIsConfiguredFor = new HashSet<ErCountry>();
		
		Set<String> countries = new HashSet<String>();
		
		//so if one country is specified with -o use that
		if (StringUtils.isNotBlank(System.getProperty("db.centralpricing.country")))
			countries.addAll(Utils.parseList(System.getProperty("db.centralpricing.country")));
		//else use all the countries specified with -O
		else if (StringUtils.isNotBlank(System.getProperty("db.centralpricing.countries")))
			countries.addAll(Utils.parseList(System.getProperty("db.centralpricing.countries")));
		
		
		for (String opco: countries){
			countriesCoreIsConfiguredFor.add(ErCountry.ErCountriesByCode.get(opco));
		}
		
		return countriesCoreIsConfiguredFor;
	}

}