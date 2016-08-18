package com.vodafone.global.er.rating;

import com.vizzavi.ecommerce.business.catalog.NetworkCode;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.common.Constants;
import com.vizzavi.ecommerce.business.common.RatingAttributes;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;

import java.util.Date;

/**
    This is returned by a content usage
 */
public class RatedEvent implements java.io.Serializable
{
	private    static final long serialVersionUID = -1459104543868625485L;
	//private static final Logger	logger = LoggerFactory.getLogger(RatedEvent.class);


	//@hud STKHREQ13076 SP ROAMNIG
	// Record the roaming net amount and roaming gross amount, by default, it's 0, meaning no effect
	protected double mRoamingNetAmount	= 0;
	protected double mRoamingGrossAmount	= 0;	// including tax

	////////////////////////////////////////////////////////
	//@hud STKHREQ13076 SP ROAMING
	protected NetworkCode	mNetworkCode = null;



	//@hud STKHREQ36
	protected double mAccessDuration = -1;		// in minutes
	protected String mPricePointId = null;	// This is the id that is set during rating
											// indicating which pricepoint is matched

	/**
        Description of the event
        This is used by the rating system to provide information on the event
	 */
	protected String mDescription;

	/**
        The rating system identifier.
	 */
	protected String mRatingSystemId;

	/**
        The version of the API
	 */
	protected String mVersion;

	/**
        Time stamp of when the rate was rated
	 */
	protected Date mTimeStamp;


	/**
        True if successful rating
	 */
	protected boolean mIsSuccess=true;

	/**
        The net price of the rate
        This is the tax inclusive price
	 */
	protected double mNetRate;

	/**
        The currency id
	 */
	protected int mCurrencyId;

	/**
        The discounted price
	 */
	protected double mNetStandardRate;

	/**
        There is a promo code associated withthis rate
        The purchase app can use this to know that the user can associate a promo code with this rate
	 */
	protected boolean mIsPromoCode = false;

	//@hud STKHREQ13107
	protected boolean mIsUniquePromoCode = false;

	protected boolean mIsDiscount = false;
	/**
        This can be used to identify the price. It allows quick rating to be performed
        It doesn't indentify the tax or discount percentage
	 */
	protected String mRateIdentifier;

	/**
        The text if there is a discount
	 */
	protected String mDiscountPromoText;

	/**
        Was this rate an alternative payment method price
	 */
	protected boolean mIsAlternativePaymentMethod = false;

	/**
        This will contain the attributes that matched the input parameters

	 */
	protected RatingAttributes mMatchingAttributes;

	protected RatingAttributes mInputAttributes;

	protected String mPackageId = Constants.STRING_MATCH_ALL;

	protected String mContentId = Constants.STRING_MATCH_ALL;

	protected String mPricingText1 = "";

	protected String mPricingText2 = "";


	protected double mDiscountPercentage;

	// VFE-ER8.0 (Record Undiscounted price on discounted purchases).  This part is added to Store the standard rate of the undiscounted pricepoint.
	protected double mStandardRate;

	/**
	 * the alternative curreny id (the currency id of the alternative price point)
	 * @todo Has to be reviewed
	 * @version ER8-2
	 */
	private int mAlternativeCurrencyId;
	/**
	 * the alternative rate (the rate of the alternative price point)
	 * @todo Has to be reviewed
	 * @version ER8-2
	 */
	private double mAlternativeNetRate;

	/**
	 * the rated identifiers of multiple subscriptions in case of usage from
	 * multiple subscriptions
	 *
	 * @todo Has to be reviewed
	 * @version ER8-2
	 */
	protected String[] mRateIdentifiers;
	protected double mEventUnits = 0;

	/** ER9.0 STKHREQ 13022 @mawn usagetime of a volume service **/

	protected double usageTime ;

	protected boolean zeroCostIgnore  = false ;

	//MQC 5067 - add custom resource balances
	protected ResourceBalance[] customResourceBalances;
	
	//CR1429
	protected boolean alwaysValidateMsisdn = false;


	
	
	public RatedEvent()
	{
	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public RatedEvent(RatedEvent event) {
        this(event, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
	public RatedEvent(RatedEvent event, Date date)
	{
		mDescription = event.mDescription;
		mTimeStamp = event.mTimeStamp;
		mRatingSystemId = event.mRatingSystemId;
		mVersion = event.mVersion;
		mIsSuccess = event.mIsSuccess;
		mNetRate = event.mNetRate;
		mNetStandardRate = event.mNetStandardRate;
		mIsPromoCode = event.mIsPromoCode;
		mCurrencyId = event.mCurrencyId;
		mRateIdentifier = event.mRateIdentifier;
		mDiscountPromoText = event.mDiscountPromoText;
		mIsDiscount = event.mIsDiscount;
		mIsAlternativePaymentMethod = event.mIsAlternativePaymentMethod;
		mAlternativeCurrencyId = event.mAlternativeCurrencyId;
		mAlternativeNetRate = event.mAlternativeNetRate;
		mPackageId = event.mPackageId;
		mContentId = event.mContentId;
		mPricingText1 = event.mPricingText1;
		mPricingText2 = event.mPricingText2;
		if (event.getMatchingAttributes()!=null) {
			if(event.getMatchingAttributes() instanceof PricePoint)	{
				//logger .debug("MD pprf: event.getMatchingAttributes() is a pricepoint");
				mMatchingAttributes = new PricePoint(event.getMatchingAttributes(), date);
			}
			else	{
				//logger .debug("MD pprf: event.getMatchingAttributes() is not a pricepoint but rather a {}", 
				//		event.getMatchingAttributes()!=null?event.getMatchingAttributes().getClass().getSimpleName():"null");
				mMatchingAttributes = new RatingAttributes(event.getMatchingAttributes());
			}
		}
		if (event.getInputAttributes()!=null) {
			mInputAttributes = new RatingAttributes(event.getInputAttributes());
		}
		mRateIdentifiers = event.mRateIdentifiers;
		usageTime = event.usageTime;

		mEventUnits = event.getEventUnits();

		//@hud STKHREQ13107
		mIsUniquePromoCode = event.isUniquePromoCode();


		//@hud STKHREQ13076 SP ROAMNIG
		mRoamingNetAmount = event.getRoamingNetAmount();
		mRoamingGrossAmount = event.getRoamingGrossAmount();

		//@hud STKHREQ13076 SP ROAMNIG
		mNetworkCode = event.getNetworkCode();


		//@hud STKHREQ36
		mAccessDuration = event.getAccessDuration();
		mPricePointId = event.getPricePointId();

		// zero cost transaction ignore
		zeroCostIgnore = event.zeroCostIgnore ;
		
		//MQC 5067 - set the custom resource balances
		customResourceBalances = event.customResourceBalances;

	}

	//////////////////////////////////////////////////////
	//@hud STKHREQ13076 SP ROAMNIG
	public double getRoamingNetAmount() {
		return mRoamingNetAmount;
	}
	public void setRoamingNetAmount(double roamingNetAmount) {
		mRoamingNetAmount = roamingNetAmount;
	}
	public double getRoamingGrossAmount() {
		return mRoamingGrossAmount;
	}
	public void setRoamingGrossAmount(double roamingGrossAmount) {
		mRoamingGrossAmount = roamingGrossAmount;
	}
	///////////////////////////////////////////////////////



	/**
        Text associated with this rate
	 */
	 public String getDescription()
	{
		return mDescription;
	}

	/**
        The time the event was rated
	 */
	 public Date getTimestamp()
	 {
		 return mTimeStamp;
	 }


	 /**
        Used to identify the system doing the rating
	  */
	 public String getRatingSystemId()
	 {
		 return mRatingSystemId;
	 }

	 /**
        The version of the system
	  */
	 public String getRatingSystemVersion()
	 {
		 return mVersion;
	 }

	 /**
        This means that the rate was successfully rated
	  */
	 public boolean isSuccess()
	 {
		 return mIsSuccess;
	 }

	 /**
        This means that the price is a promotional price
	  */
	 public boolean isDiscount()
	 {
		 return mIsDiscount;
	 }

	 /**
        If a promotion, retrieve the associated promotional text
	  */
	 public String getDiscountPromoText()
	 {
		 return mDiscountPromoText;
	 }

	 /**
        Retrieve the standard rate
        This is the price including tax
	  */
	 public double getNetStandardRate()
	 {
		 return mNetStandardRate;
	 }

	 /**
        The price retrieved
        This is the price including tax
	  */
	 public double getNetRate()
	 {
		 return mNetRate;
	 }


	 /**
        Retrieve the currency id
	  */
	 public int getCurrencyId()
	 {
		 return mCurrencyId;
	 }

	 public boolean isAlternativePaymentMethod()
	 {
		 return mIsAlternativePaymentMethod;
	 }

	 /**
        This shows the attributes that matched in the rating system
	  */
	 public RatingAttributes getMatchingAttributes()
	 {
		 return mMatchingAttributes;
	 }

	 public RatingAttributes getInputAttributes()
	 {
		 return mInputAttributes;
	 }

	 public double getDiscountPercentage()
	 {
		 return mDiscountPercentage;
	 }



	 public void setDescription(String val)
	 {
		 mDescription = val;
	 }

	 public void setTimestamp(Date val)
	 {
		 mTimeStamp = val;
	 }


	 /**

	  */
	  public void setRatingSystemId(String val)
	  {
		  mRatingSystemId = val;
	  }

	  public void setRatingSystemVersion(String val)
	  {
		  mVersion = val;
	  }



	  /**
        This means that the rate was successfully rated
	   */
	  public void setIsSuccess(boolean val)
	  {
		  mIsSuccess = val;
	  }

	  /**
        This means that the price is a promotional price
	   */
	  public void setIsPromoCode(boolean val)
	  {
		  mIsPromoCode = val;
	  }


	  /**
        This means that the price is a promotional price
	   */
	  public void setIsDiscount(boolean val)
	  {
		  mIsDiscount = val;
	  }

	  /**
        If a promotion, retrieve the associated promotional text
	   */
	  public void setDiscountPromoText(String val)
	  {
		  mDiscountPromoText = val;
	  }

	  /**
        Retrieve the standard rate
        This is the price including tax
	   */
	  public void setNetStandardRate(double val)
	  {
		  mNetStandardRate = val;
	  }

	  /**
        The price retrieved
        This is the price excluding tax
	   */
	  public void setNetRate(double val)
	  {
		  mNetRate = val;
	  }


	  /**
        Retrieve the currency id
	   */
	  public void setCurrencyId(int val)
	  {
		  mCurrencyId = val;
	  }

	  public void setIsAlternativePaymentMethod(boolean val)
	  {
		  mIsAlternativePaymentMethod = val;
	  }

	  public void setMatchingAttributes(RatingAttributes attrs)
	  {
		  mMatchingAttributes = attrs;
	  }

	  public void setInputAttributes(RatingAttributes attrs)
	  {
		  mInputAttributes = attrs;
	  }


	  public void setDiscountPercentage(double val)
	  {
		  mDiscountPercentage = val;
	  }

	  public String getPackageId()
	  {
		  return mPackageId;
	  }

	  public void setPackageId(String val)
	  {
		  mPackageId = val;
	  }

	  public String getContentId()
	  {
		  return mContentId;
	  }

	  public void setContentId(String val)
	  {
		  mContentId = val;
	  }

	  /**
        This returns associated text with this rated event
	   */
	  public String getPricingText1()
	  {
		  return mPricingText1;
	  }

	  /**
        This returns associated text with this rated event
	   */
	  public String getPricingText2()
	  {
		  return mPricingText2;
	  }

	  public void setPricingText1(String val)
	  {
		  mPricingText1 = val;
	  }

	  public void setPricingText2(String val)
	  {
		  mPricingText2 = val;
	  }

	  @Override
	public String toString()
	  {
		  StringBuffer buf = new StringBuffer();
		  buf.append("success=").append(isSuccess()).append(",");
		  buf.append("netRate=").append(getNetRate()).append(",");
		  buf.append("netStandardRate=").append(getNetStandardRate()).append(",");
//		  buf.append("promo=").append(isPromoCode()).append(",");
		  buf.append("discount=").append(isDiscount()).append(",");
		  buf.append("currencyId=").append(getCurrencyId()).append(",");
		  buf.append("rateIdentifier=").append(getRateIdentifier()).append(",");
		  buf.append("promoText=").append(getDiscountPromoText()).append(",");
		  buf.append("packageId=").append(getPackageId()).append(",");
		  buf.append("contentId=").append(getContentId()).append(",");
		  buf.append("pricingText1=").append(getPricingText1()).append(",");
		  buf.append("pricingText2=").append(getPricingText2()).append(",");

		  return buf.toString();
	  }
	  /**
	   *  @version    ER 8.0
	   * @author     VFE PS Team
	   *  @date       15-Aug-2005
	   * @description  (Record Undiscounted price on discounted purchases)   The purpose of this method is to get the standard rate of the undiscounted purchases.
	   **/
	  public double getUndiscountedStandardRate ()
	  {
		  return mStandardRate;
	  }

	  /**
	   *  @version   ER 8.0
	   * @author    VFE PS Team
	   *  @date      15-Aug-2005
	   * @description  (Record Undiscounted price on discounted purchases)   The purpose of this method is to set the standard rate of the undiscounted purchases.
	   **/
	  public void setUndiscountedStandardRate (double val)
	  {
		  mStandardRate = val;
	  }


	  public void setAlternativeCurrencyId(int mAlternativeCurrencyId)
	  {
		  this.mAlternativeCurrencyId = mAlternativeCurrencyId;
	  }

	  public void setAlternativeNetRate(double mAlternativeNetRate)
	  {
		  this.mAlternativeNetRate = mAlternativeNetRate;
	  }

	  public int getAlternativeCurrencyId()
	  {
		  return mAlternativeCurrencyId;
	  }

	  public double getAlternativeNetRate()
	  {
		  return mAlternativeNetRate;
	  }

	  public String getRateIdentifier(){
		  if(mRateIdentifiers!=null && mRateIdentifiers.length > 0 ){
			  return mRateIdentifiers[mRateIdentifiers.length-1];
		  }
		  return null;
	  }


	  public void setRateIdentifier(String rateIdentifier)
	  {
		  mRateIdentifiers = new String[1];
		  mRateIdentifiers[0] = rateIdentifier;

		 //Remedy 6843, Bruno Meseguer, API interface was broken, mRateIdentifier restored
		 //needed to maintain ER7 client compatibility
		 mRateIdentifier = rateIdentifier;
	  }

	  public String[] getRateIdentifiers(){
		  return this.mRateIdentifiers;
	  }

	  public void setRateIdentifiers(String[] rateIdentifiers)
	  {
		  this.mRateIdentifiers = rateIdentifiers;

		 //Remedy 6843, Bruno Meseguer, API interface was broken, mRateIdentifier restored
		 //needed to maintain ER7 client compatibility
		 mRateIdentifier = getRateIdentifier();
	  }

	  public double getEventUnits()
	  {
		  return mEventUnits;
	  }

	  public void setEventUnits(double mEventUnits)
	  {
		  this.mEventUnits = mEventUnits;
	  }


	  public double getUsageTime() {

		  return usageTime;
	  }


	  public void setUsageTime(double usageTime) {

		  this.usageTime = usageTime;
	  }



	 ////////////////////////////////////////
	 //@hud STKHREQ13076 SP ROAMING
	 public void setNetworkCode(NetworkCode networkCode) {
		 mNetworkCode = networkCode;
	 }
	 public NetworkCode getNetworkCode() {
		 return mNetworkCode;
	 }


	 ////////////////////////////////////////
	 //@hud STKHREQ36
	 public void setAccessDuration(double accessDuration) {
		 mAccessDuration = accessDuration;
	 }
	 public double getAccessDuration() {
		 return mAccessDuration;
	 }
	 public void setPricePointId(String id) {
		 mPricePointId = id;
	 }
	 public String getPricePointId() {
		 return mPricePointId;
	 }


	 //@hud STKHREQ13107
	 public void setIsUniquePromoCode(boolean b) {
		 mIsUniquePromoCode = b;
	 }
	 public boolean isUniquePromoCode() {
		 return mIsUniquePromoCode;
	 }

	public boolean isZeroCostIgnore() {
		return zeroCostIgnore;
	}

	public void setZeroCostIgnore(boolean zeroCostIgnore) {
		this.zeroCostIgnore = zeroCostIgnore;
	}
	
	//MQC 5067 - get / set the custom resource balances
	public ResourceBalance[] getCustomResourceBalances() {
		return customResourceBalances;
	}

	public void setCustomResourceBalances(ResourceBalance[] resourceBalances) {
		this.customResourceBalances = resourceBalances;
	}

	public boolean isAlwaysValidateMsisdn()
	{
		return alwaysValidateMsisdn;
	}

	public void setAlwaysValidateMsisdn(boolean alwaysValidateMsisdn)
	{
		this.alwaysValidateMsisdn = alwaysValidateMsisdn;
	}

}


//does not cover timezones

