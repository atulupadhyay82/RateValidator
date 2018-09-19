package com.thomsonreuters.extractvalidator.dto.determination;


import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
 * Holds the operating license options information for a merchant.
 *
 * @author Sheetal Reddy
 */
@Data
@ApiModel(value = "UiOperatingLicenseOptions", description = "Operating license options information.")
public final class UiOperatingLicenseOptions
{
	/**
	 * Indicates whether or not to evaluate oil/gas operating licenses for the customers and vendors of this merchant.
	 */
	private boolean evaluateOperatingLicenses;

	/**
	 * Indicates whether or not to evaluate oil/gas operating licenses as true for the customers of this merchant.
	 */
	private boolean assumeCustomersFullyOpLicensed;

	/**
	 * Indicates whether or not to evaluate oil/gas operating licenses as true for the vendors of this merchant.
	 */
	private boolean assumeVendorsFullyOpLicensed;
}
