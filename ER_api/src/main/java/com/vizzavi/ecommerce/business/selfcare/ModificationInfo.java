package com.vizzavi.ecommerce.business.selfcare;

/**
    Extra information about the modification
*/
public interface ModificationInfo
{

    /**
        The type of modification
    */
    public String getModificationType();

    /**
        The old value
    */
    public String getOldValue();

    /**
        The new value
    */
    public String getNewValue();
    
    //CR-1513 - start
    public String getHostId();
    
    public String getClientId();
    //CR-1513 - end

    /**
        The description|reason entered by the customer care agent
    */
    public String getDescription();    

    public boolean isInactivateAccount();
    
    public boolean isInactivateSubscription();
    
    public boolean isModifyMsisdn();
    
    public boolean isModifyBan();
    
    public boolean isModifyPaymentType();
    
    public boolean isModifyChargingMethod();
    //CR1564-utc timezone changes
    public boolean isModifyTimezone();
    
    public boolean modifyBillingCycle();
    
    public boolean modifyStatus();
    
    public boolean modifyUserGroups();

    public boolean modifyAccountSpId();		// CR 1643 - Pre-Pay Post-Pay Split

    public boolean modifyAccountIsPrepay();	// CR 1643 - Pre-Pay Post-Pay Split
    
}

