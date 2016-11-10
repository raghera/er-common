package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;
import java.util.List;

/**
 * CR2210 - MPay Rate Card
 * 
 * This class holds the details of all the rate card partners associated to a service. 
 */
public class RateCardPartners implements Serializable {

	private static final long serialVersionUID = 8325027487343301278L;

	/**
	 * This list holds the details of the rate card partners.
	 */
	protected List<Partner> mRateCardPartners;

	/**
	 * @return the mRateCardPartners
	 */
	public List<Partner> getRateCardPartners() {
		return mRateCardPartners;
	}

	/**
	 * @param mRateCardPartners the mRateCardPartners to set
	 */
	public void setRateCardPartners(List<Partner> mPartners) {
		this.mRateCardPartners = mPartners;
	}

}


