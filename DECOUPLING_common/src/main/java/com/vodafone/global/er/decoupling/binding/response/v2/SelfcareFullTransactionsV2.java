
package com.vodafone.global.er.decoupling.binding.response.v2;

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
 *         &lt;element name="payment-transactions" type="{http://localhost:8080/decoupling/schemas/v2/common}payment-transactions" minOccurs="0"/>
 *         &lt;element name="usage-transactions" type="{http://localhost:8080/decoupling/schemas/v2/common}usage-transactions" minOccurs="0"/>
 *         &lt;element name="refund-transactions" type="{http://localhost:8080/decoupling/schemas/v2/common}refund-transactions" minOccurs="0"/>
 *         &lt;element name="modify-transactions" type="{http://localhost:8080/decoupling/schemas/v2/common}modify-transactions" minOccurs="0"/>
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
    "paymentTransactions",
    "usageTransactions",
    "refundTransactions",
    "modifyTransactions"
})
@XmlRootElement(name = "selfcare-full-transactions-v2", namespace = "")
public class SelfcareFullTransactionsV2
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "payment-transactions")
    protected PaymentTransactions paymentTransactions;
    @XmlElement(name = "usage-transactions")
    protected UsageTransactions usageTransactions;
    @XmlElement(name = "refund-transactions")
    protected RefundTransactions refundTransactions;
    @XmlElement(name = "modify-transactions")
    protected ModifyTransactions modifyTransactions;

    /**
     * Gets the value of the paymentTransactions property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTransactions }
     *     
     */
    public PaymentTransactions getPaymentTransactions() {
        return paymentTransactions;
    }

    /**
     * Sets the value of the paymentTransactions property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTransactions }
     *     
     */
    public void setPaymentTransactions(PaymentTransactions value) {
        this.paymentTransactions = value;
    }

    /**
     * Gets the value of the usageTransactions property.
     * 
     * @return
     *     possible object is
     *     {@link UsageTransactions }
     *     
     */
    public UsageTransactions getUsageTransactions() {
        return usageTransactions;
    }

    /**
     * Sets the value of the usageTransactions property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageTransactions }
     *     
     */
    public void setUsageTransactions(UsageTransactions value) {
        this.usageTransactions = value;
    }

    /**
     * Gets the value of the refundTransactions property.
     * 
     * @return
     *     possible object is
     *     {@link RefundTransactions }
     *     
     */
    public RefundTransactions getRefundTransactions() {
        return refundTransactions;
    }

    /**
     * Sets the value of the refundTransactions property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundTransactions }
     *     
     */
    public void setRefundTransactions(RefundTransactions value) {
        this.refundTransactions = value;
    }

    /**
     * Gets the value of the modifyTransactions property.
     * 
     * @return
     *     possible object is
     *     {@link ModifyTransactions }
     *     
     */
    public ModifyTransactions getModifyTransactions() {
        return modifyTransactions;
    }

    /**
     * Sets the value of the modifyTransactions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModifyTransactions }
     *     
     */
    public void setModifyTransactions(ModifyTransactions value) {
        this.modifyTransactions = value;
    }

}
