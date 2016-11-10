package com.vizzavi.ecommerce.business.common;

/**
    The device type represents the type of the device the user is using
*/
public class DeviceType {

    /**
    * Device type of WEB.
    */
    public final static int WEB = 0;

    /**
    * Device type of WAP.
    */
    public final static int WAP = 1;

    /**
    * Device type of SMS.
    */
    public final static int SMS = 2;

	/**
	* Device type of MMS.
	*/
    public final static int MMS = 3;

    /**
    * Device type of IVR.
    */
    public final static int IVR = 4;

	/**
    * Device type of CCA.
    */
    public final static int CCA = 5;

	/**
	* Device type of SIM_TK.
	*/
    public final static int SIM_TK = 6;

   
    /**
	* MQC 6361 - add additional generic values for VFGR.
	*/
    public final static int SPEC1 = 10;
    public final static int SPEC2 = 11;
    public final static int SPEC3 = 12;
    public final static int SPEC4 = 13;
    public final static int SPEC5 = 14;
    public final static int SPEC6 = 15;
    public final static int SPEC7 = 16;
    public final static int SPEC8 = 17;
    public final static int SPEC9 = 18;
    public final static int SPEC10 = 19;
    
    public static String getResourceName(int val)
    {
        return "DeviceType_" + val;
    }

    public static int intValue(String name)
    {
        if("WAP".equals(name)) return DeviceType.WAP;
        if("SMS".equals(name)) return DeviceType.SMS;
        if("MMS".equals(name)) return DeviceType.MMS;
        if("WEB".equals(name)) return DeviceType.WEB;
        if("IVR".equals(name)) return DeviceType.IVR;
        if("CCA".equals(name)) return DeviceType.CCA;
        if("SIM_TK".equals(name)) return DeviceType.SIM_TK;
        //MQC 6361 - add additional generic values for VFGR.
        if("SPEC1".equals(name)) return DeviceType.SPEC1;
        if("SPEC2".equals(name)) return DeviceType.SPEC2;
        if("SPEC3".equals(name)) return DeviceType.SPEC3;
        if("SPEC4".equals(name)) return DeviceType.SPEC4;
        if("SPEC5".equals(name)) return DeviceType.SPEC5;
        if("SPEC6".equals(name)) return DeviceType.SPEC6;
        if("SPEC7".equals(name)) return DeviceType.SPEC7;
        if("SPEC8".equals(name)) return DeviceType.SPEC8;
        if("SPEC9".equals(name)) return DeviceType.SPEC9;
        if("SPEC10".equals(name)) return DeviceType.SPEC10;
        return -1;
    }

    /**
     * returns "WEB" for 0, "WAP" for 1, etc etc
     * @param code
     * @return
     */
    public static String stringValue(int code)
    {
    	switch( code )	{
			case DeviceType.WAP: return "WAP";
	        case DeviceType.SMS: return "SMS";
	        case DeviceType.MMS: return "MMS";
	        case DeviceType.WEB: return "WEB";
	        case DeviceType.IVR: return "IVR";
	        case DeviceType.CCA: return "CCA";
	        case DeviceType.SIM_TK: return "SIM_TK";
	        //MQC 6361 - add additional generic values for VFGR.
	        case DeviceType.SPEC1: return "SPEC1";
	        case DeviceType.SPEC2: return "SPEC2";
	        case DeviceType.SPEC3: return "SPEC3";
	        case DeviceType.SPEC4: return "SPEC4";
	        case DeviceType.SPEC5: return "SPEC5";
	        case DeviceType.SPEC6: return "SPEC6";
	        case DeviceType.SPEC7: return "SPEC7";
	        case DeviceType.SPEC8: return "SPEC8";
	        case DeviceType.SPEC9: return "SPEC9";
	        case DeviceType.SPEC10: return "SPEC10";
	        default: return null;
    	}
       
    }
}