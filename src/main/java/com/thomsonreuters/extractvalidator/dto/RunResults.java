package com.thomsonreuters.extractvalidator.dto;


import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * RunResults Description.
 *
 * @author Matt Godsey
 */
@Data
@AllArgsConstructor
public final class RunResults
{
	private List<TestCase> testCases;
	private String testRunNumber;
}
