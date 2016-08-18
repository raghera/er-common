/**
 * --------------------------------Modification History--------------------------------
 *
 *      Sr. No.		Date			    Author				Description
 * ------------------------------------------------------------------------------------
 *      [1]			Sep 7, 2009		Yeoh CW  		Added for CR "Off Portal"
 *
 */
package com.vizzavi.ecommerce.business.catalog;

public class BandPartner implements java.io.Serializable {

	private static final long serialVersionUID = 5413734223366283129L;
	
	private String mId;
	
	private String mPercent;
	
	private String mWhTaxAmt;
	
	private String mWhTaxOffset;

	
	
	public BandPartner(){
		
	}
	public BandPartner(String mId,String mPercent,String mWhtAmt,String mWhTaxOffset){
		this.mId=mId;
		this.mPercent=mPercent;
		this.mWhTaxAmt=mWhtAmt;
		this.mWhTaxOffset=mWhTaxOffset;
	}
	
	
	public String getId() {
		return mId;
	}

	public void setId(String id) {
		mId = id;
	}

	public String getPercent() {
		return mPercent;
	}

	public void setPercent(String percent) {
		mPercent = percent;
	}

	public String getWhTaxAmt() {
		return mWhTaxAmt;
	}

	public void setWhTaxAmt(String whTaxAmt) {
		mWhTaxAmt = whTaxAmt;
	}

	public String getWhTaxOffset() {
		return mWhTaxOffset;
	}

	public void setWhTaxOffset(String whTaxOffset) {
		mWhTaxOffset = whTaxOffset;
	}
}
