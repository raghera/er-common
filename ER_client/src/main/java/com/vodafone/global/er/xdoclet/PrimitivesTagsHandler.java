package com.vodafone.global.er.xdoclet;

import java.util.Properties;

import xdoclet.XDocletTagSupport;

public class PrimitivesTagsHandler extends XDocletTagSupport {

    public PrimitivesTagsHandler() {}


    public String initPrimitive(Properties properties) {
    
        String type = (String)properties.get("type");
        String theString = "<NOT_SUPPORTED>";
        
        if (type.equals("int")) {
            theString = "0";
        }
        else if (type.equals("long")) {
            theString = "0";
        }
        else if (type.equals("double")) {
            theString = "0";
        }
        else if (type.equals("float")) {
            theString = "0";
        }
        else if (type.equals("boolean")) {
            theString = "false";
        }
        return theString;
    }
    
 
    public String writeObjectOutputStreamPrimitive(Properties properties) {
    
        String type = (String)properties.get("type");
        String theString = "<NOT_SUPPORTED>";
        
        if (type.equals("int")) {
            theString = "writeInt";
        }
        else if (type.equals("long")) {
            theString = "writeLong";
        }
        else if (type.equals("double")) {
            theString = "writeDouble";
        }
        else if (type.equals("float")) {
            theString = "writeFloat";
        }
        else if (type.equals("boolean")) {
            theString = "writeBoolean";
        }
        return theString;
    }
    
    
    public String readObjectOutputStreamPrimitive(Properties properties) {
    
        String type = (String)properties.get("type");
        String theString = "<NOT_SUPPORTED>";
                
        if (type.equals("int")) {
            theString = "readInt";
        }
        else if (type.equals("long")) {
            theString = "readLong";
        }
        else if (type.equals("double")) {
            theString = "readDouble";
        }
        else if (type.equals("float")) {
            theString = "readFloat";
        }
        else if (type.equals("boolean")) {
            theString = "readBoolean";
        }
        return theString;
    }
}    