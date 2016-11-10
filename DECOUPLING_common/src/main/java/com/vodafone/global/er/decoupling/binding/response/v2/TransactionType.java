
package com.vodafone.global.er.decoupling.binding.response.v2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for transactionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transaction-id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="purchase-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="purchase-rate" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="transaction-type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refund-payment-transaction-id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="refund-reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tax-rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionType", propOrder = {
    "transactionId",
    "purchaseDate",
    "purchaseRate",
    "transactionType",
    "contentName",
    "refundPaymentTransactionId",
    "refundReason",
    "taxRate",
    "status",
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
    "errorDescription"
})
public class TransactionType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "transaction-id")
    protected long transactionId;
    @XmlElement(name = "purchase-date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar purchaseDate;
    @XmlElement(name = "purchase-rate", required = true)
    protected BigDecimal purchaseRate;
    @XmlElement(name = "transaction-type")
    protected String transactionType;
    @XmlElement(name = "content-name")
    protected String contentName;
    @XmlElement(name = "refund-payment-transaction-id")
    protected Long refundPaymentTransactionId;
    @XmlElement(name = "refund-reason")
    protected String refundReason;
    @XmlElement(name = "tax-rate")
    protected BigDecimal taxRate;
    protected Integer status;
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

    /**
     * Gets the value of the transactionId property.
     * 
     */
    public long getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     */
    public void setTransactionId(long value) {
        this.transactionId = value;
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
     * Gets the value of the purchaseRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPurchaseRate() {
        return purchaseRate;
    }

    /**
     * Sets the value of the purchaseRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPurchaseRate(BigDecimal value) {
        this.purchaseRate = value;
    }

    /**
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionType(String value) {
        this.transactionType = value;
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
     * Gets the value of the refundPaymentTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getRefundPaymentTransactionId() {
        if (refundPaymentTransactionId == null) {
            return  0L;
        } else {
            return refundPaymentTransactionId;
        }
    }

    /**
     * Sets the value of the refundPaymentTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRefundPaymentTransactionId(Long value) {
        this.refundPaymentTransactionId = value;
    }

    /**
     * Gets the value of the refundReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefundReason() {
        return refundReason;
    }

    /**
     * Sets the value of the refundReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefundReason(String value) {
        this.refundReason = value;
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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getStatus() {
        if (status == null) {
            return  0;
        } else {
            return status;
        }
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
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

}
