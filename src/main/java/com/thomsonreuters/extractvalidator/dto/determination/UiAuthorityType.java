package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * A data transfer object representing an Authority Type.
 *
 * @author Neal Schultz
 */
@Data
public class UiAuthorityType
{
	/**
	 * The Authority Type id.
	 */
	private Long authorityTypeId;

	/**
	 * The Authority Type description.
	 */
	private String description;

	/**
	 * The Authority Type name.
	 */
	private String name;
}
