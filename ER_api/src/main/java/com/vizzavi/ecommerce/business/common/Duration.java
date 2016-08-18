package com.vizzavi.ecommerce.business.common;

import java.io.Serializable;
import java.util.Locale;

import org.apache.log4j.Logger;

/**
    The device type represents the type of the device the user is using
*/
public class Duration implements Serializable{



    /**
	 * 
	 */
	private static final long serialVersionUID = 1485268952386596620L;
	/**
     * @since 5.1
     */
	protected static final String DAILY_STR = "daily";
    public static final String MINUTES = "minute(s)";
	protected static final String DAYS = "day(s)";
	protected static final String MONTHS = "month(s)";
	protected static final String YEARS = "year(s)";

    protected static final int DAY_BOUNDERIES [] = new int[] {1000,2000};
    protected static final int MINUTE_BOUNDERIES = 100000;
 	protected static final int ENUM_MAX_ID = 11;
    protected static final int FIXED_EXPIRY_BOUNDERIES [] = new int[] {-100,-1};

    public static final int ONEDAY		 = 8;
	//Not a Duration
    public static final int DAILY		 = 9;

    public static final int WEEKLY     	 = 2;
    public static final int FORTNIGHTLY 	 = 3;
    public static final int MONTHLY		 = 4;
    public static final int QUARTERLY	 = 5;
    public static final int HALFYEARLY	 = 6;
    public static final int ANNUALY		 = 7;
    public static final int EIGHTEEN_MONTHLY = 10;
  	public static final int TWO_YEARLY = 11;


    public static final int MICRODURATIONCODE = MINUTE_BOUNDERIES;
    // Don't show 1 as it is not an option. It is the default value for event packages
    /**
     * @deprecated
     */
    @Deprecated
	protected static final int [] sValues = new int[] {
    //MQC MQC8290 - added 15, 60 and 90 days
    //MQC7976 - added -1 No duration for Calendar package
	-1,		2,      3,      4,          5,      6,      7,      8,      9, 10, 11,
    1002,     1003,    1004,    1005,    1006,    1007,    1008,    1009,    1010,
    1011,     1012,    1013,    1014,    1015,	  1016,    1018,    1020,    1021,    1022,
    1024,     1026,    1028,    1030,    1035,    1040,    1045,  	1060,	 1090};

    protected String mUnit = "UNKNOWN";
    protected int mValue = 0;
	protected int mDurationCode = 0;
	
	//[1] R9 Phase 3 STKHREQ13113 Start	
	public final static String DEFAULT_DURATION_TEXT = "*";
	
	private String type = null;
	/** The language code **/
	private String language = "en";
	private String recurring = null;
	private String nonRecurring = null;
	private String customRecurring = null;
	private String customNonRecurring = null;

	
    //Remedy 4487/6249 - Alan
    /**
     * Get the default locale from the JVM.
     */
    private static Locale defaultLocale = Locale.getDefault();
	
    /**
     * The logger used for performing log4j logging.
     */
    private static Logger logger = Logger.getLogger(Duration.class);
    
    //END 6249
	public Duration(int code, String type, String language){
		this.mDurationCode = code;
		this.type = type;
		this.language = language;
	}
	//[1] R9 Phase 3 STKHRE13113 End	
	
    public Duration ( int durationCode ){
 		mUnit = getUnit(durationCode);
 		mValue =  calculateDuration (durationCode);
 		mDurationCode = durationCode;
    }

    @Override
	public String toString(){
    	String rv = "{ "+mDurationCode+": the duration is "+mValue +" "+mUnit+" }";
		return rv;
    }


    public static int calculateDuration ( int durationCode ){
    	int rv = 0;

    	if ((durationCode>0)&&(durationCode<=ENUM_MAX_ID)){
    		switch (durationCode){
    		case 2: rv = 7;break; //days
    		case 3: rv = 14;break; //days
    		case 4: rv = 1;break;//month
    		case 5: rv = 3;break; //months
    		case 6: rv = 6;break; //months
    		case 7: rv = 1;break; //year
    		case 8: rv = 1;break; //Not a duration
    		case 9: rv = 1;break; //day
    		case 10: rv = 18;break; // months
    		case 11: rv = 2;break; // years
    		}
    	}
        else if (durationCode>=MINUTE_BOUNDERIES) rv = durationCode - MINUTE_BOUNDERIES;
        else if ((durationCode>DAY_BOUNDERIES[0])&&(durationCode<DAY_BOUNDERIES[1]))
          	    rv = durationCode-DAY_BOUNDERIES[0];

    	return rv;
    }

   /*

   *

   *

   */

   public static Duration getDurationCode(int val,String unit){

   int code=-1;

   if (unit.equals(MINUTES)){

   code = val + MINUTE_BOUNDERIES;

   }else if (unit.equals(DAYS)){

   code = val + DAY_BOUNDERIES[0];

   }else {

   // This Duration & unit combination is not applicable

   }

   return new Duration(code);

   }



   public int getDurationCode (){
	   return this.mDurationCode;
   }
   
   /**
    * Used by PricingTool to display duration code as "Duration_1".
    * 
    * @since R9 phase 3
    * @return
    */
   public String getDurationCodeToDisplay(){
	   return "Duration_" + this.mDurationCode;
   }


    public static boolean isDayUnit ( int durationCode ){
		boolean rv = false;
    	if ((durationCode>DAY_BOUNDERIES[0])&&(durationCode<DAY_BOUNDERIES[1])) rv=true;
    	else {
    		switch (durationCode){
    			case WEEKLY: //7 days
    			case FORTNIGHTLY: //14 days
    			case ONEDAY:  rv = true ;break;//1 day
    			default: rv = false;break;
    		}
    	}
		return rv;
    }
	public boolean isDayUnit () {
		return isDayUnit(this.mDurationCode);
	}
	public static boolean isMinuteUnit ( int durationCode ){
    	return durationCode > MINUTE_BOUNDERIES;
    }

    public boolean isMinuteUnit(){
    	return isMinuteUnit(this.mDurationCode);
    }

	public static boolean isYearUnit ( int durationCode ) {
 		 return (durationCode == ANNUALY || durationCode == TWO_YEARLY);
    }
	public boolean isYearUnit ( ) {
		return isYearUnit(this.mDurationCode);
	}
	public static boolean isDaily ( int durationCode ) {
		 return durationCode == DAILY;
	}
	public boolean isDaily (  ) {
		return isDaily(this.mDurationCode);
	}

	public static boolean isMonthUnit(int durationCode) {
		boolean rv = false;
		switch (durationCode) {
		case MONTHLY:
		case HALFYEARLY:
		case QUARTERLY:
		case EIGHTEEN_MONTHLY:
			rv = true;
			break;
		default:
			rv = false;
		}
		return rv;
	}
	public boolean isMonthUnit () {
		return isMonthUnit(this.mDurationCode);
	}
	
	public static boolean isFixedExpiryDate(int durationCode){
		return FIXED_EXPIRY_BOUNDERIES[0]<=durationCode && FIXED_EXPIRY_BOUNDERIES[1]>=durationCode;
	}
	
    //REMEDY 6249
	/*
	public static String getUnit ( int durationCode ){
		 String rv = "UNKNOWN";
		 if (isMinuteUnit(durationCode)) return MINUTES;
		 else if (isDayUnit(durationCode)) return DAYS;
		  else if (isMonthUnit(durationCode)) return MONTHS;
		   else if (isYearUnit(durationCode)) return YEARS;
		    else if (isDaily(durationCode)) return DAILY_STR;
		 return rv;
	}
    */
	
	
	public static String getUnit ( int durationCode )
    {
        // Remedy 4487/6249 - Alan start
        // This will try to get the information from the ChargingTranslatorCore property file
        // If the Strings exists, then they will be used, else the Strings in this class
        // will be used.

        // Check to see if locale is set
        if (defaultLocale==null)
        {
            defaultLocale = Locale.UK;
        }
        logger.debug("Duration.defaultLocale is "+defaultLocale);

        //CR1607aL. START
		//wasResourceTranslatorImpl resourceTranslatorImpl = new ResourceTranslatorImpl(defaultLocale);
        ResourceTranslator resourceTranslatorImpl = null;
		try {
			resourceTranslatorImpl = EcomApiFactory.getResourceTranslator(defaultLocale);
		} catch (EcommerceException e) {
			logger.error("couldn't get resource translator", e);
		}
		//CR1607aL. END

        String _dailyStr = resourceTranslatorImpl.translate("daily");
        String _minute = resourceTranslatorImpl.translate("minute");
        String _days = resourceTranslatorImpl.translate("day");
        String _months = resourceTranslatorImpl.translate("month");
        String _years = resourceTranslatorImpl.translate("year");

        // Test for nulls from property file.
        if (_dailyStr==null)
        {
            _dailyStr = DAILY_STR;
        }
        if (_minute==null)
        {
            _minute = MINUTES;
        }
        if (_days==null)
        {
            _days = DAYS;
        }
        if (_months==null)
        {
            _months = MONTHS;
        }
        if (_years==null)
        {
            _years = YEARS;
        }
        logger.debug("Duration. daily :"+_dailyStr);
        logger.debug("Duration. minute :"+_minute);
        logger.debug("Duration. days :"+_days);
        logger.debug("Duration. months :"+_months);
        logger.debug("Duration. years :"+_years);

	 String rv = "UNKNOWN";
//	 if (isMinuteUnit(durationCode)) return MINUTES;
//	 else if (isDayUnit(durationCode)) return DAYS;
//	  else if (isMonthUnit(durationCode)) return MONTHS;
//	   else if (isYearUnit(durationCode)) return YEARS;
//	    else if (isDaily(durationCode)) return DAILY_STR;

         if (isMinuteUnit(durationCode)) return _minute;
         else if (isDayUnit(durationCode)) return _days;
         else if (isMonthUnit(durationCode)) return _months;
         else if (isYearUnit(durationCode)) return _years;
         else if (isDaily(durationCode)) return _dailyStr;
         logger.debug("Duration. rv :"+rv);
         // Remedy 4487 - Alan end.

         return rv;
}
	//END 6249
	
	public String getUnit (){
		return this.mUnit;
	}

	public int getValue (){
		return this.mValue;
	}

    /**
     * @deprecated since R9
     * @param val
     * @return
     */
    @Deprecated
	public static String getResourceName(int val)
    {
        return "Duration_" + val;
    }

    /**
     * @deprecated since R9
     * @return
     */
    @Deprecated
	public static String[] getResourceNames()
    {
        String[] rv = new String[sValues.length];
        for (int index=0; index<sValues.length; index++) {
            rv[index] = getResourceName(sValues[index]);
        }

        return rv;
    }
    /**
     * @deprecated since R9
     * @return
     */
    @Deprecated
	public static int[] getValues()
    {
        return sValues;
    }

 

    //[1] STKHREQ13113 Start
	public String getCustomNonRecurring() {
		return customNonRecurring;
	}

	public void setCustomNonRecurring(String customNonRecurringDuration) {
		this.customNonRecurring = customNonRecurringDuration;
	}

	public String getCustomRecurring() {
		return customRecurring;
	}

	public void setCustomRecurring(String customRecurringDuration) {
		this.customRecurring = customRecurringDuration;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setDurationCode(int durationCode) {
		mDurationCode = durationCode;
	}

	public String getNonRecurring() {
		return nonRecurring;
	}

	public void setNonRecurring(String nonRecurringDuration) {
		this.nonRecurring = nonRecurringDuration;
	}

	public String getRecurring() {
		return recurring;
	}

	public void setRecurring(String recurringDuration) {
		this.recurring = recurringDuration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	//[1] STKHREQ13113 End	
}
