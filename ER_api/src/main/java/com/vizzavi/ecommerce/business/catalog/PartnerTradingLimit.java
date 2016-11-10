package com.vizzavi.ecommerce.business.catalog;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PartnerTradingLimit implements java.io.Serializable {
	
	private     static final long serialVersionUID = 5044258547336374193L;
	
    protected String mPartnerId;
	
    protected double mMaxTransAmount = -1d;
	
    protected double mMaxCumulativeTransAmount = -1d;
	
    protected int mPeriodDays = -1;
    
   public PartnerTradingLimit() {}
    
    public PartnerTradingLimit(String id, double maxAmount, double maxCumulativeAmount, int period) {
            
    	mPartnerId = id;
    	mMaxTransAmount = maxAmount;
    	mMaxCumulativeTransAmount = maxCumulativeAmount;
    	mPeriodDays = period;
    }
    
    //@Transient
    public void setPartnerId(String id) { this.mPartnerId = id; }
    
	@Column(name="PARTNER_ID",insertable=false, updatable=false)
    //@Transient
    public String getPartnerId() { return mPartnerId; }
    
    
    public void setMaxTransAmount(Double maxAmount) { if (maxAmount!=null) mMaxTransAmount = maxAmount; }
    
    @Column(name="PARTNER_SINGLE_TRX_AMOUNT")
    public double getMaxTransAmount() { return mMaxTransAmount; }
    
    
    public void setMaxCumulativeTransAmount(Double maxCumulativeAmount) { 
    	if (maxCumulativeAmount!=null) 
    		mMaxCumulativeTransAmount = maxCumulativeAmount; 
    }
    
    @Column(name="PARTNER_TOTAL_TRX_AMOUNT")
    public double getMaxCumulativeTransAmount() { return mMaxCumulativeTransAmount; }
    
    
    public void setPeriodDays(Integer period) { if (period!=null) mPeriodDays = period; }
    
    @Column(name="PARTNER_PERIOD")
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
