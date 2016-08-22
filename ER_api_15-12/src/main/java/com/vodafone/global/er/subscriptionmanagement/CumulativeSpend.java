package com.vodafone.global.er.subscriptionmanagement;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CumulativeSpend {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CumulativeSpend.class);
	
	private double spendToday = 0.0;
	private double spendThisMonth = 0.0;
	private Date lastUpdated = new Date();
	private String partnerId; // added so we can tell which partner this cumulative spend belongs to.
	
	public CumulativeSpend() {
		// set to defaults
	}
	
	public CumulativeSpend(double spendToday, double spendThisMonth, Date lastUpdated) {
		setSpendToday(spendToday);
		setSpendThisMonth(spendThisMonth);
		setLastUpdated(lastUpdated);
	}
	
	public CumulativeSpend(String partnerId, double spendToday, double spendThisMonth, Date lastUpdated) {
		this.partnerId = partnerId;
		setSpendToday(spendToday);
		setSpendThisMonth(spendThisMonth);
		setLastUpdated(lastUpdated);
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public void addToSpend(double amount) {
		logger.debug("enter addToSpend({})", amount);
		
		spendToday = spendToday + amount;
		spendThisMonth = spendThisMonth + amount;
		setLastUpdatedToToday();
		
		logger.debug("exit addToSpend({})", amount);
	}
	
	public void subtractFromSpend(double amount) {
		logger.debug("enter subtractFromSpend({})", amount);
		
		spendToday = spendToday - amount;
		spendThisMonth = spendThisMonth - amount;
		setLastUpdatedToToday();
		
		logger.debug("exit subtractFromSpend({})", amount);
	}
	
	public double getSpendToday() {
		return spendToday;
	}
	
	public void setSpendToday(double spendToday) {
		this.spendToday = spendToday;
	}
	
	public double getSpendThisMonth() {
		return spendThisMonth;
	}
	
	public void setSpendThisMonth(double spendThisMonth) {
		this.spendThisMonth = spendThisMonth;
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
	
	public void resetSpendToday() {
		spendToday = 0.0;
		setLastUpdatedToToday();
	}
	
	public void resetSpendThisMonth() {
		spendThisMonth = 0.0;
		setLastUpdatedToToday();
	}
	
	public void setLastUpdatedToToday() {
		lastUpdated = new Date();
	}
	
	public void resetAll() {
		spendToday = 0.0;
		spendThisMonth = 0.0;
		lastUpdated = new Date();
	}
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CumulativeSpend: ");
		strBuf.append("\n partnerId: ");strBuf.append(partnerId);
		strBuf.append("\n spendToday: ");strBuf.append(spendToday);
		strBuf.append("\n spendThisMonth: ");strBuf.append(spendThisMonth);
		strBuf.append("\n lastUpdated: ");strBuf.append(lastUpdated.toString());
		return strBuf.toString();
	}

}
