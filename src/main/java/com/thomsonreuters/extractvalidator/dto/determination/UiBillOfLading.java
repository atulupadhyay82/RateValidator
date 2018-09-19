package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Holds the bill of lading information.
 *
 * @author Vivek Singh
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public final class UiBillOfLading
{
	/**
	 * Holds bill of lading number.
	 */
	@NotBlank(message = "Bill of lading number is required.")
	@Size(min = 1, max = 100, message = "Minimum and Maximum size of bill of lading number are 1 and 100 respectively.")
	private String number;

	/**
	 * Holds bill of lading dateTime.
	 */
	@NotBlank(message = "Bill of lading datetime is required.")
	@Size(max = 19, message = "Invalid bill of lading datetime.")
	private String dateTime;
}
