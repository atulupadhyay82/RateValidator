package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.thomsonreuters.extractvalidator.util.ActivityCode;
import com.thomsonreuters.extractvalidator.util.IClientChangeType;


/**
 * The address is location specific attributes for a given jurisdiction and/or store.
 *
 * @author Eli Laudi
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Address implements IClientChangeType
{
	/**
	 * Numeric hash key associated with a address
	 */
	private String addressKey;

	/**
	 * Numeric hash key associated with a jurisdiction
	 */
	private String jurisdictionKey;

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
	 * Postal Range associated with the location
	 */
	private PostalRange postalRange;

	/**
	 * Postal code associated with the location
	 */
	private String postalCode;

	/**
	 * Geocode associated with the location
	 */
	private String geocode;

	/**
	 * Contains the action for the authority (I - insert, U - update, D - delete)
	 */
	private ActivityCode changeType;
}
