/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.5.2</a>, using an XML
 * Schema.
 * $Id: Currency.java,v 1.3 2012/07/31 16:52:34 matt.darwin Exp $
 */

package com.vizzavi.ecommerce.business.common.generated;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Currency.
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/31 16:52:34 $
 */
public class Currency implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _code
     */
    private int _code;

    /**
     * keeps track of state for field: _code
     */
    private boolean _has_code;

    /**
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _symbol
     */
    private java.lang.String _symbol;

    /**
     * Field _description
     */
    private java.lang.String _description;


      //----------------/
     //- Constructors -/
    //----------------/

    public Currency() {
        super();
    } //-- com.vizzavi.ecommerce.business.common.generated.Currency()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'code'.
     * 
     * @return the value of field 'code'.
     */
    public int getCode()
    {
        return this._code;
    } //-- int getCode() 

    /**
     * Returns the value of field 'description'.
     * 
     * @return the value of field 'description'.
     */
    public java.lang.String getDescription()
    {
        return this._description;
    } //-- java.lang.String getDescription() 

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'name'.
     */
    public java.lang.String getName()
    {
        return this._name;
    } //-- java.lang.String getName() 

    /**
     * Returns the value of field 'symbol'.
     * 
     * @return the value of field 'symbol'.
     */
    public java.lang.String getSymbol()
    {
        return this._symbol;
    } //-- java.lang.String getSymbol() 

    /**
     * Method hasCode
     */
    public boolean hasCode()
    {
        return this._has_code;
    } //-- boolean hasCode() 

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
     * Sets the value of field 'code'.
     * 
     * @param code the value of field 'code'.
     */
    public void setCode(int code)
    {
        this._code = code;
        this._has_code = true;
    } //-- void setCode(int) 

    /**
     * Sets the value of field 'description'.
     * 
     * @param description the value of field 'description'.
     */
    public void setDescription(java.lang.String description)
    {
        this._description = description;
    } //-- void setDescription(java.lang.String) 

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(java.lang.String name)
    {
        this._name = name;
    } //-- void setName(java.lang.String) 

    /**
     * Sets the value of field 'symbol'.
     * 
     * @param symbol the value of field 'symbol'.
     */
    public void setSymbol(java.lang.String symbol)
    {
        this._symbol = symbol;
    } //-- void setSymbol(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.vizzavi.ecommerce.business.common.generated.Currency) Unmarshaller.unmarshal(com.vizzavi.ecommerce.business.common.generated.Currency.class, reader);
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
