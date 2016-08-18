package com.vodafone.global.er.decoupling.client;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.Element;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.business.catalog.ExpressData;
import com.vizzavi.ecommerce.business.catalog.ExpressDisplayAttribute;
import com.vizzavi.ecommerce.business.catalog.PaymentContent;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.catalog.PromotionsResult;
import com.vizzavi.ecommerce.business.catalog.internal.BalanceImpact;
import com.vizzavi.ecommerce.business.catalog.internal.BalanceImpacts;
import com.vizzavi.ecommerce.business.catalog.internal.CatalogPackageImpl;
import com.vizzavi.ecommerce.business.catalog.internal.CatalogServiceImpl;
import com.vizzavi.ecommerce.business.catalog.internal.PricePointImpl;
import com.vizzavi.ecommerce.business.catalog.internal.PricePointTier;
import com.vizzavi.ecommerce.business.catalog.internal.PricePointsImpl;
import com.vizzavi.ecommerce.business.catalog.internal.model.DateTimeTier;
import com.vizzavi.ecommerce.business.catalog.internal.model.DayRange;
import com.vizzavi.ecommerce.business.catalog.internal.model.PricingModel;
import com.vizzavi.ecommerce.business.catalog.internal.model.RangeValue;
import com.vizzavi.ecommerce.business.catalog.internal.model.Tier;
import com.vizzavi.ecommerce.business.charging.*;
import com.vizzavi.ecommerce.business.common.ChargingMethod;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.Constants;
import com.vizzavi.ecommerce.business.common.Duration;
import com.vizzavi.ecommerce.business.common.RatingAttributes;
import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.common.ResponseStatus;
import com.vizzavi.ecommerce.business.provision.UpdateServiceStatusAuthorization;
import com.vizzavi.ecommerce.business.selfcare.*;
import com.vizzavi.ecommerce.business.selfcare.BasicAccount;
import com.vizzavi.ecommerce.business.selfcare.TransactionType;
import com.vodafone.global.er.business.selfcare.BalanceFilter;
import com.vodafone.global.er.decoupling.binding.request.ExpressPackageRequest;
import com.vodafone.global.er.decoupling.binding.request.ExpressPackageRequest.ExpressDisplayAttributes;
import com.vodafone.global.er.decoupling.binding.request.ExpressPackageRequest.ServiceIds;
import com.vodafone.global.er.decoupling.binding.request.PurchaseAttributesType;
import com.vodafone.global.er.decoupling.binding.request.PurchaseAttributesType.ResourceBalancesOnly;
import com.vodafone.global.er.decoupling.binding.request.RatingAttributesType;
import com.vodafone.global.er.decoupling.binding.request.SubscriptionAttributesType;
import com.vodafone.global.er.decoupling.binding.response.*;
import com.vodafone.global.er.decoupling.binding.response.CatalogPackageFullType.CatalogServices;
import com.vodafone.global.er.decoupling.binding.response.CatalogPackageFullType.ChildPackageIds;
import com.vodafone.global.er.decoupling.binding.response.CatalogPackageFullType.PricingModels;
import com.vodafone.global.er.decoupling.binding.response.PackageType.PricePoints;
import com.vodafone.global.er.decoupling.binding.response.PricePointFullType.PricePointTiers;
import com.vodafone.global.er.decoupling.binding.response.PricingModelFullType.Tiers;
import com.vodafone.global.er.decoupling.binding.response.PurchaseOptions.Packages;
import com.vodafone.global.er.decoupling.binding.response.v2.ModifyTransaction;
import com.vodafone.global.er.decoupling.binding.response.v2.PaymentTransaction;
import com.vodafone.global.er.decoupling.binding.response.v2.Pricepoint;
import com.vodafone.global.er.decoupling.binding.response.v2.RefundTransaction;
import com.vodafone.global.er.decoupling.binding.response.v2.UsageTransaction;
import com.vodafone.global.er.partner.B2BPartner;
import com.vodafone.global.er.rating.RatedEvent;
import com.vodafone.global.er.util.DateTimeUtil;

class DecouplingConverter   {

	private final static Logger logger = LoggerFactory.getLogger(DecouplingConverter.class);
	private final Locale locale;


	public DecouplingConverter(Locale locale)	{
		this.locale = locale;
	}


	public static ReasonCodeType buildReasonCode(ReasonCode rc) {
		final ReasonCodeType rct = new ReasonCodeType();
		if (rc != null) {
			rct.setCode(rc.getCode());
			rct.setName(rc.getName());
			rct.setSubCode(rc.getSubCode());
		} else {
			rct.setCode(0);
			rct.setName("");
			rct.setSubCode(0);
		}
		return rct;
	}


	public BasicAccount buildAccount(FullAccount jaxbObject)	{

		final BasicAccount account = new BasicAccount(jaxbObject.getMsisdn(), jaxbObject.getBan(), locale, jaxbObject.getBillingCycleDate(), jaxbObject.getUtcOffset(), jaxbObject.getSpId(), jaxbObject.getIsPrepay(), jaxbObject.getChildSpId(), jaxbObject.getSpType(), jaxbObject.isSuppressCourtesyNotifications());

		final List<UserGroup> groups = new ArrayList<UserGroup>();

		final List<FullAccount.UserGroups> userGroups = jaxbObject.getUserGroups();
		if (userGroups != null && userGroups.size()>0)	{
			for (final FullAccount.UserGroups jaxbug: userGroups){
				groups.add(new UserGroup(jaxbug.getName(), jaxbug.getDescription()));
			}
		}
		account.setUserGroupList(groups);

		FullAccount.SpendLimits jaxbSL = jaxbObject.getSpendLimits();
		SpendLimits spendLimits;
		if (jaxbSL!=null)	{
			spendLimits = new SpendLimits(jaxbSL.getPerTxLimit(), 
					jaxbSL.getPerDayLimit(), jaxbSL.getPerMonthLimit(), jaxbSL.getCumulativeSpendDay(), jaxbSL.getCumulativeSpendMonth(), null);

			account.setSpendLimits(spendLimits);
		}

		//ET 219
		account.setLastValidateCallTime(jaxbObject.getLastValidateDate().getTime());
		account.setTimestamp(jaxbObject.getTimestamp().getTime());
		account.setCountry(jaxbObject.getCountry());
		
		return account;
	}

	/**
	 * sets up user groups and spend limits too, but not msisdn, ban, name, etc
	 * @param jaxbObject
	 * @return
	 */
	public BasicAccount buildAccount(BasicAccount jaxbObject)	{

		final BasicAccount account = new BasicAccount(jaxbObject.getMsisdn(), jaxbObject.getBan(), jaxbObject.getName(), locale, jaxbObject.getStatus());

		return account;

	}




	public Subscription[] convertSubscriptionsArray(SelfcareFullSubscriptions payload) 
	{
		logger.debug("Subscription[] convertSubscriptionsArray(SelfcareSubscriptionsType payload)");

		final List<Subscription> subsToReturn = new ArrayList<>();
		final List<SubscriptionFullType> subList = payload.getSubscription();
		final ListIterator<SubscriptionFullType> iter = subList.listIterator();

		for (; iter.hasNext() == true;) 
		{
			Subscription subToAdd   = new Subscription();
			SubscriptionFullType subType = null;
			final Object sub = iter.next();
			subType =  (SubscriptionFullType) sub;

			subToAdd = convertSubscriptionType(subType);
			subsToReturn.add(subToAdd);
		}
		logger.debug("{} Subscriptions converted" , subsToReturn.size());
		return subsToReturn.toArray(new Subscription[subsToReturn.size()]);
	}

	public Subscription convertSubscriptionType(SubscriptionFullType subType) 
	{
		if(logger.isDebugEnabled())
			logger.debug("Subscription convertSubscriptionType(SubscriptionType subType)");


		//MQC8202 - start - was
		//final ERSubscription subscription_ = new ERSubscription();
		final Subscription subscription_ = new Subscription();
		//MQC8202 - end
		//Subscription(CatalogPackage pack, RatingAttributes attrs, Date purchaseDate, Date expiryDate, int status)
		final SubscriptionFullType.ResourceBalances rbType  = subType.getResourceBalances();
		//new ServiceTypeImpl();
		CatalogPackage package_ = new CatalogPackage();

		final CatalogPackageFullType packType = subType.getPackage();
		package_ = convertFullPackageType(packType);

		final SubscriptionFullType.Services servicesTypes_ = subType.getServices();
		if(servicesTypes_ != null)
		{
			final List<String> serviceidsList = servicesTypes_.getServiceId();
			if(serviceidsList != null && serviceidsList.size() >0)
			{
				subscription_.setServiceProvTag(serviceidsList.get(0),"N/A");
			}
		}

		final List<ResourceBalanceFullType> list = rbType.getResourceBalance();
		if(list != null)		{
			final ResourceBalance[] resBals = convertResourceBalanceArray(list);
			subscription_.setResourceBalances(resBals);
		}

		subscription_.setIsRefundable(subType.isIsRefundable());
		subscription_.setPackage(package_);
		//MQC8385 TODO write method Subscription.setPricePoint() and getPricePoint()
		if (package_.getPricePoint()!=null)
			subscription_.setPricePoint(package_.getPricePoint());
		subscription_.setSubscriptionIdLong(Long.parseLong(subType.getId()));
		subscription_.setRatingAttributes(convertRatingAttributes(subType.getRatingAttributes()));
		subscription_.setStatus(subType.getStatus());
		//subscription_.setSuperPackage(subType.isIsSuperPackage());
		subType.getPackage().isIsParentPackage();
		try
		{
			subscription_.setHasExpired(subType.isHasExpired());

			subscription_.setSubscriptionId((subType.getId() != null?subType.getId():""));
			subscription_.setCurrentNoOfOccurences((long)subType.getCurrentNumberOfOccurences());

			final Calendar now = Calendar.getInstance();
			now.add(Calendar.YEAR, 1);
			subscription_.setExpiryDate((subType.getExpiryDate()!= null?subType.getExpiryDate().getTime():now.getTime()));
			subscription_.setPreOrdered(subType.isIsPreordered());

			subscription_.setFinalMinSubscriptionEndDate((subType.getFinalMinSubscriptionEndDate() != null?subType.getFinalMinSubscriptionEndDate().getTime():null));
			subscription_.setOverdueExpiryDate((subType.getOverdueExpiryDate() != null?subType.getOverdueExpiryDate().getTime():null));
			subscription_.setPurchaseDate((subType.getPurchaseDate() != null?subType.getPurchaseDate().getTime():null));
			subscription_.setStartDate((subType.getStartDate() != null?subType.getStartDate().getTime():subType.getPurchaseDate().getTime()));

			subscription_.setStatus(subType.getStatus());

			if(subType.getTransactions() != null)
			{
				try
				{
					final SubscriptionFullType.Transactions transactions_ = subType.getTransactions();

					final List<TransactionFullType> transactionsTypeList_ =  transactions_.getTransaction();
					final ArrayList<Transaction> transactionsList_ = new ArrayList<Transaction>(); 
					for (final TransactionFullType transactionType_ : transactionsTypeList_)
					{
						transactionsList_.add((transactionType_ != null?convertTransaction(transactionType_):null));
					}
					subscription_.setTransactions(transactionsList_);
				}
				catch(final Exception e)
				{
					logger.info("Problem with Decoupling Client added conversions - transaction type:" + e);
				}
			}
			//subscription_.setHasValidMicroService(subType.isHasValidMicroService());
			//			subscription_.setSuperCreditID((subType.getSuperCreditId() != null?subType.getSuperCreditId():null));
			//			if(subType.getSuperCreditBalances() != null && subType.getSuperCreditBalances().getResourceBalance() != null)
			//				subscription_.setSuperCreditBalances(convertResourceBalanceArray(subType.getSuperCreditBalances().getResourceBalance()));
			//
			//			subscription_.setDRMObject(convertDRMObjectFullType(subType.getDrmObject()));

			//6976
			subscription_.setPenaltyCharge(subType.getPenaltyCharge());

			subscription_.setNextCyclePercentValue(subType.getNextCycleDiscount());

			//CR2255 Phase2 - add partner information
			if (subType.getB2BPartner() != null) {
				B2BPartner b2bPartner = new  B2BPartner();
				b2bPartner.setPartnerId(subType.getB2BPartner().getId());
				subscription_.setPartnerId(subType.getB2BPartner().getId());
				b2bPartner.setUrl(subType.getB2BPartner().getUrl());
				b2bPartner.setContactInfo(subType.getB2BPartner().getContactInfo());
				b2bPartner.setEmail(subType.getB2BPartner().getEmail());
				//ET 68 : Addition of partner-name field in ER starts here	
				b2bPartner.setPartnerName(subType.getB2BPartner().getPartnerName());
				//ET 68 : Addition of partner-name field in ER starts here	
				subscription_.setB2BPartner(b2bPartner);
			}

			// MQC 9655
			subscription_.setParentPackageID(subType.getParentPackageId());

			// ET99 add merchant name to selfcare full transactions response starts here
			if(subType.getRatingAttributes() != null && subType.getRatingAttributes().getRatingAttributesType() != null)
				subscription_.setMerchantName(subType.getRatingAttributes().getRatingAttributesType().getMerchantName());
			// ET99 add merchant name to selfcare full transactions response ends here
			//ET-265 - Adding External sub id in response - begin
			subscription_.setExternalSubId(subType.getExternalSubId());
			//ET-265 - Adding External sub id in response - end
		}
		catch(final Exception e)
		{
			logger.info("Problem with Decoupling Client added conversions convertSubscriptionType " + e);
			return null;
		}      
		return subscription_;
	}

	public ResourceBalance[] convertResourceBalanceArray(List<ResourceBalanceFullType> resBalTypes) 
	{
		if(logger.isDebugEnabled())
			logger.debug("ResourceBalance[] convertResourceBalanceArray(List resBalTypes)");

		if(resBalTypes==null)	{	//TODO null check is redundant
			logger.error("Resource Balances are null");
			return new  ResourceBalance[]{};
		}	else if (resBalTypes.isEmpty())	{
			logger.warn("Resource Balances are empty");
			return new  ResourceBalance[]{};
		}
		final ResourceBalanceFullType[] resBalArr = resBalTypes.toArray(new ResourceBalanceFullType[resBalTypes.size()]);

		final ResourceBalance[]rv = new ResourceBalance[resBalArr.length];
		for(int i=0;i<resBalArr.length;i++)
		{
			rv[i] = convertFullResourceBalance(resBalArr[i]);
		}
		return rv;
	}

	public ResourceBalance convertResourceBalance(ResourceBalanceType resBalType) 
	{
		if(logger.isDebugEnabled())
			logger.debug("ResourceBalance convertResourceBalance(ResourceBalanceType resBalType)");

		if (resBalType != null) 
		{
			if(resBalType.getChargingResource() != null)
			{
				final double bal = resBalType.getBalance();
				final ChargingResource chRes = convertChargingResourceType(resBalType.getChargingResource());
				final ResourceBalance resourceBalance_ = new ResourceBalance(chRes, bal);
				resourceBalance_.setSubscriptionIdLong((resBalType.getSubscriptionId() != null?new Long(resBalType.getSubscriptionId()):-1));
				return resourceBalance_;
			}
			else
				return new ResourceBalance();
		}
		else
			return new ResourceBalance();    
	}

	public ResourceBalance convertFullResourceBalance(ResourceBalanceFullType resBalType) 
	{
		if(logger.isDebugEnabled())
			logger.debug("ResourceBalance convertFullResourceBalance(ResourceBalanceFullType resBalType)");

		if (resBalType != null) 
		{
			if(resBalType.getChargingResource() != null)
			{
				final double bal = resBalType.getBalance();
				final ChargingResource chRes = convertChargingResourceFullType(resBalType.getChargingResource());
				final ResourceBalance resourceBalance_ = new ResourceBalance(chRes, bal);
				resourceBalance_.setSubscriptionIdLong((resBalType.getSubscriptionId() != null?new Long(resBalType.getSubscriptionId()):-1));
				return resourceBalance_;
			}
			else
				return new ResourceBalance();
		}
		else
			return new ResourceBalance();    
	}

	public ChargingResource convertChargingResourceType(com.vodafone.global.er.decoupling.binding.response.ChargingResourceType chResType) 
	{
		if(logger.isDebugEnabled())
			logger.debug("ChargingResource convertChargingResourceType(com.vodafone.global.er.decoupling.binding.response.ChargingResourceType chResType)");

		final int code = Integer.parseInt(chResType.getCode());
		final ChargingResource rv = new ChargingResource(code , chResType.getName());
		return rv;
	}

	public ChargingResource convertChargingResourceFullType(ChargingResourceFullType chResFullType) 
	{
		if(logger.isDebugEnabled())
			logger.debug("convertChargingResourceFullType(ChargingResourceFullType chResFullType)");
		if(chResFullType.getChargingResourceType() != null)
		{
			final ChargingResourceType chResType = chResFullType.getChargingResourceType();
			final int code = Integer.parseInt(chResType.getCode());
			final ChargingResource rv = new ChargingResource(code , chResType.getName());
			if (isBlank(rv.getName()) && isNotBlank(chResType.getTranslatedResourceName()))
				rv.setName(chResType.getTranslatedResourceName());
			return rv;
		}
		else
		{
			logger.error("ChargingResourceFullType is null");
			return null;
		}
	}

	public CatalogPackage convertPackageType(PackageType packType)
	{
		if(logger.isDebugEnabled())
			logger.debug("CatalogPackage convertPackageType(PackageType packType)");

		if(packType != null)
		{
			try
			{        
				final CatalogPackageImpl cataloPackageImpl_ = new CatalogPackageImpl(
						(packType.getSimplePackageId() != null?packType.getSimplePackageId():""),
						(packType.getName() != null?packType.getName():""),
						(packType.getDescription() != null?packType.getDescription():""));


				cataloPackageImpl_.setPricingText1((packType.getPricingText1() != null?packType.getPricingText1():""));
				cataloPackageImpl_.setPricingText2((packType.getPricingText2() != null?packType.getPricingText2():""));
				cataloPackageImpl_.setPackageType((packType.getPackageType() != null?packType.getPackageType():""));
				cataloPackageImpl_.setReserveOnly(packType.isIsReserveOnly());
				cataloPackageImpl_.setExpressPurchase(packType.isExpressPurchase());
				cataloPackageImpl_.setRecieptingFlag(packType.isIsReceiptingFlag());
				cataloPackageImpl_.setPricePointOrder(packType.isIsPricePointOrdered());
				//				cataloPackageImpl_.setLogoId((packType.getPartnerLogo() != null?packType.getPartnerLogo():""));
				cataloPackageImpl_.setPartnerInfo((packType.getPartnerText() != null?packType.getPartnerText():""));

				


				if(packType.isIsCalendarPackage())
					cataloPackageImpl_.setPackageType(CatalogPackage.CALENDAR_PACKAGE_TYPE);
				else if(packType.isIsEventPackage())
					cataloPackageImpl_.setPackageType(CatalogPackage.EVENT_PACKAGE_TYPE);


				//				DRMType drmType_ = DRMType.NOT_DRM;
				//				try
				//				{
				//					if(packType.getDrmType().getName() != null && !packType.getDrmType().getName().equalsIgnoreCase(""))
				//						drmType_ = DRMType.convertStringToDRMType(packType.getDrmType().getName());
				//				}
				//				catch(final Exception e)
				//				{
				//					logger.error("Problem converting DRMType will set NOT_DRM:" + e);
				//					drmType_ = DRMType.NOT_DRM;
				//				}

				//				cataloPackageImpl_.setDRMType(drmType_);

				final PricePointType pricePointType_ = packType.getPricePoint();
				//PricePointImpl dummyPricepoint_ = new PricePointImpl();
				PricePointImpl singlePricePoint_= null;
				if(pricePointType_ != null)
				{
					if(pricePointType_.getId() != null)
					{
						singlePricePoint_ = this.convertPricePointType(pricePointType_);
						cataloPackageImpl_.setPricePoint(singlePricePoint_);
					}
				}

				final PricePoints pps_ = packType.getPricePoints();

				if(pps_ != null)
				{
					final List<PricePointType> points_ = pps_.getPricePoint();
					if(points_ != null)
					{
						final HashMap<String, PricePoint> map_ = new HashMap<String, PricePoint>();
						for (final PricePointType pricePoint_ : points_)
						{
							map_.put(pricePoint_.getId(), this.convertPricePointType(pricePoint_));
						}
						cataloPackageImpl_.setPricePoints(new PricePointsImpl(map_));
					}
				}
				else
				{
					if(singlePricePoint_ != null)
					{
						try
						{
							if(logger.isDebugEnabled())
								logger.debug("Setting single pricepoint as map");
							final Map<String, PricePoint> map_ = new HashMap<String, PricePoint>();
							map_.put(singlePricePoint_.getId(), singlePricePoint_);
							cataloPackageImpl_.setPricePoints(new PricePointsImpl(map_));
						}
						catch(final Exception e)
						{
							logger.error("Problem setting single pricepoint");
						}
					}

				}

				return cataloPackageImpl_;
			}
			catch(final Exception e)
			{
				logger.error("Problem converting values added for Decoupling client:" + e);
				return null;
			}
		}
		else
		{
			logger.error("PackageFullType is null");
			return null;
		}

	}

	public CatalogPackage convertFullPackageType(CatalogPackageFullType packType)
	{
		if(logger.isDebugEnabled())
			logger.debug("CatalogPackage convertFullPackageType(CatalogPackageFullType packType)");

		if(packType != null)
		{
			try
			{        
				final CatalogPackageImpl cataloPackageImpl_ = new CatalogPackageImpl(
						(packType.getId() != null?packType.getId():""),
						(packType.getName() != null?packType.getName():""),
						(packType.getDescription() != null?packType.getDescription():""));

				//MQC 7578 - set the simple package id
				cataloPackageImpl_.setPackageId(packType.getSimplePackageId());
				cataloPackageImpl_.setPricingText1((packType.getPricingText1() != null?packType.getPricingText1():""));
				cataloPackageImpl_.setPricingText2((packType.getPricingText2() != null?packType.getPricingText2():""));
				cataloPackageImpl_.setPackageType((packType.getPackageType() != null?packType.getPackageType():""));
				cataloPackageImpl_.setReserveOnly(packType.isIsReserveOnly());
				cataloPackageImpl_.setExpressPurchase(packType.isExpressPurchase());
				cataloPackageImpl_.setRecieptingFlag(packType.isIsReceiptingFlag());
				cataloPackageImpl_.setPricePointOrder(packType.isIsPricePointOrdered());
				//				cataloPackageImpl_.setLogoId((packType.getPartnerLogo() != null?packType.getPartnerLogo():""));
				cataloPackageImpl_.setPartnerInfo((packType.getPartnerText() != null?packType.getPartnerText():""));
				cataloPackageImpl_.setKpiPackageProductCategory(packType.getKpiPackageProductCategory());
				cataloPackageImpl_.setRevenueShareByUsage(packType.isIsRevenueShareByUsage());
				if (packType.getPaymentContent()!=null)
					cataloPackageImpl_.setPaymentContent(convertPaymentContent(packType.getPaymentContent()));
				cataloPackageImpl_.setParentPackageId(packType.getParentPackageId());

				if(packType.isIsCalendarPackage())
					cataloPackageImpl_.setPackageType(CatalogPackage.CALENDAR_PACKAGE_TYPE);

				if(packType.isIsEventPackage())
					cataloPackageImpl_.setPackageType(CatalogPackage.EVENT_PACKAGE_TYPE);


				//				DRMType drmType_ = DRMType.NOT_DRM;
				//				try
				//				{
				//					if(packType.getDrmType().getName() != null && !packType.getDrmType().getName().equalsIgnoreCase(""))
				//						drmType_ = DRMType.convertStringToDRMType(packType.getDrmType().getName());
				//				}
				//				catch(final Exception e)
				//				{
				//					logger.error("Problem converting DRMType will set NOT_DRM:" + e);
				//					drmType_ = DRMType.NOT_DRM;
				//				}

				//				cataloPackageImpl_.setDRMType(drmType_);

				final PricePointFullType pricePointType_ = packType.getPricePoint();
				PricePointImpl singlePricePoint_= null;
				//CR1455 - start
				cataloPackageImpl_.setIsSuperPackage(packType.isIsSuperPackage());
				if(packType.getPricingModels() != null)
				{
					final PricingModels pmTypes_ = packType.getPricingModels();
					final List<PricingModelFullType> pmList_ = pmTypes_.getPricingModel();
					final HashMap<String, PricingModel> map_ = new HashMap<String, PricingModel>();
					for (final PricingModelFullType pricingModelFullType : pmList_)
					{
						final PricingModel pricingModel_ = buildPricingModel(pricingModelFullType);
						map_.put(pricingModel_.getId(), pricingModel_);
						cataloPackageImpl_.setPriceModels(map_);
						cataloPackageImpl_.addPricingModel(pricingModel_.getId());
					}
				}

				if(pricePointType_ != null && pricePointType_.getId() != null && pricePointType_.getId().trim().length()>0)
				{
					singlePricePoint_ = this.convertFullPricePointType(pricePointType_, cataloPackageImpl_.getPricingModel());
					cataloPackageImpl_.setPricePoint(singlePricePoint_);
				}
				//CR1455 - end

				final CatalogPackageFullType.PricePoints pps_ = packType.getPricePoints();

				if(pps_ != null)
				{
					final List<PricePointFullType> points_ = pps_.getPricePoint();
					if(points_ != null)
					{
						final HashMap<String, PricePoint> map_ = new HashMap<>();
						for (final PricePointFullType pricePoint_ : points_)
						{
							map_.put(pricePoint_.getId(), this.convertFullPricePointType(pricePoint_, cataloPackageImpl_.getPricingModel()));
						}
						cataloPackageImpl_.setPricePoints(new PricePointsImpl(map_));
					}
				}
				else
				{
					if(singlePricePoint_ != null)
					{
						try
						{
							if(logger.isDebugEnabled())
								logger.debug("Setting single pricepoint as map");
							final HashMap<String, PricePoint> map_ = new HashMap<>();
							map_.put(singlePricePoint_.getId(), singlePricePoint_);
							cataloPackageImpl_.setPricePoints(new PricePointsImpl(map_));
						}
						catch(final Exception e)
						{
							logger.error("Problem setting single pricepoint");
						}
					}

				}

				cataloPackageImpl_.setIsPackageModel(packType.isIsPackageModel());

				//CR1455 - start
				try
				{
					final CatalogServices serviceNamesTypes_ =  packType.getCatalogServices();
					if(serviceNamesTypes_ != null)
					{
						final List<CatalogServiceFullType> catalogServicesList_ = serviceNamesTypes_.getCatalogService();
						final List<CatalogService> services_ = new ArrayList<CatalogService>();
						for (final CatalogServiceFullType catalogService_: catalogServicesList_)
						{
							final CatalogService service_ = convertService(catalogService_);
							//new CatalogService(catalogService_.getId(), catalogService_.getName(), "");
							services_.add(service_);
						}
						//_log.error("services_ " + services_.size());
						cataloPackageImpl_.setServices(services_);
						//_log.error("cataloPackageImpl_ map" + cataloPackageImpl_.getServiceMap().size());
						//_log.error("cataloPackageImpl_ []" + cataloPackageImpl_.getServices().length);
					}
				}
				catch(final Exception e)
				{
					logger.error("Problem adding service names " + e);
				}
				cataloPackageImpl_.setParentPackageId(packType.getParentPackageId());
				cataloPackageImpl_.setParentPackage(packType.isIsParentPackage());
				try
				{
					final ChildPackageIds childPackagesIds_ = packType.getChildPackageIds();
					if(childPackagesIds_ != null)
					{
						final List<String> childPackagesIdsList_ = childPackagesIds_.getChildPackageId();
						cataloPackageImpl_.setChildPackages(new ArrayList<>(childPackagesIdsList_));
					}
				}
				catch(final Exception e)
				{
					logger.error("Problem setting Child Packages Ids:" +e);
				}

				//CR1455 - end
				//JIRA ET-302 - VF-IT ER clients needs custom-attributes - Start
				if (null != packType.getCustomFields() && null != packType.getCustomFields().getCustomField()) {
					for (CustomFieldFullType customField : packType.getCustomFields().getCustomField()) {
						cataloPackageImpl_.setCustomField(customField.getName(), customField.getValue());
					}
				}
				//JIRA ET-302 - VF-IT ER clients needs custom-attributes - End

				cataloPackageImpl_.setDynamicDefault(packType.isIsDynamicDefault());

				return cataloPackageImpl_;
			}
			catch(final Exception e)
			{
				logger.error("Problem converting values added for Decoupling client:" + e);
				return null;
			}
		}
		else
		{
			logger.error("PackageFullType is null");
			return null;
		}

	}

	private CatalogService convertService(CatalogServiceFullType serviceType) {
		final CatalogServiceImpl catalogService_ = new CatalogServiceImpl((serviceType.getId() != null?serviceType.getId():""),
				(serviceType.getName() != null?serviceType.getName():""),
				(serviceType.getDescription() != null?serviceType.getDescription():""));
		PaymentContentFullType pc = serviceType.getPaymentContent();
		if (pc!=null)
			catalogService_.setPaymentContent(convertPaymentContent(pc));
		catalogService_.setProvisioningTag((serviceType.getProvisioningTag() != null?serviceType.getProvisioningTag():""));
		//JIRA ET-302 - VF-IT ER clients needs custom-attributes - Start
		if (null != serviceType.getCustomFields() && null != serviceType.getCustomFields().getCustomField()) {
			for (CustomFieldFullType customField : serviceType.getCustomFields().getCustomField()) {
				catalogService_.setCustomField(customField.getName(), customField.getValue());
			}
		}
		//JIRA ET-302 - VF-IT ER clients needs custom-attributes - End
		return catalogService_;
	}

	private PaymentContent convertPaymentContent(PaymentContentFullType pc){
		PaymentContent rv =null;
		if (pc!=null)
			rv= new PaymentContent(pc.getCategory(), pc.getDescription(), pc.getMerchant(), pc.getMerchantDescription(), pc.getItemVolume(), pc.getServiceType(), pc.getPromotion());
		return rv;
	}

	//MQC7353
	public CatalogPackage convertLittlePackageType(CatalogPackageLittleType packType)
	{
		if(logger.isDebugEnabled())
			logger.debug("CatalogPackage convertLittlePackageType(CatalogPackageLittleType packType)");

		if(packType != null)
		{
			final CatalogPackageImpl cataloPackageImpl_ = new CatalogPackageImpl(
					(packType.getId() != null?packType.getId():""),
					(packType.getName() != null?packType.getName():""),
					(""));


			cataloPackageImpl_.setPackageType((packType.getPackageType() != null?packType.getPackageType():""));



			cataloPackageImpl_.setDynamicDefault(packType.isIsDynamicDefault());

			final PricePointLittleType pricePointType_ = packType.getPricePoint();

			PricePointImpl singlePricePoint_= null;

			if(pricePointType_ != null && pricePointType_.getId() != null && pricePointType_.getId().trim().length()>0)
			{
				singlePricePoint_ = this.convertLittlePricePointType(pricePointType_);
				cataloPackageImpl_.setPricePoint(singlePricePoint_);
			}
			//CR1455 - end

			final CatalogPackageLittleType.PricePoints pps_ = packType.getPricePoints();

			if(pps_ != null)
			{
				final List<PricePointLittleType> points_ = pps_.getPricePoint();
				if(points_ != null)
				{
					final HashMap<String, PricePoint> map_ = new HashMap<>();
					for (final PricePointLittleType pricePoint_ : points_)
					{
						map_.put(pricePoint_.getId(), this.convertLittlePricePointType(pricePoint_));
					}
					cataloPackageImpl_.setPricePoints(new PricePointsImpl(map_));
				}
			}
			else
			{
				if(singlePricePoint_ != null)
				{
					try	{
						logger.debug("Setting single pricepoint as map");
						final HashMap<String, PricePoint> map_ = new HashMap<>();
						map_.put(singlePricePoint_.getId(), singlePricePoint_);
						cataloPackageImpl_.setPricePoints(new PricePointsImpl(map_));
					}
					catch(final Exception e)
					{
						logger.error("Problem setting single pricepoint");
					}
				}

			}


			return cataloPackageImpl_;
		}else{
			return null;
		}

	}  

	public CatalogPackage[] buildPackages(Packages packType) 
	{
		if(logger.isDebugEnabled())
			logger.debug("CatalogPackage[] buildPackages(PackagesType packType)");

		final List<CatalogPackage> packsToReturn = new ArrayList<>();
		final List<PackageType> packList = packType.getPackage();
		final Iterator<PackageType> iter = packList.listIterator();
		for (; iter.hasNext() == true;) 
		{ 
			final PackageType p =iter.next(); 
			packsToReturn.add(convertPackageType(p));
		}




		return packsToReturn.toArray(new CatalogPackage[packsToReturn.size()]);
	}

	public CatalogPackage[] buildPackages(
			FindPackagesWithServiceResponse.Packages packages) {
		final List<CatalogPackage> packsToReturn = new ArrayList<>();

		for (final PackageType pack: packages.getPackage())	{

			packsToReturn.add(convertPackageType(pack));
		}
		return packsToReturn.toArray(new CatalogPackage[packsToReturn.size()]);

	}



	public CatalogService convertService(ErServiceFullType serviceType)
	{
		if(logger.isDebugEnabled())
			logger.debug("CatalogService convertService(ErServiceType serviceType)");

		final CatalogServiceImpl catalogService_ = new CatalogServiceImpl((serviceType.getId() != null?serviceType.getId():""),
				(serviceType.getName() != null?serviceType.getName():""),
				(serviceType.getDescription() != null?serviceType.getDescription():""));

		catalogService_.setPricingText1((serviceType.getPricingText1() != null?serviceType.getPricingText1():""));
		catalogService_.setPricingText2((serviceType.getPricingText2() != null?serviceType.getPricingText2():""));
		catalogService_.setProvisioningSystem((serviceType.getProvisioningSystem() != null?serviceType.getProvisioningSystem():""));
		catalogService_.setProvisioningTag((serviceType.getProvisioningTag() != null?serviceType.getProvisioningTag():""));
		catalogService_.setServiceType((serviceType.getServiceType() != null?serviceType.getServiceType():""));
		catalogService_.setUsageId((serviceType.getUsageId() != null?serviceType.getUsageId():""));

		final PricePointFullType pricePointType_ = serviceType.getPricePoint();
		//PricePoint dummyPricepoint_ = null;
		if(pricePointType_ != null)
		{
			if(pricePointType_.getId() != null)
				catalogService_.setPricePoint(this.convertFullPricePointType(pricePointType_,null));
		}

		final ErServiceFullType.PricePoints pps_ = serviceType.getPricePoints();
		final List<PricePointFullType> points_ = pps_.getPricePoint();
		final HashMap<String, PricePoint> map_ = new HashMap<>();
		for (final PricePointFullType pricePoint_ : points_)
		{
			map_.put(pricePoint_.getId(), this.convertFullPricePointType(pricePoint_,null));
		}
		catalogService_.setPricePoints(new PricePointsImpl(map_));
		return catalogService_;
	}

	/**
	 * NB channel cannot be set since it's not in the response
	 * @param ppType
	 * @return
	 */
	public PricePointImpl convertPricePointType(PricePointType ppType)
	{
		if(logger.isDebugEnabled())
			logger.debug("PricePointImpl convertPricePointType(PricePointType ppType)");

		if(ppType != null)
		{
			//final double rate = ppType.getRate();
			final double standRate = ppType.getStandardRate();

			final RatedEvent ratedEvent = new RatedEvent();

			ratedEvent.setNetRate(ppType.getNetRate());
			ratedEvent.setNetStandardRate(standRate);

			final BaseAuthorization baseAuth = new BaseAuthorization(ratedEvent);
			try
			{
				final PricePointImpl pricePointImpl_ = new PricePointImpl(baseAuth);
				pricePointImpl_.setResource(new Integer(ppType.getChargingResource().getCode()));
				pricePointImpl_.setId(ppType.getId());
				pricePointImpl_.setDiscountPromoText((ppType.getDiscountPromoText() != null?ppType.getDiscountPromoText():""));
				pricePointImpl_.setPricingText1(ppType.getPricingText1());
				pricePointImpl_.setPricingText1(ppType.getPricingText2());
				pricePointImpl_.setPromoCodes(new String[]{(ppType.getPromoCode() != null?ppType.getPromoCode():"")});
				pricePointImpl_.setRateWithoutTax(ppType.getNetRate());       
				pricePointImpl_.setDuration(ppType.getDuration().getValue());
				pricePointImpl_.setPromoCodes( new String [] {ppType.getPromoCode()} );
				pricePointImpl_.setPricingText1(ppType.getPricingText1());
				pricePointImpl_.setPricingText2(ppType.getPricingText2());
				//pricePointImpl_.setTariff(ppType.getTariff());
				pricePointImpl_.setTranslatedPricingText( ppType.getPurchaseLinkText() );

				if(ppType.isIsRecurringPackage())
					pricePointImpl_.setChargingMethod(ChargingMethod.RECURRING);
				else
					pricePointImpl_.setChargingMethod(ChargingMethod.NON_RECURRING);

				//pricePointImpl_.setChannel(ppType.getChannel());	
				//pricePointImpl_.setChannel(getChannelFromPricePointId(ppType.getId()));	
				ppType.getUserGroups();
				//CR2241start
				//looks like missed implementation - was:
				//				ppType.isIsActive();
				//				ppType.isIsDiscount();
				//				ppType.isIsPreOrder();
				//				ppType.isIsPreview();
				//				ppType.isIsTrial();
				//				ppType.isIsValidSuperCreditOption();
				//changed to:
				if (ppType.getStartDate() != null){
					pricePointImpl_.setStartDate(ppType.getStartDate().getTime()); //handles: ppType.isIsActive();
				}
				if (ppType.getExpiryDate() != null){
					pricePointImpl_.setExpiryDate(ppType.getExpiryDate().getTime()); //handles: ppType.isIsActive();
				}
				pricePointImpl_.setIsDiscount(ppType.isIsDiscount());
				pricePointImpl_.setPreOrder(ppType.isIsPreOrder());
				if (ppType.getPromoCode() != null){
					pricePointImpl_.setPromoCodes(new String []{ppType.getPromoCode()} ); //does: ppType.isIsPreview() && isIsTrial();
				}

				//can't do this final one unless we add field to PricePointImpl or convert differently, yuk :( 
				//ppType.isIsValidSuperCreditOption();

				//				pricePointImpl_.setFixedRecurrence(ppType.getFixedNumRecurrences());
				//				pricePointImpl_.setBearer(ppType.getBearerId());
				//CR2241end

				final BalanceImpactRates balanceImpactsType_ =  ppType.getBalanceImpactRates();
				if(balanceImpactsType_ != null)
				{
					if(logger.isDebugEnabled())
						logger.debug("BalanceImpacts exist");

					final List<BalanceImpactRate> balanceImpactsType =  balanceImpactsType_.getBalanceImpactRate();
					final BalanceImpacts balanceImpacts_ = new BalanceImpacts();
					for (final BalanceImpactRate balanceImpactType_ : balanceImpactsType)
					{
						final BalanceImpact balanceImpact_ = new BalanceImpact(new ChargingResource(new Integer(balanceImpactType_.getChargingResourceCode()), ""), balanceImpactType_.getRate());
						balanceImpacts_.addBalanceImpact(balanceImpact_);
					}
					pricePointImpl_.setBalanceImpacts(balanceImpacts_, new Date());
				}

				try
				{
					final DurationType durationType_ = ppType.getDuration();
					if(durationType_ != null)
					{ 
						if(logger.isDebugEnabled())
							logger.debug("DurationType exist");

						pricePointImpl_.setDuration(durationType_.getDurationCode());
					}
				}
				catch(final Exception e)
				{
					logger.error("Problem converting Duration:" + e);
				}

				pricePointImpl_.setFixedExpiryDate((ppType.getFixedExpiryDate() != null?ppType.getFixedExpiryDate().getTime():null));
				pricePointImpl_.setInteractiveFlag((ppType.getInteractiveFlag() != null?ppType.getInteractiveFlag():""));
				pricePointImpl_.setMinSubPeriod(ppType.getMinSubPeriod());
				pricePointImpl_.setOrder(ppType.getOrder());
				pricePointImpl_.setPaymentType(ppType.getPaymentType());
				pricePointImpl_.setPricepointIdLink((ppType.getPricePointIdLink() != null?ppType.getPricePointIdLink():""));
				pricePointImpl_.setTranslatedPricingText((ppType.getPurchaseLinkText() != null?ppType.getPurchaseLinkText():""));

				//				pricePointImpl_.setRoamingGrossAmount(ppType.getRoamingGrossAmount());
				//				pricePointImpl_.setRoamingNetAmount(ppType.getRoamingNetAmount());
				//				final SuperCreditPricePointsType superCreditPricePointsType_ = ppType.getSuperCreditPricePoints();
				//				if(superCreditPricePointsType_ != null)
				//				{
				//					final List<SuperCreditPricePointType> superCreditPricePointTypes_ = superCreditPricePointsType_.getSuperCreditPricePoint();
				//					if(superCreditPricePointTypes_ != null)
				//					{
				//						final SuperCreditPricePoint[] superCreditPricePoints_ = new SuperCreditPricePoint[superCreditPricePointTypes_.size()];
				//						if(superCreditPricePoints_ != null)
				//						{
				//							int i=0;
				//							for (final SuperCreditPricePointType superCreditPricePointType_ : superCreditPricePointTypes_)
				//							{
				//								superCreditPricePoints_[i++] = convertSuperCreditPricePointType(superCreditPricePointType_);          
				//							}
				//							pricePointImpl_.setCreditPurchasePricePoints(superCreditPricePoints_);
				//						}
				//					}
				//				}

				//TODO remove this crap
				//				final NetworkCode networkCode_ = new NetworkCode();
				//				if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DOMESTIC]))
				//					networkCode_.setRoamingType(ErCoreConst.ROAMING_DOMESTIC);
				//				else if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID]))
				//					networkCode_.setRoamingType(ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID);
				//				else if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_OFF_FOOTPRINT]))
				//					networkCode_.setRoamingType(ErCoreConst.ROAMING_OFF_FOOTPRINT);
				//				else if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_ON_FOOTPRINT]))
				//					networkCode_.setRoamingType(ErCoreConst.ROAMING_ON_FOOTPRINT);

				//pricePointImpl_.setUserGroups(new String[]{"*"});

				try	{
					if(ppType.getUserGroups() != null && ppType.getUserGroups().getUserGroup() != null)	{
						//a pricepoint can only have ONE usergroup
						String fromUGArray = ppType.getUserGroups().getUserGroup().get(0);						
						if (StringUtils.isNotBlank(fromUGArray))
							pricePointImpl_.setUserGroup(fromUGArray);
					}
				}	catch(final Exception e)	{
					logger.error("Problem setting User Groups:" + e);
				}

				return pricePointImpl_;
			}
			catch(final Exception e)
			{
				logger.error("Problem converting values in Pricepoint:" + e);
				return null;
			}
		}
		else
		{
			logger.error("PricepointFullType is null");
			return null;
		}
	}

	public PricePointImpl convertFullPricePointType(PricePointFullType ppType, String pricingModel)
	{
		if(logger.isDebugEnabled())
			logger.debug("PricePointImpl convertFullPricePointType(PricePointFullType ppType)");

		if(ppType != null)
		{
			try
			{
				final PricePointImpl pricePointImpl_ = new PricePointImpl();
				pricePointImpl_.setResource(new Integer(ppType.getChargingResource().getChargingResourceType().getCode()));
				pricePointImpl_.setId(ppType.getId());
				pricePointImpl_.setDiscountPromoText((ppType.getDiscountPromoText() != null?ppType.getDiscountPromoText():""));
				pricePointImpl_.setPricingText1(ppType.getPricingText1());
				pricePointImpl_.setPricingText1(ppType.getPricingText2());
				pricePointImpl_.setPromoCodes(new String[]{(ppType.getPromoCode() != null?ppType.getPromoCode():"")});
				pricePointImpl_.setRateWithoutTax(ppType.getNetRate());
				pricePointImpl_.setTaxRate(ppType.getTaxRate());
				pricePointImpl_.setDuration(ppType.getDuration().getValue());
				pricePointImpl_.setPromoCodes( new String [] {ppType.getPromoCode()} );
				pricePointImpl_.setPricingText1(ppType.getPricingText1());
				pricePointImpl_.setPricingText2(ppType.getPricingText2());
				pricePointImpl_.setBearer((ppType.getBearerId() != null?ppType.getBearerId():null));
				pricePointImpl_.setChargingMethod(ppType.getChargingMethod());
				//MQC7098
				pricePointImpl_.setSubscriptionDuplicate(ppType.isAllowDuplicate());
				//pricePointImpl_.setTariff(ppType.getTariff());//ET104
				
				ppType.isIsActive();
				ppType.isIsDiscount();
				ppType.isIsPreOrder();
				ppType.isIsPreview();
				ppType.isIsTrial();
				ppType.isIsValidSuperCreditOption();

				final ChargingResourceFullType chargingResourceTypes_ = ppType.getChargingResource();

				if(chargingResourceTypes_ != null)
				{
					final ChargingResourceType crt_ = chargingResourceTypes_.getChargingResourceType();
					if(crt_ != null)
					{
						try
						{
							pricePointImpl_.setResource(new Integer(crt_.getCode()));
						}
						catch(final Exception e)
						{
							logger.error("Wrong format on chargingResource code:" + e);
						}
					}
				}

				final PricePointFullType.BalanceImpacts balanceImpacts_ = ppType.getBalanceImpacts();
				if(balanceImpacts_ != null)
				{
					final BalanceImpacts bis = new BalanceImpacts();
					final List<ChargingResourceFullType> balanceImpactsList_ = balanceImpacts_.getBalanceImpact();
					for (final ChargingResourceFullType chargingResourceFullType : balanceImpactsList_)
					{

						final ChargingResourceType c_ =  chargingResourceFullType.getChargingResourceType();
						final BalanceImpact b_ = new BalanceImpact(new ChargingResource(new Integer(c_.getCode()),c_.getName()),pricePointImpl_.getNetRate());
						bis.addBalanceImpact(b_);
					}
					pricePointImpl_.setBalanceImpacts(bis,new Date());  
				}


				try
				{
					final DurationType durationType_ = ppType.getDuration();
					if(durationType_ != null)
					{ 
						if(logger.isDebugEnabled())
							logger.debug("DurationType exist");

						pricePointImpl_.setDuration(durationType_.getDurationCode());
					}
				}
				catch(final Exception e)
				{
					logger.error("Problem converting Duration:" + e);
				}

				pricePointImpl_.setExpiryDate((ppType.getExpiryDate()!= null?ppType.getExpiryDate().getTime():null));
				pricePointImpl_.setFixedExpiryDate((ppType.getFixedExpiryDate() != null?ppType.getFixedExpiryDate().getTime():null));
				pricePointImpl_.setStartDate((ppType.getStartDate() != null?ppType.getStartDate().getTime():null));
				pricePointImpl_.setInteractiveFlag((ppType.getInteractiveFlag() != null?ppType.getInteractiveFlag():""));
				pricePointImpl_.setMinSubPeriod(ppType.getMinSubPeriod());
				pricePointImpl_.setOrder(ppType.getOrder());
				pricePointImpl_.setPaymentType(ppType.getPaymentType());
				pricePointImpl_.setPricepointIdLink((ppType.getPricePointIdLink() != null?ppType.getPricePointIdLink():""));
				pricePointImpl_.setTranslatedPricingText((ppType.getPurchaseLinkText() != null?ppType.getPurchaseLinkText():""));
				//				pricePointImpl_.setRoamingGrossAmount(ppType.getRoamingGrossAmount());
				//				pricePointImpl_.setRoamingNetAmount(ppType.getRoamingNetAmount());
				//MQC7098
				pricePointImpl_.setSubscriptionDuplicate(ppType.isAllowDuplicate());

				try
				{
					if(ppType.getBearerId() != null)
					{
						pricePointImpl_.setBearerIds(new String[]{ppType.getBearerId()});
					}
				}
				catch(final Exception e)
				{
					logger.error("Problem setting baerer id " + e);
				}

				if (StringUtils.isNotBlank(ppType.getUserGroup()))
					pricePointImpl_.setUserGroup(ppType.getUserGroup());
				//a pricepoint can only have ONE usergroup

				try	{
					if(ppType.getUserGroups() != null && ppType.getUserGroups().getUserGroup() != null)	{
						String fromUGArray = ppType.getUserGroups().getUserGroup().get(0);

						if (!fromUGArray.equals(pricePointImpl_.getUserGroup()))
							logger.warn("different user groups in response: {} and {}", fromUGArray, ppType.getUserGroup());

						if (StringUtils.isNotBlank(fromUGArray))
							pricePointImpl_.setUserGroup(fromUGArray);
					}
				}	catch(final Exception e)	{
					logger.error("Problem setting User Groups:" + e);
				}

				pricePointImpl_.setIsHistoric(ppType.isIsHistoric());
				try
				{
					final PricePointTiers tiersType_ = ppType.getPricePointTiers();
					if(tiersType_ != null)
					{
						final List<PricePointTierFullType> tiersTypeList_ = tiersType_.getPricePointTier();
						final HashMap<String, PricePointTier> tierMap_ = new HashMap<String, PricePointTier>();
						for (final PricePointTierFullType pricePointTierFullType_ : tiersTypeList_)
						{
							if(pricePointTierFullType_ != null)
							{
								final PricePointTier tier_ = new PricePointTier();
								tier_.setTier(pricePointTierFullType_.getTier());
								final List<BalanceImpactFullType> biList_ = pricePointTierFullType_.getBalanceImpacts().getBalanceImpact();
								final BalanceImpacts b_ = new BalanceImpacts();
								for (final BalanceImpactFullType balanceImpactType_ : biList_)
								{
									b_.addBalanceImpact(convertFullBalanceImpact(balanceImpactType_));
								}
								tier_.setBalanceImpacts(b_);
								tier_.setPromotionalPrice(pricePointTierFullType_.getPromotionalPrice());
								if(pricingModel != null)
									tier_.setPricingModel(pricingModel);

								tierMap_.put(tier_.getTier() + pricingModel, tier_);
							}
						}
						pricePointImpl_.setPricePointTiers(tierMap_);
						logger.debug("pricePointImpl_.setPricePointTiers length: {}" , pricePointImpl_.getPricePointTiers().length);
					}
				}
				catch(final Exception e)
				{
					logger.error("Problem converting PricePointTiersType:" + e);
				}
				//CR1455 - end

				//MQC 8947, add express flag and channel
				pricePointImpl_.setExpressFlag(ppType.isIsExpress());
				if (ppType.getChannel()>0)	//defaults to 0 if ER didn't put it in the response
					pricePointImpl_.setChannel(ppType.getChannel());	
				else	//MQC9516
					pricePointImpl_.setChannel(Constants.INT_MATCH_ALL);

				pricePointImpl_.setTariff(getTariffFromPricePointId(pricePointImpl_.getId()));
				//JIRA ET-302 - VF-IT ER clients needs custom-attributes - Start
				if (null != ppType.getCustomFields() && null != ppType.getCustomFields().getCustomField()) {
					for (CustomFieldFullType customField : ppType.getCustomFields().getCustomField()) {
						pricePointImpl_.setCustomField(customField.getName(), customField.getValue());
					}
				}
				//JIRA ET-302 - VF-IT ER clients needs custom-attributes - End
				return pricePointImpl_;
			}
			catch(final Exception e)
			{
				logger.error("Problem converting values in Pricepoint:" + e);
				return null;
			}
		}
		else
		{
			logger.error("PricepointFullType is null");
			return null;
		}
	}

	public PricePointImpl convertLittlePricePointType(PricePointLittleType ppType)
	{
		if(logger.isDebugEnabled())
			logger.debug("PricePointImpl convertLittlePricePointType(PricePointFullType ppType)");

		if(ppType != null)
		{
			try
			{
				final PricePointImpl pricePointImpl_ = new PricePointImpl();
				try
				{
					if(ppType.getUserGroups() != null)
					{
						if(ppType.getUserGroups().getUserGroup() != null)
						{
							final int size_ = ppType.getUserGroups().getUserGroup().size();
							final String[] userGroups_ = new String[size_];
							for(int i=0; i<size_; i++)
							{
								userGroups_[i] = ppType.getUserGroups().getUserGroup().get(i);          
							}
							pricePointImpl_.setUserGroups(userGroups_);
						}
					}
				}
				catch(final Exception e)
				{
					logger.error("Problem fetching User Groups:" + e);
				}

				return pricePointImpl_;
			}
			catch(final Exception e)
			{
				logger.error("Problem converting values in Pricepoint:" + e);
				return null;
			}
		}
		else
		{
			logger.error("convertLittlePricePointType is null");
			return null;
		}
	}

	//	public SuperCreditPricePoint convertSuperCreditPricePointType(SuperCreditPricePointType ppType)
	//	{
	//		if(logger.isDebugEnabled())
	//			logger.debug("SuperCreditPricePoint convertSuperCreditPricePointType(SuperCreditPricePointType ppType)");
	//
	//		try
	//		{
	//			final String promoCode    = ppType.getPromoCode();
	//			final RatedEvent ratedEvent = new RatedEvent();
	//			ratedEvent.setNetRate(ppType.getNetRate());
	//			ratedEvent.setNetStandardRate(ppType.getStandardRate());
	//			final SuperCreditPricePoint pp = new SuperCreditPricePoint();
	//			final String[] pm = new String[1];
	//			pm[0] = promoCode;
	//			pp.setPricingText1(ppType.getPricingText1());
	//			pp.setPricingText1(ppType.getPricingText2());
	//			pp.setPromoCodes(pm);
	//			pp.setPromoCodes( new String [] {ppType.getPromoCode()} );
	//			pp.setPricingText1(ppType.getPricingText1());
	//			pp.setPricingText2(ppType.getPricingText2());
	//			pp.setTranslatedPricingText((ppType.getPurchaseLinkText() != null?ppType.getPurchaseLinkText():""));
	////			pp.setRoamingGrossAmount(ppType.getRoamingGrossAmount());
	////			pp.setRoamingNetAmount(ppType.getRoamingNetAmount());
	//			return pp;
	//		}
	//		catch(final Exception e)
	//		{
	//			logger.error("Problem converting values in Pricepoint");
	//			return null;
	//		}
	//	}

	//	public DRMObject convertDRMObjectType(DrmObjectType drmObjectType)
	//	{
	//		if(logger.isDebugEnabled())
	//			logger.debug("DRMObject convertDRMObjectType(DrmObjectType drmObjectType)");
	//
	//		try
	//		{
	//			final DrmTypeType drmTypeType_ = drmObjectType.getDrmType();
	//			DRMType drmType_ = DRMType.NOT_DRM;
	//			if(drmTypeType_.getName() != null && !drmTypeType_.getName().equalsIgnoreCase(""))
	//			{
	//				drmType_ = DRMType.convertStringToDRMType(drmTypeType_.getName());
	//			}
	//			//CR1455 - start
	//			String description_ = null;
	//			if(drmObjectType.getDescription() != null && drmObjectType.getDescription().length() > 0)
	//				description_ = drmObjectType.getDescription();
	//			return new DRMObject(drmObjectType.getId(), description_, drmType_);  
	//			//CR1455 - end
	//		}
	//		catch(final Exception e)
	//		{
	//			logger.error("Problem converting values in DRMObject");
	//			return null;
	//		}
	//	}
	//
	//	public DRMObject convertDRMObjectFullType(DrmObjectFullType drmObjectType)
	//	{
	//		if(logger.isDebugEnabled())
	//			logger.debug("DRMObject convertDRMObjectFullType(DrmObjectFullType drmObjectType)");
	//
	//		try
	//		{
	//			final DrmTypeFullType drmTypeType_ = drmObjectType.getDrmType();
	//			DRMType drmType_ = DRMType.NOT_DRM;
	//			if(drmTypeType_.getName() != null && !drmTypeType_.getName().equalsIgnoreCase(""))
	//			{
	//				drmType_ = DRMType.convertStringToDRMType(drmTypeType_.getName());
	//			}
	//			//CR1455 - start
	//			String description_ = null;
	//			if(drmObjectType.getDescription() != null && drmObjectType.getDescription().length() > 0)
	//				description_ = drmObjectType.getDescription();
	//			return new DRMObject(drmObjectType.getId(), description_, drmType_);  
	//			//CR1455 - end
	//		}
	//		catch(final Exception e)
	//		{
	//			logger.error("Problem converting values in DRMObject");
	//			return null;
	//		}
	//	}

	//	public SuperCreditPricePoint convertFullSuperCreditPricePointType(SuperCreditPricePointFullType ppType)
	//	{
	//		if(logger.isDebugEnabled())
	//			logger.debug("SuperCreditPricePoint convertFullSuperCreditPricePointType(SuperCreditPricePointFullType ppType)");
	//
	//		try
	//		{
	//			final RatedEvent ratedEvent = new RatedEvent();
	//			ratedEvent.setNetRate(ppType.getNetRate());
	//			ratedEvent.setNetStandardRate(ppType.getStandardRate());
	//			final SuperCreditPricePointImpl pp = new SuperCreditPricePointImpl();
	//			//CR1455 - start
	//			pp.setId((ppType.getId() != null?ppType.getId():null));
	//			pp.setNetRate(ppType.getNetRate());
	//			pp.setTaxRate(ppType.getTaxRate());
	////			pp.setDRMObject((ppType.getDrmObject() != null?convertDRMObjectFullType(ppType.getDrmObject()):null));
	//			pp.setActive(ppType.isIsActive());
	//			pp.setBearer((ppType.getBearerId() != null?ppType.getBearerId():null));
	//			pp.setIsHistoric(ppType.isIsHistoric());
	//			//CR1455 - end
	//			pp.setPricingText1(ppType.getPricingText1());
	//			pp.setPricingText1(ppType.getPricingText2());
	//			pp.setPromoCodes( new String [] {ppType.getPromoCode()} );
	//			pp.setPricingText1(ppType.getPricingText1());
	//			pp.setPricingText2(ppType.getPricingText2());
	//			pp.setTranslatedPricingText((ppType.getPurchaseLinkText() != null?ppType.getPurchaseLinkText():""));
	////			pp.setRoamingGrossAmount(ppType.getRoamingGrossAmount());
	////			pp.setRoamingNetAmount(ppType.getRoamingNetAmount());
	//			return pp;
	//		}
	//		catch(final Exception e)
	//		{
	//			logger.error("Problem converting values in SuperCreditPricePoint");
	//			return null;
	//		}
	//	}

	public Transaction convertTransaction(TransactionFullType transactionType_)
	{
		if(logger.isDebugEnabled())
			logger.debug("Transaction convertTransaction(SelfcareFullTransactionType transactionType_)");

		try
		{
			final Transaction transaction_ = new Transaction();

			transaction_.setAuthCode((transactionType_.getAuthCode() != null?transactionType_.getAuthCode():""));
			transaction_.setCsrId((transactionType_.getCsrId() != null?transactionType_.getCsrId():""));
			transaction_.setDescription((transactionType_.getDescription() != null?transactionType_.getDescription():""));
			transaction_.setStandardRate(transactionType_.getStandardRate());

			transaction_.setTransactionIdLong(Long.parseLong(transactionType_.getId().trim()));

			transaction_.setPurchaseDate((transactionType_.getPurchaseDate() != null?transactionType_.getPurchaseDate().getTime():null));
			transaction_.setPurchaseRate(transactionType_.getPurchaseRate());
			//CR1455 - start
			transaction_.setTaxRate(transactionType_.getTaxRate());
			//CR1455 - end

			try	{
				if (transactionType_.getSubscription() != null)	
					transaction_.setSubscription(convertSubscriptionType(transactionType_.getSubscription()));
			}	catch (final Exception e)	{
				logger.warn("Problem converting Subscription {}", e.getMessage());
			}

			try	{
				transaction_.setPurchaseCurrency((transactionType_.getPurchaseCurrency() != null?convertChargingResourceFullType(transactionType_.getPurchaseCurrency()):null));
			}	catch(final Exception e)	{
				logger.warn ("Problem converting ChargingResourceType {}: {}", transactionType_.getPurchaseCurrency(), e.getMessage());
			}
			//			transaction_.setSuperCreditId((transactionType_.getSuperCreditId() != null?transactionType_.getSuperCreditId():""));
			//transaction_.setLocalPurchaseDate((transactionType_.getLocalDateTime() != null?transactionType_.getLocalDateTime().getTime():null));
			//CR1564 : Change the type to Calendar
			//transaction_.setLocalPurchaseDate((transactionType_.getLocalDateTime() != null?transactionType_.getLocalDateTime():null));
			transaction_.setLocalPurchaseDate((transactionType_.getLocalDateTime() != null?transactionType_.getLocalDateTime().getTime():null));

			transaction_.setPaymentType(transactionType_.getPaymentType());
			transaction_.setPaymentStatus(transactionType_.getPaymentStatus());
			transaction_.setStatus(transactionType_.getStatus());

			transaction_.setSessionId(transactionType_.getSessionId());
			//CR1455 - start
			if(isNotBlank(transactionType_.getParentTransactionId()))
				transaction_.setParentTransactionIdLong(Long.parseLong(transactionType_.getParentTransactionId().trim()));
			//			else
			//				transaction_.setParentTransactionId(null);

			transaction_.setServiceId(transactionType_.getServiceId());

			if(transactionType_.getRecipientMsisdn() != null && transactionType_.getRecipientMsisdn().trim().length()>0)
				transaction_.setReceipientMsisdn(transactionType_.getRecipientMsisdn());
			else
				transaction_.setReceipientMsisdn(null);

			transaction_.setContentDescription(transactionType_.getContentDescription());
			transaction_.setAggregatorId(transactionType_.getAggregatorId());
			transaction_.setPartnerId(transactionType_.getPartnerId());
			transaction_.setContentName(transactionType_.getContentName());
			transaction_.setAssetId(transactionType_.getAssetId());
			transaction_.setBearer(transactionType_.getBearerId());
			if(transactionType_.getMatchingAttributes() != null)
			{
				transaction_.setMatchingAttributes(convertRatingAttributes(transactionType_.getMatchingAttributes()));
			}
			transaction_.setReIssue(transactionType_.getReIssue());
			transaction_.setPurchaseChannel(transactionType_.getPurchaseChannel());
			transaction_.setReason(transactionType_.getRefundReason());
			transaction_.setRefundPaymentTransactionId(transactionType_.getRefundPaymentTransactionId());

			//MQC 6627 - set next cycle amount
			transaction_.setNextCycleDiscount(transactionType_.getNextCycleDiscount());

			//MQC 6650 - set transaction type
			transaction_.setType(new TransactionType(transactionType_.getTransactionType()));
			
			transaction_.setClientId(transactionType_.getClientId());
			transaction_.setDeviceId(transactionType_.getDeviceId());
			//transaction_.setTransactionIdLong(transactionType_.getTransactionIdLong());
			transaction_.setAccessChannel(transactionType_.getAccessChannel());
			//			transaction_.setClientNetworkCode(transactionType_.getClientNetworkCode());
			transaction_.setExternalTransId(transactionType_.getExternalTransId());
			transaction_.setExternalField1(transactionType_.getExternalField1());
			transaction_.setExternalField2(transactionType_.getExternalField2());
			transaction_.setAffiliateID(transactionType_.getAffiliateId());
			transaction_.setProductCode(transactionType_.getProductCode());
			transaction_.setRateIdentifier(transactionType_.getRateIdentifier());
			transaction_.setPaymentInfo(transactionType_.getPaymentInfo());

			//MQC9795 - add error description and errorId
			transaction_.setPaymentErrorDescription(transactionType_.getErrorDescription());
			transaction_.setPaymentErrorId(transactionType_.getErrorId());

			//JIRAET81 - set AssetId
			transaction_.setAssetId(transactionType_.getAssetId());

			//JIRAET183 - set the modification transaction id
			if (transaction_.getType() != null && transaction_.getType().isModification()) {
				transaction_.setModifyTransactionId(transactionType_.getId().trim());
			}
			
			return transaction_;
		}	catch(final Exception e)	{
			logger.error("Problem converting values in Transaction:" + e);
			return null;
		}
	}

	public Transaction[] convertTransactionArray(SelfcareFullTransactions transType)
	{

		if(logger.isDebugEnabled())
			logger.debug("Transaction[] convertTransactionArray(SelfcareTransactionsTypeImpl transType)");

		final List<Transaction> listToReturn = new ArrayList<Transaction>();
		//		final List<SelfcareFullTransaction> trans = transType.getTransaction();
		//		final ListIterator<SelfcareFullTransaction> it = trans.listIterator();
		//		for(;it.hasNext();)
		//		{
		//			final Object o = it.next();
		//			final TransactionFullType tempType = (TransactionFullType)o;
		for(TransactionFullType tempType : transType.getTransaction())	{
			listToReturn.add(convertTransaction(tempType));
		}
		return listToReturn.toArray(new Transaction[listToReturn.size()]);
	}


	public BaseAuthorization convertNextPaymentAmountType(NextPaymentAmountType payload)
	{
		if(logger.isDebugEnabled())
			logger.debug("BaseAuthorization convertNextPaymentAmountType(NextPaymentAmountType payload)");

		BaseAuthorization bauth = null;
		final CatalogPackage pack = new CatalogPackage();
		final PricePointImpl pp = new PricePointImpl();
		if (payload != null )	{
			bauth = new BaseAuthorization();
			bauth.setIsSuccess(true);
			bauth.setNetRate(payload.getNetRate());
			bauth.setTaxAmount(payload.getTaxAmount());
			//pp.setRateWithoutTax(payload.getNetRate());
			pp.setRateWithoutTax(payload.getRate());
			pack.setPricePoint(pp);//TODO Check this.
			bauth.setPackage(pack);
			bauth.setPricePoint(pp);
		}
		else 
		{
			return null;
		}

		return bauth;
	}

	public RatingAttributes convertRatingAttributes(RatingAttributesFullType ratingAttributesFullType)
	{
		if(logger.isDebugEnabled())
			logger.debug("RatingAttributes convertRatingAttributes(RatingAttributesFullType rAtts)");

		final RatingAttributes ratingAtts = new RatingAttributes();

		if(ratingAttributesFullType.getRatingAttributesType() != null)
		{
			final com.vodafone.global.er.decoupling.binding.response.RatingAttributesType rAtts = ratingAttributesFullType.getRatingAttributesType();



			ratingAtts.setAccessChannel(String.valueOf(rAtts.getAccessChannel()));
			ratingAtts.setAssetID((rAtts.getAssetId() != null?rAtts.getAssetId():null));
			ratingAtts.setBearer((rAtts.getBearer() != null?rAtts.getBearer():"*"));
			ratingAtts.setContentName((rAtts.getContentName() != null?rAtts.getContentName():null));
			ratingAtts.setDeviceType(rAtts.getDeviceType());
			ratingAtts.setDeviceID(rAtts.getDeviceId());
			ratingAtts.setEventDateTime(rAtts.getEventDateTime());


			//Sets value to -1 if null
			ratingAtts.setDuration((rAtts.getDuration() != null?rAtts.getDuration().getDurationCode():-1));
			//??
			//rAtts.isIsInteractive()
			ratingAtts.setPaymentType(rAtts.getPaymentType());
			ratingAtts.setPromoCodes((rAtts.getPromoCode() != null?new String[]{rAtts.getPromoCode()}:new String[]{""}));
			ratingAtts.setPaymentInformation(rAtts.getPaymentInformation());

			//			if(rAtts.getNetworkCodeString() != null)
			//			{
			//				final NetworkCode networkCode_ = new NetworkCode();
			//
			//				if(rAtts.getNetworkCodeString().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DOMESTIC]))
			//					networkCode_.setRoamingType(ErCoreConst.ROAMING_DOMESTIC);
			//				else if(rAtts.getNetworkCodeString().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID]))
			//					networkCode_.setRoamingType(ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID);
			//				else if(rAtts.getNetworkCodeString().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_OFF_FOOTPRINT]))
			//					networkCode_.setRoamingType(ErCoreConst.ROAMING_OFF_FOOTPRINT);
			//				else if(rAtts.getNetworkCodeString().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_ON_FOOTPRINT]))
			//					networkCode_.setRoamingType(ErCoreConst.ROAMING_ON_FOOTPRINT);
			//
			//				ratingAtts.setNetworkCode(networkCode_);
			//			}

			ratingAtts.setChargingMethod(ratingAttributesFullType.getChargingMethod());

			ratingAtts.setPaymentInformation((rAtts.getPaymentInformation() != null?rAtts.getPaymentInformation():null));
			ratingAtts.setPaymentType(rAtts.getPaymentType());
			ratingAtts.setPreRate(rAtts.getPreRate());
			ratingAtts.setPurchaseChannel((rAtts.getPurchaseChannel() != null?rAtts.getPurchaseChannel():null));
			ratingAtts.setTaxRate(rAtts.getTaxRate());
			ratingAtts.setTariff(rAtts.getTariff());


		}
		else
		{
			logger.error("ratingAttributesFullType.getRatingAttributesType() is null");
			return null;
		}

		if(ratingAttributesFullType.getUserGroups() != null)
		{
			if(ratingAttributesFullType.getUserGroups().getUserGroup() != null)
			{
				final int size_ = ratingAttributesFullType.getUserGroups().getUserGroup().size();
				final String[] userGroups_ = new String[size_];
				for(int i=0; i<size_; i++)
				{
					userGroups_[i] = ratingAttributesFullType.getUserGroups().getUserGroup().get(i);          
				}
				ratingAtts.setUserGroups(userGroups_);
			}
		}

		return ratingAtts;

	}

	public Duration convertDuration(DurationType durationType) 
	{
		if(logger.isDebugEnabled())
			logger.debug("Duration convertDuration(DurationType durationType) throws EcommerceException");

		return new Duration(durationType.getDurationCode());
	}

	public PurchaseAuthorization convertRenewPurchaseAuthObj(RenewPurchaseAuthorisation auth)
	{
		if(logger.isDebugEnabled())
			logger.debug("PurchaseAuthorization convertRenewPurchaseAuthObj(RenewPurchaseAuthorisationType auth)");

		final PurchaseAuthorization rv = new PurchaseAuthorization();
		//		rv.setRate(auth.getRate());
		rv.setPackageId((auth.getPackageSubscriptionId() != null?auth.getPackageSubscriptionId():null)); 
		if(auth.getReasonCode() != null)
		{
			rv.setReasonCode(convertReasonCode(auth.getReasonCode()));
		}
		if(auth.getSubReasonCode() != null)
		{
			rv.setSubReasonCode(convertReasonCode(auth.getSubReasonCode()));
		}
		rv.setTransactionId((auth.getTransactionId() != null?auth.getTransactionId():null));
		try
		{
			final List<ResourceBalanceType> userResourceBalancesList_ = auth.getUserResourceBalance();
			ResourceBalance resourceBalance_ = null;
			int i=0;
			if(userResourceBalancesList_ != null)
			{
				resourceBalance_ = new ResourceBalance();
				for (final ResourceBalanceType resourceBalanceType_ : userResourceBalancesList_)
				{          
					this.convertResourceBalance(resourceBalanceType_);
					i++;
				}
			}
			if(i>1)
			{
				logger.info("More than one ResourceBalance in list, PurchaseAuthorization can only handle one, last one will be used");
			}
			rv.setUserResourceBalance(resourceBalance_);
		}
		catch(final Exception e)
		{
			logger.error("Problem setting ResourceBalanceType in convertRenewPurchaseAuthObj " + e);
		}
		rv.setIsSuccess(auth.isIsSuccess());
		return rv;
	}

	public PurchaseAuthorization convertPurchaseAuthObj(UsageAuthorisation auth)
	{
		if(logger.isDebugEnabled())
			logger.debug("PurchaseAuthorization convertPurchaseAuthObj(UsageAuthorisationType auth)");

		final PurchaseAuthorization rv = new PurchaseAuthorization();
		rv.setIsSuccess(auth.isIsSuccess());
		rv.setPackageSubscriptionId(auth.getPackageSubscriptionId());
		rv.setTransactionId(auth.getTransactionId());
		if (auth.getTransactionId() != null 
				&& !auth.getTransactionId().equals("")) {
			rv.setTransactionIdLong(new Long(auth.getTransactionId()).longValue());
		}
		rv.setCharged(auth.isIsSuccess());
		rv.setNetRate(auth.getRate());
		//		rv.setRate(auth.getRate());
		//		rv.setNetworkCode(new NetworkCode());
		final ReasonCodeType codeType_ = auth.getReasonCode();
		if(codeType_ != null)
		{
			rv.setReasonCode(convertReasonCode(codeType_)); 
		}
		rv.setPackageSubscriptionId(auth.getPackageSubscriptionId());
		//CR2241start
		rv.setReserved(auth.isIsReservedOnly());
		//TODO: set pricepoint - how? only pptype contained in auth, "max no occurrences" should be available from pricepoint
		//		auth.getPricePoint().geto
		rv.setPricePoint(convertPricePointType(auth.getPricePoint()));
		//CR2241end
		//MQC9848 - set the errorid and error description
		rv.setErrorDescription(auth.getErrorDescription());
		rv.setErrorId(auth.getErrorId());
		//JIRAET93 - also covert the package
		rv.setPackage(convertPackageType(auth.getPackage()));
		return rv;
	}

	public ReasonCode convertReasonCode(ReasonCodeType rct) 
	{
		logger.debug("converting reason code {} ({})", rct.getCode(), rct.getName());

		ReasonCode rv = null;

		if (StringUtils.isNotBlank(rct.getName())) {	//should always be true
			rv = ReasonCode.getReasonCode(rct.getCode(), rct.getSubCode(), rct.getName());
		}
		else {
			logger.warn("no name for reason code {}", rct.getCode());
			rv = ReasonCode.getReasonCode(rct.getCode(), rct.getSubCode());
		}

		return rv;

	}

	public UpdateServiceStatusAuthorization convertUpdateServiceStatusAuthorisationFullType(UpdateServiceStatusAuthorisationFullType ussaft) 
	{
		logger.debug(" ussaft {}", ussaft);

		try
		{
			UpdateServiceStatusAuthorization ussa_ = null;
			if(ussaft != null)
			{
				ussa_ = new UpdateServiceStatusAuthorization(convertReasonCode(ussaft.getReasonCode()),
						"",
						"", 
						(ussaft.getErrorDescription() != null?ussaft.getErrorDescription():""), 
						ussaft.isIsSuccess());
			}
			return ussa_;
		}
		catch(final Exception e)
		{
			logger.error("Problem setting UpdateServiceStatusAuthorisation");
			return null;
		}
	}

	public PricingModel buildPricingModel(PricingModelFullType pricingModelFullType)
	{
		if(pricingModelFullType != null)
		{
			final PricingModel pricingModel_ = new PricingModel(pricingModelFullType.getId());
			final Tier[] tiers_ = buildTiers(pricingModelFullType.getTiers());
			for (final Tier element : tiers_) {
				pricingModel_.addTier(element);
			}
			return pricingModel_;
		}
		else
			return null;
	}

	public Tier[] buildTiers(Tiers tiersType)
	{
		if(tiersType != null)
		{
			final List<DateTimeTierFullType> dateTimeTiersFullType_ = tiersType.getDateTimeTier();
			final DateTimeTier[] tiers_ = new DateTimeTier[dateTimeTiersFullType_.size()];
			int i = 0;
			for (final DateTimeTierFullType dateTimeTierFullType_ : dateTimeTiersFullType_)
			{
				if(dateTimeTierFullType_ != null)
				{
					final DateTimeTier dateTimeTier_ = new DateTimeTier(dateTimeTierFullType_.getId());
					dateTimeTier_.setMonthsOfYear(buildRangeValue(dateTimeTierFullType_.getMonthsOfYear()));
					dateTimeTier_.setDaysOfMonth(buildRangeValue(dateTimeTierFullType_.getDaysOfMonth()));
					dateTimeTier_.setDaysOfWeek(buildRangeValue(dateTimeTierFullType_.getDaysOfWeek()));
					dateTimeTier_.setHoursOfDay(buildDayRangeValue(dateTimeTierFullType_.getHoursOfDay()));
					dateTimeTier_.setPromotion(dateTimeTierFullType_.isIsPromotion());
					tiers_[i++] = dateTimeTier_;
				}
			}
			return tiers_;
		}
		return null;
	}

	public RangeValue buildRangeValue(RangeValueFullType rangeValueType)
	{
		try
		{
			if(rangeValueType != null)
			{
				final RangeValue rangeValue_ = new RangeValue();
				rangeValue_.setValue((rangeValueType.getValue() != null?rangeValueType.getValue():null));
				return rangeValue_;
			}
			else
			{
				logger.info("RangeValueFullType is null");
				return null;
			}
		}
		catch(final Exception e)
		{
			logger.error("Problem converting RangeValueFullType:" + e);
			return null;
		}
	}

	public DayRange buildDayRangeValue(DayRangeFullType dayRangeType)
	{
		try
		{
			if(dayRangeType != null)
			{
				final DayRange dayRange_ = new DayRange();
				dayRange_.setStartTime((dayRangeType.getStartTime()!= null?dayRangeType.getStartTime():null));
				dayRange_.setEndTime((dayRangeType.getEndTime()!= null?dayRangeType.getEndTime():null));
				return dayRange_;
			}
			else
			{
				logger.info("DayRangeFullType is null");
				return null;
			}
		}
		catch(final Exception e)
		{
			logger.error("Problem converting DayRangeFullType:" + e);
			return null;
		}
	}

	public BalanceImpact convertFullBalanceImpact(BalanceImpactFullType balanceImpactType)
	{
		try
		{
			if(balanceImpactType != null)
			{
				final BalanceImpact balanceImpact_ = new BalanceImpact(null, 0, 0);
				balanceImpact_.setKey(balanceImpactType.getKey());
				return balanceImpact_;
			}
			else
				return null;
		}
		catch(final Exception e)
		{
			logger.error("Problem converting BalanceImpactFullType:" + e);
			return null;
		}
	}


	public PromotionsResult buildPromotionsObject(CheckPromotionsResponse promoType){
		final CatalogPackage []pack = new CatalogPackage[1];//this array should be returned as part of decoupling

		final PromotionsResult promo = new PromotionsResult(promoType.isHasPromotions(),pack);

		return promo;
	}


	/**if purchase options payload, this method should build an auth response out of purchase options<br/>
	 * NB this is not possible with the current architecture*/
	public UsageAuthorization buildUsageAuthObj(PurchaseOptions options, boolean isSuccess){
		final UsageAuthorization auth = new UsageAuthorization();

		auth.setIsSuccess(isSuccess);
		auth.setAuthorized(isSuccess);
		auth.setReasonCode(convertReasonCode(options.getReasonCode()));
		auth.setSubReasonCode(convertReasonCode(options.getSubReasonCode()));
		
		//Convert the options themselves
		if(options.getPackages() != null) {
		    List<PackageType> packageTypeList = options.getPackages().getPackage();
    		
    		for (PackageType packType : packageTypeList) {
                CatalogPackage pack = convertPackageType(packType);
                auth.setPackage( pack );
            }
    		
		}
		
		
		return auth;
	}

	public UsageAuthorization buildUsageAuthObj(UsageAuthorisation authType){
		final UsageAuthorization auth = new UsageAuthorization();
		auth.setIsSuccess(authType.isIsSuccess());
		auth.setAuthorized(authType.isIsSuccess());
		auth.setTransactionId(authType.getTransactionId());
		auth.setNetRate(authType.getRate());
		//CR2241start
		auth.setReserved(authType.isIsReservedOnly());
		//CR2241end
		if (isNotBlank(authType.getPackageSubscriptionId())){
			Subscription sub = new Subscription();
			sub.setSubscriptionId(authType.getPackageSubscriptionId());
			auth.setSubscription(sub);
			//JIRAET122 - set the subscripiton id in the auth object
			auth.setSubscriptionIds(new String[] { authType.getPackageSubscriptionId() } );
			auth.setPackageSubscriptionId(authType.getPackageSubscriptionId());
		}
		if (authType.getUserResourceBalance() != null && authType.getUserResourceBalance().getSubscriptionId() != null
				&& ! "".equals(authType.getUserResourceBalance().getSubscriptionId())){
			auth.setUserResourceBalance(convertResourceBalance(authType.getUserResourceBalance()));
		}

		//Added for MQC 9972
		if(authType.getUserResourceBalance() != null && authType.getUserResourceBalance().getChargingResource() != null) {
			final ChargingResource chargingRes = convertChargingResourceType( authType.getUserResourceBalance().getChargingResource() );
			auth.setResource(chargingRes);
		}

		if (authType.getPricePoint() != null){
			PricePoint p = convertPricePointType(authType.getPricePoint());

			//TODO: check if this is acceptable or whether must be set according to pricepoint isreserveonly flag instead
			//(which would require entire call to be refactored to return full pricepoint type instead

			if (authType.isIsReservedOnly()){
				p.setIsReserveOnly(true);
			}
			auth.setPricePoint(p);
		}
		//auth.setEventReservationId("");

		auth.setPackage(convertPackageType(authType.getPackage()));

		//CR1935
		auth.setReIssue(authType.getReIssue());

		if (authType.getReasonCode() != null) {
			auth.setReasonCode(convertReasonCode(authType.getReasonCode()));
		}

		if (authType.getSubReasonCode() != null) {
			auth.setSubReasonCode(convertReasonCode(authType.getSubReasonCode()));
		}

		auth.setReceiptingCreditBalanceImpact(authType.getReceiptingCreditBalanceImpact());
		auth.setReceiptingUsageTypeAttribute(authType.getReceiptingUsageTypeAttribute());

		if (isNotBlank(authType.getErrorDescription()))
			auth.setErrorDescription(authType.getErrorDescription());
		auth.setErrorId(authType.getErrorId());
		try	{
			auth.setPaymentStatus(ResponseStatus.get(authType.getPaymentStatus().getCode(), authType.getPaymentStatus().getId()));
		} catch (Exception e)	{
			logger.warn("couldn't set the payment status: {}", e.getMessage());
		}

		return auth;
	}

	public GoodwillCreditAuthorization buildGoodwillCreditAuthorization(
			GoodwillCreditResponse payload) {

		final GoodwillCreditAuthorization auth = new GoodwillCreditAuthorization();
		auth.setIsSuccess(payload.isIsSuccess());
		auth.setTransactionId(payload.getTransactionId());
		//auth.setMsisdn(payload.getMsisdn());
		auth.setPackageSubscriptionId(payload.getSubscriptionId());
		//can't do this - auth.setReasonCode(new ReasonCode(payload.getReasonCode().getCode()));
		//auth.getReasonCode().setName(payload.getReasonCode().getName());
		auth.setReasonCode(convertReasonCode(payload.getReasonCode()));

		//CR - Add ErrorId and Error description to response
		auth.setErrorId(payload.getErrorId());
		auth.setErrorDescription(payload.getErrorDescription());
		return auth;

	}

	public PurchaseAuthorization buildRenewPurchaseAuthObj(RenewPurchaseAuthorisation auth){
		final PurchaseAuthorization rv = new PurchaseAuthorization();
		rv.setIsSuccess(auth.isIsSuccess());
		rv.setTransactionId(auth.getTransactionId());
		if (auth.getTransactionId()!= null && auth.getTransactionId().length()>0)
			rv.setTransactionIdLong(new Long(auth.getTransactionId()));

		rv.setErrorDescription(auth.getErrorDescription());
		rv.setPackageSubscriptionId(auth.getPackageSubscriptionId());
		rv.setNetRate(auth.getRate());
		rv.setReasonCode(convertReasonCode(auth.getReasonCode()));
		//rv.setUserResourceBalance(convertResourceBalance((ResourceBalanceTypeImpl) auth.getUserResourceBalance().get(0)));
		//CR2237 - also set the error-id
		if (auth.getErrorId() != null)
			rv.setErrorId(auth.getErrorId());
		return rv;
	}


	public PurchaseAttributesType buildPurchAttrs(PurchaseAttributes purchaseAttributes){
		PurchaseAttributesType purAttrType = null;

		//Purchase Attributes
		if (purchaseAttributes != null) {
			purAttrType = new PurchaseAttributesType();

			if (purchaseAttributes.getReserveOnlyFlag()>0)
				purAttrType.setForceReservation(purchaseAttributes.getReserveOnlyFlag());
			if (purchaseAttributes.isRepurchase())
				purAttrType.setIsRepurchase(true);	//else we don't want it in the xml, even if it's set to false
			if (StringUtils.isNotBlank(purchaseAttributes.getExternalSubId()))
				purAttrType.setExternalSubId(purchaseAttributes.getExternalSubId());

			Calendar cal = null;
			if(purchaseAttributes.getStartDate() != null) {
				cal = Calendar.getInstance();
				cal.setTime(purchaseAttributes.getStartDate());
				purAttrType.setStartDate(cal);
			}
			if(purchaseAttributes.getEndDate() != null) {
				cal = Calendar.getInstance();
				cal.setTime(purchaseAttributes.getEndDate());
				purAttrType.setEndDate(cal);
			}
			if (purchaseAttributes.getStatus()!=Constants.INT_NOT_SET)
				purAttrType.setStatus(purchaseAttributes.getStatus());

			if(purchaseAttributes.getResourceBalancesOnly() != null && purchaseAttributes.getResourceBalancesOnly().length > 0) {
				final ResourceBalanceOnly[] resourceBalanceOnly = purchaseAttributes.getResourceBalancesOnly();
				final ResourceBalancesOnly resourceBalancesOnlyType = new ResourceBalancesOnly(); 
				for (final ResourceBalanceOnly element : resourceBalanceOnly) {
					final com.vodafone.global.er.decoupling.binding.request.ResourceBalanceOnlyType resourceBalanceOnlyTypeImpl = new com.vodafone.global.er.decoupling.binding.request.ResourceBalanceOnlyType();
					resourceBalanceOnlyTypeImpl.setResourceId(element.getResourceId());
					resourceBalanceOnlyTypeImpl.setBalance(element.getBalance());
					resourceBalancesOnlyType.getResourceBalanceOnly().add(resourceBalanceOnlyTypeImpl);
				}
				purAttrType.setResourceBalancesOnly(resourceBalancesOnlyType);
			}		

		}
		return purAttrType;
	}


	public SubscriptionAttributesType buildSubscriptionAttributes(
			SubscriptionAttributes subscriptionAttributes) {
		SubscriptionAttributesType rv = new SubscriptionAttributesType();

		if (subscriptionAttributes.getEndDate()!=null)
			rv.setEndDate(DateTimeUtil.convertDateToCal(subscriptionAttributes.getEndDate()));

		int status = subscriptionAttributes.getStatus();
		if (status!=Constants.INT_NOT_SET)
			rv.setStatus(status);
		return rv;
	}


	public ExpressDisplayAttributes convertExpressDisplayAttributes(
			ExpressDisplayAttribute expressAttribute, String[] serviceIds) {
		ExpressDisplayAttributes rv = new ExpressPackageRequest.ExpressDisplayAttributes();
		rv.setIsHeadline(expressAttribute.isHeadline());
		rv.setIsOption(expressAttribute.isOption());
		ExpressDisplayAttributes.TrueOptions options = new ExpressPackageRequest.ExpressDisplayAttributes.TrueOptions();
		for (String service: serviceIds)	{
			options.getServiceId().add(service);
		}
		rv.setTrueOptions(options);
		return rv;
	}


	public ServiceIds convertServices(String[] serviceId) {
		ServiceIds  rv = new  ExpressPackageRequest.ServiceIds();
		rv.getServiceId().addAll(Arrays.asList(serviceId));
		return rv;
	}


	public ExpressData convertExpressData(ExpressPackage express) {
		ExpressData rv = new ExpressData();
		rv.setExpressPrice(express.getExpressPrice());
		rv.setOption(express.isIsOption());
		rv.setPriceText(express.getPriceText());
		rv.setServiceId(express.getServiceId());
		rv.setSubscribed(express.isIsSubscribed());
		return rv;
	}


	public AccountValidationAuthorization buildAccountValidation(ValidateMsisdnResponse validate) {
		AccountValidationAuthorization rv = new AccountValidationAuthorization();
		rv.setSpId(validate.getSpid());
		rv.setErrorDescription(validate.getErrorDescription());
		rv.setErrorId(validate.getErrorId());
		//ET 176 - add fields as per in sync with non decoupling flow
		if (validate.isIsChargeable())
			rv.setStatus(ResponseStatus.ACCEPTED.getName());
		else
			rv.setStatus(validate.getStatus());
		rv.setBan(validate.getBan());
		rv.setBillingCycleDay(validate.getBillingCycleDay());
		rv.setChildSpId(validate.getChildSpId());
		rv.setSpType(validate.getSpType());
		if (validate.getUserGroups()!=null && !validate.getUserGroups().getUserGroup().isEmpty())
			rv.setUserGroups(validate.getUserGroups().getUserGroup().toArray(new String[validate.getUserGroups().getUserGroup().size()]));
		// ET 219: set the PREPAY option from response
		rv.setIsPrepay(validate.getIsPrepay());
		return rv;
	}



	/**
	 * TODO fix decoupling so the tariff is in the fullPricePointType in the response!
	 * @param pptId
	 * @return the tariff if we find it ok, * otherwise
	 */
	public String getTariffFromPricePointId(String pptId)	{
		//eg from package:pTariffUpsell_TAX_2_2_999_999_999_*_*_*_false_false_B
		//get the B
		String rv = "*";

		try	{
			int numUnderscores = StringUtils.countMatches(pptId, "_");
			int startOfTariff = StringUtils.ordinalIndexOf(pptId, "_", numUnderscores);
			rv = pptId.substring(startOfTariff+1, pptId.length());
			if (rv.indexOf("true")>-1 ||rv.indexOf("false")>-1)	//in case there's no tariff in the pptId
				rv= "*";
		} catch (Exception e)	{
			logger.warn("problem calculating channel for pricepoint {}", pptId, e);
		}
		logger.debug("got tariff {} from pptid {}", rv, pptId);
		return rv;
	}

	/**
	 * create a xml rating attributes request object based on usage attributes.  Use for UARC calls etc
	 * @param usageAttributes
	 * @return com.vodafone.global.er.decoupling.binding.request.RatingAttributesType
	 */
	public RatingAttributesType buildRatingAttributes(UsageAttributes usageAttributes) {
		RatingAttributesType ratingAtt = new RatingAttributesType();
		ratingAtt.setPaymentInformation(usageAttributes.getPaymentInformation());

		if (!usageAttributes.isInteractive())	//defaults to true
			ratingAtt.setIsInteractive(usageAttributes.isInteractive());

		ratingAtt.setAssetId(usageAttributes.getAssetID());
		ratingAtt.setExternalTransId(usageAttributes.getExternalTransId());
		ratingAtt.setPartnerId(usageAttributes.getPartnerId());
		if (usageAttributes.isExpressFlag())
			ratingAtt.setExpressPurchaseFlag(usageAttributes.isExpressFlag());

		//CR 2145
		ratingAtt.setPreRate(usageAttributes.getPreRate());
		if (usageAttributes.isPreRatePriceGross())
			ratingAtt.setPreRatePriceIsGross(usageAttributes.isPreRatePriceGross());

		if (usageAttributes.getTaxRate() > -1){
			ratingAtt.setTaxRate(usageAttributes.getTaxRate());
			ratingAtt.setHasTaxRate(true);
		}
		ratingAtt.setAccessChannel(usageAttributes.getAccessChannel());
		ratingAtt.setPurchaseChannel(usageAttributes.getPurchaseChannel());
		ratingAtt.setAffiliateId(usageAttributes.getAffiliateID());
		ratingAtt.setDeviceId(usageAttributes.getDeviceID());
		ratingAtt.setCsrId(usageAttributes.getCsrId());
		ratingAtt.setPreRate(usageAttributes.getPreRate());
		ratingAtt.setContentName(usageAttributes.getContentName());
		// 		ratingAtt.setNetworkCodeString(usageAttributes.getNetworkCodeStr());
		ratingAtt.setExternalField1(usageAttributes.getExternalField1());
		ratingAtt.setExternalField2(usageAttributes.getExternalField2());
		ratingAtt.setAggregatorId(usageAttributes.getAggregatorId());
		ratingAtt.setRecipientMsisdn(usageAttributes.getReceipientMsisdn());
		ratingAtt.setProductCode(usageAttributes.getProductCode());
		ratingAtt.setVendorId(usageAttributes.getVendorId());
		ratingAtt.setPartnerId(usageAttributes.getPartnerId());

		if (usageAttributes.getDeviceType()!=Constants.INT_MATCH_ALL)	//the default value
			ratingAtt.setDeviceType(usageAttributes.getDeviceType());

		if (!Constants.STRING_MATCH_ALL.equals(usageAttributes.getBearer()))
			ratingAtt.setBearer(usageAttributes.getBearer());
		if (usageAttributes.getRenewalPreRate()>-1)
			ratingAtt.setRenewalPreRate(usageAttributes.getRenewalPreRate());
		//CR2255 Phase2 - add partner information
		ratingAtt.setPartnerUrl(usageAttributes.getPartnerUrl());
		ratingAtt.setPartnerContactInfo(usageAttributes.getPartnerContactInfo());
		ratingAtt.setPartnerEmail(usageAttributes.getPartnerEmail());
		//ET 68 : Addition of partner-name field in ER starts here
		ratingAtt.setPartnerName(usageAttributes.getPartnerName());
		//ET 68 : Addition of partner-name field in ER ends here
		if (isNotBlank(usageAttributes.getMerchantName()))
			ratingAtt.setMerchantName(usageAttributes.getMerchantName());
		if (isNotBlank(usageAttributes.getInvoiceText()))
			ratingAtt.setInvoiceText(usageAttributes.getInvoiceText());
		
		// ET391 - Add external fields to ER_subscriptions and pass to ERIF & PNH starts here
		ratingAtt.setExternalIdentifier1(usageAttributes.getExtIdentifier1());
		ratingAtt.setExternalIdentifier2(usageAttributes.getExtIdentifier2());
		ratingAtt.setExternalIdentifier3(usageAttributes.getExtIdentifier3());
        // ET391 - Add external fields to ER_subscriptions and pass to ERIF & PNH starts here
		return ratingAtt;
	}


	/**
	 * create rating attributes request jaxb object from purchase attributes (eg for a purchase call). <br/>
	 * pass in null and it returns null
	 * @param purchaseAttributes
	 * @return
	 */
	public RatingAttributesType buildRatingAttributes(PurchaseAttributes purchaseAttributes) {

		if (purchaseAttributes == null)
			return null;
		RatingAttributesType ratingAtt = new RatingAttributesType();
		if (isNotBlank(purchaseAttributes.getPaymentInformation()))
			ratingAtt.setPaymentInformation(purchaseAttributes.getPaymentInformation());

		if(isNotBlank(purchaseAttributes.getShortPackageId())) {
			ratingAtt.setShortPackageId(purchaseAttributes.getShortPackageId());
		}
		if (isNotBlank(purchaseAttributes.getExternalTransId()))
			ratingAtt.setExternalTransId(purchaseAttributes.getExternalTransId());
		
		if (isNotBlank(purchaseAttributes.getExternalField1()))
			ratingAtt.setExternalField1(purchaseAttributes.getExternalField1());
		if (isNotBlank(purchaseAttributes.getExternalField2()))
			ratingAtt.setExternalField2(purchaseAttributes.getExternalField2());
		
		
		//CR 2145
		if(purchaseAttributes.getPreRate()>=0)	{
			ratingAtt.setPreRate(purchaseAttributes.getPreRate());
			ratingAtt.setPreRatePriceIsGross(purchaseAttributes.isPreRatePriceGross());	
		}
		if (purchaseAttributes.getRenewalPreRate()>=0)	{
			ratingAtt.setRenewalPreRate(purchaseAttributes.getRenewalPreRate());
		}
		if (purchaseAttributes.getChargingMethod() !=Constants.INT_MATCH_ALL)
			ratingAtt.setChargingMethod(purchaseAttributes.getChargingMethod());

		if (purchaseAttributes.getDuration() !=Constants.INT_MATCH_ALL) {
			ratingAtt.setDurationCode(purchaseAttributes.getDuration());
		}
		if(isNotBlank(purchaseAttributes.getContentName())) {
			ratingAtt.setContentName(purchaseAttributes.getContentName());
		}
		if(isNotBlank(purchaseAttributes.getMerchantName())) {
			ratingAtt.setMerchantName(purchaseAttributes.getMerchantName());
		}
		if(isNotBlank(purchaseAttributes.getInvoiceText())) {
			ratingAtt.setInvoiceText(purchaseAttributes.getInvoiceText());
		}
		if (purchaseAttributes.getTaxRate()>0) {
			ratingAtt.setTaxRate(purchaseAttributes.getTaxRate());
			ratingAtt.setHasTaxRate(true);
		}
		if (isNotBlank(purchaseAttributes.getCsrId())){
			ratingAtt.setCsrId(purchaseAttributes.getCsrId());
		}
		//CR2255 Phase2 - add partner information
		if (isNotBlank(purchaseAttributes.getPartnerId())) {
			ratingAtt.setPartnerId(purchaseAttributes.getPartnerId());

			if (isNotBlank(purchaseAttributes.getPartnerUrl())) {
				ratingAtt.setPartnerUrl(purchaseAttributes.getPartnerUrl());
			}
			if (isNotBlank(purchaseAttributes.getPartnerContactInfo())) {
				ratingAtt.setPartnerContactInfo(purchaseAttributes.getPartnerContactInfo());
			}
			if (isNotBlank(purchaseAttributes.getPartnerEmail())) {
				ratingAtt.setPartnerEmail(purchaseAttributes.getPartnerEmail());
			}
			//ET 68 : Addition of partner-name field in ER starts here
			if (isNotBlank(purchaseAttributes.getPartnerName())) {
				ratingAtt.setPartnerName(purchaseAttributes.getPartnerName());
			}
			//ET 68 : Addition of partner-name field in ER ends here
		}
		//JIRAET81 - add AssetId
		if (isNotBlank(purchaseAttributes.getAssetID())) {
			ratingAtt.setAssetId(purchaseAttributes.getAssetID());
		}

		if (purchaseAttributes.getChannel() != Constants.INT_NOT_SET) {
			ratingAtt.setChannel(purchaseAttributes.getChannel());
		}
		if (isNotBlank(purchaseAttributes.getPromoCode()) && !purchaseAttributes.getPromoCode() .equals( Constants.STRING_MATCH_ALL)){
			ratingAtt.setPromoCode(purchaseAttributes.getPromoCode());
		}
		if (isNotBlank(purchaseAttributes.getTariff()) && !purchaseAttributes.getTariff() .equals( Constants.STRING_MATCH_ALL)){
			ratingAtt.setTariff(purchaseAttributes.getTariff());
		}

		// ET391 - Add external fields to ER_subscriptions and pass to ERIF & PNH starts here
		ratingAtt.setExternalIdentifier1(purchaseAttributes.getExtIdentifier1());
		ratingAtt.setExternalIdentifier2(purchaseAttributes.getExtIdentifier2());
		ratingAtt.setExternalIdentifier3(purchaseAttributes.getExtIdentifier3());
		// ET391 - Add external fields to ER_subscriptions and pass to ERIF & PNH starts here

		return ratingAtt;
	}
	/**
	 * Created the method to convert the object of its compatible type.
	 * @param auth
	 * @return
	 */
	public ModifyTariffAuthorization buildModifyTariffAuthrization(ModifyTariffAuthorisation auth){

		ModifyTariffAuthorization rv = new ModifyTariffAuthorization();

		rv.setSuccess(auth.isIsSuccess());

		if (null != auth.getInactivateTariffAuthorisations()){
			List<InactivateTariffAuthorisationType> listInactiveTariffAuthType = auth.getInactivateTariffAuthorisations().getInactivateTariffAuthorisation();

			InactivateTariffAuthorization addInactivateTariffObject = null;

			for(InactivateTariffAuthorisationType object:listInactiveTariffAuthType ){
				addInactivateTariffObject = new InactivateTariffAuthorization(); 
				addInactivateTariffObject.setSuccess(object.isIsSuccess());
				addInactivateTariffObject.setSubscriptionId(object.getPackageSubscriptionId());
				rv.addInactivateTariffAuthorization(addInactivateTariffObject);
			}


		}
		if (null != auth.getPurchaseTariffAuthorisations() ){
			List<PurchaseTariffAuthorisationType> listPurchaseTariffAuthType =	auth.getPurchaseTariffAuthorisations().getPurchaseTariffAuthorisation();

			PurchaseTariffAuthorization addPurchaseTariffAuthorization=null;
			for(PurchaseTariffAuthorisationType object:listPurchaseTariffAuthType){

				addPurchaseTariffAuthorization = new PurchaseTariffAuthorization();

				addPurchaseTariffAuthorization.setSuccess(object.isIsSuccess());
				addPurchaseTariffAuthorization.setSubscriptionId(object.getPackageSubscriptionId());
				addPurchaseTariffAuthorization.setNetRate(object.getRate());//TODO:verify
				addPurchaseTariffAuthorization.setReasonCode(convertReasonCode(object.getReasonCode()));
				addPurchaseTariffAuthorization.setTransactionId(object.getTransactionId());
				rv.addPurchaseTariffAuthorization(addPurchaseTariffAuthorization);
				//addList.add(addPurchaseTariffAuthorization);
				//TaxRate is not available in PurchaseTariffAuthorization

			}

		}

		rv.setReasonCode(convertReasonCode(auth.getReasonCode()));

		return rv;
	}


	public com.vodafone.global.er.decoupling.binding.request.BalanceFilter buildBalanceFilter(
			BalanceFilter filter) {
		com.vodafone.global.er.decoupling.binding.request.BalanceFilter xmlFilter = new 
				com.vodafone.global.er.decoupling.binding.request.BalanceFilter();
		xmlFilter.setOnlyAceAttribute(filter.isOnlyAcePackage());
		return xmlFilter;
	}
	
	public com.vodafone.global.er.business.catalog.BasePrice[]   buildBasePrices(GetBasePricesResponse response)
	{
		
		List<com.vodafone.global.er.decoupling.binding.response.BasePrice> lstBasePrice = response.getBasePrices();
		com.vodafone.global.er.business.catalog.BasePrice[] arrBasePrice = new com.vodafone.global.er.business.catalog.BasePrice[lstBasePrice.size()];
		com.vodafone.global.er.business.catalog.BasePrice basePrice = null;
		int cnt=0;
		if(lstBasePrice != null)
		{
			for (com.vodafone.global.er.decoupling.binding.response.BasePrice basePrice2 : lstBasePrice) {
				PricePoint pricePoint = new PricePoint();
				double rate = Double.parseDouble(basePrice2.getRate());
				pricePoint.setTaxRate(rate);
				basePrice = new com.vodafone.global.er.business.catalog.BasePrice(basePrice2.getServiceId(),pricePoint);
				arrBasePrice[cnt]=basePrice;
				cnt++;
				
			}
		}

		return arrBasePrice;

	}
	
	
	public com.vodafone.global.er.decoupling.binding.request.SelfcareFullTransactions.TransactionsFilter buildTransactionFilter(
			TransactionFilter transactionFilter) {
		final com.vodafone.global.er.decoupling.binding.request.SelfcareFullTransactions.TransactionsFilter transactionsFilterType_ = 
				new com.vodafone.global.er.decoupling.binding.request.SelfcareFullTransactions.TransactionsFilter();

		//JIRAET183 - also set if booleans set on the transaction filter 
		if(transactionFilter.getTransactionType() == TransactionFilter.MODIFY_TRANSACTIONS_ONLY || transactionFilter.isModifyEventsOnly())
			transactionsFilterType_.setIsModifyEventsOnly(true);
		else if(transactionFilter.getTransactionType() == TransactionFilter.REFUND_TRANSACTIONS_ONLY || transactionFilter.isRefundEventsOnly())
			transactionsFilterType_.setIsRefundEventsOnly(true);
		else if(transactionFilter.getTransactionType() == TransactionFilter.REFUND_PAYMENT_TRANSACTIONS_ONLY || transactionFilter.isRefundPaymentsEventsOnly())
			transactionsFilterType_.setIsRefundPaymentsEventsOnly(true);
		else if(transactionFilter.getTransactionType() == TransactionFilter.ALL_TRANSACTIONS || transactionFilter.isAllEvents())
			transactionsFilterType_.setIsAllEvents(true);

		if (isNotBlank(transactionFilter.getExternalTransId()))
			transactionsFilterType_.setExternalTransId(transactionFilter.getExternalTransId());
		if (isNotBlank(transactionFilter.getExternalField1()))
			transactionsFilterType_.setExternalField1(transactionFilter.getExternalField1());
		if (isNotBlank(transactionFilter.getExternalField2()))
			transactionsFilterType_.setExternalField2(transactionFilter.getExternalField2());

		if(transactionFilter.isMonetaryEventsOnly())
			transactionsFilterType_.setIsMonetryEventsOnly(true);

		if(transactionFilter.isNoZeroCostEvents())
			transactionsFilterType_.setIsNoZeroCostEventsOnly(true);

		if(transactionFilter.isActiveEventsOnly())
			transactionsFilterType_.setActiveEventsOnly(true);


		transactionsFilterType_.setPartnerId((transactionFilter.getPartnerId() != null?transactionFilter.getPartnerId():null));

		if(transactionFilter.getSubscriptionIdLong() > -1)
			transactionsFilterType_.setSubscriptionId(String.valueOf(transactionFilter.getSubscriptionIdLong()));

		//MQC 6628 - set transaction type and descending order
		transactionsFilterType_.setTransactionType((transactionFilter.getTransactionType() != null?transactionFilter.getTransactionType():null));
		transactionsFilterType_.setDescendingOrder(transactionFilter.isDescendingOrder());


		if (transactionFilter.getTransactionIdLong() > -1)
		{
			transactionsFilterType_.setTransactionId(Long.toString(transactionFilter.getTransactionIdLong()));
			transactionsFilterType_.setTransactionIdLong(transactionFilter.getTransactionIdLong());
		}        

		transactionsFilterType_.setMaxEvents(transactionFilter.getMaxEvents());

		if(transactionFilter.getEndDate() != null)
		{
			final Calendar calendar_ = Calendar.getInstance();
			calendar_.setTime(transactionFilter.getEndDate());
			transactionsFilterType_.setEndDate(calendar_);
		}

		if(transactionFilter.getStartDate() != null)
		{
			final Calendar calendar_ = Calendar.getInstance();
			calendar_.setTime(transactionFilter.getStartDate());
			transactionsFilterType_.setStartDate(calendar_);
		}
		return transactionsFilterType_;
	}
	
	// Jira ET245: starts here
	/**
	 * Generate the List of Subscription from the v2.Subscription list
	 * 
	 * @param subscriptions
	 * @return
	 */
	public List<Subscription> buildSubscriptions(List<com.vodafone.global.er.decoupling.binding.response.v2.Subscription> subscriptions) {
		List<Subscription> subList = new ArrayList<Subscription>();
		
		for(com.vodafone.global.er.decoupling.binding.response.v2.Subscription subs: subscriptions)	{
			subList.add(buildSubscription(subs));
		}
		return subList;
	}

	/**
	 * Generate the Subscription from the v2.Subscription 
	 * 
	 * @param subscription
	 * @return
	 */
	private Subscription buildSubscription(com.vodafone.global.er.decoupling.binding.response.v2.Subscription subs) {
		//Set the subscription object from V2 type subscriptionType 
		Subscription s = new Subscription();
		
		s.setSubscriptionIdLong(subs.getId());
		
		s.setStatus(subs.getStatus());
		/*if(isNotBlank(subs.getSubscriptionStatus())){
			
				for (Entry<Integer, String> statusMap: SubscriptionStatus.statusMap.entrySet()){
					if (Objects.equals(subs.getSubscriptionStatus(), statusMap.getValue())) {
						s.setStatus(statusMap.getKey());
			        }
				}
			
				//s.setStatus(SubscriptionStatus.statusMap.);
		}*/
		
		s.setPricePoint(buildPricepoint(subs.getPricepoint()));
		s.setPurchaseDate(subs.getPurchaseDate().getTime()); 
		s.setStartDate(subs.getPurchaseDate().getTime());//start date set as purchase date 
		s.setExpiryDate(subs.getExpiryDate().getTime());
		if(subs.getMinSubPeriodEnd() !=null && subs.getMinSubPeriodEnd().getValue()!=null && !subs.getPurchaseDate().equals(subs.getMinSubPeriodEnd().getValue())){
			s.setFinalMinSubscriptionEndDate(subs.getMinSubPeriodEnd().getValue().getTime()); //ET-280
			s.setPenaltyCharge(subs.getMinSubPeriodEnd().getPenaltyCharge().doubleValue());
		}
		
		//Payment and Usage Transactions
		if (subs.getPaymentTransactions()!=null && !subs.getPaymentTransactions().getTransaction().isEmpty())	{
			s.setPaymentTransactions(buildPaymentTransactions(subs.getPaymentTransactions().getTransaction()));
			if(subs.getUsageTransactions() !=null && subs.getUsageTransactions().getTransaction() != null && !subs.getUsageTransactions().getTransaction().isEmpty())
				s.getPaymentTransactions().addAll(buildUsageTransactions(subs.getUsageTransactions().getTransaction()));
			
		}
		//ModifyTransactions
		if (subs.getModifyTransactions()!=null && !subs.getModifyTransactions().getModify().isEmpty())	{
			s.setModifyTransactions(buildModifyTransactions(subs.getModifyTransactions().getModify()));
		}
		//RefundTransactions
		if (subs.getRefundTransactions()!=null && !subs.getRefundTransactions().getRefund().isEmpty())	{
			buildRefundTransactions(subs.getRefundTransactions().getRefund());
			//not sure where to set this back in Subscription
			//s.getRefundTransactions().getRefund().addAll(tb.buildV2RefundTransactions(subs.getRefundTransactions()));
		}
		s.setPackageId(subs.getPackageId());

		// not sure how to set the service id... either create a new setter in subscription object.
		//s.setServiceProvTag(serviceId, provTag);		
		//s.setServices(buildV2Services(Arrays.asList(subs.getServiceIds())));
		if(subs.getResourceBalances() !=null && subs.getResourceBalances().getResourceBalance() != null && !subs.getResourceBalances().getResourceBalance().isEmpty()){
			List<ResourceBalance> respBalanceList = buildResourceBalances(subs.getResourceBalances().getResourceBalance());
			if(respBalanceList!=null && !respBalanceList.isEmpty())
				s.setResourceBalances(respBalanceList.toArray(new ResourceBalance[respBalanceList.size()]));
		}else{
			List<ResourceBalance> respBalanceList = new ArrayList<ResourceBalance>();
			s.setResourceBalances(respBalanceList.toArray(new ResourceBalance[0]));
		}
		//ET-265 - Adding External sub id in response - begin
		s.setExternalSubId(subs.getExternalSubId());
		//ET-265 - Adding External sub id in response - end
		//ET-267
		if (subs.getB2BPartner()!=null)
			s.setB2BPartner(buildB2BPartner(subs));
		//ET-280 |Start
		if(subs.getMerchantName()!=null)
			s.setMerchantName(subs.getMerchantName());
		if(subs.getPartnerId() !=null)
			s.setPartnerId(subs.getPartnerId());
		if(subs.getParentPackageId() !=null)
			s.setParentPackageID(subs.getParentPackageId());
		//here >0 check to find package is available for renew, equals -2 means no more renewal
		if(subs.getRemainingRenewals() > 0 ||subs.getRemainingRenewals() ==-2){
			s.setCurrentNoOfOccurences((long)subs.getRemainingRenewals());//ET-330
		}
//		if(subs.getPenaltyCharge() != null && subs.getMinSubPeriodEnd() != null)
//			s.setPenaltyCharge(subs.getPenaltyCharge().doubleValue());
		//ET-280 |End

		if(subs.getServices() != null && subs.getServices().getServiceId() != null) {
            for(String id : subs.getServices().getServiceId()) {
                s.setServiceProvTag(id,"N/A");
            }
        }

		// ET391 - Add external fields to ER_subscriptions and pass to ERIF & PNH starts here
		s.setExtIdentifier1(subs.getExternalIdentifier1());
		s.setExtIdentifier2(subs.getExternalIdentifier2());
		s.setExtIdentifier3(subs.getExternalIdentifier3());
        // ET391 - Add external fields to ER_subscriptions and pass to ERIF & PNH starts here
      	   
		return s;
	}
	private B2BPartner buildB2BPartner(com.vodafone.global.er.decoupling.binding.response.v2.Subscription subs) {
		B2BPartner b2bPartner = new B2BPartner();
		if(subs.getB2BPartner()!=null){
			b2bPartner.setContactInfo(subs.getB2BPartner().getContactInfo());
			b2bPartner.setEmail(subs.getB2BPartner().getEmail());
			b2bPartner.setPartnerId(subs.getB2BPartner().getId());
			b2bPartner.setPartnerName(subs.getB2BPartner().getPartnerName());
			b2bPartner.setUrl(subs.getB2BPartner().getUrl());
		}
		return b2bPartner;
	}
	
	/**
	 * Generate usageTransactions
	 * 
	 * @param transactionsList
	 * @return
	 */
	private List<PaymentTxn> buildUsageTransactions(List<UsageTransaction> usageTransactions){
		List<PaymentTxn> transactionsList = new ArrayList<PaymentTxn>();
		
		for(UsageTransaction usageTransaction: usageTransactions){
			transactionsList.add(buildUsageTransaction(usageTransaction));
		}
		
		return transactionsList;
			
	}
	
	
	/**
	 * Generate paymentTransactions
	 * 
	 * @param transactionsList
	 * @return
	 */
	private List<PaymentTxn> buildPaymentTransactions(List<PaymentTransaction> paymentTrans){
		List<PaymentTxn> transactionsList = new ArrayList<PaymentTxn>();
		
		for(PaymentTransaction paymentTran: paymentTrans){
			transactionsList.add(buildPaymentTransaction(paymentTran));
		}
		
		return transactionsList;
			
	}
	
	/**
	 * Generate modifyTransactions
	 * 
	 * @param transactionsList
	 * @return
	 */
	private List<ModifyTxn> buildModifyTransactions(List<ModifyTransaction> modifyTrans){
		List<ModifyTxn> transactionsList = new ArrayList<ModifyTxn>();
		
		for(ModifyTransaction modifyTran: modifyTrans){
			transactionsList.add((ModifyTxn) buildModifyTransaction(modifyTran));
		}
		
		return transactionsList;
			
	}

	private List<RefundTxn> buildRefundTransactions(List<RefundTransaction> refundTrans){
		List<RefundTxn> refundTxnList = new ArrayList<RefundTxn>();
		
		for(RefundTransaction refundTran: refundTrans){
			refundTxnList.add(buildRefundTransaction(refundTran));
		}
			
		return refundTxnList;
	}

	/**
	 * Build Pricepoint frpm v2.pricepoint
	 * 
	 * @param pricePoint
	 * @return
	 */
	private PricePoint buildPricepoint(Pricepoint pricePoint) {
		PricePoint pp = new PricePoint();
		pp.setChargingMethod(pricePoint.getChargingMethod());
		
		if(pricePoint.getId()!=null)
			pp.setId(String.valueOf(pricePoint.getId()));
		
		pp.setPricingText1(pricePoint.getPricingText1());
		pp.setPricingText2(pricePoint.getPricingText2());
		
		if(isNotBlank(pricePoint.getPromoCode())){
			String[] promoCodes = new String[1];
			promoCodes[0] = pricePoint.getPromoCode();		
			pp.setPromoCodes(promoCodes);
		}
		
		if(pricePoint.getRate().getResourceCode() !=null){
		pp.setResource(buildChargingResource( pricePoint.getRate().getResource(), pricePoint.getRate().getResourceCode()));
		}else{
		     pp.setResource(ChargingResource.getResource(pricePoint.getRate().getResource()));
		}
		
		if(pricePoint.getRate() != null){
			//based on gross rate and tax rate calculated the net rate, netRate = grossRate/(1+taxRate)
			pp.setNetRate(pricePoint.getRate().getValue().doubleValue() / (1 + pricePoint.getRate().getTaxRate().doubleValue()));
		}
		
		pp.setUserGroup(pricePoint.getUserGroup());
		
		pp.setDuration(pricePoint.getDurationCode());
		
		pp.setTaxRate(pricePoint.getRate().getTaxRate().doubleValue());
		
		return pp;
	}
	
	private ChargingResource buildChargingResource(String resourceName, int resourceCode) {
		return new ChargingResource(resourceCode, resourceName);
//		cr.setDescription(resource.getDescription());
//		cr.setName(resource.getName());
		
//		return cr;
	}


	/**
	 * Build resourceBalance list frpm v2.resourceBalance
	 * 
	 * @param resourceBalances
	 * @return
	 */
	private List<ResourceBalance> buildResourceBalances(List<com.vodafone.global.er.decoupling.binding.response.v2.ResourceBalance> resourceBalancesList){
		List<ResourceBalance> resourceBalances = new ArrayList<ResourceBalance>();
		
		for(com.vodafone.global.er.decoupling.binding.response.v2.ResourceBalance resourceBalance: resourceBalancesList){
			ResourceBalance rb = new ResourceBalance(buildResourceBalance(resourceBalance));
			
			resourceBalances.add(rb);
		}
			
		return resourceBalances;
	}
	
	private ResourceBalance buildResourceBalance(
			com.vodafone.global.er.decoupling.binding.response.v2.ResourceBalance resBal) {
		
		final ChargingResource chRes = new ChargingResource(resBal.getCode(), resBal.getName());
		return new ResourceBalance(chRes, resBal.getValue().doubleValue());

	}


	/**
	 * Build ChargingResource  frpm v2.ChargingResource
	 * 
	 * @param chargingResource
	 * @return
	 */
	private ChargingResource buildChargingResource(
			com.vodafone.global.er.decoupling.binding.response.v2.ChargingResource resource) {
		
		ChargingResource cr = new ChargingResource(resource.getCode(), resource.getName());
		cr.setDescription(resource.getDescription());
		cr.setName(resource.getName());
		
		return cr;
	}
	
	/**
	 * 
	 * Build PaymentTxn Object from UsageTransactaion
	 * 
	 * @param txn
	 * @return
	 */
	public PaymentTxn buildUsageTransaction(
			UsageTransaction txn) {
		PaymentTxn t = new PaymentTxn();
		t.setAffiliateID(txn.getAffiliateId());
		t.setAggregatorId(txn.getAggregatorId());
		t.setAssetId(txn.getAssetId());
		t.setContentName(txn.getContentName());
		t.setDeviceId(txn.getDeviceId());
		t.setPaymentErrorId(txn.getErrorId());
		t.setPaymentErrorDescription(txn.getErrorDescription());
		t.setExternalField1(txn.getExternalField1());
		t.setExternalField2(txn.getExternalField2());
		t.setExternalTransId(txn.getExternalTransId());
		t.setPartnerId(txn.getPartnerId());
		t.setPaymentInfo(txn.getPaymentInfo());
		t.setPurchaseDate(txn.getPurchaseDate().getTime());
		if(txn.getAmount() != null)
			t.setPurchaseRate(txn.getAmount().getValue().doubleValue());
//		if (txn.getRefundPaymentTransactionIdLong()>0)
//			t.setRefundPaymentTransactionId(txn.getRefundPaymentTransactionIdLong());
//		t.setRefundReason(txn.getReason());
		t.setServiceId(txn.getServiceId());
		t.setStatus(txn.getStatus());
		if(txn.getTaxRate() != null)
			t.setTaxRate(txn.getTaxRate().doubleValue());
		t.setTransactionIdLong(txn.getId());
		t.setType(new TransactionType(txn.getType()));
		return t;
	}
	
	/**
	 * 
	 * Build PaymentTxn Object from PaymentTransaction
	 * 
	 * @param txn
	 * @return
	 */
	public PaymentTxn buildPaymentTransaction(
			PaymentTransaction txn) {
		PaymentTxn t = new PaymentTxn();
		t.setAffiliateID(txn.getAffiliateId());
		t.setAggregatorId(txn.getAggregatorId());
		t.setAssetId(txn.getAssetId());
		t.setContentName(txn.getContentName());
		t.setDeviceId(txn.getDeviceId());
		t.setPaymentErrorId(txn.getErrorId());
		t.setPaymentErrorDescription(txn.getErrorDescription());
		t.setExternalField1(txn.getExternalField1());
		t.setExternalField2(txn.getExternalField2());
		t.setExternalTransId(txn.getExternalTransId());
		t.setPartnerId(txn.getPartnerId());
		t.setPaymentInfo(txn.getPaymentInfo());
		t.setPurchaseDate(txn.getPurchaseDate().getTime());
		if(txn.getAmount() != null)
			t.setPurchaseRate(txn.getAmount().getValue().doubleValue());
//		if (txn.getRefundPaymentTransactionIdLong()>0)
//			t.setRefundPaymentTransactionId(txn.getRefundPaymentTransactionIdLong());
//		t.setRefundReason(txn.getReason());
		t.setServiceId(txn.getServiceId());
		t.setStatus(txn.getStatus());
		if(txn.getTaxRate() != null)
			t.setTaxRate(txn.getTaxRate().doubleValue());
		t.setTransactionIdLong(txn.getId());
		t.setType(new TransactionType(txn.getType()));
		return t;
	}
	
	/**
	 * 
	 * Build Transaction Object from ModifyTransaction
	 * 
	 * @param transaction
	 * @return
	 */
	public Transaction buildModifyTransaction(
			ModifyTransaction txn) {
		Transaction m = new Transaction();
		m.setModifyTransactionId(String.valueOf(txn.getId()));
		
		m.setType(new TransactionType(txn.getType()));
		
		m.setSubscriptionIdLong(txn.getSubId());
		m.setReason(txn.getReason());
		if(m.getEventDateTime()!=null && txn.getTimestamp()!=null)
			m.getEventDateTime().setTime(txn.getTimestamp().getTime());
		return m;
	}
	
	
	/**
	 * 
	 * Build RefundTxn Object from RefundTransaction
	 * 
	 * @param refundTxn
	 * @return
	 */
	public RefundTxn buildRefundTransaction(RefundTransaction rTxn) {
		RefundTxn refundTxn = new RefundTxn();
		refundTxn.setAmount(rTxn.getAmount().getValue()); //Need to check
		refundTxn.setPaymentErrorDescription(rTxn.getErrorDescription());
		refundTxn.setPaymentErrorId(rTxn.getErrorId());
		//refundTxn.setPaymentTransaction();//TODO:need to discuss
		refundTxn.setRefundPaymentTransactionIdLong(rTxn.getId());
		refundTxn.setReason(rTxn.getReason());
		refundTxn.setType(new TransactionType(rTxn.getType()));
		refundTxn.setStatus(rTxn.getStatus());
		if(refundTxn.getEventDateTime()!=null && rTxn.getTimestamp()!=null)
			refundTxn.getEventDateTime().setTime(rTxn.getTimestamp().getTime());
		return refundTxn;
	}
	// Jira ET245: ends here

	//Start for ET-280
	public List<Subscription> buildPackageForSubscription(List<com.vodafone.global.er.decoupling.binding.response.v2.Subscription> subscripitonList,String clientId) {
		List<Subscription> list = new ArrayList<Subscription>();
		
		for (Subscription subs : buildSubscriptions(subscripitonList)) {
			PricePoint pricePoint = subs.getPricePoint();
			RatingAttributes ratingAttributes = new RatingAttributes();
			//CatalogPackage and rating attributes added to support the customer care api call
			CatalogPackage catalogPackage = getCatalogePackage(subs.getPackageId(),clientId);
			catalogPackage.setPricePoint(pricePoint);
			subs.setPackage(catalogPackage);

			ratingAttributes.setChargingMethod(pricePoint.getChargingMethod());
			ratingAttributes.setDuration(pricePoint.getDuration());
			ratingAttributes.setUserGroup(pricePoint.getUserGroup());
			String []userGroups = {pricePoint.getUserGroup()};
			ratingAttributes.setUserGroups(userGroups);
			ratingAttributes.setPromoUniqueCode(pricePoint.getPromoCode());
			ratingAttributes.setPromoCodes(pricePoint.getPromoCodes());
			subs.setRatingAttributes(ratingAttributes);

			list.add(subs);
		}
		return list;

	}
	/**
	 * Get the CatalogPackage from the caching
	 * @param packageId
	 * @param clientId
	 * @return
	 */
	private CatalogPackage getCatalogePackage(String packageId,String clientId)	{
		CachingCatalogApiImpl catalogApi = (CachingCatalogApiImpl) DecouplingApiFactory.getCatalogApi(locale, clientId);
		CatalogPackage cpack = catalogApi.getSimplePackage(packageId);
		return cpack;
	}
	//End for ET-280


	public List<ServiceOffer> buildServiceOffers(List<OfferServiceType> service) {
		List<ServiceOffer> serviceOfferList = new ArrayList<ServiceOffer>();
		
		for (OfferServiceType serviceOffer : service) {
			serviceOfferList.add(buildServiceOffer(serviceOffer));
		}
		
		return serviceOfferList;
	}
	
	private ServiceOffer buildServiceOffer(OfferServiceType serviceOffer) {
		ServiceOffer offer = new ServiceOffer();
		offer.setServiceId(serviceOffer.getId());
		if (serviceOffer.getPricepointOrSubscription() != null && serviceOffer.getPricepointOrSubscription().size()>0) {
			Element e = serviceOffer.getPricepointOrSubscription().get(0);
			if (e != null && e.getClass() != null) {
				String className = e.getClass().getName();
				if (isNotBlank(className)) {
					if(className.contains("PricePointLiteType")) {
						logger.debug("Service offer contains PricePoint options");
						List<PricePoint> ppts = getPricePointLiteList(serviceOffer.getPricepointOrSubscription());
						offer.setPricePoints(ppts);
						
					} else if(className.contains("SubscriptionLiteType")) {
						logger.debug("Service offer contains Subscriptions");
						List<Subscription> subs = getSubscriptionLiteList(serviceOffer.getPricepointOrSubscription());
						offer.setSubscriptions(subs);
					}
				}
			}
		}
		
		return offer;
	}
	
	private <T>List<T> castCollection(List srcList, Class<T> clas){
	    List<T> list =new ArrayList<T>();
	    for (Object obj : srcList) {
	    	if(obj!=null && clas.isAssignableFrom(obj.getClass())) {
	    		list.add(clas.cast(obj));
	    	}
	    }
		return list;
	}
	
	private List<Subscription> getSubscriptionLiteList(List<Element> subs) {
		List<SubscriptionLiteType> subLiteTypes = castCollection(subs, com.vodafone.global.er.decoupling.binding.response.SubscriptionLiteType.class);
		List<Subscription> subList = new ArrayList<Subscription>();
		for(SubscriptionLiteType subLiteType : subLiteTypes) {
			Subscription sub = new Subscription();
			sub.setSubscriptionIdLong(subLiteType.getId());
			sub.setStatus(subLiteType.getStatus());
			sub.setPackageId(subLiteType.getPackageId());
			sub.setPurchaseDate(subLiteType.getPurchaseDate().getTime());
			sub.setExpiryDate(subLiteType.getExpiryDate().getTime());
			RatingAttributes rAtt = new RatingAttributes();
			rAtt.setChargingMethod(subLiteType.getChargingMethod());
			rAtt.setDuration(subLiteType.getDuration());
			if (isNotBlank(subLiteType.getUserGroup()))
				rAtt.setUserGroup(subLiteType.getUserGroup());
			if (isNotBlank(subLiteType.getPromoCode()))
				rAtt.setPromoCodes(new String [] {subLiteType.getPromoCode()});
			rAtt.setChannel(subLiteType.getChannel());
			sub.setRatingAttributes(rAtt);
			subList.add(sub);
		}
		
		return subList;
	}
	
	private List<PricePoint> getPricePointLiteList(List<Element> ppts) {
		List<PricePointLiteType> pptLiteTypes = castCollection(ppts, com.vodafone.global.er.decoupling.binding.response.PricePointLiteType.class);
		List<PricePoint> pptList = new ArrayList<PricePoint>();
		for(PricePointLiteType pptLiteType : pptLiteTypes) {
			PricePoint ppt = new PricePoint();
			ppt.setId(pptLiteType.getId());
			ppt.setNetRate(pptLiteType.getRate().getValue().doubleValue() / (1 + pptLiteType.getRate().getTaxRate().doubleValue()));
			ppt.setTaxRate(pptLiteType.getRate().getTaxRate().doubleValue());
			ChargingResource cr = ChargingResource.getResource(pptLiteType.getRate().getResource());
			ppt.addBalanceImpact(new BalanceImpact(cr, ppt.getRate()));
			ppt.setChargingMethod(pptLiteType.getChargingMethod());
			ppt.setDuration(pptLiteType.getDuration());
			if (isNotBlank(pptLiteType.getUserGroup()))
				ppt.setUserGroup(pptLiteType.getUserGroup());
			if (isNotBlank(pptLiteType.getPromoCode()))
				ppt.setPromoCodes(new String [] {pptLiteType.getPromoCode()});
			ppt.setChannel(pptLiteType.getChannel());
			pptList.add(ppt);
		}
		
		return pptList;
	}
	
}