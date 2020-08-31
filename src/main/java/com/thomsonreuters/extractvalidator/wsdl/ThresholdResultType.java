//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.31 at 12:02:07 AM EDT 
//


package com.thomsonreuters.extractvalidator.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ThresholdResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ThresholdResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="THRESHOLD_INDICATOR" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}ThresholdIndicatorType" minOccurs="0"/>
 *         &lt;element name="THRESHOLD_AMOUNT" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}ThresholdAmountType" minOccurs="0"/>
 *         &lt;element name="THRESHOLD_PERCENT" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}ThresholdPercentType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ThresholdResultType", propOrder = {
    "thresholdindicator",
    "thresholdamount",
    "thresholdpercent"
})
public class ThresholdResultType {

    @XmlElement(name = "THRESHOLD_INDICATOR")
    protected String thresholdindicator;
    @XmlElement(name = "THRESHOLD_AMOUNT")
    protected String thresholdamount;
    @XmlElement(name = "THRESHOLD_PERCENT")
    protected String thresholdpercent;

    /**
     * Gets the value of the thresholdindicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTHRESHOLDINDICATOR() {
        return thresholdindicator;
    }

    /**
     * Sets the value of the thresholdindicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTHRESHOLDINDICATOR(String value) {
        this.thresholdindicator = value;
    }

    /**
     * Gets the value of the thresholdamount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTHRESHOLDAMOUNT() {
        return thresholdamount;
    }

    /**
     * Sets the value of the thresholdamount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTHRESHOLDAMOUNT(String value) {
        this.thresholdamount = value;
    }

    /**
     * Gets the value of the thresholdpercent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTHRESHOLDPERCENT() {
        return thresholdpercent;
    }

    /**
     * Sets the value of the thresholdpercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTHRESHOLDPERCENT(String value) {
        this.thresholdpercent = value;
    }

}
