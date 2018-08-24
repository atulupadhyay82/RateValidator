package com.thomsonreuters.config.Security;


import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/**
 * Base class for all Security Context's. This holds all common elements and configurations.
 * IMPORTANT: Child class which has a default profile will be active, unless otherwise it is overridden by a system/server property as below.
 * "-Dspring.profiles.active=<profile.name>"
 *
 * @author Shivakumar Gnanavel
 */
public abstract class BaseSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}


	/**
	 * Bean to build unauthorized authentication entry point.
	 *
	 * @return Unauthorized authentication entry point. This will never be {@code null}.
	 */
	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint()
	{
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}


	/**
	 * Abstract method to create Authentication Filter.
	 *
	 * @return Filter filter based on child need. Will never be {@code null}.
	 *
	 * @throws Exception if it occurs.
	 */
	protected abstract Filter createAuthenticationFilter() throws Exception;


	/**
	 * Creates AuthenticationProvider based on children's need.
	 *
	 * @return AuthenticationProvider any child class of Authentication Provider. Will never be {@code null}.
	 */
	protected abstract AuthenticationProvider createSsoAuthenticationProvider();


	/**
	 * Method to include any actions that needs to be done before configuring AuthenticationProvider.
	 */
	protected abstract void preConfigureAuthenticationProvider();


	/**
	 * Configures Authentication manager by setting required authentication provider.
	 *
	 * @param auth Authentication manager builder object. This must not be {@code null}.
	 */
	@Override
	protected void configure(final AuthenticationManagerBuilder auth)
	{
		preConfigureAuthenticationProvider();
		auth.authenticationProvider(createSsoAuthenticationProvider());
	}


	/**
	 * Configures http security object with preferred authentication mechanisms and plugs in required filter onto
	 * filter chain. Also adds headers for cache control and sets X-Frame-options to SAMEORIGIN.
	 *
	 * @param http Authentication manager builder object. This must not be {@code null}.
	 *
	 * @throws Exception Throws Exception when it occurs.
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
		final Filter authenticationProcessingFilter = createAuthenticationFilter();

		http
				.antMatcher("/**")
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests().anyRequest().authenticated()
				.and()
				.anonymous().disable()
				.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint())
				.and()
				.headers().frameOptions().sameOrigin().cacheControl();

		http.addFilterBefore(authenticationProcessingFilter, BasicAuthenticationFilter.class);
	}
}
