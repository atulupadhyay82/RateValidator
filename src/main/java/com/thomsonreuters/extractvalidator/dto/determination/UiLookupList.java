package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import lombok.Data;


/**
 * Represents a lookup list for a given code group.
 *
 * @author Neal Schultz
 */
@Data
public final class UiLookupList
{
	/**
	 * The code group for this lookup list.
	 */
	private String codeGroup;

	/**
	 * The list of {@link UiLookup}.
	 */
	private List<UiLookup> lookups;
}
