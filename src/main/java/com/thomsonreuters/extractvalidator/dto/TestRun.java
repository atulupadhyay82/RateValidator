package com.thomsonreuters.extractvalidator.dto;


import lombok.Data;
import lombok.NonNull;

import java.util.List;


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

	private String testCompanyUUID;

	private String testCompanyID;

	private String externalCompanyID;
	private String soapUser;
	private String soapPassword;
	private String soapUri;

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
	 * List of different gross amounts to use for the line. If null a default value will be used.
	 */
	private List<String> lineGrossAmounts;

	/**
	 * Name of model scenario to use.
	 */
	@NonNull
	private String modelScenarioName;

	/**
	 * Flag to use for cleaning up the model scenario before processing if the name is the same as provided in model scenario name above.
	 */
	@NonNull
	private Boolean cleanupModelScenario;

	/**
	 * username for model scenario UI login
	 */
	@NonNull
	private String envCredentialsID;

	/**
	 * password for model scenario UI login
	 */

	@NonNull
	private String envCredentialsPassword;

	/**
	 * environment for model scenario UI login
	 */
	@NonNull
	private String environmentMS;

	/**
	 * skip the already processed jurisdictions in case of failures
	 */
	private int skipScenarios;

	/**
	 * selective running for no of jurisdictions passed in this parameter
	 */
	private List<String> jurisdictionKeys;

	private List<String> postalCodeList;

	private String taxType;

	private String productCategoryName;



	public TestRun(){}
}
