
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
 *         &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}price-pointType" minOccurs="0"/>
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
    "pricePoint"
})
@XmlRootElement(name = "get-pricepoint-response")
public class GetPricepointResponse
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "price-point")
    protected PricePointType pricePoint;

    /**
     * Gets the value of the pricePoint property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointType }
     *     
     */
    public PricePointType getPricePoint() {
        return pricePoint;
    }

    /**
     * Sets the value of the pricePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointType }
     *     
     */
    public void setPricePoint(PricePointType value) {
        this.pricePoint = value;
    }

}
