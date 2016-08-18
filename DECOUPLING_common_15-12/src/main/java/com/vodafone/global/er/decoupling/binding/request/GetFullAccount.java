
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charging-id" type="{http://localhost:8080/decoupling/schemas/common}chargingId" minOccurs="0"/>
 *         &lt;element name="access-device" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="log-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="force-refresh-customer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "msisdn",
    "chargingId",
    "accessDevice",
    "logId",
    "forceRefreshCustomer"
})
@XmlRootElement(name = "get-full-account", namespace = "")
public class GetFullAccount
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected String msisdn;
    @XmlElement(name = "charging-id")
    protected ChargingId chargingId;
    @XmlElement(name = "access-device")
    protected Integer accessDevice;
    @XmlElement(name = "log-id")
    protected String logId;
    @XmlElement(name = "force-refresh-customer")
    protected Boolean forceRefreshCustomer;

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdn(String value) {
        this.msisdn = value;
    }

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
     * Gets the value of the accessDevice property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getAccessDevice() {
        if (accessDevice == null) {
            return  0;
        } else {
            return accessDevice;
        }
    }

    /**
     * Sets the value of the accessDevice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccessDevice(Integer value) {
        this.accessDevice = value;
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

    /**
     * Gets the value of the forceRefreshCustomer property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isForceRefreshCustomer() {
        if (forceRefreshCustomer == null) {
            return false;
        } else {
            return forceRefreshCustomer;
        }
    }

    /**
     * Sets the value of the forceRefreshCustomer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setForceRefreshCustomer(Boolean value) {
        this.forceRefreshCustomer = value;
    }

}
