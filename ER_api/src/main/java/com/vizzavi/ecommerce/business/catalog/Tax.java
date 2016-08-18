package com.vizzavi.ecommerce.business.catalog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Tax implements java.io.Serializable
{
	private    static final long serialVersionUID = -4417078967988329810L;
	//    private String mCreatedBy;
	//    private String mModifiedBy;
	//    private Date mModifiedDate;
	//    private char mActiveStatus;

	/**a set of all tax codes in the priceplans*/
	public static Set<String> taxCodes = new HashSet<String>();

	private String mName;
	private String mTaxCode;
	//private double mTaxRate;
	private ArrayList<TaxRate> mTaxRates;


	public Tax(String name, String taxCode, double taxRate) {

		mName = name;
		mTaxCode = taxCode;
		//mTaxRate = taxRate;
		mTaxRates = new ArrayList<TaxRate>();
		TaxRate newTaxRate = new TaxRate(taxRate);
		mTaxRates.add(newTaxRate);
		taxCodes.add(taxCode);

	}

	public Tax(String name, String taxCode, TaxRate[] taxRates) {
		mName = name;
		mTaxCode = taxCode;
		mTaxRates = new ArrayList<TaxRate>();
		if (taxRates != null) {
			for (TaxRate taxRate : taxRates) {
				mTaxRates.add(new TaxRate(taxRate.getValue(), taxRate.getStartDate(),taxRate.getEndDate()) );
			}
		}
		taxCodes.add(taxCode);

	}

	public Tax(String name, String taxCode, TaxRate[] taxRates, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus) {
		//		mCreatedBy = createdBy;
		//		mModifiedBy = modifiedBy;
		//		mModifiedDate = modifiedDate;
		//		mActiveStatus = activeStatus;

		mName = name;
		mTaxCode = taxCode;
		mTaxRates = new ArrayList<TaxRate>();
		if (taxRates != null) {
			for (TaxRate taxRate : taxRates) {
				mTaxRates.add(new TaxRate(taxRate.getValue(), taxRate.getStartDate(),taxRate.getEndDate()) );
			}
		}
		taxCodes.add(taxCode);

	}
	public void setName(String name) { mName = name; }


	public String getName() { return mName; }


	public void setTaxCode(String taxCode) { 
		mTaxCode = taxCode;
		taxCodes.add(taxCode);
	}


	public String getTaxCode() { return mTaxCode; }
	//	public String getCreatedBy() {return this.mCreatedBy;}
	//
	//	public void setCreatedBy(String createdBy) {this.mCreatedBy = createdBy;}
	//
	//	public String getModifiedBy() {return this.mModifiedBy;}
	//
	//	public void setModifiedBy(String modifiedBy) {this.mModifiedBy = modifiedBy;}
	//
	//	public Date getModifiedDate() {return this.mModifiedDate;}
	//
	//	public void setModifiedDate(Date modifiedDate) {this.mModifiedDate = modifiedDate;}
	//
	//	public char getActiveStatus() {return this.mActiveStatus;}
	//
	//	public void setActiveStatus(char activeStatus) {this.mActiveStatus = activeStatus;}


	@Deprecated
	public void setTaxRate(double taxRate) {
		//mTaxRate = taxRate;
		mTaxRates = new ArrayList<TaxRate>();
		mTaxRates.add(new TaxRate(taxRate));
	}

	public void setTaxRates(TaxRate[] taxRates) {
		//mTaxRates = taxRates;
		mTaxRates = new ArrayList<TaxRate>();
		if (taxRates != null) {
			for (TaxRate taxRate : taxRates) {
				mTaxRates.add(new TaxRate(taxRate.getValue(), taxRate.getStartDate(),taxRate.getEndDate()) );
			}
		}
	}

	public TaxRate[] getTaxRates() {
		//return mTaxRates;
		return mTaxRates.toArray((new TaxRate[]{}));
	}


	/**
	 * find out the tax rate for today
	 * @return
	 */
	public double getTaxRate() {
		return getTaxRate(new Date());
	}


	//CR1564 -Utctimezone for diff region in country
	public double getTaxRate(Date date) {

		double taxRate = 0.0;
		Date currentDate = (date != null) ? date : new Date();
		TaxRate currentTaxRate;

		for (int i=0; i < mTaxRates.size(); i++) {
			// no start and end date specified
			currentTaxRate = mTaxRates.get(i);
			if(currentTaxRate.getStartDate()==null) {
				taxRate = currentTaxRate.getValue();
				break;
			}
			// start date specified but no end date specified
			else if( (currentTaxRate.getEndDate()==null) && (currentDate.after(currentTaxRate.getStartDate())) ) {
				taxRate = currentTaxRate.getValue();
				break;
			}
			// start and end date specified
			//MQC 5654 - WAS
			//else if( currentDate.after(currentTaxRate.getStartDate()) &&
			//        	currentDate.before(currentTaxRate.getEndDate()) ) {
			//         taxRate = currentTaxRate.getValue();
			//         break;
			//}
			//NOW
			else if (currentTaxRate.getEndDate() != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(currentTaxRate.getEndDate());
				cal.add(Calendar.DATE, 1);
				if( currentDate.after(currentTaxRate.getStartDate()) &&
						currentDate.before(cal.getTime()) ) {
					taxRate = currentTaxRate.getValue();
					break;
				}
			}
			currentTaxRate = null;
		}
		/*
		 * Let's convert to % if taxrate > 1
		 * Avoiding ER4 migration problems where tax rate is entered bewteen ]1,100[
		 */
		if (taxRate>1) taxRate = taxRate / 100; 
		return taxRate;
	}

	@Override
	public String toString()	{
		StringBuilder sb = new StringBuilder();
		sb.append(mName);
		sb.append(": ");
		sb.append(mTaxCode);
		sb.append(": taxRate=");
		sb.append(getTaxRate(new Date()));  	
		return sb.toString();
	}
}
