package com.vizzavi.ecommerce.business.catalog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.vizzavi.ecommerce.business.common.ChargingResource;

/**
 * CR1789
 * used in CatalogApi.findPackagesByServiceIdOneStep()
 * Created: 20 Oct 2010, 2010
 * @author Alex Romanin
 * 
 *
 */
public class OneStepData extends ExpressData {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8363627707681474830L;
	
	//format for subscription end date.
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private String mCreditsLeft = "";
	private Date mSubscriptionEndDate = null;
	private boolean mIsExpressPackage = true; //default!
	private boolean mHasPurchaseOptions = false;
	
	//these are used internally in er core for sorting, not required for decoupling response
	private boolean isAlternativePayment = false;
	private boolean isMonetry = false;
	private double rate = 0.0;
	private ChargingResource resource = null;
	
	//MQC 6388 - add additional attributes
	private double netRate = 0.0;
	private String pricingText1 = "";
	private String pricingText2 = "";
	
	
	   /**
	 * @return the isAlternativePayment
	 */
	public boolean isAlternativePayment() {
		return isAlternativePayment;
	}

	/**
	 * @param isAlternativePayment the isAlternativePayment to set
	 */
	public void setAlternativePayment(boolean isAlternativePayment) {
		this.isAlternativePayment = isAlternativePayment;
	}
	
	

	
	
	/**
	 * @return the isMonetry
	 */
	public boolean isMonetry() {
		return isMonetry;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @return the resource
	 */
	public ChargingResource getResource() {
		return resource;
	}

	/* (non-Javadoc)
	 * @see com.vizzavi.ecommerce.business.catalog.ExpressData#formatExpressPrice(double, boolean, com.vizzavi.ecommerce.business.common.ChargingResource, java.util.Locale)
	 */
	@Override
	public String formatExpressPrice(double rate, boolean monetry,
			ChargingResource cR, Locale locale) {
		
		this.rate = rate;		
		this.resource = cR;
		this.isMonetry = monetry;
		return super.formatExpressPrice(rate, monetry, cR, locale);
	}
	
	/**
	    * @return the mCreditsLeft
	    */
	   public String getCreditsLeft() {
		   return mCreditsLeft;
	   }
	   
	   
	   /**
	    * PPM136861 refactoring aL. added
	    * @return
	    */
	   public double getCreditsLeftAsDouble(){
		   double d;
		   try {
			   d = Double.parseDouble(mCreditsLeft);
		   } catch (NumberFormatException e) {
			   d = 0.0;
		   }

		   return d;
	   }

	   /**
	    * @param mCreditsLeft the mCreditsLeft to set
	    */
	   public void setCreditsLeft(String mCreditsLeft) {
		   this.mCreditsLeft = mCreditsLeft;
	   }

	   /**
	    * @return the mSubscriptionEndDate
	    */
	   public String getSubscriptionEndDate() {
		   if (mSubscriptionEndDate == null){
			   return "";
		   }else{
			   return sdf.format(mSubscriptionEndDate);
		   }		  
	   }

	   /**
	    * @param mSubscriptionEndDate the mSubscriptionEndDate to set
	    */
	   public void setSubscriptionEndDate(Date mSubscriptionEndDate) {
		   this.mSubscriptionEndDate = mSubscriptionEndDate;
	   }

	   /**
	    * @return the mIsExpressPackage
	    */
	   public boolean isExpressPackage() {
		   return mIsExpressPackage;
	   }

	   /**
	    * @param mIsExpressPackage the mIsExpressPackage to set
	    */
	   public void setIsExpressPackage(boolean mIsExpressPackage) {
		   this.mIsExpressPackage = mIsExpressPackage;
	   }

	   /**
	    * @return the mHasPurchaseOptions
	    */
	   public boolean hasPurchaseOptions() {
		   return mHasPurchaseOptions;
	   }

	   /**
	    * @param mHasPurchaseOptions the mHasPurchaseOptions to set
	    */
	   public void setHasPurchaseOptions(boolean mHasPurchaseOptions) {
		   this.mHasPurchaseOptions = mHasPurchaseOptions;
	   }

		/**
		 * MQC 6388
		 * @return the net rate
		 */
		public double getNetRate() {
			return netRate;
		}
		
		/**
		 * MQC 6388
		 * @param mNetRate the net rate to set
		 */
		public void setNetRate(double mNetRate) {
			this.netRate = mNetRate;
		}
		   
		/**
		 * MQC 6388
		 * @return the pricing text1
		 */
		public String getPricingText1() {
			return pricingText1;
		}
		
		/**
		 * MQC 6388
		 * @param mPricingText1 the PricingText1 to set
		 */
		public void setPricingText1(String mPricingText1) {
			this.pricingText1 = mPricingText1;
		}
		
		/**
		 * MQC 6388
		 * @return the pricing text2
		 */
		public String getPricingText2() {
			return pricingText2;
		}
		
		/**
		 * MQC 6388
		 * @param mPricingText2 the PricingText2 to set
		 */
		public void setPricingText2(String mPricingText2) {
			this.pricingText2 = mPricingText2;
		}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OneStepData [getCreditsLeft()=" + getCreditsLeft()
				+ ", getSubscriptionEndDate()=" + getSubscriptionEndDate()
				+ ", hasPurchaseOptions()=" + hasPurchaseOptions()
				+ ", isSubscribed()=" + isSubscribed()				
				+ ", isExpressPackage()=" + isExpressPackage()
				+ ", getExpressPrice()=" + getExpressPrice()
				+ ", getPriceText()=" + getPriceText() 
				+ ", getServiceId()=" + getServiceId() 
				//MQC 6388 - add additional attributes
				+ ", getNetRate()=" + getNetRate()
				+ ", getPricingText1()=" + getPricingText1()
				+ ", getPricingText2()=" + getPricingText2()
				+ "]";
	}
}
