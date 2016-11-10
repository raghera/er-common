package com.vodafone.global.er.generated.test;

import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

import com.vodafone.global.er.generated.PurchaseApiStub;


public class PurchaseApiStubTest extends TestCase {


	//CR1231
    //private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(PurchaseApiStubTest.class);
    private static Logger log = Logger.getLogger(PurchaseApiStubTest.class);


    public PurchaseApiStubTest(String name) {
        super(name);
    }


    public static Test suite() {

        TestSuite testSuite = new TestSuite();
        testSuite.addTestSuite(PurchaseApiStubTest.class);

        return testSuite;
    }


    @Override
	protected void setUp() throws Exception {

    }


    @Override
	protected void tearDown() {

    }


    public void testCheckUserHasPackageWithPromoCode() throws Exception {

        PurchaseApiStub purchaseApiStub = new PurchaseApiStub(Locale.UK);

        boolean status = purchaseApiStub.checkUserHasPackageWithPromoCode ("123223","00908988","PID","121");

        log.debug("Status="+status);
    }




    public static void main(String args[]) {

        junit.textui.TestRunner.run(PurchaseApiStubTest.class);
        //junit.textui.TestRunner.run(suite());
    }
}
