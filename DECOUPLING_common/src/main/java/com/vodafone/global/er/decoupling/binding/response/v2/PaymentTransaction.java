
package com.vodafone.global.er.decoupling.binding.response.v2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for payment-transaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="payment-transaction">
 *   &lt;complexContent>
 *     &lt;extension base="{http://localhost:8080/decoupling/schemas/v2/common}transaction">
 *       &lt;sequence>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="purchase-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="amount" type="{http://localhost:8080/decoupling/schemas/v2/common}amount" minOccurs="0"/>
 *         &lt;element name="content-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tax-rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="affiliate-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="device-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aggregator-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="asset-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payment-info" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-field1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-field2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-trans-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error-description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refunds" type="{http://localhost:8080/decoupling/schemas/v2/common}refund-transactions" minOccurs="0"/>
 *         &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscription-status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="package-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="b2b-partner" type="{http://localhost:8080/decoupling/schemas/v2/common}b2bPartner" minOccurs="0"/>
 *         &lt;element name="merchant-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charging-method" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="promo-code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payment-transaction", propOrder = {
    "status",
    "purchaseDate",
    "amount",
    "contentName",
    "taxRate",
    "serviceId",
    "affiliateId",
    "deviceId",
    "aggregatorId",
    "assetId",
    "paymentInfo",
    "externalField1",
    "externalField2",
    "externalTransId",
    "partnerId",
    "errorId",
    "errorDescription",
    "refunds",
    "subscriptionId",
    "subscriptionStatus",
    "packageId",
    "b2BPartner",
    "merchantName",
    "chargingMethod",
    "promoCode"
})
public class PaymentTransaction
    extends Transaction
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected int status;
    @XmlElement(name = "purchase-date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar purchaseDate;
    protected Amount amount;
    @XmlElement(name = "content-name")
    protected String contentName;
    @XmlElement(name = "tax-rate")
    protected BigDecimal taxRate;
    @XmlElement(name = "service-id")
    protected String serviceId;
    @XmlElement(name = "affiliate-id")
    protected String affiliateId;
    @XmlElement(name = "device-id")
    protected String deviceId;
    @XmlElement(name = "aggregator-id")
    protected String aggregatorId;
    @XmlElement(name = "asset-id")
    protected String assetId;
    @XmlElement(name = "payment-info")
    protected String paymentInfo;
    @XmlElement(name = "external-field1")
    protected String externalField1;
    @XmlElement(name = "external-field2")
    protected String externalField2;
    @XmlElement(name = "external-trans-id")
    protected String externalTransId;
    @XmlElement(name = "partner-id")
    protected String partnerId;
    @XmlElement(name = "error-id")
    protected String errorId;
    @XmlElement(name = "error-description")
    protected String errorDescription;
    protected RefundTransactions refunds;
    @XmlElement(name = "subscription-id")
    protected String subscriptionId;
    @XmlElement(name = "subscription-status")
    protected Integer subscriptionStatus;
    @XmlElement(name = "package-id")
    protected String packageId;
    @XmlElement(name = "b2b-partner")
    protected B2BPartner b2BPartner;
    @XmlElement(name = "merchant-name")
    protected String merchantName;
    @XmlElement(name = "charging-method")
    protected Integer chargingMethod;
    @XmlElement(name = "promo-code")
    protected String promoCode;

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the purchaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the value of the purchaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseDate(Calendar value) {
        this.purchaseDate = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
    }

    /**
     * Gets the value of the contentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentName() {
        return contentName;
    }

    /**
     * Sets the value of the contentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentName(String value) {
        this.contentName = value;
    }

    /**
     * Gets the value of the taxRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * Sets the value of the taxRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxRate(BigDecimal value) {
        this.taxRate = value;
    }

    /**
     * Gets the value of the serviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Sets the value of the serviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceId(String value) {
        this.serviceId = value;
    }

    /**
     * Gets the value of the affiliateId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAffiliateId() {
        return affiliateId;
    }

    /**
     * Sets the value of the affiliateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAffiliateId(String value) {
        this.affiliateId = value;
    }

    /**
     * Gets the value of the deviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the value of the deviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Gets the value of the aggregatorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAggregatorId() {
        return aggregatorId;
    }

    /**
     * Sets the value of the aggregatorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAggregatorId(String value) {
        this.aggregatorId = value;
    }

    /**
     * Gets the value of the assetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * Sets the value of the assetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssetId(String value) {
        this.assetId = value;
    }

    /**
     * Gets the value of the paymentInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentInfo() {
        return paymentInfo;
    }

    /**
     * Sets the value of the paymentInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentInfo(String value) {
        this.paymentInfo = value;
    }

    /**
     * Gets the value of the externalField1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalField1() {
        return externalField1;
    }

    /**
     * Sets the value of the externalField1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalField1(String value) {
        this.externalField1 = value;
    }

    /**
     * Gets the value of the externalField2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalField2() {
        return externalField2;
    }

    /**
     * Sets the value of the externalField2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalField2(String value) {
        this.externalField2 = value;
    }

    /**
     * Gets the value of the externalTransId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalTransId() {
        return externalTransId;
    }

    /**
     * Sets the value of the externalTransId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalTransId(String value) {
        this.externalTransId = value;
    }

    /**
     * Gets the value of the partnerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerId(String value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the errorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorId() {
        return errorId;
    }

    /**
     * Sets the value of the errorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorId(String value) {
        this.errorId = value;
    }

    /**
     * Gets the value of the errorDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * Sets the value of the errorDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDescription(String value) {
        this.errorDescription = value;
    }

    /**
     * Gets the value of the refunds property.
     * 
     * @return
     *     possible object is
     *     {@link RefundTransactions }
     *     
     */
    public RefundTransactions getRefunds() {
        return refunds;
    }

    /**
     * Sets the value of the refunds property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundTransactions }
     *     
     */
    public void setRefunds(RefundTransactions value) {
        this.refunds = value;
    }

    /**
     * Gets the value of the subscriptionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * Sets the value of the subscriptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionId(String value) {
        this.subscriptionId = value;
    }

    /**
     * Gets the value of the subscriptionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getSubscriptionStatus() {
        if (subscriptionStatus == null) {
            return  0;
        } else {
            return subscriptionStatus;
        }
    }

    /**
     * Sets the value of the subscriptionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSubscriptionStatus(Integer value) {
        this.subscriptionStatus = value;
    }

    /**
     * Gets the value of the packageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageId() {
        return packageId;
    }

    /**
     * Sets the value of the packageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageId(String value) {
        this.packageId = value;
    }

    /**
     * Gets the value of the b2BPartner property.
     * 
     * @return
     *     possible object is
     *     {@link B2BPartner }
     *     
     */
    public B2BPartner getB2BPartner() {
        return b2BPartner;
    }

    /**
     * Sets the value of the b2BPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link B2BPartner }
     *     
     */
    public void setB2BPartner(B2BPartner value) {
        this.b2BPartner = value;
    }

    /**
     * Gets the value of the merchantName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * Sets the value of the merchantName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantName(String value) {
        this.merchantName = value;
    }

    /**
     * Gets the value of the chargingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getChargingMethod() {
        if (chargingMethod == null) {
            return  0;
        } else {
            return chargingMethod;
        }
    }

    /**
     * Sets the value of the chargingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChargingMethod(Integer value) {
        this.chargingMethod = value;
    }

    /**
     * Gets the value of the promoCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromoCode() {
        return promoCode;
    }

    /**
     * Sets the value of the promoCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromoCode(String value) {
        this.promoCode = value;
    }

}
