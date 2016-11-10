package com.vizzavi.ecommerce.business.catalog.internal;

import java.util.Comparator;

public class StringComparator implements Comparator<String>
{
//    public StringComparator()
//    {
//    }
    
    @Override
	public int compare(String s1, String s2)
    {
       return s1.compareTo(s2);
    }
    
//    @Override
//	public boolean equals(Object obj)  
//     {
//        return this.equals(obj);
//     }
}
