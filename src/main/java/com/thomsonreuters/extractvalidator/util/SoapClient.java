package com.thomsonreuters.extractvalidator.util;

import com.thomsonreuters.extractvalidator.wsdl.*;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.math.BigDecimal;

@Component(SoapClient.BEAN_NAME)
public class SoapClient extends WebServiceGatewaySupport {
    public static final String BEAN_NAME = "SoapClient";

    private static final Logger LOG = ESAPI.getLogger(SoapClient.class);

    private String username = "^CRETestTool";
    private String password = "password";

    public TaxCalculationResponse sendTaxCalcRequest()
    {
        IndataType indata = new IndataType();
        indata.setVersion(VersionType.G);
        indata.setEXTERNALCOMPANYID("1005307421-100");

        IndataInvoiceType indataInvoice = new IndataInvoiceType();
        indataInvoice.setCALCULATIONDIRECTION("F");
        indataInvoice.setCOMPANYROLE("S");
        indataInvoice.setCURRENCYCODE("USD");
        indataInvoice.setINVOICEDATE("2018-08-08");
        indataInvoice.setISAUDITED("N");
            ZoneAddressType zoneAddressType = new ZoneAddressType();
            zoneAddressType.setCOUNTRY("UNITED STATES");
            zoneAddressType.setSTATE("NEW YORK");
            zoneAddressType.setCITY("NEW YORK");
            zoneAddressType.setPOSTCODE("10001");
        indataInvoice.setSHIPFROM(zoneAddressType);
        indataInvoice.setSHIPTO(zoneAddressType);
        indataInvoice.setTRANSACTIONTYPE("GS");
            IndataLineType indataLineType = new IndataLineType();
            indataLineType.setID("1");
            indataLineType.setLINENUMBER(BigDecimal.valueOf(1));
            indataLineType.setGROSSAMOUNT("100");
            indataLineType.setPRODUCTCODE("clothes");
        indataInvoice.getLINE().add(indataLineType);

        indata.getINVOICE().add(indataInvoice);

        TaxCalculationRequest request = new TaxCalculationRequest();
        request.setINDATA(indata);

        JAXBElement response = (JAXBElement) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "https://det-legacy-sat.onesourcetax.com/sabrix/services/taxcalculationservice/2011-09-01/taxcalculationservice",
                        request,
                        message -> {
                            try {
                                SaajSoapMessage saajSoapMessage = (SaajSoapMessage)message;

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
                                logger.info(this.username);
                                userNameSOAPElement.addTextNode(this.username);

                                SOAPElement passwordSOAPElement = usernameTokenSOAPElement.addChildElement("Password", "wsse");
                                passwordSOAPElement.addAttribute(QName.valueOf("Type"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
                                passwordSOAPElement.addTextNode(this.password);

                                soapMessage.saveChanges();
                                logger.info("Marshalling of SOAP header success.");
                            } catch (Exception e) {
                                logger.error("error during marshalling of the SOAP headers", e);
                            }
                        });

        TaxCalculationResponse response1 = (TaxCalculationResponse)response.getValue();
        return response1;

    }


}
