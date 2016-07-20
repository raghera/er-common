package com.vodafone.global.er.generated.test;

import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.vizzavi.ecommerce.business.charging.UsageAttributes;
import com.vizzavi.ecommerce.business.charging.UsageAuthorization;
import com.vodafone.global.er.generated.ChargingApiStub;


public class ChargingApiStubTest extends TestCase {


	//CR1231
    //private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(ChargingApiStubTest.class);
    

    public ChargingApiStubTest(String name) {
        super(name);
    }


    public static Test suite() {
    
        TestSuite testSuite = new TestSuite();
        testSuite.addTestSuite(ChargingApiStubTest.class);
        
        return testSuite;
    } 
    
    
    @Override
	protected void setUp() throws Exception {
    
    }


    @Override
	protected void tearDown() {
         
    }

    
    public void testUsageAuthRateCharge() throws Exception {
        
        ChargingApiStub chargingApiStub = new ChargingApiStub(Locale.UK);
                
        UsageAuthorization usageAuth = chargingApiStub.usageAuthRateCharge("test", "1234", "test", new UsageAttributes());

        assertNotNull(usageAuth);
    }
    
        
    public static void main(String args[]) {
    
        //junit.textui.TestRunner.run(ChargingApiStubTest.class);
        junit.textui.TestRunner.run(suite());
    }
}
