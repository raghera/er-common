package com.vizzavi.ecommerce.business.common;

import java.io.Serializable;
import java.util.Calendar;

public class PeriodValue implements Serializable {
	
	private static final long serialVersionUID = -7765013992898812787L;;
	
	//========================================================
	// Definition of periods for retry/period
	public final static int HOUR_UNIT = 0;

	
	//@hud STKHREQ242
	// Added for payment_handler.properties
	// This unit may not be used by pricing tool 
	// but used by ER CORE to read from payment_handler.properties
	// No changes need to be made in pricing tool
	public final static int DAY_UNIT = 1;
	
	public final static int MONTH_UNIT = 2;
	public final static int UNDEFINED_UNIT = 3;
	
	
	// string presentation of the unit
	private final static String[] UNITS_STR = new String[] {
		"hour",
		"day",	//@hud added
		"month",
		"undefined"
	};
	//==========================================================
	/*
	 * private data members
	 */
	private int id = -1;
	private String displayText = Constants.STRING_NOT_SET;
	private double amount = 0;
	private int unit = UNDEFINED_UNIT;
	
	
	//@hud STKHREQ242
	//Added for convenience
	private long millisecond = 0;

	/**
	 * Constructor with all values populated
	 * @param id
	 * @param displayText
	 * @param amount
	 * @param unit
	 */
	public PeriodValue(int id, String displayText, double amount, String unit) {
		this(id, amount, unit);
		this.displayText = displayText;
	}
	
	/**
	 * Constructor with displayText is empty
	 * @param id
	 * @param amount
	 * @param unit
	 */
	public PeriodValue(int id, double amount, String unit) {
		this.id = id;
		this.amount = amount;
		for (int i=0;i<UNITS_STR.length;i++) {
			if (unit.equals(UNITS_STR[i])) {
				this.unit = i;
				
				//@hud added for convenience
				calcTime();
				break;
			}
		}		
	}
	
	public PeriodValue(double amount, int unit) {
		this.id = -1;
		this.amount = amount;
		this.unit = unit;
		calcTime();
	}

	/*
	 * Getters
	 */
	public double getAmount() {
		return amount;
	}

	public String getDisplayText() {
		return displayText;
	}

	public int getId() {
		return id;
	}	

	public int getUnit() {
		return unit;
	}

	/**
	 * Specifies whether this PeriodValue has unit = month
	 * @return
	 */
	public boolean hasMonthUnit() {
		return unit == MONTH_UNIT;		
	}
	
	/**
	 * Specifies whether this PeriodValue has unit = day
	 * @return
	 */
	public boolean hasDayUnit() {
		return unit == DAY_UNIT;
	}
	
	/**
	 * Specifies whether this PeriodValue has unit = hour
	 * @return
	 */
	public boolean hasHourUnit() {
		return (unit == HOUR_UNIT);			
	}
	
	
	
	/**
	 * Specifies whether this PeriodValue is undefined
	 * @return
	 */
	public boolean isUndefined() {		
		return (unit == UNDEFINED_UNIT);
	}

	/**
	 * Retrieve the string representation of the Unit of this PeriodValue
	 * @return
	 */
	public String getUnitString() {
		return UNITS_STR[unit];
	}
	
	/**
	 * @hud added for convenience
	 * may not be used externally
	 * 
	 * @return
	 */
	public long getTime() {
		return millisecond;
	}
	
	public Calendar addTo(Calendar cal) 
	{
		Calendar retCal = null;
		retCal = (Calendar)cal.clone();
		
		if (millisecond > 0) {
			retCal.setTimeInMillis(millisecond + cal.getTimeInMillis());
		}
		else {
			switch (unit) {
				case MONTH_UNIT:
					retCal.add(Calendar.MONTH, (int)Math.round(amount));
					break;
			}
		}
		
		return retCal;
	}
	
	
	
	
	
	
	
	
	//==========================================================
	// internal members 
	private void calcTime() 
	{
		long lmsUnit = -1;
		
		switch (unit) {
			case HOUR_UNIT:
				lmsUnit = 3600000;
				break;
			case DAY_UNIT:
				lmsUnit = 24 * 3600000;
				break;
			case MONTH_UNIT:
				// This is tricky
				// as days for each months are different
				// so leave it as is
				break;
		}
		
		if (lmsUnit > 0) {
			millisecond = Math.round(amount * lmsUnit);
		}
	}
	//============================================================
	//@hud STKHREQ242
	// parse from payment_handelr.properties
	// could be for general purpose
	public static PeriodValue parse(String value) 
	{
		PeriodValue pv = null;
		// format
		//{<number>} {<space>} {hour | day | month}
		if (value != null) {
			String[] s1 = value.split("\\s+");
			if (s1 != null && s1.length == 2) {
				double _amount;
				String _unit;
				try {
					_amount = Double.parseDouble(s1[0]);
					// check units
					_unit = s1[1];
					for (int i = 0; i < UNITS_STR.length; ++ i) {
						if (UNITS_STR[i].equals(_unit)) {
							// good
							pv = new PeriodValue(-1, _amount, _unit);
							break;
						}
					}
				}
				catch (NumberFormatException ne) {
					// nothing
				}
			}
		}
		return pv;
	}
	
	@Override
	public String toString()	{
		StringBuffer sb = new StringBuffer();
		sb.append(amount);
		sb.append(UNITS_STR[unit]);
		return sb.toString();
	}
	
}
