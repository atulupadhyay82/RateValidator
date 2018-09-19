package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.NotNull;

import lombok.Data;


/**
 * Holds the model scenario license information.
 *
 * @author Bala Yaddanapudi
 */
@Data
public final class UiModelScenarioLicense
{
	/**
	 * Holds the license number.
	 */
	@NotNull(message = "License Number is required.")
	private String licenseNumber;

	/**
	 * Holds the license type.
	 */
	@NotNull(message = "License Type is required.")
	private String licenseType;
}
