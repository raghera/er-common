package com.vizzavi.ecommerce.business.catalog.internal.model;


import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
    This represents the date time model that is used by ECOM_SERVICES_api

    A date time model has
        Start date
        End date
        Day Range
        Month Range
        Time Range

        A range can have a start and end range (seperated by hypen
        Or a list of allowed values (seperated by commas)


start date
end date
month of year   1 to 12
day of month
day of week
time of day start
time of day end




*/
public class DateTimeTier extends Tier implements java.io.Serializable
{
   private    static final long serialVersionUID = -432150894234539211L;
   //CR1231
   //private static final LWLogger logger = LWSupportFactoryImpl.getInstance().getLogger(DateTimeTier.class);
   
	private Long mKey;
	private String mCreatedBy;
	private String mModifiedBy;
	private Date mModifiedDate;
	private char mActiveStatus;

    private RangeValue mDaysOfWeek = new RangeValue("*");
    private RangeValue mDaysOfMonth = new RangeValue("*");
    private RangeValue mMonthsOfYear = new RangeValue("*");
    private DayRange mHoursOfDay = new DayRange("*");

    public DateTimeTier(String id)
    {
        super(id);
    }
	public Long getKey() {
		return mKey;
	}

	public void setKey(Long key){
		mKey = key;
	}

	public String getCreatedBy() {
		return this.mCreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		this.mCreatedBy= createdBy;
	}

	public String getModifiedBy() {
		return this.mModifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.mModifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.mModifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.mModifiedDate = modifiedDate;
	}

	public char getActiveStatus() {
		return this.mActiveStatus;
	}

	public void setActiveStatus(char activeStatus) {
		this.mActiveStatus = activeStatus;
	}
    public RangeValue getDaysOfWeek()
    {
        return mDaysOfWeek;
    }

    public RangeValue getDaysOfMonth()
    {
        return mDaysOfMonth;
    }

    public RangeValue getMonthsOfYear()
    {
        return mMonthsOfYear;
    }

    public DayRange getHoursOfDay()
    {
        return mHoursOfDay;
    }

    public boolean match(Calendar cal) throws ParseException
    {
        // check the following
        //logger.debug("Trying to matching the date " + cal.getTime() + " for tier " + getId() );
        boolean rv = false;

        // we have to do this as java uses one less for month and day of week
        int month = cal.get(Calendar.MONTH) + 1;
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);


        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        if (getDaysOfWeek().match(dayOfWeek)==false) {
            //logger.debug("No match for day of week for " + getDaysOfWeek() + ":" + dayOfWeek);
        } else if (getDaysOfMonth().match(dayOfMonth)==false) {
            //logger.debug("No match for day of month for " + getDaysOfMonth() + ":" + dayOfMonth);
        } else if (getMonthsOfYear().match(month)==false) {
            //logger.debug("No match for month for " + getMonthsOfYear() + ":" + month);
        } else if (getHoursOfDay().match(cal.getTime())==false) {
            //logger.debug("No match for hour of day for " + getHoursOfDay() + ":" + hour + ":" + minute);
        } else {
            //logger.debug("Successful match for " + cal.getTime());
            rv = true;
        }
        return rv;
    }
    
    //CR1759 - start
    public void setMonthsOfYear(RangeValue range)
    {
      this.mMonthsOfYear = range;
    }
    
    public void setDaysOfMonth(RangeValue range)
    {
      this.mDaysOfMonth = range;
    }
    
    public void setDaysOfWeek(RangeValue range)
    {
      this.mDaysOfWeek = range;
    }
    
    public void setHoursOfDay(DayRange range)
    {
      this.mHoursOfDay = range;
    }
    //CR1759 - end
}
