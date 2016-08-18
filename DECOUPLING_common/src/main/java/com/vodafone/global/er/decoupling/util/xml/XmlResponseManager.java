package com.vodafone.global.er.decoupling.util.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.vodafone.global.er.decoupling.binding.response.ErResponse;
import com.vodafone.global.er.decoupling.exceptions.ValidationException;
import com.vodafone.global.er.decoupling.util.xml.JAXBResponseHelper;


public class XmlResponseManager {

	private static final Logger logger = LoggerFactory.getLogger(XmlResponseManager.class);
    private XmlSubParser parser = null;
	//private XmlResponseManager() {}

	private ErResponse envelope = null;

	public XmlResponseManager(InputStream is) {
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
		return result;
	}

	private void initEnvelope(String xmlStr){
		try	{
			Object xml = JAXBResponseHelper.bind(xmlStr);

			if(xml instanceof ErResponse )
				envelope = (ErResponse) xml;
			else
				throw new RuntimeException("Invalid XML response.  The XML is not an ErResponse");
		} catch (JAXBException e) { //INVALID response...not XML...or not in correct XML format.
            logger.error(e.getMessage());
            logger.info("error binding xml: [{}]", xmlStr, e);
			throw new RuntimeException("Invalid XML response", e);
		}
	}



	public ErResponse getEnvelope() { return envelope; }



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

			Object xml = JAXBResponseHelper.bind(parser.getInner());
			return xml;
		}
		catch (UnmarshalException ume)
		{
			throw new ValidationException("Invalid XML response. "+ume.toString(), ume);
		}
		catch (JAXBException e) {
			throw new RuntimeException("Invalid XML response.  Unable to bind to the object: " + e.toString(), e);
		}
	}

	public Object getPayload() {
		Object payload = getJAXBPayload();
		logger.debug("[getPayload]payload: {}" , payload);
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
			throw new RuntimeException("Invalid XML response.  Unable to parse to the xml." , spe);
		}
		catch (SAXException sxe)
		{
			throw new RuntimeException("Invalid XML response.  Unable to parse to the xml." , sxe);
		}
		catch (ParserConfigurationException pce)
		{
			throw new RuntimeException("Invalid XML response.  Unable to parse to the xml." , pce);
		}
		catch (IOException ioe)
		{
			throw new RuntimeException("Invalid XML response.  Unable to parse to the xml." , ioe);
		}
	}


	public int getPayloadType() {

		logger.debug("[getPayloadType]payload id: {}" , envelope.getId());

		return envelope.getId();
	}

}

