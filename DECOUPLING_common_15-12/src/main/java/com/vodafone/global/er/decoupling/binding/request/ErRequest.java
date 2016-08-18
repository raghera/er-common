
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
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
 *       &lt;sequence>
 *         &lt;element name="payload" type="{http://localhost:8080/decoupling/schemas/common}payloadType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="client-application-id" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="purchase_locale" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="language_locale">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="client-domain">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="domain-translator-context">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="version">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "payload"
})
@XmlRootElement(name = "er-request", namespace = "")
public class ErRequest
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected PayloadType payload;
    @XmlAttribute(name = "id", required = true)
    protected int id;
    @XmlAttribute(name = "client-application-id", required = true)
    protected String clientApplicationId;
    @XmlAttribute(name = "purchase_locale", required = true)
    protected String purchaseLocale;
    @XmlAttribute(name = "language_locale")
    protected String languageLocale;
    @XmlAttribute(name = "client-domain")
    protected String clientDomain;
    @XmlAttribute(name = "domain-translator-context")
    protected String domainTranslatorContext;
    @XmlAttribute(name = "version")
    @XmlJavaTypeAdapter(Adapter2 .class)
    protected Integer version;

    /**
     * Gets the value of the payload property.
     * 
     * @return
     *     possible object is
     *     {@link PayloadType }
     *     
     */
    public PayloadType getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link PayloadType }
     *     
     */
    public void setPayload(PayloadType value) {
        this.payload = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the clientApplicationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientApplicationId() {
        return clientApplicationId;
    }

    /**
     * Sets the value of the clientApplicationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientApplicationId(String value) {
        this.clientApplicationId = value;
    }

    /**
     * Gets the value of the purchaseLocale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseLocale() {
        return purchaseLocale;
    }

    /**
     * Sets the value of the purchaseLocale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseLocale(String value) {
        this.purchaseLocale = value;
    }

    /**
     * Gets the value of the languageLocale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageLocale() {
        return languageLocale;
    }

    /**
     * Sets the value of the languageLocale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageLocale(String value) {
        this.languageLocale = value;
    }

    /**
     * Gets the value of the clientDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientDomain() {
        return clientDomain;
    }

    /**
     * Sets the value of the clientDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientDomain(String value) {
        this.clientDomain = value;
    }

    /**
     * Gets the value of the domainTranslatorContext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainTranslatorContext() {
        return domainTranslatorContext;
    }

    /**
     * Sets the value of the domainTranslatorContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainTranslatorContext(String value) {
        this.domainTranslatorContext = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(Integer value) {
        this.version = value;
    }

}
