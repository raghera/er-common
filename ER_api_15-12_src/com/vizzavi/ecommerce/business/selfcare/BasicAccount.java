package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.*;

import com.vizzavi.ecommerce.business.charging.AccountValidationAuthorization;
import com.vizzavi.ecommerce.business.charging.PaymentCardDetails;
import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.common.ErCountry;
import com.vizzavi.ecommerce.common.Utils;

import static org.apache.commons.lang.StringUtils.isBlank;

@Entity
@Table(name="er_accounts")
public class BasicAccount implements Serializable {
	private static final long serialVersionUID = -7392495277683348677L;

	/**replaces account_obj_id field in ERAccount*/
	protected Long id;	

	protected List<Subscription> subscriptions;

	protected String msisdn;
	protected String ban;
	protected String name;

	protected int status = AccountStatus.ACTIVE;

	protected List<UserGroup> userGroups = null;
	protected PaymentCardDetails paymentCardDetails = null;

	protected String utcTimezoneOffset;
	protected String homeTimezone;

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

	//JIRA ET148 Add SMS blacklist flag to opt out of courtesy SMS notifications starts here
	//By default we want to send sms notifications:
	//false = suppress is false (double -ve), so send notifications i.e. sms notification ENABLED
	//true  = suppress is true, so do not send notifications i.e. sms notification DISABLED
	//default value is false
	protected Boolean suppressCourtesyNotifications=false;

	//we should probably store an ErCountry object here instead.  
	//this would encapsulate locale, country string and country id.
	//But it could affect ecom clients.
	public Integer	countryId;


    private ReasonCode accountStatus = ReasonCode.OK;
	private String	errorId;
	private String	errorDescription;

	//added for NewAccountBean - promo history records
	private List<SubscriptionPromoCode> promoHistoryEntries;
	private List<ModifyTxn> modifyTxns;
	//JIRA ET148 Add SMS blacklist flag to opt out of courtesy SMS notifications ends here

	public BasicAccount() {
	}

	public BasicAccount(BasicAccount basicAccount) {
		this.msisdn = basicAccount.getMsisdn();
		this.ban = basicAccount.getBan();
		this.name = basicAccount.getName();
		ErCountry country = ErCountry.getCountryByCode(basicAccount.getCountry());
		if (country!=null)
			setCountry(country.getCode());
		//this.locale = basicAccount.getLocale();
		this.status = basicAccount.getStatus();
		UserGroup[] tmpUserGroupArray = basicAccount.getUserGroups();
		if (tmpUserGroupArray != null) {
			ArrayList<UserGroup> tmpUserGroupList = new ArrayList<>();
			for (UserGroup element : tmpUserGroupArray) {
				tmpUserGroupList.add(new UserGroup(element.getName(),
						element.getDescription()));
			}
			this.userGroups = tmpUserGroupList;
		}
		//CR1564: Make sure the copy constructor get this value
		this.homeTimezone = basicAccount.getHomeTimezone();
		this.utcTimezoneOffset = basicAccount.getUtcTimezoneOffset();
		this.setCountry(basicAccount.getCountry());
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

		//JIRA ET148 Add SMS blacklist flag to opt out of courtesy SMS notifications
		this.suppressCourtesyNotifications = basicAccount.getSuppressCourtesyNotifications();
	}

	// CR 2198 - new constructor
	public BasicAccount (String msisdn, String ban, Locale locale, int billingCycleDate,
			String utcTimezoneOffset, String spId, String isPrepay, String childSpId, String spType) {
		this.msisdn = msisdn;
		this.ban = ban;
		setCountry(ErCountry.getByLocale(locale).getCode());
		//this.locale = locale;
		this.billingCycleDate = billingCycleDate;
		this.utcTimezoneOffset = utcTimezoneOffset;
		this.spId = spId;
		this.isPrepay = isPrepay;
		this.childSpId = childSpId;
		this.spType = spType;
	}

	// JIRA ET148 Add SMS blacklist flag to opt out of courtesy SMS notifications - new constructor
	public BasicAccount (String msisdn, String ban, Locale locale, int billingCycleDate,
			String utcTimezoneOffset, String spId, String isPrepay, String childSpId, String spType, Boolean suppressNotifications) {
		this.msisdn = msisdn;
		this.ban = ban;

		setCountry(ErCountry.getByLocale(locale).getCode());

		this.billingCycleDate = billingCycleDate;
		this.utcTimezoneOffset = utcTimezoneOffset;
		this.spId = spId;
		this.isPrepay = isPrepay;
		this.childSpId = childSpId;
		this.spType = spType;
		this.suppressCourtesyNotifications = suppressNotifications;
	}


	public BasicAccount(String msisdn, String ban, String name, Locale locale, int status){
		this.msisdn = msisdn;
		this.ban = ban;
		this.name = name;
		setCountry(ErCountry.getByLocale(locale).getCode());
		//this.locale = locale;
		this.status = status;
	}

	/**the primary key in the DB for this account*/
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="accseq")
	@SequenceGenerator(name="accseq", sequenceName="er_accounts_seq", allocationSize=1	/*, initialValue = 10*/)
	@Column(name="account_obj_id")
	public Long getAccountObjId()	{
		return id;
	}

	protected void setAccountObjId(Long id){
		this.id = id;
	}

	@Column(name="msisdn")	//redundant, since the column name is 'MSISDN'
	public String getMsisdn() {
		return msisdn;
	}

	//hibernate correctly guesses the column name 'BAN'
	public String getBan() {
		return ban;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public void setStatus(Integer status) {
		if(status!=null)
			this.status = status;
	}

	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * quite what a 'name' is for an account, I have no idea
	 * @return
	 */
	public String getName() {
		return name;
	}

	@Transient
	public Locale getLocale() {
		// return locale;
		return ErCountry.getCountryById(countryId).getLocale();
	}

	@Column(name="status_obj_id")
	public int getStatus() {
		return status;
	}

	/**
	 * @deprecated use {@link #getUserGroupList} instead
	 * @return
	 */
	@Deprecated
	@Transient
	public UserGroup[] getUserGroups() {
		if (userGroups != null) {
			return userGroups.toArray(new UserGroup[userGroups.size()]);
		}
		else return null;
	}

	@Transient
	public PaymentCardDetails getPaymentCardDetails() {
		return this.paymentCardDetails;
	}

	public void setPaymentCardDetails(PaymentCardDetails val) {
		this.paymentCardDetails = val;
	}
	//CR1564-start

	@Column(name="HOME_TIMEZONE")
	public String getHomeTimezone() {
		return homeTimezone;
	}

	public void setHomeTimezone(String timezoneName) {
		this.homeTimezone = timezoneName;
	}

	@Transient
	public String getCountry() {
		return ErCountry.getCountryById(countryId).getCode();
	}

	public void setCountry(String country) {
		//this.country = country;
		this.countryId = ErCountry.getCountryByCode(country).getErId();
	}

	@Column(name="BILLING_DATE")
	public int getBillingCycleDate() {
		return billingCycleDate;
	}

	public void setBillingCycleDate(Integer billingCycleDate) {
		if(billingCycleDate != null)
			this.billingCycleDate = billingCycleDate.intValue();
	}

	@Column(name="time_stamp")
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name="LAST_VALIDATE_DATE")
	public Date getLastValidateCallTime() {
		return lastValidateCallTime;
	}

	public void setLastValidateCallTime(Date lastValidateCallTime) {
		this.lastValidateCallTime = lastValidateCallTime;
	}

	@Transient
	public String getUtcTimezoneOffset() {
		return utcTimezoneOffset;
	}

	public void setUtcTimezoneOffset(String utcTimezoneOffset) {
		this.utcTimezoneOffset = utcTimezoneOffset;
	}
	//CR1564-end

	// CR 1643 - Pre-Pay Post-Pay Split
	@Column(name="sp_id")
	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		if (isBlank(spId)) {
			this.spId = null;
		} else {
			this.spId = spId;
		}
	}

	@Column(name="is_prepay")
	public String getIsPrepay() {
		return isPrepay;
	}

	public void setIsPrepay(String isPrepay) {
		if (isBlank(isPrepay)) {
			this.isPrepay = null;
		} else {
			this.isPrepay = isPrepay;
		}
	}
	// CR 1643 - Ends

	//MQC 6416 - store the original msisdn if using a api which changes the msisdn value
	//such as custCare.modifyMsisdn and custCare.inactivateAccount
	@Transient
	public String getOriginalMsisdn() {
		return originalMsisdn;
	}

	public void setOriginalMsisdn(String originalMsisdn) {
		this.originalMsisdn = originalMsisdn;
	}

	//MQC 6510 - store the validate handler response status for the account
	@Transient
	public String getValidateResponseStatus() {
		return this.validateResponseStatus;
	}

	public void setValidateResponseStatus(String validateStatus) {
		this.validateResponseStatus = validateStatus;
	}

	//MQC 7434 Start
	@Transient
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

//		if (userGroups!=null) buf.append("usergroups=" + Utils.StringifyList(userGroups));
		if (paymentCardDetails!=null) buf.append("paymentCardDetails=" + paymentCardDetails.toString());
		// CR1564
		if (homeTimezone != null) buf.append("timezoneName=" + homeTimezone+ "\n");
		if (utcTimezoneOffset != null) buf.append("utcTimezoneOffset=" + utcTimezoneOffset+ "\n");
		//if (country != null) buf.append("country=" + country+ "\n");
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
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "basicAccount", cascade = CascadeType.ALL)
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
	@Column(name="child_sp_id")
	public String getChildSpId() {
		return childSpId;
	}

	/**
	 * CR 2198
	 * @param childSpId the childSpId to set
	 */
	public void setChildSpId(String childSpId) {
		if (isBlank(childSpId)) {
			this.childSpId = null;
		} else {
			this.childSpId = childSpId;
		}
	}

	/**
	 * CR 2198
	 * @return the spType
	 */
	@Column(name="sp_type")
	public String getSpType() {
		return spType;
	}

	/**
	 * CR 2198
	 * @param spType the spType to set
	 */
	public void setSpType(String spType) {
		if (isBlank(spType)) {
			this.spType = null;
		} else {
			this.spType = spType;
		}
	}


	/**
	 * replaces {@link #getUserGroups}
	 */
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="er_accounts_usergroups",
	joinColumns={@JoinColumn(name="ACCOUNT_OBJ_ID")},
	inverseJoinColumns={@JoinColumn(name="usergroup_obj_id")})
	public  List<UserGroup> getUserGroupList()	{
		return userGroups;
	}

	//	@JoinTable(name="er_accounts_usergroups",
	//			   joinColumns={@JoinColumn(name="ACCOUNT_OBJ_ID")},
	//			   inverseJoinColumns={@JoinColumn(name="usergroup_obj_id")})
	public void setUserGroupList(List<UserGroup> groups){
		this.userGroups =groups;
	}

	//	protected void setUserGroups(List<UserGroup> groups){
	//		this.userGroups =groups;
	//	}

	public boolean equals(Object obj)	{
		if (id==null)
			System.err.println("problem with BasicAccount equals");
		if (obj==null)
			return false;
		if (obj == this)
			return true;
		if (obj instanceof BasicAccount)	{
			BasicAccount other = (BasicAccount) obj;
			return other.id.longValue() == id;
		}	else
			return false;
	}

	public int hashCode()	{
		if (id!=null)
			return id.hashCode();
		else {
			System.err.println("problem with BasicAccount hashCode");
			return super.hashCode();
		}
	}

	/**
	 * - not to be used in ER - only used by VISION from the accounts table
	 * - default false
	 * - only accurate if account is retrieved by msisdn
	 * Gets the value of the suppressCourtesyNotifications property.
	 * 
	 */
	@Column(name="SUPPRESS_NOTIFICATIONS")
	public Boolean getSuppressCourtesyNotifications() {
		return suppressCourtesyNotifications;
	}

	/**
	 * not to be used in ER - only used by VISION from the accounts table
	 * - default false
	 * - only accurate if account is retrieved by msisdn
	 * Sets the value of the suppressCourtesyNotifications property.
	 * 
	 */
	public void setSuppressCourtesyNotifications(Boolean value) {
		this.suppressCourtesyNotifications = value;
	}

	//for future use by hibernate
	@OneToMany( mappedBy="account",	fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Column(name="country_obj_id")	
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Transient
	public ReasonCode getAccountStatus()	{
		return this.accountStatus;
	}

	public void setAccountStatus(ReasonCode accountStatus)    {
		this.accountStatus = accountStatus;
	}
	//END REMEDY 6934

	//MQC7141 - start
	public void setErrorDescription(String errorDescription)  	{
		this.errorDescription = errorDescription;
	}

	public void setErrorId(String errorId)  	{
		this.errorId = errorId;
	}

	@Transient
	public String getErrorDescription()  	{
		return this.errorDescription;
	}

	@Transient
	public String getErrorId()  	{
		return this.errorId;
	}

	@Transient
	public String[] getUserGroupNames() {
		if (userGroups != null) {
			List<String> userGroupName = new ArrayList<>();
			for (UserGroup thisUserGroup : userGroups) {
				userGroupName.add(thisUserGroup.getName());
			}
			return userGroupName.toArray((new String[userGroupName.size()]));
		} else
			return null;
	}

	public void setUserGroups(UserGroup[] userGrps) {
		userGroups = new ArrayList<>();
		if (userGrps != null) {
			for (UserGroup userGrp : userGrps) {
				userGroups.add(userGrp);
			}
		}
	}

	public void addSubscription(Subscription sub) {
		sub.packageId = sub.getPackage().getId();
		
		PurchasedService[] servicesList = sub.getPurchasedServices();
		List<PurchasedService> finalServicesList = new ArrayList<PurchasedService>();
		for(PurchasedService purcServ: servicesList){
			purcServ.setSubscription(sub);
			purcServ.setCountryId(this.getCountryId());
			purcServ.setUpdateTimeStamp(new Date());
			finalServicesList.add(purcServ);
		}
		sub.setPurcServiceList(finalServicesList);
		
		getSubscriptions().add(sub);
		sub.account = this;
		
	}

	@OneToMany( mappedBy="id.accountId",	fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public List<SubscriptionPromoCode> getPromoHistoryEntries() {
		return promoHistoryEntries;
	}

	public void setPromoHistoryEntries(List<SubscriptionPromoCode> promoHistoryEntries) {
		this.promoHistoryEntries = promoHistoryEntries;
	}
	
	@OneToMany( mappedBy="account",	fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public List<ModifyTxn> getModifyTxns() {
		return modifyTxns;
	}

	public void setModifyTxns(List<ModifyTxn> modifyTxns) {
		this.modifyTxns = modifyTxns;
	}

	
}
