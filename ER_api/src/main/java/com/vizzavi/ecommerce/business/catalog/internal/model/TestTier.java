//package com.vizzavi.ecommerce.business.catalog.internal.model;
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
//public class TestTier extends TestCase {
//
//
//    public TestTier (String name) {
//        super(name);
//    }
//
//
//    public static Test suite() {
//        return new TestSuite(TestTier.class);
//    }
//
//
//    public void setUp() throws Exception {
//
//    }
//
//
//    public void tearDown() {
//
//    }
//
//    public void testSetGetId() throws Exception
//    {
//        String id = "id";
//        String id2 = "id2";
//        Tier tier = new Tier("id");
//        assertEquals("Test constructor", id, tier.getId());
//    }
//
//    public void testSetGetName() throws Exception
//    {
//        String name1 = "id";
//        String name2 = "id2";
//        Tier tier = new Tier("test");
//        assertEquals("Test set and get name ", null, tier.getName());
//        tier.setName(name1);
//        assertEquals("Test set and get name ", name1, tier.getName());
//        tier.setName(null);
//        assertEquals("Test set and get name null ", null, tier.getName());
//    }
//
//    public void testSetGetOrder() throws Exception
//    {
//        int order1 = 5;
//        Tier tier = new Tier("test");
//        assertEquals("Test set and get order ", 0, tier.getOrder());
//        tier.setOrder(order1);
//        assertEquals("Test set and get name ", order1, tier.getOrder());
//    }
//
//}
