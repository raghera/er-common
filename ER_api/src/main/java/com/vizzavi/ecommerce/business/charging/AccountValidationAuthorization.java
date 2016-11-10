package com.vizzavi.ecommerce.business.charging;

import com.vizzavi.ecommerce.business.common.ResponseStatus;
import com.vizzavi.ecommerce.common.Utils;

import java.io.Serializable;
import java.util.*;


/**
* Encapsulates the result of a authorization call to the charging subsystem.<br/>
* For an authorization call, the isAuthorized() method should be checked to see if successful.<br/>
* For a charging call, isCharged() should be checked to see if successful.<br/>
* <p>If the call failed, the ReasonCode should be retrieved. The ReasonCode object gives more
* information why a particular call failed.
*/
public class AccountValidationAuthorization implements Serializable
{
   private    static final long serialVersionUID = -877147595267092878L;

    //@since 5.1
	// The ban
	protected String mBan;

	// The list of user groups associated with the account
//	protected List<String> mUserGroups=null;
	protected Set<String> mUserGroups=null;

	// The status of the account authorization result
	protected String mStatus;

	//	 The Billing Cycle Date
	//	R9.0 @mawn STKHREQ127 
	protected int billingCycleDay ;

    //CR1564 -Utctimezone for diff region in country
	protected String utcTimezoneOffset;

	protected String spId;		// CR 1643 - Pre-Pay Post-Pay Split

	protected String isPrepay;	// CR 1643 - Pre-Pay Post-Pay Split
	
	//MQC7141 - start
	protected String errorDescription;
	protected String errorId;
	//MQC7141 - end

	protected String childSpId;	// CR 2198 - add child spid and service provider type
	protected String spType;	// CR 2198 - add child spid and service provider type
	
	//MQC 7679 - add falg to check if usergroup tag exists in the response. 
	//In case no tag exists do not update any usergroup info for the user. 
	//If tag does exist but has empty contents, then delete all usergroups for the user
	private boolean usergroupTagExists;
		
	//MQC 7679 - add flag to check if spid tag exists in the response. 
	//In case no tag exists do not update any spid info for the user. 
	//If tag does exist but has empty contents, then delete all spid info for the user
	private boolean spIdTagExists; 
	
	//MQC 7679 - add flag to check if prepay tag exists in the response. 
	//In case no tag exists do not update any prepay info for the user. 
	//If tag does exist but has empty contents, then delete all prepay info for the user
	private boolean isPrepayTagExists; 
	
	//MQC 7679 - add flag to check if child spid tag exists in the response. 
	//In case no tag exists do not update any child spid info for the user. 
	//If tag does exist but has empty contents, then delete all child spid info for the user
	private boolean childSpIdTagExists; 

	//MQC 7679 - add flag to check if sptype tag exists in the response. 
	//In case no tag exists do not update any sptype info for the user. 
	//If tag does exist but has empty contents, then delete all sptype info for the user
	private boolean spTypeTagExists;	

	public AccountValidationAuthorization()
    {
    }

    public AccountValidationAuthorization(String ban, String[] userGroups)
    {
    	this(ban, -1, userGroups);
    }
 
	//	 Constructor with Billing Cycle Date
    //  R9.0 @mawn STKHREQ127 
    
    public AccountValidationAuthorization(String ban, int billingCycleDay , String[] userGroups)
    {
		this.mBan = ban;
		if (billingCycleDay>=0)
			this.billingCycleDay = billingCycleDay;
		
	  	if (userGroups != null) 
	  		setUserGroups(userGroups);
	  	else
			mUserGroups = new HashSet<>();
    }

    /**
    * Outputs the data in this bean
    */
    @Override
	public String toString()
    {
        StringBuffer sb = new StringBuffer(super.toString());

        sb.append(  " ban= " );
        sb.append(  getBan() );

        sb.append(  " user groups= " );

        sb.append(Utils.StringifyList(mUserGroups));
		
        sb.append(  " status= " );
        sb.append(  getStatus() );
        
        sb.append(  " errorid= " );
        sb.append(  getErrorId() );

        sb.append(  " error description= " );
        sb.append(  getErrorDescription() );


        return sb.toString();
    }



    /**
    * Obtain the ban for an account authorization result.
    * @return the validated ban.
    */
    public String getBan()
    {
        return this.mBan;
    }

    /**
    * Obtain the user groups for an account authorization result.
    * @return the user groups.
    */
 	public String[] getUserGroups() {

 		if (mUserGroups != null) {
 			return mUserGroups.toArray((new String[]{}));
 		}
 		else return null;
	}

 	/**
     * Obtain the status for an account authorization result.
     * @return the status.
     */
     public String getStatus()
     {
         return this.mStatus;
     }
     

    /**
    * Set the ban for an authorization result
    * @param ban the ban value
    */
    public void setBan(String ban)
    {
        this.mBan = ban;
    }

	@Deprecated
	public void setUserGroups(String[] userGrps) {
		mUserGroups = new HashSet<>();
//		if (userGrps != null) {
//			for (String userGrp : userGrps) {
//				mUserGroups.add(new String(userGrp));
//			}
//			//usergroupTagExists=true;
//		}
		Collections.addAll(mUserGroups, userGrps);
	}

	/**
	 * Set the status for an authorization result
	 * @param status the status value
	*/
	public void setStatus(String status)
	{
		this.mStatus = status;
	}

	public int getBillingCycleDay() {
		return billingCycleDay;
	}

	public void setBillingCycleDay(int billingCycleDay) {
		this.billingCycleDay = billingCycleDay;
	}

    //CR1564 -start
	public String getUtcTimezoneOffset() {
		return utcTimezoneOffset;
	}

	public void setUtcTimezoneOffset(String utcTimezoneOffset) {
		this.utcTimezoneOffset = utcTimezoneOffset;
	}
    //CR1564 -end

	// CR 1643 - Pre-Pay Post-Pay Split
	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getIsPrepay() {
		return isPrepay;
	}

	public void setIsPrepay(String isPrepay) {
		this.isPrepay = isPrepay;
	}
	// CR 1643 - Ends
	
	//MQC7141 - start
	public void setErrorDescription(String errordDescription)
	{
		this.errorDescription = errordDescription;
	}
	
	public void setErrorId(String errordId)
	{
		this.errorId = errordId;
	}
	
	public String getErrorDescription()
	{
		return this.errorDescription;
	}
	
	public String getErrorId()
	{
		return this.errorId;
	}
	//MQC7141 - end
	
	//CR2028 - return if chargeable account
	/**
	 * returns true if the response status is "ACCEPTED", false otherwise
	 * @return
	 */
	public boolean isChargeable() {	
		return ResponseStatus.isAccepted(mStatus);
	}
	
	/**
	 * CR 2198
	 * @return the childSpId
	 */
    public String getChildSpId() {
		return childSpId;
	}
    
    /**
     * CR 2198
	 * @param childSpId the childSpId to set
	 */
	public void setChildSpId(String childSpId) {
		this.childSpId = childSpId;
	}
	
	/**
	 * CR 2198
	 * @return the spType
	 */
    public String getSpType() {
		return spType;
	}
    
    /**
     * CR 2198
   	 * @param spType the spType to set
   	 */
	public void setSpType(String spType) {
		this.spType = spType;
	}
	
	/**
	 * MQC 7679
	 * @return the usergroupTagExists
	 */
    public boolean getUsergroupTagExists() {
		return usergroupTagExists;
	}
    
    /**
     * MQC 7679
	 * @param usergroupTag if usergroupTag exists
	 */
	public void setUsergroupTagExists(boolean usergroupTag) {
		this.usergroupTagExists = usergroupTag;
	}
	
	/**
	 * MQC 7679
	 * @return the spIdTagExists
	 */
    public boolean getSpIdTagExists() {
		return spIdTagExists;
	}
    
    /**
     * MQC 7679
	 * @param spIdTag if spIdTag exists
	 */
	public void setSpIdTagExists(boolean spIdTag) {
		this.spIdTagExists = spIdTag;
	}
	
	/**
	 * MQC 7679
	 * @return the isPrepayTagExists
	 */
    public boolean getIsPrepayTagExists() {
		return isPrepayTagExists;
	}
    
    /**
     * MQC 7679
	 * @param isPrepayTag if isPrepayTag exists
	 */
	public void setIsPrepayTagExists(boolean isPrepayTag) {
		this.isPrepayTagExists = isPrepayTag;
	}
	
	/**
	 * MQC 7679
	 * @return the childSpIdTagExists
	 */
    public boolean getChildSpIdTagExists() {
		return childSpIdTagExists;
	}
    
    /**
     * MQC 7679
	 * @param childSpIdTag if childSpIdTag exists
	 */
	public void setChildSpIdTagExists(boolean childSpIdTag) {
		this.childSpIdTagExists = childSpIdTag;
	}
	
	/**
	 * MQC 7679
	 * @return the spTypeTagExists
	 */
    public boolean getSpTypeTagExists() {
		return spTypeTagExists;
	}
    
    /**
     * MQC 7679
	 * @param spTypeTag if spTypeTag exists
	 */
	public void setSpTypeTagExists(boolean spTypeTag) {
		this.spTypeTagExists = spTypeTag;
	}
	
}
