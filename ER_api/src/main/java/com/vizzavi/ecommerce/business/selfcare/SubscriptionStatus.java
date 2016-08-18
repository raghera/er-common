package com.vizzavi.ecommerce.business.selfcare;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class SubscriptionStatus
{

  public final static int FAILED_START_RANGE = 21;
  public final static int FAILED_END_RANGE = 30;

 // default
    public final static int DEFAULT= 9999;

    // being initialsed
    public final static int NEW = 0;

    // active
    public final static int ACTIVE = 1;
    public final static int RESERVED= 2;
    public final static int BEING_PROVISIONED = 3;
    public final static int BEING_DEPROVISIONED = 6;//CR1209AR - VFES defrag changes

	// SUSPENDED		ER7 EGYPT CHANGE
	public final static int SUSPENDED = 4;
	
	//@hud STKHREQ242
	public final static int UNDER_GRACE_PERIOD = 5;

	//CR1542 New status for an event package which is reserved but has no credits left
	public final static int RESERVED_CLOSE = 7;
	
    // old packages
    public final static int INACTIVE = 11;

	public final static int CLOSE = 12;
   // error statuses

    public final static int SYSTEM_FAILED= 21;
    public final static int PAYMENT_FAILED= 22;
    public final static int PROVISIONING_FAILED= 23;
    public final static int CANCELLED= 24;
    
    //CR-1791
    public static final int SLEEP = 25;
    
    //MQC 6948
    /*
     * Used when there is an EJB transaction rollback
     * The subscription is inserted with this status
     */
    public static final int ROLLED_BACK = 26;

    public static final int GOODWILL_CREDIT_FAILED = 30;  // CR2040 MPAY replacement.  Goodwill credit.
    

	//REMEDY 6488	
    public static final int PSEUDO_ACTIVE = 888;
    private static final int[] VALUES;

    private static final Map<Integer, String> internalStatusMap = new HashMap<Integer, String>();
    /** A Map of most statuses, indexed by Id*/
    public static final Map<Integer, String> statusMap;
    static	{
    	internalStatusMap.put(NEW, "NEW");
    	internalStatusMap.put(ACTIVE, "ACTIVE");
    	internalStatusMap.put(RESERVED, "RESERVED");
    	internalStatusMap.put(BEING_PROVISIONED, "PACKAGE_BEING_PROVISIONED");
    	internalStatusMap.put(SUSPENDED, "SUSPENDED");
    	internalStatusMap.put(BEING_DEPROVISIONED, "PACKAGE_BEING_DEPROVISIONED");
    	internalStatusMap.put(RESERVED_CLOSE, "RESERVED_CLOSED");
    	internalStatusMap.put(INACTIVE, "INACTIVE");
    	internalStatusMap.put(CLOSE, "CLOSE");
    	internalStatusMap.put(PAYMENT_FAILED, "PAYMENT_FAILED");
    	internalStatusMap.put(SYSTEM_FAILED, "SYSTEM_FAILURE");
    	internalStatusMap.put(PROVISIONING_FAILED, "PROVISIONING_FAILED");
    	internalStatusMap.put(UNDER_GRACE_PERIOD, "UNDER_GRACE_PERIOD");
    	internalStatusMap.put(DEFAULT, "DEFAULT");
    	internalStatusMap.put(SLEEP, "SLEEP");
    	internalStatusMap.put(ROLLED_BACK, "ROLLED_BACK");
    	internalStatusMap.put(CANCELLED, "CANCELLED");
    	internalStatusMap.put(GOODWILL_CREDIT_FAILED, "GOODWILL_CREDIT_FAILED");
    	statusMap=Collections.unmodifiableMap(internalStatusMap);
    	
    	int n=0;
    	VALUES=new int[internalStatusMap.size()];
    	for(Integer i: internalStatusMap.keySet()){
    		VALUES[n]=i;
    		n++;
    	}

    }

    public final static String[] getNames()
    {
    	return internalStatusMap.values().toArray(new String[internalStatusMap.size()]);
        //return NAMES;
    }

    public final static int[] getValues()
    {
    	//return internalStatusMap.keySet().toArray(new Integer[internalStatusMap.size()]);
    	//can't do the above because the method returns int[] not Integer[] and can't automatically unbox
    	return VALUES;
    }

    public static boolean isActive(int val)
    {
        return val==ACTIVE;
    }

    //MQC 6132 - method to return if status in active range
    public static boolean isActiveRange(int val) {
    	
    	return ((val>=ACTIVE)&&(val<RESERVED_CLOSE));
    	
    }
    
    public static boolean isFailed(int val)
    {
        return ((val>=FAILED_START_RANGE)&&(val<FAILED_END_RANGE));            
    }

    public static boolean isClosed(int val)
    {
		return (val == CLOSE);
    }

    public static boolean isInactive(int val)
    {
        return (val==INACTIVE || ((val>=FAILED_START_RANGE)&&(val<FAILED_END_RANGE)));
    }

    public static boolean isBeingProvisioned(int val)
    {
        return (val==BEING_PROVISIONED );
    }

    public static boolean isPendingPayment(int val)
    { 
        return (val==NEW); 
    }

    public static boolean isReserved(int val)
    {
        return (val == RESERVED);
    }

    public static String getResourceName(int val)
    {
        return "PackageSubscriptionStatus_" + val;
    }

    /** is the status int supplied a valid subscription status? 
     * 
     * @param status
     */
	public static boolean isValidStatus(int status) {
		return internalStatusMap.keySet().contains(new Integer(status));
	}
	
	/**
	 * MQC 7781 - check for suspended or grace
	 * @param val
	 * @return boolean
	 */
	public static boolean isSuspendedorGrace(int val)
    {
        return (val == SUSPENDED || val == UNDER_GRACE_PERIOD);
    }
	
	/**
	 * translates from a status code to a status name.
	 * @param status eg 4
	 * @return String eg "SUSPENDED"
	 */
	public static String translate(int status)	{
		return internalStatusMap.get(status);
	}
}
