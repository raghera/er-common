
package com.vodafone.global.er.decoupling.binding.response;

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
 *         &lt;element name="next-payment-amount" type="{http://localhost:8080/decoupling/schemas/common}nextPaymentAmountType" minOccurs="0"/>
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
    "nextPaymentAmount"
})
@XmlRootElement(name = "get-next-payment-amount-response")
public class GetNextPaymentAmountResponse
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "next-payment-amount")
    protected NextPaymentAmountType nextPaymentAmount;

    /**
     * Gets the value of the nextPaymentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link NextPaymentAmountType }
     *     
     */
    public NextPaymentAmountType getNextPaymentAmount() {
        return nextPaymentAmount;
    }

    /**
     * Sets the value of the nextPaymentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link NextPaymentAmountType }
     *     
     */
    public void setNextPaymentAmount(NextPaymentAmountType value) {
        this.nextPaymentAmount = value;
    }

}
