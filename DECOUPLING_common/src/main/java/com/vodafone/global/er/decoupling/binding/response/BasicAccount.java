//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 04:57:40 PM BST 
//


package com.vodafone.global.er.decoupling.binding.response;


/**
 * Java content class for basic-account element declaration.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/Users/matt/workspace/manifest-work/us/dev/er_core_batch/working/DECOUPLING_common/schemas/response/response.xsd line 690)
 * <p>
 * <pre>
 * &lt;element name="basic-account">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="ban" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="payment-card-details" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="issue-number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="start-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                     &lt;element name="expiry-date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="user-groups" maxOccurs="unbounded" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="spend-limits" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="per-tx-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                     &lt;element name="per-day-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                     &lt;element name="per-month-limit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                     &lt;element name="cumulative-spend-day" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                     &lt;element name="cumulative-spend-month" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 */
public interface BasicAccount
    extends javax.xml.bind.Element, com.vodafone.global.er.decoupling.binding.response.BasicAccountType
{


}
