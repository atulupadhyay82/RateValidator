package com.thomsonreuters.extractvalidator.dto.content;


import java.util.List;

import lombok.Data;


/**
 * Represents a list of client zones in a web services operation.
 *
 * @author Matt Godsey
 */
@Data
public final class ClientZoneList
{
	/**
	 * The zones.
	 */
	private List<ClientZone> zones;
}
