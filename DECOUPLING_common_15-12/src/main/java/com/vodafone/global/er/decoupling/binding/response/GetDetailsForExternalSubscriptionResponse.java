
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="er-subscription-id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *           &lt;element name="end-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *           &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="package-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *         &lt;element name="reason-code" type="{http://localhost:8080/decoupling/schemas/common}reason-codeType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "msisdn",
    "erSubscriptionId",
    "endDate",
    "status",
    "packageId",
    "reasonCode"
})
@XmlRootElement(name = "get-details-for-external-subscription-response")
public class GetDetailsForExternalSubscriptionResponse
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected String msisdn;
    @XmlElement(name = "er-subscription-id")
    protected Long erSubscriptionId;
    @XmlElement(name = "end-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar endDate;
    protected Integer status;
    @XmlElement(name = "package-id")
    protected String packageId;
    @XmlElement(name = "reason-code")
    protected ReasonCodeType reasonCode;

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdn(String value) {
        this.msisdn = value;
    }

    /**
     * Gets the value of the erSubscriptionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getErSubscriptionId() {
        if (erSubscriptionId == null) {
            return  0L;
        } else {
            return erSubscriptionId;
        }
    }

    /**
     * Sets the value of the erSubscriptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setErSubscriptionId(Long value) {
        this.erSubscriptionId = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(Calendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getStatus() {
        if (status == null) {
            return  0;
        } else {
            return status;
        }
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * Gets the value of the packageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageId() {
        return packageId;
    }

    /**
     * Sets the value of the packageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageId(String value) {
        this.packageId = value;
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

}
