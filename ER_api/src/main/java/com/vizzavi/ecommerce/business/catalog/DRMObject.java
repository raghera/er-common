package com.vizzavi.ecommerce.business.catalog;


/**
 * DRMObject encapsulates the Digital Rights licence.
 * This class has 3 main attributes
 * The Licence Id
 * The Description
 * The DRM Type
 * @author DiasC
 * @since 5.1
 */
public class DRMObject implements java.io.Serializable
{
   private    static final long serialVersionUID = -7986500215473679133L;
    protected Long mKey;
    protected String m_strDRMId = "";
    protected String m_strDescription = "";
    protected DRMType m_DRMType;

    
    /**
     * Contructor
     */
    public DRMObject()
    {
    }

    /**
     * contructor for the DRMObject
     */
    public DRMObject(DRMObject drmobj)
    {
		mKey = drmobj.mKey;
        m_strDRMId = drmobj.m_strDRMId;
        m_strDescription = drmobj.m_strDescription;
        m_DRMType = drmobj.m_DRMType;
    }

    /**
     * Contructor
     */
    public DRMObject(String drmid, String description, DRMType drmtype )
    {
        m_strDRMId = drmid;
        m_strDescription = description;
        m_DRMType = drmtype;
    }

    /**
     * Contructor
     */
    public DRMObject(Long key, String drmid, String description, DRMType drmtype)
    {
		mKey = key;
        m_strDRMId = drmid;
        m_strDescription = description;
        m_DRMType = drmtype;
    }

    /**
     * Contructor
     */
	/*
    public DRMObject(Protected pProtected, DRMType drmtype)
    {
		mProtected = pProtected;
        //mKey = pProtected.getProtectedPk();
        m_strDRMId = pProtected.getId();
        m_strDescription = pProtected.getDescription();
        m_DRMType = drmtype;
    }
	*/

    /**
     * get the drm key associated with this object
     * @return Long
     */
    public Long getKey()
    {
        return mKey;
    }
     /* get the drm id associated with this object
     * @return String
     */
    public String getDRMId()
    {
        return m_strDRMId;
    }

    /**
     * get the description asscosiated with the rules object
     * @return String
     */
    public String getDescription()
    {
        return m_strDescription;
    }

    /**
     * get the DRM Type of the rules object
     * @return DRMType
     */
    public DRMType getDRMType()
    {
        return m_DRMType;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append("DRM key =" + mKey + "\n");
        sb.append("DRM Id =" + m_strDRMId + "\n");
        sb.append("Description =" + m_strDescription + "\n");

        return sb.toString();
    }
}
