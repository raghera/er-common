package com.vizzavi.ecommerce.business.catalog.internal;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import com.vizzavi.ecommerce.business.common.Constants;


public class PricePointTier implements Serializable {
	
   private    static final long serialVersionUID = -7695521634730385286L;
	private Long mKey;
    private String mCreatedBy;
    private String mModifiedBy;
    private Date mModifiedDate;
    private char mActiveStatus;
	//private String promotionalPricingText;
	private Double promotionalPrice;
	private BalanceImpacts balanceImpacts = new BalanceImpacts();
	private String tier;
	private String pricingModel;
	private boolean defaultPPT = false ;
	
	/** ID of a default tier. */
	public static final String PPT_DEFAULT_ID = "default";
	
    /** The list of Promotional Pricing Text in multiple languages including the default language. 
     * Key is the language code.  
     * Since ER9 **/
    private HashMap<String, String> promotionalPricingTextList = null;	
	
	/**
	 * @return
	 */
	public Long getKey() {
		return mKey;
	}

	public void setKey(Long key){
		mKey = key;
	}

    public String getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        mCreatedBy = createdBy;
    }

    public String getModifiedBy() {
        return mModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        mModifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return mModifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        mModifiedDate = modifiedDate;
    }

    public char getActiveStatus() {
        return mActiveStatus;
    }
    
	public void setActiveStatus(char activeStatus) {
    	mActiveStatus = activeStatus;
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
		return defaultPPT;
	}

	/**
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
	 * @param b
	 */
	public void setDefaultPPT(boolean b) {
		defaultPPT = b;
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
	
	public String getPromotionalPricingText() {
		return getPromotionalPricingText(Constants.DEFAULT_LANGUAGE_CODE);
	}
	
	public HashMap<String, String> getPromotionalPricingTextList() {
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
