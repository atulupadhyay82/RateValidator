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
	 * Tax type tested.
	 */
	private String taxType;

	/**
	 * Effective date used in test.
	 */
	private String effectiveDate;

	/**
	 * Found effective Rate.
	 */
	private String effectiveRate;

	/**
	 * Extract accumulated rate for product, tax type, and location.
	 */
	private String accumulatedRate;

	/**
	 * Address for this test case.
	 */
	private TestAddress address;
}
