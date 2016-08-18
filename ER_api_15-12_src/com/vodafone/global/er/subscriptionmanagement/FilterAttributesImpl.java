package com.vodafone.global.er.subscriptionmanagement;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.selfcare.FilterAttributes;
import com.vodafone.config.ConfigProvider;

/**
    The attributes that the user can set when getting back a transaction history
*/
public class FilterAttributesImpl implements FilterAttributes
{
    private    static final long serialVersionUID = 5710783254275574514L;
	public final static int MILLSECS_PER_DAY = 1000 * 60 * 60 * 24;

	//@hud STKHREQ12557
    private Locale mLanguageLocale = null;
	@Override
	public void setLanguageLocale(Locale lanLocale)	{
		mLanguageLocale = lanLocale;
	}
	@Override
	public Locale getLanguageLocale() {
		return mLanguageLocale;
	}
	@Override
	public String getLanguageString() {
		if (mLanguageLocale == null) {
			return null;
		}
		else {
			return mLanguageLocale.getLanguage();
		}
	}

//	private static final String MAX_EVENTS="MAX_EVENTS";
//	static int i_prop_server_max_events;
//	static{
//		i_prop_server_max_events = ConfigProvider.getPropertyAsInteger(MAX_EVENTS, -1);
//	}

    protected Date mStartDate = null;
    protected Date mEndDate = null;
//    protected int mMaxEvents = (i_prop_server_max_events<0?100:i_prop_server_max_events);
    protected int mMaxEvents = ConfigProvider.getPropertyAsInteger("MAX_EVENTS", 100);
    /* Remedy 3464, Bruno Meseguer */
    protected String mRefSubscriptionID = null;

    protected boolean mOrderEventsAscendingFlag = false;
	protected boolean mOrderEventsDescendingFlag = false;


	//@hud STKHREQ13076 SP ROAMING
	protected String mOrderByObject = null;

    /* These are not used yet */
    protected boolean mRecurringEventsFlag = false;
    protected boolean mNonRecurringEventsFlag = false;

    /* Remedy 5327, Bruno Meseguer, make Batch application scalable */
    //private static final LWLogger logger = LWSupportFactoryImpl.getInstance().getLogger(FilterAttributesImpl.class);
    private static final Logger logger = LoggerFactory.getLogger(FilterAttributesImpl.class);

    /* Remedy 5327, Bruno Meseguer, make Batch application scalable */
    protected Integer rowIdOffset 		= null;
    protected Integer rowIdMultipleOf 	= null;

    //CR1564 -Utctimezone for diff region in country
    protected boolean isUsingLocalDates = false;


    @Override
	public Date getStartDate()
    {
        return mStartDate;
    }

    @Override
	public Date getEndDate()
    {
        return mEndDate;
    }

    /* Remedy 3464, Bruno Meseguer */
    @Override
	public String getRefSubscriptionID()
    {
        return mRefSubscriptionID;
    }

    /* Remedy 3464, Bruno Meseguer */
    @Override
	public void setRefSubscriptionID(String lastCycleRefSubID)
    {
    	if(lastCycleRefSubID == null)
    		mRefSubscriptionID = null;
    	else if(lastCycleRefSubID.equals(""))
    		mRefSubscriptionID = null;
    	else
    		mRefSubscriptionID = lastCycleRefSubID;
    }

    @Override
	public int getMaxEvents()
    {
        return mMaxEvents;
    }

    @Override
	public boolean isAscendingOrder()
    {
        return mOrderEventsAscendingFlag;
    }

	@Override
	public boolean isDescendingOrder()
	{
		return mOrderEventsDescendingFlag;
	}

    @Override
	public void setMaxEvents(int val)
    {
        mMaxEvents = val;
    }

	@Override
	public void setAscendingOrder(boolean flag)
    {
        mOrderEventsAscendingFlag = flag;
        //Remedy 1371 - set the descending order flag as well
        mOrderEventsDescendingFlag = !flag;
    }

	@Override
	public void setDescendingOrder(boolean flag)
	{
		mOrderEventsDescendingFlag = flag;
		//Remedy 1371 - set the ascending order flag as well
		mOrderEventsAscendingFlag = !flag;
	}

    @Override
	public void setStartDate(Date val)
    {

        mStartDate = val;
    }

    @Override
	public void setEndDate(Date val)
    {
        mEndDate = val;
    }

    @Override
	public void setAttributes(int numberOfEvents, Date startDate, Date endDate, boolean ascending)
    {
        setStartDate(startDate);
        setEndDate(endDate);
        setMaxEvents(numberOfEvents);
        setAscendingOrder(ascending);
    }

    @Override
	public void setRecurringEventsOnly(boolean flag)
    {
        mRecurringEventsFlag = flag;
    }


    public boolean isRecurringEventsOnly()
    {
        return mRecurringEventsFlag;
    }


    @Override
	public void setNonRecurringEventsOnly(boolean flag)
    {
        mNonRecurringEventsFlag = flag;
    }


    public boolean isNonRecurringEventsOnly()
    {
        return mNonRecurringEventsFlag;
    }

	//@hud STKHREQ13076 SP ROAMING
	public String getOrderByObject() {
		return mOrderByObject;
	}
	public void setOrderByObject(String orderByObject) {
		mOrderByObject = orderByObject;
	}


    /* Remedy 5327, Bruno Meseguer, make Batch application scalable */
    @Override
	public void setRowIdFetchingMultipleOf(Integer offset, Integer multipleOf)
    {
    	//offset requires to be smaller than multiple of and 0 or higher, as it represents the module result obtained
    	//when dividing with multipleOf
    	if(offset!=null && multipleOf!=null
    		&& offset.intValue()>-1
    		&& offset.intValue()<multipleOf.intValue())
    	{
    		rowIdOffset 	= offset;
    		rowIdMultipleOf = multipleOf;
    	}
    	else
    	{
    		logger.warn("FilterAttributesImpl.setRowIdFetchingMultipleOf: Configuration failure: offset=" + offset + " ,multipleOf=" + multipleOf);
    	}

    }

    /* Remedy 5327, Bruno Meseguer, make Batch application scalable */
    @Override
	public Integer getRowIdOffset()
    {
    	return rowIdOffset;
    }

    /* Remedy 5327, Bruno Meseguer, make Batch application scalable */
    @Override
	public Integer getRowIdMultipleOf()
    {
    	return rowIdMultipleOf;
    }

    //CR1564 -start
	@Override
	public boolean isUsingLocalDates() {
		return isUsingLocalDates;
	}

	@Override
	public void setUsingLocalDates(boolean isUsingLocalDates) {
		this.isUsingLocalDates = isUsingLocalDates;
	}
    //CR1564 -end
	
	@Override
	public String toString()	{
		
		return "filter";
	}
	
	/**
	 * returns the difference between 2 dates in number of days, taking
     * into consideration leap years, year boundaries etc.  if either is null, returns -99
	 * @param endDate
	 * @param startDate
	 * @return
	 */
	public static int numDaysBetweenDates(Date endDate, Date startDate){
		if (endDate == null || startDate==null)	{
			logger.warn("problem calculating offset: date1="+endDate+", date2="+startDate);
			return -99;
		}
		//TODO would be nice to move this to BatchTime, but then the code doesn't compile since the ER_batch module is built after ER_subscriptions
		Calendar calDate1 = Calendar.getInstance();
		calDate1.setTime(endDate);
		
		Calendar calDate2 = Calendar.getInstance();
		calDate2.setTime(startDate);
		//if (calDate1 is in the past, subtract 12 hours from it, then set calDate2 to midday
		
//		if (calDate1.before(calDate2))	{
//			calDate1.add(Calendar.HOUR_OF_DAY, -12);
//		}
		
		calDate2.set(Calendar.HOUR_OF_DAY, 12);
		calDate2.set(Calendar.MINUTE, 0);
		calDate2.set(Calendar.SECOND, 0);
		
		calDate1.set(Calendar.HOUR_OF_DAY, 12);
		calDate1.set(Calendar.MINUTE, 0);
		calDate1.set(Calendar.SECOND, 0);
		//long diff = date1.getTime() - date2.getTime();
		
		return Math.round((((float)calDate1.getTimeInMillis()) - calDate2.getTimeInMillis())/MILLSECS_PER_DAY);
	}

}
