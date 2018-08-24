package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * A data transfer object representing a zone.
 *
 * @author Neal Schultz
 */
@Data
public final class UiZone
{
	/**
	 * The zone id.
	 */
	private Long zoneId;

	/**
	 * The name of the zone.
	 */
	private String name;
}
