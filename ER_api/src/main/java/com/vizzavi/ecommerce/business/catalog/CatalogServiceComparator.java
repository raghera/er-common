package com.vizzavi.ecommerce.business.catalog;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CatalogServiceComparator implements OrderableComparator
{
	private List<String>    mAttributes = new ArrayList<String>();
	private boolean mAscendingOrder = true;

	public final static String ORDER_BY_ID = "id";
	public final static String ORDER_BY_NAME = "name";

	public CatalogServiceComparator()
	{
	}

	@Override
	public int compare(Object o1, Object o2)
	{
		CatalogService s1 = (CatalogService)o1;
		CatalogService s2 = (CatalogService)o2;

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

	@Override
	public boolean equals(Object obj)  
	{
		return this.equals(obj);
	}

	@Override
	public void orderBy(List attributes) {
		mAttributes = attributes;
	}


	public void setOrderAscending(boolean val)
	{
		mAscendingOrder = val;
	}
}
