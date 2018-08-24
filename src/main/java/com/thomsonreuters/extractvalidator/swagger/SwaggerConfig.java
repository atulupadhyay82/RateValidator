package com.thomsonreuters.extractvalidator.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuring our Swagger 2.
 * This config includes the following:
 * <li>Endpoints exposed by Swagger 2</li>
 * <li>API Metadata</li>
 * <li>Token Security Scheme</li>
 *
 * @author Matt Godsey
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
	/**
	 * Defines Docket bean.
	 *
	 * @return Docket bean
	 */
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
					   .select()
					   .apis(RequestHandlerSelectors.any())
					   .paths(PathSelectors.any())
					   .build();
	}
}
