
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for expressPackage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="expressPackage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="express-price" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price-text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is-option" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-subscribed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "expressPackage", propOrder = {
    "expressPrice",
    "priceText",
    "isOption",
    "isSubscribed"
})
public class ExpressPackage
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "express-price", required = true)
    protected String expressPrice;
    @XmlElement(name = "price-text", required = true)
    protected String priceText;
    @XmlElement(name = "is-option")
    protected boolean isOption;
    @XmlElement(name = "is-subscribed")
    protected boolean isSubscribed;
    @XmlAttribute(name = "service-id", required = true)
    protected String serviceId;

    /**
     * Gets the value of the expressPrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpressPrice() {
        return expressPrice;
    }

    /**
     * Sets the value of the expressPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpressPrice(String value) {
        this.expressPrice = value;
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
     * Gets the value of the isOption property.
     * 
     */
    public boolean isIsOption() {
        return isOption;
    }

    /**
     * Sets the value of the isOption property.
     * 
     */
    public void setIsOption(boolean value) {
        this.isOption = value;
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
