package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
public class Tax implements Serializable, CatalogBean	{
	
	private static final long serialVersionUID = -4417078967988329810L;

	/**a set of all tax codes in the priceplans*/
	public static Set<String> taxCodes = new HashSet<String>();

	private String mName;
	private String mTaxCode;

	private List<TaxRate> mTaxRates;

	private Long key;

	private Tax(){}

	public Tax(String name, String taxCode, double taxRate) {

		mName = name;
		mTaxCode = taxCode;
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

	@Deprecated
	public Tax(String name, String taxCode, TaxRate[] taxRates, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus) {
		this(name, taxCode, taxRates);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public  Long getKey()	{
		return key;
	}
	
	void setKey(long key)	{
		this.key=key;
	}
	public void setName(String name) { mName = name; }


	public String getName() { return mName; }


	public void setTaxCode(String taxCode) { 
		mTaxCode = taxCode;
		taxCodes.add(taxCode);
	}


	public String getTaxCode() { return mTaxCode; }


	@Deprecated
	public void setTaxRate(double taxRate) {
		//mTaxRate = taxRate;
		mTaxRates = new ArrayList<TaxRate>();
		mTaxRates.add(new TaxRate(taxRate));
	}

	public void setTaxRates(List<TaxRate> taxRates) {
		//JIRAET-217 remove line below
		//mTaxRates = taxRates;
		mTaxRates = new ArrayList<TaxRate>();
		if (taxRates != null) {
			for (TaxRate taxRate : taxRates) {
				mTaxRates.add(new TaxRate(taxRate.getValue(), taxRate.getStartDate(),taxRate.getEndDate()) );
			}
		}
	}
	
	@OneToMany(mappedBy="tax", targetEntity=TaxRate.class,
				cascade=CascadeType.ALL
				,	fetch=FetchType.EAGER)
	public List<TaxRate> getTaxRates() {
		return mTaxRates;
	}


	/**
	 * find out the tax rate for today
	 * @return
	 */
	@Transient
	public double getTaxRate() {
		return getTaxRate(null);
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
	
	public boolean equals (Object obj)	{
		if (!(obj instanceof Tax))
			return false;	//a simple test, and null-safe
		Tax other = (Tax) obj;
		if (key == other.getKey().longValue())
			return true;	//shortcut the logic below
		return getName().equals(other.getName())
				&& getTaxCode().equals(other.getTaxCode());
			//don't compare the tax rate list since they will be done separately?
	}
	
	@Override
	public int hashCode()	{
		return new HashCodeBuilder().append(mName).append(mTaxCode).toHashCode();
	}
}
