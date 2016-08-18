/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.5.2</a>, using an XML
 * Schema.
 * $Id: Countries.java,v 1.2 2012/07/31 16:52:34 matt.darwin Exp $
 */

package com.vizzavi.ecommerce.business.common.generated;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Vector;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Countries.
 * 
 * @version $Revision: 1.2 $ $Date: 2012/07/31 16:52:34 $
 */
public class Countries implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _countryList
     */
    private java.util.Vector _countryList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Countries() {
        super();
        _countryList = new Vector();
    } //-- com.vizzavi.ecommerce.business.common.generated.Countries()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addCountry
     * 
     * @param vCountry
     */
    public void addCountry(com.vizzavi.ecommerce.business.common.generated.Country vCountry)
        throws java.lang.IndexOutOfBoundsException
    {
        _countryList.addElement(vCountry);
    } //-- void addCountry(com.vizzavi.ecommerce.business.common.generated.Country) 

    /**
     * Method addCountry
     * 
     * @param index
     * @param vCountry
     */
    public void addCountry(int index, com.vizzavi.ecommerce.business.common.generated.Country vCountry)
        throws java.lang.IndexOutOfBoundsException
    {
        _countryList.insertElementAt(vCountry, index);
    } //-- void addCountry(int, com.vizzavi.ecommerce.business.common.generated.Country) 

    /**
     * Method enumerateCountry
     */
    public java.util.Enumeration enumerateCountry()
    {
        return _countryList.elements();
    } //-- java.util.Enumeration enumerateCountry() 

    /**
     * Method getCountry
     * 
     * @param index
     */
    public com.vizzavi.ecommerce.business.common.generated.Country getCountry(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _countryList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.vizzavi.ecommerce.business.common.generated.Country) _countryList.elementAt(index);
    } //-- com.vizzavi.ecommerce.business.common.generated.Country getCountry(int) 

    /**
     * Method getCountry
     */
    public com.vizzavi.ecommerce.business.common.generated.Country[] getCountry()
    {
        int size = _countryList.size();
        com.vizzavi.ecommerce.business.common.generated.Country[] mArray = new com.vizzavi.ecommerce.business.common.generated.Country[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.vizzavi.ecommerce.business.common.generated.Country) _countryList.elementAt(index);
        }
        return mArray;
    } //-- com.vizzavi.ecommerce.business.common.generated.Country[] getCountry() 

    /**
     * Method getCountryCount
     */
    public int getCountryCount()
    {
        return _countryList.size();
    } //-- int getCountryCount() 

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
     * Method removeAllCountry
     */
    public void removeAllCountry()
    {
        _countryList.removeAllElements();
    } //-- void removeAllCountry() 

    /**
     * Method removeCountry
     * 
     * @param index
     */
    public com.vizzavi.ecommerce.business.common.generated.Country removeCountry(int index)
    {
        java.lang.Object obj = _countryList.elementAt(index);
        _countryList.removeElementAt(index);
        return (com.vizzavi.ecommerce.business.common.generated.Country) obj;
    } //-- com.vizzavi.ecommerce.business.common.generated.Country removeCountry(int) 

    /**
     * Method setCountry
     * 
     * @param index
     * @param vCountry
     */
    public void setCountry(int index, com.vizzavi.ecommerce.business.common.generated.Country vCountry)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _countryList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _countryList.setElementAt(vCountry, index);
    } //-- void setCountry(int, com.vizzavi.ecommerce.business.common.generated.Country) 

    /**
     * Method setCountry
     * 
     * @param countryArray
     */
    public void setCountry(com.vizzavi.ecommerce.business.common.generated.Country[] countryArray)
    {
        //-- copy array
        _countryList.removeAllElements();
        for (int i = 0; i < countryArray.length; i++) {
            _countryList.addElement(countryArray[i]);
        }
    } //-- void setCountry(com.vizzavi.ecommerce.business.common.generated.Country) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.vizzavi.ecommerce.business.common.generated.Countries) Unmarshaller.unmarshal(com.vizzavi.ecommerce.business.common.generated.Countries.class, reader);
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
