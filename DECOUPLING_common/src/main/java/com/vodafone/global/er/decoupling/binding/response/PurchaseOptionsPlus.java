
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="reason-code" type="{http://localhost:8080/decoupling/schemas/common}reasonCodeFullType"/>
 *         &lt;element name="sub-reason-code" type="{http://localhost:8080/decoupling/schemas/common}reasonCodeFullType"/>
 *         &lt;element name="super-credit-balances" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resourceBalanceFullType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="packages">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="package" type="{http://localhost:8080/decoupling/schemas/common}catalogPackageFullType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="has-promotions" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="promotional-packages" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="package-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="error-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error-description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "reasonCode",
    "subReasonCode",
    "superCreditBalances",
    "packages",
    "hasPromotions",
    "promotionalPackages",
    "errorId",
    "errorDescription"
})
@XmlRootElement(name = "purchase-options-plus")
public class PurchaseOptionsPlus
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "reason-code", required = true)
    protected ReasonCodeFullType reasonCode;
    @XmlElement(name = "sub-reason-code", required = true)
    protected ReasonCodeFullType subReasonCode;
    @XmlElement(name = "super-credit-balances")
    protected PurchaseOptionsPlus.SuperCreditBalances superCreditBalances;
    @XmlElement(required = true)
    protected PurchaseOptionsPlus.Packages packages;
    @XmlElement(name = "has-promotions")
    protected boolean hasPromotions;
    @XmlElement(name = "promotional-packages")
    protected PurchaseOptionsPlus.PromotionalPackages promotionalPackages;
    @XmlElement(name = "error-id")
    protected String errorId;
    @XmlElement(name = "error-description")
    protected String errorDescription;

    /**
     * Gets the value of the reasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link ReasonCodeFullType }
     *     
     */
    public ReasonCodeFullType getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReasonCodeFullType }
     *     
     */
    public void setReasonCode(ReasonCodeFullType value) {
        this.reasonCode = value;
    }

    /**
     * Gets the value of the subReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link ReasonCodeFullType }
     *     
     */
    public ReasonCodeFullType getSubReasonCode() {
        return subReasonCode;
    }

    /**
     * Sets the value of the subReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReasonCodeFullType }
     *     
     */
    public void setSubReasonCode(ReasonCodeFullType value) {
        this.subReasonCode = value;
    }

    /**
     * Gets the value of the superCreditBalances property.
     * 
     * @return
     *     possible object is
     *     {@link PurchaseOptionsPlus.SuperCreditBalances }
     *     
     */
    public PurchaseOptionsPlus.SuperCreditBalances getSuperCreditBalances() {
        return superCreditBalances;
    }

    /**
     * Sets the value of the superCreditBalances property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseOptionsPlus.SuperCreditBalances }
     *     
     */
    public void setSuperCreditBalances(PurchaseOptionsPlus.SuperCreditBalances value) {
        this.superCreditBalances = value;
    }

    /**
     * Gets the value of the packages property.
     * 
     * @return
     *     possible object is
     *     {@link PurchaseOptionsPlus.Packages }
     *     
     */
    public PurchaseOptionsPlus.Packages getPackages() {
        return packages;
    }

    /**
     * Sets the value of the packages property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseOptionsPlus.Packages }
     *     
     */
    public void setPackages(PurchaseOptionsPlus.Packages value) {
        this.packages = value;
    }

    /**
     * Gets the value of the hasPromotions property.
     * 
     */
    public boolean isHasPromotions() {
        return hasPromotions;
    }

    /**
     * Sets the value of the hasPromotions property.
     * 
     */
    public void setHasPromotions(boolean value) {
        this.hasPromotions = value;
    }

    /**
     * Gets the value of the promotionalPackages property.
     * 
     * @return
     *     possible object is
     *     {@link PurchaseOptionsPlus.PromotionalPackages }
     *     
     */
    public PurchaseOptionsPlus.PromotionalPackages getPromotionalPackages() {
        return promotionalPackages;
    }

    /**
     * Sets the value of the promotionalPackages property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseOptionsPlus.PromotionalPackages }
     *     
     */
    public void setPromotionalPackages(PurchaseOptionsPlus.PromotionalPackages value) {
        this.promotionalPackages = value;
    }

    /**
     * Gets the value of the errorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorId() {
        return errorId;
    }

    /**
     * Sets the value of the errorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorId(String value) {
        this.errorId = value;
    }

    /**
     * Gets the value of the errorDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * Sets the value of the errorDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDescription(String value) {
        this.errorDescription = value;
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
     *         &lt;element name="package" type="{http://localhost:8080/decoupling/schemas/common}catalogPackageFullType" maxOccurs="unbounded" minOccurs="0"/>
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
        "_package"
    })
    public static class Packages
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "package")
        protected List<CatalogPackageFullType> _package;

        /**
         * Gets the value of the package property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the package property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPackage().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CatalogPackageFullType }
         * 
         * 
         */
        public List<CatalogPackageFullType> getPackage() {
            if (_package == null) {
                _package = new ArrayList<CatalogPackageFullType>();
            }
            return this._package;
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
     *         &lt;element name="package-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
        "packageId"
    })
    public static class PromotionalPackages
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "package-id", required = true)
        protected List<String> packageId;

        /**
         * Gets the value of the packageId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the packageId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPackageId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getPackageId() {
            if (packageId == null) {
                packageId = new ArrayList<String>();
            }
            return this.packageId;
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
     *         &lt;element name="resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resourceBalanceFullType" maxOccurs="unbounded"/>
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
    public static class SuperCreditBalances
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "resource-balance", required = true)
        protected List<ResourceBalanceFullType> resourceBalance;

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
         * {@link ResourceBalanceFullType }
         * 
         * 
         */
        public List<ResourceBalanceFullType> getResourceBalance() {
            if (resourceBalance == null) {
                resourceBalance = new ArrayList<ResourceBalanceFullType>();
            }
            return this.resourceBalance;
        }

    }

}
