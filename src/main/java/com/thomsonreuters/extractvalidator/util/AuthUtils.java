package com.thomsonreuters.extractvalidator.util;


import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.security.authentication.InsufficientAuthenticationException;

import com.trta.lonestar.backend.spring.security.LoneStarUser;


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
	 * The property holding Lone Star 2 user name.
	 */
	private static final String LS2_USER_UID = "uid";


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


	/**
	 * Finds the logged in user and returns the usrename
	 *
	 * @param lsUser LoneStarUser from Authentication. Must not be {@code null}.
	 *
	 * @return String username of user
	 *
	 * @throws InsufficientAuthenticationException thrown when username/user is not found.
	 */
	public static String processUser(final LoneStarUser lsUser)
	{
		final String username;

		try
		{
			username = extractUserProperties(lsUser).getProperty(LS2_USER_UID);
		}
		catch (final IOException e)
		{
			// In an ideal situation this should never happen.
			LOGGER.warning(Logger.EVENT_FAILURE, "Exception while parsing username received from LoneStar.", e);
			throw new InsufficientAuthenticationException("User doesn't exist.", e);
		}

		return username;
	}


	/**
	 * Extract user details from LoneStarUser and sets it.
	 *
	 * @param lsUser LoneStarUser. Must not be {@code null}.
	 *
	 * @return Properties extracted from {@link LoneStarUser} object. Will never be {@code null}.
	 *
	 * @throws IOException, if it has problem reading properties.
	 */
	private static Properties extractUserProperties(final LoneStarUser lsUser) throws IOException
	{
		final Properties properties = new Properties();

		if (null != lsUser && StringUtils.isNotEmpty(lsUser.getUsername()))
		{
			// LoneStar gives username in this format => uid=gnanavel,o=OneSource,ou=003
			final String userDetails = lsUser.getUsername();
			final String propertiesFormat = userDetails.replaceAll(",", "\n");

			properties.load(new StringReader(propertiesFormat));
		}

		return properties;
	}
}
