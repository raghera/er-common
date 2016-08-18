package com.vizzavi.ecommerce.business.catalog.internal;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.business.catalog.DRMType;
import com.vizzavi.ecommerce.business.catalog.PaymentContent;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.catalog.PricePoints;
import com.vizzavi.ecommerce.common.EnvironmentException;


public class CatalogPackageImpl extends CatalogPackage
{
	private    static final long serialVersionUID = 2807458399041112368L;

	private static Logger logger = LoggerFactory.getLogger(CatalogPackageImpl.class);

	protected boolean mIsOriginal = false;


	protected Map<String, String> mPricingModels = new HashMap<String, String>();

	/* name of package deal */
	//protected String mPurchaseMethod = "";
	// moved into catalogpackage for partner revenue share phase 2

	public CatalogPackageImpl(String id, String name, String desc)
	{
		super();
		setId(id);
		//REMEDY 6149 - removed comments
		mName = name;
		mDescription = desc;
		setName(name);
		setDescription(name);
		mPricePoints = new PricePointsImpl();
	}

	public CatalogPackageImpl(String id, HashMap<String, String> names, HashMap<String, String> desc){
		setId(id);
		setNames(names);
		setDescriptions(desc);
		//REMEDY 6149 - set name and description
		mName = getName(null);
		mDescription = getDescription(null);
		mPricePoints = new PricePointsImpl();
	}

	public CatalogPackageImpl(Long key, String id, String name, String desc,
			String createdBy, String modifiedBy, Date modifiedDate, char activeStatus) 
	{
		super();
		setId(id);
		mKey = key;
		mCreatedBy = createdBy;
		mModifiedBy = modifiedBy;
		mModifiedDate = modifiedDate;
		mActiveStatus = activeStatus;

		mName = name;
		mDescription = desc;
		setName(name);
		setDescription(name);    	
		mPricePoints = new PricePointsImpl();
	}
	public CatalogPackageImpl(String id, String name, List<CatalogService> services, String desc,
			PricePoints pts, String notificationCategory, PaymentContent paymentContent, boolean resOnly)
	{
		super(id, name, services, desc, pts, notificationCategory, paymentContent, resOnly);
		setId(id);
		if (pts == null) {
			setPricePoints(new PricePointsImpl());
		}
	}


	public CatalogPackageImpl(Long key, String id, String name, List<CatalogService> services, String desc,
			PricePoints pts, String notificationCategory, PaymentContent paymentContent, boolean resOnly,
			String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
	{
		super(key, id, name, services, desc, pts, notificationCategory, paymentContent, resOnly, createdBy, modifiedBy, modifiedDate, activeStatus);
		setId(id);
		if (pts == null) {
			setPricePoints(new PricePointsImpl());
		}
	}



	public CatalogPackageImpl(CatalogPackage catalogPackage) {
		this(catalogPackage, new Date());
	}

	//CR1564 -Utctimezone for diff region in country
	public CatalogPackageImpl(CatalogPackage catalogPackage, Date date){
		super(catalogPackage);
		logger.debug("Constructing the package {}" , catalogPackage.getSimplePackageId());
		// this makes the copy faster
		if (catalogPackage.getPricePoints() !=null)
			mPricePoints = catalogPackage.getPricePoints();

		if (catalogPackage.getPaymentContent()!=null) {
			this.mPaymentContent = new PaymentContent(catalogPackage.getPaymentContent());
		}
		if (catalogPackage instanceof CatalogPackageImpl) {
			CatalogPackageImpl impl = (CatalogPackageImpl)catalogPackage;
			mPurchaseMethod = impl.mPurchaseMethod;
			//CR0586 KPI Reporting Fields
			kpiPackageProductCategory = impl.kpiPackageProductCategory;
			kpiPackageType = impl.kpiPackageType;
			//CR0586 End
			mPricingModels = new HashMap<String, String>(impl.mPricingModels);
		}
		//MQC8385 TODO
		if (catalogPackage.getPricePoint()!=null) {
			this.mPricePoint = new PricePointImpl(catalogPackage.getPricePoint(), date);
		}

	}


	public void addPricingModel(String val)
	{
		if (! mPricingModels.containsKey(val))
			mPricingModels.put(val,null);
	}




	/**
	 * Add Charging Service
	 * @param serv
	 */
	public void addService(CatalogService servImpl){

		mServices.put(servImpl.getId(), servImpl);
		//TODO servImpl.getPackageIds().remove(getSimplePackageId());
	}



	/**
	 * Remove Charging Service
	 * @param serv
	 */
	public void removeService(CatalogService servImpl){
		mServices.remove(servImpl.getId());
	}

	/**
	 * Add Charging Services
	 * @param services
	 */
	public void setServices(List<CatalogService> services){

		mServices = new HashMap<String, CatalogService>();

		for ( int i = 0; i<services.size(); i++ )  {
			CatalogService o = services.get( i );
			addService(o);
		}
	}

	/**
        Returns true if package has a purchase range
	 */
	public boolean hasStartEndDate()
	{
		return true;
		// this should cycle through the price points and work this out
		// we should have a setStartDate and setExpiryDate as well
		// which sets all price points

		/*        if (getStartDate()==null || getExpiryDate()==null) {
            return false;
        } else {
            return true;
        }
		 */
	}


	public Date getStartDate()
	{
		//TODO wtf?  
		return new Date();
	}

	public Date getExpiryDate()
	{
		return new Date();
	}

	public void setPackageId(String Id){
		mId = Id;
	}

	public void setKey(Long key){
		mKey = key;
	}

	public void setCreatedBy(String createdBy) {
		mCreatedBy = createdBy;
	}

	public void setModifiedBy(String modifiedBy) {
		mModifiedBy = modifiedBy;
	}

	public void setModifiedDate(Date modifiedDate) {
		mModifiedDate = modifiedDate;
	}

	public void setActiveStatus(char activeStatus) {
		mActiveStatus = activeStatus;
	}


	public void setUrl(String url) {
		mUrl = url;
	}


	@Override
	public void setPricePoints(PricePoints pricePoints) {

		mPricePoints = pricePoints;
	}


	public String[] getServiceNames()
	{
		List<String> rv = new ArrayList<String>();
		CatalogService[] packs = getServices();
		for (CatalogService pack : packs) {
			rv.add(pack.getName());
		}
		return rv.toArray(new String[] {});
	}


	public void setId(String val)
	{
		try {
			Long.parseLong(val);
			throw new EnvironmentException("The package id cannot be a number " + val);
		} catch (Exception e) {
			// this is okay
		}
		mId = val;
	}

	public void setPackageType(String type)
	{
		mPackageType = type;
	}


	// This has been moved to CatalogPackage class for
	// partner revenue share phase 2
	//public String getPurchaseMethod() { return mPurchaseMethod; }


	public void setPurchaseMethod(String purchaseMethod) { mPurchaseMethod = purchaseMethod; }

	//CR0586 KPI Reporting Fields
	@Override
	public void setKpiPackageProductCategory(String kpiPackageProductCategory) { this.kpiPackageProductCategory = kpiPackageProductCategory; }
	@Override
	public void setKpiPackageType(String kpiPackageType) { this.kpiPackageType = kpiPackageType; }
	//CR0586 End

	public void setReserveOnly(boolean isReserveOnly) { mReserveOnly = isReserveOnly; }


	@Override
	public void setPaymentContent(PaymentContent paymentContent) { mPaymentContent = paymentContent; }

	public void setNotificationCategory(String val)
	{
		mNotificationCategory = val;
	}

	/**
	 * Sets a custom field for the package.
	 * Custom fields appear in <custom_field> tags in the catalog XML.
	 */
	public void setCustomField(String name, String value) {
		mCustomFields.put(name, value);
	}

	public boolean isOriginal()
	{
		return mIsOriginal;
	}

	public void setIsOriginal()
	{
		mIsOriginal = true;
	}

	@Override
	public String[] getPricingModels()
	{

		return mPricingModels.keySet().toArray(new String [] {});
	}

	public String getPricingModel()
	{
		String rv = null;
		String [] val = getPricingModels();
		if (val.length>0) {
			rv = val[0];
		}

		return rv;
	}

	public void setPricingModelFk(String pricingModelFk)
	{
		mPricingModelFk = pricingModelFk;
	}
	@Override
	public String getPricingModelFk()
	{
		return mPricingModelFk;
	}
	public void setPricingModel(String val)
	{
		mPricingModels.put(val,val);
	}

	public void deletePricingModelData()
	{
		if (getPricePoints()!=null) {
//			PricePoint[] pts = getPricePoints().getAll();
//			for (int index=0; pts!=null && index<pts.length; index++) {
			for (PricePoint p: getPricePoints())	{
				PricePointImpl impl = (PricePointImpl)p;
				impl.deletePricingModelData();
			}
		}
	}

	public void deletePricingModel(String pricingModel)
	{
		mPricingModels.remove(pricingModel);
		if (getPricePoints()!=null) {
//			PricePoint[] pts = getPricePoints().getAll();
//			for (int index=0; pts!=null && index<pts.length; index++) {
			for (PricePoint p: getPricePoints())	{
				PricePointImpl impl = (PricePointImpl)p;
				impl.deletePricingModel(pricingModel);
			}
		}
	}

	public void deletePricingModelTier(String pricingModel, String tierId)
	{
		if (getPricePoints()!=null) {
//			PricePoint[] pts = getPricePoints().getAll();
//			for (int index=0; pts!=null && index<pts.length; index++) {
			for (PricePoint p: getPricePoints())	{
				PricePointImpl impl = (PricePointImpl)p;
				impl.deletePricingModelTier(pricingModel, tierId);
			}
		}
	}

	/**
	 * @param string
	 */
	 public void setTaxCode(String string) {
		mTaxCode = string;
	}

	/**
	 * Mutator for the m_DRMType field
	 * @param drmtype
	 * @return void
	 * @since ER 5.1
	 */
	 public void setDRMType(DRMType drmtype)
	{
		 m_DRMType = drmtype;
	}

	//[1] Mod Start
	@Override
	public void setDynamicProtectedValue(String mDynamicProtectedValue) {
		this.mDynamicProtectedValue = mDynamicProtectedValue;
	}
	//[1] Mod End
	/**
	 * @param id
	 * @return
	 */
	public boolean containsPricingModel(String id) {
		if (mPricingModels != null && id != null) return mPricingModels.containsKey(id);
		return false;
	}

	/**
	 * @param refundable
	 */
	public void setNonRefundable(boolean bool){
		mRefundable = !bool;
	}

	/**
	 * @param nonRefundableDescription
	 */
	@Override
	public void setNonRefundableDescription(String nonRefundableDescription){
		mNonRefundableDescription = nonRefundableDescription;
	}


	/**
	 * Used to retrieve to assess if the package is refundable
	 * @return boolean
	 * @since ER 5.1
	 */
	@Override
	public boolean isRefundable(){
		CatalogService[] catalogServices = getServices();
		if(catalogServices != null){
			int count = 0;
			for(int i=0; i<catalogServices.length; i++){
				if(!catalogServices[i].isRefundable()){
					count++;
				}
			}
			if(count > 0){
				this.mRefundable = false;
				setNonRefundableDescription("Sorry, the package is not refundable, because at least one of its service is not refundable!");
			}
		}
		return this.mRefundable;
	}


	/** ADDED FOR EGYPT ER6 STUB **/
	public void setExpressPurchase(boolean expressPurchase) {
		mExpressPurchase = expressPurchase;
	}
	/** ADDED FOR ER6 Requirement **/
	public void setRecieptingFlag(boolean receiptingFlag) {
		mReceiptingFlag = receiptingFlag;
	}

	/** ADDED FOR EGYPT ER7 STUB **/
	public void setPricePointOrder(boolean pricePointOrder) {
		mPricePointOrder = pricePointOrder;
	}

	/** ADDED FOR ER7 STUB **/
	public void setRevenueShareByUsage(boolean revenueShareByUsage) {
		mRevenueShareByUsage = revenueShareByUsage;
	}

	/** ADDED FOR ER7 STUB **/
	public void setDynamicDefault(boolean dynamicDefault) {
		mDynamicDefault = dynamicDefault;
	}

	/** ADDED FOR ER7 STUB **/
	public void setPriority(int priority) {
		mPriority = priority;
	}

	/** ADDED FOR ER7 STUB **/
	public void setSuperPackage(boolean superPackage) {
		mSuperPackage = superPackage;
	}

	/** ADDED FOR ER8  **/
	public void setACEPackage(boolean ACEPackage)
	{
		mACEPackage = ACEPackage;
	}

	/** ADDED FOR ER8 Ph 2  **/
	public void setUpSell(boolean UpSell)
	{
		mUpSell = UpSell;
	}

	// CR2210 - MPay Rate Card
	/**
	 * @param mUseRateCardService the mUseRateCardService to set
	 */
	public void setmUseRateCardService(boolean useRateCardService) {
		this.mUseRateCardService = useRateCardService;
	}

	/**
	 * @param mRateCardServiceId the mRateCardServiceId to set
	 */
	public void setmRateCardServiceId(String rateCardServiceId) {
		this.mRateCardServiceId = rateCardServiceId;
	}
	// CR2210 - Ends

}




