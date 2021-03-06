//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.03 at 03:16:51 PM BST 
//


package com.vodafone.global.er.decoupling.binding.request;


/**
 * Java content class for dateTimeTierFullType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch_matt/working/DECOUPLING_common/schemas/common/pa_common.xsd line 1435)
 * <p>
 * <pre>
 * &lt;complexType name="dateTimeTierFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is-promotion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="months-of-year" type="{http://localhost:8080/decoupling/schemas/common}rangeValueFullType" minOccurs="0"/>
 *         &lt;element name="days-of-month" type="{http://localhost:8080/decoupling/schemas/common}rangeValueFullType" minOccurs="0"/>
 *         &lt;element name="days-of-week" type="{http://localhost:8080/decoupling/schemas/common}rangeValueFullType" minOccurs="0"/>
 *         &lt;element name="hours-of-day" type="{http://localhost:8080/decoupling/schemas/common}dayRangeFullType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface DateTimeTierFullType {


    /**
     * Gets the value of the hoursOfDay property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.DayRangeFullType}
     */
    com.vodafone.global.er.decoupling.binding.request.DayRangeFullType getHoursOfDay();

    /**
     * Sets the value of the hoursOfDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.DayRangeFullType}
     */
    void setHoursOfDay(com.vodafone.global.er.decoupling.binding.request.DayRangeFullType value);

    /**
     * Gets the value of the daysOfMonth property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.RangeValueFullType}
     */
    com.vodafone.global.er.decoupling.binding.request.RangeValueFullType getDaysOfMonth();

    /**
     * Sets the value of the daysOfMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.RangeValueFullType}
     */
    void setDaysOfMonth(com.vodafone.global.er.decoupling.binding.request.RangeValueFullType value);

    /**
     * Gets the value of the daysOfWeek property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.RangeValueFullType}
     */
    com.vodafone.global.er.decoupling.binding.request.RangeValueFullType getDaysOfWeek();

    /**
     * Sets the value of the daysOfWeek property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.RangeValueFullType}
     */
    void setDaysOfWeek(com.vodafone.global.er.decoupling.binding.request.RangeValueFullType value);

    /**
     * Gets the value of the isPromotion property.
     * 
     */
    boolean isIsPromotion();

    /**
     * Sets the value of the isPromotion property.
     * 
     */
    void setIsPromotion(boolean value);

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
     * Gets the value of the monthsOfYear property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.RangeValueFullType}
     */
    com.vodafone.global.er.decoupling.binding.request.RangeValueFullType getMonthsOfYear();

    /**
     * Sets the value of the monthsOfYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.RangeValueFullType}
     */
    void setMonthsOfYear(com.vodafone.global.er.decoupling.binding.request.RangeValueFullType value);

}
