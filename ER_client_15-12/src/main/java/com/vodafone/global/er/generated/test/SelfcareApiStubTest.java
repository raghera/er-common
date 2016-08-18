package com.vodafone.global.er.generated.test;

import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vodafone.global.er.generated.SelfcareApiStub;


public class SelfcareApiStubTest extends TestCase {


	//CR1231
    //private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(SelfcareApiStubTest.class);
    

    public SelfcareApiStubTest(String name) {
        super(name);
    }


    public static Test suite() {
    
        TestSuite testSuite = new TestSuite();
        testSuite.addTestSuite(SelfcareApiStubTest.class);
        
        return testSuite;
    } 
    
    
    @Override
	protected void setUp() throws Exception {
    
    }


    @Override
	protected void tearDown() {
         
    }

    
    public void testGetSubscription() throws Exception {
        
        SelfcareApiStub selfcareApiStub = new SelfcareApiStub(Locale.UK);
                
        Subscription sub = selfcareApiStub.getSubscription("test", "test", 0, "test");

        assertNotNull(sub);
    }
    
        
    public static void main(String args[]) {
    
        //junit.textui.TestRunner.run(SelfcareApiStubTest.class);
        junit.textui.TestRunner.run(suite());
    }
}
