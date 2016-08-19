package com.vodafone.global.er.generated;

import com.vizzavi.ecommerce.business.catalog.*;
import com.vizzavi.ecommerce.business.charging.PurchaseAttributes;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.PromotionData;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.endpoint.DelegateEndpoints;
import com.vodafone.global.er.util.ExceptionAdapter;
import com.vodafone.global.er.util.HttpClientConnector;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import static com.vodafone.global.er.endpoint.ApiNamesEnum.CATALOG_API;

public class CatalogApiStub  extends HttpClientConnector implements CatalogApi {

    /**
	 * 
	 */
	private static final long	serialVersionUID	= -283724848623294431L;
	private final Locale locale;
    private DelegateEndpoints endpoint = new DelegateEndpoints();

    //CR1231
    //private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(CatalogApiStub.class);
    private static Logger log = Logger.getLogger(CatalogApiStub.class);
    public CatalogApiStub(Locale locale) {
        this.locale = locale;
    }

    @Override
	public com.vizzavi.ecommerce.business.catalog.CatalogService getService (String id)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getService1";
            requestPayload.put("methodName",methodName);
            requestPayload.put("id",id);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.CatalogService)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated
     */
    @Deprecated
	@Override
	public CatalogPackage getPackage(String id)  {
//        return getPackage(id, new Date());
//    }
//
//    //CR1564 -Utctimezone for diff region in country
//    @Override
//	public com.vizzavi.ecommerce.business.catalog.CatalogPackage getPackage (String id, Date date)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getPackage2";
            requestPayload.put("methodName",methodName);
            requestPayload.put("id",id);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.CatalogPackage)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }


    @Deprecated
	//CR1564 -Utctimezone for diff region in country
    @Override
	public com.vizzavi.ecommerce.business.catalog.CatalogPackage getPackage (String packageId,String pricePointId,String tierId)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getPackage4";
            requestPayload.put("methodName",methodName);
            requestPayload.put("packageId",packageId);
            requestPayload.put("pricePointId",pricePointId);
            requestPayload.put("tierId",tierId);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.CatalogPackage)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    @Override
	public com.vizzavi.ecommerce.business.catalog.CatalogPackage[] getPackages ()  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getPackages5";
            requestPayload.put("methodName",methodName);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.CatalogPackage[])result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    @Override
	public com.vizzavi.ecommerce.business.catalog.CatalogService[] getServices ()  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getServices6";
            requestPayload.put("methodName",methodName);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.CatalogService[])result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    @Override
	public com.vizzavi.ecommerce.business.catalog.CatalogPackage[] findPackagesWithService (com.vizzavi.ecommerce.business.catalog.CatalogService catalogService)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "findPackagesWithService7";
            requestPayload.put("methodName",methodName);
            requestPayload.put("catalogService",catalogService);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.CatalogPackage[])result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }


    @Override
	public CatalogPackage[] findPackagesWithService (String msisdn, CatalogService serv, PurchaseAttributes purchaseAttributes)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "findPackagesWithService9";
            requestPayload.put("methodName",methodName);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("serv",serv);
            requestPayload.put("purchaseAttributes",purchaseAttributes);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.CatalogPackage[])result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    @Override
	public String getVersion ()  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getVersion10";
            requestPayload.put("methodName",methodName);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (String)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated
     */
    @Deprecated
	@Override
	public PricePoint getPricePoint(String pricePointId) {
//        return getPricePoint(pricePointId, new Date());
//    }
//
//    @Override
//	public com.vizzavi.ecommerce.business.catalog.PricePoint getPricePoint (String pricePointId, Date date)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getPricePoint11";
            requestPayload.put("methodName",methodName);
            requestPayload.put("pricePointId",pricePointId);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.PricePoint)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    @Override
	public java.util.Locale getLocale ()  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getLocale12";
            requestPayload.put("methodName",methodName);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (java.util.Locale)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    @Override
	public com.vizzavi.ecommerce.business.catalog.Tax getTax (String name)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getTax13";
            requestPayload.put("methodName",methodName);
            requestPayload.put("name",name);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.Tax)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    @Override
	public com.vizzavi.ecommerce.business.catalog.PromotionsResult checkPromotions (String msisdn,com.vizzavi.ecommerce.business.catalog.CatalogService service)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "checkPromotions14";
            requestPayload.put("methodName",methodName);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("service",service);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.PromotionsResult)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }



    @Override
	public Hashtable<String, ExpressData> findExpressPackagesByServiceId (String[] serviceId, boolean headline)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	//boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "findExpressPackagesByServiceId17";
            requestPayload.put("methodName",methodName);
            requestPayload.put("serviceId",serviceId);
            requestPayload.put("headline", new Boolean(headline) );  
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (java.util.Hashtable)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }


    @Override
	public Map<String, ExpressData> findExpressPackagesByServiceId ( String[] serviceId, String msisdn, ExpressDisplayAttribute expressAttribute)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "findExpressPackagesByServiceId19";
            requestPayload.put("methodName",methodName);
            requestPayload.put("serviceId",serviceId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("expressAttribute",expressAttribute);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (Map<String, ExpressData>)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    @Override
	public com.vodafone.global.er.business.catalog.BasePrice[] getBasePrices (String[] serviceId) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getBasePrices20";
            requestPayload.put("methodName",methodName);
            requestPayload.put("serviceId",serviceId);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
                          throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (com.vodafone.global.er.business.catalog.BasePrice[])result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = e2;
                     if (generatedException instanceof  EcommerceException){
                          throw (EcommerceException) generatedException ;
                      }
                     } 
                    else 
					  log.error(" Exception during serialization ", e2);
                }
	     else{
	        throw new com.vizzavi.ecommerce.business.common.EcommerceException(e2);

	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    @Override
	public boolean validateService (String id) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "validateService21";
            requestPayload.put("methodName",methodName);
            requestPayload.put("id",id);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
                          throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
                return ois.readBoolean();
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = e2;
                     if (generatedException instanceof  EcommerceException){
                          throw (EcommerceException) generatedException ;
                      }
                     } 
                    else 
					  log.error(" Exception during serialization ", e2);
                }
	     else{
	        throw new com.vizzavi.ecommerce.business.common.EcommerceException(e2);

	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return false;
    }


    @Override
	public String translatePricingText (com.vizzavi.ecommerce.business.catalog.PricePoint[] pricePoints,String templateName,String languageCode,int RoamingType) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "translatePricingText23";
            requestPayload.put("methodName",methodName);
            requestPayload.put("pricePoints",pricePoints);
            requestPayload.put("templateName",templateName);
            requestPayload.put("languageCode",languageCode);
            requestPayload.put("RoamingType", new Integer(RoamingType) );  
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
                          throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (String)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = e2;
                     if (generatedException instanceof  EcommerceException){
                          throw (EcommerceException) generatedException ;
                      }
                     } 
                    else 
					  log.error(" Exception during serialization ", e2);
                }
	     else{
	        throw new com.vizzavi.ecommerce.business.common.EcommerceException(e2);

	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    @Override
	public boolean isUniquePromoPrecode (String precode)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "isUniquePromoPrecode24";
            requestPayload.put("methodName",methodName);
            requestPayload.put("precode",precode);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
                return ois.readBoolean();
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return false;
    }
    
    @Override
	public com.vizzavi.ecommerce.business.catalog.Tariff getTariff (String tariffName)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getTariff25";
            requestPayload.put("methodName",methodName);
            requestPayload.put("tariffName",tariffName);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.Tariff)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }

    //MQC 5843
    @Override
	public com.vizzavi.ecommerce.business.catalog.PurchaseOptionsAuthorization findPackagesWithServiceEx (String msisdn,com.vizzavi.ecommerce.business.catalog.CatalogService serv,com.vizzavi.ecommerce.business.charging.PurchaseAttributes purchaseAttributes)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "findPackagesWithServiceEx26";
            requestPayload.put("methodName",methodName);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("serv",serv);
            requestPayload.put("purchaseAttributes",purchaseAttributes);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (com.vizzavi.ecommerce.business.catalog.PurchaseOptionsAuthorization)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }
    
    
    
    /* CR1789
	 * @see com.vizzavi.ecommerce.business.catalog.CatalogApi#oneStep(String[], String, boolean)
	 */
	@Override
	public Hashtable<String, OneStepData> findPackagesByServiceIdOneStep(String[] serviceId, String msisdn) {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "findPackagesByServiceIdOneStep29";
            requestPayload.put("methodName",methodName);
            requestPayload.put("serviceId",serviceId);
            requestPayload.put("msisdn",msisdn);  
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                    return (java.util.Hashtable)result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    
	}

	//CR1923 Partner Trading Limit
	@Override
	public com.vizzavi.ecommerce.business.catalog.PartnerTradingLimit[] getPartnerTradingLimits() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getPartnerTradingLimits27";
            requestPayload.put("methodName",methodName);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
			method = new PostMethod( getDelegateUrl() );
			method.addRequestHeader("Content-Type", "application/octet-stream");
		        // Serialize to a byte array
			 byte[] buf = super.serializedPayload(requestPayload);
			 method.setRequestBody(new ByteArrayInputStream(buf));
			 httpclient.executeMethod(method);

			ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
			}
			catch(IOException ie ){
			     ie.printStackTrace();
			}
	        }
		else {
		     URLConnection conn = null;
		     conn = getConnection();
		    oos = getObjectOutputStream(conn);
		    oos.writeObject(requestPayload);
		    oos.flush();
		    oos.close();
		    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
	     }
            try {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      return null;
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
                	return (com.vizzavi.ecommerce.business.catalog.PartnerTradingLimit[])result;
                }
            }
            catch (OptionalDataException e1) {
                log.error("Primitive data in stream");              
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                }
	     else{
	     }
        } 
        finally {
	   try {
                if (oos != null) {
                    oos.close();
                }
	        if (ois != null) {
	            ois.close();
	       }
       		if (method != null ){
       		log.debug("Releasing http connection" );  
	     	  method.releaseConnection();
		}
	   }
	   catch (Exception e3) {
	       log.error("Error closing connection", e3);
	   }
	}
        return null;
    }
	
	//CR1923 Partner Trading Limit
	@Override
	public com.vizzavi.ecommerce.business.catalog.PartnerTradingLimit getPartnerTradingLimit(String partnerId) {
	       ObjectOutputStream oos = null;
	        ObjectInputStream ois = null;
		boolean state = true;
	        PostMethod method = null ;

	        try {
	            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
	            requestPayload.put("locale", locale);
	            String methodName = "getPartnerTradingLimit28";
	            requestPayload.put("methodName",methodName);
	            requestPayload.put("partnerId",partnerId);
		    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

		   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
			try{
				if(httpConnectionManager == null ){
					createConnectionManager();
				}
				log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
				HttpClient httpclient = new HttpClient(httpConnectionManager);

		        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
				method = new PostMethod( getDelegateUrl() );
				method.addRequestHeader("Content-Type", "application/octet-stream");
			        // Serialize to a byte array
				 byte[] buf = super.serializedPayload(requestPayload);
				 method.setRequestBody(new ByteArrayInputStream(buf));
				 httpclient.executeMethod(method);

				ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
				}
				catch(IOException ie ){
				     ie.printStackTrace();
				}
		        }
			else {
			     URLConnection conn = null;
			     conn = getConnection();
			    oos = getObjectOutputStream(conn);
			    oos.writeObject(requestPayload);
			    oos.flush();
			    oos.close();
			    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
		     }
	            try {
	            	long beforeReadObject = System.currentTimeMillis() ;
	                Object result = ois.readObject();
	              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
	                   if (result == null)
	                    {
	                      log.debug("Encountered NULL from the Input Stream. Returning...");
	                      return null;
	                    }
	                log.debug("Result object type: " + result.getClass().getName());
	                if (result instanceof ExceptionAdapter) {
						log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
	                }
	                else                 
	                {
	                    return (com.vizzavi.ecommerce.business.catalog.PartnerTradingLimit)result;
	                }
	            }
	            catch (OptionalDataException e1) {
	                log.error("Primitive data in stream");              
	            }
	            catch (ClassNotFoundException e4) {
	                log.error("Exception during deserialization", e4);
	            }
	        }
	        catch (IOException e2) {
	            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
	            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
	                }
		     else{
		     }
	        } 
	        finally {
		   try {
	                if (oos != null) {
	                    oos.close();
	                }
		        if (ois != null) {
		            ois.close();
		       }
	       		if (method != null ){
	       		log.debug("Releasing http connection" );  
		     	  method.releaseConnection();
			}
		   }
		   catch (Exception e3) {
		       log.error("Error closing connection", e3);
		   }
		}
	        return null;
	    }
	
	public URLConnection getConnection() throws IOException {
        String url = getDelegateUrl();

        URL server = null;     
        server = new URL(url);
        URLConnection conn = server.openConnection();
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Content-Type", "application/octet-stream");
        return conn;
    }

	protected String getDelegateUrl() {
        return endpoint.getUrl(CATALOG_API);
	}
    public ObjectOutputStream getObjectOutputStream(URLConnection conn) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(conn.getOutputStream()));
        return oos;
    }

	@Override
	public PromotionsResult checkPromotionSummary(PromotionData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OverallGoodwillCreditLimits getOverallGoodwillCreditLimits() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FindExpressPackagesResponseDTO findFullExpressPackagesByServiceId(
			String[] serviceId, String msisdn,
			ExpressDisplayAttribute expressAttribute) {
		return null;
	}

	@Override
	public DefaultGoodwillCreditLimits getDefaultGoodwillCreditLimits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultSpendLimits getDefaultPartnerSpendLimits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultSpendLimits getDefaultMsisdnSpendLimits() {
		// TODO Auto-generated method stub
		return null;
	}

        //MQC7712
        @Override
        public List<String> getVersions(){
            return null;
        }


}
