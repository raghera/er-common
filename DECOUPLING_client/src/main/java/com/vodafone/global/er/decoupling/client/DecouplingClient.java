package com.vodafone.global.er.decoupling.client;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.common.ErCountry;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.decoupling.binding.request.ErRequest;
import com.vodafone.global.er.decoupling.exceptions.ValidationException;
import com.vodafone.global.er.decoupling.util.xml.JAXBRequestHelper;
import com.vodafone.global.er.decoupling.util.xml.XmlSubParser;
import com.vodafone.global.er.http.ErHttpConnection;
import com.vodafone.global.er.http.ErHttpException;

/**
 * This class does the http stuff - stream handling, headers, etc
 * 
 * 
 * @author Magnus
 */
class DecouplingClient
{

	static final Logger _log = LoggerFactory.getLogger(DecouplingClient.class);
	private final boolean _use_gig = ConfigProvider.getProperty("server.communication.api", "DECOUPLING").equalsIgnoreCase("GIG");

	private final String 	_HEADER_CONTENT_TYPE = ConfigProvider.getProperty("http.header.content-type", "application/x-www-form-urlencoded");

	private final String 	_ER_SERVER_HOST = ConfigProvider.getProperty("er.server.host", "0.0.0.0");
	private final int 		_ER_SERVER_PORT = ConfigProvider.getPropertyAsInteger("er.server.port",8094);

	//MQC 8714 - set from config parameter
	private final String 	_GIG_BASIC_AUTH_CREDENTIALS = ConfigProvider.getProperty("erif.gig.credentials","");
	private final String 	_DECOUPLING_PROTOCOL = ConfigProvider.getProperty("ER.SERVER.PAYMENT.ROUTER.HTTP.PROTOCOL", "http");
	private final String 	_DECOUPLING_URL = ConfigProvider.getProperty("server.decoupling.url","/decoupling/DecouplingProcessServlet");

	private String 	_PAYLOAD_CLIENT_APPLICATION_ID = ConfigProvider.getProperty("payload.clientapplicationid", "decoupling-client");
	private String 	_PAYLOAD_CLIENT_DOMAIN = ConfigProvider.getProperty("payload.clientdomain");

	private Locale	_PAYLOAD_PURCHASE_LOCALE = ErCountry.parseLocale(ConfigProvider.getProperty("payload.purchaselocale"));
	private Locale	_PAYLOAD_LANGUAGE_LOCALE =  ErCountry.parseLocale(ConfigProvider.getProperty("payload.languagelocale"));



	/**
 	 * if you don't specify a clientId in the constructor you must have a property called payload.clientapplicationid
	 * @see {@link #DecouplingClient(Locale locale, String clientId) }
	 * @param locale
	 */
	public DecouplingClient(Locale locale)
	{
		this(locale, null);
	}

	/**
	 * get a decoupling client for a particular locale - you also need to specify the client Id (which will be used in the requests)
	 * @param locale
	 * @param clientId
	 */
	public DecouplingClient(Locale locale, String clientId)
	{
		_PAYLOAD_PURCHASE_LOCALE = locale;
		_PAYLOAD_LANGUAGE_LOCALE = locale;
		if (StringUtils.isNotBlank(clientId))
			_PAYLOAD_CLIENT_APPLICATION_ID=clientId;
		else if (StringUtils.isBlank(_PAYLOAD_CLIENT_APPLICATION_ID))	{
			_log.error("no client id supplied");
			throw new RuntimeException("you must supply a client id when creating a decoupling client - either in the method call or by setting property 'payload.clientapplicationid'");
		}	else
			_log.info("no clientId supplied creating a decoupling client. will use value from config '{}'", _PAYLOAD_CLIENT_APPLICATION_ID);
		if (StringUtils.isBlank(_PAYLOAD_CLIENT_DOMAIN))	{
			_PAYLOAD_CLIENT_DOMAIN=locale.getCountry()+"."+_PAYLOAD_CLIENT_APPLICATION_ID;
			_log.error("no client domain from config - will make one up: {}", _PAYLOAD_CLIENT_DOMAIN);
		}
		_log.trace("Locale supplied - will override property setting for PAYLOAD_PURCHASE_LOCALE and PAYLOAD_LANGUAGE_LOCALE with {}" , locale);

	}


	private String getResponse(String xml, List<Header> headers) throws IOException
	{
		try
		{
			final ErHttpConnection conn_ = ErHttpConnection.getInstance();
			byte[] resp_ = null;

			if(_use_gig)      {
				headers.add(new BasicHeader("Authorization", _GIG_BASIC_AUTH_CREDENTIALS));
			}
			//MQC 7557 - add country header for ERCC (this will affect all clients though) for global ER routing
			//only add country header if it's not already there....
			boolean addCountryHeader=true;
			for (final Header tmp: headers)	{
				if (tmp.getName().equalsIgnoreCase("country"))	{
					addCountryHeader=false;
					break;
				}		
			}

			if (addCountryHeader)	{
				_log.debug("adding a country header ({}), since it's not already present", _PAYLOAD_PURCHASE_LOCALE.getCountry());
				headers.add(new BasicHeader("country", _PAYLOAD_PURCHASE_LOCALE.getCountry()));
			}

			resp_ = conn_.doPost(getDecouplingUrl(), xml, _HEADER_CONTENT_TYPE, true, headers.toArray(new Header[headers.size()]));

			return new String(resp_, "utf-8");
		}
		catch(final ErHttpException e)
		{
			_log.error(e.getMessage(), e);
			throw new IOException(e);
			//MQC 8029 Keep original exception
			//throw e;
		}
	}

	protected String getDecouplingUrl() {
		//The URL is the same regardless of whether it is GIG or ER.  
		return _DECOUPLING_PROTOCOL +"://" + _ER_SERVER_HOST + ":" + _ER_SERVER_PORT + _DECOUPLING_URL;
	}

	/**
	 * Converts the element to an xml ByteStream, sends it to the ER core, then formats the response
	 * @param element {@link Element}
	 * @return a jaxb object from the com.vodafone.global.er.decoupling.binding.response package
	 * @throws EcommerceException
	 * @see  getPayload(Element element, List<Header> headers)
	 */
	public Object getPayload(Element element) throws EcommerceException
	{
		return getPayload(element, null);
	}



	/**
	 * Converts the element to an xml ByteStream, sends it to the ER core, then parses the xml response and marshalls the payload to a JAXB object which is then returned
	 * @param element {@link Element}
	 * @param headers a List of {@link Header} http headers to be stuck on the request.  Can be null
	 * @return a jaxb object from the com.vodafone.global.er.decoupling.binding.response package
	 * @throws EcommerceException 
	 */
	public Object getPayload(Element element,  List<Header> headers) throws EcommerceException
	{	
		//TODO see about the duplication between this and XmlResponseManager
		//the other class allows you to separate out the envelope, and get the response if (eg 100005) so we can check it against the expected response
		ByteArrayOutputStream out = null;
		ByteArrayInputStream byteArrayInputStream=null;
		try
		{

			String clientId = ((ErRequest)element).getClientApplicationId();
			if (StringUtils.isBlank(clientId))	//else for some purchase requests it is set in DecouplingMessageFactory.buildEnvelope
				((ErRequest)element).setClientApplicationId(_PAYLOAD_CLIENT_APPLICATION_ID);
			((ErRequest)element).setClientDomain(_PAYLOAD_CLIENT_DOMAIN);

			if (headers == null)	{
				headers = new ArrayList<Header>();
			}

			if (!StringUtils.isBlank(_PAYLOAD_CLIENT_APPLICATION_ID))
				headers.add(new BasicHeader("x-clientid", _PAYLOAD_CLIENT_APPLICATION_ID));

			((ErRequest)element).setPurchaseLocale(_PAYLOAD_PURCHASE_LOCALE.toString());
			((ErRequest)element).setLanguageLocale(_PAYLOAD_LANGUAGE_LOCALE.toString());

			out = JAXBRequestHelper.toByteStream(element);

			_log.info("have created xml request:\n{}" , out);

			String response = getResponse(out.toString(), headers);

			_log.info("xml response:\n{}" , response);

			if (response == null || response.equals("")) 
			{
				throw new IOException("response is empty");
			}

			return getResponseObject(response);
			
		}
		catch (final IOException e2)
		{
			_log.error("Caught IOException during serialization - is CORE BATCH running?", e2);
			throw new EcommerceException(e2);
		}
		//MQC 8029
		catch (Exception ex) {
			_log.error("Unexpected Exception when trying to call ER Core", ex);
			throw new EcommerceException(ex);
		}
		finally	{
			try {
				if (out!=null)	out.close();
				if (byteArrayInputStream!=null)	byteArrayInputStream.close();
			} catch (final IOException e) {
				_log.warn("problem closing byte stream:{}", e.getMessage(), e);
			}
		}
	}

	Object getResponseObject(String xmlResponse)	{
		try
		{
			//TODO refactor: use JAXBResponseHelper for this
			final XmlSubParser parser_ = new XmlSubParser( "payload", xmlResponse);
			final Unmarshaller u_ = JAXBContext.newInstance("com.vodafone.global.er.decoupling.binding.response" ).createUnmarshaller();
			u_.setEventHandler(new ValidationEventHandler()		{
				//this event handler ignores unexpected elements in the xml, but will return false on any other event
				@Override
				public boolean handleEvent(ValidationEvent evt) 
				{
					_log.info("Event Info: {}",evt);
					if(evt.getMessage().toLowerCase().contains("unexpected element"))
						return true;
					return false;
				}
			});	//actually this class is unnecessary as long as we haven't set um.setValidating(true) - validation is off by default in jaxb 1
			return u_.unmarshal(new ByteArrayInputStream(parser_.getInner().getBytes("utf-8")));
		}
		catch (final UnmarshalException ume)
		{
			throw new ValidationException("Invalid XML response.", ume);
		}
		catch (final JAXBException e) {
			throw new RuntimeException("Invalid XML response.  Unable to bind to the object. " + e.toString(), e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
