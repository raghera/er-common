//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 04:57:40 PM BST 
//


package com.vodafone.global.er.decoupling.binding.response;


/**
 * Java content class for usage-authorisation element declaration.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch/working/DECOUPLING_common/schemas/response/response.xsd line 312)
 * <p>
 * <pre>
 * &lt;element name="usage-authorisation">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="is-reserved-only" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="interactive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="is-success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="package" type="{http://localhost:8080/decoupling/schemas/common}packageType"/>
 *           &lt;element name="price-point" type="{http://localhost:8080/decoupling/schemas/common}price-pointType"/>
 *           &lt;element name="service-price-point" type="{http://localhost:8080/decoupling/schemas/common}price-pointType"/>
 *           &lt;element name="user-resource-balance" type="{http://localhost:8080/decoupling/schemas/common}resource-BalanceType"/>
 *           &lt;element name="package-subscription-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="reason-code" type="{http://localhost:8080/decoupling/schemas/common}reason-codeType"/>
 *           &lt;element name="sub-reason-code" type="{http://localhost:8080/decoupling/schemas/common}reason-codeType"/>
 *           &lt;element name="receipting-credit-balance-impact" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *           &lt;element name="receipting-usage-type-attribute" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="transaction-id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="is-successful-express-purchase" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *           &lt;element name="drm-object" type="{http://localhost:8080/decoupling/schemas/common}drm-objectType"/>
 *           &lt;element name="error-description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="error-id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="payment-status" type="{http://localhost:8080/decoupling/schemas/common}paymentStatusType" minOccurs="0"/>
 *           &lt;element name="re-issue" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 */
public interface UsageAuthorisation
    extends javax.xml.bind.Element, com.vodafone.global.er.decoupling.binding.response.UsageAuthorisationType
{


}
