package com.vodafone.global.er.rating;

import com.vizzavi.ecommerce.business.common.EcommerceException;


/**
 *
 */
public class TaxException extends EcommerceException
{
   private    static final long serialVersionUID = -1911426667652812349L;
    public TaxException(String msg)
    {
        super(msg);
    }

    public TaxException(String msg, Throwable thr)
    {
        super(msg, thr);
    }

    public TaxException(Throwable thr)
    {
        this(thr.getMessage(), thr);
    }

}
