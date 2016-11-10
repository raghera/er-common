package com.vodafone.global.er.xdoclet;

import java.util.Properties;

import xdoclet.XDocletTagSupport;

/**
 *  XDoclet customization for vizzavi, inserts text for from/to object to/from datatype
 *  
 *   
 *  @author Shankar Sundaram
 *  @version $Revision: 0.2 
 */
public class TypeConverterTagsHandler extends XDocletTagSupport
{
    
    public String PrimitiveToObject(Properties input)
    {
        String dataType = (String) input.get("type");
        String value    = (String) input.get("value");
        
        String result = "<NOT_SUPPORTED>";
        
        if (dataType.equalsIgnoreCase("int"))
        {
            result = "new Integer("+ value + ")";
        }
        
        if (dataType.equalsIgnoreCase("float"))
        {
           result = "new Float("+ value + ")";  
        }
        
        if (dataType.equalsIgnoreCase("double"))
        {
            result = "new Double("+ value + ")";
        }
        
        if (dataType.equalsIgnoreCase("long"))
        {
            result = "new Long("+ value + ")";
        }
        
        if (dataType.equalsIgnoreCase("boolean"))
        {
            result = "new Boolean("+ value + ")";
        }
            
        return result;
    }
    

    public String PrimitiveEquivalentObject(Properties input)
    {
        String dataType = (String) input.get("type");
        
        
        String result = "<NOT_SUPPORTED>";
        
            
        if (dataType.equalsIgnoreCase("int"))
        {
            result = "java.lang.Integer";
        }
        
        if (dataType.equalsIgnoreCase("float"))
        {
           result = "java.lang.Float";  
        }
        
        if (dataType.equalsIgnoreCase("double"))
        {
            result = "java.lang.Double";
        }
        
        if (dataType.equalsIgnoreCase("long"))
        {
            result = "java.lang.Long";
        }
        
        if (dataType.equalsIgnoreCase("boolean"))
        {
            result = "java.lang.Boolean";
        }
            
        return result;
    }
    
}
