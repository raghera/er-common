package com.vizzavi.ecommerce.business.charging;

import java.io.Serializable;
import java.util.Date;

import com.vizzavi.ecommerce.business.common.Constants;

//CR-1791
public class SubscriptionAttributes implements Serializable 
{
	private static final long serialVersionUID = 4144930732097889385L;
	private Date mEndDate = null;
	private int mStatus = Constants.INT_NOT_SET;

	public Date getEndDate()
	{
		return this.mEndDate;
	}

	public void setEndDate(Date EndDate)
    {
		this.mEndDate = EndDate;
    }

	public int getStatus()
    {
		return this.mStatus;
    }
  
    public void setStatus(int Status)
    {
    	this.mStatus = Status;
    }
}


