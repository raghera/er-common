/*
 * Created on 25-Apr-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vizzavi.ecommerce.business.catalog;

import java.util.HashMap;
import java.util.Map;

import com.vizzavi.ecommerce.business.common.Constants;

/**
 *
 *
 * 
 */

public class SuperCreditPricePoint extends PricePoint{

	
    //REMEDY 6687
	private static final long serialVersionUID = -2840201713662072620L;
    //END REMEDY 6687

	
	protected boolean mActive;
	protected String mRateIdentifier;
	protected String mSuperCreditText;
	
    /** The list of super credit text in multiple languages including the default language. 
     * Key is the language code.  
     * Since ER9 **/
    protected HashMap<String, String> superCreditTextList = null;
		
	public SuperCreditPricePoint () {
		
	}
	
	public SuperCreditPricePoint(SuperCreditPricePoint scpt ){
		super ( scpt );
		this.mActive = scpt.isActive();
	}
	
	
	@Override
	public boolean isActive() {
		return mActive;
	}
		
	public String getRateIdentifier() {
		return mRateIdentifier;
	}
	
	public String getSuperCreditText() {
		return getSuperCreditText(Constants.DEFAULT_LANGUAGE_CODE);
	}
	
	public Map<String, String> getSuperCreditTextList() {
		return superCreditTextList;
	}

	public void setSuperCreditTextList(HashMap<String, String> superCreditTextList) {
		this.superCreditTextList = superCreditTextList;
	}


	public String getSuperCreditText(String languageCode){
		if (this.superCreditTextList == null || this.superCreditTextList.isEmpty()){
			return null;
		}
		
		if (languageCode == null || languageCode.length() <= 0){
			languageCode = Constants.DEFAULT_LANGUAGE_CODE;
		}
		
		if (superCreditTextList.get(languageCode) != null){		
			return this.superCreditTextList.get(languageCode);
		}
		
		return this.superCreditTextList.get(Constants.DEFAULT_LANGUAGE_CODE);
	}
	
    public void setSuperCreditText(String text)
    {
    	//REMEDT 6687
    	mSuperCreditText = text;
    	//END REMEDY 6697
    	
    	setSuperCreditText(Constants.DEFAULT_LANGUAGE_CODE, text);
    }


    public void setSuperCreditText(String language, String text){
    	if (text == null){
    		text = new String();    		
    	}
    	
    	if (this.superCreditTextList == null){
    		this.superCreditTextList = new HashMap<String, String>();
    	}
    	
		if (language == null || language.length() <= 0){
			language = Constants.DEFAULT_LANGUAGE_CODE;
		}		
 	
    	//REMEDT 6687
    	mSuperCreditText = text;
    	//END REMEDY 6697
    	
    	this.superCreditTextList.put(language, text);    	
    }	
	
}
