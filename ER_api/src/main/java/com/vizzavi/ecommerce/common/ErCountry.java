package com.vizzavi.ecommerce.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/**
 * This enum encapsulates all the information in the ER_COUNTRY table in native Java enum form, using locale objects not Strings, etc
 * 
 * @author matt
 *
 */
//TODO add currency and so on from java.util.Currency and remove CountryCode class
/*
 * select country_name ||'('||country_obj_id||', new Locale("'||iso_language||'", "'||iso_country||'")), ' from er_country
*/
public enum ErCountry { Belgium(0,new Locale("fr", "BE")), Switzerland(1,new Locale("de", "CH"))
	, Germany(2, Locale.GERMANY), Spain(3, new Locale("es", "ES"))	
	, France(4,Locale.FRANCE)	, UK(5, Locale.UK) 
	, Greece(6,new Locale("el", "GR")), Hungary(7, new Locale("hu", "HU"))		
	, Ireland(8, new Locale("en", "IE")), Italy(9, Locale.ITALY)
	, Netherlands(10,new Locale("nl", "NL")), Poland(11,new Locale("pl", "PL"))
	, Portugal(12,new Locale("pt", "PT")), Romania(13, new Locale("ro", "RO"))
	, Sweden(14, new Locale("sv", "SE")), Australia(15, new Locale("en", "AU"))
	, New_Zealand(16, new Locale("en", "NZ")), Egypt(17, new Locale("ar", "EG"))	,
	Austria(18, new Locale("de", "AT")), 
	Bahrain(19, new Locale("ar", "BH")), 
	Croatia(20, new Locale("hr", "HR")), 
	Denmark(21, new Locale("da", "DK")), 
	Estonia(22, new Locale("et", "EE")), 
	Finland(23, new Locale("fi", "FI")), 
	Iceland(24, new Locale("is", "IS")), 
	Japan(25, Locale.JAPAN), 
	Jordan(26, new Locale("ar", "JO")), 
	Kuwait(27, new Locale("ar", "KW")), 
	Luxembourg(28, new Locale("fr", "LU")), 
	Malaysia(29, new Locale("ms", "MY")), 
	Malta(30, new Locale("en", "MT")), 	//MQC9461 - change from Maltese to English to work around a bug in ER atomic
	Singapore(31, new Locale("zh", "SG")), 
	Slovenia(32, new Locale("sl", "SI")), 
	South_Africa(33, new Locale("en", "ZA")), 
	Turkey(34, new Locale("tr", "TR")), 
	USA(35, new Locale("en", "US")), 
	Czech_Republic(36, new Locale("cs", "CZ")), 
	Bulgaria(37, new Locale("bg", "BG")), 
	Serbia(38, new Locale("sr", "RS")), 
	Macedonia(39, new Locale("mk", "MK")), 
	India(40, new Locale("hi", "IN")), 
	Cyprus(41, new Locale("el", "CY")), 
	Russia(42, new Locale("ru", "RU")), 
	Chile(43, new Locale("es", "CL")), 
	Democratic_Republic_of_Congo(44, new Locale("fr", "CD")), 
	Tanzania(45, new Locale("sw", "TZ")), 
	Ghana(46, new Locale("en", "GH")), 
	Qatar(47, new Locale("ar", "QA")), 
	Albania(48, new Locale("sq", "AL")), 
	Fiji(49, new Locale("en", "FJ")), 
	Argentina(50, new Locale("es", "AR")), 
	Brazil(51, new Locale("pt", "BR")), 
	Canada(52, new Locale("en", "CA")), 
	Columbia(53, new Locale("es", "CO")), 
	Dominican_Republic(54, new Locale("es", "DO")), 
	Ecuador(55, new Locale("es", "EC")), 
	El_Salvador(56, new Locale("es", "SV")), 
	Guatemala(57, new Locale("es", "GT")), 
	Honduras(58, new Locale("es", "HN")), 
	Mexico(59, new Locale("es", "MX")), 
	Nicaragua(60, new Locale("es", "NI")), 
	Paraguay(61, new Locale("es", "PY")), 
	Peru(62, new Locale("es", "PE")), 
	Puerto_Rico(63, new Locale("es", "PR")), 
	Uruguay(64, new Locale("es", "UY"));
		 
	private Integer erId;
	private Locale locale;
	/** a map of all the countries, indexed by ER ID (eg Portugal is 13) */
	public final static  Map<Integer, ErCountry> ErCountries ;
	/**  a map of all the countries, indexed by 2 letter ISO country code (eg DE for Germany) */
	public final static  Map<String, ErCountry> ErCountriesByCode;

	private final static Map<Integer, ErCountry> tmpCountries = new HashMap<Integer, ErCountry>();
	private final static Map<String, ErCountry> tmpCountriesByCode = new HashMap<String, ErCountry>();
	
	static	{	//populate hashmap using id as an index
		
		for(ErCountry type: ErCountry.values())	{
			tmpCountries.put(type.getErId(), type);
			tmpCountriesByCode.put(type.getLocale().getCountry(), type);
		}
		ErCountries = Collections.unmodifiableMap(tmpCountries);
		ErCountriesByCode = Collections.unmodifiableMap(tmpCountriesByCode);
		
	}
	
	private ErCountry(Integer id, Locale loc )	{
		erId=id;
		locale=loc;
	}
	
	/**The COUNTRY_OBJ_ID from the DB (eg Portugal is 13) */
	public Integer getErId() {
		return erId;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	/**
	 * returns the ErCountry object for the corresponding country code<br/>
	 * e.g. pass in 'DE' and get {@code ErCountry.Germany}
	 * @param countryCode
	 * @return
	 */
	public static ErCountry getCountryByCode(String countryCode)	{
		return ErCountriesByCode.get(countryCode);
	}
	
	/**
	 * returns the ErCountry object for the corresponding ER db ID<br/>
	 * e.g. pass in 13 and get {@code ErCountry.Portugal}
	 * @param countryCode
	 * @return
	 */
	public static ErCountry getCountryById(int id)	{
		return ErCountries.get(id);
	}
	
	
	/**
	 * can be used to compare countries - will compare using java.lang.String.compareTo() (ie lexicographically) based on 2 letter country code.
	 * @author matt
	 *
	 */
	public static class CountryComparator implements Comparator<ErCountry>	{

		@Override
		public int compare(ErCountry o1, ErCountry o2) {

			if(o1!=null && o2!=null)	{
				return o1.getLocale().getCountry().toString().compareTo(o2.getLocale().getCountry().toString());
			}
			return 0;
		}
	}

	/**
	 * Returns the country/region code for this locale  (eg DE for Germany), which will either be the empty string or an uppercase ISO 3166 2-letter code.
	 * 
	 */
	public String getCode() {
		return locale.getCountry();
	}

	/**
	 * returns an ErCountry matching the specified locale
	 * @param locale
	 * @return
	 */
	public static ErCountry getByLocale(Locale locale)	{
		return ErCountriesByCode.get(locale.getCountry());
	}
	
	/**
	 * pass in a country code (e.g. 'DE') and get a Locale back - eg {@code Locale.GERMANY}
	 * @param countryCode a 2 letter ISO 3166 string
	 * @return
	 */
	public static Locale getLocale(String countryCode)	{
		return getCountryByCode(countryCode).getLocale();
	}

	/**
	 * attempts to create a Locale object from the locale String (eg 'en_GB') passed in.<br/>
	 * You can also pass in just 'GB' or 'DE' and it will try to retrieve the complete Locale (with default language).<br/>
	 * @param localeString eg 'fr_CH' or 'IT'
	 * @return null if the locale String was null; otherwise a Locale object with at least country populated, and probably language too
	 */
	public static Locale parseLocale(String localeString)	{
		if (localeString==null) 
			return null;
		Locale locale=null;
		if (localeString.indexOf('_')>0)	{	//ie language and country specified
			locale = new Locale(localeString.split("_")[0], localeString.split("_")[1]);
		}	else	{
			//logger.info("unusual request locale : {}", localeString);
			
			if (getCountryByCode(localeString)!=null)	{
				locale = ErCountry.getCountryByCode(localeString).getLocale();
			}	else	{
				//logger.warn("unknown purchase locale {}", purchaseLocale);
				locale = new Locale("", localeString);
			}
		}
		return locale;
	}
}
