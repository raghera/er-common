/**
 * 
 */
package com.vodafone.global.er.decoupling.exceptions;

import javax.xml.bind.UnmarshalException;

import com.vodafone.global.er.decoupling.ErrorConstants;

/**
 * @author stewarm
 *
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1;
	
	private String mErrorId;
	private String mErrorDescription;
	
	public ValidationException(String mErrorDescription, Exception e)
	{
		super(mErrorDescription);
		
		// setup the errorId dependant on the instance of error we have
		if(e instanceof UnmarshalException)
			this.mErrorId = ErrorConstants.ERROR_ID_UNMARSHALL_EXCEPTION;		
		else
			this.mErrorId = ErrorConstants.ERROR_ID_UNEXPECTED;
		
		this.mErrorDescription = mErrorDescription;
	}
	
	public ValidationException(String mErrorId, String mErrorDescription) {
		super(mErrorDescription);
		this.mErrorId = mErrorId;
		this.mErrorDescription = mErrorDescription;
	}
	
	public ValidationException(String mErrorDescription) {
		super(mErrorDescription);
		this.mErrorDescription = mErrorDescription;
	}

	public String getErrorDescription() {
		return mErrorDescription;
	}

	public String getErrorId() {
		return mErrorId;
	}
	
}
