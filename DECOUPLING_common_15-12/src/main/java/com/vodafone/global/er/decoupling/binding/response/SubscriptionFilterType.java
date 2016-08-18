
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for subscription-filterType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscription-filterType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subscription-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pre-order" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="charging-method" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="subscription-status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="recurring-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="non-recurring-events-only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="end-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="subscription-end-date-start-value" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="subscription-end-date-end-value" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="transactions-not-required" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="add-services" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="client-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="max-events" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="package-class" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="is-using-local-date" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="use-max-events-for-trans" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="package-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parent-package-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tariff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="include-txns" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscription-filterType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "subscriptionId",
    "preOrder",
    "chargingMethod",
    "subscriptionStatus",
    "recurringEventsOnly",
    "nonRecurringEventsOnly",
    "startDate",
    "endDate",
    "subscriptionEndDateStartValue",
    "subscriptionEndDateEndValue",
    "transactionsNotRequired",
    "addServices",
    "clientId",
    "maxEvents",
    "packageClass",
    "isUsingLocalDate",
    "useMaxEventsForTrans",
    "packageId",
    "partnerId",
    "parentPackageId",
    "tariff",
    "includeTxns"
})
public class SubscriptionFilterType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "subscription-id")
    protected String subscriptionId;
    @XmlElement(name = "pre-order")
    protected Integer preOrder;
    @XmlElement(name = "charging-method")
    protected Integer chargingMethod;
    @XmlElement(name = "subscription-status")
    protected Integer subscriptionStatus;
    @XmlElement(name = "recurring-events-only")
    protected Boolean recurringEventsOnly;
    @XmlElement(name = "non-recurring-events-only")
    protected Boolean nonRecurringEventsOnly;
    @XmlElement(name = "start-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar startDate;
    @XmlElement(name = "end-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar endDate;
    @XmlElement(name = "subscription-end-date-start-value", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar subscriptionEndDateStartValue;
    @XmlElement(name = "subscription-end-date-end-value", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar subscriptionEndDateEndValue;
    @XmlElement(name = "transactions-not-required")
    protected String transactionsNotRequired;
    @XmlElement(name = "add-services")
    protected Boolean addServices;
    @XmlElement(name = "client-id")
    protected String clientId;
    @XmlElement(name = "max-events")
    protected Integer maxEvents;
    @XmlElement(name = "package-class")
    protected String packageClass;
    @XmlElement(name = "is-using-local-date")
    protected Boolean isUsingLocalDate;
    @XmlElement(name = "use-max-events-for-trans")
    protected Boolean useMaxEventsForTrans;
    @XmlElement(name = "package-id")
    protected String packageId;
    @XmlElement(name = "partner-id")
    protected String partnerId;
    @XmlElement(name = "parent-package-id")
    protected String parentPackageId;
    protected String tariff;
    @XmlElement(name = "include-txns")
    protected String includeTxns;

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
     * Gets the value of the preOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getPreOrder() {
        if (preOrder == null) {
            return  0;
        } else {
            return preOrder;
        }
    }

    /**
     * Sets the value of the preOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPreOrder(Integer value) {
        this.preOrder = value;
    }

    /**
     * Gets the value of the chargingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getChargingMethod() {
        if (chargingMethod == null) {
            return  0;
        } else {
            return chargingMethod;
        }
    }

    /**
     * Sets the value of the chargingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChargingMethod(Integer value) {
        this.chargingMethod = value;
    }

    /**
     * Gets the value of the subscriptionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getSubscriptionStatus() {
        if (subscriptionStatus == null) {
            return  0;
        } else {
            return subscriptionStatus;
        }
    }

    /**
     * Sets the value of the subscriptionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSubscriptionStatus(Integer value) {
        this.subscriptionStatus = value;
    }

    /**
     * Gets the value of the recurringEventsOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isRecurringEventsOnly() {
        if (recurringEventsOnly == null) {
            return false;
        } else {
            return recurringEventsOnly;
        }
    }

    /**
     * Sets the value of the recurringEventsOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecurringEventsOnly(Boolean value) {
        this.recurringEventsOnly = value;
    }

    /**
     * Gets the value of the nonRecurringEventsOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isNonRecurringEventsOnly() {
        if (nonRecurringEventsOnly == null) {
            return false;
        } else {
            return nonRecurringEventsOnly;
        }
    }

    /**
     * Sets the value of the nonRecurringEventsOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonRecurringEventsOnly(Boolean value) {
        this.nonRecurringEventsOnly = value;
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
     * Gets the value of the subscriptionEndDateStartValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getSubscriptionEndDateStartValue() {
        return subscriptionEndDateStartValue;
    }

    /**
     * Sets the value of the subscriptionEndDateStartValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionEndDateStartValue(Calendar value) {
        this.subscriptionEndDateStartValue = value;
    }

    /**
     * Gets the value of the subscriptionEndDateEndValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getSubscriptionEndDateEndValue() {
        return subscriptionEndDateEndValue;
    }

    /**
     * Sets the value of the subscriptionEndDateEndValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionEndDateEndValue(Calendar value) {
        this.subscriptionEndDateEndValue = value;
    }

    /**
     * Gets the value of the transactionsNotRequired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionsNotRequired() {
        return transactionsNotRequired;
    }

    /**
     * Sets the value of the transactionsNotRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionsNotRequired(String value) {
        this.transactionsNotRequired = value;
    }

    /**
     * Gets the value of the addServices property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isAddServices() {
        if (addServices == null) {
            return false;
        } else {
            return addServices;
        }
    }

    /**
     * Sets the value of the addServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAddServices(Boolean value) {
        this.addServices = value;
    }

    /**
     * Gets the value of the clientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the value of the clientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientId(String value) {
        this.clientId = value;
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
     * Gets the value of the packageClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageClass() {
        return packageClass;
    }

    /**
     * Sets the value of the packageClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageClass(String value) {
        this.packageClass = value;
    }

    /**
     * Gets the value of the isUsingLocalDate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsUsingLocalDate() {
        if (isUsingLocalDate == null) {
            return false;
        } else {
            return isUsingLocalDate;
        }
    }

    /**
     * Sets the value of the isUsingLocalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsUsingLocalDate(Boolean value) {
        this.isUsingLocalDate = value;
    }

    /**
     * Gets the value of the useMaxEventsForTrans property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isUseMaxEventsForTrans() {
        if (useMaxEventsForTrans == null) {
            return false;
        } else {
            return useMaxEventsForTrans;
        }
    }

    /**
     * Sets the value of the useMaxEventsForTrans property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseMaxEventsForTrans(Boolean value) {
        this.useMaxEventsForTrans = value;
    }

    /**
     * Gets the value of the packageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageId() {
        return packageId;
    }

    /**
     * Sets the value of the packageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageId(String value) {
        this.packageId = value;
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
     * Gets the value of the parentPackageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentPackageId() {
        return parentPackageId;
    }

    /**
     * Sets the value of the parentPackageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentPackageId(String value) {
        this.parentPackageId = value;
    }

    /**
     * Gets the value of the tariff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTariff() {
        return tariff;
    }

    /**
     * Sets the value of the tariff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTariff(String value) {
        this.tariff = value;
    }

    /**
     * Gets the value of the includeTxns property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludeTxns() {
        return includeTxns;
    }

    /**
     * Sets the value of the includeTxns property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludeTxns(String value) {
        this.includeTxns = value;
    }

}
