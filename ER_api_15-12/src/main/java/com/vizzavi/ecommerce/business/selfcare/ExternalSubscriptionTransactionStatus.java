package com.vizzavi.ecommerce.business.selfcare;

/**
 * created for Google DCB3 (CR 2082) this enum lists possible outcomes of a check on external subscription / transaction
 * @author matt
 *
 */
public enum ExternalSubscriptionTransactionStatus implements java.io.Serializable {
	/**neither the external subId nor transId supplied exist in the DB */
	EXT_SUB_TRAN_ID_NOT_EXISTS,
	/**the external subId exists in the DB */
	EXT_SUB_ID_SUB_EXISTS,
	/**the external transId exists in the DB */
	EXT_TRAN_ID_TRAN_EXISTS; 
	
	/**
	 * does this enum correspond to a record which already exists in the DB?
	 * @return true if the external subId or external transID exist in the DB; false otherwise 
	 */
	public boolean exists()	{
		return !this.equals(EXT_SUB_TRAN_ID_NOT_EXISTS);
	}

}
