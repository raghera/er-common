package com.vodafone.global.er.subscriptionmanagement;

import java.util.Date;


//CR1564 -Utctimezone for diff region in country
public class ERRegion {

	private Integer regionObjId;
	private Integer countryObjId;
	private String utcOffset;
	private String regionName;
	private Integer taxRate;
	private Date taxRateApplicableDate;
	// CR1564_d - Utctimezone performance fix
    private String countryCode;


	public Integer getCountryObjId() {return countryObjId;}
	public void setCountryObjId(Integer countryObjId) {this.countryObjId = countryObjId;}

	public String getRegionName() {return regionName;}
	public void setRegionName(String regionName) {this.regionName = regionName;}

	public Integer getRegionObjId() {return regionObjId;}
	public void setRegionObjId(Integer regionObjId) {this.regionObjId = regionObjId;}

	public Integer getTaxRate() {return taxRate;}
	public void setTaxRate(Integer taxRate) {this.taxRate = taxRate;}

	public Date getTaxRateApplicableDate() {return taxRateApplicableDate;}
	public void setTaxRateApplicableDate(Date taxRateApplicableDate) {this.taxRateApplicableDate = taxRateApplicableDate;}

	public String getUtcOffset() {return utcOffset;}
	public void setUtcOffset(String utcOffset) {this.utcOffset = utcOffset;}
	
	// CR1564_d - Utctimezone performance fix
    public String getCountryCode() {return countryCode;}
    public void setCountryCode(String countryCode) {this.countryCode = countryCode;}
}
