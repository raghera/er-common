package com.vodafone.global.er.endpoint;

/**
 * Created by Ravi Aghera
 */
public enum ApiNamesEnum {
    CATALOG_API("CatalogApi"),
    CHARGING_API("ChargingApi"),
    CUSTCARE_API("CustcareApi"),
    PROVISION_API("ProvisionApi"),
    PURCHASE_API("PurchaseApi"),
    SELFCARE_API("SelfcareApi");

    private String value;

    ApiNamesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
