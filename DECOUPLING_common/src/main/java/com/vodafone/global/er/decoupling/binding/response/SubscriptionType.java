
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
 * <p>Java class for subscriptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="package" type="{http://localhost:8080/decoupling/schemas/common}packageType"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="purchase-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="expiry-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="has-expired" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="resource-balances">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resource-BalanceType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="is-recurring" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="translated-status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rating-attributes" type="{http://localhost:8080/decoupling/schemas/common}ratingAttributesType"/>
 *         &lt;element name="final-min-subscription-end-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="current-number-of-occurences" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="min-subscription-period-elapsed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-preordered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="transactions" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="transaction" type="{http://localhost:8080/decoupling/schemas/common}transactionType" maxOccurs="unbounded" minOccurs="0"/>
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
 *                   &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resource-BalanceType" maxOccurs="unbounded" minOccurs="0"/>
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
 *         &lt;element name="b2b-partner" type="{http://localhost:8080/decoupling/schemas/common}b2bPartnerType" minOccurs="0"/>
 *         &lt;element name="parent-package-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscriptionType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "_package",
    "id",
    "startDate",
    "purchaseDate",
    "expiryDate",
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
    "b2BPartner",
    "parentPackageId"
})
public class SubscriptionType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "package", required = true)
    protected PackageType _package;
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
    @XmlElement(name = "has-expired")
    protected boolean hasExpired;
    @XmlElement(name = "resource-balances", required = true)
    protected SubscriptionType.ResourceBalances resourceBalances;
    @XmlElement(name = "is-recurring")
    protected boolean isRecurring;
    protected int status;
    @XmlElement(name = "translated-status", required = true)
    protected String translatedStatus;
    @XmlElement(name = "rating-attributes", required = true)
    protected RatingAttributesType ratingAttributes;
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
    protected SubscriptionType.Transactions transactions;
    @XmlElement(name = "super-credit-id", required = true)
    protected String superCreditId;
    @XmlElement(name = "translated-super-credit-name", required = true)
    protected String translatedSuperCreditName;
    @XmlElement(name = "super-credit-balances")
    protected SubscriptionType.SuperCreditBalances superCreditBalances;
    @XmlElement(name = "overdue-expiry-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar overdueExpiryDate;
    @XmlElement(name = "has-valid-micro-service")
    protected boolean hasValidMicroService;
    protected SubscriptionType.Services services;
    @XmlElement(name = "disallow-cancellations")
    protected Boolean disallowCancellations;
    @XmlElement(name = "b2b-partner")
    protected B2BPartnerType b2BPartner;
    @XmlElement(name = "parent-package-id")
    protected String parentPackageId;

    /**
     * Gets the value of the package property.
     * 
     * @return
     *     possible object is
     *     {@link PackageType }
     *     
     */
    public PackageType getPackage() {
        return _package;
    }

    /**
     * Sets the value of the package property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageType }
     *     
     */
    public void setPackage(PackageType value) {
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
     *     {@link SubscriptionType.ResourceBalances }
     *     
     */
    public SubscriptionType.ResourceBalances getResourceBalances() {
        return resourceBalances;
    }

    /**
     * Sets the value of the resourceBalances property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionType.ResourceBalances }
     *     
     */
    public void setResourceBalances(SubscriptionType.ResourceBalances value) {
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
     *     {@link RatingAttributesType }
     *     
     */
    public RatingAttributesType getRatingAttributes() {
        return ratingAttributes;
    }

    /**
     * Sets the value of the ratingAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RatingAttributesType }
     *     
     */
    public void setRatingAttributes(RatingAttributesType value) {
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
     *     {@link SubscriptionType.Transactions }
     *     
     */
    public SubscriptionType.Transactions getTransactions() {
        return transactions;
    }

    /**
     * Sets the value of the transactions property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionType.Transactions }
     *     
     */
    public void setTransactions(SubscriptionType.Transactions value) {
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
     *     {@link SubscriptionType.SuperCreditBalances }
     *     
     */
    public SubscriptionType.SuperCreditBalances getSuperCreditBalances() {
        return superCreditBalances;
    }

    /**
     * Sets the value of the superCreditBalances property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionType.SuperCreditBalances }
     *     
     */
    public void setSuperCreditBalances(SubscriptionType.SuperCreditBalances value) {
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
     *     {@link SubscriptionType.Services }
     *     
     */
    public SubscriptionType.Services getServices() {
        return services;
    }

    /**
     * Sets the value of the services property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionType.Services }
     *     
     */
    public void setServices(SubscriptionType.Services value) {
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
     * Gets the value of the b2BPartner property.
     * 
     * @return
     *     possible object is
     *     {@link B2BPartnerType }
     *     
     */
    public B2BPartnerType getB2BPartner() {
        return b2BPartner;
    }

    /**
     * Sets the value of the b2BPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link B2BPartnerType }
     *     
     */
    public void setB2BPartner(B2BPartnerType value) {
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resource-BalanceType" maxOccurs="unbounded" minOccurs="0"/>
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
        protected List<ResourceBalanceType> resourceBalance;

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
         * {@link ResourceBalanceType }
         * 
         * 
         */
        public List<ResourceBalanceType> getResourceBalance() {
            if (resourceBalance == null) {
                resourceBalance = new ArrayList<ResourceBalanceType>();
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
     *         &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resource-BalanceType" maxOccurs="unbounded" minOccurs="0"/>
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
        protected List<ResourceBalanceType> resourceBalance;

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
         * {@link ResourceBalanceType }
         * 
         * 
         */
        public List<ResourceBalanceType> getResourceBalance() {
            if (resourceBalance == null) {
                resourceBalance = new ArrayList<ResourceBalanceType>();
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
     *         &lt;element name="transaction" type="{http://localhost:8080/decoupling/schemas/common}transactionType" maxOccurs="unbounded" minOccurs="0"/>
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
        protected List<TransactionType> transaction;

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
         * {@link TransactionType }
         * 
         * 
         */
        public List<TransactionType> getTransaction() {
            if (transaction == null) {
                transaction = new ArrayList<TransactionType>();
            }
            return this.transaction;
        }

    }

}
