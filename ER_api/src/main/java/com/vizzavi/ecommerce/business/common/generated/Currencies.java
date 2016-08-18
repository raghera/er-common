/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.5.2</a>, using an XML
 * Schema.
 * $Id: Currencies.java,v 1.3 2012/07/31 16:52:34 matt.darwin Exp $
 */

package com.vizzavi.ecommerce.business.common.generated;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Vector;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Currencies.
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/31 16:52:34 $
 */
public class Currencies implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _currencyList
     */
    private java.util.Vector _currencyList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Currencies() {
        super();
        _currencyList = new Vector();
    } //-- com.vizzavi.ecommerce.business.common.generated.Currencies()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addCurrency
     * 
     * @param vCurrency
     */
    public void addCurrency(com.vizzavi.ecommerce.business.common.generated.Currency vCurrency)
        throws java.lang.IndexOutOfBoundsException
    {
        _currencyList.addElement(vCurrency);
    } //-- void addCurrency(com.vizzavi.ecommerce.business.common.generated.Currency) 

    /**
     * Method addCurrency
     * 
     * @param index
     * @param vCurrency
     */
    public void addCurrency(int index, com.vizzavi.ecommerce.business.common.generated.Currency vCurrency)
        throws java.lang.IndexOutOfBoundsException
    {
        _currencyList.insertElementAt(vCurrency, index);
    } //-- void addCurrency(int, com.vizzavi.ecommerce.business.common.generated.Currency) 

    /**
     * Method enumerateCurrency
     */
    public java.util.Enumeration enumerateCurrency()
    {
        return _currencyList.elements();
    } //-- java.util.Enumeration enumerateCurrency() 

    /**
     * Method getCurrency
     * 
     * @param index
     */
    public com.vizzavi.ecommerce.business.common.generated.Currency getCurrency(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _currencyList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.vizzavi.ecommerce.business.common.generated.Currency) _currencyList.elementAt(index);
    } //-- com.vizzavi.ecommerce.business.common.generated.Currency getCurrency(int) 

    /**
     * Method getCurrency
     */
    public com.vizzavi.ecommerce.business.common.generated.Currency[] getCurrency()
    {
        int size = _currencyList.size();
        com.vizzavi.ecommerce.business.common.generated.Currency[] mArray = new com.vizzavi.ecommerce.business.common.generated.Currency[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.vizzavi.ecommerce.business.common.generated.Currency) _currencyList.elementAt(index);
        }
        return mArray;
    } //-- com.vizzavi.ecommerce.business.common.generated.Currency[] getCurrency() 

    /**
     * Method getCurrencyCount
     */
    public int getCurrencyCount()
    {
        return _currencyList.size();
    } //-- int getCurrencyCount() 

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
     * Method removeAllCurrency
     */
    public void removeAllCurrency()
    {
        _currencyList.removeAllElements();
    } //-- void removeAllCurrency() 

    /**
     * Method removeCurrency
     * 
     * @param index
     */
    public com.vizzavi.ecommerce.business.common.generated.Currency removeCurrency(int index)
    {
        java.lang.Object obj = _currencyList.elementAt(index);
        _currencyList.removeElementAt(index);
        return (com.vizzavi.ecommerce.business.common.generated.Currency) obj;
    } //-- com.vizzavi.ecommerce.business.common.generated.Currency removeCurrency(int) 

    /**
     * Method setCurrency
     * 
     * @param index
     * @param vCurrency
     */
    public void setCurrency(int index, com.vizzavi.ecommerce.business.common.generated.Currency vCurrency)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _currencyList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _currencyList.setElementAt(vCurrency, index);
    } //-- void setCurrency(int, com.vizzavi.ecommerce.business.common.generated.Currency) 

    /**
     * Method setCurrency
     * 
     * @param currencyArray
     */
    public void setCurrency(com.vizzavi.ecommerce.business.common.generated.Currency[] currencyArray)
    {
        //-- copy array
        _currencyList.removeAllElements();
        for (int i = 0; i < currencyArray.length; i++) {
            _currencyList.addElement(currencyArray[i]);
        }
    } //-- void setCurrency(com.vizzavi.ecommerce.business.common.generated.Currency) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.vizzavi.ecommerce.business.common.generated.Currencies) Unmarshaller.unmarshal(com.vizzavi.ecommerce.business.common.generated.Currencies.class, reader);
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
