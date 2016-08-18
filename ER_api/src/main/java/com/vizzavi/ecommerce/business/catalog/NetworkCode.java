/**
 * 
 */
package com.vizzavi.ecommerce.business.catalog;

import java.util.regex.Pattern;
import com.vizzavi.ecommerce.business.common.ErCoreConst;

/**
 * @author hud
 * STKHREQ13076 SP ROAMING
 */
public class NetworkCode implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 918139017327206949L;
	
	private Pattern		mPatternWildcard;
	private Pattern		mPatternRegex;
	
	private int			id 				= 0;
	private String		code			= null;
	private String		description		= null;
	private int			roamingType		= ErCoreConst.ROAMING_DOMESTIC;	// ErCoreConst.ROAMING_*

	public NetworkCode() 
	{
	}
	
	public NetworkCode(NetworkCode nc) 
	{
		id = nc.getId();
		code = nc.getCode();
		description = nc.getDescription();
		roamingType = nc.getRoamingType();
	}
	
	// getter/setter
	public int getId() {
		return id;
	}
	public void setId(int _id) {
		id = _id;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String _code) {
		code = _code;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String _description) {
		description = _description;
	}
	
	public int getRoamingType() {
		return roamingType;
	}
	public void setRoamingType(int _roamingType) {
		roamingType = _roamingType;
	}
	
	
	@Override
	public boolean equals(Object _code) {
		if (code != null) {
			return code.equals(_code);
		}
		else {
			return false;
		}
	}
	
	//MD refactor - moved this code from NetworkCodeImpl - no need for interface model for this
	
	public boolean matchWildcard(String userCode)
	{
		return mPatternWildcard.matcher(userCode).matches();
	}
	public boolean matchRegex(String userCode)
	{
		return mPatternRegex.matcher(userCode).matches();
	}
	
	
	// Now comes init
	public void init()
	throws Exception
	{
		// normal regex
		//TODO, if * is at the beginning, screwed up, not used any way for now
		mPatternRegex = Pattern.compile(getCode().replace("*", "\\*"));
		
		// wildcard
		mPatternWildcard = Pattern.compile(getCode().replace("*", "[\\w]*"));
		
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

}
