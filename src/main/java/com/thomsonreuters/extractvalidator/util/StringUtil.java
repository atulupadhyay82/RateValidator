package com.thomsonreuters.extractvalidator.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


/**
 * Utility class containing frequently used String manipulation routines.
 *
 * @author MattM
 */
public final class StringUtil
{
	/**
	 * OS specific line separator.
	 */
	public static final String LINE_SEP = System.getProperty("line.separator");

	/**
	 * Dot (period) as a string for insertion within string literals.
	 */
	public static final String DOT_STRING = ".";

	/**
	 * References the constant for an empty string.
	 */
	public static final String EMPTY = "";

	/**
	 * References the constant for a blank space.  This is required by the SOAP service to allow us to clear out contact fields on the customer.
	 */
	public static final String EMPTY_VALUE = " ";

	/**
	 * Used as the first characters of a getter method name.
	 */
	public static final String GET = "get";

	/**
	 * Expose constant N (No) as per legacy software.
	 */
	public static final String NO = "N";

	/**
	 * Quote sign escaped for insertion within a string literal.
	 */
	public static final String QUOTE_STRING = "\"";

	/**
	 * Underscore used as a single character when creating string literals.
	 */
	public static final String UNDERSCORE = "_";

	/**
	 * Expose constant Y (Yes) as per legacy software.
	 */
	public static final String YES = "Y";


	/**
	 * Prevent construction of utility class.
	 */
	private StringUtil()
	{
	}


	/**
	 * Removes newline characters from a string.
	 *
	 * @param input Input string.
	 *
	 * @return The input string with new line characters removed.
	 */
	public static String stripNewLines(final String input)
	{
		return input.replace("\r", "").replace("\n", "");
	}


	/**
	 * Load a resource from the class loader and return it's contents as a
	 * String.
	 *
	 * @param fileName The resource to load.
	 *
	 * @return String The contents of the resource as a String.
	 *
	 * @throws IOException If the file specified can not be located.
	 */
	public static String getResourceAsString(final String fileName) throws IOException
	{
		final ClassLoader loader = StringUtil.class.getClassLoader();
		final InputStream inputStream = loader.getResourceAsStream(fileName);

		if (inputStream == null)
		{
			throw new IOException("The file '" + fileName + "' could not be found.");
		}

		final String s = getInputStreamAsString(inputStream);
		inputStream.close();
		return s;
	}


	/**
	 * Read input from an input stream and return the contents as a String.
	 *
	 * @param inputStream The stream to read.
	 *
	 * @return String The entire contents of the stream as a String.
	 *
	 * @throws IOException On error reading or closing the input stream.
	 */
	private static String getInputStreamAsString(final InputStream inputStream) throws IOException
	{
		final InputStreamReader isr = new InputStreamReader(inputStream);
		final BufferedReader inReader = new BufferedReader(isr);
		final StringBuilder buf = new StringBuilder();
		String line;

		while ((line = inReader.readLine()) != null)
		{
			buf.append(line);
		}
		inReader.close();

		return buf.toString();
	}


	/**
	 * Return text after last occurrence of '.'.
	 *
	 * @param value The input text.
	 *
	 * @return The part of the input String after the last occurrence of '.'. If '.' does not occur in the String,
	 * returns the input String. If the input String is {@code null}, returns the empty String.
	 */
	public static String getLastPartOfName(final String value)
	{
		final String lastPart;

		if (value == null)
		{
			lastPart = "";
		}
		else
		{
			final int pos = value.lastIndexOf('.');

			lastPart = pos >= 0 ? value.substring(pos + 1) : value;
		}

		return lastPart;
	}


	/**
	 * Whether the string is null/empty or not.
	 *
	 * @param s The String to evaluate.
	 *
	 * @return {@code true} if the String is either null or empty.
	 */
	public static boolean isNullOrEmpty(final String s)
	{
		return s == null || s.isEmpty();
	}


	/**
	 * Whether the {@code List} is null/empty or not.
	 *
	 * @param list The List to evaluate.
	 *
	 * @return {@code true} if the List is either null or empty.
	 */
	public static boolean isNullOrEmpty(final List list)
	{
		return list == null || list.isEmpty();
	}


	/**
	 * Convert a string with a value of Y or N to a Boolean.
	 *
	 * @param valueToConvert The string to evaluate.
	 *
	 * @return A Boolean derived from the value passed in.
	 */
	public static Boolean convertFlag(final String valueToConvert)
	{
		return valueToConvert == null ? null : YES.equalsIgnoreCase(valueToConvert);
	}


	/**
	 * Convert a Boolean to a value of Y or N.
	 *
	 * @param valueToConvert The Boolean to evaluate.
	 *
	 * @return A String derived from the value passed in.
	 */
	public static String convertBoolean(final Boolean valueToConvert)
	{
		final String result;

		if (valueToConvert == null)
		{
			result = null;
		}
		else
		{
			result = valueToConvert ? YES : NO;
		}

		return result;
	}


	/**
	 * Parse a string into Long.
	 *
	 * @param value String to be parsed.
	 *
	 * @return {@link Long} value or {@code null} if provided string is {@code null} or cannot be converted to {@link Long}.
	 */
	public static Long parseLong(final String value)
	{
		Long parsed;

		try
		{
			parsed = (value != null) ? Long.valueOf(value) : null;
		}
		catch (final NumberFormatException nfe)
		{
			parsed = null;
		}

		return parsed;
	}


	/**
	 * Convert string representing numerical IDs into {@code Long} values.
	 *
	 * @param stringIds String IDs to be parsed.
	 *
	 * @return List of numerical IDs.
	 */
	public static List<Long> convertToNumericalIDs(final List<String> stringIds)
	{
		final List<Long> ids = new LinkedList<>();

		if (!isNullOrEmpty(stringIds))
		{
			for (final String id : stringIds)
			{
				ids.add(Long.valueOf(id));
			}
		}

		return ids;
	}


	/**
	 * Convert Long IDs into representing string values.
	 *
	 * @param longIds Long IDs to be parsed.
	 *
	 * @return List of string IDs.
	 */
	public static List<String> convertToStringIDs(final List<Long> longIds)
	{
		final List<String> ids = new LinkedList<>();

		if (!isNullOrEmpty(longIds))
		{
			for (final Long id : longIds)
			{
				ids.add(String.valueOf(id));
			}
		}

		return ids;
	}


	/**
	 * Compares two strings in a null safe manner.
	 *
	 * @param str1 First string.
	 * @param str2 Second string.
	 *
	 * @return {@code true} If the two strings are equal.
	 */
	public static boolean nullSafeCompare(final String str1, final String str2)
	{
		return isNullOrEmpty(str1) ? isNullOrEmpty(str2) : str1.equals(str2);
	}


	/**
	 * Given the field name, return the corresponding getter name.
	 *
	 * @param fieldName The field name.
	 *
	 * @return The corresponding getter name for the field.
	 */
	public static String getGetterNameFromFieldName(final String fieldName)
	{
		final StringBuilder getterName = new StringBuilder();

		// Remove the leading "_" character.
		final String field = fieldName.charAt(0) == '_' ? fieldName.substring(1) : fieldName;

		getterName.append(GET).append(Character.toUpperCase(field.charAt(0))).append(field.substring(1));

		return getterName.toString();
	}


	/**
	 * Convert a string into camel-case, assuming that words are separated by blank spaces.
	 *
	 * @param target The phrase that is being converted to camel-case.
	 *
	 * @return The same target string but reformatted into camel-case.
	 */
	public static String toCamelCase(final String target)
	{
		return StringUtil.toCamelCase(target, " ");
	}


	/**
	 * Convert a string into camel-case, specifying the delimeter (e.g. a blank or a dash).
	 *
	 * @param target The string to be converted into camel-case.
	 * @param delimeter The delimeter that demarcates different words in the string, to be capitalized except for the first word.
	 *
	 * @return The transformed target string in camel-case format.
	 */
	public static String toCamelCase(final String target, final String delimeter)
	{
		final StringBuilder camelCaseResult = new StringBuilder();

		final String[] wordsArray = target.split(delimeter);

		// First word is always lower case, remaining are initial caps only.
		camelCaseResult.append(wordsArray[0].toLowerCase());

		for (int i = 1; i < wordsArray.length; i++)
		{
			camelCaseResult.append(wordsArray[i].substring(0,1).toUpperCase());
			camelCaseResult.append(wordsArray[i].substring(1).toLowerCase());
		}

		return camelCaseResult.toString();
	}


	/**
	 * Assuming the target string is already in camel case notation, convert to use underscores between words.
	 *
	 * @param target A camel-case notation phrase, e.g. "helloWorld".
	 *
	 * @return A snake-case notation phrase, e.g. "hello_world". Note that this is modified snake case, all caps are lowered.
	 */
	public static String toSnakeCase(final String target)
	{
		final StringBuilder snakeCaseResult = new StringBuilder();

		// Do not use regex because we need to insert underscores, not remove them.
		// Instead, walk through the string and look for digits and/or capital letters.
		int lastWordIndex = 0;

		for (int index = 0; index < target.length(); index++)
		{
			if (Character.isUpperCase(target.charAt(index)) || Character.isDigit(target.charAt(index)))
			{
				snakeCaseResult.append(target.substring(lastWordIndex, index));
				snakeCaseResult.append(UNDERSCORE);
				lastWordIndex = index;
			}
		}
		snakeCaseResult.append(target.substring(lastWordIndex, target.length()));

		return snakeCaseResult.toString();
	}
}
