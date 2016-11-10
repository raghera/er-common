package com.vodafone.global.er.batch;

import com.vizzavi.ecommerce.business.common.EcommerceException;


/**
 *
 */
public class BatchException extends EcommerceException
{
   private    static final long serialVersionUID = 1851598767513655841L;
    public BatchException(String msg)
    {
        super(msg);
    }

    /**
     * {@inheritDoc} 
     * @param msg
     * @param thr
     */
    public BatchException(String msg, Throwable thr)
    {
        super(msg, thr);
    }

    public BatchException(Throwable thr)
    {
        this(thr.getMessage(), thr);
    }

}
