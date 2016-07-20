package com.vodafone.global.er.generated.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.clarkware.junitperf.LoadTest;
import com.clarkware.junitperf.TimedTest;



public class CatalogApiStubLoadTest {


    public static Test suite() {
        
        int maxUsers = 500;
        long maxElapsedTime = 1000;
        
        Test testCase = new CatalogApiStubTest("testFindPackagesWithService");
        Test loadTest = new LoadTest(testCase, maxUsers);
        Test timedTest = new TimedTest(loadTest, maxElapsedTime);
        
        TestSuite suite = new TestSuite();        
        suite.addTest(timedTest);
     
        return suite;
    }
    
    public static void main(String args[]) {
    
        junit.textui.TestRunner.run(suite());
    }
}

