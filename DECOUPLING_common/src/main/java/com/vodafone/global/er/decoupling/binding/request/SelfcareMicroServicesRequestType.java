//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.03 at 03:16:51 PM BST 
//


package com.vodafone.global.er.decoupling.binding.request;


/**
 * Java content class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch_matt/working/DECOUPLING_common/schemas/request/request.xsd line 251)
 * <p>
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
 */
public interface SelfcareMicroServicesRequestType {


    /**
     * Gets the value of the microServiceFilter property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.SelfcareMicroServicesRequestType.MicroServiceFilterType}
     */
    com.vodafone.global.er.decoupling.binding.request.SelfcareMicroServicesRequestType.MicroServiceFilterType getMicroServiceFilter();

    /**
     * Sets the value of the microServiceFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.SelfcareMicroServicesRequestType.MicroServiceFilterType}
     */
    void setMicroServiceFilter(com.vodafone.global.er.decoupling.binding.request.SelfcareMicroServicesRequestType.MicroServiceFilterType value);

    /**
     * Gets the value of the device property.
     * 
     */
    int getDevice();

    /**
     * Sets the value of the device property.
     * 
     */
    void setDevice(int value);

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getMsisdn();

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setMsisdn(java.lang.String value);

    /**
     * Gets the value of the logId property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getLogId();

    /**
     * Sets the value of the logId property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setLogId(java.lang.String value);


    /**
     * Java content class for anonymous complex type.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch_matt/working/DECOUPLING_common/schemas/request/request.xsd line 256)
     * <p>
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
     */
    public interface MicroServiceFilterType {


        /**
         * Gets the value of the subscriptionId property.
         * 
         */
        long getSubscriptionId();

        /**
         * Sets the value of the subscriptionId property.
         * 
         */
        void setSubscriptionId(long value);

    }

}
