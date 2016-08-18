package com.vodafone.global.er.http;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

import com.vodafone.config.ConfigProvider;

public class ErX509TrustManager implements X509TrustManager {

    private static Logger    log = Logger.getLogger(ErX509TrustManager.class);

    /*
     * The default trust manager used to verify trusted servers.
     */  
    private X509TrustManager defaultTrustManager = null;


    /**
     * Constructor. Configures the TrustManagerFactory and gets the trust
     * manager.
     */
    public ErX509TrustManager() {
        try {

            log.debug("Loading keystore for our custom trustmanager...");
            

            KeyStore store = KeyStore
                    .getInstance(KeyStore.getDefaultType());

            store.load(new FileInputStream(
                           ConfigProvider.getProperty(
                                                    ErHttpConnection.CONFIG_HTTP_SECURITY_TRUSTSTORE_FILE,
                                                    "/opt/SP/java/current/jre/lib/security/cacerts")),
                                       ConfigProvider.getProperty(
                                            ErHttpConnection.CONFIG_HTTP_SECURITY_TRUSTSTORE_PW,
                                            "changeit").toCharArray());

            log.debug("Keystore loaded.");
            
            
            TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            log.debug("Configuring trustmanager factory...");
            

            factory.init(store);

            log.debug("Trustmanager factory configured.");
            

            defaultTrustManager = (X509TrustManager) factory.getTrustManagers()[0];

        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * Given the partial or complete certificate chain provided by the peer,
     * build a certificate path to a trusted root and return if it can be
     * validated and is trusted for client SSL authentication based on the
     * authentication type.
     * 
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[],
     *      String)
     * @param x509Certificate
     *            Partial or complete certificate chain.
     * @param authType
     *            The authentication type.
     * @throws CertificateException
     *             Thrown if the certificate chain is not trusted by this
     *             TrustManager.
     */
    @Override
	public void checkClientTrusted(X509Certificate[] x509Certificate,
            String authType) throws CertificateException {
    	
    	defaultTrustManager.checkClientTrusted(x509Certificate, authType);
    }

    /**
     * Given the partial or complete certificate chain provided by the peer,
     * build a certificate path to a trusted root and return if it can be
     * validated and is trusted for server SSL authentication based on the
     * authentication type
     * 
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[],
     *      String)
     * @param x509Certificate
     *            Partial or complete certificate chain.
     * @param authType
     *            The authentication type.
     * @throws CertificateException
     *             Thrown if the certificate chain is not trusted by this
     *             TrustManager.
     */
    @Override
	public void checkServerTrusted(X509Certificate[] x509Certificate,
            String authType) throws CertificateException {

        if (log.isDebugEnabled()) {
            log.debug("Checking if this server is trusted... ");
        }

        for (int j=0; j < x509Certificate.length; j++)
        {
        	X509Certificate theCertificate = x509Certificate[j];
        	
        	if(log.isInfoEnabled())
        	{
            	log.info("  Server certificate information:");
                log.info("  Subject DN: " + theCertificate.getSubjectDN());
                log.info("  Issuer DN: " + theCertificate.getIssuerDN());
                log.info("  Serial number: " + theCertificate.getSerialNumber());
        		
        	}

            try {
            	defaultTrustManager.checkServerTrusted(x509Certificate, authType);
            } catch (CertificateException e) {
                log.error("Error while checking server certificate.", e);
                throw e;
            }

            
            
            try{
            	theCertificate.checkValidity();
            }catch(Exception e){
            	
            	log.error("checkcertificate Validity", e);
            	log.error(e.getMessage());
            	if(e.getMessage() != null && e.getMessage().contains("NotAfter"))
            	{
            		throw new CertificateException("certificate expired");
            	}
            }

        }
    
    }

    /**
     * Returns an array of certificate authority certificates which are trusted
     * for authenticating peers.
     * 
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     * @return A non-null (possibly empty) array of acceptable CA issuer
     *         certificates.
     */
    @Override
	public X509Certificate[] getAcceptedIssuers() {
    	log.info("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
    	
    	return this.defaultTrustManager.getAcceptedIssuers();

    }

}
