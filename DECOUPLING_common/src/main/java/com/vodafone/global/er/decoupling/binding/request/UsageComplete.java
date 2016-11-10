
package com.vodafone.global.er.decoupling.binding.request;

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
 *         &lt;element name="reservation-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usage-complete-attributes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="delivery-status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="event-units" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="aggregator-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="log-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "reservationId",
    "usageCompleteAttributes",
    "logId"
})
@XmlRootElement(name = "usage-complete", namespace = "")
public class UsageComplete
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "reservation-id", required = true)
    protected String reservationId;
    @XmlElement(name = "usage-complete-attributes", required = true)
    protected UsageComplete.UsageCompleteAttributes usageCompleteAttributes;
    @XmlElement(name = "log-id")
    protected String logId;

    /**
     * Gets the value of the reservationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservationId() {
        return reservationId;
    }

    /**
     * Sets the value of the reservationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservationId(String value) {
        this.reservationId = value;
    }

    /**
     * Gets the value of the usageCompleteAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link UsageComplete.UsageCompleteAttributes }
     *     
     */
    public UsageComplete.UsageCompleteAttributes getUsageCompleteAttributes() {
        return usageCompleteAttributes;
    }

    /**
     * Sets the value of the usageCompleteAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageComplete.UsageCompleteAttributes }
     *     
     */
    public void setUsageCompleteAttributes(UsageComplete.UsageCompleteAttributes value) {
        this.usageCompleteAttributes = value;
    }

    /**
     * Gets the value of the logId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogId() {
        return logId;
    }

    /**
     * Sets the value of the logId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogId(String value) {
        this.logId = value;
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
     *         &lt;element name="delivery-status" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="event-units" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="aggregator-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "deliveryStatus",
        "eventUnits",
        "partnerId",
        "aggregatorId"
    })
    public static class UsageCompleteAttributes
        implements Serializable, Element
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "delivery-status")
        protected int deliveryStatus;
        @XmlElement(name = "event-units")
        protected Double eventUnits;
        @XmlElement(name = "partner-id")
        protected String partnerId;
        @XmlElement(name = "aggregator-id")
        protected String aggregatorId;

        /**
         * Gets the value of the deliveryStatus property.
         * 
         */
        public int getDeliveryStatus() {
            return deliveryStatus;
        }

        /**
         * Sets the value of the deliveryStatus property.
         * 
         */
        public void setDeliveryStatus(int value) {
            this.deliveryStatus = value;
        }

        /**
         * Gets the value of the eventUnits property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public double getEventUnits() {
            if (eventUnits == null) {
                return  0.0D;
            } else {
                return eventUnits;
            }
        }

        /**
         * Sets the value of the eventUnits property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setEventUnits(Double value) {
            this.eventUnits = value;
        }

        /**
         * Gets the value of the partnerId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPartnerId() {
            return partnerId;
        }

        /**
         * Sets the value of the partnerId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPartnerId(String value) {
            this.partnerId = value;
        }

        /**
         * Gets the value of the aggregatorId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAggregatorId() {
            return aggregatorId;
        }

        /**
         * Sets the value of the aggregatorId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAggregatorId(String value) {
            this.aggregatorId = value;
        }

    }

}
