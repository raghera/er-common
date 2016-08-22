package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;



public class ServiceRevenueSharePartnerKey implements Serializable{

	
	private static final long serialVersionUID = 8525541638409751843L;
	/**
	 * Partner Id
	 */
	private String mId;
	/**
	 * Purchase Channel
	 */
	private String mPurchaseChannel;

	/**
	 * 
	 * @param mId
	 * @param mPurchaseChannel
	 */
	public ServiceRevenueSharePartnerKey(String mId, String mPurchaseChannel) {

		this.mId = mId;
		this.mPurchaseChannel=mPurchaseChannel;
		if(mPurchaseChannel==null) {
			this.mPurchaseChannel = "*";
	    }
	}
	
	public ServiceRevenueSharePartnerKey() {
		
	}

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return mId;
	}
	/**
	 * 
	 * @param mId
	 */
	public void setId(String mId) {
		this.mId = mId;
	}
	/**
	 * 
	 * @return
	 */
	public String getPurchaseChannel() {
		return mPurchaseChannel;
	}
	/**
	 * 
	 * @param mPurchaseChannel
	 */
	public void setPurchaseChannel(String mPurchaseChannel) {
		this.mPurchaseChannel = mPurchaseChannel;
	}


	@Override
	public int hashCode(){
		return  12* this.mId.hashCode() * this.mPurchaseChannel.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if(object == null||getClass() != object.getClass())
		{
			return false;
		}
		else{
			ServiceRevenueSharePartnerKey serviceRevenue = (ServiceRevenueSharePartnerKey) object;
			return (this.getId() == serviceRevenue.getId() && this.getPurchaseChannel()==serviceRevenue.getPurchaseChannel());
		}

	}

}
