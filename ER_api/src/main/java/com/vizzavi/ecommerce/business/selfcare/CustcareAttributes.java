package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Locale;

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
}
