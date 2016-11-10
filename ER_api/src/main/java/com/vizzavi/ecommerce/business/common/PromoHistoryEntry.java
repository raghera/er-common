//package com.vizzavi.ecommerce.business.common;
//
//
//import java.io.Serializable;
//
//import javax.persistence.*;
//
//import com.vizzavi.ecommerce.business.selfcare.BasicAccount;
//import com.vizzavi.ecommerce.business.selfcare.PromoHistoryPK;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
///**
// * The persistent class for the ER_PROMOTION_HISTORY database table.
// * 
// */
//@Entity
//@Table(name="ER_PROMOTION_HISTORY_RT")
////@NamedQuery(name="ErPromotionHistoryRt.findAll", query="SELECT e FROM PromoHistoryEntry e")
//public class PromoHistoryEntry implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@EmbeddedId
//	private PromoHistoryPK id;
//
//	@Column(name="CONTENT_ID")
//	private String contentId;
//
//	@Column(name="COUNTRY_OBJ_ID")
//	private BigDecimal countryObjId;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="TIME_STAMP")
//	private Date timeStamp;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="UPDATE_TIME_STAMP")
//	private Date updateTimeStamp;
//
//	//bi-directional many-to-one association to ErAccountsRt
//	@ManyToOne	(optional=false)
//	//@ManyToOne(optional=false,	targetEntity=BasicAccount.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)	
//	@JoinColumn(name="ACCOUNT_ID")
//	private BasicAccount account;
//
//	public PromoHistoryEntry() {
//	}
//
//	public PromoHistoryPK getId() {
//		return this.id;
//	}
//
//	public void setId(PromoHistoryPK id) {
//		this.id = id;
//	}
//
//	public String getContentId() {
//		return this.contentId;
//	}
//
//	public void setContentId(String contentId) {
//		this.contentId = contentId;
//	}
//
//	public BigDecimal getCountryObjId() {
//		return this.countryObjId;
//	}
//
//	public void setCountryObjId(BigDecimal countryObjId) {
//		this.countryObjId = countryObjId;
//	}
//
//	public Date getTimeStamp() {
//		return this.timeStamp;
//	}
//
//	public void setTimeStamp(Date timeStamp) {
//		this.timeStamp = timeStamp;
//	}
//
//	public Date getUpdateTimeStamp() {
//		return this.updateTimeStamp;
//	}
//
////	public void setUpdateTimeStamp(Date updateTimeStamp) {
////		this.updateTimeStamp = updateTimeStamp;
////	}
//
//
//	public BasicAccount getAccount() {
//		return account;
//	}
//
//	public void setAccount(BasicAccount account) {
//		this.account = account;
//	}
//
//	
//
//}