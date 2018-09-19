package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * Holds the model scenario custom field information.
 *
 * @author Ethan Schulze
 */
@Data
public final class UiModelScenarioCustomField
{
	/**
	 * Holds the custom field name.
	 */
	private String fieldValue;

	/**
	 * Holds the custom field number.
	 */
	private Long fieldNumber;

	/**
	 * Holds the custom field type.
	 */
	private String fieldType;
}
