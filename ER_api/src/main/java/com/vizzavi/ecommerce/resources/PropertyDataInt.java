package com.vizzavi.ecommerce.resources;

import com.vodafone.config.ConfigProvider;

/**
 * TODO remove this class - can't do it until ecom clients are moved to decoupling, since RatingAttributes uses it, and 
 * changing structure of that class breaks ecom clients
 * @deprecated Don't use!  copied from ECOM_PLUGIN_log4j to ER_api to remove dependencies between modules
 *  
 */
@Deprecated
public class PropertyDataInt extends PropertyData 
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6657300589095531557L;


	/////////////////////////////////////////////////////////////
	// constants
	public static final int 	UNSET	= -1;
	
	
	////////////////////////////////////////////////////////////
	// Members
	private int mVal			= UNSET;
	private int mDefaultVal		= UNSET;
	
	/////////////////////////////////////////////////////////////////////
	// only in package level
	public PropertyDataInt()
	{
		super(INT);
	}
	
	public PropertyDataInt(
			String				key
		,	int					defaultVal
	)
	{
		this(key, defaultVal, true);
	}
	
	
	public PropertyDataInt(
			String				key
		,	int					defaultVal
		,	boolean				bSync
	)
	{
		super(INT);
		
		// get the values
		//mVal = PropertyServer.getPropertyInt(key, UNSET, bSync);
		mVal=ConfigProvider.getPropertyAsBoolean(key, (defaultVal==0?true:false))?0:1;
		// set default
		mDefaultVal = defaultVal;
	}
	

	public PropertyDataInt(
			String				key
		,	String[]			aAllowedVals
		,	int					defaultVal
	)
	{
		this(key, aAllowedVals, defaultVal, true);
	}
	
	public PropertyDataInt(
			String				key
		,	String[]			aAllowedVals
		,	int					defaultVal
		,	boolean				bSync
	)
	{
		super(INT);
		
		// get the value
		//mVal = PropertyServer.getPropertyString2Int(key, aAllowedVals, UNSET, bSync);
		mVal = ConfigProvider.getPropertyAsInteger(key, defaultVal);
		// set default
		mDefaultVal = defaultVal;
	}
	
	public int getVal() 
	{
		return mVal;
	}
	public int getDefaultVal()
	{
		return mDefaultVal;
	}
	
	public void setVal(int val)
	{
		mVal = val;
	}

	/* (non-Javadoc)
	 * @see com.vizzavi.ecommerce.resources.PropertyData#normalize(com.vizzavi.ecommerce.resources.PropertyData)
	 */
	//@Override
	@Override
	public void normalize(PropertyData pd) {
		// TODO Auto-generated method stub
		if (mVal == UNSET) {
			PropertyDataInt pdInt = (PropertyDataInt)pd;
			mVal = pdInt.getVal();
			if (mVal == UNSET) {
				mVal = pdInt.getDefaultVal();
			}
		}
	}

}
