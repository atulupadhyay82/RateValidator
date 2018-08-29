package com.thomsonreuters.extractvalidator.determination.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Locatin Description.
 *
 * @author Matt Godsey
 */
@Entity
@Table(name = "TB_LOCATIONS")
public final class Location
{
	private Long locationId;

	/**
	 * The merchant ID of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long merchantId;

	/**
	 * The name of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String name;

	/**
	 * The Retrieves the POO flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String pooFlag;

	/**
	 * The Retrieves the POA flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String poaFlag;

	/**
	 * The Retrieves the ship from flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String shipFromFlag;

	/**
	 * The Retrieves the POO usage flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String pooUsageFlag;

	/**
	 * The Retrieves the POA usage flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String poaUsageFlag;

	/**
	 * The Retrieves the ship from usage flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String shipFromUsageFlag;

	/**
	 * The Retrieves the active state of the entity
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String active;

	/**
	 * The Retrieves the notes of the entity
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String notes;

	/**
	 * The Retrieves the ship to flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String shipToFlag;

	/**
	 * The Retrieves the ship to usage flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String shipToUsageFlag;

	/**
	 * The Retrieves the bill to flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String billToFlag;

	/**
	 * The Retrieves the supply flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String supplyFlag;

	/**
	 * The Retrieves the middleman flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String middlemanFlag;

	/**
	 * The Retrieves the bill to usage flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String billToUsageFlag;

	/**
	 * The Retrieves the supply usage flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String supplyUsageFlag;

	/**
	 * The Retrieves the middleman usage flag of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String middlemanUsageFlag;

	/**
	 * The Retrieves the country of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String country;

	/**
	 * The Retrieves the district of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String district;

	/**
	 * The Retrieves the province of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String province;

	/**
	 * The Retrieves the state of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String state;

	/**
	 * The Retrieves the county of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String county;

	/**
	 * The Retrieves the city of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String city;

	/**
	 * The Retrieves the post code of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String postCode;

	/**
	 * The Retrieves the geo code of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String geoCode;


	/**
	 * Constructs a new {@code Location}.
	 */
	public Location()
	{
	}


	@Id
	@Column(name = "LOCATION_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getLocationId()
	{
		return locationId;
	}


	public void setLocationId(final Long locationId)
	{
		this.locationId = locationId;
	}


	@Column(name = "MERCHANT_ID", nullable = false, precision = 10, scale = 0)
	public Long getMerchantId()
	{
		return merchantId;
	}


	public void setMerchantId(final Long merchantId)
	{
		this.merchantId = merchantId;
	}


	@Column(name = "NAME", nullable = false, length = 30)
	public String getName()
	{
		return name;
	}


	public void setName(final String name)
	{
		this.name = name;
	}


	@Column(name = "POO_FLAG", length = 1)
	public String getPooFlag()
	{
		return pooFlag;
	}


	public void setPooFlag(final String pooFlag)
	{
		this.pooFlag = pooFlag;
	}


	@Column(name = "POA_FLAG", length = 1)
	public String getPoaFlag()
	{
		return poaFlag;
	}


	public void setPoaFlag(final String poaFlag)
	{
		this.poaFlag = poaFlag;
	}


	@Column(name = "SHIP_FROM_FLAG", length = 1)
	public String getShipFromFlag()
	{
		return shipFromFlag;
	}


	public void setShipFromFlag(final String shipFromFlag)
	{
		this.shipFromFlag = shipFromFlag;
	}


	@Column(name = "POO_USAGE_FLAG", length = 1)
	public String getPooUsageFlag()
	{
		return pooUsageFlag;
	}


	public void setPooUsageFlag(final String pooUsageFlag)
	{
		this.pooUsageFlag = pooUsageFlag;
	}


	@Column(name = "POA_USAGE_FLAG", length = 1)
	public String getPoaUsageFlag()
	{
		return poaUsageFlag;
	}


	public void setPoaUsageFlag(final String poaUsageFlag)
	{
		this.poaUsageFlag = poaUsageFlag;
	}


	@Column(name = "SHIP_FROM_USAGE_FLAG", length = 1)
	public String getShipFromUsageFlag()
	{
		return shipFromUsageFlag;
	}


	public void setShipFromUsageFlag(final String shipFromUsageFlag)
	{
		this.shipFromUsageFlag = shipFromUsageFlag;
	}


	@Column(name = "ACTIVE", length = 1)
	public String getActive()
	{
		return active;
	}


	public void setActive(final String active)
	{
		this.active = active;
	}


	@Column(name = "NOTES", length = 240)
	public String getNotes()
	{
		return notes;
	}


	public void setNotes(final String notes)
	{
		this.notes = notes;
	}


	@Column(name = "SHIP_TO_FLAG", length = 1)
	public String getShipToFlag()
	{
		return shipToFlag;
	}


	public void setShipToFlag(final String shipToFlag)
	{
		this.shipToFlag = shipToFlag;
	}


	@Column(name = "SHIP_TO_USAGE_FLAG", length = 1)
	public String getShipToUsageFlag()
	{
		return shipToUsageFlag;
	}


	public void setShipToUsageFlag(final String shipToUsageFlag)
	{
		this.shipToUsageFlag = shipToUsageFlag;
	}


	@Column(name = "BILL_TO_FLAG", length = 1)
	public String getBillToFlag()
	{
		return billToFlag;
	}


	public void setBillToFlag(final String billToFlag)
	{
		this.billToFlag = billToFlag;
	}


	@Column(name = "SUPPLY_FLAG", length = 1)
	public String getSupplyFlag()
	{
		return supplyFlag;
	}


	public void setSupplyFlag(final String supplyFlag)
	{
		this.supplyFlag = supplyFlag;
	}


	@Column(name = "MIDDLEMAN_FLAG", length = 1)
	public String getMiddlemanFlag()
	{
		return middlemanFlag;
	}


	public void setMiddlemanFlag(final String middlemanFlag)
	{
		this.middlemanFlag = middlemanFlag;
	}


	@Column(name = "BILL_TO_USAGE_FLAG", length = 1)
	public String getBillToUsageFlag()
	{
		return billToUsageFlag;
	}


	public void setBillToUsageFlag(final String billToUsageFlag)
	{
		this.billToUsageFlag = billToUsageFlag;
	}


	@Column(name = "SUPPLY_USAGE_FLAG", length = 1)
	public String getSupplyUsageFlag()
	{
		return supplyUsageFlag;
	}


	public void setSupplyUsageFlag(final String supplyUsageFlag)
	{
		this.supplyUsageFlag = supplyUsageFlag;
	}


	@Column(name = "MIDDLEMAN_USAGE_FLAG", length = 1)
	public String getMiddlemanUsageFlag()
	{
		return middlemanUsageFlag;
	}


	public void setMiddlemanUsageFlag(final String middlemanUsageFlag)
	{
		this.middlemanUsageFlag = middlemanUsageFlag;
	}


	@Column(name = "COUNTRY", length = 3)
	public String getCountry()
	{
		return country;
	}


	public void setCountry(final String country)
	{
		this.country = country;
	}


	@Column(name = "DISTRICT", length = 50)
	public String getDistrict()
	{
		return district;
	}


	public void setDistrict(final String district)
	{
		this.district = district;
	}


	@Column(name = "PROVINCE", length = 50)
	public String getProvince()
	{
		return province;
	}


	public void setProvince(final String province)
	{
		this.province = province;
	}


	@Column(name = "STATE", length = 50)
	public String getState()
	{
		return state;
	}


	public void setState(final String state)
	{
		this.state = state;
	}


	@Column(name = "COUNTY", length = 50)
	public String getCounty()
	{
		return county;
	}


	public void setCounty(final String county)
	{
		this.county = county;
	}


	@Column(name = "CITY", length = 50)
	public String getCity()
	{
		return city;
	}


	public void setCity(final String city)
	{
		this.city = city;
	}


	@Column(name = "POSTCODE", length = 50)
	public String getPostCode()
	{
		return postCode;
	}


	public void setPostCode(final String postcode)
	{
		postCode = postcode;
	}


	@Column(name = "GEOCODE", length = 50)
	public String getGeoCode()
	{
		return geoCode;
	}


	public void setGeoCode(final String geocode)
	{
		geoCode = geocode;
	}
}
