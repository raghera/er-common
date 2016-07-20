
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 		This is the request type for both usage-auth-rate-charge and usage-auth-rate-charge-plus requests
 * 		
 * 
 * <p>Java class for usageAuthRateChargeRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="usageAuthRateChargeRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{}usageAuthRateRequestType">
 *       &lt;sequence>
 *         &lt;element name="external-sub-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="context" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usageAuthRateChargeRequestType", namespace = "", propOrder = {
    "externalSubId",
    "context"
})
public class UsageAuthRateChargeRequestType
    extends UsageAuthRateRequestType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "external-sub-id")
    protected String externalSubId;
    protected String context;

    /**
     * Gets the value of the externalSubId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalSubId() {
        return externalSubId;
    }

    /**
     * Sets the value of the externalSubId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalSubId(String value) {
        this.externalSubId = value;
    }

    /**
     * Gets the value of the context property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContext() {
        return context;
    }

    /**
     * Sets the value of the context property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContext(String value) {
        this.context = value;
    }

}
