package com.vizzavi.ecommerce.business.selfcare;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang.StringUtils;

import com.vizzavi.ecommerce.business.common.ChargingResource;
import com.vizzavi.ecommerce.common.ErCountry;


/**
 * Represents a single entry in the ER_PAYMENT_TRANSACTIONS table.    <b>Only works in the context of JPA</b>
 * @author matt
 *
 */
@Entity
@Table(name="ER_PAYMENT_TRANSACTIONS")
public class PaymentTxn extends Transaction {

	private static final long serialVersionUID = -2371875240842074575L;

	public PaymentTxn(){
		
	}
	
	public PaymentTxn(Transaction transaction){
		super(transaction);
		setAmount(new BigDecimal(super.getPurchaseRate()));
		setRateIdentifier(transaction.mRateIdentifier);
		setTimeStamp(new Date());
		setCsrId(super.getCsrId());
	}

	private List<RefundTxn>	refundTransactions;

	private BigDecimal amount;

	/**
	 * The transaction Amount.  <b>Only works in the context of JPA</b>
	 * @return
	 */
	@Column(name="AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}

	protected void setAmount(BigDecimal amount)	{
		this.amount = amount;
	}

	/**
	 * Returns the transactionID as a long.  If there is a problem it will be negative.
	 * same as {@link #getTransactionIdLong}
	 * @return
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="txnseq")
	@SequenceGenerator(name="txnseq", sequenceName="ER_PAYMENT_TRANSACTIONS_SEQ", allocationSize=1/*00, initialValue = 10*/)
	@Id
	@Column(name="PAYMENT_OBJ_ID")
	public long getId() {
		return mTransactionIdLong;
	}

	public void setId(Long id)	{
		this.mTransactionIdLong = id;
	}

	@OneToMany(targetEntity=RefundTxn.class,  mappedBy="paymentTransaction")
	public List<RefundTxn> getRefundTransactions() {
		return refundTransactions;
	}


	//TODO all these methods don't really need to be here
	//they are only here for the annotations 
	//we could probably achieve the same thing with AttributeOverride
	//eg     @AttributeOverride(name="address", column=@Column(name="ADDR"))
	//where the parent has a method public void setAddress(String address) { ... }

	public void setRefundTransactions(List<RefundTxn> refundTransactions) {
		this.refundTransactions = refundTransactions;
	}

	@Override
	@Transient
	public String getMsisdn()	{
		if (getSubscription()!=null && StringUtils.isNotBlank(getSubscription().getMsisdn()))
			return getSubscription().getMsisdn();
		else
			return super.getMsisdn();
	}

	@Column(name="ZERO_COST_IGNORE")
	public boolean isZeroCostIgnore() {
		return zeroCostIgnore;
	}

	//JIRA ET-331 usage_time field removal
	/*@Column(name="usage_time")
	public double getUsageTime() {
		return usageTime;
	}*/

	@Column(name="TRANSACTION_TYPE_OBJ_ID")
	int getTxnTypeId()	{
		return TransactionTypeEnum.getTransactionType(getType().getType()).getId();
	}

	@Transient
	public double getPurchaseRate()	{
		return getAmount().doubleValue();
	}

	@Column(name="tax_rate")
	public double getTaxRate()	{
		return mTaxRate;
	}

	/**
	 * same as {@link #getPurchaseCurrency}
	 * @return
	 */
	@ManyToOne(optional=false, fetch=FetchType.LAZY)	
	@JoinColumn(name="resource_obj_id")
	public ChargingResource getChargingResource()	{
		return mResource;
	}

	@Column(name="content_name")
	public String getContentName()	{
		return getContentDescription();
	}

	@Column(name="event_units")
	public double getEventUnits() {
		double rv =0.0;
		if (mMatchingAttributes!=null) rv = mMatchingAttributes.getEventUnits();
		return rv;
	}

	@ManyToOne(optional=false,	targetEntity=Subscription.class, fetch=FetchType.LAZY)	
	@JoinColumn(name="subscription_obj_id")
	public Subscription getSubscription()	{
		return mSubscription;
	}

	@Column(name="description")
	public String getDescription()	{
		return mDescription;
	}

	/**
	 * eg 101 for COMPLETED, etc
	 * @return
	 */
	@Column(name="status_obj_id")
	public int getStatus()	{
		return mStatus;
	}

	@Column(name="auth_code")
	public String getAuthCode()	{
		return mAuthCode;
	}

	@Column(name="error_id")
	public String getPaymentErrorId()	{
		return mErrorId;
	}

	@Column(name="error_description")
	public String getPaymentErrorDescription()	{
		return mErrorDescription;
	}

	@Column(name="client_id")
	public String getClientId() {
		return mClientId;
	}

	@Column(name="host_id")
	public String getHostId() {
		return mHostId;
	}

	@Column(name="payment_info")
	public String getPaymentInfo()	{
		return mPaymentInfo;
	}

	@Column(name="bearer_type")
	public String getBearer()	{
		return mBearer;
	}

	@Column(name="asset_id")
	public String getAssetId(){
		return mAssetId;
	}

	@Column(name="access_channel")
	public String getAccessChannel()	{
		return mAccessChannel;
	}

	@Column(name="PURCHASE_CHANNEL")
	public String getPurchaseChannel()	{
		return mPurchaseChannel;
	}

	@Column(name="RATE_TAG")
	public String getRateIdentifier()	{
		return mRateIdentifier;
	}

	public void setRateIdentifier(String rateIdentifier){
		this.mRateIdentifier = rateIdentifier;
	}
	
	@Column(name="PAYMENT_PARENT_ID")
	public long getParentTransactionIdLong() {
		return mParentTransactionIdLong;
	}

	@Column(name="externalfield1")
	public String getExternalField1() {
		return mExternalField1;
	}

	@Column(name="partner_id")
	public String getPartnerId() {
		return super.getPartnerId();
	}
	
	void setCountry(ErCountry opco) {
		super.country = opco;
	}
	
	@Transient
	public ErCountry getCountry()	{
		return country;
	}
	//MQC13397
//	public void setCountryId(int id)	{
//		country = ErCountry.getCountryById(id);
//	}
//	@Column(name="country_obj_id")
//	private Integer getCountryId()	{
//		return country.getErId();
//	}
	
	@Column(name="child_sp_id")
	public String getChildSpId() {
		return childSpId;
	}
//	@Column(name="TIME_STAMP")
//	public Date getTimeStamp()	{
//		return getPurchaseDate();
//	}
	@Column(name="AFFILIATE_ID")
	public String getAffiliateID(){

		return super.getAffiliateID();
	}
	@Column(name="CONTENT_CATEGORY")
	public String getContentCategory() {
		return super.getContentCategory();
	}
	@Column(name="DEVICE_ID")
	public String getDeviceId()	{
		return super.getDeviceId();
	}
	@Column(name="EXTERNALFIELD2")
	public String getExternalField2() {
		return super.getExternalField2();
	}
	@Column(name="EXTERNALTRANSID")
	public String getExternalTransId() {
		return super.getExternalTransId();
	}
	@Column(name="INVOICE_TEXT")
	public String getInvoiceText()  	{  
		return super.getInvoiceText();  
	}
	@Column(name="IS_PREPAY")
	public String getIsPrepay()  	{  
		return super.getIsPrepay();  
	}
	@Column(name="PRODUCT_CODE")
	public String getProductCode()	{  
		return super.getProductCode();  
	}
	@Column(name="RECEIPIENT_MSISDN")
	public String getReceipientMsisdn()  {  
		return super.getReceipientMsisdn();  
	}
	@Column(name="SESSION_ID")
	public String getSessionId()	{
		return super.getSessionId();
	}
	@Column(name="SP_TYPE")
	public String getSpType() {
		return super.getSpType();
	}
	@Column(name="STANDARD_RATE")
	public double getStandardRate()	{
		return super.getStandardRate();
	}
	@Column(name="SUB_PERIOD_START")
	public Date getSubPeriodStart()	{
		return super.getSubPeriodStart();
	}
	
	@Column(name="SUB_PERIOD_END")
	public Date getSubPeriodEnd()	{
		return super.getSubPeriodEnd();
	}
	@Column(name="EXPRESS")
	public boolean getExpressFlag()	{
		return super.getExpressFlag();
	}
	
	@Column(name="CONTENT_ID")
	public String getServiceId()	{
		return super.getServiceId();
	}
	@Column(name="AGGREGATOR_ID") 
	public String getAggregatorId() {
		return super.getAggregatorId()	;
	}
	
	 
}
