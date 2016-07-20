package com.vizzavi.ecommerce.business.selfcare;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.vizzavi.ecommerce.business.selfcare.Transaction.MetaType;


/**
 * a class to link {@link TransactionType}, the ER_TRANSACTION_TYPE table, and {@link Transaction.MetaType}
 * @author matt
 *
 */
public enum TransactionTypeEnum {	   	 
	PAYMENT_NEW_PACKAGE_TRANSACTION 	(1,"PAYMENT_NEW_PACKAGE_TRANSACTION", "New Payment Package"),
	PAYMENT_PACKAGE_TRANSACTION (2,"PAYMENT_PACKAGE_TRANSACTION", "Payment Package"),
	PAYMENT_CONTENT (3,"PAYMENT_CONTENT_TRANSACTION", "Payment Content"),
	PACKAGE_ADDED(6,"PACKAGE_ADDED", "Package Added"),
	MODIFICATION (7,"MODIFICATION_RECORD", "Record of Modification"),
	REFUND_NON_CASH (8,"REFUND_PKG-NONCASH-REFUND", "Non-Cash Refund"),
	REFUND_CASH (9,"REFUND_PKG-CASH-REFUND", "Cash Refund"),
	REFUND_DISCOUNT(10,"REFUND_PKG-CYCLE-DISCOUNT", "Cycle Discount"),
	REFUND_ENLARGEMENT (11,"REFUND_PKG-ENLARGEMENT", "Enlargement"),
	REFUND_CREDIT (32,"REFUND_CREDIT", "Credit Refund"),
	/**triggered by the batch*/
	RECURRING_PACKAGE_TRANSACTION(4,"PAYMENT_RECURRING_PACKAGE_TRANSACTION", "Recurring Payment"),
	/**triggerd by a client*/
	RENEWAL_PACKAGE_TRANSACTION (5,"PAYMENT_RENEWAL_PACKAGE_TRANSACTION", "Renewal Payment"),
	MODIFY_CHARGING_METHOD_TYPE (12,"MOD_CHA_METH_TYPE", "Modification Of Charging Method"),
	MODIFY_PAYMENT_TYPE_TYPE(13,"MOD_PAY_TYPE_TYPE", "Modification Of Payment Type"),
	MODIFY_MSISDN_TYPE(14,"MOD_MSISDN_TYPE", "Modification Of MSISDN"),
	MODIFY_BAN_TYPE (15,"MOD_BAN_TYPE", "Modification Of BAN (Billing Account Number)"),
	MODIFY_INACTIVATE_SUBSCRIPTION_TYPE (16,"MOD_INACT_SUB_TYPE", "Inactivation of Subscription"),
	MODIFY_INACTIVATE_ACCOUNT_TYPE(17,"MOD_INACT_ACC_TYPE", "Inactivation of Account"),
	DUNNING_REQUEST_TRANSACTION (34,"DUNNING_REQUEST_TRANSACTION", "Dunning Payment request"),
	MODIFY_TIMEZONE_TYPE (36,"MOD_TIMEZONE_TYPE", "Modification Of Timezone"),
	MODIFY_BILLINGCYCLE_TYPE (37,"MOD_BILLINGCYCLE_TYPE", "Modification Of BillingCycle"),
	//	MODIFY_ACCOUNTSTATUS_TYPE ( "MOD_ACCOUNTSTATUS_TYPE"),	//TODO why is there no DB entry for this?
	MODIFY_USERGROUPS_TYPE(38,"MOD_MODIFYUSERGROUPS_TYPE", "Modification Of Usergroups"),
	MODIFY_ACCOUNT_SP_ID_TYPE 	 (39, "MODIFY_ACCOUNT_SP_ID_TYPE",  "Modification of SP_ID in Account"),
	MODIFY_ACCOUNT_IS_PREPAY_TYPE(40, "MODIFY_ACCOUNT_IS_PREPAY_TYPE",  "Modification of IS_PREPAY in Account"),
	MODIFY_SUBSCRIPTION_TYPE(42, "MOD_SUBSCRIPTION_TYPE",  "Modification of Subscription"),
	GOODWILL_CREDIT (50, "GOODWILL_CREDIT",  "Goodwill credit to customer"),
	MODIFY_SPEND_LIMITS_TYPE (51, "MODIFY_SPEND_LIMITS_TYPE",  "Modification of spend limits"),
	MODIFY_ACCOUNT_CHILD_SP_ID_TYPE 	 (52, "MODIFY_ACCOUNT_CHILD_SP_ID_TYPE",  "Modification of Child SP_ID in Account"),
	MODIFY_ACCOUNT_SP_TYPE	(53, "MODIFY_ACCOUNT_SP_TYPE",  "Modification of SP_TYPE in Account"),
	MODIFY_ACCOUNT_SUPPRESS_NOTIFICATIONS	(54, "MODIFY_ACCOUNT_SUPPRESS_NOTIFICATIONS",  "Modification of Suppress Notifications in Account"),
	MODIFY_INACTIVATE_SUB_PROMO_CODE	(55, "MODIFY_INACTIVATE_SUB_PROMO_CODE",  "Inactivation of Subscription Promo Code");
	
	private int	id;
	private String	name;
	private String	description;

	TransactionTypeEnum(int id, String name, String description)	{
		this.id=id;
		this.name=name;
		this.description = description;
	}
	
	static final Map<Integer, TransactionTypeEnum> typesById;
	static final Map<String, TransactionTypeEnum> typesByName;


	static	{	//populate hashmap using id as an index
		Map<Integer, TransactionTypeEnum> tmpTypes = new HashMap<>();
		Map<String, TransactionTypeEnum> tmpTypesByName = new HashMap<>();
		for(TransactionTypeEnum type: TransactionTypeEnum.values())	{
			tmpTypes.put(type.getId(), type);
			tmpTypesByName.put(type.getName(), type);
		}
		typesById = Collections.unmodifiableMap(tmpTypes);
		typesByName= Collections.unmodifiableMap(tmpTypesByName);
	}

	/**the database ID of the transaction type*/
	public int getId() {
		return id;
	}

	/**
	 * e.g. MOD_CHA_METH_TYPE
	 * @return
	 */
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public TransactionType getTransactionType()	{
		return new TransactionType(name);
	}

	/**
	 * look up by name - eg pass in MOD_CHA_METH_TYPE and get the MODIFY_CHARGING_METHOD_TYPE TransactionType back.
	 * @param name
	 * @return
	 */
	public static TransactionTypeEnum getTransactionType(String name)	{
		return typesByName.get(name);
	}
	
	public static TransactionType getTransactionType(int id)	{
		return getTransactionType(typesById.get(id).getName()).getTransactionType();
	}
	
	/**
	 * one of Payment, Goodwill Credit, Refund, Modification, or Usage.<br/>
	 * @return {@link MetaType}
	 */
	public MetaType getMetaType()	{
		return getTransactionType().getMetaType();
	}

}
