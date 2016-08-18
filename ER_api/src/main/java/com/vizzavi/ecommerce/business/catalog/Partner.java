package com.vizzavi.ecommerce.business.catalog;

import java.util.Date;

public class Partner implements java.io.Serializable
{
   private    static final long serialVersionUID = 1388584363234222606L;

    public final static String PARTNER_TYPE_VCS = "V";
    public final static String PARTNER_TYPE_OPCO = "O";
    public final static String PARTNER_TYPE_CONTENT = "C";
    
    public final static String CONTRACT_TYPE_AGENCY = "A";
    public final static String CONTRACT_TYPE_RESELLER = "R";

   //Added for ITCR007   Ramanj 2008 01 13 
    public final static String GROUP_REV_TYPE_YES = "Y";
    public final static String GROUP_REV_TYPE_NO = "N";
    
    public final static String PARTNER_CATEGORY_AGGREGATOR = "Aggregator"; // CR2040 MPAY replacement.  Partner category.
    public final static String PARTNER_CATEGORY_MERCHANT = "Merchant"; // CR2040 MPAY replacement.  Partner category.
    public final static String PARTNER_CATEGORY_SERVICE_PROVIDER = "ServiceProvider"; // CR2040 MPAY replacement.  Partner category.
    public final static String PARTNER_CATEGORY_PARTNER = "Partner"; // CR2040 MPAY replacement.  Partner category.
    
    protected Long mKey;
    protected String mCreatedBy;
    protected String mModifiedBy;
    protected Date mModifiedDate;
    protected char mActiveStatus;
    protected String mId;
    protected String mName;
    protected String mType;
    protected String mGlobalOrLocal;
    protected String mGlobalDealRef;
    protected String mContractType;
    protected String mInvVat;
    protected String mSalesVat;
    protected String mGroupRevShare;  //Added for ITCR007   Ramu 2008 01 13  

	// CR1503 - Multi Price plans - phase 2
    /** This attribute holds the external price plan's name (price plan used to import the partner). */
	protected String mExternalPrtnPricePlan;
	
	
	protected GoodwillCreditLimits goodwillCreditLimits; // CR2040 MPAY replacement.  Goodwill credit limits.
	protected SpendLimits spendLimits; // CR2040 MPAY replacement.  Spend limits.
	protected String partnerCategory; // CR2040 MPAY replacement.  Partner category."Merchant", "Aggregator", "ServiceProvider" or "Partner"
    protected String aggregator; // CR2040 MPAY replacement.  Aggregator associated with merchant.      
	
    public Partner() {}
    
    public Partner(String id, String name, String type, String globalOrLocal,
    		String globalDealRef, String contractType, String invVat, String salesVat) {
            
        mId = id;
        mName = name;
        mType = type;
        mGlobalOrLocal = globalOrLocal;
        mGlobalDealRef = globalDealRef;
        mContractType = contractType;
        mInvVat = invVat;
        mSalesVat = salesVat; 
    }
    
    
    public Partner(String id, String name, String type, String globalOrLocal,
    		String globalDealRef, String contractType, String invVat, String salesVat,String groupRevShare) {
            
        mId = id;
        mName = name;
        mType = type;
        mGlobalOrLocal = globalOrLocal;
        mGlobalDealRef = globalDealRef;
        mContractType = contractType;
        mInvVat = invVat;
        mSalesVat = salesVat;  
        mGroupRevShare = groupRevShare;        
    }
    public Partner(Long key, String id, String name, String type, String globalOrLocal,
            String globalDealRef, String contractType, String invVat, String salesVat,
            String groupRevShare,String createdBy, String modifiedBy, Date modifiedDate, char activeStatus) {
            
		mKey = key;
        mCreatedBy = createdBy;
		mModifiedBy = modifiedBy;
        mModifiedDate = modifiedDate;
    	mActiveStatus = activeStatus;

        mId = id;
        mName = name;
        mType = type;
        mGlobalOrLocal = globalOrLocal;
        mGlobalDealRef = globalDealRef;
        mContractType = contractType;
        mInvVat = invVat;
        mSalesVat = salesVat;
        mGroupRevShare = groupRevShare;
    }
    
    
    public void setId(String id) { mId = id; }
    
    
    public String getId() { return mId; }
    
    
    public void setName(String name) { mName = name; }
    
    
    public String getName() { return mName; }
    
    
    public void setType(String type) { mType = type; }
    
    
    public String getType() { return mType; }
    
    
    public void setGlobalOrLocal(String globalOrLocal) { mGlobalOrLocal = globalOrLocal; }
    
    
    public String getGlobalOrLocal() { return mGlobalOrLocal; }
    
    
    public void setGlobalDealRef(String globalDealRef) { mGlobalDealRef = globalDealRef; }

 
    public String getGlobalDealRef() { return mGlobalDealRef; }
    
    
    public void setContractType(String contractType) { mContractType = contractType; }
    
    
    public String getContractType() { return mContractType; }
    
    
    public void setInvVat(String invVat) { mInvVat = invVat; }

   
    public String getInvVat() { return mInvVat; }
    
    
    public void setSalesVat(String salesVat) { mSalesVat = salesVat; }
    
    
    public String getSalesVat() { return mSalesVat; }
    
    public void setGroupRevShare(String  groupRevShare){mGroupRevShare = groupRevShare;}
    
    public String getGroupRevShare(){ 
    	if (mGroupRevShare == null ||mGroupRevShare == "")
    	    return GROUP_REV_TYPE_NO;
    	else    
    	    return mGroupRevShare;  
    }



	public Long getKey() {
		return this.mKey;
	}

	public void setKey(Long key) {
		this.mKey = key;
	}

	public String getCreatedBy() {
		return this.mCreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		this.mCreatedBy = createdBy;
	}

	public String getModifiedBy() {
		return this.mModifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.mModifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.mModifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.mModifiedDate = modifiedDate;
	}

	public char getActiveStatus() {
		return this.mActiveStatus;
	}

	public void setActiveStatus(char activeStatus) {
		this.mActiveStatus = activeStatus;
	}

	// CR1503 - Multi Price plans - phase 2
	/**
	 * @return the mExternalPrtnPricePlan
	 */
	public String getmExternalPrtnPricePlan() {
		return mExternalPrtnPricePlan;
	}

	/**
	 * @param mExternalPrtnPricePlan the mExternalPrtnPricePlan to set
	 */
	public void setmExternalPrtnPricePlan(String mExternalPrtnPricePlan) {
		this.mExternalPrtnPricePlan = mExternalPrtnPricePlan;
	}
	// CR1503 - phase 2 ends

	// CR2040 MPAY replacement.  Goodwill credit limits.
	public GoodwillCreditLimits getGoodwillCreditLimits() {
		return goodwillCreditLimits;
	}
	
	// CR2040 MPAY replacement.  Goodwill credit limits.
	public void setGoodwillCreditLimits(GoodwillCreditLimits goodwillCreditLimits) {
		this.goodwillCreditLimits = goodwillCreditLimits;
	}

	// CR2040 MPAY replacement.  Spend limits.
	public SpendLimits getSpendLimits() {
		return spendLimits;
	}

	// CR2040 MPAY replacement.  Spend limits.
	public void setSpendLimits(SpendLimits spendLimits) {
		this.spendLimits = spendLimits;
	}

	// CR2040 MPAY replacement.  Partner category.
	public String getPartnerCategory() {
		return partnerCategory;
	}
	
	// CR2040 MPAY replacement.  Partner category.
	public void setPartnerCategory(String partnerCategory) {
		this.partnerCategory = partnerCategory;
	}
	
	// CR2040 MPAY replacement.  Aggregator associated with merchant.
	public String getAggregator() {
		return aggregator;
	}
	
	// CR2040 MPAY replacement.  Aggregator associated with merchant.
	public void setAggregator(String aggregator) {
		this.aggregator = aggregator;
	}

	//CR2210 Mpay Rate Card, equals and hashCode with id and name, moved and enhanced toString()

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mId == null) ? 0 : mId.hashCode());
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Partner other = (Partner) obj;
		if (mId == null) {
			if (other.mId != null) {
				return false;
			}
		} else if (!mId.equals(other.mId)) {
			return false;
		}
		if (mName == null) {
			if (other.mName != null) {
				return false;
			}
		} else if (!mName.equals(other.mName)) {
			return false;
		}
		return true;
	}

	public String toString() {
		StringBuilder strBldr = new StringBuilder();
		strBldr.append("key: " + mKey);
		strBldr.append(" id: " + mId);
		strBldr.append(" name: " + mName);
		strBldr.append(" type: " + mType);
		strBldr.append(" globalOrLocal: " + mGlobalOrLocal);
		strBldr.append(" globalDealRef: " + mGlobalDealRef);
		strBldr.append(" contractType: " + mContractType);
		strBldr.append(" invVat: " + mInvVat);
		strBldr.append(" salesVat: " + mSalesVat);
		strBldr.append(" GlobalRevShare :"+ mGroupRevShare);
		strBldr.append(" mCreatedBy: " + mCreatedBy);
		strBldr.append(" mModifiedBy: " + mModifiedBy);
		strBldr.append(" mModifiedDate: " + mModifiedDate);
		strBldr.append(" mActiveStatus: " + mActiveStatus);
		strBldr.append(" mExternalPrtnPricePlan: " + mExternalPrtnPricePlan);	// CR1503 - Multi Price plans - phase 2
		strBldr.append(" partnerCategory: " + partnerCategory); // CR2040 MPAY replacement.  Partner category.
		return strBldr.toString();
}

	//CR2210 Mpay Rate Card end

}
