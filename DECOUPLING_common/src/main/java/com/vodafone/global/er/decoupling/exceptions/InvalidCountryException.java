package com.vodafone.global.er.decoupling.exceptions;

import com.vodafone.global.er.decoupling.ErrorConstants;

public class InvalidCountryException extends RuntimeException {


	private static final long serialVersionUID = 198498468198L;
	private final int mErrorId;
	private final String mErrorDescription;
	
	public InvalidCountryException(String mErrorDescription, Exception e)
	{
		super(mErrorDescription, e);
		this.mErrorId = ErrorConstants.ERROR_ID_INVALID_COUNTRY;	
		this.mErrorDescription = mErrorDescription;
	}
	
	public InvalidCountryException(int errorId, String errorDescription) {
		super(errorDescription);
		this.mErrorId = errorId;
		this.mErrorDescription = errorDescription;
	}
	
	public InvalidCountryException(String mErrorDescription) {
		super(mErrorDescription);
		this.mErrorId = ErrorConstants.ERROR_ID_INVALID_COUNTRY;	
		this.mErrorDescription = mErrorDescription;
	}

	public String getErrorDescription() {
		return mErrorDescription;
	}

	public int getErrorId() {
		return mErrorId;
	}
	
	
	
}
