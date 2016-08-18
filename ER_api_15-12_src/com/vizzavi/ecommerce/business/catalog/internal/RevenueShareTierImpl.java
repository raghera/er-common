package com.vizzavi.ecommerce.business.catalog.internal;

import com.vizzavi.ecommerce.business.catalog.RevenueShareTier;

public class RevenueShareTierImpl extends RevenueShareTier {

   private    static final long serialVersionUID = -6813617199565917867L;
  public RevenueShareTierImpl() {
      super();
  }
  public void setKey(Long key) {
    mKey = key;
  }
//  public void setCreatedBy(String createdBy) {
//	mCreatedBy = createdBy;
//  }
//
//  public void setModifiedBy(String modifiedBy) {
//	mModifiedBy = modifiedBy;
//  }
//
//  public void setModifiedDate(Date modifiedDate) {
//	mModifiedDate = modifiedDate;
//  }
//
//  public void setActiveStatus(char activeStatus) {
//	mActiveStatus = activeStatus;
//  }
  public void setSharePercentage(String sharePercentage) {
    mSharePercentage = sharePercentage;
  }
  public void setDirectFixedAmount(String directFixedAmount) {
    mDirectFixedAmount = directFixedAmount;
  }
  public void setIndirectFixedAmount(String indirectFixedAmount) {
    mIndirectFixedAmount = indirectFixedAmount;
  }
  public void setIndirectFixedAmountPromo(String val) {
    mIndirectFixedAmountPromo = val;
  }
  public void setMinimumThreshold(String minimumThreshold) {
    mMinimumThreshold = minimumThreshold;
  }
  public void setMaximumThreshold(String maximumThreshold) {
    mMaximumThreshold = maximumThreshold;
  }
}
