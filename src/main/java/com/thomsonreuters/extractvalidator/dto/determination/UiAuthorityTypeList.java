package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import lombok.Data;


/**
 * A data transfer object representing an Authority Types List.
 *
 * @author Neal Schultz
 */
@Data
public final class UiAuthorityTypeList
{
	/**
	 * The list of {@link UiAuthorityTypes} objects.
	 */
	private List<UiAuthorityTypes> authorityTypeList;
}
