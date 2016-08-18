package com.vizzavi.ecommerce.business.selfcare;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**

*/
public class TransactionStatus
{

	public final static int PENDING	=100;

	public final static int COMPLETED =101;
	public final static int RESERVED =102;

	public final static int ERROR =121;
	public final static int DENIED =122;
	public final static int REJECTED =123;
	public final static int CANCELLED =124;

	//MQC 6187 - new status of rolled back
	public final static int ROLLED_BACK =125;
	
    public final static int FAILED_START_RANGE = 121;
    public final static int FAILED_END_RANGE = 130;
    
	public static final int GOODWILL_CREDIT_FAILED = 150;  // CR2040 MPAY replacement.  Goodwill credit.
    public static final int GOODWILL_CREDIT_COMPLETED = 151;  // CR2040 MPAY replacement.  Goodwill credit.

    private static final int[] VALUES;	//{ 100,101,102,121,122,123,124,125};
    private static final Map<Integer, String> internalStatusMap = new HashMap<Integer, String>();

    /** A Map of all statuses, indexed by Id*/
    public static final Map<Integer, String> statusMap;
    
    static	{
    	internalStatusMap.put(PENDING, "PENDING");
    	internalStatusMap.put(COMPLETED, "COMPLETED");
    	internalStatusMap.put(RESERVED, "RESERVED");
    	internalStatusMap.put(ERROR, "ERROR");
    	internalStatusMap.put(DENIED, "DENIED");
    	internalStatusMap.put(REJECTED, "REJECTED");
    	internalStatusMap.put(CANCELLED, "CANCELLED");
    	internalStatusMap.put(ROLLED_BACK, "ROLLED_BACK");
    	internalStatusMap.put(GOODWILL_CREDIT_FAILED, "GOODWILL_CREDIT_FAILED");
    	internalStatusMap.put(GOODWILL_CREDIT_COMPLETED, "GOODWILL_CREDIT_COMPLETED");


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
    }

    public final static int[] getValues()
    {
        return VALUES;
    }

    public static boolean isCompleted(int val)
    {
        return (val==COMPLETED);
    }

    public static boolean isFailed(int val)
    {

        return ( (val>=FAILED_START_RANGE)&&(val<FAILED_END_RANGE) ) ;
    }


    public static boolean isDenied(int val)
    {
        return (val==DENIED);
    }

    public static boolean isRejected(int val)
    {
        return val==REJECTED;
    }

    public static boolean isPending(int val)
    {
        return val==PENDING;
    }


    public static boolean isReserved(int val)
    {
        return val==RESERVED;
    }

    public static boolean isCancelled(int val)
    {
        return val==CANCELLED;
    }

    //MQC 6187 - new status of rolled back
    public static boolean isRolledBack(int val)
    {
        return val==ROLLED_BACK;
    }
    
    public static String getResourceName(int val)
    {
        return "TransactionStatus_" + val;
    }

    //CR1528 - get transactions status name
    public static String getTransactionStatusName(int val)
    {
    	return internalStatusMap.get(val);

    }
    
    // CR2040 MPAY replacement.  Goodwill credit.
    public static boolean isGoodwillCreditFailed(int val) {
    	return (val ==  GOODWILL_CREDIT_FAILED);
    }
    
    // CR2040 MPAY replacement.  Goodwill credit.
    public static boolean isGoodwillCreditCompleted(int val) {
    	return val ==  GOODWILL_CREDIT_COMPLETED;
    }
    
}
