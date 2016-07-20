/**
 * 
 */
package com.vodafone.global.er.ulf.service;

import com.vodafone.application.logging.ULFEntry;
import com.vodafone.global.er.translog.TransLogManager;

/**
 * 
 * Manages Logging data for ER applications
 * Saves required data in memory
 * Interacts with Services to obtain data perform logging
 * 
 * @author Ravi Aghera
 *
 */
public interface ERULFLogDataManager {
	
	
	/*
	 * Values to be logged
	 * 
	 * transaction-id == VF_INT_TRACE_ID - vf-trace-transaction-id *
	 * external-trace-id 
	 * external-reference-id
	 * usecase-id
	 * msisdn *
	 * timestamp
	 * component
	 * destination
	 * payload
	 * service
	 * country-code
	 * status
	 * error-code
	 * error
	 * caller-id
	 * inbound-request-ip
	 * inbound-request-port
	 * inbound-request-uri
	 * outbound-url
	 * server-name
	 * message-size
	 * logpoint
	 * 
	 */
	
	public void logULFRequestIn(final TransLogManager transLogDataManager, final ULFEntry.Logpoint logPoint);
	
	public void logULFRequestOut(final TransLogManager transLogDataManager, final ULFEntry.Logpoint logPoint);
	
	public void logULFResponseOut(final TransLogManager transLogDataManager, final ULFEntry.Logpoint logPoint);
	
	public void logULFResponseIn(final TransLogManager transLogDataManager, final ULFEntry.Logpoint logPoint);
	
	

}
