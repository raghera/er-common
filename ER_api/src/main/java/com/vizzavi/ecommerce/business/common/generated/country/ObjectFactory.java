
package com.vizzavi.ecommerce.business.common.generated.country;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vizzavi.ecommerce.business.common.generated.country package. 
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

    private final static QName _Id_QNAME = new QName("", "id");
    private final static QName _SyncPsps_QNAME = new QName("", "sync_psps");
    private final static QName _IDDPrefix_QNAME = new QName("", "IDD_prefix");
    private final static QName _Locale_QNAME = new QName("", "locale");
    private final static QName _Language_QNAME = new QName("", "language");
    private final static QName _AsyncPsps_QNAME = new QName("", "async_psps");
    private final static QName _CurrencyCode_QNAME = new QName("", "currency_code");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vizzavi.ecommerce.business.common.generated.country
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Countries }
     * 
     */
    public Countries createCountries() {
        return new Countries();
    }

    /**
     * Create an instance of {@link Country }
     * 
     */
    public Country createCountry() {
        return new Country();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "id")
    public JAXBElement<Integer> createId(Integer value) {
        return new JAXBElement<Integer>(_Id_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sync_psps")
    public JAXBElement<Integer> createSyncPsps(Integer value) {
        return new JAXBElement<Integer>(_SyncPsps_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "IDD_prefix")
    public JAXBElement<Integer> createIDDPrefix(Integer value) {
        return new JAXBElement<Integer>(_IDDPrefix_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "locale")
    public JAXBElement<String> createLocale(String value) {
        return new JAXBElement<String>(_Locale_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "language")
    public JAXBElement<String> createLanguage(String value) {
        return new JAXBElement<String>(_Language_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "async_psps")
    public JAXBElement<Integer> createAsyncPsps(Integer value) {
        return new JAXBElement<Integer>(_AsyncPsps_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "currency_code")
    public JAXBElement<Integer> createCurrencyCode(Integer value) {
        return new JAXBElement<Integer>(_CurrencyCode_QNAME, Integer.class, null, value);
    }

}
