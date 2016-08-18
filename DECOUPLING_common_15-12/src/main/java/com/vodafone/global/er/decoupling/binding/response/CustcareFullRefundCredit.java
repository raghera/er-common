
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="refundAuthorisation" type="{http://localhost:8080/decoupling/schemas/common}refundAuthorisationFullType" minOccurs="0"/>
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
    "refundAuthorisation"
})
@XmlRootElement(name = "custcare-full-refund-credit")
public class CustcareFullRefundCredit
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected RefundAuthorisationFullType refundAuthorisation;

    /**
     * Gets the value of the refundAuthorisation property.
     * 
     * @return
     *     possible object is
     *     {@link RefundAuthorisationFullType }
     *     
     */
    public RefundAuthorisationFullType getRefundAuthorisation() {
        return refundAuthorisation;
    }

    /**
     * Sets the value of the refundAuthorisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundAuthorisationFullType }
     *     
     */
    public void setRefundAuthorisation(RefundAuthorisationFullType value) {
        this.refundAuthorisation = value;
    }

}
