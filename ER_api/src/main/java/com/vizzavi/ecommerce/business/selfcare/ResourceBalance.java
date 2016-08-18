package com.vizzavi.ecommerce.business.selfcare;

import com.vizzavi.ecommerce.business.common.ChargingResource;

public class ResourceBalance implements java.io.Serializable
{
   private static final long serialVersionUID = -6123222737770569297L;

   protected ChargingResource mRes;
   protected double mBalance;
   protected int mThreshold;
   
   
   //@hud RFRFRF
   /**
    * 
    */
   protected String mSubscriptionId = null;
   protected long mSubscriptionIdLong = -1;
   
   
   
   protected String mPackageId;
   protected String mOldestSubscriptionId = null;
  
   public ResourceBalance(){
   }

   public ResourceBalance(ResourceBalance resBalances)
   {
        this.mRes = resBalances.mRes;
        this.mBalance = resBalances.mBalance;
        this.mSubscriptionId = resBalances.mSubscriptionId;
        this.mPackageId = resBalances.mPackageId;
		this.mThreshold=resBalances.mThreshold;
   }

   public ResourceBalance(ChargingResource res, double bal)
   {
        mRes = res;
        mBalance = bal;
   }

   public ResourceBalance(ChargingResource res, double bal,int threshold)
   {
        mRes = res;
        mBalance = bal;
		mThreshold = threshold;
   }

	
    public ChargingResource getResource()
    {
        return mRes;
    }

    public double getBalance()
    {
        return mBalance;
    }

	public int getThreshold()
	{
		return mThreshold;
	}

	/**
	 * 
	 * @return
	 */
    public String getSubscriptionId()
    {
    	if (mSubscriptionId == null) {
    		if (mSubscriptionIdLong > 0) {
    			mSubscriptionId = Long.toString(mSubscriptionIdLong);
    		}
    	}
        return mSubscriptionId;
    }
    public long getSubscriptionIdLong() {
    	return mSubscriptionIdLong;
    }
	public void setSubscriptionIdLong(long id) {
		mSubscriptionIdLong = id;
	}    

    public String getPackageId()
    {
        return mPackageId;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("{");
        buf.append(mRes.toString()).append("=").append(mBalance).append(',');
        buf.append("subscriptionId").append(mSubscriptionId);
		buf.append(",threshold=").append(mThreshold);
        buf.append("}");
        return buf.toString();
    }
    public String getOldestSubscriptionId(){
    	return mOldestSubscriptionId;
    }
    
    /**
    CR-1791 - setter to change existing resource balance
    */
    public void setBalance(double bal) {
    	mBalance = bal;
    }
}
