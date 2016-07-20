package com.vizzavi.ecommerce.business.common;

import java.lang.reflect.Constructor;
import java.util.Locale;

import com.vizzavi.ecommerce.business.catalog.CatalogApi;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.charging.ChargingApi;
import com.vizzavi.ecommerce.business.charging.PurchaseApi;
import com.vizzavi.ecommerce.business.provision.ProvisionApi;
import com.vizzavi.ecommerce.business.selfcare.CustcareApi;
import com.vizzavi.ecommerce.business.selfcare.MicroServiceFilter;
import com.vizzavi.ecommerce.business.selfcare.SelfcareApi;
import com.vizzavi.ecommerce.business.selfcare.SubscriptionFilter;
import com.vizzavi.ecommerce.business.selfcare.TransactionFilter;
import com.vodafone.config.ConfigProvider;

/**
* This class should be used to instantiate implementations of the ER
* service APIs. The resulting APIs by default are ecom APIs which can be used across a network.
* From within the core, consider using ErCoreApiFactory instead, but if you must use EcomApiFactory the property <code>er.ecom.client.mode</code> should be set to false, and the APIs returned will be delegate layer implementations.    
* This property is read only upon class initialization.  However you can change from internal to ECOM apis without restarting - just call 
* {@link #setClientMode()}.<p/>
* If you need the decoupling implementations of the APIs, use {@link com.vodafone.global.er.decoupling.client.DecouplingApiFactory DecouplingApiFactory} instead.<br/>
* Note that this ECOM API is actually deprecated - new clients should use decoupling instead.
* NB the partner API is not supported in the ecom mode.  
*/
@SuppressWarnings("rawtypes")
public class EcomApiFactory
{


    /**
    * The string name of the implementation class of the ResourceTranslator interface.
    */
    static String DRV_TRANSLATOR;

    /**
    * The string name of the implementation class of the ChargingApi interface.
    */
    static String DRV_CHARGING;

    /**
    * The string name of the implementation class of the PurchaseApi interface.
    */
    static String DRV_PURCHASE;

    /**
    * The string name of the implementation class of the CatalogApi interface.
    */
    static String DRV_CATALOG;

    /**
    * The string name of the implementation class of the selfcare interface.
    */
    static String DRV_SELFCARE;

    /**
    * The string name of the implementation class of the custcare interface.
    */
    static String DRV_CUSTCARE;

    /**
    * The string name of the implementation class of the Provision interface.
    */
    static String DRV_PROVISION;

    /**
    * The string name of the Transaction Filter implementation class
    */
    static String DRV_TRANSACTION_FILTER;

    /**
    * The string name of the Subscription Filter implementation class
    */
    static String DRV_SUBSCRIPTION_FILTER;

    /**
    * The string name of the PricePoint implementation class
    */    
    static String DRV_PRICE_POINT;
    
    
    /**
    * The string name of the pricing catalog implementation class
    */
    static String DRV_PRICINGTOOL_CATALOG;

   
    private static String DRV_MICRO_SERVICE_FILTER;
   
    static String DRV_OPCODATA;
    
    static {
        

        if (ConfigProvider.isDbTranslationEnabled()){
        	DRV_TRANSLATOR = ConfigProvider.getProperty( "client.driver.DB_TRANSLATOR", "com.vodafone.global.er.util.ResourceTranslatorDbImpl" );
        }else{
        	DRV_TRANSLATOR = ConfigProvider.getProperty( "client.driver.TRANSLATOR", "com.vodafone.global.er.util.ResourceTranslatorImpl" );
        }

        //this determines whether we should load internal Api impls instead (only the case for ER core)
        boolean clientMode = ConfigProvider.getPropertyAsBoolean("er.ecom.client.mode", true);
        if (clientMode){
        	setClientMode();
        }	else	{
	        DRV_CHARGING =  "com.vodafone.global.er.delegate.ChargingApiDelegateImpl";
	        DRV_PURCHASE =  "com.vodafone.global.er.delegate.PurchaseApiDelegateImpl";
	        DRV_CATALOG = "com.vodafone.global.er.delegate.CatalogApiDelegateImpl";
	        DRV_SELFCARE =  "com.vodafone.global.er.delegate.SelfcareApiDelegateImpl";
	        DRV_CUSTCARE =  "com.vodafone.global.er.delegate.SelfcareApiDelegateImpl";
	        DRV_PROVISION =  "com.vodafone.global.er.delegate.ProvisionApiDelegateImpl";
	        DRV_OPCODATA = "com.vodafone.er.opcodata.OpcoDataApiImpl";
        }
        
        DRV_TRANSACTION_FILTER =  "com.vodafone.global.er.subscriptionmanagement.TransactionFilterImpl" ;
        DRV_MICRO_SERVICE_FILTER =  "com.vodafone.global.er.subscriptionmanagement.MicroServiceFilterImpl" ;
        DRV_SUBSCRIPTION_FILTER =  "com.vodafone.global.er.subscriptionmanagement.SubscriptionFilterImpl" ;
        DRV_PRICE_POINT=  "com.vizzavi.ecommerce.business.catalog.internal.PricePointImpl" ;
        
     }

    final static Object[] OBJ_EMPTY = new Object[] {};
    final static Class[]  ARGS_EMPTY = new Class[] {};
	final static Class[]  ARGS_LOCALE = new Class[] { Locale.class };
    final static Class[]  ARGS_LOCALE_STRING = new Class[] { Locale.class, String.class };
    final static Class[]  ARGS_LOCALE_BOOLEAN = new Class[] { Locale.class, Boolean.class };
    final static Class[]  ARGS_RATING_ATTRIBUTES = new Class[] { RatingAttributes.class };


    /**
    * Obtain a ResourceTranslator implementation.
    * @return an implementation of the ResourceTranslator interface
    */
    public static ResourceTranslator getResourceTranslator( Locale locale, String resourceName )
        throws EcommerceException
    {
        return (ResourceTranslator) newInstanceLocaleStringArgs( DRV_TRANSLATOR, locale, resourceName );
    }

    /**
    * Obtain a ResourceTranslator implementation using the default translator file.
    * @return an implementation of the ResourceTranslator interface
    */
    public static ResourceTranslator getResourceTranslator( Locale locale )
        throws EcommerceException
    {
        return (ResourceTranslator) newInstanceLocaleArgs( DRV_TRANSLATOR, locale );
    }

    /**
    * Obtain a ChargingApi implementation.
    * @param locale the locale for which the ecom service is desired.
    * @return an implementation of the ChargingApi interface
    */
    public static ChargingApi getChargingApi( Locale locale )
        throws EcommerceException
    {
        return (ChargingApi) newInstanceLocaleArgs( DRV_CHARGING, locale );
    }

    /**
    * Obtain a CatalogApi implementation.
    * @param locale the locale for which the ecom service is desired.
    * @return an implementation of the CatalogApi interface
    */
    public static CatalogApi getCatalogApi(  Locale locale  )
        throws EcommerceException
    {
        return (CatalogApi) newInstanceLocaleArgs( DRV_CATALOG, locale );
    }
    

    /**
    * Obtain a PurchaseApi implementation.
    * @param locale the locale for which the ecom service is desired.
    * @return an implementation of the PurchaseApi interface
    */
    public static PurchaseApi getPurchaseApi( Locale locale )
        throws EcommerceException
    {
        return (PurchaseApi) newInstanceLocaleArgs( DRV_PURCHASE, locale );
    }
    
//    /**
//     * returns a decoupling implementation of the PartnerApi (requires DECOUPLING_client and DECOUPLING_common on the classpath)
//     * @param locale
//     * @return
//     * @throws EcommerceException
//     */
//    public static PartnerApi getPartnerApi(Locale locale) throws EcommerceException	{
//		return (PartnerApi) newInstanceLocaleArgs("com.vodafone.global.er.decoupling.client.PartnerApiDecouplingImpl", locale);
//    }
    
    /**
    * Obtain a SelfcareApi implementation.
    * @param locale the locale for which the ecom service is desired.
    * @return an implementation of the SelfcareApi interface
    */
    public static SelfcareApi getSelfcareApi( Locale locale )
        throws EcommerceException
    {
        return (SelfcareApi) newInstanceLocaleArgs( DRV_SELFCARE, locale );
    }


   /**
    * Obtain a CustcareApi implementation.
    * @param locale the locale for which the ecom service is desired.
    * @return an implementation of the CustcareApi interface
    */
    public static CustcareApi getCustcareApi( Locale locale )
        throws EcommerceException
    {
        return (CustcareApi) newInstanceLocaleArgs( DRV_CUSTCARE, locale );
    }
    

   /**
    * Obtain a ProvisionApi implementation.
    * @param locale the locale for which the ecom service is desired.
    * @return an implementation of the SelfcareApi interface
    */
    public static ProvisionApi getProvisionApi( Locale locale )
        throws EcommerceException
    {
        return (ProvisionApi) newInstanceLocaleArgs( DRV_PROVISION, locale );
    }

    /**
    * Obtain a TransactionFilter implementation.
    * @param mLocale the locale for which the ecom service is desired.
    * @return a TransactionFilter
    */
    public static TransactionFilter getTransactionFilter()
        throws EcommerceException
    {
        return (TransactionFilter) newInstanceEmptyArgs( DRV_TRANSACTION_FILTER );
    }
    

    /**
     * Obtain a MicroServiceFilter implementation.
     * @param mLocale the locale for which the ecom service is desired.
     * @return a MicroServiceFilter
     */
     public static MicroServiceFilter getMicroServiceFilter()
         throws EcommerceException {
         return (MicroServiceFilter) newInstanceEmptyArgs( DRV_MICRO_SERVICE_FILTER );
     }
     
    public static PricePoint getPricePoint(RatingAttributes attr) throws EcommerceException
    {
      return (PricePoint) newInstanceLocaleArgs(DRV_PRICE_POINT,attr);
    }
    

    /**
    * Obtain a SubscriptionFilter implementation.
    * @param mLocale the locale for which the ecom service is desired.
    * @return a SubscriptionFilter
    */
    public static SubscriptionFilter getSubscriptionFilter()
        throws EcommerceException
    {
        return (SubscriptionFilter) newInstanceEmptyArgs( DRV_SUBSCRIPTION_FILTER );
    }
    
     


    /**
    * Create an instance of a class using a zero arg constructor.
    */
    private static Object newInstanceEmptyArgs( String className )
            throws EcommerceException
    {
        Object ret = null;
        try {
            Class<?> clazz = Class.forName( className );
            Constructor<?> con = clazz.getConstructor( ARGS_EMPTY );
            ret = con.newInstance( OBJ_EMPTY );
        }
        catch(Exception e) {
            throw new EcommerceException( getMessage(className, e), e);
        }
        return ret;
    }

    /**
    * Create an instance of a class using a constructor with signature (Locale).
    */
	private static Object newInstanceLocaleArgs( String className, Locale locale )
            throws EcommerceException
    {
        Object ret = null;
        try {
            Class<?> clazz = Class.forName( className );
            Constructor<?> con = clazz.getConstructor( ARGS_LOCALE );
            ret = con.newInstance( new Object[] { locale } );
        }
        catch(Exception e) {
            throw new EcommerceException(getMessage(className, e), e);
        }
        return ret;
    }

    /**
    * Create an instance of a class using a constructor with signature (RatingAttributes).
    */
    private static Object newInstanceLocaleArgs( String className, RatingAttributes attr)
            throws EcommerceException
    {
        Object ret = null;
        try {
            Class<?> clazz = Class.forName( className );
            Constructor<?> con = clazz.getConstructor( ARGS_RATING_ATTRIBUTES );
            ret = con.newInstance( new Object[] { attr } );
        }
        catch(Exception e) {
            throw new EcommerceException(getMessage(className, e), e);
        }
        return ret;
   }
    /**
    * Create an instance of a class using a constructor with signature (Locale, String).
    */
    private static Object newInstanceLocaleStringArgs( String className, Locale locale, String stringArg )
            throws EcommerceException
    {
        Object ret = null;
        try {
            Class<?> clazz = Class.forName( className );
            Constructor<?> con = clazz.getConstructor( ARGS_LOCALE_STRING );
            ret = con.newInstance( new Object[] { locale, stringArg } );
        }
        catch(Exception e) {
            throw new EcommerceException(getMessage(className, e), e);
        }
        return ret;
    }


    private static String getMessage( String className, Exception e )
    {
        return
            "Fatal: An error occurred trying to obtain [" +
            className + "] . The exception was " + e.getMessage();
    }

    /**
     * call this method when running as a client. You will then get client APIs back - ie APIs you can use across a network
     */
	public static void setClientMode()	{
//		DRV_CHARGING = "com.vodafone.global.er.decoupling.client.ChargingApiDecouplingImpl";
		//DRV_CHARGING = "com.vodafone.global.er.decoupling.test.ChargingApiDecouplingJunitStub";
		DRV_CHARGING = "com.vodafone.global.er.generated.ChargingApiStub";
		DRV_PURCHASE = "com.vodafone.global.er.generated.PurchaseApiStub";
        DRV_CATALOG = "com.vodafone.global.er.generated.CatalogApiStub";
        DRV_SELFCARE = "com.vodafone.global.er.generated.SelfcareApiStub";
        DRV_CUSTCARE = "com.vodafone.global.er.generated.CustcareApiStub";
        DRV_PROVISION = "com.vodafone.global.er.generated.ProvisionApiStub";
        DRV_OPCODATA = "com.vodafone.global.er.decoupling.client.OpcoDataApiDecouplingImpl";
	}
	
//	/**
//     * returns a decoupling implementation of the OpCoDataApi (requires DECOUPLING_client and DECOUPLING_common on the classpath)
//     * @param locale
//     * @return
//     * @throws EcommerceException
//     */
//    public static OpcoDataApi getOpcoDataApi(Locale locale) throws EcommerceException	{
//		return (OpcoDataApi) newInstanceLocaleArgs(DRV_OPCODATA, locale);
//    }

}
