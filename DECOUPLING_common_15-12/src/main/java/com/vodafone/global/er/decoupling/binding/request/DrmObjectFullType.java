
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drmObjectFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="drmObjectFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="drm-type" type="{http://localhost:8080/decoupling/schemas/common}drmTypeFullType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drmObjectFullType", propOrder = {
    "key",
    "id",
    "description",
    "drmType"
})
public class DrmObjectFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(name = "drm-type", required = true)
    protected DrmTypeFullType drmType;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
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

}
