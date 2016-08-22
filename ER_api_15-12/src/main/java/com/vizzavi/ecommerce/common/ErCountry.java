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
 * select country_name ||'('||country_obj_id||', new Locale("'||iso_language||'", "'||iso_country||'"), "'||DEFAULT_TIMEZONE||'"), ' from er_country order by country_obj_id
*/
public enum ErCountry { 
	Belgium(0, new Locale("fr", "BE"), "Europe/Brussels"), 
	Switzerland(1, new Locale("de", "CH"), "Europe/Zurich"), 
	Germany(2, Locale.GERMANY, "Europe/Berlin"), 
	Spain(3, new Locale("es", "ES"), "Europe/Madrid"), 
	France(4, Locale.FRANCE, "Europe/Paris"), 
	UK(5,Locale.UK, "Europe/London"), 
	Greece(6, new Locale("el", "GR"), "Europe/Athens"), 
	Hungary(7, new Locale("hu", "HU"), "Europe/Budapest"), 
	Ireland(8, new Locale("en", "IE"), "Europe/Dublin"), 
	Italy(9, Locale.ITALY, "Europe/Rome"), 
	Netherlands(10, new Locale("nl", "NL"), "Europe/Amsterdam"), 
	Poland(11, new Locale("pl", "PL"), "Europe/Warsaw"), 
	Portugal(12, new Locale("pt", "PT"), "Europe/Lisbon"), 
	Romania(13, new Locale("ro", "RO"), "Europe/Bucharest"), 
	Sweden(14, new Locale("sv", "SE"), "Europe/Stockholm"), 
	Australia(15, new Locale("en", "AU"), "Australia/Sydney"), 
	New_Zealand(16, new Locale("en", "NZ"), "Pacific/Auckland"), 
	Egypt(17, new Locale("ar", "EG"), "Africa/Cairo"), 
	Austria(18, new Locale("de", "AT"), "Europe/Vienna"), 
	Bahrain(19, new Locale("ar", "BH"), "Asia/Bahrain"), 
	Croatia(20, new Locale("hr", "HR"), "Europe/Zagreb"), 
	Denmark(21, new Locale("da", "DK"), "Europe/Copenhagen"), 
	Estonia(22, new Locale("et", "EE"), "Europe/Tallinn"), 
	Finland(23, new Locale("fi", "FI"), "Europe/Helsinki"), 
	Iceland(24, new Locale("is", "IS"), "Europe/Reykjavik"), 
	Japan(25, Locale.JAPAN, "Asia/Tokyo"), 
	Jordan(26, new Locale("ar", "JO"), "Asia/Amman"), 
	Kuwait(27, new Locale("ar", "KW"), "Asia/Kuwait"), 
	Luxembourg(28, new Locale("fr", "LU"), "Europe/Luxembourg"), 
	Malaysia(29, new Locale("ms", "MY"), "Asia/Kuala_Lumpur"), 
	Malta(30, new Locale("en", "MT"), "Europe/Malta"), 		//MQC9461 - change from Maltese to English to work around a bug in ER atomic
	Singapore(31, new Locale("zh", "SG"), "Asia/Singapore"), 
	Slovenia(32, new Locale("sl", "SI"), "Europe/Ljubljana"), 
	South_Africa(33, new Locale("en", "ZA"), "Africa/Johannesburg"), 
	Turkey(34, new Locale("tr", "TR"), "Asia/Istanbul"), 
	USA(35, new Locale("en", "US"), "America/New_York"), 
	Czech_Republic(36, new Locale("cs", "CZ"), "Europe/Prague"), 
	Bulgaria(37, new Locale("bg", "BG"), "Europe/Sofia"), 
	Serbia(38, new Locale("sr", "RS"), "Europe/Belgrade"), 
	Macedonia(39, new Locale("mk", "MK"), "Europe/Skopje"), 
	India(40, new Locale("hi", "IN"), "Asia/Delhi"), 
	Cyprus(41, new Locale("el", "CY"), "Europe/Nicosia"), 
	Russia(42, new Locale("ru", "RU"), "Europe/Moscow"), 
	Chile(43, new Locale("es", "CL"), "America/Santiago"), 
	Democratic_Republic_of_Congo(44, new Locale("fr", "CD"), "Africa/Kinshasa"), 
	Tanzania(45, new Locale("sw", "TZ"), "Africa/Dar_es_Salaam"), 
	Ghana(46, new Locale("en", "GH"), "Africa/Accra"), 
	Qatar(47, new Locale("ar", "QA"), "Asia/Qatar"), 
	Albania(48, new Locale("sq", "AL"), "Europe/Tirana"), 
	Fiji(49, new Locale("en", "FJ"), "Pacific/Fiji"), 
	Argentina(50, new Locale("es", "AR"), "America/Buenos_Aires"), 
	Brazil(51, new Locale("pt", "BR"), "America/Sao_Paulo"), 
	Canada(52, new Locale("en", "CA"), "America/Toronto"), 
	Columbia(53, new Locale("es", "CO"), "America/Bogota"), 
	Dominican_Republic(54, new Locale("es", "DO"), "America/Santo_Domingo"), 
	Ecuador(55, new Locale("es", "EC"), "America/Guayaquil"), 
	El_Salvador(56, new Locale("es", "SV"), "America/El_Salvador"), 
	Guatemala(57, new Locale("es", "GT"), "America/Guatemala"), 
	Honduras(58, new Locale("es", "HN"), "America/Tegucigalpa"), 
	Mexico(59, new Locale("es", "MX"), "America/Mexico_City"), 
	Nicaragua(60, new Locale("es", "NI"), "America/Managua"), 
	Paraguay(61, new Locale("es", "PY"), "America/Asuncion"), 
	Peru(62, new Locale("es", "PE"), "America/Lima"), 
	Puerto_Rico(63, new Locale("es", "PR"), "America/Puerto_Rico"), 
	Uruguay(64, new Locale("es", "UY"), "America/Montevideo");
		 
	private Integer erId;
	private Locale locale;
	private String	defaultTimezone;
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
	
	private ErCountry(Integer id, Locale loc, String defaultTimezone )	{
		erId=id;
		locale=loc;
		this.defaultTimezone = defaultTimezone;
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
	 * pass in a country code (e.g. 'DE') and get a Locale back - eg {@code Locale.GERMANY} - or null if the country code is not recognised.
	 * @param countryCode a 2 letter ISO 3166 string
	 * @return
	 */
	public static Locale getLocale(String countryCode)	{
		ErCountry opco = getCountryByCode(countryCode);
		if (opco!=null)
			return opco.getLocale();
		else
			return null;
	}

	/**
	 * attempts to create a Locale object from the locale String (eg 'en_GB') passed in.<br/>
	 * You can also pass in just 'GB' or 'DE' and it will try to retrieve the complete Locale (with default language).<br/>
	 * @param localeString eg 'fr_CH' or 'IT' or even 'en_uk_GB'
	 * @return null if the locale String was null; otherwise a Locale object with at least country populated, and probably language too
	 */
	public static Locale parseLocale(String localeString)	{
		if (localeString==null) 
			return null;
		Locale locale=null;
		if (localeString.indexOf('_')>0)	{	//ie language and country specified
			String[] tokens = localeString.split("_");	//could be ['en', 'GB'] or ['en', 'uk', 'GB']
			locale = new Locale(tokens[0], tokens[tokens.length-1]);
		}	else	{	//assume only country specified, since that is required
			
			if (getCountryByCode(localeString)!=null)	{
				locale = ErCountry.getCountryByCode(localeString).getLocale();
			}	else	{
				//logger.warn("unknown purchase locale {}", purchaseLocale);
				locale = new Locale("", localeString);
			}
		}
		return locale;
	}

	/**
	 * the default timezone for the country, eg "Africa/Johannesburg" for South Africa
	 * @return
	 */
	public String getDefaultTimezone() {
		return defaultTimezone;
	}
}
