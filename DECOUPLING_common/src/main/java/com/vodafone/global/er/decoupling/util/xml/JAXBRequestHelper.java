package com.vodafone.global.er.decoupling.util.xml;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.decoupling.binding.request.ErRequest;

/**
 * handles marshalling and unmarshalling of {@link ErRequest} objects
 *
 */
public class JAXBRequestHelper extends JAXBHelper<ErRequest> {

	private static final String REQUEST_XSD_SCHEMA_PATH = "schemas/request/request.xsd";
	private final static Logger logger = LoggerFactory.getLogger(JAXBRequestHelper.class);
	//MQC9821 - path to decoupling request schema
	
	private static JAXBRequestHelper instance ;



	private JAXBRequestHelper()	{
		try {
			jc = JAXBContext.newInstance("com.vodafone.global.er.decoupling.binding.request" );
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		try {
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			File requestSchemaFile = new File(JAXBRequestHelper.class.getClassLoader().getResource(REQUEST_XSD_SCHEMA_PATH).getFile());
			requestSchema = sf.newSchema(requestSchemaFile);
		}   catch (Exception e) {	//could be a null pointer if the schema file is not found
			//log error and continue without validation
			logger.error(e.getMessage()+": Decoupling request validation not possible. Exception parsing request schema: " + REQUEST_XSD_SCHEMA_PATH );
			requestSchema = null;
		}
	}

	public static JAXBRequestHelper getInstance()	{
		if (instance==null)
			instance =  new JAXBRequestHelper();
		//check these each time so we can toggle without restart
		instance.validationEnabled= ConfigProvider.getPropertyAsBoolean("er.core.decoupling.validate.request.xml", true);
		instance.useStax = ConfigProvider.getPropertyAsBoolean("er.core.decoupling.xml.use.stax", true);
		return instance;
	}

	@Override
	void validate(Unmarshaller um) throws JAXBException	{
		//MQC 8623 - uarc succeeds even with force-purchase set to 'toast'

		um.setSchema(requestSchema);	//Passing null into this method will disable validation.
		um.setEventHandler(new ValidationEventHandler()	{

			@Override
			public boolean handleEvent(ValidationEvent evt) {

				if (evt.getMessage().contains("is-using-local-date"))
					return true;	//deprecated and removed from xsd, but we should still handle it gracefully in requests
				else if (evt.getMessage().contains("service-usage_instance"))
					return true;	//deprecated and removed from xsd, but we should still handle it gracefully in requests
				else if (evt.getMessage().contains("log-id"))
					return true;	//deprecated and removed from xsd, but we should still handle it gracefully in requests
				//add any other unexpected tags in here

				logger.error("validation error: {} -  severity {}", evt.getMessage(), evt.getSeverity());
				ValidationEventLocator vel = evt.getLocator();
				String error = "XML Validation Exception:  " + evt.getMessage() + " at row: " + vel.getLineNumber() + " column: " + vel.getColumnNumber();
				logger.warn(error);
				return false;	
			}
		});
	}




}

