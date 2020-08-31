package com.thomsonreuters.extractvalidator.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.thomsonreuters.extractvalidator.wsdl");
        return marshaller;
    }

    @Bean
    public SoapClient soapClient(Jaxb2Marshaller marshaller) {
        SoapClient client = new SoapClient();
        client.setDefaultUri("https://det-legacy-sat.onesourcetax.com/sabrix/services/taxcalculationservice/2011-09-01/taxcalculationservice");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

//    @Bean
//    public Wss4jSecurityInterceptor securityInterceptor() {
//        Wss4jSecurityInterceptor security = new Wss4jSecurityInterceptor();
//
//        // Adds "Timestamp" and "UsernameToken" sections in SOAP header
//        security.setSecurementActions(WSHandlerConstants.USERNAME_TOKEN);
//
//        // Set values for "UsernameToken" sections in SOAP header
//        security.setSecurementPasswordType(WSConstants.PW_TEXT);
//        security.setSecurementUsername(authUsername);
//        security.setSecurementPassword(authPassword);
//        return security;
//    }
}
