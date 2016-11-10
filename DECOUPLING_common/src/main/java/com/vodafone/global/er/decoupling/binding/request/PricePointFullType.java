
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for pricePointFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pricePointFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="discount-promo-text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="net-rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pricing-text-1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pricing-text-2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="standard-rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="standard-rate-as-string" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price-point-id-link" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="purchase-link-text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="promo-code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fixed-expiry-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="is-active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-preview" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-pre-order" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-trial" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-discount" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-recurring-package" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="interactive-flag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="drm-object" type="{http://localhost:8080/decoupling/schemas/common}drm-objectType"/>
 *         &lt;element name="charging-resource" type="{http://localhost:8080/decoupling/schemas/common}chargingResourceFullType"/>
 *         &lt;element name="is-valid-super-credit-option" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="duration" type="{http://localhost:8080/decoupling/schemas/common}durationType"/>
 *         &lt;element name="order" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="min-sub-period" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="allow-duplicate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="balance-impacts" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="balance-impact" type="{http://localhost:8080/decoupling/schemas/common}chargingResourceFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="balance-impact-rates" type="{http://localhost:8080/decoupling/schemas/common}balanceImpactRates" minOccurs="0"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="rate-as-string" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="super-credit-price-points" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="super-credit-price-point" type="{http://localhost:8080/decoupling/schemas/common}superCreditPricePointFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="roaming-net-amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="roaming-gross-amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="roaming-type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="payment-type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="expiry-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tax-rate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="bearer-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charging-method" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="user-groups" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="user-group" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="user-group" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="is-purchaseable-by-super-credit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="credit-purchase-price-points" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="credit-purchase-price-point" type="{http://localhost:8080/decoupling/schemas/common}superCreditPricePointFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="is-historic" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="price-point-tiers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="price-point-tier" type="{http://localhost:8080/decoupling/schemas/common}pricePointTierFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
 *         &lt;element name="is-express" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="channel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tariff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pricePointFullType", propOrder = {
    "id",
    "discountPromoText",
    "netRate",
    "pricingText1",
    "pricingText2",
    "standardRate",
    "standardRateAsString",
    "pricePointIdLink",
    "purchaseLinkText",
    "promoCode",
    "fixedExpiryDate",
    "isActive",
    "isPreview",
    "isPreOrder",
    "isTrial",
    "isDiscount",
    "isRecurringPackage",
    "interactiveFlag",
    "drmObject",
    "chargingResource",
    "isValidSuperCreditOption",
    "duration",
    "order",
    "minSubPeriod",
    "allowDuplicate",
    "balanceImpacts",
    "balanceImpactRates",
    "rate",
    "rateAsString",
    "superCreditPricePoints",
    "roamingNetAmount",
    "roamingGrossAmount",
    "roamingType",
    "paymentType",
    "startDate",
    "expiryDate",
    "taxRate",
    "bearerId",
    "chargingMethod",
    "userGroups",
    "userGroup",
    "isPurchaseableBySuperCredit",
    "creditPurchasePricePoints",
    "isHistoric",
    "pricePointTiers",
    "customFields",
    "isExpress",
    "channel",
    "tariff"
})
public class PricePointFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(name = "discount-promo-text", required = true)
    protected String discountPromoText;
    @XmlElement(name = "net-rate")
    protected double netRate;
    @XmlElement(name = "pricing-text-1", required = true)
    protected String pricingText1;
    @XmlElement(name = "pricing-text-2", required = true)
    protected String pricingText2;
    @XmlElement(name = "standard-rate")
    protected double standardRate;
    @XmlElement(name = "standard-rate-as-string", required = true)
    protected String standardRateAsString;
    @XmlElement(name = "price-point-id-link", required = true)
    protected String pricePointIdLink;
    @XmlElement(name = "purchase-link-text", required = true)
    protected String purchaseLinkText;
    @XmlElement(name = "promo-code", required = true)
    protected String promoCode;
    @XmlElement(name = "fixed-expiry-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar fixedExpiryDate;
    @XmlElement(name = "is-active")
    protected boolean isActive;
    @XmlElement(name = "is-preview")
    protected boolean isPreview;
    @XmlElement(name = "is-pre-order")
    protected boolean isPreOrder;
    @XmlElement(name = "is-trial")
    protected boolean isTrial;
    @XmlElement(name = "is-discount")
    protected boolean isDiscount;
    @XmlElement(name = "is-recurring-package")
    protected boolean isRecurringPackage;
    @XmlElement(name = "interactive-flag", required = true)
    protected String interactiveFlag;
    @XmlElement(name = "drm-object", required = true)
    protected DrmObjectType drmObject;
    @XmlElement(name = "charging-resource", required = true)
    protected ChargingResourceFullType chargingResource;
    @XmlElement(name = "is-valid-super-credit-option")
    protected boolean isValidSuperCreditOption;
    @XmlElement(required = true)
    protected DurationType duration;
    protected int order;
    @XmlElement(name = "min-sub-period")
    protected int minSubPeriod;
    @XmlElement(name = "allow-duplicate")
    protected Boolean allowDuplicate;
    @XmlElement(name = "balance-impacts")
    protected PricePointFullType.BalanceImpacts balanceImpacts;
    @XmlElement(name = "balance-impact-rates")
    protected BalanceImpactRates balanceImpactRates;
    protected double rate;
    @XmlElement(name = "rate-as-string", required = true)
    protected String rateAsString;
    @XmlElement(name = "super-credit-price-points")
    protected PricePointFullType.SuperCreditPricePoints superCreditPricePoints;
    @XmlElement(name = "roaming-net-amount")
    protected Double roamingNetAmount;
    @XmlElement(name = "roaming-gross-amount")
    protected Double roamingGrossAmount;
    @XmlElement(name = "roaming-type", required = true)
    protected String roamingType;
    @XmlElement(name = "payment-type")
    protected Integer paymentType;
    @XmlElement(name = "start-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar startDate;
    @XmlElement(name = "expiry-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar expiryDate;
    @XmlElement(name = "tax-rate")
    protected Double taxRate;
    @XmlElement(name = "bearer-id")
    protected String bearerId;
    @XmlElement(name = "charging-method")
    protected Integer chargingMethod;
    @XmlElement(name = "user-groups")
    protected PricePointFullType.UserGroups userGroups;
    @XmlElement(name = "user-group")
    protected String userGroup;
    @XmlElement(name = "is-purchaseable-by-super-credit")
    protected Boolean isPurchaseableBySuperCredit;
    @XmlElement(name = "credit-purchase-price-points")
    protected PricePointFullType.CreditPurchasePricePoints creditPurchasePricePoints;
    @XmlElement(name = "is-historic")
    protected boolean isHistoric;
    @XmlElement(name = "price-point-tiers")
    protected PricePointFullType.PricePointTiers pricePointTiers;
    @XmlElement(name = "custom-fields")
    protected PricePointFullType.CustomFields customFields;
    @XmlElement(name = "is-express")
    protected Boolean isExpress;
    protected Integer channel;
    protected String tariff;

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
     * Gets the value of the discountPromoText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountPromoText() {
        return discountPromoText;
    }

    /**
     * Sets the value of the discountPromoText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountPromoText(String value) {
        this.discountPromoText = value;
    }

    /**
     * Gets the value of the netRate property.
     * 
     */
    public double getNetRate() {
        return netRate;
    }

    /**
     * Sets the value of the netRate property.
     * 
     */
    public void setNetRate(double value) {
        this.netRate = value;
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
     * Gets the value of the standardRate property.
     * 
     */
    public double getStandardRate() {
        return standardRate;
    }

    /**
     * Sets the value of the standardRate property.
     * 
     */
    public void setStandardRate(double value) {
        this.standardRate = value;
    }

    /**
     * Gets the value of the standardRateAsString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandardRateAsString() {
        return standardRateAsString;
    }

    /**
     * Sets the value of the standardRateAsString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandardRateAsString(String value) {
        this.standardRateAsString = value;
    }

    /**
     * Gets the value of the pricePointIdLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePointIdLink() {
        return pricePointIdLink;
    }

    /**
     * Sets the value of the pricePointIdLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePointIdLink(String value) {
        this.pricePointIdLink = value;
    }

    /**
     * Gets the value of the purchaseLinkText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseLinkText() {
        return purchaseLinkText;
    }

    /**
     * Sets the value of the purchaseLinkText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseLinkText(String value) {
        this.purchaseLinkText = value;
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
     * Gets the value of the fixedExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getFixedExpiryDate() {
        return fixedExpiryDate;
    }

    /**
     * Sets the value of the fixedExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedExpiryDate(Calendar value) {
        this.fixedExpiryDate = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     */
    public void setIsActive(boolean value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the isPreview property.
     * 
     */
    public boolean isIsPreview() {
        return isPreview;
    }

    /**
     * Sets the value of the isPreview property.
     * 
     */
    public void setIsPreview(boolean value) {
        this.isPreview = value;
    }

    /**
     * Gets the value of the isPreOrder property.
     * 
     */
    public boolean isIsPreOrder() {
        return isPreOrder;
    }

    /**
     * Sets the value of the isPreOrder property.
     * 
     */
    public void setIsPreOrder(boolean value) {
        this.isPreOrder = value;
    }

    /**
     * Gets the value of the isTrial property.
     * 
     */
    public boolean isIsTrial() {
        return isTrial;
    }

    /**
     * Sets the value of the isTrial property.
     * 
     */
    public void setIsTrial(boolean value) {
        this.isTrial = value;
    }

    /**
     * Gets the value of the isDiscount property.
     * 
     */
    public boolean isIsDiscount() {
        return isDiscount;
    }

    /**
     * Sets the value of the isDiscount property.
     * 
     */
    public void setIsDiscount(boolean value) {
        this.isDiscount = value;
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
     * Gets the value of the interactiveFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInteractiveFlag() {
        return interactiveFlag;
    }

    /**
     * Sets the value of the interactiveFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInteractiveFlag(String value) {
        this.interactiveFlag = value;
    }

    /**
     * Gets the value of the drmObject property.
     * 
     * @return
     *     possible object is
     *     {@link DrmObjectType }
     *     
     */
    public DrmObjectType getDrmObject() {
        return drmObject;
    }

    /**
     * Sets the value of the drmObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrmObjectType }
     *     
     */
    public void setDrmObject(DrmObjectType value) {
        this.drmObject = value;
    }

    /**
     * Gets the value of the chargingResource property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingResourceFullType }
     *     
     */
    public ChargingResourceFullType getChargingResource() {
        return chargingResource;
    }

    /**
     * Sets the value of the chargingResource property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingResourceFullType }
     *     
     */
    public void setChargingResource(ChargingResourceFullType value) {
        this.chargingResource = value;
    }

    /**
     * Gets the value of the isValidSuperCreditOption property.
     * 
     */
    public boolean isIsValidSuperCreditOption() {
        return isValidSuperCreditOption;
    }

    /**
     * Sets the value of the isValidSuperCreditOption property.
     * 
     */
    public void setIsValidSuperCreditOption(boolean value) {
        this.isValidSuperCreditOption = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link DurationType }
     *     
     */
    public DurationType getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link DurationType }
     *     
     */
    public void setDuration(DurationType value) {
        this.duration = value;
    }

    /**
     * Gets the value of the order property.
     * 
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     */
    public void setOrder(int value) {
        this.order = value;
    }

    /**
     * Gets the value of the minSubPeriod property.
     * 
     */
    public int getMinSubPeriod() {
        return minSubPeriod;
    }

    /**
     * Sets the value of the minSubPeriod property.
     * 
     */
    public void setMinSubPeriod(int value) {
        this.minSubPeriod = value;
    }

    /**
     * Gets the value of the allowDuplicate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isAllowDuplicate() {
        if (allowDuplicate == null) {
            return false;
        } else {
            return allowDuplicate;
        }
    }

    /**
     * Sets the value of the allowDuplicate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowDuplicate(Boolean value) {
        this.allowDuplicate = value;
    }

    /**
     * Gets the value of the balanceImpacts property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointFullType.BalanceImpacts }
     *     
     */
    public PricePointFullType.BalanceImpacts getBalanceImpacts() {
        return balanceImpacts;
    }

    /**
     * Sets the value of the balanceImpacts property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointFullType.BalanceImpacts }
     *     
     */
    public void setBalanceImpacts(PricePointFullType.BalanceImpacts value) {
        this.balanceImpacts = value;
    }

    /**
     * Gets the value of the balanceImpactRates property.
     * 
     * @return
     *     possible object is
     *     {@link BalanceImpactRates }
     *     
     */
    public BalanceImpactRates getBalanceImpactRates() {
        return balanceImpactRates;
    }

    /**
     * Sets the value of the balanceImpactRates property.
     * 
     * @param value
     *     allowed object is
     *     {@link BalanceImpactRates }
     *     
     */
    public void setBalanceImpactRates(BalanceImpactRates value) {
        this.balanceImpactRates = value;
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
     * Gets the value of the superCreditPricePoints property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointFullType.SuperCreditPricePoints }
     *     
     */
    public PricePointFullType.SuperCreditPricePoints getSuperCreditPricePoints() {
        return superCreditPricePoints;
    }

    /**
     * Sets the value of the superCreditPricePoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointFullType.SuperCreditPricePoints }
     *     
     */
    public void setSuperCreditPricePoints(PricePointFullType.SuperCreditPricePoints value) {
        this.superCreditPricePoints = value;
    }

    /**
     * Gets the value of the roamingNetAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getRoamingNetAmount() {
        if (roamingNetAmount == null) {
            return  0.0D;
        } else {
            return roamingNetAmount;
        }
    }

    /**
     * Sets the value of the roamingNetAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRoamingNetAmount(Double value) {
        this.roamingNetAmount = value;
    }

    /**
     * Gets the value of the roamingGrossAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getRoamingGrossAmount() {
        if (roamingGrossAmount == null) {
            return  0.0D;
        } else {
            return roamingGrossAmount;
        }
    }

    /**
     * Sets the value of the roamingGrossAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRoamingGrossAmount(Double value) {
        this.roamingGrossAmount = value;
    }

    /**
     * Gets the value of the roamingType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoamingType() {
        return roamingType;
    }

    /**
     * Sets the value of the roamingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoamingType(String value) {
        this.roamingType = value;
    }

    /**
     * Gets the value of the paymentType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getPaymentType() {
        if (paymentType == null) {
            return  0;
        } else {
            return paymentType;
        }
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPaymentType(Integer value) {
        this.paymentType = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(Calendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiryDate(Calendar value) {
        this.expiryDate = value;
    }

    /**
     * Gets the value of the taxRate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getTaxRate() {
        if (taxRate == null) {
            return  0.0D;
        } else {
            return taxRate;
        }
    }

    /**
     * Sets the value of the taxRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTaxRate(Double value) {
        this.taxRate = value;
    }

    /**
     * Gets the value of the bearerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBearerId() {
        return bearerId;
    }

    /**
     * Sets the value of the bearerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBearerId(String value) {
        this.bearerId = value;
    }

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
     * Gets the value of the userGroups property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointFullType.UserGroups }
     *     
     */
    public PricePointFullType.UserGroups getUserGroups() {
        return userGroups;
    }

    /**
     * Sets the value of the userGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointFullType.UserGroups }
     *     
     */
    public void setUserGroups(PricePointFullType.UserGroups value) {
        this.userGroups = value;
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
     * Gets the value of the isPurchaseableBySuperCredit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsPurchaseableBySuperCredit() {
        if (isPurchaseableBySuperCredit == null) {
            return false;
        } else {
            return isPurchaseableBySuperCredit;
        }
    }

    /**
     * Sets the value of the isPurchaseableBySuperCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPurchaseableBySuperCredit(Boolean value) {
        this.isPurchaseableBySuperCredit = value;
    }

    /**
     * Gets the value of the creditPurchasePricePoints property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointFullType.CreditPurchasePricePoints }
     *     
     */
    public PricePointFullType.CreditPurchasePricePoints getCreditPurchasePricePoints() {
        return creditPurchasePricePoints;
    }

    /**
     * Sets the value of the creditPurchasePricePoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointFullType.CreditPurchasePricePoints }
     *     
     */
    public void setCreditPurchasePricePoints(PricePointFullType.CreditPurchasePricePoints value) {
        this.creditPurchasePricePoints = value;
    }

    /**
     * Gets the value of the isHistoric property.
     * 
     */
    public boolean isIsHistoric() {
        return isHistoric;
    }

    /**
     * Sets the value of the isHistoric property.
     * 
     */
    public void setIsHistoric(boolean value) {
        this.isHistoric = value;
    }

    /**
     * Gets the value of the pricePointTiers property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointFullType.PricePointTiers }
     *     
     */
    public PricePointFullType.PricePointTiers getPricePointTiers() {
        return pricePointTiers;
    }

    /**
     * Sets the value of the pricePointTiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointFullType.PricePointTiers }
     *     
     */
    public void setPricePointTiers(PricePointFullType.PricePointTiers value) {
        this.pricePointTiers = value;
    }

    /**
     * Gets the value of the customFields property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointFullType.CustomFields }
     *     
     */
    public PricePointFullType.CustomFields getCustomFields() {
        return customFields;
    }

    /**
     * Sets the value of the customFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointFullType.CustomFields }
     *     
     */
    public void setCustomFields(PricePointFullType.CustomFields value) {
        this.customFields = value;
    }

    /**
     * Gets the value of the isExpress property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsExpress() {
        if (isExpress == null) {
            return false;
        } else {
            return isExpress;
        }
    }

    /**
     * Sets the value of the isExpress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsExpress(Boolean value) {
        this.isExpress = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getChannel() {
        if (channel == null) {
            return  0;
        } else {
            return channel;
        }
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChannel(Integer value) {
        this.channel = value;
    }

    /**
     * Gets the value of the tariff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTariff() {
        return tariff;
    }

    /**
     * Sets the value of the tariff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTariff(String value) {
        this.tariff = value;
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
     *         &lt;element name="balance-impact" type="{http://localhost:8080/decoupling/schemas/common}chargingResourceFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "balanceImpact"
    })
    public static class BalanceImpacts
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "balance-impact")
        protected List<ChargingResourceFullType> balanceImpact;

        /**
         * Gets the value of the balanceImpact property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the balanceImpact property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBalanceImpact().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ChargingResourceFullType }
         * 
         * 
         */
        public List<ChargingResourceFullType> getBalanceImpact() {
            if (balanceImpact == null) {
                balanceImpact = new ArrayList<ChargingResourceFullType>();
            }
            return this.balanceImpact;
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
     *         &lt;element name="credit-purchase-price-point" type="{http://localhost:8080/decoupling/schemas/common}superCreditPricePointFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "creditPurchasePricePoint"
    })
    public static class CreditPurchasePricePoints
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "credit-purchase-price-point")
        protected List<SuperCreditPricePointFullType> creditPurchasePricePoint;

        /**
         * Gets the value of the creditPurchasePricePoint property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the creditPurchasePricePoint property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCreditPurchasePricePoint().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SuperCreditPricePointFullType }
         * 
         * 
         */
        public List<SuperCreditPricePointFullType> getCreditPurchasePricePoint() {
            if (creditPurchasePricePoint == null) {
                creditPurchasePricePoint = new ArrayList<SuperCreditPricePointFullType>();
            }
            return this.creditPurchasePricePoint;
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
     *         &lt;element name="price-point-tier" type="{http://localhost:8080/decoupling/schemas/common}pricePointTierFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "pricePointTier"
    })
    public static class PricePointTiers
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "price-point-tier")
        protected List<PricePointTierFullType> pricePointTier;

        /**
         * Gets the value of the pricePointTier property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pricePointTier property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPricePointTier().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PricePointTierFullType }
         * 
         * 
         */
        public List<PricePointTierFullType> getPricePointTier() {
            if (pricePointTier == null) {
                pricePointTier = new ArrayList<PricePointTierFullType>();
            }
            return this.pricePointTier;
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
     *         &lt;element name="super-credit-price-point" type="{http://localhost:8080/decoupling/schemas/common}superCreditPricePointFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "superCreditPricePoint"
    })
    public static class SuperCreditPricePoints
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "super-credit-price-point")
        protected List<SuperCreditPricePointFullType> superCreditPricePoint;

        /**
         * Gets the value of the superCreditPricePoint property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the superCreditPricePoint property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSuperCreditPricePoint().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SuperCreditPricePointFullType }
         * 
         * 
         */
        public List<SuperCreditPricePointFullType> getSuperCreditPricePoint() {
            if (superCreditPricePoint == null) {
                superCreditPricePoint = new ArrayList<SuperCreditPricePointFullType>();
            }
            return this.superCreditPricePoint;
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
     *         &lt;element name="user-group" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "userGroup"
    })
    public static class UserGroups
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "user-group")
        protected List<String> userGroup;

        /**
         * Gets the value of the userGroup property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the userGroup property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUserGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getUserGroup() {
            if (userGroup == null) {
                userGroup = new ArrayList<String>();
            }
            return this.userGroup;
        }

    }

}
