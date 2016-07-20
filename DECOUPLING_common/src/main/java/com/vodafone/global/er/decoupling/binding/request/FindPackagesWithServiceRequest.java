
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
 *         &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charging-id" type="{http://localhost:8080/decoupling/schemas/common}chargingId" minOccurs="0"/>
 *         &lt;element name="purchase-attributes" type="{http://localhost:8080/decoupling/schemas/common}purchaseAttributesType" minOccurs="0"/>
 *         &lt;element name="rating-attributes" type="{http://localhost:8080/decoupling/schemas/common}ratingAttributesType" minOccurs="0"/>
 *         &lt;element name="pricepoint-start-end-dates-required" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="pricepoint-user-groups-required" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="log-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "serviceId",
    "msisdn",
    "chargingId",
    "purchaseAttributes",
    "ratingAttributes",
    "pricepointStartEndDatesRequired",
    "pricepointUserGroupsRequired",
    "logId"
})
@XmlRootElement(name = "find-packages-with-service-request", namespace = "")
public class FindPackagesWithServiceRequest
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "service-id", required = true)
    protected String serviceId;
    protected String msisdn;
    @XmlElement(name = "charging-id")
    protected ChargingId chargingId;
    @XmlElement(name = "purchase-attributes")
    protected PurchaseAttributesType purchaseAttributes;
    @XmlElement(name = "rating-attributes")
    protected RatingAttributesType ratingAttributes;
    @XmlElement(name = "pricepoint-start-end-dates-required")
    protected Boolean pricepointStartEndDatesRequired;
    @XmlElement(name = "pricepoint-user-groups-required")
    protected Boolean pricepointUserGroupsRequired;
    @XmlElement(name = "log-id")
    protected String logId;

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
     * Gets the value of the purchaseAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link PurchaseAttributesType }
     *     
     */
    public PurchaseAttributesType getPurchaseAttributes() {
        return purchaseAttributes;
    }

    /**
     * Sets the value of the purchaseAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseAttributesType }
     *     
     */
    public void setPurchaseAttributes(PurchaseAttributesType value) {
        this.purchaseAttributes = value;
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
     * Gets the value of the pricepointStartEndDatesRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPricepointStartEndDatesRequired() {
        if (pricepointStartEndDatesRequired == null) {
            return false;
        } else {
            return pricepointStartEndDatesRequired;
        }
    }

    /**
     * Sets the value of the pricepointStartEndDatesRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPricepointStartEndDatesRequired(Boolean value) {
        this.pricepointStartEndDatesRequired = value;
    }

    /**
     * Gets the value of the pricepointUserGroupsRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPricepointUserGroupsRequired() {
        if (pricepointUserGroupsRequired == null) {
            return false;
        } else {
            return pricepointUserGroupsRequired;
        }
    }

    /**
     * Sets the value of the pricepointUserGroupsRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPricepointUserGroupsRequired(Boolean value) {
        this.pricepointUserGroupsRequired = value;
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

}
