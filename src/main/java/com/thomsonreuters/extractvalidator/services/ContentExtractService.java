package com.thomsonreuters.extractvalidator.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.dto.extract.content.ContentExtract;
import com.thomsonreuters.extractvalidator.util.AuthUtils;
import com.thomsonreuters.extractvalidator.util.ExtractRestClient;


/**
 * ContentExtractService Description.
 *
 * @author Matt Godsey
 */
@Service
public final class ContentExtractService
{
	private ExtractRestClient extractRestClient;


	@Autowired
	public ContentExtractService(final ExtractRestClient extractRestClient)
	{
		this.extractRestClient = extractRestClient;
	}


	public ContentExtract findContentExtract(final TestRun testRunData)
	{
		extractRestClient.setExtractBaseUrl(testRunData);

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final String credentialsStr = authentication.getCredentials().toString();
		final String authenticationHeaderValue = AuthUtils.prefixUDSCredentials(credentialsStr);

		return extractRestClient.findContentExtract(authenticationHeaderValue, testRunData);
	}
}
