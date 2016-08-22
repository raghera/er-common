package com.vodafone.global.er.subscriptionmanagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.vizzavi.ecommerce.business.selfcare.BasicAccount;
import com.vizzavi.ecommerce.business.selfcare.UserGroup;
import com.vizzavi.ecommerce.common.ErCountry;

/**
 *
 */
public class ERAccount extends BasicAccount implements Serializable, DirtyMarker {
    private boolean dirty = false;

    private static final long serialVersionUID = -5225927636112403967L;


    private boolean newInstance = true;


    //REMEDY 6934
//    private ReasonCode accountStatus = ReasonCode.OK;
    //END REMEDY 6934
    
	//MQC7141 - start
	protected String errorDescription;
	protected String errorId;
	//MQC7141 - end
    
    public ERAccount() {
    }
    

    /**
     * @param acc
     */
    public ERAccount(BasicAccount acc)	{
    	this(acc.getMsisdn(), acc.getBan(), acc.getLocale(), acc.getAccountObjId(), acc.getBillingCycleDate(), acc.getChildSpId(), acc.getSpId(), acc.getSpType(), 
    			acc.getLastValidateCallTime(), acc.getIsPrepay(), acc.getTimestamp(), acc.getCountry(), acc.getUserGroupList(), (acc.getSuppressCourtesyNotifications()!=null?acc.getSuppressCourtesyNotifications():false),
    			acc.getStatus());
    }
    
    /**
     * @param msisdn
     * @param ban
     * @param locale
     * @param accountObjId
     * @param billingCycleDate
     * @param childSpid
     * @param spId
     * @param spType
     * @param lastValidateCallTime
     * @param isPrepay
     * @param timestamp
     * @param country
     * @param userGroupList
     * @param suppressCourtesyNotifications
     */
    public ERAccount(String msisdn, String ban, Locale locale, Long accountObjId, int billingCycleDate, String childSpid, String spId, String spType, 
			Date lastValidateCallTime, String isPrepay, Date timestamp, String country, List<UserGroup> userGroupList, boolean suppressCourtesyNotifications, int status) {
        this.msisdn = msisdn;
        this.ban = ban;
        setLocale(locale);
        // Temp setting the account obj id
        this.id = accountObjId;
        
        this.setBillingCycleDate(billingCycleDate);
        this.setChildSpId(childSpid);
        this.setSpId(spId);
        this.setSpType(spType);
        this.setLastValidateCallTime(lastValidateCallTime);
        this.setIsPrepay(isPrepay);
        this.setTimestamp(timestamp);
        this.setCountry(country);
        this.setUserGroupList(userGroupList);
        
        
        if (userGroupList != null && userGroupList.size()>0) {
            userGroups = new ArrayList<>();
            for (UserGroup userGrp : userGroupList) {
                userGroups.add(new ERUserGroup( userGrp.getName(), "", null, ""));
            }
        }
        
        this.setSuppressCourtesyNotifications(suppressCourtesyNotifications);
        this.setStatus(status);
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
        setLocale(locale);
        // Temp setting the account obj id
        this.id = account_obj_id;
        userGroups = new ArrayList<>();
        if (userGrps != null) {
            for (ERUserGroup userGrp : userGrps) {
                userGroups.add((userGrp));
            }
        }
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
    	setCountry(ErCountry.getByLocale(locale).getCode());
    }

    // Setter getter methods for Account Obj Id
    public void setAccountObjId(Long account_obj_id) {
        this.dirty = true;
        this.id = account_obj_id;
    }


    // Setter getter methods for Country Obj Id
    public void setCountryObjId(int countryObjId) {
        this.dirty = true;
        this.countryId = countryObjId;
    }

    public int getCountryObjId() {
        return countryId;
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
            return userGroups.toArray((new UserGroup[userGroups.size()]));
        } else
            return null;
    }

    public void setUserGroups(ERUserGroup[] userGrps) {
        userGroups = new ArrayList<>();
        if (userGrps != null) {
            for (ERUserGroup userGrp : userGrps) {
            	userGroups.add(userGrp);
            }
        }
    }

    public void removeUserGroupsWithChangeFlag(String changeFlag) {
        List<UserGroup> validUserGroups = new ArrayList<>();
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


    

  	//MQC7141 - end
}
