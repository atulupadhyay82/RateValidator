package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Holds the operating license.
 *
 * @author Raghunandan Krishnamurthy
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public final class UiOperatingLicenses
{
	/**
	 * Holds the assume buyer fully licensed flag.
	 */
	@NotNull(message = "AssumeBuyerFullyLicensed cannot be null.")
	private Boolean assumeBuyerFullyLicensed;

	/**
	 * Holds the list of buyer {@link UiOperatingLicense}.
	 */
	@Valid
	@NotNull
	@Size(max = 10, message = "Maximum buyer operating licenses allowed are 10.")
	private List<UiOperatingLicense> buyerOperatingLicenses;

	/**
	 * Holds the assume seller fully licensed flag.
	 */
	@NotNull(message = "AssumeSellerFullyLicensed cannot be null.")
	private Boolean assumeSellerFullyLicensed;

	/**
	 * Holds the list of seller {@link UiOperatingLicense}.
	 */
	@Valid
	@NotNull
	@Size(max = 10, message = "Maximum seller operating licenses allowed are 10.")
	private List<UiOperatingLicense> sellerOperatingLicenses;
}
