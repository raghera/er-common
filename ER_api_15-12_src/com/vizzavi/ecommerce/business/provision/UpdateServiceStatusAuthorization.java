package com.vizzavi.ecommerce.business.provision;

import com.vizzavi.ecommerce.business.common.ReasonCode;

/**
 * Copyright OKATIMA - 2010
 * File created 18 okt 2010
 * Author: Magnus Goransson
 * info@okatima.se
 * 
 * Created for CR-1759
 * 
 */

public class UpdateServiceStatusAuthorization extends ProvisionAuthorization
{

  private static final long serialVersionUID = 1L;
  private boolean result = false;
    
  public UpdateServiceStatusAuthorization(ReasonCode code, String subId, String errorId, String ErrorMessage, boolean result)
  {
      super(code, subId, errorId);
      this.setErrorDescription(ErrorMessage);
      this.result = result;
  }  
  
  public void setResult(boolean result)
  {
    this.result = result;
  }
  
  public boolean getResult()
  {
    return this.result;
  }

  @Override
  public String toString() {
	  
	  String s = "Result: " +  result + ", ReasonCode: " + this.getReasonCode() + ", ErrorId: " + this.getErrorId() + ", ErrorMessage: " + this.getErrorDescription();
	  return s;
	  
  }
}
