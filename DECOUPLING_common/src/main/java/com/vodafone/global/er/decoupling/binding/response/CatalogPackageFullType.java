
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
 * <p>Java class for catalogPackageFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="catalogPackageFullType">
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
 *         &lt;element name="drm-type" type="{http://localhost:8080/decoupling/schemas/common}drmTypeFullType"/>
 *         &lt;element name="express-purchase" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-receipting-flag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-event-package" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-calendar-package" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-recurring-package" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="rate-as-string" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}pricePointFullType"/>
 *         &lt;element name="sub-price-point" type="{http://localhost:8080/decoupling/schemas/common}pricePointFullType" minOccurs="0"/>
 *         &lt;element name="is-price-point-ordered" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="partner-logo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="is-package-model" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-revenue-share-by-usage" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-parent-package" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="parent-package-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="service-names" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="service-name" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="child-package-ids" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="child-package-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="is-dynamic-default" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="catalog-services" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="catalog-service" type="{http://localhost:8080/decoupling/schemas/common}catalogServiceFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="is-super-package" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="pricing-models" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pricing-model" type="{http://localhost:8080/decoupling/schemas/common}pricingModelFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="payment-content" type="{http://localhost:8080/decoupling/schemas/common}paymentContentFullType" minOccurs="0"/>
 *         &lt;element name="kpi-package-product-category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notification-category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custom-fields" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="custom-field" type="{http://localhost:8080/decoupling/schemas/common}customFieldFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
@XmlType(name = "catalogPackageFullType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
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
    "isPackageModel",
    "isRevenueShareByUsage",
    "isParentPackage",
    "parentPackageId",
    "serviceNames",
    "childPackageIds",
    "isDynamicDefault",
    "catalogServices",
    "isSuperPackage",
    "pricingModels",
    "paymentContent",
    "kpiPackageProductCategory",
    "notificationCategory",
    "customFields",
    "status"
})
public class CatalogPackageFullType
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
    protected DrmTypeFullType drmType;
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
    protected PricePointFullType pricePoint;
    @XmlElement(name = "sub-price-point")
    protected PricePointFullType subPricePoint;
    @XmlElement(name = "is-price-point-ordered")
    protected boolean isPricePointOrdered;
    @XmlElement(name = "partner-logo")
    protected String partnerLogo;
    @XmlElement(name = "partner-text")
    protected String partnerText;
    @XmlElement(name = "price-points")
    protected CatalogPackageFullType.PricePoints pricePoints;
    @XmlElement(name = "is-package-model")
    protected Boolean isPackageModel;
    @XmlElement(name = "is-revenue-share-by-usage")
    protected Boolean isRevenueShareByUsage;
    @XmlElement(name = "is-parent-package")
    protected Boolean isParentPackage;
    @XmlElement(name = "parent-package-id")
    protected String parentPackageId;
    @XmlElement(name = "service-names")
    protected CatalogPackageFullType.ServiceNames serviceNames;
    @XmlElement(name = "child-package-ids")
    protected CatalogPackageFullType.ChildPackageIds childPackageIds;
    @XmlElement(name = "is-dynamic-default")
    protected Boolean isDynamicDefault;
    @XmlElement(name = "catalog-services")
    protected CatalogPackageFullType.CatalogServices catalogServices;
    @XmlElement(name = "is-super-package")
    protected Boolean isSuperPackage;
    @XmlElement(name = "pricing-models")
    protected CatalogPackageFullType.PricingModels pricingModels;
    @XmlElement(name = "payment-content")
    protected PaymentContentFullType paymentContent;
    @XmlElement(name = "kpi-package-product-category")
    protected String kpiPackageProductCategory;
    @XmlElement(name = "notification-category")
    protected String notificationCategory;
    @XmlElement(name = "custom-fields")
    protected CatalogPackageFullType.CustomFields customFields;
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
     *     {@link DrmTypeFullType }
     *     
     */
    public DrmTypeFullType getDrmType() {
        return drmType;
    }

    /**
     * Sets the value of the drmType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrmTypeFullType }
     *     
     */
    public void setDrmType(DrmTypeFullType value) {
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
     * Gets the value of the subPricePoint property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointFullType }
     *     
     */
    public PricePointFullType getSubPricePoint() {
        return subPricePoint;
    }

    /**
     * Sets the value of the subPricePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointFullType }
     *     
     */
    public void setSubPricePoint(PricePointFullType value) {
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
     *     {@link CatalogPackageFullType.PricePoints }
     *     
     */
    public CatalogPackageFullType.PricePoints getPricePoints() {
        return pricePoints;
    }

    /**
     * Sets the value of the pricePoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogPackageFullType.PricePoints }
     *     
     */
    public void setPricePoints(CatalogPackageFullType.PricePoints value) {
        this.pricePoints = value;
    }

    /**
     * Gets the value of the isPackageModel property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsPackageModel() {
        if (isPackageModel == null) {
            return false;
        } else {
            return isPackageModel;
        }
    }

    /**
     * Sets the value of the isPackageModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPackageModel(Boolean value) {
        this.isPackageModel = value;
    }

    /**
     * Gets the value of the isRevenueShareByUsage property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsRevenueShareByUsage() {
        if (isRevenueShareByUsage == null) {
            return false;
        } else {
            return isRevenueShareByUsage;
        }
    }

    /**
     * Sets the value of the isRevenueShareByUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRevenueShareByUsage(Boolean value) {
        this.isRevenueShareByUsage = value;
    }

    /**
     * Gets the value of the isParentPackage property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsParentPackage() {
        if (isParentPackage == null) {
            return false;
        } else {
            return isParentPackage;
        }
    }

    /**
     * Sets the value of the isParentPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsParentPackage(Boolean value) {
        this.isParentPackage = value;
    }

    /**
     * Gets the value of the parentPackageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentPackageId() {
        return parentPackageId;
    }

    /**
     * Sets the value of the parentPackageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentPackageId(String value) {
        this.parentPackageId = value;
    }

    /**
     * Gets the value of the serviceNames property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogPackageFullType.ServiceNames }
     *     
     */
    public CatalogPackageFullType.ServiceNames getServiceNames() {
        return serviceNames;
    }

    /**
     * Sets the value of the serviceNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogPackageFullType.ServiceNames }
     *     
     */
    public void setServiceNames(CatalogPackageFullType.ServiceNames value) {
        this.serviceNames = value;
    }

    /**
     * Gets the value of the childPackageIds property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogPackageFullType.ChildPackageIds }
     *     
     */
    public CatalogPackageFullType.ChildPackageIds getChildPackageIds() {
        return childPackageIds;
    }

    /**
     * Sets the value of the childPackageIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogPackageFullType.ChildPackageIds }
     *     
     */
    public void setChildPackageIds(CatalogPackageFullType.ChildPackageIds value) {
        this.childPackageIds = value;
    }

    /**
     * Gets the value of the isDynamicDefault property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsDynamicDefault() {
        if (isDynamicDefault == null) {
            return false;
        } else {
            return isDynamicDefault;
        }
    }

    /**
     * Sets the value of the isDynamicDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDynamicDefault(Boolean value) {
        this.isDynamicDefault = value;
    }

    /**
     * Gets the value of the catalogServices property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogPackageFullType.CatalogServices }
     *     
     */
    public CatalogPackageFullType.CatalogServices getCatalogServices() {
        return catalogServices;
    }

    /**
     * Sets the value of the catalogServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogPackageFullType.CatalogServices }
     *     
     */
    public void setCatalogServices(CatalogPackageFullType.CatalogServices value) {
        this.catalogServices = value;
    }

    /**
     * Gets the value of the isSuperPackage property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsSuperPackage() {
        if (isSuperPackage == null) {
            return false;
        } else {
            return isSuperPackage;
        }
    }

    /**
     * Sets the value of the isSuperPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSuperPackage(Boolean value) {
        this.isSuperPackage = value;
    }

    /**
     * Gets the value of the pricingModels property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogPackageFullType.PricingModels }
     *     
     */
    public CatalogPackageFullType.PricingModels getPricingModels() {
        return pricingModels;
    }

    /**
     * Sets the value of the pricingModels property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogPackageFullType.PricingModels }
     *     
     */
    public void setPricingModels(CatalogPackageFullType.PricingModels value) {
        this.pricingModels = value;
    }

    /**
     * Gets the value of the paymentContent property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentContentFullType }
     *     
     */
    public PaymentContentFullType getPaymentContent() {
        return paymentContent;
    }

    /**
     * Sets the value of the paymentContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentContentFullType }
     *     
     */
    public void setPaymentContent(PaymentContentFullType value) {
        this.paymentContent = value;
    }

    /**
     * Gets the value of the kpiPackageProductCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKpiPackageProductCategory() {
        return kpiPackageProductCategory;
    }

    /**
     * Sets the value of the kpiPackageProductCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKpiPackageProductCategory(String value) {
        this.kpiPackageProductCategory = value;
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
     * Gets the value of the customFields property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogPackageFullType.CustomFields }
     *     
     */
    public CatalogPackageFullType.CustomFields getCustomFields() {
        return customFields;
    }

    /**
     * Sets the value of the customFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogPackageFullType.CustomFields }
     *     
     */
    public void setCustomFields(CatalogPackageFullType.CustomFields value) {
        this.customFields = value;
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
     *         &lt;element name="catalog-service" type="{http://localhost:8080/decoupling/schemas/common}catalogServiceFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "catalogService"
    })
    public static class CatalogServices
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "catalog-service")
        protected List<CatalogServiceFullType> catalogService;

        /**
         * Gets the value of the catalogService property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the catalogService property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCatalogService().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CatalogServiceFullType }
         * 
         * 
         */
        public List<CatalogServiceFullType> getCatalogService() {
            if (catalogService == null) {
                catalogService = new ArrayList<CatalogServiceFullType>();
            }
            return this.catalogService;
        }

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
     *         &lt;element name="child-package-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "childPackageId"
    })
    public static class ChildPackageIds
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "child-package-id")
        protected List<String> childPackageId;

        /**
         * Gets the value of the childPackageId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the childPackageId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChildPackageId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getChildPackageId() {
            if (childPackageId == null) {
                childPackageId = new ArrayList<String>();
            }
            return this.childPackageId;
        }

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
     *         &lt;element name="custom-field" type="{http://localhost:8080/decoupling/schemas/common}customFieldFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "customField"
    })
    public static class CustomFields
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "custom-field")
        protected List<CustomFieldFullType> customField;

        /**
         * Gets the value of the customField property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the customField property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCustomField().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CustomFieldFullType }
         * 
         * 
         */
        public List<CustomFieldFullType> getCustomField() {
            if (customField == null) {
                customField = new ArrayList<CustomFieldFullType>();
            }
            return this.customField;
        }

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
     *         &lt;element name="pricing-model" type="{http://localhost:8080/decoupling/schemas/common}pricingModelFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "pricingModel"
    })
    public static class PricingModels
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "pricing-model")
        protected List<PricingModelFullType> pricingModel;

        /**
         * Gets the value of the pricingModel property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pricingModel property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPricingModel().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PricingModelFullType }
         * 
         * 
         */
        public List<PricingModelFullType> getPricingModel() {
            if (pricingModel == null) {
                pricingModel = new ArrayList<PricingModelFullType>();
            }
            return this.pricingModel;
        }

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
     *         &lt;element name="service-name" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "serviceName"
    })
    public static class ServiceNames
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "service-name")
        protected List<String> serviceName;

        /**
         * Gets the value of the serviceName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the serviceName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServiceName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getServiceName() {
            if (serviceName == null) {
                serviceName = new ArrayList<String>();
            }
            return this.serviceName;
        }

    }

}
