package com.vodafone.global.er.decoupling.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.common.EcommerceRuntimeException;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.util.ThreadUtil;

/**
 * an implementation of the catalogApi which routes ER calls via decoupling, and caches some of the results.   
 * The default cache timeout is 5 minutes and can be adjusted by setting <code>decoupling.catalog.cache.time</code> (seconds).
 * To disable caching altogether, set <code>er.decoupling.cache.enabled</code> to false.  
 * The cache is also cleared when a getService or getPackage request returns no corresponding service from ER.
 * @author matt
 *
 */
class CachingCatalogApiImpl extends CatalogApiDecouplingImpl  {

	/**default cache thread sleep time - 5 minutes*/
	public static int cacheRefreshTimeSeconds=ConfigProvider.getPropertyAsInteger("decoupling.catalog.cache.time", 300);
	static final Logger logger = LoggerFactory.getLogger(CachingCatalogApiImpl.class);

	/** a map of sparsely populated (name and id only) package arrays. key is country code eg .get("GB") returns an array of CatalogPackage objects*/
	private static final Map<String,  CatalogPackage[]> simplePackageCache = Collections.synchronizedMap(new HashMap<String, CatalogPackage[]>());

	/** a map of sparsely populated (name and id only) service arrays. key is country code eg .get("GB") returns an array of CatalogService objects */
	private static final Map<String,  CatalogService[]> simpleServiceCache = Collections.synchronizedMap(new HashMap<String, CatalogService[]>());

	/**a map of maps of packages - eg .get("GB").get("myPackageId") returns a CatalogPackage object */
	static final Map<String, Map<String, CatalogPackage>> fullPackageCache = Collections.synchronizedMap(new HashMap<String, Map<String, CatalogPackage>>());

	/**a map of maps of services - eg .get("GB").get("myServiceId") returns a CatalogPackage object */
	static final Map<String, Map<String, CatalogService>> fullServiceCache = Collections.synchronizedMap(new HashMap<String, Map<String, CatalogService>>());



	public CachingCatalogApiImpl(Locale locale, String clientId) {
		super(locale, clientId);
	}

	public CatalogPackage  getSimplePackage(String packageId)	{
			//calling getPackages() will initialize the cache
		for(CatalogPackage cp : getPackages()){
			//TODO this is not very efficient.  We should use a Map if this method will be used a lot
			if (cp.getSimplePackageId().equals(packageId))	{
				return cp;
			}
		}
		return null;
	}

//	private void initializeSimplePackageCache() {
//		//get from cache for this locale
//		CatalogPackage[] packs = simplePackageCache.get(locale.getCountry());
//		if (packs==null){
//			//if there's nothing from the cache, get from ER
//			logger.info("no packages found in cache - fetching from ER");
//			packs = super.getPackages();
//			simplePackageCache.put(locale.getCountry(), packs);
//		}
//	}

	@Override
	public CatalogPackage[] getPackages() throws EcommerceRuntimeException	{
		//get from cache for this locale
		CatalogPackage[] packs = simplePackageCache.get(locale.getCountry());
		if (packs!=null && packs.length>0)
			return packs;
		//if there's nothing from the cache, get from ER
		logger.info("no packages found in cache - fetching from ER");
		packs = super.getPackages();
		simplePackageCache.put(locale.getCountry(), packs);
		return packs;
	}

	@Override
	public CatalogService[] getServices() throws EcommerceRuntimeException	{
		//get from cache for this locale
		CatalogService[] services = simpleServiceCache.get(locale.getCountry());
		if (services!=null && services.length>0)
			return services;
		//if there's nothing from the cache, get from ER
		logger.info("no services found in cache - fetching from ER");
		services = super.getServices();
		simpleServiceCache.put(locale.getCountry(), services);
		return services;
	}

	@Override
	public CatalogPackage getPackage(String packageId) throws EcommerceRuntimeException	{
		//the ecom and decoupling impls return null if the package is not found
		Map<String, CatalogPackage> packageMap = fullPackageCache.get(locale.getCountry());
		CatalogPackage pack = null;
		if (packageMap == null)	{
			logger.debug("no full package cache for {} - creating a new cache", locale);
			packageMap = new HashMap<>();
			fullPackageCache.put(locale.getCountry(), packageMap);
		}		
		//now we have a cache for that locale - let's see if the package we want is in there

		pack = packageMap.get(packageId);
		logger.debug("{} found in cache? {}", packageId, pack!=null);
		if (pack==null && packageId!=null)	{
			logger.info("fetching {} from ER", packageId);
			//get the package requested
			//and put it in the cache
			pack = super.getPackage(packageId);
			if (pack==null)		
				//this means ER doesn't have the package - maybe the priceplan has changed
				//so clear all the caches for this locale
				clearCachesForLocale(locale.getCountry());
			else
				packageMap.put(packageId, pack);
		}

		return pack;
	}


	@Override
	public CatalogService getService(String serviceId) throws EcommerceRuntimeException	{
		//the ecom and decoupling impls return null if the service is not found
		Map<String, CatalogService> serviceMap = fullServiceCache.get(locale);
		CatalogService service = null;
		if (serviceMap == null)	{
			logger.debug("no full service cache for {} - creating a new cache", locale);
			serviceMap = new HashMap<>();
			fullServiceCache.put(locale.getCountry(), serviceMap);
		}		
		//now we have a cache for that locale - let's see if the service we want is in there

		service = serviceMap.get(serviceId);
		logger.debug("{} found in cache? {}", serviceId, service!=null);
		if (service==null && serviceId!=null)	{
			logger.info("fetching {} from ER", serviceId);
			//get the service requested
			//and put it in the cache
			service = super.getService(serviceId);
			if (service==null)		
				//this means ER doesn't have the service - maybe the priceplan has changed
				//so clear all the caches for this locale
				clearCachesForLocale(locale.getCountry());
			else
				serviceMap.put(serviceId, service);
		}

		return service;
	}


	private static void clearCachesForLocale(String locale) {
		logger.debug("clearing caches for {}", locale);
		simplePackageCache.put(locale, null);
		if (fullPackageCache.get(locale)!=null)
			fullPackageCache.get(locale).clear();
		if (fullServiceCache.get(locale)!=null)
			fullServiceCache.get(locale).clear();

	}

	static void clearAllCaches()	{
		for (Locale locale: allLocales){
			logger.info("clearing catalog api cache for {}", locale);
			clearCachesForLocale(locale.getCountry());
		}
	}

	static {	//a background thread to clear the cache every few minutes
		logger.warn("starting cache cleaner thread");

		Thread cacheCleaner = new Thread("DecouplingCatalogApiCacheCleaner"){

			@Override
			public void run() {
				while (true)	{
					//logger.info("cache cleaner thread {} sleeping {} secs", Thread.currentThread().getName(), cacheRefreshTimeSeconds);
					try {
						Thread.sleep(cacheRefreshTimeSeconds*1000);
					} catch (InterruptedException e) {
						logger.info(e.getMessage());
					}
					clearAllCaches();
				}
			}
		};
		cacheCleaner.setDaemon(true);
		ThreadUtil.startJob(cacheCleaner);
	}


}
