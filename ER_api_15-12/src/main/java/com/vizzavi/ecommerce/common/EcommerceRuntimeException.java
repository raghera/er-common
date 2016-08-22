package com.vizzavi.ecommerce.common;

import com.vodafone.global.er.common.ErCoreErrorId;



/**
 * used by the catalogApi so we can throw custom exceptions without having to declare them (which means modifying the API)
 */
public class EcommerceRuntimeException extends RuntimeException
{
   private    static final long serialVersionUID = -9040822717729055958L;

   protected int mErrorId;
   
    public EcommerceRuntimeException()
    {
        super();
    }

    public EcommerceRuntimeException(String msg)
    {
        super(msg);
    }

    public EcommerceRuntimeException(String msg, Throwable thr)
    {
        super(msg, thr);
    }

    public EcommerceRuntimeException(Throwable thr)
    {
        super(thr);
    }

    public EcommerceRuntimeException(ErCoreErrorId error)	{
    	this(error.getName());
    	mErrorId=error.getCode();
    }

    public EcommerceRuntimeException(ErCoreErrorId error, Throwable thr)	{
    	this(error.getName(), thr);
    	mErrorId=error.getCode();
    }
   
    public void setErrorId(int val)
    {
        mErrorId = val;
    }

    public int getErrorId()
    {
        return mErrorId;
    }

 
}
