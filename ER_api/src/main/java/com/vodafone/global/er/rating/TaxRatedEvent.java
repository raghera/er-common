/**
--------------------------------Modification History----------------------------------------------------------

        Sr. No.     Date        Author      Description
--------------------------------------------------------------------------------------------------------------
        [1]      04-19-2006     Ly Le      	CQPRD00014118:
        									Fix problem with rate is not rounded correctly
*/
package com.vodafone.global.er.rating;

import java.util.Date;

import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.util.MiscUtils;
import com.vodafone.config.ConfigProvider;


/**
    This is returned by a content usage
*/
public class TaxRatedEvent extends RatedEvent implements java.io.Serializable
{
   private    static final long serialVersionUID = 7750020846664979341L;

    /**
        The tax rate applied
    */
    protected double mTaxRate = 0.0;

    /**
        The associated tax code
    */
    protected String mTaxCode;

    /**
        The tax amount
    */
    protected double mTaxAmount = 0.0;

    /**
     * the alternative tax code (the tax code of the alternative price point)
     * @version ER8-2
     */
    protected String  mAlternativeTaxCode;
    /**
     * the alternative tax rate (the tax rate of the alternative price point)
     * @version ER8-2
     */
    protected double mAlternativeTaxRate;
    /**
     * the alternative tax amount (the tax amount of the alternative price point)
     * @version ER8-2
     */
    protected double mAlternativeTaxAmount;
    
    public static final String ROUND_NTH_DECIMAL = "ROUND_NTH_DECIMAL";
    
    
    public TaxRatedEvent()
    {
    }

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public TaxRatedEvent(RatedEvent event) {
        this(event, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
    public TaxRatedEvent(RatedEvent event, Date date)
    {
        super(event, date);
        if (event instanceof TaxRatedEvent) {
            TaxRatedEvent opt = (TaxRatedEvent)event;
            mTaxRate = opt.mTaxRate;
            mTaxCode = opt.mTaxCode;
            mTaxAmount = opt.mTaxAmount;
        }
    }

    /**
        The tax rate applied
    */
    public double getTaxRate()
    {
        return mTaxRate;
    }

    /**
        The tax identifier
    */
    public String getTaxCode()
    {
        return mTaxCode;
    }

    /**
        The amount of tax included in the price
    */
    public double getTaxAmount()
    {
        return mTaxAmount;
    }

    /**
     * This method returns the rate = net rate + tax amount
     * The rate returned will be rounded to the number of decimals
     * specified by default or a configurable property 
    */
    public double getRate()
    {
 
    	int round_nth_decimal = ConfigProvider.getPropertyAsInteger(ROUND_NTH_DECIMAL, -1);
        
    	if (round_nth_decimal == -1) {
    		return (getNetRate() + getTaxAmount());
    	}
        // performing rounding before returning.
    	//MQC 5478 - for the case where we only have a net rate and tax amount i.e. the next payment amount use case, use the old result calculation
    	double result = 0.0;
    	if (getTaxRate() == 0.0 && getTaxAmount() > 0.0) {
        	result = MiscUtils.roundDouble(getNetRate() + getTaxAmount(),round_nth_decimal);
        }
    	//MQC 5478 - for all other use cases use new rounding calculation
    	else {
    		result = getNetRate() + getNetRate() * getTaxRate();
    		result = MiscUtils.roundDouble(result,round_nth_decimal);
    	}
    	//[1] CQ14118 - end
        return result;
    }

    public double getStandardRate()
    {
        int currencyId = getCurrencyId();
        double rv = getNetStandardRate();
        if (ChargingResource.isCurrencyResource(currencyId)) {
            rv = (1 + getTaxRate()) * rv;
        }
        return rv;
    }

    /**
        The tax rate applied
    */
    public void setTaxRate(double val)
    {
        mTaxRate = val;
    }

    /**
        The tax identifier
    */
    public void setTaxCode(String val)
    {
        mTaxCode = val;
    }

    /**
        The amount of tax included in the price
    */
    public void setTaxAmount(double val)
    {
        mTaxAmount = val;
    }

    @Override
    public String toString() {
    	return "TaxRatedEvent [mTaxRate=" + mTaxRate + ", mTaxCode=" + mTaxCode
    			+ ", mTaxAmount=" + mTaxAmount + ", mAlternativeTaxCode="
    			+ mAlternativeTaxCode + ", mAlternativeTaxRate="
    			+ mAlternativeTaxRate + ", mAlternativeTaxAmount="
    			+ mAlternativeTaxAmount + "]";
    }

  public void setAlternativeTaxCode(String mAlternativeTaxCode)
  {
    this.mAlternativeTaxCode = mAlternativeTaxCode;
  }




public String getAlternativeTaxCode()
  {
    return mAlternativeTaxCode;
  }


  public void setAlternativeTaxRate(double mAlternativeTaxRate)
  {
    this.mAlternativeTaxRate = mAlternativeTaxRate;
  }

  public double getAlternativeTaxRate()
  {
    return mAlternativeTaxRate;
  }

  public void setAlternativeTaxAmount(double mAlternativeTaxAmount)
  {
    this.mAlternativeTaxAmount = mAlternativeTaxAmount;
  }

  public double getAlternativeTaxAmount()
  {
    return mAlternativeTaxAmount;
  }

@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	long temp;
	temp = Double.doubleToLongBits(mAlternativeTaxAmount);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime
			* result
			+ ((mAlternativeTaxCode == null) ? 0 : mAlternativeTaxCode
					.hashCode());
	temp = Double.doubleToLongBits(mAlternativeTaxRate);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(mTaxAmount);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((mTaxCode == null) ? 0 : mTaxCode.hashCode());
	temp = Double.doubleToLongBits(mTaxRate);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (!(obj instanceof TaxRatedEvent))
		return false;
	TaxRatedEvent other = (TaxRatedEvent) obj;
	if (Double.doubleToLongBits(mAlternativeTaxAmount) != Double
			.doubleToLongBits(other.mAlternativeTaxAmount))
		return false;
	if (mAlternativeTaxCode == null) {
		if (other.mAlternativeTaxCode != null)
			return false;
	} else if (!mAlternativeTaxCode.equals(other.mAlternativeTaxCode))
		return false;
	if (Double.doubleToLongBits(mAlternativeTaxRate) != Double
			.doubleToLongBits(other.mAlternativeTaxRate))
		return false;
	if (Double.doubleToLongBits(mTaxAmount) != Double
			.doubleToLongBits(other.mTaxAmount))
		return false;
	if (mTaxCode == null) {
		if (other.mTaxCode != null)
			return false;
	} else if (!mTaxCode.equals(other.mTaxCode))
		return false;
	if (Double.doubleToLongBits(mTaxRate) != Double
			.doubleToLongBits(other.mTaxRate))
		return false;
	return true;
}
}
	