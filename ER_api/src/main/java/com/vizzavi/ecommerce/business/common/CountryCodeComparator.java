package com.vizzavi.ecommerce.business.common;

import java.util.Comparator;

/**
 * CR1503 - Multi Price plans
 * This class sorts the countries based on the country code
 */
public class CountryCodeComparator implements Comparator<CountryCode> {

	/**
	 * sorts countries based on the country code
	 * @param CountryCode
	 * @param CountryCode
	 * @return integer
	 */
	public int compare(CountryCode c1, CountryCode c2) {
		return c1.getCountryCode().compareTo(c2.getCountryCode());
	}

}
