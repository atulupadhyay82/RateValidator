package com.thomsonreuters.extractvalidator.dto.content;


import lombok.Data;


/**
 * Represents a zone from Determination.
 *
 * @author Matt Godsey
 */
@Data
public final class ClientZone
{
	/**
	 * The name of the zone.
	 */
	private String name;

	/**
	 * The 2 character code of the zone.
	 */
	private String char2Code;

	/**
	 * The 3 character code of the zone.
	 */
	private String char3Code;
}