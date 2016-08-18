//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.03 at 03:16:51 PM BST 
//


package com.vodafone.global.er.decoupling.binding.request;


/**
 * Java content class for charging-resourceType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch_matt/working/DECOUPLING_common/schemas/common/pa_common.xsd line 145)
 * <p>
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
 */
public interface ChargingResourceType {


    /**
     * Gets the value of the isSuperCredit property.
     * 
     */
    boolean isIsSuperCredit();

    /**
     * Sets the value of the isSuperCredit property.
     * 
     */
    void setIsSuperCredit(boolean value);

    /**
     * Gets the value of the isResource property.
     * 
     */
    boolean isIsResource();

    /**
     * Sets the value of the isResource property.
     * 
     */
    void setIsResource(boolean value);

    /**
     * Gets the value of the translatedResourceName property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getTranslatedResourceName();

    /**
     * Sets the value of the translatedResourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setTranslatedResourceName(java.lang.String value);

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getName();

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setName(java.lang.String value);

    /**
     * Gets the value of the isPayToken property.
     * 
     */
    boolean isIsPayToken();

    /**
     * Sets the value of the isPayToken property.
     * 
     */
    void setIsPayToken(boolean value);

    /**
     * Gets the value of the resourceSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getResourceSymbol();

    /**
     * Sets the value of the resourceSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setResourceSymbol(java.lang.String value);

    /**
     * Gets the value of the resourceName property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getResourceName();

    /**
     * Sets the value of the resourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setResourceName(java.lang.String value);

    /**
     * Gets the value of the isCurrency property.
     * 
     */
    boolean isIsCurrency();

    /**
     * Sets the value of the isCurrency property.
     * 
     */
    void setIsCurrency(boolean value);

    /**
     * Gets the value of the isUsageToken property.
     * 
     */
    boolean isIsUsageToken();

    /**
     * Sets the value of the isUsageToken property.
     * 
     */
    void setIsUsageToken(boolean value);

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCode();

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCode(java.lang.String value);

}
