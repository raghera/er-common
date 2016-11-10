
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
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="spend-limits">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="per-tx-limit" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="per-day-limit" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="per-month-limit" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="csr-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "msisdn",
    "spendLimits",
    "csrId",
    "logId"
})
@XmlRootElement(name = "modify-spend-limits-request", namespace = "")
public class ModifySpendLimitsRequest
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String msisdn;
    @XmlElement(name = "spend-limits", required = true)
    protected ModifySpendLimitsRequest.SpendLimits spendLimits;
    @XmlElement(name = "csr-id")
    protected String csrId;
    @XmlElement(name = "log-id")
    protected String logId;

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdn(String value) {
        this.msisdn = value;
    }

    /**
     * Gets the value of the spendLimits property.
     * 
     * @return
     *     possible object is
     *     {@link ModifySpendLimitsRequest.SpendLimits }
     *     
     */
    public ModifySpendLimitsRequest.SpendLimits getSpendLimits() {
        return spendLimits;
    }

    /**
     * Sets the value of the spendLimits property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModifySpendLimitsRequest.SpendLimits }
     *     
     */
    public void setSpendLimits(ModifySpendLimitsRequest.SpendLimits value) {
        this.spendLimits = value;
    }

    /**
     * Gets the value of the csrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsrId() {
        return csrId;
    }

    /**
     * Sets the value of the csrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsrId(String value) {
        this.csrId = value;
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
     *         &lt;element name="per-tx-limit" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="per-day-limit" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="per-month-limit" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
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
        "perTxLimit",
        "perDayLimit",
        "perMonthLimit"
    })
    public static class SpendLimits
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "per-tx-limit")
        protected Double perTxLimit;
        @XmlElement(name = "per-day-limit")
        protected Double perDayLimit;
        @XmlElement(name = "per-month-limit")
        protected Double perMonthLimit;

        /**
         * Gets the value of the perTxLimit property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public double getPerTxLimit() {
            if (perTxLimit == null) {
                return  0.0D;
            } else {
                return perTxLimit;
            }
        }

        /**
         * Sets the value of the perTxLimit property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setPerTxLimit(Double value) {
            this.perTxLimit = value;
        }

        /**
         * Gets the value of the perDayLimit property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public double getPerDayLimit() {
            if (perDayLimit == null) {
                return  0.0D;
            } else {
                return perDayLimit;
            }
        }

        /**
         * Sets the value of the perDayLimit property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setPerDayLimit(Double value) {
            this.perDayLimit = value;
        }

        /**
         * Gets the value of the perMonthLimit property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public double getPerMonthLimit() {
            if (perMonthLimit == null) {
                return  0.0D;
            } else {
                return perMonthLimit;
            }
        }

        /**
         * Sets the value of the perMonthLimit property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setPerMonthLimit(Double value) {
            this.perMonthLimit = value;
        }

    }

}
