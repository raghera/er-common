
package com.vodafone.global.er.decoupling.binding.response.v2;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for modify-transaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modify-transaction">
 *   &lt;complexContent>
 *     &lt;extension base="{http://localhost:8080/decoupling/schemas/v2/common}transaction">
 *       &lt;sequence>
 *         &lt;element name="account-id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="sub-id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="old-value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="new-value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="csr-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modify-transaction", propOrder = {
    "accountId",
    "subId",
    "timestamp",
    "reason",
    "oldValue",
    "newValue",
    "csrId",
    "partnerId"
})
public class ModifyTransaction
    extends Transaction
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "account-id")
    protected Long accountId;
    @XmlElement(name = "sub-id")
    protected Long subId;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar timestamp;
    protected String reason;
    @XmlElement(name = "old-value")
    protected String oldValue;
    @XmlElement(name = "new-value")
    protected String newValue;
    @XmlElement(name = "csr-id")
    protected String csrId;
    @XmlElement(name = "partner-id")
    protected String partnerId;

    /**
     * Gets the value of the accountId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getAccountId() {
        if (accountId == null) {
            return  0L;
        } else {
            return accountId;
        }
    }

    /**
     * Sets the value of the accountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAccountId(Long value) {
        this.accountId = value;
    }

    /**
     * Gets the value of the subId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getSubId() {
        if (subId == null) {
            return  0L;
        } else {
            return subId;
        }
    }

    /**
     * Sets the value of the subId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubId(Long value) {
        this.subId = value;
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
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the oldValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldValue() {
        return oldValue;
    }

    /**
     * Sets the value of the oldValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldValue(String value) {
        this.oldValue = value;
    }

    /**
     * Gets the value of the newValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * Sets the value of the newValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewValue(String value) {
        this.newValue = value;
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

}
