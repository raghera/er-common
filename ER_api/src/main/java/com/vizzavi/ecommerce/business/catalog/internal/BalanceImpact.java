
package com.vizzavi.ecommerce.business.catalog.internal;

import java.util.Date;
import java.util.HashMap;

import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.Constants;

public class BalanceImpact implements java.io.Serializable, Comparable<BalanceImpact>
{
	private static final long serialVersionUID = 4314537948791993420L;
	private Long mKey;
//	private String mCreatedBy;
//	private String mModifiedBy;
//	private Date mModifiedDate;
//	private char mActiveStatus;

    protected ChargingResource mResource;
    protected double mFixedAmount = 0;
    protected double mScaledAmount = 0;

    /** ADDED FOR EGYPT ER6 STUB **/
    protected int mNotificationThreshold = 0;
    
    //CR1430 START
    protected Date mPriceChangeStartDate;

    /**
	 * @return the mChangePriceStartDate
	 */
	public Date getPriceChangeStartDate() {
		return mPriceChangeStartDate;
	}

	/**
	 * @param mChangePriceStartDate the mChangePriceStartDate to set
	 */
	public void setPriceChangeStartDate(Date priceChangeStartDate) {
		this.mPriceChangeStartDate = priceChangeStartDate;
	}
	//CR1430 END
	

    /** ADDED FOR ER7 STUB **/
    protected String mType;
    //protected String mSuperCreditText;
    private static final String SUPER_CREDIT = "SUPER_CREDIT";
    private static final String CREDIT = "CREDIT";
    //[1] Mod End
    
    /**
     * The multi language support super credit text list.
     * @since R9
     */
    private HashMap<String, String> superCreditTextList = null;
    
    public BalanceImpact(ChargingResource res, double fixedAmount)
    {
        this(res, fixedAmount, 0.0);
    }

    public BalanceImpact(Long key, ChargingResource res, double fixedAmount, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
    {
        this(key, res, fixedAmount, 0.0, createdBy, modifiedBy, modifiedDate, activeStatus);

    }
    public BalanceImpact(ChargingResource res, double fixedAmount, double scaledAmount)
    {
        mResource = res;
        mFixedAmount = fixedAmount;
        mScaledAmount = scaledAmount;
    }

    public BalanceImpact(ChargingResource res, double fixedAmount, double scaledAmount, String type, String superCreditText)
    {
        mResource = res;
        mFixedAmount = fixedAmount;
        mScaledAmount = scaledAmount;
        //AOI, 12-05-05 null chk added
        if(type==null)
            mType = CREDIT;
        else
        	mType = type;
        if ( (mType != null) && mType.equalsIgnoreCase(SUPER_CREDIT)) {
        	mResource.setSuperCredit(true);
            setSuperCreditText( superCreditText);
        }
    }
    
    public BalanceImpact(ChargingResource res, double fixedAmount, double scaledAmount, String type, HashMap<String, String> superCreditText)
    {
        mResource = res;
        mFixedAmount = fixedAmount;
        mScaledAmount = scaledAmount;
        //AOI, 12-05-05 null chk added
        if(type==null)
            mType = CREDIT;
        else
        	mType = type;
        if ( (mType != null) && mType.equalsIgnoreCase(SUPER_CREDIT)) {
        	mResource.setSuperCredit(true);
            setSuperCreditTextList( superCreditText);
        }
    }    
    
	public BalanceImpact(Long key, ChargingResource res, double fixedAmount, double scaledAmount,
                         String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
    {
		mKey = key;
//        mCreatedBy = createdBy;
//		mModifiedBy = modifiedBy;
//        mModifiedDate = modifiedDate;
//    	mActiveStatus = activeStatus;

        mResource = res;
        mFixedAmount = fixedAmount;
        mScaledAmount = scaledAmount;
    }

    public BalanceImpact(ChargingResource res, double fixedAmount, double scaledAmount, int threshold)
    {
        mResource = res;
        mFixedAmount = fixedAmount;
        mScaledAmount = scaledAmount;
		mNotificationThreshold = threshold;
    }

    public BalanceImpact(BalanceImpact impact)
    {
		mKey = impact.mKey;
//        mCreatedBy = impact.mCreatedBy;
//		mModifiedBy = impact.mModifiedBy;
//        mModifiedDate = impact.mModifiedDate;
//    	mActiveStatus = impact.mActiveStatus;

        mResource = impact.mResource;
        mFixedAmount = impact.mFixedAmount;
        mScaledAmount = impact.mScaledAmount;
		mNotificationThreshold = impact.mNotificationThreshold;
        //[1] Mod Start
        //AOI, 12-05-05 Added null chk to default to CREDIT
        if(impact.mType != null)
			mType = impact.mType;
        else
            mType = CREDIT;
        setSuperCreditTextList( impact.getSuperCreditTextList());
        
        //CR1430 START
        mPriceChangeStartDate = impact.mPriceChangeStartDate;
		//CR1430 END
       
    }

	public Long getKey() {
		return mKey;
	}

	public void setKey(Long key){
		mKey = key;
	}

//	public String getCreatedBy() {
//		return this.mCreatedBy;
//	}
//
//	public void setCreatedBy(String createdBy) {
//		this.mCreatedBy= createdBy;
//	}
//
//	public String getModifiedBy() {
//		return this.mModifiedBy;
//	}
//
//	public void setModifiedBy(String modifiedBy) {
//		this.mModifiedBy = modifiedBy;
//	}
//
//	public Date getModifiedDate() {
//		return this.mModifiedDate;
//	}
//
//	public void setModifiedDate(Date modifiedDate) {
//		this.mModifiedDate = modifiedDate;
//	}
//
//	public char getActiveStatus() {
//		return this.mActiveStatus;
//	}
//
//	public void setActiveStatus(char activeStatus) {
//		this.mActiveStatus = activeStatus;
//	}

    @Override
	public String toString()
    {
        return mResource + ":" + mFixedAmount + ":" + mScaledAmount + ":mNotificationThreshold:" + mNotificationThreshold
  
                + ":mType:" + mType + ":mSuperCreditText:" + getSuperCreditText();
  
    }

    public ChargingResource getResource()
    {
        return mResource;
    }


    public double getRate()
    {
        return mFixedAmount;
    }

    public double getRate(double volumeAmount)
    {
        return mFixedAmount + mScaledAmount*volumeAmount;
    }

    public boolean isCurrency()
    {

        return ChargingResource.isCurrencyResource(mResource.getCode());
    }

    public boolean isResource()
    {

        return mResource.isResource();
    }

    public void setRate(double val)
    {
        mFixedAmount = val;
    }

    public static String getId(ChargingResource code)
    {
        return "" + code.getCode();
    }

    public String getId()
    {
        return getId(getResource());
    }

    public double getFixedAmount()
    {
        return mFixedAmount;
    }
    
    public void setFixedAmount(double amount) {
        mFixedAmount = amount;
    }


    public double getScaledAmount()
    {
        return mScaledAmount;
    }
    public void setScaledAmount(double val)
    {
        mScaledAmount = val;
    }

     public void setResource(ChargingResource res)
     {
         mResource = res;
     }

    /** ADDED FOR EGYPT ER6 STUB **/
 	public int getNotificationThreshold()
    {
        return mNotificationThreshold;
    }

    /** ADDED FOR EGYPT ER6 STUB **/
    public void setNotificationThreshold(int notificationThreshold)
    {
        mNotificationThreshold = notificationThreshold;
    }

    /** ADDED FOR ER7 STUB **/
    public String getType() {
    	return mType;
    }
    
    /** ADDED FOR ER7 STUB **/
    public void setType(String type) {
        if(type !=null)
    		mType = type;
        else
            mType = CREDIT;
                                                       //12-05-05 SUPER_CREDITS to SUPER_CREDIT
        if ( (mType != null) && mType.equalsIgnoreCase(SUPER_CREDIT)) {
        	mResource.setSuperCredit(true);
        }
    }

    /** ADDED FOR ER7 STUB **/
    /** Changed in R( */
    public String getSuperCreditText() {
    	return getSuperCreditText(Constants.DEFAULT_LANGUAGE_CODE);
    }

    /** ADDED FOR ER7 STUB **/
    public void setSuperCreditText(String superCreditText) {
    	setSuperCreditText(Constants.DEFAULT_LANGUAGE_CODE, superCreditText);
    }
    
    public String getSuperCreditText(String languageCode){
		if (superCreditTextList == null || superCreditTextList.isEmpty()){
			return null;
		}
		
		if (languageCode == null || languageCode.length() <= 0){
			languageCode = Constants.DEFAULT_LANGUAGE_CODE;
		}
		
		if (superCreditTextList.get(languageCode) != null){
			return superCreditTextList.get(languageCode);
		}
		
		return superCreditTextList.get(Constants.DEFAULT_LANGUAGE_CODE);	
    }
    
    public void setSuperCreditText(String language, String text){
		if (superCreditTextList == null){
			superCreditTextList = new HashMap<String, String>();
		}
		
		if (language == null || language.length() <= 0){
			language = Constants.DEFAULT_LANGUAGE_CODE;
		}
		
        if (superCreditTextList == null){
        	text = new String();
        }
        
        superCreditTextList.put(language, text);    	
    }

	public HashMap<String, String> getSuperCreditTextList() {
		return superCreditTextList;
	}

	public void setSuperCreditTextList(HashMap<String, String> superCreditTextList) {
		this.superCreditTextList = superCreditTextList;
	}

	//CR1430START
	/* 
	 * CR1430 lets you sort by price change start date
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(BalanceImpact thatBI) {

		if (thatBI == null){
			return 1;
		}

		Date thisPCSD = this.getPriceChangeStartDate();
		//BalanceImpact thatBI = (BalanceImpact)o;
		Date thatPCSD = thatBI.getPriceChangeStartDate();

		if (thisPCSD == null && thatPCSD == null){
			//put non-currency > currency 
			return (this.getResource().getCode() - thatBI.getResource().getCode());				

		}else if (thisPCSD == null && thatPCSD != null){

			return -1;

		}else if (thisPCSD != null && thatPCSD == null){

			return 1;
		}else{
			//compare by date
			int x = thisPCSD.compareTo(thatPCSD);
			if (x != 0){
				return x;
			}else{
				//put non-currency > currency
				return (this.getResource().getCode() - thatBI.getResource().getCode());

			}
		}		
	}
	//CR1430END

}

