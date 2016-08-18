
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for balanceImpactRates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="balanceImpactRates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="balance-impact-rate" type="{http://localhost:8080/decoupling/schemas/common}balanceImpactRate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "balanceImpactRates", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "balanceImpactRate"
})
public class BalanceImpactRates
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "balance-impact-rate")
    protected List<BalanceImpactRate> balanceImpactRate;

    /**
     * Gets the value of the balanceImpactRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the balanceImpactRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBalanceImpactRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BalanceImpactRate }
     * 
     * 
     */
    public List<BalanceImpactRate> getBalanceImpactRate() {
        if (balanceImpactRate == null) {
            balanceImpactRate = new ArrayList<BalanceImpactRate>();
        }
        return this.balanceImpactRate;
    }

}
