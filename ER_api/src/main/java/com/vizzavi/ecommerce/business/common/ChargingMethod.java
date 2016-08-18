package com.vizzavi.ecommerce.business.common;

/**
	Event (1)<br/>
	Non-recurring (2)<br/>
	Recurring (3)<br/>
	There is also a legacy one for events which is 999
*/
public class ChargingMethod {

    /**
    * Event package
    */
    public final static int EVENT = 1;

    /**
    * Non recurring calendar package
    */
    public final static int NON_RECURRING = 2;

    /**
    * recurring calendar package
    */
    public final static int RECURRING = 3;

    /**
        The package is a recurring calendar subscription (ie has an start and end date). The length
        of the subscription can be checked by looking at the duration.  Recurring means that before
        the package expires, the recurring batch will take payment again and extend the expiry date.
        @return true if a recurring subscription, false otherwise
    */
    public static boolean isRecurring(int code)
    {
        boolean rv = false;
        if (code == RECURRING) {
            rv = true;
        }
        return rv;
    }

    /**
        The package is a non recurring calendar package (ie has an start and end date). The length
        of the package can be checked by looking at the duration.  Non recurring means that the package
        is not purchased again.
        @return true if a non-recurring calendar package, false otherwise
    */
    public static boolean isNonRecurring(int code)
    {
        boolean rv = false;
        if (code == NON_RECURRING) {
            rv = true;
        }
        return rv;
    }

    /**
        Calendar means that it is a time-based package. (It has a start and end date)
        @return return true if a calendar package
    */
    public static boolean isCalendar(int code)
    {
        boolean rv = false;
        if (isRecurring(code) || code == NON_RECURRING) {
            rv = true;
        }
        return rv;

    }

    /**
        The package is not time based.
        @return true if not time based.
    */
    public static boolean isEvent(int code)
    {
    	return code == EVENT;// ||code==999;	//maybe include this
    }

    /**
        @return a unique identifier to use in ResourceTranslator.
    */
    public static String getResourceName(int val)
    {
        return "ChargingMethod_" + val;
    }

    /**
        Return all valid resource names for charging method from getValues()
    */
    public static String[] getResourceNames()
    {
        int[] values = getValues();
            String[] rv = new String[values.length];
        for (int index=0; index<values.length; index++) {
            rv[index] = getResourceName(values[index]);
        }

        return rv;
    }

    // Don't show EVENT as it is not an option. It is the default value for event packages
    /**
        Return non recurring and recurring values.
    */
    public static int[] getValues()
    {
        return new int[] { NON_RECURRING, RECURRING };
    }
    
    /**
     * eg "Event", "Recurring", "Non-recurring"
     * @param chargingMethod
     * @return
     */
    public static String getName(int chargingMethod)	{
    	switch(chargingMethod)	{
    	case EVENT: return "Event";
    	case RECURRING: return "Recurring";
    	case NON_RECURRING: return "Non-recurring";
    	case 999 : return "Default(event)";
    	}
    	return null;

    }

}
