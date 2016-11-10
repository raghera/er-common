
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resource-BalanceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resource-BalanceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldest-subscription-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="package-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="threshold" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="charging-resource" type="{http://localhost:8080/decoupling/schemas/common}charging-resourceType"/>
 *         &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resource-BalanceType", propOrder = {
    "subscriptionId",
    "oldestSubscriptionId",
    "packageId",
    "threshold",
    "chargingResource",
    "balance"
})
public class ResourceBalanceType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "subscription-id", required = true)
    protected String subscriptionId;
    @XmlElement(name = "oldest-subscription-id", required = true)
    protected String oldestSubscriptionId;
    @XmlElement(name = "package-id", required = true)
    protected String packageId;
    protected int threshold;
    @XmlElement(name = "charging-resource", required = true)
    protected ChargingResourceType chargingResource;
    protected double balance;

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
     * Gets the value of the oldestSubscriptionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldestSubscriptionId() {
        return oldestSubscriptionId;
    }

    /**
     * Sets the value of the oldestSubscriptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldestSubscriptionId(String value) {
        this.oldestSubscriptionId = value;
    }

    /**
     * Gets the value of the packageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageId() {
        return packageId;
    }

    /**
     * Sets the value of the packageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageId(String value) {
        this.packageId = value;
    }

    /**
     * Gets the value of the threshold property.
     * 
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * Sets the value of the threshold property.
     * 
     */
    public void setThreshold(int value) {
        this.threshold = value;
    }

    /**
     * Gets the value of the chargingResource property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingResourceType }
     *     
     */
    public ChargingResourceType getChargingResource() {
        return chargingResource;
    }

    /**
     * Sets the value of the chargingResource property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingResourceType }
     *     
     */
    public void setChargingResource(ChargingResourceType value) {
        this.chargingResource = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     */
    public void setBalance(double value) {
        this.balance = value;
    }

}
