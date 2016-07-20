/**
 * 
 */
package com.vodafone.global.er.ulf.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vodafone.application.logging.ULFEntry;
import com.vodafone.application.logging.ULFLogger;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.translog.TransLogManager;
import com.vodafone.global.er.translog.TransLogManager.Attr;
import com.vodafone.global.er.ulf.constants.ERULFConstants;
import com.vodafone.global.er.ulf.constants.ERULFConstants.ComponentName;
import com.vodafone.global.er.ulf.service.ERULFLogDataManager;

/**
 * Manages the data specific for ULF
 * Also interacts with common ULF code in the APPLICATION_app_common module  
 * 
 * @author Ravi Aghera
 *
 */
public class ERULFLogDataManagerImpl implements ERULFLogDataManager {

	private static Logger logger = LoggerFactory.getLogger(ERULFLogDataManagerImpl.class);	
	private static final Logger ulfLogger = LoggerFactory.getLogger(ERULFConstants.ER_ULF_LOGGER_NAME);
	
	//If true output the payload on ulf log outputs
	private static Boolean outputPayload = null; 
	private static Boolean ulfFlag = null;
	private static Boolean transLogMode = null;
	private static String ulfComponentName = null;
	
	public ERULFLogDataManagerImpl() {
		outputPayload = ConfigProvider.getPropertyAsBoolean("ulf.payload.on", true);
		ulfFlag = ConfigProvider.getPropertyAsBoolean("ulf.logging.on", false);
		transLogMode = ConfigProvider.getPropertyAsBoolean("translog.logging.mode", false);
		ulfComponentName = ConfigProvider.getProperty("ulf.component.name", ComponentName.ER_CORE.toString());
	}
	
	private void logULFRequest(final String message) {

		logger.debug("log ULF request message ");
		ulfLogger.error(message);
		
	}
	
	private boolean getUlfModeOn() {
		
		//Pull these out from ConfigProvider each time in case they have been changed e.g. for testing
		ulfFlag = ConfigProvider.getPropertyAsBoolean("ulf.logging.on", false);
		transLogMode = ConfigProvider.getPropertyAsBoolean("translog.logging.mode", false);

		//Currently need both flags true - TODO merge both translog / ulf and merge both
		if (ulfFlag && transLogMode) {  
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void logULFRequestIn(final TransLogManager transLogManager, final ULFEntry.Logpoint logPoint) {
		
		if(!getUlfModeOn()) {
			return;
		}
		
		// Changed the order of fields as part of MQC 13803
		ULFEntry.Builder builder = new ULFEntry.Builder()
		.timestamp(new Date())
		.transactionId( transLogManager.getAttribute( Attr.VF_TRACE_TRANSACTION_ID) )
		.service( transLogManager.getAttribute(Attr.REQUEST_NAME)  == null   ? "":transLogManager.getAttribute(Attr.REQUEST_NAME))
		.externalTraceId(  (transLogManager.getAttribute(Attr.VF_EXT_TRACE_ID) == null) ? "":transLogManager.getAttribute(Attr.VF_EXT_TRACE_ID) )
		.externalReferenceId( (transLogManager.getAttribute(Attr.EXTERNAL_REFERENCE_ID) == null) ? "":transLogManager.getAttribute(Attr.EXTERNAL_REFERENCE_ID))
		.usecaseId( transLogManager.getAttribute( Attr.VF_EXT_BP_ID) == null   ? "":transLogManager.getAttribute(Attr.VF_EXT_BP_ID) )
		.msisdn( transLogManager.getAttribute(Attr.CUSTOMER_ID) == null   ? "":transLogManager.getAttribute(Attr.CUSTOMER_ID) )
		.component(ulfComponentName) 
		.destination("")//An incoming request does not have a destination
		.countryCode( transLogManager.getAttribute(Attr.COUNTRY_CODE) )
		.status("ok") //Incoming Request is always ok at this stage
		.errorCode("")
		.error("")
		.callerId( transLogManager.getAttribute( Attr.VF_INT_CALLER_ID ) == null   ? "":transLogManager.getAttribute(Attr.VF_INT_CALLER_ID) ) //Should be set in Translogging Section
		.inboundRequestIp( transLogManager.getAttribute( Attr.INBOUND_REQ_IP ) == null   ? "":transLogManager.getAttribute(Attr.INBOUND_REQ_IP) )
		.inboundRequestPort(transLogManager.getAttribute( Attr.INBOUND_REQ_PORT ) == null   ? "":transLogManager.getAttribute(Attr.INBOUND_REQ_PORT))
		.inboundRequestUri( transLogManager.getAttribute( Attr.INBOUND_REQ_URI ) == null   ? "":transLogManager.getAttribute(Attr.INBOUND_REQ_URI ) )
		.outboundUrl("") //No outbound url here
		.serverName( getServerName() )
		.messageSize( transLogManager.getAttribute( Attr.REQUEST_PL) == null ? "" :  String.valueOf( (transLogManager.getAttribute( Attr.REQUEST_PL).getBytes().length) ) )
		.logpoint( logPoint.toString() )
		.partner(transLogManager.getAttribute(Attr.PARTNER_ID)  == null   ? "":transLogManager.getAttribute(Attr.PARTNER_ID)) // MQC 13807 more information in ULF
		.serviceId(transLogManager.getAttribute(Attr.ER_SERVICE_ID)  == null   ? "":transLogManager.getAttribute(Attr.ER_SERVICE_ID)) // MQC 13807 more information in ULF
		.packageId(transLogManager.getAttribute(Attr.ER_PACKAGE_ID)  == null   ? "":transLogManager.getAttribute(Attr.ER_PACKAGE_ID)) // MQC 13807 more information in ULF
		.payload(transLogManager.getAttribute( Attr.REQUEST_PL) == null || !outputPayload   ? "" : transLogManager.getAttribute(Attr.REQUEST_PL) )
		;
		
		final ULFEntry entry = builder.build();
		final String ulfLogStr = ULFLogger.toString(entry);
		
		logULFRequest(ulfLogStr);
		
	}

	@Override
	public void logULFRequestOut(final TransLogManager transLogManager, final ULFEntry.Logpoint logPoint) {
		
		if(!getUlfModeOn()) {
			return;
		}
		
		// Changed the order of fields as part of MQC 13803
		ULFEntry.Builder builder = new ULFEntry.Builder()
		.timestamp(new Date())
		.transactionId( transLogManager.getAttribute( Attr.VF_TRACE_TRANSACTION_ID) ) //EMPTY
		.service( transLogManager.getAttribute(Attr.REQUEST_NAME)  == null   ? "":transLogManager.getAttribute(Attr.REQUEST_NAME))
		.serviceType( transLogManager.getAttribute(Attr.ULF_SERVICE_NAME)  == null   ? "":transLogManager.getAttribute(Attr.ULF_SERVICE_NAME))
		.externalTraceId(  (transLogManager.getAttribute(Attr.VF_EXT_TRACE_ID) == null) ? "":transLogManager.getAttribute(Attr.VF_EXT_TRACE_ID) )
		.externalReferenceId( (transLogManager.getAttribute(Attr.EXTERNAL_REFERENCE_ID) == null) ? "":transLogManager.getAttribute(Attr.EXTERNAL_REFERENCE_ID)) 
		.usecaseId( transLogManager.getAttribute( Attr.VF_EXT_BP_ID) == null   ? "":transLogManager.getAttribute(Attr.VF_EXT_BP_ID) )
		.msisdn( transLogManager.getAttribute(Attr.CUSTOMER_ID) == null   ? "":transLogManager.getAttribute(Attr.CUSTOMER_ID) )
		.component(ulfComponentName) 
		.destination(transLogManager.getAttribute(Attr.DESTINATION)  == null   ? "":transLogManager.getAttribute(Attr.DESTINATION)) // MQC 13807 more information in ULF //Only populate if going to a component not generating ULF logs - E.g. DB calls
		.countryCode( transLogManager.getAttribute(Attr.COUNTRY_CODE)  == null   ? "":transLogManager.getAttribute(Attr.COUNTRY_CODE) )
		.status("ok")
		.errorCode("")
		.error("")
		.callerId(ulfComponentName) 
		.inboundRequestIp( "" )
		.inboundRequestPort("")
		.inboundRequestUri("")
		.outboundUrl("") //TODO - Add outbound URL here
		.serverName( getServerName() )
		.messageSize( Integer.toString( transLogManager.getAttribute(Attr.REQUEST_PL).length() ) )
		.logpoint( ULFEntry.Logpoint.REQUEST_OUT.toString() )
		.partner(transLogManager.getAttribute(Attr.PARTNER_ID)  == null   ? "":transLogManager.getAttribute(Attr.PARTNER_ID)) // MQC 13807 more information in ULF
		.serviceId(transLogManager.getAttribute(Attr.ER_SERVICE_ID)  == null   ? "":transLogManager.getAttribute(Attr.ER_SERVICE_ID)) // MQC 13807 more information in ULF
		.outboundUrl(transLogManager.getAttribute(Attr.OUTBOUND_REQ_URL)  == null   ? "":transLogManager.getAttribute(Attr.OUTBOUND_REQ_URL)) // MQC 13807 more information in ULF
		.packageId(transLogManager.getAttribute(Attr.ER_PACKAGE_ID)  == null   ? "":transLogManager.getAttribute(Attr.ER_PACKAGE_ID)) // MQC 13807 more information in ULF
		.payload(transLogManager.getAttribute( Attr.REQUEST_PL) == null || !outputPayload  ? "":transLogManager.getAttribute(Attr.REQUEST_PL) )
		;
		
		final ULFEntry entry = builder.build();

		final String ulfLogStr = ULFLogger.toString(entry);
		
		logULFRequest(ulfLogStr);
		
	}
	
	@Override
	public void logULFResponseOut(final TransLogManager transLogManager, final ULFEntry.Logpoint logPoint) {
		
		if(!getUlfModeOn()) {
			return;
		}

		//These should all be set at this point
		//The error fields used for ULFF must be ERROR & ERROR_CODE which are contained within the transLogManager object.
		//Apart from the above two error fields, there are two additional fields which are related to errors but they shouldn't be used here:
		//ER_RESPONSE_CODE & ER_RESPONSE_ERROR_DESCRIPTION.
		final String error = transLogManager.getAttribute( Attr.ERROR) == null   ? "":transLogManager.getAttribute(Attr.ERROR);
		final String errorCode = transLogManager.getAttribute( Attr.ERROR_CODE) == null   ? "":transLogManager.getAttribute(Attr.ERROR_CODE);
		final String status = transLogManager.getAttribute( Attr.STATUS) == null   ? "":transLogManager.getAttribute(Attr.STATUS);
		
		// Changed the order of fields as part of MQC 13803
		ULFEntry.Builder builder = new ULFEntry.Builder()
		.timestamp(new Date())
		.transactionId( transLogManager.getAttribute(Attr.VF_TRACE_TRANSACTION_ID) )
		.service( transLogManager.getAttribute(Attr.REQUEST_NAME)  == null   ? "":transLogManager.getAttribute(Attr.REQUEST_NAME))
		.externalTraceId(  (transLogManager.getAttribute(Attr.VF_EXT_TRACE_ID) == null) ? "":transLogManager.getAttribute(Attr.VF_EXT_TRACE_ID) )
		.externalReferenceId( (transLogManager.getAttribute(Attr.EXTERNAL_REFERENCE_ID) == null) ? "":transLogManager.getAttribute(Attr.EXTERNAL_REFERENCE_ID))
		.usecaseId( transLogManager.getAttribute( Attr.VF_EXT_BP_ID) == null   ? "":transLogManager.getAttribute(Attr.VF_EXT_BP_ID) )
		.msisdn( transLogManager.getAttribute(Attr.CUSTOMER_ID) == null   ? "":transLogManager.getAttribute(Attr.CUSTOMER_ID) )
		.component(ulfComponentName) 
		.destination("")//Only populate if going to a component not generating ULF logs - E.g. DB calls
		.countryCode( transLogManager.getAttribute(Attr.COUNTRY_CODE) )
		.status( status )
		.errorCode( errorCode )
		.error( error )
		.callerId( transLogManager.getAttribute( Attr.VF_INT_CALLER_ID ) == null   ? "":transLogManager.getAttribute(Attr.VF_INT_CALLER_ID) )
		.inboundRequestIp("") 
		.inboundRequestPort("")
		.inboundRequestUri("")
		.outboundUrl("")
		.serverName( getServerName() )
		//.messageSize( Integer.toString(transLogManager.getAttribute(Attr.RESPONSE_PL).getBytes().length) )
		.messageSize( Integer.toString(transLogManager.getAttribute(Attr.RESPONSE_PL)!=null?(transLogManager.getAttribute(Attr.RESPONSE_PL).getBytes().length):"".length() ))
		.logpoint( ULFEntry.Logpoint.RESPONSE_OUT.toString() )
		.partner(transLogManager.getAttribute(Attr.PARTNER_ID)  == null   ? "":transLogManager.getAttribute(Attr.PARTNER_ID)) // MQC 13807 more information in ULF
		.serviceId(transLogManager.getAttribute(Attr.ER_SERVICE_ID)  == null   ? "":transLogManager.getAttribute(Attr.ER_SERVICE_ID)) // MQC 13807 more information in ULF
		.responseTime(transLogManager.getAttribute(Attr.RESPONSE_TIME)  == null   ? "":transLogManager.getAttribute(Attr.RESPONSE_TIME)) // MQC 13807 more information in ULF		
		.packageId(transLogManager.getAttribute(Attr.ER_PACKAGE_ID)  == null   ? "":transLogManager.getAttribute(Attr.ER_PACKAGE_ID)) // MQC 13807 more information in ULF
		.payload(transLogManager.getAttribute( Attr.RESPONSE_PL) == null || !outputPayload ? "":transLogManager.getAttribute(Attr.RESPONSE_PL) ) //TODO this needs to be optional
		;
		
		final ULFEntry entry = builder.build();

		final String ulfLogStr = ULFLogger.toString(entry);
		
		logULFRequest(ulfLogStr);
		
		
	}
	
	@Override
	public void logULFResponseIn(final TransLogManager transLogManager, final ULFEntry.Logpoint logPoint) {
		
		if(!getUlfModeOn()) {
			return;
		}
	
		/*
		 * The error fields used for ULFF must be ERROR & ERROR_CODE which are contained within the transLogManager object.
		 * Apart from the above two error fields, there are two additional fields which are related to errors but they shouldn't be used here:
		 * ER_RESPONSE_CODE & ER_RESPONSE_ERROR_DESCRIPTION.
		final String errorCode = (transLogManager.getAttribute(Attr.ER_RESPONSE_CODE) == null) ? "":transLogManager.getAttribute(Attr.ER_RESPONSE_CODE);
		final String errorDescription = (transLogManager.getAttribute(Attr.ER_RESPONSE_ERROR_DESCRIPTION) == null) ? "":transLogManager.getAttribute(Attr.ER_RESPONSE_ERROR_DESCRIPTION);
		*/
		// ET-142 - ULFF tweaking changes begin
		final String errorCode = (transLogManager.getAttribute(Attr.ERROR_CODE) == null) ? "":transLogManager.getAttribute(Attr.ERROR_CODE);
		final String errorDescription = (transLogManager.getAttribute(Attr.ERROR) == null) ? "":transLogManager.getAttribute(Attr.ERROR);
		// ET-142 - ULFF tweaking changes end
		
		// MQC 13807 more information in ULF
			String status = "ok";
		if(StringUtils.isNotBlank(transLogManager.getAttribute(Attr.ER_RESPONSE_CODE)) && !transLogManager.getAttribute(Attr.ER_RESPONSE_CODE).contains("OK") ) {
			status = "error";
		}
		
		String erifStatus = transLogManager.getAttribute(Attr.STATUS) == null   ? "":transLogManager.getAttribute(Attr.STATUS);
		
		// Changed the order of fields as part of MQC 13803
		ULFEntry.Builder builder = new ULFEntry.Builder()
		.timestamp(new Date())
		.transactionId( transLogManager.getAttribute(Attr.VF_TRACE_TRANSACTION_ID) )
		.service( transLogManager.getAttribute(Attr.REQUEST_NAME)  == null   ? "":transLogManager.getAttribute(Attr.REQUEST_NAME))
		.serviceType( transLogManager.getAttribute(Attr.ULF_SERVICE_NAME)  == null   ? "":transLogManager.getAttribute(Attr.ULF_SERVICE_NAME))
		.externalTraceId(  (transLogManager.getAttribute(Attr.VF_EXT_TRACE_ID) == null) ? "":transLogManager.getAttribute(Attr.VF_EXT_TRACE_ID) )
		.externalReferenceId( (transLogManager.getAttribute(Attr.EXTERNAL_REFERENCE_ID) == null) ? "":transLogManager.getAttribute(Attr.EXTERNAL_REFERENCE_ID))
		.usecaseId( transLogManager.getAttribute( Attr.VF_EXT_BP_ID) == null   ? "":transLogManager.getAttribute(Attr.VF_EXT_BP_ID) )
		.msisdn( transLogManager.getAttribute(Attr.CUSTOMER_ID) == null   ? "":transLogManager.getAttribute(Attr.CUSTOMER_ID) )
		.component(ulfComponentName) 
		.destination("")//Only populate if going to a component not generating ULF logs - E.g. DB calls
		.countryCode( transLogManager.getAttribute(Attr.COUNTRY_CODE) )
		.status(status)
		.errorCode(errorCode)
		.error(errorDescription)
		.callerId(ulfComponentName)
		.inboundRequestIp("") 
		.inboundRequestPort("")
		.inboundRequestUri("")
		.outboundUrl("") //No outbound url here
		.serverName( getServerName() )
		.messageSize(transLogManager.getAttribute(Attr.RESPONSE_PL)!=null? Integer.toString(transLogManager.getAttribute(Attr.RESPONSE_PL).getBytes().length):"" )
		.logpoint( ULFEntry.Logpoint.RESPONSE_IN.toString() )
		.erifStatus(erifStatus) // MQC 13807 more information in ULF
		.partner(transLogManager.getAttribute(Attr.PARTNER_ID)  == null   ? "":transLogManager.getAttribute(Attr.PARTNER_ID)) // MQC 13807 more information in ULF
		.serviceId(transLogManager.getAttribute(Attr.ER_SERVICE_ID)  == null   ? "":transLogManager.getAttribute(Attr.ER_SERVICE_ID)) // MQC 13807 more information in ULF
		.responseTime(transLogManager.getAttribute(Attr.RESPONSE_TIME)  == null   ? "":transLogManager.getAttribute(Attr.RESPONSE_TIME)) // MQC 13807 more information in ULF		
		.packageId(transLogManager.getAttribute(Attr.ER_PACKAGE_ID)  == null   ? "":transLogManager.getAttribute(Attr.ER_PACKAGE_ID)) // MQC 13807 more information in ULF
		.payload(transLogManager.getAttribute( Attr.RESPONSE_PL) == null || !outputPayload  ? "":transLogManager.getAttribute(Attr.RESPONSE_PL) ) //TODO this needs to be optional
		;
		
		final ULFEntry entry = builder.build();

		final String ulfLogStr = ULFLogger.toString(entry);
		
		logULFRequest( ulfLogStr );
		
	}
	
	private static String getServerName() {

		String serverName = "";
		try {
			serverName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			logger.error("[Not fatal] Could not find hostname for ULF Logging .... returning empty String", e);
		}
		
		return serverName;
		
	}


	
	/*
	 * Values to be logged
	 * 
	 * timestamp
	 * transaction-id == VF_INT_TRACE_ID - vf-trace-transaction-id *
	 * service
	 * external-trace-id 
	 * external-reference-id
	 * usecase-id
	 * msisdn *
	 * component
	 * destination
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
	 * partner
	 * service_id
	 * package_id
	 * payload
	 * 
	 */

}
