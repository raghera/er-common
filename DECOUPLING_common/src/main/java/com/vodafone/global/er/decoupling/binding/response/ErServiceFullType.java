
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for erServiceFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="erServiceFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pricing-text-1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pricing-text-2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is-reserve-only" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="service-type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}pricePointFullType"/>
 *         &lt;element name="price-points" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}pricePointFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="is-default-type" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="provisioning-system" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provisioning-tag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usage-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "erServiceFullType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "id",
    "name",
    "description",
    "pricingText1",
    "pricingText2",
    "isReserveOnly",
    "serviceType",
    "pricePoint",
    "pricePoints",
    "isDefaultType",
    "provisioningSystem",
    "provisioningTag",
    "usageId"
})
public class ErServiceFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(name = "pricing-text-1", required = true)
    protected String pricingText1;
    @XmlElement(name = "pricing-text-2", required = true)
    protected String pricingText2;
    @XmlElement(name = "is-reserve-only")
    protected boolean isReserveOnly;
    @XmlElement(name = "service-type", required = true)
    protected String serviceType;
    @XmlElement(name = "price-point", required = true)
    protected PricePointFullType pricePoint;
    @XmlElement(name = "price-points")
    protected ErServiceFullType.PricePoints pricePoints;
    @XmlElement(name = "is-default-type")
    protected boolean isDefaultType;
    @XmlElement(name = "provisioning-system", required = true)
    protected String provisioningSystem;
    @XmlElement(name = "provisioning-tag", required = true)
    protected String provisioningTag;
    @XmlElement(name = "usage-id", required = true)
    protected String usageId;

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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the isReserveOnly property.
     * 
     */
    public boolean isIsReserveOnly() {
        return isReserveOnly;
    }

    /**
     * Sets the value of the isReserveOnly property.
     * 
     */
    public void setIsReserveOnly(boolean value) {
        this.isReserveOnly = value;
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

    /**
     * Gets the value of the pricePoint property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointFullType }
     *     
     */
    public PricePointFullType getPricePoint() {
        return pricePoint;
    }

    /**
     * Sets the value of the pricePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointFullType }
     *     
     */
    public void setPricePoint(PricePointFullType value) {
        this.pricePoint = value;
    }

    /**
     * Gets the value of the pricePoints property.
     * 
     * @return
     *     possible object is
     *     {@link ErServiceFullType.PricePoints }
     *     
     */
    public ErServiceFullType.PricePoints getPricePoints() {
        return pricePoints;
    }

    /**
     * Sets the value of the pricePoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErServiceFullType.PricePoints }
     *     
     */
    public void setPricePoints(ErServiceFullType.PricePoints value) {
        this.pricePoints = value;
    }

    /**
     * Gets the value of the isDefaultType property.
     * 
     */
    public boolean isIsDefaultType() {
        return isDefaultType;
    }

    /**
     * Sets the value of the isDefaultType property.
     * 
     */
    public void setIsDefaultType(boolean value) {
        this.isDefaultType = value;
    }

    /**
     * Gets the value of the provisioningSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvisioningSystem() {
        return provisioningSystem;
    }

    /**
     * Sets the value of the provisioningSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvisioningSystem(String value) {
        this.provisioningSystem = value;
    }

    /**
     * Gets the value of the provisioningTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvisioningTag() {
        return provisioningTag;
    }

    /**
     * Sets the value of the provisioningTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvisioningTag(String value) {
        this.provisioningTag = value;
    }

    /**
     * Gets the value of the usageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageId() {
        return usageId;
    }

    /**
     * Sets the value of the usageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageId(String value) {
        this.usageId = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}pricePointFullType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pricePoint"
    })
    public static class PricePoints
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "price-point")
        protected List<PricePointFullType> pricePoint;

        /**
         * Gets the value of the pricePoint property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pricePoint property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPricePoint().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PricePointFullType }
         * 
         * 
         */
        public List<PricePointFullType> getPricePoint() {
            if (pricePoint == null) {
                pricePoint = new ArrayList<PricePointFullType>();
            }
            return this.pricePoint;
        }

    }

}
