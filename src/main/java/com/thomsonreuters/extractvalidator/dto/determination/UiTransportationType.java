package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
 * Holds the transportation type information.
 *
 * @author Ramkrishna Sharma
 */
@Data
@ApiModel(value = "UiTransportationType", description = "Transportation Type details.")
public class UiTransportationType
{
	/**
	 * Holds the external token for the transportation type.
	 */
	private String uUId;

	/**
	 * Holds the transportation type name.
	 */
	@NotBlank(message = "Transportation type name is required.")
	@Size(min = 1, max = 100, message = "Minimum and Maximum length of transportation type name is 1 and 100 respectively.")
	private String name;

	/**
	 * Holds the transportation type description.
	 */
	@Size(max = 300, message = "Transportation type description must not exceed 300 characters.")
	private String description;

	/**
	 * Holds the bulk flag.
	 */
	@NotBlank(message = "Transportation type bulk flag is required.")
	@Pattern(regexp = "[YN]", message = "Transportation type bulk flag value is not valid. It must be either Y or N.")
	private String isBulk;

	/**
	 * Holds the transportation type start date.
	 */
	@NotBlank(message = "Transportation type start date is required.")
	private String startDate;

	/**
	 * Holds the transportation type end date.
	 */
	private String endDate;
}
