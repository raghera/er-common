package com.vodafone.global.er.generated.test;

import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.vodafone.global.er.generated.ProvisionApiStub;


public class ProvisionApiStubTest extends TestCase {


	//CR1231
    //private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(ProvisionApiStubTest.class);
    

    public ProvisionApiStubTest(String name) {
        super(name);
    }


    public static Test suite() {
    
        TestSuite testSuite = new TestSuite();
        testSuite.addTestSuite(ProvisionApiStubTest.class);
        
        return testSuite;
    } 
    
    
    @Override
	protected void setUp() throws Exception {
    
    }


    @Override
	protected void tearDown() {
         
    }

    
    public void test() throws Exception {
        
        ProvisionApiStub provisionApiStub = new ProvisionApiStub(Locale.UK);
                
        boolean result = provisionApiStub.updateServiceStatus(null, 0, 0);
    
        assertFalse(result);
    }
    
        
    public static void main(String args[]) {
    
        //junit.textui.TestRunner.run(ProvisionApiStubTest.class);
        junit.textui.TestRunner.run(suite());
    }
}
