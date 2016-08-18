/**
 * 
 */
package com.vodafone.global.er.business.selfcare;

import java.io.Serializable;
import java.util.Date;

import com.vizzavi.ecommerce.business.catalog.PricePoint;

/**
 * @author hud
 * STKHREQ36 ER9
 *
 */
public class MicroServiceStatus implements  Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 268237412028721843L;
	
	
	//private CatalogService		mService = null;
	private java.util.Date		mExpiryTime = null;
	private double				mAccessDuration = -1;
	private boolean				mValid = false;
	private String				mServiceId = null;
	private String				mServicePricePointId = null;
	private String				mServiceName = null;
	
	
	public String getServicePricePointId() {
		return mServicePricePointId;
	}
	public void setServicePricePointId(String id) {
		mServicePricePointId = id;
	}
	public String getServiceName() {
		return mServiceName;
	}
	public void setServiceName(String serviceName) {
		mServiceName = serviceName;
	}
	public String getServiceId() {
		return mServiceId;
	}
	public void setServiceId(String serviceId) {
		mServiceId = serviceId;
	}
	
	public java.util.Date getExpiryTime() {
		return mExpiryTime;
	}
	public void setExpiryTime(Date date) {
		mExpiryTime = date;
	}
	
	public double getAccessDuration() {
		return mAccessDuration;
	}
	public void setAccessDuration(double accessDuration) {
		mAccessDuration = accessDuration;
	}
	
	public boolean isValid() {
		return mValid;
	}
	public void setValid(boolean b) {
		mValid = b;
	}
	
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// internal parameters
	private PricePoint			hServicePricePoint;
	public PricePoint getServicePricePoint() {
		return hServicePricePoint;
	}
	public void setServicePricePoint(PricePoint pp) {
		hServicePricePoint = pp;
	}

	private long				hSubscriptionId;
	public long getSubscriptionId() {
		return hSubscriptionId;
	}
	public void setSubscriptionId(long id) {
		hSubscriptionId = id;
	}
	
	
	// flag that tells if the micro subscription needs to be updated
	private static final int 	FLAG_UNCHANGED		= 0;
	private static final int	FLAG_DIRTY			= 1;
	private static final int	FLAG_NEW			= 2;
	private int hFlag = FLAG_UNCHANGED;
	public void setDirty() {
		hFlag = FLAG_DIRTY;
	}
	public boolean isDirty() {
		return hFlag == FLAG_DIRTY;
	}
	public void setNew() {
		hFlag = FLAG_NEW;
	}
	public boolean isNew() {
		return hFlag == FLAG_NEW;
	}
	public void reset() {
		hFlag = FLAG_UNCHANGED;
	}
	public boolean isUnchanged() {
		return hFlag == FLAG_UNCHANGED;
	}
	public int getFlag() {
		return hFlag;
	}
}
