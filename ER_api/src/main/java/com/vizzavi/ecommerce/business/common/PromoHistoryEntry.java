package com.vizzavi.ecommerce.business.common;

/**
 * class created for possible future use with the er promotion history table
 * @author matt
 *
 */
public class PromoHistoryEntry {

	private Long accountId;
	private Long subId;
	private String packageId;
	private String type;	//eg TRIAL, PROMO01, etc
	private String contentId;	//eg *
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getSubId() {
		return subId;
	}
	public void setSubId(Long subId) {
		this.subId = subId;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	
	
	
	
}
