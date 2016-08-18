
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for priceplanTypes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="priceplanTypes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="priceplan" type="{http://localhost:8080/decoupling/schemas/common}priceplanType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "priceplanTypes", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "priceplan"
})
public class PriceplanTypes
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected List<PriceplanType> priceplan;

    /**
     * Gets the value of the priceplan property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the priceplan property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPriceplan().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PriceplanType }
     * 
     * 
     */
    public List<PriceplanType> getPriceplan() {
        if (priceplan == null) {
            priceplan = new ArrayList<PriceplanType>();
        }
        return this.priceplan;
    }

}
