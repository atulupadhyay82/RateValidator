//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.31 at 12:02:07 AM EDT 
//


package com.thomsonreuters.extractvalidator.wsdl;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for TaxCalculationFault complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaxCalculationFault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="additionalMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="errorLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="errorSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxCalculationFault", propOrder = {
    "additionalMessage",
    "errorLocation",
    "errorSource"
})
@XmlRootElement(name = "taxCalculationFault")
public class TaxCalculationFault {

    @XmlElement(required = true)
    protected String additionalMessage;
    @XmlElement(required = true)
    protected String errorLocation;
    @XmlElement(required = true)
    protected String errorSource;

    /**
     * Gets the value of the additionalMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalMessage() {
        return additionalMessage;
    }

    /**
     * Sets the value of the additionalMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalMessage(String value) {
        this.additionalMessage = value;
    }

    /**
     * Gets the value of the errorLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorLocation() {
        return errorLocation;
    }

    /**
     * Sets the value of the errorLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorLocation(String value) {
        this.errorLocation = value;
    }

    /**
     * Gets the value of the errorSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorSource() {
        return errorSource;
    }

    /**
     * Sets the value of the errorSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorSource(String value) {
        this.errorSource = value;
    }

}
