
package com.vizzavi.ecommerce.business.common.generated.country;

import java.io.Serializable;
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
 *         &lt;element ref="{}id"/>
 *         &lt;element ref="{}locale"/>
 *         &lt;element ref="{}language"/>
 *         &lt;element ref="{}IDD_prefix"/>
 *         &lt;element ref="{}currency_code"/>
 *         &lt;element ref="{}async_psps" minOccurs="0"/>
 *         &lt;element ref="{}sync_psps" minOccurs="0"/>
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
    "id",
    "locale",
    "language",
    "iddPrefix",
    "currencyCode",
    "asyncPsps",
    "syncPsps"
})
@XmlRootElement(name = "country")
public class Country
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected int id;
    @XmlElement(required = true)
    protected String locale;
    @XmlElement(required = true)
    protected String language;
    @XmlElement(name = "IDD_prefix")
    protected int iddPrefix;
    @XmlElement(name = "currency_code")
    protected int currencyCode;
    @XmlElement(name = "async_psps")
    protected Integer asyncPsps;
    @XmlElement(name = "sync_psps")
    protected Integer syncPsps;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the locale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocale(String value) {
        this.locale = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the iddPrefix property.
     * 
     */
    public int getIDDPrefix() {
        return iddPrefix;
    }

    /**
     * Sets the value of the iddPrefix property.
     * 
     */
    public void setIDDPrefix(int value) {
        this.iddPrefix = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     */
    public int getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     */
    public void setCurrencyCode(int value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the asyncPsps property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAsyncPsps() {
        return asyncPsps;
    }

    /**
     * Sets the value of the asyncPsps property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAsyncPsps(Integer value) {
        this.asyncPsps = value;
    }

    /**
     * Gets the value of the syncPsps property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSyncPsps() {
        return syncPsps;
    }

    /**
     * Sets the value of the syncPsps property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSyncPsps(Integer value) {
        this.syncPsps = value;
    }

}
