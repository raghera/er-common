package com.vodafone.global.er.endpoint;

import com.google.common.base.Optional;
import com.vodafone.global.er.properties.CommonPropertyService;
import org.apache.log4j.Logger;

/**
 * Created by Ravi Aghera
 */
public class DelegateEndpoints {

    private static Logger log = Logger.getLogger(DelegateEndpoints.class);

    public String getUrl(ApiNamesEnum endpointName) {

        String serverHost = "", url;
        int serverPort = 0;

        Optional<String> serverHostOpt = CommonPropertyService.getProperty("ecom.proxy.host", "127.0.0.1");
        Optional<String> serverPortOpt = CommonPropertyService.getProperty("ecom.proxy.port", "8888");
        if(serverHostOpt.isPresent() && serverPortOpt.isPresent()) {
            serverHost = serverHostOpt.get();
            serverPort = Integer.valueOf(serverPortOpt.get());
        } else {
            log.warn("Unable to load properties from file system - could not find filename: "
                    + CommonPropertyService.getPropertyFileName()
                    + " Will use system defaults.");
        }

        url = "http://" + serverHost + ":" + serverPort + "/delegates/" + endpointName.getValue();

        log.info("ER delegate connection URL: " + url);

        return url;

    }
}
