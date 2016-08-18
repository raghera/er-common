package com.vizzavi.ecommerce.business.catalog.internal;

import java.util.Date;

import com.vizzavi.ecommerce.business.catalog.FixedPayment;

public class FixedPaymentImpl extends FixedPayment{

   private    static final long serialVersionUID = -2275549339882320231L;
  public FixedPaymentImpl() {
  }

  public void setKey(Long key) {
    mKey = key;
  }

  public void setCreatedBy(String createdBy) {
	mCreatedBy = createdBy;
  }

  public void setModifiedBy(String modifiedBy) {
	mModifiedBy = modifiedBy;
  }

  public void setModifiedDate(Date modifiedDate) {
	mModifiedDate = modifiedDate;
  }

  public void setActiveStatus(char activeStatus) {
	mActiveStatus = activeStatus;
  }

  public void setAmount(String amount) {
    mAmount = amount;
  }
  public void setStepAmount(String stepAmount) {
    mStepAmount = stepAmount;
  }
  public void setUpperLimit(String upperLimit) {
    mUpperLimit = upperLimit;
  }
  public void setFrequency(String frequency) {
    mFrequency = frequency;
  }
  public void setNumberOfPeriods(String numberOfPeriods) {
    mNumberOfPeriods = numberOfPeriods;
  }
  public void setRetainRevert(String retainRevert) {
    mRetainRevert = retainRevert;
  }

}
