
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Balance-filter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Balance-filter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="only-ace-attribute" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Balance-filter", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "onlyAceAttribute"
})
public class BalanceFilter
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "only-ace-attribute")
    protected boolean onlyAceAttribute;

    /**
     * Gets the value of the onlyAceAttribute property.
     * 
     */
    public boolean isOnlyAceAttribute() {
        return onlyAceAttribute;
    }

    /**
     * Sets the value of the onlyAceAttribute property.
     * 
     */
    public void setOnlyAceAttribute(boolean value) {
        this.onlyAceAttribute = value;
    }

}
