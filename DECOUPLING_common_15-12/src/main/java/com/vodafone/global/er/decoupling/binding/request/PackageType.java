
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for packageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="packageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="simple-package-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pricing-text-1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pricing-text-2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is-reserve-only" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="package-type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="drm-type" type="{http://localhost:8080/decoupling/schemas/common}drm-typeType"/>
 *         &lt;element name="express-purchase" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-receipting-flag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-event-package" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-calendar-package" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-recurring-package" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="rate-as-string" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}price-pointType"/>
 *         &lt;element name="sub-price-point" type="{http://localhost:8080/decoupling/schemas/common}price-pointType" minOccurs="0"/>
 *         &lt;element name="is-price-point-ordered" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="partner-logo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price-points" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}price-pointType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="notification-category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "packageType", propOrder = {
    "id",
    "simplePackageId",
    "name",
    "description",
    "pricingText1",
    "pricingText2",
    "isReserveOnly",
    "packageType",
    "drmType",
    "expressPurchase",
    "isReceiptingFlag",
    "isEventPackage",
    "isCalendarPackage",
    "isRecurringPackage",
    "rate",
    "rateAsString",
    "pricePoint",
    "subPricePoint",
    "isPricePointOrdered",
    "partnerLogo",
    "partnerText",
    "pricePoints",
    "notificationCategory",
    "status"
})
public class PackageType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(name = "simple-package-id", required = true)
    protected String simplePackageId;
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
    @XmlElement(name = "package-type", required = true)
    protected String packageType;
    @XmlElement(name = "drm-type", required = true)
    protected DrmTypeType drmType;
    @XmlElement(name = "express-purchase")
    protected boolean expressPurchase;
    @XmlElement(name = "is-receipting-flag")
    protected boolean isReceiptingFlag;
    @XmlElement(name = "is-event-package")
    protected boolean isEventPackage;
    @XmlElement(name = "is-calendar-package")
    protected boolean isCalendarPackage;
    @XmlElement(name = "is-recurring-package")
    protected boolean isRecurringPackage;
    protected double rate;
    @XmlElement(name = "rate-as-string", required = true)
    protected String rateAsString;
    @XmlElement(name = "price-point", required = true)
    protected PricePointType pricePoint;
    @XmlElement(name = "sub-price-point")
    protected PricePointType subPricePoint;
    @XmlElement(name = "is-price-point-ordered")
    protected boolean isPricePointOrdered;
    @XmlElement(name = "partner-logo")
    protected String partnerLogo;
    @XmlElement(name = "partner-text")
    protected String partnerText;
    @XmlElement(name = "price-points")
    protected PackageType.PricePoints pricePoints;
    @XmlElement(name = "notification-category")
    protected String notificationCategory;
    protected String status;

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
     * Gets the value of the simplePackageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimplePackageId() {
        return simplePackageId;
    }

    /**
     * Sets the value of the simplePackageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimplePackageId(String value) {
        this.simplePackageId = value;
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
     * Gets the value of the packageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageType() {
        return packageType;
    }

    /**
     * Sets the value of the packageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageType(String value) {
        this.packageType = value;
    }

    /**
     * Gets the value of the drmType property.
     * 
     * @return
     *     possible object is
     *     {@link DrmTypeType }
     *     
     */
    public DrmTypeType getDrmType() {
        return drmType;
    }

    /**
     * Sets the value of the drmType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrmTypeType }
     *     
     */
    public void setDrmType(DrmTypeType value) {
        this.drmType = value;
    }

    /**
     * Gets the value of the expressPurchase property.
     * 
     */
    public boolean isExpressPurchase() {
        return expressPurchase;
    }

    /**
     * Sets the value of the expressPurchase property.
     * 
     */
    public void setExpressPurchase(boolean value) {
        this.expressPurchase = value;
    }

    /**
     * Gets the value of the isReceiptingFlag property.
     * 
     */
    public boolean isIsReceiptingFlag() {
        return isReceiptingFlag;
    }

    /**
     * Sets the value of the isReceiptingFlag property.
     * 
     */
    public void setIsReceiptingFlag(boolean value) {
        this.isReceiptingFlag = value;
    }

    /**
     * Gets the value of the isEventPackage property.
     * 
     */
    public boolean isIsEventPackage() {
        return isEventPackage;
    }

    /**
     * Sets the value of the isEventPackage property.
     * 
     */
    public void setIsEventPackage(boolean value) {
        this.isEventPackage = value;
    }

    /**
     * Gets the value of the isCalendarPackage property.
     * 
     */
    public boolean isIsCalendarPackage() {
        return isCalendarPackage;
    }

    /**
     * Sets the value of the isCalendarPackage property.
     * 
     */
    public void setIsCalendarPackage(boolean value) {
        this.isCalendarPackage = value;
    }

    /**
     * Gets the value of the isRecurringPackage property.
     * 
     */
    public boolean isIsRecurringPackage() {
        return isRecurringPackage;
    }

    /**
     * Sets the value of the isRecurringPackage property.
     * 
     */
    public void setIsRecurringPackage(boolean value) {
        this.isRecurringPackage = value;
    }

    /**
     * Gets the value of the rate property.
     * 
     */
    public double getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     * 
     */
    public void setRate(double value) {
        this.rate = value;
    }

    /**
     * Gets the value of the rateAsString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateAsString() {
        return rateAsString;
    }

    /**
     * Sets the value of the rateAsString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateAsString(String value) {
        this.rateAsString = value;
    }

    /**
     * Gets the value of the pricePoint property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointType }
     *     
     */
    public PricePointType getPricePoint() {
        return pricePoint;
    }

    /**
     * Sets the value of the pricePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointType }
     *     
     */
    public void setPricePoint(PricePointType value) {
        this.pricePoint = value;
    }

    /**
     * Gets the value of the subPricePoint property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointType }
     *     
     */
    public PricePointType getSubPricePoint() {
        return subPricePoint;
    }

    /**
     * Sets the value of the subPricePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointType }
     *     
     */
    public void setSubPricePoint(PricePointType value) {
        this.subPricePoint = value;
    }

    /**
     * Gets the value of the isPricePointOrdered property.
     * 
     */
    public boolean isIsPricePointOrdered() {
        return isPricePointOrdered;
    }

    /**
     * Sets the value of the isPricePointOrdered property.
     * 
     */
    public void setIsPricePointOrdered(boolean value) {
        this.isPricePointOrdered = value;
    }

    /**
     * Gets the value of the partnerLogo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerLogo() {
        return partnerLogo;
    }

    /**
     * Sets the value of the partnerLogo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerLogo(String value) {
        this.partnerLogo = value;
    }

    /**
     * Gets the value of the partnerText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerText() {
        return partnerText;
    }

    /**
     * Sets the value of the partnerText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerText(String value) {
        this.partnerText = value;
    }

    /**
     * Gets the value of the pricePoints property.
     * 
     * @return
     *     possible object is
     *     {@link PackageType.PricePoints }
     *     
     */
    public PackageType.PricePoints getPricePoints() {
        return pricePoints;
    }

    /**
     * Sets the value of the pricePoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageType.PricePoints }
     *     
     */
    public void setPricePoints(PackageType.PricePoints value) {
        this.pricePoints = value;
    }

    /**
     * Gets the value of the notificationCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationCategory() {
        return notificationCategory;
    }

    /**
     * Sets the value of the notificationCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationCategory(String value) {
        this.notificationCategory = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
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
     *         &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}price-pointType" maxOccurs="unbounded" minOccurs="0"/>
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
        protected List<PricePointType> pricePoint;

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
         * {@link PricePointType }
         * 
         * 
         */
        public List<PricePointType> getPricePoint() {
            if (pricePoint == null) {
                pricePoint = new ArrayList<PricePointType>();
            }
            return this.pricePoint;
        }

    }

}
