package com.vodafone.global.er.decoupling.util.xml;

import com.vodafone.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import javax.xml.bind.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * only marshals and unmarshalls request objects; i.e. objects from the  <pre>com.vodafone.global.er.decoupling.binding.request</pre> package
 *
 */
public class JAXBRequestHelper {
	
    final static Logger logger = LoggerFactory.getLogger(JAXBRequestHelper.class);
	private final static JAXBContext jc;
	//mqc8623 - hidden property added so we can switch off validation without a patch release
	private static final boolean VALIDATE_REQUESTS=ConfigProvider.getPropertyAsBoolean("er.decoupling.validate.requests", true);
	static {
		try
		{
			jc = JAXBContext.newInstance("com.vodafone.global.er.decoupling.binding.request", JAXBRequestHelper.class.getClassLoader() );
		}
		catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] toByteArray(Element obj) {
		return toByteStream(obj).toByteArray();
	}

	public static String toString(Element obj) {
		return toByteStream(obj).toString();
	}
	
	public static ByteArrayOutputStream toByteStream(Element obj){

		ByteArrayOutputStream os = null;
		
		try {
	        Marshaller m = jc.createMarshaller();
	        m.setEventHandler(new ValidationEventHandler(){	//just to help debugging

				@Override
				public boolean handleEvent(ValidationEvent evt) {
					logger.warn("validation error: {}", evt.getMessage());
					logger.info("validation error: {}", evt.getLocator().getObject());
					return false;
				}
	        	
	        });
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        os = new ByteArrayOutputStream();
	        m.marshal(obj, os);
		}
		catch (JAXBException e) {
			logger.error(e.getMessage());
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
	
	public static Object bind(final InputStream xml) throws JAXBException{
		Unmarshaller um = jc.createUnmarshaller();
		//MQC 8623 - uarc succeeds even with force-purchase set to 'toast'
		um.setValidating(VALIDATE_REQUESTS);
		um.setEventHandler(new ValidationEventHandler(){

			@Override
			public boolean handleEvent(ValidationEvent evt) {
				logger.warn("validation error: {}", evt.getMessage());
				
				if (evt.getMessage().indexOf("is-using-local-date")>=0)
					return true;	//deprecated and removed from xsd, but we should still handle it gracefully in requests
				else if (evt.getMessage().indexOf("service-usage_instance")>=0)
					return true;	//deprecated and removed from xsd, but we should still handle it gracefully in requests
				//add any other unexpected tags in here
				return false;	
			}
        	
        });
		return um.unmarshal(xml);
	}
	
	public static Object bind(String xml) throws JAXBException{
		return bind(new ByteArrayInputStream(xml.getBytes()));
	}
	
	public static Object bind(byte[] xml) throws JAXBException{
		return bind(new ByteArrayInputStream(xml));
	}

}

