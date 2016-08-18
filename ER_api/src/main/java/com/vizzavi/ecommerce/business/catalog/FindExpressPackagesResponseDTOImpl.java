/**
 * 
 */
package com.vizzavi.ecommerce.business.catalog;

import java.util.Hashtable;

/**
 * 
 * 
 * @author Ravi Aghera
 *
 */
public class FindExpressPackagesResponseDTOImpl implements
		FindExpressPackagesResponseDTO {

	
	boolean isSuccess = false;
	int errorCode = -1;
	String errorDescription = null;
	Hashtable<String, ExpressData> resultsTable;
	
	public FindExpressPackagesResponseDTOImpl() {
		//default
	}

	public FindExpressPackagesResponseDTOImpl(boolean isSuccess, int errorCode, String errorDesciption){
		
		this.isSuccess = isSuccess;
		this.errorCode = errorCode;
		this.errorDescription = errorDesciption;
		
	}
	
	@Override 
	public Hashtable<String, ExpressData> getServiceResultsTable() {
		return resultsTable;
	}

	@Override
	public void setServiceResultsTable(Hashtable<String, ExpressData> serviceResultsTable) {
		
		this.resultsTable = serviceResultsTable;

	}

	@Override
	public boolean isSuccess() {
		return isSuccess;
	}
	
	@Override
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public void setErrorCode(int code) {
		this.errorCode = code;
	}

	@Override
	public String getErrorDescription() {
		return this.errorDescription;
	}

	@Override
	public void setErrorDescription(String description) {
		this.errorDescription = description;
	}

}
