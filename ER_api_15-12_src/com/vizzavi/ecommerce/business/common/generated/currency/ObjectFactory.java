
package com.vizzavi.ecommerce.business.common.generated.currency;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vizzavi.ecommerce.business.common.generated.currency package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Symbol_QNAME = new QName("", "symbol");
    private final static QName _Description_QNAME = new QName("", "description");
    private final static QName _Name_QNAME = new QName("", "name");
    private final static QName _Code_QNAME = new QName("", "code");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vizzavi.ecommerce.business.common.generated.currency
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Currencies }
     * 
     */
    public Currencies createCurrencies() {
        return new Currencies();
    }

    /**
     * Create an instance of {@link Currency }
     * 
     */
    public Currency createCurrency() {
        return new Currency();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "symbol")
    public JAXBElement<String> createSymbol(String value) {
        return new JAXBElement<String>(_Symbol_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "code")
    public JAXBElement<Integer> createCode(Integer value) {
        return new JAXBElement<Integer>(_Code_QNAME, Integer.class, null, value);
    }

}
