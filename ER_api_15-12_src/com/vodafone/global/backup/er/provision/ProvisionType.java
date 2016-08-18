package com.vodafone.global.er.provision;

/**
 *  This interface describes the various provision types.
 *
 *
 *  @author Girish
 *  @version $Revision: 1.3 $
 */


public class ProvisionType implements java.io.Serializable{

   private    static final long serialVersionUID = -2823583218401066832L;

	public static final String UPDATE_PROVISION 		= "UPDATE";

	public static final String ACTIVATE_PROVISION		= "ACTIVATE";

	public static final String DE_ACTIVATE_PROVISION	= "DE-ACTIVATE";

	public static final String RE_ACTIVATE_PROVISION	= "RE-ACTIVATE";

	public static final String SUSPEND_PROVISION		= "SUSPEND";
	
	//CR-0150 - Add new provisioning type for renewals
	public static final String RENEWAL_ACTIVATE_PROVISION	= "RENEWAL-ACTIVATE";
}
