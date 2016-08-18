package com.vodafone.global.er.decoupling.exceptions;

import com.vodafone.global.er.decoupling.ErrorConstants;

public class InvalidCountryException extends RuntimeException {


	private static final long serialVersionUID = 198498468198L;
	private final String mErrorId;
	private final String mErrorDescription;
	
	public InvalidCountryException(String mErrorDescription, Exception e)
	{
		super(mErrorDescription, e);
		this.mErrorId = ErrorConstants.ERROR_ID_INVALID_COUNTRY;	
		this.mErrorDescription = mErrorDescription;
	}
	
	public InvalidCountryException(String mErrorId, String mErrorDescription) {
		super(mErrorDescription);
		this.mErrorId = mErrorId;
		this.mErrorDescription = mErrorDescription;
	}
	
	public InvalidCountryException(String mErrorDescription) {
		super(mErrorDescription);
		this.mErrorId = ErrorConstants.ERROR_ID_INVALID_COUNTRY;	
		this.mErrorDescription = mErrorDescription;
	}

	public String getErrorDescription() {
		return mErrorDescription;
	}

	public String getErrorId() {
		return mErrorId;
	}
	
	
	
}
