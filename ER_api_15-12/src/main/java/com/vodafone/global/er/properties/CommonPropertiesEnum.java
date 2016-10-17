package com.vodafone.global.er.properties;

/**
 * Created by Ravi Aghera
 */
public enum CommonPropertiesEnum {
    ER_SERVER_HOST("er.server.host"),
    ER_SERVER_PORT("er.server.port"),
    PROP_HTTPS_ER_SERVER_HOST("https.er.server.host"),
    PROP_HTTPS_ER_SERVER_PORT("https.er.server.port"),
    PROP_HTTPS_ER_SERVER_PATH("https.er.server.path"),
    PROP_TRUSTSTORE("ssl.truststore"),
    PROP_TRUSTSTORE_PASSWORD("ssl.truststore.password"),
    PROP_EPA_PROTOCOL("protocol");



    private String value;

    CommonPropertiesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
