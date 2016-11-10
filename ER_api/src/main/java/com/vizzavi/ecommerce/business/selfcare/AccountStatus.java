package com.vizzavi.ecommerce.business.selfcare;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class AccountStatus {

	public final static int ACTIVE = 401;
	public final static int INACTIVE = 402;
	public final static int LOCKED = 403;


	private final static Map<Integer, String> internalStatusMap = new HashMap<Integer, String>();
	
	/** a Map of Statuses, indexed by ID */
	public final static Map<Integer, String> statusMap;
	
	//private final static String[] NAMES = new String[] { "ACTIVE", "INACTIVE", "LOCKED" };

	private final static int[] VALUES = new int[] { ACTIVE, INACTIVE, LOCKED };

	static	{
		internalStatusMap.put(ACTIVE, "ACTIVE");
		internalStatusMap.put(INACTIVE, "INACTIVE");
		internalStatusMap.put(LOCKED, "LOCKED");
		statusMap = Collections.unmodifiableMap(internalStatusMap);
	}

	public final static String[] getNames() {
		//return NAMES;
		return internalStatusMap.values().toArray(new String[internalStatusMap.size()]);
	}

	public final static int[] getValues() {
		return VALUES;
	}

	public static boolean isActive(int val) {
		return (val==ACTIVE);
	}

}
