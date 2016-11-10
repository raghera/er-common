package com.vizzavi.ecommerce.business.selfcare;

import java.util.ArrayList;
import java.util.List;

import com.vizzavi.ecommerce.business.catalog.PricePoint;

/**
 * represents a service offer for a serviceid for a user.
 * a service offer can either be a list of subscriptions if the user already an active subscription(s) to the serviceid OR
 * a list of pricepoint purchase options for the serviceid if the user has no active subscriptions for the serviceid
 *
 */
public class ServiceOffer {

	protected String serviceId;
	
	protected List<PricePoint> pricePoints;
	
	protected List<Subscription> subscriptions;
	
	
	public String getServiceId() {
		return serviceId;
	}
	
	public void setServiceId(String id) {
		this.serviceId = id;
	}
	
	public List<PricePoint> getPricePoints() {
		return pricePoints;
	}
	
	public void setPricePoints(List<PricePoint> ppts) {
		this.pricePoints = ppts;
	}
	
	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}
	
	public void setSubscriptions(List<Subscription> subs) {
		this.subscriptions = subs;
	}
	
	public void addSubscription(Subscription sub) {
		if (subscriptions == null) {
			subscriptions = new ArrayList<Subscription>();
		}
		subscriptions.add(sub);
	}
	
	public void addPricePoint(PricePoint ppt) {
		if (pricePoints == null) {
			pricePoints = new ArrayList<PricePoint>();
		}
		pricePoints.add(ppt);
	}
}
