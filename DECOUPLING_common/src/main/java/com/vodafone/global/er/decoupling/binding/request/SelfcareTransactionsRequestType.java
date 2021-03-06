//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.03 at 03:16:51 PM BST 
//


package com.vodafone.global.er.decoupling.binding.request;


/**
 * Java content class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch_matt/working/DECOUPLING_common/schemas/request/request.xsd line 274)
 * <p>
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
 */
public interface SelfcareTransactionsRequestType {


    /**
     * Gets the value of the purchaseRateGrossRequired property.
     * 
     */
    boolean isPurchaseRateGrossRequired();

    /**
     * Sets the value of the purchaseRateGrossRequired property.
     * 
     */
    void setPurchaseRateGrossRequired(boolean value);

    /**
     * Gets the value of the transactionAttributesRequired property.
     * 
     */
    boolean isTransactionAttributesRequired();

    /**
     * Sets the value of the transactionAttributesRequired property.
     * 
     */
    void setTransactionAttributesRequired(boolean value);

    /**
     * Gets the value of the transactionsFilter property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.SelfcareTransactionsRequestType.TransactionsFilterType}
     */
    com.vodafone.global.er.decoupling.binding.request.SelfcareTransactionsRequestType.TransactionsFilterType getTransactionsFilter();

    /**
     * Sets the value of the transactionsFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.SelfcareTransactionsRequestType.TransactionsFilterType}
     */
    void setTransactionsFilter(com.vodafone.global.er.decoupling.binding.request.SelfcareTransactionsRequestType.TransactionsFilterType value);

    /**
     * Gets the value of the device property.
     * 
     */
    int getDevice();

    /**
     * Sets the value of the device property.
     * 
     */
    void setDevice(int value);

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getMsisdn();

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setMsisdn(java.lang.String value);

    /**
     * Gets the value of the logId property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getLogId();

    /**
     * Sets the value of the logId property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setLogId(java.lang.String value);


    /**
     * Java content class for anonymous complex type.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch_matt/working/DECOUPLING_common/schemas/request/request.xsd line 286)
     * <p>
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
     */
    public interface TransactionsFilterType {


        /**
         * Gets the value of the isAllEvents property.
         * 
         */
        boolean isIsAllEvents();

        /**
         * Sets the value of the isAllEvents property.
         * 
         */
        void setIsAllEvents(boolean value);

        /**
         * Gets the value of the isMonetryEventsOnly property.
         * 
         */
        boolean isIsMonetryEventsOnly();

        /**
         * Sets the value of the isMonetryEventsOnly property.
         * 
         */
        void setIsMonetryEventsOnly(boolean value);

        /**
         * Gets the value of the maxEvents property.
         * 
         */
        int getMaxEvents();

        /**
         * Sets the value of the maxEvents property.
         * 
         */
        void setMaxEvents(int value);

        /**
         * Gets the value of the isNoZeroCostEventsOnly property.
         * 
         */
        boolean isIsNoZeroCostEventsOnly();

        /**
         * Sets the value of the isNoZeroCostEventsOnly property.
         * 
         */
        void setIsNoZeroCostEventsOnly(boolean value);

        /**
         * Gets the value of the transactionId property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getTransactionId();

        /**
         * Sets the value of the transactionId property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setTransactionId(java.lang.String value);

        /**
         * Gets the value of the endDate property.
         * 
         * @return
         *     possible object is
         *     {@link java.util.Calendar}
         */
        java.util.Calendar getEndDate();

        /**
         * Sets the value of the endDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.util.Calendar}
         */
        void setEndDate(java.util.Calendar value);

        /**
         * Gets the value of the startDate property.
         * 
         * @return
         *     possible object is
         *     {@link java.util.Calendar}
         */
        java.util.Calendar getStartDate();

        /**
         * Sets the value of the startDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.util.Calendar}
         */
        void setStartDate(java.util.Calendar value);

        /**
         * Gets the value of the isModifyEventsOnly property.
         * 
         */
        boolean isIsModifyEventsOnly();

        /**
         * Sets the value of the isModifyEventsOnly property.
         * 
         */
        void setIsModifyEventsOnly(boolean value);

        /**
         * Gets the value of the activeEventsOnly property.
         * 
         */
        boolean isActiveEventsOnly();

        /**
         * Sets the value of the activeEventsOnly property.
         * 
         */
        void setActiveEventsOnly(boolean value);

        /**
         * Gets the value of the subscriptionId property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getSubscriptionId();

        /**
         * Sets the value of the subscriptionId property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setSubscriptionId(java.lang.String value);

        /**
         * Gets the value of the partnerId property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getPartnerId();

        /**
         * Sets the value of the partnerId property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setPartnerId(java.lang.String value);

        /**
         * Gets the value of the isRefundPaymentsEventsOnly property.
         * 
         */
        boolean isIsRefundPaymentsEventsOnly();

        /**
         * Sets the value of the isRefundPaymentsEventsOnly property.
         * 
         */
        void setIsRefundPaymentsEventsOnly(boolean value);

        /**
         * Gets the value of the isRefundEventsOnly property.
         * 
         */
        boolean isIsRefundEventsOnly();

        /**
         * Sets the value of the isRefundEventsOnly property.
         * 
         */
        void setIsRefundEventsOnly(boolean value);

    }

}
