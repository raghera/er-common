
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
 *         &lt;element name="express-package" type="{}expressPackage" maxOccurs="unbounded" minOccurs="0"/>
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
    "expressPackage"
})
@XmlRootElement(name = "express-packages")
public class ExpressPackages
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "express-package")
    protected List<ExpressPackage> expressPackage;

    /**
     * Gets the value of the expressPackage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expressPackage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpressPackage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExpressPackage }
     * 
     * 
     */
    public List<ExpressPackage> getExpressPackage() {
        if (expressPackage == null) {
            expressPackage = new ArrayList<ExpressPackage>();
        }
        return this.expressPackage;
    }

}
