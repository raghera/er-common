package com.vizzavi.ecommerce.business.selfcare;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * represents a single row in the ER_REFUNDS table.   <b>Only works in the context of JPA</b>
 * @author matt
 *
 */
@Entity
@Table(name="ER_REFUNDS")
public class RefundTxn extends Transaction {

	private static final long	serialVersionUID	= 8010436701742389699L;

	public RefundTxn(){
		
	}
	
	public RefundTxn(Transaction transaction){
		super(transaction);
		setCsrId(super.getCsrId());
	}

	private PaymentTxn paymentTransaction;

	private BigDecimal amount;

	/**
	 * The refund Amount. 
	 * @return
	 */
	@Column(name="AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount)	{
		this.amount = amount;
	}

	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="txnseq")
	@SequenceGenerator(name="txnseq", sequenceName="ER_REFUND_TRANSACTIONS_SEQ", allocationSize=1/*00, initialValue = 10*/)
	@Id
	@Column(name="REFUND_OBJ_ID")
	public long getId() {
		return mTransactionIdLong;
	}

	public void setId(Long id)	{
//		this.mTransactionIdLong = id;
		setTransactionIdLong(id);
	}

	@ManyToOne(optional=false,	targetEntity=PaymentTxn.class, fetch=FetchType.LAZY)	
	@JoinColumn(name="PAYMENT_OBJ_ID")
	public  PaymentTxn getPaymentTransaction()	{
		return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTxn txn)	{
		this.paymentTransaction = txn;
		setRefundPaymentTransactionId(txn.getTransactionId());
	}


	@Transient
	public String getRefundPaymentTransactionId()	{
		return paymentTransaction.getTransactionId();
	}


	@Transient
	public long getRefundPaymentTransactionIdLong() {
		return paymentTransaction.mTransactionIdLong;
	}

	@Column(name="description")
	public String getDescription()	{
		return super.getDescription();
	}

	/**
	 * eg 101 for COMPLETED, etc
	 * @return
	 */
	@Column(name="status_obj_id")
	public int getStatus()	{
		return super.getStatus();
	}

	@Column(name="REFUND_TYPE_OBJ_ID")
	int getTxnTypeId()	{
		return super.getTxnTypeId();
	}
	
	@Column(name="NEXT_CYCLE_DISCOUNT")
	public double getNextCycleDiscount()	{
		return super.getNextCycleDiscount();
	}
	
	@Column(name="ENLARGEMENT_DATE")
	public Date getRefundEnlargementDate()	{
		return super.getRefundEnlargementDate();
	}
	
	@Column(name="partner_id")
	public String getPartnerId() {
		return super.getPartnerId();
	}   
	
	@Transient
	public double getPurchaseRate()	{
		return getAmount().doubleValue();
	}
	
	@Transient
	public Date getRefundDate()	{
		return super.getPurchaseDate();
	}
	
	@Column(name="ERROR_DESCRIPTION") 
	public String getPaymentErrorDescription() {
		return super.getPaymentErrorDescription()	;
	}
	@Column(name="ERROR_ID") 
	public String getPaymentErrorId()	{
		return super.getPaymentErrorId();
	}
	@Column(name="REASON") 
	public String getReason()	{
		return super.getReason();
	}
	@Column(name="PARENT_TRANSACTION_ID") 
	public long getParentTransactionIdLong() {
		return super.getParentTransactionIdLong();
	}
}
