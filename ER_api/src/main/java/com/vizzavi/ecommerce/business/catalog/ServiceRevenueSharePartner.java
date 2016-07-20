package com.vizzavi.ecommerce.business.catalog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Service Revenue Share partner.
 * contains a List of {@link RevenueShareTier}s
 */
@Entity
@IdClass(ServiceRevenueSharePartnerKey.class)
public class ServiceRevenueSharePartner implements java.io.Serializable  {

   private    static final long serialVersionUID = 8857818362355764441L;
    protected Long mKey;

    /**
     * Partner Id
     */
    @Id
    protected String mId;

    //[1] Mod Start
    /** Added for ER8 Requirements */
    @Id
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
        //PPM136861 refactoring aL. START
		//fixed logging NPE:
        FixedPayment fp = getFixedPayment();
        if (fp != null){
            buf.append("\nfixedPayment=");
        	buf.append(getFixedPayment().toString());
        }
      //PPM136861 refactoring aL. END
        buf.append("\nrevenueShareTiers=");
        if (getRevenueShareTiers() != null) {
            for (int i = 0; i < getRevenueShareTiers().size(); i++) {
                buf.append(getRevenueShareTiers().get(i));
            }
        }
        return buf.toString();
    }
	
	@Override
	public int hashCode(){
	    return  12* this.mId.hashCode() * this.mPurchaseChannel.hashCode();
	  }
	
	@Override
	public boolean equals(Object object) {
		if(object == null||getClass() != object.getClass())
		{
			return false;
		}
		else{
			ServiceRevenueSharePartner serviceRevenue = (ServiceRevenueSharePartner) object;
			return (this.getId() == serviceRevenue.getId() && this.getPurchaseChannel()==serviceRevenue.getPurchaseChannel());
		}
	     
	}

}