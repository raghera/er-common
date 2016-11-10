package com.vizzavi.ecommerce.business.catalog;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;



/**
*  ExpressDisplayAttribute represents the date relative to the express packages dispaly
*
*   G : Amal
*  isOption : show if the CAE will display link to the other  non express packages 
*
*
*   G : Amal
*  isHeadline : state whether to display pricePoint 's pricing text1 or price point 's pricing text2
**/

public class ExpressDisplayAttribute implements java.io.Serializable
{
	//MQC 6968
	private boolean fullResponseRequired = false;
	
	private boolean mIsOption = false;

	private boolean mIsHeadline = false;

	private Map<String, Boolean> mOptions = null;

        private    static final long serialVersionUID = -5707095817087348134L;

	
	/*static final LWLogger logger =
        LWSupportFactoryImpl.getInstance().getLogger( ExpressDisplayAttribute.class );
    */
  	

 	public boolean isOption()
	{
	  return  mIsOption ;
	}

 	/**
 	 * Check if the Boolean option value of a serviceId
 	 * if object is not in Map flase will be returned
 	 */
 	public boolean isOption(String sId){
 		boolean val = false; 		
 		if (mOptions!=null &&  (mOptions.get(sId) instanceof Boolean)){              
 			Boolean option = mOptions.get(sId);
 		    if (option != null ) {
 		    	val = option.booleanValue();
 		    }
 		} 		
 		return val;
 	}

 	/**
 	 * Adds one more option to the HMap
 	 * Stirng, Boolean
 	 *  
 	 */
 	public void addOption(String sId,boolean opt){
 		if (mOptions == null){
 			mOptions = new HashMap<String, Boolean>();
 		}
 		if (sId!=null) mOptions.put(sId,new Boolean (opt));
 	}
 	/**
 	 * created or replaces the current hashTable by 
 	 * a new one where all ServiceIds will have a Boolean value set to true 
 	 * format of the hashTable {String, Boolean}
 	 *  
 	 */	
 	public void setOptionsToTrue (String [] sIds){
 		if (sIds!=null){
 			mOptions = new HashMap<String, Boolean>();
 			for (String sId : sIds) {
 				mOptions.put(sId,new Boolean(true));
 			}
 		}
 	}
    /**
     * Allows to replace cuurent options HMap by another
     * (String, Boolean)
     * check for that the value is an instance of Boolean is done in isOption
     * @param opts
     */
 	public void setOptions (Map<String, Boolean> opts){
 		if (opts!=null){
 			mOptions = new HashMap<String, Boolean>();
 			mOptions.putAll(opts);
 		}
 	}
 	
 	//MQC 6968 Start
 	public void setFullResponseRequired(boolean fullResponseRequired) {
		this.fullResponseRequired = fullResponseRequired;
	}
 	
 	public boolean isFullResponseRequired() {
		return fullResponseRequired;
	}
 	//MQC 6968 End
 	
    public void setOption(boolean option)
	{
	    mIsOption = option;
	}
	
	public boolean isHeadline()
	{
	  return  mIsHeadline ;
	}

    public void setHeadline(boolean headline)
	{
	    mIsHeadline = headline;
	}
	
	//@hud STKHREQ12557
	private Locale mLanguageLocale = null;
	public Locale getLanguageLocale() {
		return mLanguageLocale;
	}
	public void setLanguageLocale(Locale languageLocale) {
		mLanguageLocale = languageLocale;
	}
	public String getLanguageCode() {
		if (mLanguageLocale == null) {
			return null;
		}
		else {
			return mLanguageLocale.getLanguage();
		}
	}
	
    

}
	
	
