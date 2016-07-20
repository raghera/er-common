
package com.vodafone.global.er.decoupling.binding.response.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for subscription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pricepoint" type="{http://localhost:8080/decoupling/schemas/v2/common}pricepoint"/>
 *         &lt;element name="purchase-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="expiry-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="payment-transactions" type="{http://localhost:8080/decoupling/schemas/v2/common}payment-transactions" minOccurs="0"/>
 *         &lt;element name="usage-transactions" type="{http://localhost:8080/decoupling/schemas/v2/common}usage-transactions" minOccurs="0"/>
 *         &lt;element name="refund-transactions" type="{http://localhost:8080/decoupling/schemas/v2/common}refund-transactions" minOccurs="0"/>
 *         &lt;element name="modify-transactions" type="{http://localhost:8080/decoupling/schemas/v2/common}modify-transactions" minOccurs="0"/>
 *         &lt;element name="package-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="services" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="resource-balances" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/v2/common}resource-balance" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="external-sub-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="b2b-partner" type="{http://localhost:8080/decoupling/schemas/v2/common}b2bPartner" minOccurs="0"/>
 *         &lt;element name="parent-package-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="merchant-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remaining-renewals" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="min-sub-period-end" type="{http://localhost:8080/decoupling/schemas/v2/common}min-sub-period-end" minOccurs="0"/>
 *         &lt;element name="external-identifier1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-identifier2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-identifier3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="status" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscription", propOrder = {
    "pricepoint",
    "purchaseDate",
    "expiryDate",
    "paymentTransactions",
    "usageTransactions",
    "refundTransactions",
    "modifyTransactions",
    "packageId",
    "services",
    "resourceBalances",
    "externalSubId",
    "b2BPartner",
    "parentPackageId",
    "merchantName",
    "partnerId",
    "remainingRenewals",
    "minSubPeriodEnd",
    "externalIdentifier1",
    "externalIdentifier2",
    "externalIdentifier3"
})
public class Subscription
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected Pricepoint pricepoint;
    @XmlElement(name = "purchase-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar purchaseDate;
    @XmlElement(name = "expiry-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar expiryDate;
    @XmlElement(name = "payment-transactions")
    protected PaymentTransactions paymentTransactions;
    @XmlElement(name = "usage-transactions")
    protected UsageTransactions usageTransactions;
    @XmlElement(name = "refund-transactions")
    protected RefundTransactions refundTransactions;
    @XmlElement(name = "modify-transactions")
    protected ModifyTransactions modifyTransactions;
    @XmlElement(name = "package-id")
    protected String packageId;
    protected Subscription.Services services;
    @XmlElement(name = "resource-balances")
    protected Subscription.ResourceBalances resourceBalances;
    @XmlElement(name = "external-sub-id")
    protected String externalSubId;
    @XmlElement(name = "b2b-partner")
    protected B2BPartner b2BPartner;
    @XmlElement(name = "parent-package-id")
    protected String parentPackageId;
    @XmlElement(name = "merchant-name")
    protected String merchantName;
    @XmlElement(name = "partner-id")
    protected String partnerId;
    @XmlElement(name = "remaining-renewals")
    protected Integer remainingRenewals;
    @XmlElement(name = "min-sub-period-end")
    protected MinSubPeriodEnd minSubPeriodEnd;
    @XmlElement(name = "external-identifier1")
    protected String externalIdentifier1;
    @XmlElement(name = "external-identifier2")
    protected String externalIdentifier2;
    @XmlElement(name = "external-identifier3")
    protected String externalIdentifier3;
    @XmlAttribute(name = "id", required = true)
    protected long id;
    @XmlAttribute(name = "status")
    protected int status;

    /**
     * Gets the value of the pricepoint property.
     * 
     * @return
     *     possible object is
     *     {@link Pricepoint }
     *     
     */
    public Pricepoint getPricepoint() {
        return pricepoint;
    }

    /**
     * Sets the value of the pricepoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pricepoint }
     *     
     */
    public void setPricepoint(Pricepoint value) {
        this.pricepoint = value;
    }

    /**
     * Gets the value of the purchaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the value of the purchaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseDate(Calendar value) {
        this.purchaseDate = value;
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

    /**
     * Gets the value of the paymentTransactions property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTransactions }
     *     
     */
    public PaymentTransactions getPaymentTransactions() {
        return paymentTransactions;
    }

    /**
     * Sets the value of the paymentTransactions property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTransactions }
     *     
     */
    public void setPaymentTransactions(PaymentTransactions value) {
        this.paymentTransactions = value;
    }

    /**
     * Gets the value of the usageTransactions property.
     * 
     * @return
     *     possible object is
     *     {@link UsageTransactions }
     *     
     */
    public UsageTransactions getUsageTransactions() {
        return usageTransactions;
    }

    /**
     * Sets the value of the usageTransactions property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageTransactions }
     *     
     */
    public void setUsageTransactions(UsageTransactions value) {
        this.usageTransactions = value;
    }

    /**
     * Gets the value of the refundTransactions property.
     * 
     * @return
     *     possible object is
     *     {@link RefundTransactions }
     *     
     */
    public RefundTransactions getRefundTransactions() {
        return refundTransactions;
    }

    /**
     * Sets the value of the refundTransactions property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundTransactions }
     *     
     */
    public void setRefundTransactions(RefundTransactions value) {
        this.refundTransactions = value;
    }

    /**
     * Gets the value of the modifyTransactions property.
     * 
     * @return
     *     possible object is
     *     {@link ModifyTransactions }
     *     
     */
    public ModifyTransactions getModifyTransactions() {
        return modifyTransactions;
    }

    /**
     * Sets the value of the modifyTransactions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModifyTransactions }
     *     
     */
    public void setModifyTransactions(ModifyTransactions value) {
        this.modifyTransactions = value;
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
     * Gets the value of the services property.
     * 
     * @return
     *     possible object is
     *     {@link Subscription.Services }
     *     
     */
    public Subscription.Services getServices() {
        return services;
    }

    /**
     * Sets the value of the services property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subscription.Services }
     *     
     */
    public void setServices(Subscription.Services value) {
        this.services = value;
    }

    /**
     * Gets the value of the resourceBalances property.
     * 
     * @return
     *     possible object is
     *     {@link Subscription.ResourceBalances }
     *     
     */
    public Subscription.ResourceBalances getResourceBalances() {
        return resourceBalances;
    }

    /**
     * Sets the value of the resourceBalances property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subscription.ResourceBalances }
     *     
     */
    public void setResourceBalances(Subscription.ResourceBalances value) {
        this.resourceBalances = value;
    }

    /**
     * Gets the value of the externalSubId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalSubId() {
        return externalSubId;
    }

    /**
     * Sets the value of the externalSubId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalSubId(String value) {
        this.externalSubId = value;
    }

    /**
     * Gets the value of the b2BPartner property.
     * 
     * @return
     *     possible object is
     *     {@link B2BPartner }
     *     
     */
    public B2BPartner getB2BPartner() {
        return b2BPartner;
    }

    /**
     * Sets the value of the b2BPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link B2BPartner }
     *     
     */
    public void setB2BPartner(B2BPartner value) {
        this.b2BPartner = value;
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
     * Gets the value of the merchantName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * Sets the value of the merchantName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantName(String value) {
        this.merchantName = value;
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
     * Gets the value of the remainingRenewals property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getRemainingRenewals() {
        if (remainingRenewals == null) {
            return  0;
        } else {
            return remainingRenewals;
        }
    }

    /**
     * Sets the value of the remainingRenewals property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRemainingRenewals(Integer value) {
        this.remainingRenewals = value;
    }

    /**
     * Gets the value of the minSubPeriodEnd property.
     * 
     * @return
     *     possible object is
     *     {@link MinSubPeriodEnd }
     *     
     */
    public MinSubPeriodEnd getMinSubPeriodEnd() {
        return minSubPeriodEnd;
    }

    /**
     * Sets the value of the minSubPeriodEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link MinSubPeriodEnd }
     *     
     */
    public void setMinSubPeriodEnd(MinSubPeriodEnd value) {
        this.minSubPeriodEnd = value;
    }

    /**
     * Gets the value of the externalIdentifier1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalIdentifier1() {
        return externalIdentifier1;
    }

    /**
     * Sets the value of the externalIdentifier1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalIdentifier1(String value) {
        this.externalIdentifier1 = value;
    }

    /**
     * Gets the value of the externalIdentifier2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalIdentifier2() {
        return externalIdentifier2;
    }

    /**
     * Sets the value of the externalIdentifier2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalIdentifier2(String value) {
        this.externalIdentifier2 = value;
    }

    /**
     * Gets the value of the externalIdentifier3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalIdentifier3() {
        return externalIdentifier3;
    }

    /**
     * Sets the value of the externalIdentifier3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalIdentifier3(String value) {
        this.externalIdentifier3 = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/v2/common}resource-balance" maxOccurs="unbounded" minOccurs="0"/>
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
        "resourceBalance"
    })
    public static class ResourceBalances
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "resource-balance")
        protected List<ResourceBalance> resourceBalance;

        /**
         * Gets the value of the resourceBalance property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the resourceBalance property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getResourceBalance().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResourceBalance }
         * 
         * 
         */
        public List<ResourceBalance> getResourceBalance() {
            if (resourceBalance == null) {
                resourceBalance = new ArrayList<ResourceBalance>();
            }
            return this.resourceBalance;
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
     *         &lt;element name="service-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "serviceId"
    })
    public static class Services
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "service-id")
        protected List<String> serviceId;

        /**
         * Gets the value of the serviceId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the serviceId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServiceId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getServiceId() {
            if (serviceId == null) {
                serviceId = new ArrayList<String>();
            }
            return this.serviceId;
        }

    }

}
