package com.vizzavi.ecommerce.business.catalog;

import java.util.ArrayList;
import java.util.List;

public class PricePointComparator implements OrderableComparator<PricePoint>
{
    private List<String>    mAttributes = new ArrayList<String>();

    public static String ORDER_BY_ID = "id";
    public static String ORDER_BY_NAME = "name";
    public static String ORDER_BY_RATE = "rate";


    int compareTo(String s1, String s2, int increase)
    {
        int rv = 0;
        if (s1==null || s2==null) {
            try {
                throw new Exception("comparing 2 null pricepoints");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (s1.equals("*") && s2.equals("*")) {
            rv = 0;
        } else if (s1.equals("*")) {
            rv = 1;
        } else if (s2.equals("*")) {
            rv = -1;
        } else {
            int val = s1.compareTo(s2);

            if (val<0) {
                rv = -increase;
            } else if (val>0) {
                rv = increase;
            }
        }
        return rv;
    }

    int compareTo(int s1, int s2, int increase)
    {
        int rv = 0;
        if (s1 == 999 && s2 == 999) {
            rv = 0;
        } else if (s1 == 999) {
            rv = 1;
        } else if (s2 == 999) {
            rv = -1;
        } else {
            if (s1<s2) {
                rv = -increase;
            } else if (s1>s2) {
                rv = increase;
            }
        }
        return rv;
    }


    @Override
	public int compare(PricePoint p1, PricePoint p2)
    {
  
        if(mAttributes.size() == 1 && mAttributes.get(0) == ORDER_BY_RATE)
        {
           int rv = Double.compare(p1.getRate() , p2.getRate());
           return rv;
        }
        else
        {
        int rv = 0;
        int index = 2048;

        rv = rv + compareTo(p1.getContentId(), p2.getContentId(), index);
        index = index/2;
        rv = rv + compareTo(p1.getPackageId(), p2.getPackageId(), index);
        index = index/2;
        rv = rv + compareTo(p1.getPromoCode(), p2.getPromoCode(), index);
        index = index/2;
        rv = rv + compareTo(p1.getDuration(), p2.getDuration(), index);
        index = index/2;
        rv = rv + compareTo(p1.getChargingMethod(), p2.getChargingMethod(), index);
        index = index/2;
        rv = rv + compareTo(p1.getPaymentType(), p2.getPaymentType(), index);
        index = index/2;
        rv = rv + compareTo(p1.getAccessDevice(), p2.getAccessDevice(), index);
        index = index/2;
        rv = rv + compareTo(p1.getUserGroup(), p2.getUserGroup(), index);
        index = index/2;
        rv = rv + compareTo(p1.getSupplierId(), p2.getSupplierId(), index);
        index = index/2;
        rv = rv + compareTo(p1.getPremiumLevel(), p2.getPremiumLevel(), index);
        index = index/2;
        rv = rv + compareTo(p1.getChannel(), p2.getChannel(), index);
        return rv;
        }
    }


    @Override
	public void orderBy(List<String> attributes) {
        mAttributes = attributes;
    }
}
