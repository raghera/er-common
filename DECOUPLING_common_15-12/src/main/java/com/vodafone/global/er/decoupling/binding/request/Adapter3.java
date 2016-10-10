
package com.vodafone.global.er.decoupling.binding.request;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter3
    extends XmlAdapter<String, Integer>
{


    public Integer unmarshal(String value) {
        return (com.vodafone.global.er.decoupling.util.xml.DatatypeConverter.parseInteger(value));
    }

    public String marshal(Integer value) {
        return (com.vodafone.global.er.decoupling.util.xml.DatatypeConverter.printInteger(value));
    }

}
