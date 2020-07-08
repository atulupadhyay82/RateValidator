package com.thomsonreuters.extractvalidator.dto;


import lombok.Data;

import com.thomsonreuters.extractvalidator.dto.extract.TestAddress;


/**
 * The test case result for each address verified.
 *
 * @author Matt Godsey
 */
@Data
public final class TestCase
{
	/**
	 * The result of the test, passed or failed.
	 */
	private String testResult;

	/**
	 * The message for the test if failed, the reason of failure.
	 */
	private String message;

	/**
	 * Product code tested.
	 */
	private String productCode;

	/**
	 * Product name relating to the product code.
	 */
	private String productCategoryName;

	/**
	 * Tax type tested.
	 */
	private String taxType;

	/**
	 * Effective date used in test.
	 */
	private String effectiveDate;

	/**
	 * Model Scenario found effective Rate.
	 */
	private String modelScenarioTaxAmount;

	/**
	 * Extract accumulated rate for product, tax type, and location.
	 */
	private String extractTaxAmount;

	/**
	 * Gross amount of the test case.
	 */
	private String grossAmount;

	/**
	 * Address for this test case.
	 */
	private TestAddress address;

	private Long jurisdiction;

	private int scenarioExecuted;


}
