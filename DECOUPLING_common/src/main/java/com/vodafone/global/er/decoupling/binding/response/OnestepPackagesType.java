//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 04:57:40 PM BST 
//


package com.vodafone.global.er.decoupling.binding.response;


/**
 * Java content class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch/working/DECOUPLING_common/schemas/response/response.xsd line 130)
 * <p>
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
 */
public interface OnestepPackagesType {


    /**
     * Gets the value of the OnestepPackage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the OnestepPackage property.
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
     * {@link com.vodafone.global.er.decoupling.binding.response.OnestepPackagesType.OnestepPackageType}
     * 
     */
    java.util.List getOnestepPackage();


    /**
     * Java content class for anonymous complex type.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch/working/DECOUPLING_common/schemas/response/response.xsd line 133)
     * <p>
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
     */
    public interface OnestepPackageType {


        /**
         * Gets the value of the subscriptionEndDate property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getSubscriptionEndDate();

        /**
         * Sets the value of the subscriptionEndDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setSubscriptionEndDate(java.lang.String value);

        /**
         * Gets the value of the price property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getPrice();

        /**
         * Sets the value of the price property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setPrice(java.lang.String value);

        /**
         * Gets the value of the isExpressPackage property.
         * 
         */
        boolean isIsExpressPackage();

        /**
         * Sets the value of the isExpressPackage property.
         * 
         */
        void setIsExpressPackage(boolean value);

        /**
         * Gets the value of the serviceId property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getServiceId();

        /**
         * Sets the value of the serviceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setServiceId(java.lang.String value);

        /**
         * Gets the value of the hasPurchasesOptions property.
         * 
         */
        boolean isHasPurchasesOptions();

        /**
         * Sets the value of the hasPurchasesOptions property.
         * 
         */
        void setHasPurchasesOptions(boolean value);

        /**
         * Gets the value of the isSubscribed property.
         * 
         */
        boolean isIsSubscribed();

        /**
         * Sets the value of the isSubscribed property.
         * 
         */
        void setIsSubscribed(boolean value);

        /**
         * Gets the value of the creditsLeft property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getCreditsLeft();

        /**
         * Sets the value of the creditsLeft property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setCreditsLeft(java.lang.String value);

        /**
         * Gets the value of the priceText property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getPriceText();

        /**
         * Sets the value of the priceText property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setPriceText(java.lang.String value);

    }

}
