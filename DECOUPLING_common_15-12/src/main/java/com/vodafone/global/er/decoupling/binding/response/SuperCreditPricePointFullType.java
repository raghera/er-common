
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for superCreditPricePointFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="superCreditPricePointFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rate-identifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="super-credit-text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is-active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
 *         &lt;element name="is-preview" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-pre-order" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-trial" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-discount" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-recurring-package" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="interactive-flag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="drm-object" type="{http://localhost:8080/decoupling/schemas/common}drmObjectFullType"/>
 *         &lt;element name="charging-resource" type="{http://localhost:8080/decoupling/schemas/common}chargingResourceFullType"/>
 *         &lt;element name="is-valid-super-credit-option" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="rate-as-string" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roaming-net-amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="roaming-gross-amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="roaming-type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tax-rate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="is-historic" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="bearer-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "superCreditPricePointFullType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "rateIdentifier",
    "superCreditText",
    "isActive",
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
    "isPreview",
    "isPreOrder",
    "isTrial",
    "isDiscount",
    "isRecurringPackage",
    "interactiveFlag",
    "drmObject",
    "chargingResource",
    "isValidSuperCreditOption",
    "rateAsString",
    "roamingNetAmount",
    "roamingGrossAmount",
    "roamingType",
    "taxRate",
    "isHistoric",
    "bearerId"
})
public class SuperCreditPricePointFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "rate-identifier", required = true)
    protected String rateIdentifier;
    @XmlElement(name = "super-credit-text", required = true)
    protected String superCreditText;
    @XmlElement(name = "is-active")
    protected boolean isActive;
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
    @XmlElement(name = "interactive-flag")
    protected boolean interactiveFlag;
    @XmlElement(name = "drm-object", required = true)
    protected DrmObjectFullType drmObject;
    @XmlElement(name = "charging-resource", required = true)
    protected ChargingResourceFullType chargingResource;
    @XmlElement(name = "is-valid-super-credit-option")
    protected boolean isValidSuperCreditOption;
    @XmlElement(name = "rate-as-string", required = true)
    protected String rateAsString;
    @XmlElement(name = "roaming-net-amount")
    protected Double roamingNetAmount;
    @XmlElement(name = "roaming-gross-amount")
    protected Double roamingGrossAmount;
    @XmlElement(name = "roaming-type", required = true)
    protected String roamingType;
    @XmlElement(name = "tax-rate")
    protected Double taxRate;
    @XmlElement(name = "is-historic")
    protected boolean isHistoric;
    @XmlElement(name = "bearer-id")
    protected String bearerId;

    /**
     * Gets the value of the rateIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateIdentifier() {
        return rateIdentifier;
    }

    /**
     * Sets the value of the rateIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateIdentifier(String value) {
        this.rateIdentifier = value;
    }

    /**
     * Gets the value of the superCreditText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuperCreditText() {
        return superCreditText;
    }

    /**
     * Sets the value of the superCreditText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuperCreditText(String value) {
        this.superCreditText = value;
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
     */
    public boolean isInteractiveFlag() {
        return interactiveFlag;
    }

    /**
     * Sets the value of the interactiveFlag property.
     * 
     */
    public void setInteractiveFlag(boolean value) {
        this.interactiveFlag = value;
    }

    /**
     * Gets the value of the drmObject property.
     * 
     * @return
     *     possible object is
     *     {@link DrmObjectFullType }
     *     
     */
    public DrmObjectFullType getDrmObject() {
        return drmObject;
    }

    /**
     * Sets the value of the drmObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrmObjectFullType }
     *     
     */
    public void setDrmObject(DrmObjectFullType value) {
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

}
