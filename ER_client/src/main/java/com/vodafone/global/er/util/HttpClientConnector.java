package com.vodafone.global.er.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

import com.vodafone.config.ConfigProvider;

public class HttpClientConnector {

	    public static final int BUF_SIZE = 10240;

	  protected   static  MultiThreadedHttpConnectionManager httpConnectionManager;


	    static {
	        init();
	    }



	    private static void init() {


	    	 String httpConnectorMethod = ConfigProvider.getProperty("er.http.connector.method", "urlconnection");

	    	if (httpConnectorMethod != null && httpConnectorMethod.equals("httpclient")){

	    		createConnectionManager();

	    	}
	     }


		@SuppressWarnings("deprecation")
		protected static void createConnectionManager()
		{
			if(httpConnectionManager == null)
			{
				httpConnectionManager = new MultiThreadedHttpConnectionManager();

				//Remedy 6969, Bruno Meseguer, permits better control over the protocol parameters
				int httpMaxConHost	= ConfigProvider.getPropertyAsInteger("er.server.payment.router.http.connection-manager.max-per-host",	25);
				int httpMaxTotal		= ConfigProvider.getPropertyAsInteger("er.server.payment.router.http.connection-manager.max-total",		25);

				httpConnectionManager.setMaxConnectionsPerHost(	httpMaxConHost);
				httpConnectionManager.setMaxTotalConnections(	httpMaxTotal);

				//was
				//String connectionPerHost= PropertyServer.getProperty("er.http.max.connection.perhost","30");
				//String connectionTotal=PropertyServer.getProperty("er.http.max.connection.total","30");

				//httpConnectionManager.setMaxConnectionsPerHost(Integer.parseInt(connectionPerHost));
				//httpConnectionManager.setMaxTotalConnections(Integer.parseInt(connectionTotal));
			}
		}


		public byte[]  serializedPayload(HashMap<String, Serializable> requestPayload) throws IOException {
			ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
			ObjectOutput  out = new ObjectOutputStream(bos) ;
			out.writeObject(requestPayload);
			out.close();

			// Get the bytes of the serialized object
			return bos.toByteArray();
		}

}
