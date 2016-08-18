
package com.vodafone.global.er.decoupling.binding.response;

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
 * <p>Java class for subscriptionFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscriptionFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="package" type="{http://localhost:8080/decoupling/schemas/common}catalogPackageFullType"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="purchase-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="expiry-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="local-start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="local-purchase-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="local-expiry-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="has-expired" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="resource-balances">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resourceBalanceFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="is-recurring" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="translated-status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rating-attributes" type="{http://localhost:8080/decoupling/schemas/common}ratingAttributesFullType"/>
 *         &lt;element name="final-min-subscription-end-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="current-number-of-occurences" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="min-subscription-period-elapsed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-preordered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="transactions" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="transaction" type="{http://localhost:8080/decoupling/schemas/common}transactionFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="super-credit-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="translated-super-credit-name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="super-credit-balances" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resourceBalanceFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="overdue-expiry-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="has-valid-micro-service" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="services" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="disallow-cancellations" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-refundable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-reserved" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-super-package" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="drm-object" type="{http://localhost:8080/decoupling/schemas/common}drmObjectFullType" minOccurs="0"/>
 *         &lt;element name="penalty-charge" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="next-cycle-discount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="b2b-partner" type="{http://localhost:8080/decoupling/schemas/common}b2bPartnerFullType" minOccurs="0"/>
 *         &lt;element name="parent-package-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-sub-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-identifier1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-identifier2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-identifier3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscriptionFullType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "_package",
    "id",
    "startDate",
    "purchaseDate",
    "expiryDate",
    "localStartDate",
    "localPurchaseDate",
    "localExpiryDate",
    "hasExpired",
    "resourceBalances",
    "isRecurring",
    "status",
    "translatedStatus",
    "ratingAttributes",
    "finalMinSubscriptionEndDate",
    "currentNumberOfOccurences",
    "minSubscriptionPeriodElapsed",
    "isPreordered",
    "transactions",
    "superCreditId",
    "translatedSuperCreditName",
    "superCreditBalances",
    "overdueExpiryDate",
    "hasValidMicroService",
    "services",
    "disallowCancellations",
    "isRefundable",
    "isReserved",
    "isSuperPackage",
    "drmObject",
    "penaltyCharge",
    "nextCycleDiscount",
    "b2BPartner",
    "parentPackageId",
    "externalSubId",
    "externalIdentifier1",
    "externalIdentifier2",
    "externalIdentifier3"
})
public class SubscriptionFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "package", required = true)
    protected CatalogPackageFullType _package;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(name = "start-date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar startDate;
    @XmlElement(name = "purchase-date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar purchaseDate;
    @XmlElement(name = "expiry-date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar expiryDate;
    @XmlElement(name = "local-start-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar localStartDate;
    @XmlElement(name = "local-purchase-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar localPurchaseDate;
    @XmlElement(name = "local-expiry-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar localExpiryDate;
    @XmlElement(name = "has-expired")
    protected boolean hasExpired;
    @XmlElement(name = "resource-balances", required = true)
    protected SubscriptionFullType.ResourceBalances resourceBalances;
    @XmlElement(name = "is-recurring")
    protected boolean isRecurring;
    protected int status;
    @XmlElement(name = "translated-status", required = true)
    protected String translatedStatus;
    @XmlElement(name = "rating-attributes", required = true)
    protected RatingAttributesFullType ratingAttributes;
    @XmlElement(name = "final-min-subscription-end-date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar finalMinSubscriptionEndDate;
    @XmlElement(name = "current-number-of-occurences")
    protected int currentNumberOfOccurences;
    @XmlElement(name = "min-subscription-period-elapsed")
    protected boolean minSubscriptionPeriodElapsed;
    @XmlElement(name = "is-preordered")
    protected Boolean isPreordered;
    protected SubscriptionFullType.Transactions transactions;
    @XmlElement(name = "super-credit-id", required = true)
    protected String superCreditId;
    @XmlElement(name = "translated-super-credit-name", required = true)
    protected String translatedSuperCreditName;
    @XmlElement(name = "super-credit-balances")
    protected SubscriptionFullType.SuperCreditBalances superCreditBalances;
    @XmlElement(name = "overdue-expiry-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar overdueExpiryDate;
    @XmlElement(name = "has-valid-micro-service")
    protected boolean hasValidMicroService;
    protected SubscriptionFullType.Services services;
    @XmlElement(name = "disallow-cancellations")
    protected Boolean disallowCancellations;
    @XmlElement(name = "is-refundable")
    protected Boolean isRefundable;
    @XmlElement(name = "is-reserved")
    protected Boolean isReserved;
    @XmlElement(name = "is-super-package")
    protected Boolean isSuperPackage;
    @XmlElement(name = "drm-object")
    protected DrmObjectFullType drmObject;
    @XmlElement(name = "penalty-charge")
    protected Double penaltyCharge;
    @XmlElement(name = "next-cycle-discount")
    protected Double nextCycleDiscount;
    @XmlElement(name = "b2b-partner")
    protected B2BPartnerFullType b2BPartner;
    @XmlElement(name = "parent-package-id")
    protected String parentPackageId;
    @XmlElement(name = "external-sub-id")
    protected String externalSubId;
    @XmlElement(name = "external-identifier1")
    protected String externalIdentifier1;
    @XmlElement(name = "external-identifier2")
    protected String externalIdentifier2;
    @XmlElement(name = "external-identifier3")
    protected String externalIdentifier3;

    /**
     * Gets the value of the package property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogPackageFullType }
     *     
     */
    public CatalogPackageFullType getPackage() {
        return _package;
    }

    /**
     * Sets the value of the package property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogPackageFullType }
     *     
     */
    public void setPackage(CatalogPackageFullType value) {
        this._package = value;
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
     * Gets the value of the purchaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the value of the purchaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseDate(Calendar value) {
        this.purchaseDate = value;
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
     * Gets the value of the localStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getLocalStartDate() {
        return localStartDate;
    }

    /**
     * Sets the value of the localStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalStartDate(Calendar value) {
        this.localStartDate = value;
    }

    /**
     * Gets the value of the localPurchaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getLocalPurchaseDate() {
        return localPurchaseDate;
    }

    /**
     * Sets the value of the localPurchaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalPurchaseDate(Calendar value) {
        this.localPurchaseDate = value;
    }

    /**
     * Gets the value of the localExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getLocalExpiryDate() {
        return localExpiryDate;
    }

    /**
     * Sets the value of the localExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalExpiryDate(Calendar value) {
        this.localExpiryDate = value;
    }

    /**
     * Gets the value of the hasExpired property.
     * 
     */
    public boolean isHasExpired() {
        return hasExpired;
    }

    /**
     * Sets the value of the hasExpired property.
     * 
     */
    public void setHasExpired(boolean value) {
        this.hasExpired = value;
    }

    /**
     * Gets the value of the resourceBalances property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionFullType.ResourceBalances }
     *     
     */
    public SubscriptionFullType.ResourceBalances getResourceBalances() {
        return resourceBalances;
    }

    /**
     * Sets the value of the resourceBalances property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionFullType.ResourceBalances }
     *     
     */
    public void setResourceBalances(SubscriptionFullType.ResourceBalances value) {
        this.resourceBalances = value;
    }

    /**
     * Gets the value of the isRecurring property.
     * 
     */
    public boolean isIsRecurring() {
        return isRecurring;
    }

    /**
     * Sets the value of the isRecurring property.
     * 
     */
    public void setIsRecurring(boolean value) {
        this.isRecurring = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the translatedStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranslatedStatus() {
        return translatedStatus;
    }

    /**
     * Sets the value of the translatedStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranslatedStatus(String value) {
        this.translatedStatus = value;
    }

    /**
     * Gets the value of the ratingAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link RatingAttributesFullType }
     *     
     */
    public RatingAttributesFullType getRatingAttributes() {
        return ratingAttributes;
    }

    /**
     * Sets the value of the ratingAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RatingAttributesFullType }
     *     
     */
    public void setRatingAttributes(RatingAttributesFullType value) {
        this.ratingAttributes = value;
    }

    /**
     * Gets the value of the finalMinSubscriptionEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getFinalMinSubscriptionEndDate() {
        return finalMinSubscriptionEndDate;
    }

    /**
     * Sets the value of the finalMinSubscriptionEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinalMinSubscriptionEndDate(Calendar value) {
        this.finalMinSubscriptionEndDate = value;
    }

    /**
     * Gets the value of the currentNumberOfOccurences property.
     * 
     */
    public int getCurrentNumberOfOccurences() {
        return currentNumberOfOccurences;
    }

    /**
     * Sets the value of the currentNumberOfOccurences property.
     * 
     */
    public void setCurrentNumberOfOccurences(int value) {
        this.currentNumberOfOccurences = value;
    }

    /**
     * Gets the value of the minSubscriptionPeriodElapsed property.
     * 
     */
    public boolean isMinSubscriptionPeriodElapsed() {
        return minSubscriptionPeriodElapsed;
    }

    /**
     * Sets the value of the minSubscriptionPeriodElapsed property.
     * 
     */
    public void setMinSubscriptionPeriodElapsed(boolean value) {
        this.minSubscriptionPeriodElapsed = value;
    }

    /**
     * Gets the value of the isPreordered property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsPreordered() {
        if (isPreordered == null) {
            return false;
        } else {
            return isPreordered;
        }
    }

    /**
     * Sets the value of the isPreordered property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPreordered(Boolean value) {
        this.isPreordered = value;
    }

    /**
     * Gets the value of the transactions property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionFullType.Transactions }
     *     
     */
    public SubscriptionFullType.Transactions getTransactions() {
        return transactions;
    }

    /**
     * Sets the value of the transactions property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionFullType.Transactions }
     *     
     */
    public void setTransactions(SubscriptionFullType.Transactions value) {
        this.transactions = value;
    }

    /**
     * Gets the value of the superCreditId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuperCreditId() {
        return superCreditId;
    }

    /**
     * Sets the value of the superCreditId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuperCreditId(String value) {
        this.superCreditId = value;
    }

    /**
     * Gets the value of the translatedSuperCreditName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranslatedSuperCreditName() {
        return translatedSuperCreditName;
    }

    /**
     * Sets the value of the translatedSuperCreditName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranslatedSuperCreditName(String value) {
        this.translatedSuperCreditName = value;
    }

    /**
     * Gets the value of the superCreditBalances property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionFullType.SuperCreditBalances }
     *     
     */
    public SubscriptionFullType.SuperCreditBalances getSuperCreditBalances() {
        return superCreditBalances;
    }

    /**
     * Sets the value of the superCreditBalances property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionFullType.SuperCreditBalances }
     *     
     */
    public void setSuperCreditBalances(SubscriptionFullType.SuperCreditBalances value) {
        this.superCreditBalances = value;
    }

    /**
     * Gets the value of the overdueExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getOverdueExpiryDate() {
        return overdueExpiryDate;
    }

    /**
     * Sets the value of the overdueExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverdueExpiryDate(Calendar value) {
        this.overdueExpiryDate = value;
    }

    /**
     * Gets the value of the hasValidMicroService property.
     * 
     */
    public boolean isHasValidMicroService() {
        return hasValidMicroService;
    }

    /**
     * Sets the value of the hasValidMicroService property.
     * 
     */
    public void setHasValidMicroService(boolean value) {
        this.hasValidMicroService = value;
    }

    /**
     * Gets the value of the services property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionFullType.Services }
     *     
     */
    public SubscriptionFullType.Services getServices() {
        return services;
    }

    /**
     * Sets the value of the services property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionFullType.Services }
     *     
     */
    public void setServices(SubscriptionFullType.Services value) {
        this.services = value;
    }

    /**
     * Gets the value of the disallowCancellations property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isDisallowCancellations() {
        if (disallowCancellations == null) {
            return false;
        } else {
            return disallowCancellations;
        }
    }

    /**
     * Sets the value of the disallowCancellations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisallowCancellations(Boolean value) {
        this.disallowCancellations = value;
    }

    /**
     * Gets the value of the isRefundable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsRefundable() {
        if (isRefundable == null) {
            return false;
        } else {
            return isRefundable;
        }
    }

    /**
     * Sets the value of the isRefundable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRefundable(Boolean value) {
        this.isRefundable = value;
    }

    /**
     * Gets the value of the isReserved property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsReserved() {
        if (isReserved == null) {
            return false;
        } else {
            return isReserved;
        }
    }

    /**
     * Sets the value of the isReserved property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReserved(Boolean value) {
        this.isReserved = value;
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
     * Gets the value of the penaltyCharge property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getPenaltyCharge() {
        if (penaltyCharge == null) {
            return  0.0D;
        } else {
            return penaltyCharge;
        }
    }

    /**
     * Sets the value of the penaltyCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPenaltyCharge(Double value) {
        this.penaltyCharge = value;
    }

    /**
     * Gets the value of the nextCycleDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getNextCycleDiscount() {
        if (nextCycleDiscount == null) {
            return  0.0D;
        } else {
            return nextCycleDiscount;
        }
    }

    /**
     * Sets the value of the nextCycleDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setNextCycleDiscount(Double value) {
        this.nextCycleDiscount = value;
    }

    /**
     * Gets the value of the b2BPartner property.
     * 
     * @return
     *     possible object is
     *     {@link B2BPartnerFullType }
     *     
     */
    public B2BPartnerFullType getB2BPartner() {
        return b2BPartner;
    }

    /**
     * Sets the value of the b2BPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link B2BPartnerFullType }
     *     
     */
    public void setB2BPartner(B2BPartnerFullType value) {
        this.b2BPartner = value;
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
     * Gets the value of the externalSubId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalSubId() {
        return externalSubId;
    }

    /**
     * Sets the value of the externalSubId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalSubId(String value) {
        this.externalSubId = value;
    }

    /**
     * Gets the value of the externalIdentifier1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalIdentifier1() {
        return externalIdentifier1;
    }

    /**
     * Sets the value of the externalIdentifier1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalIdentifier1(String value) {
        this.externalIdentifier1 = value;
    }

    /**
     * Gets the value of the externalIdentifier2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalIdentifier2() {
        return externalIdentifier2;
    }

    /**
     * Sets the value of the externalIdentifier2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalIdentifier2(String value) {
        this.externalIdentifier2 = value;
    }

    /**
     * Gets the value of the externalIdentifier3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalIdentifier3() {
        return externalIdentifier3;
    }

    /**
     * Sets the value of the externalIdentifier3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalIdentifier3(String value) {
        this.externalIdentifier3 = value;
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
     *         &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resourceBalanceFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "resourceBalance"
    })
    public static class ResourceBalances
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "resource-balance")
        protected List<ResourceBalanceFullType> resourceBalance;

        /**
         * Gets the value of the resourceBalance property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the resourceBalance property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getResourceBalance().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResourceBalanceFullType }
         * 
         * 
         */
        public List<ResourceBalanceFullType> getResourceBalance() {
            if (resourceBalance == null) {
                resourceBalance = new ArrayList<ResourceBalanceFullType>();
            }
            return this.resourceBalance;
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
     *         &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "serviceId"
    })
    public static class Services
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "service-id")
        protected List<String> serviceId;

        /**
         * Gets the value of the serviceId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the serviceId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServiceId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getServiceId() {
            if (serviceId == null) {
                serviceId = new ArrayList<String>();
            }
            return this.serviceId;
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
     *         &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resourceBalanceFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "resourceBalance"
    })
    public static class SuperCreditBalances
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "resource-balance")
        protected List<ResourceBalanceFullType> resourceBalance;

        /**
         * Gets the value of the resourceBalance property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the resourceBalance property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getResourceBalance().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResourceBalanceFullType }
         * 
         * 
         */
        public List<ResourceBalanceFullType> getResourceBalance() {
            if (resourceBalance == null) {
                resourceBalance = new ArrayList<ResourceBalanceFullType>();
            }
            return this.resourceBalance;
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
     *         &lt;element name="transaction" type="{http://localhost:8080/decoupling/schemas/common}transactionFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "transaction"
    })
    public static class Transactions
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        protected List<TransactionFullType> transaction;

        /**
         * Gets the value of the transaction property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the transaction property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTransaction().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TransactionFullType }
         * 
         * 
         */
        public List<TransactionFullType> getTransaction() {
            if (transaction == null) {
                transaction = new ArrayList<TransactionFullType>();
            }
            return this.transaction;
        }

    }

}
