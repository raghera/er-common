package com.vizzavi.ecommerce.business.catalog;

import java.util.List;

public class Band implements java.io.Serializable {

	private static final long serialVersionUID = -8211028752478196213L;
	
	private String mBandName;
	
	private String mBandMin;
	
	private List<BandPartner> mBandPartners;
	
	
	public Band(){
		
	}
	/*
	 * 
	 */
	public Band(String mBandname,String bandmin){
		this.mBandName=mBandname;
		this.mBandMin=bandmin;
    	}

	public String getBandName() {
		return mBandName;
	}

	public void setBandName(String bandName) {
		mBandName = bandName;
	}

	public String getBandMin() {
		return mBandMin;
	}

	public void setBandMin(String bandMin) {
		mBandMin = bandMin;
	}

	public List<BandPartner> getBandPartners() {
		return mBandPartners;
	}

	public void setBandPartners(List<BandPartner> bandPartners) {
		mBandPartners = bandPartners;
	}
}
