package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ER_EXCEPTIONS")
public class ERException implements Serializable{
	
	protected Long id;
	
	protected String procParam;
	
	protected String errorMsg;
	
	protected Date dateStamp;
	
	protected String function;
	
	protected String errorCode;

	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="excepseq")
	@SequenceGenerator(name="excepseq", sequenceName="ER_EXCEPTIONS_SEQ", allocationSize=1/*00, initialValue = 10*/)
	@Id
	@Column(name="EXCEPTION_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="PROC_PARAMETERS")
	public String getProcParam() {
		return procParam;
	}

	public void setProcParam(String procParam) {
		this.procParam = procParam;
	}

	@Column(name="ERROR_MESSAGE")
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Column(name="DATE_STAMP")
	public Date getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(Date dateStamp) {
		this.dateStamp = dateStamp;
	}

	@Column(name="FUNCTION")
	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Column(name="ERROR_CODE")
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public ERException(Long id, String procParam, String errorMsg,
			Date dateStamp, String function, String errorCode) {
		super();
		this.id = id;
		this.procParam = procParam;
		this.errorMsg = errorMsg;
		this.dateStamp = dateStamp;
		this.function = function;
		this.errorCode = errorCode;
	}

	public ERException() {
	}

	@Override
	public String toString() {
		return "ERException [id=" + id + ", procParam=" + procParam
				+ ", errorMsg=" + errorMsg + ", dateStamp=" + dateStamp
				+ ", function=" + function + ", errorCode=" + errorCode + "]";
	}
	
}
