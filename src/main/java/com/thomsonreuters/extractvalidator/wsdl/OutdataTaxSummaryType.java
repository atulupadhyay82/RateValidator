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
 * 
 * 				
 * A summary of tax amounts and warnings.
 * 				
 * 			
 * 
 * <p>Java class for OutdataTaxSummaryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OutdataTaxSummaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TAXABLE_BASIS" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}NillableDecimalType" minOccurs="0"/>
 *         &lt;element name="NON_TAXABLE_BASIS" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}NillableDecimalType"/>
 *         &lt;element name="EXEMPT_AMOUNT" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}NillableDecimalType"/>
 *         &lt;element name="TAX_RATE" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}NillableDecimalType"/>
 *         &lt;element name="EFFECTIVE_TAX_RATE" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}NillableDecimalType" minOccurs="0"/>
 *         &lt;element name="ADVISORIES" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}OutdataAdvisoriesType" minOccurs="0"/>
 *         &lt;element name="ACCRUAL_AMOUNT" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}NillableDecimalType" minOccurs="0"/>
 *         &lt;element name="PARTNER_AMOUNT" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}NillableDecimalType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OutdataTaxSummaryType", propOrder = {
    "taxablebasis",
    "nontaxablebasis",
    "exemptamount",
    "taxrate",
    "effectivetaxrate",
    "advisories",
    "accrualamount",
    "partneramount"
})
public class OutdataTaxSummaryType {

    @XmlElement(name = "TAXABLE_BASIS")
    protected String taxablebasis;
    @XmlElement(name = "NON_TAXABLE_BASIS", required = true)
    protected String nontaxablebasis;
    @XmlElement(name = "EXEMPT_AMOUNT", required = true)
    protected String exemptamount;
    @XmlElement(name = "TAX_RATE", required = true)
    protected String taxrate;
    @XmlElement(name = "EFFECTIVE_TAX_RATE")
    protected String effectivetaxrate;
    @XmlElement(name = "ADVISORIES")
    protected OutdataAdvisoriesType advisories;
    @XmlElement(name = "ACCRUAL_AMOUNT")
    protected String accrualamount;
    @XmlElement(name = "PARTNER_AMOUNT")
    protected String partneramount;

    /**
     * Gets the value of the taxablebasis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTAXABLEBASIS() {
        return taxablebasis;
    }

    /**
     * Sets the value of the taxablebasis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTAXABLEBASIS(String value) {
        this.taxablebasis = value;
    }

    /**
     * Gets the value of the nontaxablebasis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNONTAXABLEBASIS() {
        return nontaxablebasis;
    }

    /**
     * Sets the value of the nontaxablebasis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNONTAXABLEBASIS(String value) {
        this.nontaxablebasis = value;
    }

    /**
     * Gets the value of the exemptamount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEXEMPTAMOUNT() {
        return exemptamount;
    }

    /**
     * Sets the value of the exemptamount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEXEMPTAMOUNT(String value) {
        this.exemptamount = value;
    }

    /**
     * Gets the value of the taxrate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTAXRATE() {
        return taxrate;
    }

    /**
     * Sets the value of the taxrate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTAXRATE(String value) {
        this.taxrate = value;
    }

    /**
     * Gets the value of the effectivetaxrate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEFFECTIVETAXRATE() {
        return effectivetaxrate;
    }

    /**
     * Sets the value of the effectivetaxrate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEFFECTIVETAXRATE(String value) {
        this.effectivetaxrate = value;
    }

    /**
     * Gets the value of the advisories property.
     * 
     * @return
     *     possible object is
     *     {@link OutdataAdvisoriesType }
     *     
     */
    public OutdataAdvisoriesType getADVISORIES() {
        return advisories;
    }

    /**
     * Sets the value of the advisories property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutdataAdvisoriesType }
     *     
     */
    public void setADVISORIES(OutdataAdvisoriesType value) {
        this.advisories = value;
    }

    /**
     * Gets the value of the accrualamount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACCRUALAMOUNT() {
        return accrualamount;
    }

    /**
     * Sets the value of the accrualamount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACCRUALAMOUNT(String value) {
        this.accrualamount = value;
    }

    /**
     * Gets the value of the partneramount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPARTNERAMOUNT() {
        return partneramount;
    }

    /**
     * Sets the value of the partneramount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPARTNERAMOUNT(String value) {
        this.partneramount = value;
    }

}