package com.vizzavi.ecommerce.business.charging;

import java.io.Serializable;

/**
 * CR - Add Invoice Text to goodwill credit request
*/

public class GoodwillCreditAttributes implements Serializable {

	private static final long serialVersionUID = 1386299316100507684L;
	
	private String clientId;
	
	private String partnerId;
	
	private String merchantId;
	
	private String packageId;
    
	private double preRate;
	
	private String invoiceText;
    
	
	public GoodwillCreditAttributes(String clientId, String partnerId, String merchantId, String packageId, double preRate, String invoiceText) {
		this.clientId = clientId;
		this.partnerId = partnerId;
		this.merchantId = merchantId;
		this.packageId = packageId;
		this.preRate = preRate;
		this.invoiceText = invoiceText;
	}
	
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return this.clientId;
	}
	
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
    /**
     * @return the partnerId
     */
    public String getPartnerId() {
    	return this.partnerId;
    }
    
    /**
     * @param partner the partnerId to set
     */
    public void setPartnerId(String partner) {
    	this.partnerId = partner;
    }
    
    /**
     * @return the merchantId
     */
    public String getMerchantId() {
    	return this.merchantId;
    }
    
    /**
     * @param merchant the merchantId to set
     */
    public void setMerchantId(String merchant) {
    	this.merchantId = merchant;
    }
    
    /**
     * @return the packageId
     */
    public String getPackageId() {
    	return this.packageId;
    }
    
    /**
     * @param packId the packageId to set
     */
    public void setPackageId(String packId) {
    	this.packageId = packId;
    }
    
    /**
     * @return the preRate
     */
    public double getPreRate() {
    	return this.preRate;
    }
    
    /**
     * @param rate the preRate to set
     */
    public void setPreRate(double rate) {
    	this.preRate = rate;
    }
    
    /**
     * @return the invoiceText
     */
    public String getInvoiceText() {
    	return this.invoiceText;
    }
    
    /**
     * @param invoiceTxt the invoiceText to set
     */
    public void setInvoiceText(String invoiceTxt) {
    	this.invoiceText = invoiceTxt;
    }
}
