package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Account spend limits
 * @see com.vizzavi.ecommerce.business.catalog.SpendLimits 
 */
public class SpendLimits implements Serializable {
	
	private static final long serialVersionUID = 5788917496921448134L;
	private double perDayLimit = -1.0;
	private double perMonthLimit = -1.0;
	private double perTxLimit = -1.0;
	private double cumulativeSpendDay = 0.0;
	private double cumulativeSpendMonth = 0.0;
	private Date lastUpdated = Calendar.getInstance().getTime();
	
	public SpendLimits() {}
	
	public SpendLimits(Locale locale) {
		lastUpdated = Calendar.getInstance(locale).getTime();
	}
	
	public SpendLimits(SpendLimits spendLimits) {
		this.perTxLimit = spendLimits.perTxLimit;
		this.perDayLimit = spendLimits.perDayLimit;
		this.perMonthLimit = spendLimits.perMonthLimit;
		this.cumulativeSpendDay = spendLimits.cumulativeSpendDay;
		this.cumulativeSpendMonth = spendLimits.cumulativeSpendMonth;
	}
	
	public SpendLimits(double perTxLimit, double perDayLimit, double perMonthLimit, double cumulativeSpendDay, double cumulativeSpendMonth, Date lastUpdated) {
		this.perTxLimit = perTxLimit;
		this.perDayLimit = perDayLimit;
		this.perMonthLimit = perMonthLimit;
		this.cumulativeSpendDay = cumulativeSpendDay;
		this.cumulativeSpendMonth = cumulativeSpendMonth;
		this.lastUpdated = lastUpdated;
	}
	
	public SpendLimits(com.vizzavi.ecommerce.business.catalog.SpendLimits spendLimits) {
		this.perTxLimit = spendLimits.getPerTxLimit();
		this.perDayLimit = spendLimits.getPerDayLimit();
		this.perMonthLimit = spendLimits.getPerMonthLimit();
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
	public double getPerTxLimit() {
		return perTxLimit;
	}
	public void setPerTxLimit(double perTxLimit) {
		this.perTxLimit = perTxLimit;
	}
	public double getCumulativeSpendDay() {
		return cumulativeSpendDay;
	}
	public void setCumulativeSpendDay(double cumulativeSpendDay) {
		this.cumulativeSpendDay = cumulativeSpendDay;
	}
	public double getCumulativeSpendMonth() {
		return cumulativeSpendMonth;
	}
	public void setCumulativeSpendMonth(double cumulativeSpendMonth) {
		this.cumulativeSpendMonth = cumulativeSpendMonth;
	}
	
	public Date getLastUpdated() {
		return lastUpdated;
	}
	
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public boolean isNotSet() {
		boolean isNotSet = false;
		if (perTxLimit == -1.0 && perDayLimit == -1.0 && perMonthLimit == -1.0) {
			return isNotSet = true;
		}
		return isNotSet;
	}
	
	public void resetCumulativeSpendDay() {
		cumulativeSpendDay = 0.0;
	}
	
	public void resetCumulativeSpendMonth() {
		cumulativeSpendMonth = 0.0;
	}
	
	public void subtractFromSpend(double amount) {
		cumulativeSpendDay = cumulativeSpendDay - amount;
		cumulativeSpendMonth = cumulativeSpendMonth - amount;
		setLastUpdatedToToday();
	}
	
	public void addToSpend(double amount) {
		cumulativeSpendDay = cumulativeSpendDay + amount;
		cumulativeSpendMonth = cumulativeSpendMonth + amount;
		setLastUpdatedToToday();
	}
	
	public void setLastUpdatedToToday() {
		lastUpdated = new Date();
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
		strBuf.append("\ncumulativeSpendDay: ").append(cumulativeSpendDay);
		strBuf.append("\ncumulativeSpendMonth: ").append(cumulativeSpendMonth);
		strBuf.append("\nlastUpdated: ").append(lastUpdated);
		
		return strBuf.toString();
	}
}
