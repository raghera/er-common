
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for nextPaymentAmountType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="nextPaymentAmountType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="net-rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tax-amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nextPaymentAmountType", propOrder = {
    "rate",
    "netRate",
    "taxAmount"
})
public class NextPaymentAmountType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected double rate;
    @XmlElement(name = "net-rate")
    protected double netRate;
    @XmlElement(name = "tax-amount")
    protected double taxAmount;

    /**
     * Gets the value of the rate property.
     * 
     */
    public double getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     * 
     */
    public void setRate(double value) {
        this.rate = value;
    }

    /**
     * Gets the value of the netRate property.
     * 
     */
    public double getNetRate() {
        return netRate;
    }

    /**
     * Sets the value of the netRate property.
     * 
     */
    public void setNetRate(double value) {
        this.netRate = value;
    }

    /**
     * Gets the value of the taxAmount property.
     * 
     */
    public double getTaxAmount() {
        return taxAmount;
    }

    /**
     * Sets the value of the taxAmount property.
     * 
     */
    public void setTaxAmount(double value) {
        this.taxAmount = value;
    }

}
