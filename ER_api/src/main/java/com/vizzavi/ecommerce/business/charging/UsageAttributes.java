package com.vizzavi.ecommerce.business.charging;

import com.vizzavi.ecommerce.business.common.RatingAttributes;

/**
* same as rating attributes, but has reserve only flag, force purchase flag and a couple of other things
*/
public class UsageAttributes extends RatingAttributes
{
   private    static final long serialVersionUID = 4694394406271253780L;
    int         reserveOnlyFlag = 0;
	protected   boolean forcePurchaseFlag = false;
	protected String mSessionId = "";
	protected boolean mSubscribed = false;
    protected boolean subscribedFlag;    



    /**
    * Forces this usage event to only reserve funds instead of reserving then capturing.
    * Calling this overrides any setting in the Catalog for this service.
    * For most clients the default behaviour specified in the Catalog should be sufficient and they
    * should not need to call this method.
    * <b>You must be familiar with 2-phase payment scenarios before calling this method</b>.
    */
    public void forceReservation() {
        this.reserveOnlyFlag = 1;
    }

    /**
        This is used internally by the ER2 system
    */
    @Override
	public int getReserveOnlyFlag() {
        return this.reserveOnlyFlag;
    }

    /**
    * Forces this usage event to reserve and capture funds instead of possibly only reserving.
    * Calling this overrides any setting in the Catalog for this package.
    * For most clients the default behaviour specified in the Catalog should be sufficient and they
    * should not need to call this method.
    * <b>You must be familiar with 2-phase payment scenarios before calling this method</b>.
    */
    public void disableReservation() {
        this.reserveOnlyFlag = 0;
    }


	/**
	 * setter methods for the forcePurchaseFlag
	 *  if true, following scenario is automated:
	 *  1- Usage(forceUsageFlag is true) failed
	 *  2- dicoverPurchaseOption returning only one package ID
	 *  3- Purchase option
	 *  4- Use service
	 * @since ER 5.1
	 */
	public void diablePurchase(){
		this.forcePurchaseFlag = false;
	}

	/**
	 * setter methods for the forcePurchaseFlag
	 * if false, normal usage
	 * @since ER 5.1
	 */
	public void forcePurchase (){
		this.forcePurchaseFlag = true;
	}
	
	
	/**
	 * getter mnethods for the forcePurchaseFlag
	 * @since ER 5.1
	 */
	public boolean getForcePurchaseFlag(){
		return this.forcePurchaseFlag;
	}

    /**
    * Output data in the bean for logging purposes
    */
    @Override
	public String toString()
    {
        StringBuffer buf = new StringBuffer(super.toString());
        buf.append("reserveOnlyFlag=" + this.reserveOnlyFlag).append(',');
        buf.append("subscribedFlag=" + this.subscribedFlag).append(',');

        return buf.toString();
    }
    


	/**
	*   Scalability Option
	* @deprecated 
	*/
	@Deprecated
	public void forceHighVolumeInterfaceLevel() {
		//Not being used anymore
	}

	/**
	*   Not in ER5 - Scalability Option
	* @deprecated
	*/
	@Deprecated
	public void forceLowVolumeInterfaceLevel() {
		//Not being used anymore
	}

	/**
	*   Not in ER5 -  Scalability Option
	* @deprecated
	*/
	@Deprecated
	public void forceQueueVolumeInterfaceLevel() {
		//Not being used anymore
	}

	/**
	*   Not in ER5 -  Scalability Option
	* @deprectated
	*/
	public void setHighVolumeInterfaceLevel(int highVolumeInterfaceLevelIn) {
		//Not being used anymore
	}

	/**
		 Not in ER5 - This is used internally by the ER2 system
		@deprecated
	*/
	@Deprecated
	public int getHighVolumeInterfaceLevel() {
		return 0;
	}
	

	    /** @setter for sessionId
    *   @since ER 6
    */

    public void setSessionId(String sessionId)
    {
    	mSessionId = sessionId;
    }

    /** @getter for sessionId
    *   @since ER 6
    */
    public String getSessionId()
    {
    	return mSessionId;
    }
    /** @setter for mSubscribed
     *   @since ER 6
     */

     public void setSubscribed(boolean val)
     {
     	mSubscribed = val;
     }

     /** @getter for mSubscribed
     *   @since ER 6
     */
     public boolean getSubscribed()
     {
     	return mSubscribed;
     }
	/****************Angie German Migration **********/      
       public boolean isSubscribed()
       {
         //return this.subscribedFlag;
         //Remedy 4953 - set correct subscribed variable
         return this.mSubscribed;

       }
	public void setSubscriptionFlag(boolean flag)
       {
         this.subscribedFlag   = flag;       
       }
    /************************************************/
    

    

}
