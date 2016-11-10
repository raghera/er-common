
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
 * <p>Java class for transactionLiteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transactionLiteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="purchase-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="purchase-rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="purchase-currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transaction-type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="device-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transaction-status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="content-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="merchant-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="asset-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionLiteType", propOrder = {
    "id",
    "purchaseDate",
    "purchaseRate",
    "purchaseCurrency",
    "transactionType",
    "deviceId",
    "transactionStatus",
    "contentName",
    "merchantName",
    "assetId"
})
public class TransactionLiteType
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
    protected String purchaseCurrency;
    @XmlElement(name = "transaction-type", required = true)
    protected String transactionType;
    @XmlElement(name = "device-id")
    protected String deviceId;
    @XmlElement(name = "transaction-status", required = true)
    protected String transactionStatus;
    @XmlElement(name = "content-name")
    protected String contentName;
    @XmlElement(name = "merchant-name")
    protected String merchantName;
    @XmlElement(name = "asset-id")
    protected String assetId;

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
     *     {@link String }
     *     
     */
    public String getPurchaseCurrency() {
        return purchaseCurrency;
    }

    /**
     * Sets the value of the purchaseCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseCurrency(String value) {
        this.purchaseCurrency = value;
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
     * Gets the value of the transactionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionStatus() {
        return transactionStatus;
    }

    /**
     * Sets the value of the transactionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionStatus(String value) {
        this.transactionStatus = value;
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
     * Gets the value of the merchantName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * Sets the value of the merchantName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantName(String value) {
        this.merchantName = value;
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

}
