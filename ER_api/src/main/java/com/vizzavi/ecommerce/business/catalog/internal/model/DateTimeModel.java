package com.vizzavi.ecommerce.business.catalog.internal.model;

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

*/
public class DateTimeModel extends PricingModel implements java.io.Serializable
{
   private    static final long serialVersionUID = 2693512230571257458L;
    public DateTimeModel(String id)
    {
        super(id);
    }

    public DateTimeModel(String id, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
    {
        super(id, createdBy, modifiedBy, modifiedDate, activeStatus);
    }
}
