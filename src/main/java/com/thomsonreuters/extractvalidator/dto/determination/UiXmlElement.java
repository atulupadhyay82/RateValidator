package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * A data transfer object representing an xml element.
 *
 * @author Neal Schultz
 */
@Data
public final class UiXmlElement
{
	/**
	 * The parent xml element ID.
	 */
	private Long parentXmlElementId;

	/**
	 * The element.
	 */
	private String element;

	/**
	 * The tag name.
	 */
	private String tagName;

	/**
	 * The leaf node.
	 */
	private String leafNode;

	/**
	 * The error tag.
	 */
	private String errorTag;

	/**
	 * The in and out flag.
	 */
	private String inOutFlag;

	/**
	 * The data type.
	 */
	private String dataType;

	/**
	 * The obsolete as of.
	 */
	private String obsoleteAsOf;

	/**
	 * The xml element ID.
	 */
	private Long xmlElementId;
}
