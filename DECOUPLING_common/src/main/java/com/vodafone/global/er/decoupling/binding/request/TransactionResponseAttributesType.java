
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionResponseAttributesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionResponseAttributesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="asset-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payment-information" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="affiliate-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-field1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-field2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-trans-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aggregator-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionResponseAttributesType", propOrder = {
    "assetId",
    "contentName",
    "paymentInformation",
    "affiliateId",
    "externalField1",
    "externalField2",
    "externalTransId",
    "partnerId",
    "aggregatorId"
})
public class TransactionResponseAttributesType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "asset-id")
    protected String assetId;
    @XmlElement(name = "content-name")
    protected String contentName;
    @XmlElement(name = "payment-information")
    protected String paymentInformation;
    @XmlElement(name = "affiliate-id")
    protected String affiliateId;
    @XmlElement(name = "external-field1")
    protected String externalField1;
    @XmlElement(name = "external-field2")
    protected String externalField2;
    @XmlElement(name = "external-trans-id")
    protected String externalTransId;
    @XmlElement(name = "partner-id")
    protected String partnerId;
    @XmlElement(name = "aggregator-id")
    protected String aggregatorId;

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
     * Gets the value of the paymentInformation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentInformation() {
        return paymentInformation;
    }

    /**
     * Sets the value of the paymentInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentInformation(String value) {
        this.paymentInformation = value;
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

}
