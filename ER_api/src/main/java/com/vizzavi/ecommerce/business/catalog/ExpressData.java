package com.vizzavi.ecommerce.business.catalog;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.common.EcomApiFactory;
import com.vizzavi.ecommerce.business.common.ResourceTranslator;
import com.vizzavi.ecommerce.business.selfcare.Transaction;
import com.vodafone.config.ConfigProvider;

/**
*  ExpressData represents the date relative to the express packages
*
*  expressPrice:  the price of the express package and appended the currency symbol
* 
*  priceText: the pricing text of the pricepoint with the price tag %price% replaced by the 
*  expressPrice
*  
*  serviceId: the id of the service included in the express package
*  
*  G : Amal
*  isOption : show if the service does belong to any other non express packages
*
*
* G : Amal
*  isSubscribed : show if the user is subscribed in this service
**/

public class ExpressData implements java.io.Serializable
{
	private static final long serialVersionUID = -3750251805273855581L;
	private String mServiceId = "";
	private String mExpressPrice = "";
	private String mPriceText = "";
	
	private ResourceTranslator mTranslator;
	
  private boolean mIsOption = false;
	private boolean mIsSubscribed = false;
  
	//CR1231
	//static final LWLogger logger =
        //LWSupportFactoryImpl.getInstance().getLogger( ExpressData.class );
	private static Logger logger = Logger.getLogger(ExpressData.class);
	
    //<< @patnalas CQPRD00013468 /CQ14109
    // Though not necessary, but need to clean up the usage of properties parameters
    // if this is seen anywhere
    //# static final String ROUND_NTH_EXPRESS_PATTERN = "ROUND_NTH_EXPRESS_PATTERN";
    // Since the string should be only referenced once, why not just using it directly?
    static final String ROUND_NTH_EXPRESS_PATTERN_DEFAULT = "0.00";
    static final String ROUND_NTH_EXPRESS_PATTERN_INTEGER = "0.##";
    //MQC 6016 - change to private variable from static
    //WAS
    //static final String ROUND_NTH_EXPRESS_PATTERN; 
    //NOW
    private String ROUND_NTH_EXPRESS_PATTERN;
    
    //MQC 6016 - move retrieval of property to local method of where it is used
    //static {
        
    //    String round_nth_express_pattern = PropertyServer.getProperty("ROUND_NTH_EXPRESS_PATTERN");
    //    if (round_nth_express_pattern == null) {
    //        ROUND_NTH_EXPRESS_PATTERN = ROUND_NTH_EXPRESS_PATTERN_DEFAULT;
    //    }
    //    else {
    //        ROUND_NTH_EXPRESS_PATTERN = round_nth_express_pattern;
    //    }
        
    //}
    //>> @hud

        public ExpressData()
        {
            super();
        }

        public ExpressData(ExpressData express)
        {
            mServiceId = express.mServiceId;
            mExpressPrice = express.mExpressPrice;
            mPriceText = express.mPriceText;
            mTranslator = express.mTranslator; 
            mIsOption = express.mIsOption;
            mIsSubscribed = express.mIsSubscribed;
            
        }

 	public void setServiceId(String serviceId)
 	{
 		this.mServiceId = serviceId;
 	}
 	
 	public void setPriceText(String priceText)
 	{
 		this.mPriceText = priceText;
 	}
 	
 	public void setExpressPrice(String expressPrice)
 	{
 		this.mExpressPrice = expressPrice;
 	}
 	
  public void setOption(boolean option)
	{
	    mIsOption = option;
	}
	
  public void setSubscribed(boolean subscribed)
	{
	    mIsSubscribed = subscribed;
	}	

 	public String getServiceId()
 	{
 		return mServiceId;
 	}
 	
 	public String getExpressPrice()
 	{
 		return mExpressPrice;
 	}
 	
 	public String getPriceText()
 	{
 		return mPriceText;
 	}
  
  public boolean isOption()
	{
	  return  mIsOption ;
	}
  
	public boolean isSubscribed()
	{
	  return  mIsSubscribed ;
	}
  
 	public String formatPriceText(String pricingText,String ePrice)
 	{
    String val=null;
    if (pricingText!=null) mPriceText = pricingText.replaceAll("%price%",ePrice);
    return val;
  }
  /**
   * 
   */
 	public String formatExpressPrice(PricePoint pp, Locale locale)
 	{
    try
    {
    logger.debug("Entering ExpressData.formatExpressPrice");
 		ResourceTranslator mTranslator =  EcomApiFactory.getResourceTranslator(locale);
 		@SuppressWarnings("deprecation")
		double rate = pp.getRate();
 		ChargingResource cR = pp.getResource();
        String position                 = mTranslator.translateCurrencyPosition(new Integer(cR.getCode()));
 		String symbol = mTranslator.translateCurrencySymbol(new Integer(cR.getCode()));
   
    if (position.equalsIgnoreCase("Left"))
 		{
 			mExpressPrice = symbol + " " + rate;
 		}else
 		{
 			mExpressPrice = rate + " " + symbol;
 		}
 		}catch (Exception e)
 		{
      e.printStackTrace();
    }
    logger.debug("Express price: "+mExpressPrice);
    logger.debug("Exiting ExpressData.formatExpressPrice");
 		return mExpressPrice;
 	}
 	
    private String formatRate(double rate,  boolean monetry, Locale locale) {
        String strRate = "";
        
        //@hud we must not set default value like this!
        //String pattern =  "0.00";
        
        /* If monetary get the rounding property for the express price and 
          set the rate
          Else convert the rate to an integer and get the positive value
         */ 
        
        //<< @patnalas CQPRD00014109
        //double rv = -1;
        
        if (!monetry) {
            int iRate = new Double(Math.abs(rate)).intValue();
            strRate = new Integer(iRate).toString();
        } else {
            //rv = Transaction.roundDouble(rate, round_nth_decimal);
            
            //strRate = new Double(rv).toString();
            
            NumberFormat nf = NumberFormat.getNumberInstance(locale);
            DecimalFormat df = (DecimalFormat)nf;
            
            //@CQPRD00014109
            // If no decimal, just return an integer.
            // For accurasy, round "rate" first to 0.00
            //REMEDY 6447
    		int round_nth_decimal =  ConfigProvider.getPropertyAsInteger("ROUND_NTH_DECIMAL", 4);
    				
            
            double rate1 = Transaction.roundDouble(rate, round_nth_decimal);
            //END REMEDY 6447
            
            //MQC 6016 - move retrieval of property to local method of where it is used
            String round_nth_express_pattern = ConfigProvider.getProperty("ROUND_NTH_EXPRESS_PATTERN");
            if (round_nth_express_pattern == null) {
                ROUND_NTH_EXPRESS_PATTERN = ROUND_NTH_EXPRESS_PATTERN_DEFAULT;
            }
            else {
                ROUND_NTH_EXPRESS_PATTERN = round_nth_express_pattern;
            }
            
            //double rate1 = (double)Math.round(rate * 100) / 100;
            if (Double.compare(rate1, Math.rint(rate1)) == 0) {
                // apply 0.##
                df.applyPattern(ROUND_NTH_EXPRESS_PATTERN_INTEGER);
            }
            else {
                df.applyPattern(ROUND_NTH_EXPRESS_PATTERN);
            }
            strRate = df.format(rate1);
            
        }
        //>> @patnalas - CQ 14109        
        return strRate;
    }
 
   public String formatExpressPrice(double rate , boolean monetry , ChargingResource cR, Locale locale)
 	{
    try
    {
    logger.debug("Entering ExpressData.formatExpressPrice");
 		ResourceTranslator mTranslator =  EcomApiFactory.getResourceTranslator(locale);
    String symbol           = null;
    String position         = null;
    if(! monetry)     
    {  
      logger.debug("Rate in credit  ");
      position  = mTranslator.translateNonMonetaryPosition(new Integer(cR.getCode()));
      //REMEDY 6404 - also check for rate == 1
      if(rate == -1 || rate == 1)  
        symbol  = mTranslator.translateNonMonetaryName(new Integer(cR.getCode()));
      else
        symbol  = mTranslator.translateMultipleNonMonetaryName(new Integer(cR.getCode())); 
    }   
    else
    {
      logger.debug("Rate in money  ");
      position  = mTranslator.translateCurrencyPosition(new Integer(cR.getCode()));
      symbol    = mTranslator.translateCurrencySymbol(new Integer(cR.getCode()));
    } 

    //REMEDY 3897 - format issues for Express Price
    String strRate = formatRate(rate, monetry, locale);
    
		if (position.equalsIgnoreCase("Left"))
 		{
 			mExpressPrice = symbol + " " + strRate;
 		}else
 			{
 				mExpressPrice = strRate + " " + symbol;
 			}
 		}catch (Exception e)
 		{
      e.printStackTrace();
    }
    logger.debug("Express price: "+mExpressPrice);
    logger.debug("Exiting ExpressData.formatExpressPrice");
 		return mExpressPrice;
 	}
}
	
	
