
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inactivate-tariff-authorisationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inactivate-tariff-authorisationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="is-success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="package-subscription-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inactivate-tariff-authorisationType", propOrder = {
    "isSuccess",
    "packageSubscriptionId"
})
public class InactivateTariffAuthorisationType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "is-success")
    protected boolean isSuccess;
    @XmlElement(name = "package-subscription-id", required = true)
    protected String packageSubscriptionId;

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
     * Gets the value of the packageSubscriptionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageSubscriptionId() {
        return packageSubscriptionId;
    }

    /**
     * Sets the value of the packageSubscriptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageSubscriptionId(String value) {
        this.packageSubscriptionId = value;
    }

}
