
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
 * <p>Java class for refundAuthorisationFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refundAuthorisationFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="is-success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="refund-transaction-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refund-enlargement-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="reason-code" type="{http://localhost:8080/decoupling/schemas/common}reason-codeType"/>
 *         &lt;element name="transaction-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="error-description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refundAuthorisationFullType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "isSuccess",
    "refundTransactionId",
    "refundEnlargementDate",
    "reasonCode",
    "transactionId",
    "errorDescription",
    "errorId"
})
public class RefundAuthorisationFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "is-success")
    protected boolean isSuccess;
    @XmlElement(name = "refund-transaction-id")
    protected String refundTransactionId;
    @XmlElement(name = "refund-enlargement-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar refundEnlargementDate;
    @XmlElement(name = "reason-code", required = true)
    protected ReasonCodeType reasonCode;
    @XmlElement(name = "transaction-id", required = true)
    protected String transactionId;
    @XmlElement(name = "error-description")
    protected String errorDescription;
    @XmlElement(name = "error-id")
    protected String errorId;

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
     * Gets the value of the refundTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefundTransactionId() {
        return refundTransactionId;
    }

    /**
     * Sets the value of the refundTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefundTransactionId(String value) {
        this.refundTransactionId = value;
    }

    /**
     * Gets the value of the refundEnlargementDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getRefundEnlargementDate() {
        return refundEnlargementDate;
    }

    /**
     * Sets the value of the refundEnlargementDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefundEnlargementDate(Calendar value) {
        this.refundEnlargementDate = value;
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

}
