
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="er-constant-group" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="er-constant" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="sub-value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "", propOrder = {
    "erConstantGroup"
})
@XmlRootElement(name = "er-constants")
public class ErConstants
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "er-constant-group")
    protected List<ErConstants.ErConstantGroup> erConstantGroup;

    /**
     * Gets the value of the erConstantGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the erConstantGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErConstantGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErConstants.ErConstantGroup }
     * 
     * 
     */
    public List<ErConstants.ErConstantGroup> getErConstantGroup() {
        if (erConstantGroup == null) {
            erConstantGroup = new ArrayList<ErConstants.ErConstantGroup>();
        }
        return this.erConstantGroup;
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
     *         &lt;element name="er-constant" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="sub-value" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "erConstant"
    })
    public static class ErConstantGroup
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "er-constant", required = true)
        protected List<ErConstants.ErConstantGroup.ErConstant> erConstant;
        @XmlAttribute(name = "type", required = true)
        protected String type;

        /**
         * Gets the value of the erConstant property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the erConstant property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getErConstant().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ErConstants.ErConstantGroup.ErConstant }
         * 
         * 
         */
        public List<ErConstants.ErConstantGroup.ErConstant> getErConstant() {
            if (erConstant == null) {
                erConstant = new ArrayList<ErConstants.ErConstantGroup.ErConstant>();
            }
            return this.erConstant;
        }

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
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
         *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="sub-value" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class ErConstant
            implements Serializable, Element
        {

            private final static long serialVersionUID = 1L;
            @XmlAttribute(name = "name", required = true)
            protected String name;
            @XmlAttribute(name = "value", required = true)
            protected String value;
            @XmlAttribute(name = "sub-value")
            protected String subValue;
            @XmlAttribute(name = "description")
            protected String description;

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
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the subValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSubValue() {
                return subValue;
            }

            /**
             * Sets the value of the subValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSubValue(String value) {
                this.subValue = value;
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

        }

    }

}
