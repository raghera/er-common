package com.vodafone.global.er.http;

import com.google.common.base.Optional;
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

    private static Logger log = Logger.getLogger(ErX509TrustManager.class);

    private X509TrustManager defaultTrustManager = null;
    private X509KeyManager defaultKeyManager = null;
    private static final String SYS_PROP_TRUSTSTORE = "javax.net.ssl.trustStore";
    private static final String SYS_PROP_TRUSTSTORE_PASSWORD = "javax.net.ssl.trustStorePassword";
    private static final String SYS_PROP_KEYSTORE = "javax.net.ssl.keyStore";
    private static final String SYS_PROP_KEYSTORE_PASSWORD = "javax.net.ssl.keyStorePassword";

    private void init() throws GeneralSecurityException, IOException {
        Optional<String> kStore = Optional.of(System.getProperty(SYS_PROP_KEYSTORE));
        Optional<String> kStorePw = Optional.of(System.getProperty(SYS_PROP_KEYSTORE_PASSWORD));

        if(!kStore.isPresent() || !kStorePw.isPresent()) {
            throw new HttpsConfigurationException("Property " +
                    SYS_PROP_KEYSTORE + ", and/or "+
                    SYS_PROP_KEYSTORE_PASSWORD +
                    " are required for https communication but not found");
        }

        final KeyStore keyStore = KeyStore.getInstance("JKS");
        try (final InputStream is = new FileInputStream(kStore.get())) {
            keyStore.load(is, kStorePw.get().toCharArray());
        }
        final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory
                .getDefaultAlgorithm());
        kmf.init(keyStore, kStorePw.get().toCharArray());

        final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory
                .getDefaultAlgorithm());

        tmf.init(keyStore);

        defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        defaultKeyManager = (X509KeyManager) kmf.getKeyManagers()[0];
    }

    public ErX509TrustManager() {
        try {
            init();
        } catch (GeneralSecurityException | IOException e) {
            log.error(e);
            throw new HttpsConfigurationException("Could not configure https connection correctly. Fatal error.", e);
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
