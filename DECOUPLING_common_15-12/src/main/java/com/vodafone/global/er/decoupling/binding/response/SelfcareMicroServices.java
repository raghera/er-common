
package com.vodafone.global.er.decoupling.binding.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="micro-service" type="{http://localhost:8080/decoupling/schemas/common}micro-service-type" maxOccurs="unbounded" minOccurs="0"/>
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
    "microService"
})
@XmlRootElement(name = "selfcare-micro-services")
public class SelfcareMicroServices
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "micro-service")
    protected List<MicroServiceType> microService;

    /**
     * Gets the value of the microService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the microService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMicroService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MicroServiceType }
     * 
     * 
     */
    public List<MicroServiceType> getMicroService() {
        if (microService == null) {
            microService = new ArrayList<MicroServiceType>();
        }
        return this.microService;
    }

}
