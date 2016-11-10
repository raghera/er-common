package com.vizzavi.ecommerce.business.common;

/**
* This interface translates values returned from back end into sensible presentation values.
*/
public interface ResourceTranslator	{
    /**
    * Translate a property.
    */
    public String translate(String key);
    
    /**
    * Translate duration.
    */
    public String translateDuration(Integer key);

    /**
    * Translate charging method.
    */
    public String translateChargingMethod(Integer key);

    /**
    * Translate subscription status.
    */
    public String translateSubscriptionStatus(Integer key);

    /**
    * Translate service status.
    */
    public String translateServiceSubscriptionStatus(Integer key);

    /**
    * Translate payment status.
    */
    public String translatePaymentStatus(Integer key);


   /**
    *  Transalate Currency Symbol
    */
    public String translateCurrencySymbol(Integer Key);
    
    /**
    *  Transalate Currency position
    */
    public String translateCurrencyPosition(Integer Key);
    
    /**
    *  Transalate non monetary name
    *  Amal since German Migeration
    */
    public String translateNonMonetaryName(Integer Key);
    
    /**
    *  Transalate multiple non monetary name
    *  Amal since German Migeration
    */
     public String translateMultipleNonMonetaryName(Integer Key);
     
  /**
    *  Transalate non monetary position
    *  Amal since German Migeration
    */
    public String translateNonMonetaryPosition(Integer Key);

}

