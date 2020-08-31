//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.31 at 12:02:07 AM EDT 
//


package com.thomsonreuters.extractvalidator.wsdl;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommonAddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommonAddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COUNTRY" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}AddressCountryType" minOccurs="0"/>
 *         &lt;element name="PROVINCE" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}AddressProvinceType" minOccurs="0"/>
 *         &lt;element name="STATE" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}AddressStateType" minOccurs="0"/>
 *         &lt;element name="COUNTY" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}AddressCountyType" minOccurs="0"/>
 *         &lt;element name="CITY" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}AddressCityType" minOccurs="0"/>
 *         &lt;element name="DISTRICT" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}AddressDistrictType" minOccurs="0"/>
 *         &lt;element name="POSTCODE" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}AddressPostCodeType" minOccurs="0"/>
 *         &lt;element name="GEOCODE" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}AddressGEOCodeType" minOccurs="0"/>
 *         &lt;element name="BULK_STORAGE_FACILITY" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}BooleanType" minOccurs="0"/>
 *         &lt;element name="FACILITY_ID" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}FacilityIdType" minOccurs="0"/>
 *         &lt;element name="IRS_REGISTERED_FACILITY" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}BooleanType" minOccurs="0"/>
 *         &lt;element name="LATITUDE" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}LatitudeType" minOccurs="0"/>
 *         &lt;element name="LONGITUDE" type="{http://www.sabrix.com/services/taxcalculationservice/2011-09-01}LongitudeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommonAddressType", propOrder = {
    "country",
    "province",
    "state",
    "county",
    "city",
    "district",
    "postcode",
    "geocode",
    "bulkstoragefacility",
    "facilityid",
    "irsregisteredfacility",
    "latitude",
    "longitude"
})
@XmlSeeAlso({
    AddressType.class,
    ZoneAddressType.class
})
public class CommonAddressType {

    @XmlElement(name = "COUNTRY")
    protected String country;
    @XmlElement(name = "PROVINCE")
    protected String province;
    @XmlElement(name = "STATE")
    protected String state;
    @XmlElement(name = "COUNTY")
    protected String county;
    @XmlElement(name = "CITY")
    protected String city;
    @XmlElement(name = "DISTRICT")
    protected String district;
    @XmlElement(name = "POSTCODE")
    protected String postcode;
    @XmlElement(name = "GEOCODE")
    protected String geocode;
    @XmlElement(name = "BULK_STORAGE_FACILITY")
    protected String bulkstoragefacility;
    @XmlElement(name = "FACILITY_ID")
    protected String facilityid;
    @XmlElement(name = "IRS_REGISTERED_FACILITY")
    protected String irsregisteredfacility;
    @XmlElement(name = "LATITUDE")
    protected BigDecimal latitude;
    @XmlElement(name = "LONGITUDE")
    protected BigDecimal longitude;

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOUNTRY() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOUNTRY(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the province property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROVINCE() {
        return province;
    }

    /**
     * Sets the value of the province property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROVINCE(String value) {
        this.province = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATE() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATE(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the county property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOUNTY() {
        return county;
    }

    /**
     * Sets the value of the county property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOUNTY(String value) {
        this.county = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCITY() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCITY(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the district property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDISTRICT() {
        return district;
    }

    /**
     * Sets the value of the district property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDISTRICT(String value) {
        this.district = value;
    }

    /**
     * Gets the value of the postcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOSTCODE() {
        return postcode;
    }

    /**
     * Sets the value of the postcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOSTCODE(String value) {
        this.postcode = value;
    }

    /**
     * Gets the value of the geocode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGEOCODE() {
        return geocode;
    }

    /**
     * Sets the value of the geocode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGEOCODE(String value) {
        this.geocode = value;
    }

    /**
     * Gets the value of the bulkstoragefacility property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBULKSTORAGEFACILITY() {
        return bulkstoragefacility;
    }

    /**
     * Sets the value of the bulkstoragefacility property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBULKSTORAGEFACILITY(String value) {
        this.bulkstoragefacility = value;
    }

    /**
     * Gets the value of the facilityid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFACILITYID() {
        return facilityid;
    }

    /**
     * Sets the value of the facilityid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFACILITYID(String value) {
        this.facilityid = value;
    }

    /**
     * Gets the value of the irsregisteredfacility property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIRSREGISTEREDFACILITY() {
        return irsregisteredfacility;
    }

    /**
     * Sets the value of the irsregisteredfacility property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIRSREGISTEREDFACILITY(String value) {
        this.irsregisteredfacility = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLATITUDE() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLATITUDE(BigDecimal value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLONGITUDE() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLONGITUDE(BigDecimal value) {
        this.longitude = value;
    }

}
