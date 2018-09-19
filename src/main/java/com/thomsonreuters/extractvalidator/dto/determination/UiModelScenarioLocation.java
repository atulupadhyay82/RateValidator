package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the model scenario location information.
 *
 * @author Jositha Parampil
 */
@Data
public final class UiModelScenarioLocation extends UiAddressExtension
{
	/**
	 * Holds the branch ID for the model scenario location.
	 */
	@Size(max = 25, message = "Maximum Size allowed for branch ID is 25.")
	private String branchId;

	/**
	 * Holds the city for the model scenario location.
	 */
	@Size(max = 50, message = "Maximum Size allowed for city is 50.")
	private String city;

	/**
	 * Holds the county for the model scenario location.
	 */
	@Size(max = 50, message = "Maximum Size allowed for county is 50.")
	private String county;

	/**
	 * Holds the district for the model scenario location.
	 */
	@Size(max = 50, message = "Maximum Size allowed for district is 50.")
	private String district;

	/**
	 * Holds the location type for the model scenario location.
	 */
	@NotNull(message = "Location type is required.")
	private String locationType;

	/**
	 * Holds the postal code for the model scenario location.
	 */
	@Size(max = 50, message = "Maximum Size allowed for postal code is 50.")
	private String postalCode;

	/**
	 * Holds the geo code for the model scenario location.
	 */
	@Size(max = 50, message = "Maximum Size allowed for geo code is 50.")
	private String geoCode;

	/**
	 * Holds the bonded value for the corresponding location type.
	 */
	private Boolean isBonded;
}
