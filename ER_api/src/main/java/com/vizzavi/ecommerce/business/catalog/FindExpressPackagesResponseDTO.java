/**
 * 
 */
package com.vizzavi.ecommerce.business.catalog;

import java.util.Map;

/**
 * DataTransferObject to be passed from the JavaApi to the decoupling layer
 * in response to a express-packages-request
 * 
 * This will contain all info for the request including any exception/error messages
 * to be propogated back to the client
 * 
 * @author Ravi Aghera
 *
 */
public interface FindExpressPackagesResponseDTO {

	/**
	 * List of packages in a successful response
	 * @return
	 */
	public 	Map<String, ExpressData> getServiceResultsTable();
	public void setServiceResultsTable(	Map<String, ExpressData> serviceResultsTable);
	
	/**
	 * Denotes whether a successful request
	 * @return
	 */
	public boolean isSuccess();
	public void setIsSuccess(boolean success);
	
	/**
	 * ErrorCode if an unsuccessful response
	 * @return
	 */	
	public int getErrorCode();
	public void setErrorCode(int code);

	/**
	 * ErrorDesciption if an unsuccessful response
	 * @return
	 */	
	public String getErrorDescription();
	public void setErrorDescription(String desciption);
	
}
