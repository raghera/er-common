package com.vizzavi.ecommerce.business.catalog.internal.model;

import java.util.Comparator;

public class TierOrderComparator implements Comparator<Tier>
{
    private boolean mAscending = true;

    public TierOrderComparator()
    {
        this(true);
    }

    public TierOrderComparator(boolean ascending)
    {
        mAscending = ascending;
    }

    @Override
	public int compare(Tier o1, Tier o2)
    {
        Tier t1 = o1;
        Tier t2 = o2;
        int order1 = t1.getOrder();
        int order2 = t2.getOrder();

        int diff = order2 - order1;

        if (mAscending) {
            diff = -1 * diff;
        }
        return diff;
    }

    @Override
	public boolean equals(Object obj)
     {
        return this.equals(obj);
     }

    public void setOrderAscending(boolean val)
    {
        mAscending = val;
    }
}
