
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 		This is the request type for both usage-auth-rate and usage-auth-rate-plus requests
 * 		
 * 
 * <p>Java class for usageAuthRateRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="usageAuthRateRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charging-id" type="{http://localhost:8080/decoupling/schemas/common}chargingId" minOccurs="0"/>
 *         &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usage-attributes" type="{http://localhost:8080/decoupling/schemas/common}usageAttributesType" minOccurs="0"/>
 *         &lt;element name="rating-attributes" type="{http://localhost:8080/decoupling/schemas/common}ratingAttributesType" minOccurs="0"/>
 *         &lt;element name="log-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pricepoint_custom_fields_required" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usageAuthRateRequestType", namespace = "", propOrder = {
    "msisdn",
    "chargingId",
    "serviceId",
    "usageAttributes",
    "ratingAttributes",
    "logId",
    "pricepointCustomFieldsRequired"
})
@XmlSeeAlso({
    UsageAuthRateChargeRequestType.class
})
public class UsageAuthRateRequestType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected String msisdn;
    @XmlElement(name = "charging-id")
    protected ChargingId chargingId;
    @XmlElement(name = "service-id", required = true)
    protected String serviceId;
    @XmlElement(name = "usage-attributes")
    protected UsageAttributesType usageAttributes;
    @XmlElement(name = "rating-attributes")
    protected RatingAttributesType ratingAttributes;
    @XmlElement(name = "log-id")
    protected String logId;
    @XmlElement(name = "pricepoint_custom_fields_required")
    protected Boolean pricepointCustomFieldsRequired;

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
     * Gets the value of the usageAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link UsageAttributesType }
     *     
     */
    public UsageAttributesType getUsageAttributes() {
        return usageAttributes;
    }

    /**
     * Sets the value of the usageAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageAttributesType }
     *     
     */
    public void setUsageAttributes(UsageAttributesType value) {
        this.usageAttributes = value;
    }

    /**
     * Gets the value of the ratingAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link RatingAttributesType }
     *     
     */
    public RatingAttributesType getRatingAttributes() {
        return ratingAttributes;
    }

    /**
     * Sets the value of the ratingAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RatingAttributesType }
     *     
     */
    public void setRatingAttributes(RatingAttributesType value) {
        this.ratingAttributes = value;
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
     * Gets the value of the pricepointCustomFieldsRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPricepointCustomFieldsRequired() {
        if (pricepointCustomFieldsRequired == null) {
            return false;
        } else {
            return pricepointCustomFieldsRequired;
        }
    }

    /**
     * Sets the value of the pricepointCustomFieldsRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPricepointCustomFieldsRequired(Boolean value) {
        this.pricepointCustomFieldsRequired = value;
    }

}
