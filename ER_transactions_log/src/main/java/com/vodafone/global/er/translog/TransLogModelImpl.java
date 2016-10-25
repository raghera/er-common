package com.vodafone.global.er.translog;

import com.vodafone.global.er.translog.TransLogManager.Attr;

import java.util.HashMap;
import java.util.Map;

public class TransLogModelImpl implements TransLogModel {
	private Map<Attr, String> attributesContext = new HashMap<Attr, String>();
	private Map<Attr, String> attributesOnce = new HashMap<Attr, String>();
	//private Map<Attr, String> attributesULFContext = new HashMap<, String>();

    boolean isTransloggingOn = false;
    boolean isOutputPayload = false;

	private TransLogManager.Execution_Context executionUseCase;
	private String executionContext;

	// CR1978 - Global-ER
	private final Map<String, String> requestHeadersMap = new HashMap<String, String>();
	// CR1978 - ends
	
	// CR1978 - Global-ER

	//MQC 8035 Start
//	private boolean isDecouplingRequest;
	
	//MQC 6971 - Start
	// private boolean trx_loggin_on = false;
	//MQC 6971 - End
	// CR1978 - ends
	
	private int counter = 0;
	
	public TransLogModelImpl() {
		
		/*
		 * Note this is only retrieved once for each TransLogModel
		 * i.e. each Thread
		 */
		// CR1978 - Global-ER
		// trx_loggin_on = ConfigProvider.getPropertyAsBoolean(TransLogConstants.PROPERTY_TRANSLOG_LOGGING_ON, false);
		// CR1978 - Global-ER
		
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.translog.TransLogModel#getCounter()
	 */
	@Override
	public final int getCounter() {
		return counter;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.translog.TransLogModel#setCounter(int)
	 */
	@Override
	public final void setCounter(int counter) {
		this.counter = counter;
		
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.translog.TransLogModel#getAttributesConText()
	 */
	@Override
	public final Map<Attr, String> getAttributesConText() {
		return attributesContext;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.translog.TransLogModel#setAttributesConText(java.util.Map)
	 */
	@Override
	public final void setAttributesConText(Map<Attr, String> attributesConText) {
		this.attributesContext = attributesConText;
	}

	// CR1978 - Global-ER
	@Override
	public Map<String, String> getRequestHeadersMap() {
		return requestHeadersMap;
	}
	// CR1978 - ends
	/* (non-Javadoc)
	 * @see com.vodafone.global.er.translog.TransLogModel#getAttributesOnce()
	 */
	@Override
	public final Map<Attr, String> getAttributesOnce() {
		return attributesOnce;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.translog.TransLogModel#setAttributesOnce(java.util.Map)
	 */
	@Override
	public final void setAttributesOnce(Map<Attr, String> attributesOnce) {
		this.attributesOnce = attributesOnce;
	}

	//MQC 6971 Start
	@Override
	public boolean isTransLoggingOn() {
		// CR1978 - Global-ER
		// return trx_loggin_on;
//		return ConfigProvider.getPropertyAsBoolean(TransLogConstants.PROPERTY_TRANSLOG_LOGGING_ON, false);
        return isTransloggingOn;

		// CR1978 - ends
	}

	@Override
	public void setIsTransloggingOn(boolean value) {
        isTransloggingOn = value;
    }

    @Override
	public boolean isOutputPayload() {
		return isOutputPayload;
	}

	@Override
	public void setIsOutputPayload(boolean value) {
		this.isOutputPayload = value;
	}

	//CR TBD
	@Override
	public  void setExecutionContext(String context)
	{
		this.executionContext = context;
	}

	@Override
	public  String getExecutionContext()
	{
		return this.executionContext;
	}
	
	@Override
	public void setExecutionUseCase(TransLogManager.Execution_Context context)
	{
		this.executionUseCase = context;
	}

	@Override
	public TransLogManager.Execution_Context getExecutionUseCase()
	{
		return this.executionUseCase;
	}
	//CR TBD
	
//	//MQC 8035 Start
//	public boolean isDecouplingRequest() {
//		
//		return isDecouplingRequest;
//	}
//	
//	public void setIsDecouplingRequest(boolean isDecouplingRequest) {
//		
//		this.isDecouplingRequest = isDecouplingRequest;
//	}

	//MQC 8035 End
}
