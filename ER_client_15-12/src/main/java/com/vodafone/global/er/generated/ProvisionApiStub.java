package com.vodafone.global.er.generated;

import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.ReasonCode;
import com.vizzavi.ecommerce.business.provision.ProvisionApi;
import com.vizzavi.ecommerce.business.provision.ProvisionException;
import com.vizzavi.ecommerce.business.provision.UpdateServiceStatusAuthorization;
import com.vodafone.config.ConfigProvider;
import com.vodafone.global.er.endpoint.DelegateEndpoint;
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
import java.util.Vector;

import static com.vodafone.global.er.endpoint.ApiNamesEnum.PROVISION_API;

public class ProvisionApiStub  extends HttpClientConnector implements ProvisionApi {

    private final Locale locale;
    //CR1231
    //private static LWLogger log = LWSupportFactoryImpl.getInstance().getLogger(ProvisionApiStub.class);
    private static Logger log = Logger.getLogger(ProvisionApiStub.class);
    private DelegateEndpoint endpoint = new DelegateEndpoint();

	//Remedy 6969, Bruno Meseguer, permits better control over the protocol parameters
	private HttpClient httpclient = null;

    public ProvisionApiStub(Locale locale)
    {
        this.locale = locale;
    }

	//Remedy 6969, Bruno Meseguer, permits better control over the protocol parameters
	private void prepareHttpClient()
	{
		httpclient = new HttpClient(httpConnectionManager);

		int httpSockTimeOut	= ConfigProvider.getPropertyAsInteger("er.server.payment.router.http.socket.timeout", 					30000);
		int httpConnTimeOut	= ConfigProvider.getPropertyAsInteger("er.server.payment.router.http.connection.timeout",				30000);

		httpclient.setTimeout(httpSockTimeOut);
		httpclient.setConnectionTimeout(httpConnTimeOut);

		//for the socket timeout this API call may be able to retreive it:
		//httpclient.getHttpConnectionManager().getConnection(httpclient.getHostConfiguration()).getSoTimeout()
		//but we'll keep it simple this time
		log.info("http.socket.timeout = " 					+ httpSockTimeOut);
		log.info("http.connection.timeout = " 				+ httpConnTimeOut); //it doesn't seem possible to get this value from the http-client API
		log.info("http.connection-manager.max-per-host = "	+ httpConnectionManager.getMaxConnectionsPerHost());
		log.info("http.connection-manager.max-total = "		+ httpConnectionManager.getMaxTotalConnections());
	}


	//Remedy 6969, Bruno Meseguer, permits better control over the protocol parameters
	private void setParametrizedHeaders(boolean useHttpclient, PostMethod method, URLConnection conn)
	{
		boolean postConnection = ConfigProvider.getPropertyAsBoolean("er.server.payment.router.http.method.post.connection.close", false);
		boolean closePost = !postConnection;

//		if(postConnection)
//			closePost = true;

		if (closePost)
		{
			if(useHttpclient)
				method.addRequestHeader("connection","close");
			else
				conn.setRequestProperty("connection","close");
		}
	}


    //Remedy 6652, Bruno Meseguer, CORE and IF out of sync with provisioning
    //Remedy 6652, Bruno Meseguer, Method reengineered
    @Override
	public boolean updateServiceStatus (String provisioningId,int serviceStatus,int provisioningStatus) throws com.vizzavi.ecommerce.business.provision.ProvisionException
    {
        ObjectOutputStream oos 	= null;
        ObjectInputStream ois 	= null;

        boolean state 		= true;
        PostMethod method 	= null ;

        try
        {
			HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
			requestPayload.put("locale", locale);
			String methodName = "updateServiceStatus1";
			requestPayload.put("methodName",methodName);
			requestPayload.put("provisioningId",provisioningId);
			requestPayload.put("serviceStatus", new Integer(serviceStatus) );
			requestPayload.put("provisioningStatus", new Integer(provisioningStatus) );
			String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

			if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient"))
			{
			   try
			   {
					if(httpConnectionManager == null )
						createConnectionManager();

					if(httpclient == null)
						prepareHttpClient();

					log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());

					//was before Remedy 6969
					//HttpClient httpclient = new HttpClient(httpConnectionManager);
			        //httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout","30000")));

					method = new PostMethod( getDelegateUrl() );

					//Remedy 6969, Bruno Meseguer, permits better control over the protocol parameters
					setParametrizedHeaders(true, method, null);

					method.addRequestHeader("Content-Type", "application/octet-stream");

				    // Serialize to a byte array
					byte[] buf = super.serializedPayload(requestPayload);
					method.setRequestBody(new ByteArrayInputStream(buf));

					httpclient.executeMethod(method);

					ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
				}
				catch(IOException ie )
				{
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

            try
            {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                log.debug("Result object type: " + result.getClass().getName());

                if (result instanceof ExceptionAdapter)
                {
                	Exception remoteException = ((ExceptionAdapter)result).originalException;

					if (remoteException instanceof ProvisionException)
						throw (ProvisionException)remoteException;
					else
						throw new ProvisionException(remoteException);
                }
                else
                	throw new ProvisionException("Failure obtaining response");
            }
            catch (OptionalDataException e1)
            {
                log.error("Primitive data in stream");

                boolean response = ois.readBoolean();

                if(response == true)
                	return response;
                else
                	throw new ProvisionException("Unsucessful response, please inspect CORE log files");
            }
            catch (ClassNotFoundException e4)
            {
                log.error("Exception during deserialization", e4);
                throw new ProvisionException("Problems obtaining response object", e4);
            }
        }
        catch (IOException e2)
        {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            throw new ProvisionException("Problems obtaining response", e2);
        }
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

        //We do not allow returning any value different than 'true'. In case of failure we generate and throw an Exception
        //throw new ProvisionException("General Invocation Failure, please inspect logs and investigate");

        //return false;
    }

    //Remedy 6652, Bruno Meseguer, CORE and IF out of sync with provisioning
    //Remedy 6652, Bruno Meseguer, Method reengineered
    @Override
	public boolean updateServiceStatus (String provisioningId,int serviceStatus,int provisioningStatus,String provisioningTag) throws com.vizzavi.ecommerce.business.provision.ProvisionException
    {
        ObjectOutputStream oos 	= null;
        ObjectInputStream ois 	= null;

        boolean state 		= true;
        PostMethod method 	= null ;

        try
        {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "updateServiceStatus2";
            requestPayload.put("methodName",methodName);
            requestPayload.put("provisioningId",provisioningId);
            requestPayload.put("serviceStatus", new Integer(serviceStatus) );
            requestPayload.put("provisioningStatus", new Integer(provisioningStatus) );
            requestPayload.put("provisioningTag",provisioningTag);
            String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

		    if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient"))
		    {
		    	try
		    	{
					if(httpConnectionManager == null )
						createConnectionManager();

					if(httpclient == null)
						prepareHttpClient();

					log.debug(" In HTTP ConnectionManager- Connection in use : " + httpConnectionManager.getConnectionsInUse());

					//was before Remedy 6969
					//HttpClient httpclient = new HttpClient(httpConnectionManager);
			        //httpclient.setConnectionTimeout(ConfigProvider.getPropertyAsInteger("er.http.connection.timeout","30000")));

					method = new PostMethod( getDelegateUrl() );

					//Remedy 6969, Bruno Meseguer, permits better control over the protocol parameters
					setParametrizedHeaders(true, method, null);

					method.addRequestHeader("Content-Type", "application/octet-stream");

				    // Serialize to a byte array
					byte[] buf = super.serializedPayload(requestPayload);
					method.setRequestBody(new ByteArrayInputStream(buf));

					httpclient.executeMethod(method);

					ois = new ObjectInputStream(new BufferedInputStream(method.getResponseBodyAsStream(), BUF_SIZE));
				}
				catch(IOException ie )
				{
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

            try
            {
            	long beforeReadObject = System.currentTimeMillis() ;
                Object result = ois.readObject();
              	log.debug("Reading the Object from stream took " + (System.currentTimeMillis()  - beforeReadObject) +" ms.");
                log.debug("Result object type: " + result.getClass().getName());

                if (result instanceof ExceptionAdapter)
                {
                	Exception remoteException = ((ExceptionAdapter)result).originalException;

					if (remoteException instanceof ProvisionException)
						throw (ProvisionException)remoteException;
					else
						throw new ProvisionException(remoteException);
                }
                else
                	throw new ProvisionException("Failure obtaining response");
            }
            catch (OptionalDataException e1)
            {
                log.error("Primitive data in stream");

                boolean response = ois.readBoolean();

                if(response == true)
                	return response;
                else
                	throw new ProvisionException("Unsucessful response, please inspect CORE log files");
            }
            catch (ClassNotFoundException e4)
            {
                log.error("Exception during deserialization", e4);
                throw new ProvisionException("Problems obtaining response object", e4);
            }
        }
        catch (IOException e2)
        {
            log.error("Caught IOException during serialization. Probably it is EcommerceException", e2);
            throw new ProvisionException("Problems obtaining response", e2);
        }
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

        //We do not allow returning any value different than 'true'. In case of failure we generate and throw an Exception
        //throw new ProvisionException("General Invocation Failure, please inspect logs and investigate");

        //return false;
    }
    
    // CR-1759 - start
    @Override
	public UpdateServiceStatusAuthorization updateServiceStatusAuth(String provisioningId,int serviceStatus,int provisioningStatus,String provisioningTag) throws com.vizzavi.ecommerce.business.provision.ProvisionException
    {
      UpdateServiceStatusAuthorization updateServiceStatusAuth_ = null;
      try
      {
        boolean result_ = false;
        if(provisioningTag != null)
          result_ = this.updateServiceStatus(provisioningId,
            serviceStatus, provisioningStatus, provisioningTag);
        else
          result_ = this.updateServiceStatus(provisioningId,
              serviceStatus, provisioningStatus);
        
        ReasonCode rc_ = null;
        
        if(result_)
          rc_ = ReasonCode.OK;
        else
          rc_ = ReasonCode.UPDATE_SERVICE_STATUS_FAILED;
        
        updateServiceStatusAuth_ = new UpdateServiceStatusAuthorization(
            rc_,
            null,
            null,
            "",
            result_
            );
        if(log.isDebugEnabled())
          log.debug(
              " Return from ProvisionApiStub.updateServiceStatus (4 param)  "
              + updateServiceStatusAuth_.getResult());
      }
      catch(Exception e)
      {
        log.error("Error in ProvisionApiStub.updateServiceStatus() "
            + e
          );
        updateServiceStatusAuth_ = new UpdateServiceStatusAuthorization(
            ReasonCode.UPDATE_SERVICE_STATUS_FAILED,
            null,
            null,
            "Error in ProvisionApiStub.updateServiceStatus() "
            + e,
            false
            );
      }
      return updateServiceStatusAuth_;
    }
    // CR-1759 - end

    @Override
	public com.vizzavi.ecommerce.business.provision.ProvisionAuthorization updateProvisioningTag (String clientId,String msisdn,String subscriptionId,String serviceId,String newProvisioningTag) throws com.vizzavi.ecommerce.business.provision.ProvisionException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
	boolean state = true;
        PostMethod method = null ;

        try {
            HashMap<String, Serializable> requestPayload = new HashMap<String, Serializable>();
            requestPayload.put("locale", locale);
            String methodName = "updateProvisioningTag3";
            requestPayload.put("methodName",methodName);
            requestPayload.put("clientId",clientId);
            requestPayload.put("msisdn",msisdn);
            requestPayload.put("subscriptionId",subscriptionId);
            requestPayload.put("serviceId",serviceId);
            requestPayload.put("newProvisioningTag",newProvisioningTag);
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
					exceptionVector.add("com.vizzavi.ecommerce.business.provision.ProvisionException");
					if (exceptionVector.contains(exceptionName)){
					 Exception generatedException = ((ExceptionAdapter)result).originalException ;
                     if (generatedException instanceof com.vizzavi.ecommerce.business.provision.ProvisionException)
                          throw (com.vizzavi.ecommerce.business.provision.ProvisionException) generatedException ;
                     }
                    else
					  log.error(" Exception during serialization ", ((ExceptionAdapter)result).originalException);
                }
                else
                {
                    return (com.vizzavi.ecommerce.business.provision.ProvisionAuthorization)result;
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
					exceptionVector.add("com.vizzavi.ecommerce.business.provision.ProvisionException");
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

		//Remedy 6969, Bruno Meseguer, permits better control over the protocol parameters
		setParametrizedHeaders(false, null, conn);

        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Content-Type", "application/octet-stream");
        return conn;
    }

	protected String getDelegateUrl() {
        return endpoint.getEndpointUrl(PROVISION_API);
    }
    public ObjectOutputStream getObjectOutputStream(URLConnection conn) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(conn.getOutputStream()));
        return oos;
    }

}
