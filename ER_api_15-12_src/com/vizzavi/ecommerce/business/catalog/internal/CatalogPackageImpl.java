package com.vizzavi.ecommerce.business.catalog.internal;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.business.catalog.PaymentContent;
import com.vizzavi.ecommerce.business.catalog.PricePoints;


public class CatalogPackageImpl extends CatalogPackage
{
	private    static final long serialVersionUID = 2807458399041112368L;

	private static Logger logger = LoggerFactory.getLogger(CatalogPackageImpl.class);

	

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
//		mKey = key;
//		mCreatedBy = createdBy;
//		mModifiedBy = modifiedBy;
//		mModifiedDate = modifiedDate;
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
			
			//PPM136861 refactoring aL. START
//			mPricingModels = new HashMap<String, String>(impl.mPricingModels);
		}
		//MQC8385 TODO
		if (catalogPackage.getPricePoint()!=null) {
			this.mPricePoint = new PricePointImpl(catalogPackage.getPricePoint(), date);
		}

	}



}




