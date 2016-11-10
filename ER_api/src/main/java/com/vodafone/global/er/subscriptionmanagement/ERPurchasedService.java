/*
 * Created on Feb 24, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.vodafone.global.er.subscriptionmanagement;

import java.util.Date;

import com.vizzavi.ecommerce.business.selfcare.PurchasedService;


public class ERPurchasedService extends PurchasedService implements DirtyMarker {

	private boolean dirty = false;
	private boolean newInstance = true;

	//seen in ER client, 12.10.4, running against head (25/4/13 ie 13.4.1) :
	//java.io.InvalidClassException: com.vodafone.global.er.subscriptionmanagement.ERPurchasedService; local class incompatible: stream classdesc serialVersionUID = -3063682119583834984, local class serialVersionUID = -1028198592237542051
	//so added this line
	private static final long serialVersionUID = -1028198592237542051l;
	
	public ERPurchasedService(PurchasedService ps){
		//new constructor required when separating this class from PurchasedService
		setProvisioningTag(ps.getProvisioningTag());
		setId(ps.getId());
		setNonRefundDescription(ps.getNonRefundDescription());
		setIsProvisionOnUsage(ps.isProvisionOnUsage());
		setLastProvisionUpdate(ps.getLastProvisionUpdate());
		setProvStatus(ps.getProvStatus());
		setServiceClass(ps.getServiceClass());
		setServiceId(ps.getServiceId());
		setStatus(ps.getStatus());
		
		setNew();
	}
	
	/**
	/**
	 * @param string
	 */
	@Override
	public void setProvisioningTag(String string) {
		super.setProvisioningTag(string);
		this.setDirty();
	}

	/**
	 * @param string
	 */
	@Override
	public void setServiceId(String string) {
		super.setServiceId(string);
		this.setDirty();
	}

	/**
	 * @param integer
	 */
	@Override
	public void setStatus(Integer status) {
		if(status != null)
			super.setStatus(status);
		setDirty();
	}

	/**
	 * @param integer
	 */
	@Override
	public void setProvStatus(Integer provStatus) {
		if(provStatus != null)
			super.setProvStatus(provStatus);
		setDirty();
	}
	
	@Override
	public void setNonRefundDescription(String string) {
		super.setNonRefundDescription(string);
		this.setDirty();
	}
	
//	JIRA ET-331:part2 - USAGE_COUNT field removal from API and DB	
//	@Override
//	public void increaseCount(){
//		super.increaseCount();
//		this.setDirty();
//	}

	//CR 1209 add service class
	@Override
	public void setServiceClass(String srvClass) {
		super.setServiceClass(srvClass);
		this.setDirty();
	}
	
	
	/**
	 * @param lastProvisionUpdate the lastProvisionUpdate to set
	 */
	@Override
	public void setLastProvisionUpdate(Date lastProvisionUpdate) {
		super.setLastProvisionUpdate(lastProvisionUpdate);
		this.setDirty();
	}
	
	
	
	
	
	
	
	

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setDirty()
	 */
	@Override
	public void setDirty() {
		this.dirty = true;
	}



	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return dirty;
	}



	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetDirty()
	 */
	@Override
	public void resetDirty() {
		dirty = false;
	}



	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setNew()
	 */
	@Override
	public void setNew() {
		newInstance = true;
	}



	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isNew()
	 */
	@Override
	public boolean isNew() {
		return newInstance;
	}



	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetNew()
	 */
	@Override
	public void resetNew() {
		newInstance = false;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public ERPurchasedService(String aServiceId,String aProvTag){
		this.provisioningTag = aProvTag;
		this.serviceId = aServiceId;
		setNew();    	
	}

	/* CR1209 START */
	/**
	 * 
	 * 
	 * @return
	 */ // copied from ERPurchasedService(String aServiceId,String aProvTag)

	public ERPurchasedService(String aServiceId,String aProvTag,boolean isProvisionOnUsage){
		this.provisioningTag = aProvTag;
		this.serviceId = aServiceId;
		this.isProvisionOnUsage = isProvisionOnUsage;
		setNew();    	
	}
	/* CR1209 END */
	/**
	 * 
	 * 
	 * @return
	 */
	public ERPurchasedService(){
		setNew();    	
	}


}
