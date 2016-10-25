package com.vodafone.global.er.decoupling.client;


import com.google.common.base.Optional;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.common.ErCountry;
import com.vodafone.application.logging.ULFEntry;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.decoupling.binding.request.ErRequest;
import com.vodafone.global.er.decoupling.binding.response.ErResponse;
import com.vodafone.global.er.decoupling.util.xml.JAXBRequestHelper;
import com.vodafone.global.er.decoupling.util.xml.JAXBResponseHelper;
import com.vodafone.global.er.http.ErHttpConnection;
import com.vodafone.global.er.http.ErHttpException;
import com.vodafone.global.er.properties.CommonPropertiesEnum;
import com.vodafone.global.er.translog.TransLogData;
import com.vodafone.global.er.translog.TransLogManager;
import com.vodafone.global.er.translog.TransLogManager.Attr;
import com.vodafone.global.er.translog.TransLogManagerFactory;
import com.vodafone.global.er.ulf.service.ERULFLogDataManager;
import com.vodafone.global.er.ulf.service.impl.ERULFLogDataManagerImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.Element;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static com.vodafone.global.er.properties.CommonPropertyService.getProperty;

/**
 * This class does the http stuff - stream handling, headers, etc
 * 
 * @author Magnus
 */
class DecouplingClient	{


    static final Logger _log = LoggerFactory.getLogger(DecouplingClient.class);
    private static final String PROPERTY_FILE_NAME = "env.properties";

    private static final String _ER_SERVER_HOST;
    private static final int _ER_SERVER_PORT;

	static {
        Properties props = new Properties();

		final InputStream in = DecouplingClient.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
        try {
            props.load(in);
        }catch (IOException ioEx) {
            _log.warn("Unable to load properties from classpath - could not find filename: " + PROPERTY_FILE_NAME
                    + ". Will use system defaults."
            );
        }
        _ER_SERVER_HOST = props.getProperty("er.server.host", "0.0.0.0");
        _ER_SERVER_PORT = Integer.parseInt(props.getProperty("er.server.port", "8094"));

        _log.info("DECOUPLING_client host=" + _ER_SERVER_HOST + " port=" + _ER_SERVER_PORT);

	}


	private final boolean _use_gig = ConfigProvider.getProperty("server.communication.api", "DECOUPLING").equalsIgnoreCase("GIG");

	private final String _HEADER_CONTENT_TYPE = ConfigProvider.getProperty("http.header.content-type", "application/xml");

//	private final String _ER_SERVER_HOST = ConfigProvider.getProperty("er.server.host", "0.0.0.0");
//	private final int 	 _ER_SERVER_PORT = ConfigProvider.getPropertyAsInteger("er.server.port", 8094);

	//MQC 8714 - set from config parameter
	private final String _GIG_BASIC_AUTH_CREDENTIALS = ConfigProvider.getProperty("erif.gig.credentials","");
	private final String _DECOUPLING_PROTOCOL = getProperty(CommonPropertiesEnum.PROP_EPA_PROTOCOL.getValue(), "http").get();
	private final String _DECOUPLING_URL = ConfigProvider.getProperty("server.decoupling.url","/decoupling/DecouplingProcessServlet");

	private String 	_PAYLOAD_CLIENT_APPLICATION_ID = ConfigProvider.getProperty("payload.clientapplicationid");	//we want to force clients to supply a client id
	private String 	_PAYLOAD_CLIENT_DOMAIN = ConfigProvider.getProperty("payload.clientdomain");

	private Locale	_PAYLOAD_PURCHASE_LOCALE = ErCountry.parseLocale(ConfigProvider.getProperty("payload.purchaselocale"));
	private Locale	_PAYLOAD_LANGUAGE_LOCALE =  ErCountry.parseLocale(ConfigProvider.getProperty("payload.languagelocale"));
	
	//ULF
    final TransLogManager transLogManager = TransLogManagerFactory.getInstance();
	final boolean transLogging = transLogManager.isTransLoggingOn();
	ERULFLogDataManager manager = new ERULFLogDataManagerImpl();
	
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
			_log.warn("no decoupling client id supplied");
			throw new RuntimeException("you must supply a client id when creating a decoupling client - either in the method call or by setting property 'payload.clientapplicationid'");
		}	//else
			//_log.info("no clientId supplied creating a decoupling client. will use value from config '{}'", _PAYLOAD_CLIENT_APPLICATION_ID);
		if (StringUtils.isBlank(_PAYLOAD_CLIENT_DOMAIN))	{
			_PAYLOAD_CLIENT_DOMAIN=locale.getCountry()+"."+_PAYLOAD_CLIENT_APPLICATION_ID;
			//_log.error("no client domain from config - will make one up: {}", _PAYLOAD_CLIENT_DOMAIN);
		}
		_log.trace("Locale supplied - will override property setting for PAYLOAD_PURCHASE_LOCALE and PAYLOAD_LANGUAGE_LOCALE with {}" , locale);

	}

	/**
	 * send the string and headers to ER, and return the response String
	 * @param xml
	 * @param headers
	 * @return
	 * @throws ErHttpException
	 * @throws UnsupportedEncodingException
	 */
	private String getResponse(String xml, List<Header> headers) throws ErHttpException, UnsupportedEncodingException	{

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

		//ULF Request sent to core
//		transLogManager.addAttributeOnce(Attr.REQUEST_PL, xml);
//        manager.logULFRequestOut(transLogManager, ULFEntry.Logpoint.REQUEST_OUT);

        if(_DECOUPLING_PROTOCOL.equals("https")) {
            resp_ = conn_.doHttpsPost(getHttpsDecouplingUrl(), xml, _HEADER_CONTENT_TYPE, true, headers);
        } else {
            resp_ = conn_.doPost(getDecouplingUrl(), xml, _HEADER_CONTENT_TYPE, true, headers);
        }

		return new String(resp_, "utf-8");

	}

	private String getDecouplingUrl() {
		//The URL is the same regardless of whether it is GIG or ER.  
		return _DECOUPLING_PROTOCOL +"://" + _ER_SERVER_HOST + ":" + _ER_SERVER_PORT + _DECOUPLING_URL;
	}
	private String getHttpsDecouplingUrl() throws ErHttpException {

		String protocol = getProperty(CommonPropertiesEnum.PROP_EPA_PROTOCOL.getValue(), "https").get();
        Optional<String> host = getProperty(CommonPropertiesEnum.PROP_HTTPS_ER_SERVER_HOST.getValue(), "0.0.0.0");
        Optional<String> port = getProperty(CommonPropertiesEnum.PROP_HTTPS_ER_SERVER_PORT.getValue(), "8094");
        Optional<String> path = getProperty(CommonPropertiesEnum.PROP_HTTPS_ER_SERVER_PATH.getValue(), "/");

        if(!host.isPresent() || !port.isPresent() || !path.isPresent()) {
            throw new ErHttpException("HTTPS Host, Port or Path is not configured correctly");
        }

        return protocol  +"://" + host.get() + ":" + port.get() + path.get();
	}

//	/**
//	 * Converts the element to an xml ByteStream, sends it to the ER core, then formats the response
//	 * @param element {@link Element}
//	 * @return a jaxb object from the com.vodafone.global.er.decoupling.binding.response package
//	 * @throws EcommerceException
//	 * @see  getPayload(Element element, List<Header> headers)
//	 */
//	public Object getPayload(ErRequest element) throws EcommerceException
//	{
//		return getPayload(element, null);
//	}

    private void logRequestOut(String requestXml) {
        if(transLogManager.isOutputPayload()) {
            transLogManager.addAttributeOnce(Attr.REQUEST_PL, requestXml);
        }

        transLogManager.addAttributeOnce(Attr.LOG_POINT, ULFEntry.Logpoint.REQUEST_OUT.name());
        transLogManager.addAttributeOnce(Attr.STATUS, "OK");
        manager.logULFRequestOut(transLogManager, ULFEntry.Logpoint.REQUEST_OUT);
        transLogManager.logRequest(false);
    }

    private void logResponseIn(String responseXml) {
        if(transLogManager.isOutputPayload()) {
            transLogManager.addAttributeOnce(Attr.RESPONSE_PL, responseXml);
        }

        transLogManager.addAttributeOnce(Attr.LOG_POINT, ULFEntry.Logpoint.RESPONSE_IN.name());
        manager.logULFResponseIn(transLogManager, ULFEntry.Logpoint.RESPONSE_IN);
        transLogManager.addAttributeOnce(Attr.STATUS, "OK");
        transLogManager.logResponse(false);
    }

    private void logResponseErrorIn(String errorDescription) {
        transLogManager.addAttributeOnce(Attr.LOG_POINT, ULFEntry.Logpoint.RESPONSE_IN.name());
        transLogManager.addAttributeOnce(Attr.ERROR, errorDescription );
        manager.logULFResponseIn(transLogManager, ULFEntry.Logpoint.RESPONSE_IN);
        transLogManager.addAttributeOnce(Attr.STATUS, "ERROR");
        transLogManager.logResponse(false);
    }

	/**
	 * Converts the element to an xml ByteStream, sends it to the ER core, then parses the xml response and marshalls the payload to a JAXB object which is then returned
	 * @param element {@link Element}
	 * @param headers a List of {@link Header} http headers to be stuck on the request.  Can be null
	 * @return a jaxb object from the com.vodafone.global.er.decoupling.binding.response package
	 * @throws EcommerceException
	 */
	public Object getPayload(ErRequest element,  List<Header> headers) throws EcommerceException	{
        _log.info("Enter DecouplingClient.getPayload");
		String request = null;
		try		{
			String clientId = element.getClientApplicationId();
			if (StringUtils.isBlank(clientId))	//else for some purchase requests it is set in DecouplingMessageFactory.buildEnvelope
				element.setClientApplicationId(_PAYLOAD_CLIENT_APPLICATION_ID);
			element.setClientDomain(_PAYLOAD_CLIENT_DOMAIN);

			if (headers == null)	{
				headers = new ArrayList<>();
			}

			if (StringUtils.isNotBlank(_PAYLOAD_CLIENT_APPLICATION_ID))
				headers.add(new BasicHeader("x-clientid", _PAYLOAD_CLIENT_APPLICATION_ID));

			element.setPurchaseLocale(_PAYLOAD_PURCHASE_LOCALE.toString());
			element.setLanguageLocale(_PAYLOAD_LANGUAGE_LOCALE.toString());
			byte[] byteData = JAXBRequestHelper.getInstance().toByteArray(element);
			request = new String(byteData,"UTF-8");
			//request = JAXBRequestHelper.getInstance().toString(element);

			//Added for transaction id || Start
			String transactionId = getUlfTransactionId();
			headers.add(new BasicHeader("vf-trace-transaction-id",transactionId));
			transLogManager.addAttributeOnce(Attr.VF_TRACE_TRANSACTION_ID, transactionId);
			//Added for transaction id || End

            logRequestOut(request);

			_log.info("have created xml request:\n{}" , request);
			String response = getResponse(request, headers);
			_log.info("xml response:\n{}" , response);

			//ULF Response got from core
//	    	manager.logULFResponseIn(transLogManager, ULFEntry.Logpoint.RESPONSE_IN);
            if(!StringUtils.isBlank(response)) {
                logResponseIn(response);
            } else {
                logResponseErrorIn("Response from ER Core is empty.");
                throw new IOException("Response from ER Core is empty.");
            }
			return getResponseObject(response);
		}
		catch (final ErHttpException e2) {
            logResponseErrorIn(e2.getMessage());
			_log.error("Caught IOException during serialization - is CORE BATCH running?", e2);
			throw new EcommerceException(e2);
		}
		//MQC 8029
		catch (Exception ex) {
            logResponseErrorIn(ex.getMessage());
			_log.error("Unexpected Exception when trying to call ER Core", ex);
			throw new EcommerceException(ex);
		}
	}

	/**
	 * Write the request and response object into transaction log file.
     *
	 * @param request
	 * @param response
	 */
	private void transactionalLogger(String request, String response) {
		TransLogManager transLogManager = TransLogManagerFactory.getInstance();
		TransLogData data=null;
		if (transLogManager.isTransLoggingOn()) {
			data = new TransLogData();
//			data.setDataIsRequest(true);
			data.setRequest(request);
//			transLogManager.logClientData(data);
			data.setResponse(response);
//			data.setDataIsRequest(false);
			transLogManager.logClientData(data);
		}
	}
	
	/**
	 * parses an ER response string, and returns the object inside the payload
	 * @param xmlResponse
	 * @return
	 * @throws JAXBException 
	 * @throws UnsupportedEncodingException 
	 */
	Object getResponseObject(String xmlResponse) throws JAXBException, UnsupportedEncodingException{
        _log.info("Enter DecouplingClient.getResponseObject");
		ErResponse r =JAXBResponseHelper.getInstance().bind(xmlResponse);
		return r.getPayload().getAny();
	}

	/**
	 * this method will be used to get rid of the dependency on ErHttpConnection
	 * @param xml
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	private String post(String xml, List<Header> headers) throws Exception{
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
		
		HttpClient client = new DefaultHttpClient();
//		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(getDecouplingUrl());
		HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
		post.setEntity(entity);
		post.setHeader("Content-Type", _HEADER_CONTENT_TYPE);
		for(Header h: headers)	{
			post.setHeader(h);
		}
		HttpResponse httpResponse = client.execute(post);
		String response= EntityUtils.toString(httpResponse.getEntity());
//		client.getConnectionManager().shutdown();
		return response;
	}
	// This is common method in DecouplingProcessServlet and DecouplingClient to generate transaction-id
	//it may need to move some utility class
	private String getUlfTransactionId() {

		String ulfTransactionId = transLogManager.getAttribute(Attr.VF_TRACE_TRANSACTION_ID );

		if(ulfTransactionId == null || ulfTransactionId.isEmpty()) {
			ulfTransactionId = transLogManager.getAttribute(Attr.VF_INT_TRACE_ID );
		}

		if( ulfTransactionId == null || ulfTransactionId.isEmpty() ) {

			ulfTransactionId = generateId();

		}


		return ulfTransactionId;

	}

	private String generateId() {
		return UUID.randomUUID().toString();
	}
}
