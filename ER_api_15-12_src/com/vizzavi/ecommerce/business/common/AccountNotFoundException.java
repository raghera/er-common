package com.vizzavi.ecommerce.business.common;

/**
 * The account does not exist in ER2
 */
public class AccountNotFoundException extends EcommerceException
{
   private    static final long serialVersionUID = 6017519986271670793L;
    public AccountNotFoundException()
    {
        super();
    }

    public AccountNotFoundException(String msg)
    {
        super(msg);
    }

    public AccountNotFoundException(String msg, Throwable thr)
    {
        super(msg, thr);
    }

    public AccountNotFoundException(Throwable thr)
    {
        this(thr.getMessage(), thr);
    }
}
