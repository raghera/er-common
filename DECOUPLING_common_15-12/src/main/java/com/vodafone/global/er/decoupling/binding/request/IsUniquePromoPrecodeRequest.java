
package com.vodafone.global.er.decoupling.binding.request;

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
 *         &lt;element name="precode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="log-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "precode",
    "logId"
})
@XmlRootElement(name = "is-unique-promo-precode-request", namespace = "")
public class IsUniquePromoPrecodeRequest
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String precode;
    @XmlElement(name = "log-id")
    protected String logId;

    /**
     * Gets the value of the precode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrecode() {
        return precode;
    }

    /**
     * Sets the value of the precode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrecode(String value) {
        this.precode = value;
    }

    /**
     * Gets the value of the logId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogId() {
        return logId;
    }

    /**
     * Sets the value of the logId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogId(String value) {
        this.logId = value;
    }

}
