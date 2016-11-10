
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import java.util.Calendar;
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
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charging-id" type="{http://localhost:8080/decoupling/schemas/common}chargingId" minOccurs="0"/>
 *         &lt;element name="device" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="transactions-filter" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="active-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="is-no-zero-cost-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="is-monetry-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="is-modify-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="is-refund-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="is-refund-payments-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="is-all-events" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="transaction-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="end-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="max-events" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="transaction-type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="descending-order" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="external-field1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="external-field2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="external-trans-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="transaction-id-long" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                   &lt;element name="ascending-order" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "chargingId",
    "device",
    "transactionsFilter",
    "logId"
})
@XmlRootElement(name = "selfcare-full-transactions", namespace = "")
public class SelfcareFullTransactions
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected String msisdn;
    @XmlElement(name = "charging-id")
    protected ChargingId chargingId;
    protected Integer device;
    @XmlElement(name = "transactions-filter")
    protected SelfcareFullTransactions.TransactionsFilter transactionsFilter;
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
     * Gets the value of the device property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getDevice() {
        if (device == null) {
            return  0;
        } else {
            return device;
        }
    }

    /**
     * Sets the value of the device property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDevice(Integer value) {
        this.device = value;
    }

    /**
     * Gets the value of the transactionsFilter property.
     * 
     * @return
     *     possible object is
     *     {@link SelfcareFullTransactions.TransactionsFilter }
     *     
     */
    public SelfcareFullTransactions.TransactionsFilter getTransactionsFilter() {
        return transactionsFilter;
    }

    /**
     * Sets the value of the transactionsFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link SelfcareFullTransactions.TransactionsFilter }
     *     
     */
    public void setTransactionsFilter(SelfcareFullTransactions.TransactionsFilter value) {
        this.transactionsFilter = value;
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
     *         &lt;element name="active-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="is-no-zero-cost-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="is-monetry-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="is-modify-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="is-refund-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="is-refund-payments-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="is-all-events" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="transaction-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="end-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="max-events" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="transaction-type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descending-order" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="external-field1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="external-field2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="external-trans-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="transaction-id-long" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *         &lt;element name="ascending-order" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
        "activeEventsOnly",
        "isNoZeroCostEventsOnly",
        "isMonetryEventsOnly",
        "isModifyEventsOnly",
        "isRefundEventsOnly",
        "isRefundPaymentsEventsOnly",
        "isAllEvents",
        "transactionId",
        "subscriptionId",
        "startDate",
        "endDate",
        "maxEvents",
        "partnerId",
        "transactionType",
        "descendingOrder",
        "externalField1",
        "externalField2",
        "externalTransId",
        "transactionIdLong",
        "ascendingOrder"
    })
    public static class TransactionsFilter
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "active-events-only")
        protected Boolean activeEventsOnly;
        @XmlElement(name = "is-no-zero-cost-events-only")
        protected Boolean isNoZeroCostEventsOnly;
        @XmlElement(name = "is-monetry-events-only")
        protected Boolean isMonetryEventsOnly;
        @XmlElement(name = "is-modify-events-only")
        protected Boolean isModifyEventsOnly;
        @XmlElement(name = "is-refund-events-only")
        protected Boolean isRefundEventsOnly;
        @XmlElement(name = "is-refund-payments-events-only")
        protected Boolean isRefundPaymentsEventsOnly;
        @XmlElement(name = "is-all-events")
        protected Boolean isAllEvents;
        @XmlElement(name = "transaction-id")
        protected String transactionId;
        @XmlElement(name = "subscription-id")
        protected String subscriptionId;
        @XmlElement(name = "start-date", type = String.class)
        @XmlJavaTypeAdapter(Adapter1 .class)
        @XmlSchemaType(name = "dateTime")
        protected Calendar startDate;
        @XmlElement(name = "end-date", type = String.class)
        @XmlJavaTypeAdapter(Adapter1 .class)
        @XmlSchemaType(name = "dateTime")
        protected Calendar endDate;
        @XmlElement(name = "max-events")
        protected Integer maxEvents;
        @XmlElement(name = "partner-id")
        protected String partnerId;
        @XmlElement(name = "transaction-type")
        protected String transactionType;
        @XmlElement(name = "descending-order")
        protected Boolean descendingOrder;
        @XmlElement(name = "external-field1")
        protected String externalField1;
        @XmlElement(name = "external-field2")
        protected String externalField2;
        @XmlElement(name = "external-trans-id")
        protected String externalTransId;
        @XmlElement(name = "transaction-id-long")
        protected Long transactionIdLong;
        @XmlElement(name = "ascending-order")
        protected Boolean ascendingOrder;

        /**
         * Gets the value of the activeEventsOnly property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isActiveEventsOnly() {
            if (activeEventsOnly == null) {
                return false;
            } else {
                return activeEventsOnly;
            }
        }

        /**
         * Sets the value of the activeEventsOnly property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setActiveEventsOnly(Boolean value) {
            this.activeEventsOnly = value;
        }

        /**
         * Gets the value of the isNoZeroCostEventsOnly property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsNoZeroCostEventsOnly() {
            if (isNoZeroCostEventsOnly == null) {
                return false;
            } else {
                return isNoZeroCostEventsOnly;
            }
        }

        /**
         * Sets the value of the isNoZeroCostEventsOnly property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsNoZeroCostEventsOnly(Boolean value) {
            this.isNoZeroCostEventsOnly = value;
        }

        /**
         * Gets the value of the isMonetryEventsOnly property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsMonetryEventsOnly() {
            if (isMonetryEventsOnly == null) {
                return false;
            } else {
                return isMonetryEventsOnly;
            }
        }

        /**
         * Sets the value of the isMonetryEventsOnly property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsMonetryEventsOnly(Boolean value) {
            this.isMonetryEventsOnly = value;
        }

        /**
         * Gets the value of the isModifyEventsOnly property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsModifyEventsOnly() {
            if (isModifyEventsOnly == null) {
                return false;
            } else {
                return isModifyEventsOnly;
            }
        }

        /**
         * Sets the value of the isModifyEventsOnly property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsModifyEventsOnly(Boolean value) {
            this.isModifyEventsOnly = value;
        }

        /**
         * Gets the value of the isRefundEventsOnly property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsRefundEventsOnly() {
            if (isRefundEventsOnly == null) {
                return false;
            } else {
                return isRefundEventsOnly;
            }
        }

        /**
         * Sets the value of the isRefundEventsOnly property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsRefundEventsOnly(Boolean value) {
            this.isRefundEventsOnly = value;
        }

        /**
         * Gets the value of the isRefundPaymentsEventsOnly property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsRefundPaymentsEventsOnly() {
            if (isRefundPaymentsEventsOnly == null) {
                return false;
            } else {
                return isRefundPaymentsEventsOnly;
            }
        }

        /**
         * Sets the value of the isRefundPaymentsEventsOnly property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsRefundPaymentsEventsOnly(Boolean value) {
            this.isRefundPaymentsEventsOnly = value;
        }

        /**
         * Gets the value of the isAllEvents property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isIsAllEvents() {
            if (isAllEvents == null) {
                return false;
            } else {
                return isAllEvents;
            }
        }

        /**
         * Sets the value of the isAllEvents property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsAllEvents(Boolean value) {
            this.isAllEvents = value;
        }

        /**
         * Gets the value of the transactionId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTransactionId() {
            return transactionId;
        }

        /**
         * Sets the value of the transactionId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTransactionId(String value) {
            this.transactionId = value;
        }

        /**
         * Gets the value of the subscriptionId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSubscriptionId() {
            return subscriptionId;
        }

        /**
         * Sets the value of the subscriptionId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSubscriptionId(String value) {
            this.subscriptionId = value;
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
         * Gets the value of the endDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Calendar getEndDate() {
            return endDate;
        }

        /**
         * Sets the value of the endDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEndDate(Calendar value) {
            this.endDate = value;
        }

        /**
         * Gets the value of the maxEvents property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public int getMaxEvents() {
            if (maxEvents == null) {
                return  0;
            } else {
                return maxEvents;
            }
        }

        /**
         * Sets the value of the maxEvents property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setMaxEvents(Integer value) {
            this.maxEvents = value;
        }

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
         * Gets the value of the transactionType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTransactionType() {
            return transactionType;
        }

        /**
         * Sets the value of the transactionType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTransactionType(String value) {
            this.transactionType = value;
        }

        /**
         * Gets the value of the descendingOrder property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isDescendingOrder() {
            if (descendingOrder == null) {
                return false;
            } else {
                return descendingOrder;
            }
        }

        /**
         * Sets the value of the descendingOrder property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setDescendingOrder(Boolean value) {
            this.descendingOrder = value;
        }

        /**
         * Gets the value of the externalField1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExternalField1() {
            return externalField1;
        }

        /**
         * Sets the value of the externalField1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExternalField1(String value) {
            this.externalField1 = value;
        }

        /**
         * Gets the value of the externalField2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExternalField2() {
            return externalField2;
        }

        /**
         * Sets the value of the externalField2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExternalField2(String value) {
            this.externalField2 = value;
        }

        /**
         * Gets the value of the externalTransId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExternalTransId() {
            return externalTransId;
        }

        /**
         * Sets the value of the externalTransId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExternalTransId(String value) {
            this.externalTransId = value;
        }

        /**
         * Gets the value of the transactionIdLong property.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public long getTransactionIdLong() {
            if (transactionIdLong == null) {
                return  0L;
            } else {
                return transactionIdLong;
            }
        }

        /**
         * Sets the value of the transactionIdLong property.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setTransactionIdLong(Long value) {
            this.transactionIdLong = value;
        }

        /**
         * Gets the value of the ascendingOrder property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isAscendingOrder() {
            if (ascendingOrder == null) {
                return false;
            } else {
                return ascendingOrder;
            }
        }

        /**
         * Sets the value of the ascendingOrder property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setAscendingOrder(Boolean value) {
            this.ascendingOrder = value;
        }

    }

}
