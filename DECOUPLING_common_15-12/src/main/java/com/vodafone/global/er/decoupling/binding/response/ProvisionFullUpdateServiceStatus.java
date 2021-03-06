
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
 *         &lt;element name="update-service-status-authorisation" type="{http://localhost:8080/decoupling/schemas/common}updateServiceStatusAuthorisationFullType"/>
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
    "updateServiceStatusAuthorisation"
})
@XmlRootElement(name = "provision-full-update-service-status")
public class ProvisionFullUpdateServiceStatus
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "update-service-status-authorisation", required = true)
    protected UpdateServiceStatusAuthorisationFullType updateServiceStatusAuthorisation;

    /**
     * Gets the value of the updateServiceStatusAuthorisation property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateServiceStatusAuthorisationFullType }
     *     
     */
    public UpdateServiceStatusAuthorisationFullType getUpdateServiceStatusAuthorisation() {
        return updateServiceStatusAuthorisation;
    }

    /**
     * Sets the value of the updateServiceStatusAuthorisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateServiceStatusAuthorisationFullType }
     *     
     */
    public void setUpdateServiceStatusAuthorisation(UpdateServiceStatusAuthorisationFullType value) {
        this.updateServiceStatusAuthorisation = value;
    }

}
