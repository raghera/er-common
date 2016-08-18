package com.vizzavi.ecommerce.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import org.xml.sax.InputSource;

import com.vodafone.config.ConfigProvider;


public class XmlUtils
{
    final static String SAX_DRIVER = "org.apache.xerces.parsers.SAXParser";
    static XMLOutputter sJdomOutputter = new XMLOutputter("  ", false);

    static SAXBuilder sJdomBuilder     = new SAXBuilder( SAX_DRIVER, false );

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


    public static void main(String[] args)
    {
        try {

            if(args.length == 0 ) {
                System.out.println("Usage: filename");
                return;
            }

            InputSource saxIn = new InputSource( new FileInputStream(args[0]) );
            System.out.println( beautify( saxIn ) );

            /*
            String xml = "<?xml version=\"1.0\"?> <doc/>";

            // test: toJdom(String)
            Element root = toJdom( xml );

            // test: toXml( Element )
            System.out.println( toXml( root ) );

            // test getChildren( Element, Namespace )
            Namespace ns = Namespace.getNamespace("a", "urn:a");
            root.addContent( new Element("kid1") );
            root.addContent( new Element("kid2", ns) );
            List els = getChildren( root, ns );
            Element kid2 = (Element) els.get(0);
            System.out.println( kid2.toString() );
            */

        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

}
