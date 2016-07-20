package com.vodafone.global.er.translog;

import java.util.Map;

/**
 * This class is a transfer class for TransLog data. This class will be populated by an 
 * application and provided to the trans log to log data
 * @author 
 *
 */
public class TransLogData {
	private String vfExtTraceId;
	private String vfExtBPId;
	private String vfIntTraceId;
	private String vfIntTrackId;
	private String vfIntCallerId;
	private String responseCode;
	private String request;
	private String response;
	private String requestName;
	private String errorDesciption;
	
	//MQC 7397
	/**
	 * Set to true if you need a new Id generated
	 * e.g. when a new incoming request is received and
	 * needs logging
	 */
	private boolean generateNewTransLogId = false;
	
	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	boolean dataIsRequest;
	
	public boolean isDataIsRequest() {
		return dataIsRequest;
	}

	public void setDataIsRequest(boolean dataIsRequest) {
		this.dataIsRequest = dataIsRequest;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public TransLogData()
	{
		
	}
	
	public String getVfExtTraceId() {
		return vfExtTraceId;
	}

	public void setVfExtTraceId(String vfExtTraceId) {
		this.vfExtTraceId = vfExtTraceId;
	}

	public String getVfExtBPId() {
		return vfExtBPId;
	}

	public void setVfExtBPId(String vfExtBPId) {
		this.vfExtBPId = vfExtBPId;
	}

	public String getVfIntTraceId() {
		return vfIntTraceId;
	}

	public void setVfIntTraceId(String vfIntTraceId) {
		this.vfIntTraceId = vfIntTraceId;
	}

	public String getVfIntTrackId() {
		return vfIntTrackId;
	}

	public void setVfIntTrackId(String vfIntTrackId) {
		this.vfIntTrackId = vfIntTrackId;
	}

	public String getVfIntCallerId() {
		return vfIntCallerId;
	}

	public void setVfIntCallerId(String vfIntCallerId) {
		this.vfIntCallerId = vfIntCallerId;
	}

	public String getErrorDesciption() {
		return errorDesciption;
	}

	public void setErrorDesciption(String errorDesciption) {
		this.errorDesciption = errorDesciption;
	}


	//MQC 7397 Start
	public boolean isGenerateNewTransLogId() {
		return generateNewTransLogId;
	}
	
	public void setGenerateNewTransLogId(boolean generateNewTransLogId) {
		this.generateNewTransLogId = generateNewTransLogId;
	}
	//MQC 7397 End	
	
	/**
	 * Helper method to set the request data. All the client has to do is call this with the header map
	 * If any extra headers are required simply extend this
	 * @param headerMap
	 * @param request
	 * @param trackId
	 */
	public void setRequestData(Map<String, String> headerMap, String request, String trackId)
	{
        String requestName = headerMap.get("x-vf-request-class");
        
        setRequestName(requestName);
        
        //add request payload
        setRequest(request);
        
        //add various, specific headers:
        String extBPID = (headerMap.get("VF_EXT_BP_ID" ) != null) ? headerMap.get("VF_EXT_BP_ID" ) : headerMap.get("vf_ext_bp_id" );
        setVfExtBPId(extBPID);

        String extTraceID = (headerMap.get("VF_EXT_TRACE_ID" ) != null) ? headerMap.get("VF_EXT_TRACE_ID" ) : headerMap.get("vf_ext_trace_id" );        
        setVfExtTraceId(extTraceID);
        
        String extCallerID = (headerMap.get("VF_INT_CALLER_ID" ) != null) ? headerMap.get("VF_INT_CALLER_ID" ) : headerMap.get("vf_int_caller_id" );                
        setVfIntCallerId(extCallerID);
        
        String intTraceID = (headerMap.get("VF_INT_TRACE_ID" ) != null) ? headerMap.get("VF_INT_TRACE_ID" ) : headerMap.get("vf_int_trace_id" );                
        setVfIntTraceId(intTraceID); 
        
        String intTrackID = (headerMap.get("VF_INT_TRACK_ID" ) != null) ? headerMap.get("VF_INT_TRACK_ID" ) : headerMap.get("vf_int_track_id" );                
        
        if(intTrackID != null && trackId != null)
        {
        	intTrackID = intTrackID + "." + trackId;
        	headerMap.put("VF_INT_TRACK_ID", intTrackID);
        }
        
        setVfIntTrackId(intTrackID);       
        
		setDataIsRequest(true);
		
	}
	
	/**
	 * Helper for the response. Simply extend this if additional headers required.
	 * @param response
	 * @param status
	 */
	public void setResponse(String response, String status)
	{
        setResponse(response);
        setResponseCode(status);
		setDataIsRequest(false);		
	}
	
}
