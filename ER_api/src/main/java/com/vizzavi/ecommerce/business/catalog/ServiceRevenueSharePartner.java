/**
 * --------------------------------Modification History--------------------------------
 *
 *      Sr. No.		Date			    Author				Description
 * ------------------------------------------------------------------------------------
 *      [1]			Aug 31, 2005		Sagar More  		Changed for CR
 *                                                          "Purchase Channel Reporting"
 *
 */
package com.vizzavi.ecommerce.business.catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service Revenue Share partner.
 * Part of Partner Revenue Share pahse 2.
 * @author Periasamy
 *
 * @see Revenue Share Technical Design document
 */
public class ServiceRevenueSharePartner implements java.io.Serializable  {

   private    static final long serialVersionUID = 8857818362355764441L;
    protected Long mKey;
    protected String mCreatedBy;
    protected String mModifiedBy;
    protected Date mModifiedDate;
    protected char mActiveStatus;
    /**
     * Partner Id
     */
    protected String mId;

    //[1] Mod Start
    /** Added for ER8 Requirements */
    protected String mPurchaseChannel = "*";
    //[1] Mod End

    /**
     * Format of the threshold
     */
    protected String mRevThreshFormat = "";
    /**
     * The interval at which the threshold accumulator is reset
     */
    protected String mRevThreshCounterReset = "";
    /**
     * priority indicator
     */
    protected String mPriorityFlag;
    /**
     * WH Tax Amount
     */
    protected String mWhTaxAmt;
    /**
     * WH Tax Ofset indicator
     */
    protected String mWhTaxOffset;

    // new attributes for phase2 - partner revenue share
    /**
     * Model type
     */
    protected String mModel;
    /**
     * Fixed payment indicator
     */
    protected String mFixedPaymentFlag;
    /**
     * Fixed Payment details
     */
    protected FixedPayment mFixedPayment;
    /**
     * Collection of Revenue share tiers
     */
    protected List<RevenueShareTier> mRevenueShareTiers = new ArrayList<RevenueShareTier>();

	// CR2210 MPay - Partner Id change
    /**
     * Partner name
     */
    protected String mName;
	// CR2210 MPay - Partner Id change - Ends

    /**
     * default empty constructor
     */
    public ServiceRevenueSharePartner() {}
    /**
     * getting Partner Key
     * @return partner key
     */
    public Long getKey() {
        return mKey;
    }

    public String getCreatedBy() {
        return mCreatedBy;
    }

    public String getModifiedBy() {
        return mModifiedBy;
    }

    public Date getModifiedDate() {
        return mModifiedDate;
    }

    public char getActiveStatus() {
        return mActiveStatus;
    }

    /**
     * getting Partner Id
     * @return partner id
     */
    public String getId() {
        return mId;
    }

    //[1] Mod Start
    /** Added for ER8 Requirements */
    /**
     * getting Purchase Channel
     * @return Purchase Channel
     */
    public String getPurchaseChannel() {
        return mPurchaseChannel;
    }
    //[1] Mod End

    /**
     * getting format of the threshold
     * @return Threshold format
     */
    public String getRevThreshFormat() {
        return mRevThreshFormat;
    }

    /**
     * getting the value of revenue threshold counter reset interval
     * @return revenue threshhold counter reset
     */
    public String getRevThreshCounterReset() {
        return mRevThreshCounterReset;
    }

    /**
     * getting priority indicator
     * @return priority indicator
     */
    public String getPriorityFlag() {
        return mPriorityFlag;
    }

    /**
     * checking the partner for the priority
     * @return true if priority is set or false
     */
    public boolean isPriorityPartner() {
        boolean rv = false;
        if (mPriorityFlag != null && mPriorityFlag.toLowerCase().equals("y") == true) {
            rv = true;
        }
        return rv;
    }

    /**
     * getting WH Tax Amount
     * @return WH Tax Amount
     */
    public String getWhTaxAmt() {
        return mWhTaxAmt;
    }

    /**
     * getting WH Tax Offset Indicator
     * @return WH Tax Offset Indicator
     */
    public String getWhTaxOffset() {
        return mWhTaxOffset;
    }

    /**
     * getting model type
     * @return model type
     */
    public String getModel() {
        return mModel;
    }

    /**
     * getting Fixed Payment details
     * @return Fixed Payment details
     */
    public FixedPayment getFixedPayment() {
        return mFixedPayment;
    }

    /**
     * getting FixedPayment indicator
     * @return FixedPayment indicator
     */
    public String getFixedPaymentFlag() {
        return mFixedPaymentFlag;
    }

    /**
     * getting the collection of the revenue share tier
     * @return Revenue share tier collection
     */
    public List<RevenueShareTier> getRevenueShareTiers() {
        return mRevenueShareTiers;
    }

    /**
     * getting revenueshare tier based on the tier number
     * @param tierNumber position of the revenue share tier
     * @return Revenue share tier
     */
    public RevenueShareTier getTier(int tierNumber) {
        return mRevenueShareTiers.get(tierNumber /*+ 1*/);	// Anthony changed to allow it to run correctly
    }

	// CR2210 MPay - Partner Id change
    /**
	 * @return the mName
	 */
	public String getName() {
		return mName;
	}
	/**
	 * @param mName the mName to set
	 */
	public void setName(String mName) {
		this.mName = mName;
	}
	// CR2210 MPay - Partner Id change - Ends

	@Override
	public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("id=");
        buf.append(getId());
    	// CR2210 MPay - Partner Id change
        buf.append("\nmName=");
        buf.append(getName());
    	// CR2210 MPay - Partner Id change - Ends
        //[1] Mod Start
        buf.append("\nPurchaseChannel=");
        buf.append(getPurchaseChannel());
        //[1] Mod End
        buf.append("\nrevThreshFormat=");
        buf.append(getRevThreshFormat());
        buf.append("\nrevThreshCounterReset=");
        buf.append(getRevThreshCounterReset());
        buf.append("\npriorityFlag=");
        buf.append(getPriorityFlag());
        buf.append("\nwhTaxAmt=");
        buf.append(getWhTaxAmt());
        buf.append("\nwhTaxOffset=");
        buf.append(getWhTaxOffset());
        buf.append("\nmodel=");
        buf.append(getModel());
        buf.append("\nfixedPaymentFlag=");
        buf.append(getFixedPaymentFlag());
        buf.append("\nfixedPayment=");
        buf.append(getFixedPayment().toString());
        buf.append("\nrevenueShareTiers=");
        if (getRevenueShareTiers() != null) {
            for (int i = 0; i < getRevenueShareTiers().size(); i++) {
                buf.append(getRevenueShareTiers().get(i));
            }
        }
        return buf.toString();
    }

}
