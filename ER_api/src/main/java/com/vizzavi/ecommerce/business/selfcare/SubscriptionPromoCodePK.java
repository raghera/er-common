package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ER_PROMOTION_HISTORY database table (it's a composite key).
 * 
 * @see {@link SubscriptionPromoCode}
 */
@Embeddable
public class SubscriptionPromoCodePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name="SUBSCRIPTION_ID")
	private long subscriptionId;

	@Column(name="PACKAGE_ID")
	private String packageId;

	@Column(name="ACCOUNT_ID", insertable=false, updatable=false)
	private long accountId;

	public SubscriptionPromoCodePK() {
	}
	
	/**
	 * eg TRIAL
	 * @return
	 */
	public String getPromoCode() {
		return this.type;
	}
	public void setPromoCode(String code) {
		this.type = code;
	}
	public long getSubscriptionId() {
		return this.subscriptionId;
	}
	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getPackageId() {
		return this.packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public long getAccountId() {
		return this.accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubscriptionPromoCodePK)) {
			return false;
		}
		SubscriptionPromoCodePK castOther = (SubscriptionPromoCodePK)other;
		return 
			this.type.equals(castOther.type)
			&& (this.subscriptionId == castOther.subscriptionId)
			&& this.packageId.equals(castOther.packageId)
			&& (this.accountId == castOther.accountId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.type.hashCode();
		hash = hash * prime + ((int) (this.subscriptionId ^ (this.subscriptionId >>> 32)));
		hash = hash * prime + this.packageId.hashCode();
		hash = hash * prime + ((int) (this.accountId ^ (this.accountId >>> 32)));
		
		return hash;
	}
}