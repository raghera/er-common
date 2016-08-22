package com.vizzavi.ecommerce.business.charging;

//CR-1791

public class ResourceBalanceOnly implements java.io.Serializable {

	private static final long serialVersionUID = 1132086288238002160L;

	private int resourceId;
	private double balance;
	
	public ResourceBalanceOnly(int resourceId, double balance) {
		this.resourceId = resourceId;
		this.balance = balance;
	}
	
	public int getResourceId() {
		return resourceId;
	}
	
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("{");
        buf.append("resourceId=").append(resourceId).append(',');
        buf.append("balance=").append(balance);
        buf.append("}");
        return buf.toString();
    }
}
