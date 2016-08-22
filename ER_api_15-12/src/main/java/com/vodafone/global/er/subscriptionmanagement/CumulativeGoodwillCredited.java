package com.vodafone.global.er.subscriptionmanagement;

import java.util.Date;

public class CumulativeGoodwillCredited {
	
	private double creditedToday = 0.0;
	private double creditedThisMonth = 0.0;
	private Date lastUpdated = new Date();
	
	public CumulativeGoodwillCredited() {
		// set to defaults
	}
	
	public CumulativeGoodwillCredited(double creditedToday, double creditedThisMonth, Date lastUpdated) {
		setCreditedToday(creditedToday);
		setCreditedThisMonth(creditedThisMonth);
		setLastUpdated(lastUpdated);
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
		if (lastUpdated != null ) {
			this.lastUpdated = lastUpdated;
		} else {
			setLastUpdatedToToday();
		}
	}
	
	public void resetCreditedToday() {
		creditedToday = 0.0;
		setLastUpdatedToToday();
	}
	
	public void resetCreditedThisMonth() {
		creditedThisMonth = 0.0;
		setLastUpdatedToToday();
	}
	
	public void setLastUpdatedToToday() {
		lastUpdated = new Date();
	}
	
	public void resetAll() {
		creditedToday = 0.0;
		creditedThisMonth = 0.0;
		lastUpdated = new Date();
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CumulativeGoodwillCredited: ");
		strBuf.append("\n creditedToday: ");strBuf.append(creditedToday);
		strBuf.append("\n creditedThisMonth: ");strBuf.append(creditedThisMonth);
		strBuf.append("\n lastUpdated: ");strBuf.append(lastUpdated.toString());
		return strBuf.toString();
	}
}
