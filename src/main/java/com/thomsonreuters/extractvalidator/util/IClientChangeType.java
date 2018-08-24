package com.thomsonreuters.extractvalidator.util;


/**
 * Interface defines interaction with all extract entities for manipulating change type based on
 * the type of extract being requested.
 *
 * @author Derek Christman
 */
public interface IClientChangeType
{
	/**
	 * Returns the content extract change type value for a given entity.
	 *
	 * @return the change type value found
	 */
	ActivityCode getChangeType();


	/**
	 * Sets the content extract change type value for a given entity.
	 *
	 * @param changeType the change type to set the value to
	 */
	void setChangeType(ActivityCode changeType);


	/**
	 * Adjusts the change type of a content extract entity if necessary to comply with the rules:
	 *
	 * 1. A full extract should return nothing but the INSERT change type
	 * 2. A differential extract should only return either INSERT or DELETE change type for each record
	 *
	 * @param loadMethod the type of load requested by the CE client
	 */
	default void adjustChangeType(final LoadMethod loadMethod)
	{
		if (loadMethod == LoadMethod.FULL)
		{
			setChangeType(ActivityCode.INSERT);
		}
		else
		{
			if (getChangeType() != ActivityCode.DELETE)
			{
				setChangeType(ActivityCode.INSERT);
			}
		}
	}
}
