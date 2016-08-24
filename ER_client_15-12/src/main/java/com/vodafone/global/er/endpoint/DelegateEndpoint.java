package com.vodafone.global.er.endpoint;

import com.google.common.base.Optional;
import com.vodafone.global.er.properties.CommonPropertyService;
import org.apache.log4j.Logger;

import static com.vodafone.global.er.properties.CommonPropertiesEnum.ER_SERVER_HOST;
import static com.vodafone.global.er.properties.CommonPropertiesEnum.ER_SERVER_PORT;

/**
 * Created by Ravi Aghera
 */
public class DelegateEndpoint {

    private static Logger log = Logger.getLogger(DelegateEndpoint.class);

    public final String getEndpointUrl(final ApiNamesEnum endpointName) {

        String serverHost = "", url;
        int serverPort = 0;

        Optional<String> serverHostOpt = CommonPropertyService.getProperty(ER_SERVER_HOST.getValue(), "127.0.0.1");
        Optional<String> serverPortOpt = CommonPropertyService.getProperty(ER_SERVER_PORT.getValue(), "8094");
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
