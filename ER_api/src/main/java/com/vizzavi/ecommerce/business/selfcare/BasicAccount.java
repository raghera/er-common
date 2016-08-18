package com.vizzavi.ecommerce.business.selfcare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.vizzavi.ecommerce.business.charging.AccountValidationAuthorization;
import com.vizzavi.ecommerce.business.charging.PaymentCardDetails;
import com.vizzavi.ecommerce.common.Utils;


public class BasicAccount implements java.io.Serializable {
    private static final long serialVersionUID = -7392495277683348677L;


    //private static Logger logger = Logger.getLogger(BasicAccount.class);

    protected String msisdn;
    protected String ban;
    protected String name;
    protected Locale locale;
    protected int status = AccountStatus.ACTIVE;
    protected List<UserGroup> userGroups = null;
    protected PaymentCardDetails paymentCardDetails = null;
    //CR1564-Utctimezoneoffset and timezone name
    protected String utcTimezoneOffset;
    protected String homeTimezone;
	protected String country;
    protected int billingCycleDate;
    protected Date lastValidateCallTime;
    protected Date timestamp;
    protected String spId;		// CR 1643 - Pre-Pay Post-Pay Split
    protected String isPrepay;	// CR 1643 - Pre-Pay Post-Pay Split
	
    //MQC 6416 - store the original msisdn if using a api which changes the msisdn value
    //such as custCare.modifyMsisdn and custCare.inactivateAccount
    protected String originalMsisdn;

    //MQC 6510 - store the validate handler response status for the account
    protected String validateResponseStatus;

    protected SpendLimits spendLimits = null;  // CR2040 MPAY replacement. MSISDN spend limits.
    
    //MQC 7434 - If we have already got the accountValidationAuthorization store here
    //there is no need to do another validation call
    protected AccountValidationAuthorization accountValidationAuthorization;
    
    protected String childSpId;	// CR 2198 - add child spid and service provider type
	protected String spType;	// CR 2198 - add child spid and service provider type
	
    public BasicAccount() {
    }

    public BasicAccount(BasicAccount basicAccount) {
        this.msisdn = basicAccount.getMsisdn();
        this.ban = basicAccount.getBan();
        this.name = basicAccount.getName();
        this.locale = basicAccount.getLocale();
        this.status = basicAccount.getStatus();
        UserGroup[] tmpUserGroupArray = basicAccount.getUserGroups();
        if (tmpUserGroupArray != null) {
            ArrayList<UserGroup> tmpUserGroupList = new ArrayList<UserGroup>();
            for (UserGroup element : tmpUserGroupArray) {
                tmpUserGroupList.add(new UserGroup(element.getName(),
                        element.getDescription()));
            }
            this.userGroups = tmpUserGroupList;
        }
        //CR1564: Make sure the copy constructor get this value
        this.homeTimezone = basicAccount.getHomeTimezone();
        this.utcTimezoneOffset = basicAccount.getUtcTimezoneOffset();
        this.country = basicAccount.getCountry();
        this.billingCycleDate = basicAccount.getBillingCycleDate();
        this.lastValidateCallTime = basicAccount.getLastValidateCallTime();
        this.spId = basicAccount.getSpId();			// CR 1643 - Pre-Pay Post-Pay Split
        this.isPrepay = basicAccount.getIsPrepay();	// CR 1643 - Pre-Pay Post-Pay Split
        this.timestamp = basicAccount.getTimestamp();

        //MQC 6510
        this.validateResponseStatus = basicAccount.getValidateResponseStatus();
        this.spendLimits = basicAccount.getSpendLimits(); // CR2040 MPAY replacement. MSISDN spend limits.
        
        this.childSpId = basicAccount.getChildSpId();	// CR 2198 - add child spid and service provider type
        this.spType = basicAccount.getSpType();			// CR 2198 - add child spid and service provider type
    }

    // CR 2198 - new constructor
    public BasicAccount (String msisdn, String ban, Locale locale, int billingCycleDate,
    		String utcTimezoneOffset, String spId, String isPrepay, String childSpId, String spType) {
    	this.msisdn = msisdn;
        this.ban = ban;
        this.locale = locale;
        this.billingCycleDate = billingCycleDate;
        this.utcTimezoneOffset = utcTimezoneOffset;
        this.spId = spId;
    	this.isPrepay = isPrepay;
    	this.childSpId = childSpId;
    	this.spType = spType;
    }
    
    
    public BasicAccount(String msisdn, String ban,  String name,Locale locale, int status){
    	this.msisdn = msisdn;
    	this.ban = ban;
    	this.name = name;
    	this.locale = locale;
    	this.status = status;
    }
    
    public String getMsisdn() {
        return msisdn;
    }

    public String getBan() {
        return ban;
    }

    public String getName() {
        return name;
    }

    public Locale getLocale() {
        return locale;
    }

    public int getStatus() {
    	return status;
    }

    public UserGroup[] getUserGroups() {
        if (userGroups != null) {
            return userGroups.toArray((new UserGroup[]{}));
        }
        else return null;
    }

	public PaymentCardDetails getPaymentCardDetails() {
		return this.paymentCardDetails;
	}

	public void setPaymentCardDetails(PaymentCardDetails val) {
		this.paymentCardDetails = val;
	}
	//CR1564-start
	
	public String getHomeTimezone() {
		return homeTimezone;
	}

	public void setHomeTimezone(String timezoneName) {
		this.homeTimezone = timezoneName;
	}
		
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getBillingCycleDate() {
		return billingCycleDate;
	}

	public void setBillingCycleDate(int billingCycleDate) {
		this.billingCycleDate = billingCycleDate;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getLastValidateCallTime() {
		return lastValidateCallTime;
	}

	public void setLastValidateCallTime(Date lastValidateCallTime) {
		this.lastValidateCallTime = lastValidateCallTime;
	}

	 public String getUtcTimezoneOffset() {
			return utcTimezoneOffset;
		}

	public void setUtcTimezoneOffset(String utcTimezoneOffset) {
		this.utcTimezoneOffset = utcTimezoneOffset;
	}
	//CR1564-end

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

	//MQC 6416 - store the original msisdn if using a api which changes the msisdn value
    //such as custCare.modifyMsisdn and custCare.inactivateAccount
	public String getOriginalMsisdn() {
        return originalMsisdn;
    }
	
	public void setOriginalMsisdn(String originalMsisdn) {
        this.originalMsisdn = originalMsisdn;
    }
	
	//MQC 6510 - store the validate handler response status for the account
	public String getValidateResponseStatus() {
		return this.validateResponseStatus;
	}
	
	public void setValidateResponseStatus(String validateStatus) {
		this.validateResponseStatus = validateStatus;
	}
	
	//MQC 7434 Start
	public AccountValidationAuthorization getAccountValidationAuthorization( ) {
		
		return accountValidationAuthorization;
		
	}
	
	public void setAccountValidationAuthorization( AccountValidationAuthorization accountValidationAuthorization ) {
		
		this.accountValidationAuthorization = accountValidationAuthorization;
		
	}
	//MQC 7434 End
	
	@Override
	public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{BasicAccount\n");
        buf.append("msisdn=" + msisdn + "\n");
        buf.append("ban=" + ban + "\n");
        if (name !=null) buf.append("name=" + name + "\n");
        if (billingCycleDate>0) buf.append("billingCycleDate=" + billingCycleDate + "\n");
        if (locale!=null) buf.append("locale=" + locale.toString() + "\n");
        if (userGroups!=null) buf.append("usergroups=" + Utils.StringifyList(userGroups));
		if (paymentCardDetails!=null) buf.append("paymentCardDetails=" + paymentCardDetails.toString());
		// CR1564
		if (homeTimezone != null) buf.append("timezoneName=" + homeTimezone+ "\n");
		if (utcTimezoneOffset != null) buf.append("utcTimezoneOffset=" + utcTimezoneOffset+ "\n");
		if (country != null) buf.append("country=" + country+ "\n");
		if (lastValidateCallTime != null) buf.append("lastValidateCallTime=" + lastValidateCallTime+ "\n");
		if (spId != null) buf.append("spId=" + spId+ "\n");					// CR 1643 - Pre-Pay Post-Pay Split
		if (isPrepay != null) buf.append("isPrepay=" + isPrepay+ "\n");		// CR 1643 - Pre-Pay Post-Pay Split
		if (timestamp != null) buf.append("timestamp=" + timestamp+ "\n");
		if (originalMsisdn != null) buf.append("originalMsisdn=" + originalMsisdn+ "\n");
//MQC 6510
		if (validateResponseStatus != null) buf.append("validateResponseStatus=" + validateResponseStatus);
		if (spendLimits != null) buf.append("spendLimits=" + spendLimits.toString()); // CR2040 MPAY replacement. MSISDN spend limits.
		if (childSpId != null) buf.append("childSpId=" + childSpId+ "\n");	// CR 2198 - add child spid and service provider type
		if (spType != null) buf.append("spType=" + spType+ "\n");			// CR 2198 - add child spid and service provider type
		buf.append("}");
        return buf.toString();
    }
	
	// CR2040 MPAY replacement. MSISDN spend limits.
	public SpendLimits getSpendLimits() {
		return spendLimits;
	}

	// CR2040 MPAY replacement. MSISDN spend limits.
	public void setSpendLimits(SpendLimits spendLimits) {
		this.spendLimits = spendLimits;
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
	
	public void setUserGroups(UserGroup[] groups){
		this.userGroups = Arrays.asList(groups);
	}
}
