package com.com.vodafone.global.er.subscriptionmanagement;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.selfcare.PurchasedService;
import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vizzavi.ecommerce.business.selfcare.Transaction;

import java.util.Date;

/**
 * Created by Ravi Aghera
 */
public class ERSubscription extends Subscription implements DirtyMarker {

    private static final long serialVersionUID = -7843204806399453978L;


    public boolean dirty = false;
    private boolean newInstance = true;


    public ERSubscription()    {
    }

    public ERSubscription(Subscription sub)    {
        super(sub);
        setPurcServiceList(sub.getPurcServiceList());
    }

    public ERSubscription(ERSubscription sub)    {
        super(sub);
        this.dirty = sub.dirty;
        this.newInstance = sub.newInstance;
        this.msisdn = sub.msisdn;
        this.purchasedServices = sub.purchasedServices;
        //	this.acccess_device = sub.acccess_device;

    }
    public ERSubscription(CatalogPackage cpack, int status, String msisdn, Date endDate)
    {
        this.mPack = cpack;
        this.mStatus = status;
        this.msisdn = msisdn;
        this.mExpiryDate = endDate;
        if (cpack!=null) {
            this.setNonRefundDescription(cpack.getNonRefundableDescription());
            this.setIsRefundable(cpack.isRefundable());
            this.setSuperPackage(cpack.isSuperPackage());
        }
        // Temperory subscription Id
        //this.mSubscriptionId = "subId";
    }


    public ERSubscription(CatalogPackage cpack,int status,String msisdn,Date startDate,Date endDate){
        this.mPack = cpack;
        this.mStatus = status;
        this.msisdn = msisdn;
        this.mExpiryDate = endDate;
        this.mStartDate = startDate;

        // Temperory subscription Id
        //this.mSubscriptionId = "subId";
    }

    //@ER7   Setter method for the Linked Price Point Attribute
    public void setLinkedPricePointID(String LinkedPtID)
    {
        this.mLinkedPtID =  LinkedPtID;
        dirty = true;
    }

    public void setServiceProvTagProvOnUsage(String serviceId, String provTag, boolean isProvisionOnUsage)
    {
        PurchasedService ps = null;
        if (serviceId!=null) {
            if (purchasedServices.containsKey(serviceId)) {
                ps = purchasedServices.get(serviceId);
                ps.setProvisioningTag(provTag);
                ps.setIsProvisionOnUsage(isProvisionOnUsage);
            } else {
                //create the object
                ps = new ERPurchasedService();
                ps.setProvisioningTag(provTag);
                ps.setServiceId(serviceId);
                ps.setIsProvisionOnUsage(isProvisionOnUsage);
                purchasedServices.put(serviceId,ps);
            }
            //for legacy support ...
            mServiceProvisioningTags.put(serviceId, provTag);
        }
    }


    public void setPromotional(boolean promotional)	{
        mPromotional = promotional;
        dirty = true;
    }



    public void setPromotionalExpiryDate(Date newMPromotionalExpiryDate)  {
        mPromotionalExpiryDate = newMPromotionalExpiryDate;
        dirty = true;
    }



    //Getter setter methods for Renewal Date
    public void setRenewalDate(Date val)    {
        mRenewalDate = val;
        dirty = true;
    }



    //Getter setter methods for Payment Type
    public void setPaymentType(int val)    {
        mPaymentType = val;
        dirty = true;
    }


    //Getter setter methods for Content Payment Type
    public void setContentPaymentType(int val)    {
        dirty = true;
        mContentPaymentType = val;
    }


    //Getter setter methods for IsArchived
    public void setIsArchived(boolean val)
    {
        mIsArchived = val;
        dirty = true;
    }


    //Getter setter methods for CSRID
    public void setCsrId(String id)
    {
        mCsrId = id;
        dirty = true;
    }


    //Getter setter methods for PurchaseDevice Type
    public void setPurchaseDeviceType(int id)
    {
        mDeviceType = id;
        dirty = true;
    }


    /**
     * Setter method for the lock id
     * @param lockId The lock id value from the database
     * @since ER 5.1
     */
    @Override
    public final void setLockId(Long lockId) {
        // this.lockId = new Long(lockId);
        super.setLockId(lockId);
        dirty = true;
    }


    //    public final void setLockId(Long lockId) {
    //        this.lockId = lockId;
    //        dirty = true;
    //    }

    //    /**
    //     * Getter method for the lock id
    //     * @return the lock id value associated with the ERSubscription object
    //     * @since ER 5.1
    //     */
    //    public final Long getLockId() {
    //        return this.lockId;
    //    }


    /**
     * @param map
     */
    public void addPurchasedService(PurchasedService service) {
        if (service != null && service.getServiceId() != null) {
            purchasedServices.put(service.getServiceId(), service);
            //keep the serviceIds and provisioningTag updated
            //TODO this should be removed at some point ...
            mServiceStatuses.put(service.getServiceId(), new Integer(service.getStatus()));
            mServiceProvisioningTags.put(service.getServiceId(),service.getProvisioningTag());
            if (service.getNonRefundDescription() != null) mServiceNonRefundDescription.put(service.getServiceId(),service.getNonRefundDescription());
        }
    }


    public void setNonRefundDescription(String description ) {
        nonRefundDescription = description  ;
    }

    /**
     *  Sets usage history with this subscription
     *
     *@param usage can be INTERACTIVE_NONE, INTERACTIVE_FIRST or INTERACTIVE_ALL
     *@since ER 6
     */
    public void setInteractiveUsageFlag(int usage)
    {
        dirty = true;
        mInteractiveUsageFlage=usage;
    }



    //	end VFE - PS team code

    public void increaseUsageCount(String serviceId ){
        PurchasedService psvc =this.getPurchasedService(serviceId);
        if (psvc != null )
//			JIRA ET-331:part2 - USAGE_COUNT field removal from API and DB
            logger.info("purchasedService usage count should be increased");
//			psvc.increaseCount();
    }

    //REMEDY 5685
    public void setHasHistoricPricepoint(boolean hasHistoricPricepoint) {
        this.mHasHistoricPricepoint = hasHistoricPricepoint;
        dirty = true;
    }

    //REMEDY 5685
    //      public boolean getBoughtinPast() {
    //    	  return this.boughtinPast;
    //      }

    /**
     * does nothing - don't use.
     * @param boughtPast
     * @deprecated
     * */
    public void setBoughtinPast(boolean boughtPast) {
        // this.boughtinPast = boughtPast;
    }

    //REMEDY 5871
    public void setIsWasRecurringTrial(boolean isWasRecurringTrial) {
        this.mIsWasRecurringTrial = isWasRecurringTrial;
    }



    //RBT Enhancement CR - Start
    public void setParentPackageID(String parentPackageID)
    {
        this.mParentPackageId = parentPackageID;
    }
    //RBT Enhancement CR - End

    //MQC 5126
    public void setPreviousStatus(int previousStatus)
    {
        this.mPreviousStatus = previousStatus;
    }
    /**
     * Probably the transaction id of the initial purchase.
     * @param paymentTransId
     */
    //MQC 6368 - set paymentTransactionId
    public void setPaymentTransactionId(Long paymentTransId)
    {
        this.paymentTransactionId = paymentTransId;
    }

    //CR 2108
    public void setRenewalPreRate(double renewalPreRate) {
        this.renewalPreRate = renewalPreRate;
        dirty = true;
    }

    public void setPartnerTaxRate(double partnerTaxRate){
        this.partnerTaxRate = partnerTaxRate;
        dirty = true;
    }


    @Override
    public void setPackage(CatalogPackage val)
    {
        super.setPackage(val);
        setDirty();
    }


    @Override
    public void setStatus(Integer val)	    {
        if(val !=null) {
            setDirty();
            super.setStatus(val);
        }
    }


    @Override
    public void setSuperPackage(Boolean flag)	    {
        if (flag!=null)	{
            super.setSuperPackage(flag);
            setDirty();
        }
    }

    /* (non-Javadoc)
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setDirty()
     */
    @Override
    public void setDirty() {
        this.dirty = true;
    }



    /* (non-Javadoc)
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isDirty()
     */
    @Override
    public boolean isDirty() {
        return dirty;
    }



    /* (non-Javadoc)
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetDirty()
     */
    @Override
    public void resetDirty() {
        dirty = false;
    }



    /* (non-Javadoc)
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setNew()
     */
    @Override
    public void setNew() {
        newInstance = true;
    }



    /** (non-Javadoc)
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isNew()
     */
    @Override
    public boolean isNew() {
        return newInstance;
    }



    /* (non-Javadoc)
     * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetNew()
     */
    @Override
    public void resetNew() {
        newInstance = false;
    }

    @Override
    public void setPurchaseDate(Date val) {
        super.setPurchaseDate(val);
        dirty = true;
    }

    @Override
    public void setStartDate(Date val) {
        super.setStartDate(val);
        mStartDate = val;
    }

    @Override
    public void setExpiryDate(Date val) {
        super.setExpiryDate(val);
        dirty = true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void setSubscriptionId(String val) {
        dirty = true;
        super.setSubscriptionId(val);
    }

    /*********************************************************************/
//	@Override
//	public void setCurrentNoOfOccurences(long mCurrentNoOfOccurences) {
//		super.setCurrentNoOfOccurences(mCurrentNoOfOccurences);
//		dirty = true;
//	}

    @Override
    public void setCurrentNoOfOccurences(Long mCurrentNoOfOccurences) {
        if(mCurrentNoOfOccurences !=null){
            super.setCurrentNoOfOccurences(mCurrentNoOfOccurences);
            dirty = true;
        }
    }

    @Override
    public void setFinalMinSubscriptionEndDate(Date date) {
        super.setFinalMinSubscriptionEndDate(date);
        dirty = true;
    }

    @Override
    public void setPreOrdered(Boolean flag) {
        if (flag!=null)	{
            super.setPreOrdered(flag);
            dirty = true;
        }
    }



    @Override
    public void setPenaltyCharge(Double penaltyCharge) {
        if (penaltyCharge!=null)	{
            super.setPenaltyCharge(penaltyCharge);
            dirty = true;
        }
    }

    @Override
    public void setNextCyclePercentValue(Double val) {
        if (val !=null){
            super.setNextCyclePercentValue(val);
            dirty = true;
        }
    }


    /**
     * //CR 2245 upsell discount prorate, the last payment transaction (purchase, renew or recurr) for the subscription
     * @param lastTran
     */
    public void setLastPaymentTransaction(Transaction lastTran) {
        this.lastPaymentTransaction = lastTran;
    }

    /**
     * this implementation returns ERPurchaseService, a sub-class of PurchasedService.
     *
     */
    public ERPurchasedService[] getPurchasedServices() {
        ERPurchasedService[] serv = null;
        if (purchasedServices.size()>0)
            serv = purchasedServices.values().toArray(new ERPurchasedService[purchasedServices.size()]);
        return serv;
    }


}
