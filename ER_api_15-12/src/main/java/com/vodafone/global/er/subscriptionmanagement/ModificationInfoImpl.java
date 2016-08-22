/*
 * Created on Mar 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.vodafone.global.er.subscriptionmanagement;

import java.io.Serializable;

import com.vizzavi.ecommerce.business.selfcare.ModificationInfo;
import com.vizzavi.ecommerce.business.selfcare.TransactionType;


public class ModificationInfoImpl implements ModificationInfo, Serializable  {

   private    static final long serialVersionUID = -7336908049638513553L;
	protected String mType;
	protected String mOldValue;
	protected String mNewValue;
	protected String mDescription;
	
	//CR-1513 - start
        protected String mHostId;
        protected String mClientId;
        //CR-1513 - start
    
	protected final static String DELIM = "|";
 
	public ModificationInfoImpl()
	{
	}
    
	/**
	 * 
	 * @param type eg TransactionType.MODIFY_BAN_TYPE
	 * @param oldValue the old value
	 * @param newValue the new value
	 * @param description e.g. reason
	 */
	public ModificationInfoImpl(String type, String oldValue, String newValue, String description)	{
		this.mType=type;
		mOldValue = oldValue;
		this.mNewValue = newValue;
		this.mDescription = description;
	}
	
	/**
	 * 
	 * @param val type|oldValue|newValue|description
	 * @deprecated stop using this ridiculous constructor and use {@link #ModificationInfoImpl(String type, String oldValue, String newValue, String description)} instead
	 */
	@Deprecated
	public ModificationInfoImpl(String val)	{
		parse(val);
	}
    
	public String getModificationType()
	{
		return mType;
	}
    
	public String getOldValue()
	{
		return mOldValue;
	}
    
	public String getNewValue()
	{
		return mNewValue;
	}
    
	public String getDescription()
	{
		return mDescription;
	}
    
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append(getModificationType()).append(DELIM);
		buf.append(getOldValue()).append(DELIM);
		buf.append(getNewValue()).append(DELIM);
		buf.append(getDescription());
		return buf.toString();
	}
    
    
	public void setModificationType(String val)
	{
		mType = val;
	}
    
	public void setOldValue(String val)
	{
		mOldValue = val;
	}
 
	public void setNewValue(String val)
	{
		mNewValue = val;
	}
	
	//CR-1513 - start
    public void setHostId(String val)
    {
            mHostId = val;
    }
 
    public void setClientId(String val)
    {
        mClientId = val;
    }
        
    public String getHostId()
    {
        return mHostId;
    }
 
    public String getClientId()
    {
        return mClientId;
    }
    //CR-1513 - end
 

	public void setDescription(String val)
	{
		mDescription = val;
	}
 
	protected void parse(String val)    
	{
		int index = val.indexOf(DELIM);
		if (index<0) {
			mDescription = val;     
		} else {
			mType = val.substring(0, index);
			int index2 = val.indexOf(DELIM, index + DELIM.length());
			mOldValue = val.substring(index + DELIM.length(), index2);
			int index3 = val.indexOf(DELIM, index2 + DELIM.length());
			mNewValue = val.substring(index2 + DELIM.length(), index3);
			mDescription = val.substring(index3 + DELIM.length());
		}
	}
    
	public boolean isInactivateAccount()
	{
		return mType.equals(TransactionType.MODIFY_INACTIVATE_ACCOUNT_TYPE);
	}
    
	public boolean isInactivateSubscription()    
	{
		return mType.equals(TransactionType.MODIFY_INACTIVATE_SUBSCRIPTION_TYPE);
	}
 
	public boolean isModifyMsisdn()
	{
		return mType.equals(TransactionType.MODIFY_MSISDN_TYPE);
	}
 
    
	public boolean isModifyBan()
	{
		return mType.equals(TransactionType.MODIFY_BAN_TYPE);
	}
 
	public boolean isModifyPaymentType()
	{
		return mType.equals(TransactionType.MODIFY_PAYMENT_TYPE_TYPE);
	}
    
	public boolean isModifyChargingMethod()
	{
		return mType.equals(TransactionType.MODIFY_CHARGING_METHOD_TYPE);
	}
	//CR1564-utc timezone for diff region in country
	public boolean isModifyTimezone()
	{
		return mType.equals(TransactionType.MODIFY_TIMEZONE_TYPE);
	}

	public boolean modifyBillingCycle() {
		
		return mType.equals(TransactionType.MODIFY_BILLINGCYCLE_TYPE);
	}

	public boolean modifyStatus() {
		
		return mType.equals(TransactionType.MODIFY_ACCOUNTSTATUS_TYPE);
	}

	public boolean modifyUserGroups() {

		return mType.equals(TransactionType.MODIFY_USERGROUPS_TYPE);
	}
	//CR1564-end

	// CR 1643 - Pre-Pay Post-Pay Split
	public boolean modifyAccountSpId()
	{
		return mType.equals(TransactionType.MODIFY_ACCOUNT_SP_ID_TYPE);
	}
	
	public boolean modifyAccountIsPrepay()
	{
		return mType.equals(TransactionType.MODIFY_ACCOUNT_IS_PREPAY_TYPE);
	}
	// CR 1643 - Ends

}
