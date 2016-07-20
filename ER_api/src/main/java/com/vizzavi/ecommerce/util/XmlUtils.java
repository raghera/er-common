package com.vizzavi.ecommerce.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.vodafone.config.ConfigProvider;


public class XmlUtils
{
	final static String SAX_DRIVER = "org.apache.xerces.parsers.SAXParser";
	static XMLOutputter sJdomOutputter = new XMLOutputter("  ", false);

	static SAXBuilder sJdomBuilder     = new SAXBuilder( SAX_DRIVER, false );
	
	static ThreadLocal<DocumentBuilderFactory> tl = new ThreadLocal<DocumentBuilderFactory>()	{
		public DocumentBuilderFactory initialValue()	{
			return DocumentBuilderFactory.newInstance(); 
		}
	};
	static Logger logger = LoggerFactory.getLogger( XmlUtils.class );

	/**
	 * what is the encoding of the priceplan?  
	 * @return Usually UTF-8
	 */
	public static String getCharacterEncoding()    {
		String rv = ConfigProvider.getProperty("priceplan.catalog.xml.characterencoding", "UTF-8");
		logger.debug("TEST - Encoding from properties - {}" , rv);
		return rv;
	}




	public static String toXml(Element elem)
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		String xmlToReturn = "";

		try {

			String encoding = getCharacterEncoding();
			sJdomOutputter.setEncoding(encoding);
			//Remedy 4123 the XML Header is needed at the other end to interpret special characters.            
			//To fix ISS4123 CQ 14214 - porting changes of 4123
			sJdomOutputter.output(new Document(elem), out);


		} catch (IOException e) {
			// never happen
		}

		//REMEDY 6150 - use system encoding
		try
		{
			xmlToReturn = out.toString(getCharacterEncoding());
		}
		catch(UnsupportedEncodingException ex)
		{
			logger.warn("Failure to encode message with : {}, using default encoding", getCharacterEncoding());
			xmlToReturn = out.toString();
		}

		logger.debug("xmlToReturn: {}", xmlToReturn);

		return xmlToReturn;
	}

	public static Element toJdom( String xml )
	{
		Element ret = null;
		try {
			Document doc = sJdomBuilder.build( new StringReader(xml) );
			ret = doc.getRootElement();
		}
		catch (JDOMException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static Element toJdom( InputStream xml )
	{
		Element ret = null;
		try {
			Document doc = sJdomBuilder.build( xml );
			ret = doc.getRootElement();
		}
		catch (JDOMException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String beautify(String xml) throws JDOMException
	{
		ByteArrayInputStream inp = new ByteArrayInputStream(xml.getBytes());
		InputSource src = new InputSource(inp);
		return beautify(src);
	}

	public static String beautify(InputSource src) throws JDOMException
	{
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(src);
		XMLOutputter out = new XMLOutputter("  ", true);
		out.setEncoding(getCharacterEncoding());

		return out.outputString(doc);
	}



	/**
	 * Helper which is missing from JDOM Api.
	 * Ben: added this method because JDOM as of v 0.7
	 * has no way to get all child elements from a given namespace.
	 */
	public static List<Element> getChildren( Element el, Namespace ns )
	{
		List<?> clist = el.getContent();
		List<Element> elList = new ArrayList<Element>();

		for(int i=0; i<clist.size(); i++) {
			Object o = clist.get(i);
			if( o instanceof Element ) {
				Element e = (Element) o;
				if( e.getNamespace().equals(ns) ) {
					elList.add(e);
				}
			}
		}
		return elList;
	}




	/**
	 * provide backwards compatibility for crap clients which can't cope with empty tags
	 * @param xml
	 * @return
	 */
	public static String fixXml(String xml) {
		String resultStr =xml;
		DocumentBuilderFactory docBuilderFactory = 
//				DocumentBuilderFactory.newInstance();
				tl.get();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			org.w3c.dom.Document document = docBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
			org.w3c.dom.Document returnDoc = (org.w3c.dom.Document) updateXmlMessage(document);
			resultStr = convertDocToXmlString(returnDoc);
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return resultStr;
	}

	private static Node updateXmlMessage(final Node node) {
		final NodeList nodeList = node.getChildNodes();

		//JIRA ET-85 - check for attributes
		if(nodeList != null && nodeList.getLength() == 0 && "".equals(node.getTextContent()) 
				&& "".equals(node.getAttributes() ) ) {
			org.w3c.dom.Document document = node.getOwnerDocument();
			org.w3c.dom.Element type = document.createElement(node.getNodeName());
			type.appendChild(document.createTextNode(""));//zero length blank space - forces <tag/> instead of <tag><tag/>
			node.getParentNode().replaceChild(type, node);
		}
		for (int i = 0; i < nodeList.getLength(); i++) {
			final Node currentNode = nodeList.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				//calls this method for all the children which are Element
				updateXmlMessage(currentNode);
			}
		}
		return node;

	}

	private static String convertDocToXmlString(org.w3c.dom.Document document) throws Exception {

		DOMSource domSource = new DOMSource(document);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		//transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(domSource, result);

		return writer.toString();

	}

}
