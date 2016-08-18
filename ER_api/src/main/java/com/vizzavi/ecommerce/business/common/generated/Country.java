/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.5.2</a>, using an XML
 * Schema.
 * $Id: Country.java,v 1.2 2011/01/27 14:53:52 matt.darwin Exp $
 */

package com.vizzavi.ecommerce.business.common.generated;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Country.
 * 
 * @version $Revision: 1.2 $ $Date: 2011/01/27 14:53:52 $
 */
public class Country implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private int _id;

    /**
     * keeps track of state for field: _id
     */
    private boolean _has_id;

    /**
     * Field _locale
     */
    private java.lang.String _locale;

    /**
     * Field _language
     */
    private java.lang.String _language;

    /**
     * Field _IDD_prefix
     */
    private int _IDD_prefix;

    /**
     * keeps track of state for field: _IDD_prefix
     */
    private boolean _has_IDD_prefix;

    /**
     * Field _currency_code
     */
    private int _currency_code;

    /**
     * keeps track of state for field: _currency_code
     */
    private boolean _has_currency_code;

    /**
     * Field _async_psps
     */
    private int _async_psps;

    /**
     * keeps track of state for field: _async_psps
     */
    private boolean _has_async_psps;

    /**
     * Field _sync_psps
     */
    private int _sync_psps;

    /**
     * keeps track of state for field: _sync_psps
     */
    private boolean _has_sync_psps;


      //----------------/
     //- Constructors -/
    //----------------/

    public Country() {
        super();
    } //-- com.vizzavi.ecommerce.business.common.generated.Country()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteAsync_psps
     */
    public void deleteAsync_psps()
    {
        this._has_async_psps= false;
    } //-- void deleteAsync_psps() 

    /**
     * Method deleteSync_psps
     */
    public void deleteSync_psps()
    {
        this._has_sync_psps= false;
    } //-- void deleteSync_psps() 

    /**
     * Returns the value of field 'async_psps'.
     * 
     * @return the value of field 'async_psps'.
     */
    public int getAsync_psps()
    {
        return this._async_psps;
    } //-- int getAsync_psps() 

    /**
     * Returns the value of field 'currency_code'.
     * 
     * @return the value of field 'currency_code'.
     */
    public int getCurrency_code()
    {
        return this._currency_code;
    } //-- int getCurrency_code() 

    /**
     * Returns the value of field 'IDD_prefix'.
     * 
     * @return the value of field 'IDD_prefix'.
     */
    public int getIDD_prefix()
    {
        return this._IDD_prefix;
    } //-- int getIDD_prefix() 

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'id'.
     */
    public int getId()
    {
        return this._id;
    } //-- int getId() 

    /**
     * Returns the value of field 'language'.
     * 
     * @return the value of field 'language'.
     */
    public java.lang.String getLanguage()
    {
        return this._language;
    } //-- java.lang.String getLanguage() 

    /**
     * Returns the value of field 'locale'.
     * 
     * @return the value of field 'locale'.
     */
    public java.lang.String getLocale()
    {
        return this._locale;
    } //-- java.lang.String getLocale() 

    /**
     * Returns the value of field 'sync_psps'.
     * 
     * @return the value of field 'sync_psps'.
     */
    public int getSync_psps()
    {
        return this._sync_psps;
    } //-- int getSync_psps() 

    /**
     * Method hasAsync_psps
     */
    public boolean hasAsync_psps()
    {
        return this._has_async_psps;
    } //-- boolean hasAsync_psps() 

    /**
     * Method hasCurrency_code
     */
    public boolean hasCurrency_code()
    {
        return this._has_currency_code;
    } //-- boolean hasCurrency_code() 

    /**
     * Method hasIDD_prefix
     */
    public boolean hasIDD_prefix()
    {
        return this._has_IDD_prefix;
    } //-- boolean hasIDD_prefix() 

    /**
     * Method hasId
     */
    public boolean hasId()
    {
        return this._has_id;
    } //-- boolean hasId() 

    /**
     * Method hasSync_psps
     */
    public boolean hasSync_psps()
    {
        return this._has_sync_psps;
    } //-- boolean hasSync_psps() 

    /**
     * Method isValid
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'async_psps'.
     * 
     * @param async_psps the value of field 'async_psps'.
     */
    public void setAsync_psps(int async_psps)
    {
        this._async_psps = async_psps;
        this._has_async_psps = true;
    } //-- void setAsync_psps(int) 

    /**
     * Sets the value of field 'currency_code'.
     * 
     * @param currency_code the value of field 'currency_code'.
     */
    public void setCurrency_code(int currency_code)
    {
        this._currency_code = currency_code;
        this._has_currency_code = true;
    } //-- void setCurrency_code(int) 

    /**
     * Sets the value of field 'IDD_prefix'.
     * 
     * @param IDD_prefix the value of field 'IDD_prefix'.
     */
    public void setIDD_prefix(int IDD_prefix)
    {
        this._IDD_prefix = IDD_prefix;
        this._has_IDD_prefix = true;
    } //-- void setIDD_prefix(int) 

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(int id)
    {
        this._id = id;
        this._has_id = true;
    } //-- void setId(int) 

    /**
     * Sets the value of field 'language'.
     * 
     * @param language the value of field 'language'.
     */
    public void setLanguage(java.lang.String language)
    {
        this._language = language;
    } //-- void setLanguage(java.lang.String) 

    /**
     * Sets the value of field 'locale'.
     * 
     * @param locale the value of field 'locale'.
     */
    public void setLocale(java.lang.String locale)
    {
        this._locale = locale;
    } //-- void setLocale(java.lang.String) 

    /**
     * Sets the value of field 'sync_psps'.
     * 
     * @param sync_psps the value of field 'sync_psps'.
     */
    public void setSync_psps(int sync_psps)
    {
        this._sync_psps = sync_psps;
        this._has_sync_psps = true;
    } //-- void setSync_psps(int) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.vizzavi.ecommerce.business.common.generated.Country) Unmarshaller.unmarshal(com.vizzavi.ecommerce.business.common.generated.Country.class, reader);
    } //-- java.lang.Object unmarshal(java.io.Reader) 

    /**
     * Method validate
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
