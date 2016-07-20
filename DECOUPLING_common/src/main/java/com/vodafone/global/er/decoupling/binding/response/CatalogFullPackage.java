
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
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
 *         &lt;element name="package" type="{http://localhost:8080/decoupling/schemas/common}catalogPackageFullType" minOccurs="0"/>
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
@XmlRootElement(name = "catalog-full-package")
public class CatalogFullPackage
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "package")
    protected CatalogPackageFullType _package;

    /**
     * Gets the value of the package property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogPackageFullType }
     *     
     */
    public CatalogPackageFullType getPackage() {
        return _package;
    }

    /**
     * Sets the value of the package property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogPackageFullType }
     *     
     */
    public void setPackage(CatalogPackageFullType value) {
        this._package = value;
    }

}
