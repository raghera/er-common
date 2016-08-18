package com.vodafone.global.er.decoupling.client;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.*;
import com.vizzavi.ecommerce.business.charging.PurchaseAttributes;
import com.vizzavi.ecommerce.business.common.Constants;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.PromotionData;
import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.common.EcommerceRuntimeException;
import com.vizzavi.ecommerce.common.Utils;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.business.catalog.BasePrice;
import com.vodafone.global.er.decoupling.PayloadConstants;
import com.vodafone.global.er.decoupling.binding.request.*;
import com.vodafone.global.er.decoupling.binding.request.RatingAttributesType;
import com.vodafone.global.er.decoupling.binding.request.impl.ExpressPackageRequestImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.FindPackagesWithServiceRequestImpl;
import com.vodafone.global.er.decoupling.binding.request.impl.RatingAttributesTypeImpl;
import com.vodafone.global.er.decoupling.binding.response.*;
import com.vodafone.global.er.decoupling.binding.response.BasicServiceType;
import com.vodafone.global.er.decoupling.binding.response.OnestepPackagesType.OnestepPackageType;
import com.vodafone.global.er.decoupling.binding.response.BasicPackageType;

class CatalogApiDecouplingImpl extends BaseErApiDecouplingImpl implements CatalogApi {

	private static final Logger logger = LoggerFactory.getLogger(CatalogApiDecouplingImpl.class);

	private static final String date= "$Date: 2013/10/31 12:41:46 $";
	private static final String version="$Revision: 1.18 $";

	static final boolean sortByPackageName=ConfigProvider.getPropertyAsBoolean("er.decoupling.catalog.sort.package.name", true);

	public CatalogApiDecouplingImpl(Locale locale, String clientId)	{
		super(locale, clientId);
	}

	
	@Override
	public CatalogPackage[] findPackagesWithService(CatalogService catalogService) throws EcommerceRuntimeException
	{
		return findPackagesWithService(null, catalogService, null);
	}

	@Override
	public CatalogPackage getPackage(String packageId) throws EcommerceRuntimeException
	{
		checkNullParams(packageId);
		
		final CatalogFullPackageRequest request=createRequest(PayloadConstants.CATALOG_FULL_PACKAGE_REQUEST_PAYLOAD);
		request.setId(packageId);
		try {
			CatalogFullPackage packageResponse=sendRequestAndGetResponse(PayloadConstants.CATALOG_FULL_PACKAGE_REQUEST_PAYLOAD, request, CatalogFullPackage.class);
			return converter.convertFullPackageType(packageResponse.getPackage());
		} catch (EcommerceException e) {
			throw new EcommerceRuntimeException(e);
		}
		
		
	}


	/**
	 * {@inheritDoc}<br/>
	 * <b>NB the CatalogPackage objects in the array only have package name and package id set.</b><br/>
	 * Use {@link #getPackage(String)} to get the fully populated package.
	 */
	@Override
	public CatalogPackage[] getPackages() throws EcommerceRuntimeException
	{
	
		GetPackagesResponse packageResponse;
		final GetPackagesRequest request = createRequest(PayloadConstants.GET_PACKAGES_REQUEST_PAYLOAD);
		try	{
			packageResponse= sendRequestAndGetResponse(PayloadConstants.GET_PACKAGES_REQUEST_PAYLOAD, request, GetPackagesResponse.class);
			
		}	catch (EcommerceException e) {
			throw new EcommerceRuntimeException(e);
		}
		final List<BasicPackageType> packagesList = packageResponse.getPackage();
		//MQC9018 - sort by package name by default
		//unless config specifies to sort by package id
		Collections.sort(packagesList, new Comparator<BasicPackageType>(){
			@Override
			public int compare(BasicPackageType arg0, BasicPackageType arg1) {
				if (sortByPackageName)
					return arg0.getPackageName().compareTo(arg1.getPackageName());
				else
					return arg0.getPackageId().compareTo(arg1.getPackageId());
			}
		});
		final CatalogPackage[] packages = new CatalogPackage[packagesList.size()];
		int i = 0;
		for (final BasicPackageType packageType_ : packagesList)	{
			packages[i++] = new CatalogPackage(packageType_.getPackageId(), packageType_.getPackageName());
		}

		return packages;
		
	}

	@Override
	public CatalogService getService(String serviceId)
	{
		checkNullParams(serviceId);
		try
		{
			final CatalogFullServiceRequest request = createRequest(PayloadConstants.CATALOG_FULL_SERVICE_REQUEST_PAYLOAD);
			request.setServiceId(serviceId);
			final CatalogFullService serviceResponse_ =
					sendRequestAndGetResponse(PayloadConstants.CATALOG_FULL_SERVICE_REQUEST_PAYLOAD, request, CatalogFullService.class);
			return converter.convertService(serviceResponse_.getService());

		}	catch(final EcommerceException e)	{
			logger.error("Problem in getService:" + e);
			throw new EcommerceRuntimeException(e);
		}
	}


	@Override
	public String getVersion()	{
		return Utils.StringifyList(getVersions(),'+');
	}

	@Override
	public List<String> getVersions()
	{
		//Object payload_ = null;
		try
		{
			//final Object object_ = _factory_.createRequest(PayloadConstants.GET_VERSION_REQUEST_PAYLOAD);

			final GetVersionRequest request = createRequest(PayloadConstants.GET_VERSION_REQUEST_PAYLOAD);
				//	(GetVersionRequest)object_;

//			final Element element_ = _factory_.buildEnvelope(PayloadConstants.GET_VERSION_REQUEST_PAYLOAD, request_, null);
//			payload_ = _client.getPayload(element_);
//			final GetVersionResponseImpl versionInfo_ = (GetVersionResponseImpl)payload_;
//
//			if (versionInfo_ == null) 
//			{
//				logger.error("Payload is Null");
//				throw new EcommerceException("Payload is Null");
//			}
			final GetVersionResponse versionInfo = sendRequestAndGetResponse(PayloadConstants.GET_VERSION_REQUEST_PAYLOAD, request, GetVersionResponse.class);
			
			return versionInfo.getVersion();

//		}
//		catch(final ClassCastException ce)
//		{
//			final Error errorResponse_ = (Error)payload_;
//			logger.error("ErrorResponse:ID:" + errorResponse_.getId());
//			logger.error("ErrorResponse:Description:" + errorResponse_.getDescription());
//			logger.error("ErrorResponse:Type:" + errorResponse_.getType());
//			logger.error("ErrorResponse:SystemId:" + errorResponse_.getSystemId());
//			logger.error("ErrorResponse:Cause:" + errorResponse_.getCause());
//			return null;
		}	catch(final Exception e)	{
			logger.error("Problem in getVersion:" + e);
			throw new EcommerceRuntimeException(e);
		}
	}


	@Override
	public CatalogPackage[] findPackagesWithService (String msisdn, CatalogService serv, PurchaseAttributes purchaseAttributes) throws EcommerceRuntimeException
	{
		//CatalogPackage [] packs = null;
		FindPackagesWithServiceResponse response=null;
		//was actually calling UsageAuthRateCharge to get package list
		final FindPackagesWithServiceRequest request = new FindPackagesWithServiceRequestImpl();

		if(msisdn == null || msisdn.equals("")){
			request.setMsisdn("null");
		}else{
			request.setMsisdn(msisdn);
		}
		request.setServiceId(serv.getId());

		final RatingAttributesType attrs = new RatingAttributesTypeImpl();

		//Add any other required attributes here
		if (purchaseAttributes != null){
			attrs.setPromoCode(purchaseAttributes.getPromoCode());
			//MQC 8284 - add channel
			if (purchaseAttributes.getChannel() != Constants.INT_NOT_SET) {
				attrs.setChannel(purchaseAttributes.getChannel());
			}
		}

		request.setRatingAttributes(attrs);

		try {
			response = sendRequestAndGetResponse(PayloadConstants.GET_FIND_PACKAGES_WITH_SERVICE_REQUEST_PAYLOAD, request, FindPackagesWithServiceResponse.class);
			return converter.buildPackages(response.getPackages());

		}
		catch (final EcommerceException e)
		{
			logger.error("findPackagesWithService: " , e);
			throw new EcommerceRuntimeException(e);
		}

	}


	/**
	 * CR1789
	 * 
	 * @see
	 * com.vizzavi.ecommerce.business.catalog.CatalogApi#oneStep(String[], String, boolean)
	 */
	@Override
	public Hashtable<String, OneStepData> findPackagesByServiceIdOneStep(String[] serviceId,
			String msisdn) throws EcommerceRuntimeException {
		try {

			final OnestepPackageRequest request = reqObjFactory.createOnestepPackageRequest();
			//new OnestepPackageRequestImpl();

			if (msisdn == null || msisdn.equals("")) {
				request.setMsisdn("null");
			} else {
				request.setMsisdn(msisdn);
			}

			final OnestepPackageRequest.ServiceIdsType serviceIds = reqObjFactory.createOnestepPackageRequestTypeServiceIdsType();
			//new OnestepPackageRequestImpl.ServiceIdsTypeImpl();
			for (final String element : serviceId) {
				serviceIds.getServiceId().add(element)	;
			}
			request.setServiceIds(serviceIds);
			request.setMsisdn(msisdn);
			final Element element_ = _factory_.buildEnvelope(PayloadConstants.FIND_PACKAGES_BY_SERVICE_ID_ONE_STEP_PAYLOAD, request);
			final OnestepPackages response = (OnestepPackages) _client.getPayload(element_, getHeaders(element_)); // MQC 9487
			//OnestepPackages response = 
			final Hashtable<String, OneStepData> tb = new Hashtable<String, OneStepData>();
			for (final Object ed: response.getOnestepPackage()){
				final OneStepData dt = new OneStepData();
				final OnestepPackageType impl = (OnestepPackageType) ed;
				dt.setCreditsLeft(impl.getCreditsLeft());
				dt.setPriceText(impl.getPriceText());
				dt.setServiceId(impl.getServiceId());
				//CR2241start additional fields required in response
				dt.setIsExpressPackage(impl.isIsExpressPackage());
				if (null != impl.getSubscriptionEndDate() &&  ! "".equals(impl.getSubscriptionEndDate())){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt.setSubscriptionEndDate(sdf.parse(impl.getSubscriptionEndDate()));
				}
				dt.setHasPurchaseOptions(impl.isHasPurchasesOptions());
				dt.setSubscribed(impl.isIsSubscribed());
				dt.setIsExpressPackage(impl.isIsExpressPackage());
				dt.setExpressPrice(impl.getPrice());//set whether express package or not - strange, is this correct? TODO: check with TP
				//CR2241end
				tb.put(dt.getServiceId(), dt);
			}
			return tb;

		} catch (final Exception e) {
			logger.error("findPackagesByServiceIdOneStep: " + e.getMessage());
			throw new EcommerceRuntimeException(e);
		}

		//return null;
	}




	@Override
	public PricePoint getPricePoint(String pricePointId) throws EcommerceRuntimeException {
		checkNullParams(pricePointId);
		final GetPricepointRequest request = createRequest(PayloadConstants.GET_PRICEPOINT_REQUEST_PAYLOAD);
		request.setPricepointId(pricePointId);
		GetPricepointResponse jaxbResponse;
		try {
			jaxbResponse = sendRequestAndGetResponse(PayloadConstants.GET_PRICEPOINT_REQUEST_PAYLOAD, request, GetPricepointResponse.class);
		} catch (EcommerceException e) {
			throw new EcommerceRuntimeException(e);
		}
		return converter.convertPricePointType(jaxbResponse.getPricePoint());

	}

	/**
	* Checks for promotional pricepoints.
	* @param msisdn - customer's MSISDN
	* @param service - the service to match
	* @return PromotionsResult object
	* @since 5.1
	*/
	@Override
	public PromotionsResult checkPromotions(String msisdn, CatalogService service) throws EcommerceRuntimeException
	{
		PromotionsResult promoResult = null;

		final CheckPromotionsRequestType request = super.createRequest(PayloadConstants.CHECK_PROMOTIONS_REQUEST_PAYLOAD);
		
		request.setMsisdn(msisdn);
		request.setServiceId(service.getId());
		try	{
		    final CheckPromotionsResponseType response = sendRequestAndGetResponse(PayloadConstants.CHECK_PROMOTIONS_REQUEST_PAYLOAD, request, CheckPromotionsResponseType.class);
		    
		    promoResult = this.converter.buildPromotionsObject(response);
	    
	    }	catch(final EcommerceException e){
	    	throw new EcommerceRuntimeException(e);
	    }

		return promoResult;
	}
	
	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public PurchaseOptionsAuthorization findPackagesWithServiceEx(String msisdn,
			CatalogService serv, PurchaseAttributes purchaseAttributes) throws EcommerceRuntimeException {
		PurchaseOptionsAuthorization auth = new PurchaseOptionsAuthorization();
		auth.setSuccess(false);
		try	{
			CatalogPackage[] packs = findPackagesWithService(msisdn, serv, purchaseAttributes);
			auth.setCatalogPackages(packs);
			auth.setSuccess(true);
			auth.setReasonCode(ReasonCode.OK);

		}	catch(Exception e)	{
			auth.setReasonCode(ReasonCode.SYSTEM_ERROR);
		}
		return auth;
	}
	
	
	@Override
	public Hashtable<String, ExpressData> findExpressPackagesByServiceId(String[] serviceId,
			String msisdn, ExpressDisplayAttribute expressAttribute)  throws EcommerceRuntimeException {
		FindExpressPackagesResponseDTO result = findFullExpressPackagesByServiceId(serviceId, msisdn, expressAttribute);
		if(result == null) {
			//If no result for whatever reason return an empty list
			return new Hashtable<String, ExpressData>();  
		}
		return result.getServiceResultsTable();
	}


	@Override
	public FindExpressPackagesResponseDTO findFullExpressPackagesByServiceId(String[] serviceId,
			String msisdn, ExpressDisplayAttribute expressAttribute) throws EcommerceRuntimeException {
		
		checkNullParams(serviceId, serviceId[0], msisdn);
		ExpressPackageRequest request = new ExpressPackageRequestImpl();
		request.setMsisdn(msisdn);
		request.setFullResponseRequired(true);	//otherwise we get ExpressPackagesImpl back instead of FullExpressPackages
		request.setExpressDisplayAttributes(converter.convertExpressDisplayAttributes(expressAttribute, serviceId));
		request.setServiceIds(converter.convertServices(serviceId));
		try	{
			FullExpressPackages result = sendRequestAndGetResponse(PayloadConstants.EXPRESS_PACKAGE_REQUEST_PAYLOAD, request, FullExpressPackages.class);
			
			FindExpressPackagesResponseDTO response = new FindExpressPackagesResponseDTOImpl();
			Hashtable<String, ExpressData> table = new Hashtable<String, ExpressData>();
			for (Object thing: result.getExpressPackage())	{	//com.vodafone.global.er.decoupling.binding.response.impl.FullExpressPackagesTypeImpl$ExpressPackageTypeImpl
				logger.debug("adding {}", thing);
				FullExpressPackagesType.ExpressPackageType express = (FullExpressPackagesType.ExpressPackageType) thing;
				table.put(express.getServiceId(), converter.convertExpressData(express));
			}
			response.setServiceResultsTable(table);
			return response;
		}	catch (EcommerceException e)	{
			throw new EcommerceRuntimeException(e);
		}
	}


	
	
	//BEGIN UNIMPLEMENTED METHODS
	
	
	@Override
	public Tax getTax(String name) {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}

	@Override
	@Deprecated
	public CatalogPackage getPackage(String packageId, String pricePointId, String tierId) {
		// deprecated, no need to implement this method
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}



	@Override
	public CatalogService[] getServices() throws EcommerceRuntimeException{
		GetAllServicesResponse serviceResponse;
		final GetAllServicesRequest request = createRequest(PayloadConstants.GET_ALL_SERVICES_REQUEST_PAYLOAD);
		try	{
			serviceResponse= sendRequestAndGetResponse(PayloadConstants.GET_ALL_SERVICES_REQUEST_PAYLOAD, request, GetAllServicesResponse.class);
			
		}	catch (EcommerceException e) {
			throw new EcommerceRuntimeException(e);
		}
		final List<BasicServiceType> serviceList = serviceResponse.getService();
		//sort by service name by default
		//unless config specifies to sort by package id
		Collections.sort(serviceList, new Comparator<BasicServiceType>(){
			@Override
			public int compare(BasicServiceType arg0, BasicServiceType arg1) {
				if (sortByPackageName)	//TODO maybe use different param for this
					return arg0.getServiceName().compareTo(arg1.getServiceName());
				else
					return arg0.getServiceId().compareTo(arg1.getServiceId());
			}
		});
		final CatalogService[] services = new CatalogService[serviceList.size()];
		int i = 0;
		for (final BasicServiceType serviceType : serviceList)	{
			services[i++] = new CatalogService(serviceType.getServiceId(), serviceType.getServiceName(), null);
		}

		return services;
	}
	


	@Override
	public Hashtable<String, ExpressData> findExpressPackagesByServiceId(String[] serviceId,
			boolean headline) {
		//to delegate to findFullExpressPackagesByServiceId, we need msisdn. If we pass in null it will fail.
//		ExpressDisplayAttribute expressAttribute = new ExpressDisplayAttribute();
//		expressAttribute.setHeadline(headline);
//		FindExpressPackagesResponseDTO result = findFullExpressPackagesByServiceId(serviceId, msisdn, expressAttribute);
//		if(result == null) {
//			//If no result for whatever reason return an empty list
//			return new Hashtable<String, ExpressData>();  
//		}
//		return result.getServiceResultsTable();
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}





	@Override
	public BasePrice[] getBasePrices(String[] serviceId) throws EcommerceException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public boolean validateService(String id) throws EcommerceException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}



	@Override
	public String translatePricingText(PricePoint[] pricePoints, String templateName,
			String languageCode, int RoamingType) throws EcommerceException {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public boolean isUniquePromoPrecode(String precode) {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public Tariff getTariff(String tariffName) {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public PartnerTradingLimit[] getPartnerTradingLimits() {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public PartnerTradingLimit getPartnerTradingLimit(String partnerId) {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public PromotionsResult checkPromotionSummary(PromotionData data) {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public OverallGoodwillCreditLimits getOverallGoodwillCreditLimits() {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public DefaultGoodwillCreditLimits getDefaultGoodwillCreditLimits() {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public DefaultSpendLimits getDefaultPartnerSpendLimits() {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	@Override
	public DefaultSpendLimits getDefaultMsisdnSpendLimits() {
		// TODO Write this method!
		throw new UnsupportedOperationException("method not supported in this version: "+version+" dated "+date);
	}


	
}
