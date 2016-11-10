
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for balanceImpactFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="balanceImpactFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "balanceImpactFullType", propOrder = {
    "key"
})
public class BalanceImpactFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected Long key;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getKey() {
        if (key == null) {
            return  0L;
        } else {
            return key;
        }
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setKey(Long value) {
        this.key = value;
    }

}
