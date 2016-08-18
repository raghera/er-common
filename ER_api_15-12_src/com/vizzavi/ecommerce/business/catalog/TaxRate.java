package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TaxRate implements Serializable, CatalogBean	{

	private    static final long serialVersionUID = -3497324044474717963L;


	private double mValue;
	private Date mStartDate;
	private Date mEndDate;

	private Tax	tax;


	private long	key;

	public TaxRate() {
	}

	public TaxRate(double value, Date startDate, Date endDate) {

		mValue = value;
		mStartDate = startDate;
		mEndDate = endDate;
	}

	public TaxRate(double value) {
		mValue = value;
		mStartDate = null;
		mEndDate = null;
	}

	public TaxRate(double value, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus) {
		//        mCreatedBy = createdBy;
		//		mModifiedBy = modifiedBy;
		//        mModifiedDate = modifiedDate;
		//    	mActiveStatus = activeStatus;
		mValue = value;
		mStartDate = null;
		mEndDate = null;
	}

	@ManyToOne(optional=false,	targetEntity=Tax.class, fetch=FetchType.EAGER)	
	Tax getTax()	{
		return this.tax;
	}

	void setTax (Tax newTax)	{
		tax = newTax;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public	Long getKey()	{
		return key;
	}

	void setKey(long key)	{
		this.key=key;
	}

	public void setValue(double value) { mValue = value; }


	public double getValue() { return mValue; }


	public void setStartDate(Date startDate) { mStartDate = startDate; }


	public Date getStartDate() { return mStartDate; }


	public void setEndDate(Date endDate) { mEndDate = endDate; }


	public Date getEndDate() { return mEndDate; }


	@Override
	public String toString()	{
		StringBuffer sb = new StringBuffer("TaxRate ");
		sb.append(this.mValue).append('%');
		if (this.mStartDate!=null)
			sb.append(" starts ").append(this.mStartDate);
		if (this.mEndDate!=null)
			sb.append(" ends ").append(this.mEndDate);
		return sb.toString();
	}
}
