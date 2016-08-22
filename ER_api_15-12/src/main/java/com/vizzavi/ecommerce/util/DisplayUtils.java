package com.vizzavi.ecommerce.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DisplayUtils
{
    public static String displayMap(Map<?, ?> data)
    {
        StringBuffer buf = new StringBuffer();
        Set<?> keys =  data.keySet();
        Iterator<?> iter = keys.iterator();
        while (iter.hasNext()) {
            String name = iter.next().toString();
            Object value = data.get(name);
            buf.append(name);
            buf.append("=");
            buf.append(value);
            buf.append("\n");
        }
        
        return buf.toString();
    }

    public static String displayArray(Object[] data)
    {
        StringBuffer buf = new StringBuffer();
        for (Object element : data) {
            buf.append("\n\t");
            buf.append( element.toString() );
        }
        buf.append("\n");
        return buf.toString();
    }

    public static String displayList(List<Object> data)
    {
        StringBuffer buf = new StringBuffer();
        for(int i=0;i<data.size();i++) {
            buf.append("\n\t");
            buf.append( data.get(i).toString() );
        }
        buf.append("\n");
        return buf.toString();
    }

    public static String displayVector(List<Object> data)
    {
        StringBuffer buf = new StringBuffer();
        if( data != null ) {
            for(int i=0;i<data.size();i++) {
                buf.append("\n\t");
                buf.append( data.get(i).toString() );
            }
            buf.append("\n");
        }
        return buf.toString();
    }

}
