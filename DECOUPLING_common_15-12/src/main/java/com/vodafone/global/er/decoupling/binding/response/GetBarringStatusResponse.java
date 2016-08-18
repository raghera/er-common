
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="bars" type="{http://localhost:8080/decoupling/schemas/common}bar" maxOccurs="unbounded" minOccurs="0"/>
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
    "bars"
})
@XmlRootElement(name = "get-barring-status-response")
public class GetBarringStatusResponse
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected List<Bar> bars;

    /**
     * Gets the value of the bars property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bars property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBars().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bar }
     * 
     * 
     */
    public List<Bar> getBars() {
        if (bars == null) {
            bars = new ArrayList<Bar>();
        }
        return this.bars;
    }

}
