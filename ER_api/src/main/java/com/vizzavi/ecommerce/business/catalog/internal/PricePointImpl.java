package com.vizzavi.ecommerce.business.catalog.internal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogApi;
import com.vizzavi.ecommerce.business.catalog.DRMObject;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.catalog.SuperCreditPricePoint;
import com.vizzavi.ecommerce.business.charging.BaseAuthorization;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.ErCoreConst;
import com.vizzavi.ecommerce.business.common.RatingAttributes;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;
//import com.vizzavi.ecommerce.business.catalog.model.volume.VolumeModel;

public class PricePointImpl extends PricePoint {

	private static final long serialVersionUID = -6626625093547918455L;

	private static Logger logger = LoggerFactory.getLogger(PricePointImpl.class);
	
	private boolean mIsOriginal = false;

	protected boolean mIsVolumeType = false;
	protected BalanceImpacts mImpacts = new BalanceImpacts();
	protected Map<String, PricePointTier> mPricePointTiers = new HashMap<String, PricePointTier>();

	protected String mProtectedFk;

	// the range of allowable tiers is dependent on the date time model or volume model
	// decision is that you can have either volume or date time model
	// this means that the allowable of balance impacts is against the tier name not the model
	// for minimal changes
	// we could add a happy hour price

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
		super(attrs, date);
		if (attrs instanceof PricePointImpl) {
			PricePointImpl copy = (PricePointImpl) attrs;
			setRateWithoutTax(copy.getNetRate());
			//setGlid(copy.getGlid());
			setArchived(copy.isArchived());
			PricePointImpl impl = copy;
			BalanceImpacts impacts =
				new BalanceImpacts(impl.getAllBalanceImpacts());
			setBalanceImpacts(impacts, date);
			//setPricingText1(copy.getPricingText1());
			//setPricingText2(copy.getPricingText2());

            // Recalculate standard rate after setBalanceImpacts(2) has updated mRateWithoutTax
            this.mStandardRate = getStandardRate(date);

            /* ER 7 Compliant */
			mPricingText1 = copy.mPricingText1;
			mPricingText2 = copy.mPricingText2;
			/* ER 7 Compliant */
			
			this.setPricingTextList1(copy.getPricingTextList1());
			this.setPricingTextList2(copy.getPricingTextList2());
			if (copy.getResource() != null) {
				setResource(copy.getResource().getCode());
				if(copy.getResource().isSuperCredit()){
					super.mResource.setSuperCredit(true);
				}
			}

			if(copy.getDRMObject() != null)
			{
				setDRMObject(copy.getDRMObject());
			}


			mPricePointTiers =new HashMap<String, PricePointTier>(copy.getMPricePointTiers());
			//setPricingModelTier(copy.getPricingModelTier(), isDiscount());
			this.mCustomFields = new HashMap<String, String>(copy.getCustomFields());

			this.setRoamingGrossAmount(copy.getRoamingGrossAmount());
			this.setRoamingNetAmount(copy.getRoamingNetAmount());
			this.setNetworkCode(copy.getNetworkCode());


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
		super(auth, date);


		PricePointImpl copy = (PricePointImpl) auth.getPricePoint();
		//setGlid(copy.getGlid());
		setArchived(copy.isArchived());
		PricePointImpl impl = copy;
		BalanceImpacts impacts =
			new BalanceImpacts(impl.getAllBalanceImpacts());
		setBalanceImpacts(impacts, date);
		
		/** ER 7 Compliant */
		mPricingText1 = copy.mPricingText1;
		mPricingText2 = copy.mPricingText2;
		/** ER 7 Compliant */
		
		this.setPricingTextList1(copy.getPricingTextList1());
		this.setPricingTextList2(copy.getPricingTextList2());
		if (copy.getResource() != null) {
			setResource(copy.getResource().getCode());
			if(copy.getResource().isSuperCredit()){
				super.mResource.setSuperCredit(true);
			}
		}
		setRateWithoutTax(copy.getNetRate());
		if(copy.getDRMObject() != null)
		{
			setDRMObject(copy.getDRMObject());
		}


		mPricePointTiers =new HashMap<String, PricePointTier>(copy.getMPricePointTiers());
		//setPricingModelTier(copy.getPricingModelTier(), isDiscount());
		this.mCustomFields = new HashMap<String, String>(copy.getCustomFields());


	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public PricePointImpl(BaseAuthorization auth, String languageCode, CatalogApi catalogApi) {
//        this(auth, languageCode, catalogApi, new Date());
//    }
//
//    //CR1564 -Utctimezone for diff region in country
//	public PricePointImpl(BaseAuthorization auth, String languageCode, CatalogApi catalogApi, Date date)
//	{
		this(auth);

		//@mawn Translate Pricing Text
		PricePoint originalPricePoint = auth.getPricePoint();
		String ppLinkId = this.getPricepointIdLink();

		this.setRoamingGrossAmount(auth.getRoamingGrossAmount());
		this.setRoamingNetAmount(auth.getRoamingNetAmount());
		this.setNetworkCode(auth.getNetworkCode());


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

	         strPricingText = catalogApi.translatePricingText(pricePoints,pricingTextTemplate  ,
	        		 languageCode, auth.getNetworkCode() == null ? ErCoreConst.ROAMING_DOMESTIC : auth.getNetworkCode().getRoamingType());
         }
         catch(Exception e){
        	 logger.error("Price Text Translation failed."+ e.getMessage(), e);
         }

         if(strPricingText != null)
             this.setTranslatedPricingText(strPricingText);
         else this.setTranslatedPricingText("");

	}

	/*
    private void copyText(Map text) {
        Iterator iter = text.keySet().iterator();
        while (iter.hasNext()) {
            String id = (String) iter.next();
            String val = (String) text.get(id);
            setPricingModelTierPricingText(id, val);
        }
    }

    private void copyPrice(Map text) {
        Iterator iter = text.keySet().iterator();
        while (iter.hasNext()) {
            String id = (String) iter.next();
            Double val = (Double) text.get(id);
            setPricingModelTierPrice(id, val.doubleValue());
        }
    }
	 */

	public void setAttributes(
			int durationCode,
			int chargingMethod,
			String promoCode,
			int paymentTypeCode,
			int customResource) {
		setDuration(durationCode);
		setPromoCodes(new String[] {promoCode});
		setChargingMethod(chargingMethod);
		setPaymentType(paymentTypeCode);
		setResource(customResource);
	}

	public void setAttributes(
			int durationCode,
			int chargingMethod,
			String promoCode,
			//[5] Mod Starts
			String bearerId,
			//[5] Mod Ends
			int paymentTypeCode,
			int customResource) {
		setDuration(durationCode);
		setPromoCodes(new String[] {promoCode});
		//[5] Mod Starts
		setBearerIds(new String[] {bearerId});
		setBearer(bearerId);
		//[5] Mod Ends
		setChargingMethod(chargingMethod);
		setPaymentType(paymentTypeCode);
		setResource(customResource);
	}
	public void setKey(Long mKey)
	{
		super.mKey = mKey;
	}

	public void setCreatedBy(String mCreatedBy) {
		super.mCreatedBy = mCreatedBy;
	}

	public void setModifiedBy(String mModifiedBy) {
		super.mModifiedBy = mModifiedBy;
	}

	public void setModifiedDate(Date mModifiedDate) {
		super.mModifiedDate = mModifiedDate;
	}

	public void setActiveStatus(char mActiveStatus ) {
		super.mActiveStatus = mActiveStatus ;
	}
	@Override
	public void setId(String mId) {
		super.mId = mId;
	}

	public void setArchived(boolean mIsArchived) {
		super.mIsArchived = mIsArchived;
	}


	public void setRateWithoutTax(double val) {
		super.mRateWithoutTax = val;
	}

	/**
        The type of currency or tokens.

	 */
	public void setResource(int mResource) {
		super.mResource = ChargingResource.getResource(mResource);
	}


	/**
        The start date defines when a package can be purchased
	 */
	@Override
	public void setStartDate(Date val) {
		mPurchaseStartDate = val;
	}

	/**
        The end date defines when a package can be purchased
	 */
	@Override
	public void setExpiryDate(Date val) {
		mPurchaseExpiryDate = val;
	}

	public void setContentId(String val) {
		mContentId = val;
	}

	public void setPackageId(String val) {
		mPackageId = val;
	}

	public String getPackageIdentifier() {
		StringBuffer buf = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
			buf.append("package:").append(getPackageId()).append('_');
			buf.append(getTaxCode()).append('_');
			buf.append(getChargingMethod()).append('_');
			buf.append(getDuration()).append('_');
			buf.append(getPaymentType()).append('_');
			buf.append(getAccessDevice()).append('_');
			buf.append(getChannel()).append('_');
			buf.append(getPromoCode()).append('_');
			//[5] Mod Starts
			buf.append(getBearer()).append('_');
			//[5] Mod Ends
			buf.append(getUserGroup()).append('_');
			buf.append(isExpressFlag()).append('_');
			//Modified by marcell 200405
			buf.append(isHistoric());
		
			//CR MPP - start			
			if(this.isHistoric()){		
				if(this.getStartDate()!=null){
					buf.append(sdf.format(this.getStartDate()));
				} else {
					buf.append(sdf.format(new Date()));
				}			
			}
			//CR MPP - end
			
			//CR-0978 Location Services
			buf.append('_').append(getTariff());
		
		return buf.toString();
	}

	public String getServiceIdentifier() {
		StringBuffer buf = new StringBuffer();
		buf.append("content:").append(getPackageId()).append('_');
		buf.append(getTaxCode()).append('_');
		buf.append(getContentId()).append('_');
		buf.append(getChannel()).append('_');
		buf.append(getPaymentType()).append('_');
		//[5] Mod Starts
		buf.append(getBearer()).append('_');
		//[5] Mod Ends
		buf.append(getAccessDevice()).append('_');
		buf.append(getAccessDevice());
		return buf.toString();
	}

	public void addBalanceImpact(BalanceImpact impact) {

		if (mImpacts == null) {
			mImpacts = new BalanceImpacts();
		}

		mImpacts.addBalanceImpact(impact);

		/* the following code is to ensure that whenever a link is created
		 * between a pricepoint and a balance impact, a corresponding default
		 * tier is added.  this does cause redundant links between objects,
		 * but is currently perceived to be easier than refactoring the entire
		 * priceplan code!
		 *///rbw20040406
		PricePointTier pptier = new PricePointTier();
		pptier.setBalanceImpacts(mImpacts);
		pptier.setDefaultPPT(true);
		pptier.setTier(PricePointTier.PPT_DEFAULT_ID);
		addPricePointTier(pptier);
		//@mawn RF logger.debug("Added PricePointTier, during addBalanceImpact. (pptier: "+pptier.getTier()+", impact: "+impact.toString()+")");

	}

	public void deleteAllBalanceImpacts() {
		mImpacts = new BalanceImpacts();
		logger.debug("[Deleted all balance impacts.]");
	}

	public void setGlid(String val) {
		mGlid = val;
	}

	@Override
	public void setTaxCode(String val) {
		mTaxCode = val;
	}

	@Override
	public void setTaxRate(double val) {
		mTaxRate = val;
	}

	/**
        The array of resources that the user will get if the price point is purchased
	 */
	@Override
	public ChargingResource[] getBalanceImpacts() {

		//REMEDY 6493 - call super method instead
		return super.getBalanceImpacts();
		//WAS
		/*List rv = new ArrayList();

		if (mImpacts == null) {
			return null;
		}

		BalanceImpact[] balImpacts = mImpacts.getBalanceImpacts();

		if (balImpacts == null || balImpacts.length == 0) {
			return null;
		}

		for (int i=0; i<balImpacts.length; i++) {
			//REMEDY 6493 - change condition
			//WAS if (balImpacts[i].getFixedAmount() < 0) {
			if (balImpacts[i].getFixedAmount() != 0) {
				ChargingResource cr = balImpacts[i].getResource();
				if ( (balImpacts[i].getType() != null) && (balImpacts[i].getType().trim().equalsIgnoreCase(SUPER_CREDIT))) {
					cr.setSuperCredit(true);
				}
				rv.add(cr);
				//rv.add(balImpacts[i].getResource());
			}
		}
		return (ChargingResource[])rv.toArray(new ChargingResource[rv.size()]);*/
	}



	/**
        The amount of each resource purchased
	 */
	@Override
	public double getBalanceImpactRate(ChargingResource res) {
		//REMEDY 6493 - call super method instead
		return super.getBalanceImpactRate(res);
		//WAS
		//return mImpacts.getRate(res);
	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public void setBalanceImpacts(BalanceImpacts impacts) {
        setBalanceImpacts(impacts, new Date());
    }

	public void setBalanceImpacts(BalanceImpacts impacts, Date date) {
		mImpacts = impacts;


		if (impacts != null) {

			if (impacts.getCurrency() != null) {
				setResource(impacts.getCurrency().getCode());
				double rate = impacts.getRate(date);
				setRateWithoutTax(rate);
			} else if (impacts.getNonCurrencyResource() != null) {
				// Added by Nyo for Remedy 3682
				// if Non Currency Resource is super credit it is set RateWithoutTax value to -1.0
				// Assuming that Purchase App will understand this value to ignore
				setResource(impacts.getNonCurrencyResource().getCode());
				if (impacts.getNonCurrencyResource().isSuperCredit()){
					this.getResource().setSuperCredit(true);
					setRateWithoutTax(0.0);
				}
				else {
					setRateWithoutTax(impacts.getRate(impacts.getNonCurrencyResource(), date));
				}

			}






			/* see addBalanceImpact() for a dissertation on this.
			 *///rbw20030406
			PricePointTier pptier = new PricePointTier();
			pptier.setBalanceImpacts(mImpacts);
			pptier.setDefaultPPT(true);
			pptier.setTier(PricePointTier.PPT_DEFAULT_ID);
			addPricePointTier(pptier);
			// @mawn RF logger.debug("Added PricePointTier, during setBalanceImpacts. (pptier: "+pptier.getTier()+", impacts: "+impacts.toString()+")");
		}//fi
	}

	@Override
	public ChargingResource getResource() {
		ChargingResource rv = super.getResource();
		if (rv == null) {
			if (mImpacts != null) {
				rv = mImpacts.getCurrency();

				if (rv == null) {
					rv = mImpacts.getNonCurrencyResource();
				}

				if (rv==null) {
					logger.warn("Price point {} has a null charging resource with impacts {}", getId() , mImpacts);

				}
			}
		}
		return rv;
	}

	//MQC 6610 - get value of resource field 
	public ChargingResource getResourceField() {
		return mResource;
	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	@Override
	public double getRate() {
        return getRate(new Date());
    }


    //CR1564 -Utctimezone for diff region in country
	@Override
	public double getRate(Date date)
	{
		return getRate(0.0, date);
	}


    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	@Override
	public double getRate(double volumeAmount) {
        return getRate(volumeAmount, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
    //Remedy - 2213 modified
	public double getRate(double volumeAmount, Date date) {

		//@hud RFRFRF, parse here? WRONG!
		/*======================================
		int round_nth_decimal =  4;

		if(PropertyServer.getProperty(ROUND_NTH_DECIMAL)!=null){

			round_nth_decimal = Integer.parseInt(PropertyServer.getProperty(ROUND_NTH_DECIMAL));

		}
		=======================================*/

		double rv = getNetRate(volumeAmount, date);

		if (super.getResource() != null && super.getResource().isCurrency()) {

			double taxRate = getTaxRate(date);
			rv = rv + rv * taxRate;
			rv = roundDouble(rv, ErCoreConst.getRoundNthDecimal());

		}
		return rv;
	}


	/**
	 * CR1430 changed to get the rate from the Balance Impacts as there
	 * can now be >1
	 */
	@Override
	public double getNetRate()
	{
        return mRateWithoutTax;
	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	@Override
	public double getNetRate(double volumeAmount) {
        return getNetRate(volumeAmount, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
	@Override
	public double getNetRate(double volumeAmount, Date date)
	{
		double rv = super.getNetRate();

//		 Commented by Nyo REMEDY # 4221
//		 get rate on given volume amount regardless of Currency or not
//		        if (super.getResource() == null
//		            || super.getResource().isCurrency()
//		            && rv < 0) {
		            if (mImpacts != null && mImpacts.size() != 0 && !useStaticValues) {
		                rv = mImpacts.getRate(super.getResource(), volumeAmount, date);
		            }
//		        }

		return rv;
	}

	public BalanceImpacts getAllBalanceImpacts() {
		return mImpacts;
	}

	@Override
	public boolean equals(Object o1) {
		PricePointImpl impl = (PricePointImpl) o1;
		boolean rv = true;
		if (!getContentId().equals(impl.getContentId())) {
			rv = false;
		} else if (!getPackageId().equals(impl.getPackageId())) {
			rv = false;
		} else if (!getSupplierId().equals(impl.getSupplierId())) {
			rv = false;
		} else if (isHistoric() != impl.isHistoric()) {
			rv = false;
		} else if (getPremiumLevel() != impl.getPremiumLevel()) {
			rv = false;
		} else if (!getPromoCode().equals(impl.getPromoCode())) {
			rv = false;
			//[5] Mod Starts
		} else if (!getBearer().equals(impl.getBearer())) {
			rv = false;
			//[5] Mod Ends
		} else if (!getUserGroup().equals(impl.getUserGroup())) {
			rv = false;
		} else if (getAccessDevice() != impl.getAccessDevice()) {
			rv = false;
		} else if (getPaymentType() != impl.getPaymentType()) {
			rv = false;
		} else if (getChannel() != impl.getChannel()) {
			rv = false;
		} else if (getChargingMethod() != impl.getChargingMethod()) {
			rv = false;
		} else if (getDuration() != impl.getDuration()) {
			rv = false;
		}

		return rv;
	}

	/**
	 * Sets a custom field for the service.
	 * Custom fields appear in <custom_field> tags in the catalog XML.
	 */
	public void setCustomField(String name, String value) {
		mCustomFields.put(name, value);
	}

	/**
        This sets the date time model.
        This is used in the generate priceplan to generate the date time models.
	 */
	/*    public void setDateTimeModel(String val)
        {
            mDateTimeModel = val;
        }
	 */
	/**
        This retrieves the date time model.
        This is used in the generate priceplan to generate the date time models.
	 */
	/*    public String getDateTimeModel()
        {
            return mDateTimeModel;
        }
	 */

	/* This data is now in PricePointTier

    public String getPricingModelTierPricingText(String tier) {
        return (String) mPromotionalPricingText.get(tier);
    }

    public void setPricingModelTierPricingText(String tier, String text) {
        mPromotionalPricingText.put(tier, text);
    }

    public double getPricingModelTierPrice(String tier) {
        Double val = (Double) mPromotionalPrice.get(tier);
        double rv = -1;
        if (val != null) {
            rv = val.doubleValue();
        }

        return rv;
    }

    public void setPricingModelTierPrice(String tier, double price) {
        mPromotionalPrice.put(tier, new Double(price));
    }

    public String[] getPricingModelTiers() {
        return (String[]) mPromotionalPricingText.keySet().toArray(
            new String[mPromotionalPricingText.size()]);
    }

	 */

	/**
         Retrieves the price for this tier. -1 means that this tier is not valid.
         This method will change in ER5.
	 */

	/**
             Retrieves the price for this tier. -1 means that this tier is not valid.
             This method will change in ER5.
	 */
	@Override
	public double getPricingModelTierPriceWithTax(String tier, String pricingModel)
	{
		double priceWithoutTax = getPricingModelTierPrice(tier, pricingModel);
		double rv = -1;
		if (priceWithoutTax>=0) {
			double taxRate = getTaxRate();

			priceWithoutTax = 100 * priceWithoutTax + priceWithoutTax * taxRate;
			long val = Math.round(priceWithoutTax);
			rv = val*0.01;
		}

		return rv;
	}

	public double getPricingModelTierPrice(String tier, String pricingModel) {
		PricePointTier ppt = mPricePointTiers.get( getPricePointTierId(tier, pricingModel) );
		Double val = null;
		if (ppt != null)
			val = ppt.getPromotionalPrice();
		double rv = -1;
		if (val != null) {
			rv = val.doubleValue();
		}

		return rv;
	}

	@Override
	public String getPricingModelTierPricingText(String tier, String pricingModel) {
		String ret = null;
		PricePointTier ppt = mPricePointTiers.get( getPricePointTierId(tier, pricingModel) );
		if (ppt!=null) ret = ppt.getPromotionalPricingText();
		return ret;
	}


	// new methods to support promotional activity
	public void setIsDiscount(boolean val) {
		mIsDiscount = val;
	}

	public void setDiscountPromoText(String text) {
		mPromoText = text;
	}

	

	public double getStandardRateWithoutTax()
	{
		//Remedy 5037, Bruno Meseguer, Calculations were wrong. It is as simple as getting the Net Rate.
		return getNetRate();

	}

	public void setPricingModelTier(String tier, String pricingModel, boolean discount) {
		if (isOriginal()) {
			throw new RuntimeException("This object cannot be altered. A copy of the price point can be rereived using CatalogApi.getPricePoint().");
		}

		setIsDiscount(discount);
		if (tier != null && tier.equals("") == false) {
			double val = getPricingModelTierPrice(tier,pricingModel);
			if (val < 0) {
				// @mawn RF MDC.put(LoggingConstant.METHOD, "setPricingModelTier");
				logger.error( "The price for the tier in price point " + getId()
						+ " has a negative price associated with it. Please assign a price to this tier");
				// @mawn RFLoggingHelper.clearMDC();

				/**
				 *  @mawn RF
				 logger.error(
						"The price for the tier in  price point "
						+ getId()
						+ " has a negative price associated with it. Please assign a price to this tier");
						**/
			} else {
				setRateWithoutTax(getPricingModelTierPrice(tier,pricingModel));
			}
			if (discount) {
				setDiscountPromoText(getPricingModelTierPricingText(tier,pricingModel));
			}
		}
	}

	public void deletePricingModelData() {

		/* remove non default PricePointTiers */

		Set<String> keys = mPricePointTiers.keySet() ;
		Iterator<String> iter = keys.iterator();
		Object key;
		PricePointTier tier;
		while (iter.hasNext()) {
			key = iter.next();
			tier =mPricePointTiers.get(key);
			if ( tier.isDefaultPPT() == false ) mPricePointTiers.remove(key);
		}


	}


	public void deletePricingModel(String id)
	{
		PricePointTier[] tiers = getPricePointTiers();

		for (int index=0; tiers!=null && index<tiers.length; index++) {
			if (tiers[index].getPricingModel().equals(id)) {
				deletePricingModelTier(tiers[index].getTier(), id);
			}
		}
	}

	public void deletePricingModelTier(String tier, String pricingModel) {
		/*
        mPromotionalPrice.remove(tier);
        mPromotionalPricingText.remove(tier);
		 */
		mPricePointTiers.remove(getPricePointTierId(tier,pricingModel) ) ;
	}

	protected String getPricePointTierId(String tier, String pricingModel) {
		String ret = tier;
		if (ret == null) ret="";
		if (pricingModel != null ) ret = ret + pricingModel ;
		return ret;
	}


	public boolean isVolumeType() {
		return mIsVolumeType;
	}

	public void setVolumeType(boolean val) {
		mIsVolumeType = val;
	}

	public boolean isOriginal() {
		return mIsOriginal;
	}

	public void setIsOriginal() {
		mIsOriginal = true;
	}

	public PricePointTier getPricePointTier(String tierId, String modelId) {
		PricePointTier rv = mPricePointTiers.get(getPricePointTierId(tierId,modelId));
		return rv;
	}


	/**
	 * @return
	 */
	public PricePointTier[] getPricePointTiers() {
		PricePointTier[] ret =null;

		if (mPricePointTiers != null)

			ret = mPricePointTiers.values().toArray(
					new PricePointTier[0]);
		return ret;
	}

	public void addPricePointTier(PricePointTier pptier) {
		if (pptier != null)
			mPricePointTiers.put(
					this.getPricePointTierId(pptier.getTier(),pptier.getPricingModel()),
					pptier);
	}

	public void removePricePointTier(String tier, String pricingModel) {

		mPricePointTiers.remove(
				this.getPricePointTierId(tier,pricingModel));
	}

	public void removeAllPricePointTiers() {
		mPricePointTiers = new HashMap<String, PricePointTier>();
	}




	/**
	 * @return
	 */
	public Map<String, PricePointTier> getMPricePointTiers() {
		return mPricePointTiers;
	}

	/**
	 * @param map
	 */
	public void setPricePointTiers(Map<String, PricePointTier> map) {
		mPricePointTiers = map;
	}

	/**
	 * Mutator for the DRM Object
	 * @param drmobject
	 * @return void
	 * @since ER 5.1
	 */
	@Override
	public void setDRMObject(DRMObject drmobject)
	{
		m_DRMObject = drmobject;
	}


	/**
	 * Setter method for the Pricepoint ID Link field
	 * @param String
	 * @return void
	 * @since ER 5.1
	 */
	public void setPricepointIdLink(String pricepointid)
	{
		mPricepointIdLink = pricepointid;
	}

	/**
         Retrieves all of the tiers valid for this price point.
         This method will change in ER5.
	 */
	@Override
	public String[] getPricingModelTiers(String pricingModel)
	{
		String[] rv = null;
		if (mPricePointTiers!=null) {
			Iterator<PricePointTier> iter = mPricePointTiers.values().iterator();
			List<String> tiers = new ArrayList<String>();
			while (iter.hasNext()) {
				PricePointTier tier = iter.next();
				if (tier.getPricingModel().equals(pricingModel)) {
					tiers.add(tier.getTier());
				}
			}

			rv = tiers.toArray(new String[tiers.size()]);
		}

		return rv;
	}


	/** ADDED FOR EGYPT ER6 STUB **/
	public void setForcedPurchase(boolean forcedPurchase) {
		mForcedPurchase = forcedPurchase;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public void setSubscriptionDuplicate(boolean subscriptionDuplicate)
	{
		mDuplicateSubscription = subscriptionDuplicate;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public void setFixedExpiryDate(Date val) {
		mPurchaseFixedExpiryDate = val;
	}

	/** ADDED FOR EGYPT ER6 STUB **/
	public void setInteractiveFlag(String interactiveFlag) {
		mInteractiveFlag = interactiveFlag;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	public void setReserveOnly(boolean reserveOnly)
	{
		mReserveOnly = reserveOnly;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setMinSubPeriod(int MinSubPeriod) {
		mMinSubPeriod = MinSubPeriod;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setPenaltyCharges(double penaltyCharges) {
		mPenaltyCharges = penaltyCharges;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setCancellation(boolean cancellation) {
		mCancellation = cancellation;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setMonthEndSubscription(String MonthEndSubscription) {
		mMonthEndSubscription = MonthEndSubscription;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setHistoric(boolean historic) {
		mHistoric = historic;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setFixedRecurrence(long fixedRecurrence) {
		mFixedRecurrence = fixedRecurrence;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setReceipting(boolean receipting) {
		mReceipting = receipting;
	}

	/** ADDED FOR ER6.5 STUB **/
	public void setReceiptingAttribute(String receiptingAttribute) {
		mReceiptingAttribute = receiptingAttribute;
	}

	//Remedy 4945
    public void setFixedRecurringPricePoint(boolean isFixedRecurringPricePoint) {
    	mFixedRecurringPricePoint = isFixedRecurringPricePoint;
    }

    
	//[12] Mod Start
	/** ADDED FOR ER8 STUB **/
	@Override
	public void setPaymentHandler(String paymentHandler) {
		mPaymentHandler = paymentHandler;
	}
	//[12] Mod End

	/** ADDED FOR EGYPT ER8 STUB **/
	public void setBasePricePoint(boolean BasePricePoint)
	{
		mBasePricePoint = BasePricePoint;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	@Override
	public void setPreOrder(boolean preOrder)
	{
		mPreOrder = preOrder;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	public void setOrder(int order)
	{
		mOrder = order;
	}

	/** ADDED FOR EGYPT ER7 STUB superCredit **/
	public void setCreditPurchasePricePoints(SuperCreditPricePoint[] superCreditPricePoints) {
		mCreditPurchasePricePoints = superCreditPricePoints;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	public void setPurchaseableBySuperCredit(boolean purchaseableBySuperCredit)
	{
		mPurchaseableBySuperCredit = purchaseableBySuperCredit;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	public void setSuperCreditDonor(boolean superCreditDonor)
	{
		mbSuperCreditDonor = superCreditDonor;
	}

//	public void setPackageFk(com.vizzavi.ecommerce.business.catalog.internal.generated.hibernate.Package packageFk)
//	{
//		_packageFk = packageFk;
//	}
//
//	public void setServiceFk(com.vizzavi.ecommerce.business.catalog.internal.generated.hibernate.VService serviceFk)
//	{
//		_serviceFk = serviceFk;
//	}


	public void setProtectedFk(String protectedFk)
	{
		mProtectedFk = protectedFk;
	}

	//[6] Mod Starts
//	public void setVolumeModel(VolumeModel mVolumeModel) {
//		this.mVolumeModel = mVolumeModel;
//	}
	//[6] Mod Ends

//	public com.vizzavi.ecommerce.business.catalog.internal.generated.hibernate.VService getServiceFk()
//	{
//		return _serviceFk;
//	}
//
//	public com.vizzavi.ecommerce.business.catalog.internal.generated.hibernate.Package getPackageFk()
//	{
//		return _packageFk;
//	}

	public String getProtectedFk()
	{
		return mProtectedFk;
	}

	//REMEDY 6493 - set mBalances
	@Override
	public void setBalances(ResourceBalance[] resourceBalances) {
		mBalances = resourceBalances;
	}
	// CR-0095 RBT START 
	@Override
	public void setExtensionPeriod(int ExtensionPeriod){
		mExtensionPeriod = ExtensionPeriod;
	}
	// CR-0095 RBT END

    /* //CR1564 -Utctimezone for diff region in country - moved to PricePoint
	//MQC 5654 - add Tax object for real time tax rate calculation
	public void setTax(Tax taxObject) {
		mTax = taxObject;
	}
    */

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public void recalculateRateWithoutTax() {
        recalculateRateWithoutTax(new Date());
    }

    //CR1564 -Utctimezone for diff region in country
	//CR1430 start
	public void recalculateRateWithoutTax(Date date) {
		double rv = super.getNetRate();
        if (mImpacts != null && mImpacts.size() != 0 ) {
            rv = mImpacts.getRate(0.0, date);
        }
        
        mRateWithoutTax = rv;
	}
	
	@Override
	public double getFutureRate(Date futureDate) {
		double rv = super.getNetRate();
        if (mImpacts != null && mImpacts.size() != 0 ) {
            rv = mImpacts.getRate(0.0, futureDate);
            
            rv = rv + rv * getTaxRate(futureDate);
            rv = roundDouble(rv, ErCoreConst.getRoundNthDecimal());
        } //MQC 7696 - set gross value 
        else {
        	rv = rv + rv * getTaxRate(futureDate);
        }
        
        return rv;
	}
	//CR1430 end

}
