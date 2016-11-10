package com.vizzavi.ecommerce.business.catalog;

/**
 * MPAY replacement.  Partner spend limits.
 * @see com.vizzavi.ecommerce.business.selfcare.SpendLimits 
 */
public class SpendLimits implements java.io.Serializable {
	
	protected double perTxLimit = -1.0;
	protected double perDayLimit = -1.0;
	protected double perMonthLimit = -1.0;
	
	public SpendLimits() {}
	
	/**
	 * Used by CatalogAPIUnmarshaller.
	 * @param per_tx_limit
	 * @param per_day_limit
	 * @param per_month_limit
	 */
	public SpendLimits(double per_tx_limit, double per_day_limit,
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
	
	public boolean hasDefaultValues() {
		if (perTxLimit == -1.0 
				&& perDayLimit == -1.0 
				&& perMonthLimit == -1.0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer("SpendLimits: ");
		strBuf.append("\nperTxLimit: ").append(perTxLimit);
		strBuf.append("\nperDayLimit: ").append(perDayLimit);
		strBuf.append("\nperMonthLimit: ").append(perMonthLimit);
		return strBuf.toString();
	}
}
