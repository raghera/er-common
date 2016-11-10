package com.vizzavi.ecommerce.business.catalog;

import com.vizzavi.ecommerce.business.selfcare.Subscription;

/**
 * CR1789
 * Wraps a <code>Subscription</code> and a <code>OneStepData</code> object together.
 * Was intended for sorting subscriptions only.
 * 
 * Created: 2 Nov 2010, 2010
 * @author Alex Romanin
 * 
 *
 */
public class OneStepAndSubscriptionData {
	
	private Subscription sub;
	private OneStepData data;
	
	public OneStepAndSubscriptionData(Subscription sub, OneStepData data){
		this.sub = sub;
		this.data = data;
	}

	/**
	 * @return the sub
	 */
	public Subscription getSub() {
		return sub;
	}

	/**
	 * @param sub the sub to set
	 */
	public void setSub(Subscription sub) {
		this.sub = sub;
	}

	/**
	 * @return the data
	 */
	public OneStepData getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(OneStepData data) {
		this.data = data;
	}

	
}
