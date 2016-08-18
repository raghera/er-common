package com.vodafone.global.er.decoupling.client;

import java.util.Locale;

import com.vizzavi.ecommerce.business.catalog.CatalogApi;
import com.vizzavi.ecommerce.business.charging.ChargingApi;
import com.vizzavi.ecommerce.business.charging.PurchaseApi;
import com.vizzavi.ecommerce.business.provision.ProvisionApi;
import com.vizzavi.ecommerce.business.selfcare.CustcareApi;
import com.vizzavi.ecommerce.business.selfcare.SelfcareApi;
import com.vodafone.application.util.CommonConfig;
import com.vodafone.global.er.http.HttpHeaderAware;
import com.vodafone.global.er.partner.PartnerApi;

/**
 * get your decoupling implementations of ER apis here.  To add headers to a request, cast the returned class to {@link HttpHeaderAware} and call {@link HttpHeaderAware#setHeaders}.  These headers will be added to the next request and then discarded.
 *<p>You will need to add the following properties to either env.properties or central config db:</p>
 *<ul>
 *<li>er.server.host</li>
 *<li>er.server.port</li>
 *<li>HTTP.SECURITY.CONNECTION.TIMEOUT (optional - default is 30 seconds)</li>
 *<li>payload.clientapplicationid (or preferably supply clientId when calling factory methods)</li>
 *<li>payload.clientdomain (use [opco].[environment] - e.g. 'UK.DIT')</li>
 * </ul>
 * @author matt
 *
 */
public class DecouplingApiFactory {

	//this is the only public class in this package.
	//so all clients need to get their decoupling clients from the static methods below
	
	private DecouplingApiFactory() {
		// non-instantiated class
	}

	/**
	 * an implementation of CatalogApi which caches some calls to ER for performance.  The default cache timeout is 5 minutes and can be adjusted by setting <code>decoupling.catalog.cache.time</code> (seconds).
	 * To disable caching altogether, set <code>er.decoupling.cache.enabled</code> to false.  The cache is also cleared when a getService or getPackage request returns no corresponding service from ER.
	 * @param locale
	 * @param clientId
	 * @return
	 * @see CachingCatalogApiImpl
	 * @see CatalogApiDecouplingImpl
	 */
	public static CatalogApi getCatalogApi(Locale locale, String clientId)	{
		return getCatalogApi (locale, clientId, CommonConfig.getPropertyAsBoolean("er.decoupling.cache.enabled", true));
	}
	
	/**
	 * an implementation of CatalogApi which optionally caches some calls to ER for performance.  The default cache timeout is 5 minutes and can be adjusted by setting <code>decoupling.catalog.cache.time</code> (seconds).
	 * To disable caching altogether, set useCaching to false.  The cache is also cleared when a getService or getPackage request returns no corresponding service from ER.
	 * @param locale
	 * @param clientId
	 * @param useCaching whether to enable or disable caching
	 * @return
	 * @see #getCatalogApi(Locale, String)
	 */
	public static CatalogApi getCatalogApi(Locale locale, String clientId, boolean useCaching)	{
		if (useCaching)
			return new CachingCatalogApiImpl(locale, clientId);
		else
			return new CatalogApiDecouplingImpl(locale, clientId);
	}

		
	public static ChargingApi getChargingApi(Locale locale, String clientId)	{
		return new ChargingApiDecouplingImpl(locale, clientId);
	}
	
	public static PurchaseApi getPurchaseApi(Locale locale, String clientId)	{
		return new PurchaseApiDecouplingImpl(locale, clientId);
	}
	
	public static ProvisionApi getProvisionApi(Locale locale, String clientId)	{
		return new ProvisionApiDecouplingImpl(locale, clientId);
	}
	
	public static PartnerApi getPartnerApi(Locale locale, String clientId)	{
		return new PartnerApiDecouplingImpl(locale, clientId);
	}
	
	public static SelfcareApi getSelfcareApi(Locale locale, String clientId)	{
		return new SelfcareApiDecouplingImpl(locale, clientId);
	}
	
	public static CustcareApi getCustcareApi(Locale locale, String clientId)	{
		return new CustCareApiDecouplingImpl(locale, clientId);
	}
	
	/**
	 * an implementation of CatalogApi which caches some calls to ER for performance.  The default cache timeout is 5 minutes and can be adjusted by setting decoupling.catalog.cache.time (seconds)
	 * @param locale
	 * @param clientId
	 * @return
	 * @see CachingCatalogApiImpl
	 */
	@Deprecated
	public static CatalogApi getCachingCatalogApi(Locale locale, String clientId){
		return new CachingCatalogApiImpl(locale, clientId);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException	{
		throw new CloneNotSupportedException("this is a singleton");
	}
}
