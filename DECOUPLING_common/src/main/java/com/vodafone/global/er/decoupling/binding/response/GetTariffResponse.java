
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
 *         &lt;element name="tariff-name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="package-ids" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="package-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
    "tariffName",
    "packageIds"
})
@XmlRootElement(name = "get-tariff-response")
public class GetTariffResponse
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "tariff-name", required = true)
    protected String tariffName;
    @XmlElement(name = "package-ids")
    protected GetTariffResponse.PackageIds packageIds;

    /**
     * Gets the value of the tariffName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTariffName() {
        return tariffName;
    }

    /**
     * Sets the value of the tariffName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTariffName(String value) {
        this.tariffName = value;
    }

    /**
     * Gets the value of the packageIds property.
     * 
     * @return
     *     possible object is
     *     {@link GetTariffResponse.PackageIds }
     *     
     */
    public GetTariffResponse.PackageIds getPackageIds() {
        return packageIds;
    }

    /**
     * Sets the value of the packageIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTariffResponse.PackageIds }
     *     
     */
    public void setPackageIds(GetTariffResponse.PackageIds value) {
        this.packageIds = value;
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
     *         &lt;element name="package-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
    public static class PackageIds
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "package-id")
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

}
