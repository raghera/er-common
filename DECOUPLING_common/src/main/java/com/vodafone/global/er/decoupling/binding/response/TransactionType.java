
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
 * <p>Java class for transactionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="purchase-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="purchase-rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="purchase-currency" type="{http://localhost:8080/decoupling/schemas/common}charging-resourceType"/>
 *         &lt;element name="created-by-super-credit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="auth-code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="csr-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subscription" type="{http://localhost:8080/decoupling/schemas/common}subscriptionType" minOccurs="0"/>
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
 *         &lt;element name="transaction-response-attributes" type="{http://localhost:8080/decoupling/schemas/common}TransactionResponseAttributesType" minOccurs="0"/>
 *         &lt;element name="purchase-rate-gross" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
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
    "transactionResponseAttributes",
    "purchaseRateGross"
})
public class TransactionType
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
    protected ChargingResourceType purchaseCurrency;
    @XmlElement(name = "created-by-super-credit")
    protected boolean createdBySuperCredit;
    @XmlElement(name = "auth-code", required = true)
    protected String authCode;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(name = "csr-id", required = true)
    protected String csrId;
    protected SubscriptionType subscription;
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
    @XmlElement(name = "transaction-response-attributes")
    protected TransactionResponseAttributesType transactionResponseAttributes;
    @XmlElement(name = "purchase-rate-gross")
    protected Double purchaseRateGross;

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
     *     {@link ChargingResourceType }
     *     
     */
    public ChargingResourceType getPurchaseCurrency() {
        return purchaseCurrency;
    }

    /**
     * Sets the value of the purchaseCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingResourceType }
     *     
     */
    public void setPurchaseCurrency(ChargingResourceType value) {
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
     *     {@link SubscriptionType }
     *     
     */
    public SubscriptionType getSubscription() {
        return subscription;
    }

    /**
     * Sets the value of the subscription property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionType }
     *     
     */
    public void setSubscription(SubscriptionType value) {
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
     * Gets the value of the transactionResponseAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionResponseAttributesType }
     *     
     */
    public TransactionResponseAttributesType getTransactionResponseAttributes() {
        return transactionResponseAttributes;
    }

    /**
     * Sets the value of the transactionResponseAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionResponseAttributesType }
     *     
     */
    public void setTransactionResponseAttributes(TransactionResponseAttributesType value) {
        this.transactionResponseAttributes = value;
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

}
