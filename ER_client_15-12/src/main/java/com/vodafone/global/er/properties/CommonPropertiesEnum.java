package com.vodafone.global.er.properties;

/**
 * Created by Ravi Aghera
 */
public enum CommonPropertiesEnum {
    ER_SERVER_HOST("er.server.host"),
    ER_SERVER_PORT("er.server.port");

    private String value;

    CommonPropertiesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
