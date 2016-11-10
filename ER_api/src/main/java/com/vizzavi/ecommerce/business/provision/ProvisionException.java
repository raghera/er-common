package com.vizzavi.ecommerce.business.provision;

/**
* The exception to represent failiures in usage authorizations
* against the charging subsystem.
*/

public class ProvisionException extends Exception
{
	public ProvisionException()
	{
		super();
	}

	//Remedy 6652, Bruno Meseguer, CORE and IF out of sync with provisioning
	public ProvisionException(Exception original)
	{
		super(original);
	}	
	private static final long serialVersionUID = 532654226185842288L;
	
    public ProvisionException(String s)
    {
    	super(s);
    }
    
	//Remedy 6652, Bruno Meseguer, CORE and IF out of sync with provisioning
	public ProvisionException(String s, Exception original)
	{
		super(s, original);
	}    
}
