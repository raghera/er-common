//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 04:57:40 PM BST 
//


package com.vodafone.global.er.decoupling.binding.response;


/**
 * Java content class for pricingModelFullType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch/working/DECOUPLING_common/schemas/common/pa_common.xsd line 1421)
 * <p>
 * <pre>
 * &lt;complexType name="pricingModelFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tiers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="dateTimeTier" type="{http://localhost:8080/decoupling/schemas/common}dateTimeTierFullType" maxOccurs="unbounded" minOccurs="0"/>
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
 */
public interface PricingModelFullType {


    /**
     * Gets the value of the tiers property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PricingModelFullType.TiersType}
     */
    com.vodafone.global.er.decoupling.binding.response.PricingModelFullType.TiersType getTiers();

    /**
     * Sets the value of the tiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PricingModelFullType.TiersType}
     */
    void setTiers(com.vodafone.global.er.decoupling.binding.response.PricingModelFullType.TiersType value);

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getId();

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setId(java.lang.String value);


    /**
     * Java content class for anonymous complex type.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch/working/DECOUPLING_common/schemas/common/pa_common.xsd line 1425)
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="dateTimeTier" type="{http://localhost:8080/decoupling/schemas/common}dateTimeTierFullType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     */
    public interface TiersType {


        /**
         * Gets the value of the DateTimeTier property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the DateTimeTier property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDateTimeTier().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link com.vodafone.global.er.decoupling.binding.response.DateTimeTierFullType}
         * 
         */
        java.util.List getDateTimeTier();

    }

}
