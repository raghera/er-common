package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;

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

import com.vizzavi.ecommerce.business.common.ChargingResource;

@Entity
@Table(name="er_package_balances")
public class ResourceBalance implements Serializable	{
	private static final long serialVersionUID = -6123222737770569297L;


	protected long balanceObjId;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="resseq")
	@SequenceGenerator(name="resseq", sequenceName="ER_PACKAGE_BALANCES_SEQ", allocationSize=1/*00, initialValue = 10*/)
	@Id
	@Column(name="BALANCE_OBJ_ID")
	long getBalanceObjId(){
		return balanceObjId;
	}

	void setBalanceObjId(long id ){
		this.balanceObjId  =id;
	}


		
	protected ChargingResource mRes;

	
	/*public ChargingResource getRes() {
		return mRes;
	}

	public void setRes(ChargingResource mRes) {
		this.mRes = mRes;
	}*/

	
	protected double mBalance;

	
	protected int mThreshold;


	//@hud RFRFRF
	/**
	 * 
	 */
	
	protected String mSubscriptionId = null;
	
	//@Column(name="SUBSCRIPTION_OBJ_ID")
	
	protected long mSubscriptionIdLong = -1;

	//@Column(name="RESOURCE_OBJ_ID")
	//@Transient
	protected long resourceObjId;

//	public long getResourceObjId() {
//		return resourceObjId;
//	}
//
//	public void setResourceObjId(long resourceObjId) {
//		this.resourceObjId = resourceObjId;
//	}

		
	protected String mPackageId;


	protected String mOldestSubscriptionId = null;

	public ResourceBalance(){
	}

	public ResourceBalance(ResourceBalance resBalances)
	{
		this.mRes = resBalances.mRes;
		this.mBalance = resBalances.mBalance;
		this.mSubscriptionId = resBalances.mSubscriptionId;
		this.mPackageId = resBalances.mPackageId;
		this.mThreshold=resBalances.mThreshold;
	}

	public ResourceBalance(ChargingResource res, double bal)
	{
		mRes = res;
		mBalance = bal;
	}

	public ResourceBalance(ChargingResource res, double bal,int threshold)
	{
		mRes = res;
		mBalance = bal;
		mThreshold = threshold;
	}

	@ManyToOne(optional=false,	targetEntity=ChargingResource.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)	
	@JoinColumn(name="resource_obj_id")	
	public ChargingResource getResource()
	{
		return mRes;
	}
	
	public void setResource(ChargingResource resource){
		mRes=resource;
	}

	@Column(name="AMOUNT")
	public double getBalance()
	{
		return mBalance;
	}

	@Column(name="THRESHOLD")
	public int getThreshold()
	{
		return mThreshold;
	}

	/**
	 * 
	 * @return
	 */
	@Transient
	 public String getSubscriptionId()
	{
		if (mSubscriptionId == null) {
			if (mSubscriptionIdLong > 0) {
				mSubscriptionId = Long.toString(mSubscriptionIdLong);
			}
		}
		return mSubscriptionId;
	}
	@Transient
	 public long getSubscriptionIdLong() {
		 return mSubscriptionIdLong;
	 }
	 public void setSubscriptionIdLong(long id) {
		 mSubscriptionIdLong = id;
	 }    
	 @Transient
	 public String getPackageId()
	 {
		 return mPackageId;
	 }

	 public String toString()
	 {
		 StringBuffer buf = new StringBuffer();
		 buf.append("{");
		 buf.append(mRes.toString()).append("=").append(mBalance).append(',');
		 buf.append("subscriptionId").append(mSubscriptionId);
		 buf.append(",threshold=").append(mThreshold);
		 buf.append("}");
		 return buf.toString();
	 }
	 @Transient
	 public String getOldestSubscriptionId(){
		 return mOldestSubscriptionId;
	 }

	 /**
    CR-1791 - setter to change existing resource balance
	  */
	 public void setBalance(Double bal) {
		 if(bal != null)
			 mBalance = bal.doubleValue();
	 }


	 public void setThreshold(Integer threshold)
	 {
		 if(threshold!=null)
			 this.mThreshold = threshold;
	 }


	 protected Subscription subscription;

	 @ManyToOne(optional=false,	targetEntity=Subscription.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)	
	 @JoinColumn(name="subscription_obj_id")
	 public Subscription getSubscription() {
		 return subscription;
	 }

	 public void setSubscription(Subscription subscription) {
		 this.subscription = subscription;
	 }

}
