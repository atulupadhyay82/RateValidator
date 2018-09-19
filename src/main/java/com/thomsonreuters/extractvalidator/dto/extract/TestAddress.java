package com.thomsonreuters.extractvalidator.dto.extract;


import lombok.Data;


/**
 * The address data used for the model scenario calculation.
 *
 * @author Matt Godsey
 */
@Data
public final class TestAddress
{
	/**
	 * Country associated with the location
	 */
	private String country;

	/**
	 * State associated with the location
	 */
	private String state;

	/**
	 * Province associated with the location
	 */
	private String province;

	/**
	 * County associated with the location
	 */
	private String county;

	/**
	 * District associated with the location
	 */
	private String district;

	/**
	 * City associated with the location
	 */
	private String city;

	/**
	 * Postal code associated with the location
	 */
	private String postalCode;

	/**
	 * Geocode associated with the location
	 */
	private String geocode;
}
