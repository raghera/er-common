//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 04:57:40 PM BST 
//


package com.vodafone.global.er.decoupling.binding.response;


/**
 * Java content class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch/working/DECOUPLING_common/schemas/response/response.xsd line 313)
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="is-reserved-only" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="interactive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="package" type="{http://localhost:8080/decoupling/schemas/common}packageType"/>
 *         &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}price-pointType"/>
 *         &lt;element name="service-price-point" type="{http://localhost:8080/decoupling/schemas/common}price-pointType"/>
 *         &lt;element name="user-resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resource-BalanceType"/>
 *         &lt;element name="package-subscription-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reason-code" type="{http://localhost:8080/decoupling/schemas/common}reason-codeType"/>
 *         &lt;element name="sub-reason-code" type="{http://localhost:8080/decoupling/schemas/common}reason-codeType"/>
 *         &lt;element name="receipting-credit-balance-impact" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="receipting-usage-type-attribute" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="transaction-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is-successful-express-purchase" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="drm-object" type="{http://localhost:8080/decoupling/schemas/common}drm-objectType"/>
 *         &lt;element name="error-description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payment-status" type="{http://localhost:8080/decoupling/schemas/common}paymentStatusType" minOccurs="0"/>
 *         &lt;element name="re-issue" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface UsageAuthorisationType {


    /**
     * Gets the value of the servicePricePoint property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PricePointType}
     */
    com.vodafone.global.er.decoupling.binding.response.PricePointType getServicePricePoint();

    /**
     * Sets the value of the servicePricePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PricePointType}
     */
    void setServicePricePoint(com.vodafone.global.er.decoupling.binding.response.PricePointType value);

    /**
     * Gets the value of the pricePoint property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PricePointType}
     */
    com.vodafone.global.er.decoupling.binding.response.PricePointType getPricePoint();

    /**
     * Sets the value of the pricePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PricePointType}
     */
    void setPricePoint(com.vodafone.global.er.decoupling.binding.response.PricePointType value);

    /**
     * Gets the value of the isSuccess property.
     * 
     */
    boolean isIsSuccess();

    /**
     * Sets the value of the isSuccess property.
     * 
     */
    void setIsSuccess(boolean value);

    /**
     * Gets the value of the interactive property.
     * 
     */
    boolean isInteractive();

    /**
     * Sets the value of the interactive property.
     * 
     */
    void setInteractive(boolean value);

    /**
     * Gets the value of the receiptingCreditBalanceImpact property.
     * 
     */
    double getReceiptingCreditBalanceImpact();

    /**
     * Sets the value of the receiptingCreditBalanceImpact property.
     * 
     */
    void setReceiptingCreditBalanceImpact(double value);

    /**
     * Gets the value of the reasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.ReasonCodeType}
     */
    com.vodafone.global.er.decoupling.binding.response.ReasonCodeType getReasonCode();

    /**
     * Sets the value of the reasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.ReasonCodeType}
     */
    void setReasonCode(com.vodafone.global.er.decoupling.binding.response.ReasonCodeType value);

    /**
     * Gets the value of the errorId property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getErrorId();

    /**
     * Sets the value of the errorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setErrorId(java.lang.String value);

    /**
     * Gets the value of the receiptingUsageTypeAttribute property.
     * 
     */
    int getReceiptingUsageTypeAttribute();

    /**
     * Sets the value of the receiptingUsageTypeAttribute property.
     * 
     */
    void setReceiptingUsageTypeAttribute(int value);

    /**
     * Gets the value of the paymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PaymentStatusType}
     */
    com.vodafone.global.er.decoupling.binding.response.PaymentStatusType getPaymentStatus();

    /**
     * Sets the value of the paymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PaymentStatusType}
     */
    void setPaymentStatus(com.vodafone.global.er.decoupling.binding.response.PaymentStatusType value);

    /**
     * Gets the value of the subReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.ReasonCodeType}
     */
    com.vodafone.global.er.decoupling.binding.response.ReasonCodeType getSubReasonCode();

    /**
     * Sets the value of the subReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.ReasonCodeType}
     */
    void setSubReasonCode(com.vodafone.global.er.decoupling.binding.response.ReasonCodeType value);

    /**
     * Gets the value of the reIssue property.
     * 
     */
    int getReIssue();

    /**
     * Sets the value of the reIssue property.
     * 
     */
    void setReIssue(int value);

    /**
     * Gets the value of the packageSubscriptionId property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getPackageSubscriptionId();

    /**
     * Sets the value of the packageSubscriptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setPackageSubscriptionId(java.lang.String value);

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getTransactionId();

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setTransactionId(java.lang.String value);

    /**
     * Gets the value of the drmObject property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.DrmObjectType}
     */
    com.vodafone.global.er.decoupling.binding.response.DrmObjectType getDrmObject();

    /**
     * Sets the value of the drmObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.DrmObjectType}
     */
    void setDrmObject(com.vodafone.global.er.decoupling.binding.response.DrmObjectType value);

    /**
     * Gets the value of the isReservedOnly property.
     * 
     */
    boolean isIsReservedOnly();

    /**
     * Sets the value of the isReservedOnly property.
     * 
     */
    void setIsReservedOnly(boolean value);

    /**
     * Gets the value of the errorDescription property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getErrorDescription();

    /**
     * Sets the value of the errorDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setErrorDescription(java.lang.String value);

    /**
     * Gets the value of the isSuccessfulExpressPurchase property.
     * 
     */
    boolean isIsSuccessfulExpressPurchase();

    /**
     * Sets the value of the isSuccessfulExpressPurchase property.
     * 
     */
    void setIsSuccessfulExpressPurchase(boolean value);

    /**
     * Gets the value of the userResourceBalance property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.ResourceBalanceType}
     */
    com.vodafone.global.er.decoupling.binding.response.ResourceBalanceType getUserResourceBalance();

    /**
     * Sets the value of the userResourceBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.ResourceBalanceType}
     */
    void setUserResourceBalance(com.vodafone.global.er.decoupling.binding.response.ResourceBalanceType value);

    /**
     * Gets the value of the package property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PackageType}
     */
    com.vodafone.global.er.decoupling.binding.response.PackageType getPackage();

    /**
     * Sets the value of the package property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PackageType}
     */
    void setPackage(com.vodafone.global.er.decoupling.binding.response.PackageType value);

    /**
     * Gets the value of the rate property.
     * 
     */
    double getRate();

    /**
     * Sets the value of the rate property.
     * 
     */
    void setRate(double value);

}