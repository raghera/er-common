package com.vizzavi.ecommerce.resources;

import com.vodafone.config.ConfigProvider;

/**
 * TODO remove this class - can't do it until ecom clients are moved to decoupling, since RatingAttributes uses it, and 
 * changing structure of that class breaks ecom clients
 * @deprecated Don't use!  copied from ECOM_PLUGIN_log4j to ER_api to remove dependencies between modules
 *  
 */
@Deprecated
public class PropertyDataBool extends PropertyData 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3177750057842286693L;
	
	///////////////////////////////////////////////////////////
	// @hud Some constants to facilitate flags set in this class
	// ON/OFF/UNSET (3-state boolean)
	public static final int		UNSET			= -1;
	public static final int		TRUE			= 0;
	public static final int		FALSE			= 1;
	//=========================================================
	
	
	private int mVal			= UNSET;
	private int mDefaultVal		= UNSET;
	
	/////////////////////////////////////////////////////////////////////
	// only in package level
	public PropertyDataBool()
	{
		super(BOOL);
	}
	
	public PropertyDataBool(
			String				key
		,	boolean				bDefaultVal
	)
	{
		this(key, bDefaultVal, true);
	}
	
	public PropertyDataBool(
			String				key
		,	boolean				bDefaultVal
		,	boolean				bSync
	)
	{
		super(BOOL);
		
		// get the values
		//mVal = PropertyServer.getPropertyBool(key, PropertyDataBool.UNSET, bSync);
		mVal = ConfigProvider.getPropertyAsBoolean(key, bSync)?0:1;
		// save default value
		mDefaultVal = bDefaultVal ? TRUE : FALSE;
	}
	
	/////////////////////////////////////////////////////////////////////////
	// abstract methods

	
	@Override
	public void normalize(PropertyData pd)
	{
		if (mVal < TRUE || mVal > FALSE) {
			PropertyDataBool pdBool = (PropertyDataBool)pd;
			mVal = pdBool.getBoolVal();
			
			if (mVal < TRUE || mVal > FALSE) {
				mVal = pdBool.getDefaultVal();
			}			
		}
	}
	
	/////////////////////////////////////////////////////////////////////////
	// interfaces
	private int getBoolVal()
	{
		return mVal;		// default implementation
	}
	private int getDefaultVal()
	{
		return mDefaultVal;
	}
	
	
	//////////////////////////////////////////////////////////////////////////
	// external interface
	public boolean isTrue()
	{
		return mVal == TRUE;	// default implementation
	}
	
	public void setVal(boolean b)
	{
		mVal = b ? TRUE : FALSE;
	}
	
	
	
		
}
