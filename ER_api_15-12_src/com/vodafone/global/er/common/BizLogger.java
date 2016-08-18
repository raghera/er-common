package com.vodafone.global.er.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;

/**
 * a business logic logger - for errors which indicate business logic errors: for example if the ERIF response doesn't match the required format
 * @author matt
 *
 */
public class BizLogger extends Log4jLoggerFactory {
//put in ER_api since most ER modules have that as an include
	
	private static final Logger logger = LoggerFactory.getLogger("com.vodafone.business");
	
	/**
	 * usage is the same as standard slf4j logger
	 * @param message
	 * @param things
	 */
	public static void error(String message, Object... things){
		logger.error(message, things);
	}
	
	/**
	 * usage is the same as standard slf4j logger
	 * @param message
	 */
	public static void error(String message){
		logger.error(message);
	}

	/**
	 * usage is the same as standard slf4j logger
	 * @param message
	 * @param things
	 */
	public static void warn(String message, Object... things)	{
		logger.warn(message, things);
	}
	
	/**
	 * usage is the same as standard slf4j logger
	 * @param message
	 */
	public static void warn(String message){
		logger.warn(message);
	}
	
}
