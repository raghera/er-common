package com.vodafone.global.er.http;

import com.google.common.base.Optional;
import com.vizzavi.ecommerce.common.Utils;
import com.vizzavi.ecommerce.util.XmlUtils;
import com.vodafone.config.ConfigProvider;
import com.vodafone.config.util.RequestUtil;
import com.vodafone.global.er.PerfLogger;
import com.vodafone.global.er.PerfLogger.Type;
import com.vodafone.global.er.properties.CommonPropertiesEnum;
import com.vodafone.global.er.properties.CommonPropertyService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * This class should be used to establish HTTP or HTTPS connections. Use {@link #getInstance} to retrieve a connection.
 * re-factored for multi-tenancy to support one connection pool per opco.
 * 
 */
public class ErHttpConnection {

	static Logger log = LoggerFactory.getLogger(ErHttpConnection.class);

	//proxy
	public static final String CONFIG_HTTP_PROXY_ENABLED = "HTTP.SECURITY.PROXY.ENABLED";
	public static final String CONFIG_HTTP_PROXY_HOSTCONFIGURATION_HOST = "HTTP.SECURITY.PROXY.HOST";
	public static final String CONFIG_HTTP_PROXY_HOSTCONFIGURATION_PORT = "HTTP.SECURITY.PROXY.PORT";
	public static final String CONFIG_HTTP_PROXY_CREDENTIALS_USERNAME = "HTTP.SECURITY.PROXY.CREDENTIALS.USERNAME";
	public static final String CONFIG_HTTP_PROXY_CREDENTIALS_PASSWORD = "HTTP.SECURITY.PROXY.CREDENTIALS.PASSWORD";

	//https
	public static final String CONFIG_HTTP_SECURITY_HOSTNAMEVERIFICATION_REQUIRED = "HTTP.SECURITY.HOSTNAME.VERIFICATION.ENABLED";
	public static final String CONFIG_HTTP_SECURITY_CONNECTION_TIMEOUT = "HTTP.SECURITY.CONNECTION.TIMEOUT";
	public static final String CONFIG_HTTP_SECURITY_SOCKET_TIMEOUT = "HTTP.SECURITY.SOCKET.TIMEOUT";
	public static final String CONFIG_HTTP_SECURITY_TRUSTSTORE_FILE = "HTTP.SECURITY.TRUSTSTORE.FILE";
	public static final String CONFIG_HTTP_SECURITY_TRUSTSTORE_PW = "HTTP.SECURITY.TRUSTSTORE.PW";  

	//connection pooling / http options
	public static final String CONFIG_HTTP_SECURITY_MAX_TOTAL_CONNECTIONS = "HTTP.SECURITY.MAX.TOTAL.CONNECTIONS";
	public static final String CONFIG_HTTP_SECURITY_PROTOCOL = "ER.SERVER.PAYMENT.ROUTER.HTTP.PROTOCOL";
	public static final String CONFIG_HTTP_MAX_CONNS_PER_ROUTE= "HTTP.MAX.CONNECTIONS.PER.ROUTE";
	public static final String CONFIG_HTTP_CLOSE_POST_CONNECTION = "er.server.payment.router.http.method.post.connection.close";
	public static final String CONFIG_HTTP_STALE_CHECK = "HTTP.stale.connection.check";

	//instance variables
	private final boolean isPostConnectionToClose = ConfigProvider.getPropertyAsBoolean(CONFIG_HTTP_CLOSE_POST_CONNECTION, false);
	private ClientConnectionManager  mCon;
	private DefaultHttpClient client;
	private String opco;	//not nice 

	/**A map of connections indexed by opcoId - MQC9416*/
	static Map<String, ErHttpConnection> connections = new HashMap<>();

	private ErHttpConnection() throws ErHttpException {
		log.info("New instance of ErHttpConnection (for {})", RequestUtil.getRequestingCountry());
		configure();
	}

	/**
	 * Returns an instance of ErHttpConnection.
	 * 
	 * @return ErHttpConnection
	 */
	public static ErHttpConnection getInstance() throws ErHttpException {
		ErHttpConnection httpConnection = connections.get(RequestUtil.getRequestingCountry());
		//MQc9443 - we need to have a separate http client for each opco, when in multi-opco mode
		if (httpConnection  == null)	{    		
			httpConnection = new ErHttpConnection();	//hashmap can take null as a key
			connections.put(RequestUtil.getRequestingCountry(), httpConnection);
		}
		return httpConnection;
	}

	/**
	 * NB in a multi-tenant environment you need to set RequestUtil.setRequestingCountry first to get the correct config here
	 * @throws ErHttpException
	 */
	public void configure() throws ErHttpException {
		opco=RequestUtil.getRequestingCountry();
		log.info("configuring connection {}", ConfigProvider.isMultiOpCoMode()?opco:"");
		HttpParams params = new BasicHttpParams();	
		HttpHost proxy = null;
		String proxyHostConf = null;
		int proxyPortConf = 0;
		boolean proxyConnectionEnabled = false;

		int connectionTimeout = ConfigProvider.getPropertyAsInteger(
				ErHttpConnection.CONFIG_HTTP_SECURITY_CONNECTION_TIMEOUT,	30000);
		int socketTimeout = ConfigProvider.getPropertyAsInteger(
				ErHttpConnection.CONFIG_HTTP_SECURITY_SOCKET_TIMEOUT,	30000);
		int maxConnections = ConfigProvider.getPropertyAsInteger(
				ErHttpConnection.CONFIG_HTTP_SECURITY_MAX_TOTAL_CONNECTIONS,	25);

		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
		params.setParameter("http.socket.timeout", socketTimeout);
		//there's no ticket for this, but it might help reduce the number of timeouts
		params.setParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, ConfigProvider.getPropertyAsBoolean(CONFIG_HTTP_STALE_CHECK, false));
		SchemeRegistry schemeRegistry = new SchemeRegistry();

		//start MQC7259
		int maxConnsPerRoute = ConfigProvider.getPropertyAsInteger(CONFIG_HTTP_MAX_CONNS_PER_ROUTE, 25);
		params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, new ConnPerRouteBean( maxConnsPerRoute ));
		params.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, maxConnections);
		//not sure about the ConnManagerParams calls - this may be duplication, or redundant.  
		//Unfortunately, to test this you need at least 300 subscriptions to be renewed at the same time
		ConnManagerParams.setMaxConnectionsPerRoute(params,  new ConnPerRouteBean(maxConnsPerRoute));
		//end MQC7259

		schemeRegistry.register( new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		mCon = new ThreadSafeClientConnManager(params, schemeRegistry);

		// Increase max total conn
		ConnManagerParams.setMaxTotalConnections(params, maxConnections);
		ConnManagerParams.setTimeout(params, connectionTimeout);
		client = new DefaultHttpClient(mCon, params);
		proxyConnectionEnabled =  ConfigProvider.getPropertyAsBoolean(ErHttpConnection.CONFIG_HTTP_PROXY_ENABLED, false);
		if(proxyConnectionEnabled)
		{
			proxyHostConf =  ConfigProvider.getProperty(
					ErHttpConnection.CONFIG_HTTP_PROXY_HOSTCONFIGURATION_HOST,
					"localhost");
			proxyPortConf =  ConfigProvider.getPropertyAsInteger(
					ErHttpConnection.CONFIG_HTTP_PROXY_HOSTCONFIGURATION_PORT,
					80);
			log.info("Proxy enabled. (server: {}, port:{}) ", proxyHostConf, proxyPortConf);
			String proxyUsername = ConfigProvider.getProperty(
					ErHttpConnection.CONFIG_HTTP_PROXY_CREDENTIALS_USERNAME,
					null);
			UsernamePasswordCredentials userCredentials = null;
			AuthScope authScope = null;

			if(proxyUsername != null)
			{
				String proxyUserpw = ConfigProvider.getProperty(
						ErHttpConnection.CONFIG_HTTP_PROXY_CREDENTIALS_PASSWORD,
						"changeit");
				userCredentials = new UsernamePasswordCredentials(proxyUsername, proxyUserpw);
				authScope = new AuthScope(proxyHostConf, proxyPortConf);
			}

			client.getCredentialsProvider().setCredentials(authScope, userCredentials);
			proxy = new HttpHost(proxyHostConf, proxyPortConf);
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

		}	        
		//Proxy ends

		if(log.isInfoEnabled())
		{
			log.info("ErHttpConnection.CONFIG_HTTP_SECURITY_MAX_TOTAL_CONNECTIONS: ", maxConnections);
			log.info("ErHttpConnection.CONFIG_HTTP_SECURITY_SOCKET_TIMEOUT: ", socketTimeout);
			log.info("ErHttpConnection.CONFIG_HTTP_SECURITY_CONNECTION_TIMEOUT: ", connectionTimeout);
		}

		Optional<String> protocolOpt = CommonPropertyService.getProperty(CommonPropertiesEnum.PROP_EPA_PROTOCOL.getValue(), "http");
		if (protocolOpt.isPresent() && "https".equals(protocolOpt.get())) {
			configureSSL();
		}
	}

	private void configureSSL() throws ErHttpException {

		log.debug("SSL Configuaration starting");
		ErX509TrustManager tm = new ErX509TrustManager();
		SSLContext sslcontext;

		try {
			sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(new KeyManager[] {tm.getDefaultKeyManager()},
                    new TrustManager[] { tm.getDefaultTrustManager() },
                    null);
		} catch (Exception e) {
			log.error("error getting SSL Context", e);
			throw new ErHttpException(ErHttpException.E0006 , e);
		}

		SSLSocketFactory socketFactory = null;
		try {
			socketFactory = new SSLSocketFactory(sslcontext);

		} catch (Exception e) {
			log.error("creating socketFactory", e);
			throw new ErHttpException(ErHttpException.E0004 , e);
		} 

		if (!ConfigProvider.getPropertyAsBoolean(
				ErHttpConnection.CONFIG_HTTP_SECURITY_HOSTNAMEVERIFICATION_REQUIRED,
				false)) {
			socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		}

		Scheme sch = new Scheme("https", socketFactory, 443);
		client.getConnectionManager().getSchemeRegistry().register(sch);

	}

	/**
	 * send a GET request to the uri specified, and return the result as a byte array
	 * @param uri
	 * @param headers optional - can be null
	 * @return 
	 * @throws ErHttpException
	 */
	public byte[] doGet(String uri,   List<Header> headers)  throws ErHttpException {
		HttpGet  get = new HttpGet(uri);

		addHeaders(headers, get);
		return getResponse(get);
	}

	/**
	 * 
	 * @param uri
	 * @param requestBody
	 * @param contentType
	 * @param closePost
	 * @param headers
	 * @return
	 * @throws ErHttpException
	 */
	public byte[] doPost(String uri, String requestBody, String contentType, boolean closePost, List<Header> headers)
			throws ErHttpException {
		if (log.isDebugEnabled())
			log.debug("POST to {} with headers [{}]", uri, Utils.StringifyList(headers));

		HttpPost  post = new HttpPost(uri);

		addHeaders(headers, post);
		StringEntity reqEntity;
		try {
			//Set character encoding
			reqEntity = new StringEntity(requestBody, XmlUtils.getCharacterEncoding());
			reqEntity.setContentType(contentType);
			post.setEntity(reqEntity);
		} catch (UnsupportedEncodingException e) {
			log.error("doPost", e);
			throw new ErHttpException(ErHttpException.E0005 , e);
		}        		

		return getResponse(post);

	}

    public byte[] doHttpsPost(String uri, String requestBody, String contentType, boolean closePost, List<Header> headers) throws ErHttpException {

        HttpPost post = new HttpPost(uri);
        post.setHeaders(headers.toArray(new Header[headers.size()]));
        StringEntity reqEntity = null;
        try {
            reqEntity = new StringEntity(requestBody, XmlUtils.getCharacterEncoding());
            reqEntity.setContentType(contentType);
            post.setEntity(reqEntity);
        } catch (UnsupportedEncodingException e) {
            throw new ErHttpException(ErHttpException.E0005 , e);
        }
        reqEntity.setContentType(contentType);

        return getResponse(post);

//        throw new UnsupportedOperationException();

    }




	private void addHeaders(List<Header> headers, HttpRequestBase method)	{
		if (headers != null)	{
			for (Header header: headers){
				if (header != null && StringUtils.isNotBlank(header.getValue())){
					method.addHeader( header);
				}
			}
		}
	}


	/**
	 * Sends the request via POST then returns an HttpEntity of the response.<p>  To get the content, call getContent() on the response to get an InputStream.</p>
	 *  <p><b> The InputStream must be closed by the caller.</b></p>
	 * @param uri
	 * @param requestBody
	 * @param contentType for the header, e.g. "application/xml" 
	 * @param headers
	 * @return
	 * @throws ErHttpException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpEntity doPost(String uri, String requestBody, String contentType,  List<Header> headers)
			throws ErHttpException,   IOException {

		HttpPost  post = new HttpPost(uri);

		addHeaders(headers, post);
		StringEntity reqEntity;
		try {
			//Set character encoding
			reqEntity = new StringEntity(requestBody, "UTF-8");
			reqEntity.setContentType(contentType);
			post.setEntity(reqEntity);
			return getResponseEntity(post);    

		} catch (UnsupportedEncodingException e) {
			log.error("doPost", e);
			throw new ErHttpException(ErHttpException.E0005 , e);
		}  catch (IllegalStateException|ClientProtocolException e)	{
			log.error(e.getMessage());
			log.warn(e.getMessage(), e);
			throw new ErHttpException(e);
		} catch (IOException e) {
			log.error("Transport exception", e);       
			/* here goes all the logic to handle exceptions */    
			throw new ErHttpException(getErrorMessage(e), e);
		}catch(ErHttpException e)	{
			throw e;
		}
		//MQC 9980
		catch(Exception e) {
			log.warn("Unknown Error in HttpLayer calling "+ uri, e);
			//rethrow as it is
			throw new ErHttpException("Unknown Error in HttpLayer calling "+ uri, e);
		}

	}



	/**
	 * 
	 * Executes the method, calling the uri, and then reads the response to a byte array
	 * 
	 * @return A byte array containing the HTTP response.
	 * 
	 * @param method an HttpRequestBase (HttpPost or HttpGet) object containing all the necessary content
	 * 
	 * @throws ErHttpException
	 *             if the response was null, the status code was >=400, or any IOException
	 */
	private byte[] getResponse(HttpRequestBase method) throws ErHttpException {

		String errorMessage = null;
		byte[] response = null;

		try {
			HttpEntity entity = getResponseEntity(method);
			//MQC7995 - only null check here - in all other circumstances we should consume the content otherwise the connection won't be released
			if (entity != null) {
				//TODO refactor to unmarshall directly from the stream - it's more efficient
				//using	entity.getContent()
//        		String xml = EntityUtils.toString(entity, HTTP.UTF_8);
				response = EntityUtils.toByteArray(entity);
				entity.consumeContent();
			} else {
				throw new ErHttpException(ErHttpException.E0003 + "response entity is null");	        	
			}
		} catch (ConnectionPoolTimeoutException connEx) {

			log.error("Transport exception", connEx);
			if (connEx.getMessage() != null) {
				if (connEx.getMessage().contains("Timeout waiting for connection")) {
					//MQC 7091 - Add current connections property info
					errorMessage = ErHttpException.E1002 +  " PropertyName: " + CONFIG_HTTP_SECURITY_MAX_TOTAL_CONNECTIONS + ", CurrentValue: " 
							+ ConfigProvider.getProperty(CONFIG_HTTP_SECURITY_MAX_TOTAL_CONNECTIONS);
				}	else	{
					errorMessage = connEx.getMessage();
				}
			}	else	{
				errorMessage = ErHttpException.E0002 ;
			}
			throw new ErHttpException(errorMessage, connEx);
		} catch (IOException e) {	
			log.error("Transport exception", e);       
			/* here goes all the logic to handle exceptions */        	
			errorMessage = getErrorMessage(e);
			throw new ErHttpException(errorMessage, e);
		} catch(ErHttpException e)	{
			throw e;
		}
		//MQC 9980
		catch(Exception e) {
			log.warn("Unknown Error in HttpLayer calling "+ method.getURI().getHost(), e);
			//rethrow as it is
			throw new ErHttpException("Unknown Error in HttpLayer calling "+ method.getURI().getHost(), e);
		}

		return response;
	}

	/**
	 * 
	 * Executes the method, calling the uri, and then reads the response to a HttpEntity
	 * 
	 * @return A HttpEntity containing the HTTP response.
	 * 
	 * @param method an HttpRequestBase (HttpPost or HttpGet) object containing all the necessary content
	 * 
	 * @throws ErHttpException if the status code was >=400
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	private HttpEntity getResponseEntity(HttpRequestBase method)
			throws ErHttpException, ClientProtocolException, IOException {
		int statusCode = -1;
		try {
			if (isPostConnectionToClose)	{
				method.addHeader("connection", "close");
			}
			PerfLogger.start(Type.HTTP, "getResponse", method.getURI());

			HttpResponse httpResponse = client.execute(method);
			log.info("http status of response: {}", httpResponse.getStatusLine());
			statusCode = httpResponse.getStatusLine().getStatusCode();
			if	(statusCode >= 400)	{
				if (httpResponse.getEntity()!=null)	//QC12275 - always call consumeContent, otherwise connection is not released back to the pool
					httpResponse.getEntity().consumeContent();//v important!!
				log.error("http error received: code {}, reason '{}' ", statusCode, httpResponse.getStatusLine().getReasonPhrase());
				throw new ErHttpException(ErHttpException.E0003 + 
						": status code " + statusCode +
						", reason = '" + httpResponse.getStatusLine().getReasonPhrase() + "'", statusCode);
			}
			return httpResponse.getEntity();
		}	finally	{
			PerfLogger.stop(String.valueOf(statusCode));
		}

	}

	private String getErrorMessage(IOException e)	{
		String errorMessage = null;
		if(e.getMessage() != null)		{
			log.debug("raw error message: {} - class {}", e.getMessage(), e.getClass());

			if(e.getMessage().contains("doesn't contain CN or DNS subject"))	{
				errorMessage = ErHttpException.E0009;
			}else if(e.getMessage().contains("SunCertPathBuilderException")){
				errorMessage = ErHttpException.E2000;
			}else if(e.getMessage().contains("CertificateExpiredException")){
				errorMessage = ErHttpException.E2001;
			}else if(e.getMessage().contains("SSLHandshakeException")){
				errorMessage = ErHttpException.E2002;
			}else if(e.getMessage().contains("peer not authenticated")){
				errorMessage = ErHttpException.E2002;
			}else if(e.getMessage().contains("refused")) 			{
				errorMessage = ErHttpException.E0010;
			}else if(e.getMessage().contains("Connection reset"))			{
				errorMessage = ErHttpException.E0012;	
			}else if(e.getClass().getName().contains("SocketTimeoutException")){
				errorMessage = ErHttpException.E1001;
			}	else	{	//QC10818 - Przemek wants more info
				errorMessage = e.getMessage();
			}
		}

		if(errorMessage == null)	{
			errorMessage = ErHttpException.E0002 ;
		}
		log.info(errorMessage);
		return errorMessage;
	}


	public HttpClient getClient() throws ErHttpException {
		return getInstance().client;
	}


	static	{
		//this allows the config for the ErHttpConnections to be updated dynamically during runtime
		ConfigProvider.addObserver(new Observer(){
			@Override
			public void update(Observable o, Object arg) {
				//MQC9416 MutiTenant ER switches to different HTTP.SECURITY.PROXY.HOST/PORT
				//here we are being passed a Map with key <country code> and value List<property name>
				//where the keyset only contains opcos in central.configuration.country (plus AA)
				//for each country code we should set RequestUtil.setRequestingCountry before calling configure
				//in order to get the right config from configprovider (if it's multi-tenancy) 
				// we need to have an opco set in RequestUtil
				try {
					//ConfigProvider.getConfiguredCountries();
					if (arg instanceof Map)	{
						log.info("reconfigure all {} connections", connections.size());
						@SuppressWarnings("unchecked")
						Map<String, List<String>> changedPropsMap = (Map<String, List<String>>)arg;
						for (String opco: changedPropsMap.keySet())	{
							//if we want to call configure(), we don't want to set the requesting country to AA 
							//since calling ConfigProvider.getProperty will then not necessarily give us the same result as for the actual opco
							if (connections.get(opco)!=null)	{
								//strictly speaking we should also do a check on whether the properties are relevant for this class
								//since most times the changedProps are irrelevant
								log.info("reconfiguring for {}", opco);
								RequestUtil.setRequestingCountry(opco);	//so that the configure() method gets the right values from ConfigProvider
								connections.get(opco).configure();
							}
						}
					}	else	
						log.error("arg is not a Map: {}", arg);
				} catch (Exception e) {
					log.error("problem configuring http connection", e);
				}		
			}
		});
	}	//end static init block

}


