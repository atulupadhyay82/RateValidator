package com.thomsonreuters.extractvalidator.util;

import com.thomsonreuters.extractvalidator.wsdl.*;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.soap.*;

@Component(SoapClient.BEAN_NAME)
public class SoapClient extends WebServiceGatewaySupport {
    public static final String BEAN_NAME = "SoapClient";

    private static final Logger LOG = ESAPI.getLogger(SoapClient.class);


    public TaxCalculationResponse sendTaxCalcRequest(String uri, String username, String password, IndataType indata, int timeoutRetry)
    {

        TaxCalculationRequest request = new TaxCalculationRequest();
        request.setINDATA(indata);

        JAXBElement response = null;
        boolean hasIOException = false;
        do {
            try {
                hasIOException = false;
                response = (JAXBElement) getWebServiceTemplate()
                        .marshalSendAndReceive(
                                uri,
                                request,
                                message -> {
                                    try {
                                        SaajSoapMessage saajSoapMessage = (SaajSoapMessage) message;
                                        SOAPMessage soapMessage = saajSoapMessage.getSaajMessage();
                                        SOAPPart soapPart = soapMessage.getSOAPPart();
                                        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
                                        SOAPHeader soapHeader = soapEnvelope.getHeader();
                                        Name headerElementName = soapEnvelope.createName(
                                                "Security",
                                                "wsse",
                                                "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
                                        );
                                        SOAPHeaderElement soapHeaderElement = soapHeader.addHeaderElement(headerElementName);
                                        SOAPElement usernameTokenSOAPElement = soapHeaderElement.addChildElement("UsernameToken", "wsse");
                                        SOAPElement userNameSOAPElement = usernameTokenSOAPElement.addChildElement("Username", "wsse");
                                        userNameSOAPElement.addTextNode(username);
                                        SOAPElement passwordSOAPElement = usernameTokenSOAPElement.addChildElement("Password", "wsse");
                                        passwordSOAPElement.addAttribute(QName.valueOf("Type"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
                                        passwordSOAPElement.addTextNode(password);
                                        soapMessage.saveChanges();
                                    } catch (Exception e) {
                                        LOG.error(Logger.EVENT_FAILURE, "error during marshalling of the SOAP headers", e);
                                    }
                                });
            } catch (WebServiceIOException ex) {
                LOG.error(Logger.EVENT_FAILURE,
                        String.format("get WebServiceIOException when calling tax calculation api, %s, %s",
                                ex.getMessage(), ex.getStackTrace()));
                hasIOException = true;
                if (--timeoutRetry==0)
                    throw ex;
            }
        } while (hasIOException);

        TaxCalculationResponse response1 = (TaxCalculationResponse)response.getValue();
        return response1;

    }


}
