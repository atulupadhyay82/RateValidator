package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the model scenario registration information.
 *
 * @author Jason (Po-sheng Wang)
 */
@Data
public final class UiModelScenarioRegistration
{
	/**
	 * Holds the registration ID.
	 */
	private Long registrationId;

	/**
	 * Holds the registration role.
	 */
	@Size(min = 1, max = 1, message = "Minimum and Maximum length of Registration Role is 1.")
	@Pattern(regexp = "[BSM]", message = "registration role value is not valid.")
	private String role;

	/**
	 * Holds the registration number.
	 */
	@Size(min = 1, max = 50, message = "Minimum and Maximum length of Registration Number is 1 and 50 respectively.")
	private String registrationNumber;

	/**
	 * A boolean to be set to remove the scenario registration.
	 */
	private boolean isDelete;

	/**
	 * Holds {@link UiEntityAudit} for audit information.
	 */
	private UiEntityAudit entityAudit;
}
