package com.vodafone.global.er.translog;

import com.vodafone.config.ConfigProvider;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;


/**
 *
 * @author jim myau
 *
 */
public class TransLog4jManagerImpl implements TransLogManager{
    /**a logger for debugging this class*/
    private static Logger logger = LoggerFactory.getLogger(TransLog4jManagerImpl.class);
    /**The actual logger which will write the transaction log*/
    private static final Logger transactionLogger = LoggerFactory.getLogger("com.vodafone.transaction.logging");

    private final List<String> logMessageForJunit = new ArrayList<String>();

    private boolean trxjunit_testing_on = false;// for junit mode

    public String junitlogTx_id="";

    // CR1978 - Global-ER
    /**
     * Define all http common headers to filter them out when storing to forward to IF
     */
    public static final Set<String> COMMON_HTTP_HEADERS = new HashSet<String>(Arrays.asList(
            "content-type",
            "connection",
            "host",
            "accept-language",
            "accept",
            "origin",
            "user-agent",
            "accept-charset",
            "cache-control",
            "content-length",
            "x-requested-with",
            "accept-encoding"
    ));
    // CR1978 - ends
    private final ThreadLocal<TransLogModel> transLogModelThreadLocal = new ThreadLocal<TransLogModel>() {
        @Override
        protected TransLogModel initialValue() {
            return new TransLogModelImpl();
        }
    };

    public TransLog4jManagerImpl() {
        // get value from env.properties
        trxjunit_testing_on = ConfigProvider.getPropertyAsBoolean("translog.junit.mode", false);
    }

    /**
     * Add an attribute which should be logged in the next log entry, but not in any future entries
     * @param key
     * @param value
     */
    @Override
    public void addAttributeOnce(Attr key, String value) {
        transLogModelThreadLocal.get().getAttributesOnce().put(key, value);
    }

    /**
     * Add an attribute which should endure throughout the whole transaction.
     * @param key
     * @param value
     */
    @Override
    public void addAttributeContext(Attr key, String value) {
        transLogModelThreadLocal.get().getAttributesConText().put(key, value);
    }

    // CR1978 - Global-ER
    /**
     * Add the request header.
     * Does not include common http headers
     * @param headername
     * @param value
     */
    @Override
    public void addRequestHeader(String headername, String value) {
        // We are not adding in common http headers
        if (!COMMON_HTTP_HEADERS.contains(headername.toLowerCase())) {
            transLogModelThreadLocal.get().getRequestHeadersMap().put(headername, value);
        }
    }

    @Override
    public Map<String, String> getRequestHeaderMap() {
        return transLogModelThreadLocal.get().getRequestHeadersMap();
    }
    // CR1978 - ends

    /**
     * {@inheritDoc}
     */
    @Override
    public void logRequest(boolean generateNewId)
    {
//    	if (generateNewId)
//    		generateErTxLogId();
        log();

    }

//    @Override
//    public String generateErTxLogId()	{
//    	String rv=null;
//		if ( transLogModelThreadLocal.get().isTransLoggingOn() )        {
//			//MQC9519 - added log message and rewritten for performance
//			rv = new Date().getTime() + "-" + UUID.randomUUID().toString() +(trxjunit_testing_on?"junit":"") ;
//			transLogModelThreadLocal.get().getAttributesConText().put( Attr.ER_TX_LOG_ID, rv );
//        	logger.warn("generating new ER_TX_LOG_ID: {}", rv);
//            transLogModelThreadLocal.get().setCounter(transLogModelThreadLocal.get().getCounter() + 1);
//        }
//        return rv;
//    }


    /**
     * write the actual log entry, then clear 'ONCE' attributes
     */
    private void log() {
        if ( transLogModelThreadLocal.get().isTransLoggingOn() ) {

            TransLogModel translog = transLogModelThreadLocal.get();
            for (Entry<Attr, String> entry : translog.getAttributesConText().entrySet()) {
                MDC.put(entry.getKey().toString(), ((entry.getValue() == null)? "":entry.getValue()));
            }
            for (Entry<Attr, String> entry : translog.getAttributesOnce().entrySet()) {
                MDC.put(entry.getKey().toString(), ((entry.getValue() == null)? "":entry.getValue()));
            }
            transactionLogger.info(""); // don't really need a msg here

            // TODO MDC maybe conflic with existing MDC using in the project
            for (Entry<Attr, String> entry : translog.getAttributesConText().entrySet()) {
                MDC.remove(entry.getKey().toString());
            }
            for (Entry<Attr, String> entry : translog.getAttributesOnce().entrySet()) {
                MDC.remove(entry.getKey().toString());
            }
            translog.getAttributesOnce().clear();
        }

    }

    @Override
    public void clean()
    {
        if ( transLogModelThreadLocal.get().isTransLoggingOn() ) {
            transLogModelThreadLocal.remove();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logResponse( boolean eraseAfterOutput ) {
        if ( transLogModelThreadLocal.get().isTransLoggingOn() ) {
            TransLogModel translog = transLogModelThreadLocal.get();


            log();

            translog.setCounter(translog.getCounter() - 1);

            // erase me
            if (eraseAfterOutput) {
                emptyTranslogMDC();
//				for (Entry<Attr, String> entry : translog.getAttributesOnce().entrySet()) {
//					MDC.remove(entry.getKey().toString());
//				}
//				transLogModelThreadLocal.remove();
//				for (Entry<Attr, String> entry : translog.getAttributesConText().entrySet()) {
//					MDC.remove(entry.getKey().toString());
//				}
            }
        }
    }

    @Override
    public void emptyTranslogMDC() {
        TransLogModel translog = transLogModelThreadLocal.get();
        // erase me
        for (Entry<Attr, String> entry : translog.getAttributesOnce().entrySet()) {
            MDC.remove(entry.getKey().toString());
        }
        transLogModelThreadLocal.remove();
        for (Entry<Attr, String> entry : translog.getAttributesConText().entrySet()) {
            MDC.remove(entry.getKey().toString());
        }
    }

    @Override
    public List<String> getLogMessageForJunit() {
        return logMessageForJunit;
    }


    public List<String> getList(){
        return logMessageForJunit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAttribute(Attr attribute)
    {
        String ret = "";

        if ( transLogModelThreadLocal.get().isTransLoggingOn() ) {

            TransLogModel translog = transLogModelThreadLocal.get();
            ret = translog.getAttributesConText().get(attribute);
            if(ret==null || ret ==""){
                ret = translog.getAttributesOnce().get(attribute);
            }
        }

        return ret;
    }


    @Override
    public void andFinally()
    {
        int counter = transLogModelThreadLocal.get().getCounter();

        if ( counter != 0 )
        {
            logger.error("Thread["+Thread.currentThread().getName()+':'+Thread.currentThread().getId()+"] counter is not zero ["+counter+"]!?  Will clean up anyway");
            clean();
        }
    }

    //MQC 6971
    @Override
    public boolean isTransLoggingOn(){
        return transLogModelThreadLocal.get().isTransLoggingOn();
    }

    @Override
    public void setIsTransLoggingOn(boolean value) {
        transLogModelThreadLocal.get().setIsTransloggingOn(value);
    }

    //CR 2085
    @Override
    public String getExecutionContext()
    {
        return transLogModelThreadLocal.get().getExecutionContext();
    }

    @Override
    public void addExecutionContext(String execution_context)
    {
        transLogModelThreadLocal.get().setExecutionContext(execution_context);
    }

    @Override
    public void addExecutionUseCase(TransLogManager.Execution_Context context)
    {
        transLogModelThreadLocal.get().setExecutionUseCase(context);
    }

    @Override
    public TransLogManager.Execution_Context getExecutionUseCase()
    {
        return transLogModelThreadLocal.get().getExecutionUseCase();
    }

//    @Override
//    public boolean isDecouplingRequest() {
//    	
//    	return transLogModelThreadLocal.get().isDecouplingRequest();
//    	
//    }
//    @Override
//    public void setDecouplingRequest(boolean isDecouplingRequest) {
//    	
//    	transLogModelThreadLocal.get().setIsDecouplingRequest(isDecouplingRequest);
//    	
//    }

    //CR 2085    

    /**
     * CR 2085 - Helper class for trans log data.
     * Clients create the log data and call this method to log.
     */
    @Override
    public void logClientData(TransLogData data) {

        if ( data.getVfExtTraceId() != null) {
            addAttributeContext( Attr.VF_EXT_TRACE_ID, data.getVfExtTraceId());
        }

        if ( data.getVfExtBPId() != null ) {
            addAttributeContext( Attr.VF_EXT_BP_ID, data.getVfExtBPId());
        }

        if ( data.getVfIntTraceId() != null ) {
            addAttributeContext( Attr.VF_INT_TRACE_ID, data.getVfIntTraceId());
        }

        if ( data.getVfIntTrackId() != null ) {
            addAttributeContext( Attr.VF_INT_TRACK_ID, data.getVfIntTrackId());
        }

        if ( data.getVfIntCallerId() != null ) {
            addAttributeContext( Attr.VF_INT_CALLER_ID, data.getVfIntCallerId());
        }

        if(data.getErrorDesciption() != null)
        {
            addAttributeContext( Attr.ER_RESPONSE_ERROR_DESCRIPTION, data.getVfIntCallerId());
        }

//        if(data.isDataIsRequest())
//        {
        if(data.getRequest() != null)
            addAttributeOnce( Attr.REQUEST_PL, data.getRequest());

        if(data.getRequestName() != null)
            addAttributeOnce( Attr.REQUEST_NAME, data.getRequestName());

//        	logRequest( false );
//        	logRequest( data.isGenerateNewTransLogId() );
//        }
//        else
//        {
        if(data.getResponse() != null)
            addAttributeOnce( Attr.RESPONSE_PL, data.getResponse());

        if(data.getResponseCode() != null)
            addAttributeOnce( Attr.ER_RESPONSE_CODE, data.getResponseCode() );

        logResponse( false );
//            logResponse( data.isGenerateNewTransLogId() );

//        }
    }

    @Override
    public boolean hasErTxLogId() {
        if (!transLogModelThreadLocal.get().isTransLoggingOn())
            return false;
        return StringUtils.isNotBlank(getAttribute(Attr.ER_TX_LOG_ID));
    }
}
