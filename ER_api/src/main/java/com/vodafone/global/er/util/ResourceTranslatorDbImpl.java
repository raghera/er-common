package com.vodafone.global.er.util;

import java.util.Locale;

import org.apache.log4j.Logger;

import com.vizzavi.ecommerce.business.common.ChargingMethod;
import com.vizzavi.ecommerce.business.common.Duration;
import com.vizzavi.ecommerce.business.common.ResourceTranslator;
import com.vizzavi.ecommerce.business.selfcare.SubscriptionStatus;
import com.vodafone.config.ConfigProvider;

/**
* This class translates values returned from back end into presentation values using central translations.<br/>
* CR1607 aL. - Vodafone requested deprecation of 
* <code>com.vodafone.global.er.util.ResourceTranslatorImpl</code>
* in favour of this class which retrieves resource translations 
* from the central configuration database
* instead of from file.
*/
public class ResourceTranslatorDbImpl implements ResourceTranslator
{
	private static final Logger logger = Logger.getLogger(ResourceTranslatorDbImpl.class);
	private final Locale locale; 

	/**
	 * 
	 * @param locale
	 * @param bundle not used
	 */
    public ResourceTranslatorDbImpl( Locale locale, String bundle )
    {
    	this(locale);
    }

    public ResourceTranslatorDbImpl( Locale locale )
    {
    	if (locale == null){
    		throw new RuntimeException("Locale used for translation is null.");
    	}
    	this.locale = locale;
    	if (locale.getLanguage()==null || locale.getLanguage().length() == 0){
    		//shouldn't happen
    		logger.error("Locale used for translation is returning an empty string for the language.");
    		throw new RuntimeException("Locale used for translation is returning an empty string for the language.");
    	}
    }

    @Override
	public String translate(String key)
    {
        return ConfigProvider.translate(locale, key);
    }

    private String translate( String resourceName, Object object )
    {
        String translated = null;
        if( resourceName != null ) {
            translated = translate( resourceName );
        }
        // check if we found a translation. 
        if( translated == null && object != null ) {
            // just do a toString on the object
            translated = object.toString();
        }
        return translated;
    }


    /**
    * Translate duration.
    */
    @Override
	@SuppressWarnings("deprecation")
	public String translateDuration(Integer duration)
    {
        String str = "";
        if(duration != null){
            str = translate( Duration.getResourceName( duration.intValue() ), duration );
        }
        return str;
    }
        

    /**
    * Translate charging method.
    */
    @Override
	public String translateChargingMethod(Integer chargingMethod)
    {
        String str = "";
        if(chargingMethod != null){
            str = translate( ChargingMethod.getResourceName( chargingMethod.intValue() ), chargingMethod );
        }
        return str;
    }
    
    /**
    * Translate subscriptionStatus.
    */
    @Override
	public String translateSubscriptionStatus(Integer subscriptionStatus)
    {
        String str = "";
        if(subscriptionStatus != null){
            str = translate( 
                SubscriptionStatus.getResourceName( subscriptionStatus.intValue() ), subscriptionStatus );
        }
        return str;
    }

    /**
    * Translate subscriptionStatus.
    */    
    @Override
	public String translateServiceSubscriptionStatus(Integer status)
    {
        String str = "";
        if(status != null){
            str = translate( "ServiceSubscriptionStatus_" + status.intValue());
        }
        return str;
    }
    
    /**
    * Translate payment status.
    */
    @Override
	public String translatePaymentStatus(Integer status)
    {
        String str = "";
        if(status != null){
            str = translate( "PaymentStatus_" + status.intValue());
        }
        return str;
        
    }
    
    /**
    *  Transalate Currency Symbol
    */
    @Override
	public String translateCurrencySymbol(Integer chargingResource)
    {
      String str = "";
      if (chargingResource != null){
        str = translate("ChargingResourceCurrencySymbol_"+chargingResource.intValue());
        }
        return str;
    }
    
    /**
    *  Transalate Currency position
    */
    @Override
	public String translateCurrencyPosition(Integer chargingResource)
    {
      String str = "";
      if (chargingResource != null){
        str = translate("ChargingResourceCurrencyPosition_"+chargingResource.intValue());
        }
      return str;
    }
    
    /**
    *  Transalate  non monetary name
    *  @since German migration
    */
     @Override
	public String translateNonMonetaryName(Integer chargingResource)
     {
      String str = "";
      if (chargingResource != null){
        str = translate("ChargingResourceNonMonetaryName_"+chargingResource.intValue());
        }
      return str;
     }
    
    /**
    *  Transalate multiple non monetary name
    *  @since German migration
    */
     @Override
	public String translateMultipleNonMonetaryName(Integer chargingResource)
     {
      String str = "";
      if (chargingResource != null){
        str = translate("ChargingResourceMultipleNonMonetaryName_"+chargingResource.intValue());
        }
      return str;
     }
     
  /**
    *  Transalate non monetary position
    *  @since German migration
    */
    @Override
	public String translateNonMonetaryPosition(Integer chargingResource)
    {
      String str = "";
      if (chargingResource != null){
        str = translate("ChargingResourceNonMonetaryPosition_"+chargingResource.intValue());
        }
      return str;
    }
    
}

