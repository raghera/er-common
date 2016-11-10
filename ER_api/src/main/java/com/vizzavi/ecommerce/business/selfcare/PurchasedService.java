package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ER_PURCHASED_SERVICES")
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
    
//	  JIRA ET-331:part2 - USAGE_COUNT field removal from API and DB    
//    protected long usageCount ;
    
 
    protected boolean isProvisionOnUsage; // CR1209


    protected String serviceClass;
    
    
    protected Date lastProvisionUpdate; //CR1399
    
    
    protected Date updateTimeStamp;
    
   
    protected Integer countryId;
    
    @Column(name="COUNTRY_OBJ_ID")
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		if(countryId !=null)
		this.countryId = countryId;
	}
	
	@Column(name="UPDATE_TIME_STAMP")
	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	/**
	 * @return
	 */
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="purchservicesseq")
	@SequenceGenerator(name="purchservicesseq", sequenceName="ER_PURCHASED_SERVICES_SEQ", allocationSize=1/*00, initialValue = 10*/)
	@Id
	@Column(name="PURCHASED_SERVICE_OBJ_ID")
	public Long getId() {
		return mId;
	}

	/**
	 * @return
	 */
	@Column(name="PROVISIONING_TAG")
	public String getProvisioningTag() {
		return provisioningTag;
	}

	/**
	 * @return
	 */
	@Column(name="SERVICE_ID")
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @return
	 */
	@Column(name="STATUS_OBJ_ID")
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
	public void setStatus(Integer status) {
		if(status != null)
			mStatus = status;
	}

	/**
	 * @return
	 */
	@Column(name="PROV_STATUS_OBJ_ID")
	public int getProvStatus() {
		return mProvStatus;
	}

	/**
	 * @param integer
	 */
	public void setProvStatus(Integer provStatus) {
		if(provStatus != null)
			mProvStatus = provStatus;
	}
	
	public void setNonRefundDescription(String string) {
		nonRefundDescription = string;
	}

	/**
	 * @return
	 */
    @Column(name="NON_REFUND_DESCRIPTION")
	public String getNonRefundDescription() {
		return nonRefundDescription;
	}

//	JIRA ET-331:part2 - USAGE_COUNT field removal from API and DB
//    @Column(name="USAGE_COUNT")
//	public long getUsageCount() {
//		return usageCount;
//	}
//
//
//
//    public void setUsageCount(Long usageCount) {
//    	if(usageCount !=null)
//    		this.usageCount = usageCount;
//    }
//	
//	public void increaseCount(){
//		usageCount++ ;
//	}

	//CR 1209 add service class
	public void setServiceClass(String srvClass) {
		serviceClass = srvClass;
	}
    //CR 1209 add service class
    @Column(name="SERVICE_CLASS")
	public String getServiceClass() {
		return serviceClass;
	}
	
	public void setIsProvisionOnUsage(Boolean isProvisionOnUsage) {
		if(isProvisionOnUsage!=null)
			this.isProvisionOnUsage = isProvisionOnUsage.booleanValue();
	}
	//@Column(name="PROVISION_ON_USAGE")
	@Transient
	public boolean isProvisionOnUsage() {
		return isProvisionOnUsage;
	}
	
	@Column(name="PROVISION_ON_USAGE")
	boolean getIsProvisionOnUsage(){
		return isProvisionOnUsage();
	}
	
	/* CR1399 START */
	/**
	 * @return the lastProvisionUpdate
	 */
	@Column(name="LAST_PROVISION_UPDATE")
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
	

	protected Subscription subscription;
	
	@ManyToOne(optional=false,	targetEntity=Subscription.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)	
	@JoinColumn(name="subscription_obj_id")
	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

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