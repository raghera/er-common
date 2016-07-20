
package com.vodafone.global.er.decoupling.binding.response;

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
 *         &lt;element name="is-unique-promo-precode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "isUniquePromoPrecode"
})
@XmlRootElement(name = "is-unique-promo-precode-response")
public class IsUniquePromoPrecodeResponse
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "is-unique-promo-precode")
    protected boolean isUniquePromoPrecode;

    /**
     * Gets the value of the isUniquePromoPrecode property.
     * 
     */
    public boolean isIsUniquePromoPrecode() {
        return isUniquePromoPrecode;
    }

    /**
     * Sets the value of the isUniquePromoPrecode property.
     * 
     */
    public void setIsUniquePromoPrecode(boolean value) {
        this.isUniquePromoPrecode = value;
    }

}
