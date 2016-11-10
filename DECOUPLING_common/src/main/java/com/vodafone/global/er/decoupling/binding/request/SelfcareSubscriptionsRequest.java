
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
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="device" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="subscription-filter" type="{http://localhost:8080/decoupling/schemas/common}subscription-filterType" minOccurs="0"/>
 *         &lt;element name="log-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enable-disallow-cancellations" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="b2b-partner-info-required" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "msisdn",
    "device",
    "subscriptionFilter",
    "logId",
    "enableDisallowCancellations",
    "b2BPartnerInfoRequired"
})
@XmlRootElement(name = "selfcare-subscriptions-request", namespace = "")
public class SelfcareSubscriptionsRequest
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String msisdn;
    protected Integer device;
    @XmlElement(name = "subscription-filter")
    protected SubscriptionFilterType subscriptionFilter;
    @XmlElement(name = "log-id")
    protected String logId;
    @XmlElement(name = "enable-disallow-cancellations")
    protected Boolean enableDisallowCancellations;
    @XmlElement(name = "b2b-partner-info-required")
    protected Boolean b2BPartnerInfoRequired;

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
     * Gets the value of the device property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getDevice() {
        if (device == null) {
            return  0;
        } else {
            return device;
        }
    }

    /**
     * Sets the value of the device property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDevice(Integer value) {
        this.device = value;
    }

    /**
     * Gets the value of the subscriptionFilter property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionFilterType }
     *     
     */
    public SubscriptionFilterType getSubscriptionFilter() {
        return subscriptionFilter;
    }

    /**
     * Sets the value of the subscriptionFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionFilterType }
     *     
     */
    public void setSubscriptionFilter(SubscriptionFilterType value) {
        this.subscriptionFilter = value;
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
     * Gets the value of the enableDisallowCancellations property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isEnableDisallowCancellations() {
        if (enableDisallowCancellations == null) {
            return false;
        } else {
            return enableDisallowCancellations;
        }
    }

    /**
     * Sets the value of the enableDisallowCancellations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnableDisallowCancellations(Boolean value) {
        this.enableDisallowCancellations = value;
    }

    /**
     * Gets the value of the b2BPartnerInfoRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isB2BPartnerInfoRequired() {
        if (b2BPartnerInfoRequired == null) {
            return false;
        } else {
            return b2BPartnerInfoRequired;
        }
    }

    /**
     * Sets the value of the b2BPartnerInfoRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setB2BPartnerInfoRequired(Boolean value) {
        this.b2BPartnerInfoRequired = value;
    }

}
