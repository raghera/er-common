package com.vodafone.global.er.generated.test;

import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vodafone.global.er.generated.CatalogApiStub;


public class CatalogApiStubTest extends TestCase {


	//CR1231
    //private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(CatalogApiStubTest.class);
    private static Logger log = Logger.getLogger(CatalogApiStubTest.class);
    
    private static CatalogApiStub catalogApiStub;
    private CatalogService service;


    public CatalogApiStubTest(String name) {
        super(name);
    }


    public static Test suite() {

        TestSuite testSuite = new TestSuite();
        testSuite.addTestSuite(CatalogApiStubTest.class);

        return testSuite;
    }


    @Override
	protected void setUp() {
    	try{
        catalogApiStub = new CatalogApiStub(Locale.UK);
		}catch(Exception e) {System.out.println(e); System.exit(1);}

        service = new CatalogService("c001", "duration service", null);
    }


    @Override
	protected void tearDown() {

    }


    public void testFindPackagesWithService() throws Exception {



        CatalogPackage[] catalogPackages = catalogApiStub.findPackagesWithService(service);
        assertNotNull(catalogPackages);

        log.debug("catalogPackages.length: " + catalogPackages.length);
        assertTrue(catalogPackages.length > 0);

        for (int i=0; i<catalogPackages.length; i++) {
            log.debug("catalogPackages[" + i + "]: " + catalogPackages[i]);
        }


    }


    public void testGetPackages() {


        CatalogPackage[] catalogPackages = catalogApiStub.getPackages();

        assertNotNull(catalogPackages);

        log.debug("catalogPackages.length: " + catalogPackages.length);

        assertTrue(catalogPackages.length > 0);

        for (int i=0; i<catalogPackages.length; i++) {
            log.debug("catalogPackages[" + i + "]: " + catalogPackages[i]);
        }


    }


    public void testGetService() {

        CatalogService theService = catalogApiStub.getService("c001");
        assertNotNull(theService);
        assertEquals(theService.getId(), "c001");
        log.debug("service: " + theService);

    }


    public void testGetPackage() {

        CatalogPackage thePackage = catalogApiStub.getPackage("p001");
        assertNotNull(thePackage);
        assertEquals(thePackage.getSimplePackageId(), "p001");
        log.debug("package: " + thePackage);
    }


    //public CatalogPackage getPackage(String packageId, String rateIdentifier);


    public void testGetCatalogPackages() {

        CatalogPackage[] catalogPackages = catalogApiStub.getPackages();
        assertNotNull(catalogPackages);
        assertTrue(catalogPackages.length > 0);
    }


    public void testGetServices() {

        CatalogService[] catalogServices = catalogApiStub.getServices();
        assertNotNull(catalogServices);
        assertTrue(catalogServices.length > 0);
    }



    public static void main(String args[]) {

        //junit.textui.TestRunner.run(CatalogApiStubTest.class);
        junit.textui.TestRunner.run(suite());
    }
}
