package com.vizzavi.ecommerce.business.catalog;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.vizzavi.ecommerce.business.catalog.CatalogService;
import com.vizzavi.ecommerce.business.catalog.ServiceRevenueSharePartner;
import com.vizzavi.ecommerce.business.catalog.ServiceRevenueSharePartnerKey;
import com.vizzavi.ecommerce.business.catalog.internal.ServiceRevenueSharePartnerImpl;

/**
 * 
 * @author sharmao
 * 
 * This class  performs the test for the CatalogService's  methods, these changes are related to ET-123.
 * These changes currently rolled back.
 *
 */
public class TestCatalogService extends TestCase{

	protected CatalogService catalogService = null;
	

	@Override
	public void setUp() {
		catalogService = new CatalogService();
	}
	

	// ----------------------------------mRevSharePartnerPurchaseChMap---------------------------------
	
	@Test
	public void testMRevSharePartnerPurchaseChMap() {
		
		//create some objects
		ServiceRevenueSharePartner p1 = populateServiceRevenueSharePartner("906","*");
		
		
		//populate the map
		catalogService.addServiceRevenueSharePartnerPurchaseCh( p1);
		catalogService.addServiceRevenueSharePartnerPurchaseCh(populateServiceRevenueSharePartner("931",null));
		catalogService.addServiceRevenueSharePartnerPurchaseCh(populateServiceRevenueSharePartner("841",null));
		catalogService.addServiceRevenueSharePartnerPurchaseCh(populateServiceRevenueSharePartner("825","@"));
		catalogService.addServiceRevenueSharePartnerPurchaseCh(populateServiceRevenueSharePartner("870","*"));
		catalogService.addServiceRevenueSharePartnerPurchaseCh(populateServiceRevenueSharePartner("986","*"));
		catalogService.addServiceRevenueSharePartnerPurchaseCh(populateServiceRevenueSharePartner("977","*"));
		catalogService.addServiceRevenueSharePartnerPurchaseCh(populateServiceRevenueSharePartner(null,null));
		catalogService.addServiceRevenueSharePartnerPurchaseCh(populateServiceRevenueSharePartner("null","null"));
		catalogService.addServiceRevenueSharePartnerPurchaseCh(populateServiceRevenueSharePartner("",""));
	
		
		
		//check the size of map
		int number = catalogService.getServiceRevenueSharePartnerNum();
    	Assert.assertEquals(9, number);
    	
		catalogService.addServiceRevenueSharePartnerPurchaseCh( populateServiceRevenueSharePartner("906","*"));
		number = catalogService.getServiceRevenueSharePartnerNum();
    	Assert.assertEquals(9, number);
    	
    	//get the array of map and check the null key value is available into the map or not
    	ServiceRevenueSharePartner[] serviceReveSharePartnerArr =  catalogService.getServiceRevenueSharePartnersPurchaseCh();
		assertEquals(9, serviceReveSharePartnerArr.length);
		/*String strKey = "";
		ServiceRevenueSharePartner reveObject = null;
		for (int i = 0; i < serviceReveSharePartnerArr.length; i++) {
			reveObject = new  ServiceRevenueSharePartner();
			reveObject = serviceReveSharePartnerArr[i];
			strKey=strKey+reveObject.getId();
		}  
		assertEquals(true, strKey.contains("null"));*/
		List<ServiceRevenueSharePartner> list = Arrays.asList(serviceReveSharePartnerArr);
		Assert.assertTrue(list.contains(p1));
		
		//retrieve the value from the map specific to the given key
		ServiceRevenueSharePartner serviceReveSharePartner = catalogService.getServiceRevenueSharePartnerPurchaseCh("825","@");
		assertNotNull(serviceReveSharePartner);
		assertEquals("825", serviceReveSharePartner.getId());
		assertEquals("@", serviceReveSharePartner.getPurchaseChannel());
		assertNotNull(serviceReveSharePartner.getName());
		
		//Test for the null key
		serviceReveSharePartner = catalogService.getServiceRevenueSharePartnerPurchaseCh(null,null);
		assertNull(serviceReveSharePartner);
		
		
		//Test for the null key as String
		serviceReveSharePartner = catalogService.getServiceRevenueSharePartnerPurchaseCh("null","null");
		assertNotNull(serviceReveSharePartner);
		assertEquals("null", serviceReveSharePartner.getId());
		
		//Test for the blank string as key
		serviceReveSharePartner = catalogService.getServiceRevenueSharePartnerPurchaseCh("","");
		assertNotNull(serviceReveSharePartner);
		assertEquals("", serviceReveSharePartner.getId());
    	
		//remove the value from the map.
		catalogService.removeServiceRevenueSharePartnerPurchaseCh("null","null");
		ServiceRevenueSharePartner revenueSharePartner = catalogService.getServiceRevenueSharePartnerPurchaseCh("null","null");
		assertNull(revenueSharePartner);

		number = catalogService.getServiceRevenueSharePartnerNum();
    	Assert.assertEquals(8, number);
    	
    	serviceReveSharePartnerArr =  catalogService.getServiceRevenueSharePartnersPurchaseCh();
		assertEquals(8, serviceReveSharePartnerArr.length);
		
		list = Arrays.asList(serviceReveSharePartnerArr);
		Assert.assertFalse(list.contains(revenueSharePartner));
		
		ServiceRevenueSharePartnerKey k = new ServiceRevenueSharePartnerKey("hello", null);
        String r = k.getPurchaseChannel();
        Assert.assertEquals("*", r);
        
        k = new ServiceRevenueSharePartnerKey("", null);
        r = k.getPurchaseChannel();
        Assert.assertEquals("*", r);
        
        k = new ServiceRevenueSharePartnerKey(null, null);
        r = k.getPurchaseChannel();
        Assert.assertEquals("*", r);
	}
	
	
//--------------------------------------------mRevSharePartnerForPackageMap----------------------------
	
	@Test
	public void testmRevSharePartnerForPackageMap(){
		
		//Create the object
		ServiceRevenueSharePartner p2 = populateServiceRevenueSharePartner("1003","#");
		
		//add the value into the map
		catalogService.addServiceRevenueSharePartnerForPackage("PKG01", populateServiceRevenueSharePartner("1001",null));
		catalogService.addServiceRevenueSharePartnerForPackage("PKG01", populateServiceRevenueSharePartner("1002",null));
		catalogService.addServiceRevenueSharePartnerForPackage("PKG01", populateServiceRevenueSharePartner("1003","#"));
		catalogService.addServiceRevenueSharePartnerForPackage("PKG01", populateServiceRevenueSharePartner("1004","*"));
		catalogService.addServiceRevenueSharePartnerForPackage("PKG02", populateServiceRevenueSharePartner("2001","@"));
		catalogService.addServiceRevenueSharePartnerForPackage("PKG03", populateServiceRevenueSharePartner("3001","*"));
		catalogService.addServiceRevenueSharePartnerForPackage("PKG04", populateServiceRevenueSharePartner("4001","*"));
		catalogService.addServiceRevenueSharePartnerForPackage("PKG04", populateServiceRevenueSharePartner("4001","*"));
		catalogService.addServiceRevenueSharePartnerForPackage("PKG05", populateServiceRevenueSharePartner("5001","#"));
		catalogService.addServiceRevenueSharePartnerForPackage("PKG05", populateServiceRevenueSharePartner("5001","*"));
		catalogService.addServiceRevenueSharePartnerForPackage("PKG06", populateServiceRevenueSharePartner("6001","*"));
		catalogService.addServiceRevenueSharePartnerForPackage(null, populateServiceRevenueSharePartner(null,null));
		catalogService.addServiceRevenueSharePartnerForPackage("null", populateServiceRevenueSharePartner("null","null"));
		catalogService.addServiceRevenueSharePartnerForPackage("", populateServiceRevenueSharePartner("",""));
		
		
		//get the all value from the map by using the key as a package id
		ServiceRevenueSharePartner[] serviceRevePartnerArr = catalogService.getServiceRevenueSharePartnersForPackage("PKG01");
		assertEquals(4, serviceRevePartnerArr.length);
		List<ServiceRevenueSharePartner> list = Arrays.asList(serviceRevePartnerArr);
		Assert.assertTrue(list.contains(p2));
		
		
		//get the value from the map by using the package id and partner id
		ServiceRevenueSharePartner serviceRevePartner= catalogService.getServiceRevenueSharePartnerForPackage("PKG02", "2001");
		assertNotNull(serviceRevePartner);
		assertEquals("2001", serviceRevePartner.getId());
		assertEquals("@", serviceRevePartner.getPurchaseChannel());
		assertNotNull(serviceRevePartner.getName());
		
		
		serviceRevePartner= catalogService.getServiceRevenueSharePartnerForPackage("", "");
		assertNotNull(serviceRevePartner);
		assertEquals("", serviceRevePartner.getId());
		
		serviceRevePartner= catalogService.getServiceRevenueSharePartnerForPackage("null", "null");
		assertNotNull(serviceRevePartner);
		assertEquals("null", serviceRevePartner.getId());
		
		
		//remove the value from the map.
		catalogService.removeServiceRevenueSharePartnerForPackage("PKG01", "1003");
		
		serviceRevePartnerArr = catalogService.getServiceRevenueSharePartnersForPackage("PKG01");
		assertNotNull(serviceRevePartnerArr);
		assertEquals(3, serviceRevePartnerArr.length);
		
		//remove value should not be available into the map
		catalogService.removeServiceRevenueSharePartnerForPackage("null","null");
		
		serviceRevePartner= catalogService.getServiceRevenueSharePartnerForPackage("null", "null");
		assertNull(serviceRevePartner);
		
		serviceRevePartner= catalogService.getServiceRevenueSharePartnerForPackage("PKG01", "1003");
		assertNull(serviceRevePartner);
		
		//check purchase if null during object creation.
		serviceRevePartner= catalogService.getServiceRevenueSharePartnerForPackage("PKG01", "1001");
		assertNotNull(serviceRevePartner);
		assertEquals("1001", serviceRevePartner.getId());
		assertEquals(null, serviceRevePartner.getPurchaseChannel());
		assertNotNull(serviceRevePartner.getName());
		
		serviceRevePartner= catalogService.getServiceRevenueSharePartnerForPackage(null, null);
		assertNotNull(serviceRevePartner);
		assertEquals(null, serviceRevePartner.getId());
		assertEquals(null, serviceRevePartner.getPurchaseChannel());
		assertNull(serviceRevePartner.getName());
		
		
		
		
	}
	

	private ServiceRevenueSharePartner populateServiceRevenueSharePartner(String id,String purchaseChannel){
		
		ServiceRevenueSharePartnerImpl sharePartnerImpl = new ServiceRevenueSharePartnerImpl();
		sharePartnerImpl.setId(id);
		sharePartnerImpl.setName(id);
		sharePartnerImpl.setPurchaseChannel(purchaseChannel);
		sharePartnerImpl.setPriorityFlag("N");
		
		return sharePartnerImpl;
	}


}