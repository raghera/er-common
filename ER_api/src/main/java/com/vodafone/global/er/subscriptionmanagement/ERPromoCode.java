/**
 * 
 */
package com.vodafone.global.er.subscriptionmanagement;

/**
 * @author hud
 * STKHREQ13107
 *
 * The promo code information, especially for unique promo code
 * 
 */
public class ERPromoCode 
{
	// These status maps the values in ER_PROMO_STATUS table
	public static final int		NEW			= 1;
	public static final int		USED		= 2;
	public static final int		CANCELLED	= 3;
	
	
	
	// base fields
	private String		mPrecode			= null;
	private String		mUniqueCode			= null;
	private	int			mStatus				= NEW;
	
	//REMEDY 6488
    private ERSubscription mSub             = null;
	//==================================================
	// constructors
	public ERPromoCode(String precode, String uniqueCode, int status)
	{
		mPrecode = precode;
		mUniqueCode = uniqueCode;
		mStatus = status;
	}

	//Added for REMEDY 6488
	public ERPromoCode()
	{
	}
	
	//===================================================
	// getter and setters
	public String getPrecode()	{
		return mPrecode;
	}
	
	public String getUniqueCode() {
		return mUniqueCode;
	}
	
	public int getStatus() {
		return mStatus;
	}
	
	//REMEDY 6488
	public void setSubscription(ERSubscription sub)
	{
		mSub = sub;
	}
	
	public ERSubscription getSubscription()
	{
		return mSub;
	}
	
	public void setPromoCode(String promo)
	{
		mPrecode = promo;
	}
	
	public String getPromoCode()
	{
		return mPrecode;
	}
	
	//END REMEDY 6488
}
