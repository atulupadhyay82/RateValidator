package com.thomsonreuters.extractvalidator.util;


import com.fasterxml.jackson.annotation.JsonValue;


/**
 * ActivityCode Description.
 *
 * @author Derek Christman
 */
public enum ActivityCode
{
	/**
	 * Code indicating the row is a new row.
	 */
	INSERT("I"),

	/**
	 * Code indicating the row is an updated row.
	 */
	UPDATE("U"),

	/**
	 * Code indicating this is a deleted row.
	 */
	DELETE("D");

	/**
	 * The symbol used to represent a code in the database
	 */
	private final String shortName;


	/**
	 * Constructor for this enum incorporating short name for the code
	 *
	 * @param shortName the database value for this Activity Code
	 */
	ActivityCode(final String shortName)
	{
		this.shortName = shortName;
	}


	/**
	 * @return the short name for this Activity code
	 */
	@JsonValue
	public String getShortName()
	{
		return shortName;
	}


	/**
	 * Method for converting the short name value from the database to an Activity Code
	 *
	 * @param shortName the short name value
	 *
	 * @return an activity code associated with the short name or null if not found
	 */
	public static ActivityCode getActivityCodeForShortName(final String shortName)
	{
		ActivityCode result = null;

		final ActivityCode[] activityCodes = ActivityCode.values();

		for (final ActivityCode code : activityCodes)
		{
			if (code.getShortName().equals(shortName))
			{
				result = code;
				break;
			}
		}

		return result;
	}
}
