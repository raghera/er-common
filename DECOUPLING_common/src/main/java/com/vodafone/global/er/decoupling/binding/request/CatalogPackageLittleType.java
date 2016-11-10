
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for catalogPackageLittleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="catalogPackageLittleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="simple-package-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="package-type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="drm-type" type="{http://localhost:8080/decoupling/schemas/common}drmTypeFullType"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="rate-as-string" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="is-dynamic-default" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}pricePointLittleType" minOccurs="0"/>
 *         &lt;element name="price-points" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}pricePointLittleType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "catalogPackageLittleType", propOrder = {
    "id",
    "simplePackageId",
    "name",
    "packageType",
    "drmType",
    "rate",
    "rateAsString",
    "isDynamicDefault",
    "pricePoint",
    "pricePoints"
})
public class CatalogPackageLittleType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(name = "simple-package-id", required = true)
    protected String simplePackageId;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(name = "package-type", required = true)
    protected String packageType;
    @XmlElement(name = "drm-type", required = true)
    protected DrmTypeFullType drmType;
    protected Double rate;
    @XmlElement(name = "rate-as-string")
    protected String rateAsString;
    @XmlElement(name = "is-dynamic-default")
    protected Boolean isDynamicDefault;
    @XmlElement(name = "price-point")
    protected PricePointLittleType pricePoint;
    @XmlElement(name = "price-points")
    protected CatalogPackageLittleType.PricePoints pricePoints;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the simplePackageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimplePackageId() {
        return simplePackageId;
    }

    /**
     * Sets the value of the simplePackageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimplePackageId(String value) {
        this.simplePackageId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the packageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageType() {
        return packageType;
    }

    /**
     * Sets the value of the packageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageType(String value) {
        this.packageType = value;
    }

    /**
     * Gets the value of the drmType property.
     * 
     * @return
     *     possible object is
     *     {@link DrmTypeFullType }
     *     
     */
    public DrmTypeFullType getDrmType() {
        return drmType;
    }

    /**
     * Sets the value of the drmType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrmTypeFullType }
     *     
     */
    public void setDrmType(DrmTypeFullType value) {
        this.drmType = value;
    }

    /**
     * Gets the value of the rate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getRate() {
        if (rate == null) {
            return  0.0D;
        } else {
            return rate;
        }
    }

    /**
     * Sets the value of the rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRate(Double value) {
        this.rate = value;
    }

    /**
     * Gets the value of the rateAsString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateAsString() {
        return rateAsString;
    }

    /**
     * Sets the value of the rateAsString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateAsString(String value) {
        this.rateAsString = value;
    }

    /**
     * Gets the value of the isDynamicDefault property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsDynamicDefault() {
        if (isDynamicDefault == null) {
            return false;
        } else {
            return isDynamicDefault;
        }
    }

    /**
     * Sets the value of the isDynamicDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDynamicDefault(Boolean value) {
        this.isDynamicDefault = value;
    }

    /**
     * Gets the value of the pricePoint property.
     * 
     * @return
     *     possible object is
     *     {@link PricePointLittleType }
     *     
     */
    public PricePointLittleType getPricePoint() {
        return pricePoint;
    }

    /**
     * Sets the value of the pricePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePointLittleType }
     *     
     */
    public void setPricePoint(PricePointLittleType value) {
        this.pricePoint = value;
    }

    /**
     * Gets the value of the pricePoints property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogPackageLittleType.PricePoints }
     *     
     */
    public CatalogPackageLittleType.PricePoints getPricePoints() {
        return pricePoints;
    }

    /**
     * Sets the value of the pricePoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogPackageLittleType.PricePoints }
     *     
     */
    public void setPricePoints(CatalogPackageLittleType.PricePoints value) {
        this.pricePoints = value;
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
     *         &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}pricePointLittleType" maxOccurs="unbounded" minOccurs="0"/>
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
        "pricePoint"
    })
    public static class PricePoints
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "price-point")
        protected List<PricePointLittleType> pricePoint;

        /**
         * Gets the value of the pricePoint property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pricePoint property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPricePoint().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PricePointLittleType }
         * 
         * 
         */
        public List<PricePointLittleType> getPricePoint() {
            if (pricePoint == null) {
                pricePoint = new ArrayList<PricePointLittleType>();
            }
            return this.pricePoint;
        }

    }

}
