package com.vodafone.global.er.decoupling.util.xml;

import com.vizzavi.ecommerce.common.ErCountry;
import com.vodafone.global.er.decoupling.binding.request.ErRequest;
import com.vodafone.global.er.decoupling.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Locale;

public class XmlRequestManager {

    private static final Logger logger = LoggerFactory.getLogger(XmlRequestManager.class);
    private XmlSubParser parser = null;

	private Locale locale;
	private ErRequest envelope = null;
	
	//MQC 4167 - add variable to hold requestString
	private String requestString = null;
	
	public XmlRequestManager(InputStream is) {
		parser = new XmlSubParser( "payload", getString(is) );
		initEnvelope(parser.getOuter());
	}
	
	private String getString(InputStream is) {
		InputStreamReader reader = new InputStreamReader( is ); 
	    char[] buffer = new char[4096]; 

	    StringWriter writer = new StringWriter(); 

	    int bytes_read; 
		
		try
		{
		    while ((bytes_read = reader.read(buffer)) != -1) { 
		        writer.write(buffer, 0, bytes_read); 
		    } 
		}
		catch(IOException e)
		{
			throw new RuntimeException("Unable to read the inputstream.  Please try again.");
		}

	    String result = writer.toString();
	    //MQC 4167 - set requestString
	    requestString = result;
		return result;
	}
	
	private void initEnvelope(String xmlStr){
		try	{
			Object xml = JAXBRequestHelper.bind(xmlStr);

			if(xml instanceof ErRequest )
				envelope = (ErRequest) xml;
			else
				throw new RuntimeException("Invalid XML request.  The XML is not an ErRequest");
		} catch (JAXBException e) { //INVALID REQUEST...not XML...or not in correct XML format.
            logger.error(e.getMessage());
            logger.warn(e.getMessage(), e);
			throw new RuntimeException("Invalid XML request.  " + e.toString(), e);
		}
		//handle request with purchase locale 'en_GB' or just GB
		locale = ErCountry.parseLocale(envelope.getPurchaseLocale());
	}
	
	
	public ErRequest getEnvelope() { return envelope; }
	
	
	public Node getPayloadAsNode()
	{
		Document doc = this.parse( parser.getInner() );
		Node retval = doc.getFirstChild();
		return retval;
	}
	
	private Object getJAXBPayload()
	{
		try	{
			if(parser.getInner()==null)
				return null;
			return JAXBRequestHelper.bind(parser.getInner());
		}
		catch (UnmarshalException ume)
		{
			throw new ValidationException("Invalid XML request. "+ume.getMessage(), ume);
		}
		catch (JAXBException e) { 
			throw new RuntimeException("Invalid XML request.  Unable to bind to the object" + e.getMessage(), e);
		}
	}
	
	public Object getPayload() { 
		Object payload = getJAXBPayload(); 
		logger.debug("payload: {}" , payload);
		return payload;
	}	
	
	
	private Document parse(String xml)
	{
		Document document = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		// ...for now...
		factory.setValidating(false);
		factory.setNamespaceAware(true);
		
		try 
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource source = new InputSource( new StringReader(xml) );
			document = builder.parse(source);
			return document;
		} 
		catch (SAXParseException spe) 
		{
			throw new RuntimeException("Invalid XML request.  Unable to parse to the xml." , spe);
		} 
		catch (SAXException sxe) 
		{
			throw new RuntimeException("Invalid XML request.  Unable to parse to the xml." , sxe);
		} 
		catch (ParserConfigurationException pce) 
		{
			throw new RuntimeException("Invalid XML request.  Unable to parse to the xml." , pce);
		} 
		catch (IOException ioe) 
		{
			throw new RuntimeException("Invalid XML request.  Unable to parse to the xml." , ioe);
		}
	}
	
	//CR1935
	public Document getDocument(){
		
		return parse(requestString);
		
	}
	//CR1935
	public int getPayloadType() {
		
		logger.debug("payload id: {}" , envelope.getId());
		
		return envelope.getId();
	}
	
	
	/**
	 * Returns the country/region code for this request, which will be
	 * an uppercase ISO 3166 2-letter code taken from the locale declaration
	 *  in the root node of the request
	 * @return 
	 */
	public String getPayloadCountry()	{
		return locale.getCountry();
	}
	
	/**
	 * the actual xml in the request
	 * @return
	 */
	public String getRequestString() {
		return this.requestString;
	}
}


