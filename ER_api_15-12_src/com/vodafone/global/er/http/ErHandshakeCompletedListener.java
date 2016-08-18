/**
 * 
 */
package com.vodafone.global.er.http;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;

/**
 * @author RITS User
 *
 */
public class ErHandshakeCompletedListener implements HandshakeCompletedListener {
	private static Logger log = Logger.getLogger(ErHandshakeCompletedListener.class);
	private boolean isPeerCertificateExpired = Boolean.FALSE;
	/* (non-Javadoc)
	 * @see javax.net.ssl.HandshakeCompletedListener#handshakeCompleted(javax.net.ssl.HandshakeCompletedEvent)
	 */
	@Override
	public void handshakeCompleted(HandshakeCompletedEvent event) {

		SSLSession session = event.getSession();
		
		log.info("Handshake Completed with peer " + session.getPeerHost() + " cipher: " + session.getCipherSuite());
	     javax.security.cert.X509Certificate[] certs = null;      
	     try      
	     {        
	    	 certs = session.getPeerCertificateChain();      
	     
	     }catch (javax.net.ssl.SSLPeerUnverifiedException puv)
	     {   
	    	 certs = null;      
	     }      
	     if  (certs != null)     
	     {        
	    	 log.info("peer certificates:");        
	    	 for (int z=0; z<certs.length; z++) 
	    		 log.info("certs["+z+"]: " + certs[z]);      
	     
	     }      
	     else      
	     {        
	    	 log.info("No peer certificates presented");      
	     }		
/*		Certificate[] peerCertificates = null;
		try {
			peerCertificates = event.getSession().getPeerCertificates();
		} catch (SSLPeerUnverifiedException e) {
			// TODO Auto-generated catch block
			log.error("handshakeCompleted", e);
		}
		
		if(peerCertificates != null && peerCertificates.length > 0)
		{
			log.info("peerCertificates.length FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF: " + peerCertificates.length);

	        for (int j=0; j < peerCertificates.length; j++)
	        {
	        	X509Certificate theCertificate = (X509Certificate) peerCertificates[j];
	        	log.info("  Server certificate information:");
	            log.info("  Subject DN: " + theCertificate.getSubjectDN());
	            log.info("  Issuer DN: " + theCertificate.getIssuerDN());
	            log.info("  Serial number: " + theCertificate.getSerialNumber());
	            log.info("");

            try{
            	theCertificate.checkValidity();
	            }catch(Exception e){
	            	
	            	log.error("checkValidity", e);
	            	log.error(e.getMessage());
	            	if(e.getMessage() != null && e.getMessage().contains("NotAfter"))
	            	{
	            		this.isPeerCertificateExpired = Boolean.TRUE;
	            	}
	            }

	        }			
		}*/
		

	}
	
	public boolean isPeerCertificateExpired() {
		return isPeerCertificateExpired;
	}

}
