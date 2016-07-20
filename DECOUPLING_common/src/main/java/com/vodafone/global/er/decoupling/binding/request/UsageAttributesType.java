
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for usageAttributesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="usageAttributesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="force-reservation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="force-purchase" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="session-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscribed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="exclude-recurring-trial-packages" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="include-recurring-trial-packages" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="access-control-tag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="event-units" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="access-control-attributes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="href" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="cc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="cl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="des" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="enable-re-issue" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usageAttributesType", propOrder = {
    "forceReservation",
    "forcePurchase",
    "sessionId",
    "subscribed",
    "excludeRecurringTrialPackages",
    "includeRecurringTrialPackages",
    "accessControlTag",
    "eventUnits",
    "accessControlAttributes",
    "enableReIssue"
})
public class UsageAttributesType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "force-reservation")
    protected Boolean forceReservation;
    @XmlElement(name = "force-purchase")
    protected Boolean forcePurchase;
    @XmlElement(name = "session-id")
    protected String sessionId;
    protected Boolean subscribed;
    @XmlElement(name = "exclude-recurring-trial-packages")
    protected Boolean excludeRecurringTrialPackages;
    @XmlElement(name = "include-recurring-trial-packages")
    protected Boolean includeRecurringTrialPackages;
    @XmlElement(name = "access-control-tag")
    protected Boolean accessControlTag;
    @XmlElement(name = "event-units")
    protected Double eventUnits;
    @XmlElement(name = "access-control-attributes")
    protected UsageAttributesType.AccessControlAttributes accessControlAttributes;
    @XmlElement(name = "enable-re-issue")
    protected Boolean enableReIssue;

    /**
     * Gets the value of the forceReservation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isForceReservation() {
        if (forceReservation == null) {
            return false;
        } else {
            return forceReservation;
        }
    }

    /**
     * Sets the value of the forceReservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setForceReservation(Boolean value) {
        this.forceReservation = value;
    }

    /**
     * Gets the value of the forcePurchase property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isForcePurchase() {
        if (forcePurchase == null) {
            return false;
        } else {
            return forcePurchase;
        }
    }

    /**
     * Sets the value of the forcePurchase property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setForcePurchase(Boolean value) {
        this.forcePurchase = value;
    }

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the subscribed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSubscribed() {
        if (subscribed == null) {
            return false;
        } else {
            return subscribed;
        }
    }

    /**
     * Sets the value of the subscribed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSubscribed(Boolean value) {
        this.subscribed = value;
    }

    /**
     * Gets the value of the excludeRecurringTrialPackages property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isExcludeRecurringTrialPackages() {
        if (excludeRecurringTrialPackages == null) {
            return false;
        } else {
            return excludeRecurringTrialPackages;
        }
    }

    /**
     * Sets the value of the excludeRecurringTrialPackages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExcludeRecurringTrialPackages(Boolean value) {
        this.excludeRecurringTrialPackages = value;
    }

    /**
     * Gets the value of the includeRecurringTrialPackages property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIncludeRecurringTrialPackages() {
        if (includeRecurringTrialPackages == null) {
            return false;
        } else {
            return includeRecurringTrialPackages;
        }
    }

    /**
     * Sets the value of the includeRecurringTrialPackages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeRecurringTrialPackages(Boolean value) {
        this.includeRecurringTrialPackages = value;
    }

    /**
     * Gets the value of the accessControlTag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isAccessControlTag() {
        if (accessControlTag == null) {
            return false;
        } else {
            return accessControlTag;
        }
    }

    /**
     * Sets the value of the accessControlTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAccessControlTag(Boolean value) {
        this.accessControlTag = value;
    }

    /**
     * Gets the value of the eventUnits property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getEventUnits() {
        if (eventUnits == null) {
            return  0.0D;
        } else {
            return eventUnits;
        }
    }

    /**
     * Sets the value of the eventUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEventUnits(Double value) {
        this.eventUnits = value;
    }

    /**
     * Gets the value of the accessControlAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link UsageAttributesType.AccessControlAttributes }
     *     
     */
    public UsageAttributesType.AccessControlAttributes getAccessControlAttributes() {
        return accessControlAttributes;
    }

    /**
     * Sets the value of the accessControlAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageAttributesType.AccessControlAttributes }
     *     
     */
    public void setAccessControlAttributes(UsageAttributesType.AccessControlAttributes value) {
        this.accessControlAttributes = value;
    }

    /**
     * Gets the value of the enableReIssue property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isEnableReIssue() {
        if (enableReIssue == null) {
            return false;
        } else {
            return enableReIssue;
        }
    }

    /**
     * Sets the value of the enableReIssue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnableReIssue(Boolean value) {
        this.enableReIssue = value;
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
     *         &lt;element name="href" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="cc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="cl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="des" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "href",
        "cc",
        "cl",
        "des"
    })
    public static class AccessControlAttributes
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        protected String href;
        protected String cc;
        protected String cl;
        protected String des;

        /**
         * Gets the value of the href property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHref() {
            return href;
        }

        /**
         * Sets the value of the href property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHref(String value) {
            this.href = value;
        }

        /**
         * Gets the value of the cc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCc() {
            return cc;
        }

        /**
         * Sets the value of the cc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCc(String value) {
            this.cc = value;
        }

        /**
         * Gets the value of the cl property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCl() {
            return cl;
        }

        /**
         * Sets the value of the cl property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCl(String value) {
            this.cl = value;
        }

        /**
         * Gets the value of the des property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDes() {
            return des;
        }

        /**
         * Sets the value of the des property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDes(String value) {
            this.des = value;
        }

    }

}
