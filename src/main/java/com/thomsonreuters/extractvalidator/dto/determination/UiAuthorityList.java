package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import lombok.Data;


/**
 * A data transfer object representing an authority list.
 *
 * @author Neal Schultz
 */
@Data
public final class UiAuthorityList
{
	/**
	 * The list of authorities.
	 */
	private List<UiAuthorityDetail> authorities;
}
