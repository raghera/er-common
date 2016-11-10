
package com.vodafone.global.er.decoupling.binding.response.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for refund-transactions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refund-transactions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="refund" type="{http://localhost:8080/decoupling/schemas/v2/common}refund-transaction" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refund-transactions", propOrder = {
    "refund"
})
public class RefundTransactions
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected List<RefundTransaction> refund;

    /**
     * Gets the value of the refund property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refund property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefund().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefundTransaction }
     * 
     * 
     */
    public List<RefundTransaction> getRefund() {
        if (refund == null) {
            refund = new ArrayList<RefundTransaction>();
        }
        return this.refund;
    }

}
