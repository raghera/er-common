package com.vodafone.global.er.decoupling.util.xml;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.decoupling.binding.response.ErResponse;


/**
 * only marshals and unmarshalls {@link ErResponse} objects
 *
 */
public class JAXBResponseHelper extends JAXBHelper<ErResponse> {
	
	private static final Logger logger = LoggerFactory.getLogger(JAXBResponseHelper.class);
	
	private static JAXBResponseHelper theInstance ;
	
	
	JAXBResponseHelper()	{
		try {
			jc = JAXBContext.newInstance(
					"com.vodafone.global.er.decoupling.binding.response:com.vodafone.global.er.decoupling.binding.response.v2"
					);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static JAXBResponseHelper getInstance()	{
		if (theInstance==null)
			theInstance = new JAXBResponseHelper();
		theInstance.useStax = ConfigProvider.getPropertyAsBoolean("er.core.decoupling.xml.use.stax", true);
		return theInstance;
	}
	
	@Override
	void validate(Unmarshaller um) throws JAXBException {
		um.setEventHandler(new ValidationEventHandler()		{
			//this event handler ignores unexpected elements in the xml, but will return false on any other event
			@Override
			public boolean handleEvent(ValidationEvent evt) 
			{
				logger.info("Event Info: {}",evt);
				if(evt.getMessage().toLowerCase().contains("unexpected element"))
					return true;
				return false;
			}
		});		
	}

}
