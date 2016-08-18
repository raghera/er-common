package com.vodafone.global.er.util;

import java.util.Locale;

/**
 * NB this class needs to be encoded correctly, since it contains euro (€) and pound (£) symbols.  
 * @author matt
 *
 */
public class CurrencyUtils {
	
    public static String toCurrency(double d) {
        long l = StrictMath.abs(StrictMath.round(d * 100.00));
        if (l != 0) {
            String s = Long.toString(l);
            String pence = "";
            String pounds = "";
            if (s.length() < 2) {
                pence = "0" + s;
            }
            else {
                pence = s.substring(s.length() - 2);
                pounds = s.substring(0, s.length() - 2);
            }
            if (pounds == null || pounds.length() == 0) {
                pounds = "0";
            }
            return pounds + "." + pence;
        }
        else {
            return "0.00";
        }
    }


    public static String toCurrencyWithSymbol(double d) {
		return toCurrencyWithSymbol(d, Locale.getDefault());
	}

    public static String toCurrencyWithSymbol(double d, Locale locale) {
        return getCurrencySymbol(locale) + toCurrency(d);

    }

    /**
     * returns a string for the currency symbol, depending on locale - eg £ for Locale.UK.  N.B. this is not an xml character entity reference
     * @param locale
     * @return
     */
	public static String getCurrencySymbol(Locale locale) {
        String country = locale.getCountry();

    	
        String symbol = "€";	//euro, default
        if (country.equals("GB")) {
		            symbol = "£";
        }
        else if (country.equals("EG")) {
            symbol = "EGP";
        }
        //MQC8716 - start - removed - use default
        /*
        else if (country.equals("AT")) {
            symbol = "&euro;";
        }
        */
        //MQC8716 - end
        else if (country.equals("BH")) {
            symbol = "BHD";
        }
        else if(country.equals("BG")) {
		    symbol = "BGN";
        }
        else if(country.equals("CZ")) { //REMEDY 4697
		    symbol = "CZK"; //REMEDY 4697
        } //REMEDY 4697
        else if (country.equals("HR")) {
            symbol = "HRK";
        }
        else if (country.equals("DK")) {
            symbol = "DKK";
        }
        else if (country.equals("EE")) {
            symbol = "EEK";
        }
        //MQC8716 - start - removed - use default
        /*
        else if (country.equals("FI")) {
            symbol = "&euro;";
        }
        */
        //MQC8716 - end
        else if (country.equals("IS")) {
            symbol = "ISK";
        }
        else if (country.equals("JP")) {
            symbol = "JPY";
        }
        else if (country.equals("JO")) {
            symbol = "JOD";
        }
        else if (country.equals("KW")) {
            symbol = "KWD";
        }
        //MQC8716 - start - removed - use default
        /*
        else if (country.equals("LU")) {
            symbol = "&euro;";
        }
        */
        //MQC8716 - end
        else if (country.equals("MY")) {
            symbol = "MYR";
        }
        //MQC8716 - start - removed - use default
        /*
        else if (country.equals("MT")) {
			//REMEDY 6691 - change Malta to Euro
            //symbol = "MTL";
            symbol = "&euro;";
        }
        else if(country.equals("RO")) { //REMEDY 4697
		    //symbol = "&dollar"; //REMEDY 4697
		    //REMEDY 6686 - use $ symbol
		    //symbol = "$";
		    //REMEDY 6654 - Romania have moved to Euro
		    symbol = "&euro;";
        } //REMEDY 4697
        */
        //MQC8716 - end
        else if (country.equals("SG")) {
            symbol = "SGD";
        }
        //MQC8716 - start - removed - use default
        /*
        else if (country.equals("SI")) {
            //REMEDY 5903 - change Slovenia to Euro
            //symbol = "SIT";
            symbol = "&euro;";
        }
        */
        //MQC8716 - end
        else if (country.equals("ZA")) {
            symbol = "ZAR";
        }
        else if (country.equals("TR")) {
            //symbol = "TRL";
            //REMEDY 6126 - change symbol to YTL
            //MQC 4476 - change symbol to TL
            symbol = "TL";
        }
        else if (country.equals("US")) {
            //REMEDY 6686 - use $ symbol
            //symbol = "&dollar;";
		    symbol = "$";
        }
        //REMEDY 5990 - add Serbia
        else if (country.equals("RS")) {
		    symbol = "RSD";
        }
        //REMEDY 6270 - add Macedonia
        else if (country.equals("MK")) {
		    symbol = "MKD";
        }
        //REMEDY 6686 - add Austrailia
        else if (country.equals("AU")) {
		    symbol = "$";
        }
        //REMEDY 6686 - add New Zealand
        else if (country.equals("NZ")) {
			symbol = "$";
        }
        //REMEDY 6692 - add India
		else if (country.equals("IN")) {
			symbol = "INR";
        }
        //MQC8716 - start - removed - use default
        /*
        //REMEDY 6692 - add Cyprus
		else if (country.equals("CY")) {
			symbol = "&euro;";
        }
        */
        //MQC8716 - end
        //MQC 4200 - add Russia
		else if (country.equals("RU")) {
			symbol = "RUB";
        }
        //MQC 4200 - add Chile
		else if (country.equals("CL")) {
			symbol = "CLP";
        }
		//MQC 4489 - add Democratic Republic of Congo
		else if (country.equals("CD")) {
			symbol = "CDF";
        }
		//MQC 4489 - add Tanzania
		else if (country.equals("TZ")) {
			symbol = "TZS";
        }
		//MQC 4489 - add Ghana
		else if (country.equals("GH")) {
			symbol = "GHC";
        }
		//MQC 4489 - add Qatar
		else if (country.equals("QA")) {
			symbol = "QAR";
        }
		//MQC 4489 - add Albania
		else if (country.equals("AL")) {
			symbol = "ALL";
        }
		//MQC 4489 - add Fiji
		else if (country.equals("FJ")) {
			symbol = "FJD";
        }
		//CR 1051 - Add new countries START
		else if (country.equals("AR")) {
			symbol = "ARS";
        }
		else if (country.equals("BR")) {
			symbol = "BRL";
        }
		else if (country.equals("CA")) {
			symbol = "$";
        }
		else if (country.equals("CO")) {
			symbol = "COP";
        }
		else if (country.equals("DO")) {
			symbol = "DOP";
        }
		else if (country.equals("EC")) {
			symbol = "$";
        }
		else if (country.equals("SV")) {
			symbol = "$";
        }
		else if (country.equals("GT")) {
			symbol = "GTQ";
        }
		else if (country.equals("HN")) {
			symbol = "HNL";
        }
		else if (country.equals("MX")) {
			symbol = "MXN";
        }
		else if (country.equals("NI")) {
			symbol = "NIO";
        }
		else if (country.equals("PY")) {
			symbol = "PYG";
        }
		else if (country.equals("PE")) {
			symbol = "PEN";
        }
		else if (country.equals("PR")) {
			symbol = "$";
        }
		else if (country.equals("UY")) {
			symbol = "UYU";
        }
		//CR 1051 - Add new countries END
        return symbol; 
        
	}

    public static String toAmountWithNoSymbol(String d) {

        return toCurrency((new Double(d.substring(d.lastIndexOf(";")+1))).doubleValue());
    }
}
