package com.vodafone.global.er.subscriptionmanagement;

import java.util.Date;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.business.catalog.DRMObject;
import com.vizzavi.ecommerce.business.selfcare.PurchasedService;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;
import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vizzavi.ecommerce.business.selfcare.Transaction;

public class ERSubscription extends Subscription implements DirtyMarker {

   private    static final long serialVersionUID = -7843204806399453978L;
	public boolean dirty = false;
	private boolean newInstance = true;

	


    /**
     * Used to store the locking id related to the subscription row in the database.
     * This is used to handle concurrent updates to the ER_SUBSCRIPTIONS table from
     * multiple core instances.
     * @since er 5.1
     */
    //private long lockId = -1;
    //ER 7 Compliant
    private Long lockId = new Long(-1);

    private String acccess_device;

	//REMEDY 5685 - add flag if this is a sub which has already been bought in the past
	boolean boughtinPast = false;

    public ERSubscription()
    {
    }
    public ERSubscription(ERSubscription sub)
    {
		super(sub);
		this.dirty = sub.dirty;
		this.newInstance = sub.newInstance;
		this.msisdn = sub.msisdn;
		this.purchasedServices = sub.purchasedServices;
		this.acccess_device = sub.acccess_device;

    }
    public ERSubscription(CatalogPackage cpack,int status,String msisdn,Date endDate)
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


    //Getter setter methods for the Service Status
    public void setServiceStatus(String serviceId, int status)
    {
		PurchasedService ps = null;
		if (serviceId!=null) {
				if (purchasedServices.containsKey(serviceId)) {
					ps = purchasedServices.get(serviceId);
					ps.setStatus(status);
				} else {
					//create the object
					ps = new ERPurchasedService();
					ps.setStatus(status);
					ps.setServiceId(serviceId);
					purchasedServices.put(serviceId,ps);
				}
		 // for legacy support
         mServiceStatuses.put(serviceId, new Integer(status));
       }
    }

	public void setServiceStatus(CatalogService catService, int status)
	{
	PurchasedService ps = null;
	if (catService!=null)
	{			
		String serviceId = catService.getId();

		if (serviceId!=null)
		{
			if (purchasedServices.containsKey(serviceId))
			{
				ps = purchasedServices.get(serviceId);
				ps.setStatus(status);
			}
			else
			{
				//create the object
				ps = new ERPurchasedService();
				ps.setStatus(status);
				ps.setServiceId(serviceId);

				//Remedy 4100, Bruno Meseguer, Secures the ProvTag is set when creating a new ERPurchasedService
				ps.setProvisioningTag(catService.getProvisioningTag());
				
				ps.setIsProvisionOnUsage(catService.isProvisionOnUsage()); // CR1209

				purchasedServices.put(serviceId,ps);
			}

			// for legacy support
			mServiceStatuses.put(serviceId, new Integer(status));
		}
	}
	}


  public void setPromotional(boolean newMPromotional)
  {
    mPromotional = newMPromotional;
    dirty = true;
  }



  public void setPromotionalExpiryDate(Date newMPromotionalExpiryDate)
  {
    mPromotionalExpiryDate = newMPromotionalExpiryDate;
    dirty = true;
  }



  public void setBatchProcessor(boolean newMBatchProcessor)
  {
    mBatchProcessor = newMBatchProcessor;
    dirty = true;
  }


     //Getter setter methods for Renewal Date
    public void setRenewalDate(Date val)
    {
        mRenewalDate = val;
		dirty = true;
    }



   //Getter setter methods for Payment Type
    public void setPaymentType(int val)
    {
        mPaymentType = val;
		dirty = true;
    }


    //Getter setter methods for Content Payment Type
    public void setContentPaymentType(int val)
    {
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

    public double getNextCyclePercentValue()
    {
       return mNextCycleDiscount;
    }

    /**
     * Setter method for the lock id
     * @param lockId The lock id value from the database
     * @since ER 5.1
     */
    public final void setLockId(long lockId) {
        this.lockId = new Long(lockId);
        dirty = true;
    }


    public final void setLockId(Long lockId) {
        this.lockId = lockId;
        dirty = true;
    }
    
    /**
     * Getter method for the lock id
     * @return the lock id value associated with the ERSubscription object
     * @since ER 5.1
     */
    public final Long getLockId() {
        return this.lockId;
    }


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

  //    Added :     21-04-2005
  //    Added by :  VFE-PS team
  public void setSuperCreditBalance(int superCreditBalance)
  {
    this.superCreditBalance = superCreditBalance;
    dirty = true;
  }

  //	end VFE - PS team code

	/**
	 * Used to check if a package requires provisioning
	 * Put in as part of REMEDY 1830
	 * @return
	 */
	public boolean provisionRequired() {
		//START REMEDY 4919 - for a super package there will be no purchased services so return false
		if (this.getPackage() == null || this.getPackage().isSuperPackage()) {
			return false;
		}
		//END REMEDY 4919
		boolean provisioningFlag = false;
		if (this != null && this.getPurchasedServices() != null) {
//			ERPurchasedService[] erPruchaseServiceList =
//				this.getPurchasedServices();
			for (ERPurchasedService service: this.getPurchasedServices()) {
				if (!"N/A".equals(service.getProvisioningTag())) {
					provisioningFlag = true;
					break;
				}
			}
		}
		return provisioningFlag;
	}
 
      
      public void increaseUsageCount(String serviceId ){
    	  PurchasedService psvc =this.getPurchasedService(serviceId);
    	  if (psvc != null )
    		  psvc.increaseCount();
      }
      
      //REMEDY 5685
      public void setHasHistoricPricepoint(boolean hasHistoricPricepoint) {
    	  this.mHasHistoricPricepoint = hasHistoricPricepoint;
    	  dirty = true;
      }
      
      //REMEDY 5685
      public boolean getBoughtinPast() {
    	  return this.boughtinPast;
      }
      
      public void setBoughtinPast(boolean boughtPast) {
    	  this.boughtinPast = boughtPast;
      }
      
      //REMEDY 5871
      public void setIsWasRecurringTrial(boolean isWasRecurringTrial) {
    	  this.mIsWasRecurringTrial = isWasRecurringTrial;
      }
      
 
      
      //RBT Enhancement CR - Start
	  public void setParentPackageID(String parentPackageId)
	  {
		  this.mParentPackageId = parentPackageId;
	  }
	  //RBT Enhancement CR - End
	  
	  //MQC 5126
	  public void setPreviousStatus(int previousStatus)
	  {
		  this.mPreviousStatus = previousStatus;
	  }
	  
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
		public void setStatus(int val)
	    {
			setDirty();
	        super.setStatus(val);
	    }
	    
	    @Override
		public void setSuperPackage(boolean flag)
	    {
	          super.setSuperPackage(flag);
	          setDirty();
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



		/* (non-Javadoc)
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

		@Override
		public void setSubscriptionId(String val) {
			dirty = true;
		    super.setSubscriptionId(val);
		}

		/*********************************************************************/
		@Override
		public void setCurrentNoOfOccurences(long mCurrentNoOfOccurences) {
		  super.setCurrentNoOfOccurences(mCurrentNoOfOccurences);
		  dirty = true;
		}

		@Override
		public void setFinalMinSubscriptionEndDate(Date date) {
			super.setFinalMinSubscriptionEndDate(date);
			dirty = true;
		}

		@Override
		public void setPreOrdered(boolean flag) {
		 	super.setPreOrdered(flag);
		 	dirty = true;
		 }
		
		/**
		 * Setter method for the DRM Object
		 * @param drmobject
		 * @since ER 5.1
		 */
		@Override
		public void setDRMObject(DRMObject drmobject) {
		    super.setDRMObject(drmobject);
		    dirty = true;
		}

		@Override
		public void setSuperCreditBalances(ResourceBalance[] superCreditBalances) {
		    super.setSuperCreditBalances(superCreditBalances);
		    dirty = true;
		  }

		@Override
		public void setSuperCreditID(String superCreditID) {
		    super.setSuperCreditID(superCreditID);
		    dirty = true;
		  }
		  //	end VFE - PS team code

		@Override
		public void setPenaltyCharge(double penaltyCharge) {
			super.setPenaltyCharge(penaltyCharge);
			dirty = true;
		}

		@Override
		public void setNextCyclePercentValue(double val) {
		    super.setNextCyclePercentValue(val);
			dirty = true;
		}
		
		public ERPurchasedService[] getPurchasedServices() {
			ERPurchasedService[] serv = null;
			if (purchasedServices.size()>0)
			  serv = purchasedServices.values().toArray(new ERPurchasedService[purchasedServices.size()]);

			return serv;
		}
	  
		/**
		 * //CR 2245 upsell discount prorate, the last payment transaction (purchase, renew or recurr) for the subscription
		 * @param lastTran
		 */
		public void setLastPaymentTransaction(Transaction lastTran) {
			  this.lastPaymentTransaction = lastTran;
		}
	  
	  
	  
}
