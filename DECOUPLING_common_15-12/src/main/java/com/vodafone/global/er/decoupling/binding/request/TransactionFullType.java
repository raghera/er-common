
package com.vodafone.global.er.decoupling.binding.request;

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
 * <p>Java class for transactionFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transactionFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="purchase-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="purchase-rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="purchase-currency" type="{http://localhost:8080/decoupling/schemas/common}chargingResourceFullType"/>
 *         &lt;element name="created-by-super-credit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="auth-code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="csr-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subscription" type="{http://localhost:8080/decoupling/schemas/common}subscriptionFullType" minOccurs="0"/>
 *         &lt;element name="super-credit-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="translated-super-credit-name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roaming-tax-amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="roaming-amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="roaming-type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="network-code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="is-package-payment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-new-package-payment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-payment-content" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-recurring-payment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-renewal-payment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-credit-refund-transaction" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-dunning-transaction" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-modification" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-refund-cash" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-refund-discount" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-refund-enlargement" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-refund-noncash" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-Subscription" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="local-date-time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="payment-status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="payment-type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="standard-rate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="session-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parent-transaction-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drm-object" type="{http://localhost:8080/decoupling/schemas/common}drmObjectFullType" minOccurs="0"/>
 *         &lt;element name="recipient-msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content-description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aggregator-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="asset-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bearer-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="matching-attributes" type="{http://localhost:8080/decoupling/schemas/common}ratingAttributesFullType" minOccurs="0"/>
 *         &lt;element name="re-issue" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="purchase-channel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refund-reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refund-payment-transaction-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tax-rate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purchase-rate-gross" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="next-cycle-discount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="transaction-type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="device-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="client-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transaction-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transaction-id-long" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="access-channel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="client-network-code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-trans-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-field1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-field2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="affiliate-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="product-code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rate-identifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payment-info" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error-description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionFullType", propOrder = {
    "id",
    "purchaseDate",
    "purchaseRate",
    "purchaseCurrency",
    "createdBySuperCredit",
    "authCode",
    "description",
    "csrId",
    "subscription",
    "superCreditId",
    "translatedSuperCreditName",
    "roamingTaxAmount",
    "roamingAmount",
    "roamingType",
    "networkCode",
    "isPackagePayment",
    "isNewPackagePayment",
    "isPaymentContent",
    "isRecurringPayment",
    "isRenewalPayment",
    "isCreditRefundTransaction",
    "isDunningTransaction",
    "isModification",
    "isRefundCash",
    "isRefundDiscount",
    "isRefundEnlargement",
    "isRefundNoncash",
    "isSubscription",
    "localDateTime",
    "paymentStatus",
    "paymentType",
    "standardRate",
    "status",
    "sessionId",
    "parentTransactionId",
    "drmObject",
    "recipientMsisdn",
    "contentDescription",
    "aggregatorId",
    "partnerId",
    "contentName",
    "assetId",
    "bearerId",
    "matchingAttributes",
    "reIssue",
    "purchaseChannel",
    "refundReason",
    "refundPaymentTransactionId",
    "taxRate",
    "serviceId",
    "purchaseRateGross",
    "nextCycleDiscount",
    "transactionType",
    "deviceId",
    "clientId",
    "transactionId",
    "transactionIdLong",
    "accessChannel",
    "clientNetworkCode",
    "externalTransId",
    "externalField1",
    "externalField2",
    "affiliateId",
    "productCode",
    "rateIdentifier",
    "paymentInfo",
    "errorId",
    "errorDescription"
})
public class TransactionFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(name = "purchase-date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar purchaseDate;
    @XmlElement(name = "purchase-rate")
    protected double purchaseRate;
    @XmlElement(name = "purchase-currency", required = true)
    protected ChargingResourceFullType purchaseCurrency;
    @XmlElement(name = "created-by-super-credit")
    protected boolean createdBySuperCredit;
    @XmlElement(name = "auth-code", required = true)
    protected String authCode;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(name = "csr-id", required = true)
    protected String csrId;
    protected SubscriptionFullType subscription;
    @XmlElement(name = "super-credit-id", required = true)
    protected String superCreditId;
    @XmlElement(name = "translated-super-credit-name", required = true)
    protected String translatedSuperCreditName;
    @XmlElement(name = "roaming-tax-amount")
    protected Double roamingTaxAmount;
    @XmlElement(name = "roaming-amount")
    protected Double roamingAmount;
    @XmlElement(name = "roaming-type")
    protected String roamingType;
    @XmlElement(name = "network-code")
    protected String networkCode;
    @XmlElement(name = "is-package-payment")
    protected Boolean isPackagePayment;
    @XmlElement(name = "is-new-package-payment")
    protected Boolean isNewPackagePayment;
    @XmlElement(name = "is-payment-content")
    protected Boolean isPaymentContent;
    @XmlElement(name = "is-recurring-payment")
    protected Boolean isRecurringPayment;
    @XmlElement(name = "is-renewal-payment")
    protected Boolean isRenewalPayment;
    @XmlElement(name = "is-credit-refund-transaction")
    protected Boolean isCreditRefundTransaction;
    @XmlElement(name = "is-dunning-transaction")
    protected Boolean isDunningTransaction;
    @XmlElement(name = "is-modification")
    protected Boolean isModification;
    @XmlElement(name = "is-refund-cash")
    protected Boolean isRefundCash;
    @XmlElement(name = "is-refund-discount")
    protected Boolean isRefundDiscount;
    @XmlElement(name = "is-refund-enlargement")
    protected Boolean isRefundEnlargement;
    @XmlElement(name = "is-refund-noncash")
    protected Boolean isRefundNoncash;
    @XmlElement(name = "is-Subscription")
    protected Boolean isSubscription;
    @XmlElement(name = "local-date-time", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar localDateTime;
    @XmlElement(name = "payment-status")
    protected Integer paymentStatus;
    @XmlElement(name = "payment-type")
    protected Integer paymentType;
    @XmlElement(name = "standard-rate")
    protected Double standardRate;
    protected Integer status;
    @XmlElement(name = "session-id")
    protected String sessionId;
    @XmlElement(name = "parent-transaction-id")
    protected String parentTransactionId;
    @XmlElement(name = "drm-object")
    protected DrmObjectFullType drmObject;
    @XmlElement(name = "recipient-msisdn")
    protected String recipientMsisdn;
    @XmlElement(name = "content-description")
    protected String contentDescription;
    @XmlElement(name = "aggregator-id")
    protected String aggregatorId;
    @XmlElement(name = "partner-id")
    protected String partnerId;
    @XmlElement(name = "content-name")
    protected String contentName;
    @XmlElement(name = "asset-id")
    protected String assetId;
    @XmlElement(name = "bearer-id")
    protected String bearerId;
    @XmlElement(name = "matching-attributes")
    protected RatingAttributesFullType matchingAttributes;
    @XmlElement(name = "re-issue")
    protected Integer reIssue;
    @XmlElement(name = "purchase-channel")
    protected String purchaseChannel;
    @XmlElement(name = "refund-reason")
    protected String refundReason;
    @XmlElement(name = "refund-payment-transaction-id")
    protected String refundPaymentTransactionId;
    @XmlElement(name = "tax-rate")
    protected Double taxRate;
    @XmlElement(name = "service-id")
    protected String serviceId;
    @XmlElement(name = "purchase-rate-gross")
    protected Double purchaseRateGross;
    @XmlElement(name = "next-cycle-discount")
    protected Double nextCycleDiscount;
    @XmlElement(name = "transaction-type")
    protected String transactionType;
    @XmlElement(name = "device-id")
    protected String deviceId;
    @XmlElement(name = "client-id")
    protected String clientId;
    @XmlElement(name = "transaction-id")
    protected String transactionId;
    @XmlElement(name = "transaction-id-long")
    protected Long transactionIdLong;
    @XmlElement(name = "access-channel")
    protected String accessChannel;
    @XmlElement(name = "client-network-code")
    protected String clientNetworkCode;
    @XmlElement(name = "external-trans-id")
    protected String externalTransId;
    @XmlElement(name = "external-field1")
    protected String externalField1;
    @XmlElement(name = "external-field2")
    protected String externalField2;
    @XmlElement(name = "affiliate-id")
    protected String affiliateId;
    @XmlElement(name = "product-code")
    protected String productCode;
    @XmlElement(name = "rate-identifier")
    protected String rateIdentifier;
    @XmlElement(name = "payment-info")
    protected String paymentInfo;
    @XmlElement(name = "error-id")
    protected String errorId;
    @XmlElement(name = "error-description")
    protected String errorDescription;

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
     * Gets the value of the purchaseRate property.
     * 
     */
    public double getPurchaseRate() {
        return purchaseRate;
    }

    /**
     * Sets the value of the purchaseRate property.
     * 
     */
    public void setPurchaseRate(double value) {
        this.purchaseRate = value;
    }

    /**
     * Gets the value of the purchaseCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingResourceFullType }
     *     
     */
    public ChargingResourceFullType getPurchaseCurrency() {
        return purchaseCurrency;
    }

    /**
     * Sets the value of the purchaseCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingResourceFullType }
     *     
     */
    public void setPurchaseCurrency(ChargingResourceFullType value) {
        this.purchaseCurrency = value;
    }

    /**
     * Gets the value of the createdBySuperCredit property.
     * 
     */
    public boolean isCreatedBySuperCredit() {
        return createdBySuperCredit;
    }

    /**
     * Sets the value of the createdBySuperCredit property.
     * 
     */
    public void setCreatedBySuperCredit(boolean value) {
        this.createdBySuperCredit = value;
    }

    /**
     * Gets the value of the authCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * Sets the value of the authCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthCode(String value) {
        this.authCode = value;
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
     * Gets the value of the csrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsrId() {
        return csrId;
    }

    /**
     * Sets the value of the csrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsrId(String value) {
        this.csrId = value;
    }

    /**
     * Gets the value of the subscription property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionFullType }
     *     
     */
    public SubscriptionFullType getSubscription() {
        return subscription;
    }

    /**
     * Sets the value of the subscription property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionFullType }
     *     
     */
    public void setSubscription(SubscriptionFullType value) {
        this.subscription = value;
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
     * Gets the value of the roamingTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getRoamingTaxAmount() {
        if (roamingTaxAmount == null) {
            return  0.0D;
        } else {
            return roamingTaxAmount;
        }
    }

    /**
     * Sets the value of the roamingTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRoamingTaxAmount(Double value) {
        this.roamingTaxAmount = value;
    }

    /**
     * Gets the value of the roamingAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getRoamingAmount() {
        if (roamingAmount == null) {
            return  0.0D;
        } else {
            return roamingAmount;
        }
    }

    /**
     * Sets the value of the roamingAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRoamingAmount(Double value) {
        this.roamingAmount = value;
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
     * Gets the value of the networkCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkCode() {
        return networkCode;
    }

    /**
     * Sets the value of the networkCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkCode(String value) {
        this.networkCode = value;
    }

    /**
     * Gets the value of the isPackagePayment property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsPackagePayment() {
        if (isPackagePayment == null) {
            return false;
        } else {
            return isPackagePayment;
        }
    }

    /**
     * Sets the value of the isPackagePayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPackagePayment(Boolean value) {
        this.isPackagePayment = value;
    }

    /**
     * Gets the value of the isNewPackagePayment property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsNewPackagePayment() {
        if (isNewPackagePayment == null) {
            return false;
        } else {
            return isNewPackagePayment;
        }
    }

    /**
     * Sets the value of the isNewPackagePayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsNewPackagePayment(Boolean value) {
        this.isNewPackagePayment = value;
    }

    /**
     * Gets the value of the isPaymentContent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsPaymentContent() {
        if (isPaymentContent == null) {
            return false;
        } else {
            return isPaymentContent;
        }
    }

    /**
     * Sets the value of the isPaymentContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPaymentContent(Boolean value) {
        this.isPaymentContent = value;
    }

    /**
     * Gets the value of the isRecurringPayment property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsRecurringPayment() {
        if (isRecurringPayment == null) {
            return false;
        } else {
            return isRecurringPayment;
        }
    }

    /**
     * Sets the value of the isRecurringPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRecurringPayment(Boolean value) {
        this.isRecurringPayment = value;
    }

    /**
     * Gets the value of the isRenewalPayment property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsRenewalPayment() {
        if (isRenewalPayment == null) {
            return false;
        } else {
            return isRenewalPayment;
        }
    }

    /**
     * Sets the value of the isRenewalPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRenewalPayment(Boolean value) {
        this.isRenewalPayment = value;
    }

    /**
     * Gets the value of the isCreditRefundTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsCreditRefundTransaction() {
        if (isCreditRefundTransaction == null) {
            return false;
        } else {
            return isCreditRefundTransaction;
        }
    }

    /**
     * Sets the value of the isCreditRefundTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsCreditRefundTransaction(Boolean value) {
        this.isCreditRefundTransaction = value;
    }

    /**
     * Gets the value of the isDunningTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsDunningTransaction() {
        if (isDunningTransaction == null) {
            return false;
        } else {
            return isDunningTransaction;
        }
    }

    /**
     * Sets the value of the isDunningTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDunningTransaction(Boolean value) {
        this.isDunningTransaction = value;
    }

    /**
     * Gets the value of the isModification property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsModification() {
        if (isModification == null) {
            return false;
        } else {
            return isModification;
        }
    }

    /**
     * Sets the value of the isModification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsModification(Boolean value) {
        this.isModification = value;
    }

    /**
     * Gets the value of the isRefundCash property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsRefundCash() {
        if (isRefundCash == null) {
            return false;
        } else {
            return isRefundCash;
        }
    }

    /**
     * Sets the value of the isRefundCash property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRefundCash(Boolean value) {
        this.isRefundCash = value;
    }

    /**
     * Gets the value of the isRefundDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsRefundDiscount() {
        if (isRefundDiscount == null) {
            return false;
        } else {
            return isRefundDiscount;
        }
    }

    /**
     * Sets the value of the isRefundDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRefundDiscount(Boolean value) {
        this.isRefundDiscount = value;
    }

    /**
     * Gets the value of the isRefundEnlargement property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsRefundEnlargement() {
        if (isRefundEnlargement == null) {
            return false;
        } else {
            return isRefundEnlargement;
        }
    }

    /**
     * Sets the value of the isRefundEnlargement property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRefundEnlargement(Boolean value) {
        this.isRefundEnlargement = value;
    }

    /**
     * Gets the value of the isRefundNoncash property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsRefundNoncash() {
        if (isRefundNoncash == null) {
            return false;
        } else {
            return isRefundNoncash;
        }
    }

    /**
     * Sets the value of the isRefundNoncash property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRefundNoncash(Boolean value) {
        this.isRefundNoncash = value;
    }

    /**
     * Gets the value of the isSubscription property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsSubscription() {
        if (isSubscription == null) {
            return false;
        } else {
            return isSubscription;
        }
    }

    /**
     * Sets the value of the isSubscription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSubscription(Boolean value) {
        this.isSubscription = value;
    }

    /**
     * Gets the value of the localDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getLocalDateTime() {
        return localDateTime;
    }

    /**
     * Sets the value of the localDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalDateTime(Calendar value) {
        this.localDateTime = value;
    }

    /**
     * Gets the value of the paymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getPaymentStatus() {
        if (paymentStatus == null) {
            return  0;
        } else {
            return paymentStatus;
        }
    }

    /**
     * Sets the value of the paymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPaymentStatus(Integer value) {
        this.paymentStatus = value;
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
     * Gets the value of the standardRate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getStandardRate() {
        if (standardRate == null) {
            return  0.0D;
        } else {
            return standardRate;
        }
    }

    /**
     * Sets the value of the standardRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStandardRate(Double value) {
        this.standardRate = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getStatus() {
        if (status == null) {
            return  0;
        } else {
            return status;
        }
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the parentTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentTransactionId() {
        return parentTransactionId;
    }

    /**
     * Sets the value of the parentTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentTransactionId(String value) {
        this.parentTransactionId = value;
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
     * Gets the value of the recipientMsisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientMsisdn() {
        return recipientMsisdn;
    }

    /**
     * Sets the value of the recipientMsisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientMsisdn(String value) {
        this.recipientMsisdn = value;
    }

    /**
     * Gets the value of the contentDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentDescription() {
        return contentDescription;
    }

    /**
     * Sets the value of the contentDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentDescription(String value) {
        this.contentDescription = value;
    }

    /**
     * Gets the value of the aggregatorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAggregatorId() {
        return aggregatorId;
    }

    /**
     * Sets the value of the aggregatorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAggregatorId(String value) {
        this.aggregatorId = value;
    }

    /**
     * Gets the value of the partnerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerId(String value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the contentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentName() {
        return contentName;
    }

    /**
     * Sets the value of the contentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentName(String value) {
        this.contentName = value;
    }

    /**
     * Gets the value of the assetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * Sets the value of the assetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssetId(String value) {
        this.assetId = value;
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
     * Gets the value of the matchingAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link RatingAttributesFullType }
     *     
     */
    public RatingAttributesFullType getMatchingAttributes() {
        return matchingAttributes;
    }

    /**
     * Sets the value of the matchingAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RatingAttributesFullType }
     *     
     */
    public void setMatchingAttributes(RatingAttributesFullType value) {
        this.matchingAttributes = value;
    }

    /**
     * Gets the value of the reIssue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getReIssue() {
        if (reIssue == null) {
            return  0;
        } else {
            return reIssue;
        }
    }

    /**
     * Sets the value of the reIssue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReIssue(Integer value) {
        this.reIssue = value;
    }

    /**
     * Gets the value of the purchaseChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseChannel() {
        return purchaseChannel;
    }

    /**
     * Sets the value of the purchaseChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseChannel(String value) {
        this.purchaseChannel = value;
    }

    /**
     * Gets the value of the refundReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefundReason() {
        return refundReason;
    }

    /**
     * Sets the value of the refundReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefundReason(String value) {
        this.refundReason = value;
    }

    /**
     * Gets the value of the refundPaymentTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefundPaymentTransactionId() {
        return refundPaymentTransactionId;
    }

    /**
     * Sets the value of the refundPaymentTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefundPaymentTransactionId(String value) {
        this.refundPaymentTransactionId = value;
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

    /**
     * Gets the value of the purchaseRateGross property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getPurchaseRateGross() {
        if (purchaseRateGross == null) {
            return  0.0D;
        } else {
            return purchaseRateGross;
        }
    }

    /**
     * Sets the value of the purchaseRateGross property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPurchaseRateGross(Double value) {
        this.purchaseRateGross = value;
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
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionType(String value) {
        this.transactionType = value;
    }

    /**
     * Gets the value of the deviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the value of the deviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Gets the value of the clientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the value of the clientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientId(String value) {
        this.clientId = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the transactionIdLong property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getTransactionIdLong() {
        if (transactionIdLong == null) {
            return  0L;
        } else {
            return transactionIdLong;
        }
    }

    /**
     * Sets the value of the transactionIdLong property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTransactionIdLong(Long value) {
        this.transactionIdLong = value;
    }

    /**
     * Gets the value of the accessChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessChannel() {
        return accessChannel;
    }

    /**
     * Sets the value of the accessChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessChannel(String value) {
        this.accessChannel = value;
    }

    /**
     * Gets the value of the clientNetworkCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientNetworkCode() {
        return clientNetworkCode;
    }

    /**
     * Sets the value of the clientNetworkCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientNetworkCode(String value) {
        this.clientNetworkCode = value;
    }

    /**
     * Gets the value of the externalTransId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalTransId() {
        return externalTransId;
    }

    /**
     * Sets the value of the externalTransId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalTransId(String value) {
        this.externalTransId = value;
    }

    /**
     * Gets the value of the externalField1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalField1() {
        return externalField1;
    }

    /**
     * Sets the value of the externalField1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalField1(String value) {
        this.externalField1 = value;
    }

    /**
     * Gets the value of the externalField2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalField2() {
        return externalField2;
    }

    /**
     * Sets the value of the externalField2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalField2(String value) {
        this.externalField2 = value;
    }

    /**
     * Gets the value of the affiliateId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAffiliateId() {
        return affiliateId;
    }

    /**
     * Sets the value of the affiliateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAffiliateId(String value) {
        this.affiliateId = value;
    }

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

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
     * Gets the value of the paymentInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentInfo() {
        return paymentInfo;
    }

    /**
     * Sets the value of the paymentInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentInfo(String value) {
        this.paymentInfo = value;
    }

    /**
     * Gets the value of the errorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorId() {
        return errorId;
    }

    /**
     * Sets the value of the errorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorId(String value) {
        this.errorId = value;
    }

    /**
     * Gets the value of the errorDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * Sets the value of the errorDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDescription(String value) {
        this.errorDescription = value;
    }

}
