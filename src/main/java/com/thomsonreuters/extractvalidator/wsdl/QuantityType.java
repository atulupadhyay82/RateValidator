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
 * <p>Java class for QuantityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QuantityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DEFAULT" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}BooleanType" minOccurs="0"/>
 *         &lt;element name="AMOUNT" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}NillableDecimalType" minOccurs="0"/>
 *         &lt;element name="UOM" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}UOM" minOccurs="0"/>
 *         &lt;element name="TYPE" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}BilledQuantityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuantityType", propOrder = {
    "_default",
    "amount",
    "uom",
    "type"
})
public class QuantityType {

    @XmlElement(name = "DEFAULT")
    protected String _default;
    @XmlElement(name = "AMOUNT")
    protected String amount;
    @XmlElement(name = "UOM")
    protected String uom;
    @XmlElement(name = "TYPE")
    protected BilledQuantityType type;

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDEFAULT() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDEFAULT(String value) {
        this._default = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAMOUNT() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAMOUNT(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the uom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUOM() {
        return uom;
    }

    /**
     * Sets the value of the uom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUOM(String value) {
        this.uom = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link BilledQuantityType }
     *     
     */
    public BilledQuantityType getTYPE() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link BilledQuantityType }
     *     
     */
    public void setTYPE(BilledQuantityType value) {
        this.type = value;
    }

}
