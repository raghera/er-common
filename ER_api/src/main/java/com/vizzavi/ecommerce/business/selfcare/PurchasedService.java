package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Date;

public class PurchasedService implements Serializable {

	private    static final long serialVersionUID = -1028198592237542051L;

    protected String provisioningTag;
    protected int mStatus = -1;
    protected int mProvStatus = -1;
    protected String serviceId;
    
    //@hud RFRFRF
    //ER 7 Compliant
    protected Long mId = new Long(-1);
    
    protected String nonRefundDescription;
    
    protected long usageCount ;
    
    protected boolean isProvisionOnUsage; // CR1209

    //CR 1209 add service class
    protected String serviceClass;
    
    protected Date lastProvisionUpdate; //CR1399
	
    
	/**
	 * @return
	 */
	public Long getId() {
		return mId;
	}

	/**
	 * @return
	 */
	public String getProvisioningTag() {
		return provisioningTag;
	}

	/**
	 * @return
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @return
	 */
	public int getStatus() {
		return mStatus;
	}

	/**
	 * @param long1
	 */
	public void setId(long id) {
		mId = new Long(id);
	}

	public void setId(Long id) {
		mId = id;
	}

	/**
	/**
	 * @param string
	 */
	public void setProvisioningTag(String string) {
		provisioningTag = string;
	}

	/**
	 * @param string
	 */
	public void setServiceId(String string) {
		serviceId = string;
	}

	/**
	 * @param integer
	 */
	public void setStatus(int status) {
		mStatus = status;
	}

	/**
	 * @return
	 */
	public int getProvStatus() {
		return mProvStatus;
	}

	/**
	 * @param integer
	 */
	public void setProvStatus(int provStatus) {
		mProvStatus = provStatus;
	}
	
	public void setNonRefundDescription(String string) {
		nonRefundDescription = string;
	}

	/**
	 * @return
	 */
	public String getNonRefundDescription() {
		return nonRefundDescription;
	}



	public long getUsageCount() {
		return usageCount;
	}



	public void setUsageCount(long usageCount) {
		this.usageCount = usageCount;
	}
	
	public void increaseCount(){
		usageCount++ ;
	}

	//CR 1209 add service class
	public void setServiceClass(String srvClass) {
		serviceClass = srvClass;
	}

	public String getServiceClass() {
		return serviceClass;
	}
	
	public void setIsProvisionOnUsage(boolean isProvisionOnUsage) {
		this.isProvisionOnUsage = isProvisionOnUsage;
	}
	
	public boolean isProvisionOnUsage() {
		return isProvisionOnUsage;
	}
	
	/* CR1399 START */
	/**
	 * @return the lastProvisionUpdate
	 */
	public Date getLastProvisionUpdate() {
		return lastProvisionUpdate;
	}

	/**
	 * @param lastProvisionUpdate the lastProvisionUpdate to set
	 */
	public void setLastProvisionUpdate(Date lastProvisionUpdate) {
		this.lastProvisionUpdate = lastProvisionUpdate;
	}
	/* CR1399 END */
    
	
	@Override
	public String toString() {
	    StringBuffer strBuf = new StringBuffer();
	    strBuf.append("id: " + mId);
	    strBuf.append(" ,serviceId: " + serviceId);
	    strBuf.append(" ,mStatus: " + mStatus);
	    strBuf.append(" ,mProvStatus: " + mProvStatus);
	    strBuf.append(" ,provisioningTag: " + provisioningTag);
		strBuf.append(" ,serviceClass: " + serviceClass);
	    strBuf.append(" ,isProvisionOnUsage: " + isProvisionOnUsage);
	    strBuf.append(" ,lastProvisionUpdate: " + lastProvisionUpdate);
	    return strBuf.toString();
	}
    
    
    
}
