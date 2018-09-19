package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the model scenario tax type information.
 *
 * @author KarthickMoorthy
 */
@Data
public final class UiModelScenarioTaxType
{
	/**
	 * Holds the all tax type.
	 */
	@Size(max = 20, message = "Maximum Size allowed for all tax type is 20.")
	private String allTaxType;

	/**
	 * Holds the country tax type.
	 */
	@Size(max = 20, message = "Maximum Size allowed for country tax type is 20.")
	private String countryTaxType;

	/**
	 * Holds the province tax type.
	 */
	@Size(max = 20, message = "Maximum Size allowed for province tax type is 20.")
	private String provinceTaxType;

	/**
	 * Holds the state tax type.
	 */
	@Size(max = 20, message = "Maximum Size allowed for state tax type is 20.")
	private String stateTaxType;

	/**
	 * Holds the county tax type.
	 */
	@Size(max = 20, message = "Maximum Size allowed for county tax type is 20.")
	private String countyTaxType;

	/**
	 * Holds the city tax type.
	 */
	@Size(max = 20, message = "Maximum Size allowed for city tax type is 20.")
	private String cityTaxType;

	/**
	 * Holds the district tax type.
	 */
	@Size(max = 20, message = "Maximum Size allowed for district tax type is 20.")
	private String districtTaxType;

	/**
	 * Holds the postal tax type.
	 */
	@Size(max = 20, message = "Maximum Size allowed for postal code tax type is 20.")
	private String postalCodeTaxType;

	/**
	 * Holds the geo code tax type.
	 */
	@Size(max = 20, message = "Maximum Size allowed for geo code tax type is 20.")
	private String geoCodeTaxType;
}
