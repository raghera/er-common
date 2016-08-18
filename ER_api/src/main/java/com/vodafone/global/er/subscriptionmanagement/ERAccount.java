package com.vodafone.global.er.subscriptionmanagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.selfcare.BasicAccount;
import com.vizzavi.ecommerce.business.selfcare.UserGroup;

public class ERAccount extends BasicAccount implements Serializable, DirtyMarker {
    private boolean dirty = false;

    private static final long serialVersionUID = -5225927636112403967L;

    //CR1231
    //protected static final LWLogger logger = LWSupportFactoryImpl.getInstance()
            //.getLogger(ERAccount.class);

    private boolean newInstance = true;

    private Long account_obj_id;

    private int countryObjId;

    //private Date lastValidateCallTime;
    
    //STKHREQ 127 - Alignment with External Billing Cycles
    //private int billingCycleDate;    

    //REMEDY 6934
    private ReasonCode accountStatus = ReasonCode.OK;
    //END REMEDY 6934
    
	//MQC7141 - start
	protected String errorDescription;
	protected String errorId;
	//MQC7141 - end
    
    public ERAccount() {
    }

    public ERAccount(String msisdn, String ban, Locale locale, Long account_obj_id) { 
       this(msisdn, ban, locale, account_obj_id, null);

    }

    public ERAccount(String msisdn, String ban, Locale locale, Long account_obj_id, 
            ERUserGroup[] userGrps) {
       this(msisdn, ban, locale, account_obj_id, userGrps, -1);
    }
    /**
     * 
     * @param msisdn
     * @param ban
     * @param locale
     * @param account_obj_id
     * @param userGrps
     * @param billingCycleDate not used
     */
    public ERAccount(String msisdn, String ban, Locale locale, Long account_obj_id, 
            ERUserGroup[] userGrps, int billingCycleDate) {
        this.msisdn = msisdn;
        this.ban = ban;
        this.locale = locale;
        // Temp setting the account obj id
        this.account_obj_id = account_obj_id;
        userGroups = new ArrayList<UserGroup>();
        if (userGrps != null) {
            for (ERUserGroup userGrp : userGrps) {
                userGroups.add((userGrp));
            }
        }
    }
    
    
    
    /**
     * Accessor to get the external billing cycle date
     * 
     * @return external billing cycle date
     */
    @Override
	public int getBillingCycleDate() {
        return billingCycleDate;
    }

    /**
     * Mutator to set the external billing cycle date
     * 
     * @param billingCycleDate
     */
    @Override
	public void setBillingCycleDate(int billingCycleDate) {
        this.billingCycleDate = billingCycleDate;
    }

    // @since5.1
    // setter getter methods for last validate call time
    @Override
	public void setLastValidateCallTime(Date lastValidate) {
        this.lastValidateCallTime = lastValidate;
    }

    @Override
	public Date getLastValidateCallTime() {
        return this.lastValidateCallTime;
    }

    // Setter methods for Msisdn
    public void setMsisdn(String msisdn) {
        this.dirty = true;
        this.msisdn = msisdn;
    }

    // Setter methods for Ban
    public void setBan(String ban) {
        this.dirty = true;
        this.ban = ban;
    }

    // Setter methods for Locale
    public void setLocale(Locale locale) {
        this.dirty = true;
        this.locale = locale;
    }

    // Setter getter methods for Account Obj Id
    public void setAccountObjId(Long account_obj_id) {
        this.dirty = true;
        this.account_obj_id = account_obj_id;
    }

    public Long getAccountObjId() {
        return account_obj_id;
    }

    // Setter getter methods for Country Obj Id
    public void setCountryObjId(int countryObjId) {
        this.dirty = true;
        this.countryObjId = countryObjId;
    }

    public int getCountryObjId() {
        return countryObjId;
    }

    // Setter methods for Name
    public void setName(String name) {
        this.dirty = true;
        this.name = name;
    }

    // Setter methods for Status
    public void setStatus(int status) {
        this.dirty = true;
        this.status = status;
    }

   // CR1564 - ER TimeZone Enhancement
	@Override
	public void setHomeTimezone(String timeZoneName) {
		this.dirty = true;
		super.setHomeTimezone(timeZoneName);
	}
	// CR1564 - Ends
	
    
    // @since 5.1
    // Setter getter methods for userGroups
    @Override
	public UserGroup[] getUserGroups() {
        if (userGroups != null) {
            return userGroups.toArray((new UserGroup[] {}));
        } else
            return null;
    }

    public void setUserGroups(ERUserGroup[] userGrps) {
        userGroups = new ArrayList<UserGroup>();
        if (userGrps != null) {
            for (ERUserGroup userGrp : userGrps) {
                userGroups.add(new ERUserGroup(userGrp.getName(),
                        userGrp.getDescription(), userGrp
                                .getAccount_obj_id(), userGrp
                                .getChangeFlag(), userGrp.isDirty(),
                        userGrp.isNew()));
            }
        }
    }

    public String[] getUserGroupNames() {
        if (userGroups != null) {
            List<String> userGroupName = new ArrayList<String>();
            for (int i = 0; i < userGroups.size(); i++) {
                UserGroup thisUserGroup = userGroups.get(i);
                userGroupName.add(thisUserGroup.getName());
            }
            return userGroupName.toArray((new String[] {}));
        } else
            return null;
    }

    public void removeUserGroupsWithChangeFlag(String changeFlag) {
        List<UserGroup> validUserGroups = new ArrayList<UserGroup>();
        if (userGroups != null) {
            for (int i = 0; i < userGroups.size(); i++) {
                ERUserGroup thisUserGroup = ((ERUserGroup) userGroups.get(i));
                if (((thisUserGroup.getChangeFlag().trim()).equals(changeFlag)) == false) {
                    validUserGroups.add(thisUserGroup);
                }
            }
            userGroups = validUserGroups;
        }

    }

    public void setUserGroupsChangeFlag(String changeFlag) {
        if (userGroups != null) {
            for (int i = 0; i < userGroups.size(); i++) {
                ERUserGroup thisUserGroup = ((ERUserGroup) userGroups.get(i));
                thisUserGroup.setChangeFlag(changeFlag);
            }
        }

    }

    /*
     * (non-Javadoc) *
     * 
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setDirty()
     */
    @Override
	public void setDirty() {
        this.dirty = true;
    }

    /*
     * (non-Javadoc) *
     * 
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isDirty()
     */
    @Override
	public boolean isDirty() {
        return dirty;
    }

    /*
     * (non-Javadoc) *
     * 
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetDirty()
     */
    @Override
	public void resetDirty() {
        dirty = false;
    }

    /*
     * (non-Javadoc) *
     * 
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setNew()
     */
    @Override
	public void setNew() {
        newInstance = true;
    }

    /*
     * (non-Javadoc) *
     * 
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isNew()
     */
    @Override
	public boolean isNew() {
        return newInstance;
    }

    /*
     * (non-Javadoc) *
     * 
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetNew()
     */
    @Override
	public void resetNew() {
        newInstance = false;
    }

    //REMEDY 6934
    public ReasonCode getAccountStatus()
    {
    	return this.accountStatus;
    }
    
    public void setAccountStatus(ReasonCode accountStatus)
    {
    	this.accountStatus = accountStatus;
    }
    //END REMEDY 6934
    
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
}
