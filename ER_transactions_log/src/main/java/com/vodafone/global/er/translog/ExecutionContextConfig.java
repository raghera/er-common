package com.vodafone.global.er.translog;

/**
 * This class holds the spring configuration for the map between the ER CORE api
 * and the text that wil appear in the context element to IF
 * This is used in the DelegateHelper. The spring config file is execution-config.xml
 */
import java.util.Map;


public class ExecutionContextConfig {
	private Map<String, String> ExecutionConfigMap;
	
	public void setExecutionConfigMap(Map<String, String> ExecutionConfigMap) {
		this.ExecutionConfigMap = ExecutionConfigMap;
	}

	public String lookup(String operation) {
	    String executionContext = ExecutionConfigMap.get(operation);
	    
	    return executionContext;

	}
}
