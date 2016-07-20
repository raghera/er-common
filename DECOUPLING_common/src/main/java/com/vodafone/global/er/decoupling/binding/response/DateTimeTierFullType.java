
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dateTimeTierFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
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
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dateTimeTierFullType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "id",
    "isPromotion",
    "monthsOfYear",
    "daysOfMonth",
    "daysOfWeek",
    "hoursOfDay"
})
public class DateTimeTierFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(name = "is-promotion")
    protected boolean isPromotion;
    @XmlElement(name = "months-of-year")
    protected RangeValueFullType monthsOfYear;
    @XmlElement(name = "days-of-month")
    protected RangeValueFullType daysOfMonth;
    @XmlElement(name = "days-of-week")
    protected RangeValueFullType daysOfWeek;
    @XmlElement(name = "hours-of-day")
    protected DayRangeFullType hoursOfDay;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the isPromotion property.
     * 
     */
    public boolean isIsPromotion() {
        return isPromotion;
    }

    /**
     * Sets the value of the isPromotion property.
     * 
     */
    public void setIsPromotion(boolean value) {
        this.isPromotion = value;
    }

    /**
     * Gets the value of the monthsOfYear property.
     * 
     * @return
     *     possible object is
     *     {@link RangeValueFullType }
     *     
     */
    public RangeValueFullType getMonthsOfYear() {
        return monthsOfYear;
    }

    /**
     * Sets the value of the monthsOfYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeValueFullType }
     *     
     */
    public void setMonthsOfYear(RangeValueFullType value) {
        this.monthsOfYear = value;
    }

    /**
     * Gets the value of the daysOfMonth property.
     * 
     * @return
     *     possible object is
     *     {@link RangeValueFullType }
     *     
     */
    public RangeValueFullType getDaysOfMonth() {
        return daysOfMonth;
    }

    /**
     * Sets the value of the daysOfMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeValueFullType }
     *     
     */
    public void setDaysOfMonth(RangeValueFullType value) {
        this.daysOfMonth = value;
    }

    /**
     * Gets the value of the daysOfWeek property.
     * 
     * @return
     *     possible object is
     *     {@link RangeValueFullType }
     *     
     */
    public RangeValueFullType getDaysOfWeek() {
        return daysOfWeek;
    }

    /**
     * Sets the value of the daysOfWeek property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeValueFullType }
     *     
     */
    public void setDaysOfWeek(RangeValueFullType value) {
        this.daysOfWeek = value;
    }

    /**
     * Gets the value of the hoursOfDay property.
     * 
     * @return
     *     possible object is
     *     {@link DayRangeFullType }
     *     
     */
    public DayRangeFullType getHoursOfDay() {
        return hoursOfDay;
    }

    /**
     * Sets the value of the hoursOfDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link DayRangeFullType }
     *     
     */
    public void setHoursOfDay(DayRangeFullType value) {
        this.hoursOfDay = value;
    }

}
