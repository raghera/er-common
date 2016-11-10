package com.vodafone.global.er.http;

/**
 * Created by Ravi Aghera
 */
class HttpsConfigurationException extends RuntimeException {

    HttpsConfigurationException(String messaage) {
        super(messaage);
    }
    HttpsConfigurationException(String messaage, Throwable thr) {
        super(messaage, thr);
    }

}
