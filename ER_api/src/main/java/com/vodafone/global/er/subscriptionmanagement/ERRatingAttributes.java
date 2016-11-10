/*
 * Created on Feb 10, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.vodafone.global.er.subscriptionmanagement;

import java.util.Calendar;

import com.vizzavi.ecommerce.business.common.DeviceType;
import com.vizzavi.ecommerce.business.common.RatingAttributes;

/**
 * @author piotrad
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ERRatingAttributes extends RatingAttributes implements DirtyMarker {
    
   private    static final long serialVersionUID = 5886571870504432628L;
    private boolean dirty = false;
    private boolean newInstance = true;
    
    //@hud RFRFRF
    //ER 7 compliant
	protected Long mId = new Long(-1);

    /**
     * Used to store the locking id related to the ER_OPTIONS row in the database.
     * This is used to handle concurrent updates to the ER_OPTIONS table from
     * multiple core instances.
     * @since er 5.1
     */
    private Long mLockId = new Long(-1);

    public ERRatingAttributes(){
	}

    public ERRatingAttributes(RatingAttributes attrs){
		super (attrs);
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

	@Override
	public void setPremiumLevel(int level)
	{
		mPremiumLevel = level;
		dirty = true;
	}


	/**
	* Set the supplied id. This is used to rate the service.
	* Different rates can exist for different suppliers.
	*/
	@Override
	public void setSupplierId( String id) {
		this.mSupplierId = id;
		dirty = true;
	}


	/**
	*   Set the access device type. Must be defined in DeviceType class.
	* @see DeviceType
	*/
	@Override
	public void setDeviceType( int code )
	{
		this.mDeviceType = code;
		dirty = true;
	}


	/**
	*  Set the user group.
	*/
	@Override
	public void setUserGroups( String[] userGroups )
	{
		this.mUserGroups = userGroups;
		dirty = true;
	}


	/**
	*   Set the payment type.
	*/
	@Override
	public void setPaymentType( int code )
	{
		this.mPaymentType = code;
		dirty = true;
	}


	/**
	*/
	@Override
	public void setChannel( int code )
	{
		dirty = true;
		this.mChannel = code;
	}

	/**
	*   This is used to set the time that the package was purchased/ the service was used.
	*/
	@Override
	public void setEventDateTime(Calendar eventDateTime) {
		mEventDateTime = eventDateTime;
		dirty = true;
	}


	/**
	* Sets the amount the event has been used. This is should in volume service
	to set the usage amount.
	*/
	@Override
	public void setEventUnits(double eventUnits) {
		mEventUnits = eventUnits;
		dirty = true;
	}


	/**
		This allows a customer to set a number of promo codes although in practise only one is usually set.
		@param String[] The array of promo codes that the user has entered
	*/
	@Override
	public void setPromoCodes(String[] val)
	{
		this.mPromoCodes = val;
		dirty = true;
	}


	@Override
	public void setDuration(int val)
	{
		mDuration = val;
		dirty = true;
	}

	@Override
	public void setChargingMethod(int val)
	{
		mChargingMethod = val;
		dirty = true;
	}

    /**
     * Setter method for the lock id
     * @param lockId The lock id value from the database
     * @since ER 5.1
     */
    public final void setLockId(long id) {
        mLockId = new Long(id);
        dirty = true;
    }

    public final void setLockId(Long id) {
        mLockId = id;
        dirty = true;
    }

    /**
     * Getter method for the lock id
     * @return the lock id value associated with the ERRatingAttributes object
     * @since ER 5.1
     */
    public final Long getLockId() {
        return mLockId;
    }

	/**
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setDirty()
	 */
	@Override
	public void setDirty() {
		this.dirty = true;
	}



	/**
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return dirty;
	}



	/**
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetDirty()
	 */
	@Override
	public void resetDirty() {
		dirty = false;
	}



	/**
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#setNew()
	 */
	@Override
	public void setNew() {
		newInstance = true;
	}



	/**
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#isNew()
	 */
	@Override
	public boolean isNew() {
		return newInstance;
	}



	/**
	 * @see com.vodafone.global.er.subscriptionmanagement.DirtyMarker#resetNew()
	 */
	@Override
	public void resetNew() {
		newInstance = false;
	}
	

}
