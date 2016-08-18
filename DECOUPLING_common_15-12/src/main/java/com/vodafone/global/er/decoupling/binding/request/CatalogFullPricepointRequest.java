
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
 *         &lt;element name="pricepoint-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "pricepointId",
    "logId"
})
@XmlRootElement(name = "catalog-full-pricepoint-request", namespace = "")
public class CatalogFullPricepointRequest
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "pricepoint-id", required = true)
    protected String pricepointId;
    @XmlElement(name = "log-id")
    protected String logId;

    /**
     * Gets the value of the pricepointId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricepointId() {
        return pricepointId;
    }

    /**
     * Sets the value of the pricepointId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricepointId(String value) {
        this.pricepointId = value;
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
