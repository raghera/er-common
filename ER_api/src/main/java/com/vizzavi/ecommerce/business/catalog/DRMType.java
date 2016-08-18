package com.vizzavi.ecommerce.business.catalog;


/**
 * DRMType is an enumeration class to handle the 
 * various DRM types available. There are basically 3 types of DRMTypes
 * DRM - code 1 
 * JDP - code 2
 * NOT_DRM - code 0
 * @author DiasC
 * @since 5.1 
 */
public final class DRMType implements java.io.Serializable
{
   private    static final long serialVersionUID = -4207879280547428258L;
    public static final DRMType DRM_FLAG= new DRMType(1, "DRM");
    public static final DRMType JDP_FLAG= new DRMType(2, "JDP");
    public static final DRMType NOT_DRM= new DRMType(0, "NONE");

    private final int    m_iCode;
    private final String m_strName;

    /**
     * Constructor for the DRMType
     * @param code the internal code for the type
     * @param name the internal name for the type
     *
     */
    private DRMType(int code, String name)
    {
        m_iCode = code;
        m_strName = name;
    }

    /**
     * Method will convert from a string to a DRMType
     * This will be called from the Unmarshaller where the stored
     * package DRMType will be converted to a DRMType
     * @param name
     * @return DRMType
     */
    public static DRMType convertStringToDRMType(String name) throws Exception
    {
        if(name.equals(DRM_FLAG.getName()))
        {
            return DRM_FLAG;
        }
        else if (name.equals(JDP_FLAG.getName()))
        {
            return JDP_FLAG;
        }
        else if (name.equals(NOT_DRM.getName()))
        {
            return NOT_DRM;
        }
        else
        {
            throw new Exception("Not valid DRM type");
        }
    }

    /**
     * returns the internal code associated with this type
     * @param none
     * @return int the internal code associated with this type
     */
    public int getCode()
    {
        return m_iCode;
    }

    /**
     * returns the internal name associated with this type
     * @param none
     * @return String the internal name associated with this type
     */
    public String getName()
    {
        return m_strName;
    }

    /**
     * returns whether the type is a DRM Flag
     * @param void
     * @return boolean 
     */
    public boolean isDRM()
    {
        return (DRM_FLAG.getCode() == m_iCode);
    }

    /**
     * returns whether the type is a JDP Flag
     * @param void
     * @return boolean
     */
    public boolean isJDP()
    {
        return (JDP_FLAG.getCode() == m_iCode);
    }

    /**
     * returns whether the type is not a DRM Flag
     * @param void
     * @return boolean
     */
    public boolean isNotDRM()
    {
        return (NOT_DRM.getCode() == m_iCode);
    }

    public String toString()
    {
        return m_strName;
    }
}
