package com.vizzavi.ecommerce.business.common;

import java.rmi.RemoteException;

import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vizzavi.ecommerce.business.selfcare.Transaction;
import com.vodafone.global.er.common.ErCoreErrorId;



/**
 *
 */
public class EcommerceException extends RemoteException
{
   private    static final long serialVersionUID = -7750693806712490641L;
    protected int mErrorId;
    protected String mSystemId;
    protected String mErrorDescription;

    //MQC 6948 Start
    private Transaction transaction;
    private Subscription subscription;
    //MQC 6948 End
    
    public EcommerceException()
    {
        super();
    }
    
    public EcommerceException(ErCoreErrorId error)	{
    	this(error.getName());
    	mErrorId=error.getCode();
    	mErrorDescription=error.getName();
    }

    public EcommerceException(ErCoreErrorId error, Throwable thr)	{
    	this(error.getName(), thr);
    	mErrorId=error.getCode();
    	mErrorDescription=error.getName();
    }
    
    public EcommerceException(String msg)
    {
        super(msg);
    }

    /**
     * Constructs a EcommerceException with the specified detail message and cause. 
     * This constructor sets the detail field to the specified Throwable. 
     * @param msg
     * @param thr the cause of the exception
     */
    public EcommerceException(String msg, Throwable thr)
    {
        super(msg, thr);
        if (thr instanceof EcommerceException)	{
        	EcommerceException ecomEx = (EcommerceException) thr;
        	this.detail=ecomEx.detail;
        	this.mErrorId=ecomEx.mErrorId;
        	this.mErrorDescription=ecomEx.mErrorDescription;
        	this.mSystemId=ecomEx.mSystemId;
        	this.transaction=ecomEx.transaction;
        	this.subscription=ecomEx.subscription;
        }
        	
    }

    public EcommerceException(Throwable thr)
    {
        super(thr.getMessage(), thr);
        if (thr instanceof EcommerceException)	{
        	EcommerceException ecomEx = (EcommerceException) thr;
        	this.detail=ecomEx.detail;
        	this.mErrorId=ecomEx.mErrorId;
        	this.mErrorDescription=ecomEx.mErrorDescription;
        	this.mSystemId=ecomEx.mSystemId;
        	this.transaction=ecomEx.transaction;
        	this.subscription=ecomEx.subscription;
        }
    }
    
    /**MQC 7644<br/>
     * let's not nest EcommerceExceptions indefinitely.  Call this constructor and it will basically clone the exception passed in without nesting it
     * @param ecomEx another EcommerceException
     */
    public  EcommerceException(String msg, EcommerceException ecomEx)
    {
    	super(msg);
    	this.detail=ecomEx.detail;
    	this.mErrorId=ecomEx.mErrorId;
    	this.mErrorDescription=ecomEx.mErrorDescription;
    	this.mSystemId=ecomEx.mSystemId;
    	this.transaction=ecomEx.transaction;
    	this.subscription=ecomEx.subscription;
    	
    }

    /**MQC 7644<br/>
     * let's not nest EcommerceExceptions indefinitely.  Call this constructor and it will basically clone the exception passed in without nesting it
     * @param ecomEx another EcommerceException
     */
    public  EcommerceException(EcommerceException ecomEx)
    {
    	this.detail=ecomEx.detail;
    	this.mErrorId=ecomEx.mErrorId;
    	this.mErrorDescription=ecomEx.mErrorDescription;
    	this.mSystemId=ecomEx.mSystemId;
    	this.transaction=ecomEx.transaction;
    	this.subscription=ecomEx.subscription;
    	
    }
    
    @Override
	public void printStackTrace()
    {
        if (detail!=null) {
            detail.printStackTrace(System.err);
        }
        this.printStackTrace(System.err);
    }

    /**
     * Sets a nested exception object.<BR>
     * @param th java.lang.Throwable. The previously generated exception
     */
    public void setThrowable(Throwable th) {
        detail = th;
    }

    /**
     * Gets a nested exception object.<BR>
     * @return java.lang.Throwable. The nested exception
     */
    public Throwable getThrowable() {
        return detail;
    }

    public void setSystemId(String id)
    {
        mSystemId = id;
    }

    public String getSystemId()
    {
        return mSystemId;
    }

    public void setErrorId(int val)
    {
        mErrorId = val;
    }

    public int getErrorId()
    {
        return mErrorId;
    }

    public void setErrorDescription(String val)
    {
        mErrorDescription = val;
    }

    public String getErrorDescription()
    {
        return mErrorDescription;
    }

    /*
     * MQC 6948 Start
     * Need to be able to keep the subcription and transaction
     * details when required.
     */
    public final void setTransaction(final Transaction transaction) {
    	this.transaction = transaction;
    }
    public final Transaction getTransaction() {
    	return transaction;
    }
    
    public final void setSubscription(final Subscription subscription) {
    	this.subscription = subscription;
    }
    public final Subscription getSubscription() {
    	return subscription;
    }
    //MQC 6948 End
    
}
