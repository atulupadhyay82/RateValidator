package com.thomsonreuters.extractvalidator.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.dto.extract.config.ExtractDefinition;
import com.thomsonreuters.extractvalidator.util.AuthUtils;
import com.thomsonreuters.extractvalidator.util.ExtractRestClient;


/**
 * ExtractConfigService Description.
 *
 * @author Matt Godsey
 */
@Service
public final class ExtractConfigService
{
	private ExtractRestClient extractRestClient;


	@Autowired
	public ExtractConfigService(final ExtractRestClient extractRestClient)
	{
		this.extractRestClient = extractRestClient;
	}


	public ExtractDefinition findExtractConfig(final TestRun testRunData)
	{
		// Set the Content Extract base URL for this test run.
		extractRestClient.setExtractBaseUrl(testRunData);

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final String credentialsStr = authentication.getCredentials().toString();
		final String authenticationHeaderValue = AuthUtils.prefixUDSCredentials(credentialsStr);
		final List<ExtractDefinition> extractDefinitions = extractRestClient.findExtractDefinitions(authenticationHeaderValue);

		ExtractDefinition extractConfig = null;

		for (final ExtractDefinition extractDefinition : extractDefinitions)
		{
			if (extractDefinition.getCompanyName().equals(testRunData.getTestCompanyName()) && extractDefinition.getName().equals(testRunData.getTestExtractConfigName()))
			{
				extractConfig = extractDefinition;
				break;
			}
		}

		return extractConfig;
	}
}
