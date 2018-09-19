package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
public final class UiOperatingLicense
{
	/**
	 * Holds operating license ID.
	 */
	@Null(message = "Operating license number should be null.")
	private Long id;

	/**
	 * Holds operating license number.
	 */
	@NotNull(message = "Operating license number is required.")
	@Size(min = 1, max = 100, message = "Minimum and Maximum size of Operating license number are 1 and 100 respectively.")
	private String licenseNumber;

	/**
	 * Holds operating license type.
	 */
	@NotNull(message = "Operating license type is required.")
	@Size(min = 1, max = 100, message = "Minimum and Maximum size of Operating license type are 1 and 100 respectively.")
	private String licenseType;

	/**
	 * Holds operating license role.
	 */
	@NotNull(message = "Operating license role is required.")
	@Size(min = 1, max = 100, message = "Minimum and Maximum size of Operating license role are 1 and 10 respectively.")
	private String role;

	/**
	 * Holds {@link UiEntityAudit} for audit information.
	 */
	private UiEntityAudit entityAudit;
}
