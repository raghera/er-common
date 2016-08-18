//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.03 at 03:16:51 PM BST 
//


package com.vodafone.global.er.decoupling.binding.request;


/**
 * Java content class for ratingAttributesFullType complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch_matt/working/DECOUPLING_common/schemas/common/pa_common.xsd line 982)
 * <p>
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
 */
public interface RatingAttributesFullType {


    /**
     * Gets the value of the chargingMethod property.
     * 
     */
    int getChargingMethod();

    /**
     * Sets the value of the chargingMethod property.
     * 
     */
    void setChargingMethod(int value);

    /**
     * Gets the value of the ratingAttributesType property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.RatingAttributesType}
     */
    com.vodafone.global.er.decoupling.binding.request.RatingAttributesType getRatingAttributesType();

    /**
     * Sets the value of the ratingAttributesType property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.RatingAttributesType}
     */
    void setRatingAttributesType(com.vodafone.global.er.decoupling.binding.request.RatingAttributesType value);

    /**
     * Gets the value of the renewalPreRate property.
     * 
     */
    double getRenewalPreRate();

    /**
     * Sets the value of the renewalPreRate property.
     * 
     */
    void setRenewalPreRate(double value);

    /**
     * Gets the value of the userGroups property.
     * 
     * @return
     *     possible object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.RatingAttributesFullType.UserGroupsType}
     */
    com.vodafone.global.er.decoupling.binding.request.RatingAttributesFullType.UserGroupsType getUserGroups();

    /**
     * Sets the value of the userGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.vodafone.global.er.decoupling.binding.request.RatingAttributesFullType.UserGroupsType}
     */
    void setUserGroups(com.vodafone.global.er.decoupling.binding.request.RatingAttributesFullType.UserGroupsType value);


    /**
     * Java content class for anonymous complex type.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch_matt/working/DECOUPLING_common/schemas/common/pa_common.xsd line 991)
     * <p>
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
     */
    public interface UserGroupsType {


        /**
         * Gets the value of the UserGroup property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the UserGroup property.
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
         * {@link java.lang.String}
         * 
         */
        java.util.List getUserGroup();

    }

}
