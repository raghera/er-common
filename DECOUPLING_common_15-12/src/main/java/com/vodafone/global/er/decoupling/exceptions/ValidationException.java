package com.vodafone.global.er.decoupling.exceptions;

import com.vodafone.global.er.decoupling.ErrorConstants;

/**
 * default errorid is ERROR_TYPE_VALIDATION
 *
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1;
	
	private String mErrorId = ErrorConstants.ERROR_ID_UNMARSHALL_EXCEPTION;
	private final String mErrorDescription;
	
	public ValidationException(String mErrorDescription, Exception e)	{
		super(mErrorDescription, e);
		this.mErrorDescription = mErrorDescription;
	}
	
	public ValidationException(String mErrorId, String mErrorDescription) {
		super(mErrorDescription);
		this.mErrorId = mErrorId;
		this.mErrorDescription = mErrorDescription;
	}
	
	public ValidationException(String mErrorId, String mErrorDescription, Throwable cause) {
		super(mErrorDescription, cause);
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
