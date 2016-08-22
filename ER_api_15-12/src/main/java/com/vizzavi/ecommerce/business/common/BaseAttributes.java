package com.vizzavi.ecommerce.business.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.vizzavi.ecommerce.business.selfcare.Subscription;

/**
 * Marker interface for all attribute classes
 */
public class BaseAttributes implements java.io.Serializable
{
	private    static final long serialVersionUID = 1462287896071663103L;
	
	// VFE-ER8.0 - P2 (Access Channel Reporting). This field is added to Store the Access Channel in the base attributes **/
	protected String mAccessChannel;
	
	// VFE-ER8.0 - P2 (Purchase Channel Reporting). This field is added to Store the Purchase Channel in the base attributes **/
	protected String mPurchaseChannel;
	
	// VFE-ER8.0 - P2 (Distribution Channel Reporting). This field is added to Store the Device ID in the base attributes **/
	protected String mDeviceID;

	//@hud add moer attributes here for future extensible implementation
	protected String mMsisdn;
	protected Locale mLocal;
	
	//@mawn R9.0 for Arabic Support / Multi Language Support . This Locale reflects Locale that indicates the 
	// language for user experience. 
	// mLocale is used for purcahse Locale.
	protected Locale mLanguageLocale = null;
	
	//@mawn R8.0 STKHREQ10301 missing implementation.  Added in R9.0 CQPRD00015358
	protected String mExternalTransId;
	
	protected String mExternalField1 ;
	
	protected String mExternalField2 ;
	
	//CR1542 - add list of Active Subscriptions which can be stored and then passed onto
	// calls within multiple call flows, such as force purchase, thus avoiding additional
	// database calls
	
	protected List<Subscription> mActiveSubscriptions = null;
	
	protected boolean mHasActiveSubscriptions = false;
	
	//MQC 7733 - add csr id
	protected String mCsrId;
	
	//@hud RFRFRF
	public void normalize()
	{
	}
	
	
	
	/*   Default constructor.
	 */
	
	public BaseAttributes()
	{
	}
	
	//@hud RFRFRF STKHREQ12557 ER9
	public BaseAttributes(BaseAttributes attrs) 
	{
		// before writing this, I don't see this method
		// so now, I only set the languange code
		if (attrs != null) {
			mLanguageLocale = attrs.getLanguageLocale();
		}
	}
	
	/**
	 * @version        ER 8.0 - P2
	 * @author         VFE  PS Team
	 * @date           18-Sept-2005
	 *  @description  (Access Channel Reporting).  The purpose of this method is to get the Access Channel from the base attributes . 
	 **/
	public String getAccessChannel ()
	{
		return mAccessChannel;
	}
	
	/**
	 * @version        ER 8.0 - P2
	 * @author         VFE  PS Team
	 * @date           18-Sept-2005
	 *  @description  (Access Channel Reporting).  The purpose of this method is to set the Access Channel in the base attributes . 
	 **/
	public void  setAccessChannel (String AccessChannel)
	{
		mAccessChannel = AccessChannel;
	}
	
	/**
	 * @version        ER 8.0 - P2
	 * @author         VFE  PS Team
	 * @date           18-Sept-2005
	 *  @description  (Purchase Channel Reporting).  The purpose of this method is to get the Purchase Channel from the base attributes . 
	 **/
	public String getPurchaseChannel ()
	{
		return mPurchaseChannel;
	}
	
	/**
	 * @version        ER 8.0 - P2
	 * @author         VFE PS Team
	 * @date           18-Sept-2005
	 *  @description  (Purchase Channel Reporting).  The purpose of this method is to set the Purchase Channel in the base attributes . 
	 **/
	public void  setPurchaseChannel (String PurchaseChannel)
	{
		mPurchaseChannel = PurchaseChannel;
	}
	
	/**
	 * @version        ER 8.0 - P2
	 * @author         VFE PS Team
	 * @date           18-Sept-2005
	 *  @description  (Distribution Channel Reporting).  The purpose of this method is to get the Device ID from the base attributes . 
	 **/
	public String getDeviceID ()
	{
		return mDeviceID;
	}
	
	/**
	 * @version        ER 8.0 - P2
	 * @author         VFE PS Team
	 * @date           18-Sept-2005
	 *  @description  (Distribution Channel Reporting).  The purpose of this method is to set the Device ID in the base attributes . 
	 **/
	public void  setDeviceID (String DeviceID)
	{
		mDeviceID = DeviceID;
	}
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();

	}

	/**
	 * @return Returns the mLocal.
	 */
	public Locale getLocal() {
		return mLocal;
	}

	/**
	 * @param local The mLocal to set.
	 */
	public void setLocal(Locale local) {
		mLocal = local;
	}

	/**
	 * @return Returns the mMsisdn.
	 */
	public String getMsisdn() {
		return mMsisdn;
	}

	/**
	 * @param msisdn The mMsisdn to set.
	 */
	public void setMsisdn(String msisdn) {
		mMsisdn = msisdn;
	}



	//@nawn STKHREQ12557
	public Locale getLanguageLocale() {
		return mLanguageLocale;
	}
	public void setLanguageLocale(Locale languageLocale) {
		this.mLanguageLocale = languageLocale;
	}
	
	//@hud Added STKHREQ12557
	public String getLanguageCode() {
		if (mLanguageLocale == null) {
			return null;
		}
		else {
			String languageCode = mLanguageLocale.getLanguage();
			
			if (languageCode!=null )
				return languageCode.substring(0,2);
			else 
				return null ;
		}
	}



	public String getExternalField1() {
		return mExternalField1;
	}



	public void setExternalField1(String externalField1) {
		mExternalField1 = externalField1;
	}



	public String getExternalField2() {
		return mExternalField2;
	}



	public void setExternalField2(String externalField2) {
		mExternalField2 = externalField2;
	}



	public String getExternalTransId() {
		return mExternalTransId;
	}



	public void setExternalTransId(String externalTransId) {
		mExternalTransId = externalTransId;
	}
	
	//CR1542
	public List<Subscription> getActiveSubscriptions()
	{
        return mActiveSubscriptions;
    }

	//CR1542
	public void setActiveSubscriptions(List<Subscription> subs)
	{
		if (subs != null) {
			mActiveSubscriptions = new ArrayList<Subscription>();
			mActiveSubscriptions = subs;
		}	
    }
	
	//CR1542
	public boolean hasActiveSubscriptions() {
		return mHasActiveSubscriptions;
	}

	//CR1542
	public void setHasActiveSubscriptions(boolean hasActiveSubs) {
		mHasActiveSubscriptions = hasActiveSubs;
	}
	
	
	/**
	 * MQC 7733 - return csr id
	 * @return String
	 */
	public String getCsrId()
	{
		return mCsrId;
	}
	
	/**
	 * MQC 7733 - set csr id
	 * @param csrId
	 */
	public void setCsrId(String csrId)
	{
		mCsrId = csrId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mAccessChannel == null) ? 0 : mAccessChannel.hashCode());
		result = prime
				* result
				+ ((mActiveSubscriptions == null) ? 0 : mActiveSubscriptions
						.hashCode());
		result = prime * result + ((mCsrId == null) ? 0 : mCsrId.hashCode());
		result = prime * result
				+ ((mDeviceID == null) ? 0 : mDeviceID.hashCode());
		result = prime * result
				+ ((mExternalField1 == null) ? 0 : mExternalField1.hashCode());
		result = prime * result
				+ ((mExternalField2 == null) ? 0 : mExternalField2.hashCode());
		result = prime
				* result
				+ ((mExternalTransId == null) ? 0 : mExternalTransId.hashCode());
		result = prime * result + (mHasActiveSubscriptions ? 1231 : 1237);
		result = prime * result
				+ ((mLanguageLocale == null) ? 0 : mLanguageLocale.hashCode());
		result = prime * result + ((mLocal == null) ? 0 : mLocal.hashCode());
		result = prime * result + ((mMsisdn == null) ? 0 : mMsisdn.hashCode());
		result = prime
				* result
				+ ((mPurchaseChannel == null) ? 0 : mPurchaseChannel.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BaseAttributes))
			return false;
		BaseAttributes other = (BaseAttributes) obj;
		if (mAccessChannel == null) {
			if (other.mAccessChannel != null)
				return false;
		} else if (!mAccessChannel.equals(other.mAccessChannel))
			return false;
		if (mActiveSubscriptions == null) {
			if (other.mActiveSubscriptions != null)
				return false;
		} else if (!mActiveSubscriptions.equals(other.mActiveSubscriptions))
			return false;
		if (mCsrId == null) {
			if (other.mCsrId != null)
				return false;
		} else if (!mCsrId.equals(other.mCsrId))
			return false;
		if (mDeviceID == null) {
			if (other.mDeviceID != null)
				return false;
		} else if (!mDeviceID.equals(other.mDeviceID))
			return false;
		if (mExternalField1 == null) {
			if (other.mExternalField1 != null)
				return false;
		} else if (!mExternalField1.equals(other.mExternalField1))
			return false;
		if (mExternalField2 == null) {
			if (other.mExternalField2 != null)
				return false;
		} else if (!mExternalField2.equals(other.mExternalField2))
			return false;
		if (mExternalTransId == null) {
			if (other.mExternalTransId != null)
				return false;
		} else if (!mExternalTransId.equals(other.mExternalTransId))
			return false;
		if (mHasActiveSubscriptions != other.mHasActiveSubscriptions)
			return false;
		if (mLanguageLocale == null) {
			if (other.mLanguageLocale != null)
				return false;
		} else if (!mLanguageLocale.equals(other.mLanguageLocale))
			return false;
		if (mLocal == null) {
			if (other.mLocal != null)
				return false;
		} else if (!mLocal.equals(other.mLocal))
			return false;
		if (mMsisdn == null) {
			if (other.mMsisdn != null)
				return false;
		} else if (!mMsisdn.equals(other.mMsisdn))
			return false;
		if (mPurchaseChannel == null) {
			if (other.mPurchaseChannel != null)
				return false;
		} else if (!mPurchaseChannel.equals(other.mPurchaseChannel))
			return false;
		return true;
	}
}
