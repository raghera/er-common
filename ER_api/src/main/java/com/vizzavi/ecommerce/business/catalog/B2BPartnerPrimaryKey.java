package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;

import javax.persistence.Column;

public class B2BPartnerPrimaryKey implements Serializable	{

	private static final long serialVersionUID = 234234234L;

	public B2BPartnerPrimaryKey() {
	}

	public B2BPartnerPrimaryKey(String partnerId, int countryId) {
		super();
		this.id = partnerId;
		this.countryId = countryId;
	}

	protected String  id;
	
	protected int countryId;

	@Column(name="PARTNER_ID")
	public String getPartnerId() {
		return id;
	}

	public void setPartnerId(String partnerId) {
		this.id = partnerId;
	}

	@Column(name="COUNTRY_OBJ_ID")
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + countryId;
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
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
		B2BPartnerPrimaryKey other = (B2BPartnerPrimaryKey) obj;
		if (countryId != other.countryId)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
