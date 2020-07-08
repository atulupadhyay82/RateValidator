package com.thomsonreuters.extractvalidator.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


/**
 * Data transfer object to house the test results for the extract validation.
 *
 * @author Matt Godsey
 */
@Data
@AllArgsConstructor
public final class RunResults
{
	/**
	 * Result of the test run including all test cases.
	 */
	private List<TestCase> testCases;

	/**
	 * The test run number to track which test run this was.
	 */
	private String testRunNumber;


}
