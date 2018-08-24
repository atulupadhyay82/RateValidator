package com.thomsonreuters.extractvalidator.util;


import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Enumerates the possible values for grouping rule.
 *
 * @author Derek Christman
 */
public enum GroupingRule
{
	/**
	 * Indicates grouping by authority type.
	 */
	AUTHORITY_TYPE("authorityType"),

	/**
	 * Indicates grouping by authority.
	 */
	AUTHORITY("authority"),

	/**
	 * Indicates grouping by tax type.
	 */
	TAX_TYPE("taxType");

	/**
	 * The symbol used to represent a code in the database
	 */
	private final String dbName;


	/**
	 * Constructor for this enum incorporating database value for the grouping rule
	 *
	 * @param dbName the database value for this grouping rule
	 */
	GroupingRule(final String dbName)
	{
		this.dbName = dbName;
	}


	/**
	 * Getter to obtain the database value for a grouping rule
	 *
	 * @return the database value for the grouping rule
	 */
	@JsonValue
	public String getDbName()
	{
		return dbName;
	}


	/**
	 * Method for converting the value from the database to a Grouping Rule
	 *
	 * @param dbName the database value
	 *
	 * @return a grouping rule associated with the database value or null if not found
	 */
	public static GroupingRule getGroupingRuleForDbName(final String dbName)
	{
		GroupingRule result = null;

		final GroupingRule[] groupingRules = GroupingRule.values();

		for (final GroupingRule code : groupingRules)
		{
			if (code.getDbName().equals(dbName))
			{
				result = code;
				break;
			}
		}

		return result;
	}
}
