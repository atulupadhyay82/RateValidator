package com.thomsonreuters.extractvalidator.determination.dbconnection;


/**
 * A code that tells what category of error caused a {@link DatabaseCommunicationException}.
 *
 * @author MattM
 */
public enum ExceptionCode
{
	/**
	 * Code for a successful response.
	 */
	OK(200),

	/**
	 * Exception code raised when an entity is not found.
	 */
	NOT_FOUND(404),

	/**
	 * Exception code raised when trying to delete an object and there are child objects that can not be deleted.
	 */
	CHILD_OBJECTS_EXIST(409),

	/**
	 * Exception code for an unexpected error.
	 */
	INTERNAL_ERROR(500),

	/**
	 * Exception code for failed permissions.
	 */
	PERMISSION_DENIED(550);

	/**
	 * The integer value of the exception code.
	 */
	private final int _value;


	/**
	 * Construct an ExceptionCode using the given value for the code.
	 *
	 * @param value The value of the exception code.
	 */
	ExceptionCode(final int value)
	{
		_value = value;
	}


	/**
	 * Retrieves the value of the exception code.
	 *
	 * @return The value of the exception code.
	 */
	public int getValue()
	{
		return _value;
	}
}
