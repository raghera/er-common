//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 04:57:40 PM BST 
//


package com.vodafone.global.er.decoupling.binding.response;


/**
 * Java content class for durationType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch/working/DECOUPLING_common/schemas/common/pa_common.xsd line 552)
 * <p>
 * <pre>
 * &lt;complexType name="durationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="duration-code" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="translated-duration-string" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface DurationType {


    /**
     * Gets the value of the value property.
     * 
     */
    int getValue();

    /**
     * Sets the value of the value property.
     * 
     */
    void setValue(int value);

    /**
     * Gets the value of the durationCode property.
     * 
     */
    int getDurationCode();

    /**
     * Sets the value of the durationCode property.
     * 
     */
    void setDurationCode(int value);

    /**
     * Gets the value of the translatedDurationString property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getTranslatedDurationString();

    /**
     * Sets the value of the translatedDurationString property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setTranslatedDurationString(java.lang.String value);

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getUnit();

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setUnit(java.lang.String value);

}
