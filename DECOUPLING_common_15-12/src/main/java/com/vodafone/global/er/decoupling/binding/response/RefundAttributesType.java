
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for refundAttributesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refundAttributesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="csr-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deprovFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="refundRoamingCharge" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="paymentTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refundCreditAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="chargingResource" type="{http://localhost:8080/decoupling/schemas/common}simple-charging-resourceType" minOccurs="0"/>
 *         &lt;element name="merchant-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoice-text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refundAttributesType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "csrId",
    "reason",
    "description",
    "subscriptionId",
    "deprovFlag",
    "refundRoamingCharge",
    "paymentTransactionId",
    "parentTransactionId",
    "refundCreditAmount",
    "chargingResource",
    "merchantName",
    "invoiceText",
    "partnerId"
})
public class RefundAttributesType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "csr-id", required = true)
    protected String csrId;
    @XmlElement(required = true)
    protected String reason;
    protected String description;
    @XmlElement(name = "subscription-id")
    protected String subscriptionId;
    protected Integer deprovFlag;
    protected Boolean refundRoamingCharge;
    protected String paymentTransactionId;
    protected String parentTransactionId;
    protected Double refundCreditAmount;
    protected SimpleChargingResourceType chargingResource;
    @XmlElement(name = "merchant-name")
    protected String merchantName;
    @XmlElement(name = "invoice-text")
    protected String invoiceText;
    @XmlElement(name = "partner-id")
    protected String partnerId;

    /**
     * Gets the value of the csrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsrId() {
        return csrId;
    }

    /**
     * Sets the value of the csrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsrId(String value) {
        this.csrId = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
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
     * Gets the value of the deprovFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getDeprovFlag() {
        if (deprovFlag == null) {
            return  0;
        } else {
            return deprovFlag;
        }
    }

    /**
     * Sets the value of the deprovFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeprovFlag(Integer value) {
        this.deprovFlag = value;
    }

    /**
     * Gets the value of the refundRoamingCharge property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isRefundRoamingCharge() {
        if (refundRoamingCharge == null) {
            return false;
        } else {
            return refundRoamingCharge;
        }
    }

    /**
     * Sets the value of the refundRoamingCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRefundRoamingCharge(Boolean value) {
        this.refundRoamingCharge = value;
    }

    /**
     * Gets the value of the paymentTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTransactionId() {
        return paymentTransactionId;
    }

    /**
     * Sets the value of the paymentTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTransactionId(String value) {
        this.paymentTransactionId = value;
    }

    /**
     * Gets the value of the parentTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentTransactionId() {
        return parentTransactionId;
    }

    /**
     * Sets the value of the parentTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentTransactionId(String value) {
        this.parentTransactionId = value;
    }

    /**
     * Gets the value of the refundCreditAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getRefundCreditAmount() {
        if (refundCreditAmount == null) {
            return  0.0D;
        } else {
            return refundCreditAmount;
        }
    }

    /**
     * Sets the value of the refundCreditAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRefundCreditAmount(Double value) {
        this.refundCreditAmount = value;
    }

    /**
     * Gets the value of the chargingResource property.
     * 
     * @return
     *     possible object is
     *     {@link SimpleChargingResourceType }
     *     
     */
    public SimpleChargingResourceType getChargingResource() {
        return chargingResource;
    }

    /**
     * Sets the value of the chargingResource property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleChargingResourceType }
     *     
     */
    public void setChargingResource(SimpleChargingResourceType value) {
        this.chargingResource = value;
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
     * Gets the value of the invoiceText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceText() {
        return invoiceText;
    }

    /**
     * Sets the value of the invoiceText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceText(String value) {
        this.invoiceText = value;
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

}
