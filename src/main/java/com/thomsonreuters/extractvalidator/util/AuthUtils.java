package com.thomsonreuters.extractvalidator.util;


import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;


/**
 * Authentication utilities and common code.
 *
 * @author Li Ho
 */
public final class AuthUtils
{
	/**
	 * Logger for this class.
	 */
	private static final Logger LOGGER = ESAPI.getLogger(AuthUtils.class);

	/**
	 * UDSLongToken authentication header.
	 */
	private static final String UDSLONGTOKEN_AUTHENTICATION_HEADER = "UDSLongToken";


	/**
	 * Private constructor for utility class
	 */
	private AuthUtils()
	{
	}


	/**
	 * Adds UDSLongToken prefix to credentials if necessary.
	 * The UdsLongTokenHandler strips out this prefix when extracting the token.
	 *
	 * @param credentials authentication token
	 *
	 * @return credentials prefixed with UDSLongToken
	 */
	public static String prefixUDSCredentials(final String credentials)
	{
		return credentials.startsWith(UDSLONGTOKEN_AUTHENTICATION_HEADER) ? credentials :
			   String.format("%s %s", UDSLONGTOKEN_AUTHENTICATION_HEADER, credentials);
	}
}
