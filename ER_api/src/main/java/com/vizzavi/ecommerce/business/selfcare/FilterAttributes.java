package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

/**
    The attributes that the user can set when getting back a transaction history

*/
public interface FilterAttributes extends Serializable	{
	//@hud STKHREQ12557
	public void setLanguageLocale(Locale lanLocale);
	public Locale getLanguageLocale();
	public String getLanguageString();


    public void setMaxEvents(int val);

    public int getMaxEvents();

    public void setAscendingOrder(boolean flag);

    public boolean isAscendingOrder();

    //added methods for descending order attribute
    public void setDescendingOrder(boolean flag);
    public boolean isDescendingOrder();

    /**
        The search start parameter of when the transaction/subscription was created
        The matches returned are the events created between the start date and end date filter values

        NOTE: This is not the start date of a subscription
    */
    public void setStartDate(Date val);

    public Date getStartDate();

    /**
        The search end parameter of when the transaction/subscription was created
        The matches returned are the events created between the start date and end date filter values

        NOTE: This is not the end date of a subscription
    */
    public void setEndDate(Date val);

    /* Remedy 3464, Bruno Meseguer */
    public String getRefSubscriptionID();

    /* Remedy 3464, Bruno Meseguer */
    public void setRefSubscriptionID(String lastCycleRefSubID);

    public Date getEndDate();

    public void setAttributes(int numberOfEvents, Date startDate, Date endDate, boolean ascending);

    public void setRecurringEventsOnly(boolean flag);

    public void setNonRecurringEventsOnly(boolean flag);

    /* Remedy 5327, Bruno Meseguer, make Batch application scalable */
    public void setRowIdFetchingMultipleOf(Integer offset, Integer multipleOf);
    public Integer getRowIdOffset();
    public Integer getRowIdMultipleOf();

    //CR1564 -Utctimezone for diff region in country
	public boolean isUsingLocalDates();
	public void setUsingLocalDates(boolean isUsingLocalDates);
}
