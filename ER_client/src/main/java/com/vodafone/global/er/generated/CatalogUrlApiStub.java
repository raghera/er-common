//package com.vodafone.global.er.generated;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.OptionalDataException;
//import java.io.Serializable;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.HashMap;
//import java.util.Locale;
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.log4j.Logger;
//
//import com.vizzavi.ecommerce.business.catalog.CatalogUrlApi;
//import com.vodafone.config.ConfigProvider;
//import com.vodafone.global.er.util.ExceptionAdapter;
//import com.vodafone.global.er.util.HttpClientConnector;
//
//public class CatalogUrlApiStub  extends HttpClientConnector implements CatalogUrlApi {
//
//	private static final long	serialVersionUID	= -2908899731033654851L;
//	private final Locale locale;
//    //CR1231
//    //private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(CatalogUrlApiStub.class);
//    private static Logger log = Logger.getLogger(CatalogUrlApiStub.class);
//    public CatalogUrlApiStub(Locale locale) {
//        this.locale = locale;
//    }
//
//    @Override
//	public com.vizzavi.ecommerce.business.catalog.CatalogService findServiceByUrlPath (String urlPath)  {
//        ObjectOutputStream oos = null;
//        ObjectInputStream ois = null;
//	boolean state = true;
//        PostMethod method = null ;
//
//        try {
//            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
//            requestPayload.put("locale", locale);
//            String methodName = "findServiceByUrlPath1";
//            requestPayload.put("methodName",methodName);
//            requestPayload.put("urlPath",urlPath);
//	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");
//
//	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
//		try{
//			if(httpConnectionManager == null ){
//				createConnectionManager();
//			}
//			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
//			HttpClient httpclient = new HttpClient(httpConnectionManager);
//
//	        httpclient.setConnectionTimeout(Integer.parseInt(ConfigProvider.getProperty("er.http.connection.timeout","30000")));
//			method = new PostMethod( getDelegateUrl() );
//			method.addRequestHeader("Content-Type", "application/octet-stream");
//		        // Serialize to a byte array
//			 byte[] buf = super.serializedPayload(requestPayload);
//			 method.setRequestBody(new ByteArrayInputStream(buf));
//			 httpclient.executeMethod(method);
//
//			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
//			}
//			catch(IOException ie ){
//			     ie.printStackTrace();
//			}
//	        }
//		else {
//		     URLConnection conn = null;
//		     conn = getConnection();
//		    oos = getObjectOutputStream(conn);
//		    oos.writeObject(requestPayload);
//		    oos.flush();
//		    oos.close();
//		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
//	     }
//            try {
//            	long beforeReadObject = System.currentTimeMillis() ;
//                Object result = ois.readObject();
//              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
//                   if (result == null)
//                    {
//                      log.debug("Encountered NULL from the Input Stream. Returning...");
//                      return null;
//                    }
//                log.debug("Result object type: " + result.getClass().getValue());
//                if (result instanceof ExceptionAdapter) {
//					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
//                }
//                else                 
//                {
//                    return (com.vizzavi.ecommerce.business.catalog.CatalogService)result;
//                }
//            }
//            catch (OptionalDataException e1) {
//                log.error("Primitive data in stream");              
//            }
//            catch (ClassNotFoundException e4) {
//                log.error("Exception during deserialization", e4);
//            }
//        }
//        catch (IOException e2) {
//            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
//            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
//                }
//	     else{
//	     }
//        } 
//        finally {
//	   try {
//                if (oos != null) {
//                    oos.close();
//                }
//	        if (ois != null) {
//	            ois.close();
//	       }
//       		if (method != null ){
//       		log.debug("Releasing http connection" );  
//	     	  method.releaseConnection();
//		}
//	   }
//	   catch (Exception e3) {
//	       log.error("Error closing connection", e3);
//	   }
//	}
//        return null;
//    }
//
//    public URLConnection getConnection() throws IOException {
//        String url = getDelegateUrl();
//
//        URL server = null;     
//        server = new URL(url);
//        URLConnection conn = server.openConnection();
//        conn.setDoOutput(true);
//        conn.setUseCaches(false);
//        conn.setRequestProperty("Content-Type", "application/octet-stream");
//        return conn;
//    }
//
//	protected String getDelegateUrl() {
//		String serverHost = ConfigProvider.getProperty("er.server.host");
//        String serverPort = ConfigProvider.getProperty("er.server.port");
//        String url = "http://" + serverHost + ":" + serverPort + "/delegates/CatalogUrlApi";
//        log.info("ER delegate connection URL: " + url);
//		return url;
//	}
//    public ObjectOutputStream getObjectOutputStream(URLConnection conn) throws IOException {
//        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(conn.getOutputStream()));
//        return oos;
//    }
//
//}
