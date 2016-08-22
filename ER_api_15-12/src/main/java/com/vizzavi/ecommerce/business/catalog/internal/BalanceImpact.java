
package com.vizzavi.ecommerce.business.catalog.internal;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.common.ChargingResource;

@Entity
public class BalanceImpact implements Serializable, Comparable<BalanceImpact>	{
	
	private static final long serialVersionUID = 4314537948791993420L;
	private Long mKey;


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
//    private static final String SUPER_CREDIT = "SUPER_CREDIT";
    private static final String CREDIT = "CREDIT";
    //[1] Mod End
    
    /**
     * The multi language support super credit text list.
     * @since R9
     */
//    private HashMap<String, String> superCreditTextList = null;
	private PricePoint	ppt;
    
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

    }
    
    public BalanceImpact(ChargingResource res, double fixedAmount, double scaledAmount, String type, HashMap<String, String> superCreditText)
    {
        this(res, fixedAmount, scaledAmount, type);
    }    
    
    public BalanceImpact(ChargingResource res, double fixedAmount, double scaledAmount, String type)
    {
        mResource = res;
        mFixedAmount = fixedAmount;
        mScaledAmount = scaledAmount;
        //AOI, 12-05-05 null chk added
        if(type==null)
            mType = CREDIT;
        else
        	mType = type;
    }  
    
	public BalanceImpact(Long key, ChargingResource res, double fixedAmount, double scaledAmount,
                         String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
    {
		mKey = key;

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
       // setSuperCreditTextList( impact.getSuperCreditTextList());
        
        //CR1430 START
        mPriceChangeStartDate = impact.mPriceChangeStartDate;
		//CR1430 END
       
    }
    
    public BalanceImpact()    {/*default xtr for hibernate*/  }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="`KEY`")
	public Long getKey() {
		return mKey;
	}

	public void setKey(Long key){
		mKey = key;
	}



    @Override
	public String toString()    {
        return mResource + ": " + mFixedAmount + "; scaled  " + mScaledAmount + "; NotificationThreshold:" + mNotificationThreshold
  
                + ": mType:" + mType ;
  
    }

	@Column(name="chg_res")
	int getChargingResourceId()	{
		return mResource.getCode();
	}

	void setChargingResourceId(int id)	{
		mResource = ChargingResource.getResource(id);
	}

    @Transient
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

    @Transient
    public boolean isCurrency()
    {

        return ChargingResource.isCurrencyResource(mResource.getCode());
    }

    @Transient
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

    @Transient
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

    @Transient
    public String getType() {
    	return mType;
    }
    
    /** ADDED FOR ER7 STUB **/
    public void setType(String type) {
        if(type !=null)
    		mType = type;
        else
            mType = CREDIT;

    }

//    /** ADDED FOR ER7 STUB **/
//    /** Changed in R( */
//    @Transient
//    public String getSuperCreditText() {
//    	return getSuperCreditText(Constants.DEFAULT_LANGUAGE_CODE);
//    }
//
//    /** ADDED FOR ER7 STUB **/
//    public void setSuperCreditText(String superCreditText) {
//    	setSuperCreditText(Constants.DEFAULT_LANGUAGE_CODE, superCreditText);
//    }
//    
//    public String getSuperCreditText(String languageCode){
//		if (superCreditTextList == null || superCreditTextList.isEmpty()){
//			return null;
//		}
//		
//		if (languageCode == null || languageCode.length() <= 0){
//			languageCode = Constants.DEFAULT_LANGUAGE_CODE;
//		}
//		
//		if (superCreditTextList.get(languageCode) != null){
//			return superCreditTextList.get(languageCode);
//		}
//		
//		return superCreditTextList.get(Constants.DEFAULT_LANGUAGE_CODE);	
//    }
//    
//    public void setSuperCreditText(String language, String text){
//		if (superCreditTextList == null){
//			superCreditTextList = new HashMap<String, String>();
//		}
//		
//		if (language == null || language.length() <= 0){
//			language = Constants.DEFAULT_LANGUAGE_CODE;
//		}
//		
//        if (superCreditTextList == null){
//        	text = new String();
//        }
//        
//        superCreditTextList.put(language, text);    	
//    }
//
//    @Transient
//    public HashMap<String, String> getSuperCreditTextList() {
//		return superCreditTextList;
//	}
//
//	public void setSuperCreditTextList(HashMap<String, String> superCreditTextList) {
//		this.superCreditTextList = superCreditTextList;
//	}

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

	@ManyToOne(optional=false, targetEntity=PricePoint.class, fetch=FetchType.LAZY)	
	public PricePoint getPricePoint()	{
		return ppt;
	}
	
	public void setPricePoint(PricePoint ppt)	{
		this.ppt = ppt;
	}
}

