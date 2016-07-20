package com.vodafone.global.er.generated.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.clarkware.junitperf.TimedTest;

public class CatalogApiStubTimedTest {

    public static Test suite() {
        
        long maxElapsedTime = 1000;
        
        Test testCase = new CatalogApiStubTest("testFindPackagesWithService");
        Test timedTest = new TimedTest(testCase, maxElapsedTime);
        
        TestSuite suite = new TestSuite();        
        suite.addTest(timedTest);
        
        return suite;
    }
    
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}

