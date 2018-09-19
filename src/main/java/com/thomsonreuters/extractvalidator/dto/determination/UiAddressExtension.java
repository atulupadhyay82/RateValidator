package com.thomsonreuters.extractvalidator.dto.determination;


import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds UI address extension information.
 *
 * @author Raghunandan Krishnamurthy
 */
@Data
public class UiAddressExtension extends UiBasicAddress
{
	/**
	 * Holds the facility ID.
	 */
	@Size(max = 100, message = "Maximum Size allowed for facility ID is 100.")
	private String facilityId;

	/**
	 * Flag to indicate if address is a irs registered facility.
	 */
	private Boolean irsRegisteredFacility;

	/**
	 * Flag to indicate if address is a bulk storage facility.
	 */
	private Boolean bulkStorageFacility;

	/**
	 * Holds the latitude.
	 */
	@DecimalMin(value = "-90", message = "Minimum allowed value for latitude will be -90.")
	@DecimalMax(value = "90", message = "Maximum allowed value for latitude will be 90.")
	private BigDecimal latitude;

	/**
	 * Holds the longitude.
	 */
	@DecimalMin(value = "-180", message = "Minimum allowed value for longitude will be -180.")
	@DecimalMax(value = "180", message = "Maximum allowed value for longitude will be 180.")
	private BigDecimal longitude;
}
