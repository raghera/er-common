//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 04:57:40 PM BST 
//


package com.vodafone.global.er.decoupling.binding.response;


/**
 * Java content class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch/working/DECOUPLING_common/schemas/response/response.xsd line 946)
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="application" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="releaseDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="runningAs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="environment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="priceplans" type="{http://localhost:8080/decoupling/schemas/common}priceplanTypes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface ApplicationConfigType {


    /**
     * Gets the value of the environment property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getEnvironment();

    /**
     * Sets the value of the environment property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setEnvironment(java.lang.String value);

    /**
     * Gets the value of the priceplans property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PriceplanTypes}
     */
    com.vodafone.global.er.decoupling.binding.response.PriceplanTypes getPriceplans();

    /**
     * Sets the value of the priceplans property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.response.PriceplanTypes}
     */
    void setPriceplans(com.vodafone.global.er.decoupling.binding.response.PriceplanTypes value);

    /**
     * Gets the value of the application property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getApplication();

    /**
     * Sets the value of the application property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setApplication(java.lang.String value);

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getLanguage();

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setLanguage(java.lang.String value);

    /**
     * Gets the value of the runningAs property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getRunningAs();

    /**
     * Sets the value of the runningAs property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setRunningAs(java.lang.String value);

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCountry();

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCountry(java.lang.String value);

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getVersion();

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setVersion(java.lang.String value);

    /**
     * Gets the value of the releaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getReleaseDate();

    /**
     * Sets the value of the releaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setReleaseDate(java.lang.String value);

}