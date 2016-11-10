package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Account spend limits
 * @see com.vizzavi.ecommerce.business.catalog.SpendLimits 
 */
@Entity
@Table(name="ER_ACCOUNT_SPEND_LIMITS")
public class SpendLimits implements Serializable {
	
	private static final long serialVersionUID = 5788917496921448134L;
	
	@Id
	@Column(name="ACCOUNT_OBJ_ID")
	private Long id;	
	@Column(name="PER_DAY_LIMIT")
	private Double perDayLimit = -1.0;
	@Column(name="PER_MONTH_LIMIT")
	private Double perMonthLimit = -1.0;
	@Column(name="PER_TX_LIMIT")
	private Double perTxLimit = -1.0;
	@Column(name="SPEND_TOTAL_DAY")
	private Double cumulativeSpendDay = 0.0;
	@Column(name="SPEND_TOTAL_MONTH")
	private Double cumulativeSpendMonth = 0.0;
	@Column(name="LAST_UPDATED")
	private Date lastUpdated = Calendar.getInstance().getTime();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ACCOUNT_OBJ_ID",insertable=false,updatable=false)
	private BasicAccount basicAccount;
	
	@Column(name="COUNTRY_OBJ_ID")
    protected Integer countryId;
    
    
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	
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
	
	public Long getId()	{
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Double getPerDayLimit() {
		if (perDayLimit != null) 
			return perDayLimit;
		else return -1.0;
	}
	public void setPerDayLimit(Double perDayLimit) {
		this.perDayLimit = perDayLimit;
	}
	
	public Double getPerMonthLimit() {
		if (perMonthLimit != null)
			return perMonthLimit;
		else return -1.0;
	}
	
	public void setPerMonthLimit(Double perMonthLimit) {
		this.perMonthLimit = perMonthLimit;
	}
	
	public Double getPerTxLimit() {
		if (perTxLimit != null)
			return perTxLimit;
		else return -1.0;
	}
	public void setPerTxLimit(Double perTxLimit) {
		this.perTxLimit = perTxLimit;
	}
	
	public Double getCumulativeSpendDay() {
		if (cumulativeSpendDay != null)
			return cumulativeSpendDay;
		else return 0.0;
	}
	
	public void setCumulativeSpendDay(Double cumulativeSpendDay) {
		this.cumulativeSpendDay = cumulativeSpendDay;
	}
	
	public Double getCumulativeSpendMonth() {
		if (cumulativeSpendMonth != null)
			return cumulativeSpendMonth;
		else return 0.0;
	}
	
	public void setCumulativeSpendMonth(Double cumulativeSpendMonth) {
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
	


	public  BasicAccount getBasicAccount()	{
		return basicAccount;
	}

	public void setBasicAccount(BasicAccount acc)	{
		this.basicAccount = acc;
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
