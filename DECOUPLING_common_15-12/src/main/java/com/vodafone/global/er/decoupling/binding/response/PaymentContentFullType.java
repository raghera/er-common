
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paymentContentFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentContentFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="item-volume" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="merchant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="merchant-description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="promotion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="service-type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentContentFullType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "category",
    "description",
    "itemVolume",
    "merchant",
    "merchantDescription",
    "promotion",
    "serviceType"
})
public class PaymentContentFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected String category;
    protected String description;
    @XmlElement(name = "item-volume")
    protected String itemVolume;
    protected String merchant;
    @XmlElement(name = "merchant-description")
    protected String merchantDescription;
    protected String promotion;
    @XmlElement(name = "service-type")
    protected String serviceType;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the itemVolume property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemVolume() {
        return itemVolume;
    }

    /**
     * Sets the value of the itemVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemVolume(String value) {
        this.itemVolume = value;
    }

    /**
     * Gets the value of the merchant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * Sets the value of the merchant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchant(String value) {
        this.merchant = value;
    }

    /**
     * Gets the value of the merchantDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantDescription() {
        return merchantDescription;
    }

    /**
     * Sets the value of the merchantDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantDescription(String value) {
        this.merchantDescription = value;
    }

    /**
     * Gets the value of the promotion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotion() {
        return promotion;
    }

    /**
     * Sets the value of the promotion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotion(String value) {
        this.promotion = value;
    }

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

}
