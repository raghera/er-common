
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for purchaseAttributesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="purchaseAttributesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="is-repurchase" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="force-reservation" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="end-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="resource-balances-only" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="resource-balance-only" type="{http://localhost:8080/decoupling/schemas/common}resource-balance-onlyType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="external-sub-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "purchaseAttributesType", propOrder = {
    "isRepurchase",
    "forceReservation",
    "status",
    "startDate",
    "endDate",
    "resourceBalancesOnly",
    "externalSubId"
})
public class PurchaseAttributesType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "is-repurchase")
    protected Boolean isRepurchase;
    @XmlElement(name = "force-reservation")
    protected Integer forceReservation;
    protected Integer status;
    @XmlElement(name = "start-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar startDate;
    @XmlElement(name = "end-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar endDate;
    @XmlElement(name = "resource-balances-only")
    protected PurchaseAttributesType.ResourceBalancesOnly resourceBalancesOnly;
    @XmlElement(name = "external-sub-id")
    protected String externalSubId;

    /**
     * Gets the value of the isRepurchase property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsRepurchase() {
        if (isRepurchase == null) {
            return false;
        } else {
            return isRepurchase;
        }
    }

    /**
     * Sets the value of the isRepurchase property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRepurchase(Boolean value) {
        this.isRepurchase = value;
    }

    /**
     * Gets the value of the forceReservation property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getForceReservation() {
        if (forceReservation == null) {
            return  0;
        } else {
            return forceReservation;
        }
    }

    /**
     * Sets the value of the forceReservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setForceReservation(Integer value) {
        this.forceReservation = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getStatus() {
        if (status == null) {
            return  0;
        } else {
            return status;
        }
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
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
     * Gets the value of the resourceBalancesOnly property.
     * 
     * @return
     *     possible object is
     *     {@link PurchaseAttributesType.ResourceBalancesOnly }
     *     
     */
    public PurchaseAttributesType.ResourceBalancesOnly getResourceBalancesOnly() {
        return resourceBalancesOnly;
    }

    /**
     * Sets the value of the resourceBalancesOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseAttributesType.ResourceBalancesOnly }
     *     
     */
    public void setResourceBalancesOnly(PurchaseAttributesType.ResourceBalancesOnly value) {
        this.resourceBalancesOnly = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="resource-balance-only" type="{http://localhost:8080/decoupling/schemas/common}resource-balance-onlyType" maxOccurs="unbounded"/>
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
        "resourceBalanceOnly"
    })
    public static class ResourceBalancesOnly
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "resource-balance-only", required = true)
        protected List<ResourceBalanceOnlyType> resourceBalanceOnly;

        /**
         * Gets the value of the resourceBalanceOnly property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the resourceBalanceOnly property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getResourceBalanceOnly().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResourceBalanceOnlyType }
         * 
         * 
         */
        public List<ResourceBalanceOnlyType> getResourceBalanceOnly() {
            if (resourceBalanceOnly == null) {
                resourceBalanceOnly = new ArrayList<ResourceBalanceOnlyType>();
            }
            return this.resourceBalanceOnly;
        }

    }

}
