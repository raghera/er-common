
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
 * <p>Java class for pricePointTierFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pricePointTierFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="promotional-price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="balance-impacts" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="balance-impact" type="{http://localhost:8080/decoupling/schemas/common}balanceImpactFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="tier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pricePointTierFullType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "promotionalPrice",
    "balanceImpacts",
    "tier"
})
public class PricePointTierFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "promotional-price")
    protected double promotionalPrice;
    @XmlElement(name = "balance-impacts")
    protected PricePointTierFullType.BalanceImpacts balanceImpacts;
    protected String tier;

    /**
     * Gets the value of the promotionalPrice property.
     * 
     */
    public double getPromotionalPrice() {
        return promotionalPrice;
    }

    /**
     * Sets the value of the promotionalPrice property.
     * 
     */
    public void setPromotionalPrice(double value) {
        this.promotionalPrice = value;
    }

    /**
     * Gets the value of the balanceImpacts property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointTierFullType.BalanceImpacts }
     *     
     */
    public PricePointTierFullType.BalanceImpacts getBalanceImpacts() {
        return balanceImpacts;
    }

    /**
     * Sets the value of the balanceImpacts property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointTierFullType.BalanceImpacts }
     *     
     */
    public void setBalanceImpacts(PricePointTierFullType.BalanceImpacts value) {
        this.balanceImpacts = value;
    }

    /**
     * Gets the value of the tier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTier() {
        return tier;
    }

    /**
     * Sets the value of the tier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTier(String value) {
        this.tier = value;
    }


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
     *         &lt;element name="balance-impact" type="{http://localhost:8080/decoupling/schemas/common}balanceImpactFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "balanceImpact"
    })
    public static class BalanceImpacts
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "balance-impact")
        protected List<BalanceImpactFullType> balanceImpact;

        /**
         * Gets the value of the balanceImpact property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the balanceImpact property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBalanceImpact().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BalanceImpactFullType }
         * 
         * 
         */
        public List<BalanceImpactFullType> getBalanceImpact() {
            if (balanceImpact == null) {
                balanceImpact = new ArrayList<BalanceImpactFullType>();
            }
            return this.balanceImpact;
        }

    }

}
