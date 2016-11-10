
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for purchase-tariff-authorisationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="purchase-tariff-authorisationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="is-success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="package-subscription-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transaction-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reason-code" type="{http://localhost:8080/decoupling/schemas/common}reason-codeType"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "purchase-tariff-authorisationType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "isSuccess",
    "packageSubscriptionId",
    "transactionId",
    "reasonCode",
    "rate"
})
public class PurchaseTariffAuthorisationType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "is-success")
    protected boolean isSuccess;
    @XmlElement(name = "package-subscription-id", required = true)
    protected String packageSubscriptionId;
    @XmlElement(name = "transaction-id", required = true)
    protected String transactionId;
    @XmlElement(name = "reason-code", required = true)
    protected ReasonCodeType reasonCode;
    protected double rate;

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
     * Gets the value of the reasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link ReasonCodeType }
     *     
     */
    public ReasonCodeType getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReasonCodeType }
     *     
     */
    public void setReasonCode(ReasonCodeType value) {
        this.reasonCode = value;
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

}
