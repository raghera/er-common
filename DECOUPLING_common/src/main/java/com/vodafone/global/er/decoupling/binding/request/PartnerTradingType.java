
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for partnerTradingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partnerTradingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="max-trans-amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="max-cumulative-trans-amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="period-days" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partnerTradingType", propOrder = {
    "partnerId",
    "maxTransAmount",
    "maxCumulativeTransAmount",
    "periodDays"
})
public class PartnerTradingType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "partner-id", required = true)
    protected String partnerId;
    @XmlElement(name = "max-trans-amount")
    protected double maxTransAmount;
    @XmlElement(name = "max-cumulative-trans-amount")
    protected double maxCumulativeTransAmount;
    @XmlElement(name = "period-days")
    protected int periodDays;

    /**
     * Gets the value of the partnerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerId(String value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the maxTransAmount property.
     * 
     */
    public double getMaxTransAmount() {
        return maxTransAmount;
    }

    /**
     * Sets the value of the maxTransAmount property.
     * 
     */
    public void setMaxTransAmount(double value) {
        this.maxTransAmount = value;
    }

    /**
     * Gets the value of the maxCumulativeTransAmount property.
     * 
     */
    public double getMaxCumulativeTransAmount() {
        return maxCumulativeTransAmount;
    }

    /**
     * Sets the value of the maxCumulativeTransAmount property.
     * 
     */
    public void setMaxCumulativeTransAmount(double value) {
        this.maxCumulativeTransAmount = value;
    }

    /**
     * Gets the value of the periodDays property.
     * 
     */
    public int getPeriodDays() {
        return periodDays;
    }

    /**
     * Sets the value of the periodDays property.
     * 
     */
    public void setPeriodDays(int value) {
        this.periodDays = value;
    }

}
