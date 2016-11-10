
package com.vodafone.global.er.decoupling.binding.response.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for payment-transactions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="payment-transactions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transaction" type="{http://localhost:8080/decoupling/schemas/v2/common}payment-transaction" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payment-transactions", propOrder = {
    "transaction"
})
public class PaymentTransactions
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected List<PaymentTransaction> transaction;

    /**
     * Gets the value of the transaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentTransaction }
     * 
     * 
     */
    public List<PaymentTransaction> getTransaction() {
        if (transaction == null) {
            transaction = new ArrayList<PaymentTransaction>();
        }
        return this.transaction;
    }

}
