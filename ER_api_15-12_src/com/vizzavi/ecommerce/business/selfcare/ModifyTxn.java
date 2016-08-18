package com.vizzavi.ecommerce.business.selfcare;

import java.util.Date;
import java.util.Locale;

import javax.persistence.*;

import org.apache.commons.lang.StringUtils;

import com.vizzavi.ecommerce.common.ErCountry;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * represents a single row in the ER_MODIFY table
 * @author matt
 *
 */
@Entity
@Table(name="ER_MODIFY")
public class ModifyTxn extends Transaction {
		
	public ModifyTxn(Transaction transaction){
		super(transaction);
		setCsrId(super.getCsrId());
	}

	private BasicAccount account;
	private String oldValue;
	private String newValue;
	
//	public ModifyTxn(ErCountry opco, TransactionType type)	{
//		super ();
//		super.setType(type);
//		setCountry(opco);
//	}
	
	/**
	 * no-arg xtr required for hibernate / JPA.  Don't make this public
	 */
	ModifyTxn(){}
	/**
	 * 
	 * @param locale
	 * @param modifyType eg TransactionType.MODIFY_USERGROUPS_TYPE
	 */
	public ModifyTxn(Locale locale, String modifyType) {
		super();
		super.setType(TransactionTypeEnum.getTransactionType(modifyType).getTransactionType());
		setCountry(ErCountry.getByLocale(locale));
		setTimeStamp(new Date());
	}

//	@Column(name="TIME_STAMP")
//	public Date getTimeStamp()	{
//		return getPurchaseDate();
//	}

	void setCountry(ErCountry opco) {
		super.country = opco;
	}
	
	@Transient
	public ErCountry getCountry()	{
		return country;
	}
	
	public void setCountryId(int id)	{
		country = ErCountry.getCountryById(id);
	}
	@Column(name="country_obj_id")
	private Integer getCountryId()	{
		return country.getErId();
	}
	
	/**
	 * this is a modify txn, so this primary key corresponds to the MODIFY_OBJ_ID column in the ER_MODIFY table
	 * @return
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="txnseq")
	@SequenceGenerator(name="txnseq", sequenceName="ER_MODIFY_SEQ", allocationSize=1/*00, initialValue = 10*/)
	@Id
	@Column(name="MODIFY_OBJ_ID")
	public Long getId() {
		if (StringUtils.isBlank(mModifyTransactionId))
			return null;
		return Long.valueOf(mModifyTransactionId);
	}

	/**
	 * this is a modify txn, so this primary key corresponds to the MODIFY_OBJ_ID column in the ER_MODIFY table
	 * @param modifyTransactionId
	 */
	public void setId(Long modifyTransactionId) {
		this.mModifyTransactionId = String.valueOf(modifyTransactionId);
	}	
	
	@Transient
	public String getMsisdn()	{
		if (getSubscription()!=null && StringUtils.isNotBlank(getSubscription().getMsisdn()))
			return getSubscription().getMsisdn();
		else
			return super.getMsisdn();
	}
	
	@ManyToOne(optional=true,	targetEntity=Subscription.class, fetch=FetchType.LAZY)	
	@JoinColumn(name="subscription_obj_id")
	public Subscription getSubscription()	{
		return mSubscription;
	}
	
	@Column(name="host_id")
	public String getHostId() {
		return mHostId;
	}
	
	
	@Column(name="TRANSACTION_TYPE_OBJ_ID")
	int getTxnTypeId()	{
		return TransactionTypeEnum.getTransactionType(getType().getType()).getId();
	}
	
	@Transient
	public String getModifyTransactionId() {
		return String.valueOf(getId());
	}
	
	@Column(name="REASON")
	public String getReason()	{
		return super.getReason();
	}

	@ManyToOne(optional=true,	targetEntity=BasicAccount.class, fetch=FetchType.LAZY)	
	@JoinColumn(name="account_obj_id")
	BasicAccount getAccount() {
		return account;
	}

	public void setAccount(BasicAccount account) {
		this.account = account;
	}

	@Column(name="old_value")
	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	@Column(name="new_value")
	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	
	public String toString()	{
		return getId()+" ("+getType().getType()+") from "+oldValue+" to "+newValue;
	}
	
	/**
	 * not fully written yet.  sets old and new values, and client id
	 */
	@Transient
	public void setModificationInfo(ModificationInfo modInfo)	{
		setOldValue(modInfo.getOldValue());
		setNewValue(modInfo.getNewValue());
		if (isNotBlank(modInfo.getClientId()))
			setClientId(modInfo.getClientId());
		setHostId(modInfo.getHostId());

		//modInfo.get
//		TransactionTypeEnum.getTransactionType(modInfo.getModificationType())
//		setTxnTypeId(modInfo.getModificationType());
		//TODO write me
	}
	
	@Column(name="CLIENT_ID")
	public String getClientId()	{
		return super.getClientId();
	}
}
