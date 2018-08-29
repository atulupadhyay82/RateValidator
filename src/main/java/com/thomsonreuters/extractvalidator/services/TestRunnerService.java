package com.thomsonreuters.extractvalidator.services;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomsonreuters.extractvalidator.dto.RunResults;
import com.thomsonreuters.extractvalidator.dto.TestResult;
import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.dto.extract.content.ContentExtract;
import com.thomsonreuters.extractvalidator.dto.extract.config.ExtractDefinition;
import com.thomsonreuters.extractvalidator.determination.CompanyConfig;


/**
 * TestRunnerService Description.
 *
 * @author Matt Godsey
 */
@Service
public final class TestRunnerService
{
	private final String NOT_RUN = "NOT RUN";
	private final String SUCCESS = "SUCCESS";
	private final String FAILED = "FAILED";
	private final String TEST_NOT_EXECUTED = "Test not executed.";
	private Map<String, TestResult> runResults;
	private CompanyConfigService companyConfigService;
	private ExtractConfigService extractConfigService;
	private ContentExtractService contentExtractService;


	@Autowired
	public TestRunnerService(final CompanyConfigService companyConfigService, final ExtractConfigService extractConfigService, final ContentExtractService contentExtractService)
	{
		this.companyConfigService = companyConfigService;
		this.extractConfigService = extractConfigService;
		this.contentExtractService = contentExtractService;
	}


	public RunResults runTest(final TestRun testRunData)
	{
		// Initialize the configured tests result map.
		initializeRunResults();

		TestResult testResult = new TestResult();

		try
		{

			// Find the company config, extract config, and content extract test run data passed in.
			final CompanyConfig companyConfig = companyConfigService.buildCompanyConfig(testRunData);
			final ExtractDefinition extractConfig = extractConfigService.findExtractConfig(testRunData);
			final ContentExtract contentExtract = contentExtractService.findContentExtract(testRunData);

			// Iterate through the configured tests and run them, capturing the results for each test.
			for (final Map.Entry<String, TestResult> entry : runResults.entrySet())
			{
				testResult = entry.getValue();

				executeTest(entry.getKey(), testResult, extractConfig, companyConfig, contentExtract);
			}
		}
		catch(final Exception exception)
		{
			testResult.setStatus(FAILED);
			testResult.setMessage(exception.getMessage());
		}

		return new RunResults(runResults, testRunData.getTestRunNumber());
	}


	private void executeTest(final String test, final TestResult testResult, final ExtractDefinition extractConfig, final CompanyConfig companyConfig, final ContentExtract contentExtract)
	{
		String result = NOT_RUN;

		switch (test)
		{
			case "Extract-Config-And-Company-Config":
				result = validateExtractConfigAndCompanyConfig(extractConfig, companyConfig);
				break;
			case "Extract-Config-And-Extract":
				result = validateExtractConfigAndExtract(extractConfig, contentExtract);
				break;
			case "Extract-And-Company-Config":
				result = validateContentExtractAndCompanyConfig(contentExtract, companyConfig);
				break;
			case "Extract-Authorities":
				result = validateContentExtractAuthorities(contentExtract);
				break;
			case "Extract-Jurisdictions":
				result = validateContentExtractJurisdictions(contentExtract);
				break;
			case "Extract-Products":
				result = validateContentExtractProducts(contentExtract);
				break;
			case "Extract-Rates":
				result = validateContentExtractRates(contentExtract);
				break;
			default:
				break;
		}

		if (SUCCESS.equals(result))
		{
			testResult.setStatus(SUCCESS);
			testResult.setMessage("PASSED");
		}
		else if (!NOT_RUN.equals(result))
		{
			testResult.setStatus(FAILED);
			testResult.setMessage(result);
		}
	}


	private String validateExtractConfigAndCompanyConfig(final ExtractDefinition extractConfig, final CompanyConfig companyConfig)
	{
		return SUCCESS;
	}


	private String validateExtractConfigAndExtract(final ExtractDefinition extractConfig, final ContentExtract contentExtract)
	{
		return SUCCESS;
	}


	private String validateContentExtractAndCompanyConfig(final ContentExtract contentExtract, final CompanyConfig companyConfig)
	{
		return SUCCESS;
	}


	private String validateContentExtractAuthorities(final ContentExtract contentExtract)
	{
		return SUCCESS;
	}


	private String validateContentExtractJurisdictions(final ContentExtract contentExtract)
	{
		return SUCCESS;
	}


	private String validateContentExtractProducts(final ContentExtract contentExtract)
	{
		return SUCCESS;
	}


	private String validateContentExtractRates(final ContentExtract contentExtract)
	{
		return SUCCESS;
	}


	/**
	 * Initialize the run result map with all configured tests.
	 */
	private void initializeRunResults()
	{
		runResults = new HashMap<>();

		runResults.put("Extract-Config-And-Company-Config", new TestResult(NOT_RUN, TEST_NOT_EXECUTED));
		runResults.put("Extract-Config-And-Extract", new TestResult(NOT_RUN, TEST_NOT_EXECUTED));
		runResults.put("Extract-And-Company-Config", new TestResult(NOT_RUN, TEST_NOT_EXECUTED));
		runResults.put("Extract-Authorities", new TestResult(NOT_RUN, TEST_NOT_EXECUTED));
		runResults.put("Extract-Jurisdictions", new TestResult(NOT_RUN, TEST_NOT_EXECUTED));
		runResults.put("Extract-Products", new TestResult(NOT_RUN, TEST_NOT_EXECUTED));
		runResults.put("Extract-Rates", new TestResult(NOT_RUN, TEST_NOT_EXECUTED));
	}
}
