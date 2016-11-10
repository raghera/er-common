package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;

/**
 *
 * This is an object for storing the Fixed Payment details
 * <p>Copyright: Copyright (c) 2003</p>
 * @author Periasamy
 * @version 1.0
 */
public class FixedPayment implements Serializable {

   private    static final long serialVersionUID = -183739091948476282L;
    protected Long mKey;
    
    /**
     * Fixed Payment Amount
     */
    protected String mAmount;
    /**
     * Fixed Payment Step amount
     */
    protected String mStepAmount;
    /**
     * Fixed payment Upper Limit
     */
    protected String mUpperLimit;
    /**
     * Frequency of Fixed Payment
     */
    protected String mFrequency;
    /**
     * Number of Periods for Fixed Payment
     */
    protected String mNumberOfPeriods;
    /**
     * Retain/revert indicator
     */
    protected String mRetainRevert;

    /**
     * Default empty constructor
     */
    public FixedPayment() {
    }

	/**
     * Getter method for Key
     * @return Key
     */
    public Long getKey() {
		return mKey;
	}


    /**
     * getter method for Amount
     * @return amount
     */
    public String getAmount() {
        return mAmount;
    }

    /**
     * Getter method for step amount
     * @return step amount
     */
    public String getStepAmount() {
        return mStepAmount;
    }

    /**
     * getter method for Upper Limit for the Fixed Payment
     * @return upper limit
     */
    public String getUpperLimit() {
        return mUpperLimit;
    }

    /**
     *
     * @return
     */
    public String getFrequency() {
        return mFrequency;
    }

    public String getNumberOfPeriods() {
        return mNumberOfPeriods;
    }

    public String getRetainRevert() {
        return mRetainRevert;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("\namount=");
        buf.append(getAmount());
        buf.append("\nstepAmount=");
        buf.append(getStepAmount());
        buf.append("\nupperLimit=");
        buf.append(getUpperLimit());
        buf.append("\nfrequency=");
        buf.append(getFrequency());
        buf.append("\nnumberOfPeriods=");
        buf.append(getNumberOfPeriods());
        buf.append("\nretainRevert=");
        buf.append(getRetainRevert());
        return buf.toString();
    }

}
