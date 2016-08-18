package com.vizzavi.ecommerce.business.common;

import com.vizzavi.ecommerce.resources.PropertyDataBool;
import com.vizzavi.ecommerce.resources.PropertyDataInt;
import com.vodafone.config.ConfigProvider;

/**
 * 
 * Be careful putting properties in here. For multi-tenancy, the value will vary depending on the requesting country, which varies on a per-thread basis,
 *  so creating a static variable for the value is wrong and will cause bugs.  Delegate to configprovider instead.  <br/>
 * MQC9676 - adjusted so all properties are retrieved dynamically
 */
public class ErCoreConst	{
	

	public static final String ZERO_COST_IGNORE_DEFAULT_MODE ="DEFAULT";
	public static final String ZERO_COST_IGNORE_PERSIST_IGNORE_MODE ="PERSIST_IGNORE";
	public static final String ZERO_COST_IGNORE_NO_PERSISTANCE_MODE ="NO_PERSISTANCE";
	public static final String ZERO_COST_IGNORE_COUNTER_MODE ="COUNTER";
	
	
	// ROAMING - deprecated
	//TODO remove these
	public final static int ROAMING_DOMESTIC				= 0;
	public final static int ROAMING_OFF_FOOTPRINT			= 1;
	public final static int ROAMING_ON_FOOTPRINT			= 2;
	public final static String[] ROAMING_TYPE_STR = new String[]{"Domestic", "Off-Footprint", "On-Footprint"};
	
	public final static int ROAMING_DUMMY_NETWORK_CODE_ID	= 0;
		
	
	public final static int MICROSERVICE_ACTIVE				= 0;
	public final static int MICROSERVICE_INACTIVE			= 1;
	public final static long MICROSERVICE_TIME_UNIT			= 60000;	// millisecond => 1 min
	
	


	private static String BATCHCLIENTID = ConfigProvider.getProperty("com.vodafone.global.er.batch.APPLICATIONID", "BATCH");
	public static String getBatchClientId() {
		return BATCHCLIENTID;
	}
	
	public static PeriodValue getGracePeriod() {
		return PeriodValue.parse(ConfigProvider.getProperty("core.grace_period"));
	}
	
 
	public static PeriodValue getSuspensionPeriod() {
		return PeriodValue.parse(ConfigProvider.getProperty("core.suspension_period"));
	}
	
	
	
	public static boolean needNoZeroCostEvent() {
		return ConfigProvider.getPropertyAsBoolean("NO_ZERO_COST", false);
	}
	
	
	/**
	 * rounding
	 * @return
	 */
	public static int getRoundNthDecimal() {
		return ConfigProvider.getPropertyAsInteger("ROUND_NTH_DECIMAL", 4);
	}
	
	
    
	/**
	 * probably not used
	 * @return
	 */
	@Deprecated
	public static PropertyDataBool CFG_CREDIT_REFUND_REQUEST_PAYMENT_HANDLER() {
		return new PropertyDataBool("CREDIT_REFUND_REQUEST_PAYMENT_HANDLER", false, false);
	}

	
	// network code match method
	public static final int NCMATCH_WILDCARD 		= 0;
	public static final int NCMATCH_REGEX	 		= 1;
	/**
	 * shouldn't be used, this is roaming stuff and deprecated
	 * @return
	 */
	@Deprecated
	public static PropertyDataInt CFG_NETWORK_CODE_MATCH_METHOD() {
		return new PropertyDataInt("NETWORK_CODE_MATCH_METHOD", new String[] {"WILDCARD", "REGEX"}, NCMATCH_WILDCARD, false);
	}
	
	// multiusage
	public final static int MUM_ONLY_WHOLE 		= 0;
	public final static int MUM_PART_PRIORITY 	= 1;
	public final static int MUM_WHOLE_PRIORITY 	= 2;
	
	public static PropertyDataInt CFG_MULTIUSAGE_MODE() {
		return new PropertyDataInt("com.vizzavi.ecommerce.business.charging.PATIAL_WHOLE", MUM_ONLY_WHOLE, false);
	}
	
	// REFUSE_USAGE_WHEN_PROVISIONING
	public static PropertyDataBool CFG_REFUSE_USAGE_WHEN_PROVISIONING() {
		return new PropertyDataBool("REFUSE_USAGE_WHEN_PROVISIONING", false, false);
	}
	

	
	// Unique promo code setting
	public static int UNIQUE_PROMO_CODE_LENGTH() {
		return ConfigProvider.getPropertyAsInteger("UNIQUE_PROMO_CODE_LENGTH", 5);
	}


    /*
    * CR1564 -Utctimezone for diff region in country
    * Multiple time zone properties:
    * To allow information pertaining to user accounts such as usergroups,
    * timezone, billing cycle and status to be done by only the decoupling
    * interface or only through the ER-IF validation handler 
    * response or both methods can update account information. Valid 
    * values are "DI", "ERIF", "BOTH" 
    */
    public static final String MODIFY_SRC_BOTH = "BOTH";
    public static final String MODIFY_SRC_ERIF = "ERIF";
    public static final String MODIFY_SRC_DI   = "DI";

    public static String MODIFY_ACCOUNT_USERGROUP() {
        return ConfigProvider.getProperty("MODIFY_ACCOUNT_USERGROUP", MODIFY_SRC_ERIF);
    }

    public static String MODIFY_ACCOUNT_TIMEZONE() {
        return ConfigProvider.getProperty("MODIFY_ACCOUNT_TIMEZONE", MODIFY_SRC_ERIF);
    }

    public static String MODIFY_ACCOUNT_BILLINGCYCLEDATE() {
        return ConfigProvider.getProperty("MODIFY_ACCOUNT_BILLINGCYCLEDATE", MODIFY_SRC_ERIF);
    }

    public static String MODIFY_ACCOUNT_STATUS() {
        return ConfigProvider.getProperty("MODIFY_ACCOUNT_STATUS", "BOTH");
    }

    // CR 1643 - Pre-Pay Post-Pay Split
    public static String MODIFY_ACCOUNT_SP_ID() {
        return ConfigProvider.getProperty("MODIFY_ACCOUNT_SP_ID", MODIFY_SRC_BOTH);
    }

    public static String MODIFY_ACCOUNT_IS_PREPAY() {
        return ConfigProvider.getProperty("MODIFY_ACCOUNT_IS_PREPAY", MODIFY_SRC_BOTH);
    }
    // CR 1643 - Ends


    /**
     * To enable/disable multiple timezone support for performance reasons. 
     */
   	//private static String MULTIPLE_TIMEZONE_ENABLED = ConfigProvider.getProperty("MULTIPLE_TIMEZONE_ENABLED", "false");
    //public static String MULTIPLE_TIMEZONE_ENABLED() {
    //    return MULTIPLE_TIMEZONE_ENABLED;
    //}
	// CR1564 Change to boolean for easy comparison.
    public static boolean MULTIPLE_TIMEZONE_ENABLED() {
        return ConfigProvider.getPropertyAsBoolean("MULTIPLE_TIMEZONE_ENABLED", false);
    }

    // CR 2198 - add child spid and service provider type
    public static String MODIFY_ACCOUNT_CHILD_SP_ID() {
        return ConfigProvider.getProperty("MODIFY_ACCOUNT_CHILD_SP_ID", MODIFY_SRC_BOTH);
    }
    
    public static String MODIFY_ACCOUNT_SP_TYPE() {
        return ConfigProvider.getProperty("MODIFY_ACCOUNT_SP_TYPE", MODIFY_SRC_BOTH);
    }
    


}
