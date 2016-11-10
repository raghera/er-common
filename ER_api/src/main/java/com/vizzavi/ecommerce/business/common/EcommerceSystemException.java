package com.vizzavi.ecommerce.business.common;


/**
 *
 */
public class EcommerceSystemException extends EcommerceException
{
   private    static final long serialVersionUID = -19895940938448975L;
    public EcommerceSystemException()
    {
        super();
    }

    public EcommerceSystemException(String msg)
    {
        super(msg);
    }

    public EcommerceSystemException(String msg, Throwable thr)
    {
        super(msg, thr);
    }

    public EcommerceSystemException(Throwable thr)
    {
        this(thr.getMessage(), thr);
    }
}
