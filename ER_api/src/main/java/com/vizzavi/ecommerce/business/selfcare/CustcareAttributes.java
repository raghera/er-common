package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Locale;
import java.util.Set;

/*
nayera Min Subscription Period - German Migration 
new class to hold the extra attributes including boolean for applyPenalty
*/
public class CustcareAttributes implements Serializable
{
    private    static final long serialVersionUID = 3320187176159506804L;

	boolean applyPenaltyCharge = true;
	String newMsisdn = "";
	String newBan = "";
	int newChargingMethod = -1;
	String csrId = "";
	String reason = "";
	String packageSubId = "";
	//MQC 5789 - add msisdn
	String msisdn = "";
	//MQC9420 flag to immediately inactive a subscription if it is in grace or suspended status
	boolean inactivateGraceOrSuspendedSubscription;
	
	//CR1331 - add flag to indicate whether inactivate subscription is called via the modify tariff api
	boolean modifyTariffInactivateSub = false;
	
	//CR1349 - add flag to override the disallow cancellation flag, set true for ER Batch
	boolean overrideDisallowCancellationFlag = false;
	
	//CR2303p5 START
	/**
	 * all user's subscriptions to enable ER Core to determine whether a de-provision involves the last subscription
	 * for a given partner id in order to notify PNH
	 */ 
	Subscription[] allSubscriptions;
	/**
	 * Flag to indicate getSubscriptions was already called for user and won't need to be called again during de-provisioning
	 * if global PNH is required. It's not essential but setting it to true makes it clear getSubscriptions has already
	 * been called.
	 */
	boolean hasNoOtherSubscriptions = false;
	
	/**
	 * Long story - set during changeMsisdn so that deactivate provision calls
	 * will set sub status to beingProvisioned AFTER provision message sub status has been added
	 * but BEFORE count of active subs is performed 
	 * to fulfil last_serviceclass_sub functionality 
	 */
	boolean setBeingProvisioned = false;
	
	/**
	 * Longer story - set during inactivateAccount so that deactivate provision calls
	 * will keep track of which provision messages include subcontext = LAST_SERVICECLASS_SUB
	 * so that don't send twice in order to fulfil last_serviceclass_sub functionality 
	 * @return
	 */
	Set<String> lastServiceClassSent;
	//CR2303p5 END
	
	/**
	 * set during inactivateAccount so that deactivate provision calls
	 * will keep track of which provision messages include subcontext = LAST_PARTNER_SUB
	 * so that don't send twice in order to fulfil last_partner_sub functionality 
	 * 
	 */
	Set<String> lastPartnerSubSent;
	
	
	
	//JIRA-ET486 - Add optional context field to inactivate-subscription decoupling call
	String context;
	
	public boolean isApplyPenaltyCharge(){

		return applyPenaltyCharge;
	}
	
	public void setApplyPenaltyCharge ( boolean x ){
	
		applyPenaltyCharge = x;
	}

	public String getNewMsisdn(){

		return newMsisdn;
	}
	
	public void setNewMsisdn ( String x ){
	
		newMsisdn = x;
	}
	
	public String getNewBan(){

		return newBan;
	}
	
	public void setNewBan ( String x ){
	
		newBan = x;
	}

	public int getNewChargingMethod(){

		return newChargingMethod;
	}

	public void setNewChargingMethod ( int x ){
	
		newChargingMethod = x;
	}
	
	public String getCsrId(){

		return csrId;
	}
	
	public void setCsrId ( String x ){
	
		csrId = x;
	}
	
	public String getReason(){

		return reason;
	}
	
	public void setReason ( String x ){
	
		reason = x;
	}

	public String getPackageSubId()
	{
		if (packageSubId == null) {
			if (mPackageSubIdLong > 0) {
				packageSubId = Long.toString(mPackageSubIdLong);
			}
		}

		return packageSubId;
	}
	
	public void setPackageSubId ( String x ){
	
		packageSubId = x;
	}

	//@hud RF FORBIDDEN
	long mPackageSubIdLong = -1;

	
	//@hud RFRFRF
	public long getPackageSubIdLong() {
		if (mPackageSubIdLong == -1 || mPackageSubIdLong==0) {
			if (packageSubId != null) {
				try {
					mPackageSubIdLong = Long.parseLong(packageSubId) ;
				}
				catch (NumberFormatException ne) {
					// nothing
					mPackageSubIdLong = -2;
				}
			}
		}
		return mPackageSubIdLong;
	}
	public void setPackageSubIdLong(long id) {
		mPackageSubIdLong = id;
	}

	//@hud STKHREQ12557
	private Locale mLanguageLocale = null;
	public Locale getLanguageLocale() {
		return mLanguageLocale;
	}
	public void setLanguageLocale(Locale languageLocale) {
		mLanguageLocale = languageLocale;
	}
	public String getLanguageCode() {
		if (mLanguageLocale == null) {
			return null;
		}
		else {
			return mLanguageLocale.getLanguage();
		}
	}
	
	//CR1331 - add flag to indicate whether inactivate subscription is called via the modify tariff api
	public boolean isModifyTariffInactivateSub(){

		return modifyTariffInactivateSub;
	}
	
	public void setModifyTariffInactivateSub ( boolean modifyTariffCall ){
	
		modifyTariffInactivateSub = modifyTariffCall;
	}
	
	//CR1349 - add flag to override the disallow cancellation flag, set true for ER Batch
	public boolean isOverrideDisallowCancellationFlag(){

		return overrideDisallowCancellationFlag;
	}
	
	public void setOverrideDisallowCancellationFlag ( boolean overrideFlag ){
	
		overrideDisallowCancellationFlag = overrideFlag;
	}
	
	//MQC 5789
	public String getMsisdn(){

		return msisdn;
	}
	
	public void setMsisdn ( String msisdnNo ){
	
		msisdn = msisdnNo;
	}
	
	/**
	 * MQC9420 flag to immediately inactive a subscription if it is in grace or suspended status
	 * @return inactivateGraceOrSuspendedSubscription
	 */
	public boolean isInactivateGraceOrSuspendedSubscription(){

		return inactivateGraceOrSuspendedSubscription;
	}
	
	/**
	 * MQC9420 flag to immediately inactive a subscription if it is in grace or suspended status
	 * @param x, the inactivateGraceOrSuspendedSubscription to set
	 */
	public void setInactivateGraceOrSuspendedSubscription ( boolean x ){
	
		inactivateGraceOrSuspendedSubscription = x;
	}
	
	//CR2303p5 START
	public Subscription[] getAllSubscriptions() {
		return allSubscriptions;
	}

	public void setAllSubscriptions(Subscription[] allSubscriptions) {
		this.allSubscriptions = allSubscriptions;
	}

	public boolean hasNoOtherSubscriptions() {
		return hasNoOtherSubscriptions;
	}

	public void setNoOtherSubscriptions(boolean hasNoOtherSubscriptions) {
		this.hasNoOtherSubscriptions = hasNoOtherSubscriptions;
	}

	public boolean isSetBeingProvisioned() {
		return setBeingProvisioned;
	}

	public void setSetBeingProvisioned(boolean setBeingProvisioned) {
		this.setBeingProvisioned = setBeingProvisioned;
	}

	public Set<String> getLastServiceClassSent() {
		return lastServiceClassSent;
	}

	public void setLastServiceClassSent(Set lastServiceClassSent) {
		this.lastServiceClassSent = lastServiceClassSent;
	}
	
	
	//CR2303p5 END
	 
	//JIRA ET148 Add SMS blacklist flag to opt out of courtesy SMS notifications
	boolean suppressCourtesyNotifications = false;
	
	/**
	 * - not to be used in ER - only used by VISION from the accounts table
     * - default true
     * - only accurate if account is retrieved by msisdn
     * Gets the value of the suppressCourtesyNotifications property.
     * 
     */
    public boolean getSuppressCourtesyNotifications() {
        return suppressCourtesyNotifications;
    }

    /**
     * not to be used in ER - only used by VISION from the accounts table
     * - default true
     * - only accurate if account is retrieved by msisdn
     * Sets the value of the suppressCourtesyNotifications property.
     * 
     */
    public void setSuppressCourtesyNotifications(boolean value) {
        this.suppressCourtesyNotifications = value;
    }
    
	/**
	 * @return the lastPartnerSubSent
	 */
	public Set<String> getLastPartnerSubSent() {
		return lastPartnerSubSent;
	}

	/**
	 * set the lastPartnerSubSentSent
	 * @param lastPartnerSubSent
	 */
	public void setLastPartnerSubSent(Set<String> lastPartnerSubSent) {
		this.lastPartnerSubSent = lastPartnerSubSent;
	}
	/**
	 * JIRA-ET486 - Add optional context field to inactivate-subscription decoupling call
	 * @return String
	 */
	public String getContext() {
		return context;
	}

	/**
	 * JIRA-ET486 - Add optional context field to inactivate-subscription decoupling call
	 * @param context
	 */
	public void setContext(String context) {
		this.context = context;
	}
}
