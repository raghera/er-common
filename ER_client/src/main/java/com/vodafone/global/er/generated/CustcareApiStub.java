package com.vodafone.global.er.generated;

import com.vizzavi.ecommerce.business.charging.*;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.selfcare.*;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.util.ExceptionAdapter;
import com.vodafone.global.er.util.HttpClientConnector;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class CustcareApiStub  extends HttpClientConnector implements CustcareApi {

	private static final long	serialVersionUID	= -5497575109084795388L;
	private final Locale locale;
    //CR1231
    //private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(CustcareApiStub.class);
    private static Logger log = LoggerFactory.getLogger(CustcareApiStub.class);
    
    public CustcareApiStub(Locale locale) {
        this.locale = locale;
    }

    @Override
	public void setCustCareDetails (String csrId,String reason)  {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "setCustCareDetails1";
            requestPayload.put("methodName",methodName);
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
					log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                }
                else                 
                {
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
    }

    @Override
	public boolean inactivateAccount (String clientId,String msisdn,String csrId,String reason) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "inactivateAccount2";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
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
					Vector exceptionVector = new Vector();
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
					Vector exceptionVector = new Vector();
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

//    @Override
//	public boolean inactivateAccount (String clientId,String msisdn) throws com.vizzavi.ecommerce.business.common.EcommerceException {
//        ObjectOutputStream oos = null;
//        ObjectInputStream ois = null;
//	boolean state = true;
//        PostMethod method = null ;
//
//        try {
//            HashMap requestPayload = new HashMap();
//            requestPayload.put("locale", locale);
//            String methodName = "inactivateAccount3";
//            requestPayload.put("methodName",methodName);
//            requestPayload.put("clientId",clientId);
//            requestPayload.put("msisdn",msisdn);
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
//                log.debug("Result object type: " + result.getClass().getName());
//                if (result instanceof ExceptionAdapter) {
//					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//                }
//            }
//            catch (OptionalDataException e1) {
//                log.error("Primitive data in stream");              
//                return ois.readBoolean();
//            }
//            catch (ClassNotFoundException e4) {
//                log.error("Exception during deserialization", e4);
//            }
//        }
//        catch (IOException e2) {
//            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
//            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
//					String exceptionName = ((EcommerceException) e2).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//        return false;
//    }

    @Override
	public boolean inactivateAccount (String clientId,String msisdn,boolean validateAccount,String csrId,String reason) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "inactivateAccount4";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("validateAccount", new Boolean(validateAccount) );  
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
					Vector exceptionVector = new Vector();
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
					Vector exceptionVector = new Vector();
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

//    @Override
//	public boolean inactivateAccount (String clientId,String msisdn,boolean validateAccount) throws com.vizzavi.ecommerce.business.common.EcommerceException {
//        ObjectOutputStream oos = null;
//        ObjectInputStream ois = null;
//	boolean state = true;
//        PostMethod method = null ;
//
//        try {
//            HashMap requestPayload = new HashMap();
//            requestPayload.put("locale", locale);
//            String methodName = "inactivateAccount5";
//            requestPayload.put("methodName",methodName);
//            requestPayload.put("clientId",clientId);
//            requestPayload.put("msisdn",msisdn);
//            requestPayload.put("validateAccount", new Boolean(validateAccount) );  
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
//                log.debug("Result object type: " + result.getClass().getName());
//                if (result instanceof ExceptionAdapter) {
//					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//                }
//            }
//            catch (OptionalDataException e1) {
//                log.error("Primitive data in stream");              
//                return ois.readBoolean();
//            }
//            catch (ClassNotFoundException e4) {
//                log.error("Exception during deserialization", e4);
//            }
//        }
//        catch (IOException e2) {
//            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
//            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
//					String exceptionName = ((EcommerceException) e2).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//        return false;
//    }

    @Override
	public boolean inactivateSubscription (String clientId,String msisdn,String subscriptionId,String csrId,String reason) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "inactivateSubscription6";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("subscriptionId",subscriptionId);
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
					Vector exceptionVector = new Vector();
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
					Vector exceptionVector = new Vector();
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

//    @Override
//	public boolean inactivateSubscription (String clientId,String msisdn,String subscriptionId) throws com.vizzavi.ecommerce.business.common.EcommerceException {
//        ObjectOutputStream oos = null;
//        ObjectInputStream ois = null;
//	boolean state = true;
//        PostMethod method = null ;
//
//        try {
//            HashMap requestPayload = new HashMap();
//            requestPayload.put("locale", locale);
//            String methodName = "inactivateSubscription7";
//            requestPayload.put("methodName",methodName);
//            requestPayload.put("clientId",clientId);
//            requestPayload.put("msisdn",msisdn);
//            requestPayload.put("subscriptionId",subscriptionId);
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
//                log.debug("Result object type: " + result.getClass().getName());
//                if (result instanceof ExceptionAdapter) {
//					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//                }
//            }
//            catch (OptionalDataException e1) {
//                log.error("Primitive data in stream");              
//                return ois.readBoolean();
//            }
//            catch (ClassNotFoundException e4) {
//                log.error("Exception during deserialization", e4);
//            }
//        }
//        catch (IOException e2) {
//            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
//            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
//					String exceptionName = ((EcommerceException) e2).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//        return false;
//    }

    @Override
	public RefundAuthorization refundTransactionMonetary (String clientId,String msisdn,String transactionId,double amount,com.vizzavi.ecommerce.business.common.ChargingResource res,RefundAttributes attributes) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "refundTransactionMonetary8";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("transactionId",transactionId);
            requestPayload.put("amount", new Double(amount) );  
            requestPayload.put("res",res);
            requestPayload.put("attributes",attributes);
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
					Vector exceptionVector = new Vector();
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
                    return (RefundAuthorization)result;
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
					Vector exceptionVector = new Vector();
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
	public RefundAuthorization refundTransactionEnlargement (String clientId,String msisdn,String transactionId,long numberDaysToExtend,RefundAttributes attributes) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "refundTransactionEnlargement9";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("transactionId",transactionId);
            requestPayload.put("numberDaysToExtend", new Long(numberDaysToExtend) );  
            requestPayload.put("attributes",attributes);
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
					Vector exceptionVector = new Vector();
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
                    return (RefundAuthorization)result;
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
					Vector exceptionVector = new Vector();
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
	public RefundAuthorization refundTransactionDiscount (String clientId,String msisdn,String transactionId,double discount,RefundAttributes attributes) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "refundTransactionDiscount10";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("transactionId",transactionId);
            requestPayload.put("discount", new Double(discount) );  
            requestPayload.put("attributes",attributes);
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
					Vector exceptionVector = new Vector();
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
                    return (RefundAuthorization)result;
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
					Vector exceptionVector = new Vector();
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
	public boolean modifyMsisdn (String clientId,String msisdn,String newMsisdn,String csrId,String reason) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "modifyMsisdn11";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("newMsisdn",newMsisdn);
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
					Vector exceptionVector = new Vector();
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
					Vector exceptionVector = new Vector();
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

//    @Override
//	public boolean modifyMsisdn (String clientId,String msisdn,String newMsisdn) throws com.vizzavi.ecommerce.business.common.EcommerceException {
//        ObjectOutputStream oos = null;
//        ObjectInputStream ois = null;
//	boolean state = true;
//        PostMethod method = null ;
//
//        try {
//            HashMap requestPayload = new HashMap();
//            requestPayload.put("locale", locale);
//            String methodName = "modifyMsisdn12";
//            requestPayload.put("methodName",methodName);
//            requestPayload.put("clientId",clientId);
//            requestPayload.put("msisdn",msisdn);
//            requestPayload.put("newMsisdn",newMsisdn);
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
//                log.debug("Result object type: " + result.getClass().getName());
//                if (result instanceof ExceptionAdapter) {
//					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//                }
//            }
//            catch (OptionalDataException e1) {
//                log.error("Primitive data in stream");              
//                return ois.readBoolean();
//            }
//            catch (ClassNotFoundException e4) {
//                log.error("Exception during deserialization", e4);
//            }
//        }
//        catch (IOException e2) {
//            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
//            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
//					String exceptionName = ((EcommerceException) e2).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//        return false;
//    }

    @Override
	public boolean modifyBan (String clientId,String msisdn,String newBan,String csrId,String reason) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	    boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "modifyBan13";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("newBan",newBan);
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
					Vector exceptionVector = new Vector();
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
					Vector exceptionVector = new Vector();
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
	public BasicAccount getBasicAccount (String clientId,String msisdn,int accessDevice) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getBasicAccount15";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("accessDevice", new Integer(accessDevice) );  
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
					Vector exceptionVector = new Vector();
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
                    return (BasicAccount)result;
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
					Vector exceptionVector = new Vector();
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

//    @Override
//	public boolean modifyBan (String clientId,String msisdn,String newBan) throws com.vizzavi.ecommerce.business.common.EcommerceException {
//        ObjectOutputStream oos = null;
//        ObjectInputStream ois = null;
//	    boolean state = true;
//        PostMethod method = null ;
//
//        try {
//            HashMap requestPayload = new HashMap();
//            requestPayload.put("locale", locale);
//            String methodName = "modifyBan14";
//            requestPayload.put("methodName",methodName);
//            requestPayload.put("clientId",clientId);
//            requestPayload.put("msisdn",msisdn);
//            requestPayload.put("newBan",newBan);
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
//                log.debug("Result object type: " + result.getClass().getName());
//                if (result instanceof ExceptionAdapter) {
//					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//                }
//            }
//            catch (OptionalDataException e1) {
//                log.error("Primitive data in stream");              
//                return ois.readBoolean();
//            }
//            catch (ClassNotFoundException e4) {
//                log.error("Exception during deserialization", e4);
//            }
//        }
//        catch (IOException e2) {
//            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
//            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
//					String exceptionName = ((EcommerceException) e2).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//        return false;
//    }
    
    @Override
	public boolean inactivateSubscription (String clientId,String msisdn,int device,CustcareAttributes custcareAttributes) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "inactivateSubscription16";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("device", new Integer(device) );  
            requestPayload.put("custcareAttributes",custcareAttributes);
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
					Vector exceptionVector = new Vector();
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
					Vector exceptionVector = new Vector();
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
	public boolean notificationSubscribe (java.util.Locale locale1,String url,String name) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "notificationSubscribe17";
            requestPayload.put("methodName",methodName);
            requestPayload.put("locale1",locale1);
            requestPayload.put("url",url);
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
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
					Vector exceptionVector = new Vector();
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
					Vector exceptionVector = new Vector();
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
	public RefundAuthorization refundTransactionCredit (RefundAttributes refundAttr) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "refundTransactionCredit18";
            requestPayload.put("methodName",methodName);
            requestPayload.put("refundAttr",refundAttr);
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
					Vector exceptionVector = new Vector();
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
                    return (RefundAuthorization)result;
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
					Vector exceptionVector = new Vector();
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
	public Subscription[] getSubscriptions (String clientId,String msisdn,int device) throws com.vizzavi.ecommerce.business.common.AccountNotFoundException, com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getSubscriptions17";
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.AccountNotFoundException)
                          throw (com.vizzavi.ecommerce.business.common.AccountNotFoundException) generatedException ;
if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
                          throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException ;
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
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
	public Subscription[] getSubscriptions (String clientId,String msisdn,int device,SubscriptionFilter filter) throws com.vizzavi.ecommerce.business.common.AccountNotFoundException, com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getSubscriptions18";
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.AccountNotFoundException)
                          throw (com.vizzavi.ecommerce.business.common.AccountNotFoundException) generatedException ;
if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
                          throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException ;
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
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
	public boolean modifySubscriptionChargingMethod (String clientId,String msisdn,int deviceType,String packageSubId,int chargingMethod) throws com.vizzavi.ecommerce.business.common.AccountNotFoundException, com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "modifySubscriptionChargingMethod19";
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.AccountNotFoundException)
                          throw (com.vizzavi.ecommerce.business.common.AccountNotFoundException) generatedException ;
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
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
	public boolean modifySubscriptionChargingMethod (String clientId,String msisdn,int deviceType,String packageSubId,int chargingMethod,String csrId,String reason) throws com.vizzavi.ecommerce.business.common.AccountNotFoundException, com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "modifySubscriptionChargingMethod20";
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.AccountNotFoundException)
                          throw (com.vizzavi.ecommerce.business.common.AccountNotFoundException) generatedException ;
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
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
	public Transaction[] getTransactions (String clientId,String msisdn,int deviceType,java.util.Date startDate,java.util.Date endDate,int maxNum) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getTransactions21";
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
					Vector exceptionVector = new Vector();
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
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
	public Transaction[] getTransactions (String clientId,String msisdn,int accessDevice,TransactionFilter filter) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getTransactions22";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("accessDevice", new Integer(accessDevice) );  
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
					Vector exceptionVector = new Vector();
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
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
	public Transaction getTransaction (String clientId,String transId) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getTransaction23";
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
					Vector exceptionVector = new Vector();
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
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
	public Transaction[] getPaymentTransactions (String clientId,String msisdn,int deviceType,TransactionFilter filter) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getPaymentTransactions24";
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
					Vector exceptionVector = new Vector();
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
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
	public ResourceBalance[] getBalances (String clientId,String msisdn,int device) throws com.vizzavi.ecommerce.business.common.AccountNotFoundException, com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getBalances25";
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.AccountNotFoundException)
                          throw (com.vizzavi.ecommerce.business.common.AccountNotFoundException) generatedException ;
if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
                          throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException ;
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
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
	public Subscription getSubscription (String clientId,String msisdn,int deviceType,String packageSubId) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getSubscription26";
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
					Vector exceptionVector = new Vector();
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
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
	public BaseAuthorization getNextPaymentAmount (String clientId,String msisdn,String subscriptionId) throws com.vizzavi.ecommerce.business.common.EcommerceException, java.rmi.RemoteException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getNextPaymentAmount27";
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					exceptionVector.add("java.rmi.RemoteException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
                          throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException ;
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
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
	public boolean modifySubscriptionChargingMethod (String clientId,String msisdn,int deviceType,CustcareAttributes attr) throws com.vizzavi.ecommerce.business.common.AccountNotFoundException, com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "modifySubscriptionChargingMethod28";
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.AccountNotFoundException)
                          throw (com.vizzavi.ecommerce.business.common.AccountNotFoundException) generatedException ;
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
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
	public ResourceBalance[] getSuperCreditBalances (String clientId,String msisdn,int device) throws com.vizzavi.ecommerce.business.common.AccountNotFoundException, com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getSuperCreditBalances29";
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.AccountNotFoundException)
                          throw (com.vizzavi.ecommerce.business.common.AccountNotFoundException) generatedException ;
if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
                          throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException ;
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
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
	public ResourceBalance[] getBalances (String msisdn,String clientId,int deviceId,com.vodafone.global.er.business.selfcare.BalanceFilter filter) throws com.vizzavi.ecommerce.business.common.AccountNotFoundException, com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getBalances30";
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.AccountNotFoundException)
                          throw (com.vizzavi.ecommerce.business.common.AccountNotFoundException) generatedException ;
if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
                          throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException ;
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.AccountNotFoundException");
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
	public com.vodafone.global.er.business.selfcare.ParentTransaction getParentTransaction (String clientId,String msisdn,TransactionFilter transactionfilter) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getParentTransaction31";
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
					Vector exceptionVector = new Vector();
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
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
	public com.vodafone.global.er.business.selfcare.MicroServiceStatus[] getMicroServiceStatus (String clientId,String msisdn,MicroServiceFilter msfilter) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getMicroServiceStatus32";
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
					Vector exceptionVector = new Vector();
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
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
	public Transaction getTransaction (String clientId,TransactionFilter filter) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "getTransaction33";
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
					Vector exceptionVector = new Vector();
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
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
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
//		String serverHost = ConfigProvider.getProperty("er.server.host", "0.0.0.0");
////        int serverPort = ConfigProvider.getPropertyAsInteger("er.server.port", 8094);
//        int serverPort = ConfigProvider.getPropertyAsInteger("er.server.port", 8888);
//        String url = "http://" + serverHost + ":" + serverPort + "/delegates/CustcareApi";
//        log.info("ER delegate connection URL: {}" , url);
//		return url;

		final String filename = "env.properties";
		final String apiName = "CustcareApi";
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
//   //CR1564-utc timezone for diff region
//    @Override
//	public ModifyAuthorisation modifyTimezone(String clientId,String msisdn,String timezone) throws com.vizzavi.ecommerce.business.common.EcommerceException, ClassNotFoundException {
//        ObjectOutputStream oos = null;
//        ObjectInputStream ois = null;
//	    boolean state = true;
//        PostMethod method = null ;
//        ModifyAuthorisation modifyAuth=new ModifyAuthorisation();
//        try {
//            HashMap requestPayload = new HashMap();
//            requestPayload.put("locale", locale);
//            String methodName = "modifyTimezone35";
//            requestPayload.put("methodName",methodName);
//            requestPayload.put("clientId",clientId);
//            requestPayload.put("msisdn",msisdn);
//            requestPayload.put("timezone",timezone);
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
//                log.debug("Result object type: " + result.getClass().getName());
//                if (result instanceof ExceptionAdapter) {
//					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//                }
//            }
//            catch (OptionalDataException e1) {
//                log.error("Primitive data in stream");              
//                return (ModifyAuthorisation) ois.readUnshared();
//            }
//            catch (ClassNotFoundException e4) {
//                log.error("Exception during deserialization", e4);
//            }
//        }
//        catch (IOException e2) {
//            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
//            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
//					String exceptionName = ((EcommerceException) e2).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//        return modifyAuth;
//    }
   
    @Override
	public ModifyAuthorisation modifyTimezone(String clientId,String msisdn,String timezone,String csrId,String reason) throws com.vizzavi.ecommerce.business.common.EcommerceException, ClassNotFoundException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ModifyAuthorisation state = new ModifyAuthorisation();
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "modifyTimezone35";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("timezone",timezone);
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
					Vector exceptionVector = new Vector();
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
                return (ModifyAuthorisation) ois.readObject();
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
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
        return state;
    }
    //BillingCycle update 
    @Override
	public ModifyAuthorisation modifyBillingCycle(String clientId,String msisdn,int billingcycle,String csrId,String reason) throws com.vizzavi.ecommerce.business.common.EcommerceException, ClassNotFoundException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ModifyAuthorisation state = new ModifyAuthorisation();
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "modifyBillingCycle36";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("billingcycle",billingcycle);
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
					Vector exceptionVector = new Vector();
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
                return (ModifyAuthorisation) ois.readObject();
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
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
        return state;
    }
    //CR1564-end

//	@Override
//	public ModifyAuthorisation modifyBillingCycle(String clientId,
//			String msisdn, int billingCycle) throws EcommerceException,
//			ClassNotFoundException {
//		ObjectOutputStream oos = null;
//        ObjectInputStream ois = null;
//        ModifyAuthorisation state = new ModifyAuthorisation();
//        PostMethod method = null ;
//
//        try {
//            HashMap requestPayload = new HashMap();
//            requestPayload.put("locale", locale);
//            String methodName = "modifyBillingCycle36";
//            requestPayload.put("methodName",methodName);
//            requestPayload.put("clientId",clientId);
//            requestPayload.put("msisdn",msisdn);
//            requestPayload.put("billingCycle",billingCycle);
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
//                log.debug("Result object type: " + result.getClass().getName());
//                if (result instanceof ExceptionAdapter) {
//					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//                }
//            }
//            catch (OptionalDataException e1) {
//                log.error("Primitive data in stream");              
//                return (ModifyAuthorisation) ois.readObject();
//            }
//            catch (ClassNotFoundException e4) {
//                log.error("Exception during deserialization", e4);
//            }
//        }
//        catch (IOException e2) {
//            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
//            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
//					String exceptionName = ((EcommerceException) e2).getClass().getName();
//					Vector exceptionVector = new Vector();
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
//        return state;
//}

	public ModifyAuthorisation modifyAccountStatus(String clientId,
			String msisdn, int status, String csrId, String reason)
			throws EcommerceException, ClassNotFoundException {
   	    	ObjectOutputStream oos = null;
	        ObjectInputStream ois = null;
	        ModifyAuthorisation state = new ModifyAuthorisation();
	        PostMethod method = null ;

	        try {
	            HashMap requestPayload = new HashMap();
	            requestPayload.put("locale", locale);
	            String methodName = "modifyAccountStatus37";
	            requestPayload.put("methodName",methodName);
	            requestPayload.put("clientId",clientId);
	            requestPayload.put("msisdn",msisdn);
	            requestPayload.put("status",status);
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
						Vector exceptionVector = new Vector();
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
	                return (ModifyAuthorisation) ois.readObject();
	            }
	            catch (ClassNotFoundException e4) {
	                log.error("Exception during deserialization", e4);
	            }
	        }
	        catch (IOException e2) {
	            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
	            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
						String exceptionName = ((EcommerceException) e2).getClass().getName();
						Vector exceptionVector = new Vector();
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
	        return state;	
	  }


	@Override
	//CR 2229 - add action
	public ModifyAuthorisation modifyUserGroup(String clientId,
			String msisdn, List usergroup, String csrId, String reason, String action)
			throws EcommerceException {
		ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ModifyAuthorisation state = new ModifyAuthorisation();
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "modifyUserGroups38";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("usergroup",usergroup);
            requestPayload.put("csrId",csrId);
            requestPayload.put("reason",reason);
            requestPayload.put("action",action);
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
					Vector exceptionVector = new Vector();
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
                try {
					return (ModifyAuthorisation) ois.readObject();
				} catch (ClassNotFoundException e) {
					throw new EcommerceException(e1.getMessage(), e);
				}
            }
            catch (ClassNotFoundException e4) {
                log.error("Exception during deserialization", e4);
            }
        }
        catch (IOException e2) {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
					String exceptionName = ((EcommerceException) e2).getClass().getName();
					Vector exceptionVector = new Vector();
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
        return state;
	}

//	@Override
//	public ModifyAuthorisation modifyDIUserGroup(String clientId,
//			String msisdn, List usergroup) throws EcommerceException {
//	      	ObjectOutputStream oos = null;
//	        ObjectInputStream ois = null;
//	        ModifyAuthorisation state = new ModifyAuthorisation();
//	        PostMethod method = null ;
//
//	        try {
//	            HashMap requestPayload = new HashMap();
//	            requestPayload.put("locale", locale);
//	            String methodName = "modifyUserGroups38";
//	            requestPayload.put("methodName",methodName);
//	            requestPayload.put("clientId",clientId);
//	            requestPayload.put("msisdn",msisdn);
//	            requestPayload.put("usergroup",usergroup);
//		    String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");
//
//		   if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
//			try{
//				if(httpConnectionManager == null ){
//					createConnectionManager();
//				}
//				log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
//				HttpClient httpclient = new HttpClient(httpConnectionManager);
//
//		        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
//				method = new PostMethod( getDelegateUrl() );
//				method.addRequestHeader("Content-Type", "application/octet-stream");
//			        // Serialize to a byte array
//				 byte[] buf = super.serializedPayload(requestPayload);
//				 method.setRequestBody(new ByteArrayInputStream(buf));
//				 httpclient.executeMethod(method);
//
//				ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
//				}
//				catch(IOException ie ){
//				     ie.printStackTrace();
//				}
//		        }
//			else {
//			     URLConnection conn = null;
//			     conn = getConnection();
//			    oos = getObjectOutputStream(conn);
//			    oos.writeObject(requestPayload);
//			    oos.flush();
//			    oos.close();
//			    ois = new ObjectInputStream(new BufferedInputStream(conn.getInputStream()));
//		     }
//	            try {
//	            	long beforeReadObject = System.currentTimeMillis() ;
//	                Object result = ois.readObject();
//	              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
//	                log.debug("Result object type: " + result.getClass().getName());
//	                if (result instanceof ExceptionAdapter) {
//						String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//						Vector exceptionVector = new Vector();
//						exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
//						if (exceptionVector.contains(exceptionName)){
//						 Exception generatedException = ((ExceptionAdapter)result).originalException ;
//	                     if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
//	                          throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException ;
//	                     } 
//	                    else 
//						  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
//	                }
//	                else                 
//	                {
//	                }
//	            }
//	            catch (OptionalDataException e1) {
//	                log.error("Primitive data in stream");              
//	                try {
//						return (ModifyAuthorisation) ois.readObject();
//					} catch (ClassNotFoundException e) {
//						throw new EcommerceException(e1.getMessage(), e);
//					}	            }
//	            catch (ClassNotFoundException e4) {
//	                log.error("Exception during deserialization", e4);
//	            }
//	        }
//	        catch (IOException e2) {
//	            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
//	            if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
//						String exceptionName = ((EcommerceException) e2).getClass().getName();
//						Vector exceptionVector = new Vector();
//						exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
//						if (exceptionVector.contains(exceptionName)){
//						 Exception generatedException = e2;
//	                     if (generatedException instanceof  EcommerceException){
//	                          throw (EcommerceException) generatedException ;
//	                      }
//	                     } 
//	                    else 
//						  log.error(" Exception during serialization ", e2);
//	                }
//		     else{
//		        throw new com.vizzavi.ecommerce.business.common.EcommerceException(e2);
//
//		     }
//	        } 
//	        finally {
//		   try {
//	                if (oos != null) {
//	                    oos.close();
//	                }
//		        if (ois != null) {
//		            ois.close();
//		       }
//	       		if (method != null ){
//	       		log.debug("Releasing http connection" );  
//		     	  method.releaseConnection();
//			}
//		   }
//		   catch (Exception e3) {
//		       log.error("Error closing connection", e3);
//		   }
//		}
//	        return state;
//	}

	
	
	// CR 1643 - Pre-Pay Post-Pay Split
	/**
	 * 
	 * @param clientId
	 * @param msisdn
	 * @param spId
     * @return ModifyAuthorisation
     * @throws EcommerceException
     * @throws ClassNotFoundException
	 */
	@Override
	public ModifyAuthorisation modifyAccountSpId(String clientId, String msisdn, String spId) throws com.vizzavi.ecommerce.business.common.EcommerceException, ClassNotFoundException {
		ObjectOutputStream oos	= null;
		ObjectInputStream ois	= null;
		boolean state			= true;
		PostMethod method		= null;
		ModifyAuthorisation modifyAuth = new ModifyAuthorisation();

		try {
			HashMap requestPayload = new HashMap();
			requestPayload.put("locale", locale);
			String methodName = "modifyAccountSpId35";
			requestPayload.put("methodName", methodName);
			requestPayload.put("clientId", clientId);
			requestPayload.put("msisdn", msisdn);
			requestPayload.put("spId", spId);
			String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

			if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
				try{
					if(httpConnectionManager == null ){
						createConnectionManager();
					}
					log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
					HttpClient httpclient = new HttpClient(httpConnectionManager);

			        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout", 30000));
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");

					if (exceptionVector.contains(exceptionName)){
						Exception generatedException = ((ExceptionAdapter)result).originalException;
						if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
							throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException;
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
				return (ModifyAuthorisation) ois.readUnshared();
			}
			catch (ClassNotFoundException e4) {
				log.error("Exception during deserialization", e4);
			}
		}
		catch (IOException e2) {
			log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);

			if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
				String exceptionName = ((EcommerceException) e2).getClass().getName();
				Vector exceptionVector = new Vector();
				exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");

				if (exceptionVector.contains(exceptionName)){
					Exception generatedException = e2;

					if (generatedException instanceof EcommerceException){
						throw (EcommerceException) generatedException ;
					}
				}
				else 
					log.error(" Exception during serialization ", e2);
				}
			else
			{
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

        return modifyAuth;

	}
	
	/**
	 * 
	 * @param clientId
	 * @param msisdn
	 * @param isPrepay
     * @return ModifyAuthorisation
     * @throws EcommerceException
     * @throws ClassNotFoundException
	 */
	@Override
	public ModifyAuthorisation modifyAccountIsPrepay(String clientId, String msisdn, String isPrepay) throws com.vizzavi.ecommerce.business.common.EcommerceException, ClassNotFoundException {
		ObjectOutputStream oos	= null;
		ObjectInputStream ois	= null;
		boolean state			= true;
		PostMethod method		= null;
		ModifyAuthorisation modifyAuth = new ModifyAuthorisation();

		try {
			HashMap requestPayload = new HashMap();
			requestPayload.put("locale", locale);
			String methodName = "modifyAccountIsPrepay35";
			requestPayload.put("methodName", methodName);
			requestPayload.put("clientId", clientId);
			requestPayload.put("msisdn", msisdn);
			requestPayload.put("isPrepay", isPrepay);
			String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

			if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
				try{
					if(httpConnectionManager == null ){
						createConnectionManager();
					}
					log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
					HttpClient httpclient = new HttpClient(httpConnectionManager);

			        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout", 30000));
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");

					if (exceptionVector.contains(exceptionName)){
						Exception generatedException = ((ExceptionAdapter)result).originalException;
						if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
							throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException;
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
				return (ModifyAuthorisation) ois.readUnshared();
			}
			catch (ClassNotFoundException e4) {
				log.error("Exception during deserialization", e4);
			}
		}
		catch (IOException e2) {
			log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);

			if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
				String exceptionName = ((EcommerceException) e2).getClass().getName();
				Vector exceptionVector = new Vector();
				exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");

				if (exceptionVector.contains(exceptionName)){
					Exception generatedException = e2;

					if (generatedException instanceof EcommerceException){
						throw (EcommerceException) generatedException ;
					}
				}
				else 
					log.error(" Exception during serialization ", e2);
				}
			else
			{
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

        return modifyAuth;

	}
	// CR 1643 - Ends

    //CR1791
    @Override
	public ModifySubscriptionAuthorization modifySubscription
            (String clientApplicationId, String msisdn, String subscriptionId, SubscriptionAttributes subscriptionAttributes) 
            throws com.vizzavi.ecommerce.business.common.EcommerceException {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "modifySubscription40";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientApplicationId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("subscriptionId",subscriptionId);
            requestPayload.put("subscriptionAttributes",subscriptionAttributes);
            String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

            if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")) {    
                try {
                    if (httpConnectionManager == null) {
                       createConnectionManager();
                    }
                    log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
                    HttpClient httpclient = new HttpClient(httpConnectionManager);

                    httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
                    method = new PostMethod(getDelegateUrl());
                    method.addRequestHeader("Content-Type", "application/octet-stream");
                    // Serialize to a byte array
                    byte[] buf = super.serializedPayload(requestPayload);
                    method.setRequestBody(new ByteArrayInputStream(buf));
                    httpclient.executeMethod(method);

                    ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
                }
                catch (IOException ie) {
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
                long beforeReadObject = System.currentTimeMillis();
                Object result = ois.readObject();
                log.debug("Reading the Object from stream took " + (System.currentTimeMillis() - beforeReadObject) +" ms.");
                if (result == null) {
                    log.debug("Encountered NULL from the Input Stream. Returning...");
                    return null;
                }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
                    String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
                    Vector exceptionVector = new Vector();
                    exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
                    if (exceptionVector.contains(exceptionName)) {
                        Exception generatedException = ((ExceptionAdapter) result).originalException;
                        if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                            throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException;
                        }
                    }
                    else { 
                        log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                    }
                }
                else {
                    return (ModifySubscriptionAuthorization) result;
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
                Vector exceptionVector = new Vector();
                exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
                if (exceptionVector.contains(exceptionName)) {
                    Exception generatedException = e2;
                    if (generatedException instanceof  EcommerceException) {
                        throw (EcommerceException) generatedException;
                    }
                }
                else { 
                    log.error(" Exception during serialization ", e2);
                }
            }
            else {
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
                if (method != null) {
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
    
    //MQC 6669 - send refund message for a rolledback transaction
    @Override
	public RefundAuthorization refundRollbackTransactionMonetary (String clientId,String msisdn,String subscriptionId,RefundAttributes attributes) throws com.vizzavi.ecommerce.business.common.EcommerceException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "refundRollbackTransactionMonetary41";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("subscriptionId",subscriptionId);
            requestPayload.put("attributes",attributes);
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
					Vector exceptionVector = new Vector();
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
                    return (RefundAuthorization)result;
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
					Vector exceptionVector = new Vector();
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
    
    //CR2082 - validate msisdn call, used to make a direct validation msisdn call to the opco ERIF
    @Override
	public AccountValidationAuthorization validateMsisdnAccount
            (String msisdn, ValidateMsisdnAttributes validateAttributes) 
            throws com.vizzavi.ecommerce.business.common.EcommerceException {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        boolean state = true;
        PostMethod method = null ;

        try {
            HashMap requestPayload = new HashMap();
            requestPayload.put("locale", locale);
            String methodName = "validateMsisdnAccount42";
            requestPayload.put("methodName",methodName);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("validateAttributes",validateAttributes);
            String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

            if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")) {    
                try {
                    if (httpConnectionManager == null) {
                       createConnectionManager();
                    }
                    log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
                    HttpClient httpclient = new HttpClient(httpConnectionManager);

                    httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout",30000));
                    method = new PostMethod(getDelegateUrl());
                    method.addRequestHeader("Content-Type", "application/octet-stream");
                    // Serialize to a byte array
                    byte[] buf = super.serializedPayload(requestPayload);
                    method.setRequestBody(new ByteArrayInputStream(buf));
                    httpclient.executeMethod(method);

                    ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
                }
                catch (IOException ie) {
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
                long beforeReadObject = System.currentTimeMillis();
                Object result = ois.readObject();
                log.debug("Reading the Object from stream took " + (System.currentTimeMillis() - beforeReadObject) +" ms.");
                if (result == null) {
                    log.debug("Encountered NULL from the Input Stream. Returning...");
                    return null;
                }
                log.debug("Result object type: " + result.getClass().getName());
                if (result instanceof ExceptionAdapter) {
                    String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
                    Vector exceptionVector = new Vector();
                    exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
                    if (exceptionVector.contains(exceptionName)) {
                        Exception generatedException = ((ExceptionAdapter) result).originalException;
                        if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
                            throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException;
                        }
                    }
                    else { 
                        log.error(" Exception during serialization ", ((ExceptionAdapter) result).originalException);
                    }
                }
                else {
                    return (AccountValidationAuthorization) result;
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
                Vector exceptionVector = new Vector();
                exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");
                if (exceptionVector.contains(exceptionName)) {
                    Exception generatedException = e2;
                    if (generatedException instanceof  EcommerceException) {
                        throw (EcommerceException) generatedException;
                    }
                }
                else { 
                    log.error(" Exception during serialization ", e2);
                }
            }
            else {
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
                if (method != null) {
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
	public ModifyAuthorisation modifySpendLimits (String clientId,String msisdn,SpendLimits spendLimits,String csrId) throws com.vizzavi.ecommerce.business.common.EcommerceException {
    	ObjectOutputStream oos = null;
    	ObjectInputStream ois = null;
    	boolean state = true;
    	PostMethod method = null ;

    	try {
    		HashMap requestPayload = new HashMap();
    		requestPayload.put("locale", locale);
    		String methodName = "modifySpendLimits43";
    		requestPayload.put("methodName",methodName);
    		requestPayload.put("clientId",clientId);
    		requestPayload.put("msisdn",msisdn);
    		requestPayload.put("spendLimits",spendLimits);
    		requestPayload.put("csrId",csrId);
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
    				Vector exceptionVector = new Vector();
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
    				return (ModifyAuthorisation)result;
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
    			Vector exceptionVector = new Vector();
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

	/**
	 * CR2198 - Updates the Child Service Provider ID in the Accounts table
	 * @param clientId
	 * @param msisdn
	 * @param childSpId
     * @return ModifyAuthorisation
     * @throws EcommerceException
     * @throws ClassNotFoundException
	 */
	@Override
	public ModifyAuthorisation modifyAccountChildSpId(String clientId, String msisdn, String childSpId) throws com.vizzavi.ecommerce.business.common.EcommerceException, ClassNotFoundException {
		ObjectOutputStream oos	= null;
		ObjectInputStream ois	= null;
		boolean state			= true;
		PostMethod method		= null;
		ModifyAuthorisation modifyAuth = new ModifyAuthorisation();

		try {
			HashMap requestPayload = new HashMap();
			requestPayload.put("locale", locale);
			String methodName = "modifyAccountChildSpId44";
			requestPayload.put("methodName", methodName);
			requestPayload.put("clientId", clientId);
			requestPayload.put("msisdn", msisdn);
			requestPayload.put("childSpId", childSpId);
			String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

			if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
				try{
					if(httpConnectionManager == null ){
						createConnectionManager();
					}
					log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
					HttpClient httpclient = new HttpClient(httpConnectionManager);

			        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout", 30000));
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");

					if (exceptionVector.contains(exceptionName)){
						Exception generatedException = ((ExceptionAdapter)result).originalException;
						if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
							throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException;
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
				return (ModifyAuthorisation) ois.readUnshared();
			}
			catch (ClassNotFoundException e4) {
				log.error("Exception during deserialization", e4);
			}
		}
		catch (IOException e2) {
			log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);

			if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
				String exceptionName = ((EcommerceException) e2).getClass().getName();
				Vector exceptionVector = new Vector();
				exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");

				if (exceptionVector.contains(exceptionName)){
					Exception generatedException = e2;

					if (generatedException instanceof EcommerceException){
						throw (EcommerceException) generatedException ;
					}
				}
				else 
					log.error(" Exception during serialization ", e2);
				}
			else
			{
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

        return modifyAuth;

	}
	
	/**
	 * CR2198 - Updates the Service Provider Type in the Accounts table
	 * @param clientId
	 * @param msisdn
	 * @param childSpId
     * @return ModifyAuthorisation
     * @throws EcommerceException
     * @throws ClassNotFoundException
	 */
	@Override
	public ModifyAuthorisation modifyAccountSpType(String clientId, String msisdn, String spType) throws com.vizzavi.ecommerce.business.common.EcommerceException, ClassNotFoundException {
		ObjectOutputStream oos	= null;
		ObjectInputStream ois	= null;
		boolean state			= true;
		PostMethod method		= null;
		ModifyAuthorisation modifyAuth = new ModifyAuthorisation();

		try {
			HashMap requestPayload = new HashMap();
			requestPayload.put("locale", locale);
			String methodName = "modifyAccountSpType45";
			requestPayload.put("methodName", methodName);
			requestPayload.put("clientId", clientId);
			requestPayload.put("msisdn", msisdn);
			requestPayload.put("spType", spType);
			String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

			if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
				try{
					if(httpConnectionManager == null ){
						createConnectionManager();
					}
					log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());
					HttpClient httpclient = new HttpClient(httpConnectionManager);

			        httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout", 30000));
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
					Vector exceptionVector = new Vector();
					exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");

					if (exceptionVector.contains(exceptionName)){
						Exception generatedException = ((ExceptionAdapter)result).originalException;
						if (generatedException instanceof com.vizzavi.ecommerce.business.common.EcommerceException)
							throw (com.vizzavi.ecommerce.business.common.EcommerceException) generatedException;
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
				return (ModifyAuthorisation) ois.readUnshared();
			}
			catch (ClassNotFoundException e4) {
				log.error("Exception during deserialization", e4);
			}
		}
		catch (IOException e2) {
			log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);

			if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
				String exceptionName = ((EcommerceException) e2).getClass().getName();
				Vector exceptionVector = new Vector();
				exceptionVector.add("com.vizzavi.ecommerce.business.common.EcommerceException");

				if (exceptionVector.contains(exceptionName)){
					Exception generatedException = e2;

					if (generatedException instanceof EcommerceException){
						throw (EcommerceException) generatedException ;
					}
				}
				else 
					log.error(" Exception during serialization ", e2);
				}
			else
			{
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

        return modifyAuth;

	}
}
