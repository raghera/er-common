
package com.vodafone.global.er.decoupling.binding.request;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for provisionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="provisionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UPDATE"/>
 *     &lt;enumeration value="ACTIVATE"/>
 *     &lt;enumeration value="DE-ACTIVATE"/>
 *     &lt;enumeration value="RE-ACTIVATE"/>
 *     &lt;enumeration value="RENEWAL-ACTIVATE"/>
 *     &lt;enumeration value="SUSPEND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "provisionType")
@XmlEnum
public enum ProvisionType {

    UPDATE("UPDATE"),
    ACTIVATE("ACTIVATE"),
    @XmlEnumValue("DE-ACTIVATE")
    DE_ACTIVATE("DE-ACTIVATE"),
    @XmlEnumValue("RE-ACTIVATE")
    RE_ACTIVATE("RE-ACTIVATE"),
    @XmlEnumValue("RENEWAL-ACTIVATE")
    RENEWAL_ACTIVATE("RENEWAL-ACTIVATE"),
    SUSPEND("SUSPEND");
    private final String value;

    ProvisionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProvisionType fromValue(String v) {
        for (ProvisionType c: ProvisionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
