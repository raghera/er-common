package com.vizzavi.ecommerce.business.catalog;

import java.util.Map;
import java.util.Locale;
import java.util.List;

import com.vizzavi.ecommerce.business.charging.PurchaseAttributes;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.PromotionData;
import com.vodafone.global.er.business.catalog.BasePrice;
/**
    The catalog API
*/
public interface CatalogApi extends java.io.Serializable
{


    /**
        Searches the catalog for the Service matching the input service id
        If no matching service is found, null is returned.

        @param id the service id to search for
        @return CatalogService
    */
    public CatalogService getService(String id);

    /**
        Searches the catalog for the CatalogPackage matching the input package id

    *   The mandatory attributes are the id for the package.
        @param id the package id to search for
        @return CatalogPackage
    */
	public CatalogPackage getPackage(String id);

 
	/**
	   This returns the package with the appropriate price point and tier set
	*/
    @Deprecated
    public CatalogPackage getPackage(String packageId, String pricePointId, String tierId);


    /**
        Returns all of the packages in the catalog

        @return Array of CatalogPackage objects
    */
    public CatalogPackage[] getPackages();

    /**
        Returns all of the services in the catalog

        @return Array of CatalogService objects
    */
    public CatalogService[] getServices();


    /**
    *    Returns all of the packages in the catalog matching the input CatalogService
    *    The mandatory attributes are the catalogService
    *	
    *    @param serv the service to match
    *    @return Array of CatalogPackage objects
    *    @deprecated use {@link #findPackagesWithServiceEx(String msisdn, CatalogService serv, PurchaseAttributes purchaseAttributes)} instead
    *    NB deprecating this is tricky because the implementation in CatalogApiDelegateImpl is hard to delegate to another method
    */
    @Deprecated
    public CatalogPackage[] findPackagesWithService(CatalogService catalogService);


 
    /**
    *    Returns all of the packages in the catalog matching the input CatalogService and PurchaseAttributes
    *    It also checks if the user has purchased the promo code already
    *    The mandatory attributes are the catalogService
    *    @param msisdn the user to check (can be null)
    *    @param serv the service to match
    *    @param attrs the attributes to match (can be null)
    *    @return Array of CatalogPackage objects
    *    @deprecated use {@link #findPackagesWithServiceEx(String msisdn, CatalogService serv, PurchaseAttributes purchaseAttributes)} instead
    */
    @Deprecated
    public CatalogPackage[] findPackagesWithService(String msisdn, CatalogService serv, PurchaseAttributes purchaseAttributes);

    /**
        The version of the catalog
    */
    @Deprecated
    public String getVersion();

    //public List<String> getVersions();

    //CR1564 -Utctimezone for diff region in country
    /**
        Returns the price point from the price point id
    */
	public PricePoint getPricePoint(String pricePointId);

    /**
        Return the Locale of the user
    */
    public Locale getLocale();


    /**
        The tax object corresponding to the name
    */
    public Tax getTax(String name);

	/**
	* Checks for promotional pricepoints.
	* @param msisdn - customer's MSISDN
	* @param service - the service to match
	* @return PromotionsResult object
	* @since 5.1
	*/
	public PromotionsResult checkPromotions(String msisdn, CatalogService service);

 
    
   /**
    *		Method retrieves express package of a service id
    *   @param serviceId the service that is in an express package
    *   @since ER 6
    *   @see {@link #findExpressPackagesByServiceId(String[], String, ExpressDisplayAttribute)}
    *   @deprecated use findExpressPackagesByServiceId(String[], String, ExpressDisplayAttribute) instead
    */
    @Deprecated
    public Map<String, ExpressData> findExpressPackagesByServiceId(String[] serviceId,boolean headline);

/**
    *		Method retrieves express package of a service id
    *   @param serviceId the service that is in an express package
    *   @param msisdn - customer's MSISDN optional
    *   @param expressAttribute - express package display attr optional
    *   @since German migration
    */
    public Map<String, ExpressData> findExpressPackagesByServiceId(String[] serviceId,String msisdn,ExpressDisplayAttribute expressAttribute);

    //Added for MQC 6968
    /**
     *
     *	Same business logic as the normal findExpressPackagesByServiceId method.  
     *	The difference is the response which includes more information
     *	It includes a success field and errorCode / Desciption should anything go wrong.
     *
     * @param serviceId
     * @param msisdn
     * @param expressAttribute
     * @return
     */
    public FindExpressPackagesResponseDTO findFullExpressPackagesByServiceId(String [] serviceId, String msisdn, ExpressDisplayAttribute expressAttribute);
    
    
    /**
     *	CR1789	Method retrieves up to 1 package per service id.
     *   If the user is already subscribed, it returns some details about a usage and the subscription.
     *   If the user is not subscribed, if it is in an express package, it returns the price of subscribing 
     *   If the user is not subscribed, if it is not in an express package, it returns a flag indicating whether non-express packages are available to purchase in a subsequent step.
     *   @param serviceId array of service ids
     *   @param msisdn - customer's MSISDN
     *   @return Map of OneStepData objects.
     *   @since ER-10-0-x
     */
     public Map<String, OneStepData> findPackagesByServiceIdOneStep(String[] serviceId,String msisdn);
    
    
  /**
   * Retrieves an array of BasePrices (Base PricePoints) associated with
   * the given services.
   * @param serviceId An array of service ids.
   * @return An array of BasePrices
   * @since ER8, Phase 2
   * @author VFE-PS: Pre-Sign on Pricing
   */
    public BasePrice[] getBasePrices(String[] serviceId) throws EcommerceException;
    
    /**
    Validates the catalog for the Service matching the input service id
    If no matching service is found, false is returned.

    @param id the service id to validate for
    @return boolean
    @since ER9
	*/
	public boolean validateService(String id) throws EcommerceException ;
	

	/**
	 * Translate the pricing text template into the pricing text for the specified language.
	 * 
	 * @param pricePoints The price point and its linked price point
	 * @param templateName The template name	 * 
	 * @return The translated pricing text.
	 * @since ER9 Phase 3
	 */	
	//TODO move down one layer
	public String translatePricingText(PricePoint[] pricePoints, String templateName, String languageCode, int RoamingType) throws EcommerceException ;
	
	
	
	
	// check precode 
	public boolean isUniquePromoPrecode(String precode);
	
	
	/**
	 * CR-0978 Location Services
     * The tariff object corresponding to the name
	*/
	public Tariff getTariff(String tariffName);
	
	 /**
	  	*	 MQC 5843 
	    *    Returns all of the packages in the catalog matching the input CatalogService and PurchaseAttributes
	    *    and a reason code to specify any failures
	    *    It also checks if the user has purchased the promo code already
	    *    The mandatory attributes are the catalogService
	    *    @param msisdn the user to check
	    *    @param serv the service to match
	    *    @param attrs the attributes to match
	    *    @return PurchaseOptionsAuthorization which contains an array of CatalogPackage objects
	    *    and a reason code
	    */
	public PurchaseOptionsAuthorization findPackagesWithServiceEx(String msisdn, CatalogService serv, PurchaseAttributes purchaseAttributes);
	
	/**
	 * CR1923 Partner Trading Limit
     * The partner trading limits setup in the catalog
	*/
	public PartnerTradingLimit[] getPartnerTradingLimits();
	
	/**
	 * CR1923 Partner Trading Limit
     * Return a partner trading limit setup in the catalog
	*/
	public PartnerTradingLimit getPartnerTradingLimit(String partnerId);
	
	/**
      * Finds out if the msisdn within the 
      * current transaction has any promotions available
      * for a particular service
      * 
      * @param data - Data required for processing
      * @return - Result containing a boolean value only about whether the msisdn hasPromotions
	 */
	public PromotionsResult checkPromotionSummary(PromotionData data);
	
	/**
	 * CR2040 MPAY replacement.  Overall goodwill credit limits.  Gets overall goodwill credit limits.
	 * @return the overall goodwill credit limits
	 */
	public OverallGoodwillCreditLimits getOverallGoodwillCreditLimits();
	
	/**
	 * CR2203 MPAY replacement.  Default goodwill credit limits.  Gets default goodwill credit limits.
	 * @return the default goodwill credit limits
	 */
	public DefaultGoodwillCreditLimits getDefaultGoodwillCreditLimits();
	
	/**
	 * CR2203 MPAY replacement.  Default spend limits.  Gets default partner spend limits.
	 * @return the default partner spend limits
	 */
	public DefaultSpendLimits getDefaultPartnerSpendLimits();
	
	/**
	 * CR2203 MPAY replacement.  Default MSISDN spend limits.  Gets default MSISDN spend limits.
	 * @return the default MSISDN spend limits
	 */
	public DefaultSpendLimits getDefaultMsisdnSpendLimits();

        //MQC7712
    public List<String> getVersions();
 
	
}
	
