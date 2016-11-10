
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="client-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charging-id" type="{http://localhost:8080/decoupling/schemas/common}chargingId" minOccurs="0"/>
 *         &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="purchase-attributes" type="{http://localhost:8080/decoupling/schemas/common}ratingAttributesType" minOccurs="0"/>
 *         &lt;element name="log-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error-id-required" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "clientId",
    "msisdn",
    "chargingId",
    "subscriptionId",
    "purchaseAttributes",
    "logId",
    "errorIdRequired"
})
@XmlRootElement(name = "renew-purchase-package-request", namespace = "")
public class RenewPurchasePackageRequest
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "client-id", required = true)
    protected String clientId;
    protected String msisdn;
    @XmlElement(name = "charging-id")
    protected ChargingId chargingId;
    @XmlElement(name = "subscription-id", required = true)
    protected String subscriptionId;
    @XmlElement(name = "purchase-attributes")
    protected RatingAttributesType purchaseAttributes;
    @XmlElement(name = "log-id")
    protected String logId;
    @XmlElement(name = "error-id-required")
    protected Boolean errorIdRequired;

    /**
     * Gets the value of the clientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the value of the clientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientId(String value) {
        this.clientId = value;
    }

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdn(String value) {
        this.msisdn = value;
    }

    /**
     * Gets the value of the chargingId property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingId }
     *     
     */
    public ChargingId getChargingId() {
        return chargingId;
    }

    /**
     * Sets the value of the chargingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingId }
     *     
     */
    public void setChargingId(ChargingId value) {
        this.chargingId = value;
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
     * Gets the value of the purchaseAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link RatingAttributesType }
     *     
     */
    public RatingAttributesType getPurchaseAttributes() {
        return purchaseAttributes;
    }

    /**
     * Sets the value of the purchaseAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RatingAttributesType }
     *     
     */
    public void setPurchaseAttributes(RatingAttributesType value) {
        this.purchaseAttributes = value;
    }

    /**
     * Gets the value of the logId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogId() {
        return logId;
    }

    /**
     * Sets the value of the logId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogId(String value) {
        this.logId = value;
    }

    /**
     * Gets the value of the errorIdRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isErrorIdRequired() {
        if (errorIdRequired == null) {
            return false;
        } else {
            return errorIdRequired;
        }
    }

    /**
     * Sets the value of the errorIdRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setErrorIdRequired(Boolean value) {
        this.errorIdRequired = value;
    }

}
