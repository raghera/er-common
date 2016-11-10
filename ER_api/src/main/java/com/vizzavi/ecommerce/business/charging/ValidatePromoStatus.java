/**
 * 
 */
package com.vizzavi.ecommerce.business.charging;

import java.io.Serializable;


/**
 * @author hud
 * STKHREQ13107 Unique Promo Code
 *
 * return status for PurchaseApi.validaePromoCode
 */
public class ValidatePromoStatus implements Serializable 
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3320756811372266473L;
	

	static public final int UNSET				= 9999;
	
	//=========================================================
	// Status codes, for member mStatus
	static public final int INVALID_PARAM		= -1;
	static public final int VALID				= 1;	//ERPromoCode.NEW	
	static public final int USED				= 2;	//ERPromoCode.USED
	static public final int CANCELLED			= 3;	//ERPromoCode.CANCELLED
	static public final int NOT_FOUND			= 0;
	static public final int INVALID_ACCOUNT		= 4;
	
	private int		mStatus			= UNSET;
	public boolean 	isValid	()	{	
		return mStatus == VALID;		
	}
	public int getStatus() {
		return mStatus;
	}
	public void setStatus(int status) {
		mStatus = status;
	}

	//==========================================================
	// promo code type
	static public final int REGULAR				= 0;
	static public final int UNIQUE				= 1;
	
	private int		mType			= UNSET;
	public boolean isUnique() {
		return mType == UNIQUE;
	}
	public void setType(int type) {
		mType = type;
	}
	public int getType() {
		return mType;
	}
	
	//========================================================
	// Only for unique promo code
	private String mPrecode			= null;
	public String getPrecode() {
		return mPrecode;
	}
	public void setPrecode(String precode) {
		mPrecode = precode;
	}
	
	private String mUniqueCode		= null; 	// 5-char code
	public String getUniqueCode() {
		return mUniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		mUniqueCode = uniqueCode;
	}
	
	
	//========================================================
	// helpful constructors
	public ValidatePromoStatus(int status)
	{
		mStatus = status;
	}
	
	public ValidatePromoStatus(int status, int type)
	{
		mStatus = status;
		mType = type;
	}
	
	public ValidatePromoStatus(int status, int type, String precode, String uniqueCode)
	{
		mStatus = status;
		mType = type;
		mPrecode = precode;
		mUniqueCode = uniqueCode;
	}
	
	public ValidatePromoStatus(ValidatePromoStatus vpStatus)
	{
		mStatus = vpStatus.getStatus();
		mType = vpStatus.getType();
		mPrecode = vpStatus.getPrecode();
		mUniqueCode = vpStatus.getUniqueCode();
	}
	
	//=========================================================
	// helpers
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("status = ").append(mStatus)
		  .append(", type = ").append(mType)
		  .append(", precode = ").append(mPrecode);
		
		return sb.toString();
	}
}
