package com.vizzavi.ecommerce.business.catalog;

/**
 *
 * This is part of the Partner revenue share phase 2
 *
 * @author Periasamy
 * @version 1.0
 */
public class RevenueShareTier implements java.io.Serializable {

   private    static final long serialVersionUID = 5337890564955438882L;
    protected Long mKey;

    /**
     * Share percentage
     */
    protected String mSharePercentage;
    /**
     * Direct Fixed Amount
     */
    protected String mDirectFixedAmount;

    /**
     * Indirect Fixed Amount
     */
    protected String mIndirectFixedAmount;

    /**
     * Indirect Fixed Amount
     */
    protected String mIndirectFixedAmountPromo;

    /**
     * Minimum threshold amount
     */
    protected String mMinimumThreshold;
    /**
     * Maximum threshold amount
     */
    protected String mMaximumThreshold;

    /**
     * ER8 req. for PRS8
     */
    protected String mPurchaseChannel;

    /**
     * default empty constructor
     */
    public RevenueShareTier() {
    }

    /**
     * Getter method for Key
     * @return Key
     */
    public Long getKey() {
		return mKey;
	}

	/**
     * Getter method for Share Percentage
     * @return Share Percentage
     */
    public String getSharePercentage() {
        return mSharePercentage;
    }

    /**
     * Getter method for Direct Fixed Amount
     * @return Direct fixed amount
     */
    public String getDirectFixedAmount() {
        return mDirectFixedAmount;
    }

    /**
     * getter method for Indirect Fixed Amount
     * @return Indirect fixed amount
     */
    public String getIndirectFixedAmount() {
        return mIndirectFixedAmount;
    }

    /**
     * getter method for Indirect Fixed Amount
     * @return Indirect fixed amount
     */
    public String getIndirectFixedAmountPromo() {
        return mIndirectFixedAmountPromo;
    }

    /**
     * getter method for minimum threshold amount
     * @return Minimum threshold amount
     */
    public String getMinimumThreshold() {
        return mMinimumThreshold;
    }

    /**
     * getter method for Maximum Threshold Amount
     * @return Maximum threshold amount
     */
    public String getMaximumThreshold() {
        return mMaximumThreshold;
    }

	public String getPurchaseChannel(){
		return mPurchaseChannel;
	}

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("\nsharePercentage=");
        buf.append(getSharePercentage());
        buf.append("\ndirectFixedAmount=");
        buf.append(getDirectFixedAmount());
        buf.append("\nindirectFixedAmount=");
        buf.append(getIndirectFixedAmount());
        buf.append("\nminimumThreshold=");
        buf.append(getMinimumThreshold());
        buf.append("\nmaximumThreshold=");
        buf.append(getMaximumThreshold());
        buf.append("\nmPurchaseChannel=");
        buf.append(getPurchaseChannel());
        return buf.toString();
    }

}
