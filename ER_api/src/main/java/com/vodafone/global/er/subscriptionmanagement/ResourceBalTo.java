package com.vodafone.global.er.subscriptionmanagement;



import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;

public class ResourceBalTo extends ResourceBalance implements DirtyMarker {
    private static final long serialVersionUID = -7380099107261805317L;
    private boolean dirty = false;
    private boolean newInstance = true;

    /**
     * Used to store the locking id related to the resource balances row in the database.
     * This is used to handle concurrent updates to the ER_PACKAGEBALANCES table from
     * multiple core instances.
     * @since er 5.1
     */
    //ER 7 Compliant
    private Long mLockId = new Long(-1);

    // ER 7 Compliant
	protected Long mId = new Long(-1);

	// Fileds from ER_transaction_types table

	public ResourceBalTo() {
	}
	
	public ResourceBalTo(ResourceBalance res) {
		 this.mBalance = res.getBalance();
		 this.mThreshold = res.getThreshold();
		 if (res.getResource()!=null) this.mRes = res.getResource();
	}


	public void setResource(ChargingResource mRes) {

		this.mRes = mRes;
		dirty = true;

	}

	public void setBalance(double mBalance) {

		this.mBalance = mBalance;
		dirty = true;

	}
	
    /**
     * Setter method for the threshold value
     * @param mThreshold The notification threshold value from the database
     * @since ER 6
     */
	public void setThreshold(int mThreshold){
		this.mThreshold = mThreshold;
		dirty = true;
	}

	/**
	 * @removed
	 * @param mSubscriptionId
	 */
	public void setSubscriptionIdLong(long id) {
		super.setSubscriptionIdLong(id);
		dirty = true;
	}

	public void setPackageId(String mPackageId) {
		this.mPackageId = mPackageId;
		dirty = true;
	}

    /**
     * Setter method for the lock id
     * @param mLockId The lock id value from the database
     * @since ER 5.1
     */
    public final void setLockId(Long id) {
        mLockId = id;
        dirty = true;
    }

    public final void setLockId(long id) {
        mLockId = new Long(id);
        dirty = true;
    }
    
    /**
     * Getter method for the lock id
     * @return the lock id value associated with the ResourceBalnceTo object
     * @since ER 5.1
     * @hud RFRFRF
     */
    public final Long getLockId() {
        return mLockId;
    }

	/**
	 * @return
	 */
	public Long getId() {
		return mId;
	}
	/**
	 * @param l
	 */
	public void setId(long id) {
		mId = new Long(id);
		dirty = true;
	}

	public void setId(Long id) {
		mId = id;
		dirty = true;
	}


	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setDirty()
	 */
	public void setDirty() {
		this.dirty = true;
	}



	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isDirty()
	 */
	public boolean isDirty() {
		return dirty;
	}



	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetDirty()
	 */
	public void resetDirty() {
		dirty = false;
	}



	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setNew()
	 */
	public void setNew() {
		newInstance = true;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isNew()
	 */
	public boolean isNew() {
		return newInstance;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetNew()
	 */
	public void resetNew() {
		newInstance = false;
	}

}
