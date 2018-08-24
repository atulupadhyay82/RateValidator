package com.thomsonreuters.extractvalidator.dto.extract.config;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * ElementValue DTO.
 *
 * @author Eli Laudi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ElementValue
{
	/**
	 * database ID for the element value
	 */
	private Long extractElementValueId;

	/**
	 * database ID for the extract region this element value is associated with
	 */
	private Long extractRegionId;

	/**
	 * element for extract
	 */
	private String element;

	/**
	 * element value for extract
	 */
	private String elementValue;
}
