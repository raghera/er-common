package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ER_GOODWILL_CREDIT_LIMITS")
public class GoodWillCredits implements Serializable{
	
	protected Long perMonthLimit;
	protected Long perTxnLimit;
	protected Long perDayLimit;
	
	protected Long creditedTotalDay;
	protected Long creditedTotalMonth;
	
	protected Date lastUpdated;

	protected GoodWillCreditsPK id;
	
	@EmbeddedId
	public GoodWillCreditsPK getId() {
		return id;
	}

	public void setId(GoodWillCreditsPK id) {
		this.id = id;
	}

	@Column(name="PER_MONTH_LIMIT")
	public Long getPerMonthLimit() {
		return perMonthLimit;
	}

	public void setPerMonthLimit(Long perMonthLimit) {
		this.perMonthLimit = perMonthLimit;
	}

	@Column(name="PER_TX_LIMIT")
	public Long getPerTxnLimit() {
		return perTxnLimit;
	}

	public void setPerTxnLimit(Long perTxnLimit) {
		this.perTxnLimit = perTxnLimit;
	}

	@Column(name="PER_DAY_LIMIT")
	public Long getPerDayLimit() {
		return perDayLimit;
	}

	public void setPerDayLimit(Long perDayLimit) {
		this.perDayLimit = perDayLimit;
	}

	@Column(name="CREDITED_TOTAL_DAY")
	public Long getCreditedTotalDay() {
		return creditedTotalDay;
	}

	public void setCreditedTotalDay(Long creditedTotalDay) {
		this.creditedTotalDay = creditedTotalDay;
	}

	@Column(name="CREDITED_TOTAL_MONTH")
	public Long getCreditedTotalMonth() {
		return creditedTotalMonth;
	}

	public void setCreditedTotalMonth(Long creditedTotalMonth) {
		this.creditedTotalMonth = creditedTotalMonth;
	}

	@Column(name="LAST_UPDATED")
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public GoodWillCredits() {
	}

	public GoodWillCredits(Long perMonthLimit, Long perTxnLimit,
			Long perDayLimit, Long creditedTotalDay, Long creditedTotalMonth,
			Date lastUpdated, GoodWillCreditsPK id) {
		super();
		this.perMonthLimit = perMonthLimit;
		this.perTxnLimit = perTxnLimit;
		this.perDayLimit = perDayLimit;
		this.creditedTotalDay = creditedTotalDay;
		this.creditedTotalMonth = creditedTotalMonth;
		this.lastUpdated = lastUpdated;
		this.id = id;
	}

	@Override
	public String toString() {
		return "GoodWillCredits [perMonthLimit=" + perMonthLimit
				+ ", perTxnLimit=" + perTxnLimit + ", perDayLimit="
				+ perDayLimit + ", creditedTotalDay=" + creditedTotalDay
				+ ", creditedTotalMonth=" + creditedTotalMonth
				+ ", lastUpdated=" + lastUpdated + ", id=" + id + "]";
	}

	
	
}
