//package com.vizzavi.ecommerce.business.catalog.internal.model;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
///**
//    Unit tests the ER2.3 Pricing Text
//
//    The unit test, creates a catalog/priceplan with
//    default package, service, price point data.
//    Each package, service and pricepoint has the pricing text set.
//
//    Then the catalog/priceplan is saved and then read in again.
//
//    Each test tests that the pricing text is set properly.
//
//*/
//public class TestTierOrderComparator extends TestCase {
//
//
//    public TestTierOrderComparator (String name) {
//        super(name);
//    }
//
//
//    public static Test suite() {
//        return new TestSuite(TestTierOrderComparator.class);
//    }
//
//
//    @Override
//	public void setUp() throws Exception {
//
//    }
//
//
//    @Override
//	public void tearDown() {
//
//    }
//
//    private List<Tier> getTiers()
//    {
//        int index = 1;
//        Tier tier0 = new Tier("" + index++);
//        tier0.setOrder(-1);
//
//        Tier tier1 = new Tier("" + index++);
//        tier1.setOrder(2);
//        Tier tier2 = new Tier("" + index++);
//        tier2.setOrder(10);
//        Tier tier3 = new Tier("" + index++);
//        tier3.setOrder(15);
//        Tier tier4 = new Tier("" + index++);
//        tier4.setOrder(20);
//        List<Tier> rv = new ArrayList<Tier>();
//        rv.add(tier4);
//        rv.add(tier0);
//        rv.add(tier2);
//        rv.add(tier3);
//        rv.add(tier1);
//        return rv;
//    }
//
//
//    public void testAscendingOrder1() throws Exception
//    {
//        List<Tier> tiers = getTiers();
//
//        TierOrderComparator comp = new TierOrderComparator();
//        Collections.sort(tiers, comp);
//        for (int index=0; index<tiers.size(); index++) {
//            Tier tier = tiers.get(index);
//            assertEquals("Test ascending order", "" + (index + 1), tier.getId());
//        }
//    }
//
//    public void testAscendingOrder2() throws Exception
//    {
//        List<Tier> tiers = getTiers();
//
//        TierOrderComparator comp = new TierOrderComparator();
//        comp.setOrderAscending(true);
//        Collections.sort(tiers, comp);
//        for (int index=0; index<tiers.size(); index++) {
//            Tier tier = tiers.get(index);
//            assertEquals("Test ascending order", "" + (index + 1), tier.getId());
//        }
//    }
//
//    public void testDescendingOrder() throws Exception
//    {
//        List<Tier> tiers = getTiers();
//
//        TierOrderComparator comp = new TierOrderComparator();
//        comp.setOrderAscending(false);
//        Collections.sort(tiers, comp);
//        for (int index=0; index<tiers.size(); index++) {
//            Tier tier = tiers.get(index);
//            assertEquals("Test descending order", "" + (5 - index), tier.getId());
//        }
//    }
//}
