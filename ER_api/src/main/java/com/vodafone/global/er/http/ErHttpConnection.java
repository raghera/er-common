package com.vodafone.global.er.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
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
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.common.Utils;
import com.vizzavi.ecommerce.util.XmlUtils;
import com.vodafone.config.ConfigProvider;
import com.vodafone.config.util.RequestUtil;
import com.vodafone.global.er.PerfLogger;
import com.vodafone.global.er.PerfLogger.Type;

/**
 * This class should be used to establish HTTP or HTTPS connections. Use {@link #getInstance} to retrieve a connection.
 * re-factored for multi-tenancy to support one connection pool per opco.
 * 
 */
public class ErHttpConnection {

    static Logger log = LoggerFactory.getLogger(ErHttpConnection.class);

    //parameter names
    public static final String CONFIG_HTTP_PROXY_ENABLED = "HTTP.SECURITY.PROXY.ENABLED";
    public static final String CONFIG_HTTP_PROXY_HOSTCONFIGURATION_HOST = "HTTP.SECURITY.PROXY.HOST";
    public static final String CONFIG_HTTP_PROXY_HOSTCONFIGURATION_PORT = "HTTP.SECURITY.PROXY.PORT";
    public static final String CONFIG_HTTP_PROXY_CREDENTIALS_USERNAME = "HTTP.SECURITY.PROXY.CREDENTIALS.USERNAME";
    public static final String CONFIG_HTTP_PROXY_CREDENTIALS_PASSWORD = "HTTP.SECURITY.PROXY.CREDENTIALS.PASSWORD";
    public static final String CONFIG_HTTP_SECURITY_HOSTNAMEVERIFICATION_REQUIRED = "HTTP.SECURITY.HOSTNAME.VERIFICATION.ENABLED";
    public static final String CONFIG_HTTP_SECURITY_CONNECTION_TIMEOUT = "HTTP.SECURITY.CONNECTION.TIMEOUT";
    public static final String CONFIG_HTTP_SECURITY_SOCKET_TIMEOUT = "HTTP.SECURITY.SOCKET.TIMEOUT";
    public static final String CONFIG_HTTP_SECURITY_MAX_TOTAL_CONNECTIONS = "HTTP.SECURITY.MAX.TOTAL.CONNECTIONS";
    public static final String CONFIG_HTTP_SECURITY_PROTOCOL = "ER.SERVER.PAYMENT.ROUTER.HTTP.PROTOCOL";
    public static final String CONFIG_HTTP_SECURITY_TRUSTSTORE_FILE = "HTTP.SECURITY.TRUSTSTORE.FILE";
    public static final String CONFIG_HTTP_SECURITY_TRUSTSTORE_PW = "HTTP.SECURITY.TRUSTSTORE.PW";    
    public static final String CONFIG_HTTP_MAX_CONNS_PER_ROUTE= "HTTP.MAX.CONNECTIONS.PER.ROUTE";
    public static final String CONFIG_HTTP_CLOSE_POST_CONNECTION = "er.server.payment.router.http.method.post.connection.close";
    public static final String CONFIG_HTTP_STALE_CHECK = "HTTP.stale.connection.check";
   
    //instance variables
    private final boolean isPostConnectionToClose = ConfigProvider.getPropertyAsBoolean(CONFIG_HTTP_CLOSE_POST_CONNECTION, false);
    private ClientConnectionManager  mCon;
    private DefaultHttpClient client;
    private String opco;	//not nice 

    /**A map of connections indexed by opcoId - MQC9416*/
    static Map<String, ErHttpConnection> connections = new HashMap<String, ErHttpConnection>();
    
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
     * NB in a multi-tenancy environment you need to set RequestUtil.setRequestingCountry first to get the correct config here
     * @throws ErHttpException
     */
	public  void configure() throws ErHttpException {
		opco=RequestUtil.getRequestingCountry();
		log.info("configuring connection {}", ConfigProvider.isMultiOpCoMode()?opco:"");
		HttpParams params = new BasicHttpParams();	
		HttpHost proxy = null;
		String proxyHostConf = null;
		int proxyPortConf = 0;
		boolean proxyConnectionEnabled = false;
		
		int connectionTimeout = ConfigProvider.getPropertyAsInteger(
		    					ErHttpConnection.CONFIG_HTTP_SECURITY_CONNECTION_TIMEOUT,	30000);
		int  socketTimeout = ConfigProvider.getPropertyAsInteger(
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

		//MQC 6898, only configure SSL if required
		String protocol = ConfigProvider.getProperty(CONFIG_HTTP_SECURITY_PROTOCOL, "http");

		if ("https".equals(protocol)) {
			configureSSL();
		}
	}

    private void configureSSL() throws ErHttpException {

   		log.debug("configureConnection starting");
   		ErX509TrustManager myTrustManager = new ErX509TrustManager();
    	SSLContext sslcontext;

    	try {
			sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myTrustManager }, null);
			//sslcontext.init(null, null, null);
		} catch (Exception e) {
			log.error("error getting SSL Context", e);
			throw new ErHttpException(ErHttpException.E0006 + "\n" + e);
		}

    	SSLSocketFactory socketFactory = null;
		try {
			socketFactory = new SSLSocketFactory(sslcontext);

		} catch (Exception e) {
			log.error("creating socketFactory", e);
			throw new ErHttpException(ErHttpException.E0004 + "\n" + e);
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
     * 
     * @param uri
     * @param requestBody
     * @param contentType
     * @param closePost
     * @param headers
     * @return
     * @throws ErHttpException
     */
    public byte[] doPost(String uri, String requestBody, String contentType, boolean closePost, Header[] headers)
    throws ErHttpException {
    	if (log.isDebugEnabled())
    		log.debug("POST with headers [{}] to {}", Utils.stringifyList(headers), uri);
        
        HttpPost  post = new HttpPost(uri);
        if (headers != null){
        	for (Header header: headers){
        		if (header != null && StringUtils.isNotBlank(header.getValue())){
        			post.addHeader( header);
        		}
        	}
        }
        
        StringEntity reqEntity;
		try {
			//Set character encoding
			reqEntity = new StringEntity(requestBody, XmlUtils.getCharacterEncoding());
	        reqEntity.setContentType(contentType);
	        post.setEntity(reqEntity);
		} catch (UnsupportedEncodingException e) {
			log.error("doPost", e);
			throw new ErHttpException(ErHttpException.E0005 + "\n" + e.getMessage());
		}        		
    
        return getResponse(post);
    	
    }
    
    /**
     * 
     * Establishes an HTTP connection (POST method).
     * 
     * @return A byte array containing the HTTP response.
     * 
     * @param uri A String containing either an absolute or relative URI.
     * 
     * @param requestBody
     *            A byte array containing the HTTP request body.
     * 
     * @param retryCount
     *            An int containing the maximum number of times the connection
     *            will be retried.
     * 
     * @throws ErHttpException
     *             The exception thrown when something went wrong.
     */
    public byte[] doPost(String uri, String requestBody, String contentType, boolean closePost)
            throws ErHttpException {
    	//MQC6980 refactor
    	return doPost(uri, requestBody, contentType, closePost, null);
    }

    /**
     * 
     * Establishes an HTTP connection (POST method).
     * 
     * @return A byte array containing the HTTP response.
     * 
     * @param uri
     *            A String containing either an absolute or relative URI.
     * 
     * @param requestBody
     *            A byte array containing the HTTP request body.
     * 
     * @param retryCount
     *            An int containing the maximum number of times the connection will be retried.
     * 
     * @throws ErHttpException
     *             The exception thrown when something went wrong.
     */
    public byte[] doPost(String uri, ByteArrayOutputStream baos, String contentType)
            throws ErHttpException {

        log.debug("Trying to establish an http connection (POST method) to URI: {}" , uri);
        HttpPost  post = new HttpPost(uri);
        ByteArrayEntity reqEntity;
		try {
			reqEntity = new ByteArrayEntity(baos.toByteArray());
	        reqEntity.setContentType(contentType);
	        post.setEntity(reqEntity);

		} catch (Exception e) {
			log.error("doPost", e);
			throw new ErHttpException(ErHttpException.E0005 + "\n" + e.getMessage());
		}        		
    
        return getResponse(post);
    }
    
    /**
     * 
     * Establishes an HTTP connection.
     * 
     * @return A byte array containing the HTTP response.
     * 
     * @param method an HttpPost object containing all the necessary content
     * 
     * @throws ErHttpException
     *             The exception thrown when something went wrong.
     */
    private byte[] getResponse(HttpPost method)
            throws ErHttpException {

        String errorMessage = null;
        byte[] response = null;
        HttpResponse httpResponse;
        
        try {
        	if(isPostConnectionToClose)        	{
        		method.addHeader("connection","close");
        	}
        	PerfLogger.start(Type.HTTP, "getResponse", method.getURI());

        	httpResponse = client.execute(method);
        	HttpEntity entity = httpResponse.getEntity();
        	log.debug("http status of response: {}", httpResponse.getStatusLine());
        	//MQC7995 - only null check here - in all other circumstances we should consume the content otherwise the connection won't be released
        	if (entity != null) {
	        	response = EntityUtils.toByteArray(entity);
	        	entity.consumeContent();
	        } else {
	        	log.error("HttpEntity is null - http status {}, reason '{}' ",  httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine().getReasonPhrase());
				throw new ErHttpException(ErHttpException.E0003 + 
						": status code " + httpResponse.getStatusLine().getStatusCode() +
						", reason = '" + httpResponse.getStatusLine().getReasonPhrase() + "'");	        	
	        }
        	if	(httpResponse.getStatusLine().getStatusCode()>=400)	{
        		log.error("http error received: code {}, reason '{}' ",httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine().getReasonPhrase());
        		throw new ErHttpException(ErHttpException.E0003 + 
						": status code " + httpResponse.getStatusLine().getStatusCode() +
						", reason = '" + httpResponse.getStatusLine().getReasonPhrase() + "'");
        	}
        }
        
        //MQC 7091 Start
        catch (ConnectionPoolTimeoutException connEx) {
        	
        	log.error("Transport exception", connEx);
        	if (connEx.getMessage() != null) {
        		if (connEx.getMessage().contains("Timeout waiting for connection")) {
        			//MQC 7091 - Add current connections property info
        			errorMessage = ErHttpException.E1002 +  " PropertyName: " + CONFIG_HTTP_SECURITY_MAX_TOTAL_CONNECTIONS + ", CurrentValue: " 
            				+ ConfigProvider.getProperty(CONFIG_HTTP_SECURITY_MAX_TOTAL_CONNECTIONS, "25");
        		}
     
            	if(errorMessage == null){
            		errorMessage = ErHttpException.E0002  + "\n" + connEx;
            	}
        	}
        }
        //MQC 7091 End

        catch (IOException e) {
        	
        	log.error("Transport exception", e);       

        	/* here goes all the logic to handle exceptions */        	
        	if(e.getMessage() != null)
        	{
        		log.debug("raw error message: {} - class {}", e.getMessage(), e.getClass());
        			
    			if(e.getMessage().contains("doesn't contain CN or DNS subject"))	{
        			log.info("Host name verification failed");
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
        			log.warn("Connection to host refused");
        			errorMessage = ErHttpException.E0010;
    			}else if(e.getMessage().contains("Connection reset"))			{
        			log.info("Connection reset");
        			errorMessage = ErHttpException.E0012;	
        		}else if(e.getClass().getName().contains("SocketTimeoutException")){
       				log.info("SocketTimeoutException: Socket read timed out");
        			errorMessage = ErHttpException.E1001;
	    		}
        	}
        
        	if(errorMessage == null){
        		errorMessage = ErHttpException.E0002  + "\n" + e;
        	}
        	
        } catch (ErHttpException e) {
        	throw(e);
        } catch (Exception e) {
        	errorMessage = e.getMessage();
        	log.error("An unrecoverable exception has occured", e);
        } finally	{
        	PerfLogger.stop();
        }

        // if attempts exceeded throw ErHttpException
        if (errorMessage != null ) {
            throw new ErHttpException(errorMessage);
        }
        return response;
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


