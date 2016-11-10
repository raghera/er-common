package com.vodafone.global.er.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.common.ChargingMethod;
import com.vizzavi.ecommerce.business.common.Duration;
import com.vizzavi.ecommerce.business.common.ResourceTranslator;
import com.vizzavi.ecommerce.business.selfcare.SubscriptionStatus;

/**
* This class translates values returned from back end into sensible presentation values.
* CR1607 aL. - Vodafone requested deprecation of this class in favour of 
* <code> com.vodafone.global.er.util.ResourceTranslatorDbImpl</code>
* which retrieves resource translations from the central configuration database
* instead of from file.
* @see com.vodafone.global.er.util.ResourceTranslatorDbImpl
*/
public class ResourceTranslatorImpl implements ResourceTranslator
{
    final static String DEFAULT_BUNDLE = "ChargingTranslatorCore";
    private static final Logger logger = LoggerFactory.getLogger(ResourceTranslatorImpl.class);
   // private final EcommerceTranslator ecommerceTranslator;
    private static ResourceBundle res;
    
    public ResourceTranslatorImpl( Locale locale, String bundle )
    {
    	try	{
    		res = ResourceBundle.getBundle(bundle, locale);
    	} catch (MissingResourceException e)	{
    		logger.warn("{} {}",e.getMessage(), locale);
    	}
    	
       // this.ecommerceTranslator = EcommerceTranslator.getInstance( bundle, locale  );
    }

    public ResourceTranslatorImpl( Locale locale )
    {
        this( locale, DEFAULT_BUNDLE  );
    }

    @Override
	public String translate(String key)
    {
    	String result =key;// ecommerceTranslator.getProperty(key);
    	try	{
    		result = res.getString(key);
    	}	catch (MissingResourceException e)	{
    		logger.warn(e.getMessage());
    	}	catch (NullPointerException npe)	{
    		//if res is null because no ChargingTranslatorCore properties file was found
    		logger.warn("no ChargingTranslatorCore bundle loaded");
    	}
    	return result;
        //return ecommerceTranslator.getProperty(key);
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

