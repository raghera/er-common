package com.vizzavi.ecommerce.business.common;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import com.vizzavi.ecommerce.business.common.generated.Countries;
import com.vizzavi.ecommerce.business.common.generated.Country;

/**
 * TODO - make this an enum, and merge with ErCountry in com.vizzavi.ecommerce.common
 *
 */
public class CountryCode implements Serializable
{
   private    static final long serialVersionUID = -2073117339690676474L;
    /*
     * STATIC VARIABLES.
     */
   
    public static CountryCode[] ALL_COUNTRIES = null;
    //added to handle async and sync payment system type during loading of the countriescode.xml
    public static Hashtable<String, Integer> ASYNC_PSPS = new Hashtable<String, Integer>(); //key = country locale, value = async payment system type
    public static Hashtable<String, Integer> SYNC_PSPS = new Hashtable<String, Integer>(); //key = country locale, value = sync payment system type
    
    //@hud RFRFRF
    //for faster access
    private static Map<String, CountryCode> ALL_COUNTRIES_MAP_BY_COUNTRY_CODE = new HashMap<String, CountryCode>(); //key = country locale, value = CountryCode object
    //@lle - refactoring - CQ14113
    private static Map<Integer, CountryCode> ALL_COUNTRIES_MAP_BY_ID = new HashMap<Integer, CountryCode>(); //key=country locale, value = CountryCode object
    private static Map<Integer, CountryCode> ALL_COUNTRIES_MAP_BY_IDD_PREFIX = new HashMap<Integer, CountryCode>(); //key = IDD prefix, value = CountryCode object
//    private static int[] ALL_IDS = null; 

    //Initialize the classes with country codes loaded from ecom/countriescode.xml file.
    static {    	
    	try {
    		InputStream countryCodesFile = CountryCode.class.getClassLoader().getResourceAsStream("ecom/countrycodes.xml");
    		Unmarshaller unmarshaller = new Unmarshaller(Countries.class);
    		Countries countries = (Countries) unmarshaller.unmarshal(new InputSource(countryCodesFile));
    		ALL_COUNTRIES = new CountryCode[countries.getCountryCount()];    		
    		for (int i=0;i<countries.getCountryCount();i++) {
    			Country country = countries.getCountry(i);
    			CountryCode countryCode = new CountryCode(country);
    			ALL_COUNTRIES[i] = countryCode; 
    			ALL_COUNTRIES_MAP_BY_COUNTRY_CODE.put(country.getLocale(),countryCode);
    			ALL_COUNTRIES_MAP_BY_ID.put(new Integer(country.getId()), countryCode);
    			ALL_COUNTRIES_MAP_BY_IDD_PREFIX.put(new Integer(country.getIDD_prefix()), countryCode);
    			if (country.hasAsync_psps()) {
    				ASYNC_PSPS.put(country.getLocale(), new Integer(country.getAsync_psps()));    				
    			}
    			if (country.hasSync_psps()) {
    				SYNC_PSPS.put(country.getLocale(), new Integer(country.getSync_psps()));
    			}
    		}    		   		
	    	
    	} catch (Exception e) {
    		e.printStackTrace();    		
    	}
    }
    
    /*
     * INSTANCE VARIABLES.
     */

    //@hud RFRFRF
    // this code MUST BE ID
    //private int code;
    private int id;
    private Locale locale;
    private int currencyCode;

    /**
     * Only constructor is private because don't want clients creating these objects.
     */    
    private CountryCode( Country country) {
        this.id = country.getId();
        this.locale = new Locale(country.getLanguage(), country.getLocale());
        this.currencyCode = country.getCurrency_code();
    }
    public CountryCode() {
    }

    /*
     * STATIC METHODS.
     */

    /**
     * @hud RFRFRF
     * Get the CountryCode object corresponding to this String name.
     */
    public static CountryCode getCountryCode(String countryCode) throws Exception 
    {
    	CountryCode cc = ALL_COUNTRIES_MAP_BY_COUNTRY_CODE.get(countryCode);
    	if (cc != null) {
    		return cc;
    	}
    	else { 
            throw new Exception("Invalid country code: " + countryCode);
    	}    	
    }

    public static CountryCode getCountryCode(Locale loc) throws Exception
    {
        String countryCode = loc.getCountry();
        return getCountryCode(countryCode);
    }

    /**
     * Get the CountryCode object corresponding to this int code.
     */
    public static CountryCode getCountryCode(int id)  throws Exception 
    {
        CountryCode rv = ALL_COUNTRIES_MAP_BY_ID.get(new Integer(id));
        if (rv==null) {
            throw new Exception("Invalid country code id " + id);
        }
        return rv;
    }

//    /**
//     * @hud RFRFRF
//     * @deprecated see getIds()
//     * @return
//     */
//    @Deprecated
//	public static int[] getCodes() {    	
//    	return getIds();
//        
//    }
//    public static int[] getIds() {
//    	if (ALL_IDS == null) {
//    		ALL_IDS = new int[ALL_COUNTRIES.length];
//    		for (int i=0;i<ALL_COUNTRIES.length;i++) {
//    			ALL_IDS[i] = ALL_COUNTRIES[i].getId();
//    		}
//    	}
//    	return ALL_IDS;
//    }

    
    public static Collection<String> getNames() throws Exception {
    	return ALL_COUNTRIES_MAP_BY_COUNTRY_CODE.keySet();         
    }

    /**
     * if the string passed in (which should look like this: "GB,DE,IT") is null or empty, you get an empty list back, not an Exception
     * CR1222
     * @return Collection<String> of 2-digit iso-country codes
     */
    public static Collection<String> getNames(String countries)  {
    	ArrayList<String> l = new ArrayList<String>();
    	if (countries== null ||countries.equals(""))
    		return l;
    	StringTokenizer st = new StringTokenizer(countries, ",");
    	while (st.hasMoreTokens()){
    		l.add(st.nextToken());
    	}
    	return l;    	
    }
  
    /**
     * same as getId()
     * @return an int
     */
    public int getCode()   {
        return this.id;
    }
    public int getId() {
    	return id;
    }

    /**
     * @hud RFRFRF
     * 
     */
    public String getName()   {
        return this.locale.getCountry();
    }
    public String getCountryCode() {
    	return locale.getCountry();
    }

    
    @Override
	public String toString()
    {
        return this.locale.getCountry();
    }

    public Locale getLocale()
    {
        return this.locale;
    }

    /**
        This uses the international direct dialing prefix to work out the locale
    */
    public static CountryCode getCountryCodeFromMsisdn(String msisdn) throws Exception
    {
    	Iterator<Integer> allIDDs = ALL_COUNTRIES_MAP_BY_IDD_PREFIX.keySet().iterator();
    	while (allIDDs.hasNext()) {
    		Integer idd = allIDDs.next();
    		if (msisdn.startsWith(idd.toString())) {
    			return ALL_COUNTRIES_MAP_BY_IDD_PREFIX.get(idd);
    		}
    	}        
        throw new Exception("Invalid msisdn " + msisdn);
    }

    /**
        This uses the international direct dialing prefix to work out the locale.
        @return null if can't work it out
    */
    public static Locale getLocaleFromMsisdn(String msisdn)
    {
        Locale ret = null;
        try {
            CountryCode cc = getCountryCodeFromMsisdn( msisdn );
            ret = cc.getLocale();
        }
        catch(Exception e) {        
        }
        return ret;
    }

    //////////////////////////////////////////////////////////////////////
    //@hud RFRFRF
    /**
     * @hud added for convenience
     */
    public static int getSize()
    {
    	return ALL_COUNTRIES.length;
    }
    
    public static CountryCode[] getAllCountries()
    {
    	return ALL_COUNTRIES;
    }
    
    public static Locale getLocale(String countryCode)
    {
    	CountryCode cc = ALL_COUNTRIES_MAP_BY_COUNTRY_CODE.get(countryCode);
    	if (cc != null) {
    		return cc.getLocale();
    	}
    	else {
    		return null;
    	}
    }
    
    
  
	public int getCurrencyCode() {
		return currencyCode;
	}
}
