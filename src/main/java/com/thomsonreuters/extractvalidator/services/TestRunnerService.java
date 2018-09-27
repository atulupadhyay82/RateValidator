package com.thomsonreuters.extractvalidator.services;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomsonreuters.extractvalidator.dto.RunResults;
import com.thomsonreuters.extractvalidator.dto.TestCase;
import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.dto.content.ClientZone;
import com.thomsonreuters.extractvalidator.dto.determination.UiAuthorityTaxDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompany;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompanyList;
import com.thomsonreuters.extractvalidator.dto.determination.UiLineTaxDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenario;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioLine;
import com.thomsonreuters.extractvalidator.dto.determination.UiScenarioResult;
import com.thomsonreuters.extractvalidator.dto.extract.TestAddress;
import com.thomsonreuters.extractvalidator.dto.extract.content.Address;
import com.thomsonreuters.extractvalidator.dto.extract.content.AuthorityData;
import com.thomsonreuters.extractvalidator.dto.extract.content.ContentExtract;
import com.thomsonreuters.extractvalidator.dto.extract.content.JurisdictionData;
import com.thomsonreuters.extractvalidator.dto.extract.content.LocationTreatmentData;
import com.thomsonreuters.extractvalidator.dto.extract.content.ProductAuthorityData;
import com.thomsonreuters.extractvalidator.dto.extract.content.ProductJurisdictionData;
import com.thomsonreuters.extractvalidator.dto.extract.content.Tier;
import com.thomsonreuters.extractvalidator.dto.extract.content.Treatment;
import com.thomsonreuters.extractvalidator.dto.extract.content.TreatmentData;
import com.thomsonreuters.extractvalidator.util.ExternalRestClient;
import com.thomsonreuters.extractvalidator.util.LocationTreatmentBuilder;
import com.thomsonreuters.extractvalidator.util.ModelScenarioUtil;


/**
 * Service to manage the test run, all parts of the test will be managed from here.
 *
 * @author Matt Godsey
 */
@Service
public final class TestRunnerService
{
	/**
	 * Test case status for when rates don't match.
	 */
	private static final String FAILED = "FAILED";

	/**
	 * Test case status for when rates are fine.
	 */
	private static final String PASSED = "PASSED";

	/**
	 * BigDecimal comparison result constant for greater than.
	 */
	private static final int IS_GREATER_THAN = 1;

	/**
	 * BigDecimal comparison result constant for less than.
	 */
	private static final int IS_LESS_THAN = -1;

	/**
	 * Double value to multiply the rate from the model scenario by to match up with the rates from the extract.
	 */
	private static final double RATE_MULTIPLIER = 100.0;

	/**
	 * The rest client to use when making REST calls to Content Extract endpoints or Determination Endpoints.
	 */
	private final ExternalRestClient externalRestClient;

	/**
	 * Logging handle for this class
	 */
	private static final Logger LOGGER = ESAPI.getLogger(TestRunnerService.class);


	/**
	 * Create the test runner service.
	 *
	 * @param externalRestClient The rest client to use when making REST calls to Content Extract endpoints or Determination Endpoints.
	 */
	@Autowired
	public TestRunnerService(final ExternalRestClient externalRestClient)
	{
		this.externalRestClient = externalRestClient;
	}


	/**
	 * Execute the validation using java classes for the extract and model scenario data.
	 *
	 * @param testRunData The test run data provided.
	 *
	 * @return A set of test case results based on the products and rates in the extract.
	 */
	public RunResults staticRunResults(final TestRun testRunData)
	{
		LOGGER.info(Logger.EVENT_UNSPECIFIED, "Starting test run for company: " + testRunData.getTestCompanyName());
		LOGGER.info(Logger.EVENT_UNSPECIFIED, "Retrieving extract for company: " + testRunData.getTestCompanyName());
		final ContentExtract extract = (ContentExtract) externalRestClient.findContentExtract(testRunData, true);
		final List<TestCase> testCases = new LinkedList<>();
		final RunResults runResults = new RunResults(testCases, testRunData.getTestRunNumber());

		LOGGER.info(Logger.EVENT_UNSPECIFIED, "Retrieving list of companies from Determination to get the company ID.");
		final UiCompanyList companies = externalRestClient.findCompanies(testRunData);
		final UiCompany testCompany = findTestCompany(testRunData, companies);

		if (null == testCompany)
		{
			final String message = String.format("Could not find company in Determination for test company named: %s", testRunData.getTestCompanyName());

			LOGGER.error(Logger.EVENT_FAILURE, message);

			final TestCase testCase = new TestCase();
			testCase.setMessage(message);
			runResults.getTestCases().add(testCase);
		}
		else
		{
			try
			{
				// If cleanup model scenario is set, check to see if a model scenario with the same name did not get deleted in a previous run. Find it and delete it.
				if (testRunData.getCleanupModelScenario())
				{
					cleanupOldModelScenario(testRunData, testCompany);
				}

				LOGGER.info(Logger.EVENT_UNSPECIFIED, "Running extract validation.");
				verifyContentExtractWithModelScenario(extract, testRunData, testCompany, testCases);
			}
			catch (final Exception ex)
			{
				final TestCase testCase = new TestCase();

				testCase.setTestResult("ERROR");
				testCase.setMessage(ex.getMessage());
				runResults.getTestCases().add(testCase);

				LOGGER.error(Logger.EVENT_FAILURE, "Something went wrong. Message: " + ex.getMessage());
			}
		}

		LOGGER.info(Logger.EVENT_UNSPECIFIED, "All runs complete, returning test cases.");

		return runResults;
	}


	/**
	 * Clean up old model scenario if the name and company match data passed in.
	 *
	 * @param testRunData The test run data provided by the user.
	 * @param testCompany The test company provided by the user.
	 */
	private void cleanupOldModelScenario(final TestRun testRunData, final UiCompany testCompany)
	{
		LOGGER.info(Logger.EVENT_UNSPECIFIED, "Finding model scenarios to clean up.");
		final List<UiModelScenario> modelScenarios = externalRestClient.findModelScenarios(testRunData);

		for (final UiModelScenario uiModelScenario : modelScenarios)
		{
			if (uiModelScenario.getScenarioName().equals(testRunData.getModelScenarioName()) && uiModelScenario.getCompany().getCompanyName().equals(testCompany.getCompanyName()))
			{
				LOGGER.info(Logger.EVENT_UNSPECIFIED, "Found model scenario with name: " + uiModelScenario.getScenarioName() + " for company: " + testCompany.getCompanyName());
				LOGGER.info(Logger.EVENT_UNSPECIFIED, "Deleting model scenario");
				externalRestClient.deleteModelScenario(testRunData, Collections.singletonList(uiModelScenario.getScenarioId().toString()));
			}
		}
	}


	/**
	 * Find the correct company based on the name from the test run data and the list of companies returned from Determination.
	 *
	 * @param testRunData The test run data passed in.
	 * @param companies The list of companies from Determination.
	 *
	 * @return A specific company that matches the name provided in the test run data.
	 */
	private UiCompany findTestCompany(final TestRun testRunData, final UiCompanyList companies)
	{
		UiCompany testCompany = null;

		for (final UiCompany company : companies.getCompanies())
		{
			if (company.getCompanyName().equals(testRunData.getTestCompanyName()))
			{
				testCompany = company;
			}
		}

		return testCompany;
	}


	/**
	 * Verify the extract by building a model scenario for each address in the extract. Each line of the scenario will be built for each product in the extract.
	 *
	 * @param contentExtract The extract data.
	 * @param testRunData The test run data passed in.
	 * @param company The company to use.
	 * @param testCases The list of test cases to populate.
	 */
	private void verifyContentExtractWithModelScenario(final ContentExtract contentExtract,
													   final TestRun testRunData,
													   final UiCompany company,
													   final List<TestCase> testCases)
	{
		final List<String> scenarioIds = new LinkedList<>();

		// Set effective date to now, unless effective date passed in is not null.
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final LocalDate parsedDate = null == testRunData.getEffectiveDate() ? LocalDate.now() : LocalDate.parse(testRunData.getEffectiveDate(), formatter);
		final LocalDateTime effectiveDate = parsedDate.atStartOfDay();
		final List<ClientZone> countryList = externalRestClient.getCountries(testRunData);
		final List<String> lineGrossAmounts = null == testRunData.getLineGrossAmounts() ? new LinkedList<>() : testRunData.getLineGrossAmounts();
		final UiModelScenarioDetail uiModelScenarioDetail = ModelScenarioUtil.buildNewModelScenario(company,
																									contentExtract,
																									effectiveDate,
																									lineGrossAmounts,
																									testRunData.getModelScenarioName());

		UiModelScenarioDetail newModelScenario;
		int scenarioCounter = 1;

		for (final Address address : contentExtract.getAddresses())
		{
			LOGGER.info(Logger.EVENT_UNSPECIFIED, "Building location treatment data.");
			final LocationTreatmentData locationTreatmentData = LocationTreatmentBuilder.buildLocationTreatmentData(address, contentExtract);

			if (!locationTreatmentData.getProductAuthorityData().isEmpty() || !locationTreatmentData.getProductJurisdictionData().isEmpty())
			{
				LOGGER.info(Logger.EVENT_UNSPECIFIED, "Building new scenario for Address: " + address.toString());
				uiModelScenarioDetail.setLocationList(ModelScenarioUtil.buildLocations(address, countryList));

				LOGGER.info(Logger.EVENT_UNSPECIFIED, "Processing scenario run: " + scenarioCounter++);
				if (null == uiModelScenarioDetail.getScenarioId())
				{
					LOGGER.info(Logger.EVENT_UNSPECIFIED, "Creating new scenario: " + uiModelScenarioDetail.getScenarioName());
					newModelScenario = externalRestClient.createModelScenario(testRunData, company.getCompanyId().toString(), uiModelScenarioDetail);
					uiModelScenarioDetail.setScenarioId(newModelScenario.getScenarioId());
				}
				else
				{
					LOGGER.info(Logger.EVENT_UNSPECIFIED, "Updating scenario: " + uiModelScenarioDetail.getScenarioName());
					externalRestClient.updateModelScenario(testRunData, company.getCompanyId().toString(), uiModelScenarioDetail);
				}

				LOGGER.info(Logger.EVENT_UNSPECIFIED, "Running scenario: " + uiModelScenarioDetail.getScenarioName());
				final UiScenarioResult scenarioResult = externalRestClient.runModelScenario(testRunData, uiModelScenarioDetail.getScenarioId().toString(), company.getCompanyId().toString());

				LOGGER.info(Logger.EVENT_UNSPECIFIED, "Comparing scenario rate to extract rate for location.");
				testCases.addAll(compareScenarioAndExtract(scenarioResult, uiModelScenarioDetail, locationTreatmentData, effectiveDate));
			}
		}

		if (null != uiModelScenarioDetail.getScenarioId())
		{
			scenarioIds.add(uiModelScenarioDetail.getScenarioId().toString());

			LOGGER.info(Logger.EVENT_UNSPECIFIED, "Deleting scenario.");
			externalRestClient.deleteModelScenario(testRunData, scenarioIds);
		}
	}


	/**
	 * Compare the model scenario results to the products and rates in the extract.
	 *
	 * @param scenarioResult The results from the model scenario calculation.
	 * @param modelScenarioDetail The details of the model scenario.
	 * @param locationTreatmentData The rates by authority and product from the extract.
	 * @param effectiveDate The effective date used for the calculation.
	 *
	 * @return Return a set of test cases, one for each product.
	 */
	private List<TestCase> compareScenarioAndExtract(final UiScenarioResult scenarioResult,
													 final UiModelScenarioDetail modelScenarioDetail,
													 final LocationTreatmentData locationTreatmentData,
													 final LocalDateTime effectiveDate)
	{
		final Set<String> lineGrossAmounts = new HashSet<>();

		for (final UiLineTaxDetail lineDetail : scenarioResult.getInvoiceTaxDetail().getLineTaxDetails())
		{
			lineGrossAmounts.add(lineDetail.getGrossAmount());
		}

		final Map<String, BigDecimal> scenarioProductRateMap = buildScenarioProductRateMap(scenarioResult, modelScenarioDetail.getScenarioLines());
		final Map<String, BigDecimal> extractProductRateMap = buildExtractProductRateMap(locationTreatmentData, effectiveDate, lineGrossAmounts);

		final List<TestCase> testCases = new LinkedList<>();

		for (final Map.Entry<String, BigDecimal> scenarioEntry : scenarioProductRateMap.entrySet())
		{
			for (final Map.Entry<String, BigDecimal> extractEntry : extractProductRateMap.entrySet())
			{
				if (extractEntry.getKey().equals(scenarioEntry.getKey()))
				{
					final TestCase testCase = new TestCase();

					buildTestCase(extractEntry, testCase, scenarioResult, scenarioEntry, effectiveDate, locationTreatmentData);
					testCases.add(testCase);

					break;
				}
			}
		}

		return testCases;
	}


	/**
	 * Build the map of products and rates for those products from the scenario results.  This is expecting there to be a line per product in the scenario.
	 *
	 * @param scenarioResult The result of the scenario calculation.
	 * @param lines The line data from the model scenario, which aren't contained in the results.
	 *
	 * @return A map of product to rate key pairs.
	 */
	private Map<String, BigDecimal> buildScenarioProductRateMap(final UiScenarioResult scenarioResult, final List<UiModelScenarioLine> lines)
	{
		final Map<String, BigDecimal> productRateMap = new HashMap<>();

		for (final UiModelScenarioLine scenarioLine : lines)
		{
			for (final UiLineTaxDetail lineTaxDetail : scenarioResult.getInvoiceTaxDetail().getLineTaxDetails())
			{
				if (lineTaxDetail.getLineNumber().compareTo(scenarioLine.getLineNumber()) == 0)
				{
					BigDecimal accumulatedTaxRate = BigDecimal.valueOf(0);

					for (final UiAuthorityTaxDetail authorityTaxDetail : lineTaxDetail.getAuthorityTaxDetails())
					{
						accumulatedTaxRate = accumulatedTaxRate.add(BigDecimal.valueOf(Double.parseDouble(authorityTaxDetail.getTaxRate())));
					}

					productRateMap.put(scenarioLine.getProductCode() + ":" + lineTaxDetail.getGrossAmount(), accumulatedTaxRate);
					break;
				}
			}
		}

		return productRateMap;
	}


	/**
	 * Build the map of products and rates for those products in the extract.
	 *
	 * @param locationTreatmentData The location treatment data containing the products, authorities, and rates from the extract for a given jurisdiction.
	 * @param effectiveDate The effective date of the calculation.
	 * @param lineGrossAmounts The list of gross amount from the scenario line used to determine the correct tier rates.
	 *
	 * @return A map of product to rate key pairs.
	 */
	private Map<String, BigDecimal> buildExtractProductRateMap(final LocationTreatmentData locationTreatmentData,
															   final LocalDateTime effectiveDate,
															   final Set<String> lineGrossAmounts)
	{
		final Map<String, BigDecimal> scenarioProductRateMap = new HashMap<>();

		if (locationTreatmentData.getProductJurisdictionData().isEmpty())
		{
			for (final ProductAuthorityData productAuthorityData : locationTreatmentData.getProductAuthorityData())
			{
				for (final String lineGrossAmount : lineGrossAmounts)
				{
					BigDecimal accumulatedRate = new BigDecimal(0);

					for (final AuthorityData authorityData : productAuthorityData.getAuthorityData())
					{
						for (final TreatmentData authorityTreatmentData : authorityData.getAuthorityTreatmentData())
						{
							final LocalDateTime fromDate = authorityTreatmentData.getFromDate();
							final LocalDateTime toDate = authorityTreatmentData.getToDate();

							if (effectiveDate.isAfter(fromDate) && (null == toDate || (effectiveDate.isBefore(toDate))))
							{
								accumulatedRate = accumulatedRate.add(calculateAccumulatedRate(authorityTreatmentData.getTreatments(), lineGrossAmount));
							}
						}
					}

					// Shift left by 2 decimal places.
					final BigDecimal shiftedRate = accumulatedRate.multiply(BigDecimal.valueOf(RATE_MULTIPLIER));

					scenarioProductRateMap.put(productAuthorityData.getProduct().getName() + ":" + lineGrossAmount, shiftedRate);
				}
			}
		}
		else
		{
			for (final ProductJurisdictionData productJurisdictionData : locationTreatmentData.getProductJurisdictionData())
			{
				for (final String lineGrossAmount : lineGrossAmounts)
				{
					BigDecimal accumulatedRate = new BigDecimal(0);

					for (final JurisdictionData jurisdictionData : productJurisdictionData.getJurisdictionData())
					{
						for (final TreatmentData treatmentData : jurisdictionData.getJurisdictionTreatmentData())
						{
							final LocalDateTime fromDate = treatmentData.getFromDate();
							final LocalDateTime toDate = treatmentData.getToDate();

							if (effectiveDate.isAfter(fromDate) && (null == toDate || (effectiveDate.isBefore(toDate))))
							{
								accumulatedRate = accumulatedRate.add(calculateAccumulatedRate(treatmentData.getTreatments(), lineGrossAmount));
							}
						}
					}

					// Shift left by 2 decimal.
					final BigDecimal shiftedRate = accumulatedRate.multiply(BigDecimal.valueOf(RATE_MULTIPLIER));

					scenarioProductRateMap.put(productJurisdictionData.getProduct().getName() + ":" + lineGrossAmount, shiftedRate);
				}
			}
		}

		return scenarioProductRateMap;
	}


	/**
	 * For the list of treatments, add the rates up for each treatment based on the gross amount for tier calculation.
	 *
	 * @param treatments The list of treatments to add up.
	 * @param scenarioGrossAmount The gross amount to take into account for tiers.
	 *
	 * @return Returns a single accumulated rate value.
	 */
	private BigDecimal calculateAccumulatedRate(final List<Treatment> treatments, final String scenarioGrossAmount)
	{
		BigDecimal accumulatedRate = new BigDecimal(0);

		for (final Treatment treatment : treatments)
		{
			if (null != treatment.getRate())
			{
				accumulatedRate = accumulatedRate.add(treatment.getRate());
			}
			else
			{
				final BigDecimal grossAmount = BigDecimal.valueOf(Double.parseDouble(scenarioGrossAmount));

				for (final Tier tier : treatment.getTierList())
				{
					if (grossAmount.compareTo(tier.getHighValue()) != IS_GREATER_THAN && grossAmount.compareTo(tier.getLowValue()) != IS_LESS_THAN)
					{
						accumulatedRate = accumulatedRate.add(tier.getRate());
						break;
					}
				}
			}
		}

		return accumulatedRate;
	}


	/**
	 * Compose the test case result for a specific product.
	 *
	 * @param extractEntry The data product and rate from the extract.
	 * @param testCase The test case to populate.
	 * @param scenarioResult The calculate scenario result.
	 * @param scenarioEntry The product and rate data from the scenario.
	 * @param effectiveDate The effective date used for the calculation and the treatment assignment.
	 * @param locationTreatmentData The location treatment data accumulated from the extract and holds the address and products to apply to the test case.
	 */
	private void buildTestCase(final Map.Entry<String, BigDecimal> extractEntry,
							   final TestCase testCase,
							   final UiScenarioResult scenarioResult,
							   final Map.Entry<String, BigDecimal> scenarioEntry,
							   final LocalDateTime effectiveDate,
							   final LocationTreatmentData locationTreatmentData)
	{
		final BigDecimal rate = scenarioEntry.getValue();
		final BigDecimal accumulatedRate = extractEntry.getValue();
		final int splitIndex = extractEntry.getKey().indexOf(':');
		final String productCode = extractEntry.getKey().substring(0, splitIndex);
		final String grossAmount = extractEntry.getKey().substring(splitIndex + 1);

		// Handle grouping by authority (use ProductAuthorityData) or by tax type (use ProductJurisdictionData).
		if (locationTreatmentData.getProductJurisdictionData().isEmpty())
		{
			for (final ProductAuthorityData productAuthorityData : locationTreatmentData.getProductAuthorityData())
			{
				if (productAuthorityData.getProduct().getName().equals(productCode))
				{
					testCase.setProductCategoryName(productAuthorityData.getProduct().getProductCategory());
					break;
				}
			}
		}
		else if (locationTreatmentData.getProductAuthorityData().isEmpty())
		{
			for (final ProductJurisdictionData productJurisdictionData : locationTreatmentData.getProductJurisdictionData())
			{
				if (productJurisdictionData.getProduct().getName().equals(productCode))
				{
					testCase.setProductCategoryName(productJurisdictionData.getProduct().getProductCategory());
					break;
				}
			}
		}

		testCase.setProductCode(productCode);
		testCase.setEffectiveDate(effectiveDate.toString());
		testCase.setModelScenarioRate(scenarioEntry.getValue().toString());
		testCase.setAccumulatedRate(extractEntry.getValue().toString());
		testCase.setGrossAmount(grossAmount);

		final TestAddress testAddress = new TestAddress();

		testAddress.setCity(locationTreatmentData.getAddress().getCity());
		testAddress.setCountry(locationTreatmentData.getAddress().getCountry());
		testAddress.setDistrict(locationTreatmentData.getAddress().getDistrict());
		testAddress.setGeocode(locationTreatmentData.getAddress().getGeocode());
		testAddress.setPostalCode(locationTreatmentData.getAddress().getPostalCode());
		testAddress.setCounty(locationTreatmentData.getAddress().getCounty());
		testAddress.setProvince(locationTreatmentData.getAddress().getProvince());
		testAddress.setState(locationTreatmentData.getAddress().getState());

		testCase.setAddress(testAddress);

		if (null == accumulatedRate)
		{
			testCase.setTestResult(FAILED);
			testCase.setMessage("Accumulated rate has been set to null somehow, investigate.");
		}
		else if (null == rate)
		{
			testCase.setTestResult(FAILED);
			testCase.setMessage("Model Scenario failed, scenario messages: " + scenarioResult.getInvoiceTaxDetail().getMessages());
		}
		else if (accumulatedRate.compareTo(rate) == 0)
		{
			testCase.setTestResult(PASSED);
			testCase.setMessage("Effective rate is: " + rate + " for Gross Amount: " + grossAmount);
		}
		else if (accumulatedRate.equals(BigDecimal.valueOf(0)) && !rate.equals(BigDecimal.valueOf(0)))
		{
			testCase.setTestResult(FAILED);
			testCase.setMessage("The rates do not match. The accumulated rate was 0, perhaps this is a tiered rate?");
		}
		else
		{
			testCase.setTestResult(FAILED);
			testCase.setMessage("The rates do not match Scenario Rate: " + rate + " Accumulated Rate: " + accumulatedRate.toString());
		}
	}
}
