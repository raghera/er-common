package com.vizzavi.ecommerce.business.catalog;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CatalogServiceComparator implements OrderableComparator<CatalogService>
{
	private List<String>    mAttributes = new ArrayList<String>();
	private boolean mAscendingOrder = true;

	public final static String ORDER_BY_ID = "id";
	public final static String ORDER_BY_NAME = "name";



	@Override
	public int compare(CatalogService s1, CatalogService s2) 
	{


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
	public void orderBy(List<String> attributes) {
		mAttributes = attributes;
	}


	public void setOrderAscending(boolean val)
	{
		mAscendingOrder = val;
	}




}
