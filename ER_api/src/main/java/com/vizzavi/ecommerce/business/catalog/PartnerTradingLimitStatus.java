package com.vizzavi.ecommerce.business.catalog;

public class PartnerTradingLimitStatus implements java.io.Serializable {

	private     static final long serialVersionUID = -3190033475903461974L;
	
	protected double mNetSpend = -1;
	protected int	 mStatus;
	
	public final static int INSERT_RECORD_ONLY = 0;
	public final static int PARTNER_TRADING_LIMIT_EXCEEDED = 1;
	public final static int PARTNER_TRADING_LIMIT_NOT_EXCEEDED = 2;
	
	public PartnerTradingLimitStatus() {}
	
	public double getNetSpend() {
		return mNetSpend;
	}
	
	public void setNetSpend(double netSpend) {
		mNetSpend = netSpend;
	}
	
	public int getStatus() {
		return mStatus;
	}
	
	public void setStatus(int status) {
		mStatus = status;
	}
}
