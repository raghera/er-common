package com.vizzavi.ecommerce.business.catalog;


import java.util.Comparator;
import java.util.List;



/**
 * A comparator that allows you to specify attributes to order comparison by.
 */
public interface OrderableComparator<T> extends Comparator<T> {


    /**
     * Specifies list of attributes to use in comparison.
     * @param attributes List of attributes to order by
     */
    public void orderBy(List<String> attributes);

}