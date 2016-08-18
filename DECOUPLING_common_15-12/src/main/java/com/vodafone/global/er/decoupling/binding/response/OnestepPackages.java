
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="onestep-package" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="price-text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="credits-left" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="subscription-end-date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="is-subscribed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="is-express-package" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="has-purchases-options" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="service-id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "onestepPackage"
})
@XmlRootElement(name = "onestep-packages")
public class OnestepPackages
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "onestep-package")
    protected List<OnestepPackages.OnestepPackage> onestepPackage;

    /**
     * Gets the value of the onestepPackage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the onestepPackage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOnestepPackage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OnestepPackages.OnestepPackage }
     * 
     * 
     */
    public List<OnestepPackages.OnestepPackage> getOnestepPackage() {
        if (onestepPackage == null) {
            onestepPackage = new ArrayList<OnestepPackages.OnestepPackage>();
        }
        return this.onestepPackage;
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
     *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="price-text" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="credits-left" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="subscription-end-date" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="is-subscribed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="is-express-package" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="has-purchases-options" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *       &lt;/sequence>
     *       &lt;attribute name="service-id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "price",
        "priceText",
        "creditsLeft",
        "subscriptionEndDate",
        "isSubscribed",
        "isExpressPackage",
        "hasPurchasesOptions"
    })
    public static class OnestepPackage
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(required = true)
        protected String price;
        @XmlElement(name = "price-text", required = true)
        protected String priceText;
        @XmlElement(name = "credits-left", required = true)
        protected String creditsLeft;
        @XmlElement(name = "subscription-end-date", required = true)
        protected String subscriptionEndDate;
        @XmlElement(name = "is-subscribed")
        protected boolean isSubscribed;
        @XmlElement(name = "is-express-package")
        protected boolean isExpressPackage;
        @XmlElement(name = "has-purchases-options")
        protected boolean hasPurchasesOptions;
        @XmlAttribute(name = "service-id", required = true)
        protected String serviceId;

        /**
         * Gets the value of the price property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrice() {
            return price;
        }

        /**
         * Sets the value of the price property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrice(String value) {
            this.price = value;
        }

        /**
         * Gets the value of the priceText property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPriceText() {
            return priceText;
        }

        /**
         * Sets the value of the priceText property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPriceText(String value) {
            this.priceText = value;
        }

        /**
         * Gets the value of the creditsLeft property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCreditsLeft() {
            return creditsLeft;
        }

        /**
         * Sets the value of the creditsLeft property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCreditsLeft(String value) {
            this.creditsLeft = value;
        }

        /**
         * Gets the value of the subscriptionEndDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSubscriptionEndDate() {
            return subscriptionEndDate;
        }

        /**
         * Sets the value of the subscriptionEndDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSubscriptionEndDate(String value) {
            this.subscriptionEndDate = value;
        }

        /**
         * Gets the value of the isSubscribed property.
         * 
         */
        public boolean isIsSubscribed() {
            return isSubscribed;
        }

        /**
         * Sets the value of the isSubscribed property.
         * 
         */
        public void setIsSubscribed(boolean value) {
            this.isSubscribed = value;
        }

        /**
         * Gets the value of the isExpressPackage property.
         * 
         */
        public boolean isIsExpressPackage() {
            return isExpressPackage;
        }

        /**
         * Sets the value of the isExpressPackage property.
         * 
         */
        public void setIsExpressPackage(boolean value) {
            this.isExpressPackage = value;
        }

        /**
         * Gets the value of the hasPurchasesOptions property.
         * 
         */
        public boolean isHasPurchasesOptions() {
            return hasPurchasesOptions;
        }

        /**
         * Sets the value of the hasPurchasesOptions property.
         * 
         */
        public void setHasPurchasesOptions(boolean value) {
            this.hasPurchasesOptions = value;
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

    }

}
