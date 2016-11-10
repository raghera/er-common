package com.vizzavi.ecommerce.business.common;

import java.io.Serializable;
import java.text.DateFormat;

/**
 * The Pricing Text Template data object.
 * 
 * R9 Phase 3

 * @author wangs
 *
 * @date 2006-3-4
 */
public class PricingTextTemplate implements Serializable {
	
	private static final long serialVersionUID = 8966507216137831853L;
		
	// The Short date format
	public final static int SHORT = DateFormat.SHORT;
	
	// The Medium date format
	public final static int MEDIUM = DateFormat.MEDIUM;
	
	// The Short date format
	public final static int LONG = DateFormat.LONG;
	
	public final static String BEFORE = "before";
	
	public final static String AFTER = "after";
	
	/** The available tokens for pricing text. **/
	public final static String DURATION = "%DURATION%";
	
	public final static String LINKED_DURATION = "%LINKED_DURATION%";	
	
	public final static String PRICE = "%PRICE%";
	
	public final static String LINKED_PRICE = "%LINKED_PRICE%";	
	
	public final static String FIXED_EXPIRY_DATE = "%FIXED_EXPIRY_DATE%";
	
	public final static String PRE_ORDERED_START_DATE = "%PRE_ORDER_START_DATE%";
	
	public final static String CUSTOM_DURATION = "%CUSTOM_DURATION%";
	
	public final static String LINKED_CUSTOM_DURATION = "%LINKED_CUSTOM_DURATION%";
	
	public final static String ROAMING_PRICE ="%ROAMING_PRICE%";
	
	public final static String ON_FOOTPRINT_ROAMING_PRICE ="%ON_FOOTPRINT_ROAMING_PRICE%";
	
	public final static String OFF_FOOTPRINT_ROAMING_PRICE ="%OFF_FOOTPRINT_ROAMING_PRICE%";	
	
	/** The available tokens for pricing text. **/
	public final static String[] TOKENS = {DURATION, LINKED_DURATION, PRICE, LINKED_PRICE, FIXED_EXPIRY_DATE, PRE_ORDERED_START_DATE, CUSTOM_DURATION, LINKED_CUSTOM_DURATION, ROAMING_PRICE, ON_FOOTPRINT_ROAMING_PRICE, OFF_FOOTPRINT_ROAMING_PRICE};
	
	// The pricting text unique name
	private String name = new String();
	
	// The language code
	private String language = new String();

	// The type="roaming" or ""
	private String type = new String();
	
	// The pricing text
	private String text = new String();
	
	// The date format
	private int dateFormat = SHORT;
	
	// Flag indicates if the currency diplay option is "before"
	private boolean isCurrencyBefore = true;

	/**
	 * Constructor
	 */
	public PricingTextTemplate(String name, String language, String text, String type) {
		this.type = type;
		this.name = name;
		this.language = language;
		this.text = text;
	}

	public int getDateFormat() {
		return dateFormat;
	}
	
	public String getDateFormatStr(){
		switch (dateFormat){
		case SHORT:
			return "SHORT";
		case MEDIUM:
			return "MEDIUM";
		case LONG:
			return "LONG";
		}
		
		return "";
	}

	public void setDateFormat(int dateFormat) {
		this.dateFormat = dateFormat;
	}


	public boolean isCurrencyBefore() {
		return isCurrencyBefore;
	}

	public void setCurrencyBefore(boolean isCurrencyBefore) {
		this.isCurrencyBefore = isCurrencyBefore;
	}
	
	public String getCurrencyDisplay(){
		if (isCurrencyBefore){
			return BEFORE;
		}
		else {
			return AFTER;
		}
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String Type) {
		this.type = Type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	//CR1503aL. START

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this.name == null || obj == null){
			return false;
		}
		return this.name.equals(((PricingTextTemplate)obj).getName());
	}
	
	//CR1503aL. END

}
