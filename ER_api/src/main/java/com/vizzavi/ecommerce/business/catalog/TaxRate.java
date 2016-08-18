package com.vizzavi.ecommerce.business.catalog;

import java.util.Date;

public class TaxRate implements java.io.Serializable
{
   private    static final long serialVersionUID = -3497324044474717963L;
    private String mCreatedBy;
    private String mModifiedBy;
    private Date mModifiedDate;
    private char mActiveStatus;

    private double mValue;
    private Date mStartDate;
    private Date mEndDate;


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
        mCreatedBy = createdBy;
		mModifiedBy = modifiedBy;
        mModifiedDate = modifiedDate;
    	mActiveStatus = activeStatus;
		mValue = value;
		mStartDate = null;
		mEndDate = null;
	}
    public void setValue(double value) { mValue = value; }


    public double getValue() { return mValue; }


    public void setStartDate(Date startDate) { mStartDate = startDate; }


    public Date getStartDate() { return mStartDate; }


    public void setEndDate(Date endDate) { mEndDate = endDate; }


    public Date getEndDate() { return mEndDate; }

    public String getCreatedBy() {
        return this.mCreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        this.mCreatedBy= createdBy;
    }

    public String getModifiedBy() {
        return this.mModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.mModifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return this.mModifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.mModifiedDate = modifiedDate;
    }

    public char getActiveStatus() {
        return this.mActiveStatus;
    }

    public void setActiveStatus(char activeStatus) {
        this.mActiveStatus = activeStatus;
    }
    
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
