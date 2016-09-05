package com.vodafone.global.er.decoupling.client;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.common.EcommerceRuntimeException;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * an implementation of the catalogApi which routes ER calls via decoupling, and caches some of the results.   
 * The default cache timeout is 30 minutes and can be adjusted by setting <code>decoupling.catalog.cache.time</code> (seconds).
 * To disable caching altogether, set <code>er.decoupling.cache.enabled</code> to false.  
 * The cache is also cleared when a getService or getPackage request returns no corresponding service from ER.
 * @author matt
 *
 */
class CachingCatalogApiImpl extends CatalogApiDecouplingImpl  {

	private static final long	serialVersionUID	= -460035351009169974L;
	static final Logger logger = LoggerFactory.getLogger(CachingCatalogApiImpl.class);

	/**default cache thread sleep time - 30 minutes*/
	public static int cacheRefreshTimeSeconds=ConfigProvider.getPropertyAsInteger("decoupling.catalog.cache.time", 1800);

	/** a map of sparsely populated (name and id only) package arrays. key is country code eg .get(Locale.UK) returns an array of CatalogPackage objects*/
	private static final Map<Locale,  CatalogPackage[]> simplePackageCache = Collections.synchronizedMap(new HashMap<Locale, CatalogPackage[]>());

	/** a map of sparsely populated (name and id only) service arrays. key is country code eg .get(Locale.UK) returns an array of CatalogService objects */
	private static final Map<Locale,  CatalogService[]> simpleServiceCache = Collections.synchronizedMap(new HashMap<Locale, CatalogService[]>());

	/**a map of maps of packages - eg .get(Locale.UK).get("myPackageId") returns a CatalogPackage object */
	private static final Map<Locale, Map<String, CatalogPackage>> fullPackageCache = Collections.synchronizedMap(new HashMap<Locale, Map<String, CatalogPackage>>());

	/**a map of maps of services - eg .get(Locale.UK).get("myServiceId") returns a CatalogService object */
	private static final Map<Locale, Map<String, CatalogService>> fullServiceCache = Collections.synchronizedMap(new HashMap<Locale, Map<String, CatalogService>>());

	/**a map of maps of PricePoints - eg .get(Locale.UK).get("myPricePointId") returns a PricePoint object */
	private static final Map<Locale, Map<String, PricePoint>> pricePointCache = Collections.synchronizedMap(new HashMap<Locale, Map<String, PricePoint>>());


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


	@Override
	public CatalogPackage[] getPackages() throws EcommerceRuntimeException	{
		//get from cache for this locale
		CatalogPackage[] packs = simplePackageCache.get(locale);
		if (packs!=null && packs.length>0)
			return packs;
		//if there's nothing from the cache, get from ER
		logger.info("no packages found in cache - fetching from ER");
		packs = super.getPackages();
		simplePackageCache.put(locale, packs);
		return packs;
	}

	@Override
	public CatalogService[] getServices() throws EcommerceRuntimeException	{
		//get from cache for this locale
		CatalogService[] services = simpleServiceCache.get(locale);
		if (services!=null && services.length>0)
			return services;
		//if there's nothing from the cache, get from ER
		logger.info("no services found in cache - fetching from ER");
		services = super.getServices();
		simpleServiceCache.put(locale, services);
		return services;
	}

	@Override
	public CatalogPackage getPackage(String packageId) throws EcommerceRuntimeException	{
		//the ecom and decoupling impls return null if the package is not found
		Map<String, CatalogPackage> localPackageCache = fullPackageCache.get(locale);
		if (localPackageCache!=null)	{
			CatalogPackage fromCache = localPackageCache.get(packageId);
			if (fromCache!=null)	{	//if it's in the cache, use that
				logger.info("found package {} in locale cache", packageId);
				return fromCache;
		}		
		}	else	{
			//initialise cache
			logger.debug("initialising package cache for {}", locale);
			localPackageCache = new HashMap<>();
			fullPackageCache.put(locale, localPackageCache);
		}
		logger.info("Package {} not found in locale cache - fetching from ER", packageId);
		CatalogPackage fromER =  super.getPackage(packageId);
		localPackageCache.put(packageId, fromER);
		return fromER;
	}


	@Override
	public CatalogService getService(String serviceId) throws EcommerceRuntimeException	{
		//the ecom and decoupling impls return null if the service is not found
		Map<String, CatalogService> localServiceCache = fullServiceCache.get(locale);
		if (localServiceCache!=null)	{
			CatalogService fromCache = localServiceCache.get(serviceId);
			if (fromCache!=null)	{	//if it's in the cache, use that
				logger.info("found service {} in locale cache", serviceId);
				return fromCache;
		}		
		}	else	{
			//initialise cache
			logger.debug("initialising service cache for {}", locale);
			localServiceCache = new HashMap<>();
			fullServiceCache.put(locale, localServiceCache);
		}
		logger.info("service {} not found in locale cache - fetching from ER", serviceId);
		CatalogService fromER =  super.getService(serviceId);
		localServiceCache.put(serviceId, fromER);
		return fromER;
		}

	@Override
	public PricePoint getPricePoint(String id)	{
		Map<String, PricePoint> localPricePointCache = pricePointCache.get(locale);
		if (localPricePointCache!=null)	{
			PricePoint fromCache = localPricePointCache.get(id);
			if (fromCache!=null)	{
				logger.info("found ppt {} in locale cache", id);
				return fromCache;
			}
		}	else	{
			//initialise cache
			logger.debug("initialising ppt cache for {}", locale);
			localPricePointCache = new HashMap<>();
			pricePointCache.put(locale, localPricePointCache);
		}
		logger.info("ppt {} not found in locale cache - fetching from ER", id);
		PricePoint fromER =  super.getPricePoint(id);
		localPricePointCache.put(id, fromER);
		return fromER;
	}



	private static void clearCachesForLocale(Locale locale) {
		logger.debug("clearing caches for {}", locale);
		simplePackageCache.put(locale, null);
		simpleServiceCache.put(locale, null);
		if (fullPackageCache.get(locale)!=null)
			fullPackageCache.get(locale).clear();
		if (fullServiceCache.get(locale)!=null)
			fullServiceCache.get(locale).clear();
		if (pricePointCache.get(locale)!=null)
			pricePointCache.get(locale).clear();

	}

	static void clearAllCaches()	{
		for (Locale locale: allLocales){
			logger.info("clearing catalog api cache for {}", locale);
			clearCachesForLocale(locale);
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
