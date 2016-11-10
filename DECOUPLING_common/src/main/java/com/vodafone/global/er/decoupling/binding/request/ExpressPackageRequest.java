
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charging-id" type="{http://localhost:8080/decoupling/schemas/common}chargingId" minOccurs="0"/>
 *         &lt;element name="service-ids">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="log-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="full-response-required" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="express-display-attributes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="is-headline" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="is-option" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="true-options" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "chargingId",
    "serviceIds",
    "logId",
    "fullResponseRequired",
    "expressDisplayAttributes"
})
@XmlRootElement(name = "express-package-request", namespace = "")
public class ExpressPackageRequest
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected String msisdn;
    @XmlElement(name = "charging-id")
    protected ChargingId chargingId;
    @XmlElement(name = "service-ids", required = true)
    protected ExpressPackageRequest.ServiceIds serviceIds;
    @XmlElement(name = "log-id")
    protected String logId;
    @XmlElement(name = "full-response-required")
    protected Boolean fullResponseRequired;
    @XmlElement(name = "express-display-attributes", required = true)
    protected ExpressPackageRequest.ExpressDisplayAttributes expressDisplayAttributes;

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
     * Gets the value of the serviceIds property.
     * 
     * @return
     *     possible object is
     *     {@link ExpressPackageRequest.ServiceIds }
     *     
     */
    public ExpressPackageRequest.ServiceIds getServiceIds() {
        return serviceIds;
    }

    /**
     * Sets the value of the serviceIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpressPackageRequest.ServiceIds }
     *     
     */
    public void setServiceIds(ExpressPackageRequest.ServiceIds value) {
        this.serviceIds = value;
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
     * Gets the value of the fullResponseRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isFullResponseRequired() {
        if (fullResponseRequired == null) {
            return false;
        } else {
            return fullResponseRequired;
        }
    }

    /**
     * Sets the value of the fullResponseRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFullResponseRequired(Boolean value) {
        this.fullResponseRequired = value;
    }

    /**
     * Gets the value of the expressDisplayAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link ExpressPackageRequest.ExpressDisplayAttributes }
     *     
     */
    public ExpressPackageRequest.ExpressDisplayAttributes getExpressDisplayAttributes() {
        return expressDisplayAttributes;
    }

    /**
     * Sets the value of the expressDisplayAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpressPackageRequest.ExpressDisplayAttributes }
     *     
     */
    public void setExpressDisplayAttributes(ExpressPackageRequest.ExpressDisplayAttributes value) {
        this.expressDisplayAttributes = value;
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
     *         &lt;element name="is-headline" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="is-option" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="true-options" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "isHeadline",
        "isOption",
        "trueOptions"
    })
    public static class ExpressDisplayAttributes
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "is-headline")
        protected Boolean isHeadline;
        @XmlElement(name = "is-option")
        protected Boolean isOption;
        @XmlElement(name = "true-options")
        protected ExpressPackageRequest.ExpressDisplayAttributes.TrueOptions trueOptions;

        /**
         * Gets the value of the isHeadline property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsHeadline() {
            if (isHeadline == null) {
                return false;
            } else {
                return isHeadline;
            }
        }

        /**
         * Sets the value of the isHeadline property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsHeadline(Boolean value) {
            this.isHeadline = value;
        }

        /**
         * Gets the value of the isOption property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsOption() {
            if (isOption == null) {
                return false;
            } else {
                return isOption;
            }
        }

        /**
         * Sets the value of the isOption property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsOption(Boolean value) {
            this.isOption = value;
        }

        /**
         * Gets the value of the trueOptions property.
         * 
         * @return
         *     possible object is
         *     {@link ExpressPackageRequest.ExpressDisplayAttributes.TrueOptions }
         *     
         */
        public ExpressPackageRequest.ExpressDisplayAttributes.TrueOptions getTrueOptions() {
            return trueOptions;
        }

        /**
         * Sets the value of the trueOptions property.
         * 
         * @param value
         *     allowed object is
         *     {@link ExpressPackageRequest.ExpressDisplayAttributes.TrueOptions }
         *     
         */
        public void setTrueOptions(ExpressPackageRequest.ExpressDisplayAttributes.TrueOptions value) {
            this.trueOptions = value;
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
         *         &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
            "serviceId"
        })
        public static class TrueOptions
            implements Serializable, Element
        {

            private final static long serialVersionUID = 1L;
            @XmlElement(name = "service-id")
            protected List<String> serviceId;

            /**
             * Gets the value of the serviceId property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the serviceId property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getServiceId().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getServiceId() {
                if (serviceId == null) {
                    serviceId = new ArrayList<String>();
                }
                return this.serviceId;
            }

        }

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
     *         &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
        "serviceId"
    })
    public static class ServiceIds
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "service-id", required = true)
        protected List<String> serviceId;

        /**
         * Gets the value of the serviceId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the serviceId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServiceId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getServiceId() {
            if (serviceId == null) {
                serviceId = new ArrayList<String>();
            }
            return this.serviceId;
        }

    }

}
