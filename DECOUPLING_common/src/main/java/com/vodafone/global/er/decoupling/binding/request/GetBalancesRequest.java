
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
 *         &lt;element name="client-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="device-id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="balace-filter" type="{http://localhost:8080/decoupling/schemas/common}Balance-filter" minOccurs="0"/>
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
@XmlType(name = "", propOrder = {
    "msisdn",
    "chargingId",
    "clientId",
    "deviceId",
    "balaceFilter",
    "logId"
})
@XmlRootElement(name = "get-balances-request", namespace = "")
public class GetBalancesRequest
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected String msisdn;
    @XmlElement(name = "charging-id")
    protected ChargingId chargingId;
    @XmlElement(name = "client-id", required = true)
    protected String clientId;
    @XmlElement(name = "device-id")
    protected int deviceId;
    @XmlElement(name = "balace-filter")
    protected BalanceFilter balaceFilter;
    @XmlElement(name = "log-id")
    protected String logId;

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
     * Gets the value of the clientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the value of the clientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientId(String value) {
        this.clientId = value;
    }

    /**
     * Gets the value of the deviceId property.
     * 
     */
    public int getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the value of the deviceId property.
     * 
     */
    public void setDeviceId(int value) {
        this.deviceId = value;
    }

    /**
     * Gets the value of the balaceFilter property.
     * 
     * @return
     *     possible object is
     *     {@link BalanceFilter }
     *     
     */
    public BalanceFilter getBalaceFilter() {
        return balaceFilter;
    }

    /**
     * Sets the value of the balaceFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link BalanceFilter }
     *     
     */
    public void setBalaceFilter(BalanceFilter value) {
        this.balaceFilter = value;
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
