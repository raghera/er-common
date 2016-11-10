package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ER_CREDIT_LIMITS_EXCEEDED")
public class CreditLimitsExceeded implements Serializable{

	//protected Long id;
	//protected Long countryObjId;
	protected Long txnAmount;
	
	//protected String partnerId;
	protected Long totalSpendInAMonth;
	
	protected String packageId;
	protected String serviceId;
	
	protected Long totalSpendInADay;
	protected Long perMonthLimit;
	protected Long perTxnLimit;
	protected Long perDayLimit;
	
	protected Date timeStamp;
	
	protected CreditLimitsExceededPK id;
		
	@EmbeddedId
	public CreditLimitsExceededPK getId() {
		return id;
	}

	public void setId(CreditLimitsExceededPK id) {
		this.id = id;
	}

	/*@Column(name="ACCOUNT_OBJ_ID",nullable=false)
	public Long getAccountObjId() {
		return id;
	}

	public void setAccountObjId(Long id) {
		this.id = id;
	}

	@Column(name="COUNTRY_OBJ_ID",nullable=false)
	public Long getCountryObjId() {
		return countryObjId;
	}

	public void setCountryObjId(Long countryObjId) {
		this.countryObjId = countryObjId;
	}
*/
	@Column(name="TX_AMOUNT",nullable=false)
	public Long getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(Long txnAmount) {
		this.txnAmount = txnAmount;
	}

	/*@Column(name="PARTNER_ID",nullable=false)
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}*/

	@Column(name="SPEND_TOTAL_MONTH")
	public Long getTotalSpendInAMonth() {
		return totalSpendInAMonth;
	}

	public void setTotalSpendInAMonth(Long totalSpendInAMonth) {
		this.totalSpendInAMonth = totalSpendInAMonth;
	}

	@Column(name="PACKAGE_ID")
	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	@Column(name="SERVICE_ID")
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@Column(name="SPEND_TOTAL_DAY")
	public Long getTotalSpendInADay() {
		return totalSpendInADay;
	}

	public void setTotalSpendInADay(Long totalSpendInADay) {
		this.totalSpendInADay = totalSpendInADay;
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

	@Column(name="TIME_STAMP")
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public CreditLimitsExceeded(Long txnAmount, Long totalSpendInAMonth,
			String packageId, String serviceId, Long totalSpendInADay,
			Long perMonthLimit, Long perTxnLimit, Long perDayLimit,
			Date timeStamp, CreditLimitsExceededPK id) {
		super();
		this.txnAmount = txnAmount;
		this.totalSpendInAMonth = totalSpendInAMonth;
		this.packageId = packageId;
		this.serviceId = serviceId;
		this.totalSpendInADay = totalSpendInADay;
		this.perMonthLimit = perMonthLimit;
		this.perTxnLimit = perTxnLimit;
		this.perDayLimit = perDayLimit;
		this.timeStamp = timeStamp;
		this.id = id;
	}

	public CreditLimitsExceeded() {
	}

	@Override
	public String toString() {
		return "CreditLimitsExceeded [txnAmount=" + txnAmount
				+ ", totalSpendInAMonth=" + totalSpendInAMonth + ", packageId="
				+ packageId + ", serviceId=" + serviceId
				+ ", totalSpendInADay=" + totalSpendInADay + ", perMonthLimit="
				+ perMonthLimit + ", perTxnLimit=" + perTxnLimit
				+ ", perDayLimit=" + perDayLimit + ", timeStamp=" + timeStamp
				+ ", id=" + id + "]";
	}

	
	
}
