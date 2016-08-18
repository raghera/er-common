package com.vizzavi.ecommerce.business.catalog;

import java.util.Date;

/**
 * MPAY replacement.  Goodwill credit limits.
 *
 */
public class GoodwillCreditLimits {
	
	protected double perTxLimit = -1.0;
	protected double perDayLimit = -1.0;
	protected double perMonthLimit = -1.0;
	private double creditedToday = 0.0;
	private double creditedThisMonth = 0.0;
	private Date lastUpdated;
	
	public GoodwillCreditLimits() {}
	
	/**
	 * Used by CatalogAPIUnmarshaller.
	 * @param per_tx_limit
	 * @param per_day_limit
	 * @param per_month_limit
	 */
	public GoodwillCreditLimits(double per_tx_limit, double per_day_limit,
			double per_month_limit) {
		this.perTxLimit = per_tx_limit;
		this.perDayLimit = per_day_limit;
		this.perMonthLimit = per_month_limit;
	}

	public double getPerTxLimit() {
		return perTxLimit;
	}
	public void setPerTxLimit(double perTxLimit) {
		this.perTxLimit = perTxLimit;
	}
	public double getPerDayLimit() {
		return perDayLimit;
	}
	public void setPerDayLimit(double perDayLimit) {
		this.perDayLimit = perDayLimit;
	}
	public double getPerMonthLimit() {
		return perMonthLimit;
	}
	public void setPerMonthLimit(double perMonthLimit) {
		this.perMonthLimit = perMonthLimit;
	}
	
	public double getCreditedToday() {
		return creditedToday;
	}

	public void setCreditedToday(double creditedToday) {
		this.creditedToday = creditedToday;
	}

	public double getCreditedThisMonth() {
		return creditedThisMonth;
	}

	public void setCreditedThisMonth(double creditedThisMonth) {
		this.creditedThisMonth = creditedThisMonth;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public boolean hasDefaultValues() {
		if (perTxLimit == -1.0 
				&& perDayLimit == -1.0 
				&& perMonthLimit == -1.0) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("GoodwillCreditLimits: ");
		strBuf.append("\nperTxLimit: ");strBuf.append(perTxLimit);
		strBuf.append("\nperDayLimit: ");strBuf.append(perDayLimit);
		strBuf.append("\nperMonthLimit: ");strBuf.append(perMonthLimit);
		return strBuf.toString();
	}
}
