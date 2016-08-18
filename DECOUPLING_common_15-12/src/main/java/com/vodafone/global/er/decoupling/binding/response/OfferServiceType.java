
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for offerServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="offerServiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;sequence>
 *           &lt;element name="pricepoint" type="{http://localhost:8080/decoupling/schemas/common}pricePointLiteType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="subscription" type="{http://localhost:8080/decoupling/schemas/common}subscriptionLiteType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "offerServiceType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "pricepointOrSubscription"
})
public class OfferServiceType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElements({
        @XmlElement(name = "pricepoint", type = PricePointLiteType.class),
        @XmlElement(name = "subscription", type = SubscriptionLiteType.class)
    })
    protected List<Element> pricepointOrSubscription;
    @XmlAttribute(name = "id", required = true)
    protected String id;

    /**
     * Gets the value of the pricepointOrSubscription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricepointOrSubscription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricepointOrSubscription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricePointLiteType }
     * {@link SubscriptionLiteType }
     * 
     * 
     */
    public List<Element> getPricepointOrSubscription() {
        if (pricepointOrSubscription == null) {
            pricepointOrSubscription = new ArrayList<Element>();
        }
        return this.pricepointOrSubscription;
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

}
