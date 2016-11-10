package com.vodafone.global.er.rating;

import com.vizzavi.ecommerce.business.common.EcommerceException;


/**
 *
 */
public class RatingException extends EcommerceException
{
   private    static final long serialVersionUID = -5107780574538992520L;
    public RatingException(String msg)
    {
        super(msg);
    }

    public RatingException(String msg, Throwable thr)
    {
        super(msg, thr);
    }

    public RatingException(Throwable thr)
    {
        this(thr.getMessage(), thr);
    }

}
