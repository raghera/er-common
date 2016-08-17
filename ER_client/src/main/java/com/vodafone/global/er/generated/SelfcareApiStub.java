package com.vodafone.global.er.generated;

import com.vizzavi.ecommerce.business.charging.BaseAuthorization;
import com.vizzavi.ecommerce.business.common.AccountNotFoundException;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.selfcare.*;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.subsmngmnt.SubsManagementException;
import com.vodafone.global.er.util.ExceptionAdapter;
import com.vodafone.global.er.util.HttpClientConnector;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class SelfcareApiStub  extends HttpClientConnector implements SelfcareApi {
	
	private static final long	serialVersionUID	= -8402365935217485792L;
	private final Locale locale;
    //CR1231
    //private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(SelfcareApiStub.class);
    private static Logger log = Logger.getLogger(SelfcareApiStub.class);
    
    public SelfcareApiStub(Locale locale) {
        this.locale = locale;
    }

    @Override
	public Subscription[] getSubscriptions (String clientId,String msisdn,int device) throws AccountNotFoundException, EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getSubscriptions1";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("device", new Integer(device) );  
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
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof AccountNotFoundException)
                          throw (AccountNotFoundException) generatedException ;
if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (Subscription[])result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
	public Subscription[] getSubscriptions (String clientId,String msisdn,int device,SubscriptionFilter filter) throws AccountNotFoundException, EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getSubscriptions2";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("device", new Integer(device) );  
            requestPayload.put("filter",filter);
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
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof AccountNotFoundException)
                          throw (AccountNotFoundException) generatedException ;
if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (Subscription[])result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
	public boolean modifySubscriptionChargingMethod (String clientId,String msisdn,int deviceType,String packageSubId,int chargingMethod) throws AccountNotFoundException, EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "modifySubscriptionChargingMethod3";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("deviceType", new Integer(deviceType) );  
            requestPayload.put("packageSubId",packageSubId);
            requestPayload.put("chargingMethod", new Integer(chargingMethod) );  
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
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof AccountNotFoundException)
                          throw (AccountNotFoundException) generatedException ;
if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
	public boolean modifySubscriptionChargingMethod (String clientId,String msisdn,int deviceType,String packageSubId,int chargingMethod,String csrId,String reason) throws AccountNotFoundException, EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "modifySubscriptionChargingMethod4";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("deviceType", new Integer(deviceType) );  
            requestPayload.put("packageSubId",packageSubId);
            requestPayload.put("chargingMethod", new Integer(chargingMethod) );  
            requestPayload.put("csrId",csrId);
            requestPayload.put("reason",reason);
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
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof AccountNotFoundException)
                          throw (AccountNotFoundException) generatedException ;
if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
	public Transaction[] getTransactions (String clientId,String msisdn,int deviceType,java.util.Date startDate,java.util.Date endDate,int maxNum) throws EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getTransactions5";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("deviceType", new Integer(deviceType) );  
            requestPayload.put("startDate",startDate);
            requestPayload.put("endDate",endDate);
            requestPayload.put("maxNum", new Integer(maxNum) );  
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
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (Transaction[])result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
	public Transaction[] getTransactions (String clientId,String msisdn,int accessDevice,TransactionFilter filter) throws EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getTransactions6";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("accessDevice", new Integer(accessDevice) );  
            requestPayload.put("filter",filter);
	    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
	//	try{
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
//			}
//			catch(IOException ie ){
//			     ie.printStackTrace();
//			}
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
          //  try {
//            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              //	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                   if (result == null)
                    {
                      log.debug("Encountered NULL from the Input Stream. Returning...");
                      throw new EcommerceException("Encountered NULL from the Input Stream.");
                    }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
                	Exception e = (((ExceptionAdapter) result).originalException);
//					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//					Vector<String> exceptionVector = new Vector<String>();
//					exceptionVector.add("EcommerceException");
//					if (exceptionVector.contains(exceptionName)){
//					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
//                     if (generatedException instanceof EcommerceException)
//                          throw (EcommerceException) generatedException ;
//                     } 
//                    else 
//					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                	throw new EcommerceException(e);
                }
                else                 
                {
                    return (Transaction[])result;
                }
//            }
//            catch (OptionalDataException e1) {
//                log.error("Primitive data in stream");              
//            }
//            catch (ClassNotFoundException e4) {
//                log.error("Exception during deserialization", e4);
//            }
//        }	catch(EcommerceException e){
//        	throw e;
        }  catch (IOException e2) {
            log.error("Caught IOException during serialization. ", e2);
            throw new EcommerceException(e2);
//            if (e2 instanceof EcommerceException) {
//					String exceptionName = ((EcommerceException) e2).getClass().getName();
//					Vector<String> exceptionVector = new Vector<String>();
//					exceptionVector.add("EcommerceException");
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
//	        throw new EcommerceException(e2);
//	     }
        } catch (ClassNotFoundException e) {
	        throw new EcommerceException(e);
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
        //return null;
    }

    @Override
	public Transaction getTransaction (String clientId,String transId) throws EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getTransaction7";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("transId",transId);
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
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (Transaction)result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
	public Transaction[] getPaymentTransactions (String clientId,String msisdn,int deviceType,TransactionFilter filter) throws EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getPaymentTransactions8";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("deviceType", new Integer(deviceType) );  
            requestPayload.put("filter",filter);
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
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (Transaction[])result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
	public ResourceBalance[] getBalances (String clientId,String msisdn,int device) throws AccountNotFoundException, EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getBalances9";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("device", new Integer(device) );  
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
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof AccountNotFoundException)
                          throw (AccountNotFoundException) generatedException ;
if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (ResourceBalance[])result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
	public Subscription getSubscription (String clientId,String msisdn,int deviceType,String packageSubId) throws EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getSubscription10";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("deviceType", new Integer(deviceType) );  
            requestPayload.put("packageSubId",packageSubId);
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
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (Subscription)result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
//	public BaseAuthorization getNextPaymentAmount (String clientId,String msisdn,String subscriptionId) throws EcommerceException, java.rmi.RemoteException {
	public BaseAuthorization getNextPaymentAmount (String clientId,String msisdn,String subscriptionId) throws EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getNextPaymentAmount11";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("subscriptionId",subscriptionId);
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
					exceptionVector.add("EcommerceException");
					exceptionVector.add("java.rmi.RemoteException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
if (generatedException instanceof java.rmi.RemoteException)
                          throw (java.rmi.RemoteException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (BaseAuthorization)result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("EcommerceException");
					exceptionVector.add("java.rmi.RemoteException");
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
	        throw new EcommerceException(e2);

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
	public boolean modifySubscriptionChargingMethod (String clientId,String msisdn,int deviceType,CustcareAttributes attr) throws AccountNotFoundException, EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "modifySubscriptionChargingMethod12";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("deviceType", new Integer(deviceType) );  
            requestPayload.put("attr",attr);
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
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof AccountNotFoundException)
                          throw (AccountNotFoundException) generatedException ;
if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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

//    @Override
//	public ResourceBalance[] getSuperCreditBalances (String clientId,String msisdn,int device) throws AccountNotFoundException, EcommerceException {
//        ObjectOutputStream oos = null;
//        ObjectInputStream ois = null;
//	boolean state = true;
//        PostMethod method = null ;
//
//        try {
//            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
//            requestPayload.put("locale", locale);
//            String methodName = "getSuperCreditBalances13";
//            requestPayload.put("methodName",methodName);
//            requestPayload.put("clientId",clientId);
//            requestPayload.put("msisdn",msisdn);
//            requestPayload.put("device", new Integer(device) );
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
//	        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
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
//					exceptionVector.add("AccountNotFoundException");
//					exceptionVector.add("EcommerceException");
//					if (exceptionVector.contains(exceptionName)){
//					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
//                     if (generatedException instanceof AccountNotFoundException)
//                          throw (AccountNotFoundException) generatedException ;
//if (generatedException instanceof EcommerceException)
//                          throw (EcommerceException) generatedException ;
//                     }
//                    else
//					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
//                }
//                else
//                {
//                    return (ResourceBalance[])result;
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
//            if (e2 instanceof EcommerceException) {
//					String exceptionName = ((EcommerceException) e2).getClass().getName();
//					Vector<String> exceptionVector = new Vector<String>();
//					exceptionVector.add("AccountNotFoundException");
//					exceptionVector.add("EcommerceException");
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
//	        throw new EcommerceException(e2);
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
	public ResourceBalance[] getBalances (String msisdn,String clientId,int deviceId,com.vodafone.global.er.business.selfcare.BalanceFilter filter) throws AccountNotFoundException, EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getBalances14";
            requestPayload.put("methodName",methodName);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("clientId",clientId);
            requestPayload.put("deviceId", new Integer(deviceId) );  
            requestPayload.put("filter",filter);
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
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof AccountNotFoundException)
                          throw (AccountNotFoundException) generatedException ;
if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (ResourceBalance[])result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("AccountNotFoundException");
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
	public com.vodafone.global.er.business.selfcare.ParentTransaction getParentTransaction (String clientId,String msisdn,TransactionFilter transactionfilter) throws EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getParentTransaction15";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("transactionfilter",transactionfilter);
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
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (com.vodafone.global.er.business.selfcare.ParentTransaction)result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
	public com.vodafone.global.er.business.selfcare.MicroServiceStatus[] getMicroServiceStatus (String clientId,String msisdn,MicroServiceFilter msfilter) throws EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getMicroServiceStatus16";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("msfilter",msfilter);
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
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (com.vodafone.global.er.business.selfcare.MicroServiceStatus[])result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
	public Transaction getTransaction (String clientId,TransactionFilter filter) throws EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "getTransaction17";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("filter",filter);
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
					exceptionVector.add("EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof EcommerceException)
                          throw (EcommerceException) generatedException ;
                     } 
                    else 
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else                 
                {
                    return (Transaction)result;
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
            if (e2 instanceof EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector<String> exceptionVector = new Vector<String>();
					exceptionVector.add("EcommerceException");
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
	        throw new EcommerceException(e2);

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
//        String url = "http://" + serverHost + ":" + serverPort + "/delegates/SelfcareApi";
//        log.info("ER delegate connection URL: " + url);
//		return url;

		final String filename = "env.properties";
		final String apiName = "SelfcareApi";
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

    /**
     * NOT IMPLEMENTED in ECOM
     */
	@Override
	public Transaction getTransaction(String clientId, String msisdn, long transactionId)
			throws EcommerceException {
		throw new RuntimeException("getTransaction(String, String, long) not supported in ecom, only decoupling ");
	}

    /**
     * JIRA ET196 Get account subscription promo-codes info
     * @param msisdn
     * @param clientId
     * @return
     * @throws EcommerceException
     */
	@Override
	public List<SubscriptionPromoCode> getSubscriptionPromoCodes(String msisdn,
																 String clientId) throws EcommerceException {
		throw new RuntimeException("getSubscriptionPromoCodes(String msisdn, String clientId) not supported in ecom, only decoupling ");
	}

	  /**
     * Jira ET245 implement get subscriptions in decoupling version 2
     *
     * @param msisdn
     * @param filter
     * @param locale
     * @return
     * @throws SubsManagementException
     */
	@Override
	public List<Subscription> getSubscriptions(String msisdn,
			SubscriptionFilter filter, Locale locale)
			throws SubsManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceOffer> getServiceOffers(String msisdn, String serviceIds)
			throws EcommerceException {
		throw new RuntimeException("getServiceOffers(String msisdn, String serviceIds) not supported in ecom, only decoupling ");
	}

}
