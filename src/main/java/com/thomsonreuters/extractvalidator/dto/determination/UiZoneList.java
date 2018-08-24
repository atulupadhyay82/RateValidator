package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import lombok.Data;


/**
 * A data transfer object representing a zone list.
 *
 * @author Neal Schultz
 */
@Data
public final class UiZoneList
{
	/**
	 * The zone level name.
	 */
	private String zoneLevelName;

	/**
	 * The list of zones.
	 */
	private List<UiZone> zones;
}
