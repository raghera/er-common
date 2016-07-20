
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chargingResourceFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chargingResourceFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargingResourceType" type="{http://localhost:8080/decoupling/schemas/common}charging-resourceType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chargingResourceFullType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "chargingResourceType"
})
public class ChargingResourceFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected ChargingResourceType chargingResourceType;

    /**
     * Gets the value of the chargingResourceType property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingResourceType }
     *     
     */
    public ChargingResourceType getChargingResourceType() {
        return chargingResourceType;
    }

    /**
     * Sets the value of the chargingResourceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingResourceType }
     *     
     */
    public void setChargingResourceType(ChargingResourceType value) {
        this.chargingResourceType = value;
    }

}
