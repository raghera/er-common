package com.vizzavi.ecommerce.business.catalog.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vizzavi.ecommerce.business.catalog.OrderableComparator;
import com.vizzavi.ecommerce.business.catalog.Partner;

public class PartnerComparator implements OrderableComparator<Partner>
{
    private List<String>    mAttributes = new ArrayList<String>();
    private boolean mAscendingOrder = true;
    
    public static String ORDER_BY_ID = "id";
    public static String ORDER_BY_NAME = "name";
    
//    public PartnerComparator()
//    {
//    }
    
    @Override
	public int compare(Partner o1, Partner o2)
    {
        Partner s1 = o1;
        Partner s2 = o2;
        
        int rv = 0;
        if (mAttributes.size()==0) {
            mAttributes.add(ORDER_BY_ID);
        }

        Iterator<String> iter = mAttributes.iterator();
        
        while (iter.hasNext()) {
            String attribute = iter.next();
            
            if (attribute.equals(ORDER_BY_ID)) {
                rv = s1.getId().toLowerCase().compareTo(s2.getId().toLowerCase());
                if (rv!=0) {
                    break;
                }
            } else if (attribute.equals(ORDER_BY_NAME)) {
                rv = s1.getName().toLowerCase().compareTo(s2.getName().toLowerCase());
                if (rv!=0) {
                    break;
                }                
            }

        }
        
        if (!mAscendingOrder) {
            rv = rv * -1;
        }
        return rv;
    }
    
//    @Override
//	public boolean equals(Object obj)  
//     {	//er, this does not constitute over-riding the equals method...
//        return this.equals(obj);
//     }
    
    
    @Override
	public void orderBy(List<String> attributes) {
        mAttributes = attributes;
    }
    
    
    public void setOrderAscending(boolean val)
    {
        mAscendingOrder = val;
    }
}
