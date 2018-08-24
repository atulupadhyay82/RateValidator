package com.thomsonreuters.extractvalidator.dto.extract.config;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * DispException DTO.
 *
 * @author Eli Laudi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DispException
{
	/**
	 * database ID of the extract disp exception
	 */
	private Long extractDispExceptionId;

	/**
	 * database ID of the extract region this disp exception is associated with
	 */
	private Long extractRegionId;

	/**
	 * key which references the authority associated with the exception
	 */
	private String authorityKey;

	/**
	 * name for the disp exception
	 */
	private String displayName;
}
