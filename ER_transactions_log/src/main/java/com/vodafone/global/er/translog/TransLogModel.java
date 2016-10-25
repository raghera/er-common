package com.vodafone.global.er.translog;

import com.vodafone.global.er.translog.TransLogManager.Attr;

import java.util.Map;

public interface TransLogModel {

	public abstract int getCounter();

	public abstract void setCounter(int counter);

	public abstract Map<Attr, String> getAttributesConText();

	// CR1978 - Global-ER
	public Map<String, String> getRequestHeadersMap();
	// CR1978 - ends
	public abstract void setAttributesConText(
			Map<Attr, String> attributesConText);

	public abstract Map<Attr, String> getAttributesOnce();

	public abstract void setAttributesOnce(Map<Attr, String> attributesOnce);
	
	//MQC 6791 Start
	/**
	 * Returns the value of the translog.logging.mode property
	 * to determine if a thread should be logged or not
	 * @return
	 */
	public abstract boolean isTransLoggingOn();
	//MQC 6791 Start

	public void setIsTransloggingOn(boolean value);

	boolean isOutputPayload();

	void setIsOutputPayload(boolean value);

	public abstract void setExecutionContext(String context);

	public abstract String getExecutionContext();	
	
	public abstract void setExecutionUseCase(TransLogManager.Execution_Context context);

	public abstract TransLogManager.Execution_Context getExecutionUseCase();	
	
//	public boolean isDecouplingRequest();
//	
//	public void setIsDecouplingRequest(boolean isDecouplingRequest);
}