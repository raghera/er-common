package com.vizzavi.ecommerce.resources;

/**
 * TODO remove this class - can't do it until ecom clients are moved to decoupling, since RatingAttributes uses it, and 
 * changing structure of that class breaks ecom clients
 * @deprecated Don't use!  moved from ECOM_PLUGIN_log4j to ER_api to remove dependencies between modules
 *  
 */
@Deprecated
abstract public class PropertyData implements java.io.Serializable
{
	//////////////////////////////////////////////////////////////////////////
	// Pre-defined property types
	
	// BOOL
	public final static int BOOL				= 1;
	public final static int INT					= 2;
	
	//Added 23/4/13 MD. Very important for making old ecom clients work! see MQC 8560
	//older versions of this file didn't have the field declared, so it was calculated at runtime, and must match for deserialization to work
	//java.io.InvalidClassException was seen: local class incompatible: stream classdesc serialVersionUID = -2099071815255825883, local class serialVersionUID = [something else]
	private static final long serialVersionUID = -2099071815255825883l;	
	
	// type
	private final int mType;
	
	///////////////////////////////////////////////////////////////////////////
	protected PropertyData(int type)
	{
		mType = type;
	}

	
	///////////////////////////////////////////////////////////////////////////
	// getters
	int getType()
	{
		return mType;
	}	
	
	/////////////////////////////////////////////////////////////////////////////
	// interface
	public abstract void normalize(PropertyData pd);
	

	
	

}
