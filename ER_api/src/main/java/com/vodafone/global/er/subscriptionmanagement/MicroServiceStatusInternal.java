package com.vodafone.global.er.subscriptionmanagement;

import java.util.Map;

import com.vodafone.global.er.business.selfcare.MicroServiceStatus;


/**
 * @author hud
 * STKHREQ36
 *
 */
public class MicroServiceStatusInternal 
{
	private ERSubscription			mSubscription;
	private Map<String, MicroServiceStatus>						mMicroServiceUsageMap;
	
	
	public ERSubscription getSubscription() {
		return mSubscription;
	}
	public void setSubscription(ERSubscription sub) {
		mSubscription = sub;
	}
	
	public Map<String, MicroServiceStatus> getMicroServiceUsageMap() {
		return mMicroServiceUsageMap;
	}
	public void setMicroServiceUsageMap(Map<String, MicroServiceStatus> map) {
		mMicroServiceUsageMap = map;
	}
	
}
