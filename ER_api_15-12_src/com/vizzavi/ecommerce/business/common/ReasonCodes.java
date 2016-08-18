package com.vizzavi.ecommerce.business.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class ReasonCodes implements java.io.Serializable {
	
	private    static final long serialVersionUID = -1849789140679707782L;
	
	//CR1231
	//private static final LWLogger logger = LWSupportFactoryImpl.getInstance().getLogger(ReasonCodes.class);
	private static final Logger logger = Logger.getLogger(ReasonCodes.class);
	
	public final static String REASON_CODE_CLASS = "com.vizzavi.ecommerce.business.common.ReasonCode";
	/** A Map of all ReasonCodes declared in ReasonCode.java - key is the field name eg SUB_NOT_FOUND_FOR_EXT_SUB_ID*/
	public static Map<String, ReasonCode> allReasonCodes = new HashMap<String, ReasonCode>();
	public static ArrayList<ReasonCode> reasonCodes = new ArrayList<ReasonCode>();
	private ReasonCodes() {
	
	}
	
	static 	{
		try {
			Class<?> reasonCodeClass = Class.forName(REASON_CODE_CLASS);
			Field[] reasonCodeFields = reasonCodeClass.getFields();
			
			for (int i=0; i<reasonCodeFields.length; i++) {
				if (reasonCodeFields[i].get(reasonCodeClass).getClass().getName().equals(REASON_CODE_CLASS)) {
					ReasonCode reasonCode = (ReasonCode) reasonCodeFields[i].get(reasonCodeClass);
					allReasonCodes.put(reasonCodeFields[i].getName(), reasonCode);
					reasonCodes.add(reasonCode);
				}
			}
			
		}
		catch (ClassNotFoundException c) {
			logger.error("ReasonCodes.getAll : Class not found Exception " + REASON_CODE_CLASS + " " + c.getMessage());
		}
		catch (Exception e) {
			logger.error("ReasonCodes.getAll : CException " + e.getMessage());
		}
	}
	
	public static ReasonCode[] getAll() {
	
		return reasonCodes.toArray((new ReasonCode[]{}));
	}
	
}
