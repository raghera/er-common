
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
 *         &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="charging-method" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="device-type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="csr-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="log-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apply-penalty-charge" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="inactivate-grace-or-suspended-sub" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "subscriptionId",
    "chargingMethod",
    "deviceType",
    "csrId",
    "reason",
    "logId",
    "applyPenaltyCharge",
    "inactivateGraceOrSuspendedSub"
})
@XmlRootElement(name = "modify-subscription-charging-method", namespace = "")
public class ModifySubscriptionChargingMethod
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected String msisdn;
    @XmlElement(name = "charging-id")
    protected ChargingId chargingId;
    @XmlElement(name = "subscription-id", required = true)
    protected String subscriptionId;
    @XmlElement(name = "charging-method")
    protected int chargingMethod;
    @XmlElement(name = "device-type")
    protected Integer deviceType;
    @XmlElement(name = "csr-id")
    protected String csrId;
    protected String reason;
    @XmlElement(name = "log-id")
    protected String logId;
    @XmlElement(name = "apply-penalty-charge")
    protected Boolean applyPenaltyCharge;
    @XmlElement(name = "inactivate-grace-or-suspended-sub")
    protected Boolean inactivateGraceOrSuspendedSub;

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
     * Gets the value of the chargingMethod property.
     * 
     */
    public int getChargingMethod() {
        return chargingMethod;
    }

    /**
     * Sets the value of the chargingMethod property.
     * 
     */
    public void setChargingMethod(int value) {
        this.chargingMethod = value;
    }

    /**
     * Gets the value of the deviceType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getDeviceType() {
        if (deviceType == null) {
            return  0;
        } else {
            return deviceType;
        }
    }

    /**
     * Sets the value of the deviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeviceType(Integer value) {
        this.deviceType = value;
    }

    /**
     * Gets the value of the csrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsrId() {
        return csrId;
    }

    /**
     * Sets the value of the csrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsrId(String value) {
        this.csrId = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
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
     * Gets the value of the applyPenaltyCharge property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isApplyPenaltyCharge() {
        if (applyPenaltyCharge == null) {
            return false;
        } else {
            return applyPenaltyCharge;
        }
    }

    /**
     * Sets the value of the applyPenaltyCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApplyPenaltyCharge(Boolean value) {
        this.applyPenaltyCharge = value;
    }

    /**
     * Gets the value of the inactivateGraceOrSuspendedSub property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isInactivateGraceOrSuspendedSub() {
        if (inactivateGraceOrSuspendedSub == null) {
            return false;
        } else {
            return inactivateGraceOrSuspendedSub;
        }
    }

    /**
     * Sets the value of the inactivateGraceOrSuspendedSub property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInactivateGraceOrSuspendedSub(Boolean value) {
        this.inactivateGraceOrSuspendedSub = value;
    }

}
