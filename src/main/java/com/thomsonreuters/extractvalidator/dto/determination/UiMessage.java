package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * Holds the message details.
 *
 * @author NagarajDiveela
 */
@Data
public final class UiMessage
{
	/**
	 * Holds code.
	 */
	private String code;

	/**
	 * Holds message.
	 */
	private String message;

	/**
	 * Holds severity.
	 */
	private String severity;

	/**
	 * Holds error location.
	 */
	private String errorLocation;

	/**
	 * Holds category.
	 */
	private String category;
}
