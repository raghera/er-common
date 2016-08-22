package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="ER_OPTIONS")
public class Options implements Serializable	{

    private static final long	serialVersionUID	= 3299708651771836112l;
    
	public Options() {
	}

	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="optseq")
	@SequenceGenerator(name="optseq", sequenceName="ER_OPTIONS_SEQ", allocationSize=1/*00, initialValue = 10*/)
	@Id
	@Column(name="OPTION_OBJ_ID")
	protected Long mOptionIdLong;
	
	@Column(name="SUBSCRIPTION_OBJ_ID")
	protected Long mSubscriptionId;
	
	@Column(name="PAYMENT_OBJ_ID")
	protected Long mPaymentId;
	
	@Column(name="CHARGING_METHOD")
	protected Integer chargingMethod;
	
	@Column(name="DURATION")
	protected Integer duration;
		
	@Column(name="PROMO_CODE")
	protected String promoCode;
	
	@Column(name="USER_GROUP")
	protected String userGroup;
	
	@Column(name="TIME_STAMP")
	protected Date timeStamp = null;
	
	@Column(name="PAYMENT_TYPE")
	protected Integer paymentType;
	
	@Column(name="SUPPLIER_ID")
	protected String suppliedId;
	
	@Column(name="PREMIUM_LEVEL")
	protected Integer premiumLevel;
	
	@Column(name="CHANNEL")
	protected Integer channel;
	
	@Column(name="ACCESS_DEVICE")
	protected Integer accessDevice;
	
	//@Column(name="OPTS_LOCK_ID")
	//protected Integer optsLockId;
	
	@Column(name="TARIFF")
	protected String tariff;
	
	@Column(name="UPDATE_TIME_STAMP")
	protected Date updateTimeStamp = null;
	
	@Column(name="opts_lock_id")
	//@Version
	Long lockId;

	public Long getLockId() {
		return lockId;
	}

	public void setLockId(Long lockId) {
		this.lockId = lockId;
	}

	public long getOptionId() {
		return (mOptionIdLong!=null?mOptionIdLong:0);
	}

	public void setOptionId(long mOptionIdLong) {
		this.mOptionIdLong = mOptionIdLong;
	}

	public long getSubscriptionId() {
		return (mSubscriptionId!=null?mSubscriptionId:0);
	}

	public void setSubscriptionId(long mSubscriptionId) {
		this.mSubscriptionId = mSubscriptionId;
	}

	public long getPaymentId() {
		return (mPaymentId!=null?mPaymentId:0);
	}

	public void setPaymentId(long mPaymentId) {
		this.mPaymentId = mPaymentId;
	}

	public Integer getChargingMethod() {
		return chargingMethod;
	}

	public void setChargingMethod(Integer chargingMethod) {
		this.chargingMethod = chargingMethod;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public String getSuppliedId() {
		return suppliedId;
	}

	public void setSuppliedId(String suppliedId) {
		this.suppliedId = suppliedId;
	}

	public Integer getPremiumLevel() {
		return premiumLevel;
	}

	public void setPremiumLevel(Integer premiumLevel) {
		this.premiumLevel = premiumLevel;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Integer getAccessDevice() {
		return accessDevice;
	}

	public void setAccessDevice(Integer accessDevice) {
		this.accessDevice = accessDevice;
	}

//	public Integer getOptsLockId() {
//		return optsLockId;
//	}
//
//	public void setOptsLockId(Integer optsLockId) {
//		this.optsLockId = optsLockId;
//	}

	public String getTariff() {
		return tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (mOptionIdLong ^ (mOptionIdLong >>> 32));
		result = prime * result + (int) (mPaymentId ^ (mPaymentId >>> 32));
		result = prime * result
				+ (int) (mSubscriptionId ^ (mSubscriptionId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Options other = (Options) obj;
		if (mOptionIdLong != other.mOptionIdLong)
			return false;
		if (mPaymentId != other.mPaymentId)
			return false;
		if (mSubscriptionId != other.mSubscriptionId)
			return false;
		return true;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SUBSCRIPTION_OBJ_ID",insertable=false,updatable=false)
	protected Subscription subscription;


	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	
	

}
