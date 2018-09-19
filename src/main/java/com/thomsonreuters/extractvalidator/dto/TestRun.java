package com.thomsonreuters.extractvalidator.dto;


import java.util.List;

import lombok.Data;
import lombok.NonNull;


/**
 * Data transfer object for the test runner service endpoint.  This provides a way to pass required information to the service when running.
 *
 * @author Matt Godsey
 */
@Data
public final class TestRun
{
	/**
	 * Test run number used for tracking test runs. Will ignore if not set.
	 */
	private String testRunNumber;

	/**
	 * Company named used in test, and to lookup content extract. Also used to process model scenarios.
	 */
	@NonNull
	private String testCompanyName;

	/**
	 * Extract config name, used to find the content extract.
	 */
	@NonNull
	private String testExtractConfigName;

	/**
	 * Base URL for determination REST calls. Format is http://hostname:port. Alias does not seem to work in place of hostname:port.
	 */
	@NonNull
	private String determinationBaseUrl;

	/**
	 * Base URL for content extract REST calls. Format is http://hostname:port/services/rest. Can use alias for hostname:port.
	 */
	@NonNull
	private String contentExtractBaseUrl;

	/**
	 * UDS token for use in making public REST service calls to Determination.
	 */
	@NonNull
	private String udsToken;

	/**
	 * User name of native Determination user, for use in making calls to get the Content Extract.
	 */
	@NonNull
	private String serviceUser;

	/**
	 * Password of native Determination user, for use in making calls to get the Content Extract.
	 */
	@NonNull
	private String servicePassword;

	/**
	 * Results of model scenario runs compared with content extract rates.
	 */
	private List<TestCase> testCases;

	/**
	 * Gross amount to use for the line. If null will be ignored.
	 */
	private String lineGrossAmount;

	/**
	 * Effective date to use for model scenario calculations and rate comparisons.
	 */
	private String effectiveDate;

	/**
	 * Name of model scenario to use.
	 */
	@NonNull
	private String modelScenarioName;

	/**
	 * Flag to use for cleaning up the model scenario before processing if the name is the same as provided in model scenario name above.
	 */
	@NonNull
	private boolean cleanupModelScenario;
}
