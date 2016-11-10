
package com.vodafone.global.er.decoupling.binding.request;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ratingAttributesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ratingAttributesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="asset-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="device-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="device-type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="payment-type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="bearer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="event-date-time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="express-purchase-flag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-interactive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="promo-code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="device-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="access-channel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duration-code" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="duration" type="{http://localhost:8080/decoupling/schemas/common}durationType" minOccurs="0"/>
 *         &lt;element name="payment-information" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="network-code-string" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pre-rate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="renewal-pre-rate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="pre-rate-price-is-gross" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="purchase-channel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tax-rate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="has-tax-rate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="affiliate-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-field1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-field2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-trans-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tariff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aggregator-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recipient-msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="product-code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="merchant-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoice-text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="short-package-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tax-code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charging-method" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="user-group" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="has-historic-price-point-flag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="history-start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="vendor-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="csr-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content-category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-contact-info" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partner-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-identifier1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-identifier2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="external-identifier3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ratingAttributesType", propOrder = {
    "assetId",
    "contentName",
    "deviceId",
    "deviceType",
    "paymentType",
    "bearer",
    "eventDateTime",
    "expressPurchaseFlag",
    "isInteractive",
    "promoCode",
    "deviceName",
    "accessChannel",
    "durationCode",
    "duration",
    "paymentInformation",
    "networkCodeString",
    "preRate",
    "renewalPreRate",
    "preRatePriceIsGross",
    "purchaseChannel",
    "taxRate",
    "hasTaxRate",
    "affiliateId",
    "externalField1",
    "externalField2",
    "externalTransId",
    "tariff",
    "partnerId",
    "aggregatorId",
    "recipientMsisdn",
    "productCode",
    "merchantName",
    "invoiceText",
    "channel",
    "shortPackageId",
    "taxCode",
    "chargingMethod",
    "userGroup",
    "hasHistoricPricePointFlag",
    "historyStartDate",
    "vendorId",
    "csrId",
    "contentCategory",
    "partnerUrl",
    "partnerContactInfo",
    "partnerEmail",
    "partnerName",
    "externalIdentifier1",
    "externalIdentifier2",
    "externalIdentifier3"
})
public class RatingAttributesType
    implements Serializable, Element
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "asset-id")
    protected String assetId;
    @XmlElement(name = "content-name")
    protected String contentName;
    @XmlElement(name = "device-id")
    protected String deviceId;
    @XmlElement(name = "device-type")
    protected Integer deviceType;
    @XmlElement(name = "payment-type")
    protected Integer paymentType;
    protected String bearer;
    @XmlElement(name = "event-date-time", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar eventDateTime;
    @XmlElement(name = "express-purchase-flag")
    protected Boolean expressPurchaseFlag;
    @XmlElement(name = "is-interactive")
    protected Boolean isInteractive;
    @XmlElement(name = "promo-code")
    protected String promoCode;
    @XmlElement(name = "device-name")
    protected String deviceName;
    @XmlElement(name = "access-channel")
    protected String accessChannel;
    @XmlElement(name = "duration-code")
    protected Integer durationCode;
    protected DurationType duration;
    @XmlElement(name = "payment-information")
    protected String paymentInformation;
    @XmlElement(name = "network-code-string")
    protected String networkCodeString;
    @XmlElement(name = "pre-rate")
    protected Double preRate;
    @XmlElement(name = "renewal-pre-rate")
    protected Double renewalPreRate;
    @XmlElement(name = "pre-rate-price-is-gross")
    protected Boolean preRatePriceIsGross;
    @XmlElement(name = "purchase-channel")
    protected String purchaseChannel;
    @XmlElement(name = "tax-rate")
    protected Double taxRate;
    @XmlElement(name = "has-tax-rate")
    protected Boolean hasTaxRate;
    @XmlElement(name = "affiliate-id")
    protected String affiliateId;
    @XmlElement(name = "external-field1")
    protected String externalField1;
    @XmlElement(name = "external-field2")
    protected String externalField2;
    @XmlElement(name = "external-trans-id")
    protected String externalTransId;
    protected String tariff;
    @XmlElement(name = "partner-id")
    protected String partnerId;
    @XmlElement(name = "aggregator-id")
    protected String aggregatorId;
    @XmlElement(name = "recipient-msisdn")
    protected String recipientMsisdn;
    @XmlElement(name = "product-code")
    protected String productCode;
    @XmlElement(name = "merchant-name")
    protected String merchantName;
    @XmlElement(name = "invoice-text")
    protected String invoiceText;
    protected Integer channel;
    @XmlElement(name = "short-package-id")
    protected String shortPackageId;
    @XmlElement(name = "tax-code")
    protected String taxCode;
    @XmlElement(name = "charging-method")
    protected Integer chargingMethod;
    @XmlElement(name = "user-group")
    protected String userGroup;
    @XmlElement(name = "has-historic-price-point-flag")
    protected Boolean hasHistoricPricePointFlag;
    @XmlElement(name = "history-start-date", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar historyStartDate;
    @XmlElement(name = "vendor-id")
    protected String vendorId;
    @XmlElement(name = "csr-id")
    protected String csrId;
    @XmlElement(name = "content-category")
    protected String contentCategory;
    @XmlElement(name = "partner-url")
    protected String partnerUrl;
    @XmlElement(name = "partner-contact-info")
    protected String partnerContactInfo;
    @XmlElement(name = "partner-email")
    protected String partnerEmail;
    @XmlElement(name = "partner-name")
    protected String partnerName;
    @XmlElement(name = "external-identifier1")
    protected String externalIdentifier1;
    @XmlElement(name = "external-identifier2")
    protected String externalIdentifier2;
    @XmlElement(name = "external-identifier3")
    protected String externalIdentifier3;

    /**
     * Gets the value of the assetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * Sets the value of the assetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssetId(String value) {
        this.assetId = value;
    }

    /**
     * Gets the value of the contentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentName() {
        return contentName;
    }

    /**
     * Sets the value of the contentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentName(String value) {
        this.contentName = value;
    }

    /**
     * Gets the value of the deviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the value of the deviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Gets the value of the deviceType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getDeviceType() {
        if (deviceType == null) {
            return  0;
        } else {
            return deviceType;
        }
    }

    /**
     * Sets the value of the deviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeviceType(Integer value) {
        this.deviceType = value;
    }

    /**
     * Gets the value of the paymentType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getPaymentType() {
        if (paymentType == null) {
            return  0;
        } else {
            return paymentType;
        }
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPaymentType(Integer value) {
        this.paymentType = value;
    }

    /**
     * Gets the value of the bearer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBearer() {
        return bearer;
    }

    /**
     * Sets the value of the bearer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBearer(String value) {
        this.bearer = value;
    }

    /**
     * Gets the value of the eventDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getEventDateTime() {
        return eventDateTime;
    }

    /**
     * Sets the value of the eventDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventDateTime(Calendar value) {
        this.eventDateTime = value;
    }

    /**
     * Gets the value of the expressPurchaseFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isExpressPurchaseFlag() {
        if (expressPurchaseFlag == null) {
            return false;
        } else {
            return expressPurchaseFlag;
        }
    }

    /**
     * Sets the value of the expressPurchaseFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExpressPurchaseFlag(Boolean value) {
        this.expressPurchaseFlag = value;
    }

    /**
     * Gets the value of the isInteractive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsInteractive() {
        if (isInteractive == null) {
            return false;
        } else {
            return isInteractive;
        }
    }

    /**
     * Sets the value of the isInteractive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsInteractive(Boolean value) {
        this.isInteractive = value;
    }

    /**
     * Gets the value of the promoCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromoCode() {
        return promoCode;
    }

    /**
     * Sets the value of the promoCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromoCode(String value) {
        this.promoCode = value;
    }

    /**
     * Gets the value of the deviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Sets the value of the deviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceName(String value) {
        this.deviceName = value;
    }

    /**
     * Gets the value of the accessChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessChannel() {
        return accessChannel;
    }

    /**
     * Sets the value of the accessChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessChannel(String value) {
        this.accessChannel = value;
    }

    /**
     * Gets the value of the durationCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getDurationCode() {
        if (durationCode == null) {
            return  0;
        } else {
            return durationCode;
        }
    }

    /**
     * Sets the value of the durationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDurationCode(Integer value) {
        this.durationCode = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link DurationType }
     *     
     */
    public DurationType getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link DurationType }
     *     
     */
    public void setDuration(DurationType value) {
        this.duration = value;
    }

    /**
     * Gets the value of the paymentInformation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentInformation() {
        return paymentInformation;
    }

    /**
     * Sets the value of the paymentInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentInformation(String value) {
        this.paymentInformation = value;
    }

    /**
     * Gets the value of the networkCodeString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkCodeString() {
        return networkCodeString;
    }

    /**
     * Sets the value of the networkCodeString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkCodeString(String value) {
        this.networkCodeString = value;
    }

    /**
     * Gets the value of the preRate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getPreRate() {
        if (preRate == null) {
            return  0.0D;
        } else {
            return preRate;
        }
    }

    /**
     * Sets the value of the preRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPreRate(Double value) {
        this.preRate = value;
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
     * Gets the value of the preRatePriceIsGross property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPreRatePriceIsGross() {
        if (preRatePriceIsGross == null) {
            return false;
        } else {
            return preRatePriceIsGross;
        }
    }

    /**
     * Sets the value of the preRatePriceIsGross property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPreRatePriceIsGross(Boolean value) {
        this.preRatePriceIsGross = value;
    }

    /**
     * Gets the value of the purchaseChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseChannel() {
        return purchaseChannel;
    }

    /**
     * Sets the value of the purchaseChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseChannel(String value) {
        this.purchaseChannel = value;
    }

    /**
     * Gets the value of the taxRate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getTaxRate() {
        if (taxRate == null) {
            return  0.0D;
        } else {
            return taxRate;
        }
    }

    /**
     * Sets the value of the taxRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTaxRate(Double value) {
        this.taxRate = value;
    }

    /**
     * Gets the value of the hasTaxRate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isHasTaxRate() {
        if (hasTaxRate == null) {
            return false;
        } else {
            return hasTaxRate;
        }
    }

    /**
     * Sets the value of the hasTaxRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasTaxRate(Boolean value) {
        this.hasTaxRate = value;
    }

    /**
     * Gets the value of the affiliateId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAffiliateId() {
        return affiliateId;
    }

    /**
     * Sets the value of the affiliateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAffiliateId(String value) {
        this.affiliateId = value;
    }

    /**
     * Gets the value of the externalField1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalField1() {
        return externalField1;
    }

    /**
     * Sets the value of the externalField1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalField1(String value) {
        this.externalField1 = value;
    }

    /**
     * Gets the value of the externalField2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalField2() {
        return externalField2;
    }

    /**
     * Sets the value of the externalField2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalField2(String value) {
        this.externalField2 = value;
    }

    /**
     * Gets the value of the externalTransId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalTransId() {
        return externalTransId;
    }

    /**
     * Sets the value of the externalTransId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalTransId(String value) {
        this.externalTransId = value;
    }

    /**
     * Gets the value of the tariff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTariff() {
        return tariff;
    }

    /**
     * Sets the value of the tariff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTariff(String value) {
        this.tariff = value;
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

    /**
     * Gets the value of the recipientMsisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientMsisdn() {
        return recipientMsisdn;
    }

    /**
     * Sets the value of the recipientMsisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientMsisdn(String value) {
        this.recipientMsisdn = value;
    }

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the merchantName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * Sets the value of the merchantName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantName(String value) {
        this.merchantName = value;
    }

    /**
     * Gets the value of the invoiceText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceText() {
        return invoiceText;
    }

    /**
     * Sets the value of the invoiceText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceText(String value) {
        this.invoiceText = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getChannel() {
        if (channel == null) {
            return  0;
        } else {
            return channel;
        }
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setChannel(Integer value) {
        this.channel = value;
    }

    /**
     * Gets the value of the shortPackageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortPackageId() {
        return shortPackageId;
    }

    /**
     * Sets the value of the shortPackageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortPackageId(String value) {
        this.shortPackageId = value;
    }

    /**
     * Gets the value of the taxCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * Sets the value of the taxCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxCode(String value) {
        this.taxCode = value;
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
     * Gets the value of the userGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserGroup() {
        return userGroup;
    }

    /**
     * Sets the value of the userGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserGroup(String value) {
        this.userGroup = value;
    }

    /**
     * Gets the value of the hasHistoricPricePointFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isHasHistoricPricePointFlag() {
        if (hasHistoricPricePointFlag == null) {
            return false;
        } else {
            return hasHistoricPricePointFlag;
        }
    }

    /**
     * Sets the value of the hasHistoricPricePointFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasHistoricPricePointFlag(Boolean value) {
        this.hasHistoricPricePointFlag = value;
    }

    /**
     * Gets the value of the historyStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getHistoryStartDate() {
        return historyStartDate;
    }

    /**
     * Sets the value of the historyStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHistoryStartDate(Calendar value) {
        this.historyStartDate = value;
    }

    /**
     * Gets the value of the vendorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorId() {
        return vendorId;
    }

    /**
     * Sets the value of the vendorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorId(String value) {
        this.vendorId = value;
    }

    /**
     * Gets the value of the csrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsrId() {
        return csrId;
    }

    /**
     * Sets the value of the csrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsrId(String value) {
        this.csrId = value;
    }

    /**
     * Gets the value of the contentCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentCategory() {
        return contentCategory;
    }

    /**
     * Sets the value of the contentCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentCategory(String value) {
        this.contentCategory = value;
    }

    /**
     * Gets the value of the partnerUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerUrl() {
        return partnerUrl;
    }

    /**
     * Sets the value of the partnerUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerUrl(String value) {
        this.partnerUrl = value;
    }

    /**
     * Gets the value of the partnerContactInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerContactInfo() {
        return partnerContactInfo;
    }

    /**
     * Sets the value of the partnerContactInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerContactInfo(String value) {
        this.partnerContactInfo = value;
    }

    /**
     * Gets the value of the partnerEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerEmail() {
        return partnerEmail;
    }

    /**
     * Sets the value of the partnerEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerEmail(String value) {
        this.partnerEmail = value;
    }

    /**
     * Gets the value of the partnerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * Sets the value of the partnerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerName(String value) {
        this.partnerName = value;
    }

    /**
     * Gets the value of the externalIdentifier1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalIdentifier1() {
        return externalIdentifier1;
    }

    /**
     * Sets the value of the externalIdentifier1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalIdentifier1(String value) {
        this.externalIdentifier1 = value;
    }

    /**
     * Gets the value of the externalIdentifier2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalIdentifier2() {
        return externalIdentifier2;
    }

    /**
     * Sets the value of the externalIdentifier2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalIdentifier2(String value) {
        this.externalIdentifier2 = value;
    }

    /**
     * Gets the value of the externalIdentifier3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalIdentifier3() {
        return externalIdentifier3;
    }

    /**
     * Sets the value of the externalIdentifier3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalIdentifier3(String value) {
        this.externalIdentifier3 = value;
    }

}
