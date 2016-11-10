package com.vizzavi.ecommerce.business.common;

/**
    This should be own pricing constants class.
*/
public class Constants {
    public final static int INT_NOT_SET = 998;
    //public final static long LONG_NOT_SET = 9999999999L;
    public final static int INT_MATCH_ALL = 999;
    //public final static long LONG_MATCH_ALL = 9999999998L;
    public final static String STRING_NOT_SET = "";
    public final static String STRING_MATCH_ALL = "*";
    /** The language code for the default language. */
    public final static String DEFAULT_LANGUAGE_CODE = "default";

    public static boolean isNotSet(int val)
    {
        boolean rv = false;
        if (val==INT_NOT_SET) {
            rv = true;
        }
        return rv;
    }

    public static boolean isNotSet(String val)
    {
        return (val != null && val.equals(STRING_NOT_SET));
    }

    public static boolean isMatchAll(String val)
    {
        return (val!=null && val.equals(STRING_MATCH_ALL));
    }

    public static boolean isMatchAll(int val)
    {
        return val == INT_MATCH_ALL;
    }

    public static String convertIntToString(int val)
    {
        String rv = null;

        if (isNotSet(val)) {
            rv = STRING_NOT_SET;
        } else if (isMatchAll(val)) {
            rv = STRING_MATCH_ALL;
        } else {
            rv = "" + val;
        }
        return rv;
    }

    public static int convertStringToInt(String val)
    {
        int rv = 0;

        if (isNotSet(val)) {
            rv = INT_NOT_SET;
        } else if (isMatchAll(val)) {
            rv = INT_MATCH_ALL;
        } else {
            try {
                rv = Integer.parseInt(val);
            } catch(Exception e) {
                rv = INT_NOT_SET;
            }
        }
        return rv;
    }

	//++ Scalability Option
	public final static int HVI_LOW_VOLUME_INTERFACE = 0;
	public final static int HVI_HIGH_VOLUME_INTERFACE = 1;
	public final static int HVI_QUEUE_VOLUME_INTERFACE = 9;

	public final static int HVI_REAL_TIME_USAGE_AUTH_RATE = 0;
	public final static int HVI_AUTH_SERVICE = 1;
	public final static int HVI_DENY_SERVICE = 2;
	//-- Scalability Option
	
	//A. Elorza. New for BlackList Default Category
	public final static String BLACKLIST_DEFAULT_CATEGORY = "ER2";
	//End
	
	//CR 1212 - add sales model
    final public static String RESELLER_SALES_MODEL = "Reseller";
    final public static String AGENCY_SALES_MODEL = "Agency";
}
