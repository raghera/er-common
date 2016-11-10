
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateServiceStatusAuthorisationFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateServiceStatusAuthorisationFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reason-code" type="{http://localhost:8080/decoupling/schemas/common}reason-codeType"/>
 *         &lt;element name="error-description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is-success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateServiceStatusAuthorisationFullType", propOrder = {
    "reasonCode",
    "errorDescription",
    "isSuccess"
})
public class UpdateServiceStatusAuthorisationFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "reason-code", required = true)
    protected ReasonCodeType reasonCode;
    @XmlElement(name = "error-description", required = true)
    protected String errorDescription;
    @XmlElement(name = "is-success")
    protected boolean isSuccess;

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

}
