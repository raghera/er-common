package com.vodafone.global.er.decoupling.util.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.xml.sax.InputSource;

import com.vodafone.config.ConfigProvider;

/**
 * only marshals and unmarshalls response objects; i.e. objects from the  <pre>com.vodafone.global.er.decoupling.binding.response</pre> package
 *
 */
public class JAXBResponseHelper {
	
	private static final String	ENCODING	= "utf-8";

	private static JAXBContext jc = null;
	
	static final Logger logger = LoggerFactory.getLogger(JAXBResponseHelper.class);
	
	static {
		try
		{
			jc = JAXBContext.newInstance("com.vodafone.global.er.decoupling.binding.response" );
		}
		catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] toByteArray(Object obj) {
		return toByteStream(obj).toByteArray();
	}

	public static String toString(Object obj) {
		return toByteStream(obj).toString();
	}
	
	public static ByteArrayOutputStream toByteStream(Object obj){

		ByteArrayOutputStream os = null;
		
		try {
	        Marshaller m = jc.createMarshaller();
	        m.setEventHandler(new ValidationEventHandler(){

				@Override
				public boolean handleEvent(ValidationEvent evt) {
					logger.warn("validation error: {}", evt.getMessage());
					return false;
				}
	        	
	        });
        	m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, ConfigProvider.getPropertyAsBoolean("er.decoupling.format.xml", true));
	        os = new ByteArrayOutputStream();
	        m.marshal(obj, os);
		}
		catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		
		return os;
	}
	
	public static Object bind(File xml) throws JAXBException{
		return jc.createUnmarshaller().unmarshal(xml);
	}
	
	public static Object bind(InputSource xml) throws JAXBException{
		return jc.createUnmarshaller().unmarshal(xml);
	}
	
	public static Object bind(InputStream xml) throws JAXBException{
		Unmarshaller um = jc.createUnmarshaller();
		//um.setValidating(true);
		return um.unmarshal(xml);
	}
	
	public static Object bind(String xml) throws JAXBException{
		try {
			return bind(new ByteArrayInputStream(xml.getBytes(ENCODING)));
		} catch (UnsupportedEncodingException e) {
			throw new JAXBException(e);
		}
	}
	
	public static Object bind(byte[] xml) throws JAXBException{
		return bind(new ByteArrayInputStream(xml));
	}

	
}
