package com.vodafone.global.er.generated;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.vizzavi.ecommerce.business.charging.*;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.util.ExceptionAdapter;
import com.vodafone.global.er.util.HttpClientConnector;

public class PurchaseApiStub  extends HttpClientConnector implements PurchaseApi {

	private final Locale locale;
	//CR1231
	//private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(PurchaseApiStub.class);
	private static Logger log = Logger.getLogger(PurchaseApiStub.class);

	public PurchaseApiStub(Locale locale) {
		this.locale = locale;
	}

	@Override
	public PurchaseAuthorization purchasePackageMsisdn (String clientApplicationId,String msisdn,String packageId,PurchaseAttributes purchaseAttributes) throws PurchaseAuthorizationException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean state = true;
		PostMethod method = null ;

		try {
			HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
			requestPayload.put("locale", locale);
			String methodName = "purchasePackageMsisdn1";
			requestPayload.put("methodName",methodName);
			requestPayload.put("clientApplicationId",clientApplicationId);
			requestPayload.put("msisdn",msisdn);
			requestPayload.put("packageId",packageId);
			requestPayload.put("purchaseAttributes",purchaseAttributes);
			String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

			if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
			//	try{
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
//				}
//				catch(IOException ie ){
//					ie.printStackTrace();
//				}
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
		//	try {
				//long beforeReadObject = System.currentTimeMillis() ;
				Object result = ois.readObject();
				//log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
				if (result == null)
				{
					throw new PurchaseAuthorizationException("Encountered NULL from the Input Stream.");
				}
				log.debug("Result object type: " + result.getClass().getName());
				if (result instanceof ExceptionAdapter) {
//					String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//					Vector<String> exceptionVector = new Vector<String>();
//					exceptionVector.add("PurchaseAuthorizationException");
//					if (exceptionVector.contains(exceptionName)){
					Exception generatedException = ((ExceptionAdapter)result).originalException ;
					log.error(" Exception during serialization ", generatedException);
					if (generatedException instanceof PurchaseAuthorizationException)
						throw (PurchaseAuthorizationException) generatedException ;
					//} 
					else 
						throw new PurchaseAuthorizationException(generatedException);
				}
				else                 
				{
					return (PurchaseAuthorization)result;
				}
//			}
//			catch (OptionalDataException e1) {
//				log.error("Primitive data in stream");              
//			}
//			catch (ClassNotFoundException e4) {
//				log.error("Exception during deserialization", e4);
//			}
		}
		catch (IOException e2) {
			log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
			throw new PurchaseAuthorizationException(e2);
//			if (e2 instanceof com.vizzavi.ecommerce.business.common.EcommerceException) {
//				String exceptionName = ((EcommerceException) e2).getClass().getName();
//				Vector<String> exceptionVector = new Vector<String>();
//				exceptionVector.add("PurchaseAuthorizationException");
//				if (exceptionVector.contains(exceptionName)){
//					Exception generatedException = e2;
//				} 
//				else 
//					log.error(" Exception during serialization ", e2);
//			}
//			else{
//			}
		} catch (ClassNotFoundException e) {
			throw new PurchaseAuthorizationException(e);
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
	public PurchaseAuthorization purchasePackage ( String clientApplicationId, String msisdn, String packageId, PurchaseAttributes purchaseAttributes) throws PurchaseAuthorizationException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean state = true;
		PostMethod method = null ;

		try {
			HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
			requestPayload.put("locale", locale);
			String methodName = "purchasePackageMsisdn1";
			requestPayload.put("methodName",methodName);
			requestPayload.put("clientApplicationId",clientApplicationId);
			requestPayload.put("msisdn",msisdn);
			requestPayload.put("packageId",packageId);
			requestPayload.put("purchaseAttributes",purchaseAttributes);
			String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

			if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){
				//try{
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
//				}
//				catch(IOException ie ){
//					ie.printStackTrace();
//				}
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
		//	try {
				//long beforeReadObject = System.currentTimeMillis() ;
			Object result = ois.readObject();
			//log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
			if (result == null)
			{
				log.error("Encountered NULL from the Input Stream. Returning...");
				throw new PurchaseAuthorizationException("Encountered NULL from the Input Stream. Returning...");
				//return null;
			}
			log.debug("Result object type: " + result.getClass().getName());
			if (result instanceof ExceptionAdapter) {
//				String exceptionName = (((ExceptionAdapter) result).originalException).getClass().getName();
//				Vector<String> exceptionVector = new Vector<String>();
//				exceptionVector.add("PurchaseAuthorizationException");
//				if (exceptionVector.contains(exceptionName)){
				Exception generatedException = ((ExceptionAdapter)result).originalException ;
				log.error(" Exception during serialization ", generatedException);
				if (generatedException instanceof PurchaseAuthorizationException)
					throw (PurchaseAuthorizationException) generatedException ;
				//}
				else	
					throw new PurchaseAuthorizationException(generatedException);
			}
			else
			{
				return (PurchaseAuthorization)result;
			}
//			}
//			catch (OptionalDataException e1) {
//				log.error("Primitive data in stream");
//			}
//			catch (ClassNotFoundException e4) {
//				log.error("Exception during deserialization", e4);
//			}
		}	catch (IOException e2) {
			log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
			throw new PurchaseAuthorizationException(e2);
		} catch (ClassNotFoundException e) {
			throw new PurchaseAuthorizationException(e);
		}
		finally {
			try {
				if (oos != null) 	oos.close();
				if (ois != null) 	ois.close();
				if (method != null )	{
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
	public com.vizzavi.ecommerce.business.catalog.CatalogPackage ratePackage (String clientApplicationId,String msisdn,String packageId,PurchaseAttributes purchaseAttributes) throws com.vizzavi.ecommerce.business.common.EcommerceException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean state = true;
		PostMethod method = null ;

		try {
			HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
			requestPayload.put("locale", locale);
			String methodName = "ratePackage2";
			requestPayload.put("methodName",methodName);
			requestPayload.put("clientApplicationId",clientApplicationId);
			requestPayload.put("msisdn",msisdn);
			requestPayload.put("packageId",packageId);
			requestPayload.put("purchaseAttributes",purchaseAttributes);
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
	public PurchaseAuthorization renewPurchasePackageMsisdn (String clientApplicationId,String msisdn,String packageSubscriptionId,PurchaseAttributes purchaseAttributes) throws PurchaseAuthorizationException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean state = true;
		PostMethod method = null ;

		try {
			HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
			requestPayload.put("locale", locale);
			String methodName = "renewPurchasePackageMsisdn3";
			requestPayload.put("methodName",methodName);
			requestPayload.put("clientApplicationId",clientApplicationId);
			requestPayload.put("msisdn",msisdn);
			requestPayload.put("packageSubscriptionId",packageSubscriptionId);
			requestPayload.put("purchaseAttributes",purchaseAttributes);
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
					exceptionVector.add("PurchaseAuthorizationException");
					if (exceptionVector.contains(exceptionName)){
						Exception generatedException = ((ExceptionAdapter)result).originalException ;
						if (generatedException instanceof PurchaseAuthorizationException)
							throw (PurchaseAuthorizationException) generatedException ;
					} 
					else 
						log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
				}
				else                 
				{
					return (PurchaseAuthorization)result;
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
				exceptionVector.add("PurchaseAuthorizationException");
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
	public PurchaseAuthorization confirmPurchasePackagePending (String clientApplicationId,String msisdn,String paymentId,int authResult,String authCode) throws PurchaseAuthorizationException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean state = true;
		PostMethod method = null ;

		try {
			HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
			requestPayload.put("locale", locale);
			String methodName = "confirmPurchasePackagePending4";
			requestPayload.put("methodName",methodName);
			requestPayload.put("clientApplicationId",clientApplicationId);
			requestPayload.put("msisdn",msisdn);
			requestPayload.put("paymentId",paymentId);
			requestPayload.put("authResult", new Integer(authResult) );  
			requestPayload.put("authCode",authCode);
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
					exceptionVector.add("PurchaseAuthorizationException");
					if (exceptionVector.contains(exceptionName)){
						Exception generatedException = ((ExceptionAdapter)result).originalException ;
						if (generatedException instanceof PurchaseAuthorizationException)
							throw (PurchaseAuthorizationException) generatedException ;
					} 
					else 
						log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
				}
				else                 
				{
					return (PurchaseAuthorization)result;
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
				exceptionVector.add("PurchaseAuthorizationException");
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
	public boolean checkUserHasPackageWithPromoCode (String clientApplicationId,String msisdn,String packageId,String promoCode) throws com.vizzavi.ecommerce.business.common.EcommerceException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean state = true;
		PostMethod method = null ;

		try {
			HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
			requestPayload.put("locale", locale);
			String methodName = "checkUserHasPackageWithPromoCode5";
			requestPayload.put("methodName",methodName);
			requestPayload.put("clientApplicationId",clientApplicationId);
			requestPayload.put("msisdn",msisdn);
			requestPayload.put("packageId",packageId);
			requestPayload.put("promoCode",promoCode);
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
	public ValidatePromoStatus validatePromoCode (ValidatePromoParam vpParam) throws com.vizzavi.ecommerce.business.common.EcommerceException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean state = true;
		PostMethod method = null ;

		try {
			HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
			requestPayload.put("locale", locale);
			String methodName = "validatePromoCode6";
			requestPayload.put("methodName",methodName);
			requestPayload.put("vpParam",vpParam);
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
					return (ValidatePromoStatus)result;
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

	//CR-0978 Location Services
	@Override
	public ModifyTariffAuthorization modifyTariff (String msisdn, ModifyTariffAttributes modifyTariffAttributes) throws com.vizzavi.ecommerce.business.common.EcommerceException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean state = true;
		PostMethod method = null ;

		try {
			HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
			requestPayload.put("locale", locale);
			String methodName = "modifyTariff7";
			requestPayload.put("methodName",methodName);
			requestPayload.put("msisdn",msisdn);
			requestPayload.put("modifyTariffAttributes",modifyTariffAttributes);
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
					return (ModifyTariffAuthorization)result;
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
	public GoodwillCreditAuthorization goodwillCredit (String msisdn, GoodwillCreditAttributes goodwillCreditAttributes) throws GoodwillCreditAuthorizationException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean state = true;
		PostMethod method = null ;

		try {
			HashMap requestPayload = new HashMap();
			requestPayload.put("locale", locale);
			String methodName = "goodwillCredit9";
			requestPayload.put("methodName",methodName);
			requestPayload.put("msisdn",msisdn);
			requestPayload.put("goodwillCreditAttributes",goodwillCreditAttributes);
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
					exceptionVector.add("GoodwillCreditAuthorizationException");
					if (exceptionVector.contains(exceptionName)){
						Exception generatedException = ((ExceptionAdapter)result).originalException ;
						if (generatedException instanceof GoodwillCreditAuthorizationException)
							throw (GoodwillCreditAuthorizationException) generatedException ;
					} 
					else 
						log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
				}
				else                 
				{
					return (GoodwillCreditAuthorization)result;
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
				exceptionVector.add("GoodwillCreditAuthorizationException");
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
		String serverHost = ConfigProvider.getProperty("er.server.host", "0.0.0.0");
		int serverPort = ConfigProvider.getPropertyAsInteger("er.server.port", 8094);
		String url = "http://" + serverHost + ":" + serverPort + "/delegates/PurchaseApi";
		log.info("ER delegate connection URL: " + url);
		return url;
	}
	public ObjectOutputStream getObjectOutputStream(URLConnection conn) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(conn.getOutputStream()));
		return oos;
	}

	/**
	 * a decoupling-only method, this will not be implemented in this ecom implementation
	 * {@inheritDoc}
	 * @see {@link PurchaseApi#getVersionInfo}
	 */
	public Map<String, String> getVersionInfo() {
		return null;
	}

}
