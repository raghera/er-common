//package com.vizzavi.ecommerce.business.catalog.internal.model;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
///**
//*/
//public class TestPricingModel extends TestCase {
//
//
//    public TestPricingModel (String name) {
//        super(name);
//    }
//
//
//    public static Test suite() {
//        return new TestSuite(TestPricingModel.class);
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
//    public void testSetGetId() throws Exception
//    {
//        String id = "id";
//        PricingModel model = new PricingModel("id");
//        assertEquals("Test constructor", id, model.getId());
//    }
//
//    public void testSetGetName() throws Exception
//    {
//        String name1 = "id";
//       // String name2 = "id2";
//        PricingModel model = new PricingModel("test");
//        assertEquals("Test set and get name ", null, model.getName());
//        model.setName(name1);
//        assertEquals("Test set and get name ", name1, model.getName());
//        model.setName(null);
//        assertEquals("Test set and get name null ", null, model.getName());
//    }
//
//    public void testSetGetStartDate()
//    {
//        Date start = new Date();
//        PricingModel model = new PricingModel("test");
//        model.setStartDate(start);
//        assertEquals("Test set and get start date ", start, model.getStartDate());
//        model.setStartDate(null);
//        assertEquals("Test set and get start date ", null, model.getStartDate());
//    }
//
//    public void testSetGetEndDate()
//    {
//        Date start = new Date();
//        PricingModel model = new PricingModel("test");
//        model.setEndDate(start);
//        assertEquals("Test set and get start date ", start, model.getEndDate());
//        model.setEndDate(null);
//        assertEquals("Test set and get start date ", null, model.getEndDate());
//    }
//
//    public void testFormatDateInOut1() throws Exception
//    {
//        String input = "23/12/2002";
//        Date dat2 = PricingModel.parseDate(input);
//        String val2 = PricingModel.formatDate(dat2);
//        assertEquals("Test data format ", input, val2);
//    }
//
//    public void testFormatDateInOut2() throws Exception
//    {
//        Date dat = new Date();
//        String val = PricingModel.formatDate(dat);
//        Date dat2 = PricingModel.parseDate(val);
//        String val2 = PricingModel.formatDate(dat2);
//        assertEquals("The date strings should be indentical", val, val2);
//    }
//
//    private PricingModel getModel()
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
//        List rv = new ArrayList();
//        PricingModel model = new PricingModel("test");
//        model.addTier(tier4);
//        model.addTier(tier0);
//        model.addTier(tier2);
//        model.addTier(tier3);
//        model.addTier(tier1);
//        return model;
//    }
//
//    public void testTiersAddition()
//    {
//        PricingModel model = getModel();
//        Tier [] tiers = model.getTiers();
//
//        assertEquals("Number of tiers should be 5", 5, tiers.length);
//    }
//
//    public void testTiersOrdering()
//    {
//        PricingModel model = getModel();
//        Tier [] tiers = model.getTiers();
//
//        for (int index=0; index<tiers.length; index++) {
//            assertEquals("Tier order should be ascending", "" + (index+1), tiers[index].getId() );
//        }
//    }
//
//
//    public void testTiersGet()
//    {
//        PricingModel model = getModel();
//        Tier [] tiers = model.getTiers();
//
//        String id = "" + 2;
//
//
//        Tier tier = model.getTier(id);
//        assertEquals("The tier should exist", id, tier.getId());
//        Tier tier2 = model.getTier("6");
//        assertNull("The tier should not exist", tier2);
//    }
//
//    public void testTiersDelete()
//    {
//        PricingModel model = getModel();
//        Tier [] tiers = model.getTiers();
//
//        String id = tiers[2].getId();
//        model.deleteTier(tiers[2].getId());
//
//        tiers = model.getTiers();
//
//        assertEquals("Number of tiers should now be 4", 4, tiers.length);
//
//        Tier tier = model.getTier(id);
//
//        assertNull("The tier should not exist as deleted", tier);
//    }
//
//}
