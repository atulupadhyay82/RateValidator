package com.thomsonreuters.config.Security;


import javax.servlet.Filter;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.trta.lonestar.backend.core.uds.LoneStarSecurityServiceProxy;
import com.trta.lonestar.backend.core.uds.LoneStarSecurityServiceProxyImpl;
import com.trta.lonestar.backend.spring.security.LoneStarAuthenticationFilter;
import com.trta.lonestar.backend.spring.security.LoneStarTokenAuthenticationProvider;
import com.trta.lonestar.backend.spring.security.TokenService;
import com.trta.lonestar.backend.spring.security.uds.UdsLongTokenHandler;
import com.trta.lonestar.backend.spring.security.uds.UdsLongTokenPreAuthenticatedAuthenticationToken;
import com.trta.lonestar.backend.spring.security.uds.UdsLongTokenService;


/**
 * Configures Lone Star Spring Security Context and also controls how the authentication will be done for applicable requests.
 * IMPORTANT: This will be the default profile, unless otherwise it is overridden by a system/server property as below.
 * "-Dspring.profiles.active=<profile.name>"
 *
 * @author Shivakumar Gnanavel, Allen Wickham
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Profile("default")
public class LoneStarSecurityConfiguration extends BaseSecurityConfiguration
{
	/**
	 * Logger for this class.
	 */
	private static final Logger LOGGER = ESAPI.getLogger(LoneStarSecurityConfiguration.class);

	/**
	 * Holds value of UDS base Url.
	 */
	private String udsBasePath;


	@Override
	protected AuthenticationProvider createSsoAuthenticationProvider()
	{
		return new LoneStarTokenAuthenticationProvider(udsTokenService(), UdsLongTokenPreAuthenticatedAuthenticationToken.class);
	}


	/**
	 * Creates LoneStarAuthenticationFilter using authentication manager bean and required token handler.
	 *
	 * @return LoneStarAuthenticationFilter. This will never be {@code null}.
	 *
	 * @throws Exception Throws Exception when it occurs.
	 */
	@Override
	protected Filter createAuthenticationFilter() throws Exception
	{
		return new LoneStarAuthenticationFilter(authenticationManagerBean(), new UdsLongTokenHandler());
	}


	@Override
	protected void preConfigureAuthenticationProvider()
	{
		LOGGER.debug(Logger.EVENT_UNSPECIFIED, "LoneStar security is enabled.");
	}


	/**
	 * Bean to build UDS token service.
	 *
	 * @return UDS Long Token Service. This will never be {@code null}.
	 */
	@Bean
	public TokenService udsTokenService()
	{
		return new UdsLongTokenService(udsServiceProxy());
	}


	/**
	 * Bean to build UDS token service proxy.
	 *
	 * @return UDS token service proxy.
	 */
	@Bean
	public LoneStarSecurityServiceProxy udsServiceProxy()
	{
		return new LoneStarSecurityServiceProxyImpl(getUdsBasePath());
	}


	/**
	 * Gets UDS Base Path.
	 *
	 * @return UDS Base path.
	 */
	private String getUdsBasePath()
	{
		return udsBasePath;
	}


	/**
	 * Sets UDS Base Path by reading from properties file.
	 *
	 * @param udsBasePath The UDS Base Path. This must not be {@code null}.
	 */
	@Value("${lonestar.authentication.url:DEFAULT_UDS_BASE_URL}")
	public void setUdsBasePath(final String udsBasePath)
	{
		this.udsBasePath = udsBasePath;
	}
}
