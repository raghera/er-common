package com.vizzavi.ecommerce.business.charging;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vodafone.config.ConfigProvider;

/**
 * CR-0978 Location Services
 * Purchase Tariff Authorization returned when calling modify tariff API.
 */

public class PurchaseTariffAuthorization implements Serializable {

	private static final long serialVersionUID = 1389818954634199473L;

	static final String ROUND_NTH_DECIMAL = "ROUND_NTH_DECIMAL";

	private boolean success;

	private String subscriptionId;

	private ReasonCode reason;

	private double taxRate;

	private double netRate;

	private String transactionId;

	static final String ROUND_NTH_EXPRESS_PATTERN_DEFAULT = "0.00";
	static final String ROUND_NTH_EXPRESS_PATTERN_INTEGER = "0.##";
	//MQC 6016 - change to private variable from static
	//WAS
	//static final String ROUND_NTH_EXPRESS_PATTERN; 
	//NOW
	private String ROUND_NTH_EXPRESS_PATTERN;

	//MQC 6016 - move retrieval of property to local method of where it is used
	//static {

	//    String round_nth_express_pattern = PropertyServer.getProperty("ROUND_NTH_EXPRESS_PATTERN");
	//    if (round_nth_express_pattern == null) {
	//        ROUND_NTH_EXPRESS_PATTERN = ROUND_NTH_EXPRESS_PATTERN_DEFAULT;
	//    }
	//    else {
	//        ROUND_NTH_EXPRESS_PATTERN = round_nth_express_pattern;
	//    }

	//}


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSubscriptionId() {
		return this.subscriptionId;
	}

	public void setSubscriptionId(String subId) {
		this.subscriptionId = subId;
	}

	public ReasonCode getReasonCode() {
		return this.reason;
	}

	public void setReasonCode(ReasonCode reason) {
		this.reason = reason;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transId) {
		this.transactionId = transId;
	}

	public double getRate() {

		int round_nth_decimal = ConfigProvider.getPropertyAsInteger(ROUND_NTH_DECIMAL,4);
		double rv = -1;

		//		if(ConfigProvider.getProperty(ROUND_NTH_DECIMAL)!=null){
		//			round_nth_decimal = Integer.parseInt(ConfigProvider.getProperty(ROUND_NTH_DECIMAL));
		//		}

		rv = (1 + getTaxRate()) * getNetRate();

		return roundDouble(rv,round_nth_decimal);
	}

	public String getRateAsString(Locale loc)
	{
		String rv = null;
		try {
			NumberFormat form = NumberFormat.getNumberInstance(loc);

			int round_nth_decimal = ConfigProvider.getPropertyAsInteger(ROUND_NTH_DECIMAL,4);
			//            if(ConfigProvider.getProperty(ROUND_NTH_DECIMAL)!=null)
			//            {
			//                round_nth_decimal = Integer.parseInt(ConfigProvider.getProperty(ROUND_NTH_DECIMAL));

			form.setMinimumFractionDigits(round_nth_decimal);
			form.setMaximumFractionDigits(round_nth_decimal);
			//           }

			double rate = this.getRate();

			DecimalFormat df = (DecimalFormat)form;

			//MQC 6016 - move retrieval of property to local method of where it is used
			String round_nth_express_pattern = ConfigProvider.getProperty("ROUND_NTH_EXPRESS_PATTERN");
			if (round_nth_express_pattern == null) {
				ROUND_NTH_EXPRESS_PATTERN = ROUND_NTH_EXPRESS_PATTERN_DEFAULT;
			}
			else {
				ROUND_NTH_EXPRESS_PATTERN = round_nth_express_pattern;
			}

			df.applyPattern(ROUND_NTH_EXPRESS_PATTERN);

			rv = df.format(rate);

			if(ConfigProvider.getProperty("API_PRICEPOINT_RETURN_RATE_AS_STRING_NET")!=null){

				if(ConfigProvider.getPropertyAsBoolean("API_PRICEPOINT_RETURN_RATE_AS_STRING_NET", false))
				{

//					if(ConfigProvider.getProperty(ROUND_NTH_DECIMAL)!=null){
//						round_nth_decimal = Integer.parseInt(ConfigProvider.getProperty(ROUND_NTH_DECIMAL));
//					}
					rv = form.format(roundDouble(getNetRate(),round_nth_decimal));
				}
			}
		} catch (Exception e) {
			// ignore this error
		}
		return rv;
	}


	public double getTaxRate()
	{
		return taxRate;
	}

	public void setTaxRate(double val) 
	{
		taxRate = val;
	}

	public double getNetRate()
	{
		return netRate;
	}

	public void setNetRate(double rate) 
	{
		netRate = rate;
	}

	private static final double roundDouble(double d, int nthDecimal)
	{
		return Math.round(d * Math.pow(10, nthDecimal)) / Math.pow(10,nthDecimal);
	}
}
