
package com.vodafone.global.er.decoupling.binding.response.v2;

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
 *         &lt;element name="full-account-v2" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ban" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="charging-id" type="{http://localhost:8080/decoupling/schemas/v2/common}chargingId"/>
 *                   &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="user-groups" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="billing-cycle-date" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sp-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="is-prepay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="last-validate-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="child-sp-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sp-type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="spend-limits" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="per-tx-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="per-day-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="per-month-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="cumulative-spend-day" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="cumulative-spend-month" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="suppress-courtesy-notifications" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "fullAccountV2"
})
@XmlRootElement(name = "full-accounts-response", namespace = "")
public class FullAccountsResponse
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "full-account-v2", required = true)
    protected List<FullAccountsResponse.FullAccountV2> fullAccountV2;

    /**
     * Gets the value of the fullAccountV2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fullAccountV2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFullAccountV2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FullAccountsResponse.FullAccountV2 }
     * 
     * 
     */
    public List<FullAccountsResponse.FullAccountV2> getFullAccountV2() {
        if (fullAccountV2 == null) {
            fullAccountV2 = new ArrayList<FullAccountsResponse.FullAccountV2>();
        }
        return this.fullAccountV2;
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
     *         &lt;element name="ban" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="charging-id" type="{http://localhost:8080/decoupling/schemas/v2/common}chargingId"/>
     *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="user-groups" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="billing-cycle-date" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sp-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="is-prepay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="last-validate-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="child-sp-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sp-type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
     *         &lt;element name="suppress-courtesy-notifications" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
        "chargingId",
        "status",
        "userGroups",
        "billingCycleDate",
        "country",
        "spId",
        "isPrepay",
        "lastValidateDate",
        "childSpId",
        "spType",
        "spendLimits",
        "suppressCourtesyNotifications"
    })
    public static class FullAccountV2
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(required = true)
        protected String ban;
        @XmlElement(name = "charging-id", required = true)
        protected ChargingId chargingId;
        protected int status;
        @XmlElement(name = "user-groups")
        protected FullAccountsResponse.FullAccountV2 .UserGroups userGroups;
        @XmlElement(name = "billing-cycle-date")
        protected Integer billingCycleDate;
        @XmlElement(required = true)
        protected String country;
        @XmlElement(name = "sp-id")
        protected String spId;
        @XmlElement(name = "is-prepay")
        protected String isPrepay;
        @XmlElement(name = "last-validate-date", required = true, type = String.class)
        @XmlJavaTypeAdapter(Adapter1 .class)
        @XmlSchemaType(name = "dateTime")
        protected Calendar lastValidateDate;
        @XmlElement(name = "child-sp-id")
        protected String childSpId;
        @XmlElement(name = "sp-type")
        protected String spType;
        @XmlElement(name = "spend-limits")
        protected FullAccountsResponse.FullAccountV2 .SpendLimits spendLimits;
        @XmlElement(name = "suppress-courtesy-notifications")
        protected Boolean suppressCourtesyNotifications;

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
         * Gets the value of the chargingId property.
         * 
         * @return
         *     possible object is
         *     {@link ChargingId }
         *     
         */
        public ChargingId getChargingId() {
            return chargingId;
        }

        /**
         * Sets the value of the chargingId property.
         * 
         * @param value
         *     allowed object is
         *     {@link ChargingId }
         *     
         */
        public void setChargingId(ChargingId value) {
            this.chargingId = value;
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
         * @return
         *     possible object is
         *     {@link FullAccountsResponse.FullAccountV2 .UserGroups }
         *     
         */
        public FullAccountsResponse.FullAccountV2 .UserGroups getUserGroups() {
            return userGroups;
        }

        /**
         * Sets the value of the userGroups property.
         * 
         * @param value
         *     allowed object is
         *     {@link FullAccountsResponse.FullAccountV2 .UserGroups }
         *     
         */
        public void setUserGroups(FullAccountsResponse.FullAccountV2 .UserGroups value) {
            this.userGroups = value;
        }

        /**
         * Gets the value of the billingCycleDate property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public int getBillingCycleDate() {
            if (billingCycleDate == null) {
                return  0;
            } else {
                return billingCycleDate;
            }
        }

        /**
         * Sets the value of the billingCycleDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setBillingCycleDate(Integer value) {
            this.billingCycleDate = value;
        }

        /**
         * Gets the value of the country property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCountry() {
            return country;
        }

        /**
         * Sets the value of the country property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCountry(String value) {
            this.country = value;
        }

        /**
         * Gets the value of the spId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSpId() {
            return spId;
        }

        /**
         * Sets the value of the spId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSpId(String value) {
            this.spId = value;
        }

        /**
         * Gets the value of the isPrepay property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIsPrepay() {
            return isPrepay;
        }

        /**
         * Sets the value of the isPrepay property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIsPrepay(String value) {
            this.isPrepay = value;
        }

        /**
         * Gets the value of the lastValidateDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Calendar getLastValidateDate() {
            return lastValidateDate;
        }

        /**
         * Sets the value of the lastValidateDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLastValidateDate(Calendar value) {
            this.lastValidateDate = value;
        }

        /**
         * Gets the value of the childSpId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChildSpId() {
            return childSpId;
        }

        /**
         * Sets the value of the childSpId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChildSpId(String value) {
            this.childSpId = value;
        }

        /**
         * Gets the value of the spType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSpType() {
            return spType;
        }

        /**
         * Sets the value of the spType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSpType(String value) {
            this.spType = value;
        }

        /**
         * Gets the value of the spendLimits property.
         * 
         * @return
         *     possible object is
         *     {@link FullAccountsResponse.FullAccountV2 .SpendLimits }
         *     
         */
        public FullAccountsResponse.FullAccountV2 .SpendLimits getSpendLimits() {
            return spendLimits;
        }

        /**
         * Sets the value of the spendLimits property.
         * 
         * @param value
         *     allowed object is
         *     {@link FullAccountsResponse.FullAccountV2 .SpendLimits }
         *     
         */
        public void setSpendLimits(FullAccountsResponse.FullAccountV2 .SpendLimits value) {
            this.spendLimits = value;
        }

        /**
         * Gets the value of the suppressCourtesyNotifications property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isSuppressCourtesyNotifications() {
            if (suppressCourtesyNotifications == null) {
                return false;
            } else {
                return suppressCourtesyNotifications;
            }
        }

        /**
         * Sets the value of the suppressCourtesyNotifications property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setSuppressCourtesyNotifications(Boolean value) {
            this.suppressCourtesyNotifications = value;
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
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
            "name"
        })
        public static class UserGroups
            implements Serializable, Element
        {

            private final static long serialVersionUID = 1L;
            protected List<String> name;

            /**
             * Gets the value of the name property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the name property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getName().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getName() {
                if (name == null) {
                    name = new ArrayList<String>();
                }
                return this.name;
            }

        }

    }

}
