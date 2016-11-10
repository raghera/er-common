
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
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="device" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="transaction-attributes-required" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="log-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purchase-rate-gross-required" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "device",
    "transactionAttributesRequired",
    "transactionsFilter",
    "logId",
    "purchaseRateGrossRequired"
})
@XmlRootElement(name = "selfcare-transactions-request", namespace = "")
public class SelfcareTransactionsRequest
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String msisdn;
    protected Integer device;
    @XmlElement(name = "transaction-attributes-required")
    protected Boolean transactionAttributesRequired;
    @XmlElement(name = "transactions-filter")
    protected SelfcareTransactionsRequest.TransactionsFilter transactionsFilter;
    @XmlElement(name = "log-id")
    protected String logId;
    @XmlElement(name = "purchase-rate-gross-required")
    protected Boolean purchaseRateGrossRequired;

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
     * Gets the value of the transactionAttributesRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isTransactionAttributesRequired() {
        if (transactionAttributesRequired == null) {
            return false;
        } else {
            return transactionAttributesRequired;
        }
    }

    /**
     * Sets the value of the transactionAttributesRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTransactionAttributesRequired(Boolean value) {
        this.transactionAttributesRequired = value;
    }

    /**
     * Gets the value of the transactionsFilter property.
     * 
     * @return
     *     possible object is
     *     {@link SelfcareTransactionsRequest.TransactionsFilter }
     *     
     */
    public SelfcareTransactionsRequest.TransactionsFilter getTransactionsFilter() {
        return transactionsFilter;
    }

    /**
     * Sets the value of the transactionsFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link SelfcareTransactionsRequest.TransactionsFilter }
     *     
     */
    public void setTransactionsFilter(SelfcareTransactionsRequest.TransactionsFilter value) {
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
     * Gets the value of the purchaseRateGrossRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPurchaseRateGrossRequired() {
        if (purchaseRateGrossRequired == null) {
            return false;
        } else {
            return purchaseRateGrossRequired;
        }
    }

    /**
     * Sets the value of the purchaseRateGrossRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPurchaseRateGrossRequired(Boolean value) {
        this.purchaseRateGrossRequired = value;
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
        "partnerId"
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

    }

}
