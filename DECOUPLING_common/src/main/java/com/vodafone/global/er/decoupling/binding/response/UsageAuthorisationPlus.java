
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="is-reserved-only" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="interactive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="package" type="{http://localhost:8080/decoupling/schemas/common}catalogPackageFullType"/>
 *         &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}pricePointFullType"/>
 *         &lt;element name="service-price-point" type="{http://localhost:8080/decoupling/schemas/common}pricePointFullType"/>
 *         &lt;element name="user-resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resourceBalanceFullType"/>
 *         &lt;element name="package-subscription-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reason-code" type="{http://localhost:8080/decoupling/schemas/common}reasonCodeFullType"/>
 *         &lt;element name="sub-reason-code" type="{http://localhost:8080/decoupling/schemas/common}reasonCodeFullType"/>
 *         &lt;element name="receipting-credit-balance-impact" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="receipting-usage-type-attribute" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="transaction-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is-successful-express-purchase" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="drm-object" type="{http://localhost:8080/decoupling/schemas/common}drmObjectFullType"/>
 *         &lt;element name="error-description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payment-status" type="{http://localhost:8080/decoupling/schemas/common}paymentStatusFullType" minOccurs="0"/>
 *         &lt;element name="re-issue" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "isReservedOnly",
    "interactive",
    "isSuccess",
    "_package",
    "pricePoint",
    "servicePricePoint",
    "userResourceBalance",
    "packageSubscriptionId",
    "reasonCode",
    "subReasonCode",
    "receiptingCreditBalanceImpact",
    "receiptingUsageTypeAttribute",
    "transactionId",
    "isSuccessfulExpressPurchase",
    "rate",
    "drmObject",
    "errorDescription",
    "errorId",
    "paymentStatus",
    "reIssue"
})
@XmlRootElement(name = "usage-authorisation-plus")
public class UsageAuthorisationPlus
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "is-reserved-only")
    protected boolean isReservedOnly;
    protected boolean interactive;
    @XmlElement(name = "is-success")
    protected boolean isSuccess;
    @XmlElement(name = "package", required = true)
    protected CatalogPackageFullType _package;
    @XmlElement(name = "price-point", required = true)
    protected PricePointFullType pricePoint;
    @XmlElement(name = "service-price-point", required = true)
    protected PricePointFullType servicePricePoint;
    @XmlElement(name = "user-resource-balance", required = true)
    protected ResourceBalanceFullType userResourceBalance;
    @XmlElement(name = "package-subscription-id", required = true)
    protected String packageSubscriptionId;
    @XmlElement(name = "reason-code", required = true)
    protected ReasonCodeFullType reasonCode;
    @XmlElement(name = "sub-reason-code", required = true)
    protected ReasonCodeFullType subReasonCode;
    @XmlElement(name = "receipting-credit-balance-impact")
    protected double receiptingCreditBalanceImpact;
    @XmlElement(name = "receipting-usage-type-attribute")
    protected int receiptingUsageTypeAttribute;
    @XmlElement(name = "transaction-id", required = true)
    protected String transactionId;
    @XmlElement(name = "is-successful-express-purchase")
    protected boolean isSuccessfulExpressPurchase;
    protected double rate;
    @XmlElement(name = "drm-object", required = true)
    protected DrmObjectFullType drmObject;
    @XmlElement(name = "error-description")
    protected String errorDescription;
    @XmlElement(name = "error-id")
    protected String errorId;
    @XmlElement(name = "payment-status")
    protected PaymentStatusFullType paymentStatus;
    @XmlElement(name = "re-issue")
    protected Integer reIssue;

    /**
     * Gets the value of the isReservedOnly property.
     * 
     */
    public boolean isIsReservedOnly() {
        return isReservedOnly;
    }

    /**
     * Sets the value of the isReservedOnly property.
     * 
     */
    public void setIsReservedOnly(boolean value) {
        this.isReservedOnly = value;
    }

    /**
     * Gets the value of the interactive property.
     * 
     */
    public boolean isInteractive() {
        return interactive;
    }

    /**
     * Sets the value of the interactive property.
     * 
     */
    public void setInteractive(boolean value) {
        this.interactive = value;
    }

    /**
     * Gets the value of the isSuccess property.
     * 
     */
    public boolean isIsSuccess() {
        return isSuccess;
    }

    /**
     * Sets the value of the isSuccess property.
     * 
     */
    public void setIsSuccess(boolean value) {
        this.isSuccess = value;
    }

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
     * Gets the value of the servicePricePoint property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointFullType }
     *     
     */
    public PricePointFullType getServicePricePoint() {
        return servicePricePoint;
    }

    /**
     * Sets the value of the servicePricePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointFullType }
     *     
     */
    public void setServicePricePoint(PricePointFullType value) {
        this.servicePricePoint = value;
    }

    /**
     * Gets the value of the userResourceBalance property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceBalanceFullType }
     *     
     */
    public ResourceBalanceFullType getUserResourceBalance() {
        return userResourceBalance;
    }

    /**
     * Sets the value of the userResourceBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceBalanceFullType }
     *     
     */
    public void setUserResourceBalance(ResourceBalanceFullType value) {
        this.userResourceBalance = value;
    }

    /**
     * Gets the value of the packageSubscriptionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageSubscriptionId() {
        return packageSubscriptionId;
    }

    /**
     * Sets the value of the packageSubscriptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageSubscriptionId(String value) {
        this.packageSubscriptionId = value;
    }

    /**
     * Gets the value of the reasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link ReasonCodeFullType }
     *     
     */
    public ReasonCodeFullType getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReasonCodeFullType }
     *     
     */
    public void setReasonCode(ReasonCodeFullType value) {
        this.reasonCode = value;
    }

    /**
     * Gets the value of the subReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link ReasonCodeFullType }
     *     
     */
    public ReasonCodeFullType getSubReasonCode() {
        return subReasonCode;
    }

    /**
     * Sets the value of the subReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReasonCodeFullType }
     *     
     */
    public void setSubReasonCode(ReasonCodeFullType value) {
        this.subReasonCode = value;
    }

    /**
     * Gets the value of the receiptingCreditBalanceImpact property.
     * 
     */
    public double getReceiptingCreditBalanceImpact() {
        return receiptingCreditBalanceImpact;
    }

    /**
     * Sets the value of the receiptingCreditBalanceImpact property.
     * 
     */
    public void setReceiptingCreditBalanceImpact(double value) {
        this.receiptingCreditBalanceImpact = value;
    }

    /**
     * Gets the value of the receiptingUsageTypeAttribute property.
     * 
     */
    public int getReceiptingUsageTypeAttribute() {
        return receiptingUsageTypeAttribute;
    }

    /**
     * Sets the value of the receiptingUsageTypeAttribute property.
     * 
     */
    public void setReceiptingUsageTypeAttribute(int value) {
        this.receiptingUsageTypeAttribute = value;
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
     * Gets the value of the isSuccessfulExpressPurchase property.
     * 
     */
    public boolean isIsSuccessfulExpressPurchase() {
        return isSuccessfulExpressPurchase;
    }

    /**
     * Sets the value of the isSuccessfulExpressPurchase property.
     * 
     */
    public void setIsSuccessfulExpressPurchase(boolean value) {
        this.isSuccessfulExpressPurchase = value;
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
     * Gets the value of the paymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentStatusFullType }
     *     
     */
    public PaymentStatusFullType getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the value of the paymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentStatusFullType }
     *     
     */
    public void setPaymentStatus(PaymentStatusFullType value) {
        this.paymentStatus = value;
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

}
