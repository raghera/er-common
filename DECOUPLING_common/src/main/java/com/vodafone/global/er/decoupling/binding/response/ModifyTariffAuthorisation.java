
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
 *         &lt;element name="is-success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="reason-code" type="{http://localhost:8080/decoupling/schemas/common}reason-codeType"/>
 *         &lt;element name="purchase-tariff-authorisations" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="purchase-tariff-authorisation" type="{http://localhost:8080/decoupling/schemas/common}purchase-tariff-authorisationType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="inactivate-tariff-authorisations" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="inactivate-tariff-authorisation" type="{http://localhost:8080/decoupling/schemas/common}inactivate-tariff-authorisationType" maxOccurs="unbounded" minOccurs="0"/>
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
    "isSuccess",
    "reasonCode",
    "purchaseTariffAuthorisations",
    "inactivateTariffAuthorisations"
})
@XmlRootElement(name = "modify-tariff-authorisation")
public class ModifyTariffAuthorisation
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "is-success")
    protected boolean isSuccess;
    @XmlElement(name = "reason-code", required = true)
    protected ReasonCodeType reasonCode;
    @XmlElement(name = "purchase-tariff-authorisations")
    protected ModifyTariffAuthorisation.PurchaseTariffAuthorisations purchaseTariffAuthorisations;
    @XmlElement(name = "inactivate-tariff-authorisations")
    protected ModifyTariffAuthorisation.InactivateTariffAuthorisations inactivateTariffAuthorisations;

    /**
     * Gets the value of the isSuccess property.
     * 
     */
    public boolean isIsSuccess() {
        return isSuccess;
    }

    /**
     * Sets the value of the isSuccess property.
     * 
     */
    public void setIsSuccess(boolean value) {
        this.isSuccess = value;
    }

    /**
     * Gets the value of the reasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link ReasonCodeType }
     *     
     */
    public ReasonCodeType getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReasonCodeType }
     *     
     */
    public void setReasonCode(ReasonCodeType value) {
        this.reasonCode = value;
    }

    /**
     * Gets the value of the purchaseTariffAuthorisations property.
     * 
     * @return
     *     possible object is
     *     {@link ModifyTariffAuthorisation.PurchaseTariffAuthorisations }
     *     
     */
    public ModifyTariffAuthorisation.PurchaseTariffAuthorisations getPurchaseTariffAuthorisations() {
        return purchaseTariffAuthorisations;
    }

    /**
     * Sets the value of the purchaseTariffAuthorisations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModifyTariffAuthorisation.PurchaseTariffAuthorisations }
     *     
     */
    public void setPurchaseTariffAuthorisations(ModifyTariffAuthorisation.PurchaseTariffAuthorisations value) {
        this.purchaseTariffAuthorisations = value;
    }

    /**
     * Gets the value of the inactivateTariffAuthorisations property.
     * 
     * @return
     *     possible object is
     *     {@link ModifyTariffAuthorisation.InactivateTariffAuthorisations }
     *     
     */
    public ModifyTariffAuthorisation.InactivateTariffAuthorisations getInactivateTariffAuthorisations() {
        return inactivateTariffAuthorisations;
    }

    /**
     * Sets the value of the inactivateTariffAuthorisations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModifyTariffAuthorisation.InactivateTariffAuthorisations }
     *     
     */
    public void setInactivateTariffAuthorisations(ModifyTariffAuthorisation.InactivateTariffAuthorisations value) {
        this.inactivateTariffAuthorisations = value;
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
     *         &lt;element name="inactivate-tariff-authorisation" type="{http://localhost:8080/decoupling/schemas/common}inactivate-tariff-authorisationType" maxOccurs="unbounded" minOccurs="0"/>
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
        "inactivateTariffAuthorisation"
    })
    public static class InactivateTariffAuthorisations
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "inactivate-tariff-authorisation")
        protected List<InactivateTariffAuthorisationType> inactivateTariffAuthorisation;

        /**
         * Gets the value of the inactivateTariffAuthorisation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the inactivateTariffAuthorisation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInactivateTariffAuthorisation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link InactivateTariffAuthorisationType }
         * 
         * 
         */
        public List<InactivateTariffAuthorisationType> getInactivateTariffAuthorisation() {
            if (inactivateTariffAuthorisation == null) {
                inactivateTariffAuthorisation = new ArrayList<InactivateTariffAuthorisationType>();
            }
            return this.inactivateTariffAuthorisation;
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
     *         &lt;element name="purchase-tariff-authorisation" type="{http://localhost:8080/decoupling/schemas/common}purchase-tariff-authorisationType" maxOccurs="unbounded" minOccurs="0"/>
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
        "purchaseTariffAuthorisation"
    })
    public static class PurchaseTariffAuthorisations
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "purchase-tariff-authorisation")
        protected List<PurchaseTariffAuthorisationType> purchaseTariffAuthorisation;

        /**
         * Gets the value of the purchaseTariffAuthorisation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the purchaseTariffAuthorisation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPurchaseTariffAuthorisation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PurchaseTariffAuthorisationType }
         * 
         * 
         */
        public List<PurchaseTariffAuthorisationType> getPurchaseTariffAuthorisation() {
            if (purchaseTariffAuthorisation == null) {
                purchaseTariffAuthorisation = new ArrayList<PurchaseTariffAuthorisationType>();
            }
            return this.purchaseTariffAuthorisation;
        }

    }

}
