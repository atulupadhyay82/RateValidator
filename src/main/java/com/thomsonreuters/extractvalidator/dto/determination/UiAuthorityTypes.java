package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import lombok.Data;


/**
 * A data transfer object representing an Authority Type List.
 *
 * @author Neal Schultz
 */
@Data
public final class UiAuthorityTypes
{
	/**
	 * The list of {@code UiAuthorityType} objects.
	 */
	private List<UiAuthorityType> authorityTypes;
}
