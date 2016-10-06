
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This is the request type for both provision-full-update-service-status-request
 *                 and provision-simple-update-service-status-request
 *             
 * 
 * <p>Java class for provisionUpdateRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="provisionUpdateRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="provisioning-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="service-status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="provisioning-status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="provisioning-tag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="log-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "provisionUpdateRequestType", namespace = "", propOrder = {
    "provisioningId",
    "serviceStatus",
    "provisioningStatus",
    "provisioningTag",
    "logId"
})
public class ProvisionUpdateRequestType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "provisioning-id", required = true)
    protected String provisioningId;
    @XmlElement(name = "service-status")
    protected int serviceStatus;
    @XmlElement(name = "provisioning-status")
    protected int provisioningStatus;
    @XmlElement(name = "provisioning-tag")
    protected String provisioningTag;
    @XmlElement(name = "log-id")
    protected String logId;

    /**
     * Gets the value of the provisioningId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvisioningId() {
        return provisioningId;
    }

    /**
     * Sets the value of the provisioningId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvisioningId(String value) {
        this.provisioningId = value;
    }

    /**
     * Gets the value of the serviceStatus property.
     * 
     */
    public int getServiceStatus() {
        return serviceStatus;
    }

    /**
     * Sets the value of the serviceStatus property.
     * 
     */
    public void setServiceStatus(int value) {
        this.serviceStatus = value;
    }

    /**
     * Gets the value of the provisioningStatus property.
     * 
     */
    public int getProvisioningStatus() {
        return provisioningStatus;
    }

    /**
     * Sets the value of the provisioningStatus property.
     * 
     */
    public void setProvisioningStatus(int value) {
        this.provisioningStatus = value;
    }

    /**
     * Gets the value of the provisioningTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvisioningTag() {
        return provisioningTag;
    }

    /**
     * Sets the value of the provisioningTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvisioningTag(String value) {
        this.provisioningTag = value;
    }

    /**
     * Gets the value of the logId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogId() {
        return logId;
    }

    /**
     * Sets the value of the logId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogId(String value) {
        this.logId = value;
    }

}
