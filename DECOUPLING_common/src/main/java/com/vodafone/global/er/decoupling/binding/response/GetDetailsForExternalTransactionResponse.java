
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="transaction-id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *           &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *           &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *           &lt;element name="charging-resource" type="{http://localhost:8080/decoupling/schemas/common}simple-charging-resourceType"/>
 *           &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;/sequence>
 *         &lt;element name="reason-code" type="{http://localhost:8080/decoupling/schemas/common}reason-codeType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "msisdn",
    "transactionId",
    "timestamp",
    "amount",
    "chargingResource",
    "status",
    "subscriptionId",
    "reasonCode"
})
@XmlRootElement(name = "get-details-for-external-transaction-response")
public class GetDetailsForExternalTransactionResponse
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected String msisdn;
    @XmlElement(name = "transaction-id")
    protected Long transactionId;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar timestamp;
    protected Double amount;
    @XmlElement(name = "charging-resource")
    protected SimpleChargingResourceType chargingResource;
    protected Integer status;
    @XmlElement(name = "subscription-id")
    protected Long subscriptionId;
    @XmlElement(name = "reason-code")
    protected ReasonCodeType reasonCode;

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdn(String value) {
        this.msisdn = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getTransactionId() {
        if (transactionId == null) {
            return  0L;
        } else {
            return transactionId;
        }
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTransactionId(Long value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestamp(Calendar value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getAmount() {
        if (amount == null) {
            return  0.0D;
        } else {
            return amount;
        }
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmount(Double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the chargingResource property.
     * 
     * @return
     *     possible object is
     *     {@link SimpleChargingResourceType }
     *     
     */
    public SimpleChargingResourceType getChargingResource() {
        return chargingResource;
    }

    /**
     * Sets the value of the chargingResource property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleChargingResourceType }
     *     
     */
    public void setChargingResource(SimpleChargingResourceType value) {
        this.chargingResource = value;
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
     * Gets the value of the subscriptionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getSubscriptionId() {
        if (subscriptionId == null) {
            return  0L;
        } else {
            return subscriptionId;
        }
    }

    /**
     * Sets the value of the subscriptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubscriptionId(Long value) {
        this.subscriptionId = value;
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

}
