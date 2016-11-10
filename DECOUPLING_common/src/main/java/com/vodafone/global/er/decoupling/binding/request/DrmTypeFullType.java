
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drmTypeFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="drmTypeFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is-drm" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="is-jdp" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drmTypeFullType", propOrder = {
    "code",
    "name",
    "isDrm",
    "isJdp"
})
public class DrmTypeFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected int code;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(name = "is-drm")
    protected boolean isDrm;
    @XmlElement(name = "is-jdp")
    protected boolean isJdp;

    /**
     * Gets the value of the code property.
     * 
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     */
    public void setCode(int value) {
        this.code = value;
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
     * Gets the value of the isDrm property.
     * 
     */
    public boolean isIsDrm() {
        return isDrm;
    }

    /**
     * Sets the value of the isDrm property.
     * 
     */
    public void setIsDrm(boolean value) {
        this.isDrm = value;
    }

    /**
     * Gets the value of the isJdp property.
     * 
     */
    public boolean isIsJdp() {
        return isJdp;
    }

    /**
     * Sets the value of the isJdp property.
     * 
     */
    public void setIsJdp(boolean value) {
        this.isJdp = value;
    }

}
