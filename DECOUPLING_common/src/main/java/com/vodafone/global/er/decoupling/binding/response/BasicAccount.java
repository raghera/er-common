
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="ban" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payment-card-details" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="issue-number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="expiry-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="user-groups" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="spend-limits" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="per-tx-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="per-day-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="per-month-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="cumulative-spend-day" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="cumulative-spend-month" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "ban",
    "msisdn",
    "name",
    "paymentCardDetails",
    "status",
    "userGroups",
    "spendLimits"
})
@XmlRootElement(name = "basic-account")
public class BasicAccount
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String ban;
    @XmlElement(required = true)
    protected String msisdn;
    protected String name;
    @XmlElement(name = "payment-card-details")
    protected BasicAccount.PaymentCardDetails paymentCardDetails;
    protected int status;
    @XmlElement(name = "user-groups")
    protected List<BasicAccount.UserGroups> userGroups;
    @XmlElement(name = "spend-limits")
    protected BasicAccount.SpendLimits spendLimits;

    /**
     * Gets the value of the ban property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBan() {
        return ban;
    }

    /**
     * Sets the value of the ban property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBan(String value) {
        this.ban = value;
    }

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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the paymentCardDetails property.
     * 
     * @return
     *     possible object is
     *     {@link BasicAccount.PaymentCardDetails }
     *     
     */
    public BasicAccount.PaymentCardDetails getPaymentCardDetails() {
        return paymentCardDetails;
    }

    /**
     * Sets the value of the paymentCardDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicAccount.PaymentCardDetails }
     *     
     */
    public void setPaymentCardDetails(BasicAccount.PaymentCardDetails value) {
        this.paymentCardDetails = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the userGroups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userGroups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserGroups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BasicAccount.UserGroups }
     * 
     * 
     */
    public List<BasicAccount.UserGroups> getUserGroups() {
        if (userGroups == null) {
            userGroups = new ArrayList<BasicAccount.UserGroups>();
        }
        return this.userGroups;
    }

    /**
     * Gets the value of the spendLimits property.
     * 
     * @return
     *     possible object is
     *     {@link BasicAccount.SpendLimits }
     *     
     */
    public BasicAccount.SpendLimits getSpendLimits() {
        return spendLimits;
    }

    /**
     * Sets the value of the spendLimits property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicAccount.SpendLimits }
     *     
     */
    public void setSpendLimits(BasicAccount.SpendLimits value) {
        this.spendLimits = value;
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
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="issue-number" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="expiry-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
        "name",
        "number",
        "issueNumber",
        "type",
        "startDate",
        "expiryDate"
    })
    public static class PaymentCardDetails
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(required = true)
        protected String name;
        @XmlElement(required = true)
        protected String number;
        @XmlElement(name = "issue-number", required = true)
        protected String issueNumber;
        @XmlElement(required = true)
        protected String type;
        @XmlElement(name = "start-date", required = true, type = String.class)
        @XmlJavaTypeAdapter(Adapter1 .class)
        @XmlSchemaType(name = "dateTime")
        protected Calendar startDate;
        @XmlElement(name = "expiry-date", required = true, type = String.class)
        @XmlJavaTypeAdapter(Adapter1 .class)
        @XmlSchemaType(name = "dateTime")
        protected Calendar expiryDate;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the number property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumber() {
            return number;
        }

        /**
         * Sets the value of the number property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumber(String value) {
            this.number = value;
        }

        /**
         * Gets the value of the issueNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIssueNumber() {
            return issueNumber;
        }

        /**
         * Sets the value of the issueNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIssueNumber(String value) {
            this.issueNumber = value;
        }

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the startDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Calendar getStartDate() {
            return startDate;
        }

        /**
         * Sets the value of the startDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStartDate(Calendar value) {
            this.startDate = value;
        }

        /**
         * Gets the value of the expiryDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Calendar getExpiryDate() {
            return expiryDate;
        }

        /**
         * Sets the value of the expiryDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExpiryDate(Calendar value) {
            this.expiryDate = value;
        }

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
     *         &lt;element name="per-tx-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="per-day-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="per-month-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="cumulative-spend-day" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="cumulative-spend-month" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
        "perMonthLimit",
        "cumulativeSpendDay",
        "cumulativeSpendMonth"
    })
    public static class SpendLimits
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "per-tx-limit")
        protected double perTxLimit;
        @XmlElement(name = "per-day-limit")
        protected double perDayLimit;
        @XmlElement(name = "per-month-limit")
        protected double perMonthLimit;
        @XmlElement(name = "cumulative-spend-day")
        protected double cumulativeSpendDay;
        @XmlElement(name = "cumulative-spend-month")
        protected double cumulativeSpendMonth;

        /**
         * Gets the value of the perTxLimit property.
         * 
         */
        public double getPerTxLimit() {
            return perTxLimit;
        }

        /**
         * Sets the value of the perTxLimit property.
         * 
         */
        public void setPerTxLimit(double value) {
            this.perTxLimit = value;
        }

        /**
         * Gets the value of the perDayLimit property.
         * 
         */
        public double getPerDayLimit() {
            return perDayLimit;
        }

        /**
         * Sets the value of the perDayLimit property.
         * 
         */
        public void setPerDayLimit(double value) {
            this.perDayLimit = value;
        }

        /**
         * Gets the value of the perMonthLimit property.
         * 
         */
        public double getPerMonthLimit() {
            return perMonthLimit;
        }

        /**
         * Sets the value of the perMonthLimit property.
         * 
         */
        public void setPerMonthLimit(double value) {
            this.perMonthLimit = value;
        }

        /**
         * Gets the value of the cumulativeSpendDay property.
         * 
         */
        public double getCumulativeSpendDay() {
            return cumulativeSpendDay;
        }

        /**
         * Sets the value of the cumulativeSpendDay property.
         * 
         */
        public void setCumulativeSpendDay(double value) {
            this.cumulativeSpendDay = value;
        }

        /**
         * Gets the value of the cumulativeSpendMonth property.
         * 
         */
        public double getCumulativeSpendMonth() {
            return cumulativeSpendMonth;
        }

        /**
         * Sets the value of the cumulativeSpendMonth property.
         * 
         */
        public void setCumulativeSpendMonth(double value) {
            this.cumulativeSpendMonth = value;
        }

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
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "name",
        "description"
    })
    public static class UserGroups
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(required = true)
        protected String name;
        protected String description;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the description property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescription() {
            return description;
        }

        /**
         * Sets the value of the description property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescription(String value) {
            this.description = value;
        }

    }

}
