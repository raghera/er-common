package com.vizzavi.ecommerce.business.catalog.internal;

import java.util.Date;

import com.vizzavi.ecommerce.business.catalog.Partner;

public class PartnerImpl extends Partner
{

	private static final long serialVersionUID = -68877479628966187L;
	
    private long versionFk;//optimized
                                                                                                                                                                                                                                              //optimized
    public PartnerImpl(Long key, String id, String name, String type, String globalOrLocal, String globalDealRef, String contractType, long invVat, long salesVat, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus, long versionFk) {
		mKey = key;

		mId = id;
		mName = name;
		mType = type;
		mGlobalOrLocal = globalOrLocal;
		mGlobalDealRef = globalDealRef;
		mContractType = contractType;
		mInvVat = "" + invVat;
		mSalesVat = "" + salesVat;
		mCreatedBy = createdBy;
		mModifiedBy = modifiedBy;
		mModifiedDate = modifiedDate;
		mActiveStatus = activeStatus;
        this.versionFk = versionFk;//optimized
    }

    public long getVersionFk() {//optimized
        return this.versionFk;
    }

    public void setVersionFk(long versionFk) {//optimized
        this.versionFk = versionFk;
    }
}
