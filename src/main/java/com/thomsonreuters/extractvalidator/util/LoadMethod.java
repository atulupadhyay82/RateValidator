package com.thomsonreuters.extractvalidator.util;


/**
 * A URL parameter provided by the client to indicate what type or method of a load is desired
 * from the content extract REST service.
 *
 * @author Derek Christman
 */
public enum LoadMethod
{
	/**
	 * Indicates a full content extract is desired.
	 */
	FULL,

	/**
	 * Indicates only a differential content extract desired.
	 */
	UPDATE
}