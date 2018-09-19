package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds UI basic address information.
 *
 * @author Velayoudame Mouttoucomarasamy
 */
@Data
public class UiBasicAddress
{
	/**
	 * Holds the country.
	 */
	@NotNull(message = "Country is required.")
	@Size(min = 2, max = 3, message = "Minimum and Maximum length allowed for country is 2 and 3 respectively.")
	private String country;

	/**
	 * Holds the province.
	 */
	@Size(max = 50, message = "Maximum Size allowed for province is 50.")
	private String province;

	/**
	 * Holds the state.
	 */
	@Size(max = 50, message = "Maximum Size allowed for state is 50.")
	private String state;
}
