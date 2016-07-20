package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;

/**
 * CR2006 - Reusable Promo Codes
 * Promo Code Object.
*/

public class PromoCode implements Serializable {

	private static final long serialVersionUID = 9102558305177445341L;
	
	/** promo code unique id */
	private String id;
	
	/** promo code name */
	private String name;
	
	/** flag to indicate whether promo code is an unique promo code */
	private boolean unique;
	
	/** flag to indicate whether promo code is reusable */
	private boolean reusable;
	
	
	/** default constructor */
    public PromoCode() {
    }
	
	/** full constructor */
	public PromoCode (String id, String name, boolean unique, boolean reusable) {
		this.id = id;
		this.name = name;
		this.unique = unique;
		this.reusable = reusable;
	}
	
	/** full constructor */
	public PromoCode (String id, String name, String unique, String reusable) {
		this.id = id;
		this.name = name;
		if (unique != null && !unique.equals("")) { 
				if (unique.equalsIgnoreCase("true")) {
					this.unique = true;
				}
		}
		if (reusable != null && !reusable.equals("")) { 
			if (reusable.equalsIgnoreCase("true")) {
				this.reusable = true;
			}
		}
	}
	
	/** outputs to a string the promo code details */
	public String toString()
	{
		 StringBuffer buf = new StringBuffer();
		 buf.append("id=" + id + "\n");
		 buf.append("name=" + name + "\n");
		 buf.append("unique=" + unique + "\n");
		 buf.append("reusable=" + reusable + "\n");
		 
		 return buf.toString();
	}
	
	/** returns the promo code id */
	public String getId() {
		return this.id;
	}
	
	/** sets the promo code id */
	public void setId(String id) {
		this.id = id;
	}
	
	/** returns the promo code name */
	public String getName() {
		return this.name;
	}
	
	/** sets the promo code name */
	public void setName(String name) {
		this.name = name;
	}
	
	/** returns whether promo code is unique */
	public boolean isUnique() {
		return this.unique;
	}
	
	/** sets whether promo code is unique */
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	
	/** returns whether promo code is reusable */
	public boolean isReusable() {
		return this.reusable;
	}
	
	/** sets whether promo code is reusable */
	public void setReusable(boolean reusable) {
		this.reusable = reusable;
	}
}
