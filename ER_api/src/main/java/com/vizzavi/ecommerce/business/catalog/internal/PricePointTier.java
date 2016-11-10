package com.vizzavi.ecommerce.business.catalog.internal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.vizzavi.ecommerce.business.common.Constants;

/**
 * this class was used for the pricing models like date time models.  Now deprecated since pricing models were removed for priceplan refactor.
 * @author matt
 *
 */
@Deprecated
public class PricePointTier implements Serializable {
	
   private    static final long serialVersionUID = -7695521634730385286L;
	private Long mKey;

	private Double promotionalPrice;
	private BalanceImpacts balanceImpacts = new BalanceImpacts();
	private String tier;
	private String pricingModel;
	
	/** ID of a default tier. */
	public static final String PPT_DEFAULT_ID = "default";
	
    /** The list of Promotional Pricing Text in multiple languages including the default language. 
     * Key is the language code.  
     * Since ER9 **/
    private Map<String, String> promotionalPricingTextList = null;	
	
    public PricePointTier()	{
    }
    
    public PricePointTier(BalanceImpacts impacts)	{
    	this.balanceImpacts = impacts;
    	tier = PPT_DEFAULT_ID;
    	//TODO what about pricingModel?
    }
	/**
	 * @return
	 */
	public Long getKey() {
		return mKey;
	}

	public void setKey(Long key){
		mKey = key;
	}

	/**
	 * @return
	 */
	public BalanceImpacts getBalanceImpacts() {
		return balanceImpacts;
	}

	/**
	 * @return
	 */
	public boolean isDefaultPPT() {
		//return defaultPPT;
		return PPT_DEFAULT_ID.equals(tier);
	}

	/**
	 * normally null, unless this is an active model e.g. catalog_pricing_model id="barttest2"
	 * @return
	 */
	public String getPricingModel() {
		return pricingModel;
	}

	/**
	 * @return
	 */
	public Double getPromotionalPrice() {
		return promotionalPrice;
	}

	/**
	 * @return
	 */
	public String getTier() {
		return tier;
	}

	/**
	 * @param impacts
	 */
	public void setBalanceImpacts(BalanceImpacts impacts) {
		balanceImpacts = impacts;
	}


	/**
	 * @param string
	 */
	public void setPricingModel(String string) {
		pricingModel = string;
	}

	/**
	 * @param d
	 */
	public void setPromotionalPrice(Double d) {
		promotionalPrice = d;
	}

	/**
	 * @param string
	 */
	public void setTier(String string) {
		tier = string;
	}
	
	/**
	 * the text to display e.g. "now on special offer for happy hour!"
	 * @return
	 */
	public String getPromotionalPricingText() {
		return getPromotionalPricingText(Constants.DEFAULT_LANGUAGE_CODE);
	}
	
	public Map<String, String> getPromotionalPricingTextList() {
		return promotionalPricingTextList;
	}

	public void setPromotionalPricingTextList(HashMap<String, String> promPricingTextList) {
		this.promotionalPricingTextList = promPricingTextList;
	}


	public String getPromotionalPricingText(String languageCode){
		if (this.promotionalPricingTextList == null || this.promotionalPricingTextList.isEmpty()){
			return null;
		}
		
		if (languageCode == null || languageCode.length() <= 0){
			languageCode = Constants.DEFAULT_LANGUAGE_CODE;
		}
		
		if (promotionalPricingTextList.get(languageCode) != null){		
			return this.promotionalPricingTextList.get(languageCode);
		}
		
		return this.promotionalPricingTextList.get(Constants.DEFAULT_LANGUAGE_CODE);
	}
	
    public void setPromotionalPricingText(String text)
    {
    	setPromotionalPricingText(Constants.DEFAULT_LANGUAGE_CODE, text);
    }


    public void setPromotionalPricingText(String language, String text){
    	if (text == null){
    		text = new String();    		
    	}
    	
    	if (this.promotionalPricingTextList == null){
    		this.promotionalPricingTextList = new HashMap<String, String>();
    	}
    	
		if (language == null || language.length() <= 0){
			language = Constants.DEFAULT_LANGUAGE_CODE;
		}		
 	
    	this.promotionalPricingTextList.put(language, text);    	
    }		

}
