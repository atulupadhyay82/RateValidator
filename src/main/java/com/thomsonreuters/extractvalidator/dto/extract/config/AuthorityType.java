package com.thomsonreuters.extractvalidator.dto.extract.config;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * AuthorityType DTO.
 *
 * @author Eli Laudi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorityType
{
	/**
	 * the id of this authority type
	 */
	private Long extractRegionAuthTypeId;

	/**
	 * the id of the extract region this authority type is associated with
	 */
	private Long extractRegionId;

	/**
	 * indicates type of authority
	 */
	private String authorityType;
}
