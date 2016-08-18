package com.vizzavi.ecommerce.util;

import java.net.URL;
import java.util.Properties;

public class SearchClasspath
{
    static SearchClasspath sInstance = new SearchClasspath();
    
//    public SearchClasspath()
//    {
//        
//    }

    public static URL getResource(String name)
    {
        ClassLoader loader = sInstance.getClass().getClassLoader();
        URL resource = loader.getResource(name);
        
        return resource;
    }
    
    public static Properties getProperties(String name) throws java.io.IOException
    {
        URL url = SearchClasspath.getResource( name );
        Properties props = null;
        if( url != null ) {
            props = new Properties();
            props.load( url.openStream() );
        }
        return props;
    }
    
   
}