package com.thomsonreuters.extractvalidator.determination.dbconnection;


/**
 * Exception class intended to be thrown by the hibernate session management layer.
 *
 * @author Matt Godsey
 */
public class DatabaseCommunicationException extends Exception
{
	/**
	 * Exception code that tells callers the category of error that caused the exception.
	 */
	private final ExceptionCode code;


	/**
	 * Construct a DatabaseCommunicationException with a message.
	 *
	 * @param message Message explaining exception.
	 */
	public DatabaseCommunicationException(final String message)
	{
		super(message);
		code = null;
	}


	/**
	 * Construct a DatabaseCommunicationException with a message and an exception code.
	 *
	 * @param message Message explaining exception.
	 * @param code The exception code.
	 */
	public DatabaseCommunicationException(final String message, final ExceptionCode code)
	{
		super(message);

		this.code = code;
	}


	/**
	 * Construct a DatabaseCommunicationException with a message and cause.
	 *
	 * @param message Message explaining exception.
	 * @param cause Original cause of exception.
	 */
	public DatabaseCommunicationException(final String message, final Throwable cause)
	{
		super(message, cause);

		code = cause instanceof DatabaseCommunicationException ? ((DatabaseCommunicationException) cause).getCode() : null;
	}


	/**
	 * Construct a DatabaseCommunicationException with a message, cause, and exception code.
	 *
	 * @param message Message explaining exception.
	 * @param cause Original cause of exception.
	 * @param code The exception code.
	 */
	public DatabaseCommunicationException(final String message, final Throwable cause, final ExceptionCode code)
	{
		super(message, cause);

		this.code = code;
	}


	/**
	 * Retrieves the exception code for this message.
	 *
	 * @return The exception code for this message.
	 */
	public final ExceptionCode getCode()
	{
		return code;
	}
}
