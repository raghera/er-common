package com.vizzavi.ecommerce.business.catalog;

public class PartnerTradingLimit implements java.io.Serializable {
	
	private     static final long serialVersionUID = 5044258547336374193L;

    protected String mPartnerId;
    protected double mMaxTransAmount = -1;
    protected double mMaxCumulativeTransAmount = -1;
    protected int mPeriodDays = -1;
    
   public PartnerTradingLimit() {}
    
    public PartnerTradingLimit(String id, double maxAmount, double maxCumulativeAmount, int period) {
            
    	mPartnerId = id;
    	mMaxTransAmount = maxAmount;
    	mMaxCumulativeTransAmount = maxCumulativeAmount;
    	mPeriodDays = period;
    }
    
    
    public void setPartnerId(String id) { mPartnerId = id; }
    
    
    public String getPartnerId() { return mPartnerId; }
    
    
    public void setMaxTransAmount(double maxAmount) { mMaxTransAmount = maxAmount; }
    
    
    public double getMaxTransAmount() { return mMaxTransAmount; }
    
    
    public void setMaxCumulativeTransAmount(double maxCumulativeAmount) { mMaxCumulativeTransAmount = maxCumulativeAmount; }
    
    
    public double getMaxCumulativeTransAmount() { return mMaxCumulativeTransAmount; }
    
    
    public void setPeriodDays(int period) { mPeriodDays = period; }
    
    
    public int getPeriodDays() { return mPeriodDays; }
    
    
    public String toString() {
    
        StringBuffer strBuf = new StringBuffer();
        
        strBuf.append("partnerid: " + mPartnerId);
        strBuf.append("maxtransamount: " + mMaxTransAmount);
        strBuf.append("maxcumulativetransamount: " + mMaxCumulativeTransAmount);
        strBuf.append("perioddays: " + mPeriodDays);
        
        return strBuf.toString();    
    }  

}
