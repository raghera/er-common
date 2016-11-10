
package com.vodafone.global.er.decoupling.binding.response.v2;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pricepoint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pricepoint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charging-method" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="duration-code" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rate" type="{http://localhost:8080/decoupling/schemas/v2/common}amount" minOccurs="0"/>
 *         &lt;element name="user-group" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="promo-code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pricing-text-1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pricing-text-2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pricepoint", propOrder = {
    "chargingMethod",
    "durationCode",
    "rate",
    "userGroup",
    "promoCode",
    "pricingText1",
    "pricingText2"
})
public class Pricepoint
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "charging-method")
    protected Integer chargingMethod;
    @XmlElement(name = "duration-code")
    protected Integer durationCode;
    protected Amount rate;
    @XmlElement(name = "user-group")
    protected String userGroup;
    @XmlElement(name = "promo-code")
    protected String promoCode;
    @XmlElement(name = "pricing-text-1")
    protected String pricingText1;
    @XmlElement(name = "pricing-text-2")
    protected String pricingText2;
    @XmlAttribute(name = "id", required = true)
    protected String id;

    /**
     * Gets the value of the chargingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getChargingMethod() {
        if (chargingMethod == null) {
            return  0;
        } else {
            return chargingMethod;
        }
    }

    /**
     * Sets the value of the chargingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChargingMethod(Integer value) {
        this.chargingMethod = value;
    }

    /**
     * Gets the value of the durationCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getDurationCode() {
        if (durationCode == null) {
            return  0;
        } else {
            return durationCode;
        }
    }

    /**
     * Sets the value of the durationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDurationCode(Integer value) {
        this.durationCode = value;
    }

    /**
     * Gets the value of the rate property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setRate(Amount value) {
        this.rate = value;
    }

    /**
     * Gets the value of the userGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserGroup() {
        return userGroup;
    }

    /**
     * Sets the value of the userGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserGroup(String value) {
        this.userGroup = value;
    }

    /**
     * Gets the value of the promoCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromoCode() {
        return promoCode;
    }

    /**
     * Sets the value of the promoCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromoCode(String value) {
        this.promoCode = value;
    }

    /**
     * Gets the value of the pricingText1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricingText1() {
        return pricingText1;
    }

    /**
     * Sets the value of the pricingText1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricingText1(String value) {
        this.pricingText1 = value;
    }

    /**
     * Gets the value of the pricingText2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricingText2() {
        return pricingText2;
    }

    /**
     * Sets the value of the pricingText2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricingText2(String value) {
        this.pricingText2 = value;
    }

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

}
