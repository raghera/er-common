/**
 * 
 */
package com.vodafone.global.er.decoupling.exceptions;

/**
 * @author stewarm
 *
 */
public class TransportException extends RuntimeException {
	
	private static final long serialVersionUID = 1;
	
	private String mErrorId;
	private String mErrorDescription;

	public TransportException(String mErrorId, String mErrorDescription) {
		super(mErrorDescription);
		this.mErrorId = mErrorId;
		this.mErrorDescription = mErrorDescription;
	}

	public String getErrorDescription() {
		return mErrorDescription;
	}

	public String getErrorId() {
		return mErrorId;
	}
}
