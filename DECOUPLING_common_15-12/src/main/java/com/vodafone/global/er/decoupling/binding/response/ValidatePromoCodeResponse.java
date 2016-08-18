
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
 *         &lt;element name="packages">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="package" type="{http://localhost:8080/decoupling/schemas/common}packageType" maxOccurs="unbounded" minOccurs="0"/>
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
    "packages"
})
@XmlRootElement(name = "validate-promo-code-response")
public class ValidatePromoCodeResponse
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected ValidatePromoCodeResponse.Packages packages;

    /**
     * Gets the value of the packages property.
     * 
     * @return
     *     possible object is
     *     {@link ValidatePromoCodeResponse.Packages }
     *     
     */
    public ValidatePromoCodeResponse.Packages getPackages() {
        return packages;
    }

    /**
     * Sets the value of the packages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidatePromoCodeResponse.Packages }
     *     
     */
    public void setPackages(ValidatePromoCodeResponse.Packages value) {
        this.packages = value;
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
     *         &lt;element name="package" type="{http://localhost:8080/decoupling/schemas/common}packageType" maxOccurs="unbounded" minOccurs="0"/>
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
        protected List<PackageType> _package;

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
         * {@link PackageType }
         * 
         * 
         */
        public List<PackageType> getPackage() {
            if (_package == null) {
                _package = new ArrayList<PackageType>();
            }
            return this._package;
        }

    }

}
