package com.vizzavi.ecommerce.business.charging;

import java.util.Date;

import com.vizzavi.ecommerce.business.common.EcommerceException;
/**
* The exception to represent failiures in usage authorizations
* against the charging subsystem.
*/

public class UsageAuthorizationException extends EcommerceException {
        private    static final long serialVersionUID = -7417435738177372941L;
	private String mPackageName = "";
	private Date mStartDate = null;
        // protected int mError = -1;

	public Date getStartDate(){
		return mStartDate;
	}
	public String getPackageName(){
		return mPackageName;
	}
	
	public UsageAuthorizationException() { super(); }

    public UsageAuthorizationException(EcommerceException ecom) { 
		//super(ecom.getErrorDescription());
    	super(ecom);
		//this.mError = ecom.getErrorId();
    }
	public UsageAuthorizationException(Throwable e) { super(e); }

    public UsageAuthorizationException(String s) { super(s); }
    public UsageAuthorizationException(String s, Throwable e) { super(s, e); }
    public UsageAuthorizationException(String s, Throwable e,Date startDate,String packageName) { 
    	  super(s, e);
    	  mStartDate = startDate;
    	  mPackageName = packageName;
    }

    public int getErrorCodeId(){
		//return this.mError;
    	return getErrorId();
    }
}
