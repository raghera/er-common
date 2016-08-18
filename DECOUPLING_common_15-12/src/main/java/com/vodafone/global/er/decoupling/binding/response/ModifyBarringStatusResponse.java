
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
 *         &lt;element name="bar" type="{http://localhost:8080/decoupling/schemas/common}bar"/>
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
    "bar"
})
@XmlRootElement(name = "modify-barring-status-response")
public class ModifyBarringStatusResponse
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected Bar bar;

    /**
     * Gets the value of the bar property.
     * 
     * @return
     *     possible object is
     *     {@link Bar }
     *     
     */
    public Bar getBar() {
        return bar;
    }

    /**
     * Sets the value of the bar property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bar }
     *     
     */
    public void setBar(Bar value) {
        this.bar = value;
    }

}
