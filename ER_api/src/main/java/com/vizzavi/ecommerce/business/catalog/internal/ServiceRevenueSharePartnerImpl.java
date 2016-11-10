/**
 * --------------------------------Modification History--------------------------------
 *
 *      Sr. No.		Date			    Author				Description
 * ------------------------------------------------------------------------------------
 *      [1]			Aug 31, 2005		Sagar More  		Changed for CR
 *                                                          "Purchase Channel Reporting"
 *
 */
package com.vizzavi.ecommerce.business.catalog.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vizzavi.ecommerce.business.catalog.FixedPayment;
import com.vizzavi.ecommerce.business.catalog.RevenueShareTier;
import com.vizzavi.ecommerce.business.catalog.ServiceRevenueSharePartner;


/**
 * Service Revenue Share partner.
 * Part of Enhanced Reporting.
 * @see Revenue Share Technical Design document
 */
public class ServiceRevenueSharePartnerImpl extends ServiceRevenueSharePartner{

   private    static final long serialVersionUID = 1727235137109740982L;

    public ServiceRevenueSharePartnerImpl() {}


    /*public ServiceRevenueSharePartner(String id, String indirectFixAmt, String directFixAmt,
            String revShareAmtPct, String revThreshMin, String revThreshMax,
            String revThreshFormat, String priorityFlag, String whTaxAmt,
            String whTaxOffset) {

            mId = id;
            mIndirectFixAmt = indirectFixAmt;
            mDirectFixAmt = directFixAmt;
            mRevShareAmtPct = revShareAmtPct;
            mRevThreshMin = revThreshMin;
            mRevThreshMax = revThreshMax;
            mRevThreshFormat = revThreshFormat;
            mPriorityFlag = priorityFlag;
            mWhTaxAmt = whTaxAmt;
            mWhTaxOffset = whTaxOffset;
    }*/

    public ServiceRevenueSharePartnerImpl(
        String id, String model, ArrayList<RevenueShareTier> revenueShareTiers,
        String revThreshFormat, String revThreshCounterReset,
        String whTaxAmount, String whTaxOffset,
        FixedPayment fixedPayment, String fixedPaymentFlag,
        String priorityFlag
        ,String purchaseChannel) { //[1] Mod Signature

        mId = id;
        //[1] Mod Start
        mPurchaseChannel = purchaseChannel;
        //[1] Mod End
        mModel = model;
        mRevenueShareTiers = revenueShareTiers;
        mRevThreshFormat = revThreshFormat;
        mRevThreshCounterReset = revThreshCounterReset;
        mWhTaxAmt = whTaxAmount;
        mWhTaxOffset = whTaxOffset;
        mFixedPayment = fixedPayment;
        mFixedPaymentFlag = fixedPaymentFlag;
        mPriorityFlag = priorityFlag;
    }
    public ServiceRevenueSharePartnerImpl(
        Long key, String id, String model, ArrayList<RevenueShareTier> revenueShareTiers,
        String revThreshFormat, String revThreshCounterReset,
        String whTaxAmount, String whTaxOffset,
        FixedPayment fixedPayment, String fixedPaymentFlag,
        String priorityFlag, String createdBy, String modifiedBy, 
		Date modifiedDate, char activeStatus
        ,String purchaseChannel) {//[1] Mod Signature

		mKey = key;
//        mCreatedBy = createdBy;
//		mModifiedBy = modifiedBy;
//        mModifiedDate = modifiedDate;
//    	mActiveStatus = activeStatus;

        mId = id;
        //[1] Mod Start
        mPurchaseChannel = purchaseChannel;
        //[1] Mod End
        mModel = model;
        mRevenueShareTiers = revenueShareTiers;
        mRevThreshFormat = revThreshFormat;
        mRevThreshCounterReset = revThreshCounterReset;
        mWhTaxAmt = whTaxAmount;
        mWhTaxOffset = whTaxOffset;
        mFixedPayment = fixedPayment;
        mFixedPaymentFlag = fixedPaymentFlag;
        mPriorityFlag = priorityFlag;
    }
    public void setKey(Long key) { mKey = key; }

//    public void setCreatedBy(String createdBy) { mCreatedBy = createdBy; }
//
//    public void setModifiedBy(String modifiedBy) { mModifiedBy = modifiedBy; }
//
//    public void setModifiedDate(Date modifiedDate) { mModifiedDate = modifiedDate; }
//
//    public void setActiveStatus(char activeStatus) { mActiveStatus = activeStatus; }

    public void setId(String id) { mId = id; }

    //[1] Mod Start
    public void setPurchaseChannel(String purchaseChannel) { mPurchaseChannel = purchaseChannel; }
    //[1] Mod End

    public void setRevThreshFormat(String revThreshFormat) { mRevThreshFormat = revThreshFormat; }

    /**
     * setting the value of revenue threshold counter reset interval
     * @parameter revThreshCounterReset revenue threshhold counter reset
     */
    public void setRevThreshCounterReset(String revThreshCounterReset)
    {
        mRevThreshCounterReset = revThreshCounterReset;
    }

    public void setPriorityFlag(String priorityFlag) { mPriorityFlag = priorityFlag; }

    public void setWhTaxAmt(String whTaxAmt) { mWhTaxAmt = whTaxAmt; }

    public void setWhTaxOffset(String whTaxOffset) { mWhTaxOffset = whTaxOffset; }

    public void setModel(String model) {
        mModel = model;
    }

    public void setFixedPayment(FixedPayment fixedPayment) {
        mFixedPayment = fixedPayment;
    }

    public void setFixedPaymentFlag(String fixedPaymentFlag) {
        mFixedPaymentFlag = fixedPaymentFlag;
    }

    public void setRevenueShareTiers(List<RevenueShareTier> revenueShareTiers) {
        mRevenueShareTiers = revenueShareTiers;
    }

    public void addTier(RevenueShareTier revenueShareTier) {
        mRevenueShareTiers.add(revenueShareTier);
    }

}
