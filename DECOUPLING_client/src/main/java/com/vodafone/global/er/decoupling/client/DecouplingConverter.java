package com.vodafone.global.er.decoupling.client;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.*;
import com.vizzavi.ecommerce.business.catalog.internal.BalanceImpact;
import com.vizzavi.ecommerce.business.catalog.internal.BalanceImpacts;
import com.vizzavi.ecommerce.business.catalog.internal.CatalogPackageImpl;
import com.vizzavi.ecommerce.business.catalog.internal.CatalogServiceImpl;
import com.vizzavi.ecommerce.business.catalog.internal.PricePointImpl;
import com.vizzavi.ecommerce.business.catalog.internal.PricePointTier;
import com.vizzavi.ecommerce.business.catalog.internal.PricePointsImpl;
import com.vizzavi.ecommerce.business.catalog.internal.SuperCreditPricePointImpl;
import com.vizzavi.ecommerce.business.catalog.internal.model.DateTimeTier;
import com.vizzavi.ecommerce.business.catalog.internal.model.DayRange;
import com.vizzavi.ecommerce.business.catalog.internal.model.PricingModel;
import com.vizzavi.ecommerce.business.catalog.internal.model.RangeValue;
import com.vizzavi.ecommerce.business.catalog.internal.model.Tier;
import com.vizzavi.ecommerce.business.charging.AccountValidationAuthorization;
import com.vizzavi.ecommerce.business.charging.BaseAuthorization;
import com.vizzavi.ecommerce.business.charging.GoodwillCreditAuthorization;
import com.vizzavi.ecommerce.business.charging.PurchaseAttributes;
import com.vizzavi.ecommerce.business.charging.PurchaseAuthorization;
import com.vizzavi.ecommerce.business.charging.ResourceBalanceOnly;
import com.vizzavi.ecommerce.business.charging.SubscriptionAttributes;
import com.vizzavi.ecommerce.business.charging.UsageAttributes;
import com.vizzavi.ecommerce.business.charging.UsageAuthorization;
import com.vizzavi.ecommerce.business.common.ChargingMethod;
import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.Constants;
import com.vizzavi.ecommerce.business.common.Duration;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.ErCoreConst;
import com.vizzavi.ecommerce.business.common.RatingAttributes;
import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.common.ResponseStatus;
import com.vizzavi.ecommerce.business.provision.UpdateServiceStatusAuthorization;
import com.vizzavi.ecommerce.business.selfcare.BasicAccount;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;
import com.vizzavi.ecommerce.business.selfcare.SpendLimits;
import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vizzavi.ecommerce.business.selfcare.Transaction;
import com.vizzavi.ecommerce.business.selfcare.TransactionType;
import com.vizzavi.ecommerce.business.selfcare.UserGroup;
import com.vodafone.global.er.decoupling.binding.request.ExpressPackageRequestType.ExpressDisplayAttributesType;
import com.vodafone.global.er.decoupling.binding.request.ExpressPackageRequestType.ServiceIdsType;
import com.vodafone.global.er.decoupling.binding.request.PurchaseAttributesType;
import com.vodafone.global.er.decoupling.binding.request.PurchaseAttributesType.ResourceBalancesOnlyType;
import com.vodafone.global.er.decoupling.binding.request.RatingAttributesType;
import com.vodafone.global.er.decoupling.binding.request.SubscriptionAttributesType;
import com.vodafone.global.er.decoupling.binding.request.impl.ExpressPackageRequestTypeImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.PurchaseAttributesTypeImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.PurchaseAttributesTypeImpl.ResourceBalancesOnlyTypeImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.RatingAttributesTypeImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.ResourceBalanceOnlyTypeImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.SubscriptionAttributesTypeImpl;
import com.vodafone.global.er.decoupling.binding.response.*;
import com.vodafone.global.er.decoupling.binding.response.CatalogPackageFullType.CatalogServicesType;
import com.vodafone.global.er.decoupling.binding.response.CatalogPackageFullType.ChildPackageIdsType;
import com.vodafone.global.er.decoupling.binding.response.CatalogPackageFullType.PricingModelsType;
import com.vodafone.global.er.decoupling.binding.response.FullExpressPackagesType.ExpressPackageType;
import com.vodafone.global.er.decoupling.binding.response.PackageType.PricePointsType;
import com.vodafone.global.er.decoupling.binding.response.PricePointFullType.PricePointTiersType;
import com.vodafone.global.er.decoupling.binding.response.PricePointType.BalanceImpactRatesType;
import com.vodafone.global.er.decoupling.binding.response.PricePointType.BalanceImpactRatesType.BalanceImpactRateType;
import com.vodafone.global.er.decoupling.binding.response.PricingModelFullType.TiersType;
import com.vodafone.global.er.decoupling.binding.response.PurchaseOptionsType.PackagesType;
import com.vodafone.global.er.decoupling.binding.response.SubscriptionType.ServicesType;
import com.vodafone.global.er.decoupling.binding.response.impl.CatalogServiceFullTypeImpl;
import com.vodafone.global.er.decoupling.binding.response.impl.ReasonCodeTypeImpl;
import com.vodafone.global.er.decoupling.binding.response.impl.ResourceBalanceTypeImpl;
import com.vodafone.global.er.partner.B2BPartner;
import com.vodafone.global.er.rating.RatedEvent;
import com.vodafone.global.er.util.DateTimeUtil;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.isBlank;

class DecouplingConverter
{
	private final static Logger logger = LoggerFactory.getLogger(DecouplingConverter.class);
	private final Locale locale;


	public DecouplingConverter(Locale locale)	{
		this.locale = locale;
	}


	public static ReasonCodeType buildReasonCode(ReasonCode rc) {
		final ReasonCodeType rct = new ReasonCodeTypeImpl();
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

//	public static ReasonCode buildReasonCode(ReasonCodeType rct)	{
//		return ReasonCode.getReasonCode(rct.getCode());
//	}


	public BasicAccount buildAccount(FullAccountType jaxbObject)	{

		final BasicAccount account = new BasicAccount(jaxbObject.getMsisdn(), jaxbObject.getBan(), locale, jaxbObject.getBillingCycleDate(), jaxbObject.getUtcOffset(), jaxbObject.getSpId(), jaxbObject.getIsPrepay(), jaxbObject.getChildSpId(), jaxbObject.getSpType());

		final List<UserGroup> groups = new ArrayList<UserGroup>();

		final List<FullAccountType.UserGroupsType> userGroups = jaxbObject.getUserGroups();
		if (userGroups != null && userGroups.size()>0)	{
			for (final FullAccountType.UserGroupsType jaxbug: userGroups){
				groups.add(new UserGroup(jaxbug.getName(), jaxbug.getDescription()));
			}
		}
		account.setUserGroups(groups.toArray(new UserGroup[groups.size()]));

		FullAccountType.SpendLimitsType jaxbSL = jaxbObject.getSpendLimits();
		SpendLimits spendLimits;
		if (jaxbSL!=null)	{
		     spendLimits = new SpendLimits(jaxbSL.getPerTxLimit(), 
                                             jaxbSL.getPerDayLimit(), jaxbSL.getPerMonthLimit(), jaxbSL.getCumulativeSpendDay(), jaxbSL.getCumulativeSpendMonth(), null);

                     account.setSpendLimits(spendLimits);
		}//else{
	          //   spendLimits = new SpendLimits();
		//}

//		final PaymentCardDetailsType paymentCardDetailsType_ = jaxbObject.getPaymentCardDetails();
//		if(paymentCardDetailsType_ != null)
//		{
//			final PaymentCardDetails paymentCardDetails_ = new PaymentCardDetails();
//			paymentCardDetails_.setCardName((paymentCardDetailsType_.getName() != null?paymentCardDetailsType_.getName():null));
//			paymentCardDetails_.setIssueNumber((paymentCardDetailsType_.getIssueNumber() != null?paymentCardDetailsType_.getIssueNumber():null));
//			paymentCardDetails_.setCardNumber((paymentCardDetailsType_.getNumber() != null?paymentCardDetailsType_.getNumber():null));
//			paymentCardDetails_.setCardType((paymentCardDetailsType_.getType() != null?paymentCardDetailsType_.getType():null));
//			paymentCardDetails_.setExpiryDate((paymentCardDetailsType_.getExpiryDate() != null?paymentCardDetailsType_.getExpiryDate().getTime():null));
//			paymentCardDetails_.setStartDate((paymentCardDetailsType_.getStartDate() != null?paymentCardDetailsType_.getStartDate().getTime():null));
//			account.setPaymentCardDetails(paymentCardDetails_);
//		}

                

		return account;
	}

	/**
	 * sets up user groups and spend limits too, but not msisdn, ban, name, etc
	 * @param jaxbObject
	 * @return
	 */
	public BasicAccount buildAccount(BasicAccountType jaxbObject)	{

		final BasicAccount account = new BasicAccount(jaxbObject.getMsisdn(), jaxbObject.getBan(), jaxbObject.getName(), locale, jaxbObject.getStatus());

		return account;

	}




	public Subscription[] convertSubscriptionsArray(SelfcareFullSubscriptions payload) 
	{
		logger.debug("Subscription[] convertSubscriptionsArray(SelfcareSubscriptionsType payload)");

		final List<Subscription> subsToReturn = new ArrayList<Subscription>();
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
		final SubscriptionFullType.ResourceBalancesType rbType  = subType.getResourceBalances();
		//new ServiceTypeImpl();
		CatalogPackage package_ = new CatalogPackage();

		final CatalogPackageFullType packType = subType.getPackage();
		package_ = convertFullPackageType(packType);

		final ServicesType servicesTypes_ = (ServicesType)subType.getServices();
		if(servicesTypes_ != null)
		{
			final List<String> serviceidsList = servicesTypes_.getServiceId();
			if(serviceidsList != null && serviceidsList.size() >0)
			{
				subscription_.setServiceProvTag(serviceidsList.get(0),"N/A");
			}
		}

		final List<?> list = rbType.getResourceBalance();
		if(list != null)
		{
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
			subscription_.setCurrentNoOfOccurences(subType.getCurrentNumberOfOccurences());

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
					final SubscriptionFullType.TransactionsType transactions_ = subType.getTransactions();

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
				b2bPartner.setId(subType.getB2BPartner().getId());
				subscription_.setPartnerId(subType.getB2BPartner().getId());
				b2bPartner.setUrl(subType.getB2BPartner().getUrl());
				b2bPartner.setContactInfo(subType.getB2BPartner().getContactInfo());
				b2bPartner.setEmail(subType.getB2BPartner().getEmail());
				subscription_.setB2BPartner(b2bPartner);
			}

		}
		catch(final Exception e)
		{
			logger.info("Problem with Decoupling Client added conversions convertSubscriptionType " + e);
			return null;
		}      
		return subscription_;
	}

	public ResourceBalance[] convertResourceBalanceArray(List<?> resBalTypes) 
	{
		if(logger.isDebugEnabled())
			logger.debug("ResourceBalance[] convertResourceBalanceArray(List resBalTypes)");

		try 
		{
			if(resBalTypes==null)
			{
				throw new EcommerceException("Resource Balances are null");
			}
		}
		catch(final EcommerceException ee)
		{
			ee.printStackTrace();
		}
		final ResourceBalanceFullType[] resBalArr = resBalTypes.toArray(new ResourceBalanceFullType[resBalTypes.size()]);

		final ResourceBalance[]rv = new ResourceBalance[resBalArr.length];
		for(int i=0;i<resBalArr.length;i++)
		{
			rv[i] = convertFullResourceBalance(resBalArr[i]);
		}
		return rv;
	}

	public ResourceBalance convertResourceBalance(ResourceBalanceTypeImpl resBalType) 
	{
		if(logger.isDebugEnabled())
			logger.debug("ResourceBalance convertResourceBalance(ResourceBalanceTypeImpl resBalType)");

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


				DRMType drmType_ = DRMType.NOT_DRM;
				try
				{
					if(packType.getDrmType().getName() != null && !packType.getDrmType().getName().equalsIgnoreCase(""))
						drmType_ = DRMType.convertStringToDRMType(packType.getDrmType().getName());
				}
				catch(final Exception e)
				{
					logger.error("Problem converting DRMType will set NOT_DRM:" + e);
					drmType_ = DRMType.NOT_DRM;
				}

				cataloPackageImpl_.setDRMType(drmType_);

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

				final PricePointsType pps_ = packType.getPricePoints();

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


				DRMType drmType_ = DRMType.NOT_DRM;
				try
				{
					if(packType.getDrmType().getName() != null && !packType.getDrmType().getName().equalsIgnoreCase(""))
						drmType_ = DRMType.convertStringToDRMType(packType.getDrmType().getName());
				}
				catch(final Exception e)
				{
					logger.error("Problem converting DRMType will set NOT_DRM:" + e);
					drmType_ = DRMType.NOT_DRM;
				}

				cataloPackageImpl_.setDRMType(drmType_);

				final PricePointFullType pricePointType_ = packType.getPricePoint();
				PricePointImpl singlePricePoint_= null;
				//CR1455 - start
				cataloPackageImpl_.setIsSuperPackage(packType.isIsSuperPackage());
				if(packType.getPricingModels() != null)
				{
					final PricingModelsType pmTypes_ = packType.getPricingModels();
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

				final com.vodafone.global.er.decoupling.binding.response.CatalogPackageFullType.PricePointsType pps_ = packType.getPricePoints();

				if(pps_ != null)
				{
					final List<PricePointFullType> points_ = pps_.getPricePoint();
					if(points_ != null)
					{
						final HashMap<String, PricePoint> map_ = new HashMap<String, PricePoint>();
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
							final HashMap<String, PricePoint> map_ = new HashMap<String, PricePoint>();
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
					final CatalogServicesType serviceNamesTypes_ =  packType.getCatalogServices();
					if(serviceNamesTypes_ != null)
					{
						final List<CatalogServiceFullTypeImpl> catalogServicesList_ = serviceNamesTypes_.getCatalogService();
						final List<CatalogService> services_ = new ArrayList<CatalogService>();
						for (final CatalogServiceFullTypeImpl catalogService_: (catalogServicesList_))
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
					final ChildPackageIdsType childPackagesIds_ = packType.getChildPackageIds();
					if(childPackagesIds_ != null)
					{
						final List<String> childPackagesIdsList_ = childPackagesIds_.getChildPackageId();
						cataloPackageImpl_.setChildPackages(new ArrayList<String>(childPackagesIdsList_));
					}
				}
				catch(final Exception e)
				{
					logger.error("Problem setting Child Packages Ids:" +e);
				}

				//CR1455 - end

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

	private CatalogService convertService(CatalogServiceFullTypeImpl serviceType) {
		final CatalogServiceImpl catalogService_ = new CatalogServiceImpl((serviceType.getId() != null?serviceType.getId():""),
				(serviceType.getName() != null?serviceType.getName():""),
				(serviceType.getDescription() != null?serviceType.getDescription():""));
		PaymentContentFullType pc = serviceType.getPaymentContent();
		if (pc!=null)
			catalogService_.setPaymentContent(convertPaymentContent(pc));
		catalogService_.setProvisioningTag((serviceType.getProvisioningTag() != null?serviceType.getProvisioningTag():""));
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


			DRMType drmType_ = DRMType.NOT_DRM;

			try
			{
				if(packType.getDrmType().getName() != null && !packType.getDrmType().getName().equalsIgnoreCase(""))
					drmType_ = DRMType.convertStringToDRMType(packType.getDrmType().getName());
			}
			catch(final Exception e)
			{
				logger.error("Problem converting DRMType will set NOT_DRM:" + e);
				drmType_ = DRMType.NOT_DRM;
			}

			cataloPackageImpl_.setDRMType(drmType_);

			cataloPackageImpl_.setDynamicDefault(packType.isIsDynamicDefault());

			final PricePointLittleType pricePointType_ = packType.getPricePoint();

			PricePointImpl singlePricePoint_= null;

			if(pricePointType_ != null && pricePointType_.getId() != null && pricePointType_.getId().trim().length()>0)
			{
				singlePricePoint_ = this.convertLittlePricePointType(pricePointType_);
				cataloPackageImpl_.setPricePoint(singlePricePoint_);
			}
			//CR1455 - end

			final com.vodafone.global.er.decoupling.binding.response.CatalogPackageLittleType.PricePointsType pps_ = packType.getPricePoints();

			if(pps_ != null)
			{
				final List<PricePointLittleType> points_ = pps_.getPricePoint();
				if(points_ != null)
				{
					final HashMap<String, PricePoint> map_ = new HashMap<String, PricePoint>();
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
					try
					{
						if(logger.isDebugEnabled())
							logger.debug("Setting single pricepoint as map");
						final HashMap<String, PricePoint> map_ = new HashMap<String, PricePoint>();
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

	public CatalogPackage[] buildPackages(PackagesType packType) 
	{
		if(logger.isDebugEnabled())
			logger.debug("CatalogPackage[] buildPackages(PackagesType packType)");

		final List<CatalogPackage> packsToReturn = new ArrayList<CatalogPackage>();
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
			com.vodafone.global.er.decoupling.binding.response.FindPackagesWithServiceResponseType.PackagesType packages) {
			final List<CatalogPackage> packsToReturn = new ArrayList<CatalogPackage>();
		
			for (final PackageType pack: (List<PackageType>) packages.getPackage())	{
			
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

		final com.vodafone.global.er.decoupling.binding.response.ErServiceFullType.PricePointsType pps_ = serviceType.getPricePoints();
		final List<PricePointFullType> points_ = pps_.getPricePoint();
		final HashMap<String, PricePoint> map_ = new HashMap<String, PricePoint>();
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
			//PricePoint ppType = (PricePoint)ppFullType
			final double rate = ppType.getRate();
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

				final BalanceImpactRatesType balanceImpactsType_ =  ppType.getBalanceImpactRates();
				if(balanceImpactsType_ != null)
				{
					if(logger.isDebugEnabled())
						logger.debug("BalanceImpacts exist");

					final List<BalanceImpactRateType> balanceImpactsType =  balanceImpactsType_.getBalanceImpactRate();
					final BalanceImpacts balanceImpacts_ = new BalanceImpacts();
					for (final BalanceImpactRateType balanceImpactType_ : balanceImpactsType)
					{
						final BalanceImpact balanceImpact_ = new BalanceImpact(new ChargingResource(new Integer(balanceImpactType_.getChargingResourceCode()), ""), balanceImpactType_.getRate());
						balanceImpacts_.addBalanceImpact(balanceImpact_);
					}
					pricePointImpl_.setBalanceImpacts(balanceImpacts_, new Date());
				}

//				try
//				{
//					final DrmObjectType drmObjectType_ = ppType.getDrmObject();
//
//					if(drmObjectType_ != null)
//					{
//						if(_log.isDebugEnabled())
//							_log.debug("DrmObjectType exist");
//						pricePointImpl_.setDRMObject(this.convertDRMObjectType(drmObjectType_));
//					}
//				}
//				catch(final Exception e)
//				{
//					_log.error("Problem converting DRMObject:" + e);
//				}
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
				final NetworkCode networkCode_ = new NetworkCode();
				if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DOMESTIC]))
					networkCode_.setRoamingType(ErCoreConst.ROAMING_DOMESTIC);
				else if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID]))
					networkCode_.setRoamingType(ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID);
				else if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_OFF_FOOTPRINT]))
					networkCode_.setRoamingType(ErCoreConst.ROAMING_OFF_FOOTPRINT);
				else if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_ON_FOOTPRINT]))
					networkCode_.setRoamingType(ErCoreConst.ROAMING_ON_FOOTPRINT);

				//pricePointImpl_.setUserGroups(new String[]{"*"});

				try	{
					if(ppType.getUserGroups() != null && ppType.getUserGroups().getUserGroup() != null)	{
						//a pricepoint can only have ONE usergroup
						String fromUGArray = (String)ppType.getUserGroups().getUserGroup().get(0);						
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

				final PricePointFullType.BalanceImpactsType balanceImpacts_ = ppType.getBalanceImpacts();
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



//				try
//				{
//					final DrmObjectType drmObjectType_ = ppType.getDrmObject();
//
//					if(drmObjectType_ != null)
//					{
//						if(_log.isDebugEnabled())
//							_log.debug("DrmObjectType exist");
//						pricePointImpl_.setDRMObject(this.convertDRMObjectType(drmObjectType_));
//					}
//				}
//				catch(final Exception e)
//				{
//					_log.error("Problem converting DRMObject:" + e);
//				}
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


//				final NetworkCode networkCode_ = new NetworkCode();
//				if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DOMESTIC]))
//					networkCode_.setRoamingType(ErCoreConst.ROAMING_DOMESTIC);
//				else if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID]))
//					networkCode_.setRoamingType(ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID);
//				else if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_OFF_FOOTPRINT]))
//					networkCode_.setRoamingType(ErCoreConst.ROAMING_OFF_FOOTPRINT);
//				else if(ppType.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_ON_FOOTPRINT]))
//					networkCode_.setRoamingType(ErCoreConst.ROAMING_ON_FOOTPRINT);

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
						String fromUGArray = (String)ppType.getUserGroups().getUserGroup().get(0);

						if (!fromUGArray.equals(pricePointImpl_.getUserGroup()))
							logger.warn("different user groups in response: {} and {}", fromUGArray, ppType.getUserGroup());
						
						if (StringUtils.isNotBlank(fromUGArray))
							pricePointImpl_.setUserGroup(fromUGArray);
					}
				}	catch(final Exception e)	{
					logger.error("Problem setting User Groups:" + e);
				}
				//TODO remove this supercredit stuff
				pricePointImpl_.setIsPurchaseableBySuperCredit(ppType.isIsPurchaseableBySuperCredit());

				final PricePointFullType.CreditPurchasePricePointsType creditPurchasePricePointsType_ = ppType.getCreditPurchasePricePoints();
				if(creditPurchasePricePointsType_ != null)
				{
					final List<SuperCreditPricePointFullType> creditPurchasePricePointTypes_ = creditPurchasePricePointsType_.getCreditPurchasePricePoint();
					final SuperCreditPricePoint[] creditPurchasePricePoints_ = new SuperCreditPricePoint[creditPurchasePricePointTypes_.size()];
					if(creditPurchasePricePointTypes_ != null)
					{
						logger.debug("creditPurchasePricePointTypes_.size():{}" , creditPurchasePricePointTypes_.size());
						int i=0;
						for (final SuperCreditPricePointFullType creditPurchasePricePointType_ : creditPurchasePricePointTypes_)
						{
							if(creditPurchasePricePointType_ != null)
								creditPurchasePricePoints_[i++] = convertFullSuperCreditPricePointType(creditPurchasePricePointType_);          
						}
						pricePointImpl_.setCreditPurchasePricePoints(creditPurchasePricePoints_);
						logger.debug("pricePointImpl_.getCreditPurchasePricePoints(): {}", pricePointImpl_.getCreditPurchasePricePoints().length);
					}
				}
				pricePointImpl_.setIsHistoric(ppType.isIsHistoric());
				try
				{
					final PricePointTiersType tiersType_ = ppType.getPricePointTiers();
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
				else
					pricePointImpl_.setChannel(Constants.INT_MATCH_ALL);
				
				pricePointImpl_.setTariff(getTariffFromPricePointId(pricePointImpl_.getId()));
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
								userGroups_[i] = (String)ppType.getUserGroups().getUserGroup().get(i);          
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
	
	public SuperCreditPricePoint convertSuperCreditPricePointType(SuperCreditPricePointType ppType)
	{
		if(logger.isDebugEnabled())
			logger.debug("SuperCreditPricePoint convertSuperCreditPricePointType(SuperCreditPricePointType ppType)");

		try
		{
			final String promoCode    = ppType.getPromoCode();
			final RatedEvent ratedEvent = new RatedEvent();
			ratedEvent.setNetRate(ppType.getNetRate());
			ratedEvent.setNetStandardRate(ppType.getStandardRate());
			final SuperCreditPricePoint pp = new SuperCreditPricePoint();
			final String[] pm = new String[1];
			pm[0] = promoCode;
			pp.setPricingText1(ppType.getPricingText1());
			pp.setPricingText1(ppType.getPricingText2());
			pp.setPromoCodes(pm);
			pp.setPromoCodes( new String [] {ppType.getPromoCode()} );
			pp.setPricingText1(ppType.getPricingText1());
			pp.setPricingText2(ppType.getPricingText2());
			pp.setTranslatedPricingText((ppType.getPurchaseLinkText() != null?ppType.getPurchaseLinkText():""));
			pp.setRoamingGrossAmount(ppType.getRoamingGrossAmount());
			pp.setRoamingNetAmount(ppType.getRoamingNetAmount());
			return pp;
		}
		catch(final Exception e)
		{
			logger.error("Problem converting values in Pricepoint");
			return null;
		}
	}

	public DRMObject convertDRMObjectType(DrmObjectType drmObjectType)
	{
		if(logger.isDebugEnabled())
			logger.debug("DRMObject convertDRMObjectType(DrmObjectType drmObjectType)");

		try
		{
			final DrmTypeType drmTypeType_ = drmObjectType.getDrmType();
			DRMType drmType_ = DRMType.NOT_DRM;
			if(drmTypeType_.getName() != null && !drmTypeType_.getName().equalsIgnoreCase(""))
			{
				drmType_ = DRMType.convertStringToDRMType(drmTypeType_.getName());
			}
			//CR1455 - start
			String description_ = null;
			if(drmObjectType.getDescription() != null && drmObjectType.getDescription().length() > 0)
				description_ = drmObjectType.getDescription();
			return new DRMObject(drmObjectType.getId(), description_, drmType_);  
			//CR1455 - end
		}
		catch(final Exception e)
		{
			logger.error("Problem converting values in DRMObject");
			return null;
		}
	}

	public DRMObject convertDRMObjectFullType(DrmObjectFullType drmObjectType)
	{
		if(logger.isDebugEnabled())
			logger.debug("DRMObject convertDRMObjectFullType(DrmObjectFullType drmObjectType)");

		try
		{
			final DrmTypeFullType drmTypeType_ = drmObjectType.getDrmType();
			DRMType drmType_ = DRMType.NOT_DRM;
			if(drmTypeType_.getName() != null && !drmTypeType_.getName().equalsIgnoreCase(""))
			{
				drmType_ = DRMType.convertStringToDRMType(drmTypeType_.getName());
			}
			//CR1455 - start
			String description_ = null;
			if(drmObjectType.getDescription() != null && drmObjectType.getDescription().length() > 0)
				description_ = drmObjectType.getDescription();
			return new DRMObject(drmObjectType.getId(), description_, drmType_);  
			//CR1455 - end
		}
		catch(final Exception e)
		{
			logger.error("Problem converting values in DRMObject");
			return null;
		}
	}

	public SuperCreditPricePoint convertFullSuperCreditPricePointType(SuperCreditPricePointFullType ppType)
	{
		if(logger.isDebugEnabled())
			logger.debug("SuperCreditPricePoint convertFullSuperCreditPricePointType(SuperCreditPricePointFullType ppType)");

		try
		{
			final RatedEvent ratedEvent = new RatedEvent();
			ratedEvent.setNetRate(ppType.getNetRate());
			ratedEvent.setNetStandardRate(ppType.getStandardRate());
			final SuperCreditPricePointImpl pp = new SuperCreditPricePointImpl();
			//CR1455 - start
			pp.setId((ppType.getId() != null?ppType.getId():null));
			pp.setNetRate(ppType.getNetRate());
			pp.setTaxRate(ppType.getTaxRate());
			pp.setDRMObject((ppType.getDrmObject() != null?convertDRMObjectFullType(ppType.getDrmObject()):null));
			pp.setActive(ppType.isIsActive());
			pp.setBearer((ppType.getBearerId() != null?ppType.getBearerId():null));
			pp.setIsHistoric(ppType.isIsHistoric());
			//CR1455 - end
			pp.setPricingText1(ppType.getPricingText1());
			pp.setPricingText1(ppType.getPricingText2());
			pp.setPromoCodes( new String [] {ppType.getPromoCode()} );
			pp.setPricingText1(ppType.getPricingText1());
			pp.setPricingText2(ppType.getPricingText2());
			pp.setTranslatedPricingText((ppType.getPurchaseLinkText() != null?ppType.getPurchaseLinkText():""));
			pp.setRoamingGrossAmount(ppType.getRoamingGrossAmount());
			pp.setRoamingNetAmount(ppType.getRoamingNetAmount());
			return pp;
		}
		catch(final Exception e)
		{
			logger.error("Problem converting values in SuperCreditPricePoint");
			return null;
		}
	}

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
			//transaction_.setTransactionId((transactionType_.getId() != null?transactionType_.getId():""));
			transaction_.setStandardRate(transactionType_.getStandardRate());
			transaction_.setClientNetworkCode(transactionType_.getNetworkCode());
			try	{
				transaction_.setNetworkCodeId((transactionType_.getNetworkCode() != null?new Integer(transactionType_.getNetworkCode()):-1));
			} catch(final Exception e)	{	//eg getNetworkCode returns "234-04" which can't be parsed to an integer...
				logger.warn("Problem converting NetworkCodeId {}: {}", transactionType_.getNetworkCode(), e.getMessage());
			}
//			try
//			{
				transaction_.setTransactionIdLong(Long.parseLong(transactionType_.getId().trim()));
//			}
//			catch(final Exception e)
//			{
//				_log.debug("Problem converting TransactionIdLong");
//			}
			transaction_.setPurchaseDate((transactionType_.getPurchaseDate() != null?transactionType_.getPurchaseDate().getTime():null));
			transaction_.setPurchaseRate(transactionType_.getPurchaseRate());
			//CR1455 - start
			transaction_.setTaxRate(transactionType_.getTaxRate());
			//CR1455 - end
//			transaction_.setRoamingGrossAmount(transactionType_.getRoamingAmount());
//			transaction_.setRoamingNetAmount(transactionType_.getRoamingAmount()-transactionType_.getRoamingTaxAmount());
			try	{
				transaction_.setSubscription((transactionType_.getSubscription() != null?convertSubscriptionType(transactionType_.getSubscription()):null));
				if(transaction_.getSubscription() != null)
					transaction_.setSubscriptionId((transaction_.getSubscription().getSubscriptionId() != null?transaction_.getSubscription().getSubscriptionId():""));
			}	catch (final Exception e)	{
				logger.warn("Problem converting Subscription {}", e.getMessage());
			}

			try	{
				transaction_.setPurchaseCurrency((transactionType_.getPurchaseCurrency() != null?convertChargingResourceFullType(transactionType_.getPurchaseCurrency()):null));
			}	catch(final Exception e)	{
				logger.warn ("Problem converting ChargingResourceType {}: {}", transactionType_.getPurchaseCurrency(), e.getMessage());
			}
			transaction_.setSuperCreditId((transactionType_.getSuperCreditId() != null?transactionType_.getSuperCreditId():""));
			//transaction_.setLocalPurchaseDate((transactionType_.getLocalDateTime() != null?transactionType_.getLocalDateTime().getTime():null));
			//CR1564 : Change the type to Calendar
			//transaction_.setLocalPurchaseDate((transactionType_.getLocalDateTime() != null?transactionType_.getLocalDateTime():null));
			transaction_.setLocalPurchaseDate((transactionType_.getLocalDateTime() != null?transactionType_.getLocalDateTime().getTime():null));

//			if(transactionType_.getNetworkCode() != null)
//			{
//				final NetworkCode networkCode_ = new NetworkCode();
//
//				if(transactionType_.getNetworkCode().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DOMESTIC]))
//					networkCode_.setRoamingType(ErCoreConst.ROAMING_DOMESTIC);
//				else if(transactionType_.getNetworkCode().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID]))
//					networkCode_.setRoamingType(ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID);
//				else if(transactionType_.getNetworkCode().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_OFF_FOOTPRINT]))
//					networkCode_.setRoamingType(ErCoreConst.ROAMING_OFF_FOOTPRINT);
//				else if(transactionType_.getNetworkCode().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_ON_FOOTPRINT]))
//					networkCode_.setRoamingType(ErCoreConst.ROAMING_ON_FOOTPRINT);
//
//				transaction_.setNetworkCodeId(networkCode_.getId());
//			}

			transaction_.setPaymentType(transactionType_.getPaymentType());
			transaction_.setPaymentStatus(transactionType_.getPaymentStatus());
			transaction_.setStatus(transactionType_.getStatus());
			
			transaction_.setSessionId(transactionType_.getSessionId());
			//CR1455 - start
			if(transactionType_.getParentTransactionId()!= null && transactionType_.getParentTransactionId().length() > 0)
				transaction_.setParentTransactionId(transactionType_.getParentTransactionId());
			else
				transaction_.setParentTransactionId(null);

			transaction_.setServiceId(transactionType_.getServiceId());
			//CR1455 - end
//			if(transactionType_.getDrmObject() != null)	{ 
//				transaction_.setDRMObject(convertDRMObjectFullType(transactionType_.getDrmObject()));
//			}

			//Needed since null is checked
			if(transactionType_.getRecipientMsisdn() != null && transactionType_.getRecipientMsisdn().trim().length()>0)
				transaction_.setReceipientMsisdn(transactionType_.getRecipientMsisdn());
			else
				transaction_.setReceipientMsisdn(null);

			//TODO remove this roaming crap
			try	{
				if(transactionType_.getRoamingType() != null)
				{
					if(transactionType_.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DOMESTIC]))
						transaction_.setRoamingTypeId(ErCoreConst.ROAMING_DOMESTIC);
					else if(transactionType_.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID]))
						transaction_.setRoamingTypeId(ErCoreConst.ROAMING_DUMMY_NETWORK_CODE_ID);
					else if(transactionType_.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_OFF_FOOTPRINT]))
						transaction_.setRoamingTypeId(ErCoreConst.ROAMING_OFF_FOOTPRINT);
					else if(transactionType_.getRoamingType().equals(ErCoreConst.ROAMING_TYPE_STR[ErCoreConst.ROAMING_ON_FOOTPRINT]))
						transaction_.setRoamingTypeId(ErCoreConst.ROAMING_ON_FOOTPRINT);
				}
			}	catch(final Exception e)	{
				logger.error("problem converting RoamingType:" + e);
			}

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
			transaction_.setClientNetworkCode(transactionType_.getClientNetworkCode());
			transaction_.setExternalTransId(transactionType_.getExternalTransId());
			transaction_.setExternalField1(transactionType_.getExternalField1());
			transaction_.setExternalField2(transactionType_.getExternalField2());
			transaction_.setAffiliateID(transactionType_.getAffiliateId());
			transaction_.setProductCode(transactionType_.getProductCode());
			transaction_.setRateIdentifier(transactionType_.getRateIdentifier());
			transaction_.setPaymentInfo(transactionType_.getPaymentInfo());

			/*
			 * arrTrans[0].getContentCategory()
			 * 
			 */
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
		final List<SelfcareFullTransactionType> trans = transType.getTransaction();
		final ListIterator<SelfcareFullTransactionType> it = trans.listIterator();
		for(;it.hasNext();)
		{
			final Object o = it.next();
			final TransactionFullType tempType = (TransactionFullType)o;
			listToReturn.add(convertTransaction(tempType));
		}
		return listToReturn.toArray(new Transaction[trans.size()]);
	}


	public BaseAuthorization convertNextPaymentAmountType(NextPaymentAmountType payload)
	{
		if(logger.isDebugEnabled())
			logger.debug("BaseAuthorization convertNextPaymentAmountType(NextPaymentAmountType payload)");

		BaseAuthorization bauth = null;
		final CatalogPackage pack = new CatalogPackage();
		final PricePointImpl pp = new PricePointImpl();
		if (payload != null && payload instanceof NextPaymentAmountType)
		{
			bauth = new BaseAuthorization();
			bauth.setIsSuccess(true);
			//			bauth.setRate(payload.getRate());
			bauth.setNetRate(payload.getNetRate());
			bauth.setTaxAmount(payload.getTaxAmount());
			//??
			//pp.setRateWithoutTax(payload.getNetRate());
			pp.setRateWithoutTax(payload.getRate());
			pack.setPricePoint(pp);//TODO Check this.
			bauth.setPackage(pack);
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
					userGroups_[i] = (String)ratingAttributesFullType.getUserGroups().getUserGroup().get(i);          
				}
				ratingAtts.setUserGroups(userGroups_);
			}
		}

		return ratingAtts;

	}

	public Duration convertDuration(DurationType durationType) throws EcommerceException 
	{
		if(logger.isDebugEnabled())
			logger.debug("Duration convertDuration(DurationType durationType) throws EcommerceException");

		return new Duration(durationType.getDurationCode());
	}

	public PurchaseAuthorization convertRenewPurchaseAuthObj(RenewPurchaseAuthorisationType auth)
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
			final List<ResourceBalanceTypeImpl> userResourceBalancesList_ = auth.getUserResourceBalance();
			ResourceBalance resourceBalance_ = null;
			int i=0;
			if(userResourceBalancesList_ != null)
			{
				resourceBalance_ = new ResourceBalance();
				for (final ResourceBalanceTypeImpl resourceBalanceType_ : userResourceBalancesList_)
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

	public PurchaseAuthorization convertPurchaseAuthObj(UsageAuthorisationType auth)
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

	public Tier[] buildTiers(TiersType tiersType)
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


	public PromotionsResult buildPromotionsObject(CheckPromotionsResponseType promoType){
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
		
		return auth;
	}

	public UsageAuthorization buildUsageAuthObj(UsageAuthorisationType authType){
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
		}
		if (authType.getUserResourceBalance() != null && authType.getUserResourceBalance().getSubscriptionId() != null
				&& ! "".equals(authType.getUserResourceBalance().getSubscriptionId())){
			auth.setUserResourceBalance(convertResourceBalance((ResourceBalanceTypeImpl)authType.getUserResourceBalance()));
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
//			auth.setReasonCode(ReasonCode.getReasonCode(
//					authType.getReasonCode().getCode(),
//					authType.getReasonCode().getSubCode(),
//					authType.getReasonCode().getName()));
			auth.setReasonCode(convertReasonCode(authType.getReasonCode()));
		}

		if (authType.getSubReasonCode() != null) {
//			auth.setSubReasonCode(ReasonCode.getReasonCode(
//					authType.getSubReasonCode().getCode(),
//					authType.getSubReasonCode().getSubCode(),
//					authType.getSubReasonCode().getName()));
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
			GoodwillCreditResponseType payload) {

		final GoodwillCreditAuthorization auth = new GoodwillCreditAuthorization();
		auth.setIsSuccess(payload.isIsSuccess());
		auth.setTransactionId(auth.getTransactionId());
		//auth.setMsisdn(payload.getMsisdn());
		auth.setPackageSubscriptionId(payload.getSubscriptionId());
		//can't do this - auth.setReasonCode(new ReasonCode(payload.getReasonCode().getCode()));
		//auth.getReasonCode().setName(payload.getReasonCode().getName());
		auth.setReasonCode(convertReasonCode(payload.getReasonCode()));
		return auth;

	}

	public PurchaseAuthorization buildRenewPurchaseAuthObj(RenewPurchaseAuthorisationType auth){
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
			purAttrType = new PurchaseAttributesTypeImpl();
			
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
				final ResourceBalancesOnlyType resourceBalancesOnlyType = new ResourceBalancesOnlyTypeImpl(); 
				for (final ResourceBalanceOnly element : resourceBalanceOnly) {
					final ResourceBalanceOnlyTypeImpl resourceBalanceOnlyTypeImpl = new ResourceBalanceOnlyTypeImpl();
					resourceBalanceOnlyTypeImpl.setResourceId(element.getResourceId());
					resourceBalanceOnlyTypeImpl.setBalance(element.getBalance());
					resourceBalancesOnlyType.getResourceBalanceOnly().add(resourceBalanceOnlyTypeImpl);
				}
				purAttrType.setResourceBalancesOnly(resourceBalancesOnlyType);
			}		
			//CR2241start
//			BeanUtils.copyProperties(purchaseAttributes, purAttrType);
//				if (purchaseAttributes.getAccessChannel() != null){
//					purAttrType.setAccessChannel(purchaseAttributes.getAccessChannel());
//				}
//				if (purchaseAttributes.getAffiliateID() != null){
//					purAttrType.setAffiliateId(purchaseAttributes.getAffiliateID());
//				}
//				if (purchaseAttributes.getAggregatorId() != null){
//					purAttrType.setAggregatorId(purchaseAttributes.getAggregatorId());
//				}
//				if (purchaseAttributes.getAssetID() != null){
//					purAttrType.setAssetId(purchaseAttributes.getAssetID());
//				}
//				
//				purAttrType.setIsBatchProcessor(purchaseAttributes.isBatchProcessor());
//				
//				if (purchaseAttributes.getBearer() != null){
//					purAttrType.setBearer(purchaseAttributes.getBearer());
//				}
//				purAttrType.setChargingMethod(purchaseAttributes.getChargingMethod());
//	
//				if (purchaseAttributes.getContentName() != null){
//					purAttrType.setContentName(purchaseAttributes.getContentName());
//				}
//				if (purchaseAttributes.getCsrId() != null){
//					purAttrType.setCsrId(purchaseAttributes.getCsrId());
//				}
//				if (purchaseAttributes.getDeviceID() != null){
//					purAttrType.setDeviceId(purchaseAttributes.getDeviceID());
//				}
//				
//				//TODO: duration type may be necessary
//	//			DurationType dt = (DurationType) new DurationTypeImpl();
//	//			dt.setDurationCode(purchaseAttributes.getDuration());
//	
//				if (purchaseAttributes.getExternalTransId() != null){
//					purAttrType.setExternalTransId(purchaseAttributes.getExternalTransId());
//				}
//				if (purchaseAttributes.getExternalField1() != null){
//					purAttrType.setExternalField1(purchaseAttributes.getExternalField1());
//				}
//				if (purchaseAttributes.getExternalField2() != null){
//					purAttrType.setExternalField2(purchaseAttributes.getExternalField2());
//				}
//				purAttrType.setHasActiveSubscriptions(purchaseAttributes.hasActiveSubscriptions());
//	
//				//TODO: look into how to implement this one correctly?
//				if (purchaseAttributes.getActiveSubscriptions() != null && purchaseAttributes.getActiveSubscriptions().size() == 1){
//					purAttrType.setActiveSubscriptions(purchaseAttributes.getActiveSubscriptions().get(0).getSubscriptionId());
//				}
//				if (purchaseAttributes.getNetworkCodeStr() != null){
//					purAttrType.setNetworkCodeString(purchaseAttributes.getNetworkCodeStr());
//				}
//				if (purchaseAttributes.getPartnerId() != null){
//					purAttrType.setPartnerId(purchaseAttributes.getPartnerId());
//				}
//				if (purchaseAttributes.getPaymentInformation() != null){
//					purAttrType.setPaymentInformation(purchaseAttributes.getPaymentInformation());
//				}
//					
//				purAttrType.setPaymentType(purchaseAttributes.getPaymentType());
//	
//				purAttrType.setPreRate(purchaseAttributes.getPreRate());
//	
//				if (purchaseAttributes.getPurchaseChannel() != null){
//					purAttrType.setPurchaseChannel(purchaseAttributes.getPurchaseChannel());
//				}
//				
//				purAttrType.setIsPromotional(purchaseAttributes.isPromotional());
//	
//				if (purchaseAttributes.getPromotionalExpiryDate() != null){
//					Calendar cal1 = Calendar.getInstance();
//					cal1.setTime(purchaseAttributes.getPromotionalExpiryDate());
//					purAttrType.setPromotionalExpiryDate(cal1);
//				}
//				if (purchaseAttributes.getPromoCode() != null){
//					purAttrType.setPromoCode(purchaseAttributes.getPromoCode());
//				}
//				if (purchaseAttributes.getShortPackageId() != null){
//					purAttrType.setShortPackageId(purchaseAttributes.getShortPackageId());
//				}
				
				//might be some more to be set (also add to pa_common.xsd)
						//CR2241end
		}
		return purAttrType;
	}


	public SubscriptionAttributesType buildSubscriptionAttributes(
			SubscriptionAttributes subscriptionAttributes) {
		SubscriptionAttributesType rv = new SubscriptionAttributesTypeImpl();
		
		if (subscriptionAttributes.getEndDate()!=null)
			rv.setEndDate(DateTimeUtil.convertDateToCal(subscriptionAttributes.getEndDate()));
		
		int status = subscriptionAttributes.getStatus();
		if (status!=Constants.INT_NOT_SET)
			rv.setStatus(status);
		return rv;
	}


	public ExpressDisplayAttributesType convertExpressDisplayAttributes(
			ExpressDisplayAttribute expressAttribute, String[] serviceIds) {
		ExpressDisplayAttributesType rv = new com.vodafone.global.er.decoupling.binding.request.impl.ExpressPackageRequestTypeImpl.ExpressDisplayAttributesTypeImpl();
		rv.setIsHeadline(expressAttribute.isHeadline());
		rv.setIsOption(expressAttribute.isOption());
		ExpressDisplayAttributesType.TrueOptionsType options = new ExpressPackageRequestTypeImpl.ExpressDisplayAttributesTypeImpl.TrueOptionsTypeImpl();
		for (String service: serviceIds)	{
			options.getServiceId().add(service);
		}
		rv.setTrueOptions(options);
		return rv;
	}


	public ServiceIdsType convertServices(String[] serviceId) {
		ServiceIdsType  rv = new  com.vodafone.global.er.decoupling.binding.request.impl.ExpressPackageRequestTypeImpl.ServiceIdsTypeImpl();
		rv.getServiceId().addAll(Arrays.asList(serviceId));
		return rv;
	}


	public ExpressData convertExpressData(ExpressPackageType express) {
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
		if (validate.isIsChargeable())
			rv.setStatus(ResponseStatus.ACCEPTED.getName());
		return rv;
	}
	
	
//	/**
//	 * @param pptId
//	 * @return
//	 */
//	public int getChannelFromPricePointId(String pptId)	{
//		//eg from package:PK_CBS_TAX_3_1030_999_999_5_*_*_*_false_false_*
//		//get the 5
//		int rv = Constants.INT_MATCH_ALL;
//		try	{
//			int numUnderscores = StringUtils.countMatches(pptId, "_");
//			int firstChannelUnderscore = numUnderscores-6;
//			int startOfChannel = StringUtils.ordinalIndexOf(pptId, "_", firstChannelUnderscore);
//			int endOfChannel = StringUtils.ordinalIndexOf(pptId, "_", firstChannelUnderscore+1);
//			String channel = pptId.substring(startOfChannel+1, endOfChannel);
//			rv = Integer.valueOf(channel);
//		} catch (Exception e)	{
//			logger.error("problem calculating channel for pricepoint {}", pptId);
//		}
//		return rv;
//	}

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
 		RatingAttributesType ratingAtt = new RatingAttributesTypeImpl();
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
 		ratingAtt.setNetworkCodeString(usageAttributes.getNetworkCodeStr());
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
 
 		if (isNotBlank(usageAttributes.getMerchantName()))
 			ratingAtt.setMerchantName(usageAttributes.getMerchantName());
 		if (isNotBlank(usageAttributes.getInvoiceText()))
 			ratingAtt.setInvoiceText(usageAttributes.getInvoiceText());
 		return ratingAtt;
 	}
 
 	
 	/**
 	 * create rating attributes request xml object from purchase attributes (eg for a purchase call)
 	 * @param purchaseAttributes
 	 * @return
 	 */
 	public RatingAttributesType buildRatingAttributes(PurchaseAttributes purchaseAttributes) {
 
 		if (purchaseAttributes == null)
 			return null;
 		RatingAttributesType ratingAtt = new RatingAttributesTypeImpl();
 		ratingAtt.setPaymentInformation(purchaseAttributes.getPaymentInformation());
 		if(purchaseAttributes.getShortPackageId() != null) {
 			ratingAtt.setShortPackageId(purchaseAttributes.getShortPackageId());
 		}
 		ratingAtt.setExternalTransId(purchaseAttributes.getExternalTransId());
 		ratingAtt.setPartnerId(purchaseAttributes.getPartnerId());
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
 		if(purchaseAttributes.getContentName() != null) {
 			ratingAtt.setContentName(purchaseAttributes.getContentName());
 		}
 		if(purchaseAttributes.getMerchantName() != null) {
 			ratingAtt.setMerchantName(purchaseAttributes.getMerchantName());
 		}
 		if(purchaseAttributes.getInvoiceText() != null) {
 			ratingAtt.setInvoiceText(purchaseAttributes.getInvoiceText());
 		}
 		if (purchaseAttributes.getTaxRate()>0) {
 			ratingAtt.setTaxRate(purchaseAttributes.getTaxRate());
 			ratingAtt.setHasTaxRate(true);
 		}
 		if (purchaseAttributes.getCsrId() !=null){
 			ratingAtt.setCsrId(purchaseAttributes.getCsrId());
 		}
 		//CR2255 Phase2 - add partner information
 		if (isNotBlank(purchaseAttributes.getPartnerId())) {
 			if (isNotBlank(purchaseAttributes.getPartnerUrl())) {
 				ratingAtt.setPartnerUrl(purchaseAttributes.getPartnerUrl());
 			}
 			if (isNotBlank(purchaseAttributes.getPartnerContactInfo())) {
 				ratingAtt.setPartnerContactInfo(purchaseAttributes.getPartnerContactInfo());
 			}
 			if (isNotBlank(purchaseAttributes.getPartnerEmail())) {
 				ratingAtt.setPartnerEmail(purchaseAttributes.getPartnerEmail());
 			}
 		}
 		return ratingAtt;
 	}
}
