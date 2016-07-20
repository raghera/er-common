//package com.vizzavi.ecommerce.util;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import org.junit.Ignore;
//
//import junit.framework.TestCase;
//
//public class TestFileUtils extends TestCase {
//
//	public void testDeleteProp() throws FileNotFoundException, IOException	{
//		//relies on other FileUtils methods working ok
//		String fileName = "src/test/java/com/vizzavi/ecommerce/util/test.properties";
//		String fileProps = FileUtils.getFile(fileName);
//		assertTrue(fileProps.contains("my.property=true"));
//		//add a new property
//		FileUtils.writeProperty("new.property", "propertyValue", fileName);
//		//check it's there
//		fileProps = FileUtils.getFile(fileName);
//		assertTrue(fileProps.contains("new.property=propertyValue"));
//		//now remove it
//		FileUtils.deleteProperty("new.property", fileName);
//		//check it's gone
//		fileProps = FileUtils.getFile(fileName);
//		assertFalse(fileProps.contains("new.property=propertyValue"));
//
//	}
//	
//	
//}
