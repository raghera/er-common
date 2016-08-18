package com.vodafone.global.er.decoupling.util.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.decoupling.exceptions.ValidationException;


/**
 * generic superclass for {@link JAXBRequestHelper} and {@link JAXBResponseHelper}
 * @author matt
 *
 * @param <T> ErResponse or ErRequest
 */
public abstract class JAXBHelper<T extends Element> {

	private final static Logger logger = LoggerFactory.getLogger(JAXBHelper.class);
	static final String ENCODING = "utf-8";
	
	/**Threadsafe. Not static, but we're using a singleton model so that's not necessary*/
	JAXBContext	jc;
	private XMLInputFactory	xmlif;	//only used for stax
	private XMLOutputFactory xmlof;	//ditto

	/**a high-performance jaxb implementation*/
	boolean useStax	= false;
	Schema requestSchema = null;	//for validation
	Schema responseSchema = null;	//for validation
	Schema responseV2Schema = null;	//for validation
	boolean validationEnabled = false;
	private boolean formatXml = ConfigProvider.getPropertyAsBoolean("er.decoupling.format.xml", true);


	private ThreadLocal<Unmarshaller> unmarshallers = new ThreadLocal<Unmarshaller>()	{

		protected Unmarshaller initialValue()	{
			try {
				return jc.createUnmarshaller();
			} catch (JAXBException e) {
				throw new RuntimeException(e);
			}
		}
	};

	private ThreadLocal<Marshaller> marshallers = new ThreadLocal<Marshaller>()	{

		protected Marshaller initialValue()	{
			try {
				return jc.createMarshaller();
			} catch (JAXBException e) {
				throw new RuntimeException(e);
			}
		}
	};
	
	JAXBHelper()	{
		//expensive calls, but only happen once (singleton)
		xmlif = XMLInputFactory.newInstance();
		xmlof = XMLOutputFactory.newInstance();
	}

	/**
	 * unmarshalls the input String (xml) to an ErResponse or an ErRequest
	 * unmarshalls the input to a java object (ErRequest / ErResponse).  Will perform validation, if it's enabled and the schema is found
	 * @param xml
	 * @return
	 * @throws JAXBException
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public T bind(String xml) throws JAXBException, UnsupportedEncodingException{
		Unmarshaller um = getUnmarshaller();
		if (useStax)	{	//high performance - ~10 times faster than vanilla jaxb
			XMLStreamReader xmlsr = null;
			try {
				//Created XMLStreamReader to perform the  unmarshalling stuff
				xmlsr = xmlif.createXMLStreamReader(getInputStream(xml), ENCODING);
				// http://stackoverflow.com/questions/5793087/stax-xml-validation
				if (validationEnabled && requestSchema != null)	{
					//Created the XMLStreamReader to perform the StAX validation stuff
					XMLStreamReader xmlsrValidation = new CustomXmlStreamReader(xmlif.createXMLStreamReader(getInputStream(xml), ENCODING));
			        Validator validator = requestSchema.newValidator();
			        StAXSource source = new StAXSource(xmlsrValidation);
			        try {
						validator.validate(source);
					} catch ( IOException e) {
						logger.warn("request validation failed: ", e);
					} catch (SAXException e) {
						logger.warn("request invalid : {}", e.getMessage());
						throw new ValidationException(e.getMessage(),e);
					} catch(IllegalArgumentException e)	{
						//	http://stackoverflow.com/questions/20493359/staxsource-not-accepted-by-validator-in-jboss-eap-6-1
						logger.warn("validation in stax requires version 2.11.0 of xerces - request will not be processed");
						logger.error("switch off either stax or request validation.  Or upgrade xerces");
						logger.info(e.getMessage());
						requestSchema=null;	//to prevent this error on the next request
					}
				}	
				 return (T) um.unmarshal(xmlsr);
			} catch (XMLStreamException e) {
				throw new JAXBException(e);
			}	finally	{
				closeQuietly(xmlsr);
			}
		}	else	{
			//non-stax case
			if (validationEnabled)	
				validate(um);
		}
		return  (T) um.unmarshal(getInputStream(xml));
	}

	/**
	 * It will preapre the InputStream from string.
	 * @param xml
	 * @return
	 * @throws JAXBException 
	 */
	private InputStream getInputStream(String xml) throws JAXBException {
		
		try {
			return  new ByteArrayInputStream(xml.getBytes(ENCODING));
		} catch (UnsupportedEncodingException e) {
			throw new JAXBException(e);
		}
	}
	
	private Unmarshaller getUnmarshaller() throws JAXBException {
		//Unmarshallers are not threadsafe, but can be re-used
		//    https://jaxb.java.net/2.2.7/docs/release-documentation.html#d0e4072
		try	{
			Unmarshaller um = unmarshallers.get();
			return um;
		}	catch (RuntimeException e)	{
			throw (JAXBException) e.getCause();
		}
	}


	/**
	 * marshalls the object passed (which must be a ErRequest / ErResponse) to a byte array output stream
	 * @param <T> obj
	 * @return
	 * @throws JAXBException 
	 */
	protected  ByteArrayOutputStream toByteStream(T obj) throws JAXBException	{

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		OutputStreamWriter w;
		try {
			w = new OutputStreamWriter (os, ENCODING);
			toWriter(obj, w);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}

		return os;
	}

	/**
	 * validate the old-fashioned way (ie not using stax)
	 * @param um
	 * @throws JAXBException
	 */
	abstract void validate(Unmarshaller um) throws JAXBException;

	/**
	 * marshalls the object passed (which must be a jaxb object in the {@code com.vodafone.global.er.decoupling.binding.request} package) to a String
	 * @param obj
	 * @return
	 * @throws JAXBException 
	 */
	public  String toString(T obj) throws JAXBException {
		return toByteStream(obj).toString();
	}


	/**
	 * marshalls the object passed (which must be an ErResponse or an ErRequest) to a byte array
	 * @param obj
	 * @return
	 * @throws JAXBException 
	 */
	public  byte[] toByteArray(T obj) throws JAXBException {
		return toByteStream(obj).toByteArray();
	}


	protected void closeQuietly(XMLStreamWriter xmlStreamWriter) {
		if ( xmlStreamWriter !=null)
			try {
				xmlStreamWriter.close();
			} catch (XMLStreamException e) {
				logger.info(e.getMessage());
			}
	}
	
	protected void closeQuietly(XMLStreamReader xmler) {
		if (xmler!=null)
			try {
				xmler.close();
			} catch (XMLStreamException e) {
				logger.info(e.getMessage());
			}
	}

	public JAXBContext getJAXBContext() {
		return jc;
	}

	/**
	 * marshalls the ErResponse / ErRequest direct to the Writer
	 * @param ErResponse / ErRequest 
	 * @param writer a Writer
	 * @throws JAXBException 
	 */
	public  void toWriter(T element, Writer writer) throws JAXBException {
		XMLStreamWriter xsw =null;
		try {
			Marshaller m = getMarshaller();
			if (useStax)	{
				xsw = xmlof.createXMLStreamWriter(writer);
	        	if (formatXml)	// http://stackoverflow.com/questions/290326/stax-xml-formatting-in-java
	        		xsw = new IndentingXMLStreamWriter(xsw);
				m.marshal(element, xsw);
			}	else	{
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatXml);
				m.marshal(element, writer);
			}
		} catch (XMLStreamException e) {
			throw new JAXBException(e);
		}	finally	{
			closeQuietly(xsw);
		}
	}

	private Marshaller getMarshaller() throws JAXBException {
		//Marshallers are not threadsafe, but can be re-used
		try	{
			Marshaller m = marshallers.get();
			return m;
		}	catch (RuntimeException e)	{
			throw (JAXBException) e.getCause();
		}
	}
	
	//To validate the response, ensure that the v2-response.xsd contains the element er-response element from response.xsd
	public void validateResponse(String xml) throws JAXBException	{
		if (useStax)	{	//high performance - ~10 times faster than vanilla jaxb
			XMLStreamReader xmlsr = null;
			try {
				//Created XMLStreamReader to perform the  unmarshalling stuff
				xmlsr = xmlif.createXMLStreamReader(getInputStream(xml), ENCODING);
				// http://stackoverflow.com/questions/5793087/stax-xml-validation
				if (validationEnabled && responseV2Schema != null && xml.contains("-v2"))	{
					//Created the XMLStreamReader to perform the StAX validation stuff
					XMLStreamReader xmlsrValidation = new CustomXmlStreamReader(xmlif.createXMLStreamReader(getInputStream(xml), ENCODING));
			        Validator validator = responseV2Schema.newValidator();
			        StAXSource source = new StAXSource(xmlsrValidation);
			        try {
			        	logger.debug("About to validate v2 response {}", xml);
						validator.validate(source);
						logger.debug("Validation passed");
					} catch ( IOException | SAXException e) {
						logger.warn("response V2 invalid : {}, {}", xml, e.getMessage());
						throw new ValidationException(e.getMessage(),e);
					} catch(IllegalArgumentException e)	{
						//	http://stackoverflow.com/questions/20493359/staxsource-not-accepted-by-validator-in-jboss-eap-6-1
						logger.warn("validation in stax requires version 2.11.0 of xerces - request will not be processed");
						logger.error("switch off either stax or request validation.  Or upgrade xerces");
						logger.info(e.getMessage());
						responseV2Schema=null;	//to prevent this error on the next request
					}
				}
				else if (validationEnabled && responseSchema != null)	{
					//Created the XMLStreamReader to perform the StAX validation stuff
					XMLStreamReader xmlsrValidation = new CustomXmlStreamReader(xmlif.createXMLStreamReader(getInputStream(xml), ENCODING));
			        Validator validator = responseSchema.newValidator();
			        StAXSource source = new StAXSource(xmlsrValidation);
			        try {
			        	logger.debug("About to validate response {}", xml);
						validator.validate(source);
						logger.debug("Validation passed");
					} catch ( IOException | SAXException e) {
						logger.warn("response invalid : {}, {}", xml, e.getMessage());
						throw new ValidationException(e.getMessage(),e);
					} catch(IllegalArgumentException e)	{
						//	http://stackoverflow.com/questions/20493359/staxsource-not-accepted-by-validator-in-jboss-eap-6-1
						logger.warn("validation in stax requires version 2.11.0 of xerces - request will not be processed");
						logger.error("switch off either stax or request validation.  Or upgrade xerces");
						logger.info(e.getMessage());
						requestSchema=null;	//to prevent this error on the next request
					}
				}	
			} catch (XMLStreamException e) {
				throw new JAXBException(e);
			}	finally	{
				closeQuietly(xmlsr);
			}
		}	else	{
			//non-stax case
		}
	}

}
