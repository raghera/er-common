package com.vizzavi.ecommerce.business.catalog;
/**
 * PromotionResult object.  Returned by CatalogApi.checkPromotions method.
 * @since 5.1
 */
public class PromotionsResult implements java.io.Serializable {

   private    static final long serialVersionUID = -4926571379569513851L;

	protected boolean hasPromotion = true;
	protected CatalogPackage[] catalogPackages = null;
	
	public PromotionsResult (boolean vHasPromo, CatalogPackage[] vCatalogPack){
		 this.hasPromotion = vHasPromo;
		 this.catalogPackages = vCatalogPack;
	}
	public PromotionsResult (){
		this.catalogPackages = new CatalogPackage [1];
	}
	
	/**
	 * Indicates if there are promotional pricepoints available
	 * @return true - there are promotional pricepoints available
	 * ; or false - there are not promotional pricepoints available
	 */
	public boolean hasPromotions() {
		return this.hasPromotion;
	}


	/**
	 * Returns list of non-promotional pricepoints, only if there 
	 * are no promotional pricepoints available.
	 * @return list of catalog packages if there are no promotional pricepoints
	 * ; or a zero length list if there are promotional pricepoints.
	 * @see PromotionsResult.hasPromotions
	 */
	public CatalogPackage[] getPackages() {
		return this.catalogPackages;
	}

}
