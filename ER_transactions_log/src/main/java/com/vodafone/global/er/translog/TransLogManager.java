package com.vodafone.global.er.translog;

import java.util.List;
import java.util.Map;

public interface TransLogManager {
    
    public enum Attr {
        CUSTOMER_ID,
        ER_PACKAGE_ID, 
        ER_PRICEPOINT_ID,
        ER_RESPONSE_CODE,
        ER_RESPONSE_ERROR_DESCRIPTION, 
        ER_SERVICE_ID,
        ER_SUBSCRIPTION_ID, 
        ER_TX_ID,
        /**
         * an internal ER tracking id which is used to track the same request into the core and back out again.
         */
        ER_TX_LOG_ID,
        RESPONSE_PL,
        VF_EXT_BP_ID,
        VF_EXT_TRACE_ID,
        VF_INT_CALLER_ID,
        VF_INT_TRACE_ID,
        /**
		 * This header is used to track all systems/applications involved in a single transaction.  
		 * Each VF system/application will append its ID to the incoming tracking ID header received from the requesting system and 
		 * will propagate the updated header to VF internal systems only. If this header is not set in requests to the GIG, 
		 * it must be set by the GIG and propagated to VF internal systems only. For example:DPN.GIGER.DPS.ER.ERIF.ER.ERIF.ER.DPS.GIGER.DPN
		 * ER Core must append 'ER' to this attribute every time it receives a request.
		 */
        VF_INT_TRACK_ID, 
        REQUEST_PL, 
        REQUEST_NAME, 
        DI_REQUEST,
        //CR 2199 - ULF Logging
        EXTERNAL_REFERENCE_ID,
        ULF_SERVICE_NAME,
        VF_TRACE_TRANSACTION_ID,
        COUNTRY_CODE,
		SERVER_NAME,
		INBOUND_REQ_IP,
		INBOUND_REQ_PORT,
		INBOUND_REQ_URI,
        ERROR,
        ERROR_CODE,
        STATUS,
        PARTNER_ID, // Introduced as part of MQC 13807
        RESPONSE_TIME, // Introduced as part of MQC 13807
        DESTINATION, // Introduced as part of MQC 13807
        OUTBOUND_REQ_URL, // Introduced as part of MQC 13807
        ER_API_NAME,
        LOG_POINT,
        TX_START_TS,
        TX_COMPLETE_TS,
        TX_DURATION,
        TX_CLIENT_ID,
        REQUEST_TYPE
        ;
    }


    /**
     * Enumeration for the special use cases.
     * This will be used to set the context in the message to IF
     *
     */
    public enum Execution_Context
    {
    	NA("NA"),
    	UPSELL("UPSELL"),
    	ACTIVATE_SUSPENDED ("SUSPEND_TO_ACTIVE"),
    	INACTIVATE_SUSPENDED ("SUSPEND_TO_INACTIVE"), //CR 2081 - Usecase SUSPENDED to INACTIVE
    	//MQC 8098 - add for internal force purchase and express purchase flows
    	PURCHASE("PURCHASE"),
    	INACTIVATE_SUBSCRIPTION("INACTIVATE_SUBSCRIPTION"),
    	//MQC 9593 - add context CHANGE_MSISDN & TERMINATE_ACCOUNT
    	CHANGE_MSISDN("CHANGE_MSISDN"),
    	TERMINATE_ACCOUNT("TERMINATE_ACCOUNT"),
    	//JIRAET77 - add context for renew
    	RENEW_PURCHASE("RENEW_PURCHASE");
    	
    	String execution_context;
    	
    	public String getCurrentFlow()
    	{
    		return execution_context;
    	}
    	
    	Execution_Context(String execution_context)
    	{
    		this.execution_context = execution_context;
    	}
    }
    
	void addAttributeOnce(Attr attribute, String value);

	void addAttributeContext(Attr attribute, String value);

	//CR TBD
	void addExecutionContext(String execution_context);
	
	void addExecutionUseCase(Execution_Context execution_context);
	
	// CR1978 - Global-ER
	void addRequestHeader(String headername, String value);

	Map<String,String> getRequestHeaderMap();
	// CR1978 - ends
	/**
	 * 
	 * @param generateNewId - If this is a new request and a trans_log_id field needs to be
	 * generated then set this to true.  
	 * Most likely this is when a new incoming request is received and 
	 * it is being logged the first time.
	 * In all other cases this should be set to false.
	 * @return
	 */
	void logRequest(boolean generateNewId);

	void clean();

	/**
	 * log a response in the transaction log.  if emptyAfterOutput is set to true, it will then clear both the 'once' and 'context' attributes.  
	 * This should only be done if ER has finished processing the request and is responding to an external client.
	 * @param emptyAfterOutput
	 */
	void logResponse(boolean emptyAfterOutput);

    void emptyTranslogMDC();

	List<String> getLogMessageForJunit();
	
	/**
	 * looks first in the context, and if that's blank, gets it from the 'once' list
	 * @param attribute
	 * @return
	 */
	String getAttribute(Attr attribute);
	
	//CR TBD
	String getExecutionContext();
	
	TransLogManager.Execution_Context getExecutionUseCase();
	
	/** Called from a finally{} block, to check that a thread's cleaned-up properly. */
    void andFinally();

    boolean isTransLoggingOn();

    void setIsTransLoggingOn(boolean value);

    void setIsOutputPayload(boolean value);

    boolean isOutputPayload();


    void logClientData(TransLogData data);

    /**
     * generate a new ER_TX_LOG_ID value and add it to the context
     * @return the logId generated, or null if trans logging is disabled
     */
//	String generateErTxLogId();

	/**
	 * does the context have a ER_TX_LOG_ID value set?
	 * @return false if translogging is switched off, or the context has no ER_TX_LOG_ID set, true otherwise
	 */
	boolean hasErTxLogId();    
}
