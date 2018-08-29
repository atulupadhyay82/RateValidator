package com.thomsonreuters.extractvalidator.util;


import com.thomsonreuters.extractvalidator.determination.dbconnection.ExceptionCode;


/**
 * Exception class intended to be thrown during test run to capture test framework failures.
 *
 * @author Matt Godsey
 */
public class ExtractValidatorException extends Exception
{
	/**
	 * Exception code that tells callers the category of error that caused the exception.
	 */
	private final ExceptionCode code;


	/**
	 * Construct an ExtractValidatorException with a message.
	 *
	 * @param message Message explaining exception.
	 */
	public ExtractValidatorException(final String message)
	{
		super(message);
		code = null;
	}


	/**
	 * Construct a ExtractValidatorException with a message and an exception code.
	 *
	 * @param message Message explaining exception.
	 * @param code The exception code.
	 */
	public ExtractValidatorException(final String message, final ExceptionCode code)
	{
		super(message);

		this.code = code;
	}


	/**
	 * Construct a ExtractValidatorException with a message and cause.
	 *
	 * @param message Message explaining exception.
	 * @param cause Original cause of exception.
	 */
	public ExtractValidatorException(final String message, final Throwable cause)
	{
		super(message, cause);

		code = cause instanceof ExtractValidatorException ? ((ExtractValidatorException) cause).getCode() : null;
	}


	/**
	 * Construct a ExtractValidatorException with a message, cause, and exception code.
	 *
	 * @param message Message explaining exception.
	 * @param cause Original cause of exception.
	 * @param code The exception code.
	 */
	public ExtractValidatorException(final String message, final Throwable cause, final ExceptionCode code)
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
