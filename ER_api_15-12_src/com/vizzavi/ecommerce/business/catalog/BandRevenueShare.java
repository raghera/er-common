package com.vizzavi.ecommerce.business.catalog;

import java.util.List;

public class BandRevenueShare implements java.io.Serializable {

	private static final long serialVersionUID = 2152486544185749145L;
	
	protected String mPurchaseChannel;
	
	protected List<Band> mBands;

	public String getPurchaseChannel() {
		return mPurchaseChannel;
	}

	public void setPurchaseChannel(String purchaseChannel) {
		mPurchaseChannel = purchaseChannel;
	}

	public List<Band> getBands() {
		return mBands;
	}

	public void setBands(List<Band> bands) {
		mBands = bands;
	}
	
}
