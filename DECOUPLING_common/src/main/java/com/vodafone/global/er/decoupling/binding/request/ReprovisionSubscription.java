
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="charging-id" type="{http://localhost:8080/decoupling/schemas/common}chargingId"/>
 *         &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provision-type" type="{http://localhost:8080/decoupling/schemas/common}provisionType"/>
 *         &lt;element name="context" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="service-ids" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "chargingId",
    "subscriptionId",
    "provisionType",
    "context",
    "serviceIds"
})
@XmlRootElement(name = "reprovision-subscription", namespace = "")
public class ReprovisionSubscription
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "charging-id", required = true)
    protected ChargingId chargingId;
    @XmlElement(name = "subscription-id", required = true)
    protected String subscriptionId;
    @XmlElement(name = "provision-type", required = true)
    @XmlSchemaType(name = "string")
    protected ProvisionType provisionType;
    protected String context;
    @XmlElement(name = "service-ids")
    protected String serviceIds;

    /**
     * Gets the value of the chargingId property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingId }
     *     
     */
    public ChargingId getChargingId() {
        return chargingId;
    }

    /**
     * Sets the value of the chargingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingId }
     *     
     */
    public void setChargingId(ChargingId value) {
        this.chargingId = value;
    }

    /**
     * Gets the value of the subscriptionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * Sets the value of the subscriptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionId(String value) {
        this.subscriptionId = value;
    }

    /**
     * Gets the value of the provisionType property.
     * 
     * @return
     *     possible object is
     *     {@link ProvisionType }
     *     
     */
    public ProvisionType getProvisionType() {
        return provisionType;
    }

    /**
     * Sets the value of the provisionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProvisionType }
     *     
     */
    public void setProvisionType(ProvisionType value) {
        this.provisionType = value;
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

    /**
     * Gets the value of the serviceIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceIds() {
        return serviceIds;
    }

    /**
     * Sets the value of the serviceIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceIds(String value) {
        this.serviceIds = value;
    }

}
