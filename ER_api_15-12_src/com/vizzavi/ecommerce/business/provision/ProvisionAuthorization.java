package com.vizzavi.ecommerce.business.provision;

import com.vizzavi.ecommerce.business.charging.BaseAuthorization;
import com.vizzavi.ecommerce.business.common.ReasonCode;

/**
* Encapsulates the result of a provision authorization call to the charging subsystem.
*/
public class ProvisionAuthorization extends BaseAuthorization {

   private    static final long serialVersionUID = -4325566692681133392L;

    public ProvisionAuthorization(ReasonCode code, String subId)
    {
        setReasonCode(code);
        packageSubscriptionId=subId;
        if (code.equals(ReasonCode.OK)) {
            mIsSuccess = true;
        } else {
            mIsSuccess = false;
        }
    }    
    
    public ProvisionAuthorization(ReasonCode code, String subId, String errorId)
    {
        this(code, subId);
        super.errorId = errorId;
    } 
    
    
}
