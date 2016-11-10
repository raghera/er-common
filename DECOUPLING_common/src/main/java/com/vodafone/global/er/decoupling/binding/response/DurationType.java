
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for durationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
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
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "durationType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "unit",
    "value",
    "durationCode",
    "translatedDurationString"
})
public class DurationType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String unit;
    protected int value;
    @XmlElement(name = "duration-code")
    protected int durationCode;
    @XmlElement(name = "translated-duration-string", required = true)
    protected String translatedDurationString;

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    /**
     * Gets the value of the value property.
     * 
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the value of the durationCode property.
     * 
     */
    public int getDurationCode() {
        return durationCode;
    }

    /**
     * Sets the value of the durationCode property.
     * 
     */
    public void setDurationCode(int value) {
        this.durationCode = value;
    }

    /**
     * Gets the value of the translatedDurationString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranslatedDurationString() {
        return translatedDurationString;
    }

    /**
     * Sets the value of the translatedDurationString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranslatedDurationString(String value) {
        this.translatedDurationString = value;
    }

}
