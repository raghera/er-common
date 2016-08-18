package com.vizzavi.ecommerce.business.selfcare;


/**

*/
public class ServiceStatus
{

	public final static int BEING_PROVISIONED	=200;
	public final static int ACTIVE				=201;
	public final static int INACTIVE			=211;
	public final static int DISABLED			=212;

	public final static int PROVISION_SUCCESS	=221;
	public final static int PROVISION_DELAY		=222;
	public final static int PROVISION_FAILED	=223;

	public static final int PECS_PROVISION_FAILED_DEACTIVATION = 5;
	public static final int PECS_PROVISION_DELAYED_DEACTIVATION = 7;
	public static final int PECS_PROVISION_SUCCESSFULL_DEACTIVATION = 9;


    private final static String[] NAMES = new String[] {

        "BEING_PROVISIONED",
		"ACTIVE",
		"INACTIVE",
		"DISABLED",
		"PROVISION_SUCCESS",
		"PROVISION_DELAY",
		"PROVISION_FAILED"

    };


    private final static int[] VALUES = new int[] { 200,201,211,212,221,222,223 };


//	// Remedy Case 00000417 - returns the equivelent ER4 status given a ER5 status
//	protected static int getER4Status(int val)
//	{
//		switch(val)
//		{	case BEING_PROVISIONED:
//				return PECS_PKG_SRV_PKG_PAYMENT_OK_BEING_PROVISIONED;
//			case ACTIVE:
//				return PECS_PKG_SRV_ACTIVE;
//			case INACTIVE:
//				return PECS_PKG_SRV_INACTIVE;
//			case DISABLED:
//				return PECS_PKG_SRV_INACTIVE;
//			case PROVISION_SUCCESS:
//				return PECS_PKG_SRV_ACTIVE;
//			case PROVISION_DELAY:
//				return PECS_PKG_SRV_PROV_DELAYED;
//			case PROVISION_FAILED:
//				return PECS_PKG_SRV_PROV_FAILED;
//			default:
//				return val;
//		}
//	}

    public final static String[] getNames()
    {
        return NAMES;
    }

    public final static int[] getValues()
    {
        return VALUES;
    }

	public final static int getNonStatusValue()
	{
		//used for PROVISISIONING NOT REQUIRED
		int rv = 0;
		for (int element : VALUES)
			rv=rv+element;
		return rv;
	}

    public static boolean isActive(int val)
    {
    	return (val==ACTIVE);

    }

	public static boolean isProvisionSuccess(int val)
	{
		boolean rv = false;
	
        if (val==PROVISION_SUCCESS) {
			rv = true;
		}
		return rv;
	}

	public static boolean isProvisionFailed(int val)
	{
		boolean rv = false;
	
        if (val==PROVISION_FAILED) {
			rv = true;
		}
		return rv;
	}

    public static boolean isProvisionDelay(int val)
    {
        boolean rv = false;
  
        if (val==PROVISION_DELAY) {
            rv = true;
        }
        return rv;
    }

	public static boolean isDisabled(int val)
	{

		return (val==DISABLED);
	}


    public static boolean isPending(int val)
    {
    	return  (val==BEING_PROVISIONED ||val==PROVISION_DELAY);

    }

    public static boolean isInactive(int val)
    {

    	return  (val==INACTIVE);
    }

    public static boolean isBeingProvisioned(int val)
    {
    	return (val==BEING_PROVISIONED);

    }

    //MQC7734
    public static boolean isProvisionStatusValid(int val)
    {
    	return (val == PROVISION_SUCCESS || val == PROVISION_DELAY || val == PROVISION_FAILED 
    			|| val ==PECS_PROVISION_DELAYED_DEACTIVATION || val == PECS_PROVISION_FAILED_DEACTIVATION || val == PECS_PROVISION_SUCCESSFULL_DEACTIVATION );

    }



    public static String getResourceName(int val)
    {
        return "PackageSubscriptionStatus_" + val;
    }

}
