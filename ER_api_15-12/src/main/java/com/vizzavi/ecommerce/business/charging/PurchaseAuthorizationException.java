package com.vizzavi.ecommerce.business.charging;

import com.vizzavi.ecommerce.business.common.EcommerceException;

/**
* The exception to represent failures in purchase authorizations
* against the charging subsystem.
*/

public class PurchaseAuthorizationException extends EcommerceException {
	
   private    static final long serialVersionUID = -2375291492847046449L;
	protected int mError = -1;
    public PurchaseAuthorizationException() { super(); }
    public PurchaseAuthorizationException(String s) { super(s); }
	public PurchaseAuthorizationException(EcommerceException ecom){
		super(ecom);
		this.mError = ecom.getErrorId();	
	}
		
    /**
     * Constructor taking a String and a Throwable object.
     * @param message - The String to set for the detailMessage.
     * @param thr - The Throwable object to wrap in this class.
     * @return String of the stack trace.
     */
    public PurchaseAuthorizationException(String message, Throwable thr)
    {
        super(message, thr);
    }

    public PurchaseAuthorizationException( Throwable thr)
    {
        super(thr);
    }
	public int getErrorCodeId(){
		return this.mError; 
	}
}
