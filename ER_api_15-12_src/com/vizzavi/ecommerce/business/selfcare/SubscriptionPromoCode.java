package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the ER_PROMOTION_HISTORY database table.<br/>
 * JIRA ET196 - Subscription Promo Code info from the ER_PROMOTION_HISTORY table
 * @author trushantpatel
 * @see {@link SubscriptionPromoCodePK} the primary key class for this class
 */
@Entity
@Table(name="ER_PROMOTION_HISTORY_RT")
public class SubscriptionPromoCode implements Serializable	{
   private static final long serialVersionUID = 4950706325859081741L;

   public SubscriptionPromoCode(){
	   id = new SubscriptionPromoCodePK();
   }

	@EmbeddedId
	private SubscriptionPromoCodePK id;

	@Column(name="CONTENT_ID")
	private String contentId;

	@Column(name="COUNTRY_OBJ_ID")
	private int countryObjId;

	@Temporal(TemporalType.DATE)
	@Column(name="TIME_STAMP")
	private Date timeStamp;

//	@Temporal(TemporalType.DATE)
//	@Column(name="UPDATE_TIME_STAMP")
//	private Date updateTimeStamp;


	public SubscriptionPromoCodePK getId() {
		return this.id;
	}

	public void setId(SubscriptionPromoCodePK id) {
		this.id = id;
	}

	public String getContentId() {
		return this.contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

//	public Date getUpdateTimeStamp() {
//		return this.updateTimeStamp;
//	}

//	public void setUpdateTimeStamp(Date updateTimeStamp) {
//		this.updateTimeStamp = updateTimeStamp;
//	}
   

   /**
    * NB this constructor is not suitable for persisting one of these objects using JPA, since the accountId is not set
    * @param subId - the subscriptionId to set
    * @param promo - the promoCode to set
    * @param packageId - the packageId to set
    * @see {@link #SubscriptionPromoCode(long accountId, long subId, String promo, String packageId, Date timeStamp)}
    */
   public SubscriptionPromoCode(long subId, String promo, String packageId, Date timeStamp) {
	   this();
	   id.setSubscriptionId(subId);
	   id.setPromoCode(promo);
	   id.setPackageId(packageId);
	   this.timeStamp = timeStamp;
   }
   
   /**
    * Use this constructor when creating an object for population into the DB using JPA
    * @param accountId
    * @param subId
    * @param promo
    * @param packageId
    * @param timeStamp
    */
   public SubscriptionPromoCode(long accountId, long subId, String promo, String packageId, Date timeStamp) {
	   this(subId, promo, packageId, timeStamp);
	   id.setAccountId(accountId);
   }
   
   /**
    * @return the subscriptionId
    */
   public long getSubscriptionId() {
	   return this.id.getSubscriptionId();
   }
   
   /**
    * @return the promoCode
    */
   public String getPromoCode() {
	   return this.id.getPromoCode();
   }
   
   /**
    * @return the packageId
    */
   public String getPackageId() {
	   return this.id.getPackageId();
   }
   
   /**
    * @return the subscriptionId as a String
    */
   public String getSubscriptionIdString()
   {
	   return Long.toString(getSubscriptionId());

   }

   
   /**
    * @return toString
    */
   public String toString()
   {
	   StringBuffer buf = new StringBuffer();
       buf.append("{");
       buf.append("subscriptionId").append(getSubscriptionId());
       buf.append(",promocode=").append(getPromoCode());
       buf.append(",packageId=").append(getPackageId());
       buf.append(",timeStamp=").append(timeStamp);
       buf.append("}");
       return buf.toString();
    }
}
