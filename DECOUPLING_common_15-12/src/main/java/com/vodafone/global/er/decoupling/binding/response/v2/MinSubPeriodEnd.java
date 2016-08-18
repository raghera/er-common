
package com.vodafone.global.er.decoupling.binding.response.v2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for min-sub-period-end complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="min-sub-period-end">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>dateTime">
 *       &lt;attribute name="penalty-charge" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="min-sub-period-end" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "min-sub-period-end", propOrder = {
    "value"
})
public class MinSubPeriodEnd
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlValue
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar value;
    @XmlAttribute(name = "penalty-charge")
    protected BigDecimal penaltyCharge;
    @XmlAttribute(name = "min-sub-period-end")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar minSubPeriodEnd;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(Calendar value) {
        this.value = value;
    }

    /**
     * Gets the value of the penaltyCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPenaltyCharge() {
        return penaltyCharge;
    }

    /**
     * Sets the value of the penaltyCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPenaltyCharge(BigDecimal value) {
        this.penaltyCharge = value;
    }

    /**
     * Gets the value of the minSubPeriodEnd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getMinSubPeriodEnd() {
        return minSubPeriodEnd;
    }

    /**
     * Sets the value of the minSubPeriodEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinSubPeriodEnd(Calendar value) {
        this.minSubPeriodEnd = value;
    }

}
