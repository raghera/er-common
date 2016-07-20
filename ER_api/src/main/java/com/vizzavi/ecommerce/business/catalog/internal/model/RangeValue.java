package com.vizzavi.ecommerce.business.catalog.internal.model;

import java.io.Serializable;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;


/**
    This allows values to have a range of values or a selection of values

    Lists have values seperated by comma (,)
    Ranges have a hyphen to seperate the start and end times

    If anything has a special character (ie / or *) it must be escaped.
*/
public class RangeValue implements Serializable
{
   private    static final long serialVersionUID = -1629418577512526784L;
   //CR1231
   //private static final LWLogger logger = LWSupportFactoryImpl.getInstance().getLogger(RangeValue.class);
   private static Logger logger = Logger.getLogger(RangeValue.class);

    protected String mValue = "";

    public RangeValue()
    {
        setValue("");
    }

    public RangeValue(String val)
    {
        setValue(val);
    }

    public void setValue(String val)
    {
        mValue = val;
    }

    public String getValue()
    {
        return mValue;
    }

    public boolean isNotSet(String val)
    {
        boolean rv = false;
        if (val==null || val.equals("")) {
            rv = true;
        }

        return rv;
    }

    public boolean isNotSet()
    {
        return isNotSet(mValue);
    }

    public static boolean isMatchAll(String val)
    {
        boolean rv = false;
        if (val!=null && val.equals("*")) {
            rv = true;
        }

        return rv;
    }

    public static boolean isRange(String val)
    {
        boolean rv = false;
        if (val!=null && val.indexOf('-', 1)>0) {
            rv = true;
        }

        return rv;
    }

    public String[] getValues()
    {
        StringTokenizer tokens = new StringTokenizer(mValue, ",");
        List<String> values = new ArrayList<String>();
        while (tokens.hasMoreTokens())
        {
            String val = tokens.nextToken();
            values.add(val);
        }

        return values.toArray(new String[values.size()]);
    }

    public static String getStartValue(String range)
    {
        String rv = "";
        if (isMatchAll(range)) {
            rv = range;
        } else if (isRange(range)) {
            rv = range;
            int index = range.indexOf('-');
            if (index>0) {
                rv = range.substring(0, index);
            }
        }
        return rv;
    }

    public static String getEndValue(String range)
    {
        String rv = "";
        if (isMatchAll(range)) {
            rv = range;
        } else if (isRange(range)) {
            rv = range;
            int index = range.indexOf('-');
            if (index>0) {
                rv = range.substring(index+1);
            }
        }
        return rv;
    }

    @Override
	public String toString()
    {
        return mValue;
    }

    /**
        Assume compatable is a number
    */
    public boolean match(int val) throws ParseException
    {
        NumberFormat form = NumberFormat.getNumberInstance();
        form.setParseIntegerOnly(true);
        return match(new Long(val), form);
    }

    /**
        Assume compatable is a number
    */
    public boolean match(Comparable<?> val) throws ParseException
    {
        return match(val, NumberFormat.getNumberInstance());
    }

    public boolean match(Comparable<?> val, Format decoder) throws ParseException
    {
        boolean rv = false;


        if (isNotSet()) {
            rv = false;
            //logger.debug("The range value is not set " + toString() + " for value " + val);
        } else if (isMatchAll(getValue())) {
            rv = true;
            //logger.debug("The range value is match all " + toString() + " for value " + val);
        } else {
            String[] values = getValues();
            for (int index=0; values!=null && index<values.length; index++) {
                String value = values[index];
                if (isNotSet(value)) {
                    // do nothing
                    //logger.debug("One of the list values is not set " + toString() + " for value " + val);
                } else if (isMatchAll(value)) {
                    rv = true;
                    //logger.debug("One of the list values is a wildcard " + toString() + " for value " + val);
                    break;
                } else if (isRange(value)) {
                    try {
                        String endValueStr = getEndValue(value);
                        String startValueStr = getStartValue(value);
                        Comparable<?> startValue = null;
                        Comparable<?> endValue = null;
                        if (isMatchAll(startValueStr)==false) {
                            startValue = (Comparable<?>)decoder.parseObject(startValueStr);
                        }
                        if (isMatchAll(endValueStr)==false) {
                            endValue = (Comparable<?>)decoder.parseObject(endValueStr);
                        }

                        Comparable<Comparable<?>> newValue = (Comparable<Comparable<?>>)decoder.parseObject(decoder.format(val));

                        int after = 1;
                        if (startValue!=null) {
                            after = newValue.compareTo(startValue);
                        }
                        int before = -1;
                        if (endValue!=null) {
                            before = newValue.compareTo(endValue);
                        }


                        if (after>=0 && before<=0) {
                            rv = true;
                            //logger.debug("MATCH The range value is  " + startValue + " to " + endValue  + " for range " +  toString() + " for value " + val + " and status " +rv);
                            break;
                        }
                        //logger.debug("The range value is  " + startValue + " to " + endValue  + " for range " +  toString() + " for value " + val + " and status " +rv);
                    } catch (ParseException e) {
                        //logger.error("Failed to parse value " + getStartValue(value) + " or " + getEndValue(value));
			            
			            logger.error(e.getMessage());
			            

                        throw e;
                    }
                } else {
                    try {
                        Comparable<?> valToMatch = (Comparable<?>)decoder.parseObject(value);
                        Comparable<Comparable<?>> newValue = (Comparable<Comparable<?>>)decoder.parseObject(decoder.format(val));
                        int equal = newValue.compareTo(valToMatch);
                        if (equal==0) {
                            rv = true;
                            //logger.debug("MATCH found for list value " + toString() + " for input " + val + " and match " + valToMatch);
                            break;
                        }
                    } catch (ParseException e) {
                        //logger.error("Failed to parse value " + value);
			            
			            logger.error(e.getMessage());
			            
                        throw e;
                    }
                }
            }
            if (rv==false) {
                //logger.debug("No match found for list value " +  toString() + " for value ");
            }
        }

        return rv;
    }

    /**
        This validate assumes that all values are integer
        It uses the IntegerValidator
    */
    public void validate() throws ParseException
    {
        validate(NumberFormat.getNumberInstance());
    }

    /**
        Throws
    */
    public void validate(Format decoder) throws ParseException
    {
        // This will create an exception if invalid data
        if (isNotSet()==false || isMatchAll(getValue())==false) {
            String[] values = getValues();
            for (int index=0; values!=null && index<values.length; index++) {
                String value = values[index];
                if (isRange(value)) {
                    Comparable<Comparable<?>> endValue = (Comparable<Comparable<?>>)decoder.parseObject(getEndValue(value));
                    Comparable<?> startValue = (Comparable<?>)decoder.parseObject(getStartValue(value));
                    if (endValue.compareTo(startValue)<0) {
                        throw new ParseException("The end value is before the start value", 0);
                    }
                } else {
                    Comparable<?> valToMatch = (Comparable<?>)decoder.parseObject(value);
                }
            }
        }
    }
}
