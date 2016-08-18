package com.vizzavi.ecommerce.business.catalog.internal.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
    A day range has a start time and an end time
    This could be extended to include seconds
*/
public class DayRange extends RangeValue
{
   private    static final long serialVersionUID = 6890983173752582751L;
   //CR1231
   //private static final LWLogger logger = LWSupportFactoryImpl.getInstance().getLogger(DayRange.class);

    private final SimpleDateFormat mTimeFormat = new SimpleDateFormat("HHmm");



    public DayRange(String val)
    {
        super(val);
    }


    public DayRange()
    {
        super();
    }

    public String getStartTime()
    {
        return getStartValue(getValue());
    }

    public String getEndTime() throws ParseException
    {
        return getEndValue(getValue());
    }

    public void setStartTime(String value)
    {
        setValue(value + "-" + getEndValue(getValue()));
    }

    public void setEndTime(String value)
    {
        setValue(getStartValue(getValue()) + "-" + value);
    }

    public boolean match(Date val) throws ParseException
    {
        return match(val, mTimeFormat);
    }

    @Override
	public void validate() throws ParseException
    {
        validate(mTimeFormat);
    }
}
