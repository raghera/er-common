
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for charging-resourceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="charging-resourceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="translated-resource-name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is-usage-token" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-pay-token" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-resource" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-currency" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="resource-name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resource-symbol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is-super-credit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "charging-resourceType", propOrder = {
    "name",
    "translatedResourceName",
    "code",
    "isUsageToken",
    "isPayToken",
    "isResource",
    "isCurrency",
    "resourceName",
    "resourceSymbol",
    "isSuperCredit"
})
public class ChargingResourceType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(name = "translated-resource-name", required = true)
    protected String translatedResourceName;
    @XmlElement(required = true)
    protected String code;
    @XmlElement(name = "is-usage-token")
    protected boolean isUsageToken;
    @XmlElement(name = "is-pay-token")
    protected boolean isPayToken;
    @XmlElement(name = "is-resource")
    protected boolean isResource;
    @XmlElement(name = "is-currency")
    protected boolean isCurrency;
    @XmlElement(name = "resource-name", required = true)
    protected String resourceName;
    @XmlElement(name = "resource-symbol", required = true)
    protected String resourceSymbol;
    @XmlElement(name = "is-super-credit")
    protected boolean isSuperCredit;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the translatedResourceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranslatedResourceName() {
        return translatedResourceName;
    }

    /**
     * Sets the value of the translatedResourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranslatedResourceName(String value) {
        this.translatedResourceName = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the isUsageToken property.
     * 
     */
    public boolean isIsUsageToken() {
        return isUsageToken;
    }

    /**
     * Sets the value of the isUsageToken property.
     * 
     */
    public void setIsUsageToken(boolean value) {
        this.isUsageToken = value;
    }

    /**
     * Gets the value of the isPayToken property.
     * 
     */
    public boolean isIsPayToken() {
        return isPayToken;
    }

    /**
     * Sets the value of the isPayToken property.
     * 
     */
    public void setIsPayToken(boolean value) {
        this.isPayToken = value;
    }

    /**
     * Gets the value of the isResource property.
     * 
     */
    public boolean isIsResource() {
        return isResource;
    }

    /**
     * Sets the value of the isResource property.
     * 
     */
    public void setIsResource(boolean value) {
        this.isResource = value;
    }

    /**
     * Gets the value of the isCurrency property.
     * 
     */
    public boolean isIsCurrency() {
        return isCurrency;
    }

    /**
     * Sets the value of the isCurrency property.
     * 
     */
    public void setIsCurrency(boolean value) {
        this.isCurrency = value;
    }

    /**
     * Gets the value of the resourceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Sets the value of the resourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceName(String value) {
        this.resourceName = value;
    }

    /**
     * Gets the value of the resourceSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceSymbol() {
        return resourceSymbol;
    }

    /**
     * Sets the value of the resourceSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceSymbol(String value) {
        this.resourceSymbol = value;
    }

    /**
     * Gets the value of the isSuperCredit property.
     * 
     */
    public boolean isIsSuperCredit() {
        return isSuperCredit;
    }

    /**
     * Sets the value of the isSuperCredit property.
     * 
     */
    public void setIsSuperCredit(boolean value) {
        this.isSuperCredit = value;
    }

}
