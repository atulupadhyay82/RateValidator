package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * Represents a Lookup.
 *
 * @author Neal Schultz
 */
@Data
public final class UiLookup
{
	/**
	 * The Lookup code.
	 */
	private String code;

	/**
	 * The Lookup description.
	 */
	private String description;

	/**
	 * The Lookup id.
	 */
	private Long id;

	/**
	 * The Lookup status.
	 */
	private String status;
}
