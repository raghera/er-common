package com.vodafone.global.er.generated;

import com.vizzavi.ecommerce.business.charging.ChargingApi;
import com.vizzavi.ecommerce.business.charging.UsageAuthorization;
import com.vizzavi.ecommerce.business.charging.UsageAuthorizationException;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.util.ExceptionAdapter;
import com.vodafone.global.er.util.HttpClientConnector;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

public class ChargingApiStub  extends HttpClientConnector implements ChargingApi {

    private final Locale locale;
    //CR1231
    //private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(ChargingApiStub.class);
    private static Logger log = Logger.getLogger(ChargingApiStub.class);
    
    public ChargingApiStub(Locale locale) {
        this.locale = locale;
    }

    @Override
	public com.vizzavi.ecommerce.business.charging.UsageAuthorization usageAuth (String clientApplicationId,String msisdn,String serviceId,com.vizzavi.ecommerce.business.charging.UsageAttributes usageAttributes) throws com.vizzavi.ecommerce.business.charging.UsageAuthorizationException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "usageAuth1";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientApplicationId",clientApplicationId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("serviceId",serviceId);
            requestPayload.put("usageAttributes",usageAttributes);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(Integer.parseInt(ConfigProvider.getProperty("er.http.connection.timeout","30000")));
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
					exceptionVector.add("com.vizzavi.ecommerce.business.charging.UsageAuthorizationException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.charging.UsageAuthorizationException)
                          throw (com.vizzavi.ecommerce.business.charging.UsageAuthorizationException) generatedException ;
                     }
                    else
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else
                {
                    return (com.vizzavi.ecommerce.business.charging.UsageAuthorization)result;
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
					exceptionVector.add("com.vizzavi.ecommerce.business.charging.UsageAuthorizationException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = e2;
                                          }
                    else
					  log.error(" Exception during serialization ", e2);
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
	public com.vizzavi.ecommerce.business.charging.UsageAuthorization usageAuthRate (String clientApplicationId,String msisdn,String serviceId,com.vizzavi.ecommerce.business.charging.UsageAttributes usageAttributes) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "usageAuthRate2";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientApplicationId",clientApplicationId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("serviceId",serviceId);
            requestPayload.put("usageAttributes",usageAttributes);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(Integer.parseInt(ConfigProvider.getProperty("er.http.connection.timeout","30000")));
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
                    return (com.vizzavi.ecommerce.business.charging.UsageAuthorization)result;
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
	public com.vizzavi.ecommerce.business.charging.UsageAuthorization usageAuthRateCharge (String clientApplicationId,String msisdn,String serviceId,com.vizzavi.ecommerce.business.charging.UsageAttributes usageAttributes) throws com.vizzavi.ecommerce.business.charging.UsageAuthorizationException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "usageAuthRateCharge3";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientApplicationId",clientApplicationId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("serviceId",serviceId);
            requestPayload.put("usageAttributes",usageAttributes);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(Integer.parseInt(ConfigProvider.getProperty("er.http.connection.timeout","30000")));
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
					exceptionVector.add("com.vizzavi.ecommerce.business.charging.UsageAuthorizationException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.charging.UsageAuthorizationException)
                          throw (com.vizzavi.ecommerce.business.charging.UsageAuthorizationException) generatedException ;
                     }
                    else
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else
                {
                    return (com.vizzavi.ecommerce.business.charging.UsageAuthorization)result;
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
					exceptionVector.add("com.vizzavi.ecommerce.business.charging.UsageAuthorizationException");
					if (exceptionVector.contains(exceptionName)){
						 Exception generatedException = e2;
	                     throw (com.vizzavi.ecommerce.business.charging.UsageAuthorizationException) generatedException ;
					}
                    else
					  log.error(" Exception during serialization ", e2);
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
	public com.vizzavi.ecommerce.business.charging.UsageAuthorization usageComplete (String clientApplicationId,String eventReservationId,int deliveryStatus) throws com.vizzavi.ecommerce.business.charging.UsageAuthorizationException
    {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean state = true;
		PostMethod method = null ;

		try
		{
			HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
			requestPayload.put("locale", locale);
			String methodName = "usageComplete4";
			requestPayload.put("methodName",methodName);
			requestPayload.put("clientApplicationId",clientApplicationId);
			requestPayload.put("eventReservationId",eventReservationId);
			requestPayload.put("deliveryStatus", new Integer(deliveryStatus) );
			String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

			if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient"))
			{
				try
				{
					if(httpConnectionManager == null )
					{
						createConnectionManager();
					}
					log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
					HttpClient httpclient = new HttpClient(httpConnectionManager);

					httpclient.setConnectionTimeout(Integer.parseInt(ConfigProvider.getProperty("er.http.connection.timeout","30000")));
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
			else
			{
				URLConnection conn = null;
				conn = getConnection();
				oos = getObjectOutputStream(conn);
				oos.writeObject(requestPayload);
				oos.flush();
				oos.close();
				ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
			}

//Remedy 6008, Bruno Meseguer, change for Batch cancel Payment
//this piece of code has been refactored
//----------------------------------------------------------------------------------

			Object result = deserializeResponse(ois);

			//happy scenario, we return the result
			if(result instanceof UsageAuthorization)
					return (UsageAuthorization)result;

			//all other cases are not expected
			else
				throw new UsageAuthorizationException("Unexpected deserialized object: " + result);

        }
        catch(UsageAuthorizationException usageException)
        {
			//As defined in the API interface only a UsageAuthorizationException must be thrown
			throw usageException;
		}
        catch(Exception toHandle)
        {
			//unexpected exception
			throw new UsageAuthorizationException("API invokation failure.", toHandle);
        }

//----------------------------------------------------------------------------------
//Remedy 6008, Bruno Meseguer, change for Batch cancel Payment
//end of refactoring

        finally
        {
			try
			{
				if (oos != null)
					oos.close();

				if (ois != null)
					ois.close();

				if (method != null )
				{
					log.debug("Releasing http connection" );
					method.releaseConnection();
				}
			}
			catch (Exception e3)
			{
				log.error("Error closing connection", e3);
			}
		}

		//Remedy 6008, Bruno Meseguer, change for Batch cancel Payment
		//was
        //return null;
    }

    @Override
	public com.vizzavi.ecommerce.business.catalog.CatalogService rateService (String clientApplicationId,String serviceId,com.vizzavi.ecommerce.business.charging.UsageAttributes usageAttributes) throws com.vizzavi.ecommerce.business.charging.UsageAuthorizationException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "rateService5";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientApplicationId",clientApplicationId);
            requestPayload.put("serviceId",serviceId);
            requestPayload.put("usageAttributes",usageAttributes);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(Integer.parseInt(ConfigProvider.getProperty("er.http.connection.timeout","30000")));
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
					exceptionVector.add("com.vizzavi.ecommerce.business.charging.UsageAuthorizationException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.charging.UsageAuthorizationException)
                          throw (com.vizzavi.ecommerce.business.charging.UsageAuthorizationException) generatedException ;
                     }
                    else
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
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
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("com.vizzavi.ecommerce.business.charging.UsageAuthorizationException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = e2;
                                          }
                    else
					  log.error(" Exception during serialization ", e2);
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

//    @Override
//	public com.vizzavi.ecommerce.business.charging.UsageAuthorization usageAuthRateCharge (String clientApplicationId,String msisdn,String serviceId,com.vizzavi.ecommerce.business.charging.UsageAttributes usageAttributes,com.vizzavi.ecommerce.business.charging.ServiceUsageInstance serviceUsageInstance) throws com.vizzavi.ecommerce.business.charging.UsageAuthorizationException {
//        ObjectOutputStream oos = null;
//        ObjectInputStream ois = null;
//	boolean state = true;
//        PostMethod method = null ;
//
//        try {
//            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
//            requestPayload.put("locale", locale);
//            String methodName = "usageAuthRateCharge6";
//            requestPayload.put("methodName",methodName);
//            requestPayload.put("clientApplicationId",clientApplicationId);
//            requestPayload.put("msisdn",msisdn);
//            requestPayload.put("serviceId",serviceId);
//            requestPayload.put("usageAttributes",usageAttributes);
//            requestPayload.put("serviceUsageInstance",serviceUsageInstance);
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
//                log.debug("Result object type: " + result.getClass().getName());
//                if (result instanceof ExceptionAdapter) {
//					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//					Vector<String> exceptionVector = new Vector<String>();
//					exceptionVector.add("com.vizzavi.ecommerce.business.charging.UsageAuthorizationException");
//					if (exceptionVector.contains(exceptionName)){
//					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
//                     if (generatedException instanceof com.vizzavi.ecommerce.business.charging.UsageAuthorizationException)
//                          throw (com.vizzavi.ecommerce.business.charging.UsageAuthorizationException) generatedException ;
//                     }
//                    else
//					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
//                }
//                else
//                {
//                    return (com.vizzavi.ecommerce.business.charging.UsageAuthorization)result;
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
//					String exceptionName = ((EcommerceException) e2).getClass().getName();
//					Vector<String> exceptionVector = new Vector<String>();
//					exceptionVector.add("com.vizzavi.ecommerce.business.charging.UsageAuthorizationException");
//					if (exceptionVector.contains(exceptionName)){
//					 Exception generatedException = e2;
//                                          }
//                    else
//					  log.error(" Exception during serialization ", e2);
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
//    @Override
//	public com.vizzavi.ecommerce.business.charging.UsageAuthorization usageAuthRate (String clientApplicationId,String msisdn,String serviceId,com.vizzavi.ecommerce.business.charging.UsageAttributes usageAttributes,com.vizzavi.ecommerce.business.charging.ServiceUsageInstance serviceUsageInstance) throws com.vizzavi.ecommerce.business.common.EcommerceException {
//        ObjectOutputStream oos = null;
//        ObjectInputStream ois = null;
//	boolean state = true;
//        PostMethod method = null ;
//
//        try {
//            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
//            requestPayload.put("locale", locale);
//            String methodName = "usageAuthRate7";
//            requestPayload.put("methodName",methodName);
//            requestPayload.put("clientApplicationId",clientApplicationId);
//            requestPayload.put("msisdn",msisdn);
//            requestPayload.put("serviceId",serviceId);
//            requestPayload.put("usageAttributes",usageAttributes);
//            requestPayload.put("serviceUsageInstance",serviceUsageInstance);
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
//                log.debug("Result object type: " + result.getClass().getName());
//                if (result instanceof ExceptionAdapter) {
//					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//					Vector<String> exceptionVector = new Vector<String>();
//					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
//					if (exceptionVector.contains(exceptionName)){
//					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
//                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
//                          throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException ;
//                     }
//                    else
//					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
//                }
//                else
//                {
//                    return (com.vizzavi.ecommerce.business.charging.UsageAuthorization)result;
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
//					String exceptionName = ((EcommerceException) e2).getClass().getName();
//					Vector<String> exceptionVector = new Vector<String>();
//					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
//					if (exceptionVector.contains(exceptionName)){
//					 Exception generatedException = e2;
//                     if (generatedException instanceof  EcommerceException){
//                          throw (EcommerceException) generatedException ;
//                      }
//                     }
//                    else
//					  log.error(" Exception during serialization ", e2);
//                }
//	     else{
//	        throw new com.vizzavi.ecommerce.business.common.EcommerceException(e2);
//
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

    @Override
	public com.vizzavi.ecommerce.business.charging.UsageAuthorization usageComplete (String clientId,String reservationId,com.vodafone.global.er.business.charging.UsageCompleteAttributes att) throws com.vizzavi.ecommerce.business.charging.UsageAuthorizationException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "usageComplete8";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("reservationId",reservationId);
            requestPayload.put("att",att);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(Integer.parseInt(ConfigProvider.getProperty("er.http.connection.timeout","30000")));
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
					exceptionVector.add("com.vizzavi.ecommerce.business.charging.UsageAuthorizationException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.charging.UsageAuthorizationException)
                          throw (com.vizzavi.ecommerce.business.charging.UsageAuthorizationException) generatedException ;
                     }
                    else
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else
                {
                    return (com.vizzavi.ecommerce.business.charging.UsageAuthorization)result;
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
					exceptionVector.add("com.vizzavi.ecommerce.business.charging.UsageAuthorizationException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = e2;
                                          }
                    else
					  log.error(" Exception during serialization ", e2);
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
	public com.vizzavi.ecommerce.business.charging.PromoCodeValidation validatePromoCode (com.vizzavi.ecommerce.business.charging.PromoCodeAttributes promoCodeAttrs) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "validatePromoCode9";
            requestPayload.put("methodName",methodName);
            requestPayload.put("promoCodeAttrs",promoCodeAttrs);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
		try{
			if(httpConnectionManager == null ){
				createConnectionManager();
			}
			log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
			HttpClient httpclient = new HttpClient(httpConnectionManager);

	        httpclient.setConnectionTimeout(Integer.parseInt(ConfigProvider.getProperty("er.http.connection.timeout","30000")));
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
                    return (com.vizzavi.ecommerce.business.charging.PromoCodeValidation)result;
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
//		String serverHost = ConfigProvider.getProperty("er.server.host", "localhost");
////        String serverPort = ConfigProvider.getProperty("er.server.port", "8094");
//        int serverPort = ConfigProvider.getPropertyAsInteger("er.server.port", 8888);
//        String url = "http://" + serverHost + ":" + serverPort + "/delegates/ChargingApi";
//        log.info("ER delegate connection URL: " + url);
//		return url;

		final String filename = "env.properties";
        final String apiName = "ChargingApi";
		Properties props = new Properties();
		String url = "";
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);

			System.out.println("Input stream " + in);
			props.load(in);

		} catch (IOException ioEx) {
			log.warn("Unable to load properties from file system - could not find filename: " + filename
					+ " Will use system defaults."
			);
		}

		final String serverHost = props.getProperty("ecom.proxy.host", "127.0.0.1");
		int serverPort = Integer.valueOf(props.getProperty("ecom.proxy.port", "8888"));
		url = "http://" + serverHost + ":" + serverPort + "/delegates/" + apiName;

		log.info("ER delegate connection URL: " + url);

		return url;

	}


    public ObjectOutputStream getObjectOutputStream(URLConnection conn) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(conn.getOutputStream()));
        return oos;
    }

//Remedy 6008, Bruno Meseguer, change for Batch cancel Payment
//this new method should be used as the standard to obtain the response.
//it implements a correct error handling to meet the API interface definition
//----------------------------------------------------------------------------------
	private Object deserializeResponse(ObjectInputStream ois) throws Exception
	{
		try
		{
			long beforeReadObject = System.currentTimeMillis() ;
			Object result = ois.readObject();
			log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");

			//this should not happen, but needs to be controlled.
			if (result == null)
				throw new UsageAuthorizationException("Encountered NULL from the Input Stream.");

			log.debug("Result object type: " + result.getClass().getName());

			//we first check if ER returns an Exception
			if(result instanceof Exception)
				throw (Exception)result;

			//at this point, the object was succesfully desirialized
			return result;

		}
		catch(OptionalDataException e1)
		{
			throw new UsageAuthorizationException("Primitive data in stream", e1);
		}
		catch(ClassNotFoundException e4)
		{
			throw new UsageAuthorizationException("Class not found during deserialization", e4);
		}
		catch(ExceptionAdapter toHandle)
		{
			Exception cause = toHandle.originalException;

			//three different cases to diffirientiate.
			if(cause == null)
				throw new UsageAuthorizationException("Exception during deserialization.", toHandle);

			else if(cause instanceof UsageAuthorizationException)
				throw (UsageAuthorizationException)cause;

			else
				throw new UsageAuthorizationException("Exception during deserialization.", cause);
		}
	}
}
