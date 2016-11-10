
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
 *         &lt;element name="parent-transaction" type="{http://localhost:8080/decoupling/schemas/common}parentTransactionType" minOccurs="0"/>
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
    "parentTransaction"
})
@XmlRootElement(name = "get-parent-transaction-response")
public class GetParentTransactionResponse
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "parent-transaction")
    protected ParentTransactionType parentTransaction;

    /**
     * Gets the value of the parentTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link ParentTransactionType }
     *     
     */
    public ParentTransactionType getParentTransaction() {
        return parentTransaction;
    }

    /**
     * Sets the value of the parentTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParentTransactionType }
     *     
     */
    public void setParentTransaction(ParentTransactionType value) {
        this.parentTransaction = value;
    }

}
