package com.vodafone.global.er.http;

import com.google.common.base.Optional;
import com.vodafone.global.er.properties.CommonPropertiesEnum;
import com.vodafone.global.er.properties.CommonPropertyService;
import org.apache.log4j.Logger;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ErX509TrustManager implements X509TrustManager {

    private static Logger    log = Logger.getLogger(ErX509TrustManager.class);

    private X509TrustManager defaultTrustManager = null;
    private X509KeyManager defaultKeyManager = null;

    //Load the ssl properties
    static {
        Optional<String> store = CommonPropertyService.getProperty(CommonPropertiesEnum.PROP_TRUSTSTORE.getValue(), "./truststore/cacerts");
        Optional<String> pass = CommonPropertyService.getProperty(CommonPropertiesEnum.PROP_TRUSTSTORE_PASSWORD.getValue(), "changeit");

        if(store.isPresent() && pass.isPresent()) {
            System.setProperty("javax.net.ssl.trustStore", store.get());
            System.setProperty("javax.net.ssl.trustStorePassword", pass.get());
        } else {
            log.warn("Could not find javax.net.ssl.trustStore property" );
        }
    }

    private void init() throws GeneralSecurityException, IOException {
        Optional<String> trustStore = Optional.of(System.getProperty("javax.net.ssl.trustStore"));
        Optional<String> password = Optional.of(System.getProperty("javax.net.ssl.trustStorePassword"));

        final KeyStore keyStore = KeyStore.getInstance("JKS");
        try (final InputStream is = new FileInputStream(trustStore.get())) {
            keyStore.load(is, password.get().toCharArray());
        }
        final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory
                .getDefaultAlgorithm());
        kmf.init(keyStore, password.get().toCharArray());

        final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory
                .getDefaultAlgorithm());
        tmf.init(keyStore);

        defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        defaultKeyManager = (X509KeyManager) kmf.getKeyManagers()[0];
    }

    /**
     * Constructor. Configures the TrustManagerFactory and gets the trust
     * manager.
     */
    public ErX509TrustManager() {
        try {
            init();
//            log.debug("Loading keystore for our custom trustmanager...");
//
//            KeyStore store = KeyStore.getInstance(KeyStore.getDefaultType());
//
//            Optional<String> certfile = CommonPropertyService.getProperty(CommonPropertiesEnum.PROP_TRUSTSTORE.getValue(), "./truststore/cacerts");
//            Optional<String> password = CommonPropertyService.getProperty(CommonPropertiesEnum.PROP_TRUSTSTORE_PASSWORD.getValue(), "changeit");
//
//            if((!certfile.isPresent() || !password.isPresent())) {
//                throw new ErHttpException("Could not find truststore at: " + CommonPropertiesEnum.PROP_TRUSTSTORE.getValue());
//            } else {
//                FileInputStream in = new FileInputStream(certfile.get());
//
//                store.load(in, password.get().toCharArray());
//
//                log.debug("Keystore loaded.");
//                TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//
//                log.debug("Configuring trustmanager factory...");
//
//                factory.init(store);
//
//                log.debug("Trustmanager factory configured.");
//
//                defaultTrustManager = (X509TrustManager) factory.getTrustManagers()[0];
        } catch (GeneralSecurityException | IOException e) {
            log.error(e);
            //TODO should be failing here so rethrow an Exception.  No point continuing if you couldn't setup
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

    public X509TrustManager getDefaultTrustManager() {
        return defaultTrustManager;
    }

    public X509KeyManager getDefaultKeyManager() {
        return defaultKeyManager;
    }
}
