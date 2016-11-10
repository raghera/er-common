package com.vodafone.global.er.payment;

import java.io.Serializable;

import com.vizzavi.ecommerce.business.common.PeriodValue;
import com.vizzavi.ecommerce.business.common.ResponseStatus;


/**
 * this class wraps ResponseStatus, encapsulating ACCEPTED, REJECTED, DENIED or ERROR (and also including INVALID_BAN)<br/>
 *  use {@link ResponseStatus} instead.<br/>
 *  TODO this class should be removed once the ecom clients have been migrated to decoupling
 */
public class PaymentAuthStatus implements Serializable {
	
	private static final long serialVersionUID = -1529892833067892488L;
	
	//TODO this class is superceded by the enum ResponseStatus and so should be deprecated
	//however it's still used in BaseAuthorization which is serialized as part of ecom calls,
	// so we can't remove it from that class (but maybe we could set the field to transient)
	
	// predefined status
	/**The error is non-recoverable, and ER may close all subscriptions relating to this user.*/
    public final static PaymentAuthStatus		DENIED	= new PaymentAuthStatus(ResponseStatus.DENIED);
	/**everything went ok*/
    public final static PaymentAuthStatus		ACCEPTED	= new PaymentAuthStatus(ResponseStatus.ACCEPTED);
	/**ER may retry the operation in the future and may get a different result*/
    public final static PaymentAuthStatus		REJECTED	= new PaymentAuthStatus(ResponseStatus.REJECTED);
    /**DO NOT USE - added for legacy code support only.  TODO remove*/ @Deprecated
    public final static PaymentAuthStatus		INVALID_BAN	= new PaymentAuthStatus(ResponseStatus.INVALID_BAN);
    //MQC 6676 - add payment auth status of ERROR
	/**something needs attention on either opco or global side, or the request could not be understood*/
    public final static PaymentAuthStatus		ERROR	= new PaymentAuthStatus(ResponseStatus.ERROR);

	
	// definition of the status
	private String			code = null;
	private int				id = -1;			// may not be used
	
	// for Retry frequence
	private final PeriodValue		retryPeriod = null;

	//private ResponseStatus	responseStatus;
	
	PaymentAuthStatus(String _code,  int _id) 
	{
		code = _code;
		id = _id;
	}
	
//	PaymentAuthStatus(String _code,  int _id, PeriodValue _retryPeriod)
//	{
//		this(_code, _id);
//		retryPeriod = _retryPeriod;
//	}
	
	public PaymentAuthStatus(ResponseStatus status) {
		code=status.getName();
		id=status.getId();
		//this.responseStatus = status;
	}
	
	public String getCode() {
		return code;
	}

	public int getId() {
		return id;
	}
	
	public PeriodValue getRetryPeriod() {
		return retryPeriod;
	}
	
	
	@Override
	public String toString() 
	{
		StringBuffer sb = new StringBuffer("{code = ");
		sb.append(code).append(", id = ").append(id).append("}");
		
		return sb.toString();
	}
	
	public ResponseStatus getResponseStatus()	{
		//can't store the actual ResponseStatus since this class needs to be serialized by old ecom clients
		return ResponseStatus.valueOf(code);
	}
	
	public boolean equals(PaymentAuthStatus _status) {
		if (_status != null) {
			return _status.getId() == id;
		}
		else {
			return false;
		}
	}

}
