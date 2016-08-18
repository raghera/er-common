
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ratingAttributesFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ratingAttributesFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ratingAttributesType" type="{http://localhost:8080/decoupling/schemas/common}ratingAttributesType" minOccurs="0"/>
 *         &lt;element name="charging-method" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="renewal-pre-rate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="user-groups" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="user-group" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ratingAttributesFullType", namespace = "http://localhost:8080/decoupling/schemas/common", propOrder = {
    "ratingAttributesType",
    "chargingMethod",
    "renewalPreRate",
    "userGroups"
})
public class RatingAttributesFullType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    protected RatingAttributesType ratingAttributesType;
    @XmlElement(name = "charging-method")
    protected Integer chargingMethod;
    @XmlElement(name = "renewal-pre-rate")
    protected Double renewalPreRate;
    @XmlElement(name = "user-groups")
    protected RatingAttributesFullType.UserGroups userGroups;

    /**
     * Gets the value of the ratingAttributesType property.
     * 
     * @return
     *     possible object is
     *     {@link RatingAttributesType }
     *     
     */
    public RatingAttributesType getRatingAttributesType() {
        return ratingAttributesType;
    }

    /**
     * Sets the value of the ratingAttributesType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RatingAttributesType }
     *     
     */
    public void setRatingAttributesType(RatingAttributesType value) {
        this.ratingAttributesType = value;
    }

    /**
     * Gets the value of the chargingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getChargingMethod() {
        if (chargingMethod == null) {
            return  0;
        } else {
            return chargingMethod;
        }
    }

    /**
     * Sets the value of the chargingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChargingMethod(Integer value) {
        this.chargingMethod = value;
    }

    /**
     * Gets the value of the renewalPreRate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getRenewalPreRate() {
        if (renewalPreRate == null) {
            return  0.0D;
        } else {
            return renewalPreRate;
        }
    }

    /**
     * Sets the value of the renewalPreRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRenewalPreRate(Double value) {
        this.renewalPreRate = value;
    }

    /**
     * Gets the value of the userGroups property.
     * 
     * @return
     *     possible object is
     *     {@link RatingAttributesFullType.UserGroups }
     *     
     */
    public RatingAttributesFullType.UserGroups getUserGroups() {
        return userGroups;
    }

    /**
     * Sets the value of the userGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link RatingAttributesFullType.UserGroups }
     *     
     */
    public void setUserGroups(RatingAttributesFullType.UserGroups value) {
        this.userGroups = value;
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
     *         &lt;element name="user-group" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "userGroup"
    })
    public static class UserGroups
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "user-group")
        protected List<String> userGroup;

        /**
         * Gets the value of the userGroup property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the userGroup property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUserGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getUserGroup() {
            if (userGroup == null) {
                userGroup = new ArrayList<String>();
            }
            return this.userGroup;
        }

    }

}
