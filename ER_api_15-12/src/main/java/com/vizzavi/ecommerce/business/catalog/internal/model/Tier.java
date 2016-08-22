package com.vizzavi.ecommerce.business.catalog.internal.model;

import java.io.Serializable;

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

*/
public class Tier implements Serializable
{
   private    static final long serialVersionUID = 8022393188692756158L;
    private String mId;
    private String mName;
    private int mOrder;
    protected boolean mPromotion = false;

    public Tier(String id)
    {
        mId = id;
    }

    public String getId()
    {
        return mId;
    }

    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    public int getOrder()
    {
        return mOrder;
    }

    public void setOrder(int val)
    {
        mOrder = val;
    }

    public boolean isPromotion()
    {
        return mPromotion;
    }

    public void setPromotion(boolean val)
    {
        mPromotion = val;
    }

}
