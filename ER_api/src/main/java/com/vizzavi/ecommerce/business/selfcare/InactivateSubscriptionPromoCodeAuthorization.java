package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;

import com.vizzavi.ecommerce.business.common.ReasonCode;

/**
 * JIRA ET196 Inactivate subscription promo-code response
 * 
*/

public class InactivateSubscriptionPromoCodeAuthorization implements Serializable {
	
	private static final long serialVersionUID = -1312577443044254738L;
	
	private boolean success;
	
	private long subscriptionId = -1;
	
	private String packageId;
	   
	private ReasonCode reason;
	
	//The result from the DB Stored proc to update the ER_PROMOTION_HISTORY table:
	//1 = SUCCESS
	//2 = SUBSCRIPTION PROMO CODE NOT PRESENT IN ER_PROMOTION_HISTORY table
	//3 = SUBSCRIPTION PROMO CODE ALREADY INACTIVATED IN ER_PROMOTION_HISTORY table
	//4 = SYSTEM ERROR
	private int result;
	
	private String oldPromoCode;
	
	private String newPromoCode;
	
	public InactivateSubscriptionPromoCodeAuthorization() {
		
	}
	
	public InactivateSubscriptionPromoCodeAuthorization(boolean isSuccess, ReasonCode reason) {
		this.success = isSuccess;
		this.reason = reason;
	}
	
	public InactivateSubscriptionPromoCodeAuthorization(boolean isSuccess, long subId, ReasonCode reason) {
		this.success = isSuccess;
		this.reason = reason;
		this.subscriptionId = subId;
	}
	
	public InactivateSubscriptionPromoCodeAuthorization(long subId, String packId, int result, String oldPromo, String newPromo) {
		this.subscriptionId = subId;
		this.packageId = packId;
		this.result = result;
		this.oldPromoCode = oldPromo;
		this.newPromoCode = newPromo;
	}
	
	
   /**
    * @return is success
    */	
	public boolean isSuccess() {
		return this.success;
	}
	
   /**
    * @param subId
 	*/
   public void setSuccess(boolean success) {
	   this.success = success;
   }
	   
   /**
    * @return the subscriptionId
    */
   public long getSubscriptionId() {
	   return this.subscriptionId;
   }
	   
   /**
    * @param subId
 	*/
   public void setSubscriptionId(long subId) {
	   this.subscriptionId = subId;
   }
   
   /**
    * @return the subscriptionId as a String
    */
   public String getSubscriptionIdString()
   {
	   return Long.toString(subscriptionId);

   }
   
   /**
    * @return the packageId
    */
   public String getPackageId() {
	   return this.packageId;
   }
   
   /**
    * @param packId
 	*/
   public void setPackageId(String packId) {
	   this.packageId = packId;
   }
   
   /**
    * @return the reason
    */
   public ReasonCode getReasonCode() {
	   return this.reason;
   }
	
   public void setReasonCode(ReasonCode reason) {
	   this.reason = reason;
   }
   
   /**
    * @return the result
    */
   public int getResult() {
	   return this.result;
   }
   
   /**
    * @param result
 	*/
   public void setResult(int result) {
	   this.result = result;
   }
   
   /**
    * @return the oldPromoCode
    */
   public String getOldPromoCode() {
	   return this.oldPromoCode;
   }
   
   /**
    * @param oldPromo
 	*/
   public void setOldPromoCode(String oldPromo) {
	   this.oldPromoCode = oldPromo;
   }
   
   /**
    * @return the newPromoCode
    */
   public String getNewPromoCode() {
	   return this.newPromoCode;
   }
   
   /**
    * @param newPromo
 	*/
   public void setNewPromoCode(String newPromo) {
	   this.newPromoCode = newPromo;
   }
   
   /**
    * @return toString
    */
   public String toString()
   {
	   StringBuffer buf = new StringBuffer();
       buf.append("{");
       buf.append("isSuccess=").append(success);
       buf.append(",subscriptionId").append(subscriptionId);
       buf.append(",packageId=").append(packageId);
       buf.append(",reasonCode=").append(reason);
       buf.append(",result=").append(reason);
       buf.append(",oldPromoCode=").append(oldPromoCode);
       buf.append(",newPromoCode=").append(newPromoCode);
       buf.append("}");
       return buf.toString();
    }
	
}

