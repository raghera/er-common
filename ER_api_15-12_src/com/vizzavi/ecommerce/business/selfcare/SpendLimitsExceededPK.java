package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SpendLimitsExceededPK implements Serializable{
	
	protected Long accountObjId;
	protected Long countryObjId;
	
	@Column(name="ACCOUNT_OBJ_ID",nullable=false)
	public Long getAccountObjId() {
		return accountObjId;
	}

	public void setAccountObjId(Long accountObjId) {
		this.accountObjId = accountObjId;
	}

	@Column(name="COUNTRY_OBJ_ID",nullable=false)
	public Long getCountryObjId() {
		return countryObjId;
	}

	public void setCountryObjId(Long countryObjId) {
		this.countryObjId = countryObjId;
	}

	public SpendLimitsExceededPK(){
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountObjId == null) ? 0 : accountObjId.hashCode());
		result = prime * result
				+ ((countryObjId == null) ? 0 : countryObjId.hashCode());
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
		SpendLimitsExceededPK other = (SpendLimitsExceededPK) obj;
		if (accountObjId == null) {
			if (other.accountObjId != null)
				return false;
		} else if (!accountObjId.equals(other.accountObjId))
			return false;
		if (countryObjId == null) {
			if (other.countryObjId != null)
				return false;
		} else if (!countryObjId.equals(other.countryObjId))
			return false;
		return true;
	}
	
	

}
