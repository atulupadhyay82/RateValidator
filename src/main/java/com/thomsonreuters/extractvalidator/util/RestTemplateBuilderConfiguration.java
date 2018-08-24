package com.thomsonreuters.extractvalidator.util;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * This class will be detected by Spring Boot and defines beans required by classes in this package.
 *
 * @author Neal Schultz
 */
@Configuration
public class RestTemplateBuilderConfiguration
{
	/**
	 * Allows Spring Boot to inject a Rest Template wherever specified by classes in this package.
	 *
	 * @param builder The builder to be used to create the Rest Template.
	 *
	 * @return The Rest Template to inject into this class.
	 */
	@Bean
	public RestTemplate restTemplate(final RestTemplateBuilder builder)
	{
		return builder.build();
	}
}
