
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
 *         &lt;element name="micro-service-filter">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "msisdn",
    "device",
    "microServiceFilter",
    "logId"
})
@XmlRootElement(name = "selfcare-micro-services-request", namespace = "")
public class SelfcareMicroServicesRequest
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String msisdn;
    protected Integer device;
    @XmlElement(name = "micro-service-filter", required = true)
    protected SelfcareMicroServicesRequest.MicroServiceFilter microServiceFilter;
    @XmlElement(name = "log-id")
    protected String logId;

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
     * Gets the value of the microServiceFilter property.
     * 
     * @return
     *     possible object is
     *     {@link SelfcareMicroServicesRequest.MicroServiceFilter }
     *     
     */
    public SelfcareMicroServicesRequest.MicroServiceFilter getMicroServiceFilter() {
        return microServiceFilter;
    }

    /**
     * Sets the value of the microServiceFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link SelfcareMicroServicesRequest.MicroServiceFilter }
     *     
     */
    public void setMicroServiceFilter(SelfcareMicroServicesRequest.MicroServiceFilter value) {
        this.microServiceFilter = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
        "subscriptionId"
    })
    public static class MicroServiceFilter
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "subscription-id")
        protected long subscriptionId;

        /**
         * Gets the value of the subscriptionId property.
         * 
         */
        public long getSubscriptionId() {
            return subscriptionId;
        }

        /**
         * Sets the value of the subscriptionId property.
         * 
         */
        public void setSubscriptionId(long value) {
            this.subscriptionId = value;
        }

    }

}
