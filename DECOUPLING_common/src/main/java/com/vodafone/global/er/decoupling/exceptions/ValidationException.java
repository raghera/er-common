package com.vodafone.global.er.decoupling.exceptions;

import com.vodafone.global.er.decoupling.ErrorConstants;

/**
 * default errorid is ERROR_TYPE_VALIDATION
 *
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1;
	
	private int mErrorId = ErrorConstants.ERROR_ID_UNMARSHALL_EXCEPTION;
	private final String mErrorDescription;
	
	public ValidationException(String mErrorDescription, Exception e)	{
		super(mErrorDescription, e);
		this.mErrorDescription = mErrorDescription;
	}
	
	public ValidationException(int errorId, String errorDescription) {
		super(errorDescription);
		this.mErrorId = errorId;
		this.mErrorDescription = errorDescription;
	}
	
	public ValidationException(int errorId, String errorDescription, Throwable cause) {
		super(errorDescription, cause);
		this.mErrorId = errorId;
		this.mErrorDescription = errorDescription;
	}
	
	public ValidationException(String errorDescription) {
		super(errorDescription);
		this.mErrorDescription = errorDescription;
	}

	public String getErrorDescription() {
		return mErrorDescription;
	}

	public int getErrorId() {
		return mErrorId;
	}
	
}
