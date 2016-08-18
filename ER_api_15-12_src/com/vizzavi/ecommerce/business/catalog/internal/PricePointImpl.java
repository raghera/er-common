package com.vizzavi.ecommerce.business.catalog.internal;

import java.util.Date;
import java.util.HashMap;

import com.vizzavi.ecommerce.business.catalog.CatalogApi;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.charging.BaseAuthorization;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.ErCoreConst;
import com.vizzavi.ecommerce.business.common.RatingAttributes;

public class PricePointImpl extends PricePoint {

	private static final long serialVersionUID = -6626625093547918455L;

	public PricePointImpl() {
		super();
	}


	/**
	 * 
	 * @param attrs
	 */
	public PricePointImpl(RatingAttributes attrs) {
        this(attrs, new Date());
    }

	/**
	 * 
	 * @param attrs
	 * @param date
	 */
	public PricePointImpl(RatingAttributes attrs, Date date) {
		super(attrs/*,ET-153 date*/);
		if (attrs instanceof PricePoint) {
			PricePoint copy = (PricePoint) attrs;
			setRateWithoutTax(copy.getNetRate());
			//setGlid(copy.getGlid());
			setArchived(copy.isArchived());
			PricePoint impl = copy;
			BalanceImpacts impacts =
				new BalanceImpacts(impl.getAllBalanceImpacts());
			setBalanceImpacts(impacts, date);

            // Recalculate standard rate after setBalanceImpacts(2) has updated mRateWithoutTax
            this.mStandardRate = getStandardRate(date);

			setmPricingText1(copy.getmPricingText1());
			setmPricingText2(copy.getmPricingText2());
			
			this.setPricingTextList1(copy.getPricingTextList1());
			this.setPricingTextList2(copy.getPricingTextList2());
			if (copy.getResource() != null) {
				setResource(copy.getResource().getCode());
			}

			//PPM136861 refactoring aL. START
//			mPricePointTiers =new HashMap<String, PricePointTier>(copy.getMPricePointTiers());
			setPricePointTier(copy.getPricePointTier());
			
			this.mCustomFields = new HashMap<String, String>(copy.getCustomFields());

			this.setTranslatedPricingText1(copy.getTranslatedPricingText1());
			this.setTranslatedPricingText2(copy.getTranslatedPricingText2());
			this.setTranslatedPricingText(copy.getTranslatedPricingText());

			//CR1429
			this.setAlwaysValidateMsisdn(copy.isAlwaysValidateMsisdn());
		}

	}

	public PricePointImpl(
			String id,
			int durationCode,
			int chargingMethod,
			String promoCode,
			double rate,
			ChargingResource res,
			boolean archiveFlag,
			BalanceImpacts impacts)
	{

		super(
				id,
				durationCode,
				chargingMethod,
				promoCode,
				rate,
				res,
				new HashMap<Object, Object>(),
				archiveFlag);

		mImpacts = impacts;
	}

	public PricePointImpl(
			String id,
			int durationCode,
			int chargingMethod,
			String promoCode,
			//[5] Mod Starts
			String bearerId,
			//[5] Mod Ends
			double rate,
			ChargingResource res,
			boolean archiveFlag,
			BalanceImpacts impacts)
	{

		super( id,
				durationCode,
				chargingMethod,
				promoCode,
				//[5] Mod Starts
				bearerId,
				//[5] Mod Ends
				rate,
				res,
				new HashMap<Object, Object>(),
				archiveFlag);

		mImpacts = impacts;
	}
	public PricePointImpl(
			Long key,
			String id,
			int durationCode,
			int chargingMethod,
			String promoCode,
			double rate,
			ChargingResource res,
			boolean archiveFlag,
			BalanceImpacts impacts,
			String createdBy, String modifiedBy, Date modifiedDate, char activeStatus
	)
	{

		super(key, id, durationCode, chargingMethod, promoCode, rate, res, new HashMap<Object, Object>(), archiveFlag, createdBy, modifiedBy, modifiedDate, activeStatus);

		mImpacts = impacts;
	}

	public PricePointImpl(
			Long key,
			String id,
			int durationCode,
			int chargingMethod,
			String promoCode,
			//[5] Mod Starts
			String bearerId,
			//[5] Mod Ends
			double rate,
			ChargingResource res,
			boolean archiveFlag,
			BalanceImpacts impacts,
			String createdBy, String modifiedBy, Date modifiedDate, char activeStatus
	)
	{
		//[5] Mod Starts Changes done in original code
		super(key, id, durationCode, chargingMethod, promoCode,bearerId, rate, res, new HashMap<Object, Object>(), archiveFlag, createdBy, modifiedBy, modifiedDate, activeStatus);

		mImpacts = impacts;
	}
	public PricePointImpl(int durationCode, int chargingMethod, String promoCode, int paymentTypeCode, int customResource)
	{
		setAttributes(durationCode, chargingMethod, promoCode, paymentTypeCode, customResource);
	}

	public PricePointImpl(int durationCode, int chargingMethod, String promoCode,String bearerId , int paymentTypeCode, int customResource)
	{
		setAttributes(durationCode, chargingMethod, promoCode, bearerId , paymentTypeCode, customResource);
	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public PricePointImpl(BaseAuthorization auth) {
        this(auth, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
	//>> Added by Nyo to allow to create PricePointImpl.java
	// from BaseAuth Object REMEDY 4221
	public PricePointImpl(BaseAuthorization auth, Date date) {
		super(auth/*, date ET-153*/);

		// PPM136861 refactoring aL. removed cast
		PricePoint copy = auth.getPricePoint();
		//impl AND copy - wtf?
		setArchived(copy.isArchived());
		PricePoint impl = copy;
		BalanceImpacts impacts =
			new BalanceImpacts(impl.getAllBalanceImpacts());
		setBalanceImpacts(impacts, date);
		
		/** ER 7 Compliant */
		setmPricingText1(copy.getmPricingText1());
		setmPricingText2(copy.getmPricingText2());
		/** ER 7 Compliant */
		
		this.setPricingTextList1(copy.getPricingTextList1());
		this.setPricingTextList2(copy.getPricingTextList2());
		if (copy.getResource() != null) {
			setResource(copy.getResource().getCode());
		}
		setRateWithoutTax(copy.getNetRate());


		//PPM136861 refactoring aL. START
//		mPricePointTiers =new HashMap<String, PricePointTier>(copy.getMPricePointTiers());
		setPricePointTier(copy.getPricePointTier());
		
		//setPricingModelTier(copy.getPricingModelTier(), isDiscount());
		this.mCustomFields = new HashMap<String, String>(copy.getCustomFields());


	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public PricePointImpl(BaseAuthorization auth, String languageCode, CatalogApi catalogApi) {
		this(auth);
		PricePoint originalPricePoint = auth.getPricePoint();
		String ppLinkId = this.getPricepointIdLink();



		if (ppLinkId == null) {
			ppLinkId = "";
		}

		  String strPricingText = "";

	     try{

			 PricePoint[] pricePoints = new PricePoint[2];
	         pricePoints[0] = originalPricePoint;
	         if(!"".equals(ppLinkId)){
	             pricePoints[1] = catalogApi.getPricePoint(ppLinkId);
	         }


	         String pricingTextTemplate = null;

	         if (originalPricePoint != null )
	        	 pricingTextTemplate = originalPricePoint.getPricingTextTemplateName1();

//	         strPricingText = catalogApi.translatePricingText(pricePoints,pricingTextTemplate  ,
//	        		 languageCode, auth.getNetworkCode() == null ? ErCoreConst.ROAMING_DOMESTIC : auth.getNetworkCode().getRoamingType());
	         strPricingText = catalogApi.translatePricingText(pricePoints,pricingTextTemplate  ,
	        		 languageCode, 0);
         }
         catch(Exception e){
        	 log.error("Price Text Translation failed."+ e.getMessage(), e);
         }

         if(strPricingText != null)
             this.setTranslatedPricingText(strPricingText);
         else this.setTranslatedPricingText("");

	}
	@Override
	public double getRate(Date date) {	//TODO why 0 below?  surely 1 unit, by default?
		return getRate(0.0, date);
	}

	public double getRate(double volumeAmount) {
	    return getRate(volumeAmount, null);
	}

	public double getNetRate(double volumeAmount) {
		//        return getNetRate(volumeAmount, new Date());
		//    }
		//
		//    //CR1564 -Utctimezone for diff region in country
		//	public double getNetRate(double volumeAmount, Date date)
		//	{
		double rv = this.getNetRate();

		if (mImpacts != null && mImpacts.size() != 0 && !useStaticValues) {
			rv = mImpacts.getRate(this.getResource(), volumeAmount);
		}

		return rv;
	}
	// PPM136861 refactoring aL. removed
//	@Override
//	public ChargingResource getResource() {
//		ChargingResource rv = super.getResource();
//		if (rv == null) {
//			if (mImpacts != null) {
//				rv = mImpacts.getCurrency();
//	
//				if (rv == null) {
//					rv = mImpacts.getNonCurrencyResource();
//				}
//	
//				if (rv==null) {
//					logger.warn("Price point {} has a null charging resource with impacts {}", getId() , mImpacts);
//	
//				}
//			}
//		}
//		return rv;
//	}
	@Override
	public double getFutureRate(Date futureDate) {
		double rv = this.getNetRate();
	    if (mImpacts != null && mImpacts.size() != 0 ) {
	        rv = mImpacts.getRate(0.0, futureDate);
	        
	        rv = rv + rv * getTaxRate(/*futureDate ET-153*/);
	        rv = roundDouble(rv, ErCoreConst.getRoundNthDecimal());
	    } //MQC 7696 - set gross value 
	    else {
	    	rv = rv + rv * getTaxRate(/*futureDate ET-153*/);
	    }
	    
	    return rv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
