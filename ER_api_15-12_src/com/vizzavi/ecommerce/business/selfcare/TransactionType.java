package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.selfcare.Transaction.MetaType;

//TODO - make this an enum, with a display name, an id (as per DB), and all the isXXX() methods as per below
/**
 * eg PAYMENT_PACKAGE_TRANSACTION, REFUND_CREDIT, GOODWILL_CREDIT, etc
 *
 * @see {@link Transaction.MetaType}
 * @see {@link TransactionTypeEnum}
 */
public class TransactionType implements Serializable	{
	
   private    static final long serialVersionUID = -7819313848690768532L;
   private static final Logger logger = LoggerFactory.getLogger(TransactionType.class);
   
    public static final String PAYMENT_NEW_PACKAGE_TRANSACTION = "PAYMENT_NEW_PACKAGE_TRANSACTION";
    public static final String PAYMENT_PACKAGE_TRANSACTION = "PAYMENT_PACKAGE_TRANSACTION";
    public static final String PAYMENT_CONTENT = "PAYMENT_CONTENT_TRANSACTION";
    public static final String PACKAGE_ADDED = "PACKAGE_ADDED";
    public static final String MODIFICATION = "MODIFICATION RECORD";
    public static final String REFUND_NON_CASH = "REFUND_PKG-NONCASH-REFUND";
    public static final String REFUND_CASH = "REFUND_PKG-CASH-REFUND";
    public static final String REFUND_DISCOUNT = "REFUND_PKG-CYCLE-DISCOUNT";
    public static final String REFUND_ENLARGEMENT = "REFUND_PKG-ENLARGEMENT";
    public static final String REFUND_CREDIT = "REFUND_CREDIT";
    public static final String RECURRING_PACKAGE_TRANSACTION = "PAYMENT_RECURRING_PACKAGE_TRANSACTION";
    public static final String RENEWAL_PACKAGE_TRANSACTION = "PAYMENT_RENEWAL_PACKAGE_TRANSACTION";
	public final static String MODIFY_CHARGING_METHOD_TYPE = "MOD_CHA_METH_TYPE";
	public final static String MODIFY_PAYMENT_TYPE_TYPE = "MOD_PAY_TYPE_TYPE";
	public final static String MODIFY_MSISDN_TYPE = "MOD_MSISDN_TYPE";
	public final static String MODIFY_BAN_TYPE = "MOD_BAN_TYPE";
	public final static String MODIFY_INACTIVATE_SUBSCRIPTION_TYPE = "MOD_INACT_SUB_TYPE";
	public final static String MODIFY_INACTIVATE_ACCOUNT_TYPE = "MOD_INACT_ACC_TYPE";
	public final static String DUNNING_REQUEST_TRANSACTION = "DUNNING_REQUEST_TRANSACTION";
    //CR1564-utctimezone for diff region in county
	public final static String MODIFY_TIMEZONE_TYPE= "MOD_TIMEZONE_TYPE";
	public final static String MODIFY_BILLINGCYCLE_TYPE= "MOD_BILLINGCYCLE_TYPE";
	public final static String MODIFY_ACCOUNTSTATUS_TYPE= "MOD_ACCOUNTSTATUS_TYPE";
	public final static String MODIFY_USERGROUPS_TYPE= "MOD_MODIFYUSERGROUPS_TYPE";

	public final static String MODIFY_ACCOUNT_SP_ID_TYPE 	 	= "MODIFY_ACCOUNT_SP_ID_TYPE";		// CR 1643 - Pre-Pay Post-Pay Split
	public final static String MODIFY_ACCOUNT_IS_PREPAY_TYPE	= "MODIFY_ACCOUNT_IS_PREPAY_TYPE";	// CR 1643 - Pre-Pay Post-Pay Split

	//MQC 6629 - add modification subscription type
	public final static String MODIFY_SUBSCRIPTION_TYPE= "MOD_SUBSCRIPTION_TYPE";
	
	// CR2040 MPAY replacement.  Goowill credit.
	public final static String GOODWILL_CREDIT = "GOODWILL_CREDIT";
	// CR2040 MPAY replacement.  Modify MSISDN spend limits.
	public static final String MODIFY_SPEND_LIMITS_TYPE = "MODIFY_SPEND_LIMITS_TYPE";
	
	//CR2198 - Modify the Account Child Service Provider ID
	public final static String MODIFY_ACCOUNT_CHILD_SP_ID_TYPE 	 	= "MODIFY_ACCOUNT_CHILD_SP_ID_TYPE";
	//CR2198 - Modify the Account Service Provider Type
	public final static String MODIFY_ACCOUNT_SP_TYPE	= "MODIFY_ACCOUNT_SP_TYPE";
	
	//JIRA ET155 - Add Modify Account Suppress Notifications Modify Transaction
	public final static String MODIFY_ACCOUNT_SUPPRESS_NOTIFICATIONS	= "MODIFY_ACCOUNT_SUPPRESS_NOTIFICATIONS";
	
	//JIRA ET196 Inactivate subscription promo-code
	public final static String MODIFY_INACTIVATE_SUB_PROMO_CODE	= "MODIFY_INACTIVATE_SUB_PROMO_CODE";
	
    private final String mName;
    
    public TransactionType(String name)
    {
    	if (StringUtils.isBlank(name))	{	//MQc9679
    		logger.error("can't create a transaction type with name [{}] - see stack trace at WARN level", name);
    		//to actually debug it we'll need a stack trace
    		if (logger.isWarnEnabled())	{
    			Exception e = new Exception();
    			logger.warn("can't create a transaction type with no name", e);
    		}
    	}
        mName = name;
    }

    /**
     * will return false for a usage (including monetary) but true for any package purchases, renewals, etc
     * @return
     */
    public boolean isPackagePayment()    {
        return ((mName.equals(PAYMENT_PACKAGE_TRANSACTION))
        	|| (mName.equals(PAYMENT_NEW_PACKAGE_TRANSACTION))
        	|| (mName.equals(RECURRING_PACKAGE_TRANSACTION))
        	|| (mName.equals(RENEWAL_PACKAGE_TRANSACTION)));
    }

    /**
     * is this the first purchase transaction made on a subscription? (as opposed to the renewals which happen after)
     * @return
     */
    public boolean isNewPackagePayment()   {
        //Remedy 3463
       return ((mName.equals(PAYMENT_NEW_PACKAGE_TRANSACTION))||(mName.equals(PAYMENT_PACKAGE_TRANSACTION)));
    }

    /**
     * a modify txn (6)
     * @return
     */
    public boolean isSubscription()    {
        return mName.equals(PACKAGE_ADDED);
    }

    /**
     * any kind of modify including account or subscription modifications
     * @return
     */
    public boolean isModification()    {
        if (mName.equals(MODIFICATION)
        	||mName.equals(MODIFY_CHARGING_METHOD_TYPE)
        	||mName.equals(MODIFY_PAYMENT_TYPE_TYPE)
        	||mName.equals(MODIFY_MSISDN_TYPE)
        	||mName.equals(MODIFY_BAN_TYPE)
        	||mName.equals(MODIFY_INACTIVATE_SUBSCRIPTION_TYPE)
        	||mName.equals(MODIFY_INACTIVATE_ACCOUNT_TYPE)
        	||mName.equals(MODIFY_TIMEZONE_TYPE)
        	||mName.equals(MODIFY_BILLINGCYCLE_TYPE)
        	||mName.equals(MODIFY_ACCOUNTSTATUS_TYPE)
        	//MQC 6629 - add modification subscription type
        	||mName.equals(MODIFY_SUBSCRIPTION_TYPE)
        	||mName.equals(MODIFY_ACCOUNT_CHILD_SP_ID_TYPE) /*CR2198 - Modify the Account Child Service Provider ID and Service Provider Type*/
        	||mName.equals(MODIFY_ACCOUNT_SP_TYPE)
        	//MQC 8068 - add modify usergroups and modify spend limits
        	||mName.equals(MODIFY_USERGROUPS_TYPE)
        	||mName.equals(MODIFY_SPEND_LIMITS_TYPE)
        	||mName.equals(MODIFY_ACCOUNT_IS_PREPAY_TYPE)
        	||mName.equals(MODIFY_ACCOUNT_SP_ID_TYPE)
        	//JIRA ET155 - Add Modify Account Suppress Notifications Modify Transaction
        	||mName.equals(MODIFY_ACCOUNT_SUPPRESS_NOTIFICATIONS)
        	//JIRA ET196 Inactivate subscription promo-code
        	||mName.equals(MODIFY_INACTIVATE_SUB_PROMO_CODE)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isRefundNonCash()
    {
        return (mName.equals(REFUND_NON_CASH));
    }

    public boolean isRefundCash()    {
        return mName.equals(REFUND_CASH);
    }

    public boolean isRefundDiscount()    {
        return mName.equals(REFUND_DISCOUNT);
    }

    public boolean isRefundEnlargement()    {
        return mName.equals(REFUND_ENLARGEMENT);
    }

    /**
     * i.e. a usage transaction
     * @return
     */
    public boolean isPaymentContent()    {
        return mName.equals(PAYMENT_CONTENT);
    }

    /**
     * is a recur - ie a renewal triggered by the batch<br/>
     * PAYMENT_RECURRING_PACKAGE_TRANSACTION (4)
     * @return
     */
    public boolean isRecurringPayment()    {
        return mName.equals(RECURRING_PACKAGE_TRANSACTION);
    }

    /**
     * is a renewal triggered by a client (ie not by the batch)<br/>
     * PAYMENT_RENEWAL_PACKAGE_TRANSACTION (5)
     * @return
     */
    public boolean isRenewalPayment()    {
        return mName.equals(RENEWAL_PACKAGE_TRANSACTION);
    }

    /**
     * is a renewal of any kind (ie triggered by an external client or by the batch)<br/>
     * PAYMENT_RECURRING_PACKAGE_TRANSACTION (4) or PAYMENT_RENEWAL_PACKAGE_TRANSACTION (5)
     * @return
     */
    public boolean isRecurOrRenew()	{
    	return isRenewalPayment() || isRecurringPayment();
    }

    /**
     * a String representing the type of this transaction
     * e.g. REFUND_PKG-NONCASH-REFUND
     * @return
     */
    public String getType()    {
        return mName;
    }

    @Override
	public String toString()    {
        return getType();
    }

    /**
     * same as {@link #getType} - use that instead
     * @return
     */
    public String getResourceName()    {
        return getType();
    }
    public boolean isDunningTransaction()    {
        return (mName.equals(DUNNING_REQUEST_TRANSACTION));
    }
    
    public boolean isCreditRefundTransaction()    {
        return mName.equals(REFUND_CREDIT);
    }
    
    //MQC 6281 - is account modification transaction
    public boolean isAccountModification()
    {
        if (mName.equals(MODIFY_TIMEZONE_TYPE)
        	||mName.equals(MODIFY_BILLINGCYCLE_TYPE)
        	||mName.equals(MODIFY_USERGROUPS_TYPE)
        	||mName.equals(MODIFY_ACCOUNT_SP_ID_TYPE)
        	||mName.equals(MODIFY_ACCOUNT_IS_PREPAY_TYPE)
        	||mName.equals(MODIFY_MSISDN_TYPE)
        	||mName.equals(MODIFY_BAN_TYPE)
        	||mName.equals(MODIFY_SPEND_LIMITS_TYPE) /* CR2040 MPAY replacement*/
        	||mName.equals(MODIFY_ACCOUNT_CHILD_SP_ID_TYPE) /*CR2198 - Modify the Account Child Service Provider ID and Service Provider Type*/
        	||mName.equals(MODIFY_ACCOUNT_SP_TYPE)
        	//JIRA ET155 - Add Modify Account Suppress Notifications Modify Transaction
        	||mName.equals(MODIFY_ACCOUNT_SUPPRESS_NOTIFICATIONS)) {
            return true;
        } else {
            return false;
        }
    }
    
    //CR 2080 - SMS Notification additions
    public boolean isRefund()    {
        return isRefundCash() || isRefundDiscount() || isRefundEnlargement() 
        		|| isRefundNonCash() || isCreditRefundTransaction();
    }

    //MQC 9593 - is modify msisdn
    public boolean isModifyMsisdn()    {
        return mName.equals(MODIFY_MSISDN_TYPE);
    }

    public boolean isGoodwillCredit()	{
    	return mName.equals(GOODWILL_CREDIT);
    }
    
    //MQC 9420 - is inactivate subscription
    public boolean isModifyInactivateSubscription()    {
        return mName.equals(MODIFY_INACTIVATE_SUBSCRIPTION_TYPE);
    }
    
    /**
     * JIRATET183 - is modify charging method type
     * @return boolean
     */
    public boolean isModifyChargingMethod()    {
        return mName.equals(MODIFY_CHARGING_METHOD_TYPE);
    }
    
    /**
     * JIRATET183 - is modify subscription type
     * @return boolean
     */
    public boolean isModifySubscription()    {
        return mName.equals(MODIFY_SUBSCRIPTION_TYPE);
    }
    
    /**
     * JIRATET183 - is modify usergroups type
     * @return boolean
     */
    public boolean isModifyUserGroups()    {
        return mName.equals(MODIFY_USERGROUPS_TYPE);
    }
    
    /**
     * JIRATET183 - is modify BAN type
     * @return boolean
     */
    public boolean isModifyBAN()    {
        return mName.equals(MODIFY_BAN_TYPE);
    }
    
    /**
     * JIRATET196 - is modify inactive sub promo code type
     * @return boolean
     */
    public boolean isModifyInactivateSubPromoCode()    {
        return mName.equals(MODIFY_INACTIVATE_SUB_PROMO_CODE);
    }

    /**
	 *  one of Payment, Refund, Modification, Goodwill_Credit or Usage.  Or null if it can't figure out the right type.
	 * @author matt
	 * @return {@link MetaType}
	 */
    public MetaType getMetaType()	{
    	if (isRefund())
    		return MetaType.Refund;
    	if (isModification())
    		return MetaType.Modification;
    	if (isGoodwillCredit())
    		return MetaType.Goodwill_Credit;
    	if ( isPaymentContent())
    		return MetaType.Usage;
    	if (isPackagePayment())
    		return MetaType.Payment;
    	
    	logger.warn("don't know metatype for "+getType());
    	return null;
    }
    
}
